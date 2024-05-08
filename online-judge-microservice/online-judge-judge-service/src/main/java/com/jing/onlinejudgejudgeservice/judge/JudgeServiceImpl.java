package com.jing.onlinejudgejudgeservice.judge;

import cn.hutool.json.JSONUtil;

import com.jing.onlinejudgecommon.common.ErrorCode;
import com.jing.onlinejudgecommon.exception.BusinessException;
import com.jing.onlinejudgejudgeservice.judge.codesandbox.CodeSandbox;
import com.jing.onlinejudgejudgeservice.judge.codesandbox.CodeSandboxFactory;
import com.jing.onlinejudgejudgeservice.judge.codesandbox.CodeSandboxProxy;
import com.jing.onlinejudgejudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.jing.onlinejudgejudgeservice.judge.strategy.JudgeContext;
import com.jing.onlinejudgejudgeservice.judge.strategy.JudgeStrategy;
import com.jing.onlinejudgemodel.model.codesandbox.ExecuteCodeRequest;
import com.jing.onlinejudgemodel.model.codesandbox.ExecuteCodeResponse;
import com.jing.onlinejudgemodel.model.codesandbox.JudgeInfo;
import com.jing.onlinejudgemodel.model.dto.question.JudgeCase;
import com.jing.onlinejudgemodel.model.entity.Question;
import com.jing.onlinejudgemodel.model.entity.QuestionSubmit;
import com.jing.onlinejudgemodel.model.enums.QuestionSubmitStatusEnum;
import com.jing.onlinejudgeserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService{

    @Resource
    QuestionFeignClient questionFeignClient;

    @Resource
    JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        // 1 传入题目的提交id,获取到对应的题目 提交信息(包含代码 编程语言等)
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if(questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        // 2 如果题目提交状态不为等待中,就不用重复执行了
        Integer status = questionSubmit.getStatus();
        if(!status.equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题中");
        }
        // 3 更改判题(题目提交)的状态为'判题中',防止重复执行,也能让用户即时看到状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmit);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目状态更新错误 ");
        }
        // 4 调用沙箱 获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        //  获取输入用例
        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList).build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        // 5 根据沙箱的执行结果,设置题目的判题状态和信息
        List<String> outputList = executeCodeResponse.getOutputList();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo( executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);
        
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 7 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        boolean updateEnd = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if(!updateEnd){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"题目更新错误");
        }
        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionId);
        return questionSubmitResult;
    }
}

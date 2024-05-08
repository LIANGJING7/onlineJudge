package com.jing.onlinejudgejudgeservice.judge;

import com.jing.onlinejudgejudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.jing.onlinejudgejudgeservice.judge.strategy.JudgeContext;
import com.jing.onlinejudgejudgeservice.judge.strategy.JudgeStrategy;
import com.jing.onlinejudgemodel.model.codesandbox.JudgeInfo;
import com.jing.onlinejudgemodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Component;

@Component
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if("java".equals(language)){
            judgeStrategy = new DefaultJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    };
}

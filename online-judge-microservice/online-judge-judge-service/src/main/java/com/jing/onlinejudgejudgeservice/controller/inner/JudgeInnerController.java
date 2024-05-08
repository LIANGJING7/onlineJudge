package com.jing.onlinejudgejudgeservice.controller.inner;

import com.jing.onlinejudgejudgeservice.judge.JudgeService;
import com.jing.onlinejudgemodel.model.entity.QuestionSubmit;
import com.jing.onlinejudgeserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {
    @Resource
    private JudgeService judgeService;

    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") Long questionSubmitId){
        return judgeService.doJudge(questionSubmitId);
    }
}

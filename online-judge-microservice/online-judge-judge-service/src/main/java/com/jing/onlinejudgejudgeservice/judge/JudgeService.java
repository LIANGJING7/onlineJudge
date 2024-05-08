package com.jing.onlinejudgejudgeservice.judge;


import com.jing.onlinejudgemodel.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit doJudge(Long questionSubmitId);

}

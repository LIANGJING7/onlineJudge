package com.jing.onlinejudgejudgeservice.judge.strategy;


import com.jing.onlinejudgemodel.model.codesandbox.JudgeInfo;
import com.jing.onlinejudgemodel.model.dto.question.JudgeCase;
import com.jing.onlinejudgemodel.model.entity.Question;
import com.jing.onlinejudgemodel.model.entity.QuestionSubmit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用于定义在策略中传递的参数
 */
@Data
@NoArgsConstructor
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}

package com.online.judge.judge;

import com.online.judge.judge.strategy.DefaultJudgeStrategy;
import com.online.judge.judge.strategy.JudgeStrategy;
import com.online.judge.judge.strategy.JavaLanguageJudgeStrategy;
import com.online.judge.judge.strategy.JudgeContext;
import com.online.judge.judge.codesandbox.model.JudgeInfo;
import com.online.judge.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}

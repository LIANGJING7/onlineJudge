package com.jing.onlinejudgejudgeservice.judge.strategy;


import com.jing.onlinejudgemodel.model.codesandbox.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}

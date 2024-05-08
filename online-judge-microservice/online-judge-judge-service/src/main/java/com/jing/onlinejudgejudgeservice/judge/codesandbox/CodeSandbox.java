package com.jing.onlinejudgejudgeservice.judge.codesandbox;


import com.jing.onlinejudgemodel.model.codesandbox.ExecuteCodeRequest;
import com.jing.onlinejudgemodel.model.codesandbox.ExecuteCodeResponse;

public interface CodeSandbox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}

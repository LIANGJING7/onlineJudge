package com.jing.onlinejudgejudgeservice.judge.codesandbox.impl;


import com.jing.onlinejudgejudgeservice.judge.codesandbox.CodeSandbox;
import com.jing.onlinejudgemodel.model.codesandbox.ExecuteCodeRequest;
import com.jing.onlinejudgemodel.model.codesandbox.ExecuteCodeResponse;

public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}

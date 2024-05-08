package com.jing.onlinejudgejudgeservice.judge.codesandbox;


import com.jing.onlinejudgejudgeservice.judge.codesandbox.impl.ExampleCodeSandbox;
import com.jing.onlinejudgejudgeservice.judge.codesandbox.impl.RemoteCodeSandbox;
import com.jing.onlinejudgejudgeservice.judge.codesandbox.impl.ThirdPartyCodeSandbox;

public class CodeSandboxFactory {
    public static CodeSandbox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();

        }
    }
}

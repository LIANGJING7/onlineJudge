package com.jing.onlinejudgejudgeservice.judge.codesandbox.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import com.jing.onlinejudgecommon.common.ErrorCode;
import com.jing.onlinejudgecommon.exception.BusinessException;
import com.jing.onlinejudgejudgeservice.judge.codesandbox.CodeSandbox;
import com.jing.onlinejudgemodel.model.codesandbox.ExecuteCodeRequest;
import com.jing.onlinejudgemodel.model.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

public class RemoteCodeSandbox implements CodeSandbox {
    public static final String AUTH_REQUEST_HEADER = "auth";

    public static final String AUTH_REQUEST_SECRET = "secretKey";
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8000/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER,md5.digestHex(AUTH_REQUEST_SECRET))
                .body(json)
                .execute()
                .body();
        System.out.println(responseStr);
        if (StringUtils.isBlank(responseStr)){
             throw  new BusinessException(ErrorCode.API_REQUEST_ERROR,"executeCode remoteSandbox error,message =" + responseStr);
        }
        return JSONUtil.toBean(responseStr,ExecuteCodeResponse.class);
    }
}

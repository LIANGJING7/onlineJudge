package com.jing.onlinejudgemodel.model.codesandbox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {

    private List<String> inputList;

    /**
     * 提交代码
     * @param inputList
     */
    private String code;

    /**
     * 代码使用语言
     * @param code
     */
    private String language;
}

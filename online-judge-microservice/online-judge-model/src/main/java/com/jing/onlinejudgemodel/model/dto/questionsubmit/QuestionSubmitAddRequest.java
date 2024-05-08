package com.jing.onlinejudgemodel.model.dto.questionsubmit;



import lombok.Data;

import java.io.Serializable;


/**
 * 创建请求
 *
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {



    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;



    private static final long serialVersionUID = 1L;
}
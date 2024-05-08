package com.jing.onlinejudgemodel.model.dto.questionsubmit;

import com.jing.onlinejudgecommon.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author jing
 * @from jing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 创建用户id
     */
    private Long userId;

    /**
     * 编程语言
     */
    private String language;


    /**
     * 提交状态(0-待判题,1-判题中,2-成功,3-失败)
     */
    private Integer status;


    private static final long serialVersionUID = 1L;
}
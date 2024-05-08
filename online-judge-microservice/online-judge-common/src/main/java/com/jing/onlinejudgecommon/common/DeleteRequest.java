package com.jing.onlinejudgecommon.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author jing
 * @from jing
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
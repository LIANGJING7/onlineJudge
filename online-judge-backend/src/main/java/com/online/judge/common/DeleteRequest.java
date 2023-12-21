package com.online.judge.common;

import java.io.Serializable;
import lombok.Data;

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
package com.jing.onlinejudgemodel.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableName;

import com.jing.onlinejudgemodel.model.dto.question.JudgeCase;
import com.jing.onlinejudgemodel.model.dto.question.JudgeConfig;
import com.jing.onlinejudgemodel.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目封装类
 *
 * @TableName question
 */
@TableName(value = "question")
@Data
public class QuestionVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表(json数组)
     */
    private List<String> tags;


    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 题目答案
     */
    private String answer;
    /**
     * 判题配置 (json对象)
     */
    private JudgeConfig judgeConfig;

    /**
     * 判题配置 (json对象)
     */
    private List<JudgeCase> judgeCase;

    /**
     * 创建用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建题目人信息
     */
    private UserVO userVO;

    /**
     * 包装类转对象
     *
     * @param questionVO
     * @return
     */
    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);
        List<String> tagList = questionVO.getTags();
        if (tagList != null) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }
        JudgeConfig judgeConfig = questionVO.getJudgeConfig();
        if (judgeConfig != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeConfig));
        }
        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        String tags = question.getTags();
        if (tags != null) {
            List<String> tagList = JSONUtil.toList(tags, String.class);
            questionVO.setTags(tagList);
        }
        String judgeConfigStr = question.getJudgeConfig();
        if (judgeConfigStr != null) {
            JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
            questionVO.setJudgeConfig(judgeConfig);
        }
        String judgeCase1 = question.getJudgeCase();
        if (judgeCase1 != null) {
            List<JudgeCase> judgeCases = JSONUtil.toList(judgeCase1, JudgeCase.class);
            questionVO.setJudgeCase(judgeCases);
        }
        return questionVO;
    }

    private static final long serialVersionUID = 1L;
}
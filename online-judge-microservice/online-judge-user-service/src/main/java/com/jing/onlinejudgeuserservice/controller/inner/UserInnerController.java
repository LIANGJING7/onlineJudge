package com.jing.onlinejudgeuserservice.controller.inner;

import com.jing.onlinejudgemodel.model.entity.User;
import com.jing.onlinejudgemodel.model.enums.UserRoleEnum;
import com.jing.onlinejudgeserviceclient.service.UserFeignClient;
import com.jing.onlinejudgeuserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 该服务仅内部调用,不是给前端
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {
    @Resource
    private UserService userService;

    /**
     * 根据id获取当前用户
     */
    @GetMapping("/get/id")
    @Override
    public User getById(@RequestParam("userId") long userId) {

        return userService.getById(userId);
    }

    /**
     * 根据id获取用户列表
     */
    @GetMapping("/get/ids")
    @Override
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList) {
        return userService.listByIds(idList);
    }
}

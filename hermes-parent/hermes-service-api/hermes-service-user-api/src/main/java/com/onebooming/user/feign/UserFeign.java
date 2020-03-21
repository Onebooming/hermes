package com.onebooming.user.feign;

import com.onebooming.user.pojo.User;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-17 20:42
 * @ApiNote
 */
@FeignClient(name = "user")
@RequestMapping("/user")
public interface UserFeign {
    /***
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    Result<User> findById(@PathVariable String id);
}

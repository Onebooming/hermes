package com.onebooming.goods.feign;

import com.onebooming.goods.pojo.Category;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-12 18:05
 * @ApiNote
 */
@FeignClient("goods")
@RequestMapping("/category")
public interface CategoryFeign {
    /**
     * 获取分类的对象信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable(name = "id") Integer id);

}
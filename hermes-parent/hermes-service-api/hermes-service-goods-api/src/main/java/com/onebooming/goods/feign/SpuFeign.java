package com.onebooming.goods.feign;

import com.onebooming.goods.pojo.Spu;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-12 18:06
 * @ApiNote
 */
@FeignClient("goods")
@RequestMapping("/spu")
public interface SpuFeign {
    /***
     * 根据SpuID查询Spu信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable(name = "id") Long id);


}

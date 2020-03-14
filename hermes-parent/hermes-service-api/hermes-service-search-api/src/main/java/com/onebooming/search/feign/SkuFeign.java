package com.onebooming.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-09 22:59
 * @ApiNote
 */
@FeignClient(name = "search")
@RequestMapping("/search")
public interface SkuFeign {

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @GetMapping
    Map search(@RequestParam(required = false) Map searchMap);
}

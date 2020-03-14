package com.onebooming.controller;

import com.onebooming.service.SkuService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-27 11:37
 * @ApiNote
 */
@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 导入数据
     * @return
     */
    @GetMapping("/import")
    public Result importData(){
        skuService.importAllData();
        return new Result(true, StatusCode.OK,"导入数据到索引库中成功！");
    }

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @GetMapping
    public Map search(@RequestParam(required = false) Map<String,String> searchMap){
        //调用service实现搜索
        Map resultMap = skuService.search(searchMap);
        return resultMap;
    }

//    /**
//     * 搜索
//     * @param searchMap
//     * @return
//     */
//    @PostMapping
//    public Result search(@RequestBody(required = false) Map searchMap){
//        Map resultMap = skuService.search(searchMap);
//        return new Result(true, StatusCode.OK, "elasticsearch查询成功", resultMap);
//    }
}
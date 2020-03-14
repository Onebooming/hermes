package com.onebooming.search.controller;

import com.onebooming.search.feign.SkuFeign;
import com.onebooming.search.pojo.SkuInfo;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-09 23:07
 * @ApiNote
 */
@Controller
@RequestMapping(value = "/search")
public class SkuController {
    @Autowired
    private SkuFeign skuFeign;

    @GetMapping(value = "/list")
    public String search(@RequestParam(required = false) Map searchMap, Model model){
        // 替换特殊字符
        handleSearchMap(searchMap);
        //调用changgou-service-search微服务
        Map resultMap = skuFeign.search(searchMap);
        //搜索数据结果
        model.addAttribute("result",resultMap);
        //将搜索条件存储，用于页面回显数据
        model.addAttribute("searchMap",searchMap);
        //请求地址的拼接和处理:需要两个url，一个url不带排序规则，一个url带排序规则
        String[] urls = url(searchMap);
        model.addAttribute("url",urls[0]);
        model.addAttribute("sorturl",urls[1]);
        //分页计算
        Page<SkuInfo> page = new Page<SkuInfo>(
                Long.parseLong(resultMap.get("totalPages").toString()), //总记录数
                Integer.parseInt(resultMap.get("pageNum").toString()),  //当前页
                Integer.parseInt(resultMap.get("pageSize").toString())  //每页显示条数
        );
        model.addAttribute("page",page);
        return "search";
    }

    /**
     * url组装和处理
     * @param searchMap
     * @return
     */
    private String[] url(Map<String, String> searchMap) {// { spec_网络:"移动4G","keywords":"华为"}
        String url = "/search/list"; // a/b?id=1&
        String sortUrl = "/search/list"; // a/b?id=1&
        if (searchMap != null) {
            url += "?";
            sortUrl += "?";
            for (Map.Entry<String, String> stringStringEntry : searchMap.entrySet()) {
                String key = stringStringEntry.getKey();//key搜索条件对象
                String value = stringStringEntry.getValue();//value搜索的值
                if(stringStringEntry.getKey().equalsIgnoreCase("pageNum")){
                    continue;
                }
                url += key + "=" + value + "&";

                //如果是排序 则 跳过 拼接排序的地址 因为有数据
                if(stringStringEntry.getKey().equals("sortField") || stringStringEntry.getKey().equals("sortRule")){
                    continue;
                }
                sortUrl += key + "=" + value + "&";

            }
            if(url.lastIndexOf("&")!=-1)
                url = url.substring(0, url.lastIndexOf("&"));
            if(sortUrl.lastIndexOf("&")!=-1)
                sortUrl = sortUrl.substring(0, sortUrl.lastIndexOf("&"));
        }
        return new String[]{url,sortUrl};
    }

    public void handleSearchMap(Map<String,String> map){
        if (map != null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(entry.getKey().startsWith("spec_")){
                    entry.setValue(entry.getValue().replace("+","%2B"));
                }
            }
        }
    }
}

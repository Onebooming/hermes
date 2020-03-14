package com.onebooming.service;

import java.util.Map;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-02-27 10:59
 * @ApiNote
 */
public interface SkuService {
    /***
     * 导入SKU数据到索引库中
     */
    void importSku();
    void importAllData();

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    Map<String, Object> search(Map<String,String> searchMap);
}

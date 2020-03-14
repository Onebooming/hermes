package com.onebooming.item.service;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-12 18:11
 * @ApiNote
 */
public interface PageService {
    /**
     * 根据商品的ID 生成静态页
     * @param spuId
     */
    public void createPageHtml(Long spuId) ;
}

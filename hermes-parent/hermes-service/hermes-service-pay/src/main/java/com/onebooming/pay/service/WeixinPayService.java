package com.onebooming.pay.service;

import java.util.Map;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-22 17:21
 * @ApiNote
 */
public interface WeixinPayService {
    /*****
     * 创建二维码
     * @param out_trade_no : 客户端自定义订单编号
     * @param total_fee    : 交易金额,单位：分
     * @return
     */
    public Map createNative(String out_trade_no, String total_fee);

    /***
     * 查询订单状态
     * @param out_trade_no : 客户端自定义订单编号
     * @return
     */
    public Map queryPayStatus(String out_trade_no);
}

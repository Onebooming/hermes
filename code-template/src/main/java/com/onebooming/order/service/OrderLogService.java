package com.onebooming.order.service;
import com.onebooming.order.pojo.OrderLog;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:Onebooming
 * @Description:OrderLog业务层接口
 * @Date 2020/2/2 0:20
 *****/
public interface OrderLogService {

    /***
     * OrderLog多条件分页查询
     * @param orderLog
     * @param page
     * @param size
     * @return
     */
    PageInfo<OrderLog> findPage(OrderLog orderLog, int page, int size);

    /***
     * OrderLog分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<OrderLog> findPage(int page, int size);

    /***
     * OrderLog多条件搜索方法
     * @param orderLog
     * @return
     */
    List<OrderLog> findList(OrderLog orderLog);

    /***
     * 删除OrderLog
     * @param id
     */
    void delete(String id);

    /***
     * 修改OrderLog数据
     * @param orderLog
     */
    void update(OrderLog orderLog);

    /***
     * 新增OrderLog
     * @param orderLog
     */
    void add(OrderLog orderLog);

    /**
     * 根据ID查询OrderLog
     * @param id
     * @return
     */
     OrderLog findById(String id);

    /***
     * 查询所有OrderLog
     * @return
     */
    List<OrderLog> findAll();
}
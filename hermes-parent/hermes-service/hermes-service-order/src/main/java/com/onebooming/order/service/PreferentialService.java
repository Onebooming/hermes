package com.onebooming.order.service;

import com.github.pagehelper.PageInfo;
import com.onebooming.order.pojo.Preferential;

import java.util.List;

/****
 * @Author:Onebooming
 * @Description:Preferential业务层接口
 * @Date 2020/2/2 0:20
 *****/
public interface PreferentialService {

    /***
     * Preferential多条件分页查询
     * @param preferential
     * @param page
     * @param size
     * @return
     */
    PageInfo<Preferential> findPage(Preferential preferential, int page, int size);

    /***
     * Preferential分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Preferential> findPage(int page, int size);

    /***
     * Preferential多条件搜索方法
     * @param preferential
     * @return
     */
    List<Preferential> findList(Preferential preferential);

    /***
     * 删除Preferential
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Preferential数据
     * @param preferential
     */
    void update(Preferential preferential);

    /***
     * 新增Preferential
     * @param preferential
     */
    void add(Preferential preferential);

    /**
     * 根据ID查询Preferential
     * @param id
     * @return
     */
     Preferential findById(Integer id);

    /***
     * 查询所有Preferential
     * @return
     */
    List<Preferential> findAll();
}

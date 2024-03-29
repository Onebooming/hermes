package com.onebooming.service;

import com.github.pagehelper.PageInfo;
import com.onebooming.goods.pojo.Para;

import java.util.List;

/****
 * @Author:Onebooming
 * @Description:Para业务层接口
 * @Date 2020/2/2 0:20
 *****/
public interface ParaService {
    /***
     * 根据分类ID查询参数列表
     * @param id
     * @return
     */
    List<Para> findByCategoryId(Integer id);


    /***
     * Para多条件分页查询
     * @param para
     * @param page
     * @param size
     * @return
     */
    PageInfo<Para> findPage(Para para, int page, int size);

    /***
     * Para分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Para> findPage(int page, int size);

    /***
     * Para多条件搜索方法
     * @param para
     * @return
     */
    List<Para> findList(Para para);

    /***
     * 删除Para
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Para数据
     * @param para
     */
    void update(Para para);

    /***
     * 新增Para
     * @param para
     */
    void add(Para para);

    /**
     * 根据ID查询Para
     * @param id
     * @return
     */
     Para findById(Integer id);

    /***
     * 查询所有Para
     * @return
     */
    List<Para> findAll();
}

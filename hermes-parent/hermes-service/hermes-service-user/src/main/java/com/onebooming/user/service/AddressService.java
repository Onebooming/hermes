package com.onebooming.user.service;

import com.github.pagehelper.PageInfo;
import com.onebooming.user.pojo.Address;

import java.util.List;

/****
 * @Author:Onebooming
 * @Description:Address业务层接口
 * @Date 2020/2/2 0:20
 *****/
public interface AddressService {


    /***
     * 收件地址查询
     * @param username
     * @return
     */
    List<Address> list(String username);

    /***
     * Address多条件分页查询
     * @param address
     * @param page
     * @param size
     * @return
     */
    PageInfo<Address> findPage(Address address, int page, int size);

    /***
     * Address分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Address> findPage(int page, int size);

    /***
     * Address多条件搜索方法
     * @param address
     * @return
     */
    List<Address> findList(Address address);

    /***
     * 删除Address
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Address数据
     * @param address
     */
    void update(Address address);

    /***
     * 新增Address
     * @param address
     */
    void add(Address address);

    /**
     * 根据ID查询Address
     * @param id
     * @return
     */
     Address findById(Integer id);

    /***
     * 查询所有Address
     * @return
     */
    List<Address> findAll();
}

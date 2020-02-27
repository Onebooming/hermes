package com.onebooming.dao;
import com.onebooming.goods.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:Onebooming
 * @Description:Category的Dao
 * @Date 2020/2/20 0:12
 *****/
public interface CategoryMapper extends Mapper<Category> {

}

package com.onebooming.dao;
import com.onebooming.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:Onebooming
 * @Description:Brand的Dao
 * @Date 2020/2/20 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 根据分类Id查询所有品牌集合
     * @param categoryId
     * @return
     */
    @Select("select tb.* from tb_brand tb, tb_category_brand tcb where tb.id=tcb.brand_id and tcb.category_id=#{categoryId}")
    public List<Brand> findByCategory(Integer categoryId);
}

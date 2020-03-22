package com.onebooming.dao;
import com.onebooming.goods.pojo.Sku;
import com.onebooming.order.pojo.OrderItem;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:Onebooming
 * @Description:Sku的Dao
 * @Date 2020/2/20 0:12
 *****/
public interface SkuMapper extends Mapper<Sku> {

    /**
     * 递减库存
     * @param orderItem
     * @return
     */
    @Update("UPDATE tb_sku SET num=num-#{num},sale_num=sale_num+#{num} WHERE id=#{skuId} AND num>=#{num}")
    int decrCount(OrderItem orderItem);
}

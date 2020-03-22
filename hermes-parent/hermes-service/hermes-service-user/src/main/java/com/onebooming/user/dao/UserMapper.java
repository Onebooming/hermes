package com.onebooming.user.dao;
import com.onebooming.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:Onebooming
 * @Description:User的Dao
 * @Date 2020/2/20 0:12
 *****/
public interface UserMapper extends Mapper<User> {

    /***
     * 增加用户积分
     * @param username
     * @param pint
     * @return
     */
    @Update("UPDATE tb_user SET points=points+#{point} WHERE  username=#{username}")
    int addUserPoints(@Param("username") String username, @Param("point") Integer pint);
}

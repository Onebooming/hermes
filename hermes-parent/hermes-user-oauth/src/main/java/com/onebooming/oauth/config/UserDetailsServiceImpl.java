package com.onebooming.oauth.config;
import com.onebooming.oauth.util.UserJwt;
import com.onebooming.user.feign.UserFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*****
 * 自定义授权认证类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    UserFeign userFeign;

    /****
     * 自定义授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //====================================客户端认证start====================================
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //秘钥
                String clientSecret = clientDetails.getClientSecret();
                //静态方式
//                return new User(username,new BCryptPasswordEncoder().encode(clientSecret), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
                //数据库查找方式
                return new User(username,//客户端id
                        clientSecret, //客户端秘钥-》加密操作
                        AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        //====================================客户端认证end====================================

        //====================================用户账号密码信息认证start====================================

        if (StringUtils.isEmpty(username)) {
            return null;
        }

        //查询数据库
        Result<com.onebooming.user.pojo.User> user = userFeign.findById(username);
        //根据用户名查询用户信息
//        String pwd = new BCryptPasswordEncoder().encode("szitheima");
        //创建User对象
//        String permissions = "goods_list,seckill_list";
        String permissions = "salesman,accountant,user";//上述代码给登录用户定义了三个角色，分别为salesman,accountant,user，这一块我们目前使用的是硬编码方式将角色写死了，后面会从数据库加载。


        UserJwt userDetails = new UserJwt(username,user.getData().getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        //====================================用户账号密码信息认证end====================================

        //userDetails.setComy(songsi);
        return userDetails;
    }
}

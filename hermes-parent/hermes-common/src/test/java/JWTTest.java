
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-14 15:48
 * @ApiNote
 */
public class JWTTest {

    /****
     * 创建Jwt令牌
     */
    @Test
    public void testCreateJwt(){
        JwtBuilder builder= Jwts.builder()
                .setId("777")             //设置唯一编号
                .setSubject("Onebooming")       //设置主题  可以是JSON数据
                .setIssuedAt(new Date())  //设置签发日期
                .signWith(SignatureAlgorithm.HS256,"itcastboe")//设置签名 使用HS256算法，并设置SecretKey(字符串)
                .setExpiration(new Date(System.currentTimeMillis()+360000));//设置token过期时间

        //自定义载荷
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","HHH");
        map.put("age",45);
        map.put("address","北京");
        builder.addClaims(map);
        //构建 并返回一个字符串
        System.out.println( builder.compact() );
    }

    /***
     * 解析Jwt令牌数据
     */
    @Test
    public void testParseJwt(){
        String compactJwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3NzciLCJzdWIiOiJPbmVib29taW5nIiwiaWF0IjoxNTg0MTk5NTgyLCJleHAiOjE1ODQxOTk5NDMsImFkZHJlc3MiOiLljJfkuqwiLCJuYW1lIjoiSEhIIiwiYWdlIjo0NX0.0TUlcCbnQKkd49jI0rLv7Egn6OtSldSw--shbX-OtPc";
        Claims claims = Jwts.parser().
                setSigningKey("itcastboe").
                parseClaimsJws(compactJwt).
                getBody();
        System.out.println(claims);
    }

}

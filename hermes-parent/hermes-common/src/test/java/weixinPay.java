import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-23 21:30
 * @ApiNote:微信SDK相关测试
 */
public class weixinPay {
    /**
     * 1-生成随机字符
     * 2-将Map转成XML字符串
     */
    @Test
    public void testDemo() throws Exception {
        //生成随机字符串
        String s = WXPayUtil.generateNonceStr();
        System.out.println(s);

        //将Map转成XML字符串
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("id","1");
        dataMap.put("title","1");
        dataMap.put("money","1");
        dataMap.put("d","1");
        String s1 = WXPayUtil.mapToXml(dataMap);
        System.out.println(s1);

    }
}

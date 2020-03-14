import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-14 22:48
 * @ApiNote
 */
public class Base64Test {
    /**
     * Base64加密测试
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testEncode() throws UnsupportedEncodingException {
        byte[] encode = Base64.getEncoder().encode("abcdff".getBytes());
        String encodeString = new String(encode, "UTF-8");
        System.out.println("加密后的密文：" + encodeString);
    }

    /**
     * Base64解密测试
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testDecode() throws UnsupportedEncodingException {
        String encodeStr = "YWJjZGZm";
        byte[] decode = Base64.getDecoder().decode(encodeStr);
        String s = new String(decode, "UTF-8");
        System.out.println("解密后的密文：" + s);
    }
}

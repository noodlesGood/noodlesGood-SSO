package Util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码生成工具类
 */
public class PasswordGenerator {
    @Test
    public void getpassword(){
        //每次加密的结果都不同，且加密结果不可逆
        System.out.println(new BCryptPasswordEncoder().encode("oauth1"));
    }
}

package code;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.applet.Main;

import java.sql.PseudoColumnUsage;

/**
 * 密码加密工具类
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static  String encodePassword(String password){
         String password_encode =  bCryptPasswordEncoder.encode(password);
         return  password_encode;
    }

    public static void main(String[] args) {
        String password = "hhh";
        String password_encode = encodePassword(password);
        System.out.println(password_encode);
    }

}

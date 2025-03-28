package com.werun.user.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailUtil {
    // 定义一个正则表达式，用于匹配邮箱格式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // 使用正则表达式检查字符串是否为邮箱格式
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}

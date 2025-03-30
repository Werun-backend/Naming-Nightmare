package com.werun.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.werun.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  用户启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "  █████   ███   █████                                                     █████                        █████                              █████\n" +
                "▒▒███   ▒███  ▒▒███                                                     ▒▒███                        ▒▒███                              ▒▒███\n" +
                " ▒███   ▒███   ▒███   ██████  ████████  █████ ████ ████████              ▒███████   ██████    ██████  ▒███ █████  ██████  ████████    ███████\n" +
                " ▒███   ▒███   ▒███  ███▒▒███▒▒███▒▒███▒▒███ ▒███ ▒▒███▒▒███  ██████████ ▒███▒▒███ ▒▒▒▒▒███  ███▒▒███ ▒███▒▒███  ███▒▒███▒▒███▒▒███  ███▒▒███\n" +
                " ▒▒███  █████  ███  ▒███████  ▒███ ▒▒▒  ▒███ ▒███  ▒███ ▒███ ▒▒▒▒▒▒▒▒▒▒  ▒███ ▒███  ███████ ▒███ ▒▒▒  ▒██████▒  ▒███████  ▒███ ▒███ ▒███ ▒███\n" +
                "  ▒▒▒█████▒█████▒   ▒███▒▒▒   ▒███      ▒███ ▒███  ▒███ ▒███             ▒███ ▒███ ███▒▒███ ▒███  ███ ▒███▒▒███ ▒███▒▒▒   ▒███ ▒███ ▒███ ▒███\n" +
                "    ▒▒███ ▒▒███     ▒▒██████  █████     ▒▒████████ ████ █████            ████████ ▒▒████████▒▒██████  ████ █████▒▒██████  ████ █████▒▒████████\n" +
                "     ▒▒▒   ▒▒▒       ▒▒▒▒▒▒  ▒▒▒▒▒       ▒▒▒▒▒▒▒▒ ▒▒▒▒ ▒▒▒▒▒            ▒▒▒▒▒▒▒▒   ▒▒▒▒▒▒▒▒  ▒▒▒▒▒▒  ▒▒▒▒ ▒▒▒▒▒  ▒▒▒▒▒▒  ▒▒▒▒ ▒▒▒▒▒  ▒▒▒▒▒▒▒▒");
    }
}

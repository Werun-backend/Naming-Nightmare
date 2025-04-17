package com.werun.comments;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.werun.comments.mapper")
/*@ComponentScan("com.werun.common.openFeign")*/
@EnableFeignClients(basePackages = "com.werun.common.openFeign")
public class CommentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  评论启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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

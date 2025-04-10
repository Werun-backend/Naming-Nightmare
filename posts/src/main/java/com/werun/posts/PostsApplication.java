package com.werun.posts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.werun.posts.mapper")
public class PostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  帖子启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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

package com.werun.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  网关启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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

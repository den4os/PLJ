package io.zanin.mock3.fw.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "io.zanin.mock3")
public class Mock3Application {
    public static void main(String[] args) {
        SpringApplication.run(Mock3Application.class, args);
    }
}

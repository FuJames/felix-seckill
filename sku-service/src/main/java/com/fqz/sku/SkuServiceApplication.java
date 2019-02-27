package com.fqz.sku;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fuqianzhong
 * @date 19/1/2
 */
@SpringBootApplication
@MapperScan("com.fqz.sku.mapper")
public class SkuServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkuServiceApplication.class, args);
    }
}

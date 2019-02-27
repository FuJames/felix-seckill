package com.fqz.seckill.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author fuqianzhong
 * @date 2019/2/26
 */
@Configuration
public class Config {
    @Value("${zk.address}")
    private String zkAddress;

    public String getZkAddress() {
        return zkAddress;
    }
}

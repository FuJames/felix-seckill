package com.fqz.seckill.locks;

import com.fqz.seckill.config.Config;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author fuqianzhong
 * @date 2019/2/26
 */
@Component
public class DistributeLocks {
    @Resource
    private Config config;

    private InterProcessMutex lock;

    private CuratorFramework client;

    private static final String LOCK_PATH = "/seckill/locks";

    @PostConstruct
    public void init(){
        CuratorFramework curatorClient = CuratorFrameworkFactory.builder().connectString(config.getZkAddress())
                .sessionTimeoutMs(2000).connectionTimeoutMs(2000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorClient.start();
        CuratorFramework oldClient = this.client;
        this.client = curatorClient;
        close(oldClient);
        lock = new InterProcessMutex(this.client,LOCK_PATH);
    }

    private void close(CuratorFramework client) {
        if (client != null) {
            try {
                client.close();
            } catch (Exception e) {
            }
        }
    }

    public void lock() throws Exception {
        if(lock != null){
            lock.acquire();
        }
    }

    public void unlock() throws Exception {
        if(lock != null){
            lock.release();
        }
    }

}

package com.mmd.carrotuser.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author carrot
 * @date 2020/4/17 14:17
 *
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页工具配置
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

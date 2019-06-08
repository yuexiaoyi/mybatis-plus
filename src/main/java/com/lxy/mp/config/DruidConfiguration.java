package com.lxy.mp.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author lxy
 * @time 2019-02-25 15:07
 **/
//@Configuration
public class DruidConfiguration {
    @Bean("default-ds")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource(){
        DruidDataSource dds = new DruidDataSource();
        return dds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("default-ds")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);

        return ssfb.getObject();

    }

}

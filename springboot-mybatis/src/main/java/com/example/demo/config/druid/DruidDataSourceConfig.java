package com.example.demo.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.config.mybatis.MybatisConfig;
import com.example.demo.utils.DesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by dinghw on 2017/7/3.
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig implements EnvironmentAware {
    private static final Logger log = LoggerFactory.getLogger(MybatisConfig.class);

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }


    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    public DataSource writeDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        try {
            datasource.setUrl(propertyResolver.getProperty("url"));
            datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
            datasource.setUsername(DesUtil.decrypt(propertyResolver.getProperty("username")));
            datasource.setPassword(DesUtil.decrypt(propertyResolver.getProperty("password")));
            datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));
            datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
            datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));
            datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));
            datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
            datasource.setTimeBetweenEvictionRunsMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
            datasource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
            datasource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("testWhileIdle")));
            datasource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("testOnBorrow")));
            datasource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("testOnReturn")));
            datasource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty("poolPreparedStatements")));
            datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return datasource;
    }

}

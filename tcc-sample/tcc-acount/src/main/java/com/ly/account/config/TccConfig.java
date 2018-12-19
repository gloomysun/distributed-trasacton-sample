package com.ly.account.config;

import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.serializer.KryoPoolSerializer;
import org.mengyun.tcctransaction.serializer.ObjectSerializer;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

/**
 * TCC 事务配置
 *
 * @author Huanghs
 * @date 2017/12/5
 * @since 2.0
 */
@Configuration
@ImportResource(locations = "classpath:tcc-transaction.xml")
public class TccConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.tcc")
    public TccDataSourceProperties tccDataSourceProperties() {
        return new TccDataSourceProperties();
    }

    @Bean
    public TransactionRepository transactionRepository(TccDataSourceProperties properties) {

        SpringJdbcTransactionRepository repository = new SpringJdbcTransactionRepository();
        repository.setDataSource(tccDataSource(properties));
        repository.setDomain("ACCOUNT");
        repository.setTbSuffix("_ACCOUNT");
        return repository;
    }

    // 使用 @Bean，避免 JPA 使用了 tcc 的数据源
    public DataSource tccDataSource(TccDataSourceProperties properties) {
        return DataSourceBuilder.create()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

//    @Bean
//    public ObjectSerializer<?> objectSerializer() {
//        return new KryoPoolSerializer();
//    }

}

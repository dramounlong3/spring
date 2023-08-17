//package com.example.demo.configuration;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import javax.sql.DataSource;
//
//// 要run此段code需將application.properties的多資料庫設定解除註解
//@Configuration
//public class DataSourceConfiguration {
//
//    // 連線到test1 資料庫的DataSource 和 NameParameterJdbcTemplate
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.test1") //springboot test1 db datasource
//    public DataSource test1DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    // 建立test1 jdbc template 的bean
//    @Bean
//    public NamedParameterJdbcTemplate test1JdbcTemplate(
//            @Qualifier("test1DataSource") DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//
//
//    // 連線到test2 資料庫的DataSource 和 NameParameterJdbcTemplate
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.test2") //springboot test2 db datasource
//    public DataSource test2DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    // 建立test2 jdbc template 的bean
//    @Bean
//    public NamedParameterJdbcTemplate test2JdbcTemplate(
//            @Qualifier("test2DataSource") DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//}

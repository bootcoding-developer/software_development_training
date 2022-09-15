package com.bootcoding.restaurant.config;

import com.bootcoding.restaurant.common.DefaultConfiguration;
import com.bootcoding.restaurant.common.EnvironmentProfile;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@Getter
@Slf4j
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Value("${db.batch.size}")
    private int batchSize;

    @Value("${db.query.max.results}")
    private int queryMaxResults;

    @Value("${country.codes}")
    private List<String> countries;

    @Value("${upload.image.path}")
    private String defaultImagePath;

    @Value("${upload.video.path}")
    private String defaultVideoPath;

    @Value("${vendor.register.auto.approve}")
    private boolean autoApprove;

    @Value("${bootcoding.datasource.username}")
    private String dbUsername;

    @Value("${vendor.meta.table.name}")
    private String vendorMetaTableName;
    @Value("${restaurant.table.schema}")
    private String restaurantSchema;

    @Bean
    public DefaultConfiguration defaultConfiguration() {
        return DefaultConfiguration.builder()
                .countries(countries)
                .autoApprove(autoApprove)
                .batchSize(batchSize)
                .dbUsername(dbUsername)
                .queryMaxResults(queryMaxResults)
                .environmentProfile(EnvironmentProfile.DEV)
                .vendorMetaTable(vendorMetaTableName)
                .restaurantSchema(restaurantSchema)
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "bootcoding.datasource")
    public DataSource bootCodingDataSource() {
        return DataSourceBuilder.create().build();
    }

}

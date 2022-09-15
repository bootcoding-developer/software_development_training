package com.bootcoding.restaurant.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

@Builder
@Getter
public class DatabaseConfiguration {

    private final JdbcTemplate jdbcTemplate;
    private final int queryMaxResults;
    private final String dbUsername;
}

package com.bootcoding.restaurant.common;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DefaultConfiguration {


    private final int queryMaxResults;
    private final String dbUsername;

    private final int batchSize;
    private final List<String> countries;

    private final boolean autoApprove;
    private final EnvironmentProfile environmentProfile;

    private final String vendorMetaTable;
    private final String vendorMenuTable;
    private final String categoryTable;
    private final String restaurantSchema;
    private final String restaurantMenuTable;

    private final String orderTable;
    private final String orderMenuTable;
    private final String orderHistoryTable;


}

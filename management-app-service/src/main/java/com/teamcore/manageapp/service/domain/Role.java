package com.teamcore.manageapp.service.domain;


import static com.teamcore.manageapp.service.domain.Role.Code.*;

public enum Role {
    ADMIN(ADMIN_ID, "admin"),
    MANAGER(MANAGER_ID, "manager"),
    DEVELOPER(DEVELOPER_ID, "developer"),
    CUSTOMER(CUSTOMER_ID, "customer");

    public static class Code {
        public static final int ADMIN_ID = 0;
        public static final int MANAGER_ID = 1;
        public static final int DEVELOPER_ID = 2;
        public static final int CUSTOMER_ID = 3;
    }

    public static class Name {
        public static final String ADMIN_ROLE = "admin";
        public static final String MANAGER_ROLE = "manager";
        public static final String DEVELOPER_ROLE = "developer";
    }

    private final Integer dbId;
    private final String dbName;

    public final static Role getRoleByRoleId(Integer dbId) {
        switch (dbId) {
            case ADMIN_ID: return ADMIN;
            case MANAGER_ID: return MANAGER;
            case DEVELOPER_ID: return DEVELOPER;
            case CUSTOMER_ID: return CUSTOMER;
        }
        throw new IllegalArgumentException("Role dbId is not correct!");
    }

    Role(Integer dbId, String dbName) {
        this.dbId = dbId;
        this.dbName = dbName;
    }

    public Integer getRoleId() {
        return dbId;
    }

    public String getName() {
        return dbName;
    }
}


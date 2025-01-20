package com.sapo.store_management.security;

public class Endpoints {

    // Common Endpoints for all roles

    public static String[] CSR_STAFF_ADMIN_MANAGER_GET_ENDPOINT = {
            "/api/customers/**",
            "/api/customers/search/**",

    };
    public static String[] CSR_STAFF_ADMIN_MANAGER_POST_ENDPOINT = {
            "/api/customers/insert/**",
    };
    public static String[] CSR_STAFF_ADMIN_MANAGER_PUT_ENDPOINT = {
            "/api/customers/**",
    };
    public static String[] CSR_ADMIN_MANAGER_DELETE_ENDPOINT = {
            "/api/customers/**",
    };

    public static String[] STAFF_ADMIN_MANAGER_GET_ENDPOINT = {
            "/api/product/**",
            "/api/product/by-tag/**",
            "/api/product/by-name/**",
            "/api/orders/**",
            "/api/orders/search/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",

    };

    public static String[] STAFF_ADMIN_MANAGER_POST_ENDPOINT = {
            "/api/product/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/orders/**"
    };
    public static String[] STAFF_ADMIN_MANAGER_PUT_ENDPOINT = {
            "/api/product/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/orders/**"
    };

    public static String[] ADMIN_MANAGER_DELETE_ENDPOINT = {
            "/api/product/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/orders/**"
    };

    public static String[] MANAGER_POST_ENDPOINT = {
            "/api/auth/register**"
    };

    public static String[] MANAGER_DELETE_ENDPOINT = {
            "/api/auth/**"
    };
}

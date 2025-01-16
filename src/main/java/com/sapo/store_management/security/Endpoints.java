package com.sapo.store_management.security;

public class Endpoints {

    public static String[] ADMIN_GET_ENDPOINT = {
            "/api/test/admin/**",
            "/api/product/**",
            "/api/storages/**",
            "/api/suppliers/**",
            "/api/inventories/**",
            "/api/inventory-variants/**",

//            "/api/brand/**",
//            "/api/category/**",
//            "/api/tag/**",
//            "/api/product/**",
            "/api/product/by-tag/**"


    };
    public static String[] ADMIN_POST_ENDPOINT = {
            "/api/test/admin/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/product/**"
    };
    public static String[] ADMIN_PUT_ENDPOINT = {
            "/api/test/admin/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/product/**"

    };
    public static String[] ADMIN_DELETE_ENDPOINT = {
            "/api/test/admin/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/product/**"
    };
    public static String[] STAFF_GET_ENDPOINT = {
            "/api/test/staff/**"
    };
    public static String[] STAFF_POST_ENDPOINT = {
            "/api/test/staff/**",
    };
    public static String[] STAFF_DELETE_ENDPOINT = {
            "/api/test/staff/**"

    };
    public static String[] STAFF_PUT_ENDPOINT = {
            "/api/test/staff/**"
    };
    public static String[] CSR_GET_ENDPOINT = {
            "/api/test/csr/**"
    };
    public static String[] CSR_POST_ENDPOINT = {
            "/api/test/staff/**"
    };
    public static String[] CSR_DELETE_ENDPOINT = {
            "/api/test/staff/**"
    };
    public static String[] CSR_PUT_ENDPOINT = {
            "/api/test/staff/**"
    };

    //test
    public static String[] PUBLIC_GET_ENDPOINT = {
            "/api/brand/getall/**",
            "/api/category/getall/**",
            "/api/tag/getall/**",
            "/api/product/getall/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/product/**",
            "/api/product/by-tag/**",
            "/api/product/by-name/**",
            "/api/tag/by-name/**"

    };
    public static String[] PUBLIC_POST_ENDPOINT = {
            "/api/test/admin/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/product/**",
            "/api/image/**"
    };
    public static String[] PUBLIC_DELETE_ENDPOINT = {
            "/api/test/admin/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/product/**"
    };
    public static String[] PUBLIC_PUT_ENDPOINT = {
            "/api/test/admin/**",
            "/api/brand/**",
            "/api/category/**",
            "/api/tag/**",
            "/api/product/**",
            "/api/product/generate-variants/**"
    };


}

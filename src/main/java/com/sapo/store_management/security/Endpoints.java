package com.sapo.store_management.security;

public class Endpoints {

        public static String[] ADMIN_GET_ENDPOINT = {
                        "/api/test/admin/**",
                        "/api/product/**",
                        "/api/storages/**",
                        "/api/suppliers/**",
                        "/api/inventories/**",
                        "/api/inventory-variants/**"
        };
        public static String[] ADMIN_POST_ENDPOINT = {
                        "/api/test/admin/**"
        };
        public static String[] ADMIN_PUT_ENDPOINT = {
                        "/api/test/admin/**"

        };
        public static String[] ADMIN_DELETE_ENDPOINT = {
                        "/api/test/admin/**"

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

}

package com.service.departmentsecurity.config.Roles_n_Permissions;

import lombok.Getter;

@Getter
public enum ApplicationUserPermission {
    CLIENT_READ("client:read") , CLIENT_WRITE("client:write") , CLIENT_CONTACT("client:contact") , CLIENT_DELETE("client:delete"), CLIENT_UPDATE("client:update"),
    PRODUCT_READ("product:read") , PRODUCT_WRITE("product:write") , PRODUCT_BUY("product:buy") , PRODUCT_SELL("product:sell"), PRODUCT_DELETE("product:delete"), PRODUCT_UPDATE("product:update"),
    EMPLOYEE_READ("employee:read") , EMPLOYEE_WRITE("employee:write") ,

   MANAGER_READ("manager:read") , MANAGER_WRITE("manager:write") , MANAGEMENT_CONTACT("management:contact") ;

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}

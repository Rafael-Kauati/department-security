package com.service.departmentsecurity.config.Roles_n_Permissions;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

@Getter
public enum ApplicationUserRoles {
    CLIENT(Sets.newHashSet(ApplicationUserPermission.PRODUCT_BUY, ApplicationUserPermission.CLIENT_CONTACT)),
    EMPLOYEE(Sets.newHashSet(ApplicationUserPermission.EMPLOYEE_READ, ApplicationUserPermission.EMPLOYEE_WRITE, ApplicationUserPermission.MANAGEMENT_CONTACT, ApplicationUserPermission.CLIENT_READ, ApplicationUserPermission.CLIENT_WRITE)),
    MANAGER(Sets.newHashSet(ApplicationUserPermission.CLIENT_READ, ApplicationUserPermission.CLIENT_WRITE, ApplicationUserPermission.EMPLOYEE_READ, ApplicationUserPermission.EMPLOYEE_WRITE, ApplicationUserPermission.MANAGER_READ, ApplicationUserPermission.MANAGER_WRITE, ApplicationUserPermission.MANAGEMENT_CONTACT));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}

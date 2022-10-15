package com.service.departmentsecurity.config.Roles_n_Permissions;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum ApplicationUserRoles {
    CLIENT(Sets.newHashSet(ApplicationUserPermission.PRODUCT_BUY, ApplicationUserPermission.CLIENT_READ, ApplicationUserPermission.CLIENT_CONTACT)),
    EMPLOYEE(Sets.newHashSet(ApplicationUserPermission.EMPLOYEE_READ, ApplicationUserPermission.EMPLOYEE_WRITE, ApplicationUserPermission.MANAGEMENT_CONTACT, ApplicationUserPermission.CLIENT_READ, ApplicationUserPermission.CLIENT_WRITE, ApplicationUserPermission.CLIENT_DELETE, ApplicationUserPermission.CLIENT_UPDATE)),

    MANAGER(Sets.newHashSet(ApplicationUserPermission.CLIENT_READ, ApplicationUserPermission.CLIENT_WRITE, ApplicationUserPermission.CLIENT_DELETE, ApplicationUserPermission.CLIENT_UPDATE, ApplicationUserPermission.EMPLOYEE_READ, ApplicationUserPermission.EMPLOYEE_WRITE, ApplicationUserPermission.MANAGER_READ, ApplicationUserPermission.MANAGER_WRITE, ApplicationUserPermission.MANAGEMENT_CONTACT));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
         Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

         permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

         return permissions;
    }
}

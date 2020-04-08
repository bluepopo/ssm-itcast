package code.service;

import code.domain.Permission;
import code.domain.Role;

import java.util.List;

public interface IRoleService {


    List<Role> findAll() throws  Exception;

    void save(Role role)throws  Exception;

    Role findById(String roleId)throws  Exception;


    List<Permission> findOtherPermissions(String roleId)throws  Exception;

    void addPermissionToRole( String[] permissionIds,String roleId);
}

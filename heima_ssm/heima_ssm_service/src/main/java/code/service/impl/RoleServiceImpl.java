package code.service.impl;

import code.dao.IRoleDao;
import code.domain.Permission;
import code.domain.Role;
import code.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * 查询所有角色
     */
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 添加角色
     */
    @Override
    public void save(Role role) throws  Exception{
        roleDao.save(role);
    }

    /**
     * 根据id查询
     */
    @Override
    public Role findById(String roleId) throws  Exception{
        return roleDao.findById(roleId);
    }

    /**
     *查询角色可以添加的权限
     */
    @Override
    public List<Permission> findOtherPermissions(String roleId) throws  Exception{
        return roleDao.findOtherPermissions(roleId);
    }

    /**
     * 添加角色
     */
    @Override
    public void addPermissionToRole(String[] permissionIds,String roleId) {
        for (String permissionId:permissionIds){
            roleDao.addPermissionToRole(permissionId,roleId);
        }

    }


}

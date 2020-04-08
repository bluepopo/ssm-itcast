package code.controller;

import code.domain.Permission;
import code.domain.Role;
import code.domain.UserInfo;
import code.service.IRoleService;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有
     */
    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv = new ModelAndView();
       List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 添加角色
     */
    @RequestMapping(value = "/save.do")
    public String save(Role role)throws Exception{

        roleService.save(role);
        return "redirect:findAll.do";
    }


    /**
     * 查询当前角色可添加的权限
     */
    @RequestMapping(value = "findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
         List<Permission>  permissionList = roleService.findOtherPermissions(roleId);
         mv.addObject("role",role);
         mv.addObject("permissionList",permissionList);
         mv.setViewName("role-permission-add");

        return mv;
    }

    /**
     * 添加权限
     */
    @RequestMapping(value = "addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds) throws Exception{
        System.out.println(roleId);
        roleService.addPermissionToRole(permissionIds,roleId);
        return "redirect:findAll.do";
    }

}

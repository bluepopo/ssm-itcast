package code.controller;


import code.domain.Role;
import code.domain.UserInfo;
import code.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;


    /**
     * 查询所有用户
     */
    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAll() throws Exception{
        List<UserInfo> users = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",users);
        mv.setViewName("user-list");
        return mv;

}

    /**
     * 添加用户
     */
    @RequestMapping(value = "/save.do")
    public  String  save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return  "redirect:findAll.do";
}


/**
 * 用户详情查询
 *
 */
    @RequestMapping(value = "/findById.do")
    public ModelAndView findById(String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");

        return mv;
    }


    /**
     * 查询用户可添加的角色
     */
    @RequestMapping(value = "findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> otherRoles = userService.findOtherRoles(userId);

        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");

        return mv;
    }

    @RequestMapping(value = "addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds) throws Exception{
        userService.addRoleToUser(userId,roleIds);

        return "redirect:findAll.do";
    }

}

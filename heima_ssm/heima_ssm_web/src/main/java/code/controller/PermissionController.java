package code.controller;

import code.domain.Permission;
import code.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAll() throws  Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }


    @RequestMapping(value = "/save.do")
    public String save(Permission permission) throws Exception{

        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}

package code.controller;

import code.domain.Product;
import code.service.IProductService;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询所有产品
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAll() throws Exception {
        System.out.println("web....");

        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        mv.addObject("productList",products);
        mv.setViewName("product_list"); //指定视图
        for (Product product :products){
            System.out.println(product);
        }

        return mv;
    }


    /**
     * 保存产品
     */
    @PreAuthorize("authentication.principal.username == 'tom'")
    @RequestMapping(value = "/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

}

package code.controller;

import code.domain.Orders;
import code.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //未分页
//    @RequestMapping(value = "/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//
//        List<Orders> ordersList = ordersService.findAll();
//
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//
//        for (Orders orders:ordersList){
//            System.out.println(orders);
//        }
//        return mv;
//    }



    //分页
    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAllByPage(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                      @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {

        ModelAndView mv = new ModelAndView();

        List<Orders> ordersList = ordersService.findAll(page,size);

        //PageInfo 就是一个分页bean
        PageInfo pageInfo = new PageInfo(ordersList);

        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");

        return mv;
    }

    @RequestMapping(value = "/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        Orders orders =  ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");

        return mv;
    }

}

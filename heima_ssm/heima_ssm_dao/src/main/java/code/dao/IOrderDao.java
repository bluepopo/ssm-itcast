package code.dao;

import code.domain.Member;
import code.domain.Orders;
import code.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property ="id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "code.dao.IProductDao.findById")),
    })
    List<Orders> findAll() throws  Exception;




    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,property ="id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "code.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "code.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select = "code.dao.ITravellerDao.findByOrdersId"))


    })
    Orders findById(String id);
}

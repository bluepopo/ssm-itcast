package code.dao;

import code.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IProductDao {
    /**
     * 查询所有的产品信息
     */
    @Select("select * from product")
  public   List<Product> findAll() throws Exception;


    /**
     * 保存产品
     * @param product
     */
    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)" +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);


    /**
     * 根据id查询产品
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id) throws Exception;

}

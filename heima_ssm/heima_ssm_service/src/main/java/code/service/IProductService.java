package code.service;

import code.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询所有的产品信息
     */
    public List<Product> findAll() throws Exception;

    /**
     * 保存产品
     * @param product
     */
    void save(Product product)throws Exception;
}

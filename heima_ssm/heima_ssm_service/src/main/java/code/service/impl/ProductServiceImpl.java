package code.service.impl;

import code.dao.IProductDao;
import code.domain.Product;
import code.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        System.out.println("service  findAll....");
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {

        productDao.save(product);

    }
}

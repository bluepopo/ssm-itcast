package code.service.impl;

import code.dao.IOrderDao;
import code.domain.Orders;
import code.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        //分页查询 pageNum是页码值，pageSize是每页大小
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        return orderDao.findById(id);
    }
}

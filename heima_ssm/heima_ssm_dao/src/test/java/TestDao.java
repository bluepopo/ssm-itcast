import code.dao.IProductDao;
import code.domain.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDao {

    @Test
    public void test1() throws Exception {

        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建者模式创建工厂
       SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3. 工厂模式生成 SqlSession
        SqlSession sqlSession = factory.openSession();
        //4.代理模式生成dao
        IProductDao dao = sqlSession.getMapper(IProductDao.class);
        //5.调用方法
        List<Product> products = dao.findAll();

        //6.输出结果
        for (Product product : products){
            System.out.println(product);
        }
    }
}

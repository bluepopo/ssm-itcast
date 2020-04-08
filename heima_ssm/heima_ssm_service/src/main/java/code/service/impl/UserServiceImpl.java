package code.service.impl;

import code.dao.IUserDao;
import code.domain.Role;
import code.domain.UserInfo;
import code.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证器
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录验证
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;

        try {
            userInfo = userDao.findByUserName(username);
            System.out.println(userInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //处理自己的对象封装成UserDetails
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }


    /**
     * 获取角色
     * 返回list集合，集合中装入的是 角色描述
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();

        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return  list;
    }

    /**
     * 查询所有
     */
    @Override
    public List<UserInfo> findAll() throws Exception {

        return userDao.findAll();
    }

    /**
     * 添加用户
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码加密
        String password_encode = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(password_encode);
        //调用dao
        userDao.save(userInfo);
    }

    /**
     * 根据id查询用户
     */
    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    /**
     * 查询用户可以添加的角色
     */
    @Override
    public List<Role> findOtherRoles(String userId) {
        return userDao.findOtherRoles(userId);
    }

    /**
     * 给用户添加角色
     */
    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
      for (String roleId:roleIds){
          userDao.addRoleToUser(userId,roleId);
      }
    }
}

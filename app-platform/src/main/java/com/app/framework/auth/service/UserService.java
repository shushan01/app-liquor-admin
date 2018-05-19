package com.app.framework.auth.service;

import com.app.framework.auth.dao.UserDao;
import com.app.framework.auth.model.User;
import com.app.framework.core.utils.Md5SaltUtil;
import com.app.framework.core.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService extends BaseService<User> {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    public boolean login(String dbPassword, String inpassword) {
        if (Md5SaltUtil.validPassword(inpassword, dbPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public void updatePassword(String username, String password) {
        userDao.updatePassword(username, password);
    }

    public PageResult<User> findAll(int pageNo, int pageSize) {
        Page page = PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userDao.selectAll();
        return new PageResult<User>(pageNo, pageSize, page.getTotal(), userList);
    }
}

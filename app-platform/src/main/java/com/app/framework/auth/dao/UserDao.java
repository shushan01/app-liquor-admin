package com.app.framework.auth.dao;

import com.app.framework.auth.model.User;
import com.app.framework.core.utils.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDao extends BaseDao<User> {
    @Update("update ts_user set password = #{password} where user_name=#{username}")
    int updatePassword(@Param("username") String username, @Param("password") String password);
}

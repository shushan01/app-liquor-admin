package com.app.framework.base;

import com.app.framework.auth.model.User;
import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class BaseBusiness {
    protected Log log = LoggerFactory.getLogger(this.getClass());

    protected User getUser() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            return (User) subject.getSession().getAttribute("user");
        }
        return null;
    }

    protected String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            return (String) subject.getPrincipal();
        }
        return null;
    }

    protected Long getUserId() {
        return getUser().getId();
    }
}

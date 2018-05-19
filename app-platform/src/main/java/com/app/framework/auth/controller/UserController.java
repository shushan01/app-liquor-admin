package com.app.framework.auth.controller;

import com.app.framework.auth.model.User;
import com.app.framework.auth.service.UserService;
import com.app.framework.base.BaseController;
import com.app.framework.core.utils.Md5SaltUtil;
import com.app.framework.core.utils.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping("/save")
    @SuppressWarnings("unchecked")
    public Response save(@RequestBody @Valid User user) {
        user.setPassword(Md5SaltUtil.getEncryptedPwd(user.getPassword()));
        user.setCtime(new Date());
        userService.save(user);
        return Response.success();
    }
}

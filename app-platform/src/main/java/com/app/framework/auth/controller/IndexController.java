package com.app.framework.auth.controller;

import com.app.framework.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiresAuthentication
public class IndexController extends BaseController {


    @GetMapping("/index")
    @SuppressWarnings("unchecked")
    public String index() {
        return "index";
    }
}

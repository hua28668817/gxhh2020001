package com.gxhh.cloudclient.controller;


import com.gxhh.cloudclient.entities.Result;
import com.gxhh.cloudclient.entities.User;
import com.gxhh.cloudclient.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignClientDemoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${server.port}")
    Integer serverPort;

    @Autowired(required = false)
    UserService userService;

    @RequestMapping(value = "/getUserByFeignClient/{key}", method = RequestMethod.GET)
    public User getUserByFeignClient(@PathVariable("key") Integer key)
    {

        logger.info(">>从nacos中获取到的微服务地址为56:" + key);
//通过restTemplate调用商品微服务
        User user = userService.findByPid(key);

        return  user;
    }

    @RequestMapping(value = "/authLoginByFeignClient/{uname}", method = RequestMethod.GET)
    public Result authLoginByFeignClient(@PathVariable("uname") String uname)
    {


//通过restTemplate调用商品微服务
        Result rUser = userService.authLogin(uname);
        rUser.getRetInfo().put("cloud-client server port ",serverPort.toString() );
        logger.info("client 端口是:" + serverPort.toString()+ ", 服务端返回数据： "+rUser.getRetInfo().toString());

        return  rUser;
    }

}

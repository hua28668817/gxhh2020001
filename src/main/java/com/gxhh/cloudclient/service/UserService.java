package com.gxhh.cloudclient.service;



import com.gxhh.cloudclient.entities.Result;
import com.gxhh.cloudclient.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gxhh-cloud-sso")//声明调用的提供者的name
public interface UserService {

    @GetMapping(value ="/gxhh-cloud-sso/user/get/{pid}")
    User findByPid(@PathVariable("pid") Integer pid);

    @GetMapping(value ="/gxhh-cloud-sso/auth/login/{uname}")
    Result authLogin(@PathVariable("uname") String uname);

}

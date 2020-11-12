package com.gxhh.cloudclient.controller;



import com.gxhh.cloudclient.entities.Dept;
import com.gxhh.cloudclient.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class DeptController
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = false)
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;


	@RequestMapping(value = "/login/{key}", method = RequestMethod.GET)
	public User login(@PathVariable("key") String key)
	{
		User user = restTemplate.getForObject(
				"http://localhost:8022/gxhh-cloud-sso/user/get/" + key, User.class);

		return  user;
	}

	@RequestMapping(value = "/getUserByDiscoveryClient/{key}", method = RequestMethod.GET)
	public User getUserByDiscoveryClient(@PathVariable("key") String key)
	{

		List<ServiceInstance> instances = discoveryClient.getInstances("gxhh-cloud-sso");
		int index = new Random().nextInt(instances.size());
		ServiceInstance serviceInstance = instances.get(index);
		String url = serviceInstance.getHost() + ":" +
				serviceInstance.getPort()+ "/"+serviceInstance.getServiceId();

		url ="http://" + url +"/user/get/"+key ;
		logger.info(">>从nacos中获取到的微服务地址为:" + url);
//通过restTemplate调用商品微服务
		User user = restTemplate.getForObject(url , User.class);

		return  user;
	}

	@RequestMapping(value = "/getUserByRibbon/{key}", method = RequestMethod.GET)
	public User getUserByRibbon(@PathVariable("key") String key)
	{

		String url = "gxhh-cloud-sso";
		url ="http://" + url +"/gxhh-cloud-sso/user/get/"+key ;
		logger.info(">>从nacos中获取到的微服务地址为555:" + url);
//通过restTemplate调用商品微服务
		User user = restTemplate.getForObject(url , User.class);

		return  user;
	}



	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id)
	{
		return  new Dept("li si ");
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		List<Dept>  depts= new ArrayList<Dept>();
		depts.add(new Dept("zhang san"));
		return depts;
	}



}

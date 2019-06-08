package com.lxy.mp;

import com.lxy.mp.mapper.UserMapper;
import com.lxy.mp.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusDemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void select(){
		List<User> list = userMapper.selectList(null);
		Assert.assertEquals(5,list.size());
		list.forEach(System.out::println);
	}

	@Test
	public void insert(){
		User user = new User();
		user.setName("张三");
		user.setEmail("zs@baomidou.com");
		user.setManagerId(1087982257332887553L);
		user.setAge(23);
		user.setCreateTime(LocalDateTime.now());
		user.setRemark("这是个备注字段~");

		int rs = userMapper.insert(user);
		System.out.println(rs);
	}

}

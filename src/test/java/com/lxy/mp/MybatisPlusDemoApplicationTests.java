package com.lxy.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxy.mp.mapper.UserMapper;
import com.lxy.mp.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
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

	/**
	 * 根据多个Id查找记录
	 */
	@Test
	public void selectByBatchId(){
		List<Long> ids = Arrays.asList(1094590409767661570L,1094592041087729666L);
		List<User> list = userMapper.selectBatchIds(ids);
		list.forEach(System.out::println);
	}

	@Test
	public void selectByMap(){
		HashMap<String, Object> map = new HashMap();
		map.put("age", 23);
		map.put("name", "张三");
		List<User> list = userMapper.selectByMap(map);
		list.forEach(System.out::println);
	}

	/**
	 * 名字为王姓并且（年龄小于40或邮箱不为空）
	 */
	@Test
	public void selectByWrapper(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.likeRight("name","王").and(i -> i.lt("age", 40).or().isNotNull("email"));
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	/**
	 * 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
	 * name like '王%' or (age < 40 and age >20 and email is not null</>)
	 */
	@Test
	public void selectByWrapper2(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.likeRight("NAME","王%").or(i -> i.between("AGE",20,40).isNotNull("Email"));
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	/**
	 * （年龄小于40或邮箱不为空）并且名字为王姓
	 */
	@Test
	public void selectByWrapper3(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.nested(i -> i.lt("AGE", 40).or().isNotNull("EMAIL")).likeRight("NAME","王");
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	/**
	 * 姓名为王姓的，并且只显示id，name,age,email四列,实现方式1
	 */
	@Test
	public void selectByWrapper4(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.select("id","name","age","email").likeRight("name","王");
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	/**
	 * 姓名为王姓的，并且只显示id，name,age,email四列，实现方式2
	 */
	@Test
	public void selectByWrapper5(){
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.likeRight("name","王").select(User.class, i -> !i.getColumn().equals("manager_id")&& !i.getColumn().equals("create_time"));
		List<User> list = userMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	/**
	 * lamda表达式查询
	 */
	@Test
	public void selectLamda(){
//		LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda();
//		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
		LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();

		lambdaQueryWrapper.like(User::getName, "王");

		List<User> list = userMapper.selectList(lambdaQueryWrapper);
		list.forEach(System.out::println);
	}

	/**
	 * 测试分页
	 */
	@Test
	public void testPage(){
		IPage<User> page = new Page<>(1,2);
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.ge("age",30);

		IPage<User> result = userMapper.selectPage(page, wrapper);
		System.out.println(result.getCurrent());
		System.out.println(result.getTotal());
		System.out.println(result.getSize());
		System.out.println(result.getRecords());
	}
}

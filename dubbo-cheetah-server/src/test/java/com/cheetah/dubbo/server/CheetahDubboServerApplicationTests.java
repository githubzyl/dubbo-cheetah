package com.cheetah.dubbo.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheetah.dubbo.api.service.IUserService;
import com.cheetah.dubbo.base.entity.User;
import com.cheetah.dubbo.base.enums.GenderEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheetahDubboServerApplicationTests {

	@Autowired
	private IUserService userService;
	
	@Test
	public void save() {
		User entity = new User();
		entity.setUserName("q6");
		entity.setPassword("9876543210");
		entity.setRealName("Q六");
		entity.setMobile("54321123451");
		entity.setEmail("54321123451@126.com");
		entity.setGender(GenderEnum.MALE);
		userService.save(entity);
	}

	@Test
	public void query() {
		QueryWrapper<User> queryWrapper = new  QueryWrapper<>();
		queryWrapper.eq(User.FIELD_REAL_NAME, "Q六");
		queryWrapper.select(User.FIELD_ID, User.FIELD_USER_NAME, User.FIELD_REAL_NAME, User.FIELD_GENDER);
		User user = userService.getOne(queryWrapper);
		System.out.println(user);
	}
	
	@Test
	public void remove() {
		userService.removeById(7);
		System.out.println(userService.getById(7));
	}
	
}

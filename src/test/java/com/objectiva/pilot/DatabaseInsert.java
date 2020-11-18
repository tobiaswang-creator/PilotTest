package com.objectiva.pilot;

import org.junit.jupiter.api.Test;
import javax.annotation.Resource;
import com.objectiva.pilot.dao.UserDao;
import com.objectiva.pilot.model.SysUser;

public class DatabaseInsert {

	@Resource
    private UserDao userMapper;
	
	@Test
	public void InsertAccessDB(){
		SysUser user = new SysUser();
		user.setUserId(11);
		user.setAccountType("saving");
		user.setAccountNumber("090909090");
		int result = userMapper.addUser(user);
	}
	
}

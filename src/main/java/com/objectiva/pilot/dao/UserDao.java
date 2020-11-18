package com.objectiva.pilot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.objectiva.pilot.model.SysUser;

@Repository
@Mapper
public interface UserDao {

	public SysUser selectUserByName(String userName);

	public int addUser(SysUser user);

	public List<SysUser> selectAllUsers();

	public String findAccountById(int id);

}

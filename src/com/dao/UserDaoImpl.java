package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;
import com.util.DBconn;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean login(String name, String pwd) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from user where name='"+name+"' and pwd='"+pwd+"'"); 
			while (rs.next())
			{
				if(rs.getString("name").equals(name)&&rs.getString("pwd").equals(pwd));
				{
					flag = true;
				}
			}
			DBconn.closeConn();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		DBconn.init();
		int i = DBconn.addUpdDel("insert into user(name,pwd,sex,home,info) " +  
                "values('"+user.getName()+"','"+user.getPwd()+"','"+user.getSex()+"','"+user.getHome()+"','"+user.getInfo()+"')");
		if(i>0)
		{
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	@Override
	public List<User> getUserAll() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		try {
			DBconn.init();
			ResultSet rs;
			rs = DBconn.selectSql("select * from user");
			while (rs.next())
			{
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setHome(rs.getString("home"));
				user.setInfo(rs.getString("info"));
				user.setPwd(rs.getString("pwd"));
				user.setSex(rs.getString("sex"));
				users.add(user);
			}
			DBconn.closeConn();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean delect(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		DBconn.init();
		int i =DBconn.addUpdDel("Delete from user where id="+id);
		if(i>0)
		{
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	@Override
	public boolean update(int id, String name, String pwd, String sex, String home, String info) {
		// TODO Auto-generated method stub
		boolean flag= false;
		DBconn.init();
		String sql = "update user set name ='"+name  
                +"' , pwd ='"+pwd  
                +"' , sex ='"+sex  
                +"' , home ='"+home  
                +"' , info ='"+info+"' where id = "+id;
		int i = DBconn.addUpdDel(sql);
		if(i>0)
		{
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

}

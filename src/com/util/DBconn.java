package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconn {
	static String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";   
    static String username = "root";   
    static String password = "root";   
    static Connection  conn = null;  
    static ResultSet rs = null;  
    static PreparedStatement ps =null; 
	public static void init()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("init [SQL���������ʼ��ʧ�ܣ�]");
			e.printStackTrace();
		}
	}
	public static int addUpdDel(String sql)
	{
		int i =0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("��ɾ���쳣");
			e.printStackTrace();
		}
		return i;
	}
	public static ResultSet selectSql(String sql)
	{
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("���ݿ��ѯ�쳣");
			e.printStackTrace();
		}
		return rs;
	}
	public static void closeConn()
	{
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���ݿ�ر��쳣");
			e.printStackTrace();
		}
		
	}
}

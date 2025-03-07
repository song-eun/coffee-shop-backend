package com.shop.cafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.shop.cafe.dto.Member;

@Repository
public class MemberDao {
	
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USER;
	
	@Value("${spring.datasource.password}")
	private String DB_PW;
	
	public Member login(Member m) throws Exception {
		System.out.println("MemberDao login() 호출됨");
		
		Class.forName(DB_DRIVER);	
	
		Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);	
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from member where email='" + m.getEmail() + "' and pwd='" + m.getPwd() + "'");
		
		if(rs.next()) { // 회원정보 일치
			String nickname = rs.getString("nickname");
			m.setNickname(nickname);
		}
		return m;
	}
	
	public void insertMember(Member m) throws Exception {
		System.out.println("MemberDao insertMember() 호출됨");
		
		Class.forName(DB_DRIVER);	
	
		Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);	
		PreparedStatement stmt = con.prepareStatement("insert into member(email, pwd, nickname) values(?, ?, ?)");	
	
		stmt.setString(1, m.getEmail());
		stmt.setString(2, m.getPwd());
		stmt.setString(3, m.getNickname());
			
		int i = stmt.executeUpdate();
			
		System.out.println(i + "행이 insert되었습니다");
		
	}

}

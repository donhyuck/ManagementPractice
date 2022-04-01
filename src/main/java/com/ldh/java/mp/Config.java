package com.ldh.java.mp;

public class Config {

	// 접속경로 및 인증
	public static String getDBUrl() {
		return "jdbc:mysql://localhost:3306/mp?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
	}

	public static String getDBId() {
		return "sbsst";
	}

	public static String getDBPw() {
		return "sbs123414";
	}

	public static String getDBDriverClassName() {
		return "com.mysql.cj.jdbc.Driver";
	}
}

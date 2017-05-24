package com.faceye.component.security.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordEncoder {

	public static String encoder(String password) {
		String res = "";
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		res = passwordEncoder.encodePassword(password, null);
		return res;
	}
	public static void main(String asrg[]) throws Exception{
		System.out.println(PasswordEncoder.encoder("admin"));
	}
	
}

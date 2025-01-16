package com.elearn.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.elearn.app.config.security.JwtUtil;

@SpringBootTest
class StartLearnBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private JwtUtil jwtUtil;

	@Test
	public void testJwt(){
		System.out.println("testing jwt");
		String token = jwtUtil.generateToken("vini");
		System.out.println(token);
		System.out.println(jwtUtil.validateToken(token, "vini"));
		System.out.println(jwtUtil.extractUsername(token));
	}

}

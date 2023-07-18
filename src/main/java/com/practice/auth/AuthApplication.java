package com.practice.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(AuthApplication.class, args);
//		KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//		SecretKey secretKey = keyGen.generateKey();
//		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//		System.out.println(encodedKey);

//		String encodedKey = "zCdQccvxPwCT/nq3KkfePM42K3ufo6KcBCsvwoAByKM=";
//		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
//		Key key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
//		System.out.println(key);
	}

}

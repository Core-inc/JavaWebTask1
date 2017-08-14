package com.teamcore.manageapp.service.utils;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Scanner;

public class PasswordHash {
	
	public static void main(String[] args) {

		Scanner sin = new Scanner(System.in);
		System.out.println("Enter raw password: ");
		String rawPassword = sin.nextLine();

		Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("", 200000, 512);
		String encodedPassword = passwordEncoder.encode(rawPassword);
		System.out.println("encoded password: "+encodedPassword);
		System.out.println(passwordEncoder.matches(rawPassword, encodedPassword));
	}
}

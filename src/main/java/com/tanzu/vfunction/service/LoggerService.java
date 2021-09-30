package com.tanzu.vfunction.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class LoggerService {

	public void log(final String message) {
		System.out.println(new Date().toString() + " : " + message);
	}
}

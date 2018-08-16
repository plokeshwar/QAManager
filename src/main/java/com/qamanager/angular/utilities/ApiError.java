package com.qamanager.angular.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiError {
	
	public static ResponseEntity errorNotFound(String message) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("errorCode", HttpStatus.NOT_FOUND.toString());
		map.put("errorMessage", message);
		return new ResponseEntity(map, HttpStatus.NOT_FOUND).status(HttpStatus.NOT_FOUND).body(message);
	}

	public static ResponseEntity errorDuplicate(String message) {
		Map<String, Object> map = new LinkedHashMap<>();

		map.put("errorCode", HttpStatus.CONFLICT.toString());
		map.put("errorMessage", message);
		return new ResponseEntity(map, HttpStatus.CONFLICT).status(HttpStatus.CONFLICT).body(message);
	}


}

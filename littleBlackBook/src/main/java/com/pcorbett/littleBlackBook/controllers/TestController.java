package com.pcorbett.littleBlackBook.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Patrick Corbett
 *
 * @created 20.07.2018
 */
@Controller
public class TestController {

	public static Integer count = 0;
	
	@RequestMapping(value = "/", produces = { "application/json" })
	public @ResponseBody ResponseEntity<Map<String, Object>> configuration() {

		Map<String, Object> resp = new LinkedHashMap<>();
		resp.put("REQUEST", "" + (++TestController.count));

		return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);
	}

}
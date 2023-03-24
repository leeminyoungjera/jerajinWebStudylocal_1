package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

//uri - /sample/*
// mapping은 클래스 위에, 메서드 위에 -> 클래스 매핑 + 메서드 매핑 ==> 전체 uri에 해당된다.
@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	// String - redirect : 에 안붙으면 jsp로 froward, redirect:이 붙어있으면 페이지 이동
	// void면 uri가 jsp정보가 된다. 예) /sample/test -> /sample/test.jsp
	@RequestMapping("/basic") //여기의 uri는 /sample/basic - 클래서 매핑 + 메소드 매핑
	public void basic() {
		log.info("basic.................");
	}
	
	// uri -> /sample/basic
	@RequestMapping(value = "/basicGet", method = RequestMethod.GET)
	public void basicGet() {
		log.info("basicGet.................");
		
	}
	
	// @RequestMapping에서 Get방식만 받도록 정의한 어노테이션 -> @GetMapping
	@GetMapping("/basicGet2")
	public void basicGet2() {
		log.info("basicGet2().................");
	}
	
}

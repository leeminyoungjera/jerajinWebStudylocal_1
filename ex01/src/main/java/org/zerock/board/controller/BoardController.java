package org.zerock.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.board.sercive.BoardServiceImpl;
import org.zerock.board.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {

	
	//git 커밋 테스트 주석
	@Autowired
	private BoardServiceImpl service;
	
	@RequestMapping("/list.do")
	public String list(Model model) {
		log.info("게시판 리스트 -----------------------");
//		System.out.println(10/0);
		model.addAttribute("list", service.list());
		log.info(service.list());
		return "board/list";
	}
	
	@RequestMapping("/view.do")
	public String view(long no, int inc, Model model) {
		log.info("게시판 글보기 -----------------------");
		log.info("no=" + no + ", inc=" + inc);
		model.addAttribute("vo", service.view(no, inc));
		return "board/view";
	}
	
	@GetMapping("/write.do")
	public String writeForm() {
		log.info("게시판 쓰기폼 -----------------------");
		return "board/write";
	}
	
	@PostMapping("/write.do")
	public String write(BoardVO vo) {
		log.info("게시판 쓰기 처리 -----------------------");
		log.info(vo);
		service.write(vo);
		return "redirect:list.do";
	}
	
	@GetMapping("/update.do")
	public String updateForm(long no, Model model) {
		log.info("게시판 수정폼 -----------------------");
		model.addAttribute("vo", service.view(no, 0));
		return "board/update";
	}
	
	@PostMapping("/update.do")
	public String update(BoardVO vo) {
		log.info("게시판 수정 처리 -----------------------");
		service.update(vo);
		return "redirect:view.do?no="+ vo.getNo() +"&inc=0";
	}
	
	@PostMapping("/delete.do")
	//@RequestParam([name, defaultValue, required, value] : 넘어오는 데이터의 이름이 변수와 다른 경우, 값이 넘어오지 않는 경우 기본값, 필수 항목, 값 셋팅(잘안씀)
//	public String delete(@RequestParam("no") ArrayList<Long> no) {
//		public String delete(long[] no) {
	public String delete(BoardVO vo) {
		log.info("게시판 글 삭제 처리 -----------------------");
//		log.info(no);
		log.info(vo); //no, pw
		service.delete(vo);
		return "redirect:list.do";
	}
}
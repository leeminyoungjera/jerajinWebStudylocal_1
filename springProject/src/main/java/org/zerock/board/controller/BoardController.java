package org.zerock.board.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.service.BoardService;
import org.zerock.board.vo.BoardVO;

import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	//git 커밋 테스트 주석
		@Autowired
		@Qualifier("boardServiceImpl") //@Qualifier 사용할 때는 @Autowired를 사용해서 DI 적용해야함.
		private BoardService service;
		
		private String module = "board";
		
		@RequestMapping("/list.do")
		public String list(@ModelAttribute("pageObject") PageObject pageObject, Model model) {
			log.info("게시판 리스트 -----------------------" + LocalDateTime.now().toString());
			model.addAttribute("list", service.list(pageObject));
			return module + "/list";
		}
		
		@RequestMapping("/view.do")
		public String view(long no, int inc, Model model) {
			log.info("게시판 글보기 -----------------------");
			log.info("no=" + no + ", inc=" + inc);
			model.addAttribute("vo", service.view(no, inc));
			return module + "/view";
		}
		
		@GetMapping("/write.do")
		public String writeForm() {
			log.info("게시판 쓰기폼 -----------------------");
			return module + "/write";
		}
		
		@PostMapping("/write.do")
		public String write(BoardVO vo, long perPageNum, RedirectAttributes rttr) {
			log.info("게시판 쓰기 처리 -----------------------");
			log.info(vo);
			service.write(vo);
			rttr.addFlashAttribute("msg", "글 등록이 되었습니다.");
			return "redirect:list.do?perPageNum=" + perPageNum;
		}
		
		@GetMapping("/update.do")
		public String updateForm(long no, Model model) {
			log.info("게시판 수정폼 -----------------------");
			model.addAttribute("vo", service.view(no, 0));
			return module + "/update";
		}
		
		@PostMapping("/update.do")
		public String update(BoardVO vo, RedirectAttributes rttr) {
			log.info("게시판 수정 처리 -----------------------");
			service.update(vo);
//			rttr.addFlashAttribute("msg", "글 수정이 되었습니다.");
			return "redirect:view.do?no="+ vo.getNo() +"&inc=0";
		}
		
		@PostMapping("/delete.do")
		//@RequestParam([name, defaultValue, required, value] : 넘어오는 데이터의 이름이 변수와 다른 경우, 값이 넘어오지 않는 경우 기본값, 필수 항목, 값 셋팅(잘안씀)
//		public String delete(@RequestParam("no") ArrayList<Long> no) {
//			public String delete(long[] no) {
		public String delete(BoardVO vo, RedirectAttributes rttr) {
			log.info("게시판 글 삭제 처리 -----------------------");
//			log.info(no);
			log.info(vo); //no, pw
			service.delete(vo);
			rttr.addFlashAttribute("msg", "글 삭제가 되었습니다.");
			return "redirect:list.do";
		}
}

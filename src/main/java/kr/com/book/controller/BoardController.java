package kr.com.book.controller;

import javax.xml.ws.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.com.book.domain.Board;
import kr.com.book.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	// 글 작성 화면
	@RequestMapping(value = "/board/createView", method = RequestMethod.GET)
	public void create() throws Exception{
		logger.info("createView");
	}
	
	// 글 작성
	@RequestMapping(value = "board/create", method = RequestMethod.POST)
	public String create(Board b) throws Exception{
		logger.info("create");
		boardService.create(b);
		return "redirect:/";
	}
	
}
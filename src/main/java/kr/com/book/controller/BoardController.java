package kr.com.book.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.com.book.domain.Board;
import kr.com.book.domain.BoardListPageView;
import kr.com.book.domain.SearchParams;
import kr.com.book.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	// 글 작성 화면
	@RequestMapping(value = "/board/createView", method = RequestMethod.GET)
	public void create() throws Exception {
		logger.info("createView");
	}

	// 글 작성
	@RequestMapping(value = "board/create", method = RequestMethod.POST)
	public String create(Board b) throws Exception {
		logger.info("create");
		boardService.create(b);
		return "redirect:/";
	}

	// 글 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, SearchParams sp) throws Exception {
		logger.info("list");

		model.addAttribute("list", boardService.list(sp));
		
		BoardListPageView pv = new BoardListPageView();
		pv.setCri(sp);
		pv.setTotalCount(boardService.listCount());
		
		model.addAttribute("pv", pv);
		
		return "board/list";

	}

	// 글 조회
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Board b, Model model) throws Exception {
		logger.info("read");

		model.addAttribute("read", boardService.read(b.getBno()));

		return "board/read";
	}

	// 게시판 수정뷰
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(Board b, Model model) throws Exception {
		logger.info("updateView");

		model.addAttribute("update", boardService.read(b.getBno()));

		return "board/updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Board b) throws Exception {
		logger.info("update");

		boardService.update(b);

		return "redirect:/board/list";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Board b) throws Exception {
		logger.info("delete");

		boardService.delete(b.getBno());

		return "redirect:/board/list";
	}

}

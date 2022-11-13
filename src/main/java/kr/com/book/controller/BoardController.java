package kr.com.book.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.com.book.domain.Board;
import kr.com.book.domain.BoardListPageView;
import kr.com.book.domain.Reply;
import kr.com.book.domain.Search;
import kr.com.book.service.BoardService;
import kr.com.book.service.ReplyService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService boardService;
	@Inject
	private ReplyService replyService;
	

	// 글 작성 화면
	@RequestMapping(value = "/board/createView", method = RequestMethod.GET)
	public void create() throws Exception {
		logger.info("createView");
	}

	// 글 작성
	@RequestMapping(value = "board/create", method = RequestMethod.POST)
	public String create(Board b, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("create");
		boardService.create(b, mpRequest);
		return "redirect:/";
	}

	// 글 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,@ModelAttribute("s") Search s) throws Exception {
		logger.info("list");

		model.addAttribute("list", boardService.list(s));
		
		BoardListPageView pv = new BoardListPageView();
		pv.setP(s);
		pv.setTotalCount(boardService.listCount(s));
		
		model.addAttribute("pv", pv);
		
		return "board/list";

	}
	
	// 게시판 조회
		@RequestMapping(value = "/read", method = RequestMethod.GET)
		public String read(Board b, @ModelAttribute("s") Search s, Model model) throws Exception {
			logger.info("read");

			model.addAttribute("read", boardService.read(b.getBno()));
			model.addAttribute("s", s);

			List<Reply> replyList = replyService.readReply(b.getBno());
			model.addAttribute("replyList", replyList);

			List<Map<String, Object>> fileList = boardService.selectFileList(b.getBno());
			model.addAttribute("file", fileList);
			return "board/read";
		}

	// 게시판 수정뷰
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(Board b, Model model, @ModelAttribute("s") Search s) throws Exception {
		logger.info("updateView");

		model.addAttribute("update", boardService.read(b.getBno()));
		model.addAttribute("s", s);

		return "board/updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Board b, @ModelAttribute("s") Search s, RedirectAttributes rttr) throws Exception {
		logger.info("update");

		boardService.update(b);
		
		rttr.addAttribute("page", s.getPage());
		rttr.addAttribute("perPageNum", s.getPerPageNum());
		rttr.addAttribute("searchType", s.getSearchType());
		rttr.addAttribute("keyword", s.getKeyword());

		return "redirect:/board/list";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Board b, @ModelAttribute("s") Search s, RedirectAttributes rttr) throws Exception {
		logger.info("delete");

		boardService.delete(b.getBno());
		
		rttr.addAttribute("page", s.getPage());
		rttr.addAttribute("perPageNum", s.getPerPageNum());
		rttr.addAttribute("searchType", s.getSearchType());
		rttr.addAttribute("keyword", s.getKeyword());

		return "redirect:/board/list";
	}
	
	// 댓글 작성
	@RequestMapping(value="/replyWrite", method = RequestMethod.POST)
	public String replyWrite(Reply r, Search s, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");
			
		replyService.writeReply(r);
			
		rttr.addAttribute("bno", r.getBno());
		rttr.addAttribute("page", s.getPage());
		rttr.addAttribute("perPageNum", s.getPerPageNum());
		rttr.addAttribute("searchType", s.getSearchType());
		rttr.addAttribute("keyword", s.getKeyword());
			
		return "redirect:/board/read";
	}
	
	//댓글 수정 GET
	@RequestMapping(value="/replyUpdateView", method = RequestMethod.GET)
	public String replyUpdateView(Reply r, Search s, Model model) throws Exception {
		logger.info("reply Write");
			
		model.addAttribute("replyUpdate", replyService.selectReply(r.getRno()));
		model.addAttribute("s", s);
			
		return "board/replyUpdateView";
	}
		
	//댓글 수정 POST
	@RequestMapping(value="/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(Reply r, Search s, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");
		
		replyService.updateReply(r);
		
		rttr.addAttribute("bno", r.getBno());
		rttr.addAttribute("page", s.getPage());
		rttr.addAttribute("perPageNum", s.getPerPageNum());
		rttr.addAttribute("searchType", s.getSearchType());
		rttr.addAttribute("keyword", s.getKeyword());
		
		return "redirect:/board/read";
	}
	
	//댓글 삭제 GET
	@RequestMapping(value="/replyDeleteView", method = RequestMethod.GET)
	public String replyDeleteView(Reply r, Search s, Model model) throws Exception {
		logger.info("reply Write");
		
		model.addAttribute("replyDelete", replyService.selectReply(r.getRno()));
		model.addAttribute("s", s);
		
			return "board/replyDeleteView";
	}
	
	//댓글 삭제
	@RequestMapping(value="/replyDelete", method = RequestMethod.POST)
	public String replyDelete(Reply r, Search s, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");
		
		replyService.deleteReply(r);
		
		rttr.addAttribute("bno", r.getBno());
		rttr.addAttribute("page", s.getPage());
		rttr.addAttribute("perPageNum", s.getPerPageNum());
		rttr.addAttribute("searchType", s.getSearchType());
		rttr.addAttribute("keyword", s.getKeyword());
		
		return "redirect:/board/read";
	}

	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = boardService.selectFileInfo(map);
		String editName = (String) resultMap.get("editname");
		String fileName = (String) resultMap.get("filename");
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\mp\\file\\"+editName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(fileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
	
}

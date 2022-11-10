package kr.com.book.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.com.book.domain.Member;
import kr.com.book.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	@Inject
	BCryptPasswordEncoder pe;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(Member m) throws Exception {
		logger.info("post register");
		
		int result = service.idChk(m);
		if(result == 1) {
			return "/member/register";
		} else if(result == 0) {
			String inputPass = m.getUserPass();
			String pw = pe.encode(inputPass);
			m.setUserPass(pw);
			service.register(m);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Member m, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		session.getAttribute("member");
		Member login = service.login(m);
		boolean pwdMatch = pe.matches(m.getUserPass(), login.getUserPass());

		if(login != null && pwdMatch == true) {
			session.setAttribute("member", login);
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		
		return "member/memberUpdateView";
	}

	@RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(Member m, HttpSession session) throws Exception{
		
		service.memberUpdate(m);
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "member/memberDeleteView";
	}
		
	@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
	public String memberDelete(Member m, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		service.memberDelete(m);
		session.invalidate();
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/passChk", method = RequestMethod.POST)
	public int passChk(Member m) throws Exception {
		int result = service.passChk(m);
		return result;
	}
	

	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public int idChk(Member m) throws Exception {
		int result = service.idChk(m);
		return result;
	}
	
	
}

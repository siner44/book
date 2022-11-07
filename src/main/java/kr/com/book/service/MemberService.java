package kr.com.book.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.book.dao.MemberDAO;
import kr.com.book.domain.Member;

@Service
public class MemberService {

	MemberDAO dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public void register(Member m) {
		dao = template.getMapper(MemberDAO.class);
		dao.register(m);
	}
	
	public Member login(Member m) {
		dao = template.getMapper(MemberDAO.class);
		return dao.login(m);
	}
	
	public void memberUpdate(Member m) {
		dao = template.getMapper(MemberDAO.class);
		dao.memberUpdate(m);
	}
	
	public void memberDelete(Member m) {
		dao = template.getMapper(MemberDAO.class);
		dao.memberDelete(m);
	}
	
}

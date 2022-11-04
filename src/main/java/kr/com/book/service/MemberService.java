package kr.com.book.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kr.com.book.dao.BoardDAO;
import kr.com.book.dao.MemberDAO;
import kr.com.book.domain.Member;

public class MemberService {

	MemberDAO dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public void register(Member m) {
		dao = template.getMapper(MemberDAO.class);
		dao.register(m);
	}
	
}

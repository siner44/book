package kr.com.book.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kr.com.book.dao.ReplyDAO;
import kr.com.book.domain.Reply;

public class ReplyService {

	ReplyDAO dao;

	@Autowired
	private SqlSessionTemplate template;
	
	public List<Reply> readReply(int bno) {
		
		dao = template.getMapper(ReplyDAO.class);
		return dao.readReply(bno);
	}
	
	public void writeReply(Reply r) {
		
		dao = template.getMapper(ReplyDAO.class);
		dao.writeReply(r);
	}
	
}

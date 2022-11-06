package kr.com.book.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.book.dao.ReplyDAO;
import kr.com.book.domain.Reply;

@Service
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
	
	public void updateReply(Reply r) {
		
		dao = template.getMapper(ReplyDAO.class);
		dao.updateReply(r);
	}
	
	public void deleteReply(Reply r) {
		
		dao = template.getMapper(ReplyDAO.class);
		dao.deleteReply(r);
	}
	
	public Reply selectReply(int rno) {
	
		dao = template.getMapper(ReplyDAO.class);
		return dao.selectReply(rno);
	}

}

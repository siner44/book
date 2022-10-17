package kr.com.book.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.book.dao.BoardDAO;
import kr.com.book.domain.Board;

@Service
public class BoardService {

	BoardDAO dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public void create(Board b) throws Exception {
		dao = template.getMapper(BoardDAO.class);
		dao.create(b);
	}
	
	public List<Board> list(){
		dao = template.getMapper(BoardDAO.class);
		return dao.list();
	}
	
}

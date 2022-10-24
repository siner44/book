package kr.com.book.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.book.dao.BoardDAO;
import kr.com.book.domain.Board;
import kr.com.book.domain.SearchParams;

@Service
public class BoardService {

	BoardDAO dao;

	@Autowired
	private SqlSessionTemplate template;

	public void create(Board b) throws Exception {
		dao = template.getMapper(BoardDAO.class);
		dao.create(b);
	}

	public List<Board> list(SearchParams sp) {
		dao = template.getMapper(BoardDAO.class);
		return dao.list(sp);
	}
	
	public int listCount() {
		dao = template.getMapper(BoardDAO.class);
		return dao.listCount();
	}

	public Board read(int bno) {
		dao = template.getMapper(BoardDAO.class);
		return dao.read(bno);
	}

	public void update(Board b) {
		dao = template.getMapper(BoardDAO.class);
		dao.update(b);
	}

	// 게시물 삭제
	public void delete(int bno) {
		dao = template.getMapper(BoardDAO.class);
		dao.delete(bno);
	}
}

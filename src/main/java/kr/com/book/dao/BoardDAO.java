package kr.com.book.dao;

import java.util.List;

import kr.com.book.domain.Board;

public interface BoardDAO {
	public int create(Board b);
	
	public List<Board> list();
}

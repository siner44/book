package kr.com.book.dao;

import java.util.List;

import kr.com.book.domain.Board;
import kr.com.book.domain.SearchParams;

public interface BoardDAO {
	public int create(Board b);
	
	public List<Board> list(SearchParams sp);
	
	public int listCount();
	
	public Board read(int bno);
	
	public void update(Board b);
	
	public void delete(int bno);
	
	
	
}

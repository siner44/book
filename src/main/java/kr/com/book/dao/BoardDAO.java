package kr.com.book.dao;

import java.util.List;
import java.util.Map;

import kr.com.book.domain.Board;
import kr.com.book.domain.Search;

public interface BoardDAO {
	public int create(Board b);
	
	public List<Board> list(Search s);
	
	public int listCount(Search s);
	
	public Board read(int bno);
	
	public void update(Board b);
	
	public void delete(int bno);
	
	public void insertFile(Map<String, Object> map);
	
	public List<Map<String, Object>> selectFileList(int bno);
	
	public Map<String, Object> selectFileinfo(Map<String, Object> map);
	
	public void updateFile(Map<String, Object> map);
	
	public void boardCnt(int bno);
	
}

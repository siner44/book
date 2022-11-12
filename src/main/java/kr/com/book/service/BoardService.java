package kr.com.book.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.com.book.dao.BoardDAO;
import kr.com.book.domain.Board;
import kr.com.book.domain.Search;
import kr.com.book.util.FileUtils;

@Service
public class BoardService {
	
	@Inject
	private BoardDAO dao;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;

	@Autowired
	private SqlSessionTemplate template;

	public void create(Board b, MultipartHttpServletRequest mpRequest) throws Exception {
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(b, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			dao.insertFile(list.get(i)); 
		}
		dao = template.getMapper(BoardDAO.class);
		dao.create(b);
	}

	public List<Board> list(Search s) {
		dao = template.getMapper(BoardDAO.class);
		return dao.list(s);
	}
	
	public int listCount(Search s) {
		dao = template.getMapper(BoardDAO.class);
		return dao.listCount(s);
	}

	public Board read(int bno) {
		dao = template.getMapper(BoardDAO.class);
		return dao.read(bno);
	}

	public void update(Board b) {
		dao = template.getMapper(BoardDAO.class);
		dao.update(b);
	}

	public void delete(int bno) {
		dao = template.getMapper(BoardDAO.class);
		dao.delete(bno);
	}
	
	public List<Map<String, Object>> selectFileList(int bno){
		dao = template.getMapper(BoardDAO.class);
		return dao.selectFileList(bno);
	}
	
}

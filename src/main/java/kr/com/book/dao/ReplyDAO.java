package kr.com.book.dao;

import java.util.List;
import kr.com.book.domain.Reply;

public interface ReplyDAO {

	public List<Reply> readReply(int bno);
	
	public void writeReply(Reply r);
	
	public void updateReply(Reply r);
	
	public void deleteReply(Reply r);
	
	public Reply selectReply(int rno);
}

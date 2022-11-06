package kr.com.book.dao;

import kr.com.book.domain.Member;

public interface MemberDAO {
	
	public void register(Member m);
	
	public Member login(Member m);
	
}

package kr.com.book.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {

	private int bno;
	private int rno;
	private String content;
	private String writer;
	private Date regdate;
	
}

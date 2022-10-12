package kr.com.book.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
}

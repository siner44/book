package kr.com.book.domain;

import lombok.Data;

@Data
public class Page {

	private int page;
	private int perPageNum;
	private int rowStart;
	private int rowEnd;

}
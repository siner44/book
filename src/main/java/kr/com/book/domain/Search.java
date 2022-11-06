package kr.com.book.domain;

import lombok.Data;

@Data
public class Search extends Page {

	private String searchType = "";
	private String keyword = "";

}
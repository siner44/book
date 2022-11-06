package kr.com.book.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Member {

	private String userId;
	private String userPass;
	private String userName;
	private Date regDate;
}

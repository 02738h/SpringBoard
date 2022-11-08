package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private Long bno; //게시물 번호
	private String title; //제목
	private String content; //내용
	private String writer; //작성자
	private Date regdate; //생성 시간
	private Date updateDate; //최종 수정 시간
}

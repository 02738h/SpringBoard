package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total; //전체 페이지 
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10; //페이지 끝 번호
		this.startPage = this.endPage -9; //페이지 시작 번호
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount())); //진짜 끝 페이지
		if(realEnd < this.endPage) {
			this.endPage = realEnd; //끝페이지가 진짜끝페이지보다 크다면, 끝페이지는 진짜끝페이지가 되야함
		}
		
		this.prev = this.startPage > 1; //이전
		this.next = this.endPage < realEnd; //다음
	}
}

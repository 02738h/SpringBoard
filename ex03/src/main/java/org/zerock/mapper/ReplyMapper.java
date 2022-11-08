package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo); //외래키를 사용하는 등록 작업
	
	public ReplyVO read(Long rno); //특정 댓글 읽기
	
	public int delete(Long rno);	//특정 댓글 삭제
	
	public int update(ReplyVO reply);	//댓글의 내용(reply)와 수정시간을 수정
	
	public List<ReplyVO> getListWithPaging( //댓글 목록 페이징 처리
			@Param("cri") Criteria cri, 
			@Param("bno") Long bno);
}

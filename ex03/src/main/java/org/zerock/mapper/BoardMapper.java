package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	
//	@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList(); //현재 테이블에 저장된 모든 데이터를 가져옴
	
	public void insert(BoardVO board); //insert만 처리. bno 값 몰라도됨
	
	public void insertSelectKey(BoardVO board); //insert가 처리되며 bno 값 알아야 함
	
	public BoardVO read(Long bno); //insert가 된 데이터를 조회하는 작업

	public int delete(Long bno);
	
	public int update(BoardVO board);

	public List<BoardVO> getListWithPaging(Criteria cri); //criteria 타입을 파라미터로 사용
	
	public int getTotalCount(Criteria cri); //전체 데이터 개수 처리
}

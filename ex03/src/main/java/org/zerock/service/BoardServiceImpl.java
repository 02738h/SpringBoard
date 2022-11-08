package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		log.info("register....." + board);
		mapper.insertSelectKey(board); //나중에 생성된 게시물의 번호를 확인할 수 있도록 작성함
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get..........." + bno);
		return mapper.read(bno); //특정 게시물 번호의 데이터 가져오기
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify....." + board);
		return mapper.update(board) == 1; //정상적으로 수정되면 1이 반환됨
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove......." + bno);
		return mapper.delete(bno) == 1; //정상적으로 삭제되면 1이 반환됨
	}

//	@Override //현재 테이블에 저장된 모든 데이터를 가져옴
//	public List<BoardVO> getList() {
//		log.info("getList.......");
//		return mapper.getList();
//	}

	@Override //페이징을 해서 테이블에 저장된 모든 데이터를 가져옴
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with criteria: " + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
	
	
	
	
	
	

}

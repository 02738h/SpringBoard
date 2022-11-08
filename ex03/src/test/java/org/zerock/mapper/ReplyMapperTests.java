package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	//테스트 전, 해당 번호의 게시물이 존재하는지 확인하기
	private Long[] bnoArr = {40L, 41L, 42L, 43L, 44L};
	
	@Setter(onMethod_= @Autowired)
	private ReplyMapper mapper;
	
	
	
	@Test //ReplyMapper 타입 객체가 정상적으로 사용가능한지 테스트
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test //댓글 등록 되는지 테스트
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			//게시물의 번호
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("replyer" + i);
			mapper.insert(vo);
		});
	}

	@Test //특정 댓글을 읽어올 수 있는지 테스트
	public void testRead() {
		Long targetRno = 5L;
		ReplyVO vo = mapper.read(targetRno);
		log.info(vo);
	}
	
	@Test //특정 댓글 삭제 테스트
	public void testDelete() {
		Long targetRno = 1L;
		mapper.delete(targetRno);
	}
	
	@Test //특정 댓글 업데이트 테스트, 최종수정일도 현재 시간으로 수정시키기
	public void testUpdate() {
		Long targetRno = 10L;
		ReplyVO vo = mapper.read(targetRno);
		vo.setReply("Update Reply");
		int count = mapper.update(vo);
		log.info("UPDATE COUNT: " + count);
	}
	
	@Test //댓글 목록 가져오기 테스트
	public void testList() {
		Criteria cri = new Criteria();
		//40L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
		
	}
	
	
	

}

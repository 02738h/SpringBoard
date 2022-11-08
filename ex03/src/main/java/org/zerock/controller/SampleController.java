package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
/**	
문자열을 반환. 기존은 JSP 파일 이름으로 처리했지만, @RestController는 순수한 데이터.
produces는 해당 메서드가 생산하는 MIME 타입을 의미함. 문자열이 될수도 있고, MediaType 클래스 이용할수도있음
**/
	@GetMapping(value="/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE:  " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하뇨뇨";
	}
	
/*SampleVO를 리턴하는 메서드
 XML과 JSON 방식의 데이터를 생성*/
	@GetMapping(value="/getSample", 
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, 
							  MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {
		return new SampleVO(112, "스타", "로드");
	}
	
/*@GetMapping이나 @RequestMapping의 produces 속성은 반드시 지정해야 할 필요 없음 (생략가능)	 */	
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(113, "로켓", "라쿤");
	}

	
/*여러 데이터를 한 번에 전송하기 위함 => 리스트 전송*/
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + " Last"))
				.collect(Collectors.toList());
	}
	
/*여러 데이터를 한 번에 전송하기 위함 =>맵 전송 : '키'와 '값'을 가지는 하나의 객체*/
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
/**/
	@GetMapping(value="/check", params= {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		SampleVO vo = new SampleVO(0, ""+height, ""+weight);
		ResponseEntity<SampleVO> result = null;
		
		if(height<150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}

/*/sample/product/bags/1234로 호출할 수 있음*/
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		return new String[] {"category: " + cat, "productid: " + pid};
	}
	
/*post를 적용한 이유는 requestbody가 요청한 내용을 처리하기 때문 */
/*JSON으로 전달되는 데이터를 받아서 Ticket 타입으로 변환*/
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert.......ticket" + ticket);
		
		return ticket;
	}
	
	
}

/**/


package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service; //BoardService 클래스를 service로 객체 생성
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list........................."); //"list"라고 콘솔창에 찍기
//		model.addAttribute("list", service.getList()); 
//		//service 객체의 getList()를 가져와서, 'list'라는 이름으로 추가함
//		//view 단에서는 'list'로 지정한 이름을 통해서 service.getList() 객체를 사용한다.
//	}
	
	@GetMapping("/list") //새롭게 페이징을 한 목록 출력
	public void list(Criteria cri, Model model) {
		log.info("list......:" + cri);
		model.addAttribute("list", service.getList(cri));
//		model.addAttribute("pageMaker", new PageDTO(cri, 123)); 
		//pageDTO클래스를 모델에 담아줌. 전체 데이터수는 123
		
		int total = service.getTotal(cri);
		
		log.info("total:" + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@PostMapping("/register")
	//String을 리턴타입으로 지정
	//RedirectAttributes를 파라미터로 지정함 -> 등록작업이 끝난 후, 다시 목록화면으로 이동하기 위함
	//추가적으로 새롭게 등록된 게시물의 번호를 같이 전달하기 위해 RedirectAttributes 이용
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register:" + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno()); //결과: 새롭게 등록된 게시물 번호 저장메소드
		//addFlashAttribute()의 경우 일회성으로만 데이터를 전달함=> 보관된 데이터는 한번만 사용할 수 있음
		return "redirect:/board/list"; //다시 리스트로 리다이렉트
	}
	
	@GetMapping("/register")
	public void register() {
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		//bno값을 좀 더 명시적으로 처리하기 위해 requestparam을 사용
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno)); 
		//화면쪽으로 해당 번호의 게시물을 전달해야 하므로 Model을 파라미터로 지정
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify: " + board);
		if(service.modify(board)) { //수정 여부를 boolean으로 처리함
			rttr.addFlashAttribute("result", "success"); //성공한 경우에만 redirectattributes에 추가 
		}
	
//		Criteria 클래스에 UriComponentsBuilder를 사용했기 때문에 rttr필요X
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, 
			@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		//삭제 후, 페이지의 이동이 필요하기 때문에 RedirectAttributes 사용
		log.info("remove..." + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
//		Criteria 클래스에 UriComponentsBuilder를 사용했기 때문에 rttr필요X
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list"; //삭제 처리 후 다시 목록 페이지로 이동
	}
	
	
	
}

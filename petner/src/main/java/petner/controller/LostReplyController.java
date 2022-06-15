package petner.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import petner.model.Lost;
import petner.model.LostReply;
import petner.service.LostReplyService;
import petner.service.LostService;

@Controller
public class LostReplyController {

	@Autowired
	private LostReplyService rls;	
	
	@Autowired
	private LostService ls;
	
	@RequestMapping("replyLostInsert")
//	public String replyLostInsert(@RequestParam("lost_no") int num, String mem_id , Model model ) {
		public String replyLostInsert( Model model,HttpSession session ) {
						
		/*
		 * // 상세정보 구하기 BoardBean board = boardService.board_cont(board_num);
		 */
//		ReplyLost replylost = rls.select1(num);
		
		int num = 10;
		String mem_id="test";
		
		
		Lost lost = ls.select(num);
		
		
		model.addAttribute("num", num);
		model.addAttribute("mem_id", mem_id);
		model.addAttribute("lost", lost);
		
				
		return "lost/replyinsertform";
	}
	
	/* 댓글 저장 -> 여기서 lost_no과 lostreply_content, mem_id를 lostreply로 받은 이유는 해당 컬럼들이 모두 lostreply에 존재하기 때문이다. */
	@RequestMapping(value = "/replyLostInsertOk", method = RequestMethod.POST)
	public String replyLostInsertOk(LostReply lostreply, Model model ) throws Exception {
//		public String replyLostInsertOk(@ModelAttribute LostReply lostreply, int lost_no, String mem_id, Model model ) throws Exception {
//	public String board_write_ok(@RequestParam HashMap board)
//			throws Exception {
	
		System.out.println("lost_no:"+lostreply.getLost_no());
		System.out.println("mem_id:"+lostreply.getMem_id());
		System.out.println("content:"+lostreply.getLostreply_content());
				
		rls.insertreply(lostreply);		
		model.addAttribute("lost_no", lostreply.getLost_no());
		return "redirect:replyList?lost_no="+lostreply.getLost_no(); // 부모 번호값 보내기
	}
	
	/* 게시판 목록 */
	@RequestMapping(value = "/replyList")
	public String list(int lost_no, Model model, HttpSession session) throws Exception {

		String mem_id = (String)session.getAttribute("mem_id");
		System.out.println("lost_no:" + lost_no);
		
		Lost lost = ls.select(lost_no);
		String reply_id = lost.getMem_id(); // 댓글 작성자의 아이디
//		LostReply lostreply = rls.getMemid(reply_id);
		List<LostReply> replylist = rls.list(lost_no);
		System.out.println("replylist:"+replylist);
		
		model.addAttribute("lost", lost);
		model.addAttribute("replylist", replylist);
		model.addAttribute("mem_id", mem_id);
		model.addAttribute("reply_id", reply_id);
		
		return "lost/replylistview";
	}
	
	@RequestMapping("/replyUpdate")				// 댓글 내용 수정
	public String replyUpdate(LostReply lostreply,
								int lostreply_no,
								int lostreply_content,
								int lost_no,
								Model model) {
		
		System.out.println(lostreply_no);
		System.out.println(lostreply_content);
		System.out.println(lost_no);
		
		rls.update(lostreply);							// 댓글 내용 수정
		
		return "redirect:replyList?lost_no="+lostreply.getLost_no();	//다시 댓글 목록을 요청
	}
	
	@RequestMapping("/replyDelete")		// 댓글 삭제
	public String delete(LostReply lostreply, Model model) {
		
		int lost_no = lostreply.getLost_no(); // dto 형태로 값을 가져와서 삭제하기전 부모의 lost_no를 따로 빼내서 구하고 그 뒤 delete()를 수행하여 지우고 lost_no값을  redirect로 보낸다.
		rls.delete(lostreply.getLostreply_no());
		/* return "redirect:replyList?lost_no=" + lostreply.getLostreply_no(); */
		return "redirect:replyList?lost_no=" + lost_no;
	}
	
	/* 게시판 답변달기 저장 */
	@RequestMapping(value = "/rreply", method = RequestMethod.POST)
	public String rreply(@ModelAttribute LostReply lostreply) throws Exception {
		int lost_no = lostreply.getLost_no();
		rls.rreply(lostreply);

		return "redirect:replyList?lost_no=" + lost_no;
	}
	
	
	
}

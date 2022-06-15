package petner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import petner.model.Tipboard;
import petner.model.Tipreply;
import petner.service.TipboardService;
import petner.service.TipreplyService;

@Controller
public class TipreplyController {

	@Autowired
	private TipreplyService trs;
	
	@Autowired
	private TipboardService tbs;
	
	@RequestMapping("insertTipreply")
	public String insertTipreply(Tipreply tipreply, Model model) {	
		
		System.out.println("tip_no:"+tipreply.getTip_no());
		System.out.println("mem_id:"+tipreply.getMem_id());
		System.out.println("tipreply_content:"+tipreply.getTipreply_content());
		
		
		 String reply_content = tipreply.getTipreply_content().replace("\n", "<br>");
		 
		 tipreply.setTipreply_content(reply_content); 
		
		trs.insertTipreply(tipreply); 
		System.out.println("tipreply");
		return "redirect:slist?tip_no="+tipreply.getTip_no();
	}
	
	@RequestMapping("slist")
	public String slist(int tip_no, Model model) {
		
		Tipboard tipboard = tbs.getTip(tip_no);
		List<Tipreply> slist = trs.getTipreplyList(tip_no);
		model.addAttribute("slist", slist);
		model.addAttribute("tipboard", tipboard);
		
		return "tip_reply/slist";
	}
	
	@RequestMapping("deleteTipreply")
	public String deleteTipreply(int tip_no, int tipreply_no, Model model) {
		
		trs.deleteTipreply(tipreply_no);
		
		return "redirect:slist?tip_no="+tip_no;
	}
	
	@RequestMapping("/updateTipreply")
	public String updateTipreply(Tipreply tipreply, Model model) {
		
		trs.updateTipreply(tipreply);
		
		return "redirect:slist?tip_no="+tipreply.getTip_no();
	}
	
	@RequestMapping("addreply")
	public String addreply(Tipreply tipreply, Model model) {
		
		System.out.println("tipreply_seq(controller):"+tipreply.getTipreply_seq());
		System.out.println("tipreply_lev(controller):"+tipreply.getTipreply_lev());
		System.out.println("들어옴");
		
		int tip_no = tipreply.getTip_no();
		System.out.println(tipreply.getTipreply_no());
		System.out.println(tipreply.getMem_id());
		System.out.println(tipreply.getTipreply_lev());
		System.out.println(tipreply.getTip_no());
		System.out.println(tipreply.getTipreply_seq());
		System.out.println(tipreply.getTipreply_content());
		trs.addreply(tipreply);
		
		return "redirect:slist?tip_no="+tip_no;
	}
}

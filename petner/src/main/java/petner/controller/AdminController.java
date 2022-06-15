package petner.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import petner.model.Admin;
import petner.service.AdminService;


@Controller
public class AdminController {

	@Autowired
	private AdminService as;
	
	// 관리자 등록폼
	@RequestMapping("admin_insertForm")
	public String insertForm() {
		return "admin/insertForm";
	}
	
	// 관리자 등록
	@RequestMapping("admin_insert")
	public String insert(Admin admin, Model model) {
		
		int insertResult = as.insert(admin);
		model.addAttribute("insertResult", insertResult);
		
		return "admin/result";
	}
	
	// 관리자 로그인
	@RequestMapping("admin_loginform")
	public String admin_loginform() {
		return "admin/admin_login";
	}
	
	// 관리자 정보 구해오기
	@RequestMapping("admin_login") 
	public String getAdmin(String admin_id, HttpSession session, Model model) {
	  
	Admin admin = as.getAdmin(admin_id);
	model.addAttribute("admin", admin);
	session.setAttribute("mem_id", admin);
	  
	return "redirect:productlist2";
	}
	 
}

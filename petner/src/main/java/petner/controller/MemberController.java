package petner.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import petner.model.Member;
import petner.model.Seller;
import petner.service.MemberService;
import petner.service.SellerService;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;
	
	@Autowired
	private SellerService ss;

	@RequestMapping("Main")
	public String Main() {
		return "Main";
	}

	// 회원가입 폼 이동
	@RequestMapping("Sign")
	public String Sign() {
		return "member/Sign";
	}

	/*
	 * @RequestMapping("Sign_up") public String Sign_up() throws Exception { return
	 * "redirect:Main"; }
	 */

	// 회원가입
	@RequestMapping(value = "sign1", method = RequestMethod.POST)
	public String Sign_up(@RequestParam("mem_profile1") MultipartFile mf, Member member, HttpServletRequest request,
			Model model) throws Exception {

		System.out.println("mem_phone1:" + member.getMem_phone1());
		System.out.println("controller in");

		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize(); // 첨부파일의 크기 (단위:Byte)

		String path = request.getRealPath("upload/member");
		System.out.println("mf=" + mf);
		System.out.println("filename=" + filename);
		System.out.println("size=" + size);
		System.out.println("Path=" + path);
		int result = 0;

		String file[] = new String[2];

		String newfilename = "";

		if (filename != "") { // 첨부파일이 전송된 경우

			// 파일 중복문제 해결
			String extension = filename.substring(filename.lastIndexOf("."), filename.length());
			System.out.println("extension:" + extension);

			UUID uuid = UUID.randomUUID(); // 파일이름 랜덤 생성

			newfilename = uuid.toString() + extension;
			System.out.println("newfilename:" + newfilename);

			StringTokenizer st = new StringTokenizer(filename, ".");
			file[0] = st.nextToken(); // 파일명 Koala
			file[1] = st.nextToken(); // 확장자 jpg

			if (size > 1000000) { // 1000KB
				result = 1;
				model.addAttribute("result", result);

				return "member/uploadResult";

			} else if (!file[1].equals("jpg") && !file[1].equals("gif") && !file[1].equals("png")) {

				result = 2;
				model.addAttribute("result", result);

				return "member/uploadResult";
			}
		}
		if (size > 0) { // 첨부파일이 전송된 경우

			mf.transferTo(new File(path + "/" + newfilename)); // 예외처리를 해주어야 한다. 예외처리를 하지 않으면 오류 발생

		}

		member.setMem_profile(newfilename);

		ms.Sign_up(member); // 회원가입 실행
		
		// Mail Server 설정
			String charSet = "utf-8";
			String hostSMTP = "smtp.naver.com";
			String hostSMTPid = "rndjhds@naver.com";
			String hostSMTPpwd = "wodls135@"; // 비밀번호 입력해야함

			// 보내는 사람 EMail, 제목, 내용
			String fromEmail = "rndjhds@naver.com";
			String fromName = "관리자";
			String subject = "회원가입 축하";

			// 받는 사람 E-Mail 주소
			String mail = member.getMem_email()+"@"+member.getMem_domain();

			try {
				HtmlEmail email = new HtmlEmail();
				email.setDebug(true);
				email.setCharset(charSet);
				email.setSSL(true);
				email.setHostName(hostSMTP);
				email.setSmtpPort(587);

				email.setAuthentication(hostSMTPid, hostSMTPpwd);
				email.setTLS(true);
				email.addTo(mail, charSet);
				email.setFrom(fromEmail, fromName, charSet);
				email.setSubject(subject);
				email.setHtmlMsg("<p align = 'center'>회원가입 축하</p><br>" + "<div align='center'> 당신의 회원가입을 축하합니다."
					+member.getMem_id()+ "님</div>");
				email.send();
				} catch (Exception e) {
					System.out.println(e);
				}

		
		return "redirect:productlist1";
	}

	// 중복 ID 체크
	@RequestMapping(value = "member_idcheck")
	public String mem_idcheck(@RequestParam("mem_id") String mem_id, Model model) {
		System.out.println("mem_id:" + mem_id);

		int result = ms.mem_idcheck(mem_id);

		model.addAttribute("result", result);

		return "member/idCheckResult";
	}

	// 로그인 폼
	@RequestMapping("Loginform")
	public String Loginform() {
		return "member/Loginform";
	}

	// 로그인
	@RequestMapping("Login")
	public String Login(Member member, HttpSession session, Model model) {

		Member old = ms.getuser(member.getMem_id());

		int result = 0;

		if (old == null) {
			result = 1;
			model.addAttribute("result", result);
			System.out.println("result:" + result);
			return "member/Logincheck";
		} else {

			if (old.getMem_pwd().equals(member.getMem_pwd())) {
				session.setAttribute("mem_id", member.getMem_id());
				session.setAttribute("mem_type", old.getMem_type());
				if(old.getMem_type().equals("사업자")) {
					Seller seller = ss.getSeller_no(old.getMem_id());
						if(seller.getSeller_accept() == 1) {
							session.setAttribute("seller_no", seller.getSeller_no());
						}
					}
				return "redirect:productlist1";
				}
				else { // 비밀번호가 틀렸을경우
					result = 2;
					model.addAttribute("result", result);
					System.out.println("result:" + result);
					return "member/Logincheck";
			
			}
		}

	}


	// 로그아웃
	@RequestMapping("Logout")
	public String Logout() {
		return "member/Logout";
	}

	// 마이페이지 이동
	@RequestMapping("Mypage")
	public String Mypageform(String mem_id, Model model) {

		Member member = ms.getuser(mem_id);

		model.addAttribute("member", member);

		return "member/Mypage";
	}

	// 회원 정보 수정 폼
	@RequestMapping("updateMember")
	public String updateMember(String mem_id, Model model) {

		Member member = ms.getuser(mem_id);

		model.addAttribute("member", member);

		return "member/updateform";
	}

	// 회원 정보 수정
	@RequestMapping("mem_update")
	public String mem_update(@RequestParam("mem_profile1") MultipartFile mf, HttpServletRequest request, Member member,
			Model model) throws Exception {

		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize(); // 첨부파일의 크기 (단위:Byte)

		String path = request.getRealPath("upload/member");
		System.out.println("mf=" + mf);
		System.out.println("filename=" + filename);
		System.out.println("size=" + size);
		System.out.println("Path=" + path);
		int result = 0;

		String file[] = new String[2];

		String newfilename = "";

		if (filename != "") { // 첨부파일이 전송된 경우

			// 파일 중복문제 해결
			String extension = filename.substring(filename.lastIndexOf("."), filename.length());
			System.out.println("extension:" + extension);

			UUID uuid = UUID.randomUUID(); // 파일이름 랜덤 생성

			newfilename = uuid.toString() + extension;
			System.out.println("newfilename:" + newfilename);

			StringTokenizer st = new StringTokenizer(filename, ".");
			file[0] = st.nextToken(); // 파일명 Koala
			file[1] = st.nextToken(); // 확장자 jpg

			if (size > 1000000) { // 1000KB
				result = 1;
				model.addAttribute("result", result);

				return "member/uploadResult";

			} else if (!file[1].equals("jpg") && !file[1].equals("gif") && !file[1].equals("png")) {

				result = 2;
				model.addAttribute("result", result);

				return "member/uploadResult";
			}
		}
		if (size > 0) { // 첨부파일이 전송된 경우

			mf.transferTo(new File(path + "/" + newfilename)); // 예외처리를 해주어야 한다. 예외처리를 하지 않으면 오류 발생

		}

		Member old = ms.getuser(member.getMem_id());

		if (size > 0) { // 첨부파일이 전송된 경우
			member.setMem_profile(newfilename);
		} else { // 첨부파일이 전송되지 않은 경우
			member.setMem_profile(old.getMem_profile());
		}

		ms.mem_update(member);
		model.addAttribute("mem_id", member.getMem_id());

		return "redirect:Mypage";
	}

	// 회원 삭제 폼
	@RequestMapping("deleteMember")
	public String deleteMember(String mem_id, Model model) {

		Member member = ms.getuser(mem_id);
		model.addAttribute("member", member);

		return "member/deleteform";
	}

	@RequestMapping("mem_delete")
	public String mem_delete(Member member, Model model, HttpSession session, HttpServletRequest request) {

		String up = request.getRealPath("upload/member");
		String fname = member.getMem_profile();
		System.out.println("fname:" + fname);
		System.out.println("up:" + up);

		// 디비에 저장된 기존 이진파일명을 가져옴
		if (fname != null) {// 기존 이진파일이 존재하면
			File delFile = new File(up);
			delFile.mkdir();
			System.out.println("delFile:" + delFile);
			File[] f = delFile.listFiles();
			for (int i = 0; i < f.length; i++) {
				if (f[i].getName().equals(fname)) {
					System.out.println("f[i]:" + f[i].getName());
					f[i].delete();// 기존 이진파일을 삭제
				}
			}
		}
		ms.mem_delete(member.getMem_id());// 삭제 메서드 호출

		session.invalidate(); // 세션만료

		return "redirect:productlist1";
	}
	
	/* 비번찾기 폼 */
	@RequestMapping(value = "/pwd_find.nhn")
	public String pwd_find() {
		return "member/pwd_find";
		// member 폴더의 pwd_find.jsp 뷰 페이지 실행
	}
	
	/* 비번찾기 완료 */
	@RequestMapping("pwd_find_ok.nhn")
	public String pwd_find_ok(Member member, Model model) throws Exception {

		Member old = ms.getuser(member.getMem_id());

		if (old == null) {// 값이 없는 경우

			return "member/pwdResult";

		} else {

			// Mail Server 설정
			String charSet = "utf-8";
			String hostSMTP = "smtp.naver.com";
			String hostSMTPid = "rndjhds@naver.com";
			String hostSMTPpwd = "wodls135@"; // 비밀번호 입력해야함

			// 보내는 사람 EMail, 제목, 내용
			String fromEmail = "rndjhds@naver.com";
			String fromName = "관리자";
			String subject = "비밀번호 찾기";

			// 받는 사람 E-Mail 주소
			String mail = old.getMem_email()+"@"+old.getMem_domain();

			try {
				HtmlEmail email = new HtmlEmail();
				email.setDebug(true);
				email.setCharset(charSet);
				email.setSSL(true);
				email.setHostName(hostSMTP);
				email.setSmtpPort(587);

				email.setAuthentication(hostSMTPid, hostSMTPpwd);
				email.setTLS(true);
				email.addTo(mail, charSet);
				email.setFrom(fromEmail, fromName, charSet);
				email.setSubject(subject);
				email.setHtmlMsg("<p align = 'center'>비밀번호 찾기</p><br>" + "<div align='center'> 비밀번호 : "
						+ old.getMem_pwd() + "</div>");
				email.send();
			} catch (Exception e) {
				System.out.println(e);
			}

			model.addAttribute("pwdok", "등록된 email을 확인 하세요~!!");
			return "member/pwd_find";

		}

	}
}

package petner.controller;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import petner.model.Product;
import petner.model.Qna;
import petner.service.MemberService;
import petner.service.PagingPgm;
import petner.service.ProductService;
import petner.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qs;
	
	@Autowired
	private MemberService ms;

	@Autowired
	private ProductService ps;
	
	
	// 글목록
	@RequestMapping("qna_list")
	public String list(String page, Qna qna, Model model) throws Exception {

		final int rowPerPage = 10; // 화면에 출력할 데이터 개수
		if (page == null || page.equals("")) {
			page = "1";
		}
		int currentPage = Integer.parseInt(page); // 현재 페이지 번호
		int total = qs.getTotal(qna); // 검색(데이터 개수)

		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		qna.setStartRow(startRow);
		qna.setEndRow(endRow);

		int no = total - startRow + 1; // 화면 출력 번호
		List<Qna> list = qs.list(qna);
		
		model.addAttribute("qna", qna);

		// 페이징 처리
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);

		// 검색
		model.addAttribute("search", qna.getSearch());
		model.addAttribute("keyword", qna.getKeyword());

		return "qna/list";
	}
	
	// 글작성 폼
	@RequestMapping("qna_insertForm")
	public String insertForm(Qna qna, Model model) {
		if(qna.getProduct_no() > 0) {
			model.addAttribute("product_no", qna.getProduct_no());
		}else {
			model.addAttribute("product_no", 0);
		}
		return "qna/insertForm";
	}
	
	// 글작성
	@RequestMapping("qna_insert")
	public String insert(@RequestParam("qna_file1") MultipartFile mf,
						HttpServletRequest request, Qna qna,
						Model model) throws Exception {
		// 첨부파일
		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize();
		
		String path = request.getRealPath("upload/qna");
		System.out.println("path:"+path);
		
		int uploadResult = 0;
		String file[] = new String[2];
		
		String newfilename = "";
		
		if(filename != "") {	// 첨부파일이 전송된 경우
			
			// 파일 중복문제 해결
			String extension = filename.substring(filename.lastIndexOf("."), filename.length());
			System.out.println("extension:"+extension);
			
			UUID uuid = UUID.randomUUID();
			
			newfilename = uuid.toString() + extension;
			System.out.println("newfilename:"+newfilename);
			
			StringTokenizer st = new StringTokenizer(filename, ".");
			file[0] = st.nextToken();	// 파일명
			file[1] = st.nextToken();	// 확장자
			
			if(!file[1].equals("jpg") &&
			   !file[1].equals("gif") &&
			   !file[1].equals("png") ) {
				
				uploadResult = 1;
				model.addAttribute("uploadResult", uploadResult);
				
				return "qna/uploadResult";
			}
		}
		
		if (size > 0) {					// 첨부파일이 전송된 경우
			mf.transferTo(new File(path + "/" + newfilename));
		}
		
		qna.setQna_file(newfilename);
		
		int insertResult = qs.insert(qna);
		
		model.addAttribute("insertResult", insertResult);

		return "qna/result";
	}
	
	// 상세페이지, 수정폼, 삭제폼, 답변폼
	@RequestMapping("qna_view")
	public String veiw(int qna_no, String qna_pwd, int page, String state,
						Model model, HttpSession session ) throws Exception  {

		qs.hit(qna_no);					// 조회수 1 증가
		Qna qna = qs.getQna(qna_no);	// 상세정보 구하기
		
//		String mem_id = (String) session.getAttribute("mem_id");

		model.addAttribute("qna", qna);
		model.addAttribute("qna_no", qna_no);
		model.addAttribute("page", page);

		if (state.equals("view")) {
			return "qna/view";
		} else if (state.equals("update")) {
			return "qna/updateForm";
		} else if (state.equals("delete")) {
			return "qna/deleteForm";
		} else if (state.equals("reply")) {
			return "qna/replyForm";
		}

		return null;
	}
	
	// 글수정
	@RequestMapping("qna_update")
	public String update(@RequestParam("qna_file1") MultipartFile mf,
						HttpServletRequest request, Qna qna, String qna_pwd2,
						int qna_no, int page, Model model) throws Exception {

		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize();
		
		String path = request.getRealPath("upload/qna");
		System.out.println("path:"+path);
		
		int uploadResult = 0;
		String file[] = new String[2];
		
		String newfilename = "";
		
		if(filename != "") {	// 첨부파일이 전송된 경우
			
			// 파일 중복문제 해결
			String extension = filename.substring(filename.lastIndexOf("."), filename.length());
			System.out.println("extension:"+extension);
			
			UUID uuid = UUID.randomUUID();
			
			newfilename = uuid.toString() + extension;
			System.out.println("newfilename:"+newfilename);
			
			StringTokenizer st = new StringTokenizer(filename, ".");
			file[0] = st.nextToken();	// 파일명
			file[1] = st.nextToken();	// 확장자
			
			if(!file[1].equals("jpg") &&
			   !file[1].equals("gif") &&
			   !file[1].equals("png") ) {
				uploadResult = 1;
				model.addAttribute("uploadResult", uploadResult);
				
				return "qna/uploadResult";
			}
		}
		
		if (size > 0) {				// 첨부파일이 전송된 경우
			mf.transferTo(new File(path + "/" + newfilename));
		}
		
		Qna oldQna = this.qs.getQna(qna_no);
		
		if (size > 0 ) { 			// 첨부파일이 수정되면
			qna.setQna_file(newfilename);			
		} else { 					// 첨부파일이 수정되지 않으면
			qna.setQna_file(oldQna.getQna_file());
		}
		
		int updateResult = 0;
		if(!qna.getQna_pwd().equals(qna_pwd2)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
		} else {
			updateResult = qs.update(qna);
		}
		
		model.addAttribute("updateResult", updateResult);
		model.addAttribute("page", page);
		model.addAttribute("qna", qna);
		model.addAttribute("qna_no", qna_no);
		
		return "qna/result";
	}
	
	// 글삭제
	@RequestMapping("qna_delete")
	public String delete(HttpServletRequest request, HttpSession session, int qna_no,
						int page, Qna qna, Model model) throws Exception {
		
		String up = request.getRealPath("upload/qna");
		String fname = qna.getQna_file();
		System.out.println("up:"+up);
		System.out.println("fname:"+fname);
		
		// DB에 저장된 기존 이진파일명을 가져옴
		if (fname != "") {				// 기존 이진파일이 존재하면
			
			File delFile = new File(up);
			delFile.mkdir();
			File[] f = delFile.listFiles();
			
			for(int i=0; i<f.length; i++) {
				if(f[i].getName().equals(fname)) {
					f[i].delete();		// 기존 이진파일을 삭제
				}
			}
		}
		
		int deleteResult = qs.delete(qna_no);
		model.addAttribute("deleteResult", deleteResult);
		model.addAttribute("page", page);

		return "qna/result";
	}
	
	// 답변
	@RequestMapping("qna_reply")
	public String reply(Qna qna, int page, Model model) throws Exception {

		qs.replyUp(qna);					  // 기존 qna_seq 값 1 증가
		qna.setQna_lev(qna.getQna_lev() + 1); // 부모보다 1 증가된 값을 저장함
		qna.setQna_seq(qna.getQna_seq() + 1);
		
		int replyResult = qs.reply(qna);
		qs.replyAnswer(qna);
		model.addAttribute("replyResult", replyResult);
		model.addAttribute("page", page);
		
		return "qna/result";
	}
	
	// 비밀글 메시지
	@RequestMapping("qna_msg")
	public String Msg(int msg, Model model) throws Exception {
		
		model.addAttribute("msg", msg);
		
		return "qna/result";
	}
}


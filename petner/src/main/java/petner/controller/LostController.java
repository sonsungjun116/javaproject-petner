package petner.controller;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ibatis.sqlmap.engine.scope.SessionScope;

import petner.service.PagingPgm;
import petner.model.Lost;
import petner.model.Seller;
import petner.service.LostService;


@Controller
public class LostController {

	@Autowired
	private LostService ls;
	
	@RequestMapping("lostList")
	public String initList() {		
		return "redirect:lostListView?pageNum=1";
					
	}
	
	@RequestMapping("lostListView")
	public String lostListView(String pageNum ,Lost lost ,Model model) {
//		public String lostListView(@PathVariable String pageNum ,Lost lost ,Model model) {
		final int rowPerPage = 10;	// 화면에 출력할 데이터 갯수 limit
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);	// 현재 페이지 번호
		int total = ls.getTotal(lost); // 검색 (데이터 갯수)
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		lost.setStartRow(startRow);
		lost.setEndRow(endRow);
		// List<Board> list = bs.list(startRow, endRow);
		int no = total - startRow + 1;	// 화면 출력 번호
		List<Lost> list = ls.list(lost); // search, keyword, startRow, endRow검색된 search와 keyword를 통해서 각 번호를 부여하고 내림차순으로 번호를 부여해서 리스트를 생성
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		// 검색
		model.addAttribute("search", lost.getSearch());
		model.addAttribute("keyword", lost.getKeyword());
		
		return "lost/lostlistview";
	}
	
	
	
	// 회원가입 폼	
	@RequestMapping("lostInsertForm")
//	public String lostInsertForm(String mem_id, String pageNum, Model model, HttpSession session) {
		public String lostInsertForm( String pageNum, Model model, HttpSession session) {
				System.out.println("lostInsertForm");
				String mem_id = (String) session.getAttribute("mem_id");
				//				mem_id = "test";
				pageNum = "1";
				int pageNum1 = Integer.parseInt(pageNum);

		model.addAttribute("pageNum", pageNum1);
		model.addAttribute("mem_id", mem_id);
		
		
		return "lost/lostinsertform";
//		return "lost/view";
		
	}
	
	/* 회원 가입 저장(fileupload) */
	@RequestMapping(value = "insertAction", method = RequestMethod.POST)
	public String insertAction(@RequestParam("lost_file1") MultipartFile mf, 
								@ModelAttribute Lost lost, // 앞에 @modelattribute어노테이션이 생략되어있다
								 HttpServletRequest request,
								 @RequestParam double lat, 
								 @RequestParam double lng,
								 Model model) throws Exception {

		System.out.println(lat);
		System.out.println(lng);
		System.out.println("insertAction");
		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize(); 	// 첨부파일의 크기 (단위:Byte) ,getsize는 long형 변수(8byte)이기 때문에 형변환해야한다 int(4byte)이므로 다운캐스팅해야함

		String path = request.getRealPath("upload/lost"); // webapp 하위에 upload파일 생성
		System.out.println("mf=" + mf);
		System.out.println("filename=" + filename); // filename="Koala.jpg"
		System.out.println("size=" + size);
		System.out.println("Path=" + path);
		int result=0;
		
		String file[] = new String[2];
//		file = filename.split(".");
//		System.out.println(file.length);
//		System.out.println("file0="+file[0]);
//		System.out.println("file1="+file[1]);
		
		String newfilename = "";
	
	if(filename != ""){	 // 첨부파일이 전송된 경우	
		
		// 파일 중복문제 해결
		String extension = filename.substring(filename.lastIndexOf("."), filename.length());
		System.out.println("extension:"+extension); //  ex) .jpg
		
		UUID uuid = UUID.randomUUID();
		
		newfilename = uuid.toString() + extension;
		System.out.println("newfilename:"+newfilename);		
		
		StringTokenizer st = new StringTokenizer(filename, ".");
		file[0] = st.nextToken();		// 파일명		Koala
		file[1] = st.nextToken();		// 확장자	    jpg
		
		if(size > 100000){				// 100KB
			result=1;
			model.addAttribute("result", result);
			
			return "lost/uploadResult";
			
		}else if(!file[1].equals("jpg") &&
				 !file[1].equals("gif") &&
				 !file[1].equals("png") ){
			
			result=2;
			model.addAttribute("result", result);
			
			return "lost/uploadResult";
		}
	}	

		if (size > 0) { // 첨부파일이 전송된 경우

			mf.transferTo(new File(path + "/" + newfilename));

		}
			
		String mem_id = request.getParameter("mem_id").trim();
		String lost_pwd = request.getParameter("lost_pwd").trim();
		String lost_content = request.getParameter("lost_content").trim();
		String lost_title = request.getParameter("lost_title").trim();
				
		lost.setMem_id(mem_id);
		lost.setLost_pwd(lost_pwd);
		lost.setLost_content(lost_content);
		lost.setLost_file(newfilename);
		lost.setLost_title(lost_title);
		
		
		ls.insertLost(lost);		

		return "lost/insert"; // view 페이지가 아닐때는 redirect:를 붙인다/ 팝업을 띄우고 리스트로 이동 board1참조
	}
	
	
	/* 회원정보 수정 폼 */
	@RequestMapping(value = "lostUpdateForm")
	public String lostUpdateForm(HttpSession session, int num, String pageNum, @RequestParam double lat, 
			 @RequestParam double lng, Model model) throws Exception {


		String mem_id = (String) session.getAttribute("mem_id");

//		MemberBean editm = memberService.userCheck(id); //아이디확인
		System.out.println("lat:"+lat);
		System.out.println("lng"+lng);
		Lost lost = ls.select(num);
		lost.setLat(lat);
		lost.setLng(lng);
		
		model.addAttribute("lat", lat);
		model.addAttribute("lng", lng);
		model.addAttribute("lost", lost);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("mem_id", mem_id);

		return "lost/lostupdateform";
	}
	
	// 가짜 업데이트 폼 -> 수정하기 위한 lat값과 lng값을 위한
	@RequestMapping(value = "lostUpdateForm2")
	public String lostUpdateForm2(HttpSession session, int num, String pageNum, @RequestParam double lat, 
			 @RequestParam double lng, Model model) throws Exception {

		String mem_id = (String) session.getAttribute("mem_id");
		
		
//		MemberBean editm = memberService.userCheck(id); //아이디확인
		
		
		Lost lost = ls.select(num);
		
		model.addAttribute("lat", lat);
		model.addAttribute("lng", lng);
		model.addAttribute("lost", lost);
		model.addAttribute("pageNum", pageNum);

		return "lost/lostupdateform2";
	}
	
		
	/* 회원정보 수정(fileupload) */
	@RequestMapping(value = "lostUpdateFormOk", method = RequestMethod.POST)
	public String lostUpdateFormOk(@RequestParam("lost_file1") MultipartFile mf, 
								 Lost lost,
								 String pageNum,
								 HttpServletRequest request, 
								 HttpSession session,
								  @RequestParam double lat, 
								 @RequestParam double lng,
								 Model model) throws Exception {

		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize();		
		
		String path = request.getRealPath("upload/lost");
		System.out.println("path:"+path);
		
		int result=0;		
		String file[] = new String[2];
//		file = filename.split(".");
//		System.out.println(file.length);
//		System.out.println("file0="+file[0]);
//		System.out.println("file1="+file[1]);
		
		String newfilename = "";
		
	if(filename != ""){	 // 첨부파일이 전송된 경우		
		
		// 파일 중복문제 해결
		String extension = filename.substring(filename.lastIndexOf("."), filename.length());
		System.out.println("extension:"+extension);
				
		UUID uuid = UUID.randomUUID();
				
		newfilename = uuid.toString() + extension;
		System.out.println("newfilename:"+newfilename);			
		
		StringTokenizer st = new StringTokenizer(filename, ".");
		file[0] = st.nextToken();		// 파일명
		file[1] = st.nextToken();		// 확장자	
		
		if(size > 100000){				// 100KB
			
			result=1;
			model.addAttribute("result", result);
			
			return "lost/uploadResult";
			
		}else if(!file[1].equals("jpg") &&
				 !file[1].equals("gif") &&
				 !file[1].equals("png") ){
			
			result=2;
			model.addAttribute("result", result);
			
			return "lost/uploadResult";
		}	
		
	}
		
		if (size > 0) { // 첨부파일이 전송된 경우

			mf.transferTo(new File(path + "/" + newfilename)); // 첨부파일을 서버로 전송

		}		
		

//		String id = (String) session.getAttribute("id");

			

//		MemberBean editm = this.memberService.userCheck(id);
		//수정하기 직전에 이전의 이미지파일을 불러 오기 위해서 
		Lost lostold = this.ls.select(lost.getLost_no());
		
		// 첨부파일을 수정하지 않았을때 기존 파일이 지워지지 않게 하는 코드
		if (size > 0 ) { 
			
			
			// 첨부 파일이 수정되면
			lost.setLost_file(newfilename);			
		} else { 					// 첨부파일이 수정되지 않으면
			lost.setLost_file(lostold.getLost_file());
		}

		
		String lost_content = request.getParameter("lost_content").trim();
		String lost_title = request.getParameter("lost_title").trim();
	
		lost.setLost_title(lost_title);
		lost.setLost_content(lost_content);
		
		
		int result1 = ls.update(lost);
		lost.setLat(lat);
		lost.setLng(lng);
		model.addAttribute("result1", result1);
		model.addAttribute("pageNum", pageNum);
		
		return "lost/update";
		

		
	}

	/* 게시판 삭제 폼 */
	@RequestMapping(value = "lostDeleteForm")
	public String lostDeleteForm(HttpSession session, int num, String pageNum, Model model) throws Exception {

//		String id = (String) session.getAttribute("id");
		
		Lost lost = ls.select(num);
		
		model.addAttribute("lost", lost);
		model.addAttribute("pageNum", pageNum);

		return "lost/lost_del";
	}
	

	/* 게시판 삭제 완료 */
	@RequestMapping(value = "lostDeleteFormOk", method = RequestMethod.POST)
	public String lostDeleteFormOk(@RequestParam("lost_pwd") String lost_pwd, // RequestParam을 지워도 된다							   
							    HttpSession session,
							    Lost lost,
							    int lost_no,
							    HttpServletRequest request,
							    Model model) throws Exception {

//		String id = (String) session.getAttribute("id");
//		MemberBean member = this.memberService.userCheck(id);

		/*
		 * if (!lost.getLost_pwd().equals(lost_pwd)) {
		 * 
		 * return "lost/deleteResult";
		 * 
		 * } else { // 비번이 같은 경우
		 */			
			String up = session.getServletContext().getRealPath("upload/lost"); // request객체 = session.getServletContext()
//			String up = request.getRealPath("upload/lost");
			String fname = lost.getLost_file();
			System.out.println("up:"+up);
			System.out.println("fname:"+fname);
			
			// 디비에 저장된 기존 이진파일명을 가져옴
			if (fname != null) {// 기존 이진파일이 존재하면
				
				File delFile = new File(up);
				delFile.mkdir();
				File[] f = delFile.listFiles();
				
				for(int i=0; i<f.length; i++) {
					if(f[i].getName().equals(fname)) {
						f[i].delete();
					}
				}
				delFile.delete();// 기존 이진파일을 삭제		불완전한 코드
			}
//			MemberBean delm = new MemberBean();
//			delm.setJoin_id(id);
//			delm.setJoin_delcont(del_cont);

//			memberService.deleteMember(delm);// 삭제 메서드 호출

//			session.invalidate();	// 세션만료
//			String lost_no1 = request.getParameter("lost_no").trim();
//			int lost_no = Integer.parseInt(lost_no1);
			
			int result = ls.delete(lost_no);
			model.addAttribute("result", result);
			
			
			return "lost/delete";
//		}
	}

	
	
	
	@RequestMapping("lostView")	// 상세 페이지
	public String view(int lost_no, String pageNum, Model model ,HttpSession session) {
		ls.selectUpdate(lost_no);	// 조회수 증가
		Lost lost = ls.select(lost_no);
		String mem_id = (String)session.getAttribute("mem_id");
//		String join_name = lost.getJoin_name();
		String lost_file = lost.getLost_file();

		model.addAttribute("lost_file", lost_file);
		
		model.addAttribute("lost", lost);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("mem_id", mem_id);
		
		return "lost/view";
		
	}
	//게시물 작성시 위도 경도를 받아주는 메서드
	@RequestMapping("lostlatlng")
	public String lostlatlng(String mem_id, double lat, double lng, Model model) {
//		public String popup2(MytestModel mytest, double lat, double lng, Model model) {
		
		System.out.println("lostlatlng");
		System.out.println(lat);
		System.out.println(lng);
		
//		mytest.setLat(lat);
//		mytest.setLng(lng);
//		return "redirect:popup3.do?lat="+lat+"&lng="+lng;
//		return "redirect:popup3.do?lat="+lat+'&lng='+lng";
//		ms.insert(mytest);
		model.addAttribute("mem_id", mem_id);
		model.addAttribute("lat", lat );
		model.addAttribute("lng", lng );
		
		
		return "lost/lostinsertform";
		
	}
	
	//수정시 위도 경도를 받아주는 메서드
		@RequestMapping("lostlatlng2")
		public String lostlatlng(String mem_id, double lat, double lng, Model model, String lost_title,
				String lost_content, Lost lost, String lost_pwd ,String lost_file) {
			
			System.out.println("lostlatlng2");
			System.out.println(lat);
			System.out.println(lng);
			System.out.println("lost:"+lost);
			model.addAttribute("mem_id", mem_id);
			model.addAttribute("lat", lat );
			model.addAttribute("lng", lng );
			model.addAttribute("lost_title", lost_title );
			model.addAttribute("lost_content", lost_content );
			model.addAttribute("lost", lost );
			model.addAttribute("lost_pwd", lost_pwd );
			model.addAttribute("lost_file", lost_file );
			
			return "lost/lostupdateform";
			
		}

	
		
}





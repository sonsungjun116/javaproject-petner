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

import petner.model.Admin;
import petner.model.Hospital;
import petner.model.Qna;
import petner.service.AdminService;
import petner.service.HospitalService;
import petner.service.PagingPgm;

@Controller
public class HospitalController {

	@Autowired
	private HospitalService hs;
	
	@Autowired
	private AdminService as;
	
	// 병원목록(전체, 검색)
	@RequestMapping("hospital_list")
	public String list(String page, Hospital hospital, Model model) throws Exception {

		final int rowPerPage = 10; // 화면에 출력할 데이터 개수
		if (page == null || page.equals("")) {
			page = "1";
		}
		int currentPage = Integer.parseInt(page); // 현재 페이지 번호
		int total = hs.getTotal(hospital); // 검색(데이터 개수)

		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		hospital.setStartRow(startRow);
		hospital.setEndRow(endRow);

		int no = total - startRow + 1; // 화면 출력 번호
		List<Hospital> list = hs.list(hospital);

		// 페이징 처리
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);

		// 검색
		model.addAttribute("search", hospital.getSearch());
		model.addAttribute("keyword", hospital.getKeyword());

		return "hospital/list";
	}

	// 병원등록 폼
	@RequestMapping("hospital_insertForm")
	public String insertForm() {
		return "hospital/insertForm";
	}

	// 병원등록
	@RequestMapping("hospital_insert")
	public String insert(@RequestParam("hospital_file1") MultipartFile mf,
						HttpServletRequest request, Hospital hospital,
						Model model) throws Exception {
	
		// 첨부파일
		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize();

		String path = request.getRealPath("upload/hospital");
		System.out.println("path:" + path);

		int uploadResult = 0;
		String file[] = new String[2];

		String newfilename = "";

		if (filename != "") { 	// 첨부파일이 전송된 경우

			// 파일 중복문제 해결
			String extension = filename.substring(filename.lastIndexOf("."), filename.length());
			System.out.println("extension:" + extension);

			UUID uuid = UUID.randomUUID();

			newfilename = uuid.toString() + extension;
			System.out.println("newfilename:" + newfilename);

			StringTokenizer st = new StringTokenizer(filename, ".");
			file[0] = st.nextToken(); 	// 파일명
			file[1] = st.nextToken(); 	// 확장자

			if (!file[1].equals("jpg") &&
				!file[1].equals("gif") &&
				!file[1].equals("png")) {
				
				uploadResult = 1;
				model.addAttribute("uploadResult", uploadResult);

				return "hospital/uploadResult";
			}
		}

		if (size > 0) { // 첨부파일이 전송된 경우
			mf.transferTo(new File(path + "/" + newfilename));
		}

		hospital.setHospital_file(newfilename);

		int insertResult = hs.insert(hospital);
		
		model.addAttribute("insertResult", insertResult);
	
		return "hospital/result";
	}

	// 상세페이지, 수정폼, 삭제폼
	@RequestMapping("hospital_view")
	public String view(int hospital_no, int page, String state,
						Admin admin, HttpSession session, Model model) throws Exception {
		
		String admin_id = (String) session.getAttribute("id");
		
		Hospital hospital = hs.getHospital(hospital_no);

//		세션 연결되면 주석 해제
//		admin = as.getAdmin(admin_id);
		
		model.addAttribute("admin", admin);
		model.addAttribute("hospital", hospital);
		model.addAttribute("hospital_no", hospital_no);
		model.addAttribute("page", page);

		if (state.equals("view")) {
			return "hospital/view";
		} else if(state.equals("update")) {
			return "hospital/updateForm";
		} else if(state.equals("delete")) {
			return "hospital/deleteForm";
		}

		return null;
	}

	// 병원수정
	@RequestMapping("hospital_update")
	public String update(@RequestParam("hospital_file1") MultipartFile mf, HttpServletRequest request,
						String admin_pwd, String hospital_pwd, Hospital hospital,
						Admin admin, int hospital_no, int page, Model model) throws Exception {

		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize();

		String path = request.getRealPath("upload/hospital");
		System.out.println("path:" + path);

		int uploadResult = 0;
		String file[] = new String[2];

		String newfilename = "";

		if (filename != "") { // 첨부파일이 전송된 경우

			// 파일 중복문제 해결
			String extension = filename.substring(filename.lastIndexOf("."), filename.length());
			System.out.println("extension:" + extension);

			UUID uuid = UUID.randomUUID();

			newfilename = uuid.toString() + extension;
			System.out.println("newfilename:" + newfilename);

			StringTokenizer st = new StringTokenizer(filename, ".");
			file[0] = st.nextToken(); // 파일명
			file[1] = st.nextToken(); // 확장자

			if (!file[1].equals("jpg") && !file[1].equals("gif") && !file[1].equals("png")) {
				uploadResult = 1;
				model.addAttribute("uploadResult", uploadResult);

				return "hospital/uploadResult";
			}
		}

		if (size > 0) { 	// 첨부파일이 전송된 경우
			mf.transferTo(new File(path + "/" + newfilename));
		}

		Hospital oldhospital = this.hs.getHospital(hospital_no);
		
		if (size > 0) {		 // 첨부파일이 수정되면
			hospital.setHospital_file(newfilename);
		} else { 			 // 첨부파일이 수정되지 않으면
			hospital.setHospital_file(oldhospital.getHospital_file());
		}
		
		int updateResult = 0;
		if(!admin.getAdmin_pwd().equals(hospital_pwd)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
		} else {
			updateResult = hs.update(hospital);
		}
		
		model.addAttribute("updateResult", updateResult);
		model.addAttribute("page", page);
		model.addAttribute("hospital", hospital);
		model.addAttribute("hospital_no", hospital_no);

		return "hospital/result";
	}

	// 병원삭제
	@RequestMapping("hospital_delete")
	public String delete(HttpServletRequest request, int hospital_no,
						int page, String hospital_pwd, Admin admin,
						Hospital hospital, Model model) throws Exception {

		String up = request.getRealPath("upload/hospital");
		String fname = hospital.getHospital_file();
		System.out.println("up:" + up);
		System.out.println("fname:" + fname);

		// DB에 저장된 기존 이진파일명을 가져옴
		if (fname != "") { // 기존 이진파일이 존재하면

			File delFile = new File(up);
			delFile.mkdir();
			File[] f = delFile.listFiles();

			for (int i = 0; i < f.length; i++) {
				if (f[i].getName().equals(fname)) {
					f[i].delete(); // 기존 이진파일을 삭제
				}
			}
		}

		int deleteResult = hs.delete(hospital_no);
		
		model.addAttribute("deleteResult", deleteResult);
		model.addAttribute("page", page);

		return "hospital/result";
	}
}

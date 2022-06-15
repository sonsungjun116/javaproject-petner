package petner.controller;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import petner.model.Member;
import petner.model.Tipboard;
import petner.service.MemberService;
import petner.service.PagingPgm;
import petner.service.TipboardService;
import scala.collection.immutable.IntMap.Tip;

@Controller
public class TipBoardController {
	@Autowired
	private TipboardService tbs;
	
	@Autowired
	private MemberService ms;

	// 팁게시판 입력 폼
	@RequestMapping("insertTipboard")
	public String insertTipboard() {
		return "tip_board/insertTipboard";
	}

	// 팁게시판 입력
	@RequestMapping("insertTip")
	public String insert(@RequestParam("tip_file1") MultipartFile mf, HttpServletRequest request, Tipboard tipboard,
			String PageNum, Model model) throws Exception {

		if (PageNum == null || PageNum.equals("")) {
			PageNum = "1";
		}
		
		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize(); // 첨부파일의 크기 (단위:Byte)

		String path = request.getRealPath("upload/tipboard");
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
		String tipcontent = tipboard.getTip_content().replace("\n", "<br>");
		tipboard.setTip_content(tipcontent);
		tipboard.setTip_file(newfilename);

		tbs.insertTip(tipboard);

		return "redirect:tipboardList?pageNum="+PageNum;
	}

	// 팁게시판 리스트
	@RequestMapping("tipboardList")
	public String tipboardlist(String pageNum, Tipboard tipboard, Model model) {

		final int rowPerPage = 10; // 화면에 출력할 데이터 갯수
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum); // 현재 페이지 번호

		int total = tbs.getTotal(tipboard); // 검색 (데이터 갯수)

		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		tipboard.setStartRow(startRow);
		tipboard.setEndRow(endRow);
		// List<Board> list = bs.list(startRow, endRow);
		int no = total - startRow + 1; // 화면 출력 번호
		List<Tipboard> list = tbs.list(tipboard);

		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);

		// 검색
		model.addAttribute("search", tipboard.getSearch());
		model.addAttribute("keyword", tipboard.getKeyword());
		return "tip_board/tipboardList";
	}

	// 팁게시판 상세/ 조회수 증가
	@RequestMapping("tipboard_detail")
	public String tipboardDetail(int tip_no, String pageNum, Model model) {

		Tipboard tipboard = tbs.getTip(tip_no);
		tbs.updatetip_readcount(tip_no);

		model.addAttribute("tipboard", tipboard);
		model.addAttribute("pageNum", pageNum);

		return "tip_board/tipboardDetail";
	}

	@RequestMapping("statusTipboard")
	public String statusTipboard(int tip_no, String pageNum, String state, Model model) {

		// 상세정보 구하기
		Tipboard tipboard = tbs.getTip(tip_no);
		Member member = ms.getuser(tipboard.getMem_id());

		model.addAttribute("member", member);
		model.addAttribute("tipboard", tipboard);
		model.addAttribute("pageNum", pageNum);

		if (state.equals("edit")) {
			return "tip_board/tipboardEdit";
		} else if (state.equals("del")) {
			return "tip_board/tipboardDel";
		}

		return null;
	}

	@RequestMapping("updateTip")
	public String updateTip(Tipboard tipboard, @RequestParam("tip_file1") MultipartFile mf, HttpServletRequest request,
			String pageNum, Model model) throws Exception {
		
		String filename = mf.getOriginalFilename();
		int size = (int) mf.getSize(); // 첨부파일의 크기 (단위:Byte)

		String path = request.getRealPath("upload/tipboard");
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
		String tipcontent = tipboard.getTip_content().replace("\n", "<br>");
		tipboard.setTip_content(tipcontent);
		tipboard.setTip_file(newfilename);

		tbs.updateTip(tipboard);
		
		System.out.println("tipboard:"+tipboard);
		System.out.println("pageNum:"+pageNum);

		return "redirect:tipboardList?tipboard="+tipboard+"&pageNum="+pageNum;
	}
	
	@RequestMapping("deleteTip")
	public String deleteTip(String pageNum, Tipboard tipboard, HttpServletRequest request, Model model) {
		
		String up = request.getRealPath("upload/tipboard");
		String fname = tipboard.getTip_file();
		System.out.println("fname:"+fname);
		System.out.println("up:"+up);
		
		// 디비에 저장된 기존 이진파일명을 가져옴
		if (fname != null) {// 기존 이진파일이 존재하면
			File delFile = new File(up);
			delFile.mkdir();
			System.out.println("delFile:"+delFile);
			File[] f = delFile.listFiles();
			for(int i = 0; i < f.length; i++) {
				if(f[i].getName().equals(fname)) {
					System.out.println("f[i]:"+f[i].getName());
					f[i].delete();// 기존 이진파일을 삭제						
				}
			}
		}

		
		tbs.deleteTip(tipboard.getTip_no());
		
		return "redirect:tipboardList?pageNum="+pageNum;
	}
}

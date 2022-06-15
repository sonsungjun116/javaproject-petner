package petner.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import petner.model.Member;
import petner.model.Product;
import petner.model.Review;
import petner.service.MemberService;
import petner.service.PagingPgm;
import petner.service.ProductService;
import petner.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService rs;
	
	@Autowired
	private MemberService ms;
	
	@Autowired
	private ProductService ps;
	
	// 리뷰전체
	@RequestMapping("reviewlist")
	public String reviewlist(Review review, Model model, String pageNum) {
		
		final int rowPerPage = 10;		// 출력 데이터 수
		
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		int total = rs.getTotal(review);	// 데이터 수
		System.out.println("total:"+total);
		
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		review.setStartRow(startRow);		// 초기화
		review.setEndRow(endRow);
		
		int no = total - startRow + 1;		// 화면 출력 번호
		
		List<Review> list = rs.list(review);
		
		model.addAttribute("pp", pp);
		model.addAttribute("no", no);
		model.addAttribute("list", list);
		
		return "review/reviewlist";
	}
	
	// 상품리뷰
	@RequestMapping("reviewnolist")
	public String reviewnolist(Review review, Model model, String pageNum) {
		
		final int rowPerPage = 10;		// 출력 데이터 수
		
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		int total = rs.getNototal(review);	// 데이터 수
		System.out.println("total:"+total);
		
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		review.setStartRow(startRow);		// 초기화
		review.setEndRow(endRow);
		
		int no = total - startRow + 1;		// 화면 출력 번호
		
		List<Review> nolist = rs.nolist(review);
		
		model.addAttribute("pp", pp);
		model.addAttribute("no", no);
		model.addAttribute("nolist", nolist);
		model.addAttribute("product_no", review.getProduct_no());
		
		return "review/reviewnolist";
	}
	
	// 리뷰등록폼
	@RequestMapping("reviewinsertform")
	public String reviewinsertform(Member member, Product product, Model model) throws Exception {
		// 멤버상세
		Member getmember = ms.getuser(member.getMem_id());
		// 상품상세
		Product getproduct = ps.getProduct(product.getProduct_no());
		
		model.addAttribute("member", getmember);
		model.addAttribute("product", getproduct);
		
		return "review/reviewinsertform";
	}
	
	// 리뷰등록
	@RequestMapping("reviewinsertresult")
	public String reviewinsert(MultipartHttpServletRequest mtf, HttpServletRequest request, Review review, Model model) throws Exception {
		String path = request.getRealPath("upload/review");
		System.out.println(path);
		
		List<MultipartFile> filelist = mtf.getFiles("review_file_");
		
		String file = "";
		String files = "";
		
		for(MultipartFile mf : filelist) {
			String filename = mf.getOriginalFilename();
			int size = (int) mf.getSize();
			
			if(size > 0) {
				UUID uuid = UUID.randomUUID();
				file = uuid + filename;
				
				mf.transferTo(new File(path + "/" + file));
			}
			files += file + "/";
		}
		review.setReview_file(files);
		
		int result = rs.insert(review);		// 리뷰등록
		model.addAttribute("result", result);
		
		return "review/reviewinsertresult";
	}
	
	// 리뷰상세
	@RequestMapping("reviewdetail")
	public String reviewdetail(HttpSession session, Review review, Model model, String pageNum) {
		
		Review reviewdetail = rs.getReview(review.getReview_no());
		
		// 다중 이미지 출력
		String reviewfile = reviewdetail.getReview_file();
		String file[] = reviewfile.split("/");
		
		List flist = new ArrayList();
		for(String img : file) {
			flist.add(img);
		}
		
		session.setAttribute("review_no", review.getReview_no());
		
		model.addAttribute("reviewdetail", reviewdetail);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("flist", flist);
		
		return "review/reviewdetail";
	}
	
	// 리뷰수정폼
	@RequestMapping("reviewupdateform")
	public String reviewupdateform(Review review, Model model, String pageNum) {
		
		Review old = rs.getReview(review.getReview_no());
		
		// 다중 이미지 출력
		String reviewfile = old.getReview_file();
		String file[] = reviewfile.split("/");
		
		List flist = new ArrayList();
		for(String img : file) {
			flist.add(img);
		}
		
		model.addAttribute("old", old);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("flist", flist);
		
		return "review/reviewupdateform";
	}
	
	// 리뷰수정
	@RequestMapping("reviewupdateresult")
	public String reviewupdateresult(MultipartHttpServletRequest mtf, HttpServletRequest request, Review review, Model model, String pageNum) throws Exception {
		String path = request.getRealPath("upload/review");
		System.out.println(path);
		
		Review old = rs.getReview(review.getReview_no());	// 리뷰 상세페이지
		
		List<MultipartFile> filelist = mtf.getFiles("review_file_");
		
		String file = "";
		String files = "";
		int size = 0;
		
		for(MultipartFile mf : filelist) {
			String filename = mf.getOriginalFilename();
			size = (int) mf.getSize();
			
			if(size > 0) {
				UUID uuid = UUID.randomUUID();
				file = uuid + filename;
				
				mf.transferTo(new File(path + "/" + file));
			}
			files += file + "/";
		}
		
		if(size > 0) {
			// 새로운 파일 등록
			review.setReview_file(files);
		} else {
			// 기존 파일 유지
			review.setReview_file(old.getReview_file());
		}
		
		int result = rs.update(review);		// 리뷰수정
		
		model.addAttribute("review", review);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("result", result);
		
		return "review/reviewupdateresult";
	}
		
	// 리뷰삭제
	@RequestMapping("reviewdeleteresult")
	public String reviewdeleteresult(Review review, Model model, String pageNum) throws Exception {
		
		int result = rs.delete(review.getReview_no());
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("result", result);
		
		return "review/reviewdeleteresult";
	}
}

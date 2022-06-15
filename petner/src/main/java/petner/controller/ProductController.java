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

import petner.model.Lost;
import petner.model.Payment;
import petner.model.Product;
import petner.model.Seller;
import petner.model.Tipboard;
import petner.service.HospitalService;
import petner.service.LostService;
import petner.service.PagingPgm;
import petner.service.ProductService;
import petner.service.SellerService;
import petner.service.TipboardService;

@Controller
public class ProductController {

	@Autowired
	private ProductService ps;

	@Autowired
	private SellerService sd;

	@Autowired
	private TipboardService tbs;

	@Autowired
	private LostService ls;

	// 메인화면 상품 출력
	// 상품목록(전체, 검색)
	@RequestMapping("productlist1")
	public String productlist1(Product product, Model model, String pageNum) {

		final int rowPerPage = 10; // 출력 데이터 수

		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);

		int total = ps.getTotal(product); // 데이터 수
		System.out.println("total:" + total);

		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		product.setStartRow(startRow); // 초기화
		product.setEndRow(endRow);

		int no = total - startRow + 1; // 화면 출력 번호

		Lost lost = new Lost();
		int total2 = ls.getTotal(lost);
		PagingPgm pp2 = new PagingPgm(total2, rowPerPage, currentPage);
		lost.setStartRow(startRow);
		lost.setEndRow(endRow);

		Tipboard tipboard = new Tipboard();
		int total1 = tbs.getTotal(tipboard);
		PagingPgm pp1 = new PagingPgm(total1, rowPerPage, currentPage);
		tipboard.setStartRow(startRow);
		tipboard.setEndRow(endRow);
		
		List<Product> list = ps.list(product);
		List<Tipboard> tlist = tbs.list(tipboard);
		List<Lost> llist = ls.list(lost);
//			String img = "";
//			for(Product p : list) {
//				img = p.getProduct_img();
//			}
//			String file[] = img.split("/");

		model.addAttribute("pp", pp);
		model.addAttribute("no", no);
		model.addAttribute("list", list);
		model.addAttribute("tlist", tlist);
		model.addAttribute("llist", llist);
//			model.addAttribute("file", file);

		// 검색
		model.addAttribute("search", product.getSearch());
		model.addAttribute("keyword", product.getKeyword());

		return "Main";
	}
	@RequestMapping("productlist2")
	public String productlist2(Product product, Model model, String pageNum) {
		
		final int rowPerPage = 10; // 출력 데이터 수
		
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		int total = ps.getTotal(product); // 데이터 수
		System.out.println("total:" + total);
		
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		product.setStartRow(startRow); // 초기화
		product.setEndRow(endRow);
		
		int no = total - startRow + 1; // 화면 출력 번호
		
		Lost lost = new Lost();
		int total2 = ls.getTotal(lost);
		PagingPgm pp2 = new PagingPgm(total2, rowPerPage, currentPage);
		lost.setStartRow(startRow);
		lost.setEndRow(endRow);
		
		Tipboard tipboard = new Tipboard();
		int total1 = tbs.getTotal(tipboard);
		PagingPgm pp1 = new PagingPgm(total1, rowPerPage, currentPage);
		tipboard.setStartRow(startRow);
		tipboard.setEndRow(endRow);
		
		List<Product> list = ps.list(product);
		List<Tipboard> tlist = tbs.list(tipboard);
		List<Lost> llist = ls.list(lost);
//			String img = "";
//			for(Product p : list) {
//				img = p.getProduct_img();
//			}
//			String file[] = img.split("/");
		
		model.addAttribute("pp", pp);
		model.addAttribute("no", no);
		model.addAttribute("list", list);
		model.addAttribute("tlist", tlist);
		model.addAttribute("llist", llist);
//			model.addAttribute("file", file);
		
		// 검색
		model.addAttribute("search", product.getSearch());
		model.addAttribute("keyword", product.getKeyword());
		
		return "admin/admin_view";
	}

	// 상품목록(전체, 검색)
	@RequestMapping("productlist")
	public String productlist(Product product, Model model, String pageNum) {

		final int rowPerPage = 10; // 출력 데이터 수

		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);

		int total = ps.getTotal(product); // 데이터 수
		System.out.println("total:" + total);

		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		product.setStartRow(startRow); // 초기화
		product.setEndRow(endRow);

		int no = total - startRow + 1; // 화면 출력 번호

		List<Product> list = ps.list(product);

//			String img = "";
//			for(Product p : list) {
//				img = p.getProduct_img();
//			}
//			String file[] = img.split("/");

		model.addAttribute("pp", pp);
		model.addAttribute("no", no);
		model.addAttribute("list", list);
//			model.addAttribute("file", file);

		// 검색
		model.addAttribute("search", product.getSearch());
		model.addAttribute("keyword", product.getKeyword());

		return "product/productlist";
	}

	// 내 상품목록(전체, 검색)
	@RequestMapping("productmylist")
	public String productmylist(Product product, Model model, String pageNum) {

		final int rowPerPage = 10; // 출력 데이터 수

		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);

		int total = ps.getMytotal(product); // 데이터 수
		System.out.println("total:" + total);

		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;

		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		product.setStartRow(startRow); // 초기화
		product.setEndRow(endRow);

		int no = total - startRow + 1; // 화면 출력 번호
		List<Product> mylist = ps.mylist(product);

		model.addAttribute("pp", pp);
		model.addAttribute("no", no);
		model.addAttribute("mylist", mylist);
		model.addAttribute("seller_no", product.getSeller_no());

		// 검색
		model.addAttribute("search", product.getSearch());
		model.addAttribute("keyword", product.getKeyword());

		return "product/productmylist";
	}

	// 상품등록폼
	@RequestMapping("productinsertform")
	public String productinsertform(Seller seller, Model model) throws Exception {
		// 사업자상세
		Seller getseller = sd.getSeller(seller.getSeller_no());
		model.addAttribute("seller", getseller);

		return "product/productinsertform";
	}

	// 상품등록
	@RequestMapping("productinsertresult")
	public String productinsert(MultipartHttpServletRequest mtf, HttpServletRequest request, Product product,
			Model model) throws Exception {
		String path = request.getRealPath("upload/product");
		System.out.println(path);

		List<MultipartFile> fileList1 = mtf.getFiles("product_img_");

		String file1 = "";

		for (MultipartFile mf : fileList1) {
			String filename = mf.getOriginalFilename();
			int size = (int) mf.getSize();

			if (size > 0) {
				UUID uuid = UUID.randomUUID();
				file1 = uuid + filename;

				mf.transferTo(new File(path + "/" + file1));
			}
		}
		product.setProduct_img(file1);

		List<MultipartFile> fileList2 = mtf.getFiles("product_contentimg_");

		String file2 = "";
		String files2 = "";

		for (MultipartFile mf : fileList2) {
			String filename = mf.getOriginalFilename();
			int size = (int) mf.getSize();

			if (size > 0) {
				UUID uuid = UUID.randomUUID();
				file2 = uuid + filename;

				mf.transferTo(new File(path + "/" + file2));
			}
			files2 += file2 + "/";
		}
		product.setProduct_contentimg(files2);

		int result = ps.insert(product); // 상품등록
		model.addAttribute("result", result);

		return "product/productinsertresult";
	}

	// 상품상세
	@RequestMapping("productdetail")
	public String productdetail(HttpSession session, Product product, Model model, String pageNum) {

		Product productdetail = ps.getProduct(product.getProduct_no());
//		int total = productdetail.getProduct_price() * productdetail.getProduct_ea();

		// 다중 이미지 출력
		String contentimg = productdetail.getProduct_contentimg();
		String file[] = contentimg.split("/");

		List list = new ArrayList();
		for (String img : file) {
			list.add(img);
		}

		session.setAttribute("product_no", product.getProduct_no());

		model.addAttribute("productdetail", productdetail);
		model.addAttribute("pageNum", pageNum);
//		model.addAttribute("total", total);
		model.addAttribute("list", list);

		return "product/productdetail";
	}

	// 수량변경
	@RequestMapping("productea")
	public String getEa(Product product) {
		System.out.println("product_no:" + product.getProduct_no());
		System.out.println("product_ea:" + product.getProduct_ea());

		int result = ps.getEa(product);

		return "redirect:productdetail?product_no=" + product.getProduct_no();
	}

	// 상품수정폼
	@RequestMapping("productupdateform")
	public String productupdateform(Product product, Model model, String pageNum) {

		Product old = ps.getProduct(product.getProduct_no());

		// 다중 이미지 출력
		String contentimg = old.getProduct_contentimg();
		String file[] = contentimg.split("/");

		List list = new ArrayList();
		for (String img : file) {
			list.add(img);
		}

		model.addAttribute("old", old);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("list", list);

		return "product/productupdateform";
	}

	// 상품수정
	@RequestMapping("productupdateresult")
	public String productupdate(MultipartHttpServletRequest mtf, HttpServletRequest request, Product product,
			String stockadd, Model model, String pageNum) throws Exception {
		String path = request.getRealPath("upload/product");
		System.out.println(path);

		Product old = ps.getProduct(product.getProduct_no()); // 상품 상세페이지

		List<MultipartFile> fileList1 = mtf.getFiles("product_img_");

		String file1 = "";

		for (MultipartFile mf : fileList1) {
			String filename = mf.getOriginalFilename();
			int size = (int) mf.getSize();

			if (size > 0) {
				UUID uuid = UUID.randomUUID();
				file1 = uuid + filename;

				// 새로운 파일 등록
				product.setProduct_img(file1);

				mf.transferTo(new File(path + "/" + file1));
			} else {
				// 기존 파일 유지
				product.setProduct_img(old.getProduct_img());
			}
		}

		List<MultipartFile> fileList2 = mtf.getFiles("product_contentimg_");

		String file2 = "";
		String files2 = "";
		int size = 0;

		for (MultipartFile mf : fileList2) {
			String filename = mf.getOriginalFilename();
			size = (int) mf.getSize();

			if (size > 0) {
				UUID uuid = UUID.randomUUID();
				file2 = uuid + filename;

				mf.transferTo(new File(path + "/" + file2));
			}
			files2 += file2 + "/";
		}

		if (size > 0) {
			// 새로운 파일 등록
			product.setProduct_contentimg(files2);
		} else {
			// 기존 파일 유지
			product.setProduct_contentimg(old.getProduct_contentimg());
		}

		// 기존 재고 유지
		if (product.getProduct_stock() == 0) {
			product.setProduct_stock(old.getProduct_stock());
		} else {
			// 재고 더하기
			if (stockadd.equals("+")) {
				product.setProduct_stock(old.getProduct_stock() + product.getProduct_stock());
			}
			// 재고 빼기
			if (stockadd.equals("-")) {
				product.setProduct_stock(old.getProduct_stock() - product.getProduct_stock());
			}
		}

		int result = ps.update(product); // 상품수정

		model.addAttribute("product", product);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("result", result);

		return "product/productupdateresult";
	}

	// 상품삭제
	@RequestMapping("productdeleteresult")
	public String productdeleteresult(Product product, Model model) throws Exception {

		int result = ps.delete(product.getProduct_no());

		model.addAttribute("result", result);

		return "product/productdeleteresult";
	}

	// 결제연결
	@RequestMapping("insertpayment")
	   public String insertpayment(Product product, String mem_id, Model model) {
	      
	      System.out.println("insertpayment :"+ mem_id);
	      System.out.println("insertpayment :"+ product.getProduct_ea());
	      System.out.println("insertpayment :"+ product.getProduct_no());

	      int product_ea = product.getProduct_ea();
	      int product_no = product.getProduct_no();
	      
	      return "redirect:paymentstart?mem_id="+mem_id+"&product_ea="+product_ea+"&product_no="+product_no;
	   }
	}

package petner.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import petner.model.Basket;
import petner.model.Member;
import petner.model.Payment;
import petner.model.Product;
import petner.service.BasketService;
import petner.service.MemberService;
import petner.service.PaymentService;
import petner.service.ProductService;

@Controller
public class PaymentController {
	
	@Autowired
	private PaymentService pays;
	
	@Autowired
	private MemberService ms;
	
	@Autowired
	private ProductService ps;
	
	@Autowired
	private BasketService bs;
	
	// 구매하기 버튼
	@RequestMapping("paymentstart")
//	public String paymentstart(@RequestParam("payment") Payment payment, Model model, HttpServletRequest request ) {		
		public String paymentstart(@RequestParam("mem_id") String mem_id, @RequestParam("product_no") int product_no, @RequestParam("product_ea") int product_ea, Model model,HttpSession session ) {
	
		Payment payment = new Payment();
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		String signdate = formatter.format(new java.util.Date());
		int nowdate = Integer.parseInt(signdate);
		 System.out.println(nowdate);
		 Random r = new Random();
		 int n4 = (int)(Math.random() * 100000000) + 100000;
			System.out.println("n4="+n4);
			String str = String.valueOf(n4);		
			String order_no =nowdate + str;
//			String mem_id = payment.getMem_id();
//			int product_no = payment.getProduct_no();
//			int product_ea = payment.getProduct_ea();
			System.out.println(product_no);
			System.out.println(mem_id);
			System.out.println(product_ea);
//		String mem_id = "test"; 
		Member member = ms.getuser(mem_id);
				
//		int product_no = 10061;
		
		Product product = ps.getProduct(product_no); // product_name, product_price 구함
		int seller_no = product.getSeller_no();
		
//		String product_name = "슬개골탈구 관절영양제 조인트4.0 70정";
//		int product_price = 300;
		String product_name = product.getProduct_name();
		int product_price = product.getProduct_price();
		
//		int product_ea = 2;
		
		payment.setPayment_price(product_price * product_ea);
		// vip가 아닐시 배송비 계산
		if(member.getMem_grade().equals("VIP")) {
			int payment_price = product_price * product_ea;
			payment.setPayment_price(payment_price);
			
		}else {
			int payment_price = (product_price * product_ea) + 2500;
			payment.setPayment_price(payment_price);
		}
				payment.setMem_id(mem_id);
				payment.setProduct_no(product_no);
				payment.setProduct_name(product_name);
				payment.setProduct_ea(product_ea);
				payment.setSeller_no(seller_no);
				
		model.addAttribute("member", member);
//		model.addAttribute("product_no", product_no);
//		model.addAttribute("product_name", product_name);
//		model.addAttribute("product_price", product_price);
//		model.addAttribute("product_ea", product_ea);
		model.addAttribute("payment", payment);
		model.addAttribute("product", product);
		session.setAttribute(mem_id, mem_id);
		
// 		폼으로 넘기기 전에 여기서 상품가격 x 수량을 변수 payment_price변수를 만들어서 저장한후 모델을 통해서 뿌려주어야 한다.		
		return "payment/paymentstartform";
				
	}
	
	// 체크박스가 눌리면서 insert -> javascript로 실행하는데 이상하게 payment dto로 못받아 오길래 일단 하나하나 값을넣어줌
	@RequestMapping( value = "paymentapi", method = RequestMethod.POST)
	public String paymentapi(Payment payment,
								String mem_id,
								String mem_post,
								String mem_addr1,
								String mem_addr2,
								int payment_price,
								int product_no,
								int product_ea,
								int product_price,
								String product_name,
								String payment_info,
								int seller_no,
								Model model) {	
		
		System.out.println("테스트 성공");
		System.out.println("mem_id:"+mem_id); // id값은 원래 받는게 맞지만 
		System.out.println("mem_post:"+mem_post);
		System.out.println("mem_addr1:"+mem_addr1);
		System.out.println("mem_addr2:"+mem_addr2);
		System.out.println("payment_price:"+payment_price);
		System.out.println("product_no:"+product_no);
		System.out.println("payment_info:"+payment_info);
		System.out.println("seller_no:"+seller_no);
		
		String payment_info1 = payment_info;
		// 만약 무통장입금이라면 
		//if(payment_info.equals("noaccount")) {
			
		//}		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		String signdate = formatter.format(new java.util.Date());
		int nowdate = Integer.parseInt(signdate);
		 System.out.println(nowdate);
		 Random r = new Random();
		 int n4 = (int)(Math.random() * 100000000) + 100000;
			String str = String.valueOf(n4);		
			String order_no =nowdate + str;
			String payment_step = "입금전";
			Member member = ms.getuser(payment.getMem_id());
			String tel1 = member.getMem_phone1();
			String tel2 = member.getMem_phone2();
			String tel3 = member.getMem_phone3();
			String telall = tel1 + tel2 + tel3;
			System.out.println("telall: "+telall);
			String em1 = member.getMem_email();
			String em2 = member.getMem_domain();
			String pullemail = em1+"@"+em2;
			System.out.println("pullemail: "+pullemail);
			
			System.out.println("order_no:"+order_no);
			payment.setMem_id(mem_id);
			payment.setMem_post(mem_post);
			payment.setMem_addr1(mem_addr1);
			payment.setMem_addr2(mem_addr2);
			payment.setPayment_price(payment_price);
			payment.setProduct_no(product_no);
			payment.setOrder_no(order_no);
			payment.setPayment_step(payment_step);
			payment.setProduct_ea(product_ea);
			payment.setProduct_name(product_name);
			payment.setSeller_no(seller_no);
			
		
		pays.insertPayment(payment);
		int payment_no1 = pays.selectPayment();
//		int payment_no1 = payment.getPayment_no();
		payment.setPayment_no(payment_no1);
		System.out.println("payment_no1 : "+payment_no1);
		
		
		model.addAttribute("payment_info1", payment_info1);
		model.addAttribute("member", member);
		model.addAttribute("product_name", product_name);
		model.addAttribute("product_price", product_price);
		model.addAttribute("product_ea", product_ea);
		model.addAttribute("payment", payment);
		model.addAttribute("telall", telall);
		model.addAttribute("pullemail", pullemail);
		model.addAttribute("payment_no1", payment_no1);
		model.addAttribute("seller_no", seller_no);
		
				
		return "payment/paymentstartform2";
	
	}
	
	//적립금 사용
	@RequestMapping("membermiletest")
	public String membermiletest(int mem_point,int usemem_point,int product_no,int product_ea, Model model,Payment payment, HttpSession session ) {
		System.out.println("mileteststart");
		String mem_id = (String)session.getAttribute("mem_id");
//		String mem_id = "test"; 
		Member member = ms.getuser(mem_id);
		Product product = ps.getProduct(product_no); //상품 번호를 통해서 상품 이름과 가격을 가져온다. 수량은 매개변수로 받는다.
		int seller_no = product.getSeller_no();
		System.out.println("seller_no"+seller_no);
		
// 상품 테이블에서 필요한 값인 상품 번호와 상품이름 상품 가격 수량을 임의로 뿌려준다.
//		int product_no = 10061;
//		String product_name = "슬개골탈구 관절영양제 조인트4.0 70정";
//		int product_price = 300;
//		int product_ea = 2;
		String product_name = product.getProduct_name();
		int product_price = product.getProduct_price();
		
		if(member.getMem_grade().equals("VIP")) {
			int payment_price = product_price * product_ea;
			payment.setPayment_price(payment_price);
			
		}else {
			int payment_price = (product_price * product_ea) + 2500;
			payment.setPayment_price(payment_price);
		}
		String tel1 = member.getMem_phone1();
		String tel2 = member.getMem_phone2();
		String tel3 = member.getMem_phone3();
		String telall = tel1 + tel2 + tel3;
		System.out.println("telall: "+telall);
		String em1 = member.getMem_email();
		String em2 = member.getMem_domain();
		String pullemail = em1+"@"+em2;
		System.out.println("pullemail: "+pullemail);		
		
				
		System.out.println("mem_point transfer? : "+ mem_point);
		System.out.println("usemem_point transfer? : "+ usemem_point);
		// mem_point는 구매자가 보유하고 있는 현재 총 마일리지, usemem_point는 구매자가 사용하려고 하는 마일리지
		int transferpoint = mem_point - usemem_point; //transferpoint는 적용후 남아있는 내 마일리지
		member.setMem_point(transferpoint);
		int newprice = payment.getPayment_price() - usemem_point;
		payment.setPayment_price(newprice);
		model.addAttribute("member", member);
		model.addAttribute("product_no", product_no);
		model.addAttribute("product_name", product_name);
		model.addAttribute("product_price", product_price);
		model.addAttribute("product_ea", product_ea);
		model.addAttribute("payment", payment);
		model.addAttribute("transferpoint", transferpoint);
		model.addAttribute("telall", telall);
		model.addAttribute("pullemail", pullemail);
		model.addAttribute("seller_no", seller_no);
		model.addAttribute("mem_id", mem_id);
		
		
		return "payment/paymentstartform3";
		
	}
	
	@RequestMapping("payment")
	public String payment(HttpServletRequest request, Model model, HttpSession session ) {
		
		System.out.println("mypayment good");
		String mem_id = (String) session.getAttribute("mem_id");
		Member member = ms.getuser(mem_id);
		System.out.println("mem_id? :"+ mem_id);
		String[] basket_payment = request.getParameterValues("basket_payment");
		
		System.out.println("basket_no:"+basket_payment);
		
		List<Basket> list = new ArrayList<Basket>();
		
		for(String basket_no : basket_payment) {
			
			Basket basket = bs.getBasket(Integer.parseInt(basket_no));	// 장바구니 상세 정보 구하기			
			Product product = ps.getProduct(basket.getProduct_no());
			basket.setBasket_product_sum(product.getProduct_price() * basket.getBasket_ea());
			basket.setProduct_name(product.getProduct_name());
			basket.setProduct_price(product.getProduct_price());
			
			list.add(basket);
			
		}
		
		String pro_name = "";
		int totalprice = 0;
		String totalea ="";
		
		for (Basket basket : list) {		
		
		pro_name += "/" + basket.getProduct_name(); //선택한 품목을 누적
		totalprice += basket.getBasket_product_sum(); //각 항목의 총 금액을 누적
		totalea += "/" + basket.getBasket_ea();
			}
		
		model.addAttribute("list", list);
		model.addAttribute("pro_name", pro_name);
		model.addAttribute("totalprice", totalprice);
		model.addAttribute("member", member);
		model.addAttribute("totalea", totalea);
		
		return "payment/basketinsertform";
	}
	
			
	@RequestMapping( value = "basketinsertstart1", method = RequestMethod.POST)
	public String basketinsertstart1(Payment payment,
								String mem_id,
								String mem_post,
								String mem_addr1,
								String mem_addr2,
								int payment_price,
								int product_no,																							
								String payment_info,
								int totalprice,
								String pro_name,
								int seller_no,
								Model model) {	
		
		System.out.println("테스트 성공");
		System.out.println("mem_id:"+mem_id); // id값은 원래 받는게 맞지만 
		System.out.println("mem_post:"+mem_post);
		System.out.println("mem_addr1:"+mem_addr1);
		System.out.println("mem_addr2:"+mem_addr2);
		System.out.println("payment_price:"+payment_price);
		System.out.println("product_no:"+product_no);
		System.out.println("payment_info:"+payment_info);
		System.out.println("totalprice:"+totalprice);
		String payment_info1 = payment_info;
		
		
		// 만약 무통장입금이라면 
		//if(payment_info.equals("noaccount")) {
			
		//}		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		String signdate = formatter.format(new java.util.Date());
		int nowdate = Integer.parseInt(signdate);
		 System.out.println(nowdate);
		 Random r = new Random();
		 int n4 = (int)(Math.random() * 100000000) + 100000;
			String str = String.valueOf(n4);		
			String order_no =nowdate + str;
			String payment_step = "입금전";
			Member member = ms.getuser(payment.getMem_id());
			String tel1 = member.getMem_phone1();
			String tel2 = member.getMem_phone2();
			String tel3 = member.getMem_phone3();
			String telall = tel1 + tel2 + tel3;
			System.out.println("telall: "+telall);
			String em1 = member.getMem_email();
			String em2 = member.getMem_domain();
			String pullemail = em1+"@"+em2;
			System.out.println("pullemail: "+pullemail);
			
			System.out.println("order_no:"+order_no);
			payment.setMem_id(mem_id);
			payment.setMem_post(mem_post);
			payment.setMem_addr1(mem_addr1);
			payment.setMem_addr2(mem_addr2);
			payment.setPayment_price(totalprice);
//			payment.setProduct_no(product_no);
			payment.setProduct_no(0);
			payment.setOrder_no(order_no);
			payment.setPayment_step(payment_step);
//			payment.setProduct_ea(product_ea);
			payment.setProduct_ea(0);
			payment.setProduct_name(pro_name);
			// product ea,no 문제 해결 -> 다수의 숫자가 등록됨 name은 일단 pro_name이라는 문자열로 저장함
		
		pays.insertPayment(payment);
		int payment_no1 = pays.selectPayment();
//		int payment_no1 = payment.getPayment_no();
		payment.setPayment_no(payment_no1);
		System.out.println("payment_no1 : "+payment_no1);
		
		
		model.addAttribute("payment_info1", payment_info1);
		model.addAttribute("member", member);
		model.addAttribute("product_name", pro_name);
		model.addAttribute("product_price", totalprice);
//		model.addAttribute("product_ea", product_ea);
		model.addAttribute("payment", payment);
		model.addAttribute("telall", telall);
		model.addAttribute("pullemail", pullemail);
		model.addAttribute("payment_no1", payment_no1);
		
				
		return "payment/basketinsertform4";
	
	}
	@RequestMapping("basketinsertstart")
	public String basketinsertstart(List<Basket> list, Model model, HttpSession session) {
	
		System.out.println("list확인 : "+ list);
//		String mem_id = session.getAttribute("id");
//		Member member = ms.getuser(mem_id);
//		List<Basket> list = session.getAttribute(name);
//		List<Basket> list = bs.getbsList(basket); // basket에서 id에 해당되는 놈들만 리스트로 생성? 혹은 인자를 basket_no로 해서 이 번호들에 대한 정보를 구해옴		
		String pro_name = "";
		int totalprice;		
//		for (Basket basket : list) {		
				
//		pro_name += "/" + basket.getProduct_name(); 선택한 품목을 누적
//		totalprice += basket.getTotalPrice(); 각 항목의 총 금액을 누적
		
//			}

//		if(!member.getMem_grade().equals("VIP")) {
//			int totalprice += 2500;
//			payment.setPayment_price(payment_price);			
//		}		
//		model.addAttribute("member", member);
//		model.addAttribute("pro_name", pro_name);
//		model.addAttribute("totalprice", totalprice);
		
//							
		return "basketinsertform";
	}
	
	// 장바구니 삽입  선택된 리스트의 id값만 가지고 그 id에 해당하는 리스트를 만들고 
	@RequestMapping("basketinsert")
	public String basketinsert( Model model, HttpSession session) {
//		public String basketinsert(List<Basket> list, Model model, HttpSession session) {
		String mem_id = "test";
		Payment payment = new Payment();
		payment.setMem_id(mem_id);
//		List<Basket> list = session.getAttribute(name);
//		List<Basket> list = bs.getbsList(basket); // basket에서 id에 해당되는 놈들만 리스트로 생성? 혹은 인자를 basket_no로 해서 이 번호들에 대한 정보를 구해옴
		
		String pro_name = "";
		int totalprice;
//        if(list != null && !list.isEmpty()){
//           pro_name = list.get(0).getPro_name();
//            pro_name += list.size() > 1 ? pro_name + "외 " + (list.size() - 1) + "건" : "";   // 결제 상품명 + ~외 건수 처리
//        }		       
//		for (Basket basket : list) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		String signdate = formatter.format(new java.util.Date());
		int nowdate = Integer.parseInt(signdate);
		 System.out.println(nowdate);
		 Random r = new Random();
		 int n4 = (int)(Math.random() * 100000000) + 100000;
			String str = String.valueOf(n4);		
			String order_no =nowdate + str;
			String payment_step = "입금전";
//          payment.setMem_id(mem_id);
//			payment.setMem_post(mem_post);
//			payment.setMem_addr1(mem_addr1);
//			payment.setMem_addr2(mem_addr2);
//			payment.setPayment_price(payment_price);
//			payment.setProduct_no(product_no);
//			payment.setOrder_no(order_no);
//			payment.setPayment_step(payment_step);
				
//				pays.insertPayment(payment);
//				pro_name += product_name;
//				totalprice += payment.getPrice;				
//        }
//		
				
		return"";
	}
	
	@RequestMapping("paymenttest")
	public String paymenttest(int payment_no ,Model model, HttpSession session) {
	
		System.out.println("payment_no123: "+payment_no);
		
		
		return "redirect:order_complete?payment_no="+payment_no;
	}
	
}

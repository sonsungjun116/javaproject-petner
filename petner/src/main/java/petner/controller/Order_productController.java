package petner.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import petner.model.Member;
import petner.model.Order_product;
import petner.model.Payment;
import petner.model.Product;
import petner.model.Seller;
import petner.service.MemberService;
import petner.service.Order_productService;
import petner.service.PaymentService;
import petner.service.ProductService;
import petner.service.SellerService;

@Controller
public class Order_productController {

	@Autowired
	private Order_productService os;
	
	@Autowired
	private ProductService ps;
	
	@Autowired
	private PaymentService pays;
	
	@Autowired
	private MemberService ms;
	
	@Autowired
	private SellerService ss;
	
	// 주문완료 페이지_구매회원
	@RequestMapping("order_complete")
	public String complete(int payment_no, Payment payment, HttpSession session,
							Model model) throws Exception {

		String mem_id = (String) session.getAttribute("mem_id");

		os.step_update(payment_no);							// 결제단계 입금전 -> 입금완료

		Member member = ms.getuser(mem_id);					// 회원 상세정보
		payment = pays.getPayment(payment_no);				// 결제 상세정보
		payment.setMem_id(mem_id);
		
		os.insert(payment);
		
		Order_product order_product = os.getOrder_product(payment_no);

		Product product = ps.getProduct(payment.getProduct_no());	// 상품 상세정보
		order_product.setProduct_img(product.getProduct_img());		// product_img set
		order_product.setProduct_price(product.getProduct_price());	// product_price set

		String payment_info = "card";
		
		if(payment_info.equals("card")) {							// order_state set
			order_product.setOrder_state("입금완료");
		}else if(payment_info.equals("noaccount")) {
			order_product.setOrder_state("입금대기");
		}
		
		os.update(order_product);	// product_price, order_state, product_img insert
		
		List<Order_product> list = os.product_info(payment);		// 주문 상품 정보 출력
		
        model.addAttribute("list", list);
        model.addAttribute("member", member);
        model.addAttribute("product", product);
        model.addAttribute("payment", payment);
		
		return "order_product/complete";
	}
	
	// 주문상품 리스트_구매회원
	@RequestMapping("order_list")
	public String order_list(Payment payment, Order_product order_product,
							HttpSession session, Model model) throws Exception {

		String mem_id = (String) session.getAttribute("mem_id");
	    
		payment.setMem_id(mem_id);
		List<Payment> list = pays.getOrderList(payment);
		
		session.setAttribute("order_state", order_product.getOrder_state());
	    
		model.addAttribute("list", list);
	    
	    return "order_product/order_list";
	}
	
	// 주문상품 상세페이지
	@RequestMapping("order_view")
	public String view(int payment_no, HttpSession session, Model model) throws Exception {

//		String mem_id = (String) session.getAttribute("mem_id");
		
        Payment payment = pays.getPayment(payment_no);		// 결제 상세정보
        Order_product order_product = os.getOrder_product(payment_no);

        String mem_id = payment.getMem_id();
        Member member = ms.getuser(mem_id);
        
        String mem_grade = member.getMem_grade();			// 회원등급
        int shippingFee = 0;								// 배송비
        if(mem_grade.equals("VIP")) {
        	shippingFee = 0;
        }else if(mem_grade.equals("일반")) {
        	shippingFee = 2500;
        }
        
        int product_no = payment.getProduct_no();
        Product product = ps.getProduct(product_no);
        int product_stock = product.getProduct_stock();
        
        List<Order_product> list = os.getOrder_productDetail(order_product);

        model.addAttribute("list", list);				
        model.addAttribute("member", member);
        model.addAttribute("payment", payment);
        model.addAttribute("product_stock", product_stock);
        model.addAttribute("order_product", order_product);
        model.addAttribute("shippingFee", shippingFee);

		return "order_product/view";
	}
	
	// 주문상태 변경
	@RequestMapping("change_state")
	public String change_state(int payment_no, String state, HttpSession session,
							Model model) throws Exception {
		
		String mem_id = (String) session.getAttribute("mem_id");
		Member member = ms.getuser(mem_id);
		String mem_type = member.getMem_type();
		
		Order_product order_product = os.getOrder_product(payment_no);
		
		String order_state = null;
		
		if (state.equals("delivery")) {
			order_state = "배송완료";
		} else if(state.equals("cancel")) {
			order_state = "주문취소";
		} else if(state.equals("fix")) {
			order_state = "구매확정";
			os.stocksell_update(order_product);
		}
		
		order_product.setOrder_state(order_state);
		int result = os.change_state(order_product);
		
		model.addAttribute("result", result);
		model.addAttribute("mem_type", mem_type);
		
		return "order_product/result";
	}
	
	// 판매상품 리스트_사업자
	@RequestMapping("sell_list")
	public String sell_list(Payment payment, HttpSession session, Model model) throws Exception {

		String mem_id = (String) session.getAttribute("mem_id");

		Seller seller = ss.getSeller_no(mem_id); 
		int seller_no = seller.getSeller_no();

	    List<Payment> list = pays.getPaymentList(seller_no);
	    
	    model.addAttribute("list", list);
	    
		return "order_product/sell_list";
	}
	
	// 판매관리 리스트_사업자
	@RequestMapping("sell_manage")
	public String sell_manage(Payment payment, HttpSession session, Model model) throws Exception {
		
		String mem_id = (String) session.getAttribute("mem_id");
		    
		Seller seller = ss.getSeller_no(mem_id); 
		int seller_no = seller.getSeller_no();

		List<Product> list = ps.getProductList(seller_no);
		    
		model.addAttribute("list", list);
		    
		return "order_product/sell_manage";
	}
}

package petner.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import petner.model.Basket;
import petner.model.Product;
import petner.service.BasketService;
import petner.service.ProductService;

@Controller
public class BasketController {

	@Autowired
	private BasketService bs;
	
	@Autowired
	private ProductService ps;
	
	// 장바구니 입력
	@RequestMapping("insertBasket")
	public String insertBasket(Basket basket ,Model model) {
		
		basket.setBasket_product_sum(basket.getProduct_price() * basket.getBasket_ea());
		bs.insertBasket(basket);
		return "redirect:basketList?mem_id="+basket.getMem_id();
	}
	
	// 장바구니 리스트 출력
	@RequestMapping("basketList")
	public String basketList(String mem_id, Model model) {
		
		System.out.println("mem_id:"+mem_id);
		
		List<Basket> list = bs.basketList(mem_id);
		model.addAttribute("list", list);
		
		return "basket/basketList";
	}
	
	// 장바구니 수량 변경
	@RequestMapping("update_ea")
	public String update_ea(Basket basket, Model model) {
		
		System.out.println("성공?");
		System.out.println("basket_no:"+basket.getBasket_no());
		System.out.println("basket_ea:"+basket.getBasket_ea());
		System.out.println("product_price:"+basket.getProduct_price());
		basket.setBasket_product_sum(basket.getProduct_price() * basket.getBasket_ea());
		
		bs.update_ea(basket);
		
		return "redirect:basketList?mem_id="+basket.getMem_id();
	}
	
	/*
	 * @RequestMapping("payment") public String payment(HttpServletRequest request,
	 * Model model) {
	 * 
	 * String[] basket_payment = request.getParameterValues("basket_payment");
	 * 
	 * System.out.println("basket_no:"+basket_payment); List<Basket> list = new
	 * ArrayList<Basket>();
	 * 
	 * for(String basket_no : basket_payment) {
	 * 
	 * Basket basket = bs.getBasket(Integer.parseInt(basket_no)); // 장바구니 상세 정보 구하기
	 * 
	 * Product product = ps.getProduct(basket.getProduct_no());
	 * basket.setBasket_product_sum(product.getProduct_price() *
	 * basket.getBasket_ea()); basket.setProduct_name(product.getProduct_name());
	 * basket.setProduct_price(product.getProduct_price());
	 * 
	 * list.add(basket); } model.addAttribute("list", list);
	 * 
	 * return "redirect:basketinsert?list="+list; }
	 */
}

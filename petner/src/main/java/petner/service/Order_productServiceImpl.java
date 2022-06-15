package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import petner.dao.Order_productDao;
import petner.model.Order_product;
import petner.model.Payment;

@Service
public class Order_productServiceImpl implements Order_productService {

	@Autowired
	private Order_productDao od;
	
	// 등록
	public void insert(Payment payment) {
		od.insert(payment);
	}
	
	// 수정
	public void update(Order_product order_product) {
		od.update(order_product);
	}
	
	// 목록(order_complete)
	public List<Order_product> product_info(Payment payment) {
		return od.product_info(payment);
	}
	
	// 목록2
	public List<Order_product> list2(String mem_id) {
		return od.list2(mem_id);
	}
//	public List<Order_product> list2(Order_product order_product) {
//		return od.list2(order_product);
//	}
	
	// 상세(DTO)
	public Order_product getOrder_product(int payment_no) {
		return od.getOrder_product(payment_no);
	}
	
	// 상세(List)
	public List<Order_product> getOrder_productDetail(Order_product order_product) {
		return od.getOrder_productDetail(order_product);
	}
	
	// 결제단계 변경
	public void step_update(int payment_no) {
		od.step_update(payment_no);
	}
	
	// 주문상태 변경
	public int change_state(Order_product order_product) {
		return od.change_state(order_product);
	}
	
	// 재고량 -1, 판매량 +1
	public void stocksell_update(Order_product order_product) {
		od.stocksell_update(order_product);
	}
	
	// 총 데이터 개수
	public int getTotal(Order_product order_product) {
		return od.getTotal(order_product);
	}
}

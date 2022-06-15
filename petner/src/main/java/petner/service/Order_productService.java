package petner.service;

import java.util.List;

import petner.model.Order_product;
import petner.model.Payment;

public interface Order_productService {

	// 등록
	public void insert(Payment payment);
	
	// 수정
	public void update(Order_product order_product);
	
	// 목록(order_complete)
	public List<Order_product> product_info(Payment payment);
	
	// 목록2
	public List<Order_product> list2(String mem_id);
	
	// 상세(DTO)
	public Order_product getOrder_product(int payment_no);
	
	// 상세(List)
	public List<Order_product> getOrder_productDetail(Order_product order_product);
	
	// 결제단계 변경
	public void step_update(int payment_no);
	
	// 주문상태 변경
	public int change_state(Order_product order_product);
	
	// 재고량 -1, 판매량 +1
	public void stocksell_update(Order_product order_product);
	
	// 총 데이터 개수
	public int getTotal(Order_product order_product);
}

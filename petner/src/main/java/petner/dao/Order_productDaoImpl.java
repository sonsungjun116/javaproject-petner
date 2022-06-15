package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Order_product;
import petner.model.Payment;

@Repository
public class Order_productDaoImpl implements Order_productDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	// 등록
	public void insert(Payment payment) {
		sst.insert("opns.insert", payment);
	}
	
	// 수정
	public void update(Order_product order_product) {
		sst.update("opns.update", order_product);
	}
	
	// 목록(order_complete)
	public List<Order_product> product_info(Payment payment){
		return sst.selectList("opns.product_info", payment);
	}
	
	// 목록2
	public List<Order_product> list2(String mem_id) {
		return sst.selectList("opns.list2", mem_id);
	}
//	public List<Order_product> list2(Order_product order_product) {
//		return sst.selectList("opns.list2", order_product);
//	}
	
	// 상세(DTO)
	public Order_product getOrder_product(int payment_no){
		return sst.selectOne("opns.getOrder_product", payment_no);
	}
	
	// 상세(List)
	public List<Order_product> getOrder_productDetail(Order_product order_product){
		return sst.selectList("opns.getOrder_productDetail", order_product);
	}
	
	// 결제단계 변경
	public void step_update(int payment_no) {
		sst.update("opns.step_update", payment_no);
	}
	
	// 주문상태변경
	public int change_state(Order_product order_product) {
		return sst.update("opns.change", order_product);
	}
	
	// 재고량 -1, 판매량 +1
	public void stocksell_update(Order_product order_product) {
		sst.update("opns.stocksell_update", order_product);
	}
	
	// 총 데이터 개수
	public int getTotal(Order_product order_product) {
		return sst.selectOne("opns.getTotal", order_product);
	}
}

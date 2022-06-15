package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Lost;
import petner.model.Payment;

@Repository
public class paymentDaoImpl implements PaymentDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	
	public void insertPayment(Payment payment)  {

		sst.insert("paymentns.insert", payment);
	}


	@Override
	public int selectPayment() {
		
		return sst.selectOne("max");
	}


	@Override
	public Payment getPayment(int payment_no) {
		
		return sst.selectOne("paymentns.getPayment", payment_no);
	}


	@Override
	public List<Payment> list(String mem_id) {
		
		return sst.selectList("paymentns.list", mem_id);
	}
	
	public List<Payment> list(Payment payment) {
	    
		return sst.selectList("paymentns.list2", payment);
	   }
	
	public List<Payment> getPaymentList(int seller_no) {
	
		return sst.selectList("opns.getPaymentList", seller_no);
	   }
	
	public List<Payment> getOrderList(Payment payment) {
	
		return sst.selectList("opns.getOrderList", payment);
	   }
	
	public int getTotal(Payment payment) {
	
		return sst.selectOne("paymentns.getTotal", payment);
	   }
	
}

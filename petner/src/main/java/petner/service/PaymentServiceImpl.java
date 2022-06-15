package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.dao.PaymentDao;
import petner.model.Lost;
import petner.model.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentDao payd;
	
	public void insertPayment(Payment payment) { // springmember참조
		payd.insertPayment(payment);
	}

	@Override
	public int selectPayment() {
		
		return  payd.selectPayment();
	}

	@Override
	public Payment getPayment(int payment_no) {
		
		
		return payd.getPayment(payment_no);
	}

	@Override
	public List<Payment> list(String mem_id) {
		
		return payd.list(mem_id);
	}
	
	public List<Payment> list(Payment payment) {
	    
		return payd.list(payment);
	   }
	
	public List<Payment> getPaymentList(int seller_no) {
	
		return payd.getPaymentList(seller_no);
	   }
	
	public List<Payment> getOrderList(Payment payment) {
	
		return payd.getOrderList(payment);
	   }
	
	public int getTotal(Payment payment) {
	
		return payd.getTotal(payment);
	   }
	
}

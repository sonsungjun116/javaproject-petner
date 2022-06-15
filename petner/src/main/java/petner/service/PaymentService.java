package petner.service;


import java.util.List;

import petner.model.Lost;
import petner.model.Payment;

public interface PaymentService {
	
	public void insertPayment(Payment payment);
	
	public int selectPayment();
	
	Payment getPayment(int payment_no); 
	
	List<Payment> list(String mem_id);
	
	public List<Payment> list(Payment payment);

	public List<Payment> getPaymentList(int seller_no);

	public List<Payment> getOrderList(Payment payment);

	public int getTotal(Payment payment);


}

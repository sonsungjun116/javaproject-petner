package petner.model;

import java.sql.Date;

public class Payment {

	private int payment_no;
	private Date payment_regdate;
	private String order_no;
	private String mem_id;
	private String mem_post;
	private String mem_addr1;
	private String mem_addr2;
	private int payment_price;
	private String payment_step;
	private Date payment_canceldate;
	private int product_no;
	private int product_ea;
	private String product_name;
	private int seller_no;
	private String order_state;
	
	public int getSeller_no() {
		return seller_no;
	}
	public void setSeller_no(int seller_no) {
		this.seller_no = seller_no;
	}
	public int getProduct_ea() {
		return product_ea;
	}
	public void setProduct_ea(int product_ea) {
		this.product_ea = product_ea;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPayment_no() {
		return payment_no;
	}
	public void setPayment_no(int payment_no) {
		this.payment_no = payment_no;
	}
	public Date getPayment_regdate() {
		return payment_regdate;
	}
	public void setPayment_regdate(Date payment_regdate) {
		this.payment_regdate = payment_regdate;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_post() {
		return mem_post;
	}
	public void setMem_post(String mem_post) {
		this.mem_post = mem_post;
	}
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}
	public int getPayment_price() {
		return payment_price;
	}
	public void setPayment_price(int payment_price) {
		this.payment_price = payment_price;
	}
	public String getPayment_step() {
		return payment_step;
	}
	public void setPayment_step(String payment_step) {
		this.payment_step = payment_step;
	}
	public Date getPayment_canceldate() {
		return payment_canceldate;
	}
	public void setPayment_canceldate(Date payment_canceldate) {
		this.payment_canceldate = payment_canceldate;
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	
}

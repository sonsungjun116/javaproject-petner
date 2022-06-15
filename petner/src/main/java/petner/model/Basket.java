package petner.model;

public class Basket {

	private int basket_no;
	private int product_no;
	private String mem_id;
	private int basket_ea;
	private int basket_product_sum;
	private String product_name;
	private int product_price;
	
	public int getBasket_no() {
		return basket_no;
	}
	public void setBasket_no(int basket_no) {
		this.basket_no = basket_no;
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getBasket_ea() {
		return basket_ea;
	}
	public void setBasket_ea(int basket_ea) {
		this.basket_ea = basket_ea;
	}
	public int getBasket_product_sum() {
		return basket_product_sum;
	}
	public void setBasket_product_sum(int basket_product_sum) {
		this.basket_product_sum = basket_product_sum;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
	
}

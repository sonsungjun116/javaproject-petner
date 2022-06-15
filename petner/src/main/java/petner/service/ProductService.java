package petner.service;

import java.util.List;

import petner.model.Product;
import petner.model.Seller;

public interface ProductService {

	public List<Product> list(Product product);
	
	public List<Product> mylist(Product product);
	
	public int getTotal(Product product);
	
	public int getMytotal(Product product);

	public Product getProduct(int product_no);
	
	public int getEa(Product product);
	
	public int insert(Product product);
	
	public int update(Product product);
	
	public int delete(int product_no);
	
	public List<Product> getProductList(int seller_no);
}

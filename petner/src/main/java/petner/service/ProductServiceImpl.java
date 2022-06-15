package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petner.dao.ProductDao;
import petner.dao.SellerDao;
import petner.model.Product;
import petner.model.Seller;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao pd;
	
	public List<Product> list(Product product){
		return pd.list(product);
	}
	
	public List<Product> mylist(Product product) {
		return pd.mylist(product);
	}

	public int getTotal(Product product) {
		return pd.getTotal(product);
	}
	
	public int getMytotal(Product product) {
		return pd.getMytotal(product);
	}

	public Product getProduct(int product_no) {
		return pd.getProduct(product_no);
	}

	public int getEa(Product product) {
		return pd.getEa(product);
	}
	
	public int insert(Product product) {
		return pd.insert(product);
	}

	public int update(Product product) {
		return pd.update(product);
	}

	public int delete(int product_no) {
		return pd.delete(product_no);
	}
	
	public List<Product> getProductList(int seller_no) {
		return pd.getProductList(seller_no);
	}

}

package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Product;
import petner.model.Seller;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	public List<Product> list(Product product){
		return sst.selectList("productns.list", product);
	}
	
	public List<Product> mylist(Product product) {
		return sst.selectList("productns.mylist", product);
	}
	
	public int getTotal(Product product) {
		return sst.selectOne("productns.getTotal", product);
	}
	
	public int getMytotal(Product product) {
		return sst.selectOne("productns.getMytotal", product);
	}
	
	public Product getProduct(int product_no) {
		return sst.selectOne("productns.getProduct", product_no);
	}
	
	public int getEa(Product product) {
		return sst.update("productns.getEa", product);
	}

	public int insert(Product product) {
		return sst.insert("productns.insert", product);
	}

	public int update(Product product) {
		return sst.update("productns.update", product);
	}

	public int delete(int product_no) {
		return sst.update("productns.delete", product_no);
	}

	public List<Product> getProductList(int seller_no) {
		 return sst.selectList("opns.getProductList", seller_no);
	}
}

package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Basket;

@Repository
public class BasketDaoImpl implements BasketDao{

	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public void insertBasket(Basket basket) {
		sst.insert("basketns.insertBasket", basket);
	}

	@Override
	public List<Basket> basketList(String mem_id) {
		System.out.println("mem_id:"+mem_id);
		return sst.selectList("basketns.basketList", mem_id);
	}

	@Override
	public void update_ea(Basket basket) {
		System.out.println("basket_no:"+basket.getBasket_no());
		System.out.println("basket_ea:"+basket.getBasket_ea());
		sst.update("basketns.update_ea", basket);
	}

	@Override
	public Basket getBasket(int basket_no) {
		return sst.selectOne("basketns.getBasket", basket_no);
	}
}

package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.dao.BasketDao;
import petner.model.Basket;

@Service
public class BasketServiceImpl implements BasketService{

	@Autowired
	private BasketDao bd;

	@Override
	public void insertBasket(Basket basket) {
		bd.insertBasket(basket);
	}

	@Override
	public List<Basket> basketList(String mem_id) {
		System.out.println("mem_id:"+mem_id);
		return bd.basketList(mem_id);
	}

	@Override
	public void update_ea(Basket basket) {
		System.out.println("basket_no:"+basket.getBasket_no());
		System.out.println("basket_ea:"+basket.getBasket_ea());
		bd.update_ea(basket);
	}

	@Override
	public Basket getBasket(int basket_no) {
		return bd.getBasket(basket_no);
	}
}

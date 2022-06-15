package petner.dao;

import java.util.List;

import petner.model.Basket;

public interface BasketDao {

	public void insertBasket(Basket basket);
	
	public List<Basket> basketList(String mem_id);
	
	public void update_ea(Basket basket);
	
	public Basket getBasket(int basket_no);
}

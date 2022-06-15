package petner.service;

import java.util.List;

import petner.model.Basket;

public interface BasketService {

	// 장바구니 담기
	public void insertBasket(Basket basket);
	
	// 회원의 장바구니 리스트 출력
	public List<Basket> basketList(String mem_id);
	
	// 장바구니 수량 변경
	public void update_ea(Basket basket);
	
	// 장바구니 상세정보 구하기
	public Basket getBasket(int basket_no);
}

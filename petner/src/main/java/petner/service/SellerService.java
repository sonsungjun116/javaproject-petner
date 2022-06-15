package petner.service;

import java.util.List;

import petner.model.Member;
import petner.model.Seller;

public interface SellerService {

   void insertSeller(Seller seller) throws Exception;
   
   public Seller getSeller(int seller);
   
   public Seller getSeller_no(String mem_id);
   
   public List<Seller> sellerList();
   
   public void update_seller_accept(int li);
   
   public Seller select_seller(int li);
}
package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.model.*;

import petner.dao.MemberDao;
import petner.dao.SellerDao;

@Service
public class SellerServiceImpl implements SellerService {

   @Autowired
   private SellerDao sd;
   
   public void insertSeller(Seller seller) throws Exception{
      sd.insertSeller(seller);
   }

   public Seller getSeller(int seller) {
      return sd.getSeller(seller);
   }

   @Override
   public Seller getSeller_no(String mem_id) {
      return sd.getSeller_no(mem_id);
   }

   @Override
   public List<Seller> sellerList() {
      return sd.sellerList();
   }

   @Override
   public void update_seller_accept(int li) {
      sd.update_seller_accept(li);
   }

   @Override
   public Seller select_seller(int li) {
      return sd.select_seller(li);
   }
   
}
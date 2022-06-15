package petner.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Seller;



@Repository
public class SellerDaoImpl implements SellerDao {

   @Autowired
   private SqlSessionTemplate sst;
   
   public void insertSeller(Seller seller) throws Exception {
      
      sst.insert("sellerns.insert",seller);
   }

   public Seller getSeller(int seller) {
      return sst.selectOne("productns.getSeller", seller);
   }

   @Override
   public Seller getSeller_no(String mem_id) {
      return sst.selectOne("memberns.getSeller_no", mem_id);
   }

   @Override
   public List<Seller> sellerList() {
      return sst.selectList("sellerns.sellerList");
   }

   @Override
   public void update_seller_accept(int li) {
      sst.update("sellerns.update_seller_accept", li);
   }

   @Override
   public Seller select_seller(int li) {
      return sst.selectOne("sellerns.select", li);
   }
}
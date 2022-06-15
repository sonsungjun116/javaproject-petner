package petner.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

   @Autowired
   private SqlSessionTemplate sst;

   @Override
   public void Sign_up(Member member) {
      sst.insert("memberns.Sign_up", member);
   }

   @Override
   public int mem_idcheck(String mem_id) {
      int result = -1; // 사용 가능한 ID
      Member mb = sst.selectOne("memberns.mem_idcheck", mem_id);
      if (mb != null) {
         result = 1; // 중복 ID
      }
      return result;
   }

   public Member getuser(String mem_id) {
      return sst.selectOne("memberns.mem_idcheck", mem_id);
   }

   public void mem_update(Member member) {
      sst.update("memberns.mem_update", member);
   }

   public void mem_delete(String mem_id) {
      sst.update("memberns.mem_delete", mem_id);
   }
   
   public Member getDelivery_info(int payment_no) {
         return sst.selectOne("opns.getDelivery_info", payment_no);
      }

   @Override
   public void update_mem(String mem_id) {
      sst.update("memberns.update_mem", mem_id);
   }
}
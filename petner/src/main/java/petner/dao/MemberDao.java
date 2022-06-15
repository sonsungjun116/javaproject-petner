package petner.dao;

import petner.model.Member;

public interface MemberDao {

   public void Sign_up(Member member);
   
   public int mem_idcheck(String mem_id);
   
   public Member getuser(String mem_id);
   
   public void mem_update(Member member);
   
   public void mem_delete(String mem_id);
   
   public Member getDelivery_info(int payment_no);
   
   public void update_mem(String mem_id);
}
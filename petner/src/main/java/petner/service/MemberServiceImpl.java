package petner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import petner.dao.MemberDao;
import petner.model.Member;

@Service
public class MemberServiceImpl implements MemberService {

   @Autowired
   private MemberDao md;
   
   public void Sign_up(Member member) {
      md.Sign_up(member);   // 회원 가입 실행
   }

   public int mem_idcheck(String mem_id) {
      return md.mem_idcheck(mem_id);
   }

   public Member getuser(String mem_id) {
      return md.getuser(mem_id);
   }

   public void mem_update(Member member) {
      md.mem_update(member);
   }

   public void mem_delete(String mem_id) {
      md.mem_delete(mem_id);
   }
   
   public Member getDelivery_info(int payment_no) {
         return md.getDelivery_info(payment_no);
      }

   @Override
   public void update_mem(String mem_id) {
      md.update_mem(mem_id);
   }
}
package petner.service;

import petner.model.Member;

public interface MemberService {

   public void Sign_up(Member member);   // 회원 가입 실행
   
   public int mem_idcheck(String mem_id);   // 회원 ID 중복검사 확인
   
   public Member getuser(String mem_id);   // 회원 정보 가져오기
   
   public void mem_update(Member member);   // 회원 정보 업데이트
   
   public void mem_delete(String mem_id);   // 회원 삭제
   
   public Member getDelivery_info(int payment_no);
   
   public void update_mem(String mem_id);
}
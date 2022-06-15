package petner.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import petner.model.Member;
import petner.model.Seller;
import petner.service.MemberService;
import petner.service.SellerService;

@Controller
public class SellerController {

   @Autowired
   private SellerService ss;

   @Autowired
   private MemberService ms;

   @RequestMapping("TransferForm")
   public String TransferForm(String mem_id, Model model) {  //String mem_id, Model model --> 로그인한 상태로 session을 통해 넘길꺼기 때문에 아이디 값을 받음
      //Member member = ms.select(mem_id); 상세정보 구하기
//      model.addAttribute("member", member);
      
      model.addAttribute("mem_id", mem_id);
      
      return "seller/TransferForm";
   }
   
   @RequestMapping(value="Transfer", method = RequestMethod.POST)
   public String Transfer(Seller seller, Model model) throws Exception { // id값 받기, seller dto를 받는다  Seller seller,String mem_id, Model model
      
      
//      String mem_id = request.getParameter("mem_id").trim();
//      String seller_name = request.getParameter("seller_name").trim();
//      String seller_post = request.getParameter("seller_post").trim();
//      String seller_addr1 = request.getParameter("seller_addr1").trim();
//      String seller_addr2 = request.getParameter("seller_addr2").trim();
//      String seller_tel1 = request.getParameter("seller_tel1").trim();
//      String seller_tel2 = request.getParameter("seller_tel2").trim();
//      String seller_tel3 = request.getParameter("seller_tel3").trim();
      
//      Seller.setmem_id(mem_id);      
//      Seller.setSeller_name(seller_name);      
//      Seller.setSeller_post(seller_post);      
//      Seller.setSeller_addr1(seller_addr1);      
//      Seller.setSeller_addr2(seller_addr2);      
//      Seller.setSeller_tel1(seller_tel1);      
//      Seller.setSeller_tel2(seller_tel2);      
//      Seller.setSeller_tel3(seller_tel3);      
   
      
       ss.insertSeller(seller);// db 입력
      //Member member = ms.select(mem_id); 상세정보 구하기
//      model.addAttribute("member", member);
//      model.addAttribute("", );
      
      return "redirect:productlist1";
   }
   
   @RequestMapping("sellerList")
   public String  sellerList(Model model) {
      
      List<Seller> list = ss.sellerList();
      model.addAttribute("list", list);
      
      return "seller/sellerList";
   }
   
   @RequestMapping("update_seller_accept")
   public String update_seller_accept(HttpServletRequest request, Model model) {
      
      String[] list = request.getParameterValues("update_seller_accept");
      
      for(String li : list) {
         ss.update_seller_accept(Integer.parseInt(li));
         Seller seller = ss.select_seller(Integer.parseInt(li));
         ms.update_mem(seller.getMem_id());
      }
      
      return "redirect:sellerList";
   }
}
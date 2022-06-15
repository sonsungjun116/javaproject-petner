$(function() {
   $("#tab").submit(function() {
                  if ($.trim($("#mem_id").val()) == "") {
                     alert("ID를 입력하세요.");
                     $("#mem_id").focus();
                     return false;
                  }

                  if ($.trim($("#mem_id").val()).length < 4) {
                     alert("ID는 4자리 이상으로 입력하세요.");
                     $("#mem_id").val("");
                     $("#mem_id").focus();
                     return false;
                  }

                  if ($.trim($("#mem_id").val()).length > 12) {
                     alert("ID는 12자리 이하로 입력하세요.");
                     $("#mem_id").val("");
                     $("#mem_id").focus();
                     return false;
                  }
                  
                  if ($.trim($("#mem_name").val()) == "") {
                     alert("이름을 입력하세요.");
                     $("#mem_name").focus();
                     return false;
                  }

                  if (!(isNaN($("#mem_name").val()))) {
                     alert("이름은 숫자 입력이 불가능합니다.");
                     $("#mem_name").val("");
                     $("#mem_name").focus();
                     return false;
                  }

                  if ($.trim($("#mem_pwd").val()) == "") {
                     alert("비밀번호를 입력하세요.");
                     $("#mem_pwd").focus();
                     return false;
                  }
                  if ($.trim($("#mem_pwd").val()).length <= 8) {
                     alert("비밀번호는 8자리 이상으로 입력하세요.");
                     $("#mem_pwd").val("");
                     $("#mem_pwd").focus();
                     return false;
                  }
                  
                  if ($.trim($("#mem_pwd2").val()) == "") {
                     alert("비밀번호를 한번 더 입력하세요.");
                     $("#mem_pwd2").focus();
                     return false;
                  }

                  if ($.trim($("#mem_pwd2").val()).length <= 8) {
                     alert("비밀번호를 한번 더 입력하세요.");
                     $("#mem_pwd2").val("");
                     $("#mem_pwd2").focus();
                     return false;
                  }
                  
                  if ($.trim($("#mem_pwd").val()) != $
                        .trim($("#mem_pwd2").val())) {
                     alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                     $("#mem_pwd2").val("");
                     $("#mem_pwd2").focus();
                     return false;
                  }

                  if (isNaN($("#mem_phone2").val())) {
                     alert("전화번호는 숫자 입력만 가능합니다.");
                     $("#mem_phone2").val("");
                     $("#mem_phone2").focus();
                     return false;
                  }

                  if (isNaN($("#mem_phone3").val())) {
                     alert("전화번호는 숫자 입력만 가능합니다.");
                     $("#mem_phone3").val("");
                     $("#mem_phone3").focus();
                     return false;
                  }

                  if ($.trim($("#mem_phone2").val()) == "") {
                     alert("전화번호를 입력하세요.");
                     $("#mem_phone2").focus();
                     return false;
                  }
                  
                  if ($.trim($("#mem_phone2").val()).length != 4) {
                     alert("핸드폰 번호는 4자리 이하로 입력하세요.");
                     $("#mem_phone2").val("");
                     $("#mem_phone2").focus();
                     return false;
                  }

                  if ($.trim($("#mem_phone3").val()) == "") {
                     alert("전화번호를 입력하세요.");
                     $("#mem_phone3").focus();
                     return false;
                  }
                  
                  if ($.trim($("#mem_phone3").val()).length != 4) {
                     alert("핸드폰 번호는 4자리 이하로 입력하세요.");
                     $("#mem_phone3").val("");
                     $("#mem_phone3").focus();
                     return false;
                  }
                  
                  if ($("#mem_gender1").is(":checked") == false
                        && $("#mem_gender2").is(":checked") == false) {
                     alert("성별을 선택하세요.");
                     return false;
                  }

                  if ($.trim($("#mem_post").val()) == "") {
                     alert("우편번호를 입력하세요.");
                     return false;
                  }

                  if ($.trim($("#mem_addr2").val()) == "") {
                     alert("상세주소를 입력하세요.");
                     $("#mem_addr2").focus();
                     return false;
                  }

                  if ($.trim($("#datepicker").val()) == "") {
                     alert("생일을 입력하세요.");
                     return false;
                  }

                  if ($.trim($("#mem_email").val()) == "") {
                     alert("이메일 ID를 입력하세요.");
                     $("#mem_email").focus();
                     return false;
                  }

                  if ($.trim($("#mem_domain").val()) == "") {
                     alert("이메일 주소를 선택하세요.");
                     $("#mem_domain").focus();
                     return false;
                  }

               });

});

function domain_list() {
   var num = tab.mail_list.selectedIndex;
   /*
    * selectedIndex속성은 select객체하위의 속성으로서 해당리스트 목록번호를 반환
    */
   if (num == -1) {
      // num==-1은 해당 리스트목록이 없다
      return true;
   }
   if (tab.mail_list.value == "") // 직접입력
   {
      /* 리스트에서 직접입력을 선택했을때 */
      tab.mem_domain.value = "";
      // @뒤의 도메인입력란을 빈공간시켜라.
      tab.mem_domain.readOnly = false;
      // @뒤의 도메인입력란을 쓰기 가능
      tab.mem_domain.focus();
      // 도메인입력란으로 입력대기상태
   }

   else {
      // 리스트목록을 선택했을때

      tab.mem_domain.value = tab.mail_list.options[num].value;
      /*
       * num변수에는 해당리스트 목록번호가 저장되어있다.해당리스트 번호의 option value값이 도메인입력란에
       * 선택된다.options속성은 select객체의 속성으로서 해당리스트번호의 value값을 가져온다
       */
      tab.mem_domain.readOnly = true;
   }
}

// ID 중복 확인
function idCheck() {
   $("#tex").hide();

   if ($.trim($("#mem_id").val()) == "") {
      alert("ID를 입력하세요.");
      $("#mem_id").focus();
      return false;
   }

   if ($.trim($("#mem_id").val()).length < 4) {
      alert("ID는 4자리 이상으로 입력하세요.");
      $("#mem_id").val("");
      $("#mem_id").focus();
      return false;
   }

   if ($.trim($("#mem_id").val()).length > 12) {
      alert("ID는 12자리 이하로 입력하세요.");
      $("#mem_id").val("");
      $("#mem_id").focus();
      return false;
   }

   var mem_id = $("#mem_id").val();

   $.ajax({
      type : "POST",
      url : "member_idcheck",
      data : {
         "mem_id" : mem_id
      },
      success : function(data) {
         // alert("data:"+data)
         if (data == 1) { // 중복 ID
            $("#tex").show();
            $("#tex").html('<font color="red">중복 아이디입니다.</font>');
            $("#mem_id").val("").focus();
         } else { // 사용가능한 ID
            $("#tex").show();
            $("#tex").html('<font color="blue">사용가능한 아이디입니다.</font>');
            $("#mem_name").val("").focus();
         }
      }
   });

}

$(function(){
   $("#tab1").submit(function(){
      if($.trim($("#mem_id").val()) == "") {
         alert("ID를 입력하세요.");
         $("#mem_id").focus();
         return false;
      }

      if($.trim($("#mem_pwd").val()) == "") {
         alert("비밀번호를 입력하세요.");
         $("#mem_pwd").focus();
         return false;
      }
   });
});

$(function(){
   $("#tab2").submit(function(){
      
      if ($.trim($("#mem_pwd").val()) != $
            .trim($("#mem_pwd2").val())) {
         alert("비밀번호를 다시 입력하세요.");
         $("#mem_pwd2").val("");
         $("#mem_pwd2").focus();
         return false;
      }
      
      if (isNaN($("#mem_phone2").val())) {
         alert("전화번호는 숫자 입력만 가능합니다.");
         $("#mem_phone2").val("");
         $("#mem_phone2").focus();
         return false;
      }

      if (isNaN($("#mem_phone3").val())) {
         alert("전화번호는 숫자 입력만 가능합니다.");
         $("#mem_phone3").val("");
         $("#mem_phone3").focus();
         return false;
      }

      if ($.trim($("#mem_phone2").val()) == "") {
         alert("전화번호를 입력하세요.");
         $("#mem_phone2").focus();
         return false;
      }
      
      if ($.trim($("#mem_phone2").val()).length != 4) {
         alert("핸드폰 번호는 4자리 이하로 입력하세요.");
         $("#mem_phone2").val("");
         $("#mem_phone2").focus();
         return false;
      }

      if ($.trim($("#mem_phone3").val()) == "") {
         alert("전화번호를 입력하세요.");
         $("#mem_phone3").focus();
         return false;
      }
      
      if ($.trim($("#mem_phone3").val()).length != 4) {
         alert("핸드폰 번호는 4자리 이하로 입력하세요.");
         $("#mem_phone3").val("");
         $("#mem_phone3").focus();
         return false;
      }
      
      if ($.trim($("#mem_post").val()) == "") {
         alert("우편번호를 입력하세요.");
         return false;
      }

      if ($.trim($("#mem_addr2").val()) == "") {
         alert("상세주소를 입력하세요.");
         $("#mem_addr2").focus();
         return false;
      }
      
      if ($.trim($("#mem_email").val()) == "") {
         alert("이메일 ID를 입력하세요.");
         $("#mem_email").focus();
         return false;
      }

      if ($.trim($("#mem_domain").val()) == "") {
         alert("이메일 주소를 선택하세요.");
         $("#mem_domain").focus();
         return false;
      }

   });
});

$(function(){
   $("#tab3").submit(function(){
      if ($.trim($("#mem_pwd").val()) != $
            .trim($("#mem_pwd2").val())) {
         alert("비밀번호를 다시 입력하세요.");
         $("#mem_pwd2").val("");
         $("#mem_pwd2").focus();
         return false;
      }
   });
});  
insert into hospital values (hospital_no_seq.nextval, '동물병원', '부산', '부산시 동래구', '50524', '051-205-7671',
'', '평일 10:00 - 18:00', 'Y', 'Y', 'N', '동물병원입니다.', 'https://www.petner.com', '');

select * from admin;

insert into admin values ('admin', '1234');
insert into admin values ('admin2', '1234');

insert into qna values ( qna_no_seq.nextval, '문의글입니다.',
		'', '', 0, sysdate, '내용입니다.',
		'', 'N', qna_no_seq.nextval,
		0, 0, '상품', '답변대기', '1' )
		
select * from tab;
select * from product;
select * from hospital;
select * from qna;
select * from board53;
select * from member;
select * from order_product;
select * from product;

create sequence product_no_seq
	start with 1
	increment by 1
	
create sequence orderproduct_no_seq
	start with 1
	increment by 1

insert into product values (
	product_no_seq.nextval, '', '상품이름', '100', '내용',
	'', '10000', '', 'n', '1', '강아지', '간식', '1', '제조사'
);




create sequence lost_seq 
increment by 1 start with 1 nocache;

create sequence lostreply_seq 
increment by 1 start with 1 nocache;

create sequence seller_seq 
increment by 1 start with 1 nocache;

insert into member values('test0525','1234','21013','인천 계양구','용종동','손성준','96년 1월 16일',
'qqeerr123','naver.com','010','4112','8871','남',sysdate,'123123','일반회원','no','n',1000,'VIP',100);

create sequence board53_num_seq
                increment by 1 start with 1 nocache;
                select * from seq;
                
                select * from lostreply;
                
alter table lost add lat number(35,20);              
alter table lost add lng number(35,20);

create sequence payment_seq 
increment by 1 start with 1 nocache;
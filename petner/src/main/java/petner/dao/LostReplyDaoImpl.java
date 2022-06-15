package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import petner.model.Lost;
import petner.model.LostReply;

@Repository
public class LostReplyDaoImpl implements LostReplyDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	public void insertreply(LostReply lostreply)  {

		sst.insert("lostreplyns.insert", lostreply);
	}
	
	public List<LostReply> list(int lost_no) {
		/*
		 * HashMap<String, Integer> hm=new HashMap<String, Integer>();
		 * hm.put("startRow",startRow); 
		 * hm.put("endRow",endRow);
		 */
		return sst.selectList("lostreplyns.list", lost_no);
	}
	
	public void update(LostReply lostreply) {
		sst.update("lostreplyns.update", lostreply);
	}
	
	public void delete(int lostreply_no) {
		sst.delete("lostreplyns.delete", lostreply_no);
	}
	
	/* 답변글 레벨 증가  */
	public void refEdit(LostReply lostreply) throws Exception {
		sst.update("lostreplyns.lostreply_Level", lostreply);		
	}

	
	/* 답변글 저장  */
	public void boardReplyOk(LostReply lostreply) throws Exception {
		sst.insert("lostreplyns.lostreply_reply", lostreply);		
	}
	
}

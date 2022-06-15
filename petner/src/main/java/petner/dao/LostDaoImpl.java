package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import petner.model.Lost;

@Repository
public class LostDaoImpl implements LostDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	public List<Lost> list(Lost lost) {
		/*
		 * HashMap<String, Integer> hm=new HashMap<String, Integer>();
		 * hm.put("startRow",startRow); 
		 * hm.put("endRow",endRow);
		 */
		return sst.selectList("lostns.list", lost);
	}
	
	public int getTotal(Lost lost) {
		return sst.selectOne("lostns.getTotal", lost);
	}
	
	public void selectUpdate(int num) {
		sst.update("lostns.selectUpdate",num);
	}
	
	public Lost select(int num) {
		return sst.selectOne("lostns.select",num);
	}
	
	public void insertLost(Lost lost)  {

		sst.insert("lostns.insert", lost);
	}
	
	public int update(Lost lost) {
		return sst.update("lostns.update",lost);
	}
	
	public int delete(int lost_no) {
		return sst.update("lostns.delete",lost_no);
	}
	
}

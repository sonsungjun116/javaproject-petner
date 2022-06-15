package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import petner.dao.LostDao;
import petner.model.Lost;

@Service
public class LostServiceImpl implements LostService {

	@Autowired
	private LostDao ld;
	
	public List<Lost> list(Lost lost) {
		return ld.list(lost);
		// return bd.list(startRow, endRow);
	}
	
	public int getTotal(Lost lost) {
		return ld.getTotal(lost);
	}
	
	public void selectUpdate(int num) {
		ld.selectUpdate(num);
	}
	
	public Lost select(int num) {
		return ld.select(num);
	}
	
	public void insertLost(Lost lost) { // springmember참조
		ld.insertLost(lost);
	}
	
	public int update(Lost lost) {
		return ld.update(lost);
	}
	
	public int delete(int lost_no) {
		return ld.delete(lost_no);
	}
	
}

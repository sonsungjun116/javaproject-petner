package petner.dao;

import java.util.List;

import petner.model.Tipboard;

public interface TipboardDao {

	public void insertTip(Tipboard tipboard);
	
	public int getTotal(Tipboard tipboard);
	
	public List<Tipboard> list(Tipboard tipboard);
	
	public Tipboard getTip(int tip_no);
	
	public void updatetip_readcount(int tip_no);
	
	public void updateTip(Tipboard tipboard);
	
	public void deleteTip(int tip_no);
}

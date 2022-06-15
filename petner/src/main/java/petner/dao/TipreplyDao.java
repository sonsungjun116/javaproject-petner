package petner.dao;

import java.util.List;

import petner.model.Tipreply;

public interface TipreplyDao {

	public void insertTipreply(Tipreply tipreply);
	
	public List<Tipreply> getTipreplyList(int tip_no);
	
	public void deleteTipreply(int tipreply_no);
	
	public void updateTipreply(Tipreply tipreply);
	
	public void addreply(Tipreply tipreply);
	
	public void refEdit(Tipreply tipreply);
}

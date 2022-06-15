package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.dao.TipreplyDao;
import petner.model.Tipreply;

@Service
public class TipreplyServiceImpl implements TipreplyService{

	@Autowired
	private TipreplyDao trd;

	@Override
	public void insertTipreply(Tipreply tipreply) {
		trd.insertTipreply(tipreply);
	}

	@Override
	public List<Tipreply> getTipreplyList(int tip_no) {
		return trd.getTipreplyList(tip_no);
	}

	@Override
	public void deleteTipreply(int tipreply_no) {
		trd.deleteTipreply(tipreply_no);
	}

	@Override
	public void updateTipreply(Tipreply tipreply) {
		trd.updateTipreply(tipreply);
	}

	@Override
	public void addreply(Tipreply tipreply) {
		
		System.out.println("tipreply_seq(controller):"+tipreply.getTipreply_seq());
		System.out.println("tipreply_lev(controller):"+tipreply.getTipreply_lev());
		trd.refEdit(tipreply);
		
		tipreply.setTipreply_lev(tipreply.getTipreply_lev() + 1);
		tipreply.setTipreply_seq(tipreply.getTipreply_seq() + 1);
		
		System.out.println(tipreply.getTipreply_no());
		System.out.println(tipreply.getMem_id());
		System.out.println(tipreply.getTipreply_lev());
		System.out.println(tipreply.getTip_no());
		System.out.println(tipreply.getTipreply_seq());
		System.out.println(tipreply.getTipreply_content());
		
		trd.addreply(tipreply);
	}
}

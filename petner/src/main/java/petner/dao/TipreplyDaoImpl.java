package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Tipreply;

@Repository
public class TipreplyDaoImpl implements TipreplyDao{

	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public void insertTipreply(Tipreply tipreply) {
		sst.insert("tip_replyns.insertTipreply", tipreply);
	}

	@Override
	public List<Tipreply> getTipreplyList(int tip_no) {
		return sst.selectList("tip_replyns.getTipreplyList", tip_no);
	}

	@Override
	public void deleteTipreply(int tipreply_no) {
		sst.delete("tip_replyns.deleteTipreply", tipreply_no);
	}

	@Override
	public void updateTipreply(Tipreply tipreply) {
		sst.update("tip_replyns.updateTipreply", tipreply);
	}

	@Override
	public void addreply(Tipreply tipreply) {
		sst.insert("tip_replyns.addreply", tipreply);
	}

	@Override
	public void refEdit(Tipreply tipreply) {
		System.out.println(tipreply.getTipreply_no());
		System.out.println(tipreply.getMem_id());
		System.out.println(tipreply.getTipreply_lev());
		System.out.println(tipreply.getTip_no());
		System.out.println(tipreply.getTipreply_seq());
		System.out.println(tipreply.getTipreply_content());
		sst.update("tip_replyns.refEdit", tipreply);
	}
}

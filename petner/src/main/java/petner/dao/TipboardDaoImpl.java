package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Tipboard;

@Repository
public class TipboardDaoImpl implements TipboardDao{

	@Autowired
	private SqlSessionTemplate sst;

	public void insertTip(Tipboard tipboard) {
		sst.insert("tip_boardns.insertTip", tipboard);
	}

	public int getTotal(Tipboard tipboard) {
		return sst.selectOne("tip_boardns.getTotal", tipboard);
	}

	public List<Tipboard> list(Tipboard tipboard) {
		return sst.selectList("tip_boardns.list", tipboard);
	}

	public Tipboard getTip(int tip_no) {
		return sst.selectOne("tip_boardns.getTip", tip_no);
	}

	@Override
	public void updatetip_readcount(int tip_no) {
		sst.update("tip_boardns.updatetip_readcount", tip_no);
	}

	@Override
	public void updateTip(Tipboard tipboard) {
		sst.update("tip_boardns.updateTip", tipboard);
	}

	@Override
	public void deleteTip(int tip_no) {
		sst.delete("tip_boardns.deleteTip", tip_no);
	}
}

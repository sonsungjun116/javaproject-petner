package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import petner.dao.TipboardDao;
import petner.model.Tipboard;

@Service
public class TipboardServiceImpl implements TipboardService{
	
	@Autowired
	private TipboardDao tbd;

	public void insertTip(Tipboard tipboard) {
		tbd.insertTip(tipboard);
	}

	public int getTotal(Tipboard tipboard) {
		return tbd.getTotal(tipboard);
	}

	public List<Tipboard> list(Tipboard tipboard) {
		return tbd.list(tipboard);
	}

	@Override
	public Tipboard getTip(int tip_no) {
		return tbd.getTip(tip_no);
	}

	@Override
	public void updatetip_readcount(int tip_no) {
		tbd.updatetip_readcount(tip_no);
	}

	@Override
	public void updateTip(Tipboard tipboard) {
		tbd.updateTip(tipboard);
	}

	@Override
	public void deleteTip(int tip_no) {
		tbd.deleteTip(tip_no);
	}

	
}

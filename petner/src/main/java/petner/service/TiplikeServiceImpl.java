package petner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.dao.TiplikeDao;

@Service
public class TiplikeServiceImpl implements TiplikeService{

	@Autowired
	private TiplikeDao td;
}

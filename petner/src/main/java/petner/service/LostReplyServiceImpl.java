package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import petner.dao.LostDao;
import petner.dao.LostReplyDao;
import petner.model.Lost;
import petner.model.LostReply;

@Service
public class LostReplyServiceImpl implements LostReplyService {

	@Autowired
	private LostReplyDao lrd;
	
	
	public void insertreply(LostReply lostreply) { // springmember참조
		lrd.insertreply(lostreply);
	}
	
	public List<LostReply> list(int lost_no) {
		return lrd.list(lost_no);
		// return bd.list(startRow, endRow);
	}
	
	public void update(LostReply lostreply) {
		lrd.update(lostreply);
	}
	
	public void delete(int lostreply_no) {
		lrd.delete(lostreply_no);
	}
	
	/* 게시판 댓글 달기 */
	public void rreply(LostReply lostreply) throws Exception {

//		lrd.refEdit(lostreply); // 기존 댓글 board_re_seq값 1증가 ,답변 글 레벨증가
//
		lostreply.setLostreply_lev(lostreply.getLostreply_lev() + 1); // 부모보다 1증가된 값을 저장함
//		lostreply.setLostreply_seq(lostreply.getLostreply_seq() + 1);

		lrd.boardReplyOk(lostreply);	// 답변글 저장
	}
}

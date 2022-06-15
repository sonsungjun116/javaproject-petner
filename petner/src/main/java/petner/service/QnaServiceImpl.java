package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.dao.QnaDao;
import petner.model.Qna;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaDao qd;

	// 문의글 목록
	public List<Qna> list(Qna qna) {
		return qd.list(qna);
	}

	// 문의글 개수
	public int getTotal(Qna qna) {
		return qd.getTotal(qna);
	}

	// 조회수 증가
	public void hit(int qna_no) {
		qd.hit(qna_no);
	}

	// 문의글 등록
	public int insert(Qna qna) {
		return qd.insert(qna);
	}

	// 상세페이지, 수정폼, 삭제폼, 답변폼
	public Qna getQna(int qna_no) {
		return qd.getQna(qna_no);
	}

	// 문의글 수정
	public int update(Qna qna) {
		return qd.update(qna);
	}

	// 문의글 삭제
	public int delete(int qna_no) {
		return qd.delete(qna_no);
	}

	// 문의글 답변
	public int reply(Qna qna) {
		return qd.reply(qna);
	}
	
	// 답변 레벨 증가
	public void replyUp(Qna qna) {
		qd.replyUp(qna);
	}
	
	// 답변 상태 변경
	public void replyAnswer(Qna qna) {
		qd.replyAnswer(qna);
	}
}

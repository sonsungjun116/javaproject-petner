package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Qna;

@Repository
public class QnaDaoImpl implements QnaDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	// 문의글 목록
	public List<Qna> list(Qna qna) {
		return sst.selectList("qnans.list", qna);
	}

	// 문의글 개수
	public int getTotal(Qna qna) {
		return sst.selectOne("qnans.getTotal", qna);
	}

	// 조회수 증가
	public void hit(int qna_no) {
		sst.update("qnans.hit", qna_no);
	}

	// 문의글 등록
	public int insert(Qna qna) {
		return sst.insert("qnans.insert", qna);
	}

	// 상세페이지, 수정폼, 삭제폼, 답변폼
	public Qna getQna(int qna_no) {
		return sst.selectOne("qnans.select", qna_no);
	}

	// 문의글 수정
	public int update(Qna qna) {
		return sst.update("qnans.update", qna);
	}

	// 문의글 삭제
	public int delete(int qna_no) {
		return sst.delete("qnans.delete", qna_no);
	}

	// 문의글 답변
	public int reply(Qna qna) {
		return sst.insert("qnans.reply", qna);
	}
	
	// 답변 레벨 증가
	public void replyUp(Qna qna) {
		sst.update("qnans.replyUp", qna);
	}
	
	// 답변 상태 변경
	public void replyAnswer(Qna qna) {
		sst.update("qnans.replyAnswer", qna);
	}
}

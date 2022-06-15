package petner.dao;

import java.util.List;

import petner.model.Qna;

public interface QnaDao {

	// 문의글 목록
	public List<Qna> list(Qna qna);
	
	// 문의글 개수
	public int getTotal(Qna qna);
	
	// 문의글 조회수 증가
	public void hit(int qna_no);
	
	// 문의글 등록
	public int insert(Qna qna);
	
	// 상세페이지, 수정폼, 삭제폼, 답변폼
	public Qna getQna(int qna_no);
	
	// 문의글 수정
	public int update(Qna qna);
	
	// 문의글 삭제
	public int delete(int qna_no);
	
	// 문의글 답변
	public int reply(Qna qna);
	
	// 답변 레벨 증가
	public void replyUp(Qna qna);
	
	// 답변 상태 변경
	public void replyAnswer(Qna qna);
}

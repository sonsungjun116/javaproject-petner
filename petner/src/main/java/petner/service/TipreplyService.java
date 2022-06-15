package petner.service;

import java.util.List;

import petner.model.Tipreply;

public interface TipreplyService {

	// 댓글 입력
	public void insertTipreply(Tipreply tipreply);
	
	// 댓글 리스트 출력
	public List<Tipreply> getTipreplyList(int tip_no);
	
	// 댓글 삭제
	public void deleteTipreply(int tipreply_no);
	
	// 댓글 수정
	public void updateTipreply(Tipreply tipreply);
	
	// 대댓글 입력
	public void addreply(Tipreply tipreply);
}

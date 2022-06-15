package petner.service;

import java.util.List;

import petner.model.Tipboard;

public interface TipboardService {
	
	// 팁게시판 작성
	public void insertTip(Tipboard tipboard);
	
	// 총 데이터 갯수 출력
	public int getTotal(Tipboard tipboard);
	
	// 데이터 리스트 출력
	public List<Tipboard> list(Tipboard tipboard);
	
	// 특정 데이터 출력
	public Tipboard getTip(int tip_no);
	
	// 조회수 증가
	public void updatetip_readcount(int tip_no);
	
	// 팁게시판 수정
	public void updateTip(Tipboard tipboard);
	
	// 팁게시판 삭제
	public void deleteTip(int tip_no);
}

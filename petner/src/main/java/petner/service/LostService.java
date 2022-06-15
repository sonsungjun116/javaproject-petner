package petner.service;

import java.util.List;


import petner.model.Lost;

public interface LostService {

	List<Lost> list(Lost lost); // 미아게시판 리스트 출력
	
	public int getTotal(Lost lost);	// 미아게시판 리스트 총수
	
	void selectUpdate(int num); // 조회수 증가
	
	Lost select(int num); // 특정 미아게시판 조회
	
	public void insertLost(Lost lost); // 미아게시판 작성
	
	int update(Lost lost);	// 미아 게시판 수정
	
	int delete(int lost_no); // 미아게시판 삭제
	
}

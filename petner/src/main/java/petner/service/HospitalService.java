package petner.service;

import java.util.List;

import petner.model.Hospital;

public interface HospitalService {
	
	// 병원 목록
	public List<Hospital> list(Hospital hospital);
	
	// 병원 개수
	public int getTotal(Hospital hospital);
	
	// 병원 등록
	public int insert(Hospital hospital);
	
	// 상세페이지, 수정폼, 삭제폼
	public Hospital getHospital(int hospital_no);
	
	// 병원 수정
	public int update(Hospital hospital);
	
	// 병원 삭제
	public int delete(int hospital_no);
}

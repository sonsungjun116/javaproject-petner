package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.dao.HospitalDao;
import petner.model.Hospital;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDao hd;
	
	// 병원 목록
	public List<Hospital> list(Hospital hospital) {
		return hd.list(hospital);
	}
	
	// 병원 개수
	public int getTotal(Hospital hospital) {
		return hd.getTotal(hospital);
	}
	
	// 병원 등록
	public int insert(Hospital hospital) {
		return hd.insert(hospital);
	}
	
	// 상세페이지, 수정폼, 삭제폼
	public Hospital getHospital(int hospital_no) {
		return hd.getHospital(hospital_no);
	}
	
	// 병원 수정
	public int update(Hospital hospital) {
		return hd.update(hospital);
	}
	
	// 병원 삭제
	public int delete(int hospital_no) {
		return hd.delete(hospital_no);
	}
}

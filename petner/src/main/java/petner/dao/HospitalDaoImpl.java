package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Hospital;

@Repository
public class HospitalDaoImpl implements HospitalDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	// 병원 목록
	public List<Hospital> list(Hospital hospital) {
		return sst.selectList("hospitalns.list", hospital);
	}
	
	// 병원 개수
	public int getTotal(Hospital hospital) {
		return sst.selectOne("hospitalns.getTotal", hospital);
	}
	
	// 병원 등록
	public int insert(Hospital hospital) {
		return sst.insert("hospitalns.insert", hospital);
	}
	
	// 상세페이지, 수정폼, 삭제폼
	public Hospital getHospital(int hospital_no) {
		return sst.selectOne("hospitalns.select", hospital_no);
	}
	
	// 병원 수정
	public int update(Hospital hospital) {
		return sst.update("hospitalns.update", hospital);
	}
	
	// 병원 삭제
	public int delete(int hospital_no) {
		return sst.delete("hospitalns.delete", hospital_no);
	}
}

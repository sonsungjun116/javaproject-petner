package petner.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.model.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	// 관리자 등록
	public int insert(Admin admin) {
		return sst.insert("adminns.insert", admin);
	}
	
	// 정보 구해오기
	public Admin getAdmin(String admin_id) {
		return sst.selectOne("adminns.select", admin_id);
	}
}

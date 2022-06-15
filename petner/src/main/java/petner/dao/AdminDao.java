package petner.dao;

import petner.model.Admin;

public interface AdminDao {

	// 관리자 등록
	public int insert(Admin admin);
	
	// 정보 구해오기
	public Admin getAdmin(String admin_id);

}

package petner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.dao.AdminDao;
import petner.model.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao ad;
	
	// 관리자 등록
	public int insert(Admin admin) {
		return ad.insert(admin);
	}
	
	// 정보 구해오기
	public Admin getAdmin(String admin_id) {
		return ad.getAdmin(admin_id);
	}
}

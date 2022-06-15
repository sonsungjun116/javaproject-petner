package petner.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import petner.dao.ReviewDao;
import petner.model.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public int getTotal(Review review) {
		return sst.selectOne("reviewns.getTotal", review);
	}

	@Override
	public List<Review> list(Review review) {
		System.out.println("dao:"+review);
		return sst.selectList("reviewns.list", review);
	}

	@Override
	public int getNototal(Review review) {
		return sst.selectOne("reviewns.getNototal", review);
	}

	@Override
	public List<Review> nolist(Review review) {
		return sst.selectList("reviewns.nolist", review);
	}

	@Override
	public int insert(Review review) {
		return sst.insert("reviewns.insert", review);
	}

	@Override
	public Review getReview(int review_no) {
		return sst.selectOne("reviewns.getReview", review_no);
	}

	@Override
	public int update(Review review) {
		return sst.update("reviewns.update", review);
	}

	@Override
	public int delete(int review_no) {
		return sst.update("reviewns.delete", review_no);
	}
	
}

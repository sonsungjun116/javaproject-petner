package petner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import petner.dao.ReviewDao;
import petner.model.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao rd;

	@Override
	public int getTotal(Review review) {
		return rd.getTotal(review);
	}

	@Override
	public List<Review> list(Review review) {
		System.out.println("service:"+review);
		return rd.list(review);
	}

	@Override
	public int getNototal(Review review) {
		return rd.getNototal(review);
	}

	@Override
	public List<Review> nolist(Review review) {
		return rd.nolist(review);
	}

	@Override
	public int insert(Review review) {
		return rd.insert(review);
	}

	@Override
	public Review getReview(int review_no) {
		return rd.getReview(review_no);
	}

	@Override
	public int update(Review review) {
		return rd.update(review);
	}

	@Override
	public int delete(int review_no) {
		return rd.delete(review_no);
	}
	
}

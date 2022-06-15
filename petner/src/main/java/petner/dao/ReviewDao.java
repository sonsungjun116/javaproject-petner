package petner.dao;

import java.util.List;

import petner.model.Review;

public interface ReviewDao {

	public int getTotal(Review review);
	
	public List<Review> list(Review review);
	
	public int getNototal(Review review);
	
	public List<Review> nolist(Review review);
	
	public int insert(Review review);
	
	public Review getReview(int review_no);
	
	public int update(Review review);
	
	public int delete(int review_no);
}

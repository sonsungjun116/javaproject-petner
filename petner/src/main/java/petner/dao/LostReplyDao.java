package petner.dao;


import java.util.List;


import petner.model.Lost;
import petner.model.LostReply;

public interface LostReplyDao {

	public void insertreply(LostReply lostreply);
	
	List<LostReply> list(int lost_no);
	
	void update(LostReply lostreply);
	
	void delete(int lostreply_no);
	
	public void refEdit(LostReply lostreply) throws Exception;

	public void boardReplyOk(LostReply lostreply) throws Exception;
}

package petner.service;


import java.util.List;


import petner.model.Lost;
import petner.model.LostReply;

public interface LostReplyService {

	public void insertreply(LostReply lostreply);
	
	List<LostReply> list(int lost_no);
	
	void update(LostReply lostreply);
	
	void delete(int lostreply_no);
	
	public void rreply(LostReply lostreply) throws Exception;
}

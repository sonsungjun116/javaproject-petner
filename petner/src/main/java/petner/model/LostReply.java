package petner.model;

import java.util.Date;

public class LostReply {

	private int lostreply_no;
	private int lost_no;
	private String mem_id;
	private String lostreply_content;
	private Date lostreply_date;
	private int lostreply_seq;
	private int lostreply_lev;
	private int lostreply_ref;
	
	
	public int getLostreply_ref() {
		return lostreply_ref;
	}
	public void setLostreply_ref(int lostreply_ref) {
		this.lostreply_ref = lostreply_ref;
	}
	public int getLostreply_no() {
		return lostreply_no;
	}
	public void setLostreply_no(int lostreply_no) {
		this.lostreply_no = lostreply_no;
	}
	public int getLost_no() {
		return lost_no;
	}
	public void setLost_no(int lost_no) {
		this.lost_no = lost_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getLostreply_content() {
		return lostreply_content;
	}
	public void setLostreply_content(String lostreply_content) {
		this.lostreply_content = lostreply_content;
	}
	public Date getLostreply_date() {
		return lostreply_date;
	}
	public void setLostreply_date(Date lostreply_date) {
		this.lostreply_date = lostreply_date;
	}
	public int getLostreply_seq() {
		return lostreply_seq;
	}
	public void setLostreply_seq(int lostreply_seq) {
		this.lostreply_seq = lostreply_seq;
	}
	public int getLostreply_lev() {
		return lostreply_lev;
	}
	public void setLostreply_lev(int lostreply_lev) {
		this.lostreply_lev = lostreply_lev;
	}
	
	
	
}

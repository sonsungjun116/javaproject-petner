package petner.model;

import java.util.Date;

public class Tipreply {

	private int tipreply_no;
	private int tipreply_ref;
	private int tipreply_seq;
	private int tipreply_lev;
	private int tip_no;
	private String mem_id;
	
	public int getTipreply_ref() {
		return tipreply_ref;
	}
	public void setTipreply_ref(int tipreply_ref) {
		this.tipreply_ref = tipreply_ref;
	}
	private String tipreply_content;
	private Date tipreply_date;
	
	public int getTipreply_no() {
		return tipreply_no;
	}
	public void setTipreply_no(int tipreply_no) {
		this.tipreply_no = tipreply_no;
	}
	public int getTipreply_seq() {
		return tipreply_seq;
	}
	public void setTipreply_seq(int tip_seq) {
		this.tipreply_seq = tip_seq;
	}
	public int getTipreply_lev() {
		return tipreply_lev;
	}
	public void setTipreply_lev(int tip_lev) {
		this.tipreply_lev = tip_lev;
	}
	public int getTip_no() {
		return tip_no;
	}
	public void setTip_no(int tip_no) {
		this.tip_no = tip_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getTipreply_content() {
		return tipreply_content;
	}
	public void setTipreply_content(String tipreply_content) {
		this.tipreply_content = tipreply_content;
	}
	public Date getTipreply_date() {
		return tipreply_date;
	}
	public void setTipreply_date(Date tipreply_date) {
		this.tipreply_date = tipreply_date;
	}
	
	
}

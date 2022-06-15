package petner.model;

import java.sql.Date;

public class Lost {

	private int lost_no;
	private String lost_title;
	private int lost_readcount;
	private Date lost_date;
	private String lost_content;
	private String lost_file;
	private String mem_id;
	private String lost_del;
	private String lost_pwd;
	private double lat;
	private double lng;
		
		// page
		private int startRow;
		private int endRow;
		
		// 검색
		private String search;
		private String keyword;
		
		
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
		public int getStartRow() {
			return startRow;
		}
		public void setStartRow(int startRow) {
			this.startRow = startRow;
		}
		public int getEndRow() {
			return endRow;
		}
		public void setEndRow(int endRow) {
			this.endRow = endRow;
		}
		public String getSearch() {
			return search;
		}
		public void setSearch(String search) {
			this.search = search;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
	
	
	public int getLost_no() {
		return lost_no;
	}
	public void setLost_no(int lost_no) {
		this.lost_no = lost_no;
	}
	public String getLost_title() {
		return lost_title;
	}
	public void setLost_title(String lost_title) {
		this.lost_title = lost_title;
	}
	public int getLost_readcount() {
		return lost_readcount;
	}
	public void setLost_readcount(int lost_readcount) {
		this.lost_readcount = lost_readcount;
	}
	public Date getLost_date() {
		return lost_date;
	}
	public void setLost_date(Date lost_date) {
		this.lost_date = lost_date;
	}
	public String getLost_content() {
		return lost_content;
	}
	public void setLost_content(String lost_content) {
		this.lost_content = lost_content;
	}
	public String getLost_file() {
		return lost_file;
	}
	public void setLost_file(String lost_file) {
		this.lost_file = lost_file;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getLost_del() {
		return lost_del;
	}
	public void setLost_del(String lost_del) {
		this.lost_del = lost_del;
	}
	public String getLost_pwd() {
		return lost_pwd;
	}
	public void setLost_pwd(String lost_pwd) {
		this.lost_pwd = lost_pwd;
	}
	

}

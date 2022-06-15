package petner.model;

import java.util.Date;

public class Tipboard {
	private int tip_no;
	private String tip_title;
	private String mem_id;
	private int tip_readcount;
	private Date tip_regdate;
	private String tip_content;
	private String tip_file;
	private String tip_pet;

	private int startRow;
	private int endRow;

	// 검색
	private String search;
	private String keyword;

	public int getTip_no() {
		return tip_no;
	}

	public void setTip_no(int tip_no) {
		this.tip_no = tip_no;
	}

	public String getTip_title() {
		return tip_title;
	}

	public void setTip_title(String tip_title) {
		this.tip_title = tip_title;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getTip_readcount() {
		return tip_readcount;
	}

	public void setTip_readcount(int tip_readcount) {
		this.tip_readcount = tip_readcount;
	}

	public Date getTip_regdate() {
		return tip_regdate;
	}

	public void setTip_regdate(Date tip_regdate) {
		this.tip_regdate = tip_regdate;
	}

	public String getTip_content() {
		return tip_content;
	}

	public void setTip_content(String tip_content) {
		this.tip_content = tip_content;
	}

	public String getTip_file() {
		return tip_file;
	}

	public void setTip_file(String tip_file) {
		this.tip_file = tip_file;
	}

	public String getTip_pet() {
		return tip_pet;
	}

	public void setTip_pet(String tip_pet) {
		this.tip_pet = tip_pet;
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

}

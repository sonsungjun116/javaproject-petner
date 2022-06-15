package petner.model;

public class Seller {

	private int seller_no;
	private String seller_name;
	private String seller_post;
	private String seller_addr1;
	private String seller_addr2;
	private String seller_tel1;
	private String seller_tel2;
	private String seller_tel3;
	private String mem_id;
	private int seller_accept;

	// page
	private int startRow;
	private int endRow;
	
	// 검색
	private String search;
	private String keyword;
	
	

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

	public int getSeller_no() {
		return seller_no;
	}

	public void setSeller_no(int seller_no) {
		this.seller_no = seller_no;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_post() {
		return seller_post;
	}

	public void setSeller_post(String seller_post) {
		this.seller_post = seller_post;
	}

	public String getSeller_addr1() {
		return seller_addr1;
	}

	public void setSeller_addr1(String seller_addr1) {
		this.seller_addr1 = seller_addr1;
	}

	public String getSeller_addr2() {
		return seller_addr2;
	}

	public void setSeller_addr2(String seller_addr2) {
		this.seller_addr2 = seller_addr2;
	}

	public String getSeller_tel1() {
		return seller_tel1;
	}

	public void setSeller_tel1(String seller_tel1) {
		this.seller_tel1 = seller_tel1;
	}

	public String getSeller_tel2() {
		return seller_tel2;
	}

	public void setSeller_tel2(String seller_tel2) {
		this.seller_tel2 = seller_tel2;
	}

	public String getSeller_tel3() {
		return seller_tel3;
	}

	public void setSeller_tel3(String seller_tel3) {
		this.seller_tel3 = seller_tel3;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getSeller_accept() {
		return seller_accept;
	}

	public void setSeller_accept(int seller_accept) {
		this.seller_accept = seller_accept;
	}

	
}

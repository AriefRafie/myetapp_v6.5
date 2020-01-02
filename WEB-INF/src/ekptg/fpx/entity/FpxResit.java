package ekptg.fpx.entity;

import java.io.Serializable;

public class FpxResit implements Serializable {
	private int id;
	private String year;
	private int currentNo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getCurrentNo() {
		return currentNo;
	}
	public void setCurrentNo(int currentNo) {
		this.currentNo = currentNo;
	}
	
	
}

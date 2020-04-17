package ekptg.fpx;

import java.io.Serializable;

public class FpxAnalaticData implements Serializable {
	private int totalSuccess;
	private int totalFail;
	private double totalColletion;
	public int getTotalSuccess() {
		return totalSuccess;
	}
	public void setTotalSuccess(int totalSuccess) {
		this.totalSuccess = totalSuccess;
	}
	public int getTotalFail() {
		return totalFail;
	}
	public void setTotalFail(int totalFail) {
		this.totalFail = totalFail;
	}
	public double getTotalColletion() {
		return totalColletion;
	}
	public void setTotalColletion(double totalColletion) {
		this.totalColletion = totalColletion;
	}
	
	public int getTotalTransaction(){
		return getTotalFail() + getTotalSuccess();
	}
}

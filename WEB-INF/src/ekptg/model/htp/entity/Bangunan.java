package ekptg.model.htp.entity;

import java.io.Serializable;

import ekptg.model.htp.HakmilikUrusan;

public class Bangunan implements Serializable {
	private long idBangunan;
	private String noPetak;
	private String noBangunan;
	private String noTingkat;
	private HakmilikUrusan hakmilikUrusan;
	public long getIdBangunan() {
		return idBangunan;
	}
	public void setIdBangunan(long idBangunan) {
		this.idBangunan = idBangunan;
	}
	public void setIdBangunan(String idBangunan) {
		if(idBangunan.equals(""))
			idBangunan ="0";
		this.idBangunan = Long.parseLong(idBangunan);
	}
	public String getNoPetak() {
		return noPetak;
	}
	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}
	public String getNoBangunan() {
		return noBangunan;
	}
	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}
	public String getNoTingkat() {
		return noTingkat;
	}
	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}
	public HakmilikUrusan getHakmilikUrusan() {
		return hakmilikUrusan;
	}
	public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
		this.hakmilikUrusan = hakmilikUrusan;
	}
	
	

}

package ekptg.model.htp.entity;

public class HakmilikAgensi {
	private long idHakmilik;
	private long idHakmilikAgensi;
	private long idKementerian;
	private long idAgensi;
	private long idMasuk;
	private long idKemaskini;
	private String status;
	private String luas;
	private String luasBersamaan;
	private int idLuas;

	public String getLuas() {
		return luas;
	}
	public void setLuas(String luas) {
		this.luas = luas;
	}
	
	public String getLuasBersamaan() {
		return luasBersamaan;
	}
	public void setLuasBersamaan(String luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}

	public int getIdLuas() {
		return idLuas;
	}
	public void setIdLuas(int idLuas) {
		this.idLuas = idLuas;
	}

	public long getIdHakmilikAgensi() {
		return idHakmilikAgensi;
	}
	public void setIdHakmilikAgensi(long idHakmilikAgensi) {
		this.idHakmilikAgensi = idHakmilikAgensi;
	}
	
	public long getIdKementerian() {
		return idKementerian;
	}
	public void setIdKementerian(long idKementerian) {
		this.idKementerian = idKementerian;
	}
	
	public long getIdAgensi() {
		return idAgensi;
	}
	public void setIdAgensi(long idAgensi) {
		this.idAgensi = idAgensi;
	}
	
	public long getIdHakmilik() {
		return idHakmilik;
	}
	public void setIdHakmilik(long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(long idMasuk) {
		this.idMasuk = idMasuk;
	}
	
	public long getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	
}

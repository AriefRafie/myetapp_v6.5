package ekptg.view.admin.utils;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 */

public class Seksyen {

	private String id_seksyen;
	private String kod_seksyen;
	private String nama_seksyen;

	public String getId_seksyen() {
		return id_seksyen;
	}

	public void setId_seksyen(String id_seksyen) {
		this.id_seksyen = id_seksyen;
	}

	public String getKod_seksyen() {
		return kod_seksyen;
	}

	public void setKod_seksyen(String kod_seksyen) {
		this.kod_seksyen = kod_seksyen;
	}

	public String getNama_seksyen() {
		return nama_seksyen;
	}

	public void setNama_seksyen(String nama_seksyen) {
		this.nama_seksyen = nama_seksyen;
	}
	
	public String getFullListDropDown() {
		return kod_seksyen + " - " + nama_seksyen;
	}

}

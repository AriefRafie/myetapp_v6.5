package ekptg.view.admin.utils;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 */

public class Jawatan {

	private String id_jawatan;
	private String kod_jawatan;
	private String keterangan;

	public String getId_jawatan() {
		return id_jawatan;
	}

	public void setId_jawatan(String id_jawatan) {
		this.id_jawatan = id_jawatan;
	}

	public String getKod_jawatan() {
		return kod_jawatan;
	}

	public void setKod_jawatan(String kod_jawatan) {
		this.kod_jawatan = kod_jawatan;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getFullListDropDown() {
		return kod_jawatan + " - " + keterangan;
	}

}

package ekptg.view.admin.utils;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 */

public class Pejabat {

	private String id_pejabat;
	private String id_negeri;
	private String kod_pejabat;
	private String nama_pejabat;

	public String getId_pejabat() {
		return id_pejabat;
	}

	public void setId_pejabat(String id_pejabat) {
		this.id_pejabat = id_pejabat;
	}

	public String getId_negeri() {
		return id_negeri;
	}

	public void setId_negeri(String id_negeri) {
		this.id_negeri = id_negeri;
	}

	public String getKod_pejabat() {
		return kod_pejabat;
	}

	public void setKod_pejabat(String kod_pejabat) {
		this.kod_pejabat = kod_pejabat;
	}

	public String getNama_pejabat() {
		return nama_pejabat;
	}

	public void setNama_pejabat(String nama_pejabat) {
		this.nama_pejabat = nama_pejabat;
	}
	
	public String getFullListDropDown(){
		return id_pejabat + " - " + nama_pejabat;
	}

}

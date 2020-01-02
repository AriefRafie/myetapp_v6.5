package ekptg.view.admin.utils;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 *
 */

public class Negeri {

	private String id_negeri;
	private String kod_negeri;
	private String nama_negeri;

	public String getId_negeri() {
		return id_negeri;
	}

	public void setId_negeri(String id_negeri) {
		this.id_negeri = id_negeri;
	}

	public String getKod_negeri() {
		return kod_negeri;
	}

	public void setKod_negeri(String kod_negeri) {
		this.kod_negeri = kod_negeri;
	}

	public String getNama_negeri() {
		return nama_negeri;
	}

	public void setNama_negeri(String nama_negeri) {
		this.nama_negeri = nama_negeri;
	}
	
	public String getFullListDropDown(){
		return kod_negeri + " - " + nama_negeri;
	}

}

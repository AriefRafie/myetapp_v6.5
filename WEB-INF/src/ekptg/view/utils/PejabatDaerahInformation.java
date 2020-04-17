package ekptg.view.utils;
/**
 * 
 * @author Mohd Nazrul
 *
 */
public class PejabatDaerahInformation {

	private String id_jenispejabat;
	private String id_pejabat;
	private String id_negeri;
	private String id_daerah;
	private String nama_daerah;

	public PejabatDaerahInformation() {
	}

	public PejabatDaerahInformation(String id_jenispejabat, String id_pejabat,
			String id_negeri, String id_daerah, String nama_daerah) {
		this.id_jenispejabat = id_jenispejabat;
		this.id_pejabat = id_pejabat;
		this.id_negeri = id_negeri;
		this.id_daerah = id_daerah;
		this.nama_daerah = nama_daerah;
	}

	public String getId_jenispejabat() {
		return id_jenispejabat;
	}

	public void setId_jenispejabat(String idJenispejabat) {
		id_jenispejabat = idJenispejabat;
	}

	public String getId_pejabat() {
		return id_pejabat;
	}

	public void setId_pejabat(String idPejabat) {
		id_pejabat = idPejabat;
	}

	public String getId_negeri() {
		return id_negeri;
	}

	public void setId_negeri(String idNegeri) {
		id_negeri = idNegeri;
	}

	public String getId_daerah() {
		return id_daerah;
	}

	public void setId_daerah(String idDaerah) {
		id_daerah = idDaerah;
	}

	public String getNama_daerah() {
		return nama_daerah;
	}

	public void setNama_daerah(String namaDaerah) {
		nama_daerah = namaDaerah;
	}

}

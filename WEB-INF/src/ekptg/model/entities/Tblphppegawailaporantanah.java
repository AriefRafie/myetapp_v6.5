package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppegawailaporantanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppegawailaporantanah extends
		AbstractTblphppegawailaporantanah implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppegawailaporantanah() {
	}

	/** minimal constructor */
	public Tblphppegawailaporantanah(Long idPegawailaporantanah) {
		super(idPegawailaporantanah);
	}

	/** full constructor */
	public Tblphppegawailaporantanah(Long idPegawailaporantanah,
			Tblphplawatantapak tblphplawatantapak, String namaPegawai,
			String jawatan, Long idNegeripegawai, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idPegawailaporantanah, tblphplawatantapak, namaPegawai, jawatan,
				idNegeripegawai, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}

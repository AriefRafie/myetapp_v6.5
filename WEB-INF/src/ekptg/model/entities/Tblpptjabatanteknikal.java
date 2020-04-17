package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptjabatanteknikal entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptjabatanteknikal extends AbstractTblpptjabatanteknikal
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptjabatanteknikal() {
	}

	/** full constructor */
	public Tblpptjabatanteknikal(String kodJabatanTeknikal,String namaJabatan, String alamat1,
			String alamat2, String alamat3, String poskod, Long idNegeri,
			String noTel, String noFax, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(kodJabatanTeknikal,namaJabatan, alamat1, alamat2, alamat3, poskod, idNegeri, noTel,
				noFax, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}


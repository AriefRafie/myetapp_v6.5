package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblhtpperingkatbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpperingkatbayaran extends AbstractTblhtpperingkatbayaran
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpperingkatbayaran() {
	}

	/** minimal constructor */
	public Tblhtpperingkatbayaran(Long idPeringkatbayaran,
			Tblhtppermohonan tblhtppermohonan, String peringkatBayaran,
			Long idNegeri) {
		super(idPeringkatbayaran, tblhtppermohonan, peringkatBayaran, idNegeri);
	}

	/** full constructor */
	public Tblhtpperingkatbayaran(Long idPeringkatbayaran,
			Tblhtppermohonan tblhtppermohonan, String peringkatBayaran,
			String tahunCukai, Long idPegawai, Long idNegeri, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblhtpbayarancukais, Set tblhtpcukaiutamas) {
		super(idPeringkatbayaran, tblhtppermohonan, peringkatBayaran,
				tahunCukai, idPegawai, idNegeri, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblhtpbayarancukais,
				tblhtpcukaiutamas);
	}

}

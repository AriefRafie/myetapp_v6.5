package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtpbayaranId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtpbayaranId extends AbstractTblhtpbayaranId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtpbayaranId() {
	}

	/** minimal constructor */
	public TblhtpbayaranId(Long idBayaran, Tblhtppermohonan tblhtppermohonan,
			Long idCarabayar, Long idMasuk) {
		super(idBayaran, tblhtppermohonan, idCarabayar, idMasuk);
	}

	/** full constructor */
	public TblhtpbayaranId(Long idBayaran, Tblhtppermohonan tblhtppermohonan,
			String noResit, Date tarikhResit, Double amaunAtasResit,
			Date tarikhHantarResit, String noBayaran, Double jumlahBayaran,
			Date tarikhTerima, String noBaucer, Date tarikhBaucer,
			Double amaunAtasBaucer, String tujuanBayaran, String noUrusan,
			Date tarikhCek, String namaBank, Date tarikhLuput,
			String statusBayar, Date tkhMula, Long idCarabayar, Long idMasuk,
			Date tarikhMasuk, String idKemaskini, Date tarikhKemaskini) {
		super(idBayaran, tblhtppermohonan, noResit, tarikhResit,
				amaunAtasResit, tarikhHantarResit, noBayaran, jumlahBayaran,
				tarikhTerima, noBaucer, tarikhBaucer, amaunAtasBaucer,
				tujuanBayaran, noUrusan, tarikhCek, namaBank, tarikhLuput,
				statusBayar, tkhMula, idCarabayar, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}

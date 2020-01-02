package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppknotisobmst entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppknotisobmst extends AbstractTblppknotisobmst implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppknotisobmst() {
	}

	/** minimal constructor */
	public Tblppknotisobmst(Long idNotisobmst) {
		super(idNotisobmst);
	}

	/** full constructor */
	public Tblppknotisobmst(Long idNotisobmst, Long bil, Date tarikhSerahan,
			String statusSerah, String jenisSerah, String statusAkuanSumpah,
			String catatan, String namaPenghantarNotis, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppknotisperbicaraans, Set tblppknotisobdtls,
			Set tblppknotiskolaterals) {
		super(idNotisobmst, bil, tarikhSerahan, statusSerah, jenisSerah,
				statusAkuanSumpah, catatan, namaPenghantarNotis, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblppknotisperbicaraans, tblppknotisobdtls,
				tblppknotiskolaterals);
	}

}

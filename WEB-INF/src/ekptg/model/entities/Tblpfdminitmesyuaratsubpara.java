package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfdminitmesyuaratsubpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfdminitmesyuaratsubpara extends
		AbstractTblpfdminitmesyuaratsubpara implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdminitmesyuaratsubpara() {
	}

	/** minimal constructor */
	public Tblpfdminitmesyuaratsubpara(Long idMinitmesyuaratsubpara) {
		super(idMinitmesyuaratsubpara);
	}

	/** full constructor */
	public Tblpfdminitmesyuaratsubpara(Long idMinitmesyuaratsubpara,
			Long idMinitmesyuaratpara, String subPara, String maklumbalas,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idMinitmesyuaratsubpara, idMinitmesyuaratpara, subPara,
				maklumbalas, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}

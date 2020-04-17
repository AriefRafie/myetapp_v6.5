package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtminitmesyuaratpara entity. @author MyEclipse Persistence Tools
 */
public class Tblpdtminitmesyuaratpara extends AbstractTblpdtminitmesyuaratpara
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtminitmesyuaratpara() {
	}

	/** full constructor */
	public Tblpdtminitmesyuaratpara(Long idMinitmesyuarat,
			String para, String idMasuk, Date tarikhMasuk, String idKemaskini,
			Date tarikhKemaskini, Long idDb, Long idMesyuarat,String pihakBertindak,String maklumbalas,Set tblpdtminitmesyuarattindakans) {
		super(idMinitmesyuarat, para, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb,idMesyuarat, pihakBertindak,
                                maklumbalas,tblpdtminitmesyuarattindakans);
	}

}

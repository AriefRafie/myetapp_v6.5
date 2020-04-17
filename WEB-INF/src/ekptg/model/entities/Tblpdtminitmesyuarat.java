package ekptg.model.entities;


import java.util.Date;
import java.util.Set;

/**
 * Tblpdtminitmesyuarat entity. @author MyEclipse Persistence Tools
 */
public class Tblpdtminitmesyuarat extends AbstractTblpdtminitmesyuarat
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtminitmesyuarat() {
	}

	/** full constructor */
	public Tblpdtminitmesyuarat(Long idAgendamesyuarat,
			String tajukMinit, String namaPegawai, String catatan,
			String idMasuk, Date tarikhMasuk, String idKemaskini,
			Date tarikhKemaskini,Long idDb, Long idMesyuarat,Set tblpdtminitmesyuaratparas) {
		super(idAgendamesyuarat, tajukMinit, namaPegawai, catatan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,idDb,idMesyuarat,
				tblpdtminitmesyuaratparas);
	}

}

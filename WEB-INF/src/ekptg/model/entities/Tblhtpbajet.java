package ekptg.model.entities;

import java.util.Set;

/**
 * Tblhtpbajet entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpbajet extends AbstractTblhtpbajet implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpbajet() {
	}

	/** minimal constructor */
	public Tblhtpbajet(Long idBajet, Long idNegeri) {
		super(idBajet, idNegeri);
	}

	/** full constructor */ 

	public Tblhtpbajet(Long idBajet, Double jumlah, Long tahun, Long idNegeri,Set tblhtpbajetgunas) {
		super(idBajet, jumlah, tahun, idNegeri,tblhtpbajetgunas);
	}

}

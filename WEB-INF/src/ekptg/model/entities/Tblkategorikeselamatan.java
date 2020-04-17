package ekptg.model.entities;

/**
 * Tblkategorikeselamatan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblkategorikeselamatan extends AbstractTblkategorikeselamatan
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblkategorikeselamatan() {
	}

	/** full constructor */
	public Tblkategorikeselamatan(String kodKategoriKeselamatan,
			String kategoryKeselamatan, String keterangan) {
		super(kodKategoriKeselamatan, kategoryKeselamatan, keterangan);
	}

}

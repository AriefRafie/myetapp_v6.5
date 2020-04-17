package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkhubunganob entity provides the base persistence definition of
 * the Tblppkhubunganob entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkhubunganob implements java.io.Serializable {

	// Fields

	private Long idHubunganob;
	//private Tblppkob tblppkob;
        private Long idOb;
	private Long idParentob;
	private Long idSaudara;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        

	// Constructors

	/** default constructor */
	public AbstractTblppkhubunganob() {
	}

	/** minimal constructor */
	public AbstractTblppkhubunganob(Long idHubunganob) {
		this.idHubunganob = idHubunganob;
		//this.tblppkob = tblppkob;
	}

	/** full constructor */
	public AbstractTblppkhubunganob(Long idHubunganob,Long idOb,
			Long idParentob, Long idSaudara, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idHubunganob = idHubunganob;
	//	this.tblppkob = tblppkob;
                this.idOb = idOb;
		this.idParentob = idParentob;
		this.idSaudara = idSaudara;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdHubunganob() {
		return this.idHubunganob;
	}

	public void setIdHubunganob(Long idHubunganob) {
		this.idHubunganob = idHubunganob;
	}

/*	public Tblppkob getTblppkob() {
		return this.tblppkob;
	}

	public void setTblppkob(Tblppkob tblppkob) {
		this.tblppkob = tblppkob;
	}
*/
	public Long getIdParentob() {
		return this.idParentob;
	}

	public void setIdParentob(Long idParentob) {
		this.idParentob = idParentob;
	}

	public Long getIdSaudara() {
		return this.idSaudara;
	}

	public void setIdSaudara(Long idSaudara) {
		this.idSaudara = idSaudara;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

    public void setIdOb(Long idOb) {
        this.idOb = idOb;
    }

    public Long getIdOb() {
        return idOb;
    }
}

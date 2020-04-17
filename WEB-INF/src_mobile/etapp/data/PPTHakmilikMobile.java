package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPTHAKMILIK")
public class PPTHakmilikMobile {

	@Id @Column(name="ID_HAKMILIK")
	private long id;
	@Column(name="NO_HAKMILIK")
	private String noHakmilik;
	
	@ManyToOne
	@JoinColumn(name="ID_PERMOHONAN")
	private PPTPermohonanMobile permohonan;

	@Column(name="NO_LOT")
	private String noLot;
	@Column(name="NO_PT")
	private String noPT;

	@ManyToOne
	@JoinColumn(name="ID_MUKIM")
	private RujMukimMobile mukim;
	
	@Column(name="FLAG_PENARIKAN_KESELURUHAN")
	private String fTarikKeseluruhan;
	@Column(name="FLAG_PEMBATALAN_KESELURUHAN")
	private String fBatalKeseluruhan;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public PPTPermohonanMobile getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(PPTPermohonanMobile permohonan) {
		this.permohonan = permohonan;
	}
	public String getNoLot() {
		return noLot;
	}
	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}
	public String getNoPT() {
		return noPT;
	}
	public void setNoPT(String noPT) {
		this.noPT = noPT;
	}
	public RujMukimMobile getMukim() {
		return mukim;
	}
	public void setMukim(RujMukimMobile mukim) {
		this.mukim = mukim;
	}
	public String getFTarikKeseluruhan() {
		return fTarikKeseluruhan;
	}
	public void setFTarikKeseluruhan(String tarikKeseluruhan) {
		fTarikKeseluruhan = tarikKeseluruhan;
	}
	public String getFBatalKeseluruhan() {
		return fBatalKeseluruhan;
	}
	public void setFBatalKeseluruhan(String batalKeseluruhan) {
		fBatalKeseluruhan = batalKeseluruhan;
	}
}
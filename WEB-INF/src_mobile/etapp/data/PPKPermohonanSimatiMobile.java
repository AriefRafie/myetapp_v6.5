package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPKPERMOHONANSIMATI")
public class PPKPermohonanSimatiMobile {
	
	@Id @Column(name="ID_PERMOHONANSIMATI")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="ID_PERMOHONAN")
	private PPKPermohonanMobile permohonan;
	@ManyToOne
	@JoinColumn(name="ID_SIMATI")
	private PPKSimatiMobile simati;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PPKPermohonanMobile getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(PPKPermohonanMobile permohonan) {
		this.permohonan = permohonan;
	}
	public PPKSimatiMobile getSimati() {
		return simati;
	}
	public void setSimati(PPKSimatiMobile simati) {
		this.simati = simati;
	}
}
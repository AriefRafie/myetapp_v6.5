package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPTBORANGO")
public class PPTBorangOMobile {
	
	@Id @Column(name="ID_BORANGO")
	private long id;
	@Column(name="NO_BORANGO")
	private long noHakmilik;
	
	@ManyToOne
	@JoinColumn(name="ID_BANTAHAN")
	private PPTBantahanMobile bantahan;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(long noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public PPTBantahanMobile getBantahan() {
		return bantahan;
	}
	public void setBantahan(PPTBantahanMobile bantahan) {
		this.bantahan = bantahan;
	}
}
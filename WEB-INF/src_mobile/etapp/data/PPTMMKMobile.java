package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPTMMK")
public class PPTMMKMobile {
	
	@Id @Column(name="ID_MMK")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ID_PERMOHONAN")
	private PPTPermohonanMobile permohonan;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PPTPermohonanMobile getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(PPTPermohonanMobile permohonan) {
		this.permohonan = permohonan;
	}
}
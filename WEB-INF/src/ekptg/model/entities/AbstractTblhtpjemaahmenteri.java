package ekptg.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * AbstractTblhtpjemaahmenteri entity provides the base persistence definition
 * of the Tblhtpjemaahmenteri entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpjemaahmenteri implements
		java.io.Serializable {

	// Fields

	private Long idJemaahmenteri;
	private Tblhtppermohonan tblhtppermohonan;
	private Date tarikhHantarKsu;
	private Date tarikhTerimaKsu;
	private Date tarikhTerima;
	private Date tarikhHantarPemohon;
	private Date tarikhMemorandum;
	private Date tarikhKeputusan;
	private Date tarikhHantarDasar;
	private Date tarikhMsyrtJemaah;
	private Date tarikhKuasaPbdn;
	private String tarikhHantarKsuStr;
	private String tarikhTerimaKsuStr;
	private String tarikhTerimaStr;
	private String tarikhHantarPemohonStr;
	private String tarikhMemorandumStr;
	private String tarikhKeputusanStr;
	private String tarikhHantarDasarStr;
	private String tarikhMensyuaratJemaahStr;
	private String tarikhKuasaPbdnStr;
	private String noRujukanKptsn;
	private String ulasan;
	private String statusKeputusan;
	private String tindakanLanjut;
	private String noMemorandum;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLog = Logger.getLogger(AbstractTblhtpjemaahmenteri.class);

	// Constructors

	/** default constructor */
	public AbstractTblhtpjemaahmenteri() {
	}

	/** minimal constructor */
	public AbstractTblhtpjemaahmenteri(Long idJemaahmenteri,
			Tblhtppermohonan tblhtppermohonan) {
		this.idJemaahmenteri = idJemaahmenteri;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtpjemaahmenteri(Long idJemaahmenteri,
			Tblhtppermohonan tblhtppermohonan, Date tarikhHantarKsu,
			Date tarikhTerimaKsu, Date tarikhTerima, Date tarikhHantarPemohon,
			Date tarikhMemorandum, Date tarikhKeputusan, String noRujukanKptsn,
			String ulasan, String statusKeputusan, String tindakanLanjut,
			Date tarikhHantarDasar, String noMemorandum,
			Date tarikhMsyrtJemaah, Date tarikhKuasaPbdn, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idJemaahmenteri = idJemaahmenteri;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tarikhHantarKsu = tarikhHantarKsu;
		this.tarikhTerimaKsu = tarikhTerimaKsu;
		this.tarikhTerima = tarikhTerima;
		this.tarikhHantarPemohon = tarikhHantarPemohon;
		this.tarikhMemorandum = tarikhMemorandum;
		this.tarikhKeputusan = tarikhKeputusan;
		this.noRujukanKptsn = noRujukanKptsn;
		this.ulasan = ulasan;
		this.statusKeputusan = statusKeputusan;
		this.tindakanLanjut = tindakanLanjut;
		this.tarikhHantarDasar = tarikhHantarDasar;
		this.noMemorandum = noMemorandum;
		this.tarikhMsyrtJemaah = tarikhMsyrtJemaah;
		this.tarikhKuasaPbdn = tarikhKuasaPbdn;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors
	public Long getIdJemaahmenteri() {
		return this.idJemaahmenteri;
	}

	public void setIdJemaahmenteri(Long idJemaahmenteri) {
		this.idJemaahmenteri = idJemaahmenteri;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Date getTarikhHantarKsu() {
		return this.tarikhHantarKsu;
	}

	public void setTarikhHantarKsu(Date tarikhHantarKsu) {
		if(tarikhHantarKsu.equals(""))
			this.tarikhHantarKsu = new java.util.Date("01/01/1900");
		else
			this.tarikhHantarKsu = tarikhHantarKsu;
	}
	
	public String getTarikhHantarKsuFormat() {
		if(tarikhHantarKsuStr.equals("01/01/1900"))
			return "";
		else
			return tarikhHantarKsuStr;
	}	

	public void setTarikhHantarKsuStr(String tarikhHantarKsuStr) {
		this.tarikhHantarKsuStr = tarikhHantarKsuStr;
	}
	
	public Date getTarikhTerimaKsu() {
		return this.tarikhTerimaKsu;
	}

	public void setTarikhTerimaKsu(Date tarikhTerimaKsu) {
		this.tarikhTerimaKsu = tarikhTerimaKsu;
	}

	public String getTarikhTerimaKsuFormat() {
		if(tarikhTerimaKsuStr.equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhTerimaKsuStr);
	}	

	public void setTarikhTerimaKsuStr(String tarikhTerimaKsuStr) {
		this.tarikhTerimaKsuStr = tarikhTerimaKsuStr;
	}
		
	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public String getTarikhTerimaFormat() {
		if(tarikhTerimaStr.equals("01/01/1900"))
			return "";
		else
			return tarikhTerimaStr;
	}	

	public void setTarikhTerimaStr(String tarikhTerimaStr) {
		this.tarikhTerimaStr = tarikhTerimaStr;
	}
	
	public Date getTarikhHantarPemohon() {
		return this.tarikhHantarPemohon;
	}

	public void setTarikhHantarPemohon(Date tarikhHantarPemohon) {
		this.tarikhHantarPemohon = tarikhHantarPemohon;
	}

	public String getTarikhHantarPemohonFormat() {
		if(tarikhHantarPemohonStr.equals("01/01/1900"))
			return "";
		else
			return tarikhHantarPemohonStr;
	}	

	public void setTarikhHantarPemohonStr(String tarikhHantarPemohonStr) {
		this.tarikhHantarPemohonStr = tarikhHantarPemohonStr;
	}
		
	public Date getTarikhMemorandum() {
		return this.tarikhMemorandum;
	}

	public void setTarikhMemorandum(Date tarikhMemorandum) {
		this.tarikhMemorandum = tarikhMemorandum;
	}
	
	public String getTarikhMemorandumFormat() {
		if(tarikhMemorandumStr.equals("01/01/1900"))
			return "";
		else
			return tarikhMemorandumStr;
	}	

	public void setTarikhMemorandumStr(String tarikhMemorandumStr) {
		this.tarikhMemorandumStr = tarikhMemorandumStr;
	}
	
	public Date getTarikhKeputusan() {
		return this.tarikhKeputusan;
	}

	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}
	
	public String getTarikhKeputusanFormat() {
		if(tarikhKeputusanStr.equals("01/01/1900"))
			return "";
		else
			return tarikhKeputusanStr;
	}	

	public void setTarikhKeputusanStr(String tarikhKeputusanStr) {
		this.tarikhKeputusanStr = tarikhKeputusanStr;
	}
	
	public String getNoRujukanKptsn() {
		return this.noRujukanKptsn;
	}

	public void setNoRujukanKptsn(String noRujukanKptsn) {
		this.noRujukanKptsn = noRujukanKptsn;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getStatusKeputusan() {
		return this.statusKeputusan;
	}

	public void setStatusKeputusan(String statusKeputusan) {
		this.statusKeputusan = statusKeputusan;
	}

	public String getTindakanLanjut() {
		return this.tindakanLanjut;
	}

	public void setTindakanLanjut(String tindakanLanjut) {
		this.tindakanLanjut = tindakanLanjut;
	}

	public Date getTarikhHantarDasar() {
		return this.tarikhHantarDasar;
	}

	public void setTarikhHantarDasar(Date tarikhHantarDasar) {
		this.tarikhHantarDasar = tarikhHantarDasar;
	}
	
	public String getTarikhHantarDasarFormat() {
		if(tarikhHantarDasarStr.equals("01/01/1900"))
			return "";
		else
			return tarikhHantarDasarStr;
	}	

	public void setTarikhHantarDasarStr(String tarikhHantarDasarStr) {
		this.tarikhHantarDasarStr = tarikhHantarDasarStr;
	}
	
	public String getNoMemorandum() {
		return this.noMemorandum;
	}

	public void setNoMemorandum(String noMemorandum) {
		this.noMemorandum = noMemorandum;
	}

	public Date getTarikhMsyrtJemaah() {
		return this.tarikhMsyrtJemaah;
	}
	
	public String getTarikhMsyrtJemaahFormat() {
		if(sdf.format(tarikhMsyrtJemaah).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhMsyrtJemaah);
	}	

	public void setTarikhMsyrtJemaah(Date tarikhMsyrtJemaah) {
		this.tarikhMsyrtJemaah = tarikhMsyrtJemaah;
		//myLog.info(this.tarikhMsyrtJemaah);
	}
	
	public String getTarikhMesyuaratJemaahFormat() {
		if(tarikhMensyuaratJemaahStr.equals("01/01/1900"))
			return "";
		else
			return this.tarikhMensyuaratJemaahStr;
	}
	
	public void setTarikhMesyuaratJemaahStr(String tarikhMensyuaratJemaahStr) {
		this.tarikhMensyuaratJemaahStr = tarikhMensyuaratJemaahStr;
	}

	public Date getTarikhKuasaPbdn() {
		return this.tarikhKuasaPbdn;
	}

	public void setTarikhKuasaPbdn(Date tarikhKuasaPbdn) {
		this.tarikhKuasaPbdn = tarikhKuasaPbdn;
	}

	public String getTarikhKuasaPbdnFormat() {
		if(tarikhKuasaPbdnStr.equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhKuasaPbdnStr);
	}	

	public void setTarikhKuasaPbdnStr(String tarikhMsyrtJemaah) {
		this.tarikhKuasaPbdnStr = tarikhKuasaPbdnStr;
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

}
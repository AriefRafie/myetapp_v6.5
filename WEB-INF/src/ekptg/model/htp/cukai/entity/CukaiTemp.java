package ekptg.model.htp.cukai.entity;

import java.io.Serializable;
import java.util.Date;

import ekptg.model.entities.Tblrujdaerah;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.entities.Tblrujnegeri;


public class CukaiTemp implements Serializable {
	public static final String TABLENAME="TBLHTPCUKAITEMP";
	private long idCukaiTemp;
	private long idPermohonan;
	private double tunggakkan;
	private String tahun;
	private double cukailain;
	private double lebihan;
	private double cukaiKenaBayar;
	private double cukaiPerluBayar;
	private double cukaiDiBayar;
	private double denda;
	private double bayaranLain;
	private double cukaiTaliAir;
	private double cukaiParit;
	private double pengurangan;
	private double pengecualian;
	private String noResit;
	private String tarikhResit;
	private long idDaerah;
	private long idNegeri;
	private long idMukim;
	private long idMasuk;
	private long idJenisHakmilik;
	private String noHakmilik;
	private long idLot;
	private String noLot;
	private String kegunaanTanah;
	private String tarikhMasuk;
	private String idKemaskini;
	private String tarikhKemaskini;
	private double totalcukai;
		
	private Tblrujnegeri rujNegeri;
	private Tblrujdaerah rujDaerah;
	private Tblrujlot rujLot;
	private Tblrujjenishakmilik rujJenisHakmilik;
	private Tblrujmukim rujMukim;
	
	public long getIdCukaiTemp() {
		return idCukaiTemp;
	}
	public void setIdCukaiTemp(long idCukaiTemp) {
		this.idCukaiTemp = idCukaiTemp;
	}
	public long getIdPermohonan() {
		return idPermohonan;
	}
	public void setIdPermohonan(long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}
	public double getTunggakkan() {
		return tunggakkan;
	}
	public void setTunggakkan(double tunggakkan) {
		this.tunggakkan = tunggakkan;
	}
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
	}
	public double getCukaiKenaBayar() {
		return cukaiKenaBayar;
	}
	public void setCukaiKenaBayar(double cukaiKenaBayar) {
		this.cukaiKenaBayar = cukaiKenaBayar;
	}
	public double getCukaiPerluBayar() {
		double cukaiPerluBayar = getDenda()+getCukaiKenaBayar()+getTunggakkan()+getCukailain()-getLebihan();
		return cukaiPerluBayar;
	}
	public void setCukaiPerluBayar(double cukaiPerluBayar) {
		this.cukaiPerluBayar = cukaiPerluBayar;
	}
	public double getCukaiDiBayar() {
		return cukaiDiBayar;
	}
	public void setCukaiDiBayar(double cukaiDiBayar) {
		this.cukaiDiBayar = cukaiDiBayar;
	}
	public double getDenda() {
		return denda;
	}
	public void setDenda(double denda) {
		this.denda = denda;
	}
	public double getBayaranLain() {
		return bayaranLain;
	}
	public void setBayaranLain(double bayaranLain) {
		this.bayaranLain = bayaranLain;
	}
	public double getCukaiTaliAir() {
		return cukaiTaliAir;
	}
	public void setCukaiTaliAir(double cukaiTaliAir) {
		this.cukaiTaliAir = cukaiTaliAir;
	}
	public double getCukaiParit() {
		return cukaiParit;
	}
	public void setCukaiParit(double cukaiParit) {
		this.cukaiParit = cukaiParit;
	}
	public double getPengurangan() {
		return pengurangan;
	}
	public void setPengurangan(double pengurangan) {
		this.pengurangan = pengurangan;
	}
	public double getPengecualian() {
		return pengecualian;
	}
	public void setPengecualian(double pengecualian) {
		this.pengecualian = pengecualian;
	}
	public String getNoResit() {
		return noResit;
	}
	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}
	public String getTarikhResit() {
		return tarikhResit;
	}	
	public Date getTarikhResitDate() {
		return new Date(tarikhResit);
	}	
	public void setTarikhResit(String tarikhResit) {
		this.tarikhResit = tarikhResit;
	}
	public long getIdDaerah() {
		return idDaerah;
	}
	public void setIdDaerah(long idDaerah) {
		this.idDaerah = idDaerah;
	}
	public long getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(long idNegeri) {
		this.idNegeri = idNegeri;
	}
	public long getIdMukim() {
		return idMukim;
	}
	public void setIdMukim(long idMukim) {
		this.idMukim = idMukim;
	}
	
	public long getIdJenisHakmilik() {
		return idJenisHakmilik;
	}
	public void setIdJenisHakmilik(long idJenisHakmilik) {
		this.idJenisHakmilik = idJenisHakmilik;
	}
	public String getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public long getIdLot() {
		return idLot;
	}
	public void setIdLot(long idLot) {
		this.idLot = idLot;
	}
	public String getNoLot() {
		return noLot;
	}
	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}
	public String getKegunaanTanah() {
		return kegunaanTanah;
	}
	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
	}
	public long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public String getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(String tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public String getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(String idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public Tblrujnegeri getRujNegeri() {
		return rujNegeri;
	}
	public void setRujNegeri(Tblrujnegeri rujNegeri) {
		this.rujNegeri = rujNegeri;
	}
	public Tblrujdaerah getRujDaerah() {
		return rujDaerah;
	}
	public void setRujDaerah(Tblrujdaerah rujDaerah) {
		this.rujDaerah = rujDaerah;
	}
	public Tblrujlot getRujLot() {
		return rujLot;
	}
	public void setRujLot(Tblrujlot rujLot) {
		this.rujLot = rujLot;
	}
	public Tblrujjenishakmilik getRujJenisHakmilik() {
		return rujJenisHakmilik;
	}
	public void setRujJenisHakmilik(Tblrujjenishakmilik rujJenisHakmilik) {
		this.rujJenisHakmilik = rujJenisHakmilik;
	}
	public Tblrujmukim getRujMukim() {
		return rujMukim;
	}
	public void setRujMukim(Tblrujmukim rujMukim) {
		this.rujMukim = rujMukim;
	}
	public double getTotalcukai() {
		return totalcukai;
	}
	public void setTotalcukai(double totalcukai) {
		this.totalcukai = totalcukai;
	}
	public double getCukailain() {
		return cukailain;
	}
	public void setCukailain(double cukailain) {
		this.cukailain = cukailain;
	}
	public double getLebihan() {
		return lebihan;
	}
	public void setLebihan(double lebihan) {
		this.lebihan = lebihan;
	}	
}

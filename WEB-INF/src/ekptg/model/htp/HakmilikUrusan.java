/*
 * entity hakmilikurusan to cater pajakan kecil
 * 
 * */

package ekptg.model.htp;

import java.io.Serializable;
import java.util.Vector;

import ekptg.model.htp.entity.Permohonan;

public class HakmilikUrusan implements Serializable {
	private String idhakmilikurusan;
	private String nolot;
	private String nohakmilik;
	private String namanegeri;
	private String namadaerah;
	private String namamukim;
	private String keterangan;
	private String kodjenishakmilik;
	Vector<PihakBerkepentingan> pihaks = new Vector<PihakBerkepentingan>();
	private String idNegeri;
	private String idDaerah;
	private String idMukim;
	private String idSeksyen;
	private String idHakmilik;
	private String idLot;
	private String idKemaskini;
	private String noBangunan;
	private String noPetak;
	private String noTingkat;
	private Permohonan permohonan;
	private String idJenisRizab;
	private String idKategoriTanah;
	private String idSubKetegoriTanah;
	private String luas;
	private String luasAsal;
	private String idLuas;
	private String idLuasBersamaan;
	private String luasBersamaan;
	private String noPlan;
	private String peganganHakmilik;
	private String tarikhMula;
	private String tarikhLuput;
	private String keteranganLot;
	private String luasKeterangan;
	private String jenisKeterangan;
	private String statusTanah ;
	private String tempohTanah ;
	private String tempohBakiTanah ;


	public String getJenisKeterangan() {
		return jenisKeterangan;
	}


	public void setJenisKeterangan(String jenisKeterangan) {
		this.jenisKeterangan = jenisKeterangan;
	}


	public String getLuasKeterangan() {
		return luasKeterangan;
	}


	public void setLuasKeterangan(String luasKeterangan) {
		this.luasKeterangan = luasKeterangan;
	}


	public String getLuas() {
		return luas;
	}
	public void setLuas(String luas) {
		this.luas = luas;
	}

	public String getLuasAsal() {
		return luasAsal;
	}
	public void setLuasAsal(String luasAsal) {
		this.luasAsal = luasAsal;
	}
	
	public String getLuasBersamaan() {
		return luasBersamaan;
	}

	public void setLuasBersamaan(String luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}

	public String getNolot() {
		return nolot;
	}


	public void setNolot(String nolot) {
		this.nolot = nolot;
	}


	public String getNohakmilik() {
		return nohakmilik;
	}


	public void setNohakmilik(String nohakmilik) {
		this.nohakmilik = nohakmilik;
	}


	public String getNamanegeri() {
		return namanegeri;
	}


	public void setNamanegeri(String namanegeri) {
		this.namanegeri = namanegeri;
	}


	public String getNamadaerah() {
		return namadaerah;
	}


	public void setNamadaerah(String namadaerah) {
		this.namadaerah = namadaerah;
	}


	public String getNamamukim() {
		return namamukim;
	}


	public void setNamamukim(String namamukim) {
		this.namamukim = namamukim;
	}


	public String getIdhakmilikurusan() {
		return idhakmilikurusan;
	}


	public void setIdhakmilikurusan(String idhakmilikurusan) {
		this.idhakmilikurusan = idhakmilikurusan;
	}


	public String getKeterangan() {
		return keterangan;
	}


	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}


	public String getKodjenishakmilik() {
		return kodjenishakmilik;
	}


	public void setKodjenishakmilik(String kodjenishakmilik) {
		this.kodjenishakmilik = kodjenishakmilik;
	}
	
	public String getIdNegeri() {
		return idNegeri;
	}


	public void setIdNegeri(String idNegeri) {
		this.idNegeri = idNegeri;
	}


	public String getIdDaerah() {
		return idDaerah;
	}


	public void setIdDaerah(String idDaerah) {
		this.idDaerah = idDaerah;
	}

	public String getIdMukim() {
		return idMukim;
	}
	public void setIdMukim(String idMukim) {
		this.idMukim = idMukim;
	}

	public String getIdSeksyen() {
		return idSeksyen;
	}
	public void setIdSeksyen(String idSeksyen) {
		this.idSeksyen = idSeksyen;
	}
	
	public String getIdHakmilik() {
		return idHakmilik;
	}

	public void setIdHakmilik(String idHakmilik) {
		this.idHakmilik = idHakmilik;
	}


	public String getIdLot() {
		return idLot;
	}


	public void setIdLot(String idLot) {
		this.idLot = idLot;
	}
	

	public String getIdKemaskini() {
		return idKemaskini;
	}


	public void setIdKemaskini(String idKemaskini) {
		this.idKemaskini = idKemaskini;
	}


	public Vector<PihakBerkepentingan> getPihaks() {
		return pihaks;
	}


	public void setPihaks(Vector<PihakBerkepentingan> pihaks) {
		this.pihaks = pihaks;
	}
	public void addPihakBerkepentingan(PihakBerkepentingan pb){
		if(pihaks == null){
			pihaks = new Vector<PihakBerkepentingan>();
		}
		this.pihaks.addElement(pb);
	
	}


	public String getNoBangunan() {
		return noBangunan;
	}


	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}


	public String getNoPetak() {
		return noPetak;
	}


	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}


	public String getNoTingkat() {
		return noTingkat;
	}


	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}


	public Permohonan getPermohonan() {
		return permohonan;
	}


	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}


	public String getIdJenisRizab() {
		return idJenisRizab;
	}


	public void setIdJenisRizab(String idJenisRizab) {
		this.idJenisRizab = idJenisRizab;
	}


	public String getIdKategoriTanah() {
		return idKategoriTanah;
	}


	public void setIdKategoriTanah(String idKategoriTanah) {
		this.idKategoriTanah = idKategoriTanah;
	}


	public String getIdSubKetegoriTanah() {
		return idSubKetegoriTanah;
	}


	public void setIdSubKetegoriTanah(String idSubKetegoriTanah) {
		this.idSubKetegoriTanah = idSubKetegoriTanah;
	}


	public String getIdLuas() {
		return idLuas;
	}

	public void setIdLuas(String idLuas) {
		this.idLuas = idLuas;
	}

	public String getIdLuasBersamaan() {
		return idLuasBersamaan;
	}


	public void setIdLuasBersamaan(String idLuasBersamaan) {
		this.idLuasBersamaan = idLuasBersamaan;
	}
	
	public String getNoPlan() {
		return noPlan;
	}


	public void setNoPlan(String noPlan) {
		this.noPlan = noPlan;
	}


	public String getPeganganHakmilik() {
		return peganganHakmilik;
	}


	public void setPeganganHakmilik(String peganganHakmilik) {
		this.peganganHakmilik = peganganHakmilik;
	}


	public String getTarikhMula() {
		return tarikhMula;
	}


	public void setTarikhMula(String tarikhMula) {
		this.tarikhMula = tarikhMula;
	}


	public String getTarikhLuput() {
		return tarikhLuput;
	}


	public void setTarikhLuput(String tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}


	public String getKeteranganLot() {
		return keteranganLot;
	}


	public void setKeteranganLot(String keteranganLot) {
		this.keteranganLot = keteranganLot;
	}
	

	public String getStatusTanah() {
		return statusTanah;
	}


	public void setStatusTanah(String statusTanah) {
		this.statusTanah = statusTanah;
	}	
	
	public String getTempohTanah() {
		return tempohTanah;
	}


	public void setTempohTanah(String tempohTanah) {
		this.tempohTanah = tempohTanah;
	}
	

	public String getTempohBakiTanah() {
		return tempohBakiTanah;
	}


	public void setTempohBakiTanah(String tempohBakiTanah) {
		this.tempohBakiTanah = tempohBakiTanah;
	}
	
}

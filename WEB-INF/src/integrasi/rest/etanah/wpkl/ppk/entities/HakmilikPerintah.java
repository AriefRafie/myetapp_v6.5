package integrasi.rest.etanah.wpkl.ppk.entities;

import java.util.List;

public class HakmilikPerintah {

	private String idHakmilik;
	private String idNegeri;
	private String idDaerah;
	private String idMukim;
	private String noHakmilik;
	private String bangunan;
	private String noTingkat;
	private String noPetak;
	private String idJenisHakmilik;
	private String noLot;
	private String idLuas;
	private String luas;
	private String baSimati;
	private String bbSimati;
	private String jenisPembahagian;
	private List<BorangET> borangETList;
	private List<BorangF> borangFList;

	public String getIdHakmilik() {
		return idHakmilik;
	}

	public void setIdHakmilik(String idHakmilik) {
		this.idHakmilik = idHakmilik;
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

	public String getNoHakmilik() {
		return noHakmilik;
	}

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}

	public String getBangunan() {
		return bangunan;
	}

	public void setBangunan(String bangunan) {
		this.bangunan = bangunan;
	}

	public String getNoTingkat() {
		return noTingkat;
	}

	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}

	public String getNoPetak() {
		return noPetak;
	}

	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}

	public String getIdJenisHakmilik() {
		return idJenisHakmilik;
	}

	public void setIdJenisHakmilik(String idJenisHakmilik) {
		this.idJenisHakmilik = idJenisHakmilik;
	}

	public String getNoLot() {
		return noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public String getIdLuas() {
		return idLuas;
	}

	public void setIdLuas(String idLuas) {
		this.idLuas = idLuas;
	}

	public String getLuas() {
		return luas;
	}

	public void setLuas(String luas) {
		this.luas = luas;
	}

	public String getBaSimati() {
		return baSimati;
	}

	public void setBaSimati(String baSimati) {
		this.baSimati = baSimati;
	}

	public String getBbSimati() {
		return bbSimati;
	}

	public void setBbSimati(String bbSimati) {
		this.bbSimati = bbSimati;
	}

	public String getJenisPembahagian() {
		return jenisPembahagian;
	}

	public void setJenisPembahagian(String jenisPembahagian) {
		this.jenisPembahagian = jenisPembahagian;
	}

	public List<BorangET> getBorangETList() {
		return borangETList;
	}

	public void setBorangETList(List<BorangET> borangETList) {
		this.borangETList = borangETList;
	}

	public List<BorangF> getBorangFList() {
		return borangFList;
	}

	public void setBorangFList(List<BorangF> borangFList) {
		this.borangFList = borangFList;
	}
}

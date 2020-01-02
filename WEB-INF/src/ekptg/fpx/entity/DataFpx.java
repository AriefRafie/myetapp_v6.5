package ekptg.fpx.entity;

import java.io.Serializable;
import java.util.Date;

import ekptg.fpx.JenisBayaran;

public class DataFpx implements Serializable{
	private String id;
	private String noTransaksi;
	private String idJenisBayaran;
	private double amount;
	private String idMasuk;
	private String noKp;
	private String noFail;
	private String noBil;
	private String tarikhMasuk;
	private String catatan;
	private String nama;
	private String type;
	private String status;
	private String noResit;
	private Date transactionDate;
	private String amountInWord;
	private String directMessage;
	private String indirectMessage;
	@Deprecated
	private String jenisBayaran;
	private JenisBayaran jBayaran;
	
	@Deprecated
	public String getJenisBayaran() {
		return jenisBayaran;
	}
	@Deprecated
	public void setJenisBayaran(String jenisBayaran) {
		this.jenisBayaran = jenisBayaran;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoTransaksi() {
		return noTransaksi;
	}
	public void setNoTransaksi(String noTransaksi) {
		this.noTransaksi = noTransaksi;
	}
	public String getIdJenisBayaran() {
		return idJenisBayaran;
	}
	public void setIdJenisBayaran(String idJenisBayaran) {
		this.idJenisBayaran = idJenisBayaran;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(String idMasuk) {
		this.idMasuk = idMasuk;
	}
	public String getNoKp() {
		return noKp;
	}
	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}
	public String getNoFail() {
		return noFail;
	}
	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}
	public String getNoBil() {
		return noBil;
	}
	public void setNoBil(String noBil) {
		this.noBil = noBil;
	}
	public String getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(String tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNoResit() {
		return noResit;
	}
	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getAmountInWord() {
		return amountInWord;
	}
	public void setAmountInWord(String amountInWord) {
		this.amountInWord = amountInWord;
	}
	public String getDirectMessage() {
		return directMessage;
	}
	public void setDirectMessage(String directMessage) {
		this.directMessage = directMessage;
	}
	public String getIndirectMessage() {
		return indirectMessage;
	}
	public void setIndirectMessage(String indirectMessage) {
		this.indirectMessage = indirectMessage;
	}
	public void setjBayaran(JenisBayaran jBayaran) {
		this.jBayaran = jBayaran;
	}
	public JenisBayaran getjBayaran() {
		return jBayaran;
	}
	
	
}

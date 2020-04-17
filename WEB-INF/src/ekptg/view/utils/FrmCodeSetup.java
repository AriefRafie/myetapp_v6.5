package ekptg.view.utils;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.utils.CodeData;

public class FrmCodeSetup extends AjaxBasedModule {

	static Logger myLogger = Logger.getLogger(FrmCodeSetup.class);

	public String doTemplate2() throws Exception {

	//myLogger.info("Sample info log");

	String template = "";
	HttpSession session = this.request.getSession();
	

	this.context.put("list_of_tables",toArray(
			"PDTDOKUMENPEKELILING,"+
			"PDTPERKARAPEKELILING,"+
			"PFDFAHARASAT,"+
			"PFDLOKASIFAIL,"+
			"PFDLOKASIMESYUARAT,"+
			"PFDTARAFKESELAMATAN,"+
			"PHPDOKUMEN,"+
			"PHPJENISTUJUAN,"+
			"PHPKEDUDUKAN,"+
			"PHPTUJUANKAITAN,"+
			"PPKBUKTIMATI,"+
			"PPKJENISHA,"+
			"PPKJENISPERINTAH,"+
			"PPKSAUDARA,"+
			"PPKTARAFKPTG,"+
			"PPKUNIT,"+
			"AGAMA,"+
			"AGENSI,"+
			"BANDAR,"+
			"BANGSA,"+
			"BANK,"+
			"BEBANAN,"+
			"CARABAYAR,"+
			"DAERAH,"+
			"DB,"+
			"DBLAMA,"+
			"DBLAMABARU,"+
			"JAWATAN,"+
			"JENISADUAN,"+
			"JENISBAYARAN,"+
			"JENISDOKUMEN,"+
			"JENISHAKMILIK,"+
			"JENISNOPB,"+
			"JENISPB,"+
			"JENISPEJABAT,"+
			"JENISRIZAB,"+
			"JENISTANAH,"+
			"KATEGORI,"+
			"KATEGORIPEMOHON,"+
			"KEMENTERIAN,"+
			"LOT,"+
			"LUAS,"+
			"MUKIM,"+
			"NEGARA,"+
			"NEGERI,"+
			"PEGAWAI,"+
			"PEJABAT,"+
			"PEJABATJKPTG,"+
			"PEJABATURUSAN,"+
			"SEKSYEN," +
			"SUMBERADUAN,"+
			"STATUS,"+
			"SUBKATEGORI,"+
			"ULASANRINGKASAN,"+
			"URUSAN,"+
			//"URUSANSEKSYEN,"+
			//"URUSANSTATUS,"+
			"WARGANEGARA,"+
			"KATEGORITINDAKANADUAN,"+
			"MODUL"));

	String NiceTableName = "";
	String table = "";


	String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
	String action = getParam("action"); // Second Level

	String id = getParam("id");

	//System.out.println("ID: " + id);

	String id_fieldname = getParam("id_fieldname");
	String orderby = getParam("orderby");

	String ordertype = getParam("ordertype");

	if ("asc".equals(ordertype)) {
		ordertype="desc";
	}
	else {
		ordertype = "asc";
	}

	this.context.put("ordertype",ordertype);

	if (orderby == "") {
		orderby = null;
	}

	if (orderby != null) {
		orderby = orderby + " " +ordertype;
		///System.out.println("OrderBy:"+orderby);
	}

	//Init value

	this.context.put("mode", "");
	this.context.put("error", "");

	//System.out.println("submit:"+submit);

	NiceTableName = getParam("NiceTableName")==""?(String)this.context.get("NiceTableName"):getParam("NiceTableName");
	this.context.put("NiceTableName",NiceTableName);


	String tablename = null;
	String fields = null;
	String nicefields = null;
	String fieldLength = null;
	String data_type = null;
	String data_nullable = null;
	String isLookup = "";

	//myLogger.info("submit:"+submit);
	if ("doChanges".equals(submit)) {
		submit = "record_listing";
	}
	if ("record_listing".equals(submit)) {
		context.put("error","");
		context.put("EkptgHTML", new HTML());
		template = "app/utils/CodeSetupForm.jsp";
		this.context.put("mode", "view");

		if ("PDTDOKUMENPEKELILING".equals(NiceTableName)) {
			tablename = "TBLPDTRUJDOKUMENPEKELILING";
			fields = "id_dokumenpekeliling,jenis_dokumen_pekeliling,kod_jenis_dokumenpekeliling";
			nicefields = " dokumenpekeliling,jenis dokumen pekeliling,kod jenis dokumenpekeliling";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,4000,10";
			data_nullable = "N,Y,Y";
		}
		else if ("PDTPERKARAPEKELILING".equals(NiceTableName)) {
			tablename = "TBLPDTRUJPERKARAPEKELILING";
			fields = "id_perkarapekeliling,kod_perkara_pekeliling,perkara_pekeliling";
			nicefields = " perkarapekeliling,kod perkara pekeliling,perkara pekeliling";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,4000";
			data_nullable = "N,Y,Y";
		}
		else if ("PFDFAHARASAT".equals(NiceTableName)) {
			tablename = "TBLPFDRUJFAHARASAT";
			fields = "id_faharasat,faharasat";
			nicefields = " faharasat,faharasat";
			data_type = "NUMBER,VARCHAR2";
			fieldLength = "22,100";
			data_nullable = "N,Y";
		}
		else if ("PFDLAMPIRAN".equals(NiceTableName)) {
			tablename = "TBLPFDRUJLAMPIRAN";
			fields = "id_lampiran,id_dokumen,content,nama_fail,jenis_mime,id_mesyuarat";
			nicefields = " lampiran, dokumen,content,nama fail,jenis mime, mesyuarat";
			data_type = "NUMBER,NUMBER,BLOB,VARCHAR2,VARCHAR2,NUMBER";
			fieldLength = "22,22,4000,100,100,22";
			data_nullable = "N,Y,Y,Y,Y,Y";
		}
		else if ("PFDLOKASIFAIL".equals(NiceTableName)) {
			tablename = "TBLPFDRUJLOKASIFAIL";
			fields = "id_lokasifail,lokasi_fail";
			nicefields = " lokasifail,lokasi fail";
			data_type = "NUMBER,VARCHAR2";
			fieldLength = "22,100";
			data_nullable = "N,Y";
		}
		else if ("PFDLOKASIMESYUARAT".equals(NiceTableName)) {
			tablename = "TBLPFDRUJLOKASIMESYUARAT";
			fields = "id_lokasi,lokasi,id_negeri,id_seksyen";
			nicefields = " lokasi,nama lokasi,negeri,seksyen";
			data_type = "NUMBER,VARCHAR2,NUMBER,NUMBER";
			fieldLength = "22,50,22,22";
			data_nullable = "N,Y,Y,Y";
		}
		else if ("PFDTARAFKESELAMATAN".equals(NiceTableName)) {
			tablename = "TBLPFDRUJTARAFKESELAMATAN";
			fields = "id_tarafkeselamatan,kod_taraf_keselamatan,taraf_keselamatan";
			nicefields = " tarafkeselamatan,kod taraf keselamatan,taraf keselamatan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,10,15";
			data_nullable = "N,Y,Y";
		}
		else if ("PHPDOKUMEN".equals(NiceTableName)) {
			tablename = "TBLPHPRUJDOKUMEN";
			fields = "id_dokumen,kod_dokumen,keterangan";
			nicefields = " dokumen,kod dokumen,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,50";
			data_nullable = "N,Y,Y";
		}
		else if ("PHPJENISTUJUAN".equals(NiceTableName)) {
			tablename = "TBLPHPRUJJENISTUJUAN";
			fields = "id_jenistujuan,kod_jenistujuan,keterangan";
			nicefields = " jenistujuan,kod jenistujuan,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,50";
			data_nullable = "N,Y,Y";
		}
		else if ("PHPKEDUDUKAN".equals(NiceTableName)) {
			tablename = "TBLPHPRUJKEDUDUKAN";
			fields = "id_kedudukan,kod_kedudukan,keterangan";
			nicefields = " kedudukan,kod kedudukan,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,80";
			data_nullable = "N,Y,Y";
		}
		else if ("PHPTUJUANKAITAN".equals(NiceTableName)) {
			tablename = "TBLPHPRUJTUJUANKAITAN";
			fields = "id_tujuankaitan,kod_tujuankaitan,keterangan";
			nicefields = " tujuankaitan,kod tujuankaitan,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,50";
			data_nullable = "N,Y,Y";
		}
		else if ("PPKBUKTIMATI".equals(NiceTableName)) {
			tablename = "TBLPPKRUJBUKTIMATI";
			fields = "id_buktimati,kod,keterangan";
			nicefields = " buktimati,kod,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,250";
			data_nullable = "N,Y,Y";
		}
		else if ("PPKJENISHA".equals(NiceTableName)) {
			tablename = "TBLPPKRUJJENISHA";
			fields = "id_jenisha,kod,keterangan";
			nicefields = " jenisha,kod,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,250";
			data_nullable = "N,Y,Y";
		}
		else if ("PPKJENISPERINTAH".equals(NiceTableName)) {
			tablename = "TBLPPKRUJJENISPERINTAH";
			fields = "id_jenisperintah,kod,keterangan";
			nicefields = " jenisperintah,kod,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,250";
			data_nullable = "N,Y,Y";
		}
		else if ("PPKSAUDARA".equals(NiceTableName)) {
			tablename = "TBLPPKRUJSAUDARA";
			fields = "id_saudara,kod,keterangan,jantina";
			nicefields = " saudara,kod,keterangan,jantina";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,250,2";
			data_nullable = "N,Y,Y,Y";
		}
		else if ("PPKTARAFKPTG".equals(NiceTableName)) {
			tablename = "TBLPPKRUJTARAFKPTG";
			fields = "id_tarafkptg,kod,keterangan";
			nicefields = " tarafkptg,kod,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,250";
			data_nullable = "N,Y,Y";
		}
		else if ("PPKUNIT".equals(NiceTableName)) {
			tablename = "TBLPPKRUJUNIT";
			fields = "id_unitpsk,kod,id_negeri,id_jkptg,nama_pejabat,keterangan_unit_psk,nama_pegawai,jawatan,status_peg,alamat1,alamat2,alamat3,poskod,no_tel,no_tel_sambungan,catatan";
			nicefields = " unitpsk,kod, negeri, jkptg,nama pejabat,keterangan unit psk,nama pegawai,jawatan,status peg,alamat1,alamat2,alamat3,poskod,no tel,no tel sambungan,catatan";
			data_type = "NUMBER,VARCHAR2,NUMBER,NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,22,22,80,100,80,60,1,80,80,80,5,14,14,100";
			data_nullable = "N,Y,N,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y";
		}
		else if ("AGAMA".equals(NiceTableName)) {
			tablename = "TBLRUJAGAMA";
			fields = "id_agama,kod_agama,keterangan";
			nicefields = " agama,kod agama,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,15";
			data_nullable = "N,Y,Y";
		}
		else if ("AGENSI".equals(NiceTableName)) {
			tablename = "TBLRUJAGENSI";
			fields = "id_agensi,kod_agensi,nama_agensi,alamat1,alamat2,alamat3,poskod,id_negeri,jawatan,id_kementerian";
			nicefields = " agensi,kod agensi,nama agensi,alamat1,alamat2,alamat3,poskod, negeri,jawatan, kementerian";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,NUMBER,VARCHAR2,NUMBER";
			fieldLength = "22,2,80,80,80,80,5,22,100,22";
			data_nullable = "N,Y,Y,Y,Y,Y,Y,N,Y,N";
		}
		else if ("BANDAR".equals(NiceTableName)) {
			tablename = "TBLRUJBANDAR";
			fields = "id_bandar,kod_bandar,keterangan,id_negeri,id_daerah";
			nicefields = " bandar,kod bandar,keterangan, negeri, daerah";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,NUMBER,NUMBER";
			fieldLength = "22,4,40,22,22";
			data_nullable = "N,Y,Y,Y,Y";
		}
		else if ("BANGSA".equals(NiceTableName)) {
			tablename = "TBLRUJBANGSA";
			fields = "id_bangsa,kod_bangsa,keterangan";
			nicefields = " bangsa,kod bangsa,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,30";
			data_nullable = "N,Y,Y";
		}
		else if ("BANK".equals(NiceTableName)) {
			tablename = "TBLRUJBANK";
			fields = "id_bank,kod_bank,nama_bank,alamat1,alamat2,alamat3,poskod";
			nicefields = " bank,kod bank,nama bank,alamat1,alamat2,alamat3,poskod";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,35,80,80,80,5";
			data_nullable = "N,Y,Y,Y,Y,Y,Y";
		}
		else if ("BEBANAN".equals(NiceTableName)) {
			tablename = "TBLRUJBEBANAN";
			fields = "id_bebanan,kod_bebanan,keterangan";
			nicefields = " bebanan,kod bebanan,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,20,80";
			data_nullable = "N,Y,Y";
		}
		else if ("CARABAYAR".equals(NiceTableName)) {
			tablename = "TBLRUJCARABAYAR";
			fields = "id_carabayar,kod_carabayar,keterangan";
			nicefields = " carabayar,kod carabayar,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,25";
			data_nullable = "N,Y,Y";
		}
		else if ("DAERAH".equals(NiceTableName)) {
			tablename = "TBLRUJDAERAH";
			fields = "id_daerah,kod_daerah,nama_daerah,id_negeri";
			nicefields = " daerah,kod daerah,nama daerah, negeri";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,NUMBER";
			fieldLength = "22,2,80,22";
			data_nullable = "N,Y,Y,Y";
		}
		else if ("DB".equals(NiceTableName)) {
			tablename = "TBLRUJDB";
			fields = "kod_db,keterangan";
			nicefields = "kod db,keterangan";
			data_type = "VARCHAR2,VARCHAR2";
			fieldLength = "30,100";
			data_nullable = "Y,Y";
		}
		else if ("DBLAMA".equals(NiceTableName)) {
			tablename = "TBLRUJDBLAMA";
			fields = "id_dblama,kod_db,keterangan";
			nicefields = " dblama,kod db,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,30,100";
			data_nullable = "N,Y,Y";
		}
		else if ("DBLAMABARU".equals(NiceTableName)) {
			tablename = "TBLRUJDBLAMABARU";
			fields = "id_dblamabaru,id_dblama";
			nicefields = " dblamabaru, dblama";
			data_type = "NUMBER,NUMBER";
			fieldLength = "22,22";
			data_nullable = "N,N";
		}
		else if ("HUBUNGAN".equals(NiceTableName)) {
			tablename = "TBLRUJHUBUNGAN";
			fields = "id_hubungan,jantina_simati,hbgn_waris_mati,hbgn_waris_berikut,hbgn_baru";
			nicefields = " hubungan,jantina simati,hbgn waris mati,hbgn waris berikut,hbgn baru";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2";
			fieldLength = "22,10,50,50,50";
			data_nullable = "Y,Y,Y,Y,Y";
		}
		else if ("JAWATAN".equals(NiceTableName)) {
			tablename = "TBLRUJJAWATAN";
			fields = "id_jawatan,kod_jawatan,keterangan";
			nicefields = " jawatan,kod jawatan,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,50";
			data_nullable = "N,Y,Y";
		}
		else if ("JENISADUAN".equals(NiceTableName)) {
			tablename = "TBLRUJJENISADUAN";
			fields = "id_jenisaduan,kod_jenis_aduan,jenis_aduan";
			nicefields = " jenisaduan,kod jenis aduan,jenis aduan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,200";
			data_nullable = "N,Y,Y";
		}
		else if ("JENISBAYARAN".equals(NiceTableName)) {
			tablename = "TBLRUJJENISBAYARAN";
			fields = "id_jenisbayaran,kod_jenis_bayaran,keterangan,amaun,id_seksyen";
			nicefields = " jenisbayaran,kod jenis bayaran,keterangan,amaun, seksyen";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,NUMBER,NUMBER";
			fieldLength = "22,2,50,22,22";
			data_nullable = "N,N,Y,Y,Y";
		}
		else if ("JENISDOKUMEN".equals(NiceTableName)) {
			tablename = "TBLRUJJENISDOKUMEN";
			fields = "id_jenisdokumen,kod_jenis_dokumen,keterangan,id_seksyen,id_laporan";
			nicefields = " jenisdokumen,kod jenis dokumen,keterangan, seksyen, laporan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,NUMBER,VARCHAR2";
			fieldLength = "22,3,100,22,10";
			data_nullable = "N,Y,Y,N,Y";
		}
		else if ("JENISHAKMILIK".equals(NiceTableName)) {
			tablename = "TBLRUJJENISHAKMILIK";
			fields = "id_jenishakmilik,kod_jenis_hakmilik,keterangan,status_hakmilik,simpanan";
			nicefields = " jenishakmilik,kod jenis hakmilik,keterangan,status hakmilik,simpanan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,45,1,3";
			data_nullable = "N,Y,Y,Y,Y";
		}
		else if ("JENISNOPB".equals(NiceTableName)) {
			tablename = "TBLRUJJENISNOPB";
			fields = "id_jenisnopb,kod_jenis_nopb,keterangan";
			nicefields = " jenisnopb,kod jenis nopb,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,1,30";
			data_nullable = "N,Y,Y";
		}
		else if ("JENISPB".equals(NiceTableName)) {
			tablename = "TBLRUJJENISPB";
			fields = "id_jenispb,kod_jenis_pb,keterangan,jenis_daftar_pb";
			nicefields = " jenispb,kod jenis pb,keterangan,jenis daftar pb";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,35,1";
			data_nullable = "N,Y,Y,Y";
		}
		else if ("JENISPEJABAT".equals(NiceTableName)) {
			tablename = "TBLRUJJENISPEJABAT";
			fields = "id_jenispejabat,kod_jenis_pejabat,keterangan";
			nicefields = " jenispejabat,kod jenis pejabat,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,30";
			data_nullable = "N,Y,Y";
		}
		else if ("JENISRIZAB".equals(NiceTableName)) {
			tablename = "TBLRUJJENISRIZAB";
			fields = "id_jenisrizab,kod_rizab,keterangan";
			nicefields = " jenisrizab,kod rizab,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,30";
			data_nullable = "N,Y,Y";
		}
		else if ("JENISTANAH".equals(NiceTableName)) {
			tablename = "TBLRUJJENISTANAH";
			fields = "id_jenistanah,kod_jenis_tanah,keterangan";
			nicefields = " jenistanah,kod jenis tanah,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,30";
			data_nullable = "N,Y,Y";
		}
		else if ("KATEGORI".equals(NiceTableName)) {
			tablename = "TBLRUJKATEGORI";
			fields = "id_kategori,kod_kategori,keterangan";
			nicefields = " kategori,kod kategori,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,30";
			data_nullable = "N,Y,Y";
		}
		else if ("KATEGORIPEMOHON".equals(NiceTableName)) {
			tablename = "TBLRUJKATEGORIPEMOHON";
			fields = "id_kategoripemohon,kod_kategoripemohon,keterangan";
			nicefields = " kategoripemohon,kod kategoripemohon,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,30";
			data_nullable = "N,Y,Y";
		}
		else if ("KEMENTERIAN".equals(NiceTableName)) {
			tablename = "TBLRUJKEMENTERIAN";
			fields = "id_kementerian,kod_kementerian,nama_kementerian,alamat1,alamat2,alamat3,poskod,id_negeri,jawatan";
			nicefields = " kementerian,kod kementerian,nama kementerian,alamat1,alamat2,alamat3,poskod, negeri,jawatan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,NUMBER,VARCHAR2";
			fieldLength = "22,2,80,80,80,80,5,22,100";
			data_nullable = "N,Y,Y,Y,Y,Y,Y,N,Y";
		}
		else if ("LOT".equals(NiceTableName)) {
			tablename = "TBLRUJLOT";
			fields = "id_lot,kod_lot,keterangan";
			nicefields = " lot,kod lot,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,30";
			data_nullable = "N,Y,Y";
		}
		else if ("LUAS".equals(NiceTableName)) {
			tablename = "TBLRUJLUAS";
			fields = "id_luas,kod_luas,keterangan";
			nicefields = " luas,kod luas,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,30";
			data_nullable = "N,Y,Y";
		}
		else if ("MUKIM".equals(NiceTableName)) {
			tablename = "TBLRUJMUKIM";
			fields = "id_mukim,kod_mukim,nama_mukim,id_daerah";
			nicefields = " mukim,kod mukim,nama mukim, daerah";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,NUMBER";
			fieldLength = "22,2,80,22";
			data_nullable = "N,Y,Y,N";
		}
		else if ("NEGARA".equals(NiceTableName)) {
			tablename = "TBLRUJNEGARA";
			fields = "id_negara,kod_negara,nama_negara";
			nicefields = " negara,kod negara,nama negara";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,20,80";
			data_nullable = "N,Y,Y";
		}
		else if ("NEGERI".equals(NiceTableName)) {
			tablename = "TBLRUJNEGERI";
			fields = "id_negeri,kod_negeri,nama_negeri,id_negara,kod_mampu,abbrev";
			nicefields = " negeri,kod negeri,nama negeri, negara,kod mampu,abbrev";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,2,80,22,2,3";
			data_nullable = "N,Y,Y,N,Y,Y";
		}
		else if ("PEGAWAI".equals(NiceTableName)) {
			tablename = "TBLRUJPEGAWAI";
			fields = "id_pegawai,nama_pegawai,no_pekerja,no_kp,jawatan,alamat1,alamat2,alamat3,poskod,id_negeri,id_mukim,no_tel_pejabat,no_tel_rumah,no_tel_bimbit,id_seksyen,emel";
			nicefields = " pegawai,nama pegawai,no pekerja,no kp,jawatan,alamat1,alamat2,alamat3,poskod, negeri, mukim,no tel pejabat,no tel rumah,no tel bimbit, seksyen,emel";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,NUMBER,NUMBER,NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,NUMBER,VARCHAR2";
			fieldLength = "22,80,10,14,30,80,80,80,22,22,22,14,14,14,22,100";
			data_nullable = "N,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y";
		}
		else if ("PEJABAT".equals(NiceTableName)) {
			tablename = "TBLRUJPEJABAT";
			fields = "id_pejabat,id_negeri,id_daerah,id_seksyen,id_jenispejabat,kod_pejabat,nama_pejabat,alamat1,alamat2,alamat3,poskod,no_tel,no_fax,jawatan,no_akaun,id_bandar";
			nicefields = " pejabat, negeri, daerah, seksyen, jenispejabat,kod pejabat,nama pejabat,alamat1,alamat2,alamat3,poskod,no tel,no fax,jawatan,no akaun, bandar";
			data_type = "NUMBER,NUMBER,NUMBER,NUMBER,NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,NUMBER";
			fieldLength = "22,22,22,22,22,3,80,80,80,80,5,14,14,60,14,22";
			data_nullable = "N,N,N,N,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y,Y";
		}
		else if ("PEJABATJKPTG".equals(NiceTableName)) {
			tablename = "TBLRUJPEJABATJKPTG";
			fields = "id_pejabatjkptg,id_negeri,id_daerah,id_seksyen,kod_jkptg,id_jenispejabat,nama_pejabat,alamat1,alamat2,alamat3,poskod,prefix,no_tel,no_fax,id_jawatan,id_bandar";
			nicefields = " pejabatjkptg,negeri, daerah, seksyen,kod jkptg, jenispejabat,nama pejabat,alamat1,alamat2,alamat3,poskod, prefix,no tel,no fax, jawatan, bandar";
			data_type = "NUMBER,NUMBER,VARCHAR2,NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,VARCHAR2,NUMBER,NUMBER,VARCHAR2,VARCHAR2,VARCHAR2,NUMBER,NUMBER";
			fieldLength = "22,22,3,22,80,80,80,80,5,22,22,15,14,14,22,22";
			data_nullable = "N,N,Y,Y,Y,Y,Y,Y,Y,N,N,Y,Y,Y,Y,Y";
		}
		else if ("PEJABATURUSAN".equals(NiceTableName)) {
			tablename = "TBLRUJPEJABATURUSAN";
			fields = "id_pejabaturusan,id_jenispejabat,id_pejabatjkptg,id_negeri,id_daerah,id_jenispejabaturus,id_pejabaturus,id_negeriurus,id_daerahurus,id_seksyen";
			nicefields = " pejabaturusan, jenispejabat, pejabatjkptg, negeri, daerah, jenispejabaturus, pejabaturus, negeriurus, daerahurus, seksyen";
			data_type = "NUMBER,NUMBER,NUMBER,NUMBER,NUMBER,NUMBER,NUMBER,NUMBER,NUMBER,NUMBER";
			fieldLength = "22,22,22,22,22,22,22,22,22,22";
			data_nullable = "N,Y,Y,Y,Y,Y,Y,Y,Y,Y";
		}
		else if ("POSKOD".equals(NiceTableName)) {
			tablename = "TBLRUJPOSKOD";
			fields = "id_poskod,poskod,id_bandar";
			nicefields = " poskod,poskod, bandar";
			data_type = "NUMBER,VARCHAR2,NUMBER";
			fieldLength = "22,10,22";
			data_nullable = "Y,Y,Y";
		}
		else if ("SEKSYEN".equals(NiceTableName)) {
			tablename = "TBLRUJSEKSYEN";
			fields = "id_seksyen,kod_seksyen,nama_seksyen,versi_seksyen";
			nicefields = " seksyen,kod seksyen,nama seksyen,versi seksyen";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,VARCHAR2";
			fieldLength = "22,4,80,3";
			data_nullable = "N,Y,Y,Y";
		}
		else if ("SUMBERADUAN".equals(NiceTableName)) {
			tablename = "TBLRUJSUMBERADUAN";
			fields = "id_sumberaduan,kod_sumber,nama_sumber";
			nicefields = " sumberaduan,kod sumber,nama sumber";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "10,3,50";
			data_nullable = "N,Y,Y";
		}
		else if ("STATUS".equals(NiceTableName)) {
			tablename = "TBLRUJSTATUS";
			fields = "id_status,kod_status,keterangan,id_seksyen";
			nicefields = " status,kod status,keterangan, seksyen";
			data_type = "NUMBER,VARCHAR2,VARCHAR2,NUMBER";
			fieldLength = "22,60,100,22";
			data_nullable = "N,Y,Y,Y";
		}
		else if ("SUBKATEGORI".equals(NiceTableName)) {
			tablename = "TBLRUJSUBKATEGORI";
			fields = "id_subkategori,id_kategori,kod_subkategori,keterangan";
			nicefields = " subkategori, kategori,kod subkategori,keterangan";
			data_type = "NUMBER,NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,22,20,30";
			data_nullable = "N,N,Y,Y";
		}
		else if ("ULASANRINGKASAN".equals(NiceTableName)) {
			tablename = "TBLRUJULASANRINGKASAN";
			fields = "id_ulasanringkasan,kod_ulasanringkasan,keterangan";
			nicefields = " ulasanringkasan,kod ulasanringkasan,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,40";
			data_nullable = "N,Y,Y";
		}
		else if ("URUSAN".equals(NiceTableName)) {
			tablename = "TBLRUJURUSAN";
			fields = "id_urusan,kod_urusan,nama_urusan";
			nicefields = " urusan,kod urusan,nama urusan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,5,80";
			data_nullable = "N,Y,Y";
		}
		else if ("URUSANSEKSYEN".equals(NiceTableName)) {
			tablename = "TBLRUJURUSANSEKSYEN";
			fields = "id_urusanseksyen,id_urusan,id_seksyen";
			nicefields = " urusanseksyen, urusan, seksyen";
			data_type = "NUMBER,NUMBER,NUMBER";
			fieldLength = "22,22,22";
			data_nullable = "N,N,Y";
		}
		else if ("URUSANSTATUS".equals(NiceTableName)) {
			tablename = "TBLRUJURUSANSTATUS";
			fields = "id_urusanstatus,id_permohonan,id_status_fail,id_urusan,tarikh_mula,tarikh_akhir,aktif,kod_lama";
			nicefields = " urusanstatus, permohonan, status fail, urusan,tarikh mula,tarikh akhir,aktif,kod lama";
			data_type = "NUMBER,NUMBER,NUMBER,NUMBER,DATE,DATE,VARCHAR2,NUMBER";
			fieldLength = "22,22,22,22,7,7,2,22";
			data_nullable = "N,N,N,N,Y,Y,Y,Y";
		}
		else if ("WARGANEGARA".equals(NiceTableName)) {
			tablename = "TBLRUJWARGANEGARA";
			fields = "id_warganegara,kod_warga,keterangan";
			nicefields = " warganegara,kod warga,keterangan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,3,20";
			data_nullable = "N,Y,Y";
		}
		else if ("KATEGORITINDAKANADUAN".equals(NiceTableName)) {
			tablename = "TBLRUJKATEGORITINDAKAN";
			fields = "ID_KATEGORITINDAKAN,KATEGORITINDAKAN";
			nicefields = " ID_KATEGORITINDAKAN,Kategori Tindakan";
			data_type = "NUMBER,VARCHAR2,VARCHAR2";
			fieldLength = "22,300";
			data_nullable = "N,N";
		}
		else if ("MODUL".equals(NiceTableName)) {
			tablename = "TBLRUJMODUL";
			fields = "id_modul,kod_modul,nama_modul";
			nicefields = " id modul,kod modul,nama modul";
			data_type = "VARCHAR2,VARCHAR2,VARCHAR2";
			fieldLength = "22,4,60";
			data_nullable = "N,Y,Y";
		}
		


		try {
//			CodeSetupTable cs = new CodeSetupTable(tablename,NiceTableName,fields,nicefields,orderby,
//					isLookupString);
			CodeData cd = new CodeData(tablename,NiceTableName,fields,nicefields,orderby,
					isLookup,fieldLength);
			getListing(cd,session,action);
		} catch (Exception e) {
			context.put("error",e.getMessage());
		}

		/* Select Many checkbox
		String ids[] = request.getParameterValues("ids");
		if (ids != null) {
			for(int i = 0; i < ids.length; i++) {
				System.out.println("id "+i+"="+ids[i]);
			}
		}*/

	}
	else if ("edit_form".equals(submit)) {
		template = "app/utils/CodeEditForm.jsp";
		this.context.put("mode", "edit");

		CodeData cd = (CodeData)this.context.get("CodeData");
		
		myLogger.info("DATA UNTUK EDIT <> ID :" + cd);
				
		//System.out.println(cd.getTablename());
		//System.out.println(cd.getList());
		cd.setID(id);
		System.out.println(cd.getList());
		System.out.println(cd.getValue(cd.getList().toString()));

		getListing(cd,session,action);
	} else if ("doUpdate".equals(submit)) {
		template = "app/utils/CodeSetupForm.jsp";
		Hashtable parameters = new Hashtable();
		setParameterValues(parameters);
		CodeData cd = (CodeData)this.context.get("CodeData");
		cd.update(cd.getTablename(),parameters,id_fieldname,id);
		cd.setID(null);
		getListing(cd,session,action);
	} else if ("delete".equals(submit)) {
		template = "app/utils/CodeSetupForm.jsp";
		CodeData cd = (CodeData)this.context.get("CodeData");
		try {
			cd.delete(cd.getTablename(),id_fieldname,id);
			cd.setID(null);
			getListing(cd,session,action);
		} catch (Exception e) {
			context.put("error",e.getMessage());
		}
	}
	else if  ("AddNew".equals(submit)) {
		template = "app/utils/CodeEditForm.jsp";
		context.put("mode", "AddNew");
	}
	else if ("doAdd".equals(submit)) {
		template = "app/utils/CodeSetupForm.jsp";
		Hashtable parameters = new Hashtable();
		setParameterValues(parameters);
		CodeData cd = (CodeData)this.context.get("CodeData");
		cd.insert(cd.getTablename(),parameters,id_fieldname);
		cd.setID(null);
		getListing(cd,session,action);
	}
	else if ("goBack2".equals(submit)) {
		template = "app/utils/CodeSetupForm.jsp";
		CodeData cd = (CodeData)this.context.get("CodeData");
		cd.setID(null);
		getListing(cd,session,action);
	}
	else {
		template = "app/utils/CodeSetup.jsp";
	}

	return template;
}

	public String[] toArray(String str ) {
		return str.split(",");
	}

	public void setParameterValues(Hashtable parameters) {
		String name="";
		String value="";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements(); ) {
	        name = (String)allparam.nextElement();
	        if (name.indexOf("Form_") != -1) { // get only parameters with name like Form_
		        value = request.getParameter(name);
		        parameters.put(name,value);
	        }
		}
	}

	public void getListing(CodeData cd,HttpSession session,String action) throws Exception {
		try {
			//myLogger.info("action:"+action);
			context.put("CodeData",cd == null ? "" : cd);
			context.put("FieldName",cd.getFields());
			context.put("NiceFieldName",cd.getNicefields());
			context.put("FieldLength",cd.getFieldLength());
			context.put("isLookup",cd.getIsLookup());
			setupPage(session,action,cd.getList());

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}
	}

	//public void setCSData(CodeSet)

}

package ekptg.view.htp.rekod.kjp;

/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.rekod.kjp.FrmKjpRekodDB;

public class KjpRekodController_Bak extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(KJPRekodController.class);
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		FrmKjpRekodDB rekodDb = new FrmKjpRekodDB();
		Vector<Hashtable<String,String>> listDbPTP = null;
		
		String path = "";
		String selectedForm = "";
		
    	//screen jsp
		String folder = "app/htp/kjp/rekod/";
    	String index = folder+"index.jsp";
    	
    	//command for pagings
    	String action = getParam("action");
    	
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewMainReportNegeri".equals(submit)){		
    		//selected form
    		selectedForm = "reportNegeri";	
    		//Get luas in hektar by negeri
    		getLuasPTPByNegeri(listDbPTP,rekodDb);
    		
    	}else if("viewMainReportNegeriDetail".equals(submit)){		
    		//selected form
    		selectedForm = "reportNegeriDetail";
    		
    		String idNegeri = getParam("idNegeri");
    		context.put("idNegeri", idNegeri); 		
    		//Get luas in hektar by negeri
    		getLuasPTPByNegeriDetail(listDbPTP,rekodDb,idNegeri,session,action);
    		
    	}else if("viewMainReportKementerian".equals(submit)){	
    		//selected form
    		selectedForm = "reportKementerian";
    		//Get luas in hektar by negeri
    		getLuasPTPByKementerian(listDbPTP,rekodDb);
    	
    	}else if("viewMainReportKementerianDetail".equals(submit)){		
    		//selected form
    		selectedForm = "reportKementerianDetail";
    		
    		String idKementerian = getParam("idKementerian");
    		context.put("idKementerian", idKementerian); 		
    		//Get luas in hektar by negeri
    		getLuasPTPByKementerianDetail(listDbPTP,rekodDb,idKementerian,session,action);
    		
    	}else if("viewMainReportAgensiDetail".equals(submit)){	
    		//selected form
    		selectedForm = "reportAgensiDetail";
    		
    		String idAgensi = getParam("idAgensi");
    		context.put("idAgensi", idAgensi);		
    		//Get luas in hektar by negeri
    		getLuasPTPByAgensiDetail(listDbPTP,rekodDb,idAgensi,session,action);
    		
    	}else if("viewLot".equals(submit)){
    		//selected form
    		selectedForm = "reportLot";
    		
    		String idHakmilik = getParam("idHakmilik");
    		context.put("idHakmilik", idHakmilik);		
    		//Get maklumat hakmilik
    		getDataHakmilik(listDbPTP,rekodDb,idHakmilik);
    		
    	}else{	
    		//selected form
    		selectedForm = "main";
    		//Get Luas in hektar
    		getLuasPTP(listDbPTP,rekodDb);		
    		//dropdown (meter,kaki,ekar & hektar)
    		context.put("selectLuas",HTML.SelectLuaSemasa("socLuas",null,null,"style=width:200px"));
    		
		}//close else
    	//selected form
		context.put("selectedForm",selectedForm);
		context.put("utils",new Utils());
		//screen
		path = index;	
		return path;
    		
	}//close public template
	
	public void getLuasPTP(Vector listDbPTP,FrmKjpRekodDB rekodDb) throws Exception{
		Double luasTanahMilik = 0.00;
		Double luasTanahRizab = 0.00;
		Double luasKeseluruhan = 0.00;
    	Double percentTanahMilik = 0.00;
    	Double percentTanahRizab = 0.00;
    	
		listDbPTP = rekodDb.getDBLuasPTP();
		//System.out.println("listDbPTP : "+listDbPTP);
		if(listDbPTP.size()!=0){
			Hashtable ldb = (Hashtable)listDbPTP.get(0);
			luasTanahMilik = (Double)ldb.get("luas_tanah_milik");
			luasTanahRizab = (Double)ldb.get("luas_tanah_rizab");
			luasKeseluruhan = (Double)ldb.get("luas_keseluruhan");
			percentTanahMilik = (Double)ldb.get("percentTanahMilik");
			percentTanahRizab = (Double)ldb.get("percentTanahRizab");
		}
		
		context.put("luasTanahMilik", Utils.formatLuas(luasTanahMilik));
		context.put("luasTanahRizab", Utils.formatLuas(luasTanahRizab));
		context.put("luasKeseluruhan", Utils.formatLuas(luasKeseluruhan));
		context.put("percentTanahMilik", Utils.format2Decimal(percentTanahMilik));
		context.put("percentTanahRizab", Utils.format2Decimal(percentTanahRizab));
	}
	
	
	@SuppressWarnings("rawtypes")
	public void getLuasPTPByNegeri(Vector listDbPTPByNegeri,FrmKjpRekodDB rekodDb) throws Exception{
		listDbPTPByNegeri = rekodDb.getDBLuasPTPByNegeri();
    	Double totalTanahMilik = 0.00;
    	Double totalTanahRizab = 0.00;
    	Double totalKeseluruhan = 0.00;
    	if(listDbPTPByNegeri.size()!=0){
	    	for (int i = 0; i < listDbPTPByNegeri.size(); i++) { 
	    		Hashtable ldb = (Hashtable)listDbPTPByNegeri.get(i);
	    		totalTanahMilik += Double.parseDouble(String.valueOf(ldb.get("luas_tanah_milik")));
	    		totalTanahRizab += Double.parseDouble(String.valueOf(ldb.get("luas_tanah_rizab")));
	    		totalKeseluruhan += Double.parseDouble(String.valueOf(ldb.get("luas_keseluruhan")));
			}
    	}
    	context.put("listDbPTPByNegeri",listDbPTPByNegeri);
    	context.put("totalTanahMilik",Utils.formatLuas(totalTanahMilik));
    	context.put("totalTanahRizab",Utils.formatLuas(totalTanahRizab));
    	context.put("totalKeseluruhan",Utils.formatLuas(totalKeseluruhan));
    	
	}
	
	public void getLuasPTPByNegeriDetail(Vector<Hashtable<String,String>> listDbPTPByNegeriDetail,FrmKjpRekodDB rekodDb,String idNegeri, HttpSession session,String action) throws Exception{		
		String namaNegeri = "";
		Vector<Hashtable<String,String>> listDbDataNegeri = rekodDb.getDBDataNegeri(idNegeri);
		if(listDbDataNegeri.size()!=0){
			Hashtable<String,String> dn = (Hashtable<String,String>)listDbDataNegeri.get(0);
			namaNegeri = (String)dn.get("nama_negeri");
		}
		
		listDbPTPByNegeriDetail = rekodDb.getDBLuasPTPByNegeriDetail(idNegeri);
    	Double totalLuas = 0.00;
    	if(listDbPTPByNegeriDetail.size()!=0){
	    	for (int i = 0; i < listDbPTPByNegeriDetail.size(); i++) { 
	    		Hashtable<String,String> ldb = (Hashtable<String,String>)listDbPTPByNegeriDetail.get(i);
	    		totalLuas += Double.parseDouble(String.valueOf(ldb.get("luas")));
	    		
			}
    	}
    	context.put("listDbPTPByNegeriDetail",listDbPTPByNegeriDetail);
    	context.put("totalLuas",Utils.formatLuas(totalLuas));
    	context.put("namaNegeri", namaNegeri);
    	
	}
	
	public void getLuasPTPByKementerian(Vector<Hashtable<String,String>> listDbPTPByKementerian,FrmKjpRekodDB rekodDb) throws Exception{
		listDbPTPByKementerian = rekodDb.getDBLuasPTPByKementerian();
    	Double totalTanahMilik = 0.00;
    	Double totalTanahRizab = 0.00;
    	Double totalKeseluruhan = 0.00;
    	if(listDbPTPByKementerian.size()!=0){
	    	for (int i = 0; i < listDbPTPByKementerian.size(); i++) { 
	    		Hashtable<String,String> ldb = (Hashtable<String,String>)listDbPTPByKementerian.get(i);
	    		totalTanahMilik += Double.parseDouble(String.valueOf(ldb.get("luas_tanah_milik")));
	    		totalTanahRizab += Double.parseDouble(String.valueOf(ldb.get("luas_tanah_rizab")));
	    		totalKeseluruhan += Double.parseDouble(String.valueOf(ldb.get("luas_keseluruhan")));
	    		
			}
    	}
    	context.put("listDbPTPByKementerian",listDbPTPByKementerian);
    	context.put("totalTanahMilik",Utils.formatLuas(totalTanahMilik));
    	context.put("totalTanahRizab",Utils.formatLuas(totalTanahRizab));
    	context.put("totalKeseluruhan",Utils.formatLuas(totalKeseluruhan));
	}
	
	@SuppressWarnings("rawtypes")
	public void getDataHakmilik(Vector listDataHakmilik,FrmKjpRekodDB rekodDb,String idHakmilik) throws Exception{
		
		String nama_negeri = "",nama_kementerian = "",nama_agensi = "",no_fail = "",no_rujukan_kjp = "",no_rujukan_ptg = "",tajuk_fail = "" , status = "";
		String nama_daerah = "",nama_mukim = "",jenis_hakmilik = "",kod_jenis_hakmilik = "",no_hakmilik = "",jenis_lot = "",no_lot = "", no_pu = "";
		
		String status_sah = "",hakmilik_asal = "",kegunaan_tanah = "",no_pelan = "",no_syit = "",no_warta = "";
		String jenis_rizab = "",kategori = "",syarat_nyata = "",sekatan = "",catatan = "",tarikh_terima = "",tarikh_daftar = "",tarikh_warta = "";
		Double cukai = 0.00, cukai_terkini = 0.00, luas_bersamaan = 0.00;
		
		listDataHakmilik = rekodDb.getDBDataHakmilik(idHakmilik);
		if(listDataHakmilik.size()!=0){
			Hashtable dn = (Hashtable)listDataHakmilik.get(0);
			nama_kementerian = (String)dn.get("nama_kementerian");
			nama_agensi = (String)dn.get("nama_agensi");
			no_fail = (String)dn.get("no_fail");
			no_rujukan_kjp = (String)dn.get("no_rujukan_kjp");
			no_rujukan_ptg = (String)dn.get("no_rujukan_ptg");
			tajuk_fail = (String)dn.get("tajuk_fail");
			nama_daerah = (String)dn.get("nama_daerah");
			nama_mukim = (String)dn.get("nama_mukim");
			nama_negeri = (String)dn.get("nama_negeri");
			jenis_hakmilik = (String)dn.get("jenis_hakmilik");
			kod_jenis_hakmilik = (String)dn.get("kod_jenis_hakmilik");
			no_hakmilik = (String)dn.get("no_hakmilik");
			jenis_lot = (String)dn.get("jenis_lot");
			no_lot = (String)dn.get("no_lot");
			status_sah = (String)dn.get("status_sah");
			hakmilik_asal = (String)dn.get("hakmilik_asal");
			cukai = (Double)dn.get("cukai");
			cukai_terkini = (Double)dn.get("cukai_terkini");
			kegunaan_tanah = (String)dn.get("kegunaan_tanah");
			luas_bersamaan = (Double)dn.get("luas_bersamaan");
			no_pelan = (String)dn.get("no_pelan");
			no_syit = (String)dn.get("no_syit");
			no_warta = (String)dn.get("no_warta");
			no_pu = (String)dn.get("no_pu");
			jenis_rizab = (String)dn.get("jenis_rizab");
			kategori = (String)dn.get("kategori");
			syarat_nyata = (String)dn.get("syarat_nyata");
			sekatan = (String)dn.get("sekatan");
			catatan = (String)dn.get("catatan");
			tarikh_terima = (String)dn.get("tarikh_terima");
			tarikh_daftar = (String)dn.get("tarikh_daftar");
			tarikh_warta = (String)dn.get("tarikh_warta");
			status = (String)dn.get("status");
		}
		
		context.put("nama_kementerian", nama_kementerian);
		context.put("nama_agensi", nama_agensi);
		context.put("no_fail", no_fail);
    	context.put("no_rujukan_kjp", no_rujukan_kjp);
    	context.put("no_rujukan_ptg", no_rujukan_ptg);
		context.put("tajuk_fail", tajuk_fail);
		context.put("nama_daerah", nama_daerah);
    	context.put("nama_mukim", nama_mukim);
    	context.put("nama_negeri",nama_negeri );
		context.put("jenis_hakmilik", jenis_hakmilik);
		context.put("kod_jenis_hakmilik", kod_jenis_hakmilik);
    	context.put("no_hakmilik", no_hakmilik);
    	context.put("jenis_lot", jenis_lot);
		context.put("no_lot", no_lot);
		context.put("status_sah", status_sah);
    	context.put("hakmilik_asal", hakmilik_asal);
    	context.put("cukai", Utils.format2Decimal(cukai));
    	context.put("cukai_terkini", Utils.format2Decimal(cukai_terkini));
		context.put("kegunaan_tanah", kegunaan_tanah);
    	context.put("luas_bersamaan", Utils.formatLuas(luas_bersamaan));
    	context.put("no_pelan", no_pelan);
		context.put("no_syit", no_syit);
		context.put("no_warta", no_warta);
		context.put("no_pu", no_pu);
    	context.put("jenis_rizab", jenis_rizab);
    	context.put("kategori", kategori);
		context.put("syarat_nyata", syarat_nyata);
		context.put("sekatan", sekatan);
    	context.put("catatan", catatan);
    	context.put("tarikh_terima", tarikh_terima);
		context.put("tarikh_daftar", tarikh_daftar);
		context.put("tarikh_warta", tarikh_warta);
		context.put("status", status);
	}
	
	public void getLuasPTPByKementerianDetail(Vector<Hashtable<String,String>> listDbPTPByKementerianDetail,FrmKjpRekodDB rekodDb,String idKementerian, HttpSession session,String action) throws Exception{	
		String namaKementerian = "";
		Vector<Hashtable<String,String>> listDbDataKementerian = rekodDb.getDBDataKementerian(idKementerian);
		if(listDbDataKementerian.size()!=0){
			Hashtable<String,String> dn = (Hashtable<String,String>)listDbDataKementerian.get(0);
			namaKementerian = (String)dn.get("nama_kementerian");
		}
		
		listDbPTPByKementerianDetail = rekodDb.getDBLuasPTPByKementerianDetail(idKementerian);
    	Double totalTanahMilik = 0.00;
    	Double totalTanahRizab = 0.00;
    	Double totalKeseluruhan = 0.00;
    	if(listDbPTPByKementerianDetail.size()!=0){
	    	for (int i = 0; i < listDbPTPByKementerianDetail.size(); i++) { 
	    		Hashtable<String,String> ldb = (Hashtable<String,String>)listDbPTPByKementerianDetail.get(i);
	    		totalTanahMilik += Double.parseDouble(String.valueOf(ldb.get("luas_tanah_milik")));
	    		totalTanahRizab += Double.parseDouble(String.valueOf(ldb.get("luas_tanah_rizab")));
	    		totalKeseluruhan += Double.parseDouble(String.valueOf(ldb.get("luas_keseluruhan")));
	    		
			}
    	}
    	context.put("listDbPTPByKementerianDetail",listDbPTPByKementerianDetail);
    	context.put("totalTanahMilik",Utils.formatLuas(totalTanahMilik));
    	context.put("totalTanahRizab",Utils.formatLuas(totalTanahRizab));
    	context.put("totalKeseluruhan",Utils.formatLuas(totalKeseluruhan));
    	context.put("namaKementerian", namaKementerian);
    	
	}
	
	public void getLuasPTPByAgensiDetail(Vector<Hashtable<String,String>> listDbPTPByAgensiDetail,FrmKjpRekodDB rekodDb,String idAgensi, HttpSession session,String action) throws Exception{		
		String namaAgensi = "";
		String namaKementerian = "";
		Vector<Hashtable<String,String>> listDbDataAgensi = rekodDb.getDBDataAgensi(idAgensi);
		if(listDbDataAgensi.size()!=0){
			Hashtable<String,String> dn = (Hashtable<String,String>)listDbDataAgensi.get(0);
			namaAgensi = (String)dn.get("nama_agensi");
			namaKementerian = (String)dn.get("nama_kementerian");
		}
		
		listDbPTPByAgensiDetail = rekodDb.getDBLuasPTPByAgensiDetail(idAgensi);
    	Double totalLuas = 0.00;
    	if(listDbPTPByAgensiDetail.size()!=0){
	    	for (int i = 0; i < listDbPTPByAgensiDetail.size(); i++) { 
	    		Hashtable<String,String> ldb = (Hashtable<String,String>)listDbPTPByAgensiDetail.get(i);
	    		totalLuas += Double.parseDouble(String.valueOf(ldb.get("luas")));
	    		
			}
    	}
    	context.put("listDbPTPByAgensiDetail",listDbPTPByAgensiDetail);
    	context.put("totalLuas",Utils.formatLuas(totalLuas));
    	context.put("namaAgensi", namaAgensi);
    	context.put("namaKementerian", namaKementerian);
    	
	}
	
	
}//close class

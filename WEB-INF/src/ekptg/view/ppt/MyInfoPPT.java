
package ekptg.view.ppt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.FrmPermohonanUPTOnlineData;
import ekptg.model.ppt.MyInfoPPTData;
/**
 * @author Razman/Elly/syazreen
 *
 */

@SuppressWarnings("serial")
public class MyInfoPPT extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(MyInfoPPT.class);
	FrmPembatalanInternalData logic1 = new FrmPembatalanInternalData();
	FrmPermohonanUPTOnlineData modelOnline = new FrmPermohonanUPTOnlineData();
	
	MyInfoPPTData logic = null;
	@SuppressWarnings("unchecked")
	List list = null;
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception {
		
		logic = new MyInfoPPTData();
			
		HttpSession session = this.request.getSession();
		
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String UserRole = (String) session.getAttribute("myrole");
		context.put("UserRole",UserRole);
		String main_command = getParam("command");
		
		String vm = ""; 
		String action = getParam("action"); 
		String flagAdvSearch = getParam("flagAdvSearch");
		
		list = Collections.synchronizedList(new ArrayList());
		
		
		
		Vector list_status = null;
		//Vector list_negeri = null;
		Vector list_kementerian = null;
		//Vector jenis_hakmilik = null;
		//Vector list_pegawai = null;
		Vector checkEmail = null;
		Vector listing_online_penarikan = null;
		Vector listing_online_pembatalan = null;
		Vector listing_online_permohonan = null;
		Vector listing_online_bantahan_pb = null;
		Vector listing_online_bantahan_agensi = null;
		Vector listFailExpired = new Vector();
		Vector listPenarikanExpired = new Vector();
		Vector listPembatalanExpired = new Vector();
		
		listPembatalanExpired.clear();	
		listPenarikanExpired.clear();
		listFailExpired.clear();
		
		//get data
		String no_fail = getParam("no_fail");
		String no_hakmilik = getParam("no_hakmilik");
		String id_jenishakmilik = getParam("socJenisHakmilik");		
		String no_lot_pt = getParam("no_lot_pt");	
		String nama_pb = getParam("nama_pb");
		String no_pb = getParam("no_pb");
		String no_akaun = getParam("no_akaun");
		String no_kes = getParam("no_kes");
		String no_siasatan = getParam("no_siasatan");
		String no_prosiding = getParam("no_prosiding");
		String tarikh_permohonan = getParam("tarikh_permohonan");
		String tarikh_permohonan_kjp = getParam("tarikh_permohonan_kjp");
		String id_status = getParam("id_status_permohonan");
		//String id_negeri = getParam("id_negeri_permohonan");
		String id_negeri = getParam("socNegeri");
		String id_pegawai = getParam("socPegawai");		
		String socJenisKeputusan = getParam("socJenisKeputusan");
		String flagJenisPampasan = getParam("socflagJenisPampasan");		
		String id_kementerian = getParam("id_kementerian");
		String no_permohonan = getParam("no_permohonan");
		String namaprojek = getParam("namaprojek");
		String id_tahun = getParam("socTahun");
		String socTahun = getParam("socTahun");
		
		String bolehsimpan = "";
		//String readmode = "";
		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		myLogger.info("BOLEH SIMPAN :"+bolehsimpan);

		//screen
		vm = "app/ppt/MyInfoPPT.jsp";
		
		if(id_tahun.equals("")){
			id_tahun = "";
		}else{
			id_tahun = getParam("socTahun");
		}
	
		//check dah isi emel ke belum
		context.put("showEmelAlert", "yes");
		checkEmail = logic.checkEmail(userId);
		if(checkEmail.size()!=0){
			context.put("showEmelAlert", "no");
		}else{
			context.put("showEmelAlert", "yes");
		}
		
		//myLogger.info("id_tahun--- "+id_tahun);
		
		//get fail tamat tempoh
		listFailExpired = modelOnline.getListFailExpiredUnit(negeriUser);    		
		listPenarikanExpired = modelOnline.getListPenarikanExpiredUnit(negeriUser);   
		listPembatalanExpired = modelOnline.getListPembatalanExpiredUnit(negeriUser);   
		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
		
		if ("hapus_fail".equals(main_command)){
			
			String listFail = "''";
			String[] ids1 = this.request.getParameterValues("ids1");
			if (ids1 != null) {
				for (int i = 0; i < ids1.length; i++) {	
						listFail = listFail + "," + ids1[i];
										
					}
				}
			
			myLogger.info("listFail:::"+listFail);
			if (bolehsimpan.equals("yes")) 
			{
				logic1.deleteFail(listFail, session);
			}
		}
		
		else if ("hapus_fail_online".equals(main_command)){
			
			if (bolehsimpan.equals("yes")){
				logic1.deleteFail(getParam("id_fail"), session);
			}
			
			//get fail tamat tempoh
			listFailExpired = modelOnline.getListFailExpiredUnit(negeriUser);  
		}
		
		else if ("hapus_fail_online_penarikan".equals(main_command)){
			
			if (bolehsimpan.equals("yes")){
				logic1.deleteFailPenarikan(getParam("id_penarikanbalik"));
			}
			
			//get fail tamat tempoh
			listPenarikanExpired = modelOnline.getListPenarikanExpiredUnit(negeriUser);  
		}
		
		else if ("hapus_fail_online_pembatalan".equals(main_command)){
			
			if (bolehsimpan.equals("yes")){
				logic1.deleteFailPembatalan(getParam("id_pembatalan"));
			}
			
			//get fail tamat tempoh
			listPembatalanExpired = modelOnline.getListPembatalanExpiredUnit(negeriUser); 
		}
		
		//data fail tamat tempoh
		context.put("listFailExpired",listFailExpired);
		context.put("listPenarikanExpired",listPenarikanExpired);
		context.put("listPembatalanExpired",listPembatalanExpired);
		context.put("typeList","internal");
		
		this.context.put("JumlahFail",logic.totalFail(session));	
		
	    list = logic.carianFail(session,no_fail,no_hakmilik,id_jenishakmilik,no_lot_pt,nama_pb,no_pb,no_akaun,no_kes,no_siasatan
	    						,no_prosiding,tarikh_permohonan,id_status,id_kementerian,no_permohonan,namaprojek,id_negeri,id_pegawai,socJenisKeputusan,flagJenisPampasan,socTahun);
		this.context.put("SenaraiFail", list);			
		
		listing_online_bantahan_agensi = logic.listing_online_bantahan_agensi(session);
		this.context.put("listing_online_bantahan_agensi", listing_online_bantahan_agensi);	
		
		listing_online_bantahan_pb = logic.listing_online_bantahan_pb(session);
		this.context.put("listing_online_bantahan_pb", listing_online_bantahan_pb);	
		
		
		listing_online_penarikan = logic.listing_online_penarikan(session);
		this.context.put("listing_online_penarikan", listing_online_penarikan);	
		
		listing_online_pembatalan = logic.listing_online_pembatalan(session);
		this.context.put("listing_online_pembatalan", listing_online_pembatalan);
		
		listing_online_permohonan = logic.listing_online_permohonan(session);
		this.context.put("listing_online_permohonan", listing_online_permohonan);
		
		
		
		
		//list_pegawai = logic.list_pegawai(session);
		//this.context.put("list_pegawai",list_pegawai);
		
		list_status = logic.list_status();
		this.context.put("list_status",list_status);
		
		//list_negeri = logic.list_negeri();
		//this.context.put("list_negeri",list_negeri);
		context.put("selectTahun",HTML.SelectTahun("socTahun",id_tahun, null, "style=width:130px"));
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:auto"));		
		context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeri(negeriUser,"socPegawai",Utils.parseLong(id_pegawai),null,"style=width:auto"));
		
		list_kementerian = logic1.list_kementerian();
		this.context.put("list_kementerian",list_kementerian);
		
		if(negeriUser.equals("10")){
			//jenis_hakmilik = logic.jenis_hakmilik_selangor();
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik style=width:auto")); 
		}else{
			//jenis_hakmilik = logic1.jenis_hakmilik();
			context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(id_jenishakmilik),"id=socJenisHakmilik style=width:auto"));
		}
		
		//this.context.put("jenis_hakmilik",jenis_hakmilik);
		
		
		//set data	
		
		this.context.put("socJenisKeputusan", socJenisKeputusan);
		this.context.put("flagJenisPampasan", flagJenisPampasan);
		
		this.context.put("no_permohonan", no_permohonan.trim());
		this.context.put("no_fail", no_fail.trim());
		this.context.put("namaprojek", namaprojek.trim());
		this.context.put("no_hakmilik", no_hakmilik.trim());
		this.context.put("id_jenishakmilik", id_jenishakmilik);
		this.context.put("no_lot_pt", no_lot_pt.trim());
		this.context.put("nama_pb", nama_pb.trim());
		this.context.put("no_pb", no_pb.trim());
		this.context.put("no_akaun", no_akaun.trim());
		this.context.put("no_kes", no_kes.trim());
		this.context.put("no_siasatan", no_siasatan.trim());
		this.context.put("no_prosiding", no_prosiding.trim());
		this.context.put("tarikh_permohonan", tarikh_permohonan);
		//this.context.put("id_pegawai_pendaftar", id_pegawai);
		this.context.put("id_status_permohonan", id_status);
		//this.context.put("id_negeri_permohonan", id_negeri);
		this.context.put("id_kementerian", id_kementerian);
		this.context.put("flagAdvSearch", flagAdvSearch);
		this.context.put("itemsPerPage",null);
		
		this.context.put("socTahun", socTahun);
		
		setupPage(session,action,list);
		
		return vm;
	}
	
}

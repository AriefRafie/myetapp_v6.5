package ekptg.view.htp;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikSementaraData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.rekod.FrmRekodUtilData;

public class FrmPendaftaranHakmilikPembelian extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private final String PATH="app/htp/rekod/";
	private static Logger log = Logger.getLogger(ekptg.view.htp.FrmPendaftaranHakmilikPembelian.class);
	//FrmUtilData frmUtilData = null;
	FrmRekodUtilData frmRekodUtilData = null;
	String action = "";
	String mode = "";
	String firstAction ="";
	String vm = "";
	
	String idKementerian = "";
	String idAgensi = "";
	private String idnegeri = "";
 	private String iddaerah = "";
 	private String idmukim = "";
 	private String idJenisHakmilik = "";
 	private String idHakmilik = "";
 	String userId = "";
	public String doTemplate2() throws Exception {
		
		//frmUtilData = FrmUtilData.getInstance();
		frmRekodUtilData = FrmRekodUtilData.getInstance();
		
		String submit = getParam("command");
		this.context.put("action","");
		action = getParam("action");
		this.context.put("INS_UPD",getParam("INS_UPD"));
		String idRizab = getParam("socRizab");
		mode = getParam("mode");
		String socKementerian = "";
		
		idnegeri = getParam("socNegeri");
	 	iddaerah = getParam("socDaerah");
	 	idmukim = getParam("socMukim");

		idKementerian = getParam("socKementerian");
		idAgensi = getParam("socAgensi");

		String firstAction = getParam("firstAction");
		this.context.put("firstAction",firstAction);

		String idJenisHakmilikBaru = getParam("socJenisHakmilikBaru");
		if (idJenisHakmilikBaru == null || idJenisHakmilikBaru.trim().length() == 0){
			idJenisHakmilikBaru = "99999";
		}
 	
		Vector list =null;
		HttpSession session = this.request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		userId = (String)session.getAttribute("_ekptg_user_id");
		String noFail = getParam("txtNoFail");
		String txtTajukFail = getParam("txtTajukFail");
		String noHakmilik = getParam("txtNoHakmilikC");
		String noWarta = getParam("txtNoWarta");
		String noLot = getParam("txtNoLotC");
		
		Vector listSambungan = null;
		
		String noHakmilikAsal = getParam("txtNoHakmilik");
		this.context.put("modePopup", "");

		log.info("command:"+submit +","+"mode:"+mode);
		
		idHakmilik = getParam("idHakmilik");
		
		//Override submit - if user change tukar paparan mukasurat 
		if ("doChanges".equals(submit) || "record_listing".equals(submit)) {
			submit = "";
		}
		if ("".equals(submit)){
			vm = PATH+"frmPendaftaranHakmilikRizabSenarai.jsp";
	
			if ("".equals(idKementerian)) idKementerian="-1";
			if ("".equals(idAgensi)) idAgensi="-1";
			if ("".equals(idnegeri)||"0".equals(idnegeri)) idnegeri = "-1";
	    	if ("".equals(iddaerah)) iddaerah = "-1";
	    	if ("".equals(idmukim)) idmukim = "-1";

			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"",""));

			//this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			this.context.put("socKementerian",HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), null,"onChange=\"doChangeKementerian()\" "));
			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "", ""));

			//senarai fail
			if ("carian".equals(mode)) {
		    	list = frmRekodUtilData.senaraiFailMengikutCarian(null,noFail,txtTajukFail,
		    			idKementerian,idAgensi,
		    			idnegeri,iddaerah,idmukim,"-1");
		    }else{
		    	list = frmRekodUtilData.getHakmilikUrusanMengikutUrusan("1,10,2,5");
		    }
			if ("doChangeState".equals(action)) {
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerah();\""));
			}
			if ("doChangeDaerah".equals(action)) {
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"",""));
			}
			//this.context.put("SenaraiHakmilik", list);
			this.context.put("txtNoFail",noFail);	
			this.context.put("txtTajukFail",txtTajukFail);		
			this.context.put("txtNoHakmilik",noHakmilik);			
			this.context.put("SenaraiHakmilik", list); 
		}else if ("paparDetailHakmilik2".equals(submit)){	//VIEW HAKMILIK BY ID
			vm = PATH+"frmPendaftaranTerimaHakmilik.jsp";
			doPaparDetailHakmilik2(list);
		}else if ("updateDetailHakmilik2".equals(submit)){
			//method deleted
			//submit=update, tapi kita nak insert sebenarnya...huhu
			//vm = PATH+"frmPendaftaranTerimaHakmilik.jsp";
			//doUpdateDetailHakmilik2();
		}else if ("kemaskiniDetailHakmilik".equals(submit)) {
			vm = PATH+"frmPendaftaranTerimaHakmilik.jsp";
			dokemaskiniDetailHakmilik();
		}else if ("paparDetailRizab2".equals(submit)){			  
			vm = PATH+"frmPendaftaranTerimaRizab.jsp";
			this.context.put("readOnly", "");
			this.context.put("disabled", "");	
			//this.context.put("mode", "update");
			String idPermohonan = getParam("idPermohonan"); //translator for idpermohonan	
			//JIKA MAKLUMAT RIZAB DI DALAM TBLHTPHAKMILIK SUDAH ADA
			if(frmRekodUtilData.isHTPHakmilik(idPermohonan)){
				vm = PATH+"frmPendaftaranTerimaRizab.jsp";
				this.context.put("readOnly", "readonly");
				String dis =  "disabled";
				this.context.put("disabled", dis);	
				this.context.put("mode", "view");

				list = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idPermohonan);
				viewMaklumatRizab(list,dis,submit);
				this.context.put("SenaraiTanah", list);
				return vm;
			
			//JIKA MAKLUMAT RIZAB YANG BARU, BERSEDIA UNTUK DISIMPAN
			}else{
					// VIEW MAKLUMAT FAIL/RIZAB(JIKA ADA)
					log.info("firstAction:"+firstAction);
					this.context.put("readOnly", "");
					this.context.put("disabled", "");	
					this.context.put("mode", "update");
					getMaklumatRizabAwal(session,idPermohonan,submit);
					String idDaerah = getParam("socDaerah");
				 	String idMukim = getParam("socMukim");
				 	if ("doChangeDaerahRizab".equals(mode)) {
						this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah,"socMukim", Utils.parseLong(idMukim)," style=\"width:300px\""));  
						//this.context.put("txtNamaMukim",HTML.SelectMukim("socMukim",Long.parseLong(maklumatRizab.get("IdMukim").toStr/ing()),""));
					}
					this.context.put("txtNoWarta", getParam("txtNoWarta") );

			}
			this.context.put("SenaraiHakmilik", list); 
		
		 
				
 		}else if ("simpanRizab".equals(submit)){
			 vm = PATH+"frmPendaftaranTerimaRizab.jsp";

			 Hashtable rizab = new Hashtable();
			 String idPermohonan = getParam("idPermohonan");
			 String idHakmilik = getParam("idHakmilik");
			 
			 rizab.put("idKementerian", getParam("txtIdKementerian"));	//TBLHTPHAKMILIKAGENSI
			 rizab.put("idAgensi", getParam("txtIdAgensi"));	//TBLHTPHAKMILIKAGENSI
			 rizab.put("txtNoFail", getParam("txtNoFail"));
			 rizab.put("txtFailKJP", getParam("txtFailKJP"));
			 rizab.put("txtFailPTG", getParam("txtFailPTG"));
			 rizab.put("txtFailPTD", getParam("txtFailPTD"));
			 rizab.put("socNegeriHR", getParam("socNegeri"));
			 rizab.put("socDaerahHR", getParam("socDaerah"));
			 rizab.put("socMukimHR", getParam("socMukim"));
			 rizab.put("txtNoWarta", getParam("txtNoWarta"));
			 rizab.put("socLot", getParam("socLot"));
			 rizab.put("txtNoLotR", getParam("txtNoLot"));
			 rizab.put("txdTarikhWarta", getParam("txdTarikhWarta"));
			 rizab.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			 rizab.put("txtLokasi", getParam("txtLokasi"));			
			 rizab.put("txtKegunaanTanah", getParam("txtKegunaanTanah")); // TBLHTPHAKMILIKPERIHAL			 
			 rizab.put("socLuas", getParam("socLuas"));
			 rizab.put("txtLuasLama", getParam("txtLuasGabung"));
			 // id_luas baru (default 2-HEKTAR)
			 //rizab.put("txtLuas", getParam("txtLuas"));
			 rizab.put("txtLuasBersamaan", getParam("txtLuas"));
			 rizab.put("txtNoPelan", getParam("txtNoPelan"));
			 rizab.put("txtNoSyit", getParam("txtNoSyit"));
			 rizab.put("txtNoPu", getParam("txtNoPu"));

			 rizab.put("idHakmilik", idHakmilik);
			 rizab.put("idPermohonan", idPermohonan);
			 rizab.put("socStatus", "D");
			 rizab.put("idMasuk", userId);
			 rizab.put("idKemaskini", userId);
			 
			 //kena tambah diskrin
			 //rizab.put("socLotHR", getParam("noLot"));
			 //rizab.put("socLotHR", 1);
			 
			 log.info("Ready To INSERT");
			 log.info("idPermohonan"+ getParam("idPermohonan"));
			 log.info("socNegeriHR"+ getParam("socNegeri"));
			  log.info("socDaerahHR"+ getParam("socDaerah"));
			  log.info("socMukimHR"+ getParam("socMukim"));
			  log.info("txtNoWarta"+ getParam("txtNoWarta"));
			  log.info("txdTarikhWarta"+ getParam("txdTarikhWarta"));
			  log.info("txdTarikhTerima"+ getParam("txdTarikhTerima"));
			  log.info("txtLokasi"+ getParam("txtLokasi"));			
			  log.info("txtKegunaanTanah"+ getParam("txtKegunaanTanah")); // TBLHTPHAKMILIKPERIHAL			 
			  log.info("socLuas"+ getParam("socLuas"));
			  log.info("txtLuas"+ getParam("txtLuasGabung"));
			  log.info("txtLuasBersamaan"+ getParam("txtLuas"));
			  log.info("txtNoSyit"+ getParam("txtNoSyit"));
			  log.info("txtNoPelan"+ getParam("txtNoPelan"));
			  log.info("txtNoPu"+ getParam("txtNoPu"));
			  log.info("socStatus"+ "D");
			  log.info("idKementerian"+ getParam("txtIdKementerian"));	//TBLHTPHAKMILIKAGENSI
			  log.info("idAgensi"+ getParam("txtIdAgensi"));	//TBLHTPHAKMILIKAGENSI
			 
			 //kena tambah diskrin
			 //rizab.put("socLotHR"+ getParam("noLot"));
			  log.info("socLotHR"+ 1);
			  log.info("txtNoLot"+ getParam("txtNoLot"));
			  
			  if(frmRekodUtilData.isHTPHakmilik(idPermohonan)){
				  frmRekodUtilData.kemaskiniRizabMengikutId(rizab);
			  }else{
				  //AZAM CHANGE TO TRANSACTION
				  /*
				  idHakmilik = frmRekodUtilData.insertHTPRizab(rizab);
				  FrmUtilData.insertHakmilikPerihal(rizab,idHakmilik);
				  FrmUtilData.insertHakmilikAgensi(rizab,idHakmilik);
				  */
				  FrmUtilData.XinsertHTPRizabTransaction(rizab,userId);
			  }
//			 //VIEW SEMULA RIZAB YANG DISIMPAN

			 this.context.put("readOnly", "readonly");
		 	 //this.context.put("disabled", "disabled");	
			 //this.context.put("mode", "view");
					
			 // VIEW HEADER(MASTER) BY ID
			 //viewModeMaklumatFail(session,idHakmilik);
// 					
//			 // VIEW MAKLUMAT RIZAB BY ID
			 //viewModeHakmilikRizab(idHakmilik,session,"kemaskiniDetailRizab","doChangeDaerahHR",getParam("socNegeri"),getParam("socDaerah"),getParam("socMukim"),"0","0");
		 
				String dis =  "disabled";
				this.context.put("disabled", dis);	
				this.context.put("mode", "view");

				list = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(getParam("idPermohonan"));
				viewMaklumatRizab(list,dis,submit);
			 
		} else if ("kemaskiniMaklumatRizab".equals(submit)){ //KEMASKINI DETAIL MAKLUMAT RIZAB
				
			    vm = PATH+"frmPendaftaranTerimaRizab.jsp";
			    String idPermohonan = getParam("idPermohonan");
			    String dis = "";
				this.context.put("readonly", "");
				this.context.put("disabled", dis);	
				this.context.put("mode", "update");
				
				// VIEW HEADER(MASTER) BY ID
				//view_modeMaklumatFail(session);
				list = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idPermohonan);
				viewMaklumatRizab(list,dis,submit);
				this.context.put("SenaraiTanah", list);


				// VIEW MAKLUMAT RIZAB BY ID
				//view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
		
		}else if("tambahHakmilik".equals(submit))
			if ("tambahHakmilikBaru".equals(firstAction)){//VIEW HAKMILIK BY ID 
				vm = PATH+"frmPendaftaranTerimaHakmilikBaru.jsp";
				this.context.put("modePopup", "openSambungan");
				this.context.put("socStatus", "S");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("readOnly2", "");
				this.context.put("disabled2", "");
				this.context.put("mode", "simpanBaru");
				this.context.put("selectJenisHakmilikBaru", HTML.SelectJenisHakmilik("socJenisHakmilikBaru", Utils.parseLong(idJenisHakmilikBaru), " style=\"width:200px\""));
				this.context.put("txtHakmilikBerikut", "");
				//view_modeHakmilikRizab(session, submit);
				view_modeMaklumatFail(session);
				
			    view_modeHakmilikSementara(session);
				//listSambungan = view_modeSenaraiHakmilikSambungan(session, noHakmilikAsal);
			    String idPermohonan = getParam("idpermohonan");
				listSambungan = frmRekodUtilData.getCarianSenaraiHakmilikRizabById(idPermohonan, null, null, null, null, null, null, null, null, null, null, null, null, null);
				this.context.put("listSambungan", listSambungan);
		
			}else
			//DAFTAR HAMILIK BARU
			if("daftarHakmilik".equals(firstAction)){
				vm = PATH+"frmPendaftaranTerimaHakmilikBaru.jsp";
				this.context.put("sambung","sambung");	
				this.context.put("txtHakmilikBerikut","");
				
				//CREATE FAIL BARU BASE ON DATA LAME 
				Hashtable hHakmilikBaru = new Hashtable();
				hHakmilikBaru.put("idHakmilik", getParam("idHakmilik"));
				hHakmilikBaru.put("socNegeriHR", getParam("socNegeriHR"));
				hHakmilikBaru.put("socDaerahHR", getParam("socDaerahHR"));
				hHakmilikBaru.put("socMukimHR", getParam("socMukimHR"));
				hHakmilikBaru.put("socJenisHakmilikHR", getParam("socJenisHakmilikHR"));
				hHakmilikBaru.put("txtNoHakmilik", getParam("txtNoHakmilik"));	
				hHakmilikBaru.put("socLotHR", getParam("socLotHR"));
				hHakmilikBaru.put("txtNoLot", getParam("txtNoLot"));
				hHakmilikBaru.put("txdTarikhTerima", getParam("txdTarikhTerima"));
				hHakmilikBaru.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
				hHakmilikBaru.put("txtNoBangunan", getParam("txtNoBangunan"));
				hHakmilikBaru.put("txtNoTingkat", getParam("txtNoTingkat"));
				hHakmilikBaru.put("txtNoPetak", getParam("txtNoPetak"));
				hHakmilikBaru.put("txtCukaiTahun", getParam("txtCukaiTahun"));
				hHakmilikBaru.put("txtLokasi", getParam("txtLokasi"));	
				hHakmilikBaru.put("socLuas", getParam("socLuas"));
				hHakmilikBaru.put("txtLuas", getParam("txtLuas"));
				hHakmilikBaru.put("socTaraf", getParam("socTaraf"));
				hHakmilikBaru.put("socKategori", getParam("socKategori"));			
				hHakmilikBaru.put("txtNoPelan", getParam("txtNoPelan"));
				hHakmilikBaru.put("txtTempoh", getParam("txtTempoh"));			
				hHakmilikBaru.put("txtSyarat", getParam("txtSyarat"));
				hHakmilikBaru.put("txtNoFailJopa", getParam("txtNoFailJopa"));			
				hHakmilikBaru.put("txtHakmilikAsal", getParam("txtHakmilikAsal"));
				hHakmilikBaru.put("txtCukaiTerkini", getParam("txtCukaiTerkini"));
				hHakmilikBaru.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
				hHakmilikBaru.put("txtTarafHakmilik", getParam("txtTarafHakmilik"));
				hHakmilikBaru.put("txdTarikhLuput", getParam("txdTarikhLuput"));			
				hHakmilikBaru.put("txtNoPu", getParam("txtNoPu"));
				hHakmilikBaru.put("txdTarikhWarta", getParam("txdTarikhWarta"));
				hHakmilikBaru.put("txtKawasanRizab", getParam("txtKawasanRizab"));
				hHakmilikBaru.put("txtNoSyit", getParam("txtNoSyit"));
				hHakmilikBaru.put("txtNoWarta", getParam("txtNoWarta"));
				hHakmilikBaru.put("txtSekatan", getParam("txtSekatan"));
				hHakmilikBaru.put("txtSyarat", getParam("txtSyarat"));
				hHakmilikBaru.put("txtKawasanRizab", getParam("txtKawasanRizab"));
				hHakmilikBaru.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
				hHakmilikBaru.put("socStatus", getParam("socStatus"));
				hHakmilikBaru.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));			
				hHakmilikBaru.put("socJenisRizab", getParam("socJenisRizab"));	
				hHakmilikBaru.put("socRizab", getParam("socRizab"));	
				//hHakmilikBaru.put("socRizab", (("".equals( getParam("socRizab")))?getParam("socRizab"):"X"));	
				hHakmilikBaru.put("idKemaskini",session.getAttribute("_ekptg_user_id").toString());
				
				
				//CREATE FAIL BARU BASE ON NEW INPUT
				hHakmilikBaru.put("socJenisHakmilikBaru", getParam("socJenisHakmilikBaru"));
				hHakmilikBaru.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
				FrmRekodPendaftaranHakmilikSementaraData.addHakmilikBaruById(hHakmilikBaru);
				
				//VIEW HAKMILIK ASAL
				view_modeHakmilikRizab(session,submit);
				
				//LIST HAKMILIK SAMBUNGAN
				listSambungan = view_modeSenaraiHakmilikSambungan(session,noHakmilikAsal);
				this.context.put("listSambungan",listSambungan);
				
				this.context.put("txtHakmilikBerikut", "");
				/**/
				
		}else{
			vm = PATH+"frmPendaftaranHakmilikRizabSenarai.jsp";
		    //String nofail = getParam("nofail");
		 	//String txtTajukFail = getParam("txtTajukFail");
		 	//String idnegeri = getParam("socNegeri");
		 	//String iddaerah = getParam("socdaerah");
		 	//String idmukim = getParam("socMukim");

			if ("".equals(idKementerian)) idKementerian="-1";
	    	if ("".equals(idnegeri)||"0".equals(idnegeri)) idnegeri = "-1";
	    	if ("".equals(iddaerah)) iddaerah = "-1";
	    	if ("".equals(idmukim)) idmukim = "-1";
			

			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukim("socMukim", Utils.parseLong(idmukim),null));
			//this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik), ""));
			this.context.put("socKementerian",socKementerian = HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), null,"onChange=\"doChangeKementerian()\" "));
		    	
	    	if ("carian".equals(mode)) {
		    	list = frmRekodUtilData.senaraiFailMengikutCarian(null,noFail,txtTajukFail,
		    			idKementerian,idAgensi,
		    			idnegeri,iddaerah,idmukim,"-1");
		    } else {
		    	list = frmRekodUtilData.getHakmilikUrusanMengikutUrusan("2");		    
		    }
			this.context.put("txtNoHakmilik", "");			
			this.context.put("SenaraiHakmilik", list); 
		    this.context.put("txtNoFail",noFail);
		    this.context.put("txtTajukFail",txtTajukFail);
	
		}
		if (list != null) {
			setupPage(session,action,list);
		}

		return vm;
	}
	
	private void view_modeHakmilik1(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(idHakmilik);
	}
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeHakmilik(HttpSession session) throws Exception {
		String id = getParam("idHakmilik");	
		Hashtable hMaklumatFail = (Hashtable)FrmUtilData.getHakmilikUrusan("0",id);
		//Hashtable hMaklumatFail = (Hashtable) list.get(0);
		
		this.context.put("txtFailPTG",(String)hMaklumatFail.get("noFailPtg"));
		this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
		this.context.put("txtNamaKementerian", (String)hMaklumatFail.get("namaKementerian"));
		this.context.put("txtNoFailSeksyen", (String)hMaklumatFail.get("noFailSeksyen"));
		//this.context.put("txtNamaNegeri", (String)hMaklumatFail.get("namaNegeri"));
		this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(hMaklumatFail.get("IdNegeri").toString()),""));
		//this.context.put("txtNamaDaerah", (String)hMaklumatFail.get("namaDaerah"));
		this.context.put("txtNamaDaerah",HTML.SelectDaerah("socDaerah",Long.parseLong(hMaklumatFail.get("IdDaerah").toString()),""));
		//this.context.put("txtNamaMukim", (String)hMaklumatFail.get("namaMukim"));
		this.context.put("txtNamaMukim",HTML.SelectMukim("socMukim",Long.parseLong(hMaklumatFail.get("IdMukim").toString()),""));
		this.context.put("txtNamaAgensi", (String)hMaklumatFail.get("namaAgensi"));
		//this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
	   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong( (String)hMaklumatFail.get("IdJenisHakmilik")), "style=\"width:200px\""));

		this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
		this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
		this.context.put("txtNoHakmilik",(String) hMaklumatFail.get("noHakmilik"));
        this.context.put("selectLot", HTML.SelectLot("noLot",Utils.parseLong( (String)hMaklumatFail.get("IdLot")),"style=\"width:200px\""));
		this.context.put("txtNoLot", (String)hMaklumatFail.get("NoLot"));	
		this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong( (String)hMaklumatFail.get("IdLuas")), " style=\"width:200px\""," pilihLuas()"));
		this.context.put("selectKategori", HTML.SelectKategori("socKategori",0L, " style='width:200px;'"));
		this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",0L, " style='width:200px;'"));
		//this.context.put("txtNoLot", (String)hMaklumatFail.get("NoLot"));	
		this.context.put("txtIdKementerian", (String)hMaklumatFail.get("idKementerian"));	
		this.context.put("txtIdAgensi", (String)hMaklumatFail.get("idAgensi"));	
		this.context.put("txdTarikhTerima", "");	
		this.context.put("txdTarikhDaftar", "");	
		this.context.put("idPermohonan", (String)hMaklumatFail.get("idPermohonan"));	
		this.context.put("txtCukaiTahun", "");	
		this.context.put("txtLokasi", "");	
		this.context.put("txtKegunaanTanah", "");	
		this.context.put("txtLuas", "");	
		this.context.put("txtNoWarta", "");	
		this.context.put("txdTarikhWarta", "");	
		this.context.put("txtSyarat", "");	
		this.context.put("txtSekatan", "");	
		this.context.put("txtSekatan", "");	
		this.context.put("txtNoPu", "");	
		this.context.put("txtNoPelan", "");	
		this.context.put("txtKawasanRizab", "");	
		this.context.put("txtTempoh", "");
		this.context.put("txtNoSyit", "");
		this.context.put("idHakmilik",""+id);	

	}
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeMaklumatFailBaru(String comm,String idPermohonan) throws Exception {
		//String idPermohonan = getParam("idHakmilik"); //translator for idpermohonan	
		Hashtable<?, ?> maklumatFail = (Hashtable<?, ?>)frmRekodUtilData.getPermohonanInfoV1(idPermohonan);
		
		this.context.put("txtFailPTG",(String)maklumatFail.get("noFailPTG"));
		this.context.put("txtTajuk",(String)maklumatFail.get("tajukFail"));
		this.context.put("txtIdKementerian", (String)maklumatFail.get("idkementerian"));	
		this.context.put("txtNamaKementerian", (String)maklumatFail.get("kementerian"));
		this.context.put("txtIdAgensi", (String)maklumatFail.get("idagensi"));	
		this.context.put("txtNamaAgensi", (String)maklumatFail.get("agensi"));
		this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));
		this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(maklumatFail.get("idnegeri").toString())," onChange=\"doChangeStateHakmilik();\""));	
		//this.context.put("txtNamaDaerah",HTML.SelectDaerah("socDaerah",Long.parseLong(maklumatFail.get("idDaerah").toString())," onChange=\"doChangeDaerahHakmilik();\""));
		if("paparDetailRizab2".equals(comm)){
			//this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString(), "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString()),""," onChange=\"doChangeDaerahRizabV()\""));
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString(), "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString()),"",""));
		}else{
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString(), "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString()),""," onChange=\"doChangeDaerahHakmilik();\""));
		}
		String idnegeri = getParam("socNegeri");
	 	String iddaerah = getParam("socDaerah");
	 	if ("doChangeState".equals(mode)) {
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahHakmilik();\""));
		}
		if ("doChangeDaerah".equals(mode)) {
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahHakmilik();\""));
		}else if ("doChangeDaerahRizab".equals(mode)) {
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			//this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabV()\""));
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),"",""));
		}
		//this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), " onChange=\"doChangeStateHakmilik();\""));
		//this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahHakmilik();\""));
		this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));
		
	
	} 
	
	// VIEW MAKLUMAT FAIL BY ID (MASTER) -(AKTIF)
	private void getMaklumatRizabAwal(HttpSession session,String idPermohonan,String submit) throws Exception {
		log.info("getMaklumatRizabAwal"+idPermohonan);
		Hashtable<?, ?> maklumatFail = (Hashtable<?, ?>)frmRekodUtilData.getPermohonanInfoV1(idPermohonan);
		
	 	if ("changeTarafRizab".equals(mode)) {
	 		log.info("getMaklumatRizabAwal:changeTarafRizab");
			this.context.put("txtFailPTD",getParam("txtFailPTD"));
			this.context.put("txtFailPTG",getParam("txtFailPTG"));
			this.context.put("txtTajuk",getParam("txtNamaFail"));
			this.context.put("txtIdKementerian", (String)maklumatFail.get("idkementerian"));	
			this.context.put("txtNamaKementerian", (String)maklumatFail.get("kementerian"));
			this.context.put("txtIdAgensi", (String)maklumatFail.get("idagensi"));	
			this.context.put("txtNamaAgensi", (String)maklumatFail.get("agensi"));
			this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));
			//this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(maklumatFail.get("idnegeri").toString())," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
			//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString(), "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString()),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
			String idnegeri = getParam("socNegeri");
		 	String iddaerah = getParam("socDaerah");
		 	if ("doChangeStateRizab".equals(mode)) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
			}
			if ("doChangeDaerahRizab".equals(mode)) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
			}
			this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));
			
			//this.context.put("idPermohonan", (String)maklumatFail.get("idpermohonan"));	
	 		
	 	}else{
	 		log.info("getMaklumatRizabAwal:else changeTarafRizab");
	 		this.context.put("txtFailPTD",(String)maklumatFail.get("noFailPTD"));
			this.context.put("txtFailPTG",(String)maklumatFail.get("noFailPTG"));
			this.context.put("txtTajuk",(String)maklumatFail.get("tajukFail"));
			this.context.put("txtIdKementerian", (String)maklumatFail.get("idkementerian"));	
			this.context.put("txtNamaKementerian", (String)maklumatFail.get("kementerian"));
			this.context.put("txtIdAgensi", (String)maklumatFail.get("idagensi"));	
			this.context.put("txtNamaAgensi", (String)maklumatFail.get("agensi"));
			this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(maklumatFail.get("idnegeri").toString())," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString(), "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString()),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
			String idnegeri = getParam("socNegeri");
		 	String iddaerah = getParam("socDaerah");
		 	if ("doChangeStateRizab".equals(mode)) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
			}
			if ("doChangeDaerahRizab".equals(mode)) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
			}
			this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));
			//this.context.put("idPermohonan", (String)maklumatFail.get("idpermohonan"));	
	 	}
	 	getMaklumatRizabAwal(session,"R",idPermohonan,submit);
		this.context.put("changeLuas",submit);


	}
	
	// VIEW MAKLUMAT RIZAB BY ID_PERMOHONAN - (AKTIF)
	private void getMaklumatRizabAwal(HttpSession session,String r,String idPermohonan,String submit) throws Exception {
		log.info("getMaklumatRizabAwal("+session+","+r+","+idPermohonan+") ");	
		Hashtable maklumatRizab = (Hashtable)frmRekodUtilData.getRizabUrusan(idPermohonan,null);
		log.info("getMaklumatRizabAwal("+session+","+r+","+idPermohonan+"):maklumatRizab::"+maklumatRizab);	
		
		if(!maklumatRizab.get("IdDaerahPermohonan").equals("0")){
			this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong((String)maklumatRizab.get("IdDaerah"))," style=\"width:300px\" "));
		}
		log.info("getMaklumatRizabAwal("+session+","+r+","+idPermohonan+"):maklumatRizab::getParam(\"txtNoWarta\"):::"+getParam("txtNoWarta"));	
		this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong((String)maklumatRizab.get("IdMukim"))," style=\"width:300px\""));
		
		this.context.put("txtNoWarta", getParam("txtNoWarta") );
		this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong( (String)maklumatRizab.get("IdLuas")), " style=\"width:200px\"","pilihLuas()"));
		this.context.put("txtLuas", getParam("txtLuas") );	
		this.context.put("statusSah", "");	
		this.context.put("pegawaiAkhir", "");	
		this.context.put("txdTarikhDaftar", "");	
		this.context.put("txdTarikhKemaskini", "");	
		
		this.context.put("txtNoWarta", getParam("txtNoWarta"));
		this.context.put("selectLot", HTML.SelectLot("socLot",Utils.parseLong( String.valueOf(getParam("socLot"))),"style=\"width:200px\""));
		this.context.put("txtNoLot", getParam("txtNoLot"));	
		this.context.put("txdTarikhTerima",getParam("txdTarikhTerima"));	
		this.context.put("txdTarikhWarta", getParam("txdTarikhWarta"));	
		this.context.put("txtLokasi",  getParam("txtLokasi"));	
		//this.context.put("idPermohonan", (String)maklumatRizab.get("idPermohonan"));	
		this.context.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));	
		this.context.put("txtIdLuasLama",(String)maklumatRizab.get("idLuasLama"));	
		this.context.put("txtLuasLama", (String)maklumatRizab.get("luasLama"));	
		this.context.put("socLuas",getParam("socLuas"));	
		//this.context.put("txtLuas", (String)maklumatRizab.get("luas"));	
		this.context.put("txtNoPelan",getParam("txtNoPelan"));	
		this.context.put("txtNoSyit", getParam("txtNoSyit"));
		this.context.put("txtNoPu", getParam("txtNoPu"));	

		this.context.put("idHakmilik",idPermohonan);	
		this.context.put("idPermohonan",idPermohonan);	
		log.info("getMaklumatRizabAwal("+session+","+r+","+idPermohonan+"): END");
	}
	
	//By Rosli 06/05/2010 
	//VIEW MAKLUMAT RIZAB 
	private void viewMaklumatRizab(Vector listPermohonan, String dis,String changeLuas) throws Exception {

		Hashtable<?, ?> maklumatRizab =(Hashtable<?, ?>)listPermohonan.get(0);
;
		if("kemaskiniMaklumatRizab".equals(changeLuas)){
			this.context.put("txtFailPTD",getParam("txtFailPTD"));
			this.context.put("txtFailPTG",getParam("txtFailPTG"));
			this.context.put("txtTajuk",(String)maklumatRizab.get("tajukFail"));
			this.context.put("txtIdKementerian", (String)maklumatRizab.get("idkementerian"));	
			this.context.put("txtNamaKementerian", (String)maklumatRizab.get("kementerian"));
			this.context.put("txtIdAgensi", (String)maklumatRizab.get("idagensi"));	
			this.context.put("txtNamaAgensi", (String)maklumatRizab.get("agensi"));
			this.context.put("txtNoFailSeksyen", (String)maklumatRizab.get("noFail"));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(maklumatRizab.get("idNegeri").toString())," onChange=\"doChangeStateRizabEdit("+changeLuas+");\" class=\""+dis+"\" "+dis));	
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatRizab.get("idNegeri").toString(), "socDaerah", Utils.parseLong(maklumatRizab.get("idDaerah").toString()),""," onChange=\"doChangeDaerahRizabEdit("+changeLuas+");\" class=\""+dis+"\" "+dis));
			String idnegeri = getParam("socNegeri");
		 	String iddaerah = getParam("socDaerah");
		 	if ("doChangeStateRizab".equals(mode)) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," style=\"width:300px\" onChange=\"doChangeDaerahRizabEdit("+changeLuas+");\""));
			}
			if ("doChangeDaerahRizab".equals(mode)) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," style=\"width:300px\" onChange=\"doChangeDaerahRizabEdit("+changeLuas+");\""));
			}
			this.context.put("selectMukim",HTML.SelectMukim("socMukim",Long.parseLong(maklumatRizab.get("idMukim").toString())," style=\"width:300px\" class=\""+dis+"\" "+dis));
			
			this.context.put("idHakmilik", (String)maklumatRizab.get("idHakmilik"));			
			this.context.put("txtFailKJP", (String)maklumatRizab.get("noFailKJP"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
	        this.context.put("selectLot", HTML.SelectLot("socLot",Utils.parseLong( String.valueOf(getParam("socLot"))),"style=\"width:200px\" class=\""+dis+"\" "+dis));
			this.context.put("txtNoLot", getParam("txtNoLot"));	
			//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong( (String)maklumatRizab.get("idLuas")), " style=\"width:200px\""," pilihLuas()"));
			this.context.put("txdTarikhTerima",getParam("txdTarikhTerima"));	
			this.context.put("txdTarikhWarta", getParam("txdTarikhWarta"));	
			this.context.put("txtLokasi",  getParam("txtLokasi"));	
			this.context.put("idPermohonan", (String)maklumatRizab.get("idPermohonan"));	
			//this.context.put("txtCukaiTahun", "");	
			this.context.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));	
			this.context.put("txtIdLuasLama",(String)maklumatRizab.get("idLuasLama"));	
			this.context.put("txtLuasLama", (String)maklumatRizab.get("luasLama"));	
			this.context.put("socLuas",getParam("socLuas"));	
			this.context.put("txtLuas", (String)maklumatRizab.get("luas"));	
			//this.context.put("txtSyarat", "");	
			//this.context.put("txtSekatan", "");	
			//this.context.put("txtSekatan", "");	
			this.context.put("txtNoPelan",getParam("txtNoPelan"));	
			this.context.put("txtNoSyit", getParam("txtNoSyit"));
			this.context.put("txtNoPu", getParam("txtNoPu"));	
			//this.context.put("txtKawasanRizab", "");	
			//this.context.put("txtTempoh", "");
			this.context.put("statusSah", (String)maklumatRizab.get("statusSah"));
			this.context.put("pegawaiAkhir", (String)maklumatRizab.get("pegawaiAkhir"));
			this.context.put("txdTarikhKemaskini", (String)maklumatRizab.get("tarikhKemaskini"));
			this.context.put("changeLuas",changeLuas);
		
		}else{
			this.context.put("txtFailPTD",(String)maklumatRizab.get("noFailPTD"));
			this.context.put("txtFailPTG",(String)maklumatRizab.get("noFailPTG"));
			this.context.put("txtTajuk",(String)maklumatRizab.get("tajukFail"));
			this.context.put("txtIdKementerian", (String)maklumatRizab.get("idkementerian"));	
			this.context.put("txtNamaKementerian", (String)maklumatRizab.get("kementerian"));
			this.context.put("txtIdAgensi", (String)maklumatRizab.get("idagensi"));	
			this.context.put("txtNamaAgensi", (String)maklumatRizab.get("agensi"));
			this.context.put("txtNoFailSeksyen", (String)maklumatRizab.get("noFail"));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(maklumatRizab.get("idNegeri").toString())," onChange=\"doChangeStateRizabEdit();\" class=\""+dis+"\" "+dis));	
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatRizab.get("idNegeri").toString(), "socDaerah", Utils.parseLong(maklumatRizab.get("idDaerah").toString()),""," onChange=\"doChangeDaerahRizabEdit();\" class=\""+dis+"\" "+dis));
			String idnegeri = getParam("socNegeri");
		 	String iddaerah = getParam("socDaerah");
		 	if ("doChangeStateRizab".equals(mode)) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabEdit();\""));
			}
			if ("doChangeDaerahRizab".equals(mode)) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabEdit();\""));
			}
			this.context.put("selectMukim",HTML.SelectMukim("socMukim",Long.parseLong(maklumatRizab.get("idMukim").toString())," style=\"width:300px\" class=\""+dis+"\" "+dis));
	
			this.context.put("idHakmilik", (String)maklumatRizab.get("idHakmilik"));			
			this.context.put("txtFailKJP", (String)maklumatRizab.get("noFailKJP"));
			this.context.put("txtNoWarta", (String)maklumatRizab.get("noWarta"));
	        this.context.put("selectLot", HTML.SelectLot("socLot",Utils.parseLong( (String)maklumatRizab.get("idLot")),"style=\"width:200px\" class=\""+dis+"\" "+dis));
			this.context.put("txtNoLot", (String)maklumatRizab.get("noLot"));	
			this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong( (String)maklumatRizab.get("idLuas")), " style=\"width:200px\""," pilihLuas()"));
			this.context.put("txdTarikhTerima",(String)maklumatRizab.get("tarikhTerima"));	
			this.context.put("txdTarikhWarta", (String)maklumatRizab.get("tarikhWarta"));	
			this.context.put("idPermohonan", (String)maklumatRizab.get("idPermohonan"));	
			//this.context.put("txtCukaiTahun", "");	
			this.context.put("txtLokasi",(String)maklumatRizab.get("lokasi") );	
			this.context.put("txtKegunaanTanah", (String)maklumatRizab.get("kegunaanTanah"));	
			this.context.put("txtIdLuasLama",(String)maklumatRizab.get("idLuasLama"));	
			this.context.put("txtLuasLama", (String)maklumatRizab.get("luasLama"));	
			this.context.put("socLuas",(String)maklumatRizab.get("idLuas"));	
			this.context.put("txtLuas", (String)maklumatRizab.get("luas"));	
//			this.context.put("txtSyarat", "");	
//			this.context.put("txtSekatan", "");	
			this.context.put("txtNoPelan",(String)maklumatRizab.get("noPelan"));	
			this.context.put("txtNoSyit", (String)maklumatRizab.get("noSyit"));
			this.context.put("txtNoPu", (String)maklumatRizab.get("noPu"));	
//			this.context.put("txtKawasanRizab", "");	
//			this.context.put("txtTempoh", "");
			this.context.put("statusSah", (String)maklumatRizab.get("statusSah"));
			this.context.put("pegawaiAkhir", (String)maklumatRizab.get("pegawaiAkhir"));
			this.context.put("txdTarikhKemaskini", (String)maklumatRizab.get("tarikhKemaskini"));
			this.context.put("changeLuas",changeLuas);
		}

		
	}

	
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeHakmilikV1(String idPermohonan) throws Exception {
		//String idPermohonan = getParam("idHakmilik"); //translator for idpermohonan	
    	
		Hashtable<?, ?> maklumatHakmilik = null;
		maklumatHakmilik = (Hashtable<?, ?>)frmRekodUtilData.getHakmilikUrusanV1(idPermohonan,"0");	
		if(maklumatHakmilik!=null){
			this.context.put("txtNamaMukim",HTML.SelectMukim("socMukim",Long.parseLong(maklumatHakmilik.get("idMukim").toString()),""));
	        this.context.put("selectLot", HTML.SelectLot("noLot",Utils.parseLong( (String)maklumatHakmilik.get("idLot"))," style=\"width:200px\""));
			this.context.put("txtNoLot", (String)maklumatHakmilik.get("noLot"));	
			this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong( (String)maklumatHakmilik.get("idLuas")), " style=\"width:200px\"","pilihLuas()"));
			this.context.put("txdTarikhTerima", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));	
			this.context.put("txdTarikhDaftar", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));	
		   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong( (String)maklumatHakmilik.get("idJenisHakmilik")), "style=\"width:200px\""));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",0L, " style='width:200px;'"));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",0L, " style='width:200px;'"));
		}else{
			this.context.put("txtNamaMukim",HTML.SelectMukim("socMukim",null,""));
	        this.context.put("selectLot", HTML.SelectLot("noLot",null," style=\"width:200px\""));
			this.context.put("txtNoLot", "");	
			this.context.put("selectLuas", HTML.SelectLuas("socLuas",null, " style=\"width:200px\"","pilihLuas()"));
			this.context.put("txdTarikhTerima", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));	
			this.context.put("txdTarikhDaftar", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));	
		   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",null, "style=\"width:200px\""));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",0L, " style='width:200px;'"));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",0L, " style='width:200px;'"));	
		}
		String iddaerah = getParam("socDaerah");
	 	String idmukim = getParam("socMukim");
	 	if ("doChangeDaerah".equals(mode)) {
			this.context.put("txtNamaMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"",""));
		}

		this.context.put("txtCukaiTahun", "");	
		this.context.put("txtLokasi", "");	
		this.context.put("txtKegunaanTanah", "");	
		this.context.put("txtLuas", "");	
		this.context.put("txtNoWarta", "");	
		this.context.put("txdTarikhWarta", "");	
		this.context.put("txtSyarat", "");	
		this.context.put("txtSekatan", "");	
		this.context.put("txtSekatan", "");	
		this.context.put("txtNoPu", "");	
		this.context.put("txtNoPelan", "");	
		this.context.put("txtKawasanRizab", "");	
		this.context.put("txtTempoh", "");
		this.context.put("txtNoSyit", "");
		this.context.put("idHakmilik",idPermohonan);	
	}

	// SETUP PAGING
	public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("SenaraiHakmilik",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	
	
	   // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID 
	private void view_modeHakmilikSementara(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikSementaraData.getPaparHakmilikSementaraById(idHakmilik);
		Hashtable hHakmilik = (Hashtable) list.get(0);

		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
		this.context.put("txdTarikhTerima", (String)hHakmilik.get("tarikhTerima"));
		this.context.put("txdTarikhDaftar", (String)hHakmilik.get("tarikhDaftar"));
		this.context.put("txtCukaiTahun", (String)hHakmilik.get("cukai"));
		this.context.put("txtLokasi", (String)hHakmilik.get("lokasi"));
        this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")),"disabled", " style=\"width:200px\" onChange=\"doChangeStateHR();\" class=\"disabled\""));
	    this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),"disabled"," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" class=\"disabled\""));
	    this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR")),"disabled"," style=\"width:200px\" class=\"disabled\""));
	    this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")),"disabled", " style=\"width:200px\"  class=\"disabled\""));
	    this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")),"disabled", " style=\"width:200px\" class=\"disabled\""));
		//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
	    this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
	    this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
		this.context.put("selectRizab", HTML.SelectRizab("socRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
		this.context.put("kodJenis", (String)hHakmilik.get("kodJenis"));
		this.context.put("noHakmilik", (String)hHakmilik.get("noHakmilik"));
		this.context.put("txtCukaiTerkini",(String)hHakmilik.get("cukaiTerkini"));
		this.context.put("txtNoBangunan",(String)hHakmilik.get("noBangunan"));
		this.context.put("txtNoTingkat",(String)hHakmilik.get("noTingkat"));
		this.context.put("txtNoPetak",(String)hHakmilik.get("noPetak"));
		this.context.put("txtNoPelan", (String)hHakmilik.get("noPelan"));
		this.context.put("txtTempoh", (String)hHakmilik.get("tempoh"));
		this.context.put("txtSyarat", (String)hHakmilik.get("syarat"));
		this.context.put("txtHakmilikAsal", (String)hHakmilik.get("hakmilikAsal"));
		this.context.put("txtNoFailJopa", (String)hHakmilik.get("noFailJopa"));
		this.context.put("txtTarafHakmilik", (String)hHakmilik.get("tarafHakmilik"));
		this.context.put("txdTarikhLuput", (String)hHakmilik.get("tarikhLuput"));
		this.context.put("txtCukaiTerkini", (String)hHakmilik.get("cukaiTerkini"));
		this.context.put("txtKegunaanTanah", (String)hHakmilik.get("kegunaanTanah"));
		this.context.put("txtLuas", (String)hHakmilik.get("luas"));
		this.context.put("txtNoPu", (String)hHakmilik.get("noPu"));
		this.context.put("txdTarikhWarta",(String)hHakmilik.get("tarikhWarta"));
		this.context.put("txtNoWarta",(String)hHakmilik.get("noWarta"));
		this.context.put("txtNoRizab",(String)hHakmilik.get("noRizab"));
		this.context.put("txdTarikhRizab",(String)hHakmilik.get("tarikhRizab"));
		this.context.put("txtKawasanRizab",(String)hHakmilik.get("kawasanRizab"));
		this.context.put("txtNoSyit",(String)hHakmilik.get("noSyit"));
		this.context.put("txtSekatan",(String)hHakmilik.get("sekatan"));
		this.context.put("txtHakmilikBerikut",(String)hHakmilik.get("hakmilikBerikut"));
		this.context.put("socStatus", getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
		this.context.put("txtKemAgenTerkini", getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
		this.context.put("socTaraf",(String)hHakmilik.get("socTaraf"));
		this.context.put("socRizab", (String)hHakmilik.get("socRizab"));
		this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
		this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));
		this.context.put("txtPegawaiAkhir", (String)hHakmilik.get("userName"));
		this.context.put("socLuas",(String)hHakmilik.get("idLuasLama"));
		this.context.put("txtLuasLama",(String)hHakmilik.get("luasLama"));
		this.context.put("txtLuas",(String)hHakmilik.get("luasConvert"));		
		this.context.put("txtNoHakmilikAsal",(String)hHakmilik.get("hakmilikAsal"));		
	}
	// view_modeSenaraiHakmilikSambungan
	private Vector view_modeSenaraiHakmilikSambungan(HttpSession session,String noHakmilikAsal) throws Exception {
		return FrmRekodPendaftaranHakmilikSementaraData.getSenaraiHakmilikSambungan(noHakmilikAsal);	
		
	}
	
	   // VIEW MAKLUMAT DETAIL HAKMILIK DAN RIZAB BY ID	(AKTIF)
	private void view_modeHakmilikRizab(HttpSession session,String submit) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		log.info("idHakmilik :"+id);
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikSementaraData.getPaparHakmilikSementaraById(idHakmilik);
		Hashtable hHakmilik = (Hashtable) list.get(0);

		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
		this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? (String)hHakmilik.get("tarikhTerima"):getParam("txdTarikhTerima"));
		this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
		this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? (String)hHakmilik.get("cukai"):getParam("txtCukaiTahun"));
		this.context.put("txtLokasi",getParam("txtLokasi") == "" ? (String)hHakmilik.get("lokasi"):getParam("txtLokasi"));
		if(submit.equals("kemaskiniDetailHakmilik")||submit.equals("kemaskiniDetailRizab")){
			//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style='width:200px;'"));
		    this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), " style='width:200px;'" ));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
		}
		else
			if(submit.equals("updateDetailHakmilik")|| submit.equals("updateDetailRizab")||submit.equals("paparDetailHakmilik")||submit.equals("paparDetailRizab")){
				//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
				this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
				this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
			}	
		this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? (String)hHakmilik.get("noPelan"):getParam("txtNoPelan"));
		this.context.put("txtTempoh",getParam("txtTempoh") == "" ? (String)hHakmilik.get("tempoh"):getParam("txtTempoh"));
		this.context.put("txtSyarat",getParam("txtSyarat") == "" ? (String)hHakmilik.get("syarat"):getParam("txtSyarat"));
		this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
		this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? (String)hHakmilik.get("noFailJopa"):getParam("txtNoFailJopa"));
		this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? (String)hHakmilik.get("tarafHakmilik"):getParam("txtTarafHakmilik"));
		this.context.put("txdTarikhLuput",getParam("txdTarikhLuput") == "" ? (String)hHakmilik.get("tarikhLuput"):getParam("txdTarikhLuput"));
		this.context.put("txtCukaiTerkini",getParam("txtCukaiTerkini") == "" ? (String)hHakmilik.get("cukaiTerkini"):getParam("txtCukaiTerkini"));
		this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
		this.context.put("txtLuas",getParam("txtLuas") == "" ? (String)hHakmilik.get("luas"):getParam("txtLuas"));
		this.context.put("txtNoPu",getParam("txtNoPu") == "" ? (String)hHakmilik.get("noPu"):getParam("txtNoPu"));
		this.context.put("txdTarikhWarta",getParam("txdTarikhWarta") == "" ? (String)hHakmilik.get("tarikhWarta"):getParam("txdTarikhWarta"));
		this.context.put("txtKawasanRizab",getParam("txtKawasanRizab") == "" ? (String)hHakmilik.get("kawasanRizab"):getParam("txtKawasanRizab"));
		this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? (String)hHakmilik.get("noSyit"):getParam("txtNoSyit"));
		this.context.put("txtNoWarta",getParam("txtNoWarta") == "" ? (String)hHakmilik.get("noWarta"):getParam("txtNoWarta"));
		this.context.put("txtSekatan",getParam("txtSekatan") == "" ? (String)hHakmilik.get("sekatan"):getParam("txtSekatan"));
		this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? (String)hHakmilik.get("hakmilikBerikut"):getParam("txtHakmilikBerikut"));
		this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
		this.context.put("socTaraf", getParam("socTaraf") == "" ? (String)hHakmilik.get("socTaraf"):getParam("socTaraf"));
		this.context.put("socRizab", getParam("socRizab") == "" ? (String)hHakmilik.get("socRizab"):getParam("socRizab"));
		this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
		this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));	
		this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtNoHakmilikAsal"));
		this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));
		
	}	

	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeMaklumatFail(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikSementaraData.getPaparMaklumatFailById(idHakmilik);
		Hashtable hMaklumatFail = (Hashtable) list.get(0);
		
		this.context.put("txtFailPTG",(String)hMaklumatFail.get("noFailPtg"));
		this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
		this.context.put("txtNamaKementerian", (String)hMaklumatFail.get("namaKementerian"));
		this.context.put("txtNoFailSeksyen", (String)hMaklumatFail.get("noFailSeksyen"));
		this.context.put("txtNamaNegeri", (String)hMaklumatFail.get("namaNegeri"));
		this.context.put("txtNamaDaerah", (String)hMaklumatFail.get("namaDaerah"));
		this.context.put("txtNamaMukim", (String)hMaklumatFail.get("namaMukim"));
		this.context.put("txtNamaAgensi", (String)hMaklumatFail.get("namaAgensi"));
		this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
		this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
		this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
		this.context.put("txtNoHakmilik",(String) hMaklumatFail.get("noHakmilik"));
		this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
		this.context.put("caraPerolehan", (String)hMaklumatFail.get("caraPerolehan"));
	}
	
	//****************SECOND PAGE METHOD/FUCTION **************************
	// VIEW HEADER BY ID
	private void viewModeMaklumatFail(HttpSession session,String idHakmilik) throws Exception {
		//int id = Integer.parseInt(idHakmilik);	
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById(idHakmilik);
		Hashtable hMaklumatFail = (Hashtable) list.get(0);
		
		this.context.put("txtFailPTG",(String)hMaklumatFail.get("noFailPtg"));
		this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
		this.context.put("txtNamaKementerian", (String)hMaklumatFail.get("namaKementerian"));
		this.context.put("txtNoFailSeksyen", (String)hMaklumatFail.get("noFailSeksyen"));
		this.context.put("txtNamaNegeri", (String)hMaklumatFail.get("namaNegeri"));
		this.context.put("txtNamaDaerah", (String)hMaklumatFail.get("namaDaerah"));
		this.context.put("txtNamaMukim", (String)hMaklumatFail.get("namaMukim"));
		this.context.put("txtNamaAgensi", (String)hMaklumatFail.get("namaAgensi"));
		this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
		this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
		this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
		this.context.put("txtNoHakmilik",(String) hMaklumatFail.get("noHakmilik"));
		this.context.put("txtNoLot", (String)hMaklumatFail.get("noLot"));	
		this.context.put("caraPerolehan", (String)hMaklumatFail.get("caraPerolehan"));
		
	}
    
	// VIEW MAKLUMAT HAKMILIK BY ID -(AKTIF)
	private void viewModeHakmilikRizab(String idHakmilik,HttpSession session,String nextAction,String lastAction,String idNegeriHR, String idDaerahHR, String idMukimHR,String idJenisHakmilikHR, String idCaraBayar) throws Exception {
		Vector list =null;
		list = FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(idHakmilik);

		Hashtable hHakmilik = (Hashtable) list.get(0);
		
		this.context.put("idHakmilik", (String)hHakmilik.get("idHakmilik"));
		this.context.put("statusBatal", (String)hHakmilik.get("socStatus"));
    	this.context.put("txdTarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
    	this.context.put("txtCukaiTahun",getParam("txtCukaiTahun") == "" ? (String)hHakmilik.get("cukai"):getParam("txtCukaiTahun"));
		this.context.put("txtLokasi",getParam("txtLokasi") == "" ? (String)hHakmilik.get("lokasi"):getParam("txtLokasi"));
		this.context.put("txdTarikhTerima",getParam("txdTarikhTerima") == "" ? (String)hHakmilik.get("tarikhTerima"):getParam("txdTarikhTerima"));	
		if(nextAction.equals("kemaskiniDetailHakmilik")||nextAction.equals("kemaskiniDetailRizab")){
			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), ""," style=\"width:200px\"")); 
			this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")), " style=\"width:200px\" onChange=\"doChangeStateHR();\""));
	    	this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),""," style=\"width:200px\" onChange=\"doChangeDaerahHR();\""));
	    	this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR"))," style=\"width:200px\""));		
	    	this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")), " style=\"width:200px\""));
	    	this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")), " style=\"width:200px\""));
	    	//this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), " style=\"width:200px\""));
		    this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), " style='width:200px;'"));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), " style='width:200px;'"));
			if ("doChangeStateHR".equals(lastAction)) {
				this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\" style=\"width:200px\""));
				this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\" style=\"width:200px\""));	
				this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));
			}
			if ("doChangeDaerahHR".equals(lastAction)) {
				this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong(idNegeriHR), " onChange=\"doChangeStateHR();\"  style=\"width:200px\""));
				this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri(idNegeriHR, "socDaerahHR", Utils.parseLong(idDaerahHR),""," onChange=\"doChangeDaerahHR();\"  style=\"width:200px\""));
				this.context.put("selectMukimHR", HTML.SelectMukimByDaerah(idDaerahHR, "socMukimHR", Utils.parseLong(idMukimHR),"","style=\"width:200px\""));
			}
		}
		else
		if(nextAction.equals("updateDetailHakmilik")|| nextAction.equals("updateDetailRizab")||nextAction.equals("paparDetailHakmilik")||nextAction.equals("paparDetailRizab")){
			this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idCaraBayar), "disabled"," style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectNegeriHR", HTML.SelectNegeri("socNegeriHR",Utils.parseLong((String)hHakmilik.get("idNegeriHR")),"disabled", " style=\"width:200px\" onChange=\"doChangeStateHR();\" class=\"disabled\""));
		    this.context.put("selectDaerahHR", HTML.SelectDaerahByNegeri((String)hHakmilik.get("idNegeriHR"),"socDaerahHR", Utils.parseLong((String)hHakmilik.get("idDaerahHR")),"disabled"," style=\"width:200px\" onChange=\"doChangeDaerahHR();\" class=\"disabled\""));
		    this.context.put("selectMukimHR", HTML.SelectMukimByDaerah((String)hHakmilik.get("idDaerahHR"),"socMukimHR", Utils.parseLong((String)hHakmilik.get("idMukimHR")),"disabled"," style=\"width:200px\" class=\"disabled\""));
		    this.context.put("selectJenisLotHR", HTML.SelectLot("socLotHR",Utils.parseLong((String)hHakmilik.get("idLot")),"disabled", " style=\"width:200px\" class=\"disabled\""));
		    this.context.put("selectJenisHakmilikHR", HTML.SelectJenisHakmilik("socJenisHakmilikHR",Utils.parseLong((String)hHakmilik.get("idJenisHakmilikHR")),"disabled", " style=\"width:200px\" class=\"disabled\""));		    
		    //this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuas")), "disabled"," style=\"width:200px\" class=\"disabled\""));
		    this.context.put("selectLuasLama", HTML.SelectLuas("socLuas",Utils.parseLong((String)hHakmilik.get("idLuasLama")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong((String)hHakmilik.get("idKategori")), "disabled"," style=\"width:200px\" class=\"disabled\""));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong((String)hHakmilik.get("idJenisRizab")), "disabled", " style=\"width:200px\" class=\"disabled\""));
		}	
		this.context.put("txtNoBangunan",getParam("txtNoBangunan") == "" ? (String)hHakmilik.get("noBangunan"):getParam("txtNoBangunan"));
		this.context.put("txtNoTingkat",getParam("txtNoTingkat") == "" ? (String)hHakmilik.get("noTingkat"):getParam("txtNoTingkat"));
		this.context.put("txtNoPetak",getParam("txtNoPetak") == "" ? (String)hHakmilik.get("noPetak"):getParam("txtNoPetak"));
		this.context.put("txtNoPelan",getParam("txtNoPelan") == "" ? (String)hHakmilik.get("noPelan"):getParam("txtNoPelan"));
		this.context.put("txtTempoh",getParam("txtTempoh") == "" ? (String)hHakmilik.get("tempoh"):getParam("txtTempoh"));
		this.context.put("txtSyarat",getParam("txtSyarat") == "" ? (String)hHakmilik.get("syarat"):getParam("txtSyarat"));
		this.context.put("txtHakmilikAsal",getParam("txtHakmilikAsal") == "" ? (String)hHakmilik.get("hakmilikAsal"):getParam("txtHakmilikAsal"));
		this.context.put("txtNoFailJopa",getParam("txtNoFailJopa") == "" ? (String)hHakmilik.get("noFailJopa"):getParam("txtNoFailJopa"));
		this.context.put("txtTarafHakmilik",getParam("txtTarafHakmilik") == "" ? (String)hHakmilik.get("tarafHakmilik"):getParam("txtTarafHakmilik"));
		this.context.put("txdTarikhLuput",getParam("txdTarikhLuput") == "" ? (String)hHakmilik.get("tarikhLuput"):getParam("txdTarikhLuput"));
		this.context.put("txtCukaiTerkini",getParam("txtCukaiTerkini") == "" ? (String)hHakmilik.get("cukaiTerkini"):getParam("txtCukaiTerkini"));
		this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
		this.context.put("txtLuas",getParam("txtLuas") == "" ? (String)hHakmilik.get("luas"):getParam("txtLuas"));
		this.context.put("txtNoPu",getParam("txtNoPu") == "" ? (String)hHakmilik.get("noPu"):getParam("txtNoPu"));
		this.context.put("txdTarikhWarta",getParam("txdTarikhWarta") == "" ? (String)hHakmilik.get("tarikhWarta"):getParam("txdTarikhWarta"));
		this.context.put("txtNoWarta",getParam("txtNoWarta") == "" ? (String)hHakmilik.get("noWarta"):getParam("txtNoWarta"));
		this.context.put("txtNoRizab",getParam("txtNoRizab") == "" ? (String)hHakmilik.get("noRizab"):getParam("txtNoRizab"));
		this.context.put("txdTarikhRizab",getParam("txdTarikhRizab") == "" ? (String)hHakmilik.get("tarikhRizab"):getParam("txdTarikhRizab"));
		this.context.put("txtKawasanRizab",getParam("txtKawasanRizab") == "" ? (String)hHakmilik.get("kawasanRizab"):getParam("txtKawasanRizab"));
		this.context.put("txtNoSyit",getParam("txtNoSyit") == "" ? (String)hHakmilik.get("noSyit"):getParam("txtNoSyit"));
		this.context.put("txtSekatan",getParam("txtSekatan") == "" ? (String)hHakmilik.get("sekatan"):getParam("txtSekatan"));
		this.context.put("txtHakmilikBerikut",getParam("txtHakmilikBerikut") == "" ? (String)hHakmilik.get("hakmilikBerikut"):getParam("txtHakmilikBerikut"));
		this.context.put("socStatus",getParam("socStatus") == "" ? (String)hHakmilik.get("socStatus"):getParam("socStatus"));
		this.context.put("socTaraf", getParam("socTaraf") == "" ? (String)hHakmilik.get("socTaraf"):getParam("socTaraf"));
		this.context.put("socRizab", getParam("socRizab") == "" ? (String)hHakmilik.get("socRizab"):getParam("socRizab"));
		this.context.put("statusRizab", (String)hHakmilik.get("statusRizab"));
		this.context.put("txdTarikhKemaskini", (String)hHakmilik.get("tarikhKemaskini"));
		this.context.put("txtPegawaiAkhir", (String)hHakmilik.get("userName"));
		this.context.put("socLuas", getParam("socLuas") == "" ? (String)hHakmilik.get("idLuasLama"):getParam("socLuas"));
		this.context.put("txtLuasLama",getParam("txtLuasLama") == "" ? (String)hHakmilik.get("luasLama"):getParam("txtLuasLama"));
		this.context.put("txtLuas",getParam("txtLuas") == "" ? (String)hHakmilik.get("luasConvert"):getParam("txtLuas"));
		this.context.put("txtNoLot",getParam("txtNoLot") == "" ? (String)hHakmilik.get("noLot"):getParam("txtNoLot"));
		this.context.put("txtNoHakmilikAsal",getParam("txtNoHakmilikAsal") == "" ? (String)hHakmilik.get("noHakmilikAsal"):getParam("txtNoHakmilikAsal"));
		this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini") == "" ? (String)hHakmilik.get("catatan"):getParam("txtKemAgenTerkini"));

	}	

	///AZAM
	public void doXX() throws Exception {
		try {
			
		} catch (Exception e) {
			 throw new Exception("Ralat:"+e.getMessage());
		}
	}
	
	public void emptyContext() throws Exception {
		this.context.put("readOnly", "");
		this.context.put("disabled", "");	

		this.context.put("txtCukaiTahun", "");	
		this.context.put("txtLokasi", "");	
		this.context.put("txtKegunaanTanah", "");	
		this.context.put("txtLuas", "");	
		this.context.put("txtNoWarta", "");	
		this.context.put("txdTarikhWarta", "");	
		this.context.put("txtSyarat", "");	
		this.context.put("txtSekatan", "");	
		this.context.put("txtSekatan", "");	
		this.context.put("txtNoPu", "");	
		this.context.put("txtNoPelan", "");	
		this.context.put("txtKawasanRizab", "");	
		this.context.put("txtTempoh", "");
		this.context.put("txdTarikhLuput", "");
		
		
		this.context.put("txtNoSyit", "");
		this.context.put("txtNoHakmilik", "");	
		this.context.put("socTaraf", "");
		this.context.put("txtNoLot","");
		this.context.put("txtNoBangunan","");
		this.context.put("txtNoTingkat","");
		this.context.put("txtNoPetak","");
	}
	
	public void initContextValue() throws Exception {
	   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",0L, "style=\"width:200px\""));
        this.context.put("selectLot", HTML.SelectLot("noLot",0L," style=\"width:200px\""));
	   	this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",0L, " style='width:200px;'"));
		this.context.put("selectKategori", HTML.SelectKategori("socKategori",0L, " style='width:200px;'"));
		this.context.put("selectLuas", HTML.SelectLuas("socLuas",0L, " style=\"width:200px\"","pilihLuas()"));
		this.context.put("txdTarikhTerima", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));	
		this.context.put("txdTarikhDaftar", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));	
		this.context.put("txtLuas","");
		this.context.put("txtLuas1","");
		this.context.put("txtTempoh","");
		this.context.put("txtCukaiTahun","");
		this.context.put("txtLokasi","");
		this.context.put("txtKegunaanTanah","");
		this.context.put("socLuas","");
		this.context.put("txtNoLot","");
		
		
	}
	
	public void doInitMaklumatFail(String idPermohonan) throws Exception {
		Hashtable  maklumatFail = (Hashtable)frmRekodUtilData.getPermohonanInfoV1(idPermohonan);
		if (maklumatFail != null) { 
			idnegeri = (String)maklumatFail.get("idnegeri");
			//iddaerah = (String)maklumatFail.get("idDaerah");
			
		    this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			
			this.context.put("txtFailPTG",(String)maklumatFail.get("noFailPTG"));
			this.context.put("txtFailPTD",(String)maklumatFail.get("noFailPTD"));
			this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));

			this.context.put("txtTajuk",(String)maklumatFail.get("tajukFail"));
			this.context.put("txtIdKementerian", (String)maklumatFail.get("idkementerian"));	
			this.context.put("txtNamaKementerian", (String)maklumatFail.get("kementerian"));
			this.context.put("txtIdAgensi", (String)maklumatFail.get("idagensi"));	
			this.context.put("txtNamaAgensi", (String)maklumatFail.get("agensi"));
			this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));


		} else {
			log.debug("** maklumatFail empty **");
		}
	}
	
	public void doInitNegeriDaerahMukim(String idPermohonan) throws Exception {
		if ("".equals(idPermohonan)) idPermohonan = "-1";
		Hashtable  maklumatFail = (Hashtable)frmRekodUtilData.getPermohonanInfoV1(idPermohonan);
		if(maklumatFail!=null){
			idnegeri = (String)maklumatFail.get("idnegeri");
			iddaerah = (String)maklumatFail.get("idDaerah");
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),"$readonly class=\"$disabled\""));
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),"$readonly class=\"$disabled\""," onChange=\"doChangeDaerahHakmilik();\""));
			this.context.put("txtNamaMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim",0L,"$readonly class=\"$disabled\"",""));
		} else {
			log.debug("** maklumatHakmilik empty **");
			initContextValue();
		}
	}
	
	public void doInitMaklumatHakmilik(String idHakmilik) throws Exception {
		if ("".equals(idHakmilik)) idHakmilik = "-1";
		
		Hashtable maklumatHakmilik = (Hashtable)frmRekodUtilData.getDetailHakmilik(idHakmilik);	
		if(maklumatHakmilik!=null){
			
			idnegeri = (String)maklumatHakmilik.get("id_negeri");
			iddaerah = (String)maklumatHakmilik.get("id_daerah");
			idmukim = (String)maklumatHakmilik.get("id_mukim");
			
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),"$readonly class=\"$disabled\""));
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),"$readonly class=\"$disabled\""," onChange=\"doChangeDaerahHakmilik();\""));
			this.context.put("txtNamaMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"$readonly class=\"$disabled\"",""));
			
			this.context.put("txtFailPTG",(String)maklumatHakmilik.get("no_fail_ptg"));
			this.context.put("txtFailPTD",(String)maklumatHakmilik.get("no_fail_ptd"));
		   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong( (String)maklumatHakmilik.get("idJenisHakmilik")), "style=\"width:200px\""));
		   	this.context.put("txtNoHakmilik",(String)maklumatHakmilik.get("no_hakmilik"));
		   	this.context.put("txtNoBangunan",(String)maklumatHakmilik.get("no_bangunan"));
		   	this.context.put("txtNoTingkat", (String)maklumatHakmilik.get("no_tingkat"));
		   	this.context.put("txtNoPetak",(String)maklumatHakmilik.get("no_petak"));
		   	this.context.put("selectLot", HTML.SelectLot("noLot",Utils.parseLong( (String)maklumatHakmilik.get("idLot"))," style=\"width:200px\""));
			this.context.put("txtNoLot", (String)maklumatHakmilik.get("noLot"));	
			this.context.put("txdTarikhTerima",(String)maklumatHakmilik.get("tarikh_terima"));	
			this.context.put("txdTarikhDaftar",(String)maklumatHakmilik.get("tarikh_daftar"));	
			
			this.context.put("socTaraf",(String)maklumatHakmilik.get("taraf_hakmilik"));	
			
			//log.debug(" ** CHECK SOC TARAF **"+maklumatHakmilik.get("taraf_hakmilik"));
			
			this.context.put("txtTempoh",(String)maklumatHakmilik.get("tempoh"));	
			this.context.put("txdTarikhLuput",(String)maklumatHakmilik.get("tarikh_luput"));	
			this.context.put("txtCukaiTahun",(String)maklumatHakmilik.get("cukai"));	
			this.context.put("txtLokasi",(String)maklumatHakmilik.get("lokasi"));	
			
			this.context.put("txtKegunaanTanah",(String)maklumatHakmilik.get("kegunaan_tanah"));	
			
			//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong( (String)maklumatHakmilik.get("idLuas")), " style=\"width:200px\"","pilihLuas()"));
			this.context.put("socLuas",(String)maklumatHakmilik.get("idLuas"));
			
			//this.context.put("txtLuas",(String)maklumatHakmilik.get("luas_lama"));	
			this.context.put("txtLuas",(String)maklumatHakmilik.get("luas"));	
			
			this.context.put("txtLuas1",(String)maklumatHakmilik.get("luas_lama"));	
			
			
			this.context.put("statusRizab",(String)maklumatHakmilik.get("status_rizab"));	
			this.context.put("txtNoWarta",(String)maklumatHakmilik.get("no_warta"));	
			this.context.put("txtTarikhWarta",(String)maklumatHakmilik.get("tarikh_warta"));	
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong( (String)maklumatHakmilik.get("id_rizab")), " style='width:200px;'"));
			this.context.put("txtKawasanRizab",(String)maklumatHakmilik.get("kawasan_rizab"));	
		   	this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong( (String)maklumatHakmilik.get("id_kategori")), " style='width:200px;'"));
			this.context.put("txtSyarat",(String)maklumatHakmilik.get("syarat"));	
			this.context.put("txtSekatan",(String)maklumatHakmilik.get("sekatan"));	
			this.context.put("txtNoPelan",(String)maklumatHakmilik.get("no_pelan"));	
			this.context.put("txtNoSyit",(String)maklumatHakmilik.get("no_syit"));	
			this.context.put("txtNoPu",(String)maklumatHakmilik.get("no_pu"));	

			
		} else {
			log.debug("** maklumatHakmilik empty **");
			initContextValue();
		}
	}
	
	
	
	public void doGetListBox() throws Exception {
		//idnegeri = getParam("socNegeri");if ("".equals(idnegeri)) idnegeri = "-1";
		iddaerah = getParam("socDaerah");if ("".equals(iddaerah)) iddaerah = "-1";
		idmukim = getParam("socMukim");if ("".equals(idmukim)) idmukim = "-1";
		//this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
		this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),"$readonly class=\"$disabled\""," onChange=\"doChangeDaerahHakmilik();\""));
		this.context.put("txtNamaMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"$readonly class=\"$disabled\"",""));
	   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")), "style=\"width:200px\""));
	    this.context.put("selectLot", HTML.SelectLot("noLot",Utils.parseLong( getParam("noLot")),"style=\"width:200px\""));
		this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong( getParam("socJenisRizab")), " style='width:200px;'"));
		this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong(getParam("socLuas")), " style=\"width:200px\""," pilihLuas()"));
		this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong(getParam("socKategori")), " style='width:200px;'"));
		
	}
	
	public void doPaparDetailHakmilik2(Vector list) throws Exception {
		log.debug("** doPaparDetailHakmilik2 ** ");
		try {
			String idPermohonan = getParam("idPermohonan"); //translator for idpermohonan	
			String idHakmilik = getParam("idHakmilik");
			
			idnegeri = getParam("socNegeri");
		 	iddaerah = getParam("socDaerah");
		 	idmukim = getParam("socMukim");
		 	idJenisHakmilik = getParam("socJenisHakmilik");

		 	//INIT ID
//		 	if ("".equals(idnegeri)) idnegeri = "-1";
//		 	if ("".equals(iddaerah)) iddaerah = "-1";
//		 	if ("".equals(idmukim)) idmukim = "-1";
		 	//KEY
		 	this.context.put("idPermohonan",idPermohonan);
			this.context.put("idHakmilik",idHakmilik);
				
			//
			boolean isHTPHakmilik = frmRekodUtilData.isHTPHakmilik(idPermohonan);
			
			if (isHTPHakmilik && "".equals(mode)) {
				//Senaraikan hakmilik
				vm = PATH+"frmPendaftaranTerimaHakmilikSenarai.jsp";
				list = frmRekodUtilData.getCarianSenaraiHakmilikRizabById(idPermohonan, null, null, null, null, null, null, null, null, null, null, null, null, null);
				this.context.put("SenaraiTanah", list);
				return;
			} //else if ("doChangeState".equals(mode) || "doChangeDaerah".equals(mode)) {
				else if (mode.indexOf("doChange") != -1) {
				//CATER FOR ALL DO CHANGES
				doGetListBox();
			   	getValuesAndAssignToContext();					
				if ("doChangeKodLuas".equals(mode)) {
					//RESET LUAS
					this.context.put("txtLuas","");
					this.context.put("txtLuas1","");
				}
			   	this.context.put("mode",mode);
			} else if ("addHakmilik".equals(mode)) {
				this.context.put("readOnly", "");
			 	this.context.put("disabled", "");
				this.context.put("mode", "addHakmilik");
				emptyContext();
				initContextValue();
				doInitMaklumatFail(idPermohonan);
				doInitNegeriDaerahMukim(idPermohonan);
			} else if ("doAddHakmilik".equals(mode)) { 
				 idHakmilik = doInsertDetailHakmilik2();
				 //VIEW SEMULA HAKMILIK 
				 doInitMaklumatHakmilik(idHakmilik);
				 this.context.put("readOnly", "readonly");
			 	 this.context.put("disabled", "disabled");	
				 this.context.put("mode", "doView");
			} else if ("updateHakmilik".equals(mode)) { 
				this.context.put("readOnly", "");
			 	this.context.put("disabled", "");
				this.context.put("mode", "update");
				//NOW GET ALL THE DATA
				doInitMaklumatFail(idPermohonan);
				doInitMaklumatHakmilik(idHakmilik);
			} else {
				initContextValue();
				this.context.put("readOnly", "");
			 	this.context.put("disabled", "");
				this.context.put("mode", "addHakmilik");
				doInitMaklumatFail(idPermohonan);
				doGetListBox();
				
			}
	
		} catch (Exception e) {
			 e.printStackTrace();
			 throw new Exception("Ralat:"+e.getCause());
			 
		}
	}
	
	
	public void getValuesAndAssignToContext() {
		String name="";String value="";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements(); ) {
	        name = (String)allparam.nextElement();
	        value = request.getParameter(name);
	        if (!"mode".equals(name)) {
	        	this.context.put(name,value);
	        }
		}
	}
	
	public String doInsertDetailHakmilik2() throws Exception {
		 //doUpdateDetailHakmilik2 ni sebenarnya INSERT.
		 //azam change method name accordingly
		 String idHakmilik = "0";
		 String idJenisRizab = getParam("socJenisRizab");
		 try {
			 Hashtable hHakmilikUpdate = new Hashtable();
			 hHakmilikUpdate.put("idPermohonan", getParam("idPermohonan"));
			 hHakmilikUpdate.put("socNegeriHR", getParam("socNegeri"));
			 hHakmilikUpdate.put("socDaerahHR", getParam("socDaerah"));
			 hHakmilikUpdate.put("socMukimHR", getParam("socMukim"));
			 hHakmilikUpdate.put("socJenisHakmilikHR", getParam("socJenisHakmilik"));
			 hHakmilikUpdate.put("txtNoHakmilik", getParam("txtNoHakmilik"));	
			 hHakmilikUpdate.put("socLotHR", getParam("noLot"));
			 hHakmilikUpdate.put("txtNoLot", getParam("txtNoLot"));
			 hHakmilikUpdate.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			 hHakmilikUpdate.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
			 hHakmilikUpdate.put("txtCukaiTahun", getParam("txtCukaiTahun"));
			 hHakmilikUpdate.put("txtLokasi", getParam("txtLokasi"));			
			 hHakmilikUpdate.put("socLuas", getParam("socLuas"));
			 hHakmilikUpdate.put("socTaraf", getParam("socTaraf"));
			 hHakmilikUpdate.put("socKategori", getParam("socKategori"));			
			 hHakmilikUpdate.put("txtNoPelan", getParam("txtNoPelan"));
			 hHakmilikUpdate.put("txtTempoh", getParam("txtTempoh"));			
			 hHakmilikUpdate.put("txtSyarat", getParam("txtSyarat"));
			 //hHakmilikUpdate.put("txtNoFailJopa", getParam("txtNoFailJopa"));			
			 //hHakmilikUpdate.put("txtHakmilikAsal", getParam("txtHakmilikAsal"));
			 //hHakmilikUpdate.put("txtCukaiTerkini", getParam("txtCukaiTerkini"));
			 hHakmilikUpdate.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
			 hHakmilikUpdate.put("txtLuas", getParam("txtLuas"));
			 hHakmilikUpdate.put("txtLuasGabung", getParam("txtLuasGabung"));
			 hHakmilikUpdate.put("txtTarafHakmilik", getParam("txtTarafHakmilik"));
			 hHakmilikUpdate.put("txdTarikhLuput", getParam("txdTarikhLuput"));			
			 hHakmilikUpdate.put("txtNoPu", getParam("txtNoPu"));
			 hHakmilikUpdate.put("txdTarikhWarta", getParam("txdTarikhWarta"));
			 hHakmilikUpdate.put("txtKawasanRizab", getParam("txtKawasanRizab"));
			 hHakmilikUpdate.put("txtNoSyit", getParam("txtNoSyit"));
			 hHakmilikUpdate.put("txtNoWarta", getParam("txtNoWarta"));
			 hHakmilikUpdate.put("txtSekatan", getParam("txtSekatan"));
			 hHakmilikUpdate.put("txtSyarat", getParam("txtSyarat"));
			 hHakmilikUpdate.put("txtKawasanRizab", getParam("txtKawasanRizab"));
			 hHakmilikUpdate.put("txtHakmilikBerikut", getParam("txtHakmilikBerikut"));
			 //hHakmilikUpdate.put("socStatus", getParam("socStatus"));
			 //AZAM REMARK-NOT SURE WHY D?
			 hHakmilikUpdate.put("socStatus", "D");
			 hHakmilikUpdate.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));			
			 hHakmilikUpdate.put("socJenisRizab",idJenisRizab );	
			 //hHakmilikUpdate.put("socRizab", getParam("socRizab"));	
			 hHakmilikUpdate.put("socRizab", (("".equals( getParam("socRizab")))?getParam("socRizab"):"X"));	
			 hHakmilikUpdate.put("idKementerian", getParam("txtIdKementerian"));	
			 hHakmilikUpdate.put("idAgensi", getParam("txtIdAgensi"));	
			 
			 hHakmilikUpdate.put("idMasuk",userId);	
			 
			 //AZAM ADD TRANSACTION HERE
			 /*
			 idHakmilik = FrmUtilData.insertHTPHakmilik(hHakmilikUpdate);
			 FrmUtilData.insertHakmilikPerihal(hHakmilikUpdate,idHakmilik);
			 FrmUtilData.insertHakmilikAgensi(hHakmilikUpdate,idHakmilik);
			 FrmUtilData.insertHakmilikCukai((String)hHakmilikUpdate.get("txtCukaiTahun"),
					 						  userId,idHakmilik);
		 	 */
			 idHakmilik = FrmUtilData.insertHTPHakmilikTransaction(hHakmilikUpdate,userId);
			 
		 } catch (SQLException se) {
			 throw new Exception(se.getMessage());
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 throw new Exception("Ralat:"+e.getMessage());
		 }
		 return idHakmilik;
	}
	
	public void dokemaskiniDetailHakmilik() throws Exception {
		log.debug("** dokemaskiniDetailHakmilik ** ");
		try {
			if ("update".equals(mode)) {
				 //Update Hakmilik - must match with db field
				 Hashtable hHakmilikUpdate = new Hashtable();
				 hHakmilikUpdate.put("no_fail_ptg", getParam("txtFailPTG")); 
				 hHakmilikUpdate.put("no_fail_ptd", getParam("txtFailPTD")); 
				 hHakmilikUpdate.put("id_jenishakmilik", getParam("socJenisHakmilik")); 
				 
				 hHakmilikUpdate.put("no_hakmilik", getParam("txtNoHakmilik")); 
				 hHakmilikUpdate.put("no_bangunan", getParam("txtNoBangunan")); 
				 hHakmilikUpdate.put("no_tingkat", getParam("txtNoTingkat")); 
				 hHakmilikUpdate.put("no_petak", getParam("txtNoPetak")); 

				 hHakmilikUpdate.put("id_lot", getParam("noLot")); 
				 hHakmilikUpdate.put("no_lot", getParam("txtNoLot")); 
				 
				 
				 hHakmilikUpdate.put("tarikh_terima",getParam("txdTarikhTerima"));	
				 hHakmilikUpdate.put("tarikh_daftar",getParam("txdTarikhDaftar"));	
				 
				 hHakmilikUpdate.put("taraf_hakmilik",getParam("socTaraf"));	
				 hHakmilikUpdate.put("tempoh",	      getParam("txtTempoh"));	
				 hHakmilikUpdate.put("tarikh_luput",getParam("txdTarikhLuput"));	
				 hHakmilikUpdate.put("cukai",	      getParam("txtCukaiTahun"));
				 hHakmilikUpdate.put("lokasi",	      getParam("txtLokasi"));	
				 
				 //table perihal
				 //hHakmilikUpdate.put("kegunaan_tanah",getParam("txtKegunaanTanah"));
				 hHakmilikUpdate.put("luas_lama",getParam("txtLuasGabung"));
				 hHakmilikUpdate.put("luas",getParam("txtLuas"));	
				 
				 
				 hHakmilikUpdate.put("status_rizab",getParam("statusRizab"));		
				 hHakmilikUpdate.put("no_warta",	getParam("txtNoWarta"));	
				 hHakmilikUpdate.put("tarikh_warta",getParam("txtTarikhWarta"));	
				 hHakmilikUpdate.put("kawasan_rizab",getParam("txtKawasanRizab"));	
				 hHakmilikUpdate.put("syarat",	      getParam("txtSyarat"));	
				 hHakmilikUpdate.put("sekatan",	getParam("txtSekatan"));	
				 hHakmilikUpdate.put("no_pelan",	getParam("txtNoPelan"));	
				 hHakmilikUpdate.put("no_syit",	getParam("txtNoSyit"));	
				 hHakmilikUpdate.put("no_pu",	      getParam("txtNoPu"));	
	
				 FrmUtilData.updateHTPHakmilik("TBLHTPHAKMILIK",hHakmilikUpdate,idHakmilik);
				 
				 Hashtable hHakmilikPerihal = new Hashtable();
				 hHakmilikPerihal.put("kegunaan_tanah",getParam("txtKegunaanTanah"));
				 FrmUtilData.updateHTPHakmilik("TBLHTPHAKMILIKPERIHAL",hHakmilikPerihal,idHakmilik);
				 //REFRESH DATA
				 doInitMaklumatHakmilik(idHakmilik);
				
				 this.context.put("readOnly", "readonly");
			 	 this.context.put("disabled", "disabled");	
				 this.context.put("mode", "doView");
				 this.context.put("INS_UPD", "doView");
			} else {
				 log.debug("VIEW MODE");
				 this.context.put("readOnly", "");
			 	 this.context.put("disabled", "");	
				 this.context.put("mode", "update");
				 this.context.put("INS_UPD", "update");
			}
		}
		 catch (Exception e) {
			 throw new Exception("Ralat:"+e.getMessage());
		 }

	}		
}

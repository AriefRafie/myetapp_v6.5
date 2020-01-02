package ekptg.view.integrasi.etanah;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.misc.Fraction;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.integrasi.FrmIntegrasiStatusBean;
import ekptg.model.integrasi.FrmLHDNBean;
import ekptg.model.integrasi.IIntegrasi;
import ekptg.model.integrasi.IIntegrasiStatus;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPerintahSek8Data;
import etapp.entity.integrasi.MaklumatCukai;
import etapp.entity.integrasi.StatusSemasa;

public class FrmMaklumateTaPP extends AjaxBasedModule {

	private final String PATH="app/integrasi/etanah/";
	private final String MODUL = "LHDN";
	private IHtp iHTP = null;  
	private IIntegrasi iIntegrasi = null;
	private IIntegrasiStatus iStatus = null;
	private static final long serialVersionUID = 1L;
	private String DISABILITY = " disabled class=\"disabled\" ";
	private String DISABILITYNUM = " disabled class=\"inputnumberdisabled\" ";
  	private String inputStyle = DISABILITY;
  	private String inputStyleNum = DISABILITYNUM;
	static Logger myLog = Logger.getLogger(ekptg.view.integrasi.etanah.FrmMaklumateTaPP.class);
	
  	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmPerintahSek8Data logic = new FrmPerintahSek8Data();
	String idUser = "";
	String role = null;
	Utils utils = new Utils();


	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();	
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    } 	    
	    role = (String)session.getAttribute("myrole");
	    myLog.info("CURRENT ROLE :"+role);	   
		idUser = (String)session.getAttribute("_ekptg_user_id");		
	    
	    //GET DEFAULT PARAM
		String vm = ""; 
        String actionPerintah = getParam("actionPerintah");
        String mode = getParam("mode");
        String hitButt = getParam("hitButt");
        String submit = getParam("command");       
        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String selectedTabLower = getParam("selectedTabLower").toString();
		if (selectedTabLower == null || "".equals(selectedTabLower) ) {
			selectedTabLower = "0";
		}
		String anchor = getParam("anchor");

		String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
        
        //GET VALUE PARAM
		String idPermohonanSimati = getParam("idPermohonanSimati");
        String idPermohonan = getParam("idPermohonan");
        String idPerintah = getParam("idPerintah");
        String idPerbicaraan = getParam("idPerbicaraan");
        String idSimati = getParam("idSimati");
        String idStatus = getParam("idStatus");
        
        String idHTA = getParam("idHTA");
        String idHA = getParam("idHA");
        
        String idJenisHA = getParam("idJenisHA");
        
        //GET FLAG PARAM
		String flagAdaHTA = "0";
		String flagAdaHTATH = "0";
		String flagAdaHA = "0";
		
		String flagAdaHTAPT = "0";
		String flagAdaHAPT = "0";
		String flagAdaHTAPKT = "0";
		String flagAdaHAPKT = "0";
		String flagAdaHTAPL = "0";
		String flagAdaHAPL = "0";
		String flagAdaHTAPF = "0";
		String flagAdaHAPF = "0";
		
		String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
		String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
		
		String flagSelesaiHTA = getParam("flagSelesaiHTA");
		String flagSelesaiHA = getParam("flagSelesaiHA");
		
		String flagSelesaiPembahagian = "Y";
		this.context.put("systemMsg", "");
        
        //VECTOR
        Vector list = new Vector();
        Vector beanMaklumatPermohonan = new Vector();
        Vector beanHeaderDetail = new Vector();
        
        list.clear();
        beanMaklumatPermohonan.clear();
        beanHeaderDetail.clear();
        
        Vector listHTA = new Vector();
        Vector beanMaklumatHTA = new Vector();
        Vector listHTAPopup = new Vector();
        Vector listHTATH = new Vector();
        Vector beanMaklumatHTATH = new Vector();
        Vector listHA = new Vector();
        Vector beanMaklumatHA = new Vector();
        Vector listHAPopup = new Vector();
        
        listHTA.clear();
        beanMaklumatHTA.clear();
        listHTAPopup.clear();
        listHTATH.clear();
        beanMaklumatHTATH.clear();
        listHA.clear();
        beanMaklumatHA.clear();
        listHAPopup.clear();
        
        Vector listHTAPT = new Vector();
        Vector listHAPT = new Vector();
        Vector listHTAPKT = new Vector();
        Vector listHAPKT = new Vector();
        Vector listHTAPL = new Vector();
        Vector listHAPL = new Vector();
        Vector listHTAPF = new Vector();
        Vector listHAPF = new Vector();
        Vector beanMaklumatHTAPL = new Vector();
        Vector beanMaklumatHAPL = new Vector();      
        
        listHTAPT.clear();
        listHAPT.clear();
        listHTAPKT.clear();
        listHAPKT.clear();
        listHTAPL.clear();
        listHAPL.clear();
        listHTAPF.clear();
        listHAPF.clear();        
        
        Vector listHTAPTDTL = new Vector();
        Vector listHAPTDTL = new Vector();
        Vector listHTAPKTDTL = new Vector();
        Vector listHAPKTDTL = new Vector();
        Vector listHTAPFDTL = new Vector();
        Vector listHAPFDTL = new Vector();
        Vector listPembahagianHTAPKTDTL = new Vector();  
        
        listHTAPTDTL.clear();
        listHAPTDTL.clear();
        listHTAPKTDTL.clear();
        listHAPKTDTL.clear();
        listHTAPFDTL.clear();
        listHAPFDTL.clear();
        listPembahagianHTAPKTDTL.clear();
        
        //GET DROPDOWN PARAM
		String jenisKp = getParam("socJenisKp");
		if (jenisKp == null || jenisKp.trim().length() == 0){
			jenisKp = "0";
		}
		
		String idJenisPerintahHTA = getParam("socJenisPerintahHTA");
		if (idJenisPerintahHTA == null || idJenisPerintahHTA.trim().length() == 0){
			idJenisPerintahHTA = "0";
		}
		String idJenisPerintahHA = getParam("socJenisPerintahHA");
		if (idJenisPerintahHA == null || idJenisPerintahHA.trim().length() == 0){
			idJenisPerintahHA = "0";
		}
	    
		this.context.put("onload", "");	
		String flagAdvSearch = getParam("flagAdvSearch");
		myLog.info(submit+",action="+action);
		if(submit.equals("")){
			vm = PATH+"senaraiBaruIndex.jsp";		
			list = new Vector();
			this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong(jenisKp), "", "class=\"autoselect\""));		
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
			this.context.put("txtSimati", getParam("txtSimati"));
			this.context.put("jenisIc", getParam("socJenisKp"));
			this.context.put("txtIcSimati", getParam("txtIcSimati"));			
			list = getIntegrasi().carianPerintahHQ(role, getParam("txtNoFail"),getParam("txtPemohon")
				, getParam("txtSimati"),getParam("socJenisKp"),getParam("txtIcSimati")
				, session,true);
			setupPage(session,action,list);

		}else if(submit.equals("tambahFail")){
			vm = PATH+"senaraiBaruIndex.jsp";		
			this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong(jenisKp), "", "class=\"autoselect\""));		
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
			this.context.put("txtSimati", getParam("txtSimati"));
			this.context.put("jenisIc", getParam("socJenisKp"));
			this.context.put("txtIcSimati", getParam("txtIcSimati"));			
			list = getIntegrasi().carianPerintahHQ(role, getParam("txtNoFail"),getParam("txtPemohon")
					, getParam("txtSimati"),getParam("socJenisKp"),getParam("txtIcSimati")
					, session,false);
			setupPage(session,action,list);
			
		}else if(submit.equals("paparsimati")){
			vm = PATH+"frmMaklumatCukai.jsp";
			
			if(mode.equals("simpan")){
				mode = "viewsimpan";
				myLog.info(getParam("idSimati"));
				Hashtable hInsert = setMaklumatValues("");
				String idMaklumatCukai = getIntegrasi().simpan(hInsert);
				simpanStatus("",idMaklumatCukai,MODUL,"2");
				
				getMaklumatValues(idMaklumatCukai);
				
			}else if(mode.equals("view")){
				myLog.info(getParam("idmaklumatcukai"));
			  	inputStyle = DISABILITY;
			  	inputStyleNum = DISABILITYNUM;
				getMaklumatValues(getParam("idmaklumatcukai"));
				
			}else if(mode.equals("kemaskini")){
				inputStyle = " class=\"\" ";
				inputStyleNum = " class=\"inputnumber\" ";
				myLog.info(getParam("idmaklumatcukai"));
				getMaklumatValues(getParam("idmaklumatcukai"));
			
			}else if(mode.equals("update")){
				StatusSemasa status = null;
				mode = "view";
				Hashtable hUpdate = setMaklumatValues(getParam("idmaklumatcukai"));
				String idMaklumatCukai = getIntegrasi().kemaskini(hUpdate);
				long idStatusModulSemasa = getStatus().getIdStatusModulByLangkah("2",MODUL,"=");
				status = new StatusSemasa();
				status.setIdMasuk(Long.parseLong(idUser));
				status.setIdStatusModul(idStatusModulSemasa);
				status.setIdSumber(Long.parseLong(idMaklumatCukai));
				getStatus().kemaskini(status);
			
				getMaklumatValues(idMaklumatCukai);
		
			}else{
				mode = "";
				inputStyle = " class=\"\" ";
				inputStyleNum = " class=\"inputnumber\" ";
				context.remove("idMaklumatCukai");
				context.remove("cukai");
				context.remove("tunggakan");
				context.remove("jumlah");
				context.remove("baki");
			}
			myLog.info("inputStyle="+inputStyle);
			context.put("inputStyle",inputStyle);
			context.put("inputStyleNum",inputStyleNum);
			context.put("jspMode",mode);
			
			
		    //SCREEN NAVIGATION
			if (actionPerintah.equals("papar")){
				
				//vm = "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
				
				//<-------------------- SELESAI PERMOHONAN --------------------------->
	        	
	        	if (hitButt.equals("selesaiPermohonan")){
	        		
	        		//CHECK ALL HTA YANG BELUM DIDAFTARKAN
					if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih (Ada Hakmilik) Simati Yang Belum Didaftarkan Perintahnya.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}				
					//CHECK ALL HA YANG BELUM DIDAFTARKAN
					if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
					//check all HTAPT telah dibahagikan sepenuhnya bagi perintah terus
					if (getParam("flagAdaHTAPT").equals("1")){
						if (logic.checkPembahagianHTAPTLengkap(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all HAPT telah dibahagikan sepenuhnya
					if (getParam("flagAdaHAPT").equals("1")){
						if (logic.checkPembahagianHAPTLengkap(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all HTA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
					if (getParam("flagAdaHTAPKT").equals("1")){
						if (logic.checkPembahagianHTAPKTLengkap(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all HA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
					if (getParam("flagAdaHAPKT").equals("1")){
						if (logic.checkPembahagianHAPKTLengkap(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all HTA telah dikemaskini bagi perintah lelong
					if (getParam("flagAdaHTAPL").equals("1")){
						if (logic.checkPembahagianHTAPLLengkap(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Tidak Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all HA telah dikemaskini bagi perintah lelong
					if (getParam("flagAdaHAPL").equals("1")){
						if (logic.checkPembahagianHAPLLengkap(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pa
					if (getParam("flagAdaHTAPT").equals("1")){
						if (logic.checkWarisYangPerluAdaPAHTAPT(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pa
					if (getParam("flagAdaHAPT").equals("1")){
						if (logic.checkWarisYangPerluAdaPAHAPT(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Terus.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pt
					if (getParam("flagAdaHTAPT").equals("1")){
						if (logic.checkWarisYangPerluAdaPTHTAPT(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pt
					if (getParam("flagAdaHAPT").equals("1")){
						if (logic.checkWarisYangPerluAdaPTHAPT(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Terus.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pa
					if (getParam("flagAdaHTAPF").equals("1")){
						if (logic.checkWarisYangPerluAdaPAHTAPF(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pa
					if (getParam("flagAdaHAPF").equals("1")){
						if (logic.checkWarisYangPerluAdaPAHAPF(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Faraid.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pt
					if (getParam("flagAdaHTAPF").equals("1")){
						if (logic.checkWarisYangPerluAdaPTHTAPF(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pt
					if (getParam("flagAdaHAPF").equals("1")){
						if (logic.checkWarisYangPerluAdaPTHAPF(idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Faraid.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
					}
					
					//all validation pass - proceed to hantar permohonan
					if (postDB){
						logic.selesaiPermohonan(idPermohonan, session);
					}
				}
	        	
	        	//<-------------------- SELESAI --------------------------->

				if (hitButt.equals("selesai")){					
					//all validation pass - proceed to selesai permohonan
					if (postDB){
						if (idStatus.equals("25")){

							//CHECK ALL HTA YANG BELUM DIDAFTARKAN
							if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih (Ada Hakmilik) Simati Yang Belum Didaftarkan Perintahnya.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}				
							//CHECK ALL HA YANG BELUM DIDAFTARKAN
							if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
							//check all HTAPT telah dibahagikan sepenuhnya bagi perintah terus
							if (getParam("flagAdaHTAPT").equals("1")){
								if (logic.checkPembahagianHTAPTLengkap(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all HAPT telah dibahagikan sepenuhnya
							if (getParam("flagAdaHAPT").equals("1")){
								if (logic.checkPembahagianHAPTLengkap(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all HTA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
							if (getParam("flagAdaHTAPKT").equals("1")){
								if (logic.checkPembahagianHTAPKTLengkap(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all HA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
							if (getParam("flagAdaHAPKT").equals("1")){
								if (logic.checkPembahagianHAPKTLengkap(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all HTA telah dikemaskini bagi perintah lelong
							if (getParam("flagAdaHTAPL").equals("1")){
								if (logic.checkPembahagianHTAPLLengkap(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Tidak Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all HA telah dikemaskini bagi perintah lelong
							if (getParam("flagAdaHAPL").equals("1")){
								if (logic.checkPembahagianHAPLLengkap(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pa
							if (getParam("flagAdaHTAPT").equals("1")){
								if (logic.checkWarisYangPerluAdaPAHTAPT(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pa
							if (getParam("flagAdaHAPT").equals("1")){
								if (logic.checkWarisYangPerluAdaPAHAPT(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Terus.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pt
							if (getParam("flagAdaHTAPT").equals("1")){
								if (logic.checkWarisYangPerluAdaPTHTAPT(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pt
							if (getParam("flagAdaHAPT").equals("1")){
								if (logic.checkWarisYangPerluAdaPTHAPT(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Terus.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pa
							if (getParam("flagAdaHTAPF").equals("1")){
								if (logic.checkWarisYangPerluAdaPAHTAPF(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pa
							if (getParam("flagAdaHAPF").equals("1")){
								if (logic.checkWarisYangPerluAdaPAHAPF(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Faraid.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pt
							if (getParam("flagAdaHTAPF").equals("1")){
								if (logic.checkWarisYangPerluAdaPTHTAPF(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pt
							if (getParam("flagAdaHAPF").equals("1")){
								if (logic.checkWarisYangPerluAdaPTHAPF(idPerintah)){
									this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Faraid.')\"");
									return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
								}
							}
							
							if (getParam("printOption").equals("potrait")){
								this.context.put("onload", " \"javascript:generateNotisKeluarGeranPot('" + getParam("idFail") + "')\"");
							} else {
								this.context.put("onload", " \"javascript:generateNotisKeluarGeranLand('" + getParam("idFail") + "')\"");
							}
							logic.selesai(idPermohonan, idPerintah, session,idPermohonanSimati);
							
						} else {
							
							if (getParam("printOption").equals("potrait")){
								this.context.put("onload", " \"javascript:generateNotisKeluarGeranPot('" + getParam("idFail") + "')\"");
							} else {
								this.context.put("onload", " \"javascript:generateNotisKeluarGeranLand('" + getParam("idFail") + "')\"");
							}
							
						}
					}
				}
				
	 			//<--------------------------- HARTA TAK ALIH (ADA HAKMILIK) --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA TAK ALIH (ADA HAKMILIK)
				if (hitButt.equals("simpanBaruHTA")){
					if (postDB) {
						String ids[] = request.getParameterValues("idspentadbir");
						if (ids != null) {
							for (int i = 0; i < ids.length; i++) {
								logic.saveHTA(getParam("socJenisPerintahHTA"), getParam("txtCatatanHTA"), ids[i], idPerintah, idPermohonan, idSimati, idPermohonanSimati, session);
							}
						}
						idJenisPerintahHTA = "0";
					}
				}
				if (hitButt.equals("hapusHTA")){
					if (postDB) {
						logic.hapusHTA(idHTA);
					}
				}
				if (hitButt.equals("simpanKemaskiniHTA")){
					if (getParam("jenisPerintah").equals(getParam("socJenisPerintahHTA"))){
						if (postDB) {
							logic.updateCatatanHTA(getParam("txtCatatanHTA"), idHTA, session);
						}
					} else {
						if (postDB) {
							logic.updateHTA(getParam("socJenisPerintahHTA"), getParam("txtCatatanHTA"), idHTA, idPermohonan, idSimati, idPermohonanSimati, idPerintah, session);
						}
					}
				}
				
				//OPEN POPUP HARTA TAK ALIH (ADA HAKMILIK)
	        	if (flagPopup.equals("openHTA")){
	        		this.context.put("idHTA", idHTA);
	        		
	        		//MODE = NEW
					if (modePopup.equals("new")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						
						//DETAIL HTA
						Hashtable hashHTA = new Hashtable();
						hashHTA.put("catatan", getParam("txtCatatanHTA") == null ? "" : getParam("txtCatatanHTA"));
						beanMaklumatHTA.addElement(hashHTA);
						this.context.put("BeanMaklumatHTA", beanMaklumatHTA);
						
						this.context.put("selectJenisPerintahHTA",HTML.SelectJenisPerintahSek8("socJenisPerintahHTA", Long.parseLong(idJenisPerintahHTA), "", ""));
						
						// SENARAI HTA
						logic.setDataSenaraiHTAPopup(idPermohonanSimati, idPerintah);
						listHTAPopup = logic.getSenaraiHTAPopup();
						this.context.put("SenaraiHTAPopup", listHTAPopup);
						
						Hashtable h = (Hashtable) listHTAPopup.get(0);
						if (h.get("bil").toString().equals("")){
							this.context.put("listSizeHTAPopup", 0);
						} else {
							this.context.put("listSizeHTAPopup", listHTAPopup.size());
						}
					}
					
					//MODE = UPDATE
					if (modePopup.equals("update")){
						if (mode.equals("update")){
							this.context.put("readonly", "");
							this.context.put("inputTextClass", "");
						} else {
							this.context.put("readonly", "readonly");
							this.context.put("inputTextClass", "disabled");
						}
						
						
						//DETAIL HTA
						logic.setDataMaklumatHTA(idHTA,idPermohonanSimati);
						beanMaklumatHTA = logic.getBeanMaklumatHTA();
						Hashtable hashHTA = (Hashtable) logic.getBeanMaklumatHTA().get(0);
						this.context.put("BeanMaklumatHTA", beanMaklumatHTA);
						
						if ("0".equals(idJenisPerintahHTA)){
							idJenisPerintahHTA = hashHTA.get("idJenisPerintah").toString();
						}
						if (mode.equals("update")){
							this.context.put("selectJenisPerintahHTA",HTML.SelectJenisPerintahSek8("socJenisPerintahHTA", Long.parseLong(idJenisPerintahHTA), "", ""));
						} else {
							this.context.put("selectJenisPerintahHTA",HTML.SelectJenisPerintahSek8("socJenisPerintahHTA", Long.parseLong(hashHTA.get("idJenisPerintah").toString()), "disabled", " class=\"disabled\""));
						}
						
						this.context.put("jenisPerintah", hashHTA.get("idJenisPerintah"));
					}
	        	}
	        	
	        	//<--------------------------- HARTA TAK ALIH (TIADA HAKMILIK) --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA TAK ALIH (TIADA HAKMILIK)
				if (hitButt.equals("simpanKemaskiniHTATH")){
					if (postDB) {
						logic.updateCatatanHTA(getParam("txtCatatanHTATH"), idHTA, session);
					}
				}
				//HITBUTTON FOR HAPUS HARTA TAK ALIH (TIADA HAKMILIK)
				if (hitButt.equals("hapusHTATH")){
					if (postDB) {
						logic.hapusHTATH(idHTA);
					}
				}
	        	//OPEN POPUP HARTA TAK ALIH (TIADA HAKMILIK)
	        	if (flagPopup.equals("openHTATH")){
	        		this.context.put("idHTA", idHTA);
					
					//MODE = UPDATE
					if (modePopup.equals("update")){
						if (mode.equals("update")){
							this.context.put("readonly", "");
							this.context.put("inputTextClass", "");
						} else {
							this.context.put("readonly", "readonly");
							this.context.put("inputTextClass", "disabled");
						}
						
						//DETAIL HTATH
						logic.setDataMaklumatHTATH(idHTA,idPermohonanSimati);					
						beanMaklumatHTATH = logic.getBeanMaklumatHTATH();
						Hashtable hashHTATH = (Hashtable) logic.getBeanMaklumatHTATH().get(0);
						this.context.put("BeanMaklumatHTATH", beanMaklumatHTATH);
					}
	        	}
	        	
	        	// <--------------------------- HARTA ALIH --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA ALIH
				if (hitButt.equals("simpanBaruHA")){
					if (postDB) {
						String ids[] = request.getParameterValues("idspentadbir");
						if (ids != null) {
							for (int i = 0; i < ids.length; i++) {
								logic.saveHA(getParam("socJenisPerintahHA"), getParam("txtCatatanHA"), ids[i], idPerintah, idPermohonan, idSimati, idPermohonanSimati, session);
							}
						}
						idJenisPerintahHA = "0";
					}
				}
				if (hitButt.equals("hapusHA")){
					if (postDB) {
						logic.hapusHA(idHA);
					}
				}
				if (hitButt.equals("simpanKemaskiniHA")){
					if (getParam("jenisPerintah").equals(getParam("socJenisPerintahHA"))){
						if (postDB) {
							logic.updateCatatanHA(getParam("txtCatatanHA"), idHA, session);
						}
					} else {
						if (postDB) {
							logic.updateHA(getParam("socJenisPerintahHA"), getParam("txtCatatanHA"), idHA, idPermohonan, idSimati, idPermohonanSimati, idJenisHA, idPerintah, session);
						}
					}
				}
	        	
	        	//OPEN POPUP HARTA ALIH
	        	if (flagPopup.equals("openHA")){
	        		this.context.put("idHA", idHA);
	        		
	        		//MODE = NEW
					if (modePopup.equals("new")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						
						//DETAIL HA
						Hashtable hashHA = new Hashtable();
						hashHA.put("catatan", getParam("txtCatatanHA") == null ? "" : getParam("txtCatatanHA"));
						beanMaklumatHA.addElement(hashHA);
						this.context.put("BeanMaklumatHA", beanMaklumatHA);
						
						this.context.put("selectJenisPerintahHA",HTML.SelectJenisPerintahSek8("socJenisPerintahHA", Long.parseLong(idJenisPerintahHA), "", ""));
						
						// SENARAI HA
						logic.setDataSenaraiHAPopup(idPermohonanSimati, idPerintah);
						listHAPopup = logic.getSenaraiHAPopup();
						this.context.put("SenaraiHAPopup", listHAPopup);
						
						Hashtable h = (Hashtable) listHAPopup.get(0);
						if (h.get("bil").toString().equals("")){
							this.context.put("listSizeHAPopup", 0);
						} else {
							this.context.put("listSizeHAPopup", listHAPopup.size());
						}
					}
					
					//MODE = UPDATE
					if (modePopup.equals("update")){
						if (mode.equals("update")){
							this.context.put("readonly", "");
							this.context.put("inputTextClass", "");
						} else {
							this.context.put("readonly", "readonly");
							this.context.put("inputTextClass", "disabled");
						}
						
						//DETAIL HA
						logic.setDataMaklumatHA(idHA,idPermohonanSimati);
						beanMaklumatHA = logic.getBeanMaklumatHA();
						Hashtable hashHA = (Hashtable) logic.getBeanMaklumatHA().get(0);
						this.context.put("BeanMaklumatHA", beanMaklumatHA);
						
						if ("0".equals(idJenisPerintahHA)){
							idJenisPerintahHA = hashHA.get("idJenisPerintah").toString();
						}
						idJenisHA = hashHA.get("idJenisHA").toString();
						
						if (mode.equals("update")){
							this.context.put("selectJenisPerintahHA",HTML.SelectJenisPerintahSek8("socJenisPerintahHA", Long.parseLong(idJenisPerintahHA), "", ""));
						} else {
							this.context.put("selectJenisPerintahHA",HTML.SelectJenisPerintahSek8("socJenisPerintahHA", Long.parseLong(hashHA.get("idJenisPerintah").toString()), "disabled", " class=\"disabled\""));
						}
						
						this.context.put("jenisPerintah", hashHA.get("idJenisPerintah"));
					}
	        	}
	        	
	        	// <--------------------------- HTAPT --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HTAPT
				if (hitButt.equals("simpanKemaskiniHTAPT")){
					if (postDB) {
						logic.updateCatatanHTA(getParam("txtCatatan"), idHTA, session);
						String ids[] = request.getParameterValues("idspentadbir");
						String status[] = request.getParameterValues("status");
						String BA[] = request.getParameterValues("txtBA");
						String BB[] = request.getParameterValues("txtBB");
						if (ids != null) {
							for (int i = 0; i < ids.length; i++) {
								if (BA[i] != null){
									if (Utils.parseLong(BA[i]) > 0){
										logic.updateHTAPT(idHTA, ids[i], status[i], BA[i], BB[i], idPerintah, session,idPermohonanSimati);
									} else {
										logic.removeHTAOBDTL(idHTA, ids[i]);
									}
								}
							}
						}
						
						//SAVE FOR WARIS YANG HILANG
						if (getParam("chkWarisHilang").equals("1") && getParamAsInteger("txtBAHilang") > 0){
							logic.updateHTAPTHilang(idHTA, idPerintah, getParam("txtBAHilang"), getParam("txtBBHilang"), session,idPermohonanSimati);
						} else {
							logic.removeHTAPTHilang(idHTA);
						}
						
						logic.updatePecahanWarisHTA(idHTA);
					}
				}
	        	
	        	//OPEN POPUP HARTA TAK ALIH PERINTAH TERUS
	        	if (flagPopup.equals("openHTAPT")){
	        		this.context.put("idHTA", idHTA);
	        		
	        		if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						this.context.put("disabled", "");
					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
						this.context.put("disabled", "disabled");
					}
	        		
	        		//HEADER DETAIL
	        		logic.setMaklumatHeaderDetailHTA(idHTA,idPermohonanSimati);
	    			beanHeaderDetail = logic.getBeanHeaderDetail();
	    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
	    			
	    			logic.setDataSenaraiHTAPTDTL(idHTA, idSimati, idPermohonanSimati);
					listHTAPTDTL = logic.getSenaraiHTAPTDTL();
					this.context.put("SenaraiHTAPTDTL", listHTAPTDTL);
					
					Fraction f = new Fraction(0,1);
					for (int i = 0; i < listHTAPTDTL.size(); i++){
						Hashtable hash = (Hashtable) listHTAPTDTL.get(i);
						long pembawah = 1;
						//Azam add checking
						if ("".equals(hash.get("BB"))) {
							hash.put("BB","0");
						}
						if ("".equals(hash.get("BA"))) {
							hash.put("BA","0");
						}
						//end here
						if (!hash.get("BB").equals("0")){
							pembawah = Long.parseLong(hash.get("BB").toString());
						}
						Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
						f = f.plus(f2);
					}

					logic.setDataSenaraiHTAPTDTLHilang(idHTA);
					if (logic.getSenaraiHTAPTDTLHilang().size() != 0){
						Hashtable hash = (Hashtable) logic.getSenaraiHTAPTDTLHilang().get(0);
						
						this.context.put("idHilang", hash.get("idPerintahHTAOBDTL"));
						this.context.put("BAHilang", hash.get("BA"));
						this.context.put("BBHilang", hash.get("BB"));
						this.context.put("checked", "checked");
						this.context.put("readonlyHilang", "");
						this.context.put("disabledHilang", "");
						
						Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),Long.parseLong(hash.get("BB").toString()));
						f = f.plus(f2);
						
					} else {
						
						this.context.put("idHilang", "");
						this.context.put("BAHilang", "0");
						this.context.put("BBHilang", "0");
						this.context.put("checked", "");
						this.context.put("readonlyHilang", "readonly");	
						this.context.put("disabledHilang", "disabled");
					}

					this.context.put("txtJumlahBA", f.numerator());
					this.context.put("txtJumlahBB", f.denominator());
	        	}
	        	
	        	// <--------------------------- HAPT --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HAPT
				if (hitButt.equals("simpanKemaskiniHAPT")){
					if (postDB) {
						logic.updateCatatanHA(getParam("txtCatatan"), idHA, session);
						String ids[] = request.getParameterValues("idspentadbir");
						String status[] = request.getParameterValues("status");
						String BA[] = request.getParameterValues("txtBA");
						String BB[] = request.getParameterValues("txtBB");
						if (ids != null) {
							for (int i = 0; i < ids.length; i++) {
								if (BA[i] != null){
									if (Utils.parseLong(BA[i]) > 0){
										logic.updateHAPT(idHA, ids[i], status[i], BA[i], BB[i], idPerintah, session);
									} else {
										logic.removeHAOBDTL(idHA, ids[i]);
									}
								}
							}
						}
						
						//save for waris yang hilang
						if (getParam("chkWarisHilang").equals("1") && getParamAsInteger("txtBAHilang") > 0){
							logic.updateHAPTHilang(idHA, idPerintah, getParam("txtBAHilang"), getParam("txtBBHilang"), session);
						} else {
							logic.removeHAPTHilang(idHA);
						}
						
						logic.updatePecahanWarisHA(idHA);
					}
				}
	        	
	        	//OPEN POPUP HARTA ALIH PERINTAH TERUS
	        	if (flagPopup.equals("openHAPT")){
	        		this.context.put("idHA", idHA);
	        		
	        		if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						this.context.put("disabled", "");
					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
						this.context.put("disabled", "disabled");
					}
	        		
	        		//HEADER DETAIL
	        		logic.setMaklumatHeaderDetailHA(idHA,idPermohonanSimati);
	    			beanHeaderDetail = logic.getBeanHeaderDetail();
	    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
	    			
	    			logic.setDataSenaraiHAPTDTL(idHA, idSimati, idPermohonanSimati);
					listHAPTDTL = logic.getSenaraiHAPTDTL();
					this.context.put("SenaraiHAPTDTL", listHAPTDTL);
					
					Fraction f = new Fraction(0,1);
					for (int i = 0; i < listHAPTDTL.size(); i++){
						Hashtable hash = (Hashtable) listHAPTDTL.get(i);
						long pembawah = 1;
						if (!hash.get("BB").equals("0")){
							pembawah = Long.parseLong(hash.get("BB").toString());
						}
						Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
						f = f.plus(f2);
					}
					
					logic.setDataSenaraiHAPTDTLHilang(idHA);
					if (logic.getSenaraiHAPTDTLHilang().size() != 0){
						Hashtable hash = (Hashtable) logic.getSenaraiHAPTDTLHilang().get(0);
						
						this.context.put("idHilang", hash.get("idPerintahHTAOBDTL"));
						this.context.put("BAHilang", hash.get("BA"));
						this.context.put("BBHilang", hash.get("BB"));
						this.context.put("checked", "checked");
						this.context.put("readonlyHilang", "");
						this.context.put("disabledHilang", "");
						
						Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),Long.parseLong(hash.get("BB").toString()));
						f = f.plus(f2);
						
					} else {
						
						this.context.put("idHilang", "");
						this.context.put("BAHilang", "0");
						this.context.put("BBHilang", "0");
						this.context.put("checked", "");
						this.context.put("readonlyHilang", "readonly");	
						this.context.put("disabledHilang", "disabled");
					}

					this.context.put("txtJumlahBA", f.numerator());
					this.context.put("txtJumlahBB", f.denominator());
	        	}
	        	
	        	
	        	// <--------------------------- HTAPKT --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA TAK ALIH PERINTAH KUASA TADBIR
	        	if (hitButt.equals("simpanKemaskiniHTAPKT")){
	        		if (postDB) {
	        			String idPA1 = "";
	        			String idPA2 = "";
	        			String idPA3 = "";
	        			String idPA4 = "";
	        			String ids[] = request.getParameterValues("idsOB");
	        			
	        			if (ids != null) {
	        				for (int i = 0; i < ids.length; i++) {
	        					if (i == 0){
	        						idPA1 = ids[i];
	        					} else if (i == 1){
	        						idPA2 = ids[i];
	        					} else if (i == 2){
	        						idPA3 = ids[i];
	        					} else if (i == 3){
	        						idPA4 = ids[i];
	        					}
	        				}
	        			}
	        			
	        			logic.updateCatatanHTA(getParam("txtCatatan"), idHTA, session);
	        			logic.updatePentadbir(idPA1, idPA2, idPA3, idPA4, idPerintah, session);
	        			
	        			//UPDATE PECAHAN HARTA
	        			String idspentadbir[] = request.getParameterValues("idspentadbir");
	        			String status[] = request.getParameterValues("status");
						String BA[] = request.getParameterValues("txtBA");
						String BB[] = request.getParameterValues("txtBB");
						
						if (idspentadbir != null) {
							for (int i = 0; i < idspentadbir.length; i++) {
								if (BA[i] != null){
									if (Utils.parseLong(BA[i]) > 0){
										logic.updatePembahagianHTAPKT(idHTA, idspentadbir[i], status[i], BA[i], BB[i], idPerintah, session, idPermohonanSimati);
									} else {
										logic.removePembahagianHTAPKT(idHTA, idspentadbir[i]);
									}
								}
							}
						}
						logic.updatePecahanWarisHTAPKT(idHTA);
	        		}
				}
	        	
	        	//OPEN POPUP HARTA TAK ALIH PERINTAH KUASA TADBIR
	        	if (flagPopup.equals("openHTAPKT")){
	        		this.context.put("idHTA", idHTA);
	        		
	        		if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						this.context.put("disabled", "");
					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
						this.context.put("disabled", "disabled");
					}
	        		
	        		//HEADER DETAIL
	        		logic.setMaklumatHeaderDetailHTA(idHTA,idPermohonanSimati);
	    			beanHeaderDetail = logic.getBeanHeaderDetail();
	    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
	    			
	    			// SENARAI HTAPKT
	    			logic.setDataSenaraiHTAPKTDTL(idHTA, idPermohonanSimati, idSimati);
	    			listHTAPKTDTL = logic.getSenaraiHTAPKTDTL();
	    			this.context.put("ListHTAPKTDTL", listHTAPKTDTL);
	    			
	    			Hashtable h = (Hashtable) listHTAPKTDTL.get(0);
	    			if (h.get("bil").toString().equals("")){
	    				this.context.put("listSize", 0);
	    			} else {
	    				this.context.put("listSize", listHTAPKTDTL.size());
	    			}
	    			
	    			// SENARAI PEMBAHAGIAN HTAPKT
	    			logic.setDataSenaraiPembahagianHTAPKTDTL(idHTA, idSimati, idPermohonanSimati);
					listPembahagianHTAPKTDTL = logic.getSenaraiPembahagianHTAPKTDTL();
					this.context.put("SenaraiPembahagianHTAPKTDTL", listPembahagianHTAPKTDTL);
					
					Fraction f = new Fraction(0,1);
					for (int i = 0; i < listPembahagianHTAPKTDTL.size(); i++){
						Hashtable hash = (Hashtable) listPembahagianHTAPKTDTL.get(i);
						long pembawah = 1;
						//Azam add checking
						if ("".equals(hash.get("BB"))) {
							hash.put("BB","0");
						}
						if ("".equals(hash.get("BA"))) {
							hash.put("BA","0");
						}
						//end here
						if (!hash.get("BB").equals("0")){
							pembawah = Long.parseLong(hash.get("BB").toString());
						}
						Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
						f = f.plus(f2);
						
						this.context.put("txtJumlahBA", f.numerator());
						this.context.put("txtJumlahBB", f.denominator());
					}
	    			
	        	}
	        	
	        	// <--------------------------- HAPKT --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA ALIH PERINTAH KUASA TADBIR
	        	if (hitButt.equals("simpanKemaskiniHAPKT")){
	        		if (postDB) {
	        			String idPA1 = "";
	        			String idPA2 = "";
	        			String idPA3 = "";
	        			String idPA4 = "";
	        			String ids[] = request.getParameterValues("idspentadbir");
	        			
	        			if (ids != null) {
	        				for (int i = 0; i < ids.length; i++) {
	        					if (i == 0){
	        						idPA1 = ids[i];
	        					} else if (i == 1){
	        						idPA2 = ids[i];
	        					} else if (i == 2){
	        						idPA3 = ids[i];
	        					} else if (i == 3){
	        						idPA4 = ids[i];
	        					}
	        				}
	        			}
	        			
	        			logic.updateCatatanHA(getParam("txtCatatan"), idHA, session);
	        			logic.updatePentadbir(idPA1, idPA2, idPA3, idPA4, idPerintah, session);
	        		}
				}
	        	
	        	//OPEN POPUP HARTA ALIH PERINTAH KUASA TADBIR
	        	if (flagPopup.equals("openHAPKT")){
	        		this.context.put("idHA", idHA);
	        		
	        		if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						this.context.put("disabled", "");
					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
						this.context.put("disabled", "disabled");
					}
	        		
	        		//HEADER DETAIL
	        		logic.setMaklumatHeaderDetailHA(idHA,idPermohonanSimati);
	    			beanHeaderDetail = logic.getBeanHeaderDetail();
	    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
	    			
	    			// SENARAI HAPKT
	    			logic.setDataSenaraiHAPKTDTL(idHA, idPermohonanSimati, idSimati);
	    			listHAPKTDTL = logic.getSenaraiHAPKTDTL();
	    			this.context.put("ListHAPKTDTL", listHAPKTDTL);
	    			
	    			Hashtable h = (Hashtable) listHAPKTDTL.get(0);
	    			if (h.get("bil").toString().equals("")){
	    				this.context.put("listSize", 0);
	    			} else {
	    				this.context.put("listSize", listHAPKTDTL.size());
	    			}
	        	}
	        	
	        	// <--------------------------- HTAPL --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA TAK ALIH PERINTAH LELONG
	        	if (hitButt.equals("simpanKemaskiniHTAPL")){
	        		if (postDB) {
	        			updateHTAPL(idHTA, session);
	        		}
				}
	        	
	        	//OPEN POPUP HARTA TAK ALIH PERINTAH LELONG
	        	if (flagPopup.equals("openHTAPL")){
	        		this.context.put("idHTA", idHTA);
					
					//MODE = UPDATE
					if (modePopup.equals("update")){
						if (mode.equals("update")){
							this.context.put("style", "style1");
							this.context.put("readonly", "");
							this.context.put("inputTextClass", "");
							this.context.put("disabled", "");
						} else {
							this.context.put("style", "");
							this.context.put("readonly", "readonly");
							this.context.put("inputTextClass", "disabled");
							this.context.put("disabled", "disabled");
						}					
						
						//DETAIL
						logic.setDataMaklumatHTAPL(idHTA,idPermohonanSimati);
						beanMaklumatHTAPL = logic.getBeanMaklumatHTAPL();
						Hashtable hashHTAPL = (Hashtable) logic.getBeanMaklumatHTAPL().get(0);
						this.context.put("BeanMaklumatHTAPL", beanMaklumatHTAPL);
											
						if (hashHTAPL.get("jenisLelong").equals("A")){
							this.context.put("checkedA", "checked");
							this.context.put("checkedT", "");
						} else if (hashHTAPL.get("jenisLelong").equals("T")){
							this.context.put("checkedA", "");
							this.context.put("checkedT", "checked");
						} else {
							this.context.put("checkedA", "");
							this.context.put("checkedT", "");
						}
					}
	        	}
	        	
	        	// <--------------------------- HAPL --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA ALIH PERINTAH LELONG
	        	if (hitButt.equals("simpanKemaskiniHAPL")){
	        		if (postDB) {
	        			updateHAPL(idHA,session);
	        		}
				}
	        	
	        	//OPEN POPUP FOR HARTA ALIH PERINTAH LELONG
	        	if (flagPopup.equals("openHAPL")){
	        		this.context.put("idHA", idHA);
					
					//MODE = UPDATE
					if (modePopup.equals("update")){
						if (mode.equals("update")){
							this.context.put("style", "style1");
							this.context.put("readonly", "");
							this.context.put("inputTextClass", "");
							this.context.put("disabled", "");
						} else {
							this.context.put("style", "");
							this.context.put("readonly", "readonly");
							this.context.put("inputTextClass", "disabled");
							this.context.put("disabled", "disabled");
						}
						
						//detail
						logic.setDataMaklumatHAPL(idHA,idPermohonanSimati);
						beanMaklumatHAPL = logic.getBeanMaklumatHAPL();
						Hashtable hashHAPL = (Hashtable) logic.getBeanMaklumatHAPL().get(0);
						this.context.put("BeanMaklumatHAPL", beanMaklumatHAPL);
						
						if (hashHAPL.get("jenisLelong").equals("A")){
							this.context.put("checkedA", "checked");
							this.context.put("checkedT", "");
						} else if (hashHAPL.get("jenisLelong").equals("T")){
							this.context.put("checkedA", "");
							this.context.put("checkedT", "checked");
						} else {
							this.context.put("checkedA", "");
							this.context.put("checkedT", "");
						}
					}
	        	}
	        	
	        	// <--------------------------- HTAPF --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA TAK ALIH PERINTAH FARAID
	        	if (hitButt.equals("simpanKemaskiniHTAPF")){
	        		if (postDB) {
	        			logic.updateCatatanHTA(getParam("txtCatatan"), idHTA, session);
	        		}
				}
	        	
	        	//OPEN POPUP HARTA TAK ALIH PERINTAH FARAID
	        	if (flagPopup.equals("openHTAPF")){
	        		this.context.put("idHTA", idHTA);
	        		
	        		if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");

					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
					}
	        		
	        		//HEADER DETAIL
	        		logic.setMaklumatHeaderDetailHTA(idHTA,idPermohonanSimati);
	    			beanHeaderDetail = logic.getBeanHeaderDetail();
	    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
	    			
	    			logic.setDataSenaraiHTAPFDTL(idHTA,idPermohonanSimati);
					listHTAPFDTL = logic.getSenaraiHTAPFDTL();
					this.context.put("SenaraiHTAPFDTL", listHTAPFDTL);
					
					Fraction f = new Fraction(0,1);
					for (int i = 0; i < listHTAPFDTL.size(); i++){
						Hashtable hash = (Hashtable) listHTAPFDTL.get(i);
						long pembawah = 1;
						if (!hash.get("BB").equals("0")){
							pembawah = Long.parseLong(hash.get("BB").toString());
						}
						Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
						f = f.plus(f2);
					}
					this.context.put("jumlahBahagian", f.numerator() + " / " + f.denominator());
	        	}
	        	
	        	// <--------------------------- HAPF --------------------------->
	        	
	        	//HITBUTTON FOR POPUP HARTA ALIH PERINTAH FARAID
	        	if (hitButt.equals("simpanKemaskiniHAPF")){
	        		if (postDB) {
	        			logic.updateCatatanHA(getParam("txtCatatan"), idHA, session);
	        		}
				}
	        	
	        	//OPEN POPUP HARTA ALIH PERINTAH FARAID
	        	if (flagPopup.equals("openHAPF")){
	        		this.context.put("idHA", idHA);
	        		
	        		if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");

					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
					}
	        		
	        		//HEADER DETAIL
	        		logic.setMaklumatHeaderDetailHA(idHA,idPermohonanSimati);
	    			beanHeaderDetail = logic.getBeanHeaderDetail();
	    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
	    			
	    			logic.setDataSenaraiHAPFDTL(idHA,idPermohonanSimati);
					listHAPFDTL = logic.getSenaraiHAPFDTL();
					this.context.put("SenaraiHAPFDTL", listHAPFDTL);
					
					Fraction f = new Fraction(0,1);
					for (int i = 0; i < listHAPFDTL.size(); i++){
						Hashtable hash = (Hashtable) listHAPFDTL.get(i);
						long pembawah = 1;
						if (!hash.get("BB").equals("0")){
							pembawah = Long.parseLong(hash.get("BB").toString());
						}
						Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
						f = f.plus(f2);
					}
					this.context.put("jumlahBahagian", f.numerator() + " / " + f.denominator());
	        	}
				
				//HEADER
				logic.setMaklumatPermohonan(idPermohonan,session);
				beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
				this.context.put("MaklumatPermohonan", beanMaklumatPermohonan);
				//hashtable maklumat header
				headerppk_baru(session,idPermohonan,"Y","","T");
				
				if (beanMaklumatPermohonan.size() != 0){
					//SET MODE AND ID_STATUS
					Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
					if (hashHeader.get("id_Status").equals("41") || hashHeader.get("id_Status").equals("25")){
						idStatus = hashHeader.get("id_Status").toString();
						mode = "update";
					} else {
						String roleId = "";  
						roleId = (String)session.getAttribute("_portal_role");
						if (roleId.equals("adminppk")){
							idStatus = hashHeader.get("id_Status").toString();
							mode = "update";
						} else {
							idStatus = hashHeader.get("id_Status").toString();
							//mode = "view";
							//man tukar flag
							mode = "update";
						}
				   		
					}
				}
				
				//GET ID PERINTAH
				if (idPerintah.isEmpty()){
					idPerintah =  logic.getIdPerintah(idPermohonan);
				}
				this.context.put("idPerintah", idPerintah);
				
				//GET ID PERBICARAAN
				if (idPerbicaraan.isEmpty()){
					idPerbicaraan =  logic.getIdPerbicaraan(idPerintah);
				}
				this.context.put("idPerbicaraan", idPerbicaraan);
				
				
				
				//CHECK IF SIMATI ADA HTA, HTATH AND HA		
				if (logic.checkExistHTA(idPermohonanSimati)){
					flagAdaHTA = "1";
				}
				if (logic.checkExistHTATH(idPermohonanSimati)){
					flagAdaHTATH = "1";
				}
				if (logic.checkExistHA(idPermohonanSimati)){
					flagAdaHA = "1";
				}
				
				//AUTOMATIC REGISTER HTATH INTO PERINTAH KUASA TADBIR IF EXIST
				if (flagAdaHTATH.equals("1")){
					logic.registerHTATHIntoPKT(idPerintah, idPermohonanSimati, session);
				}
				
				//GET LIST HARTA TAK ALIH (ADA HAKMILIK)
				if (flagAdaHTA.equals("1")){
					logic.setDataSenaraiHTA(idPerintah,idPermohonanSimati);
					//###temp
					//logic.setDataMaklumatHTATH(idHTA,idPermohonanSimati);
					
					listHTA = logic.getSenaraiHTA();
					this.context.put("SenaraiHTA", listHTA);
					
					//CHECK ALL HTA YANG BELUM DIDAFTARKAN
					if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
						flagSelesaiHTA = "";
					} else {
						flagSelesaiHTA = "Y";
					}
				} 
				
				//GET LIST HARTA TAK ALIH (TIADA HAKMILIK)
				if (flagAdaHTATH.equals("1")){
					logic.setDataSenaraiHTATH(idPerintah,idPermohonanSimati);
					listHTATH = logic.getSenaraiHTATH();
					this.context.put("SenaraiHTATH", listHTATH);
				}
				
				//GET LIST HARTA ALIH
				if (flagAdaHA.equals("1")){
					logic.setDataSenaraiHA(idPerintah,idPermohonanSimati);
					listHA = logic.getSenaraiHA();
					this.context.put("SenaraiHA", listHA);
					
					//CHECK ALL HA YANG BELUM DIDAFTARKAN
					if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
						flagSelesaiHA = "";
					} else {
						flagSelesaiHA = "Y";
					}
				}
				
				//CHECK IF ADA PEMBAHAGIAN HTA AND HA FOR PT,PKT,PL AND PF		
				if (flagAdaHTA.equals("1") || flagAdaHTATH.equals("1")){
					//HTA PERINTAH TERUS
					if (logic.checkExistHTAPT(idPerintah)){
						flagAdaHTAPT = "1";
					}
					//HTA PERINTAH KUASA TADBIR
					if (logic.checkExistHTAPKT(idPerintah)){
						flagAdaHTAPKT = "1";
					}
					//HTA PERINTAH LELONG
					if (logic.checkExistHTAPL(idPerintah)){
						flagAdaHTAPL = "1";
					}
					//HTA PERINTAH FARAID
					if (logic.checkExistHTAPF(idPerintah)){
						flagAdaHTAPF = "1";
					}
				}
				if (flagAdaHA.equals("1")){
					//HA PERINTAH TERUS
					if (logic.checkExistHAPT(idPerintah)){
						flagAdaHAPT = "1";
					}
					//HA PERINTAH KUASA TADBIR
					if (logic.checkExistHAPKT(idPerintah)){
						flagAdaHAPKT = "1";
					}
					//HA PERINTAH LELONG
					if (logic.checkExistHAPL(idPerintah)){
						flagAdaHAPL = "1";
					}
					//HA PERINTAH FARAID
					if (logic.checkExistHAPF(idPerintah)){
						flagAdaHAPF = "1";
					}
				}
				
				if (flagAdaHTAPT.equals("1")){
					logic.setDataSenaraiHTAPT(idPerintah,idPermohonanSimati);
					listHTAPT = logic.getSenaraiHTAPT();
					this.context.put("SenaraiHTAPT", listHTAPT);
				}			
				if (flagAdaHAPT.equals("1")){
					logic.setDataSenaraiHAPT(idPerintah,idPermohonanSimati);
					listHAPT = logic.getSenaraiHAPT();
					this.context.put("SenaraiHAPT", listHAPT);
				}
				if (flagAdaHTAPKT.equals("1")){
					logic.setDataSenaraiHTAPKT(idPerintah,idPermohonanSimati);
					listHTAPKT = logic.getSenaraiHTAPKT();
					this.context.put("SenaraiHTAPKT", listHTAPKT);
				}
				if (flagAdaHAPKT.equals("1")){
					logic.setDataSenaraiHAPKT(idPerintah,idPermohonanSimati);
					listHAPKT = logic.getSenaraiHAPKT();
					this.context.put("SenaraiHAPKT", listHAPKT);
				}
				if (flagAdaHTAPL.equals("1")){
					logic.setDataSenaraiHTAPL(idPerintah,idPermohonanSimati);
					listHTAPL = logic.getSenaraiHTAPL();
					this.context.put("SenaraiHTAPL", listHTAPL);
				}
				if (flagAdaHAPL.equals("1")){
					logic.setDataSenaraiHAPL(idPerintah,idPermohonanSimati);
					listHAPL = logic.getSenaraiHAPL();
					this.context.put("SenaraiHAPL", listHAPL);
				}
				if (flagAdaHTAPF.equals("1")){
					logic.setDataSenaraiHTAPF(idPerintah,idPermohonanSimati);
					listHTAPF = logic.getSenaraiHTAPF();
					this.context.put("SenaraiHTAPF", listHTAPF);
				}
				if (flagAdaHAPF.equals("1")){
					logic.setDataSenaraiHAPF(idPerintah,idPermohonanSimati);
					listHAPF = logic.getSenaraiHAPF();
					this.context.put("SenaraiHAPF", listHAPF);
				}
				
			} else {
				
				//GO TO LIST PERINTAH
				
				vm = "app/ppk/frmSenaraiFailPerintahSek8.jsp";
				
				if(role.equals("userSemakanPerintah") || role.contains("(INTEGRASI)"))
				{
					myLog.info(role);
					
					logic.carianFail_semakanPerintahHQ(role,getParam("txtNoFail"),getParam("txtPemohon"), getParam("txtSimati"),getParam("socJenisKp"), getParam("txtIcSimati"), session);
					list = logic.getSenaraiFail_semakanPerintahHQ();
				}
				else
				{
				logic.carianFail(getParam("txtNoFail"),getParam("txtPemohon"), getParam("txtSimati"), getParam("socJenisKp"), getParam("txtIcSimati"), session);
				list = logic.getSenaraiFail();
				}
				this.context.put("SenaraiFail", list);
				
				this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong(jenisKp), "", "class=\"autoselect\""));
				
				this.context.put("txtNoFail", getParam("txtNoFail"));
				this.context.put("txtPemohon", getParam("txtPemohon"));
				this.context.put("txtSimati", getParam("txtSimati"));
				this.context.put("jenisIc", getParam("socJenisKp"));
				this.context.put("txtIcSimati", getParam("txtIcSimati"));
				
				setupPage(session,action,list);
			}

		}else{
			this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong(jenisKp), "", "class=\"autoselect\""));		
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
			this.context.put("txtSimati", getParam("txtSimati"));
			this.context.put("jenisIc", getParam("socJenisKp"));
			this.context.put("txtIcSimati", getParam("txtIcSimati"));			
			myLog.info(getParam("pagejsp"));
			if(getParam("pagejsp").equals("index")){
				vm = PATH+"senaraiBaruIndex.jsp";
				list = new Vector();
				list = getIntegrasi().carianPerintahHQSemak(role, getParam("txtNoFail"),getParam("txtPemohon")
						, getParam("txtSimati"),getParam("socJenisKp"),getParam("txtIcSimati")
						, session);

			}else if(getParam("pagejsp").equals("senarai")){
				vm = PATH+"senaraiBaruIndex.jsp";		
				list = new Vector();
				if(flagAdvSearch.equals("")){
					list = getIntegrasi().carianPerintahHQ(role, getParam("txtNoFail"),getParam("txtPemohon")
						, getParam("txtSimati"),getParam("socJenisKp"),getParam("txtIcSimati")
						, session, true);
					
				}else{
					list = getIntegrasi().carianPerintahHQ(role, getParam("txtNoFail"),getParam("txtPemohon")
						, getParam("txtSimati"),getParam("socJenisKp"),getParam("txtIcSimati")
						, session, false);
					
				}
				
			}
			setupPage(session,action,list);
			
		}
		
		//SET DEFAULT PARAM
		context.put("flagAdvSearch", flagAdvSearch);
		this.context.put("actionPerintah", actionPerintah);
        this.context.put("mode", mode);
        this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
        this.context.put("selectedTabUpper", selectedTabUpper);
        this.context.put("selectedTabLower", selectedTabLower);
        
        //SET FLAG PARAM
        this.context.put("flagAdaHTA", flagAdaHTA);
        this.context.put("flagAdaHTATH", flagAdaHTATH);
        this.context.put("flagAdaHA", flagAdaHA);
        
        this.context.put("flagAdaHTAPT", flagAdaHTAPT);
        this.context.put("flagAdaHAPT", flagAdaHAPT);
        this.context.put("flagAdaHTAPKT", flagAdaHTAPKT);
        this.context.put("flagAdaHAPKT", flagAdaHAPKT);
        this.context.put("flagAdaHTAPL", flagAdaHTAPL);
        this.context.put("flagAdaHAPL", flagAdaHAPL);
        this.context.put("flagAdaHTAPF", flagAdaHTAPF);
        this.context.put("flagAdaHAPF", flagAdaHAPF);
        
        this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
		this.context.put("flagFromSenaraiPermohonanSek8", flagFromSenaraiPermohonanSek8);
		
		this.context.put("flagSelesaiHTA", flagSelesaiHTA);
		this.context.put("flagSelesaiHA", flagSelesaiHA);
		
		if (checkSelesaiPembahagian(idPermohonanSimati, idPerintah)){
			flagSelesaiPembahagian = "T";
		}
		this.context.put("flagSelesaiPembahagian", flagSelesaiPembahagian);
		
        
        //SET ID PARAM
        this.context.put("idPermohonanSimati", idPermohonanSimati);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPerintah", idPerintah);
        this.context.put("idSimati", idSimati);
        this.context.put("idStatus", idStatus);
        
        if (!anchor.equals("")){
        	this.context.put("onload", " \"javascript:goToAnchor('" + anchor + "')\"");
        }
	    
		return vm;
		
	}
	
	private boolean checkSelesaiPembahagian(String idPermohonanSimati, String idPerintah) throws Exception {

		//CHECK ALL HTA YANG BELUM DIDAFTARKAN
		if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
			this.context.put("systemMsg", "Masih Terdapat Harta Tidak Alih (Ada Hakmilik) Simati Yang Belum Didaftarkan Perintahnya.");
			return true;
		}				
		//CHECK ALL HA YANG BELUM DIDAFTARKAN
		if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
			this.context.put("systemMsg", "Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.");
			return true;
		}
		//check all HTAPT telah dibahagikan sepenuhnya bagi perintah terus
		if (getParam("flagAdaHTAPT").equals("1")){
			if (logic.checkPembahagianHTAPTLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Harta Tidak Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.");
				return true;
			}
		}
		//check all HAPT telah dibahagikan sepenuhnya
		if (getParam("flagAdaHAPT").equals("1")){
			if (logic.checkPembahagianHAPTLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Harta Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.");
				return true;
			}
		}
		//check all HTA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
		if (getParam("flagAdaHTAPKT").equals("1")){
			if (logic.checkPembahagianHTAPKTLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Harta Tidak Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.");
				return true;
			}
		}
		//check all HA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
		if (getParam("flagAdaHAPKT").equals("1")){
			if (logic.checkPembahagianHAPKTLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Harta Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.");
				return true;
			}
		}
		//check all HTA telah dikemaskini bagi perintah lelong
		if (getParam("flagAdaHTAPL").equals("1")){
			if (logic.checkPembahagianHTAPLLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Maklumat Harta Tidak Alih Yang Belum Dikemaskini Bagi Perintah Lelong.");
				return true;
			}
		}
		//check all HA telah dikemaskini bagi perintah lelong
		if (getParam("flagAdaHAPL").equals("1")){
			if (logic.checkPembahagianHAPLLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Maklumat Harta Alih Yang Belum Dikemaskini Bagi Perintah Lelong.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pa
		if (getParam("flagAdaHTAPT").equals("1")){
			if (logic.checkWarisYangPerluAdaPAHTAPT(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Terus.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pa
		if (getParam("flagAdaHAPT").equals("1")){
			if (logic.checkWarisYangPerluAdaPAHAPT(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Terus.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pt
		if (getParam("flagAdaHTAPT").equals("1")){
			if (logic.checkWarisYangPerluAdaPTHTAPT(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Terus.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pt
		if (getParam("flagAdaHAPT").equals("1")){
			if (logic.checkWarisYangPerluAdaPTHAPT(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Terus.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pa
		if (getParam("flagAdaHTAPF").equals("1")){
			if (logic.checkWarisYangPerluAdaPAHTAPF(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Faraid.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pa
		if (getParam("flagAdaHAPF").equals("1")){
			if (logic.checkWarisYangPerluAdaPAHAPF(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Faraid.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pt
		if (getParam("flagAdaHTAPF").equals("1")){
			if (logic.checkWarisYangPerluAdaPTHTAPF(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Faraid.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pt
		if (getParam("flagAdaHAPF").equals("1")){
			if (logic.checkWarisYangPerluAdaPTHAPF(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Faraid.");
				return true;
			}
		}
		
		return false;
	}

	private void updateHTAPL(String idHTA, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("jenisLelong", getParam("sorJenisLelong"));
		hash.put("tarikhJualan", getParam("txdTarikhJualan"));
		hash.put("hargaRizab", utils.RemoveSymbol(getParam("txtHargaRizab").toString()));
		hash.put("amaun", utils.RemoveSymbol(getParam("txtAmaun").toString()));
		hash.put("catatan", getParam("txtCatatanHTAPL"));
		logic.updateHTAPL(idHTA,hash,session);
	}
	
	private void updateHAPL(String idHAMST, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("jenisLelong", getParam("sorJenisLelongHAPL"));
		hash.put("tarikhJualan", getParam("txdTarikhJualanHAPL"));
		hash.put("hargaRizab", utils.RemoveSymbol(getParam("txtHargaRizabHAPL").toString()));
		hash.put("amaun", utils.RemoveSymbol(getParam("txtAmaunHAPL").toString()));
		hash.put("catatan", getParam("txtCatatanHTAPL"));
		logic.updateHAPL(idHAMST,hash,session);
	}

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
				this.context.put("SenaraiFail",paging.getPage(page));
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
	
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
		this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
		this.context.put("list_sub_header",list_sub);
		this.context.put("flag_jenis_vm","ajax");
	}
	private void headerppk_baru_default()
	{
		Hashtable headerppk = null;
		this.context.put("headerppk","");
		this.context.put("list_sub_header","");
		this.context.put("flag_jenis_vm","ajax");
	}
	
	private Hashtable setMaklumatValues(String idPermohonan) throws Exception{
		String idSimati = getParam("idSimati");
		String cukai =  Utils.RemoveComma(getParam("txtcukai"));
		String tunggakan = Utils.RemoveComma(getParam("txttunggakan"));
		String jumlah =  Utils.RemoveComma(getParam("txtjumlah"));
		String baki =  Utils.RemoveComma(getParam("txtbaki"));
		Hashtable h = new Hashtable();
		h.put("idSimati", idSimati);
		h.put("cukai", cukai);
		h.put("tunggakan", tunggakan);
		h.put("jumlah",jumlah);
		h.put("baki", baki);
		h.put("idMasuk",idUser);
		h.put("idMaklumatCukai",idPermohonan);
		return h;
		
	}
	
	private void getMaklumatValues(String id) throws Exception{
		MaklumatCukai mc = getIntegrasi().getMaklumat(id);
		String cukai =  Utils.format2Decimal(mc.getCukai());
		String tunggakan = Utils.format2Decimal(mc.getTunggakan());
		String jumlah =  Utils.format2Decimal(mc.getJumlah());
		String baki =  Utils.format2Decimal(mc.getBaki());
		Hashtable h = new Hashtable();
		context.put("idMaklumatCukai", id);
		context.put("cukai", cukai);
		context.put("tunggakan", tunggakan);
		context.put("jumlah",jumlah);
		context.put("baki", baki);
		
	}
	
	// Skrin Tindakan
	private String simpanStatus(String idStatusModul,String idSumber,String modul,String langkah)throws Exception {
		StatusSemasa ss = null;
		try {
			ss = new StatusSemasa();
			
			ss.setIdStatusModul(getStatus().getIdStatusModulByLangkah(String.valueOf((Integer.parseInt(langkah)-1)),modul,"="));
			ss.setIdSumber(Long.parseLong(idSumber));
			ss.setAktif("0");
			myLog.info("idFail="+idStatusModul+",idPermohonan="+idSumber);
			
			StatusSemasa semasa = new StatusSemasa();
			long idStatusModulSemasa = getStatus().getIdStatusModulByLangkah(langkah,modul,"=");
			semasa.setIdStatusModul(idStatusModulSemasa);
			semasa.setAktif("1");
			semasa.setCatatan("-");
			semasa.setIdMasuk(Long.parseLong(idUser));
			return String.valueOf(getStatus().simpan(ss,semasa));
				
		} catch (Exception e) {
			throw new Exception("Ralat FrmLHDN, simpanStatus:"+getIHTP().getErrorHTML(e.getMessage()));		
		}
		
	}	
	
	private IIntegrasi getIntegrasi(){
		if(iIntegrasi==null){
			iIntegrasi = new FrmLHDNBean();
		}
		return iIntegrasi;
				
	}	

	private IIntegrasiStatus getStatus(){
		if(iStatus==null){
			iStatus = new FrmIntegrasiStatusBean();
		}
		return iStatus;
				
	}	
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	  
	}
	
}
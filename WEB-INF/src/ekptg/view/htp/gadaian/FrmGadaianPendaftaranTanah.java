package ekptg.view.htp.gadaian;

import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujagensi;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmGadaianInfoData;
import ekptg.model.htp.FrmGadaianPenHamilikData;
import ekptg.model.htp.FrmGadaianSenaraiPermohonanData;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikSementaraData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikAgensi;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.rekod.FrmHakmilikRizabPengesahanTanahBean;
import ekptg.model.htp.rekod.FrmRekodUtilData;
import ekptg.model.htp.rekod.FrmTanahDaftarRizabBean;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.rekod.IHakmilikRizabPengesahanTanah;
import ekptg.model.htp.rekod.ITanahDaftar;
import ekptg.model.htp.utiliti.IKod;
import ekptg.model.htp.utiliti.IUtilHTMLPilihan;
import ekptg.model.htp.utiliti.KodLotBean;
import ekptg.model.htp.utiliti.UtilHTMLPilihanJenisRizabBean;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;

public class FrmGadaianPendaftaranTanah extends AjaxBasedModule {

	private final String PATH="app/htp/gadaian/";
	private IHakmilikRizabPengesahanTanah iPengesahan = null;
	private IHtp iHTP = null;  
 	private IHTPFail iHTPFail = null;  
 	private IKod iKod = null;  
 	private IUtilHTMLPilihan iUtilPilihan = null;
 	private ekptg.model.htp.IHTPPermohonan iPermohonan = null;
	private IHTPStatus iStatus = null;
	private ITanahDaftar iTanahDaftar = null;
	private HakmilikInterface iHakmilik = null;
	private HakMilik hakmilik = null;
	private HakmilikAgensi hakmilikAgensi = null;
	private HtpPermohonan htpPermohonan = null;
	private IPembelian iPembelian = null;
	private String idSubUrusan = "0";
	private String idFail = "0";
	private String idPermohonan = "";
	private Long idJenisHakmilik = 0L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.gadaian.FrmGadaianPendaftaranTanah.class);
	FrmRekodUtilData frmRekodUtilData = null;
	UtilHTML utilHTML = new UtilHTML();

	String action = "";
	String disabled = "";
	String firstAction ="";
	String flagAdvSearch = "";
	String idKementerian = "";
	String idAgensi = "";
	String idKementerianKemasukan = "";
	String idAgensiKemasukan = "";
	String idSeksyen = "3";
	String mode = "";
	String readOnly = "";
	String style = "";
 	String userId = "";
	String vm = PATH+"pendaftaran/frmPendaftaranTanahIndex.jsp";
	private String idnegeri = "";
 	private String iddaerah = "";
 	private String idmukim = "";
 	//private String idJenisHakmilik = "";
 	private String tarikhSemasa = "";
 	HttpSession sessionExt = null;
	private Long setIdStatus = 89L; //Status FAIL SELESAI			
	private Long setIdSuburusanstatus = 0L;
	private Tblrujsuburusanstatusfail statusPermohonan = null;
	//private String idSubUrusan = "";
	String langkah = "99"; //Status FAIL SELESAI
	private boolean semakSubUrusan = true;
	
	public String doTemplate2() throws Exception {
		
		frmRekodUtilData = FrmRekodUtilData.getInstance();
		String firstAction = getParam("firstAction");
		String submit = getParam("command");
		action = getParam("action");		
		idAgensi = getParam("socAgensi");
		idAgensiKemasukan = getParam("txtIdAgensi");
	 	iddaerah = getParam("socDaerah");
		idKementerian = getParam("socKementerian");
		idKementerianKemasukan = getParam("txtIdKementerian");
	 	idmukim = getParam("socMukim");
		idnegeri = getParam("socNegeri");
		mode = getParam("mode");
		this.context.put("action","");
		this.context.put("firstAction",firstAction);
		this.context.put("INS_UPD",getParam("INS_UPD"));
		
	
		String idJenisHakmilikBaru = getParam("socJenisHakmilikBaru");
		if (idJenisHakmilikBaru == null || idJenisHakmilikBaru.trim().length() == 0){
			idJenisHakmilikBaru = "99999";
		}
		
		
 	
		Vector list =null;
		HttpSession session = this.request.getSession();
		if(session == null){
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] SILA LOGIN SEMULA"));
		}
		String noFail = getParam("txtNoFail").trim();
		String nama = getParam("txtNamaPemilik");
		//String noHakmilik = getParam("txtNoHakmilikC");
		//String noHakmilikAsal = getParam("txtNoHakmilik");
		//String noWarta = getParam("txtNoWarta");
		//String noLot = getParam("txtNoLotC");
		//Vector listSambungan = null;		
		userId = (String)session.getAttribute("_ekptg_user_id");		
		if(session == null){
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] SILA LOGIN SEMULA, ID PENGGUNA"));
		}
		this.context.put("modePopup", "");
		//Override submit - if user change tukar paparan mukasurat 
		if ("doChanges".equals(submit) || "record_listing".equals(submit)) {
			submit = "";
		}		
		this.context.put("page1",false);
		this.context.put("page2",false);
		this.context.put("page3",true);//Default page
		flagAdvSearch = getParam("flagAdvSearch");
		tarikhSemasa = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
		myLog.info("\nsubmit="+submit+" ,action="+action +" ,mode="+mode +" ,INS_UPD="+getParam("INS_UPD"));
		
		
		String readability = "";
	    String disability = "";
	    String style1 = "";
	    String style2 = "";
		
		if (submit.equals("paparDetailHakmilik2")){	//[1.0] VIEW HAKMILIK BY ID - FUNGSI INI DIGUNA 
			//myLog.info("\npaparDetailHakmilik2");
			
			vm = PATH+"pendaftaran/frmGadaianPendaftaranMaklumatHakmilik.jsp";
			getPermohonanInfo();
			this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("IdPermohonan", getParam("idPermohonan"));
		    
		    myLog.info("PenHakmilik view");
		    readability = "readonly";
		    disability = "disabled";
		    style1 = "none";
		    style2 = "none";
		    DataPenHamilik(session, disability, readability,style1,style2);
			sessionExt = session;

			doPaparDetailHakmilik2(list);
		
			
		}else if (submit.equals("PenHakmilik")) {				
	    	myLog.info("PenHakmilik:mode="+mode);
			vm = PATH+"pendaftaran/frmGadaianPendaftaranMaklumatHakmilik.jsp";

			getPermohonanInfo();
			String idHTPGadaian = String.valueOf(htpPermohonan.getIdHtpPermohonan());

			//int idPermohonanInt = Integer.parseInt(getParam("idPermohonan"));
			String idPermohonanInt = getParam("idPermohonan");
			//this.context.put("IdPermohonan", idPermohonan);
			//System.out.println("ID PERMOHONAN HIHIHI :"+idPermohonan);
		    this.context.put("NoFail", getParam("noFail"));
		    this.context.put("IdFail", getParam("idFail"));
		    this.context.put("IdHakmilikurusan", getParam("idHakmilikurusan"));
			list = FrmGadaianInfoData.getSemak(idPermohonanInt);
			this.context.put("Info", list);
			Hashtable h = (Hashtable) list.get(0);
			idFail = String.valueOf(h.get("idFail"));
			idSubUrusan = String.valueOf(h.get("idSuburusan"));
			idPermohonan = String.valueOf(h.get("idPermohonan"));
		
			
			
		    System.out.println("Id Permohonan keluar--- :"+idPermohonan);
		   
		    
		    if("baru".equals(mode)){
		    	
		    }else if("kemaskini".equals(mode)){
		    	myLog.info("kemaskini");
		    	readability = "readonly";
				disability = "disabled";
				style2 = "none";
				style1 = "none";			  
				DataPenHamilik(session, disability, readability,style1,style2);
		    
		    }else if("simpan".equals(mode)){
		    	myLog.info("selesaisimpan");
		    	myLog.info("selesaisimpan 1="+idFail);
		    	myLog.info("selesaisimpan 2="+idPermohonan);
		    	myLog.info("selesaisimpan 3="+idSubUrusan);
		    	myLog.info("getParam(\"txdTarikhTerimaSelesai\"):"+getParam("txdTarikhTerimaSelesai"));
		    	kemaskiniSimpanStatusSelesai(idFail,idPermohonan,idSubUrusan,"99");
		    	statusView(idPermohonan);
		    
		    }
		    
		    
		}
		else if (submit.equals("papardetailhakmilik2kembali")){	//[1.0] VIEW HAKMILIK BY ID - FUNGSI INI DIGUNA 
			//myLog.info("\npapardetailhakmilik2kembali");
			vm = PATH+"pendaftaran/frmPendaftaranTerimaHakmilik.jsp";
			sessionExt = session;
			mode = "";
			doPaparDetailHakmilik2(list);
		
		}
		else if (submit.equals("kemaskiniDetailHakmilik")){ //FUNGSI INI DIGUNA
			myLog.info("\nkemaskiniDetailHakmilik");
			vm = PATH+"pendaftaran/frmPendaftaranTerimaHakmilik.jsp";
			sessionExt = session;
			dokemaskiniDetailHakmilik();

		}else if (submit.equals("tambahRizab")){ //[2.X]TAMBAH RIZAB DARI HTPHAKMILIK
		    //vm = PATH+"frmPendaftaranTerimaRizabPermohonan.jsp";
		    vm = PATH+"pendaftaran/frmPendaftaranTerimaRizab.jsp";
		    myLog.info("submit:tambahRizab = "+submit);
		    String idPermohonan = getParam("idPermohonan");
		    myLog.info("submit:tambahRizab:: idPermohonan= "+getParam("idPermohonan"));
		    String display = "";
			this.context.put("readonly", "");
			this.context.put("disabled", display);	
			this.context.put("mode", "new");			
			// VIEW HEADER(MASTER) BY ID
			System.out.println("idPermohonan : "+idPermohonan);
			list = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idPermohonan);
			System.out.println("list : "+list);
			viewMaklumatRizabPermohonan(list,display,submit);
			this.context.put("SenaraiTanah", list);
			String idDaerah = getParam("socDaerah");
			String idMukim = getParam("socMukim");
			String idLuas= getParam("socLuas");
			//String idSocLuas= getParam("socLuas");
			String txdTarikhWarta = tarikhSemasa;
			String txdTarikhTerima = tarikhSemasa;
			
			if (mode.toLowerCase().indexOf("change") != -1) {
				iddaerah = getParam("socDaerah");
				this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian", Utils.parseLong((String)getParam("txtIdKementerian")), null,"onChange=\"doChangeKementerianRizabPer()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("txtIdAgensi")), "", " style=\"width:400px\""));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idnegeri)
						," onChange=\"doChangeStateRizabPer();\" class=\""+display+"\" "+display+" style=\"width:300px\""));	
				this.context.put("selectDaerah", UtilHTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabPer();\" style=\"width:300px\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim)," style=\"width:300px\""));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong(idLuas), " style=\"width:200px\""," pilihLuasRizabPer()"));
				this.context.put("txtFailPTD",getParam("txtFailPTD"));
				this.context.put("txtFailPTG",getParam("txtFailPTG"));
				txdTarikhWarta = getParam("txdTarikhWarta") ;
				txdTarikhTerima = getParam("txdTarikhTerima");
				this.context.put("socLuas", idLuas);
				if(idLuas.equals("1") || idLuas.equals("2") || idLuas.equals("3") || idLuas.equals("5") 
						|| idLuas.equals("6")){
					//this.context.put("txtLuas1", "");
					this.context.put("txtLuasLama", getParam("txtLuas1"));
					
				}else if(idLuas.equals("4") || idLuas.equals("8")){
					this.context.put("txtLuas2", getParam("txtLuas2"));
					this.context.put("txtLuas3", getParam("txtLuas3"));
					this.context.put("txtLuas4", getParam("txtLuas4"));
					
				}else if(idLuas.equals("7")){
					this.context.put("txtLuas5", getParam("txtLuas5"));
					this.context.put("txtLuas6", getParam("txtLuas6"));
					
				}
				this.context.put("txtLuas",getParam("txtLuas"));	
				this.context.put("txtNoWarta", getParam("txtNoWarta") );
				this.context.put("txtNoLot", getParam("txtNoLot") );
				//this.context.put("txtNoWarta", getParam("txtNoWarta") );

				
				//FIX BUG! syaz
				this.context.put("txtLokasi",getParam("txtLokasi"));	
				this.context.put("txtKegunaanTanah",getParam("txtKegunaanTanah"));	
				this.context.put("txtNoPelan",getParam("txtNoPelan"));
				this.context.put("txtNoSyit",getParam("txtNoSyit"));
				this.context.put("txtNoPu",getParam("txtNoPu"));
				this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini"));
				this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab",getParam("socjenisrizab"), " class=\""+display+"\" "+display+" style=\"width:200px\"" ));
				
			}
			this.context.put("txdTarikhWarta", txdTarikhWarta);
			this.context.put("txdTarikhTerima", txdTarikhTerima);
			//this.context.put("txtNoWarta", getParam("txtNoWarta") );
		
		//[10.6.2] Dari Senarai Fail, Pilih Fail Rizab
		//}else if (submit.equals("paparrizabterperinci")){	//[2.1]-TAMBAH RIZAB- DARI PERMOHONAN, SEKIRANYA TIADA RIZAB				
		}else if (submit.equals("baru") || submit.equals("paparrizabterperinci")){	//[2.1]-TAMBAH RIZAB- DARI PERMOHONAN, SEKIRANYA TIADA RIZAB				
			vm = PATH+"pendaftaran/frmPendaftaranTerimaRizab.jsp";
			myLog.info("baru/paparrizabterperinci="+submit+",mode="+mode);
			myLog.info("baru/paparrizabterperinci="+submit+",idPermohonan="+getParam("idPermohonan"));
			String idPermohonan = getParam("idPermohonan");	

			/** [2.2] JIKA MAKLUMAT RIZAB DALAM TABLE TBLHTPHAKMILIK SUDAH ADA/WUJUD */
			if(frmRekodUtilData.isHTPHakmilik(idPermohonan)){
				myLog.debug("isHTPHakmilik = TRUE");
				//Kemaskini by Rosli 20/08/2010
				vm = PATH+"pendaftaran/frmPendaftaranTerimaRizabSenarai.jsp";
				this.context.put("mode", "view");
				list = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idPermohonan);
				/* Senarai Tanah Rizab
				 * Kemaskini pada 13/09/2013, set value baru idPermohonan kerana komen// fungsi viewMaklumatRizab
				 */
				//viewMaklumatRizab(list,dis,submit);
				this.context.put("idPermohonan", idPermohonan);	
				this.context.put("SenaraiTanah", list);								
			
			/** [2.1.1]-TAMBAH RIZAB- JIKA MAKLUMAT RIZAB YANG BARU, BERSEDIA UNTUK DISIMPAN
			// [10.6.2.1] Skrin Kemasukan Warta Baru, Masih Tiada Maklumat Warta */
			}else{
				/**
				* VIEW MAKLUMAT FAIL/RIZAB(JIKA ADA)
				**/
				myLog.info("isHTPHakmilik = FALSE");
				//myLog.info("firstAction:"+firstAction);`
				//2012/12/31
				//getMaklumatRizabAwal(session,idPermohonan,submit);
				getMaklumatRizabMengikutPermohonan(idPermohonan,submit);
				String idDaerah = getParam("socDaerah");
				//add by rosli 20/08/2010 for default current date
				String txdTarikhWarta = tarikhSemasa;
				String txdTarikhTerima = tarikhSemasa;
				this.context.put("mode", "new");			
				//AZAM - Override - capture what ever do changes mode
//2012/12/31			
				if (mode.toLowerCase().indexOf("change") != -1) {
//					this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian"
//							, Utils.parseLong((String)getParam("txtIdKementerian")), null
//							,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
//					this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi"
//							, (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("txtIdAgensi"))
//							, "", " style=\"width:400px\""));
//					this.context.put("selectDaerah", UtilHTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
//					this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim)," style=\"width:300px\""));
//					this.context.put("txtFailPTD",getParam("txtFailPTD"));
//					this.context.put("txtFailPTG",getParam("txtFailPTG"));
					txdTarikhWarta = getParam("txdTarikhWarta") ;
					txdTarikhTerima = getParam("txdTarikhTerima");
		
				}
				this.context.put("txdTarikhWarta", txdTarikhWarta);
				this.context.put("txdTarikhTerima", txdTarikhTerima);
//				this.context.put("txtNoWarta", getParam("txtNoWarta") );
				
				this.context.put("flagPendaftaran","permohonan");

			}
			this.context.put("SenaraiHakmilik", list);	
			this.context.put("page2",true);
			this.context.put("page3",false);			
			this.context.put("readOnly",readOnly);
			this.context.put("disabled",disabled);	

			
			this.context.put("readonly","");
			
		}else if (submit.equals("barukembali")){	//[2.1]-TAMBAH RIZAB- DARI PERMOHONAN, SEKIRANYA TIADA RIZAB				
			//myLog.info("baru/paparrizabterperinci="+submit+",mode="+mode);
			//myLog.info("barukembali/paparrizabterperinci="+submit+",idPermohonan="+getParam("idPermohonan"));
			String idPermohonan = getParam("idPermohonan");	
			String dis =  "";
			this.context.put("readOnly", "");
			this.context.put("disabled", dis);	
			this.context.put("page2",true);
			this.context.put("page3",false);			
			
			vm = PATH+"pendaftaran/frmPendaftaranTerimaRizabSenarai.jsp";
			//String dis =  "disabled";
			this.context.put("page2",true);
			this.context.put("page3",false);
			//this.context.put("readOnly", "readonly");
			//this.context.put("disabled", dis);	
			this.context.put("mode", "view");
			list = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idPermohonan);
			//Senarai Tanah Rizab
			viewMaklumatRizab(list,dis,submit);
			this.context.put("SenaraiTanah", list);								
			this.context.put("flagPendaftaran","permohonan");

			this.context.put("SenaraiHakmilik", list);	

		}else if (submit.equals("pengesahanrizab")){	//[2.X]-PENGESAHAN RIZAB- DARI "PRE-DAFTAR"				
			vm = PATH+"tanahrizab/frmPendaftaranTerimaRizab.jsp";
			String idHakmilik = getParam("idHakmilik");
			myLog.info("pengesahanrizab=pengesahanrizab,mode="+mode+"idHakmilik="+idHakmilik);
			this.context.put("readOnly", "");
			this.context.put("disabled", "");	
			this.context.put("page2",true);
			this.context.put("page3",false);			
			// VIEW MAKLUMAT FAIL/RIZAB(JIKA ADA)
			//myLog.info("firstAction:"+firstAction);`
				this.context.put("mode", "new");
				getMaklumatRizabAwalPengesahan(session,idHakmilik,submit);
				String idDaerah = getParam("socDaerah");
				String idMukim = getParam("socMukim");
				//add by rosli 20/08/2010 for default current date
				String txdTarikhWarta = tarikhSemasa;
				String txdTarikhTerima = tarikhSemasa;
				//AZAM - Override - capture what ever do changes mode
				if (mode.toLowerCase().indexOf("change") != -1) {
					//this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian"
					this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian"
							, Utils.parseLong((String)getParam("txtIdKementerian")), null
							,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
					this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi"
							, (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("txtIdAgensi"))
							, "", " style=\"width:400px\""));
					this.context.put("selectDaerah", UtilHTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
					this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim)," style=\"width:300px\""));
					this.context.put("txtFailPTD",getParam("txtFailPTD"));
					this.context.put("txtFailPTG",getParam("txtFailPTG"));
					txdTarikhWarta = getParam("txdTarikhWarta") ;
					txdTarikhTerima = getParam("txdTarikhTerima");
				}
				this.context.put("txdTarikhWarta", txdTarikhWarta);
				this.context.put("txdTarikhTerima", txdTarikhTerima);
				this.context.put("txtNoWarta", getParam("txtNoWarta") );
				this.context.put("flagPendaftaran","permohonan");

			//}
			this.context.put("SenaraiHakmilik", list);	

		// Kemaskini pada 28/05/2013
		}else if (submit.equals("tambahRizabSimpan")){
			 vm = PATH+"pendaftaran/frmPendaftaranTerimaRizab.jsp";
			 myLog.info("submit=tambahRizabSimpan,mode="+mode);
			 Hashtable rizab = new Hashtable();
			 String idPermohonan = getParam("idPermohonan");
			 String idHakmilik = getParam("idHakmilik");
			 //myLog.info("tambahRizabSimpan : idPermohonan="+idPermohonan+",idHakmilik="+idHakmilik);
			 idKementerianKemasukan = getParam("txtIdKementerian");
			 idAgensiKemasukan = getParam("txtIdAgensi");
			 rizab.put("idKementerian", idKementerianKemasukan);	//TBLHTPHAKMILIKAGENSI
			 rizab.put("idAgensi", idAgensiKemasukan);	//TBLHTPHAKMILIKAGENSI
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
			 //rizab.put("txtLuasBersamaan",getParam("txtLuas"));
			 rizab.put("txtLuasBersamaan", Utils.RemoveComma(getParam("txtLuas")));
			 rizab.put("txtNoPelan", getParam("txtNoPelan"));
			 rizab.put("txtNoSyit", getParam("txtNoSyit"));
			 rizab.put("txtNoPu", getParam("txtNoPu"));

			 rizab.put("idHakmilik", idHakmilik);
			 rizab.put("idPermohonan", idPermohonan);
			 rizab.put("socStatus", "D");
			 rizab.put("idMasuk", userId);
			 rizab.put("idKemaskini", userId);
			 rizab.put("txtCatatan", getParam("txtKemAgenTerkini"));
			 rizab.put("socjenisrizab", getParam("socjenisrizab"));
			 // START Input(id_seksyen, maklumat tanah) bagi Audit Trail
			 idSeksyen = FrmUtilData.getSeksyenMengikutPengguna(userId);
			 String noTanah = getParam("txtNoWarta")+ ", "+getKodLot().getKod(getParam("socLot"))+getParam("txtNoLot"); 
			 // END Input bagi Audit Trail
			  
//			 if(frmRekodUtilData.isHTPHakmilik(idPermohonan)){
//				 frmRekodUtilData.kemaskiniRizabMengikutId(rizab);
//				 hakmilikAgensi = new HakmilikAgensi();
//				 hakmilikAgensi.setIdHakmilik(Long.parseLong(idHakmilik));
//				 hakmilikAgensi.setIdHakmilikAgensi(Long.parseLong(idAgensiKemasukan));
//				 hakmilikAgensi.setIdKementerian(Long.parseLong(idKementerianKemasukan));
//				 hakmilikAgensi.setIdKemaskini(Long.parseLong(userId));
//
//				 hakmilikAgensi = getIHakmilik().kemaskiniHakmilikAgensi(hakmilikAgensi);
//				 AuditTrail.logActivity("1", idSeksyen, this, session, "UPD", "HAKMILIK/RIZAB  ["+noTanah+"] DIKEMASKINI ");
//				 
//			 }else{
			 	//AZAM CHANGE TO TRANSACTION
			 	// Kemaskini pada 13/09/2013, rename kepada XinsertHTPRizabTransaction
			 	//idHakmilik = FrmUtilData.insertHTPRizabTransaction(rizab,userId);
			 	idHakmilik = getIDaftar().simpanTransaction(rizab);
			 	AuditTrail.logActivity("1", idSeksyen, this, session, "INS", "HAKMILIK/RIZAB  ["+noTanah+"] DITAMBAH ");
			 //}
			 //VIEW SEMULA RIZAB YANG DISIMPAN
			 this.context.put("readOnly", "readonly"); 
			 String dis =  "disabled";
			 this.context.put("disabled", dis);	
			 //this.context.put("mode", "view");
			 this.context.put("mode", "viewtambah");
			 //idHakmilik = "1613101831"; //no Warta 166(3), Lot 20130107
			 hakmilik = getIHakmilik().getHakmilik(idHakmilik);
			 viewMaklumatRizab(hakmilik,dis,submit);			
		
			 // Sub Urusan LAIN-LAIN RIZAB (1)
			 simpanKemaskiniFail("1",langkah);

		}else if ("prakemaskinimaklumatrizab".equals(submit)){ //KEMASKINI DETAIL MAKLUMAT RIZAB
			myLog.debug("** prakemaskinimaklumatrizab **");
		    vm = PATH+"frmPendaftaranTerimaRizab.jsp";
			String idHakmilik = getParam("idHakmilik");	
	
			hakmilik = getIHakmilik().getHakmilik(idHakmilik);
			//viewMaklumatRizab(hakmilik,dis,submit);
		    String dis = "";
			this.context.put("readonly", "");
			this.context.put("disabled", dis);	
			this.context.put("mode", "update");
			
			viewMaklumatRizab(hakmilik,dis,submit);

		}else if (submit.equals("kemaskiniMaklumatRizab")){ //RIZAB[2.2.1]KEMASKINI DETAIL MAKLUMAT RIZAB
		    //vm = PATH+"frmPendaftaranTerimaRizab.jsp";
		    vm = PATH+"pendaftaran/frmPendaftaranTerimaRizab.jsp";
			myLog.debug("submit=kemaskiniMaklumatRizab");
			String idHakmilik = getParam("idHakmilik");		
			hakmilik = getIHakmilik().getHakmilik(idHakmilik);
			//viewMaklumatRizab(hakmilik,dis,submit);
		    String dis = "";
			this.context.put("readonly", "");
			this.context.put("disabled", dis);	
			this.context.put("mode", "update");			
			viewMaklumatRizab(hakmilik,dis,submit);
			//this.context.put("SenaraiTanah", list);

		//[Pautan/Link Kemaskini Warta]	10.6.2.2.2, -KEMASKINI RIZAB- (VIEW ONLY)FUNGSI INI DIGUNA
		}else if (submit.equals("viewonlyrizab") || submit.equals("viewdetailrizab")) { 
			vm = PATH+"pendaftaran/frmPendaftaranTerimaRizab.jsp";
			myLog.info("viewonlyrizab/viewdetailrizab="+submit+",mode="+mode);
			String idHakmilik = getParam("idHakmilik");	
			String dis =  "disabled";
			this.context.put("readOnly", "readonly");
			this.context.put("disabled", dis);	
			this.context.put("mode", "view");	
			hakmilik = getIHakmilik().getHakmilik(idHakmilik);
			viewMaklumatRizab(hakmilik,dis);
		  
		/**
		 * UPDATE DETAIL RIZAB
		 * [Simpan] 10.6.2.2.2.3.1.5
		 * Apabila klik butang Simpan pautan ke 
		 									" [Pautan/Link Kemaskini Warta]	10.6.2.2.2"
		 */			
		}else if (submit.equals("updateDetailRizab"))	{
			myLog.info("submit = updateDetailRizab");
			vm = PATH+"pendaftaran/frmPendaftaranTerimaRizab.jsp";
			String idHakmilik = getParam("idHakmilik");	
			Hashtable hRizabUpdate = new Hashtable();
			hRizabUpdate.put("idHakmilik", idHakmilik);
			hRizabUpdate.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			hRizabUpdate.put("txdTarikhWarta", getParam("txdTarikhWarta"));
			hRizabUpdate.put("socLuas", getParam("socLuas"));
			 //LUAS LAMA
			if(getParam("socLuas").equals("1")){
				hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"KM"));
			}else if(getParam("socLuas").equals("2")){
				 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"H"));
			 }else if(getParam("socLuas").equals("3")){
				 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"M"));
			 }else if(getParam("socLuas").equals("4")){
				 hRizabUpdate.put("txtLuasLama",(getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
			 }else if(getParam("socLuas").equals("5")){
				 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas1")+"K"));
			 }else if(getParam("socLuas").equals("7")){
				 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas5")+"E"+getParam("txtLuas6")+"D"));
			 }else if(getParam("socLuas").equals("8")){
				 hRizabUpdate.put("txtLuasLama", (getParam("txtLuas2")+"R"+getParam("txtLuas3")+"J"+getParam("txtLuas4")+"K"));
			 }
			 System.out.println((getParam("txtLuas2")+"E"+getParam("txtLuas3")+"R"+getParam("txtLuas4")+"P"));
			 //LUAS BARU
			 hRizabUpdate.put("txtLuas", getParam("txtLuas"));
			 hRizabUpdate.put("txtNoPelan", getParam("txtNoPelan"));
			 hRizabUpdate.put("txtNoPu", getParam("txtNoPu"));
			 hRizabUpdate.put("txtLokasi", getParam("txtLokasi"));	
			 hRizabUpdate.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));
			 hRizabUpdate.put("txtNoFailJopa", getParam("txtNoFailJopa"));	
			 hRizabUpdate.put("socStatus", getParam("socStatus"));
			 //hRizabUpdate.put("socStatus", "D");
			 hRizabUpdate.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));
			 hRizabUpdate.put("txtNoWarta", getParam("txtNoWarta"));
			 hRizabUpdate.put("txtNoLot", getParam("txtNoLot"));
			 hRizabUpdate.put("txtNoLotR", getParam("txtNoLot"));		 
			 hRizabUpdate.put("catatan", getParam("txtKemAgenTerkini"));
			 String noTanah = getParam("txtNoWarta")+ ", "+getKodLot().getKod(getParam("socLot"))+getParam("txtNoLot"); 
			 hRizabUpdate.put("socjenisrizab", getParam("socjenisrizab"));			 			 
			 // Kemaskini pada 13/09/2013, rename kepada XupdateRizabById
			 //FrmRekodPendaftaranHakmilikRizabData.updateRizabById(hRizabUpdate);
			 getIDaftar().kemaskini(hRizabUpdate);
			 
			 idKementerianKemasukan = getParam("txtIdKementerian");
			 idAgensiKemasukan = getParam("txtIdAgensi");
			 hakmilikAgensi = new HakmilikAgensi();
			 hakmilikAgensi.setIdHakmilik(Long.parseLong(idHakmilik));
			 hakmilikAgensi.setIdHakmilikAgensi(Long.parseLong(idAgensiKemasukan));
			 hakmilikAgensi.setIdKementerian(Long.parseLong(idKementerianKemasukan));
			 hakmilikAgensi.setIdKemaskini(Long.parseLong(userId));
			 hakmilikAgensi = getIHakmilik().kemaskiniHakmilikAgensi(hakmilikAgensi);
			 
			 AuditTrail.logActivity("1", idSeksyen, this, session, "UPD", "HAKMILIK/RIZAB  ["+noTanah+"] DIKEMASKINI ");

			 //PAPAR SEMULA RIZAB YANG DIUPDATE
			 String dis =  "disabled";
			 this.context.put("readonly", "readonly");
			 this.context.put("disabled", dis);	
			 this.context.put("mode", "view");
			 //VIEW MAKLUMAT RIZAB BY ID
			 //view_modeHakmilikRizab(session,nextAction,lastAction,idNegeriHR,idDaerahHR,idMukimHR,idJenisHakmilikHR,idCaraBayar,idJenisHakmilikBaru);
			 //}	
			hakmilik = getIHakmilik().getHakmilik(idHakmilik);
			viewMaklumatRizab(hakmilik,dis);
			
			 // Sub Urusan LAIN-LAIN RIZAB (1)
			 simpanKemaskiniFail("1",langkah);
				
		}else if (submit.equals("hapustanah")){	//[10.6.2.1.6.1.1]-				
			//vm = PATH+"pendaftaran/frmPendaftaranTerimaRizab.jsp";
			myLog.info("submit="+submit+",mode="+mode);
			myLog.info("submit="+submit+",idPermohonan="+getParam("idPermohonan"));
			String idPermohonan = getParam("idPermohonan");	
			this.context.put("readOnly", "");
			this.context.put("disabled", "");	
			this.context.put("page2",true);
			this.context.put("page3",false);
			list = new Vector();
			getIHakmilik().hapusHakmilikById(getParam("idHakmilik"));
			idSeksyen = FrmUtilData.getSeksyenMengikutPengguna((String) session
					.getAttribute("_ekptg_user_id"));
			String noTanah = getParam("txtmaklumatnotanah");
			String idStatusTanah = getStatus().getIdStatusSah(getParam("socStatusDaftar") == null?"0":getParam("socStatusDaftar"));
			myLog.info("idStatusTanah="+idStatusTanah);	
			AuditTrail.logActivity(idStatusTanah, idSeksyen, this, session, "DEL", "HAKMILIK/RIZAB  ["+noTanah+"] DIHAPUSKAN ");

			if(frmRekodUtilData.isHTPHakmilik(idPermohonan)){
				list = frmRekodUtilData.getHakmilikRizabMengikutPermohonan(idPermohonan);
				Hashtable<?, ?> maklumatTanah =(Hashtable<?, ?>)list.get(0);
				if(!maklumatTanah.get("noHakmilik").equals(""))
					vm = PATH+"pendaftaran/frmPendaftaranTerimaHakmilikSenarai.jsp";
				else if(!maklumatTanah.get("noWarta").equals(""))	
					vm = PATH+"pendaftaran/frmPendaftaranTerimaRizabSenarai.jsp";
				
				String dis =  "disabled";
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", dis);	
				this.context.put("mode", "view");
				//Senarai Tanah Rizab
				//viewMaklumatRizab(list,dis,submit);
				this.context.put("SenaraiTanah", list);								

			}
			this.context.put("SenaraiHakmilik", list);	

		}
		else if (submit.equals("SenaraiPemilik")){	//[10.0]REKOD
			//vm = PATH+"frmPendaftaranHakmilikRizabSenarai.jsp";
			myLog.info("First:mode="+mode);
			if ("".equals(idKementerian)) idKementerian="-1";
			if ("".equals(idAgensi)) idAgensi="-1";
	    	if ("".equals(idnegeri)||"0".equals(idnegeri)) idnegeri = "-1";
	    	if ("".equals(iddaerah)) iddaerah = "-1";
	    	if ("".equals(idmukim)) idmukim = "-1";
	    	if(firstAction.equals("reset")){
	    		idnegeri = "-1";
		    	iddaerah = "-1";
		    	idmukim = "-1";
		    	idKementerian ="-1";
				idAgensi="-1";
		
	    	}	    	
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"",""));
			this.context.put("socKementerian",HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), null," style=\"width:400\" onChange=\"doChangeKementerian()\" "));
			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "  style=\"width:400\"", ""));
		    	
	    	
		}
		else{	//[10.0]REKOD
			//vm = PATH+"frmPendaftaranHakmilikRizabSenarai.jsp";
			myLog.info("First:mode="+mode);
			if ("".equals(idKementerian)) idKementerian="-1";
			if ("".equals(idAgensi)) idAgensi="-1";
	    	if ("".equals(idnegeri)||"0".equals(idnegeri)) idnegeri = "-1";
	    	if ("".equals(iddaerah)) iddaerah = "-1";
	    	if ("".equals(idmukim)) idmukim = "-1";
	    	if(firstAction.equals("reset")){
	    		idnegeri = "-1";
		    	iddaerah = "-1";
		    	idmukim = "-1";
		    	idKementerian ="-1";
				idAgensi="-1";
		
	    	}	    	
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), " onChange=\"doChangeState();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"",""));
			this.context.put("socKementerian",HTML.SelectKementerian("socKementerian", Utils.parseLong(idKementerian), null," style=\"width:400\" onChange=\"doChangeKementerian()\" "));
			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Utils.parseLong(idAgensi), "  style=\"width:400\"", ""));
		    	
	    	if (mode.equals("carian")) {
	    	
//		    	list = frmRekodUtilData.senaraiFailMengikutCarian(null,noFail,txtTajukFail,
//		    			idKementerian,idAgensi,
//		    			idnegeri,iddaerah,idmukim,"-1");
//				list = getIHTPFail().getSenaraiFailMengikutUrusans(null,noFail,txtTajukFail
//						,idKementerian,idAgensi
//						,idnegeri,iddaerah,idmukim
//						,"1,10,2,5,4", "", ""
//						,"", true);
//		    	flagAdvSearch = "Y";
	    		
	    		
	    		FrmGadaianSenaraiPermohonanData data = new FrmGadaianSenaraiPermohonanData();
		    	list = data.getSenaraiPermohonan(idFail,noFail,nama);
		    	flagAdvSearch = "Y";
		    	
		    	this.context.put("permohonans", list); 
		    } else  {
		    	//list = frmRekodUtilData.getHakmilikUrusanMengikutUrusan("1,10,2,5");	
//				list = get().getSenaraiPermohonan(null, "", ""
//						, "", ""
//						, "", "", ""
//						, "1,10,2,5,4", "", ""
//						,"", false);
		    	FrmGadaianSenaraiPermohonanData data = new FrmGadaianSenaraiPermohonanData();
		    	list = data.getSenaraiPermohonan(idFail,noFail,nama);

		    	if(flagAdvSearch.equals("Y")){
//		    		list = frmRekodUtilData.senaraiFailMengikutCarian(null,noFail,txtTajukFail,
//			    			idKementerian,idAgensi,
//			    			idnegeri,iddaerah,idmukim,"-1");
		    		list = data.getSenaraiPermohonan(idFail,noFail,nama);
			    	//log.info("kosong 396");
		    	}
		    }
			this.context.put("txtNoHakmilik", "");			
			this.context.put("SenaraiHakmilik", list); 
			this.context.put("permohonans", list); 
		    this.context.put("txtNoFail",noFail);
		    this.context.put("txtNamaPemilik",nama);

		}
		
	    this.context.put("flagAdvSearch",flagAdvSearch);	
		if (list != null) {
			setupPage(session,action,list);
		}
		this.context.put("semakSubUrusan",semakSubUrusan);
		return vm;
		
	}
	
	//penambahan pendaftaran hakmmilik	
	private void getPermohonanInfo()throws Exception{
	String idPermohonan = getParam("idPermohonan");
	String idHtpPermohonan = getParam("txtidHtpPermohonan");
	htpPermohonan = getIPembelian().findPermohonan(idPermohonan,"");
	context.put("htpPermohonan", htpPermohonan);
	context.put("idPermohonan", idPermohonan);
	System.out.println("ID PERMOHONAN SINI-- :"+idPermohonan);
}
	
	private void view_modeHakmilik1(HttpSession session) throws Exception {
		FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(String.valueOf(getParam("idHakmilik")));
	}
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeHakmilik(HttpSession session) throws Exception {
		Hashtable hMaklumatFail = (Hashtable)FrmUtilData.getHakmilikUrusan("0",getParam("idHakmilik"));
		
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
		//if("paparDetailRizab2".equals(comm)){
		if(comm.equals("paparrizabterperinci")){
			
			//this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString(), "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString()),""," onChange=\"doChangeDaerahRizabV()\""));
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString(), "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString()),"",""));
		}else{
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString(), "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString()),""," onChange=\"doChangeDaerahHakmilik();\""));
		}
		String idnegeri = getParam("socNegeri");
	 	String iddaerah = getParam("socDaerah");
	 	if (mode.equals("doChangeState")) {
	 	 
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahHakmilik();\""));
		
	 	}
		if (mode.equals("doChangeDaerah")) {
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahHakmilik();\""));
		
		}else if (mode.equals("doChangeDaerahRizab")) {
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),"",""));
		
		}
		//this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), " onChange=\"doChangeStateHakmilik();\""));
		//this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahHakmilik();\""));
		this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));
		
	
	} 
	
	// VIEW MAKLUMAT FAIL BY ID (MASTER) -(AKTIF)
	private void getMaklumatRizabAwal(HttpSession session,String idPermohonan,String submit) throws Exception {
		try{
			myLog.info("getMaklumatRizabAwal(idPermohonan="+idPermohonan+",submit="+submit+")");		
			Hashtable<?, ?> maklumatFail = (Hashtable<?, ?>)frmRekodUtilData.getPermohonanInfoV1(idPermohonan);	
		 	if (mode.equals("changeTarafRizab")) {
		 		myLog.info("getMaklumatRizabAwal:changeTarafRizab");
				this.context.put("txtFailPTD",getParam("txtFailPTD"));
				this.context.put("txtFailPTG",getParam("txtFailPTG"));
				this.context.put("txtTajuk",(String)maklumatFail.get("tajukFail"));
				this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)getParam("txtIdKementerian")), null,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("idagensi")), "", " style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));
				String idnegeri = getParam("socNegeri");
			 	String iddaerah = getParam("socDaerah");
			 	if (mode.equals("doChangeStateRizab")) {
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				
			 	}
				if (mode.equals("doChangeDaerahRizab")) {
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				
				}
				this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));
				this.context.put("txtKemAgenTerkini", getParam("txtKemAgenTerkini"));
			 		
		 	}else{
	//	 		myLog.info("getMaklumatRizabAwal:else changeTarafRizab");
		 		this.context.put("txtFailPTD",(String)maklumatFail.get("noFailPTD"));
				this.context.put("txtFailPTG",(String)maklumatFail.get("noFailPTG"));
				this.context.put("txtTajuk",(String)maklumatFail.get("tajukFail"));
				//this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)maklumatFail.get("idkementerian")), null,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
				this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian"
						, Utils.parseLong((String)maklumatFail.get("idkementerian")), null
						,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi"
						, (String)maklumatFail.get("idkementerian"), Utils.parseLong((String)maklumatFail.get("idagensi"))
						, "", " style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"
						,Long.parseLong(maklumatFail.get("idnegeri").toString())
						," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString()
						, "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString())
						,""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				idnegeri = String.valueOf(maklumatFail.get("idnegeri"));
	//			myLog.info("getMaklumatRizabAwal:else idnegeri="+idnegeri+"iddaerah="+iddaerah);
				if (mode.toLowerCase().indexOf("change") != -1) {
					idnegeri = getParam("socNegeri");
				 	iddaerah = getParam("socDaerah");
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
	
				}
				if (mode.equals("doChangeDaerahRizab")) {
					//this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\""));	
					//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				}
				this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));
	
		 	}
		 	getMaklumatRizabAwal(session,"R",idPermohonan,submit);
			this.context.put("changeLuas",submit);
		
		}catch(Exception e){
			 //e.printStackTrace();
			//throw new Exception("MASALAH RANGKAIAN[2] , SILA SEMAK RANGKAIAN DB METHOD doGetListBox(ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod)");
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] MASALAH RANGKAIAN[2] , SILA SEMAK RANGKAIAN DB METHOD doGetListBox(ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod)"));
		}

	}
	
	// VIEW MAKLUMAT RIZAB BY ID_PERMOHONAN - (AKTIF)
	private void getMaklumatRizabAwal(HttpSession session,String r,String idPermohonan,String submit) throws Exception {
		myLog.info("getMaklumatRizabAwal:0");
		Hashtable maklumatRizab = (Hashtable)frmRekodUtilData.getRizabUrusan(idPermohonan,null);
		if(!maklumatRizab.get("IdDaerahPermohonan").equals("0")){
			iddaerah = (getParam("socDaerah")==null||getParam("socDaerah")==""?String.valueOf(maklumatRizab.get("IdDaerah")):getParam("socDaerah"));
			this.context.put("selectDaerah", UtilHTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong((String)maklumatRizab.get("IdDaerah")),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
		
		}
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong((String)maklumatRizab.get("IdMukim"))," style=\"width:300px\""));
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
		this.context.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));	
		this.context.put("txtIdLuasLama",(String)maklumatRizab.get("idLuasLama"));	
		this.context.put("txtLuasLama", (String)maklumatRizab.get("luasLama"));
		//myLog.info("socLuad:"+getParam("socLuas"));
		this.context.put("socLuas",getParam("socLuas"));	
		//this.context.put("txtLuas", (String)maklumatRizab.get("luas"));	
		this.context.put("txtNoPelan",getParam("txtNoPelan"));	
		this.context.put("txtNoSyit", getParam("txtNoSyit"));
		this.context.put("txtNoPu", getParam("txtNoPu"));	

		this.context.put("idHakmilik",idPermohonan);	
		this.context.put("idPermohonan",idPermohonan);	
		myLog.info("getMaklumatRizabAwal("+session+","+r+","+idPermohonan+"): END");
		
	}
	/**
	 *  VIEW MAKLUMAT FAIL MENGIKUT PERMOHONAN -(AKTIF)
	 * @param idPermohonan
	 * @param submit
	 * @throws Exception
	 */
	private void getMaklumatRizabMengikutPermohonan(String idPermohonan,String submit) throws Exception {
		try{
			myLog.info("getMaklumatRizabMengikutPermohonan(idPermohonan="+idPermohonan+",submit="+submit+") mode="+mode);		
			HtpPermohonan  maklumatFail = getIPermohonan().getPermohonanInfo("",idPermohonan);
			//myLog.info(getSemakSubUrusan(String.valueOf(maklumatFailB.getPermohonan().getPfdFail().getIdSubUrusan())));
			semakSubUrusan = getSemakSubUrusan(String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdSubUrusan()));

			//Hashtable<?, ?> maklumatFail = (Hashtable<?, ?>)frmRekodUtilData.getPermohonanInfoV1(idPermohonan);	
		 	//if (mode.equals("changeTarafRizab")) {
			if (mode.toLowerCase().indexOf("change") != -1) {
		 		myLog.info("getMaklumatRizabMengikutPermohonan:changeTarafRizab");
				this.context.put("txtFailPTD",getParam("txtFailPTD"));
				this.context.put("txtFailPTG",getParam("txtFailPTG"));
				this.context.put("txtTajuk",maklumatFail.getPermohonan().getTujuan());
				this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)getParam("txtIdKementerian")), null,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("txtIdAgensi")), "", " style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen",maklumatFail.getPermohonan().getPfdFail().getNoFail());
				String idnegeri = getParam("socNegeri");
			 	String iddaerah = getParam("socDaerah");
//			 	if (mode.equals("doChangeStateRizab")) {
//					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
//					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
//				
//			 	}
//				if (mode.equals("doChangeDaerahRizab")) {
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				
//				}
				this.context.put("txtFailKJP", maklumatFail.getNoRujukanKJP());
				this.context.put("txtKemAgenTerkini", getParam("txtKemAgenTerkini"));
			 		
		 	}else{
	//	 		myLog.info("getMaklumatRizabAwal:else changeTarafRizab");
				this.context.put("txtFailPTG",maklumatFail.getNoRujukanPTG());
				this.context.put("txtFailPTD",maklumatFail.getNoRujukanPTD());
				this.context.put("txtTajuk",maklumatFail.getPermohonan().getTujuan());

				this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian"
						, maklumatFail.getPermohonan().getPfdFail().getIdKementerian(), null
						,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi"
						, String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdKementerian())
						, maklumatFail.getIdAgensi()
						, "", " style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen", maklumatFail.getPermohonan().getPfdFail().getNoFail());
				this.context.put("txtFailKJP",maklumatFail.getNoRujukanKJP());
				//myLog.info("getMaklumatRizabMengikutPermohonan: idNegeriFail="+maklumatFail.get("idnegeri"));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"
						,maklumatFail.getPermohonan().getPfdFail().getIdNegeri()
						," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
				//myLog.info("getMaklumatRizabMengikutPermohonan: idDdaerahFail="+maklumatFail.get("idDaerah"));
				iddaerah = maklumatFail.getIdDaerah();
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdNegeri())
						, "socDaerah",Utils.parseLong(maklumatFail.getIdDaerah())
						,""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				//idnegeri = String.valueOf(maklumatFail.get("idnegeri"));
				//iddaerah = String.valueOf(maklumatFail.get("idDaerah"));
				myLog.info("getMaklumatRizabAwal:else idNegeriFail="+idnegeri+"idDdaerahFail="+iddaerah);
	
		 	}
		 	// Dibuat pada 31/12/2012, get maklumat awal tanah
			getMaklumatTanahAwal(idPermohonan);
			this.context.put("changeLuas",submit);
		
		}catch(Exception e){
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] MASALAH DATA[RIZAB 1] , HUBUNGI PENTADBIR SISTEM, " +
					"SILA SEMAK DATA BAGI PERMOHONAN "+idPermohonan+ " doGetListBox(ekptg.view.htp.FrmPendaftaranTanah)"));
		}

	}
	
	/**
	 *  VIEW MAKLUMAT TANAH MENGIKUT ID_PERMOHONAN (RUJUK TBLHTPHAKMILIKURUSAN)- (AKTIF)
	 * @param session
	 * @param idPermohonan
	 * @throws Exception
	 */
	private void getMaklumatTanahAwal(String idPermohonan) throws Exception {	
		Hashtable maklumatRizab = (Hashtable)frmRekodUtilData.getRizabUrusan(idPermohonan,null);
//		myLog.info("getMaklumatTanahAwal:0,maklumatRizab=\n"+maklumatRizab);
//		if(!maklumatRizab.get("IdDaerahPermohonan").equals("0")){
//			iddaerah = (getParam("socDaerah")==null||getParam("socDaerah")==""?String.valueOf(maklumatRizab.get("IdDaerah")):getParam("socDaerah"));
//			this.context.put("selectDaerah", UtilHTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong((String)maklumatRizab.get("IdDaerah")),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
//		
//		}
		if (mode.toLowerCase().indexOf("change") != -1) {

			String idMukim = getParam("socMukim");
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idMukim)," style=\"width:300px\""));
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
			this.context.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));	
			this.context.put("txtIdLuasLama",(String)maklumatRizab.get("idLuasLama"));	
			this.context.put("txtLuasLama", (String)maklumatRizab.get("luasLama"));
			//myLog.info("socLuad:"+getParam("socLuas"));
			this.context.put("socLuas",getParam("socLuas"));	
			this.context.put("txtNoPelan",getParam("txtNoPelan"));	
			this.context.put("txtNoSyit", getParam("txtNoSyit"));
			this.context.put("txtNoPu", getParam("txtNoPu"));	
			this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab"
					,String.valueOf( getParam("socjenisrizab")), " style=\"width:200px\""," "));
	
		}else{
			//myLog.info("getMaklumatTanahAwal : idnegeri="+idnegeri+",iddaerah = "+iddaerah+",idmukim = "+maklumatRizab.get("idMukim")+maklumatRizab.get("bil"));
			idmukim = String.valueOf(maklumatRizab.get("idMukim"));
			if(!iddaerah.equals("0") && !maklumatRizab.get("bil").equals("")){
				//myLog.info("getMaklumatTanahAwal : Ada hakmilik ");
				iddaerah = String.valueOf(maklumatRizab.get("IdDaerah"));
				this.context.put("selectDaerah", UtilHTML.SelectDaerah("socDaerah",Utils.parseLong(String.valueOf(maklumatRizab.get("IdDaerah")))," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));

			}
			//myLog.info("getMaklumatTanahAwal : Selepas if");
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim)," style=\"width:300px\""));
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
			this.context.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));	
			this.context.put("txtIdLuasLama",(String)maklumatRizab.get("idLuasLama"));	
			this.context.put("txtLuasLama", (String)maklumatRizab.get("luasLama"));
			this.context.put("socLuas",getParam("socLuas"));	
			this.context.put("txtNoPelan",getParam("txtNoPelan"));	
			this.context.put("txtNoSyit", getParam("txtNoSyit"));
			this.context.put("txtNoPu", getParam("txtNoPu"));	
			this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab"
					,"0", " style=\"width:200px\""," "));

		}
		this.context.put("idHakmilik",idPermohonan);	
		this.context.put("idPermohonan",idPermohonan);	
		myLog.info("getMaklumatTanahAwal(,,"+idPermohonan+"): END");
		
	}
	
	// VIEW MAKLUMAT FAIL BY ID (MASTER) -(AKTIF)
	private void getMaklumatRizabAwalPengesahan(HttpSession session,String idHakmilik,String submit) throws Exception {
		try{
			myLog.info("getMaklumatRizabAwalPengesahan(idPermohonan="+idHakmilik+",submit="+submit+")");		
			//Hashtable<?, ?> maklumatFail = (Hashtable<?, ?>)frmRekodUtilData.getPermohonanInfoV1(idPermohonan);
			Hashtable<?, ?> maklumatFail = (Hashtable<?, ?>)getIPengesahan().getPerolehanInfo(idHakmilik);
		 	if (mode.equals("changeTarafRizab")) {
		 		myLog.info("getMaklumatRizabAwal:changeTarafRizab");
				this.context.put("txtFailPTD",getParam("txtFailPTD"));
				this.context.put("txtFailPTG",getParam("txtFailPTG"));
				this.context.put("txtTajuk",(String)maklumatFail.get("tajukFail"));
				this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)getParam("txtIdKementerian")), null,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("idagensi")), "", " style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));
				String idnegeri = getParam("socNegeri");
			 	String iddaerah = getParam("socDaerah");
			 	if (mode.equals("doChangeStateRizab")) {
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				
			 	}
				if (mode.equals("doChangeDaerahRizab")) {
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				
				}
				this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));
				this.context.put("txtKemAgenTerkini", getParam("txtKemAgenTerkini"));
			 		
		 	}else{
	//	 		myLog.info("getMaklumatRizabAwal:else changeTarafRizab");
		 		this.context.put("txtFailPTD",(String)maklumatFail.get("noFailPTD"));
				this.context.put("txtFailPTG",(String)maklumatFail.get("noFailPTG"));
				this.context.put("txtTajuk",(String)maklumatFail.get("tajukFail"));
				//this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)maklumatFail.get("idkementerian")), null,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
				this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian"
						, Utils.parseLong((String)maklumatFail.get("idkementerian")), null
						,"onChange=\"doChangeKementerianRizab()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi"
						, (String)maklumatFail.get("idkementerian"), Utils.parseLong((String)maklumatFail.get("idagensi"))
						, "", " style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"
						,Long.parseLong(maklumatFail.get("idnegeri").toString())
						," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatFail.get("idnegeri").toString()
						, "socDaerah", Utils.parseLong(maklumatFail.get("idDaerah").toString())
						,""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				idnegeri = String.valueOf(maklumatFail.get("idnegeri"));
	//			myLog.info("getMaklumatRizabAwal:else idnegeri="+idnegeri+"iddaerah="+iddaerah);
				if (mode.toLowerCase().indexOf("change") != -1) {
					idnegeri = getParam("socNegeri");
				 	iddaerah = getParam("socDaerah");
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\" style=\"width:300px\""));	
	
				}
				if (mode.equals("doChangeDaerahRizab")) {
					//this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizab();\""));	
					//this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
				}
				this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));
	
		 	}
		 	getMaklumatRizabAwalPengesahan(session,"R",idHakmilik,submit);
			this.context.put("changeLuas",submit);
		
		}catch(Exception e){
			 //e.printStackTrace();
			//throw new Exception("MASALAH RANGKAIAN[2] , SILA SEMAK RANGKAIAN DB METHOD doGetListBox(ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod)");
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] MASALAH RANGKAIAN[2] , SILA SEMAK RANGKAIAN DB METHOD doGetListBox(ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod)"));
		}

	}

	// VIEW MAKLUMAT RIZAB BY ID_PERMOHONAN - (AKTIF)
	private void getMaklumatRizabAwalPengesahan(HttpSession session,String r,String idHakmilik,String submit) throws Exception {
		myLog.info("getMaklumatRizabAwal:0");
		//Hashtable maklumatRizab0 = (Hashtable)frmRekodUtilData.getRizabUrusan(idPermohonan,null);
		Hashtable<?, ?> maklumatRizab = (Hashtable<?, ?>)getIPengesahan().getPerolehanInfo(idHakmilik);
		if(!maklumatRizab.get("IdDaerahPermohonan").equals("0")){
			//myLog.info("maklumatRizab.get(\"IdDaerah\"):"+maklumatRizab.get("IdDaerah"));
			//myLog.info("getParam(\"socDaerah\"):"+getParam("socDaerah"));
			iddaerah = (getParam("socDaerah")==null||getParam("socDaerah")==""?String.valueOf(maklumatRizab.get("IdDaerah")):getParam("socDaerah"));
			this.context.put("selectDaerah", UtilHTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong((String)maklumatRizab.get("IdDaerah")),""," onChange=\"doChangeDaerahRizab();\" style=\"width:300px\""));
		
		}
		this.context.put("selectMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong((String)maklumatRizab.get("IdMukim"))," style=\"width:300px\""));
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
		this.context.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));	
		this.context.put("txtIdLuasLama",(String)maklumatRizab.get("idLuasLama"));	
		this.context.put("txtLuasLama", (String)maklumatRizab.get("luasLama"));
		//myLog.info("socLuad:"+getParam("socLuas"));
		this.context.put("socLuas",getParam("socLuas"));	
		//this.context.put("txtLuas", (String)maklumatRizab.get("luas"));	
		this.context.put("txtNoPelan",getParam("txtNoPelan"));	
		this.context.put("txtNoSyit", getParam("txtNoSyit"));
		this.context.put("txtNoPu", getParam("txtNoPu"));	

		this.context.put("idHakmilik",idHakmilik);	
		this.context.put("idPermohonan",(String)maklumatRizab.get("idPermohonan"));	
		myLog.info("getMaklumatRizabAwal("+session+","+r+","+idHakmilik+"): END");
		
	}
	
	//By Rosli 06/05/2010 
	//VIEW MAKLUMAT RIZAB MENGIKUT PERMOHONAN
	private void viewMaklumatRizab(Vector listPermohonan, String dis,String changeLuas) throws Exception {

		Hashtable maklumatRizab =(Hashtable<?, ?>)listPermohonan.get(0);
		//changeLuas = paparrizabterperinci
		//changeLuas = viewdetailrizab
		//changeLuas = viewdetailrizab
		//changeLuas =kemaskiniMaklumatRizab
		if(changeLuas.equals("kemaskiniMaklumatRizab")){
			this.context.put("txtFailPTD",getParam("txtFailPTD"));
			this.context.put("txtFailPTG",getParam("txtFailPTG"));
			this.context.put("txtTajuk",(String)maklumatRizab.get("tajukFail"));
//			this.context.put("txtIdKementerian", (String)maklumatRizab.get("idkementerian"));	
//			this.context.put("txtNamaKementerian", (String)maklumatRizab.get("kementerian"));
//			this.context.put("txtIdAgensi", (String)maklumatRizab.get("idagensi"));	
//			this.context.put("txtNamaAgensi", (String)maklumatRizab.get("agensi"));
			myLog.info(maklumatRizab.get("idkementerian"));
			myLog.info(maklumatRizab.get("idagensi"));
			this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)maklumatRizab.get("idKementerian")), null,"onChange=\"doChangeKementerianRizabTambah()\" style=\"width:400px\""));
			//this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)maklumatRizab.get("idKementerian")), null,"onChange=\"doChangeKementerianRizabTambah()\" style=\"width:400px\""));
			this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)maklumatRizab.get("idKementerian"), Utils.parseLong((String)maklumatRizab.get("idAgensi")), "", " style=\"width:400px\""));
			this.context.put("txtNoFailSeksyen", (String)maklumatRizab.get("noFail"));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(maklumatRizab.get("idNegeri").toString())," onChange=\"doChangeStateRizabEdit('"+changeLuas+"');\" class=\""+dis+"\" "+dis));	
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatRizab.get("idNegeri").toString(), "socDaerah", Utils.parseLong(maklumatRizab.get("idDaerah").toString()),""," onChange=\"doChangeDaerahRizabEdit('"+changeLuas+"');\" class=\""+dis+"\" "+dis));
			String idnegeri = getParam("socNegeri");
		 	String iddaerah = getParam("socDaerah");
		 	if (mode.equals("doChangeStateRizab")) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," style=\"width:300px\" onChange=\"doChangeDaerahRizabEdit('"+changeLuas+"');\""));
			}
			if (mode.equals("doChangeDaerahRizab")) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," style=\"width:300px\" onChange=\"doChangeDaerahRizabEdit('"+changeLuas+"');\""));
			}

			this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",Long.parseLong(maklumatRizab.get("idMukim").toString())," style=\"width:300px\" class=\""+dis+"\" "+dis));
			//this.context.put("selectMukim",HTML.SelectMukim("socMukim",Long.parseLong(maklumatRizab.get("idMukim").toString())," style=\"width:300px\" class=\""+dis+"\" "+dis));
			
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
		
		//}else if("simpanRizab".equals(changeLuas)){
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
			
		 	//AZAM - Override - capture what ever do changes mode
		 	if (mode.toLowerCase().indexOf("change") != -1) {
				String idnegeri = getParam("socNegeri");
			 	String iddaerah = getParam("socDaerah");
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabEdit();\""));
				this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)getParam("txtIdKementerian")), null,"onChange=\"doChangeKementerianRizabEdit()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("txtIdAgensi")), "", " style=\"width:400px\""));
		 	}
		 	/*
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
			*/
			//this.context.put("selectMukim",HTML.SelectMukim("socMukim",Long.parseLong(maklumatRizab.get("idMukim").toString())," style=\"width:300px\" class=\""+dis+"\" "+dis));
			iddaerah = (String)maklumatRizab.get("idDaerah");
			this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",Long.parseLong(maklumatRizab.get("idMukim").toString())," style=\"width:300px\" class=\""+dis+"\" "+dis));

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
			//this.context.put("txtLuas", (String)maklumatRizab.get("luas"));	
			// kosongkan untuk data baru
			//this.context.put("txtLuas",Utils.formatLuas(Double.parseDouble(String.valueOf(maklumatRizab.get("luasBersamaan")))));	
			
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

	private void viewMaklumatRizabPermohonan(Vector listPermohonan, String display,String changeLuas) 
		throws Exception {
		
		System.out.println("listPermohonan : "+listPermohonan);
		
		Hashtable<?, ?> maklumatRizab =(Hashtable<?, ?>)listPermohonan.get(0);
		myLog.info("viewMaklumatRizabPermohonan("+display+","+changeLuas+")");
		if(changeLuas.equals("kemaskiniMaklumatRizab")){
			this.context.put("txtFailPTD",getParam("txtFailPTD"));
			this.context.put("txtFailPTG",getParam("txtFailPTG"));
			this.context.put("txtTajuk",(String)maklumatRizab.get("tajukFail"));
			this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)maklumatRizab.get("idKementerian")), null,"onChange=\"doChangeKementerianRizabPer()\" style=\"width:400px\""));
			this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)maklumatRizab.get("idKementerian"), Utils.parseLong((String)maklumatRizab.get("idAgensi")), "", " style=\"width:400px\""));
			this.context.put("txtNoFailSeksyen", (String)maklumatRizab.get("noFail"));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(maklumatRizab.get("idNegeri").toString())," style=\"width:300px\" onChange=\"doChangeStateRizabEdit('"+changeLuas+"');\" class=\""+display+"\" "+display));	
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatRizab.get("idNegeri").toString()
					, "socDaerah", Utils.parseLong(maklumatRizab.get("idDaerah").toString()),""
					," style=\"width:300px\" onChange=\"doChangeDaerahRizabEdit('"+changeLuas+"');\" class=\""+display+"\" "+display));
			String idnegeri = getParam("socNegeri");
		 	String iddaerah = getParam("socDaerah");
		 	if (mode.equals("doChangeStateRizab")) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," style=\"width:300px\" onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," style=\"width:300px\" onChange=\"doChangeDaerahRizabEdit('"+changeLuas+"');\""));
			}
			if (mode.equals("doChangeDaerahRizab")) {
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," style=\"width:300px\" onChange=\"doChangeStateRizabEdit();\""));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," style=\"width:300px\" onChange=\"doChangeDaerahRizabEdit('"+changeLuas+"');\""));
			}
			this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim"
					,Long.parseLong(maklumatRizab.get("idMukim").toString())," style=\"width:300px\" class=\""+display+"\" "+display));
			//this.context.put("selectMukim",HTML.SelectMukim("socMukim",Long.parseLong(maklumatRizab.get("idMukim").toString())," style=\"width:300px\" class=\""+dis+"\" "+dis));
			
			this.context.put("idHakmilik", (String)maklumatRizab.get("idHakmilik"));			
			this.context.put("txtFailKJP", (String)maklumatRizab.get("noFailKJP"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
	        this.context.put("selectLot", HTML.SelectLot("socLot",Utils.parseLong( String.valueOf(getParam("socLot")))
	        		,"style=\"width:200px\" class=\""+display+"\" "+display));
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
			
			this.context.put("txtNoWarta", "");
			this.context.put("txtNoPu", "");
			this.context.put("txtLokasi", "");
			this.context.put("txtKegunaanTanah", "");
			this.context.put("txtLuas", "");
			this.context.put("txtNoPelan", "");
			this.context.put("socLuas", "");
			this.context.put("txtKemAgenTerkini", "");
			this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab",null, " class=\""+display+"\" "+display+" style=\"width:200px\"" ));
			iddaerah = (String)maklumatRizab.get("idDaerah");
			this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",null," class=\""+display+"\" "+display+" style=\"width:300px\" "));
			
			this.context.put("txtFailPTD",(String)maklumatRizab.get("noFailPTD"));
			this.context.put("txtFailPTG",(String)maklumatRizab.get("noFailPTG"));
			this.context.put("txtTajuk",(String)maklumatRizab.get("tajukFail"));
			this.context.put("txtIdKementerian", (String)maklumatRizab.get("idkementerian"));	
			this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian", Utils.parseLong((String)getParam("txtIdKementerian")), null,"onChange=\"doChangeKementerianRizabPer()\" style=\"width:400px\""));
			this.context.put("txtIdAgensi", (String)maklumatRizab.get("idagensi"));	
			this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("txtIdAgensi")), "", " style=\"width:400px\""));
			//this.context.put("socLuas",(String)maklumatRizab.get("idLuas"));
			if(changeLuas.equals("tambahRizab")){
				this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian", Utils.parseLong((String)maklumatRizab.get("idKementerian")), null,"onChange=\"doChangeKementerianRizabPer()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)maklumatRizab.get("idKementerian"), Utils.parseLong((String)maklumatRizab.get("idAgensi")), "", " style=\"width:400px\""));
				//this.context.put("socLuas",getParam("socLuas"));	
			}
			this.context.put("txtNoFailSeksyen", (String)maklumatRizab.get("noFail"));
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(maklumatRizab.get("idNegeri").toString())
					," onChange=\"doChangeStateRizabPer();\" class=\""+display+"\" "+display+ " style=\"width:300px\"" ));	
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(maklumatRizab.get("idNegeri").toString(), "socDaerah"
					, Utils.parseLong(maklumatRizab.get("idDaerah").toString()),""
					," onChange=\"doChangeDaerahRizabPer();\" class=\""+display+"\" "+display+" style=\"width:300px\" "));
			
			//this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim"
			//		,Long.parseLong(maklumatRizab.get("idMukim").toString())," class=\""+display+"\" "+display+" style=\"width:300px\" "));
			this.context.put("idHakmilik", (String)maklumatRizab.get("idHakmilik"));			
			this.context.put("txtFailKJP", (String)maklumatRizab.get("noFailKJP"));
			//this.context.put("txtNoWarta", (String)maklumatRizab.get("noWarta"));
	        this.context.put("selectLot", HTML.SelectLot("socLot",Utils.parseLong( (String)maklumatRizab.get("idLot"))
	        		,"class=\""+display+"\" "+display+" style=\"width:200px\" "));
			//this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab"
			//		,String.valueOf(maklumatRizab.get("idJenisRizab")), " class=\""+display+"\" "+display+" style=\"width:200px\"" ));
			// Kemaskini pada 28/05/2013
	        //this.context.put("txtNoLot", (String)maklumatRizab.get("noLot"));	
			this.context.put("txtNoLot", "");	
			//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong( (String)maklumatRizab.get("idLuas")), " style=\"width:200px\""," pilihLuasRizabPer()"));
			this.context.put("txdTarikhTerima",(String)maklumatRizab.get("tarikhTerima"));	
			this.context.put("txdTarikhWarta", (String)maklumatRizab.get("tarikhWarta"));	
			this.context.put("idPermohonan", (String)maklumatRizab.get("idPermohonan"));	
			//this.context.put("txtLokasi",(String)maklumatRizab.get("lokasi") );	
			//this.context.put("txtKegunaanTanah", (String)maklumatRizab.get("kegunaanTanah"));	
			this.context.put("txtIdLuasLama",(String)maklumatRizab.get("idLuasLama"));	
			//this.context.put("txtLuas",Utils.formatLuas(Double.parseDouble(String.valueOf(maklumatRizab.get("luasBersamaan")))));	
			//this.context.put("txtLuas","0.00000");	

			//this.context.put("txtNoPelan",(String)maklumatRizab.get("noPelan"));	
			this.context.put("txtNoSyit", (String)maklumatRizab.get("noSyit"));
			//this.context.put("txtNoPu", (String)maklumatRizab.get("noPu"));	
			this.context.put("statusSah", (String)maklumatRizab.get("statusSah"));
			//this.context.put("pegawaiAkhir", (String)maklumatRizab.get("pegawaiAkhir"));
			//this.context.put("txdTarikhKemaskini", (String)maklumatRizab.get("tarikhKemaskini"));
			this.context.put("pegawaiAkhir", "");
			this.context.put("txdTarikhKemaskini", "");
			this.context.put("changeLuas",changeLuas);
			this.context.put("txtLuas1", "");
			this.context.put("txtLuas2", "");
			this.context.put("txtLuas3", "");
			this.context.put("txtLuas4", "");
			this.context.put("txtLuas5", "");
			this.context.put("txtLuas6", "");
			this.context.put("txtLuasLama", "0.0");
			
		}
		//this.context.put("socLuas",(String)maklumatRizab.get("idLuas"));
				
	}

	// access by submit = kemaskiniDetailRizab
	private void viewMaklumatRizab(HakMilik hakmilik, String display,String btnSubmit) throws Exception {	
		try{			
			myLog.info("viewMaklumatRizab:submit="+btnSubmit+":display="+display);
			//Kemaskini pada 19/07/2013
			//htpPermohonan = getIPembelian().findPermohonan(String.valueOf(hakmilik.getIdPermohonan()), "");
			htpPermohonan = getIPermohonan().getPermohonanInfo("",String.valueOf(hakmilik.getIdPermohonan()));
			if(btnSubmit.equals("kemaskiniMaklumatRizab")){
				this.context.put("txtFailPTD",getParam("txtFailPTD"));
				this.context.put("txtFailPTG",getParam("txtFailPTG"));
				//this.context.put("txtTajuk",hakmilik.getNoFail());
				this.context.put("txtTajuk",htpPermohonan.getPermohonan().getPfdFail().getTajukFail());			
				//log.info(maklumatRizab.get("idkementerian")+maklumatRizab.get("idagensi"));
				this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian", hakmilik.getAgensi().getKementerian().getIdKementerian(), null,"onChange=\"doChangeKementerianRizabEdit()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", String.valueOf(hakmilik.getAgensi().getKementerian().getIdKementerian()), hakmilik.getAgensi().getIdAgensi(), "", " style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen",hakmilik.getNoFail());
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",hakmilik.getNegeri().getIdNegeri()," style=\"width:300px\" onChange=\"doChangeStateRizabEdit('"+btnSubmit+"');\" class=\""+display+"\" "+display+" style=\"width:300px\" "));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(String.valueOf(hakmilik.getNegeri().getIdNegeri()), "socDaerah", hakmilik.getDaerah().getIdDaerah(),""," onChange=\"doChangeDaerahRizabEdit('"+btnSubmit+"');\" class=\""+display+"\" "+display+" style=\"width:300px\" "));
				String idnegeri = getParam("socNegeri");
			 	iddaerah = getParam("socDaerah")==null||getParam("socDaerah")==""?String.valueOf(hakmilik.getDaerah().getIdDaerah()):getParam("socDaerah");
//			 	String iddaerah = getParam("socDaerah")==null?String.valueOf(hakmilik.getDaerah().getIdDaerah()):getParam("socDaerah");
//				iddaerah = String.valueOf(hakmilik.getDaerah().getIdDaerah()); --lama
//				if (mode.equals("doChangeStateRizab")) {
//					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\" style=\"width:300px\" "));	
//					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabEdit('"+btnSubmit+"');\" style=\"width:300px\" "));
//				
//				}
//				if (mode.equals("doChangeDaerahRizab")) {
//					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\" style=\"width:300px\" "));	
//					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabEdit('"+btnSubmit+"');\" style=\"width:300px\" "));
//				
//				}
				this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",hakmilik.getMukim().getIdMukim()," style=\"width:300px\" class=\""+display+"\" "+display+" style=\"width:300px\" "));
				this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab"
						,String.valueOf(hakmilik.getIdJenisRizab()), " style=\"width:200px class=\""+display+"\" "+display," "));

				if (mode.toLowerCase().indexOf("change") != -1) {
			 		myLog.info("index of change");
					this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian", Utils.parseLong((String)getParam("txtIdKementerian")), null,"onChange=\"doChangeKementerianRizabEdit()\" style=\"width:400px\""));
					this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("txtIdAgensi")), "", " style=\"width:400px\""));
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\" style=\"width:300px\" "));	
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabEdit('"+btnSubmit+"');\" style=\"width:300px\" "));
					this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim", Utils.parseLong(String.valueOf(getParam("socMukim")))," class=\""+display+"\" "+display+" style=\"width:300px\" "));
			 		//myLog.info("index of change="+getParam("socjenisrizab"));
					this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab"
							,String.valueOf(getParam("socjenisrizab")), " class=\""+display+"\" "+display+" style=\"width:200px\""));

			 	}
				this.context.put("idHakmilik",hakmilik.getIdHakmilik());			
				this.context.put("txtFailKJP", htpPermohonan.getNoRujukanKJP());
				this.context.put("txtNoWarta", getParam("txtNoWarta"));
		        this.context.put("selectLot", HTML.SelectLot("socLot",Utils.parseLong( String.valueOf(getParam("socLot"))),"style=\"width:200px\" class=\""+display+"\" "+display));
				this.context.put("txtNoLot", getParam("txtNoLot"));	
				this.context.put("txdTarikhTerima",getParam("txdTarikhTerima"));	
				this.context.put("txdTarikhWarta", getParam("txdTarikhWarta"));	
				this.context.put("txtLokasi",  getParam("txtLokasi"));	
				this.context.put("idPermohonan", hakmilik.getIdPermohonan());	
				this.context.put("txtKegunaanTanah", getParam("txtKegunaanTanah"));	
				this.context.put("txtIdLuasLama",getParam("socLuas"));	
				myLog.info("viewMaklumatRizab:submit="+btnSubmit+":luas="+getParam("socLuas"));
				//this.context.put("txtLuasGabung", hakmilik.getLuasString());	
				String luas = "0";
				String luas1 = "0";
				String luas2 = "0";

				if(getParam("socLuas").equals("1") || getParam("socLuas").equals("2") || getParam("socLuas").equals("3") || getParam("socLuas").equals("5") || getParam("socLuas").equals("6")){
					myLog.info("Belum viewMaklumatRizab:submit="+btnSubmit+":luas="+luas);
					if(getParam("socLuas").equals("1")){
						if(getParam("txtLuasGabung").contains("KM"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
						else
							luas = getParam("txtLuasGabung");
						
					}else if(getParam("socLuas").equals("2")){
						if(getParam("txtLuasGabung").contains("H"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
						else
							luas = getParam("txtLuasGabung");
						
					}else if(getParam("socLuas").equals("3")){
						if(getParam("txtLuasGabung").contains("MP"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
						else{
							if(getParam("txtLuasGabung").contains("M"))
								luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
							else
								luas = getParam("txtLuasGabung");
						}
						
					}else if(getParam("socLuas").equals("5")){
						if(getParam("txtLuasGabung").contains("KP"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-2));
						else{
							if(getParam("txtLuasGabung").contains("K"))
								luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
							else
								luas = getParam("txtLuasGabung");
						}
						
					}else if(getParam("socLuas").equals("6")){
						if(getParam("txtLuasGabung").contains("P"))
							luas = getParam("txtLuasGabung").substring(0, (getParam("txtLuasGabung").length()-1));
						else
							luas = getParam("txtLuasGabung");
						
					}
					//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas="+luas);

				}else if(getParam("socLuas").equals("4")){
					//myLog.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("E,")));
					myLog.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
					if(getParam("txtLuasGabung").contains(",")){
						luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("E,"));
						luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("E,")+2,getParam("txtLuasGabung").indexOf("R,"));
						luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R,")+2,(getParam("txtLuasGabung").length()-1));
					
					}else{
						luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("E"));
						luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("E")+1,getParam("txtLuasGabung").indexOf("R"));
						luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R")+1,(getParam("txtLuasGabung").length()-1));
						
					}
					//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas1="+luas);
					//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas2="+luas1.trim());
					//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas3="+luas2.trim());
					
				}else if(getParam("socLuas").equals("8")){
					//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
					luas = getParam("txtLuasGabung").substring(0,getParam("txtLuasGabung").indexOf("R,"));
					luas1 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("R,")+2,getParam("txtLuasGabung").indexOf("J"));
					luas2 = getParam("txtLuasGabung").substring(getParam("txtLuasGabung").indexOf("J,")+2,(getParam("txtLuasGabung").length()-1));

					
				}else{ //7||9 (TIADA SAMPLE DATA)
					luas = getParam("txtLuasGabung");
					
				}
				this.context.put("txtLuasLama1", luas1.trim());	
				this.context.put("txtLuasLama2", luas2.trim());	
				this.context.put("txtLuasLama", luas);	

				//this.context.put("txtLuasLama", hakmilik.getLuasString());	
				this.context.put("socLuas",getParam("socLuas"));	
				//this.context.put("txtLuas", hakmilik.getLuas());	
				this.context.put("txtLuas", getParam("txtLuas"));	
				//this.context.put("txtSyarat", "");	
				//this.context.put("txtSekatan", "");	
				//this.context.put("txtSekatan", "");	
				this.context.put("txtNoPelan",getParam("txtNoPelan"));	
				this.context.put("txtNoSyit", getParam("txtNoSyit"));
				this.context.put("txtNoPu", getParam("txtNoPu"));	
				//this.context.put("txtKawasanRizab", "");	
				//this.context.put("txtTempoh", "");
				this.context.put("statusSah", hakmilik.getStatusSah());
				//this.context.put("pegawaiAkhir", hakmilik.getNamaKemaskini());txtPegawaiAkhir
				//this.context.put("txdTarikhKemaskini", hakmilik.getTarikhKemaskini());txdTarikhKemaskini
				this.context.put("pegawaiAkhir", getParam("txtPegawaiAkhir"));
				this.context.put("txdTarikhKemaskini", getParam("txdTarikhKemaskini"));
				this.context.put("changeLuas",btnSubmit);						
				this.context.put("txtKemAgenTerkini",getParam("txtKemAgenTerkini"));
				
			}else{
				myLog.info(" !kemaskiniMaklumatRizab");
				this.context.put("txtFailPTD",hakmilik.getNoFailPTD());
				this.context.put("txtFailPTG",hakmilik.getNoFailPTG());
				this.context.put("txtTajuk",htpPermohonan.getPermohonan().getPfdFail().getTajukFail());
				//this.context.put("txtIdKementerian",hakmilik.getAgensi().getKementerian().getIdKementerian());	
				//this.context.put("txtNamaKementerian", hakmilik.getAgensi().getKementerian().getNamaKementerian());
				//this.context.put("txtIdAgensi", hakmilik.getAgensi().getIdAgensi());	
				//this.context.put("txtNamaAgensi", hakmilik.getAgensi().getNamaAgensi());
				this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", hakmilik.getAgensi().getKementerian().getIdKementerian(), null,"onChange=\"doChangeKementerianRizabEdit()\" class=\""+display+"\" "+display+" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", String.valueOf(hakmilik.getAgensi().getKementerian().getIdKementerian()), hakmilik.getAgensi().getIdAgensi(), "", " class=\""+display+"\" "+display+"  style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen", hakmilik.getNoFail());
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",hakmilik.getNegeri().getIdNegeri()," onChange=\"doChangeStateRizabEdit();\" class=\""+display+"\" "+display +" style=\"width:300px\" "));	
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(String.valueOf(hakmilik.getNegeri().getIdNegeri()), "socDaerah", hakmilik.getDaerah().getIdDaerah(),""," onChange=\"doChangeDaerahRizabEdit();\" class=\""+display+"\" "+display+" style=\"width:300px\" "));				
				this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab"
						,String.valueOf(hakmilik.getIdJenisRizab()), " class=\""+display+"\" "+display+" style=\"width:200px\""));
			 	//AZAM - Override - capture what ever do changes mode
			 	if (mode.toLowerCase().indexOf("change") != -1) {
					String idnegeri = getParam("socNegeri");
				 	String iddaerah = getParam("socDaerah");
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateRizabEdit();\" style=\"width:300px\" "));	
					this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),""," onChange=\"doChangeDaerahRizabEdit();\" style=\"width:300px\" "));
					this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong((String)getParam("txtIdKementerian")), null," onChange=\"doChangeKementerianRizabEdit()\" style=\"width:400px\""));
					this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)getParam("txtIdKementerian"), Utils.parseLong((String)getParam("txtIdAgensi")), "", " style=\"width:400px\""));
					this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab"
							,String.valueOf(getParam("socjenisrizab")), " class=\""+display+"\" "+display+" style=\"width:200px\""));
			 	
			 	}
				iddaerah = String.valueOf(hakmilik.getDaerah().getIdDaerah());
				this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",hakmilik.getMukim().getIdMukim()," class=\""+display+"\" "+display+" style=\"width:300px\" "));
	
				this.context.put("idHakmilik", hakmilik.getIdHakmilik());			
				this.context.put("txtFailKJP", htpPermohonan.getNoRujukanKJP());
				this.context.put("txtNoWarta", hakmilik.getNoWarta());
		        this.context.put("selectLot", HTML.SelectLot("socLot",hakmilik.getIdJenisLot()," style=\"width:200px\" class=\""+display+"\" "+display));
				this.context.put("txtNoLot", hakmilik.getNoLot());	
				this.context.put("selectLuas", HTML.SelectLuas("socLuas",hakmilik.getIdLuas(), " style=\"width:200px\""," pilihLuas()"));
				this.context.put("txdTarikhTerima",lebah.util.DateTool.getDateTimeFormatted(hakmilik.getTarikhTerima(), "dd/MM/yyyy"));	
				this.context.put("txdTarikhWarta",lebah.util.DateTool.getDateTimeFormatted(hakmilik.getTarikhWarta(), "dd/MM/yyyy"));	
				this.context.put("idPermohonan", hakmilik.getIdPermohonan());	
				this.context.put("txtLokasi",hakmilik.getLokasi() );	
				this.context.put("txtKegunaanTanah", hakmilik.getKegunaan());	
				this.context.put("txtIdLuasLama",hakmilik.getIdLuas());	
				this.context.put("txtLuasLama", hakmilik.getLuasString());	
				this.context.put("txtLuasGabung", hakmilik.getLuasString());	
				this.context.put("socLuas",hakmilik.getIdLuas());	
				
				//this.context.put("txtLuas", hakmilik.getLuas());	
				this.context.put("txtLuas",Utils.formatLuas(hakmilik.getLuasBersamaan()));	
				this.context.put("txtNoPelan",hakmilik.getNoPelan());	
				this.context.put("txtNoSyit", hakmilik.getNoSyit());
				this.context.put("txtNoPu", hakmilik.getNoPU());	
				this.context.put("statusSah", hakmilik.getStatusSah());
				this.context.put("pegawaiAkhir", hakmilik.getNamaKemaskini());
				this.context.put("txdTarikhKemaskini",lebah.util.DateTool.getDateTimeFormatted(hakmilik.getTarikhKemaskini(),"dd/MM/yyyy"));
				this.context.put("changeLuas",btnSubmit);
				this.context.put("txtKemAgenTerkini",hakmilik.getCatatan());
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] MASALAH RANGKAIAN[2] , SILA SEMAK RANGKAIAN DB METHOD doGetListBox(ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod)"));
		
		}
		
	}

	
	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeHakmilikV1(String idPermohonan) throws Exception {
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
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",0L, " style=\"width:200px;\""));
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
			//e.printStackTrace();
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
		myLog.info("idHakmilik :"+id);
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
	private void XXview_modeMaklumatFail(HttpSession session) throws Exception {
		String idHakmilik = getParam("idHakmilik");
		Vector list =null;
		//list = FrmRekodPendaftaranHakmilikSementaraData.getPaparMaklumatFailById(idHakmilik);
		list = getIHakmilik().getPaparMaklumatFailById(idHakmilik);
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

	// VIEW MAKLUMAT FAIL BY ID (MASTER)
	private void view_modeMaklumatFail(String idHakmilik) throws Exception {
		Vector list =null;
		// Dikemaskini 09/04/2012
		//list = FrmRekodPendaftaranHakmilikSementaraData.getPaparMaklumatFailById(idHakmilik);
		list = getIHakmilik().getPaparMaklumatFailById(idHakmilik);
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
	private void XviewModeMaklumatFail(HttpSession session,String idHakmilik) throws Exception {
		Vector list =null;
		//list = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById(idHakmilik);
		list = getIHakmilik().getPaparMaklumatFailById(idHakmilik);
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
		//int id = Integer.parseInt(idHakmilik);
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

	//AZAM
	public void doXX() throws Exception {
		try {
			
		} catch (Exception e) {
			//throw new Exception("Ralat:"+e.getMessage());
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] "+e.getMessage()));
		 
		}
	}
	
	public void emptyContext() throws Exception {
		this.context.put("readOnly", "");
		this.context.put("disabled", "");	

		this.context.put("txtCukaiTahun", "");	
		this.context.put("txtLokasi", "");	
		this.context.put("txtKegunaanTanah", "");	
		this.context.put("txtNoWarta", "");	
		this.context.put("txdTarikhWarta", "");	
		this.context.put("txtSyarat", "");	
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
			
		this.context.put("txtLuas","");
		this.context.put("txtLuas1","");
		this.context.put("txtTempoh","");
		this.context.put("txtCukaiTahun","");
		this.context.put("txtLokasi","");
		this.context.put("txtKegunaanTanah","");
		this.context.put("socLuas","");
		this.context.put("txtNoLot","");
		this.context.put("txtNoPelan","");
		this.context.put("txtNoSyit","");
		this.context.put("txtNoHakmilik","");
		
		//fixbug:syaz:11/11/2014
		this.context.put("txtKemAgenTerkini","");
		this.context.put("txtLuasLama","");
		
	}
	
	public void initContextValue() throws Exception {
	   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",null, "style=\"width:200px\""));
        this.context.put("selectLot", HTML.SelectLot("noLot",null," style=\"width:200px\""));
	   	this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",null, " style='width:200px;'"));
		this.context.put("selectKategori", HTML.SelectKategori("socKategori",null, " style='width:200px;'"));
		this.context.put("selectLuas", HTML.SelectLuas("socLuas",null, " style=\"width:200px\"","pilihLuas()"));
		this.context.put("txdTarikhTerima", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));	
		this.context.put("txdTarikhDaftar", lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));	
		emptyContext();
		
	}
	
	public void doInitMaklumatFail(String idPermohonan) throws Exception {
		try{
			HtpPermohonan  maklumatFail = getIPermohonan().getPermohonanInfo("",idPermohonan);
			myLog.info(getSemakSubUrusan(String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdSubUrusan())));
			semakSubUrusan = getSemakSubUrusan(String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdSubUrusan()));

			//myLog.info("maklumatFail="+maklumatFail);
			if (maklumatFail != null) { 
				idnegeri = String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdNegeri()); 
				this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			
				if(idnegeri!=null && idnegeri!=""){
					this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", null," onChange=\"doChangeDaerahHakmilik();\""));
				}
				
				this.context.put("txtFailPTG",maklumatFail.getNoRujukanPTG());
				this.context.put("txtFailPTD",maklumatFail.getNoRujukanPTD());
				this.context.put("txtFailKJP",maklumatFail.getNoRujukanKJP());

				this.context.put("txtTajuk",maklumatFail.getPermohonan().getTujuan());
				this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian",maklumatFail.getPermohonan().getPfdFail().getIdKementerian(), null,"onChange=\"doChangeKementerianHakmilik()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi",String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdKementerian())
						, maklumatFail.getIdAgensi(), "", " style=\"width:400px\""));
				this.context.put("txtNoFailSeksyen", maklumatFail.getPermohonan().getPfdFail().getNoFail());

		} else {
			myLog.debug("** maklumatFail empty **");
		}
		
		}catch(Exception e){
			 e.printStackTrace();
//			 throw new Exception(getIHTP().getErrorHTML(
//					 "MASALAH RANGKAIAN[4] , SILA SEMAK RANGKAIAN DB METHOD doInitMaklumatFail(ekptg.view.htp.FrmPendaftaranTanah)"));
			 throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] "+ e.getMessage()));
		
		}
		
	}
		
	public void XdoInitMaklumatFail(String idPermohonan) throws Exception {
		//Hashtable  maklumatFail = (Hashtable)frmRekodUtilData.getPermohonanInfoV1(idPermohonan);
		try{
			Hashtable  maklumatFail = getIPermohonan().getPermohonanInfo(idPermohonan);
		myLog.info("maklumatFail="+maklumatFail);
		if (maklumatFail != null) { 
			idnegeri = (String)maklumatFail.get("idnegeri");
			//iddaerah = (String)maklumatFail.get("idDaerah");
			
		    this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			
			this.context.put("txtFailPTG",(String)maklumatFail.get("noFailPTG"));
			this.context.put("txtFailPTD",(String)maklumatFail.get("noFailPTD"));
			this.context.put("txtFailKJP", (String)maklumatFail.get("rujukankjp"));

			this.context.put("txtTajuk",(String)maklumatFail.get("tajukFail"));
			/*this.context.put("txtIdKementerian", (String)maklumatFail.get("idkementerian"));	
			this.context.put("txtNamaKementerian", (String)maklumatFail.get("kementerian"));
			this.context.put("txtIdAgensi", (String)maklumatFail.get("idagensi"));	
			this.context.put("txtNamaAgensi", (String)maklumatFail.get("agensi"));
			*/
			this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian", Utils.parseLong((String)maklumatFail.get("idkementerian")), null,"onChange=\"doChangeKementerianHakmilik()\" style=\"width:400px\""));
			this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)maklumatFail.get("idkementerian"), Utils.parseLong((String)maklumatFail.get("idagensi")), "", " style=\"width:400px\""));
			this.context.put("txtNoFailSeksyen", (String)maklumatFail.get("fail"));

		} else {
			myLog.debug("** maklumatFail empty **");
		}
		
		}catch(Exception e){
			 e.printStackTrace();
//			 throw new Exception(getIHTP().getErrorHTML(
//					 "MASALAH RANGKAIAN[4] , SILA SEMAK RANGKAIAN DB METHOD doInitMaklumatFail(ekptg.view.htp.FrmPendaftaranTanah)"));
			 throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] "+ e.getMessage()));
		
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
			myLog.debug("** maklumatHakmilik empty **");
			initContextValue();
		}
	}
	
	public void doInitMaklumatHakmilik(String mode,String idHakmilik) throws Exception {
		myLog.info("\ndoInitMaklumatHakmilik("+mode+","+idHakmilik+")");	
		if ("".equals(idHakmilik)) idHakmilik = "-1";
		
		Hashtable maklumatHakmilik = null;
		if ("byIdPermohonan".equals(mode)) {
			myLog.info("\nbyIdPermohonan");	
			maklumatHakmilik = (Hashtable)frmRekodUtilData.getDetailHakmilikByIdPermohonan(idHakmilik);
		} else {
			myLog.info("\nTidak byIdPermohonan");	
			maklumatHakmilik = (Hashtable)frmRekodUtilData.getDetailHakmilik(idHakmilik);	
		}
		 
		if(maklumatHakmilik != null){			
			idnegeri = (String)maklumatHakmilik.get("id_negeri");
			iddaerah = (String)maklumatHakmilik.get("id_daerah");
			idmukim = (String)maklumatHakmilik.get("id_mukim");
			//if(!"update".equals(mode)){
		//		this.context.put("txtNamaKementerian",maklumatHakmilik.get("namakementerian"));
			//}else{
			// 2013/04/10
			//
			this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian", Utils.parseLong((String)maklumatHakmilik.get("idkementerian")), style,"onChange=\"doChangeKementerianHakmilik()\" style=\"width:400px\""));
			// 2013/07/19
			//this.context.put("txtNamaKementerian",UtilHTML.SelectKementerianAktif("txtIdKementerian", Utils.parseLong((String)maklumatHakmilik.get("idkementerian")), style," onChange=\"pilihKementerian('"+mode+"');\" style=\"width:400px\""));
			this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", (String)maklumatHakmilik.get("idkementerian"), Utils.parseLong((String)maklumatHakmilik.get("idagensi")), style , " style=\"width:400px\""));

			// 2013/04/23
			//
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),style," onChange=\"doChangeStateHakmilik();\""));
			//
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),style," onChange=\"doChangeDaerahHakmilik();\""));
			// 2013/07/19
			//this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),style," onChange=\"pilihKementerian('"+mode+"');\""));
			//this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),style," onChange=\"pilihKementerian('"+mode+"');\""));
			this.context.put("txtNamaMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),style,""));
			
			this.context.put("txtFailPTG",(String)maklumatHakmilik.get("no_fail_ptg"));
			this.context.put("txtFailPTD",(String)maklumatHakmilik.get("no_fail_ptd"));
		   	
			if ("byIdPermohonan".equals(mode)) {
				this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong("-1")," style=\"width:200px;\" "+style));
			   	this.context.put("txtNoHakmilik","");
			   	this.context.put("txtNoBangunan","");
			   	this.context.put("txtNoTingkat","");
			   	this.context.put("txtNoPetak","");
			   	this.context.put("selectLot", HTML.SelectLot("noLot",Utils.parseLong("-1")," style=\"width:200px;\" "+style));
				this.context.put("txtNoLot", "");	
			
			} else {
				this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong( (String)maklumatHakmilik.get("idJenisHakmilik"))," style=\"width:200px;\" "+style));
			   	this.context.put("txtNoHakmilik",(String)maklumatHakmilik.get("no_hakmilik"));
			   	this.context.put("txtNoBangunan",(String)maklumatHakmilik.get("no_bangunan"));
			   	this.context.put("txtNoTingkat", (String)maklumatHakmilik.get("no_tingkat"));
			   	this.context.put("txtNoPetak",(String)maklumatHakmilik.get("no_petak"));
			   	this.context.put("selectLot", HTML.SelectLot("noLot",Utils.parseLong( (String)maklumatHakmilik.get("idLot"))," style=\"width:200px;\" "+style));
				this.context.put("txtNoLot", (String)maklumatHakmilik.get("noLot"));	

			}
			
			this.context.put("txdTarikhTerima",(String)maklumatHakmilik.get("tarikh_terima"));	
			this.context.put("txdTarikhDaftar",(String)maklumatHakmilik.get("tarikh_daftar"));	
			
			this.context.put("socTaraf",(String)maklumatHakmilik.get("taraf_hakmilik"));	
			
			//log.debug(" ** CHECK SOC TARAF **"+maklumatHakmilik.get("taraf_hakmilik"));
			
			this.context.put("txtTempoh",(String)maklumatHakmilik.get("tempoh"));	
			this.context.put("txdTarikhLuput",(String)maklumatHakmilik.get("tarikh_luput"));	
			this.context.put("txtCukaiTahun",Utils.format2Decimal(Double.parseDouble((String)maklumatHakmilik.get("cukai"))));	
			this.context.put("txtLokasi",(String)maklumatHakmilik.get("lokasi"));	
			
			this.context.put("txtKegunaanTanah",(String)maklumatHakmilik.get("kegunaan_tanah"));	
			
			//this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong( (String)maklumatHakmilik.get("idLuas")), " style=\"width:200px\"","pilihLuas()"));
			this.context.put("socLuas",(String)maklumatHakmilik.get("idLuas"));
			
			//this.context.put("txtLuas",(String)maklumatHakmilik.get("luas_lama"));	
			this.context.put("txtLuas",Utils.formatLuas(Double.parseDouble((String)maklumatHakmilik.get("luas"))));	
			// 29/05/2013
			//this.context.put("txtLuasLama",(String)maklumatHakmilik.get("luas_lama"));	
			setLuas(String.valueOf(maklumatHakmilik.get("idLuas")), String.valueOf(maklumatHakmilik.get("luas_lama")));
						
			this.context.put("statusRizab",(String)maklumatHakmilik.get("status_rizab"));	
			this.context.put("txtNoWarta",(String)maklumatHakmilik.get("no_warta"));	
			this.context.put("txdTarikhWarta",(String)maklumatHakmilik.get("tarikh_warta"));	
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong( (String)maklumatHakmilik.get("id_rizab"))," style=\"width:200px;\" "+style));
			this.context.put("txtKawasanRizab",(String)maklumatHakmilik.get("kawasan_rizab"));
			//log.info("socKategorisocKategori:"+maklumatHakmilik.get("id_kategori"));
		   	this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong( (String)maklumatHakmilik.get("id_kategori"))," style=\"width:200px;\" "+style));
			this.context.put("txtSyarat",(String)maklumatHakmilik.get("syarat"));	
			this.context.put("txtSekatan",(String)maklumatHakmilik.get("sekatan"));	
			this.context.put("txtNoPelan",(String)maklumatHakmilik.get("no_pelan"));	
			this.context.put("txtNoSyit",(String)maklumatHakmilik.get("no_syit"));	
			this.context.put("txtNoPu",(String)maklumatHakmilik.get("no_pu"));	
			this.context.put("txtKemAgenTerkini",(String)maklumatHakmilik.get("catatan"));	
			
		} else {
			myLog.debug("** maklumatHakmilik empty **");
			initContextValue();
		
		}
		
	}
	
	public void doGetListBox() throws Exception {
		try{
			//26/10/2010 Buka komen, diguna untuk kemaskini hakmilik(pilih negeri),param id_negeri  
			idnegeri = getParam("socNegeri");if ("".equals(idnegeri)) idnegeri = "-1";
			iddaerah = getParam("socDaerah");if ("".equals(iddaerah)) iddaerah = "-1";
			idmukim = getParam("socMukim");if ("".equals(idmukim)) idmukim = "-1";
			//26/10/2010 Buka komen, diguna untuk kemaskini hakmilik(pilih negeri)  
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"doChangeStateHakmilik();\""));	
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),"$readonly class=\"$disabled\""," onChange=\"doChangeDaerahHakmilik();\""));
			this.context.put("txtNamaMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"$readonly class=\"$disabled\"",""));
		   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")), "style=\"width:200px\""));
		    this.context.put("selectLot", HTML.SelectLot("noLot",Utils.parseLong( getParam("noLot")),"style=\"width:200px\""));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong( getParam("socJenisRizab")), " style=\"width:200px;\""));
			this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong(getParam("socLuas")), " style=\"width:200px\""," pilihLuas()"));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong(getParam("socKategori")), " style=\"width:200px;\""));
			myLog.info("idKementerianKemasukan="+idKementerianKemasukan);
			if (mode.indexOf("doChange") != -1){
				this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong(idKementerianKemasukan), null,"onChange=\"doChangeKementerianHakmilik()\" style=\"width:400px\""));
				this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", idKementerianKemasukan, Utils.parseLong(idAgensiKemasukan), "", " style=\"width:400px\""));
			}
		
		}catch(Exception e){
			 e.printStackTrace();
			 throw new Exception(getIHTP().getErrorHTML(
					 "[MODUL HTP REKOD] MASALAH RANGKAIAN[1], SILA SEMAK RANGKAIAN DB METHOD doGetListBox(ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod)"));
		
		}
		
	}
	
	/**
	 * Untuk kegunaan kemaskini hakmilik
	 * @throws Exception
	 */
	public void doGetListBoxUpdate() throws Exception {
		try{
			idnegeri = getParam("socNegeri");if ("".equals(idnegeri)) idnegeri = "-1";
			iddaerah = getParam("socDaerah");if ("".equals(iddaerah)) iddaerah = "-1";
			idmukim = getParam("socMukim");if ("".equals(idmukim)) idmukim = "-1";
			this.context.put("txtNamaNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," onChange=\"pilihKementerian('"+mode+"');\""));	
			this.context.put("txtNamaDaerah", HTML.SelectDaerahByNegeri(idnegeri, "socDaerah", Utils.parseLong(iddaerah),"$readonly class=\"$disabled\""," onChange=\"pilihKementerian('"+mode+"');\""));
			this.context.put("txtNamaMukim", HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim),"$readonly class=\"$disabled\"",""));
		   	this.context.put("txtJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")), "style=\"width:200px\""));
		    this.context.put("selectLot", HTML.SelectLot("noLot",Utils.parseLong( getParam("noLot")),"style=\"width:200px\""));
			this.context.put("selectRizab", HTML.SelectRizab("socJenisRizab",Utils.parseLong( getParam("socJenisRizab")), " style=\"width:200px;\""));
			this.context.put("selectLuas", HTML.SelectLuas("socLuas",Utils.parseLong(getParam("socLuas")), " style=\"width:200px\""," pilihLuas()"));
			this.context.put("selectKategori", HTML.SelectKategori("socKategori",Utils.parseLong(getParam("socKategori")), " style=\"width:200px;\""));
			//if (mode.indexOf("doChange") != -1){
			this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian", Utils.parseLong(idKementerianKemasukan), null,"onChange=\"pilihKementerian('"+mode+"');\" style=\"width:400px\""));
			this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi", idKementerianKemasukan, Utils.parseLong(idAgensiKemasukan), "", " style=\"width:400px\""));
			//}
		
		}catch(Exception e){
			 e.printStackTrace();
			 throw new Exception(getIHTP().getErrorHTML(
					 "[MODUL HTP REKOD] MASALAH RANGKAIAN[2], SILA SEMAK RANGKAIAN DB METHOD doGetListBoxUpdate("+this.className+")"));
		
		}
		
	}
	
	public void doPaparDetailHakmilik2(Vector list) throws Exception {
//		myLog.info("doPaparDetailHakmilik2");
		try {
			String idPermohonan = getParam("idPermohonan"); //translator for idpermohonan	
			String idHakmilik = getParam("idHakmilik");
			myLog.info("doPaparDetailHakmilik2:idPermohonan="+idPermohonan+",idHakmilik="+idHakmilik);
			// declared - line 92
			//mode = getParam("mode");
			idnegeri = getParam("socNegeri");
		 	iddaerah = getParam("socDaerah");
		 	idmukim = getParam("socMukim");
		 	//idJenisHakmilik = getParam("socJenisHakmilik");
		 	idKementerianKemasukan = getParam("txtIdKementerian");
		 	
		 	this.context.put("idPermohonan",idPermohonan);
			this.context.put("idHakmilik",idHakmilik);
			this.context.put("page2",true);
			this.context.put("page3",false);			
			
			System.out.println("fix bug: mode : "+mode);
			
			boolean isHTPHakmilik = frmRekodUtilData.isHTPHakmilik(idPermohonan);
			System.out.println("fix bug: isHTPHakmilik : "+isHTPHakmilik);
			
			//myLog.debug("isHTPHakmilik:"+isHTPHakmilik);
			if (mode.equals("") && isHTPHakmilik) {
				System.out.println("fix bug:no mode");
				//myLog.debug("**isHTPHakmilik**");
				context.remove("SenaraiTanah");
				//Senaraikan hakmilik
				this.context.put("page2",true);
				this.context.put("page3",false);
				vm = PATH+"pendaftaran/frmPendaftaranTerimaHakmilikSenarai.jsp";
				HtpPermohonan  maklumatFail = getIPermohonan().getPermohonanInfo("",idPermohonan);
				//myLog.info(getSemakSubUrusan(String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdSubUrusan())));
				semakSubUrusan = getSemakSubUrusan(String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdSubUrusan()));
				
				list = frmRekodUtilData.getCarianSenaraiHakmilikRizabById(idPermohonan, null, null, null, null, null, null, null, null, null, null, null, null, null);
				this.context.put("SenaraiTanah", list);
				
//			}else if (mode.indexOf("doChange") != -1) {
//				myLog.debug("mode=doChange");
//				//CATER FOR ALL DO CHANGES
//				doGetListBox();
//			   	getValuesAndAssignToContext();					
//				if (mode.equals("doChangeKodLuas")) {
//					//RESET LUAS
//					this.context.put("txtLuas","");
//					this.context.put("txtLuas1","");
//				}
			   	//this.context.put("mode",mode);
			   	
				//Diguna semasa Tambah Hakmilik Permohonan	
			} else if (mode.equals("addHakmilik")) {
				//myLog.debug(" **addHakmilik**");
				this.context.put("page2",false);
				this.context.put("page3",true);
				this.context.put("readOnly", "");
			 	this.context.put("disabled", "");
				//this.context.put("mode", "addHakmilik");
			 	mode = "addHakmilik";
				emptyContext();
				initContextValue();
				doInitMaklumatFail(idPermohonan);
				doInitNegeriDaerahMukim(idPermohonan);
				
				/* syaz:
				if (isHTPHakmilik) {
					//INIT HTPHAKMILIK
					doInitMaklumatHakmilik("byIdPermohonan",idPermohonan);
					//Clearkan No Hakmilik
				}*/
				
				//fix bug. syaz. 17112014
				this.context.put("readonly", "");
				
			//Langkah Ketiga (3)
			} else if (mode.equals("doAddHakmilik")) { 
				//myLog.debug(" **doAddHakmilik**");
				idHakmilik = doInsertDetailHakmilik2();
				//VIEW SEMULA HAKMILIK 
 				readOnly = "readonly";
				disabled = "disabled";
				style = readOnly + " class="+disabled+" ";
				hakmilik = getIHakmilik().getHakmilik(idHakmilik);
					
				doInitMaklumatFail(String.valueOf(hakmilik.getIdPermohonan()));
				doInitMaklumatHakmilik(null,idHakmilik);
				this.context.put("readOnly",readOnly);
			 	this.context.put("disabled",disabled);	
				//this.context.put("mode", "doView");
			 	mode = "doView";
				this.context.put("idHakmilik",idHakmilik);
			// Langkah Ketiga (4)
			} else if (mode.equals("update") || mode.equals("kemaskini")) { 
				//myLog.debug(" update||kemaskini");
				readOnly = "";
				disabled = "";
				style = "";
				this.context.put("page2",false);
				this.context.put("page3",true);

				//String idHakmilik = getParam("idHakmilik");
				doInitMaklumatHakmilik(mode,idHakmilik);
				//this.context.put("mode", "update");
			 	//mode = "update";
			 
				doGetListBoxUpdate();
			   	getValuesAndAssignToContext();					
				if (getParam("INS_UPD").equals("doChangeKodLuas")) {
					myLog.info("doChangeKodLuas");
					//RESET LUAS					
					this.context.put("txtLuas",""); 	// value luas persamaan
					//this.context.put("txtLuas1","");
					this.context.put("txtLuasLama","");	// value luas lama
				
				}
				this.context.put("INS_UPD", "update");
				this.context.put("readOnly",readOnly);
				this.context.put("disabled",disabled);	
				
				//fix bug. syaz. 17112014
				this.context.put("readonly", "");
				
			} else if (mode.equals("view")) { 
 				readOnly = "readonly";
				disabled = "disabled";
				style = readOnly + " class=\""+disabled+"\" "+disabled;
				this.context.put("page2",false);
				this.context.put("page3",true);
				hakmilik = getIHakmilik().getHakmilik(idHakmilik);
				
//				HtpPermohonan  maklumatFail = getIPermohonan().getPermohonanInfo("",String.valueOf(hakmilik.getIdPermohonan()));
//				if(getSemakSubUrusan(String.valueOf(maklumatFail.getPermohonan().getPfdFail().getIdSubUrusan())) == false){
//					throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] SILA KEMASKINI MAKLUMAT SUB URUSAN BAGI FAIL YANG DIPILIH TERLEBIH DAHULU"));
//				}

				doInitMaklumatFail(String.valueOf(hakmilik.getIdPermohonan()));
				doInitMaklumatHakmilik(null,idHakmilik);
				this.context.put("readOnly",readOnly);
			 	this.context.put("disabled",disabled);	
				//this.context.put("mode", "doView");
			 	//mode = "view";
				this.context.put("idHakmilik",idHakmilik);
				 
			} else if (mode.equals("updateHakmilik")) { 
				myLog.info("updateHakmilik");
				// menguunakan mode = updateHakmilik
				this.context.put("page2",false);
				this.context.put("page3",true);
				this.context.put("readOnly", "");
			 	this.context.put("disabled", "");
				//this.context.put("mode", "update");
			 	//mode = "update";
				//NOW GET ALL THE DATA
				//doInitMaklumatFail(idPermohonan);
				//modified 20/08/2010
				view_modeMaklumatFail(idHakmilik);
				doInitMaklumatHakmilik(null,idHakmilik);
				
			} else if (mode.equals("update_")) {
				//myLog.debug(" **update**");
				this.context.put("page2",true);
				this.context.put("page3",true);
				vm = PATH+"pendaftaran/frmPendaftaranTerimaHakmilikSenarai.jsp";
				list = frmRekodUtilData.getCarianSenaraiHakmilikRizabById(idPermohonan, null, null, null, null, null, null, null, null, null, null, null, null, null);
				this.context.put("SenaraiTanah", list);

			//Diguna semasa Tambah Hakmilik, Tambah Hakmilik Permohonan (Pilih Negeri)	
			} else {
				System.out.println("fix bug: default mode : "+mode);
				//myLog.debug(" ** NEW FORM **"+idPermohonan);
				initContextValue();
				doGetListBox();
				doInitMaklumatFail(idPermohonan);
				
				if (mode.indexOf("doChange") != -1){
					doGetListBox();
					getValuesAndAssignToContext();					
				}
				
				mode = "view";
				this.context.put("readOnly", "");
			 	this.context.put("disabled", "");
				//this.context.put("mode", "addHakmilik");
				this.context.put("page2",true);
				this.context.put("page3",false);
				this.context.put("txtLuas","");
				this.context.put("txtLuas1","");
				this.context.put("INS_UPD", "add");
			
				//fix bug. syaz. 17112014
				this.context.put("readonly", "");
				
			
//				myLog.info("PenHakmilik view");
//			    String readability = "readonly";
//			    String disability = "disabled";
//			    String style2 = "none";
//			    String style1 = "none";
//			    HttpSession session = this.request.getSession();
//			    DataPenHamilik(session, disability, readability,style1,style2);
//			    	this.context.put("selesaiBean", hInfo);
				this.context.put("mode", "disabled='disabled'");
				this.context.put("classDis", "class='disabled'");
				this.context.put("pagemode", "view");
				
				myLog.info("PenHakmilik view");
			    String readability = "readonly";
			    String disability = "disabled";
			    String style2 = "none";
			    String style1 = "none";
			    HttpSession session = this.request.getSession();
				DataPenHamilik(session, disability, readability,style1,style2);
				
				
			}
			myLog.info("\nmode = "+mode);
			this.context.put("mode", mode);

		} catch (Exception e) {
			 e.printStackTrace();
			 throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] MASALAH RANGKAIAN[3], SILA SEMAK RANGKAIAN DB METHOD doPaparDetailHakmilik2(ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod)"));

		}
		
	}
	
	public void getValuesAndAssignToContext() {
		System.out.println("fix bug : getValuesAndAssignToContext");
		String name="";String value="";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements(); ) {
	        name = (String)allparam.nextElement();
	        value = request.getParameter(name);
	        if (!"mode".equals(name)) {
	        	if(name.equals("txtLuas1"))
	        		this.context.put("txtLuasLama",value);	
	        	else if(name.equals("txtLuasLama"))
	        		this.context.put("luas",value);	
	        	else	
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
			 hHakmilikUpdate.put("socKodJenisHakmilikHR",frmRekodUtilData.getKodJenisHakmilik(getParam("socJenisHakmilik")));

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
			 hHakmilikUpdate.put("txtFailKJP", getParam("txtFailKJP"));	
			 
			 
			 hHakmilikUpdate.put("idMasuk",userId);	
			 //Tambah pada 2010/07/09
			 hHakmilikUpdate.put("noBangunan", getParam("txtNoBangunan"));	
			 hHakmilikUpdate.put("noTingkat", getParam("txtNoTingkat"));	
			 hHakmilikUpdate.put("noPetak", getParam("txtNoPetak"));	
			 hHakmilikUpdate.put("catatan", getParam("txtKemAgenTerkini"));	
			 
			 //AZAM ADD TRANSACTION HERE
			 /*
			 idHakmilik = FrmUtilData.insertHTPHakmilik(hHakmilikUpdate);
			 FrmUtilData.insertHakmilikPerihal(hHakmilikUpdate,idHakmilik);
			 FrmUtilData.insertHakmilikAgensi(hHakmilikUpdate,idHakmilik);
			 FrmUtilData.insertHakmilikCukai((String)hHakmilikUpdate.get("txtCukaiTahun"),
					 						  userId,idHakmilik);
		 	 */
			 idHakmilik = FrmUtilData.insertHTPHakmilikTransaction(hHakmilikUpdate,userId);
//			 myLog.info("Hakmilik:"+getIHakmilik().getKodJenisHakmilik(getParam("socJenisHakmilik")));
//			 myLog.info("Lot:"+getParam("socLot"));
//			 myLog.info("Lot 1:"+getKodLot().getKod(getParam("socLot")));
			 String noTanah = getIHakmilik().getKodJenisHakmilik(getParam("socJenisHakmilik"))+getParam("txtNoHakmilik")+ ", "+getKodLot().getKod(getParam("noLot"))+getParam("txtNoLot");
			 AuditTrail.logActivity("1", idSeksyen, this, sessionExt, "INS", "HAKMILIK/RIZAB  ["+noTanah+"] DITAMBAH ");
			 
			 // Kemaskini Fail perolehan
			 htpPermohonan = getIPermohonan().getPermohonanInfo("",getParam("idPermohonan"));
			 // PERMOHONAN TANAH
			 simpanKemaskiniFail("42",langkah);
			 	 
		 } catch (SQLException se) {
			 throw new Exception(se.getMessage());
		 }
		 catch (Exception e) {
			 //e.printStackTrace();
			 //throw new Exception("Ralat:"+e.getMessage());
			 throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] 2681, "+e.getMessage()));
		 }
		 return idHakmilik;
	}
	
	public void dokemaskiniDetailHakmilik() throws Exception {
		//myLog.debug("** dokemaskiniDetailHakmilik ** ");
		//hakmilik = getIHakmilik().kemaskiniHakmilikCatatan(getParam("idHakmilik"), "CATATAN ROSLI");
		try {
			if ("update".equals(mode)) {
				 //Update Hakmilik - must match with db field
				 String idHakmilik = getParam("idHakmilik");
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
				 hHakmilikUpdate.put("cukai",Utils.RemoveComma(getParam("txtCukaiTahun")));
				 hHakmilikUpdate.put("lokasi",	      getParam("txtLokasi"));	
				 
				 //table perihal
				 //hHakmilikUpdate.put("kegunaan_tanah",getParam("txtKegunaanTanah"));
				 hHakmilikUpdate.put("luas",getParam("txtLuasGabung"));
				 hHakmilikUpdate.put("luas_bersamaan",Utils.RemoveComma(getParam("txtLuas")));	
				 hHakmilikUpdate.put("id_luas",getParam("socLuas"));
				 
				 //hHakmilikUpdate.put("status_rizab",getParam("statusRizab"));	
				 
				 hHakmilikUpdate.put("id_rizab",getParam("socJenisRizab"));	
				 //hHakmilikUpdate.put("no_warta",	getParam("txtNoWarta"));	
				 hHakmilikUpdate.put("no_rizab",	getParam("txtNoWarta"));	
				 hHakmilikUpdate.put("tarikh_warta",getParam("txdTarikhWarta"));	
				 hHakmilikUpdate.put("kawasan_rizab",getParam("txtKawasanRizab"));	
				 hHakmilikUpdate.put("syarat",	      getParam("txtSyarat"));	
				 hHakmilikUpdate.put("sekatan",	getParam("txtSekatan"));	
				 hHakmilikUpdate.put("no_pelan",	getParam("txtNoPelan"));	
				 hHakmilikUpdate.put("no_syit",	getParam("txtNoSyit"));	
				 hHakmilikUpdate.put("no_pu",	      getParam("txtNoPu"));	
				 hHakmilikUpdate.put("no_fail_kjp",	      getParam("txtFailKJP"));	
				 hHakmilikUpdate.put("kegunaan_tanah",getParam("txtKegunaanTanah"));
				 hHakmilikUpdate.put("id_agensi",idAgensiKemasukan);	
				 hHakmilikUpdate.put("id_kementerian",idKementerianKemasukan);
				 
				 //id kategori
				 hHakmilikUpdate.put("id_kategori",getParam("socKategori"));
				 hHakmilikUpdate.put("id_subkategori",FrmUtilData.getSubCategory((String)getParam("socKategori")));
				 hHakmilikUpdate.put("catatan",getParam("txtKemAgenTerkini"));
				 
				 String ph = FrmUtilData.getKodNegeri(idnegeri);
				 ph += FrmUtilData.getKodDaerah(iddaerah);
				 ph += FrmUtilData.getKodMukim(idmukim);
				 ph += FrmUtilData.getKodJenisHakmilik(String.valueOf(getParam("socJenisHakmilik")));
				 //comment on 07/06/2010
				 //ph += String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtNoHakmilik"))));
				 ph += getParam("txtNoHakmilik");
				 if(!getParam("txtNoBangunan").equals("") && !getParam("txtNoTingkat").equals("") && !getParam("txtNoPetak").equals("")){	
					ph += (String)getParam("txtNoBangunan")+(String)getParam("txtNoTingkat")+(String)getParam("txtNoPetak");
				 }	
				 hHakmilikUpdate.put("pegangan_hakmilik", ph);
				 hHakmilikUpdate.put("id_negeri", idnegeri); 
				 hHakmilikUpdate.put("id_daerah", iddaerah); 
				 hHakmilikUpdate.put("id_mukim", idmukim); 
				 //FrmUtilData.updateHTPHakmilik("TBLHTPHAKMILIK",hHakmilikUpdate,idHakmilik,null);
				 FrmUtilData.updateHakmilik("TBLHTPHAKMILIK",hHakmilikUpdate,idHakmilik,null);
				 
				 hakmilikAgensi = new HakmilikAgensi();
				 hakmilikAgensi.setIdHakmilik(Long.parseLong(idHakmilik));
				 hakmilikAgensi.setIdHakmilikAgensi(Long.parseLong(idAgensiKemasukan));
				 hakmilikAgensi.setIdKementerian(Long.parseLong(idKementerianKemasukan));
				 hakmilikAgensi.setIdKemaskini(Long.parseLong(userId));
				 
				 hakmilikAgensi = getIHakmilik().kemaskiniHakmilikAgensi(hakmilikAgensi);
			
				 String noTanah = getIHakmilik().getKodJenisHakmilik(getParam("socJenisHakmilik"))+getParam("txtNoHakmilik")+ ", "+getKodLot().getKod(getParam("noLot"))+getParam("txtNoLot");
				 AuditTrail.logActivity("1", idSeksyen, this, sessionExt, "UPD", "HAKMILIK/RIZAB  ["+noTanah+"] DIKEMASKINI ");

				 // Kemaskini Fail perolehan
				 htpPermohonan = getIPermohonan().getPermohonanInfo("",getParam("idPermohonan"));
				 // PERMOHONAN TANAH
				 simpanKemaskiniFail("42",langkah);

				 /*
				 Hashtable hHakmilikPerihal = new Hashtable();
				 hHakmilikPerihal.put("kegunaan_tanah",getParam("txtKegunaanTanah"));
				 FrmUtilData.updateHTPHakmilik("TBLHTPHAKMILIKPERIHAL",hHakmilikPerihal,idHakmilik);
				 */
				 
				 //REMOVED UPDATING CUKAI. HANDLE DI REKOD
				 /*
				 Hashtable hHakmilikCukai = new Hashtable();
				 hHakmilikCukai.put("cukai",Utils.RemoveComma(getParam("txtCukaiTahun")));
				 hHakmilikCukai.put("cukai_terkini",Utils.RemoveComma(getParam("txtCukaiTahun")));
				 FrmUtilData.updateHTPHakmilik("TBLHTPHAKMILIKCUKAI",hHakmilikCukai,idHakmilik," AND STATUS_PELARASAN IS NULL ");
				*/
				 
				 //REFRESH DATA
				 readOnly = "readonly";
				 disabled = "disabled";
				 style = readOnly + " class=\""+disabled+"\" "+disabled;
				 doInitMaklumatHakmilik(null,idHakmilik);
			
				 //this.context.put("mode", "doView");
				 this.context.put("mode", "view");
				 this.context.put("INS_UPD", "doView");
				 this.context.put("idHakmilik",idHakmilik);
				 
			} else {
				myLog.debug("VIEW MODE");
				readOnly = "";
				disabled = "";
				style = "";
				String idHakmilik = getParam("idHakmilik");
				doInitMaklumatHakmilik(null,idHakmilik);
				this.context.put("mode", "update");
				this.context.put("INS_UPD", "update");
			
			}		 
			this.context.put("readOnly",readOnly);
			this.context.put("disabled",disabled);	
		
		}catch (Exception e) {
			 //throw new Exception("Ralat:"+e.getMessage());
			 throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] 2818, "+e.getMessage()));

		}

	}
	
	// Maklumat Rizab untuk Kemaskini
	private void viewMaklumatRizab(HakMilik hakmilik, String display) throws Exception {	
		myLog.info("viewMaklumatRizab");
		try{		
			// Dikemaskini pada 19/07/2013
			//htpPermohonan = getIPembelian().findPermohonan(String.valueOf(hakmilik.getIdPermohonan()), "");
			htpPermohonan = getIPermohonan().getPermohonanInfo("",String.valueOf(hakmilik.getIdPermohonan()));
			this.context.put("txtFailPTD",hakmilik.getNoFailPTD());
			this.context.put("txtFailPTG",hakmilik.getNoFailPTG());
			this.context.put("txtTajuk",htpPermohonan.getPermohonan().getPfdFail().getTajukFail());
			this.context.put("txtNamaKementerian",HTML.SelectKementerian("txtIdKementerian"
				, hakmilik.getAgensi().getKementerian().getIdKementerian()
				, null,"onChange=\"doChangeKementerianRizabEdit()\"  class=\""+display+"\" "+display+" style=\"width:400px\""));
			this.context.put("txtNamaAgensi",HTML.SelectAgensiByKementerian("txtIdAgensi"
				, String.valueOf(hakmilik.getAgensi().getKementerian().getIdKementerian())
				, hakmilik.getAgensi().getIdAgensi(), "  class=\""+display+"\" "+display+" ", " style=\"width:400px\""));
			this.context.put("txtNoFailSeksyen", hakmilik.getNoFail());
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",hakmilik.getNegeri().getIdNegeri()
					," onChange=\"doChangeStateRizabEdit();\" class=\""+display+"\" "+display +" style=\"width:300px\" "));	
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(String.valueOf(hakmilik.getNegeri().getIdNegeri())
					, "socDaerah", hakmilik.getDaerah().getIdDaerah(),""
					," onChange=\"doChangeDaerahRizabEdit();\" class=\""+display+"\" "+display+" style=\"width:300px\" "));
			iddaerah = String.valueOf(hakmilik.getDaerah().getIdDaerah());
			this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah
					,"socMukim",hakmilik.getMukim().getIdMukim()," class=\""+display+"\" "+display+" style=\"width:300px\" "));
			this.context.put("idHakmilik", hakmilik.getIdHakmilik());			
			this.context.put("txtFailKJP", htpPermohonan.getNoRujukanKJP());
			this.context.put("txtNoWarta", hakmilik.getNoWarta());
	        this.context.put("selectLot", HTML.SelectLot("socLot",hakmilik.getIdJenisLot()," style=\"width:200px\" class=\""+display+"\" "+display));
			this.context.put("txtNoLot", hakmilik.getNoLot());	
			this.context.put("selectLuas", HTML.SelectLuas("socLuas",hakmilik.getIdLuas(), " style=\"width:200px\""," pilihLuas()"));
			this.context.put("socjenisrizab", getIUtilPilihan().Pilihan("socjenisrizab"
					,String.valueOf(hakmilik.getIdJenisRizab()), " class=\""+display+"\" "+display+" style=\"width:200px\"" ));
			this.context.put("txdTarikhTerima",lebah.util.DateTool.getDateTimeFormatted(hakmilik.getTarikhTerima(), "dd/MM/yyyy"));	
			this.context.put("txdTarikhWarta",lebah.util.DateTool.getDateTimeFormatted(hakmilik.getTarikhWarta(), "dd/MM/yyyy"));	
			this.context.put("idPermohonan", hakmilik.getIdPermohonan());	
			this.context.put("txtLokasi",hakmilik.getLokasi() );	
			this.context.put("txtKegunaanTanah", hakmilik.getKegunaan());	
			this.context.put("txtIdLuasLama",hakmilik.getIdLuas());	
			this.context.put("txtLuasLama", hakmilik.getLuasString());	
			this.context.put("txtLuasGabung", hakmilik.getLuasString());	
			this.context.put("socLuas",hakmilik.getIdLuas());	
			this.context.put("txtLuas",Utils.formatLuas(hakmilik.getLuasBersamaan()));	
			this.context.put("txtNoPelan",hakmilik.getNoPelan());	
			this.context.put("txtNoSyit", hakmilik.getNoSyit());
			this.context.put("txtNoPu", hakmilik.getNoPU());	
			this.context.put("statusSah", hakmilik.getStatusSah());
			this.context.put("pegawaiAkhir", hakmilik.getNamaKemaskini());
			this.context.put("txdTarikhKemaskini",lebah.util.DateTool.getDateTimeFormatted(hakmilik.getTarikhKemaskini(),"dd/MM/yyyy"));
			this.context.put("changeLuas",submit);
			this.context.put("txtKemAgenTerkini",hakmilik.getCatatan());

		}catch(Exception e){
			e.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML(" [MODUL HTP REKOD] MASALAH RANGKAIAN[2], SILA SEMAK RANGKAIAN DB METHOD doGetListBox(ekptg.view.htp.FrmPendaftaranHakmilikRizabRekod)"));
		
		}
		
	}
	
	//14/10/2010
	private void setLuas(String strIdLuas, String strLuas){
		String luas = "0";
		String luas1 = "0";
		String luas2 = "0";
	
		if(strIdLuas.equals("1") || strIdLuas.equals("2") || strIdLuas.equals("3") || strIdLuas.equals("5") || strIdLuas.equals("6")){
			if(strIdLuas.equals("1")){
				if(strLuas.contains("KM"))
					luas = strLuas.substring(0, (strLuas.length()-2));
				else
					luas = strLuas;
				
			}else if(strIdLuas.equals("2")){
				if(strLuas.contains("H"))
					luas = strLuas.substring(0, (strLuas.length()-1));
				else
					luas = strLuas;
				
			}else if(strIdLuas.equals("3")){
				if(strLuas.contains("MP"))
					luas = strLuas.substring(0, (strLuas.length()-2));
				else{
					if(strLuas.contains("M"))
						luas = strLuas.substring(0, (strLuas.length()-1));
					else
						luas = strLuas;
				}
				
			}else if(strIdLuas.equals("5")){
				if(strLuas.contains("KP"))
					luas = strLuas.substring(0, (strLuas.length()-2));
				else{
					if(strLuas.contains("K"))
						luas = strLuas.substring(0, (strLuas.length()-1));
					else
						luas = strLuas;
				}
				
			}else if(strIdLuas.equals("6")){
				if(strLuas.contains("P"))
					luas = strLuas.substring(0, (strLuas.length()-1));
				else
					luas = strLuas;
				
			}
			//log.info("viewMaklumatRizab:submit="+btnSubmit+":luas="+luas);
	
		}else if(strIdLuas.equals("4")){
			//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
			if(strLuas.contains("E,") && strLuas.contains("R,")){
				luas = strLuas.substring(0,strLuas.indexOf("E,"));
				luas1 = strLuas.substring(strLuas.indexOf("E,")+2,strLuas.indexOf("R,"));
				luas2 = strLuas.substring(strLuas.indexOf("R,")+2,(strLuas.length()-1));
			
			}
			
		}else if(strIdLuas.equals("8")){
			//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+strLuas);
			if(strLuas.contains("R,") && strLuas.contains("J,")){
				luas = strLuas.substring(0,strLuas.indexOf("R,"));
				luas1 = strLuas.substring(strLuas.indexOf("R,")+2,strLuas.indexOf("J"));
				luas2 = strLuas.substring(strLuas.indexOf("J,")+2,(strLuas.length()-1));
			}
			
		}else{ //7||9 (TIADA SAMPLE DATA)
			luas = strLuas;
			
		}
		
		this.context.put("txtLuasLama1", luas1.trim());	
		this.context.put("txtLuasLama2", luas2.trim());	
		this.context.put("txtLuasLama", luas);	
		
	}
	
	private void simpanKemaskiniFail(String idSubUrusanDef,String langkah) throws Exception{
		//idSubUrusan = idSubUrusanDef; // Sub Urusan LAIN-LAIN RIZAB
		if(getStatus().getInfoStatusPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()),langkah)
			 == null){
			myLog.info("getStatus():INSERT="+htpPermohonan+","+getParam("idPermohonan"));
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(
					langkah,String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()),"=");
			
			if(setIdSuburusanstatus == 0L){
				throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] SILA KEMASKINI MAKLUMAT STATUS/ SUBURUSAN "
						+htpPermohonan.getPermohonan().getIdPermohonan() +", "+htpPermohonan.getPermohonan().getPfdFail().getNoFail()));
			}
			statusPermohonan = new Tblrujsuburusanstatusfail();
			statusPermohonan.setIdPermohonan(htpPermohonan.getPermohonan().getIdPermohonan());
			statusPermohonan.setIdFail(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			statusPermohonan.setAktif("1");
			statusPermohonan.setIdMasuk(Long.parseLong(userId));
			statusPermohonan.setIdKemaskini(Long.parseLong(userId));
			statusPermohonan.setIdSuburusanstatus(setIdSuburusanstatus);
			statusPermohonan.setUrl("-");
		 	getStatus().simpanKemaskiniStatus(statusPermohonan,setIdStatus);
		 
		}else{
			myLog.info("getStatus():UPDATE="+htpPermohonan+","+getParam("idPermohonan"));
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(
			langkah,String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()),"=");
			
			if(setIdSuburusanstatus == 0L){
				throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] SILA KEMASKINI MAKLUMAT STATUS/ SUBURUSAN "
						+htpPermohonan.getPermohonan().getIdPermohonan()+", "+htpPermohonan.getPermohonan().getPfdFail().getNoFail()));
			}
			statusPermohonan = new Tblrujsuburusanstatusfail();
			statusPermohonan.setIdPermohonan(htpPermohonan.getPermohonan().getIdPermohonan());
			statusPermohonan.setIdFail(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			statusPermohonan.setAktif("1");
			statusPermohonan.setIdMasuk(Long.parseLong(userId));
			statusPermohonan.setIdKemaskini(Long.parseLong(userId));
			statusPermohonan.setIdSuburusanstatus(setIdSuburusanstatus);
			statusPermohonan.setUrl("-");
			getStatus().kemaskiniStatus(statusPermohonan);
		 
		}
	
	}
	
	private boolean getSemakSubUrusan(String str){
		boolean returnValue = false;
		if(str.equals("42")){
			returnValue = true;
    	}else if(str.equals("1")){
    		returnValue = true;
       	}else if(str.equals("2")){
    		returnValue = true;
       	}else if(str.equals("3")){
    		returnValue = true;
       	}else if(str.equals("4")){
    		returnValue = true;
       	}else if(str.equals("5")){
    		returnValue = true;
       	}else if(str.equals("6")){
    		returnValue = true;
       	}else if(str.equals("16")){
    		returnValue = true;
       	}else if(str.equals("19")){
    		returnValue = true;
       	}else if(str.equals("20")){
    		returnValue = true;
       	}else if(str.equals("21")){
    		returnValue = true;
       	}else if(str.equals("22")){
    		returnValue = true;
       	}else if(str.equals("23")){
    		returnValue = true;
       	}else if(str.equals("25")){
    		returnValue = true;
    	//Penswastaan 	
       	}else if(str.equals("8")){
       		returnValue = true;
      	}else if(str.equals("9")){
      		returnValue = true;
      	}else if(str.equals("10")){
      		returnValue = true;
      	}else if(str.equals("11")){
    		returnValue = true;
      	}else if(str.equals("12")){
      		returnValue = true;
      	}else if(str.equals("62")){
    		returnValue = true;
		}else {
    		returnValue = false;
   		}
		return returnValue;
		
	}

	private IPembelian getIPembelian(){
	if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	}
	
	private HakmilikInterface getIHakmilik(){
		if (iHakmilik==null){
			iHakmilik=new HakmilikBean();
		}
		return iHakmilik;
	}

	private IKod getKodLot(){
		if(iKod== null)
			iKod = new KodLotBean();
		return iKod;
	}
		
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}		  
	
	private IHTPFail getIHTPFail(){
		if (iHTPFail==null){
			iHTPFail = new HTPFailBean();
		}
		return iHTPFail;
	}
	
	private IHakmilikRizabPengesahanTanah getIPengesahan(){
		if (iPengesahan==null){
			iPengesahan = new FrmHakmilikRizabPengesahanTanahBean();
		}
		return iPengesahan;
	}
	
	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;
			
	}
	
	private IHTPPermohonan getIPermohonan(){
		if(iPermohonan==null){
			iPermohonan = new HTPPermohonanBean();
		}
		return iPermohonan;
			
	}
	
	private IUtilHTMLPilihan getIUtilPilihan(){
		if(iUtilPilihan == null){
			iUtilPilihan = new UtilHTMLPilihanJenisRizabBean();
		}
		return iUtilPilihan;
			
	}
	
	private ITanahDaftar getIDaftar(){
		if(iTanahDaftar == null){
			iTanahDaftar = new FrmTanahDaftarRizabBean();
		}
		return iTanahDaftar;
			
	}
	
	private void DataPenHamilik(HttpSession session, String disability, String readability, String style1, String style2) throws Exception {		
		Vector list = new Vector();
		Hashtable h = null;
		list.clear();
		try{
			this.context.remove("PenHamilik");
			FrmGadaianPenHamilikData gadaianData = new FrmGadaianPenHamilikData();
			String idPermohonan = getParam("idPermohonan");

			//list = FrmGadaianPenHamilikData.getPenHakmilik();
			list= (Vector)gadaianData.getPendaftaranHakmilik(idPermohonan);

			this.context.remove("selectJenisHakmilik");
			if(list.size() != 0){
			//if(h != null){
				String luasLama = "";
				String luasBersamaan = "";
				String noLot = "";
				String NoJofa = "";
				h = (Hashtable) list.get(0);
				if(this.mode.equalsIgnoreCase("kemaskini")){				
				    myLog.info("DataPenHamilik : kemaskini :: " + h);
					this.context.put("PenHamilik", list);				    
				    //fir 07022010
					 this.context.put("selectKementerian",getKementerian(Long.parseLong((String)h.get("IdKementerian"))));
					    this.context.put("selectAgensi",getAgensi(Long.parseLong((String)h.get("IdAgensi"))));
					    //this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
					    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" disabled=\"disabled\" class=\"disabled\" class=\"mediumselect\" "));
				    
					    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					    
					    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));				    
					    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
							this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),Long.parseLong(String.valueOf(h.get("IdJenishakmilik")))," disabled=\"disabled\" class=\"disabled\"  style='width:200px;'"));
					    
					    }else{
					    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", idJenisHakmilik, " disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));   
					    }
					    //this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString())," disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					    //this.context.put("selectLuas",HTML.SelectLuas("socLuas",Long.parseLong(h.get("IdLuas").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
					    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
					String socLuas = getParam("socLuas");
		    		context.put("socLuas", socLuas);
		    		luasLama = getParam("Luas");
		    		luasBersamaan = getParam("luasBersamaan");
				    noLot = getParam("txtNoLot");	
				    NoJofa = (String)h.get("NoJofa");

				    //fir test 04042010
//				    String tajukFail = h.get("TajukFail").toString();
//				    myLog.info("test1" + tajukFail);
//				    
//				    String SOC = "";
//				    
//				    if(tajukFail.contains("SATISFACTION OF CHARGE")){
//				    	SOC = "fileSOC";
//				    }
//				    
//				    this.context.put("SOC", SOC);
				    this.context.put("modeSoc", disability);
				    this.context.put("mode", readability);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				    this.context.put("pagemode", "kemaskini");
				    this.context.put("classDis", "");
				    
				    this.context.put("disabled", disability);
				    //this.context.put("readonly", "");
				   
				    
					
				}else{					
					this.context.put("PenHamilik", list);
				    h = (Hashtable) list.get(0);			    
				    myLog.info("DataPenHamilik : else kemaskini ::" + h);

				    this.context.put("selectKementerian",getKementerian(Long.parseLong((String)h.get("IdKementerian"))));
				    this.context.put("selectAgensi",getAgensi(Long.parseLong((String)h.get("IdAgensi"))));
				    //this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
				    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", Long.parseLong(h.get("IdNegeri").toString()) ,"", " onchange=\"doChangeDaerahKemaskini()\" disabled=\"disabled\" class=\"disabled\" class=\"mediumselect\" "));
			    
				    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong(h.get("IdDaerah").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    
				    this.context.put("selectMukim",HTML.SelectMukimByDaerah(h.get("IdDaerah").toString(), "socMukim", Long.parseLong(h.get("IdMukim").toString()), "disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));				    
				    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Long.parseLong(h.get("IdJenishakmilik").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
						this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),Long.parseLong(String.valueOf(h.get("IdJenishakmilik")))," disabled=\"disabled\" class=\"disabled\"  style='width:200px;'"));
				    
				    }else{
				    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", idJenisHakmilik, " disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));   
				    }
				    //this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    this.context.put("selectLot",HTML.SelectLot("socLot",Long.parseLong(h.get("IdLot").toString())," disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    //this.context.put("selectLuas",HTML.SelectLuas("socLuas",Long.parseLong(h.get("IdLuas").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;' "));
				    this.context.put("selectRizab",HTML.SelectRizab("socRizab",Long.parseLong(h.get("IdRizab").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    this.context.put("selectKategori",HTML.SelectKategori("socKategori",Long.parseLong(h.get("IdKategori").toString()),"disabled=\"disabled\" class=\"disabled\" style='width:200px;'"));
				    luasLama = (String)h.get("luas");				    
				    luasBersamaan = (String)h.get("luasBersamaan");				    
				    noLot = (String)h.get("NoLot");		
				    NoJofa = (String)h.get("NoJofa");	
				    
				    this.context.put("modeSoc", disability);
				    this.context.put("mode", readability);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				    this.context.put("pagemode", "simpan");
				    String classDis = "class=\"disabled\" ";
				    this.context.put("classDis", classDis);					
				    this.context.put("disabled", disability);
				    this.context.put("readonly", classDis);
				    
				}
				this.context.put("txtLuas", luasBersamaan);
				this.context.put("txtLuas1", luasLama);
				this.context.put("NoLot", noLot);
				this.context.put("NoJofa", NoJofa);

			}else{
				//myLog.info("GadaianProcess::DataPenHamilik:: list.size() = "+list.size());
				//list.clear();
				//int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
				//FrmGadaianPenHamilikData.setPenHakmilikBaru(idPermohonan);
				list = (Vector)gadaianData.getPendaftaranHakmilikBaru(idPermohonan);
				this.context.put("PenHamilik", list);
				myLog.info("DataPenHamilik:else:: list = "+list);
			    //Hashtable 
				h = (Hashtable) list.get(0);
			    
			    //test fir
			    String tajuk = h.get("TajukFail").toString();
			    myLog.info("test12 : " + tajuk);
			    String SOC = "";
			    if(tajuk.contains("SATISFACTION OF CHARGE")){
			    	SOC = "SOC";
			    }
			    
			    this.context.put("SOC", SOC);
			    this.context.put("TarikhLepasGadai", "");
			    
			    //myLog.info("fir DataPenHamilik list 0 : " + h);
			    
//			    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("IdKementerian").toString()),"disabled"));
//			    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("IdAgensi").toString()),"disabled"));
//			    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("IdNegeri").toString()),"disabled"));
			    
			    this.context.put("selectKementerian",getKementerian(Long.parseLong(h.get("IdKementerian").toString())));
			    this.context.put("selectAgensi",getAgensi(Long.parseLong(h.get("IdAgensi").toString())));
			    //this.context.put("selectNegeri",getNegeri(h.get("IdNegeri").toString()));
			    this.context.put("selectNegeri",HTML.SelectNegeri("selectNegeri", " onchange=\"doChangeDaerahKemaskini()\" class=\"mediumselect\" "));
			    
			    this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(h.get("IdNegeri").toString(), "socDaerah", Long.parseLong("0"), null, " onchange=\"doChangeDaerah()\" class=\"mediumselect\" "));
			    this.context.put("selectMukim",HTML.SelectMukimByDaerah("", "socMukim", " class=\"mediumselect\" "));
			    
//			    this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik"));
			    if(Integer.parseInt(String.valueOf(h.get("IdNegeri")))==13 || Integer.parseInt(String.valueOf(h.get("IdNegeri")))==12){
					this.context.put("selectJenisHakmilik",utilHTML.PilihJenisHakmilikMengikutNegeri("socJenisHakmilik",String.valueOf(h.get("IdNegeri")),0L," style='width:200px;'"));
			    
			    }else{
			    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong("0"), "style='width:200px;'"));   
			    }
//			    this.context.put("selectLot",HTML.SelectLot("socLot"));
			    this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong("0"), "style='width:200px;'"));
//			    this.context.put("selectLuas",HTML.SelectLuas("socLuas"));
//			    this.context.put("selectLuas",HTML.SelectLuas("socLuas", Long.parseLong("0"), "style='width:200px;'"));
//			    this.context.put("selectRizab",HTML.SelectRizab("socRizab"));
			    this.context.put("selectRizab",HTML.SelectRizab("socLuas", Long.parseLong("0"), "style='width:200px;'"));
//			    this.context.put("selectKategori",HTML.SelectKategori("socKategori"));
			    this.context.put("selectKategori",HTML.SelectKategori("socKategori", Long.parseLong("0"), "style='width:200px;'"));
			    this.context.put("modeSoc", "");
			    this.context.put("mode", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			    this.context.put("pagemode", "baru");
			    this.context.put("classDis", "");
			}
		
		}catch(Exception ex){
//			myLog.error("GadaianProcess::DataPenHamilik::Exception = "+ex);
			ex.printStackTrace();
		}
		
	}
	public String getAgensi(Long idagensi){
		
	    Vector v = null;
	    Tblrujagensi a = null;
	    String agensiName = "";
		try {
			v = new Vector();
			v = DB.getAgensi();
			for(int i = 0; i < v.size(); i++){
				a = (Tblrujagensi)v.get(i);
				if(a.getIdAgensi().equals(idagensi)){
					agensiName = a.getNamaAgensi();
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return agensiName;
		
	}
	
	public String getKementerian(Long idKementerian){
		
	    Vector v = null;
	    Tblrujkementerian k = null;
	    String namaKementerian = "";
		try {
			v = new Vector();
			v = DB.getKementerian();
			k = (Tblrujkementerian)v.get(0);
			
			for(int i = 0; i < v.size(); i++){
				k = (Tblrujkementerian)v.get(i);
				if(k.getIdKementerian().equals(idKementerian)){
					namaKementerian = k.getNamaKementerian();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
		return namaKementerian;
		
	}
	
	private void kemaskiniSimpanStatusSelesai(String idFail,String idPermohonan,String idSubUrusan,String langkah)throws Exception {
		 try {
			 Tblrujsuburusanstatusfail subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			myLog.info("idPermohonan:"+idPermohonan);
			subUrusanStatusFail.setIdPermohonan(Long.parseLong(idPermohonan));
			myLog.info("idFail:"+idFail);
			subUrusanStatusFail.setIdFail(Long.parseLong(idFail));
			subUrusanStatusFail.setAktif("0");
		
			Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah,idSubUrusan,"=");
			myLog.info("setIdSuburusanstatus:"+setIdSuburusanstatus);
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			myLog.info("getParam(\"txtKeterangan\"):"+getParam("txtKeterangan"));
			subUrusanStatusFailN.setUrl(getParam("txtKeterangan") == null ? "" : getParam("txtKeterangan"));
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
			myLog.info("getParam(\"txdTarikhTerimaSelesai\"):"+getParam("txdTarikhTerimaSelesai"));
			subUrusanStatusFailN.setTarikhMasuk(new Date(getParam("txdTarikhTerimaSelesai")));
			getIHTP().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail, subUrusanStatusFailN,getParam("txdTarikhTerimaSelesai"));
			
		} catch (Exception e) {
			throw new Exception("Ralat FrmGadaian[554]:"+e.getCause());
			
		}
	}
	
	public void statusView(String idPermohonan) throws Exception{			
		Hashtable hInfo = null; 		
		try{
			hInfo = new Hashtable();
			hInfo = getIHTP().getInfoSelesaiPermohonan(idPermohonan);				
			if(hInfo == null){					
				//hInfo.put("tarikhSelesai", "");
				//hInfo.put("keterangan", "");
				//geranInfo.addElement(hashGeran);					
				this.context.put("selesaiBean", hInfo);
				this.context.put("mode", "");
			    this.context.put("classDis", "");
			    this.context.put("pagemode", "baru");
				
			}else if(this.mode.equalsIgnoreCase("selesaikemaskini")){
				this.context.put("selesaiBean", hInfo);
				this.context.put("mode", "");
				this.context.put("classDis", "");
				this.context.put("pagemode", "kemaskini");
					
			}else{					
				this.context.put("selesaiBean", hInfo);
				this.context.put("mode", "disabled='disabled'");
				this.context.put("classDis", "class='disabled'");
				this.context.put("pagemode", "view");
					
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	
	}	
	
	public String getNegeri(String idnegeri){
		
	    Vector v = null;
	    Tblrujnegeri n = null;
		try {
			v = new Vector();
			v = DB.getNegeri(idnegeri);
			n = (Tblrujnegeri)v.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
		return n.getNamaNegeri();
		
	}
	

}

package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujdokumen;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.ppt.BantahanAgensiDaftar;
import ekptg.model.ppt.BantahanAgensiDaftarOperations;
import ekptg.model.ppt.BantahanDaftar;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8BorangFData;
import ekptg.model.ppt.PPTHeader;

public class FrmBantahanAgensiPemohonSenaraiCarian  extends AjaxBasedModule {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2134293724087904664L;
	static Logger myLogger = Logger.getLogger(FrmBantahanAgensiPemohonSenaraiCarian.class);
	BantahanAgensiDaftar model = new BantahanAgensiDaftar();	
	BantahanAgensiDaftarOperations modelOperations = new BantahanAgensiDaftarOperations();
	BantahanDaftar modelBantahanPB = new BantahanDaftar();	
	FrmUPTSek8BorangFData modelBorangE = new FrmUPTSek8BorangFData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	String checkedsbcBantahan1 = "";
	String checkedsbcBantahan2 = "";
	String checkedsbcBantahan3 = "";
	String checkedsbcBantahan4 = "";
	String checkedsorKeputusanMahkamah1 = "";
	String checkedsorKeputusanMahkamah2 = "";
	String checkedsorKeputusanMahkamah3 = "";
	String checkedsorKeputusanMahkamah4 = "";
	String checkedStatusPemulangan1 = "";
	String checkedStatusPemulangan2 = "";
	String checkedStatusPemulangan3 = "";
	String checkedStatusPemulangan4 = "";
	
	public String doTemplate2() throws Exception{
    	HttpSession session = this.request.getSession();   	
    	String vm = "";    	
    	// PPT-39 ii String jenisDoc = getParam("jenisDoc");
    	Vector listHeader = null;
    	Vector list = null;    	
    	Vector listA = null;
    	Vector listB = null;
    	Vector listC = null;
    	Vector listD = null;
    	Vector listE = null;
    	Vector listF = null;
    	Vector listG = null;
    	Vector listH = null;
    	Vector listJ = null; 
    	Vector listK = null;
    	Vector getDataBorangO = null;
    	Vector view_details_dokumen = null;    	
    	Vector listDokumen = null;    	
    	Vector dataBorangE = new Vector();
    	Vector dataNamaPengarah = new Vector();    	
    	Vector dataSuburusanHakmilik = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector listPageDepan = new Vector();
    	Vector listPageNoLot = new Vector();  	
    	// PPT-35(i)	Vector semakanSenarai = new Vector();
    
    	dataNamaPengarah.clear();
    	listPageDepan.clear();
    	listPageNoLot.clear();
    	
    	Db dbx = null;
		try {
			dbx = new Db();			
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik", dbx) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik","Skrin Capaian Hakmilik", "EKPTG - PPT", dbx);
			}
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupCarianPB", dbx) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.SkrinPopupCarianPB","Skrin Capaian PB", "EKPTG - PPT", dbx);
			}
			
		} finally {
			if (dbx != null)
				dbx.close();
		}
    	
   		context.put("nama_skrin",getParam("nama_skrin"));	//upload
    	String doPost = (String)session.getAttribute("doPost");
    	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command");
    	myLogger.info("SUBMIT ="+submit);
    	this.context.put("Util",new lebah.util.Util());	// UNTUK FORMAT UTIL.DECIMAL (EX: 12,000.00)
    	//get user login detail
		String usid = usid = (String)session.getAttribute("_ekptg_user_id");  
    	userData(usid);
    	
    	String userIdNeg = userData(usid); 
   	   	String id_fail = getParam("id_fail");	
   		context.put("id_fail", id_fail);
   		
   		String id_permohonan = getParam("id_permohonan");
   		context.put("id_permohonan", id_permohonan);
   		
   		if(id_permohonan!=null){
	   		Vector list_sub = null;
			list_sub = header.listPerjalananFail(id_permohonan);
			this.context.put("list_sub_header", list_sub);
   		}
   		
   		String id_hakmilik = getParam("id_hakmilik");
   		context.put("id_hakmilik", id_hakmilik);
   		
   		String status_bantahan_ap = getParam("status_bantahan_ap");
   		context.put("status_bantahan_ap", status_bantahan_ap);
   		
		listHeader = modelBantahanPB.getHeader(id_fail,id_permohonan); 
		context.put("Header", listHeader);
		String idNegeriMhn = "";
		if (listHeader.size()!=0){
			Hashtable h = (Hashtable) listHeader.get(0);
			idNegeriMhn = (String)h.get("id_negeri");	
		}
		myLogger.info("-------------- idNegeriMhn :"+idNegeriMhn);		
		//GET NO SIASATAN
		listH = model.getNoSiasatanAP(id_permohonan,id_hakmilik);
		context.put("getNoSiasatan", listH);
		String id_siasatan = "";
		if(listH.size()!=0){
			Hashtable b = (Hashtable) listH.get(0);
			id_siasatan = (String)b.get("id_siasatan");	
			context.put("id_siasatan", id_siasatan);
		}	//	END GET NO SIASATAN 		
		//GET MAX NO WARTA
		listJ = model.getNoWarta(id_permohonan);
		context.put("getNoSiasatan", listJ);
		String id_warta = "";
		if(listJ.size()!=0){
			Hashtable e = (Hashtable) listJ.get(0);
			id_warta = (String)e.get("id_warta");	
			context.put("id_warta", id_warta);
		}		
		//END GET MAX NO WARTA 			
		Hashtable statusFail = modelBantahanPB.getStatusFail(id_permohonan);	
		this.context.put("statusFail", statusFail);
		
		String selectedtab = getParam("selectedtab");
		if ("".equals(selectedtab)){
			selectedtab = "0";
		}
		this.context.put("selectedtab", selectedtab);			
		//CLEARKAN FORM
		String key = "";
		String value = "";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements();) {
		key = (String) allparam.nextElement();
		value = request.getParameter(key);
		this.context.put(key, value);
		}
		//END CLEARKAN FORM     			
		// HEADER
		String negeriMMK = "";
		String idkementerian = "";
    	header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_fail = (String)dh.get("id_fail");
			negeriMMK = (String)dh.get("id_projekNegeri");	
			idkementerian = (String)dh.get("id_kementerian");	
			
		}		
		// GET NAMA PENGARAH
	    String nama_pengarah = "";
	    modelUPT.setNamaPengarah(negeriMMK);
	    dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    }	    
	    context.put("nama_pengarah",nama_pengarah);
	    
		// get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		modelUPT.setDataSuburusanHakmilik(id_hakmilik);
		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
		if(dataSuburusanHakmilik.size()!=0){
			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
		} 		
		
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(id_permohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}			    
    	// SCREEN JSP
		String skrinListDepan = "app/ppt/frmBantahanAgensiPemohonSenaraiCarian.jsp";
		String skrinDaftarBantahanMasterAP = "app/ppt/frmBantahanMasterAP.jsp";
		String skrinDepositAP = "app/ppt/frmBantahanDepositAP.jsp"; 
				
    	if("DaftarBantahanAP".equals(submit)){    		
        	//try{        		
    		String status_bantahan = getParam("status_bantahan_ap");
        	System.out.println("status---"+status_bantahan);
        	if(status_bantahan.equals("220")){
//        		System.out.println("masuk");
        		list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan, id_warta);
        		context.put("getMaklumatBantahan", list);
        			
        		if (list.size()!=0 ){
//        			System.out.println("masuk---1");
        			Hashtable a = (Hashtable) list.get(0);
        			String idNegeri = (String)a.get("id_negeri");
        			String idBandar = (String)a.get("id_bandar");
        			String jenis_pembantah = (String)a.get("jenis_pembantah");
        			String flag_penerima_pampasan = (String)a.get("flag_penerima_pampasan");
        			String flag_bahagian_pampasan = (String)a.get("flag_bahagian_pampasan");
        			String flag_ukur_luas = (String)a.get("flag_ukur_luas");
        			String flag_pampasan = (String)a.get("flag_pampasan");
        			String desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
        			
        			context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
        			context.put("selectHakmilik", HTML.SelectHakmilikByAP(id_hakmilik, "socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
        			context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
//        				context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah",Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));				
        			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
        			context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
//        				context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,"socPihakBantah", Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));								
        			if (jenis_pembantah.equals("1")){
        				myLogger.info("masuk--2");
        				setValueJenisPembantah("1","");
        			}else{
        				myLogger.info("masuk--3");
        				setValueJenisPembantah("","2");
        			}    				
        			if (flag_penerima_pampasan.equals("Y")){
        				myLogger.info("masuk--4");
        				setValueBantahanTerhadap("checked","","","");
        				context.put("TEMPchecked1", checkedsbcBantahan1);
        
        			}
        			if (flag_bahagian_pampasan.equals("Y")){
        				myLogger.info("masuk--5");
        				setValueBantahanTerhadap("","checked","","");
        				context.put("TEMPchecked2", checkedsbcBantahan2);
        
        			}
        			if (flag_ukur_luas.equals("Y")){
        				myLogger.info("masuk--6");
        				setValueBantahanTerhadap("","","checked","");
       					context.put("TEMPchecked3", checkedsbcBantahan3);
       
        			}
        			if (flag_pampasan.equals("Y")){
        				myLogger.info("masuk--7");
        				setValueBantahanTerhadap("","","","checked");
        				context.put("TEMPchecked4", checkedsbcBantahan4);
        
        			}        				
        			//GET PAMPASAN AMAUN YG DITAWARKAN
        			listA = model.getMaklumatPampasanAP(id_hakmilik);
    				context.put("getMaklumatPampasan", listA);
    				//END  				
    				//GET TARIKH AWARD & TARIKH BORANG H
    				listF = model.getTarikhPentingAP(id_permohonan,id_hakmilik,id_siasatan);
        			context.put("getTarikhPentingAP",listF);
        			//END
        			myLogger.info("masuk--8");
        			context.put("flag","semak");
        			context.put("mode", "disabled");
        			context.put("clearForm", "");
        			context.put("button", "view");
        			vm = skrinDaftarBantahanMasterAP; 
        			myLogger.info("vmmm::"+vm);
        										
        		}else{
        			context.put("status",true);
        			myLogger.info("masuk--9");        
        		}
        		vm = skrinDaftarBantahanMasterAP; 
        		
        	}else if(status_bantahan.equals("")){
//        		System.out.println("masuk sini");
    			context.put("selectHakmilik",HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik","style=width:auto tabindex=5 disabled "));
    			context.put("selectNoLot",HTML.SelectNoLotByHakmilik(id_hakmilik,"socNoLot","style=width:auto tabindex=7 disabled "));
    			
    			//GET ADD OF AGENSI PEMOHON
    			Hashtable getAddAgensi = model.getAddAgensi(id_permohonan,id_hakmilik);	
    			String id_kementerian = getAddAgensi.get("id_kementerian").toString();
    			String nama_kementerian = getAddAgensi.get("nama_kementerian").toString();
    			String alamat1 = getAddAgensi.get("alamat1").toString();
    			String alamat2 = getAddAgensi.get("alamat2").toString();
    			String alamat3 = getAddAgensi.get("alamat3").toString();
    			String poskod = getAddAgensi.get("poskod").toString();
    			String id_negeri = getAddAgensi.get("id_negeri").toString();
    			String nama_negeri = getAddAgensi.get("nama_negeri").toString();
    			context.put("txtIdKementerian", id_kementerian);
    			context.put("txtNamaPembantah", nama_kementerian);
    			context.put("txtAlamat1", alamat1);
    			context.put("txtAlamat2", alamat2);
    			context.put("txtAlamat3", alamat3);
    			context.put("txtPoskod", poskod);
    			context.put("txtIdNegeri",id_negeri);
    			context.put("txtNamaNegeri", nama_negeri);
        		//END GET ADD OF AGENSI PEMOHON
    			
				//GET MAKLUMAT PAMPASAN YG DITAWARKAN	  			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);		
    			if(list.size()>0){
    				myLogger.info("masuk sini 1");
    				Hashtable b = (Hashtable) list.get(0);	
    				String id_bantahan = b.get("id_bantahan").toString();
				
    				//CHECKING JUMLAH PAMPASAN SEKSYEN 8
    				listA = model.getMaklumatPampasanAP(id_hakmilik);
    				context.put("getMaklumatPampasan", listA);
    				
    				if(listA.size()!=0){
    					myLogger.info("masuk sini 2");
						Hashtable d = (Hashtable) listA.get(0);
						double amaun_bayaran = Double.parseDouble(d.get("amaun_bayaran").toString());
						myLogger.info("amaun_bayaran >>> "+amaun_bayaran);
						//PAMPASAN <= 15000.00 : ALASAN 3 DAN 4 
						if(amaun_bayaran <= 15000.00){
							context.put("syarat","bawah");
							context.put("alasan1","1");
							context.put("alasan2","2");
							context.put("alasan3","");
							context.put("alasan4","");
						}else{
							myLogger.info("Track Amaun 'else' >>> "+amaun_bayaran);
							context.put("syarat","");
							context.put("alasan1","");
							context.put("alasan2","");
							context.put("alasan3","");
							context.put("alasan4","");					
						}
				
    				}
				//END	
    			}
    
       		}else{
       			context.put("syarat","");
				context.put("alasan1","");
				context.put("alasan2","");
				context.put("alasan3","");
				context.put("alasan4","");	

       		}
        	
        	//GET TARIKH AWARD & TARIKH BORANG H
    		listF = model.getTarikhPentingAP(id_permohonan,id_hakmilik,id_siasatan);
    		context.put("getTarikhPenting",listF);
    			
        			
          }else if("block_bantahan".equals(submit)){     		
    	   		context.put("selectHakmilik",HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik","style=width:auto disabled tabindex=5 onChange=\"doChangeNoLot();\" "));
        		context.put("selectNoLot",HTML.SelectNoLotByHakmilik(id_hakmilik,"socNoLot","style=width:auto disabled tabindex=7 "));
//        		context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah", null, "style=width:auto disabled tabindex=11", null));    
//        		context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan, "socPihakBantah", Utils.parseLong(id_pihakberkepentingan), "style=width:auto disabled"));
    		
        		vm = "app/ppt/frmBantahanPapar.jsp";      		

        	
          }else if("cariNoHakmilik".equals(submit)){      		
        	  String carianNoHakmilik = getParam("carianNoHakmilik");
        	  context.put("carianNoHakmilik", carianNoHakmilik.trim());
        		
        	  String carianNoLot = getParam("carianNoLot");
        	  context.put("carianNoLot", carianNoLot.trim());
        		
        		//listPageNoLot = model.setCarianNoHakmilik(id_permohonan,carianNoHakmilik,carianNoLot);    		
         		//context.put("getSenaraiNoLot", listPageNoLot);
         		//context.put("list_size", listPageNoLot.size());
     
        	  context.put("clearForm", "");
        		
        	  vm = "app/ppt/frmBantahanAgensiSenaraiPB.jsp";
        	
          }else if("papar_pb".equals(submit)){       		
        	  listPageNoLot = model.getSenaraiNoLot(id_permohonan);
        		//context.put("getSenaraiNoLot", listPageNoLot);
        		//context.put("list_size", listPageNoLot.size());
        		//setupPageNoLot(session,action,listPageNoLot);
        	  context.put("list_size", model.getSenaraiNoLot_count(id_permohonan));       				
        	  context.put("clearForm", "yes");
        		
        	  vm = "app/ppt/frmBantahanAgensiSenaraiPB.jsp"; 
          		          		
          }else if("add_bantahanAP".equals(submit)){	
    			
        	  if (doPost.equals("true")){				    				
    				// INSERT TBLPPTBANTAHAN & INSERT TBLPPTSUBURUSANSTATUSBANTAHAN & UPDATE TBLPPTHAKMILIK
    				add_bantahanAP(usid);
    				
    				context.put("flag","semak");
    				//context.put("mode", "disabled");
    				//context.put("clearForm", "");
    				context.put("button", "view");	
    				System.out.println("button---");
    				
    				// UPDATE TBLPPTPERMOHONAN
    				updateStatusDalamProses(id_permohonan,usid);
    				
    				// UPDATE TBLPPTSUBURUSANHAKMILIK
    				updateSuburusanHakmilik(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);
    				
    				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
    				updateSuburusanStatusFailPPT(session,id_permohonan,id_fail,id_suburusanstatusfailppt);   
    				
        	  }
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan, id_warta);
    			context.put("getMaklumatBantahan", list);
    			if (list.size()!=0 ){				
    				Hashtable a = (Hashtable) list.get(0);
    				String idNegeri = (String)a.get("id_negeri");
    				String idBandar = (String)a.get("id_bandar");
    				String jenis_pembantah = (String)a.get("jenis_pembantah");
    				String flag_penerima_pampasan = (String)a.get("flag_penerima_pampasan");
    				String flag_bahagian_pampasan = (String)a.get("flag_bahagian_pampasan");
    				String flag_ukur_luas = (String)a.get("flag_ukur_luas");
    				String flag_pampasan = (String)a.get("flag_pampasan");
    				String desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
    			
    				context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
    				context.put("selectHakmilik", HTML.SelectHakmilikByAP(id_hakmilik, "socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
    				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
//    				context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah",Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));				
    				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
    				context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
//    				context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,"socPihakBantah", Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));								
    				if (jenis_pembantah.equals("1")){
    					setValueJenisPembantah("1","");
    				}else{
    					setValueJenisPembantah("","2");
    				}
    				
    				if (flag_penerima_pampasan.equals("Y")){
    					setValueBantahanTerhadap("checked","","","");
    					context.put("TEMPchecked1", checkedsbcBantahan1);
    				}
    				if (flag_bahagian_pampasan.equals("Y")){
    					setValueBantahanTerhadap("","checked","","");
    					context.put("TEMPchecked2", checkedsbcBantahan2);
    				}
    				if (flag_ukur_luas.equals("Y")){
    					setValueBantahanTerhadap("","","checked","");
    					context.put("TEMPchecked3", checkedsbcBantahan3);
    				}
    				if (flag_pampasan.equals("Y")){
    					setValueBantahanTerhadap("","","","checked");
    					context.put("TEMPchecked4", checkedsbcBantahan4);
    				}
    				
					//GET PAMPASAN AMAUN YG DITAWARKAN
    				listA = model.getMaklumatPampasanAP(id_hakmilik);
					context.put("getMaklumatPampasan", listA);
					//END
    				
    				//GET TARIKH AWARD & TARIKH BORANG H
    				listF = model.getTarikhPentingAP(id_permohonan,id_hakmilik,id_siasatan);
    				context.put("getTarikhPentingAP",listF);
    				//END
    				
    				context.put("flag","semak");
    				//context.put("mode", "disabled");
    				context.put("clearForm", "");
    				context.put("button", "view");				
    										
    			}else{
    				context.put("status",true);
    			}
    			
   			vm = skrinDaftarBantahanMasterAP; 
   			
          }else if(("dalamProses".equals(submit)) || ("bantahan".equals(submit))){	   			
        	  String jenisDoc = "bantahan";
        	  selectedtab = "0";
        	  context.put("selectedtab", selectedtab);
        	  context.put("idWarta", id_warta);

        	  //CHECKING JUMLAH PAMPASAN SEKSYEN 8
        	  listA = model.getMaklumatPampasanAP(id_hakmilik);
        	  if(listA.size()!=0){
        		  Hashtable d = (Hashtable) listA.get(0);
        		  double amaun_bayaran = Double.parseDouble(d.get("amaun_bayaran").toString());	
        		  //PAMPASAN <= 3000.00 : ALASAN 3 DAN 4 
        		  if(amaun_bayaran <= 3000.00){
        			  context.put("syarat","bawah");
        			  context.put("alasan1","1");
        			  context.put("alasan2","2");
        			  context.put("alasan3","");
        			  context.put("alasan4","");
    				
    				}else{
    					context.put("syarat","");
    					context.put("alasan1","1");
    					context.put("alasan2","2");
    					context.put("alasan3","3");
    					context.put("alasan4","4");					
    				
    				}
        		  
        	  }
        	  //END
    			
        	  list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
        	  context.put("getMaklumatBantahan", list);
        	  if (list.size()!=0 ){				
    				Hashtable b = (Hashtable) list.get(0);
    				String idNegeri = b.get("id_negeri").toString();
    				String jenis_pembantah = b.get("jenis_pembantah").toString();
    				String id_bantahan = (String)b.get("id_bantahan");
    				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
    				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
    				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
    				String flag_pampasan = (String)b.get("flag_pampasan");
    				String flag_online = (String)b.get("flag_online");
    				String id_status_bantahan = (String)b.get("status_bantahan_ap");
    				
    				if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    		     		listDokumen = modelBantahanPB.senaraiDokumenBantahan(id_bantahan);
    		    		context.put("listDokumen", listDokumen);
    		    		context.put("listDokumen_size", listDokumen.size());	    		
    				}else{				
    					context.put("listDokumen", "");
    					context.put("listDokumen_size", 0);
    				}
    				
    				if(id_hakmilik!=""){    					
    					context.put("selectHakmilik", HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
    					context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
   					
    					if (jenis_pembantah.equals("1")){
    						setValueJenisPembantah("1","");
    					}else{
    						setValueJenisPembantah("","2");
    					}
    					
    					if (flag_penerima_pampasan.equals("Y")){
    						setValueBantahanTerhadap("checked","","","");
    						context.put("TEMPchecked1", checkedsbcBantahan1);
    					}
    					
    					if (flag_bahagian_pampasan.equals("Y")){
    						setValueBantahanTerhadap("","checked","","");
    						context.put("TEMPchecked2", checkedsbcBantahan2);
    					}
    					
    					if (flag_ukur_luas.equals("Y")){
    						setValueBantahanTerhadap("","","checked","");
    						context.put("TEMPchecked3", checkedsbcBantahan3);
    					}
    					
    					if (flag_pampasan.equals("Y")){
    						setValueBantahanTerhadap("","","","checked");
    						context.put("TEMPchecked4", checkedsbcBantahan4);
    					
    					}
    					
    					//flag and id untuk permohonan online
    					context.put("flag_online",flag_online);
    					context.put("id_status_bantahan",id_status_bantahan);
    					context.put("id_bantahan",id_bantahan);
    					
    					//GET TARIKH AWARD & TARIKH BORANG H
        				listF = model.getTarikhPentingAP(id_permohonan,id_hakmilik,id_siasatan);
        				context.put("getTarikhPenting",listF);
        				
        				if (listF.size()!=0 ){				
            				Hashtable a = (Hashtable) listF.get(0);
            				String txdTkhBrgH = (String)a.get("txdTkhBrgH");
            				//System.out.println("tarikh penting---"+txdTkhBrgH);
        				}
    					//END        				
    					context.put("flag", "semak");
    					context.put("mode", "disabled");
    					context.put("button", "view");
    					context.put("clearForm","");
    					
    				}else{
    					context.put("status",true);					
    				}		
    				
        	  }else{
        		  context.put("status",true);
        	  }			       		
        	  // PPT-35 (i) Bantahan Terhadap untuk Agensi
        	  Vector<Hashtable<String,String>> semakanBantahan = FrmSemakan.getSenaraiSemakan("pptbantahan");
        	  this.context.put("semakanBantahan", semakanBantahan);

        	  // Checkbox PPT-35 (i) Jenis Bantahan Pampasan untuk frmBantahanMaster.jsp
        	  Vector<Hashtable<String,String>> semakanPampasan = FrmSemakan.getSenaraiSemakan("pptpampasan"); // Cari pada TBLSEMAKANSENARAI pada KOD_FORM
        	  context.put("semakanPampasan", semakanPampasan);
        	  
        	  context.put("semakanclass", new FrmSemakan());

        	  vm = skrinDaftarBantahanMasterAP;		
    			
          }else if ("kemaskiniBantahan".equals(submit)){		
        	  selectedtab = "0";
        	  context.put("selectedtab", selectedtab);
    			
        	  //CHECKING JUMLAH PAMPASAN SEKSYEN 8
        	  listA = model.getMaklumatPampasanAP(id_hakmilik);
        	  if(listA.size()!=0){
        		  Hashtable d = (Hashtable) listA.get(0);
        		  double amaun_bayaran = Double.parseDouble(d.get("amaun_bayaran").toString());
        		  //PAMPASAN <= 15000.00 : ALASAN 3 DAN 4 
        		  if(amaun_bayaran <= 15000.00){
        			  context.put("syarat","bawah");
        			  context.put("alasan1","1");
        			  context.put("alasan2","2");
        			  context.put("alasan3","");
        			  context.put("alasan4","");
    				
        		  }else{
        			  context.put("syarat","");
        			  context.put("alasan1","");
        			  context.put("alasan2","");
        			  context.put("alasan3","");
        			  context.put("alasan4","");					
    				
        		  }
    				
        		  // PPT-35 (i) Bantahan Terhadap untuk Agensi
            	  Vector<Hashtable<String,String>> semakanBantahan = FrmSemakan.getSenaraiSemakan("pptbantahan");
            	  this.context.put("semakanBantahan", semakanBantahan);

            	  // Checkbox PPT-35 (i) Jenis Bantahan Pampasan untuk frmBantahanMaster.jsp
            	  Vector<Hashtable<String,String>> semakanPampasan = FrmSemakan.getSenaraiSemakan("pptpampasan"); // Cari pada TBLSEMAKANSENARAI pada KOD_FORM
            	  context.put("semakanPampasan", semakanPampasan);
            	  
            	  context.put("semakanclass", new FrmSemakan());
            	  
    			}
    			//END
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			context.put("getMaklumatBantahan", list);			
    			if(list.size()!=0){
    				Hashtable b = (Hashtable) list.get(0);
    				String idNegeri = b.get("id_negeri").toString();
    				String jenis_pembantah = b.get("jenis_pembantah").toString();
    				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
    				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
    				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
    				String flag_pampasan = (String)b.get("flag_pampasan");
    				
    				context.put("selectHakmilik", HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled "));
    				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
    				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
    				if (jenis_pembantah.equals("1")){
    					setValueJenisPembantah("1","");
    				}else{
    					setValueJenisPembantah("","2");
    				}
    				
    				if (flag_penerima_pampasan.equals("Y")){
    					setValueBantahanTerhadap("checked","","","");
    					context.put("TEMPchecked1", checkedsbcBantahan1);
    				}
    				if (flag_bahagian_pampasan.equals("Y")){
    					setValueBantahanTerhadap("","checked","","");
    					context.put("TEMPchecked2", checkedsbcBantahan2);
    				}
    				if (flag_ukur_luas.equals("Y")){
    					setValueBantahanTerhadap("","","checked","");
    					context.put("TEMPchecked3", checkedsbcBantahan3);
    				}
    				if (flag_pampasan.equals("Y")){
    					setValueBantahanTerhadap("","","","checked");
    					context.put("TEMPchecked4", checkedsbcBantahan4);		
    				}
    			}
    			
    			//GET TARIKH AWARD & TARIKH BORANG H
    			//listF = model.getTarikhPentingAP(id_permohonan,id_hakmilik,id_siasatan);
    			context.put("getTarikhPenting",listF);
    			//END
    						
    			context.put("flag", "semak");
    			context.put("mode", "");
    			context.put("clearForm", "");
    			context.put("button", "edit");
    			
    			vm = skrinDaftarBantahanMasterAP;	
    			
          }else if (submit.equals("tolakPermohonan")){		
    			selectedtab = "0";
    			context.put("selectedtab", selectedtab);	
    			
    			//tolak permohonan
    			tolakPermohonan(session,getParam("id_bantahan"));
    			
    			String id_status_bantahan = "";
    			String flag_online = "";
    			String id_bantahan = "";
    			String jenis_pembantah = "";
    			String idNegeri = "";
    			String idBandar = "";
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);		
    			context.put("getMaklumatBantahan", list);
    			if(list.size()!=0){
    				Hashtable b = (Hashtable) list.get(0);	
    				id_bantahan = b.get("id_bantahan").toString();
    				idNegeri = (String)b.get("id_negeri");
    				idBandar = (String)b.get("id_bandar");
    				jenis_pembantah = (String)b.get("jenis_pembantah");
    				flag_online = (String)b.get("flag_online");
    				id_status_bantahan = (String)b.get("status_bantahan_ap");
    			}
				
    			context.put("flag_online",flag_online);
    			context.put("id_status_bantahan",id_status_bantahan);
    			context.put("id_bantahan",id_bantahan);
    		
				
				context.put("selectHakmilik", HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
				context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
				if (jenis_pembantah.equals("1")){
					setValueJenisPembantah("1","");
				}else{
					setValueJenisPembantah("","2");
				}
				
				context.put("flag", "semak");
				context.put("mode", "disabled");
				context.put("button", "view");
				context.put("clearForm","");	
				
    			vm = skrinDaftarBantahanMasterAP;
    		//close tolakPermohonan
        	
          }else if (submit.equals("simpanBantahan")){	
        	  selectedtab = "0";
    			context.put("selectedtab", selectedtab);
    			Db db = null;
				String NO_BANTAHAN_temp = "";
				String AMAUN_TUNTUTAN_temp = "";
    			try{				
    				list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);			
    				Hashtable b = (Hashtable) list.get(0);	
    				String id_bantahan = b.get("id_bantahan").toString();
    						
    				db = new Db();
    				Statement stmt = db.getStatement();
    				String sql = " SELECT NO_BANTAHAN,TRIM(TO_CHAR(AMAUN_TUNTUTAN,'999,999,990.99')) AS AMAUN_TUNTUTAN FROM TBLPPTBANTAHAN" +
    						"" +
    						" WHERE ID_BANTAHAN = '"+id_bantahan+"'";			
    				ResultSet rs = stmt.executeQuery(sql);	
    				myLogger.info("SQL  :"+sql);
    				while (rs.next()){				
    					NO_BANTAHAN_temp = rs.getString("NO_BANTAHAN");	
    					AMAUN_TUNTUTAN_temp = rs.getString("AMAUN_TUNTUTAN");
    			    }
//    			    AuditTrail at = new AuditTrail();
    			    AuditTrail.logActivity("","1",null,session,"UPD","BANTAHAN AGENSI [BIL. BANTAHAN : "+NO_BANTAHAN_temp+", AMAUN TUNTUTAN : RM "+AMAUN_TUNTUTAN_temp+"] KEMASKINI");
    				
    				if(id_bantahan != ""){
    					
    					//System.out.println("123xxx");
    				updateBantahan(usid,id_bantahan,idkementerian);	
    				}				
    				list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    				context.put("getMaklumatBantahan", list);
    				String idNegeri = (String)b.get("id_negeri");
    				String idBandar = (String)b.get("id_bandar");
    				String jenis_pembantah = (String)b.get("jenis_pembantah");
    				
    				context.put("selectHakmilik", HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
    				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
//    				context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah",Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));				
    				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
    				context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
//    				context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,"socPihakBantah", Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));								
    				if (jenis_pembantah.equals("1")){
    					setValueJenisPembantah("1","");
    				}else{
    					setValueJenisPembantah("","2");
    				}
    				
    				context.put("flag", "semak");
    				context.put("mode", "disabled");
    				context.put("button", "view");
    				context.put("clearForm","");	
    				
    				vm = skrinDaftarBantahanMasterAP;	
    			
    			} catch (Exception e) {
    				throw new Exception("PENGEMASKINIAN FAIL TIDAK DAPAT DITERUSKAN.SILA CUBA LAGI. :" +e.getMessage());
    			}	
    			finally {
    				if (db != null)
    					db.close();
    			}	
    			
          }else if (submit.equals("deposit")) {
        	  selectedtab = "1";
        	  context.put("selectedtab", selectedtab);
    			
        	  list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
        	  context.put("getMaklumatBantahan", list);

        	  if (list.size()!=0){
        		  Hashtable a = (Hashtable) list.get(0);
        		  String id_bantahan = a.get("id_bantahan").toString();
        		  double amaun_tuntutan = Double.parseDouble(a.get("amaun_tuntutan").toString());
        		  double txtAmaunPampasan = Double.parseDouble(a.get("txtAmaunPampasan").toString());
        		  double txtAmaunTuntutan = Double.parseDouble(a.get("txtAmaunTuntutan").toString());
        		  myLogger.info("amaun tuntutan >>>> "+amaun_tuntutan);

        		  if(id_bantahan!=""){
    					//KIRAAN DEPOSIT = PAMPASAN YG DITAWARKAN * 10%
//    					double deposit ;
//    					deposit = 0.1 * amaun_tuntutan ;
//    					context.put("txtAmaunResit",deposit);
//    					myLogger.info("KIRAAN DEPOSIT >>>> :: "+deposit);
    					
    					//KIRAAN DEPOSIT = AMAUN TUNTUTAN * 10%
    					double deposit ;
    					deposit = 0.1 * amaun_tuntutan;
    					
    					if(deposit > 3000){
    						context.put("txtAmaunResit",3000.00);						
    					}
    					else{
    						context.put("txtAmaunResit",deposit);						
    					}					
    					
    					myLogger.info("KIRAAN DEPOSIT >>>> :: "+deposit);
    					//END    									
    					
    					//listA = model.getMaklumatPampasanAP(id_hakmilik);
    					//context.put("getMaklumatPampasan", listA);
    					
    					listB = model.getMaklumatDepositAP(id_hakmilik);
    					context.put("getMaklumatDeposit", listB);
    					if (listB.size()!=0){
    						
    						System.out.println("masuk deposit---6");
    						Hashtable c = (Hashtable) listB.get(0);
    						String cara_bayar = c.get("cara_bayar").toString();
    						String id_bank = c.get("id_bank").toString();
    						context.put("idcarabayar", cara_bayar);
    						
    						context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(cara_bayar), "style=width:auto disabled onChange=\"doChangeCaraBayaran();\" "));
    						context.put("flag","semak");
    						context.put("mode", "disabled");
    						context.put("button", "view");
    						context.put("clearForm", "");
    					}else{
    						System.out.println("masuk deposit---7");
    						context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", null, "style=width:auto onChange=\"doChangeCaraBayaran();\" "));
    						context.put("flag", "");
    						context.put("mode", "");
    						context.put("clearForm", "yes");
    						context.put("button", "add");
    					}
    					
    				}else{
    					context.put("status",true);
    				}

    			}else{

    				context.put("status",true);
    			}
    			vm = skrinDepositAP;
    			
    		}else if("doChangeCaraBayaran".equals(submit)){
    			
    			selectedtab = "1";
    			context.put("selectedtab", selectedtab);
    			
    			String idcarabayar = getParam("socCaraBayar");
    			context.put("idcarabayar", idcarabayar);

    			context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idcarabayar), "style=width:auto onChange=\"doChangeCaraBayaran();\" "));
    			
    			//GET & POST VALUE IN FIELDS CONTENTS
    			String txtAmaunPampasan = getParam("txtAmaunPampasan");
    			String txtNoCekPampasan = getParam("txtNoCekPampasan");
    			String txtNoAkaunPampasan = getParam("txtNoAkaunPampasan");
    			String txdTkhTerimaResit = getParam("txdTkhTerimaResit");
    			String txdTkhResit = getParam("txdTkhResit");
    			String txtNoResit = getParam("txtNoResit");
    			String txtAmaunResit = getParam("txtAmaunResit");
    			String txtNoCek = getParam("txtNoCek");
    			String txtNoAkaun = getParam("txtNoAkaun");
    			String txtNamaBank = getParam("txtNamaBank");
    			String txdTkhPulang = getParam("txdTkhPulang");
    			String txdTkhHantar = getParam("txdTkhHantar");
    			String txtNamaPenghantar = getParam("txtNamaPenghantar");
    			
    			
//    			listA = model.getMaklumatPampasanAP(id_hakmilik);
//    			context.put("getMaklumatPampasan", listA);			
//    			if (listA.size()!=0){
//    				Hashtable b =(Hashtable) listA.get(0);
//    				String cara_bayarPampasan = b.get("cara_bayar").toString();
//    				context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", Utils.parseLong(cara_bayarPampasan), "style=width:auto tabindex=5 disabled "));
//    			}else{
//    				context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan",null, "style=width:auto disabled"));
//    			}						
    			//END

    			context.put("flag", "");
    			context.put("mode", "");
    			context.put("clearForm", "");
    			context.put("button", "add");
    			
    			vm = skrinDepositAP;
    			
    		}else if("add_deposit".equals(submit)){
    			
    			selectedtab = "1";
    			context.put("selectedtab",selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			if (list.size()!=0){
    				Hashtable b = (Hashtable) list.get(0);
    				String id_bantahan = b.get("id_bantahan").toString();
    				String desc_status_bantahan_ap = (String)b.get("desc_status_bantahan_ap");
    				if (doPost.equals("true")){
    					
    					// UPDATE TBLPPTBANTAHAN & INSERT TBLPPTBORANGO
    					add_deposit(usid,id_bantahan);
    					
    					// UPDATE TBLPPTPERMOHONAN
    					updateStatus(session);
    					
    					// UPDATE TBLPPTSUBURUSANHAKMILIK
    					updateSuburusanHakmilik_deposit(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);
    					
    					// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
    					updateSuburusanStatusFailPPT_deposit(session,id_permohonan,id_fail,id_suburusanstatusfailppt);    					
    					
    					// UPDATE TBLPPTRUJSUBURUSANSTATUSBANTAHAN
    					deposit_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
    				}
    				
    				// UPDATE STATUS SEMASA BANTAHAN
    				context.put("desc_status_bantahan_ap",desc_status_bantahan_ap);    				
    			
    				listA = model.getMaklumatPampasanAP(id_hakmilik);
    				context.put("getMaklumatPampasan", listA);  				
    					
    				listB = model.getMaklumatDepositAP(id_hakmilik);
    				context.put("getMaklumatDeposit", listB);
    				Hashtable c = (Hashtable) listB.get(0);
    				
    				String cara_bayar = c.get("cara_bayar").toString();
    				String id_bank = c.get("id_bank").toString();
    				context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(cara_bayar), "style=width:auto disabled "));
    				context.put("selectBank", HTML.SelectBank("socBank", Utils.parseLong(id_bank), "style=width:auto disabled "));
    				context.put("flag","semak");
    				context.put("mode", "disabled");
    				context.put("button", "view");
    				
    			}else{
    				context.put("status", true);
    			}			
    			vm = skrinDepositAP;
    			
    		}else if("urusanDeposit".equals(submit)){
    			selectedtab = "1";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				String id_bantahan = a.get("id_bantahan").toString();
    				context.put("id_bantahan", id_bantahan);
    				String desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
    				context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
    				if(id_bantahan!=""){
    					
    					listA = model.getMaklumatPampasanAP(id_hakmilik);
    					context.put("getMaklumatPampasan", listA);									

    					
    					listB = model.getMaklumatDepositAP(id_hakmilik);
    					context.put("getMaklumatDeposit", listB);					
    					if (listB.size()!=0){
    						Hashtable c = (Hashtable) listB.get(0);
    						String cara_bayar = c.get("cara_bayar").toString();
    						String id_bank = c.get("id_bank").toString();
    						context.put("idcarabayar", cara_bayar);
    						context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(cara_bayar), "style=width:auto disabled ", null));
    						context.put("selectBank",HTML.SelectBank("socBank", Utils.parseLong(id_bank), "style=width:auto disabled "));
    						context.put("flag", "semak");
    						context.put("mode", "disabled");
    						context.put("button", "view");
    					}else{
    						context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", null, "style=width:auto"));
    						context.put("selectBank", HTML.SelectBank("socBank", null, "style=width:auto"));						
    						context.put("flag", "");
    						context.put("mode", "");
    						context.put("clearForm", "yes");
    						context.put("button", "add");
    					}	
    					
    				}else{
    					context.put("status",true);
    				}
    				
    			}else{
    				context.put("status",true);
    			}						
    			vm = skrinDepositAP;    			
    			
    		}else if("kemaskiniDeposit".equals(submit)){
    			
    			selectedtab = "1";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    			
    				listA = model.getMaklumatPampasanAP(id_hakmilik);
    				context.put("getMaklumatPampasan", listA);	
    				
    				listB = model.getMaklumatDepositAP(id_hakmilik);
    				context.put("getMaklumatDeposit", listB);				
    				if (listB.size()!=0){
    					Hashtable c = (Hashtable) listB.get(0);
    					String cara_bayar = (String) c.get("cara_bayar").toString();
    					String id_bank = (String) c.get("id_bank").toString();
    					context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(cara_bayar), "style=width:auto onChange=\"doChangeCaraBayaranUpdate();\" ", null));
//    					context.put("selectBank",HTML.SelectBank("socBank", Utils.parseLong(id_bank), "style=width:auto "));
    					context.put("flag", "semak");
    					context.put("mode", "disabled");
    					context.put("button", "view");
    				}else{
    					context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", null, "style=width:auto onChange=\"doChangeCaraBayaran();\" "));
//    					context.put("selectBank", HTML.SelectBank("socBank", null, "style=width:auto"));						
    					context.put("flag", "");
    					context.put("mode", "");
    					context.put("clearForm", "yes");
    					context.put("button", "add");
    				}	
    			}

    			context.put("flag","semak");
    			context.put("mode","");
    			context.put("button", "edit");
    			
    			vm = "app/ppt/frmBantahanDepositAP.jsp";
    			
    		}else if("doChangeCaraBayaranUpdate".equals(submit)){
    			
    			selectedtab = "1";
    			context.put("selectedtab", selectedtab);
    			
    			String idcarabayar = getParam("socCaraBayar");
    			context.put("idcarabayar", idcarabayar);

    			context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(idcarabayar), "style=width:auto onChange=\"doChangeCaraBayaranUpdate();\" "));
    			
    			//GET & POST VALUE IN FIELDS CONTENTS
    			String txtAmaunPampasan = getParam("txtAmaunPampasan");
    			String txtNoCekPampasan = getParam("txtNoCekPampasan");
    			String txtNoAkaunPampasan = getParam("txtNoAkaunPampasan");
    			String txdTkhTerimaResit = getParam("txdTkhTerimaResit");
    			String txdTkhResit = getParam("txdTkhResit");
    			String txtNoResit = getParam("txtNoResit");
    			String txtAmaunResit = getParam("txtAmaunResit");
    			String txtNoCek = getParam("txtNoCek");
    			String txtNoAkaun = getParam("txtNoAkaun");
    			String txtNamaBank = getParam("txtNamaBank");
    			String txdTkhPulang = getParam("txdTkhPulang");
    			String txdTkhHantar = getParam("txdTkhHantar");
    			String txtNamaPenghantar = getParam("txtNamaPenghantar");
    			
    			listA = model.getMaklumatPampasanAP(id_hakmilik);
    			context.put("getMaklumatPampasan", listA);			

    			context.put("flag", "semak");
    			context.put("mode", "");
    			context.put("clearForm", "");
    			context.put("button", "edit");
    			
    			vm = skrinDepositAP;			
    			
    		}else if(submit.equals("update_deposit")){
    			selectedtab = "1";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				String id_bantahan = (String) a.get("id_bantahan");
    				
    				Hashtable getIdBorangO = model.getIdBorangOAP(id_hakmilik,id_bantahan);	
    				String idBorangO = getIdBorangO.get("id_borango").toString();
    				
    				update_deposit(usid,id_bantahan,idBorangO);		
    				
    				listA = model.getMaklumatPampasanAP(id_hakmilik);
    				context.put("getMaklumatPampasan", listA);	
    				
    				listB = model.getMaklumatDepositAP(id_hakmilik);
    				context.put("getMaklumatDeposit", listB);				
    				if (listB.size()!=0){
    					Hashtable c = (Hashtable) listB.get(0);
    					String cara_bayar = (String) c.get("cara_bayar").toString();
    					String id_bank = (String) c.get("id_bank").toString();
    					context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(cara_bayar), "style=width:auto disabled ", null));
    					context.put("selectBank",HTML.SelectBank("socBank", Utils.parseLong(id_bank), "style=width:auto disabled "));
    				}
    			}
    				
    			context.put("flag", "semak");
    			context.put("mode","disabled");
    			context.put("button","view");
    			context.put("clearForm", "");
    			
    			vm = skrinDepositAP;
    			
    		}else if("borangO".equals(submit)){		
    			String jenisDoc = "borangO";
    			selectedtab = "2";
    			context.put("selectedtab",selectedtab);
    			
    	    	id_fail = getParam("id_fail");	
    	   		context.put("id_fail", id_fail);
    	   		
    	   		myLogger.info("ID FAIL xx :: "+id_fail);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			context.put("getMaklumatBantahan", list);
    			String id_bantahan = "";
    			
    			if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);		
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}				
    			
    			Hashtable checkingIdMahkamah = model.getCheckingIdMahkamahAP(id_hakmilik,id_bantahan);	
    			String _cIdMahkamah = "";			
    			if (checkingIdMahkamah.size()!=0){
    				_cIdMahkamah = checkingIdMahkamah.get("id_mahkamah").toString();			
    			}
    			
    			if ( _cIdMahkamah == "" ){				   				
    				String id_pejabat = getParam("socMahkamahTinggi");
    				if (idNegeriMhn!=""){
    					context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", null, "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));					
    				}else{
    					context.put("selectMahkamahTinggi", HTML.SelectMahkamah("socMahkamahTinggi", null, "style=width:auto "));
    				}
    				
    				context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeriMahkamah", null, "style=width:auto disabled "));
    				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandarMahkamah", null, "style=width:auto disabled "));				
    				context.put("mode", "");
    				context.put("clearForm", "yes");
    				context.put("flag", "");
    				context.put("button", "edit");
    				
    			}else{   				
    				myLogger.info("ADA MAKLUMAT BORANG O >>>> ");
    				Hashtable getIdBorangO = model.getIdBorangOAP(id_hakmilik,id_bantahan);	
    				String idBorangO = getIdBorangO.get("id_borango").toString();
    				
    				getDataBorangO = modelBantahanPB.getDataBorangO(idBorangO);
    				context.put("getDataBorangO", getDataBorangO);
    				
    				Hashtable b = (Hashtable) getDataBorangO.get(0);
    				String id_mahkamah = b.get("id_mahkamah").toString();
    				String id_bandar = b.get("id_bandar").toString();
    				String id_negeri = b.get("id_negeri").toString();
    				String tarikhBorangO = b.get("tarikh_borango").toString();
//    				String checking6BulanBorangO = model.checking6BulanBorangO(tarikhBorangO);
    				
    				// CONVERT STRING TO INT
//    				int BorangODiffInt = Integer.parseInt(checking6BulanBorangO);	
//    				myLogger.info("diff days...."+BorangODiffInt);
    				
    				//CHECKING DARI TARIKH TERIMA BORANG O PERLU DIPROSES DLM TEMPOH 6 BULAN
    				//CHECKING DIKIRA DARI BULAN KE-5 (140 HARI)
//    				if(BorangODiffInt > 139){					
//    					context.put("statusBorangO", "true");
//    				}else{
//    					context.put("statusBorangO", "");
//    				}
    				
    				if(!idNegeriMhn.equals("16")){
    					context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeri), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto disabled "));
    					context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
    					context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));										
    				}else{
    					myLogger.info("test .....");
    					context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(id_negeri), "style=width:auto disabled onChange=\"doChangeNegeriMahkamah();\"  "));
    					context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeri), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto disabled onChange=\"doChangeAlamatMahkamah();\" "));
    					context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
    				}
    				
    				context.put("flag", "semak");
    				context.put("mode", "disabled");
    				context.put("clearForm", "");
    				context.put("button","view");
    	
    			}			
    		
    			vm = "app/ppt/frmBantahanAgensiBorangO.jsp";
    			
    	}else if("doChangeNegeriMahkamah".equals(submit)){
    			selectedtab = "2";
    			context.put("selectedtab", selectedtab);
    			
    			String id_negeriMahkamah = getParam("socNegeriMahkamah");
    			context.put("id_negeriMahkamah", id_negeriMahkamah);
    			myLogger.info("ID Negeri Mahkamah = "+id_negeriMahkamah);
    			
    			context.put("clearForm", "");
    			context.put("mode", "");
    			context.put("flag", "");
    			context.put("button", "edit");
    			
    			context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(id_negeriMahkamah), "style=width:auto onChange=\"doChangeNegeriMahkamah();\"  "));
    			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriMahkamah), "socMahkamahTinggi", null, "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
    			context.put("selectBandarMahkamah", HTML.SelectBandar("socBandarMahkamah", null, "style=width:auto disabled "));
    			
    			vm = "app/ppt/frmBantahanAgensiBorangO.jsp";
    			    			
    			
    	}else if("doChangeAlamatMahkamah".equals(submit)){
    			try{
    			selectedtab = "2";
    			context.put("selectedtab", selectedtab);
    			
    			context.put("clearForm", "");
    			context.put("mode", "");
    			context.put("flag", "");
    			context.put("button", "edit");
    			
    			String txdTkhBrgO = getParam("txdTkhBrgO");
    			String id_pejabat = getParam("socMahkamahTinggi");
    			String id_negeriMahkamah = getParam("socNegeriMahkamah");
    			
    			if(!idNegeriMhn.equals("16")){   				
    				context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(idNegeriMhn), "style=width:auto disabled  "));
    				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_pejabat), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
    				listC = model.getAlamatMahkamah(idNegeriMhn,id_pejabat);
    			}else{
    				context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(id_negeriMahkamah), "style=width:auto onChange=\"doChangeNegeriMahkamah();\"  "));
    				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriMahkamah), "socMahkamahTinggi", Utils.parseLong(id_pejabat), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
    				listC = model.getAlamatMahkamah(id_negeriMahkamah,id_pejabat);
    			}

    			if (listC.size()!=0){
    				Hashtable a = (Hashtable) listC.get(0);
    				String idPejabatMahkamah = a.get("id_pejabat").toString();
    				String txtAlamatMahkamah1 = a.get("alamat1").toString();
    				String txtAlamatMahkamah2 = a.get("alamat2").toString();
    				String txtAlamatMahkamah3 = a.get("alamat3").toString();
    				String txtPoskodMahkamah = a.get("poskod").toString();
    				String txtNoTelMahkamah = a.get("no_tel").toString();
    				String txtNoFaxMahkamah = a.get("no_fax").toString();
    				String idBandarMahkamah = a.get("id_bandar").toString();
    				String idNegeriMahkamah = a.get("id_negeri").toString();
    				context.put("idPejabatMahkamah", idPejabatMahkamah);
    				context.put("txtAlamatMahkamah1", txtAlamatMahkamah1);
    				context.put("txtAlamatMahkamah2", txtAlamatMahkamah2);
    				context.put("txtAlamatMahkamah3", txtAlamatMahkamah3);
    				context.put("txtPoskodMahkamah", txtPoskodMahkamah);
    				context.put("txtNoTelMahkamah", txtNoTelMahkamah);
    				context.put("txtNoFaxMahkamah", txtNoFaxMahkamah);	
    				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandarMahkamah", Utils.parseLong(idBandarMahkamah), "style=width:auto disabled "));				
    			}
    						
    			} catch (Exception e) {
    				throw new Exception("MAHKAMAH YANG DIPILIH TIADA MAKLUMAT :" +e.getMessage());
    			}					
    		    			
    			vm = "app/ppt/frmBantahanAgensiBorangO.jsp";
    			
    	}else if ("simpanBorangO".equals(submit)){
    			try{
    			selectedtab = "2";
    			context.put("selectedtab", selectedtab);

    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			String desc_status_bantahan_ap = "";
    			if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = a.get("id_bantahan").toString();
    				desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
    			}else{
    				context.put("status",true);
    			}
    			
    			
    			Hashtable getIdBorangO = model.getIdBorangOAP(id_hakmilik,id_bantahan);	
    			String idBorangO = (String)getIdBorangO.get("id_borango");
    			
    			if (doPost.equals("true")){
    				
    				// UPDATE TBLPPTBORANGO
    				daftarBorangO(usid,idBorangO,id_bantahan);	
    				
    				// UPDATE TBLPPTPERMOHONAN		
    				updateStatus_borangO(session);			

    				// UPDATE TBLPPTSUBURUSANHAKMILIK
    				updateSuburusanHakmilik_borangO(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);

    				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
    				updateSuburusanStatusFailPPT_borangO(session,id_permohonan,id_fail,id_suburusanstatusfailppt);	    				
    				
    				// UPDATE TBLPPTSUBURUSANSTATUSBANTAHAN
    				urusanMahkamah_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
    			}	
    			
    			// UPDATE STATUS SEMASA BANTAHAN
    			context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
    			
    			getDataBorangO = modelBantahanPB.getDataBorangO(idBorangO);
    			context.put("getDataBorangO", getDataBorangO);
    			
    			Hashtable b = (Hashtable) getDataBorangO.get(0);
    			String id_mahkamah = (String)b.get("id_mahkamah");
    			String id_bandar = (String)b.get("id_bandar");
    			String id_negeri = (String)b.get("id_negeri");    			
    			
//    			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto disabled "));
//    			context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
//    			context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));			
//    			 			
    			if(!idNegeriMhn.equals("16")){
    				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" disabled "));
    				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
    				context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));			
    			}else{
    				myLogger.info("daerah mohon putrajaya .....");
    				context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(id_negeri), "style=width:auto onChange=\"doChangeNegeriMahkamah();\" disabled  "));
    				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeri), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" disabled "));
    				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
    			}
    			   			
    			context.put("flag", "semak");
    			context.put("mode", "disabled");
    			context.put("button", "view");
    			context.put("clearForm", "");
    			
    			} catch (Exception e) {
    				throw new Exception(" PERMOHONAN BANTAHAN TIDAK DAPAT DITERUSKAN. :" +e.getMessage());
    			}			
    			
    			vm = "app/ppt/frmBantahanAgensiBorangO.jsp";
    			
    	}else if("kemaskiniBorangO".equals(submit)){  			
    			selectedtab = "2";
    			context.put("selectedtab",selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);	
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}				
    			
    			Hashtable getIdBorangO = model.getIdBorangOAP(id_hakmilik,id_bantahan);	
    			String idBorangO = getIdBorangO.get("id_borango").toString();
    			getDataBorangO = modelBantahanPB.getDataBorangO(idBorangO);
    			context.put("getDataBorangO", getDataBorangO);
    			
    			Hashtable b = (Hashtable) getDataBorangO.get(0);
    			String id_mahkamah = (String)b.get("id_mahkamah");
    			String id_bandar = (String)b.get("id_bandar");
    			String id_negeri = (String)b.get("id_negeri");
    			
    			myLogger.info("idnegeri mohon >>"+idNegeriMhn);
    			myLogger.info("idnegeri mahkamah >>"+id_negeri);
    			
    			if(!idNegeriMhn.equals("16")){
    				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
    				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
    				context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));			
    			}else{
    				myLogger.info("daerah mohon putrajaya .....");
    				context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(id_negeri), "style=width:auto onChange=\"doChangeNegeriMahkamah();\"  "));
    				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeri), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
    				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
    			}    			
    			
//    			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
//    			context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
//    			context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));			
    			
    			context.put("flag", "semak");
    			context.put("button","edit");
    			context.put("mode", "");
    			vm = "app/ppt/frmBantahanAgensiBorangO.jsp";
    			
    	}else if(submit.equals("lanjutanTempoh")){
    		String jenisDoc = "";
    		selectedtab = "3";
    			context.put("selectedtab",selectedtab);
    			Hashtable getIdAward = model.getIdAwardAP(id_hakmilik);	
    			String id_award = "";
    			if (getIdAward.size()!=0){
    				id_award = getIdAward.get("id_award").toString();
    			}
    			context.put("id_award", id_award);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);		
    			if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				String id_bantahan = a.get("id_bantahan").toString();
    				context.put("id_bantahan", id_bantahan);
    						
    				listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
    				//listK = modelBantahanPB.getTarikhBorangG(id_siasatan);
    				
					
    				if (listA.size()!=0){
    					System.out.println("masuk----1");
    					Hashtable b = (Hashtable) listA.get(0);
    					String tarikh_lanjutan_mahkamah_ob = b.get("tarikh_lanjutan_mahkamah_ob").toString();
    					String tarikh_lanjutan_mahkamah_pt = b.get("tarikh_lanjutan_mahkamah_pt").toString();
        				context.put("tarikh_lanjutan_mahkamah_ob", tarikh_lanjutan_mahkamah_ob); 
        				context.put("tarikh_lanjutan_mahkamah_pt", tarikh_lanjutan_mahkamah_pt); 
    					System.out.println("tarikh_lanjutan_mahkamah_ob----"+tarikh_lanjutan_mahkamah_ob);
        				
        				if ((b.get("tarikh_lanjutan_mahkamah_ob").equals("")) && (b.get("tarikh_lanjutan_mahkamah_pt").equals(""))){
        				
        					System.out.println("123");
        					System.out.println("xxx");
        					context.put("clearForm", "yes");
            				context.put("mode", "");
            				context.put("flag", "");
            				context.put("button", "edit");
        				}
        				if ((!b.get("tarikh_lanjutan_mahkamah_ob").equals("")) && (!b.get("tarikh_lanjutan_mahkamah_pt").equals(""))){
            				
        					System.out.println("1234");
        					System.out.println("xxx4");
        					//context.put("getMaklumatSusulan", listA);
        				    context.put("flag", "semakBoth");
        					context.put("mode","disabledBoth");
        					//context.put("clearForm","");
        					context.put("button", "view");
        				}
        			/*
        				if ((!tarikh_lanjutan_mahkamah_ob.equals("") && (!b.get("tarikh_lanjutan_mahkamah_pt").equals("")))){
                			System.out.println("xxx");
        					context.put("clearForm", "yes");
            				context.put("mode", "");
            				context.put("flag", "");
            				context.put("button", "edit");
                			}
        				*/
        				
    					//if (listK.size()!=0)
    					{
    					//Hashtable c = (Hashtable)listK.get(0);
    					
    					//String tarikh_borangg = lebah.util.Util.getDateTime((Date)c.get("tarikh_borangg"), "dd/MM/yyyy");
//    					String tarikh_lanjutan_mahkamah_ob = lebah.util.Util.getDateTime((Date)b.get("tarikh_lanjutan_mahkamah_ob"), "dd/MM/yyyy");
//    					String tarikh_lanjutan_mahkamah_pt = lebah.util.Util.getDateTime((Date)b.get("tarikh_lanjutan_mahkamah_pt"), "dd/MM/yyyy");
//    					
    //					myLogger.info("tarikh_borangg :: "+tarikh_borangg);
    					
    					
       					if ((
       							//!tarikh_borangg.equals("") && 
       							b.get("tarikh_lanjutan_mahkamah_ob").equals(""))){
       						
       						System.out.println("xxx--1");
    						//String checkingDateTarikhLanjutanOB =  modelBantahanPB.checkingDateDiffBG(tarikh_borangg);							
    						// CONVERT STRING TO INT
    						//int LanjutanOBDateDiffInt = Integer.parseInt(checkingDateTarikhLanjutanOB);						
    					//	myLogger.info("LanjutanOBDateDiffInt >> "+LanjutanOBDateDiffInt);
    						/*if(LanjutanOBDateDiffInt >=42){
    							
    							System.out.println("xxx--2");
    							context.put("getMaklumatSusulan", listA);
    							context.put("clearForm", "yes");
        						context.put("mode", "");
        						context.put("flag", "");
        						context.put("button", "edit");
    						}else*/
    						{
    							
    							System.out.println("xxx--3");
    							context.put("flag", "semakOb");
        						context.put("mode","disabledOb");
        						context.put("clearForm","");
        						//context.put("button", "view");	
    						}	
    					
    					}else if((
    						//	!tarikh_borangg.equals("") && 
    							b.get("tarikh_lanjutan_mahkamah_pt").equals(""))){
    						
    						System.out.println("xxx--4");
    						//String checkingDateTarikhLanjutanPT =  modelBantahanPB.checkingDateDiffBG(tarikh_borangg);							
    						// CONVERT STRING TO INT
    						//int LanjutanPTDateDiffInt = Integer.parseInt(checkingDateTarikhLanjutanPT);						
    						//myLogger.info("BorangGDateDiffInt >> "+LanjutanPTDateDiffInt);
    						/*if(LanjutanPTDateDiffInt >= 180){
    							
    							System.out.println("xxx--5");
    							context.put("getMaklumatSusulan", listA);
    							context.put("clearForm", "yes");
        						context.put("mode", "");
        						context.put("flag", "");
        						context.put("button", "edit");
    						}else*/{
    							
    							System.out.println("xxx--6");
    							context.put("flag", "semakPT");
        						context.put("mode","disabledPT");
        						context.put("clearForm","");
        						//context.put("button", "view");	
    						}	
    					}else if ((
    							//!tarikh_borangg.equals("") && 
    									(!b.get("tarikh_lanjutan_mahkamah_ob").equals("") && (!b.get("tarikh_lanjutan_mahkamah_pt").equals(""))))){
    						System.out.println("xxx--7");
    							context.put("getMaklumatSusulan", listA);
    						    context.put("flag", "semakBoth");
    							context.put("mode","disabledBoth");
    							context.put("clearForm","");
    							context.put("button", "view");	
    					}
       					
    					}
//    					else if ((!tarikh_borangg.equals("") && (!b.get("tarikh_lanjutan_mahkamah_pt").equals("")))){
//    						context.put("getMaklumatSusulan", listA);
//    						context.put("flag", "semakPT");
//							context.put("mode","disabledPT");
//							context.put("clearForm","");
//							context.put("button", "view");	
//					}
    				/*	System.out.println("cccc1");
    					context.put("clearForm", "");
						context.put("mode", "disabledBoth");
						context.put("flag", "semak");
						context.put("button", "view");
						*/
    				}else{
    					
    					System.out.println("xxx--8");
    					System.out.println("cccc2");
    					context.put("flag", "semak");
						context.put("mode","disabledBoth");
						context.put("clearForm","");
						context.put("button", "edit");
    				}
    				
    				//ptt masuk sini
    				//context.put("getMaklumatSusulan", listA);
				   // context.put("flag", "semakBoth");
					//context.put("mode","disabledBoth");
					//context.put("clearForm","");
					//context.put("button", "view");
    				
    				
    			}else{	
    				context.put("status", true);  					
    			}		
    			vm = "app/ppt/frmBantahanAgensiLanjutanTempoh.jsp";	
    			
    	}else if ("kemaskiniLanjutan".equals(submit)){
//    		System.out.println("kemaskini lanjutan");
    		selectedtab = "3";
    		context.put("selectedtab", selectedtab);
    			
    		list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);			
    		if (list.size()!=0){
    			Hashtable a = (Hashtable) list.get(0);
    			String id_bantahan = a.get("id_bantahan").toString();
    				
    			listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
    			listK = modelBantahanPB.getTarikhBorangG(id_siasatan);
    				
    			Hashtable b = (Hashtable) listA.get(0);
    			if (listA.size()!=0 && (!b.get("tarikh_lanjutan_mahkamah_ob").equals("") || !b.get("tarikh_lanjutan_mahkamah_pt").equals(""))){
//    				System.out.println("masuk 1x");		
    						//Hashtable c = (Hashtable)listK.get(0);	
    				    //String tarikh_borangg = lebah.util.Util.getDateTime((Date)c.get("tarikh_borangg"), "dd/MM/yyyy");
//    					String tarikh_lanjutan_mahkamah_ob = lebah.util.Util.getDateTime((Date)b.get("tarikh_lanjutan_mahkamah_ob"), "dd/MM/yyyy");
//    					String tarikh_lanjutan_mahkamah_pt = lebah.util.Util.getDateTime((Date)b.get("tarikh_lanjutan_mahkamah_pt"), "dd/MM/yyyy");
//    					
    				if ((!b.get("tarikh_lanjutan_mahkamah_ob").equals("") || !b.get("tarikh_lanjutan_mahkamah_pt").equals(""))){
    					System.out.println("masuk 2x");
    					context.put("clearForm", "yes");
    					context.put("mode", "");
    					context.put("flag", "");
    					context.put("button", "edit");
    				}					
    				
    			}else{
    				context.put("flag", "semak");
					context.put("mode","disabled");
					context.put("clearForm","");
					context.put("button", "view");
    				
    			}	
    				
    		}else{				
    			context.put("status", true);
    		}
    		vm = "app/ppt/frmBantahanAgensiLanjutanTempoh.jsp";
    		
    		
    	}else if(submit.equals("simpanLanjutan")){				
    			selectedtab = "3";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			String desc_status_bantahan_ap = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    				desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
    			}else{
    				context.put("status", true);
    			}
    			
    			Hashtable getIdBorangO = model.getIdBorangOAP(id_hakmilik,id_bantahan);	
    			String idBorangO = getIdBorangO.get("id_borango").toString();
    			
    			if (doPost.equals("true")){
    				simpanLanjutan(usid,idBorangO,id_bantahan);
    			}
    			
    			// UPDATE STATUS SEMASA BANTAHAN
    			context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
    			listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
//    			listA = modelBantahanPB.getMaklumatSusulan(id_bantahan, jenisDoc);
    			context.put("getMaklumatSusulan",listA);
    			
    		
    			context.put("flag", "semak");
				context.put("mode","disabledBoth");
				context.put("clearForm","");
				context.put("button", "view");

    			vm = "app/ppt/frmBantahanAgensiLanjutanTempoh.jsp";
    			
    	}else if(submit.equals("susulanBantahan")){			
    		selectedtab = "4";
    		context.put("selectedtab",selectedtab);
    			
    			Hashtable getIdAward = model.getIdAwardAP(id_hakmilik);	
    			String id_award = "";
    			if (getIdAward.size()!=0){
    				id_award = getIdAward.get("id_award").toString();
    			}
    			context.put("id_award", id_award);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);		
    			if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				String id_bantahan = a.get("id_bantahan").toString();
    				context.put("id_bantahan", id_bantahan);
    				

    				//:::upload
    				if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    		     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan);
//    		     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan,);
    		    		context.put("listDokumen", listDokumen);
    		    		context.put("listDokumen_size", listDokumen.size());	    		
    				}else{				
    					context.put("listDokumen", "");
    					context.put("listDokumen_size", 0);
    				}
    				
    				Hashtable getKeteranganPampasan = model.getKeteranganPampasanAP(id_award);
    				String keterangan_pampasan = "";
    				if (getKeteranganPampasan.size()!=0){
    					keterangan_pampasan = getKeteranganPampasan.get("keterangan").toString();
    				}
    				context.put("txtKeteranganPampasan", keterangan_pampasan);
    				listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
//    				listA = modelBantahanPB.senarai_dokumen_bantahan(id_bantahan, jenisDoc);
    				if (listA.size()!=0){
    					context.put("getMaklumatSusulan", listA);
    					Hashtable b = (Hashtable) listA.get(0);
    					String keputusan_mahkamah = b.get("keputusan_mahkamah").toString();
    					String flag_pulang_deposit = b.get("flag_pulang_deposit").toString();	
    					String no_rujukan_tanah = b.get("no_rujukan_tanah").toString();	
    					myLogger.info("NO RUJUKAN TANAH >> "+no_rujukan_tanah);				
    					if (!no_rujukan_tanah.equals("")){
    						String tarikh_terimaPerintah = lebah.util.Util.getDateTime((Date)b.get("tarikhTerimaPerintah_convert"), "dd/MM/yyyy");
    						String checkingDateTerimaPerintah =  modelBantahanPB.checkingDateTerimaPerintah(tarikh_terimaPerintah);							
    						// CONVERT STRING TO INT
    						int PerintahDateDiffInt = Integer.parseInt(checkingDateTerimaPerintah);						
    						myLogger.info("PerintahDateDiffInt >> "+PerintahDateDiffInt);
    						if(PerintahDateDiffInt > 69){
    							context.put("statusPerintah", "true");
    						}else{
    							context.put("statusPerintah", "");
    						}	
    						
    						if (keputusan_mahkamah.equals("1")){
    							setValueKeputusanMahkamah("checked","","","");
    						}else if (keputusan_mahkamah.equals("2")){
    							setValueKeputusanMahkamah("","checked","","");
    						}else if (keputusan_mahkamah.equals("3")){
    							setValueKeputusanMahkamah("","","checked","");
    						}else if (keputusan_mahkamah.equals("4")){
    							setValueKeputusanMahkamah("","","","checked");
    						}
    						context.put("TEMPchecked1", checkedsorKeputusanMahkamah1);
    						context.put("TEMPchecked2", checkedsorKeputusanMahkamah2);
    						context.put("TEMPchecked3", checkedsorKeputusanMahkamah3);
    						context.put("TEMPchecked4", checkedsorKeputusanMahkamah4);
    						
    						if (flag_pulang_deposit.equals("1")){
    							setValueStatusPemulanganDeposit("checked","","","");
    						}else if (flag_pulang_deposit.equals("2")){
    							setValueStatusPemulanganDeposit("","checked","","");
    						}else if (flag_pulang_deposit.equals("3")){
    							setValueStatusPemulanganDeposit("","","checked","");
    						}else if (flag_pulang_deposit.equals("4")){
    							setValueStatusPemulanganDeposit("","","","checked");
    						}
    						context.put("TEMPcheckedDep1", checkedStatusPemulangan1);
    						context.put("TEMPcheckedDep2", checkedStatusPemulangan2);
    						context.put("TEMPcheckedDep3", checkedStatusPemulangan3);
    						context.put("TEMPcheckedDep4", checkedStatusPemulangan4);				
    						
    						context.put("flag", "semak");
    						context.put("mode","disabled");
    						context.put("clearForm","");
    						context.put("button", "view");			
    					}else{
    						context.put("clearForm", "yes");
    						context.put("mode", "");
    						context.put("flag", "");
    						context.put("button", "edit");
    					}    					
    				}else{					
    					context.put("clearForm", "yes");
    					context.put("mode", "");
    					context.put("flag", "");
    					context.put("button", "edit");
    				}	
    				
    			}else{				
    				context.put("status", true);
    			}

    			vm = "app/ppt/frmBantahanAgensiSusulan.jsp";	
    			
    	}else if (submit.equals("kemaskiniSusulan")){
    		selectedtab = "4";
    		context.put("selectedtab", selectedtab);
    		list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);			
    		if (list.size()!=0){
    			Hashtable a = (Hashtable) list.get(0);
    			String id_bantahan = a.get("id_bantahan").toString();
    				
//    			listA = modelBantahanPB.senarai_dokumen_bantahan(id_bantahan, jenisDoc);
    			listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
    			context.put("getMaklumatSusulan",listA);				
    			Hashtable b = (Hashtable) listA.get(0);
    				String keputusan_mahkamah = b.get("keputusan_mahkamah").toString();
    				String flag_pulang_deposit = b.get("flag_pulang_deposit").toString();	
    				
    				if (keputusan_mahkamah.equals("1")){
    					setValueKeputusanMahkamah("checked","","","");
    				}else if (keputusan_mahkamah.equals("2")){
    					setValueKeputusanMahkamah("","checked","","");
    				}else if (keputusan_mahkamah.equals("3")){
    					setValueKeputusanMahkamah("","","checked","");
    				}else if (keputusan_mahkamah.equals("4")){
    					setValueKeputusanMahkamah("","","","checked");
    				}
    				context.put("TEMPchecked1", checkedsorKeputusanMahkamah1);
    				context.put("TEMPchecked2", checkedsorKeputusanMahkamah2);
    				context.put("TEMPchecked3", checkedsorKeputusanMahkamah3);
    				context.put("TEMPchecked4", checkedsorKeputusanMahkamah4);
    				
    				if (flag_pulang_deposit.equals("1")){
    					setValueStatusPemulanganDeposit("checked","","","");
    				}else if (flag_pulang_deposit.equals("2")){
    					setValueStatusPemulanganDeposit("","checked","","");
    				}else if (flag_pulang_deposit.equals("3")){
    					setValueStatusPemulanganDeposit("","","checked","");
    				}else if (flag_pulang_deposit.equals("4")){
    					setValueStatusPemulanganDeposit("","","","checked");
    				}
    				context.put("TEMPcheckedDep1", checkedStatusPemulangan1);
    				context.put("TEMPcheckedDep2", checkedStatusPemulangan2);
    				context.put("TEMPcheckedDep3", checkedStatusPemulangan3);
    				context.put("TEMPcheckedDep4", checkedStatusPemulangan4);	
    				
    				context.put("flag","semak");
    				context.put("mode", "");
    				context.put("clearForm","");
    				context.put("button", "edit");
    			}else{
    				context.put("status", true);
    			}

    			vm = "app/ppt/frmBantahanAgensiSusulan.jsp";
    		
    	}else if(submit.equals("simpanSusulan")){			    			
    		selectedtab = "4";
    		context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			String desc_status_bantahan_ap = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    				desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
    			}else{
    				context.put("status", true);
    			}
    			
    			
    			Hashtable getIdBorangO = model.getIdBorangOAP(id_hakmilik,id_bantahan);	
    			String idBorangO = getIdBorangO.get("id_borango").toString();
    			
    			if (doPost.equals("true")){
    				System.out.println("masuk---1");
    				System.out.println("masuk---usid"+usid);
    				System.out.println("masuk---id_bantahan"+id_bantahan);
    				System.out.println("masuk---idBorangO"+idBorangO);
    				simpanSusulan(usid,idBorangO,id_bantahan);
//    				updateStatusUrusanMahkamah(id_permohonan,usid);
    			}
    			
    			// UPDATE STATUS SEMASA BANTAHAN
    			context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
    			
//    		listA = modelBantahanPB.senarai_dokumen_bantahan(id_bantahan, jenisDoc);
    		listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
   			context.put("getMaklumatSusulan",listA);
    			if(listA.size()!=0){
    				Hashtable b = (Hashtable)listA.get(0);
    				String keputusan_mahkamah = b.get("keputusan_mahkamah").toString();
    				String flag_pulang_deposit = b.get("flag_pulang_deposit").toString();	
    				
    				if (keputusan_mahkamah.equals("1")){
    					setValueKeputusanMahkamah("checked","","","");
    				}else if (keputusan_mahkamah.equals("2")){
    					setValueKeputusanMahkamah("","checked","","");
    				}else if (keputusan_mahkamah.equals("3")){
    					setValueKeputusanMahkamah("","","checked","");
    				}else if (keputusan_mahkamah.equals("4")){
    					setValueKeputusanMahkamah("","","","checked");
    				}
    				context.put("TEMPchecked1", checkedsorKeputusanMahkamah1);
    				context.put("TEMPchecked2", checkedsorKeputusanMahkamah2);
    				context.put("TEMPchecked3", checkedsorKeputusanMahkamah3);
    				context.put("TEMPchecked4", checkedsorKeputusanMahkamah4);
    				
    				if (flag_pulang_deposit.equals("1")){
    					setValueStatusPemulanganDeposit("checked","","","");
    				}else if (flag_pulang_deposit.equals("2")){
    					setValueStatusPemulanganDeposit("","checked","","");
    				}else if (flag_pulang_deposit.equals("3")){
    					setValueStatusPemulanganDeposit("","","checked","");
    				}else if (flag_pulang_deposit.equals("4")){
    					setValueStatusPemulanganDeposit("","","","checked");
    				}
    				context.put("TEMPcheckedDep1", checkedStatusPemulangan1);
    				context.put("TEMPcheckedDep2", checkedStatusPemulangan2);
    				context.put("TEMPcheckedDep3", checkedStatusPemulangan3);
    				context.put("TEMPcheckedDep4", checkedStatusPemulangan4);	
    			}else{
    				context.put("status", true);
    			}
    		
    			context.put("flag", "semak");
    			context.put("mode", "disabled");
    			context.put("clearForm","");
    			context.put("button", "view");

    			vm = "app/ppt/frmBantahanAgensiSusulan.jsp";
    			    		
    	}else if("pemulanganDeposit".equals(submit)){	  			
    		selectedtab = "5";
    			context.put("selectedtab",selectedtab);
    			
    			Hashtable getIdAward = model.getIdAwardAP(id_hakmilik);	
    			String id_award = "";
    			if (getIdAward.size()!=0){
    				id_award = getIdAward.get("id_award").toString();
    			}
    			context.put("id_award", id_award);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);		
    		if (list.size()!=0){
    			Hashtable a = (Hashtable) list.get(0);
    			String id_bantahan = a.get("id_bantahan").toString();
    			context.put("id_bantahan", id_bantahan);
   				
//    			listA = modelBantahanPB.senarai_dokumen_bantahan(id_bantahan, jenisDoc);
    			listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
    			if (listA.size()!=0){
    				context.put("getMaklumatSusulan", listA);
    					Hashtable b = (Hashtable) listA.get(0);
    					String flag_pulang_deposit = b.get("flag_pulang_deposit").toString();	
    								
    					if (!flag_pulang_deposit.equals("")){
    					   						
    						if (flag_pulang_deposit.equals("1")){
    							setValueStatusPemulanganDeposit("checked","","","");
    						}else if (flag_pulang_deposit.equals("2")){
    							setValueStatusPemulanganDeposit("","checked","","");
    						}else if (flag_pulang_deposit.equals("3")){
    							setValueStatusPemulanganDeposit("","","checked","");
    						}else if (flag_pulang_deposit.equals("4")){
    							setValueStatusPemulanganDeposit("","","","checked");
    						}else{
    	    					setValueStatusPemulanganDeposit("","","","");
    	    				}
    						context.put("TEMPcheckedDep1", checkedStatusPemulangan1);
    						context.put("TEMPcheckedDep2", checkedStatusPemulangan2);
    						context.put("TEMPcheckedDep3", checkedStatusPemulangan3);
    						context.put("TEMPcheckedDep4", checkedStatusPemulangan4);				
    						
    						context.put("flag", "semak");
    						context.put("mode","disabled");
    						context.put("clearForm","");
    						context.put("button", "view");			
    					}else{
    						context.put("clearForm", "yes");
    						context.put("mode", "");
    						context.put("flag", "");
    						context.put("button", "edit");
    					}    					
    				}else{					
    					context.put("clearForm", "yes");
    					context.put("mode", "");
    					context.put("flag", "");
    					context.put("button", "edit");
    				}	
    				
    			}else{				
    				context.put("status", true);
    			}

    			vm = "app/ppt/frmBantahanAgensiPemulanganDeposit.jsp";	
    			
    	}else if ("kemaskiniPemulanganDeposit".equals(submit)){
    		selectedtab = "5";
    		context.put("selectedtab", selectedtab);
    		
    		list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);			
    		if (list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				String id_bantahan = a.get("id_bantahan").toString();
    				
//    			listA = modelBantahanPB.senarai_dokumen_bantahan(id_bantahan, jenisDoc);
    			listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
    				context.put("getMaklumatSusulan",listA);				
    				Hashtable b = (Hashtable) listA.get(0);
    				String flag_pulang_deposit = b.get("flag_pulang_deposit").toString();	
    				
    			    				
    				if (flag_pulang_deposit.equals("1")){
    					setValueStatusPemulanganDeposit("checked","","","");
    				}else if (flag_pulang_deposit.equals("2")){
    					setValueStatusPemulanganDeposit("","checked","","");
    				}else if (flag_pulang_deposit.equals("3")){
    					setValueStatusPemulanganDeposit("","","checked","");
    				}else if (flag_pulang_deposit.equals("4")){
    					setValueStatusPemulanganDeposit("","","","checked");
    				}else{
    					setValueStatusPemulanganDeposit("","","","");
    				}
    				context.put("TEMPcheckedDep1", checkedStatusPemulangan1);
    				context.put("TEMPcheckedDep2", checkedStatusPemulangan2);
    				context.put("TEMPcheckedDep3", checkedStatusPemulangan3);
    				context.put("TEMPcheckedDep4", checkedStatusPemulangan4);	
    				
    				context.put("flag","semak");
    				context.put("mode", "");
    				context.put("clearForm","");
    				context.put("button", "edit");
    			
    		}else{
    			context.put("status", true);
    		}
    		vm = "app/ppt/frmBantahanAgensiPemulanganDeposit.jsp";
    		
    	}else if("simpanPemulanganDeposit".equals(submit)){			    			
    			selectedtab = "5";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			String desc_status_bantahan_ap = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    				desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
    			}else{
    				context.put("status", true);
    			}
    			
    			
    			Hashtable getIdBorangO = model.getIdBorangOAP(id_hakmilik,id_bantahan);	
    			String idBorangO = getIdBorangO.get("id_borango").toString();
    			
    			if (doPost.equals("true")){
    				simpanPemulanganDeposit(usid,idBorangO,id_bantahan);
//    				updateStatusUrusanMahkamah(id_permohonan,usid);
    			}
    			
    			// UPDATE STATUS SEMASA BANTAHAN
    			context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
    			
//    		listA = modelBantahanPB.senarai_dokumen_bantahan(id_bantahan, jenisDoc);
    		listA = modelBantahanPB.getMaklumatSusulan(id_bantahan);
   			context.put("getMaklumatSusulan",listA);
   			if(listA.size()!=0){
    				Hashtable b = (Hashtable)listA.get(0);
    				
    				String flag_pulang_deposit = b.get("flag_pulang_deposit").toString();	
    				
    			
    				
    				if (flag_pulang_deposit.equals("1")){
    					setValueStatusPemulanganDeposit("checked","","","");
    				}else if (flag_pulang_deposit.equals("2")){
    					setValueStatusPemulanganDeposit("","checked","","");
    				}else if (flag_pulang_deposit.equals("3")){
    					setValueStatusPemulanganDeposit("","","checked","");
    				}else if (flag_pulang_deposit.equals("4")){
    					setValueStatusPemulanganDeposit("","","","checked");
    				}
    				context.put("TEMPcheckedDep1", checkedStatusPemulangan1);
    				context.put("TEMPcheckedDep2", checkedStatusPemulangan2);
    				context.put("TEMPcheckedDep3", checkedStatusPemulangan3);
    				context.put("TEMPcheckedDep4", checkedStatusPemulangan4);	
    			}else{
    				context.put("status", true);
    			}
    		
    			context.put("flag", "semak");
    			context.put("mode", "disabled");
    			context.put("clearForm","");
    			context.put("button", "view");

    			vm = "app/ppt/frmBantahanAgensiPemulanganDeposit.jsp";
    		
    	}else if("tarikBalikBantahan".equals(submit)){  			
    			selectedtab = "6";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			
//    			if (status_bantahan_ap.equals("201")){    				
//    				myLogger.info("TAK BOLEH BT TARIK BALIK!");
//    				context.put("button", "");
//    				context.put("clearForm", "yes");
//    				context.put("mode", "disabled");
//    				context.put("flag","");
//    			}else{
    				
//    				myLogger.info("SELAIN STATUS 201");				    			
    				String id_bantahan = "";
    				if(list.size()!=0){
    					Hashtable a = (Hashtable) list.get(0);
    					id_bantahan = (String)a.get("id_bantahan");
    				}else{
    					context.put("status", true);
    				}		
    				
    				if( id_bantahan.equals("") || id_bantahan.equals(null) ){	
    					
    					context.put("button", "edit");
    					context.put("clearForm", "yes");
    					context.put("mode", "");
    					context.put("flag","");		
    					
    				}else{		
    					
    					listA = modelBantahanPB.getMaklumatTarikBalik(id_bantahan);
    					context.put("getMaklumatTarikBalik", listA);
    					if (listA.size()!=0){
    						Hashtable b = (Hashtable)listA.get(0);
    						String no_rujukan_surattarikbalik = (String)b.get("no_rujukan_surattarikbalik");
    						if(!no_rujukan_surattarikbalik.equals("")){
    							context.put("clearForm", "");
    							context.put("flag", "semak");
    							context.put("mode", "disabled");
    							context.put("button", "view");
    						}else{
    							context.put("clearForm", "yes");
    							context.put("flag", "");
    							context.put("mode", "");
    							context.put("button", "edit");
    						}
    					}						
    				}

//    			}

    			vm = "app/ppt/frmBantahanAgensiTarikBalik.jsp";
    			
    		}else if("simpanTarikBalik".equals(submit)){
    			
    			selectedtab = "6";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			String desc_status_bantahan_ap = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    				desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
    			}else{
    				context.put("status", true);
    			}
    		
    			if (doPost.equals("true")){
    				
    				// UPDATE TBLPPTBANTAHAN
    				simpanTarikBalik(usid,id_bantahan);
    				
    				// UPDATE TBLPPTPERMOHONAN
    				updateStatusTarikBalik(id_permohonan,usid);
    				
    				// UPDATE TBLPPTSUBURUSANHAKMILIK
    				updateSuburusanHakmilik_tarikbalik(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);

    				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
    				updateSuburusanStatusFailPPT_tarikbalik(session,id_permohonan,id_fail,id_suburusanstatusfailppt);    				
    				
    				// UPDATE TBLPPTSUBURUSANSTATUSFAIL
    				tarikBalik_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
    			}
    			
    			// UPDATE STATUS SEMASA BANTAHAN
    			context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
    			
    			listA = modelBantahanPB.getMaklumatTarikBalik(id_bantahan);
    			context.put("getMaklumatTarikBalik", listA);
    			
    			context.put("flag","semak");
    			context.put("clearForm", "");
    			context.put("button", "view");
    			context.put("mode","disabled");
    			
    			vm = "app/ppt/frmBantahanAgensiTarikBalik.jsp";			
    			
    		}else if ("kemaskiniTarikBalik".equals(submit)){
    			selectedtab = "6";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}
    						
    			listA = modelBantahanPB.getMaklumatTarikBalik(id_bantahan);
    			context.put("getMaklumatTarikBalik",listA);
    			
    			context.put("flag","semak");
    			context.put("button","edit");
    			context.put("mode", "");
    			context.put("clearForm", "");
    			
    			vm = "app/ppt/frmBantahanAgensiTarikBalik.jsp";
    			
    	}else if("batalBantahan".equals(submit)){
    		String jenisDoc = "batalBantahan";
    			selectedtab = "7";
    			context.put("selectedtab", selectedtab);
    			
//    			if (status_bantahan.equals("184")){				
//    				myLogger.info("TAK BOLEH BT BATAL MAHKAMAH!");
//    				context.put("button", "");
//    				context.put("clearForm", "yes");
//    				context.put("mode", "disabled");
//    				context.put("flag","");
//    			}else{				
//    				myLogger.info("SELAIN STATUS 184");	
    				list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    				String id_bantahan = "";
    				if(list.size()!=0){
    					Hashtable a = (Hashtable) list.get(0);
    					id_bantahan = (String)a.get("id_bantahan");
    				}else{
    					context.put("status", true);
    				}		
    				
    				if( id_bantahan.equals("") || id_bantahan.equals(null) ){	
    					
    					context.put("button", "edit");
    					context.put("clearForm", "yes");
    					context.put("mode", "");
    					context.put("flag","");		
    					
    				}else{		
    					
    					listA = modelBantahanPB.getMaklumatBatalMahkamah(id_bantahan);
    					context.put("getMaklumatBatalMahkamah", listA);
    					if (listA.size()!=0){
    						Hashtable b = (Hashtable)listA.get(0);
    						String no_rujukan_surat_batalmahkamah = (String)b.get("no_rujukan_surat_batalmahkamah");
    						if(!no_rujukan_surat_batalmahkamah.equals("")){
    							context.put("clearForm", "");
    							context.put("flag", "semak");
    							context.put("mode", "disabled");
    							context.put("button", "view");
    						}else{
    							context.put("clearForm", "yes");
    							context.put("flag", "");
    							context.put("mode", "");
    							context.put("button", "edit");
    						}
    					}						
    				}
//    			}

    			vm = "app/ppt/frmBantahanPembatalanAP.jsp";
    			
    	}else if(submit.equals("simpan_batalMahkamah")){    			
    			selectedtab = "7";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);	
    			String id_bantahan = "";
    			String desc_status_bantahan_ap = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    				desc_status_bantahan_ap = (String)a.get("desc_status_bantahan_ap");
    			}else{
    				context.put("status", true);
    			}
    		
    			if (doPost.equals("true")){
    				
    				// UPDATE TBLPPTBANTAHAN
    				simpanBatalMahkamah(usid,id_bantahan);
    				
    				// UPDATE TBLPPTPERMOHONAN				
    				updateStatus_pembatalanOlehMT(session);
    							
    				// UPDATE TBLPPTSUBURUSANHAKMILIK
    				updateSuburusanHakmilik_pembatalanOlehMT(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);

    				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
    				updateSuburusanStatusFailPPT_pembatalanOlehMT(session,id_permohonan,id_fail,id_suburusanstatusfailppt);				
    				
    				
    				// UPDATE TBLPPTSUBURUSANSTATUSBANTAHAN
    				pembatalanOlehMT_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
    			}
    			
    			// UPDATE STATUS SEMASA BANTAHAN
    			context.put("desc_status_bantahan_ap", desc_status_bantahan_ap);
    			
    			listA = modelBantahanPB.getMaklumatBatalMahkamah(id_bantahan);
    			context.put("getMaklumatBatalMahkamah", listA);
    			
    			context.put("flag","semak");
    			context.put("clearForm", "");
    			context.put("button", "view");
    			context.put("mode","disabled");
    			
    			vm = "app/ppt/frmBantahanPembatalanAP.jsp";
    			
    		}else if("kemaskiniBatalBantahan".equals(submit)){
    			
    			selectedtab = "7";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}
    						
    			listA = modelBantahanPB.getMaklumatBatalMahkamah(id_bantahan);
    			context.put("getMaklumatBatalMahkamah",listA);
    			
    			context.put("flag","semak");
    			context.put("button","edit");
    			context.put("mode", "");
    			context.put("clearForm", "");
    			
    			vm = "app/ppt/frmBantahanPembatalanAP.jsp";    		
    			
    		
    		}else if("upload_dokumen".equals(submit)){

    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    				id_hakmilik = (String)a.get("id_hakmilik");
    			}else{
    				context.put("status", true);
    			}			
    					
    			String id_dokumen = getParam("id_dokumen");				
    			if(id_dokumen == ""){	
    				uploadFiles();		
    			    this.context.put("readmode", "edit");	
    			}else {
    				updateFiles(usid);
    				view_details_dokumen = model.view_details_dokumen(id_dokumen);				
    				context.put("view_details_dokumen",view_details_dokumen);
    				this.context.put("readmode", "view");
    			}	
    			
    		    context.put("id_permohonan",id_permohonan);
        		context.put("id_bantahan",id_bantahan);
        		
        		if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    	     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan);
    	    		context.put("listDokumen", listDokumen);
    	    		context.put("listDokumen_size", listDokumen.size());	    		
    			}else{				
    				context.put("listDokumen", "");
    				context.put("listDokumen_size", 0);
    			}
        
        		//:::upload
        		context.put("nama_skrin",getParam("nama_skrin"));
        		myLogger.info("-----------------NAMA SKRIN"+getParam("nama_skrin"));
        		
    			vm = "app/ppt/frmBantahanAgensiDokumen.jsp";
    			
    		}else if("kemaskini_dokumen".equals(submit)){
    			
    			String id_dokumen = getParam("id_dokumen");
    			view_details_dokumen = model.view_details_dokumen(id_dokumen);	
    			
    			context.put("view_details_dokumen",view_details_dokumen);
    			this.context.put("readmode", "edit");
    			
    			vm = "app/ppt/frmBantahanAgensiDokumen.jsp";
    			
    		}else if("hapus_dokumen_papar".equals(submit)){
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			context.put("getMaklumatBantahan", list);
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable)list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}
    						
    		    modelOperations.deleteDokumen(getParam("id_dokumen"));
    		    
    		    context.put("num_files", 5);
                this.context.put("readmode", "edit");
                context.put("view_details_dokumen","");
                this.context.put("display_error_message","no");	
                
                if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    	     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan);
    	    		context.put("listDokumen", listDokumen);
    	    		context.put("listDokumen_size", listDokumen.size());	    		
    			}else{				
    				context.put("listDokumen", "");
    				context.put("listDokumen_size", 0);
    			}
                
                vm = "app/ppt/frmBantahanAgensiDokumen.jsp";	
      
                
    		}else if("tambah_dokumen".equals(submit)){
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			context.put("getMaklumatBantahan", list);
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable)list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}
    			
                context.put("num_files", 5);
                this.context.put("readmode", "edit");
                context.put("view_details_dokumen","");
                this.context.put("display_error_message","no");	
               	
                if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    	     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan);
    	    		context.put("listDokumen", listDokumen);
    	    		context.put("listDokumen_size", listDokumen.size());	    		
    			}else{				
    				context.put("listDokumen", "");
    				context.put("listDokumen_size", 0);
    			}                
               	vm = "app/ppt/frmBantahanAgensiDokumen.jsp";    		
            
    		}else if("view_Dokumen_Details".equals(submit)){
    			
    			String id_dokumen = getParam("id_dokumen");			
    			view_details_dokumen = model.view_details_dokumen(id_dokumen);				
    			context.put("view_details_dokumen",view_details_dokumen);			
    			this.context.put("readmode", "view");
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}
    			
               if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    	     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan);
    	    		context.put("listDokumen", listDokumen);
    	    		context.put("listDokumen_size", listDokumen.size());	    		
    			}else{				
    				context.put("listDokumen", "");
    				context.put("listDokumen_size", 0);
    			}			
    			
    			vm = "app/ppt/frmBantahanAgensiDokumen.jsp";					
    			
    		}else if("hapus_dokumen".equals(submit)){
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);			
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}
    						
    			String[] ids1 = this.request.getParameterValues("ids1");
    			if (ids1 != null) {
    				for (int i = 0; i < ids1.length; i++) {						
    						if (doPost.equals("true")) {
    							modelOperations.deleteDokumen(ids1[i]);
    						}
    					}
    				}
    			this.context.put("readmode", getParam("readmode"));	
    	
                if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    	     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan);
    	    		context.put("listDokumen", listDokumen);
    	    		context.put("listDokumen_size", listDokumen.size());	    		
    			}else{				
    				context.put("listDokumen", "");
    				context.put("listDokumen_size", 0);
    			}

    			vm = "app/ppt/frmBantahanAgensiDokumen.jsp";	
    		 			
    	}else if(submit.equals("hapusDokumenMasterPerintah")){    			
    		selectedtab = "3";
    		context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);		
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}
    			
    			String[] ids1 = this.request.getParameterValues("ids1");
    			if (ids1 != null) {
    				for (int i = 0; i < ids1.length; i++) {						
    						if (doPost.equals("true")) {
    							modelOperations.deleteDokumen(ids1[i]);
    						}
    					}
    				}
    			this.context.put("readmode", getParam("readmode"));	
    	
                if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    	     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan);
    	    		context.put("listDokumen", listDokumen);
    	    		context.put("listDokumen_size", listDokumen.size());	    		
    			}else{				
    				context.put("listDokumen", "");
    				context.put("listDokumen_size", 0);
    			}
                
                vm = "app/ppt/frmBantahanAgensiSusulan.jsp";	    		
    			
    		}
    		
    		else if("hapusDokumenMaster".equals(submit)){
    			
    			selectedtab = "0";
    			context.put("selectedtab", selectedtab);
    			
    			list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);		
    			String id_bantahan = "";
    			if(list.size()!=0){
    				Hashtable a = (Hashtable) list.get(0);
    				id_bantahan = (String)a.get("id_bantahan");
    			}else{
    				context.put("status", true);
    			}
    			
    			String[] ids1 = this.request.getParameterValues("ids1");
    			if (ids1 != null) {
    				for (int i = 0; i < ids1.length; i++) {						
    						if (doPost.equals("true")) {
    							modelOperations.deleteDokumen(ids1[i]);
    						}
    					}
    				}
    			this.context.put("readmode", getParam("readmode"));	
    	
                if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
    	     		listDokumen = model.senarai_dokumen_bantahan(id_bantahan);
    	    		context.put("listDokumen", listDokumen);
    	    		context.put("listDokumen_size", listDokumen.size());	    		
    			}else{				
    				context.put("listDokumen", "");
    				context.put("listDokumen_size", 0);
    			}
                
    			vm = skrinDaftarBantahanMasterAP;	    		
    			
    		}else if("batal_dokumen".equals(submit)){
    			
    			String id_dokumen = getParam("id_dokumen");
    			view_details_dokumen = model.view_details_dokumen(id_dokumen);				
    			context.put("view_details_dokumen",view_details_dokumen);
    			this.context.put("readmode", "edit");
    			
    			vm = "app/ppt/frmBantahanAgensiDokumen.jsp";	
    			
    		}else if("kembali".equals(submit)){	
    		
    			//listE = model.getSenaraiNoLot(id_permohonan);
    			//context.put("getSenaraiNoLot", listE);
    			context.put("list_size", model.getSenaraiNoLot_count(id_permohonan));
        		if (model.getSenaraiNoLot_count(id_permohonan)!=0){
        			context.put("selectHakmilik",HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik","style=width:auto tabindex=5 disabled "));
        		}
        		//context.put("selectHakmilik",HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik","style=width:auto tabindex=5 disabled "));
    			
    			//GET ADD OF AGENSI PEMOHON
    			Hashtable getAddAgensi = model.getAddAgensi(id_permohonan,id_hakmilik);	
    			String id_kementerian = getAddAgensi.get("id_kementerian").toString();
    			String nama_kementerian = getAddAgensi.get("nama_kementerian").toString();
    			String alamat1 = getAddAgensi.get("alamat1").toString();
    			String alamat2 = getAddAgensi.get("alamat2").toString();
    			String alamat3 = getAddAgensi.get("alamat3").toString();
    			String poskod = getAddAgensi.get("poskod").toString();
    			String id_negeri = getAddAgensi.get("id_negeri").toString();
    			String nama_negeri = getAddAgensi.get("nama_negeri").toString();
    			context.put("txtIdKementerian", id_kementerian);
    			context.put("txtNamaPembantah", nama_kementerian);
    			context.put("txtAlamat1", alamat1);
    			context.put("txtAlamat2", alamat2);
    			context.put("txtAlamat3", alamat3);
    			context.put("txtPoskod", poskod);
    			context.put("txtIdNegeri",id_negeri);
    			context.put("txtNamaNegeri", nama_negeri);
        		//END
        		
        		context.put("selectPihakBantah", HTML.SelectPihakBantah("socPihakBantah", null,"class=medium tabindex=6 disabled"));
//        		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=medium disabled "));
//        		context.put("selectBandar",HTML.SelectBandar("socBandar",null,"class=medium disabled"));	
        		context.put("selectHakmilik",HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik","style=width:auto tabindex=5 disabled "));
        		context.put("selectNoLot",HTML.SelectNoLotByHakmilik(null,"socNoLot","style=width:auto tabindex=7 disabled"));
//        		context.put("selectNamaPembantah", HTML.SelectNamaPembantahByHakmilik(null, "socNamaPembantah", null, "style=width:auto tabindex=11 disabled", null));   		
        		
    			vm = "app/ppt/frmBantahanDaftarByAgensiPemohon.jsp";
  		    		
    		
    		} else if ("batalAP".equals(submit)){	
    			
    			context.put("selectHakmilik",HTML.SelectHakmilikByAP(id_hakmilik,"socHakmilik","style=width:auto tabindex=5 disabled "));
    			context.put("selectNoLot",HTML.SelectNoLotByHakmilik(id_hakmilik,"socNoLot","style=width:auto tabindex=7 disabled "));
    			
    			//GET ADD OF AGENSI PEMOHON
    			Hashtable getAddAgensi = model.getAddAgensi(id_permohonan,id_hakmilik);	
    			String id_kementerian = getAddAgensi.get("id_kementerian").toString();
    			String nama_kementerian = getAddAgensi.get("nama_kementerian").toString();
    			String alamat1 = getAddAgensi.get("alamat1").toString();
    			String alamat2 = getAddAgensi.get("alamat2").toString();
    			String alamat3 = getAddAgensi.get("alamat3").toString();
    			String poskod = getAddAgensi.get("poskod").toString();
    			String nama_negeri = getAddAgensi.get("nama_negeri").toString();
    			context.put("txtIdKementerian", id_kementerian);
    			context.put("txtNamaPembantah", nama_kementerian);
    			context.put("txtAlamat1", alamat1);
    			context.put("txtAlamat2", alamat2);
    			context.put("txtAlamat3", alamat3);
    			context.put("txtPoskod", poskod);
    			context.put("txtNamaNegeri", nama_negeri);
        		//END
    			
    			//GET MAX_NOSIASATAN
//    			listK = model.getNoSiasatanAP(id_permohonan,id_hakmilik);
//    			context.put("getNoSiasatan", listK);
//    			String id_siasatan = "";
//    			if(listK.size()!=0){
//    				Hashtable b = (Hashtable) listK.get(0);
//    				id_siasatan = b.get("id_siasatan").toString();	
//    			}			
    			//END			
    			
    			//GET TARIKH AWARD & TARIKH BORANG H
    			listF = model.getTarikhPentingAP(id_permohonan,id_hakmilik,id_siasatan);
    			context.put("getTarikhPentingAP",listF);
    			//END
    			
    			listD = model.getHakmilikAP(id_hakmilik);
    			context.put("getHakmilik", listD);
    			
    			context.put("flag", "semak");
    			context.put("clearForm", "yes");
    			context.put("clear", "");

    			vm = "app/ppt/frmBantahanDaftarByAgensiPemohon.jsp";
    		
    		} else if ("kembaliList".equals(submit)){	
    			
               	//GET LIST DATA PB
//        		listE = model.getSenaraiNoLot(id_permohonan);
//        		context.put("getSenaraiNoLot", listE);
        		
    			//listPageNoLot = model.getSenaraiNoLot(id_permohonan);
        		//context.put("getSenaraiNoLot", listPageNoLot);
        		//context.put("list_size", listPageNoLot.size());
        		//setupPageNoLot(session,action,listPageNoLot);
    			context.put("list_size", model.getSenaraiNoLot_count(id_permohonan));
        		        		
        		vm = "app/ppt/frmBantahanAgensiSenaraiPB.jsp"; 
        		
    		} else if ("kembaliListBantahan".equals(submit)){	
    			
               	//GET LIST DATA
        		list = modelBantahanPB.getListPemohonAP(userIdNeg);		
        		context.put("PermohonanList", list);
        		context.put("list_size", list.size());  	    		          		
        		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",null,"style=width:470px"));
        		
        		vm = skrinListDepan; 
        		
        	}else if("Cari".equals(submit)){

    			//carian
    			carianBantahan(usid,userIdNeg );		
    			listPageDepan = modelBantahanPB.getListCarian();
        		
        		//data & size default list
        		context.put("PermohonanList", listPageDepan);
        		context.put("list_size", listPageDepan.size());  

        		//maintain searching value
        		this.context.put("txtNoFail", getParam("txtNoFail"));
        		String idKementerian = getParam("socKementerian");
        		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(idKementerian),"style=width:470px"));
        		
    			vm = skrinListDepan;      			
        		
        	}else {    		
        		
        		String txtNoFail = "";
    			context.put("txtNoFail", "");
    			
               	//GET LIST DATA
        		listPageDepan = modelBantahanPB.getListPemohonAP(userIdNeg );	

        		context.put("PermohonanList", listPageDepan);
        		context.put("list_size", listPageDepan.size());  
        		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",null,"style=width:470px"));   	
        		
    			//PAGING		    		
        		setupPageDepan(session,action,listPageDepan);
        		
        		vm = skrinListDepan;
        	} 
    	
	    	String id_bantahan_check = "";
			String idBorangO_check = "";
	    	list = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan,id_warta);
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);		
				id_bantahan_check = (String)a.get("id_bantahan");
			}	
			context.put("id_bantahan_check", id_bantahan_check);
			Hashtable getIdBorangO_check = model.getIdBorangOAP(id_hakmilik,id_bantahan_check);	
			if(getIdBorangO_check.size()!=0)
			{
				idBorangO_check = getIdBorangO_check.get("id_borango").toString();
			}
			context.put("idBorangO_check", idBorangO_check);
			
	
			
        	return vm;
    }
	
	// METHOD

	private void tolakPermohonan(HttpSession session,String id_bantahan) throws Exception{
		
		modelOperations.tolakPermohonanOnline(id_bantahan,session.getAttribute("_ekptg_user_id").toString());
		
	}//close tolakPermohonan

	private String userData(String id_user) throws Exception{
		
		Vector listUserid = new Vector();
		listUserid.clear();
		
		modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    
	    return userIdNeg;
	}
		

		private void carianBantahan(String usid, String userIdNeg ) throws Exception {
    		String txtNoFail = getParam("txtNoFail");
    		String idKementerian = getParam("socKementerian");
    		modelBantahanPB.setCarianFailAP(usid,txtNoFail,idKementerian,userIdNeg );		
    	}

    	@SuppressWarnings("unchecked")
    	private void uploadFiles() throws Exception {
    		    DiskFileItemFactory factory = new DiskFileItemFactory();
    		    ServletFileUpload upload = new ServletFileUpload(factory);
    		    List items = upload.parseRequest(request);
    		    Iterator itr = items.iterator();
    		    while (itr.hasNext()) {
    		      FileItem item = (FileItem)itr.next();
    		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
    		    	  saveData(item);
    		      }
    		    }
    		  }
    	//penambahbaikan yati
    	 private void saveData(FileItem item) throws Exception {
    		 	HttpSession session = request.getSession();		
    	  		Db db = null;
    	        try {
    	        	
    	        	String id_jenisDoc = "1530";
    	        	long id_Dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");	        	
    	        	db = new Db();			      
    			     
    	        	Connection con = db.getConnection();
    	        	con.setAutoCommit(false);
    	        	SQLRenderer r = new SQLRenderer();
    	        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN (id_Dokumen,id_bantahan,nama_Fail,jenis_Mime,content,tajuk,keterangan,id_masuk,id_kemaskini,id_jenisdokumen,tarikh_masuk) " +
    	        			"values(?,?,?,?,?,?,?,?,?,?,SYSDATE)");
    	        	ps.setLong(1, id_Dokumen);
    	        	ps.setString(2, getParam("id_bantahan"));
    	        	ps.setString(3,item.getName());
    	        	ps.setString(4,item.getContentType());
    	        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
    	        	ps.setString(6, getParam("txtnamadokumen"));
    	        	ps.setString(7, getParam("txtketerangandokumen"));	        	
    	        	ps.setString(8,(String) session.getAttribute("_ekptg_user_id"));	        	      	
    	        	ps.setString(9,(String) session.getAttribute("_ekptg_user_id"));
    	        	ps.setString(10, id_jenisDoc);  
    	        	ps.executeUpdate();
    	            con.commit();
    	
    		    } finally {
    			      if (db != null) db.close();
    		    }
    	  }

    		private void updateFiles(String usid) throws Exception {			
    			String id_dokumen = getParam("id_dokumen");
    			String txtnamadokumen = getParam("txtnamadokumen");
    			String txtketerangandokumen = getParam("txtketerangandokumen");

    			modelOperations.updateFiles(usid,id_dokumen,txtnamadokumen,txtketerangandokumen);			
    		}	 
    	
		public void setupPageDepan(HttpSession session,String action,Vector list) {
			
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
			this.context.put("PermohonanList",paging.getPage(page));
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
    	

    	public void setupPageNoLot(HttpSession session,String action,Vector listPageNoLot) {
    		try {		
    		this.context.put("totalRecords",listPageNoLot.size());
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
    	    Paging paging = new Paging(session,listPageNoLot,itemsPerPage);		
    		if (page > paging.getTotalPages()) page = 1; //reset page number
    		this.context.put("getSenaraiNoLot",paging.getPage(page));
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
    	
    	
    	public void setValuesbcBantahan(String checkedsbcBantahan1,String checkedsbcBantahan2,String checkedsbcBantahan3,String checkedsbcBantahan4 ) {		
    		this.checkedsbcBantahan1 = checkedsbcBantahan1;
    		this.checkedsbcBantahan2 = checkedsbcBantahan2;
    		this.checkedsbcBantahan3 = checkedsbcBantahan3;		
    		this.checkedsbcBantahan4 = checkedsbcBantahan4;
    	}
    	public void setValueJenisPembantah(String PB,String AP){		
    		context.put("jenis_pembantah", PB);
    		context.put("jenis_pembantah", AP);
    	}	
    	public void setValueStatusPemulanganDeposit(String checkedStatusPemulangan1,String checkedStatusPemulangan2,String checkedStatusPemulangan3,String checkedStatusPemulangan4 ) {		
    		this.checkedStatusPemulangan1 = checkedStatusPemulangan1;
    		this.checkedStatusPemulangan2 = checkedStatusPemulangan2;
    		this.checkedStatusPemulangan3 = checkedStatusPemulangan3;		
    		this.checkedStatusPemulangan4 = checkedStatusPemulangan4;
    	}	       	
    	public void add_bantahanAP(String usid) throws Exception {
    	    String txtNoBantahan = getParam("txtNoBantahan");		
    	    String txdTkhMasuk = getParam("txdTkhMasuk");
    	    String txdTkhTerimaBrgN = getParam("txdTkhTerimaBrgN");
    	    String txdBrgN = getParam("txdBrgN");
    	    String txtNoLot = getParam("txtNoLot");
    	    String txtNoPt = getParam("txtNoPt");
    	    String txdTkhBrgH = getParam("txdTkhBrgH");
    	    String txdTkhAward = getParam("txdTkhAward");
    	    String txtNoHakmilik = getParam("txtNoHakmilik");
    	    String txtIdKementerian = getParam("txtIdKementerian");
    	    String txtNamaPembantah = getParam("txtNamaPembantah");
    	    String txtAlamat1 = getParam("txtAlamat1");
    	    String txtAlamat2 = getParam("txtAlamat2");
    	    String txtAlamat3 = getParam("txtAlamat3");
    	    String txtPoskod = getParam("txtPoskod");
    	    String txtIdNegeri = getParam("txtIdNegeri");
    	    String txtKptgnAtasTnh = getParam("txtKptgnAtasTnh");
    	    String txtAlasanBantahan = getParam("txtAlasanBantahan");
    	    String idAgensi = getParam("id_agensi");	
    	    String id_hakmilik = getParam("id_hakmilik");
    	    String jenis_pembantah = getParam("jenis_pembantah");
    	    String flag_syarat = getParam("flag_syarat");
    	    String ukuran_luas = getParam("ukuran_luas");
    	    String amaun_pampasan = getParam("amaun_pampasan");
    	    String terima_pampasan = getParam("terima_pampasan");
    	    String umpuk_pampasan = getParam("umpuk_pampasan");
    	    String txtAmaunTuntutan = getParam("txtAmaunTuntutan");		
    	    String id_permohonan = getParam("id_permohonan");
    	    String txtMaklumatBantahanTamat = getParam("txtMaklumatBantahanTamat");
    	    HttpSession session = request.getSession();	
    	    modelOperations.add_bantahanAP(session,txtNoBantahan,txdTkhMasuk,txdTkhTerimaBrgN,txdBrgN,txtNoLot,txtNoPt,
    	    		txdTkhBrgH,txdTkhAward,txtNoHakmilik,txtIdKementerian,txtNamaPembantah,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,txtIdNegeri,
    	    		txtKptgnAtasTnh,txtAlasanBantahan,usid,idAgensi,id_hakmilik,
    	    		jenis_pembantah,flag_syarat,ukuran_luas,amaun_pampasan,
    	    		terima_pampasan,umpuk_pampasan,txtAmaunTuntutan,id_permohonan,txtMaklumatBantahanTamat);
    	  
    	    
    	   
    	
    	}	    	

    	public void updateStatusDalamProses(String id_permohonan, String usid) throws Exception {
    		modelOperations.updateStatusDalamProses(id_permohonan,usid);
    	}
    	public void setValueBantahanTerhadap(String checkedsbcBantahan1, String checkedsbcBantahan2,String checkedsbcBantahan3, String checkedsbcBantahan4) {
    		this.checkedsbcBantahan1 = checkedsbcBantahan1;
    		this.checkedsbcBantahan2 = checkedsbcBantahan2;
    		this.checkedsbcBantahan3 = checkedsbcBantahan3;
    		this.checkedsbcBantahan4 = checkedsbcBantahan4;
    	}
    	public void setValueKeputusanMahkamah(String checkedsorKeputusanMahkamah1, String checkedsorKeputusanMahkamah2,String checkedsorKeputusanMahkamah3, String checkedsorKeputusanMahkamah4) {
    		this.checkedsorKeputusanMahkamah1 = checkedsorKeputusanMahkamah1;
    		this.checkedsorKeputusanMahkamah2 = checkedsorKeputusanMahkamah2;
    		this.checkedsorKeputusanMahkamah3 = checkedsorKeputusanMahkamah3;
    		this.checkedsorKeputusanMahkamah4 = checkedsorKeputusanMahkamah4;
    	}	
    	private void add_deposit(String usid,String id_bantahan) throws Exception {
    		String txdTkhTerimaResit = getParam("txdTkhTerimaResit");
    		String txdTkhResit = getParam("txdTkhResit");
    		String txtNoResit = getParam("txtNoResit");
    		String txtAmaunResit = getParam("txtAmaunResit");
    		String socCaraBayar = getParam("socCaraBayar");
    		String txtNoCek = getParam("txtNoCek");
    		String txtNoAkaun = getParam("txtNoAkaun");
//    		String socBank = getParam("socBank");
    		String txdTkhPulang = getParam("txdTkhPulang");
    		String socStatusPulang = getParam("socStatusPulang");
    		String txdTkhHantar = getParam("txdTkhHantar");
    		String txtNamaPenghantar = getParam("txtNamaPenghantar");
    		String txtNamaBank = getParam("txtNamaBank");
    		String txtCatatanPlgDeposit = getParam("txtCatatanPlgDeposit");
    		modelOperations.add_deposit(usid,id_bantahan,txdTkhTerimaResit,txdTkhResit,txtNoResit,txtAmaunResit,
    				socCaraBayar,txtNoCek,txtNoAkaun,txtNamaBank,txdTkhPulang,socStatusPulang,txdTkhHantar,
    				txtNamaPenghantar,txtCatatanPlgDeposit);	
    		
    		HttpSession session = request.getSession();		 
    		Db db = null;
    		String NO_BANTAHAN_temp = "";
    		String AMAUN_TUNTUTAN_temp = "";
    		try {
    		db = new Db();
    		Statement stmt = db.getStatement();
    		String sql = " SELECT NO_BANTAHAN,TRIM(TO_CHAR(AMAUN_DEPOSIT,'999,999,990.99')) AS AMAUN_TUNTUTAN FROM TBLPPTBANTAHAN" +
    				"" +
    				" WHERE ID_BANTAHAN = '"+id_bantahan+"'";			
    		ResultSet rs = stmt.executeQuery(sql);	
    		myLogger.info("SQL  :"+sql);
    		while (rs.next()){				
    			NO_BANTAHAN_temp = rs.getString("NO_BANTAHAN");	
    			AMAUN_TUNTUTAN_temp = rs.getString("AMAUN_TUNTUTAN");
    	    }
    	    AuditTrail at = new AuditTrail();
    		at.logActivity("","1",null,session,"INS","BANTAHAN AGENSI [BIL. BANTAHAN : "+NO_BANTAHAN_temp+", AMAUN DEPOSIT : RM "+AMAUN_TUNTUTAN_temp+"] INSERT");
    		
    		} finally {
    			if (db != null)
    				db.close();
    		}
    	}
    	private void updateStatusUrusanDeposit(String id_permohonan, String usid) throws Exception {
    		modelOperations.updateStatusUrusanDeposit(id_permohonan, usid);
    	}
    	private void daftarBorangO(String usid,String idBorangO,String id_bantahan) throws Exception {
    		String txdTkhBrgO = getParam("txdTkhBrgO");
    		String idPejabatMahkamah = getParam("idPejabatMahkamah");
    		String txdTkhHantarBorangO = getParam("txdTkhHantarBorangO");
    		String txtNamaPenghantarBorangO = getParam("txtNamaPenghantarBorangO");
    		String txtNamaPenerimaBorangO = getParam("txtNamaPenerimaBorangO");
    		modelOperations.daftarBorangO(usid,idBorangO,id_bantahan,txdTkhBrgO,idPejabatMahkamah,
    				txdTkhHantarBorangO,txtNamaPenghantarBorangO,txtNamaPenerimaBorangO);
    	}
    	private void updateBantahan(String usid, String id_bantahan,String id_kementerian) throws Exception {
    	    String txtNoBantahan = getParam("txtNoBantahan");		
    	    String txdTkhMasuk = getParam("txdTkhMasuk");
    	    String txdTkhTerimaBrgN = getParam("txdTkhTerimaBrgN");
    	    String txdBrgN = getParam("txdBrgN");
    	    String txtNoLot = getParam("txtNoLot");
    	    String txtNoPt = getParam("txtNoPt");
    	    String txdTkhBrgH = getParam("txdTkhBrgH");
    	    String txdTkhAward = getParam("txdTkhAward");
    	    String txtNoHakmilik = getParam("txtNoHakmilik");
    	    String txtNama = getParam("txtNama");
    	    String txtAlamat1 = getParam("txtAlamat1");
    	    String txtAlamat2 = getParam("txtAlamat2");
    	    String txtAlamat3 = getParam("txtAlamat3");
    	    String txtPoskod = getParam("txtPoskod");
    	    String txtKptgnAtasTnh = getParam("txtKptgnAtasTnh");
    	    String txtAlasanBantahan = getParam("txtAlasanBantahan");
    	    String sbcBantahan = getParam("sbcBantahan"); 
    	    String socPihakPembantah = getParam("socPihakBantah");  
    	    String idKementerian = id_kementerian;
    	    String idAgensi = getParam("id_agensi");	
    	    String id_hakmilik = getParam("id_hakmilik");
    	    String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
    	    String jenis_pembantah = getParam("jenis_pembantah");	//* 1=PIHAK BERKEPENTINGAN @ 2=AGENSI PEMOHON
    	    String flag_syarat = getParam("flag_syarat");
    	    String ukuran_luas = getParam("ukuran_luas");
    	    String amaun_pampasan = getParam("amaun_pampasan");
    	    String terima_pampasan = getParam("terima_pampasan");
    	    String umpuk_pampasan = getParam("umpuk_pampasan");
    	    String txtAmaunTuntutan = getParam("txtAmaunTuntutan");
    	    
    	    String txtMaklumatBantahanTamat = getParam("txtMaklumatBantahanTamat");
    	    
    	    modelOperations.updateBantahan(id_bantahan,txtNoBantahan,txdTkhMasuk,txdTkhTerimaBrgN,txdBrgN,
    	    		txtNoLot,txtNoPt,txdTkhBrgH,txdTkhAward,txtNoHakmilik,txtNama,txtAlamat1,txtAlamat2,txtAlamat3,
    	    		txtPoskod,txtKptgnAtasTnh,txtAlasanBantahan,sbcBantahan,socPihakPembantah,usid,idKementerian,idAgensi,
    	    		id_hakmilik,id_pihakberkepentingan,jenis_pembantah,flag_syarat,ukuran_luas,
    	    		amaun_pampasan,terima_pampasan,umpuk_pampasan,txtAmaunTuntutan,txtMaklumatBantahanTamat); 
    	}
    	private void simpanLanjutan(String usid,String idBorangO,String id_bantahan) throws Exception {
    		
        	String txdTarikhLanjutanOB = getParam("txdTarikhLanjutanOB");
    		String txdTarikhLanjutanPT = getParam("txdTarikhLanjutanPT");
    		
    		modelOperations.simpanLanjutan(usid,idBorangO,id_bantahan, txdTarikhLanjutanOB, txdTarikhLanjutanPT);
    	}
    	private void simpanSusulan(String usid,String idBorangO,String id_bantahan) throws Exception {
    		String txtNoProsiding = getParam("txtNoProsiding");
    		String sorKeputusanMahkamah = getParam("sorKeputusanMahkamah");
    		String sorStatusPulangDep = getParam("sorStatusPulangDep");
    		String txdTkhPerintah = getParam("txdTkhPerintah");
    		String txdTkhTerimaPerintah = getParam("txdTkhTerimaPerintah");
    		String txtAmaunPampasanBantahan = getParam("txtAmaunPampasanBantahan");
    		String txtKosPengapitHakim = getParam("txtKosPengapitHakim");
    		String txtTempohBayaran = getParam("txtTempohBayaran");
    		String unitTempohBayaran = getParam("unitTempohBayaran");
    		String id_award = getParam("id_award");
    		String txtKeteranganPampasan = getParam("txtKeteranganPampasan");
    		String txtKosJPPH = getParam("txtKosJPPH");
    		String txtNamaJPPH = getParam("txtNamaJPPH");
    		String txtKosSwasta = getParam("txtKosSwasta");
    		String txtNamaSwasta = getParam("txtNamaSwasta");
    		String txtNamaSyarikat = getParam("txtNamaSyarikat");
    		
    		
    		//24022012
    		String txtNoRujukanMahkamah = getParam("txtNoRujukanMahkamah");
    		
    		modelOperations.simpanSusulan(usid,idBorangO,id_bantahan,txtNoProsiding,
    				sorKeputusanMahkamah,sorStatusPulangDep,txdTkhPerintah,txdTkhTerimaPerintah,
    				txtAmaunPampasanBantahan,txtKosPengapitHakim,txtTempohBayaran,unitTempohBayaran,id_award,
    				txtKeteranganPampasan,txtNoRujukanMahkamah,txtKosJPPH,txtNamaJPPH,
    				txtKosSwasta,txtNamaSwasta,txtNamaSyarikat);
    	}
    	private void simpanPemulanganDeposit(String usid,String idBorangO,String id_bantahan) throws Exception {
    		
    		String sorStatusPulangDep = getParam("sorStatusPulangDep");
    		    		
    		modelOperations.simpanPemulanganDeposit(usid,idBorangO,id_bantahan,sorStatusPulangDep);
    	}
//    	private void updateStatusUrusanMahkamah(String id_permohonan, String usid) throws Exception {
//    		modelOperations.updateStatusUrusanMahkamah(id_permohonan,usid);		
//    	}	
    	private void update_deposit(String usid, String id_bantahan, String idBorangO) throws Exception {
    		String txdTkhTerimaResit = getParam("txdTkhTerimaResit");
    		String txdTkhResit = getParam("txdTkhResit");
    		String txtNoResit = getParam("txtNoResit");
    		String txtAmaunResit = getParam("txtAmaunResit");
    		String socCaraBayar = getParam("socCaraBayar");
    		String txtNoCek = getParam("txtNoCek");
    		String txtNoAkaun = getParam("txtNoAkaun");
//    		String socBank = getParam("socBank");
    		String txtNamaBank = getParam("txtNamaBank");
    		String txdTkhPulang = getParam("txdTkhPulang");
    		String socStatusPulang = getParam("socStatusPulang");
    		String txdTkhHantar = getParam("txdTkhHantar");
    		String txtNamaPenghantar = getParam("txtNamaPenghantar");
    		String txtCatatanPlgDeposit = getParam("txtCatatanPlgDeposit");
    		modelOperations.update_deposit(usid,id_bantahan,idBorangO,txdTkhTerimaResit,txdTkhResit,
    				txtNoResit,txtAmaunResit,socCaraBayar,txtNoCek,txtNoAkaun,txtNamaBank,txdTkhPulang,
    				socStatusPulang,txdTkhHantar,txtNamaPenghantar,txtCatatanPlgDeposit);
    		
    		HttpSession session = request.getSession();		 
    		Db db = null;
    		String NO_BANTAHAN_temp = "";
    		String AMAUN_TUNTUTAN_temp = "";
    		try {
    		db = new Db();
    		Statement stmt = db.getStatement();
    		String sql = " SELECT NO_BANTAHAN,TRIM(TO_CHAR(AMAUN_DEPOSIT,'999,999,990.99')) AS AMAUN_TUNTUTAN FROM TBLPPTBANTAHAN" +
    				"" +
    				" WHERE ID_BANTAHAN = '"+id_bantahan+"'";			
    		ResultSet rs = stmt.executeQuery(sql);	
    		myLogger.info("SQL  :"+sql);
    		while (rs.next()){				
    			NO_BANTAHAN_temp = rs.getString("NO_BANTAHAN");	
    			AMAUN_TUNTUTAN_temp = rs.getString("AMAUN_TUNTUTAN");
    	    }
    	    AuditTrail at = new AuditTrail();
    		at.logActivity("","1",null,session,"UPD","BANTAHAN AGENSI [BIL. BANTAHAN : "+NO_BANTAHAN_temp+", AMAUN DEPOSIT : RM "+AMAUN_TUNTUTAN_temp+"] KEMASKINI");
    		
    		} finally {
    			if (db != null)
    				db.close();
    		}
    	}	
    	private void simpanTarikBalik(String usid, String id_bantahan) throws Exception {
    		String txdTkhTerimaSurat = getParam("txdTkhTerimaSurat");
    		String txdTkhSurat = getParam("txdTkhSurat");
    		String txtNoRujSurat = getParam("txtNoRujSurat");
    		modelOperations.simpanTarikBalik(usid,id_bantahan,txdTkhTerimaSurat,txdTkhSurat,txtNoRujSurat);
    	}	
    	private void updateStatusTarikBalik(String id_permohonan, String usid) throws Exception {
    		modelOperations.updateStatusTarikBalik(id_permohonan, usid);		
    	}		
    	private void simpanBatalMahkamah(String usid, String id_bantahan) throws Exception {
    		String txdTkhTerimaSurat = getParam("txdTkhTerimaSurat");
    		String txdTkhSurat = getParam("txdTkhSurat");
    		String txtNoRujSurat = getParam("txtNoRujSurat");
    		String txtCatatanBatalMahkamah = getParam("txtCatatanBatalMahkamah");
    		modelOperations.simpanBatalMahkamah(usid,id_bantahan,txdTkhTerimaSurat,txdTkhSurat,txtNoRujSurat,
    				txtCatatanBatalMahkamah);
    	}
    	private void deposit_tblrujsuburusanstatusbantahan(String usid,String id_bantahan,String id_permohonan,String id_hakmilik,String id_fail) throws Exception {
    		modelOperations.deposit_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);	
    	} 
    	private void urusanMahkamah_tblrujsuburusanstatusbantahan(String usid,String id_bantahan,String id_permohonan,String id_hakmilik,String id_fail) throws Exception {
    		modelOperations.urusanMahkamah_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);	
    	}    	
    	private void tarikBalik_tblrujsuburusanstatusbantahan(String usid,String id_bantahan,String id_permohonan,String id_hakmilik,String id_fail) throws Exception {
    		modelOperations.tarikBalik_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);	
    	}   
    	private void pembatalanOlehMT_tblrujsuburusanstatusbantahan(String usid,String id_bantahan,String id_permohonan,String id_hakmilik,String id_fail) throws Exception {
    		modelOperations.pembatalanOlehMT_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);	
    	} 
    	
    	@SuppressWarnings({ "unchecked", "static-access" })
    	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
        
    		Hashtable h = new Hashtable();
    		
    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_hakmilik", id_hakmilik);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));
    		
    		modelOperations.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"16102106");    
    	}
    	
    	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
        	
    		Hashtable h = new Hashtable();
    	
    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));
    		
    		//update suburusanstatusfailppt
    		modelOperations.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"16102106");
    		
    	}//close updateSuburusanStatusFailPPT
 
    	
    	// UPDATE STATUS URUSAN DEPOSIT AGENSI
    	private void updateStatus(HttpSession session) throws Exception{
        	
    		String id_permohonan = getParam("id_permohonan");
        	String idUser = (String) session.getAttribute("_ekptg_user_id");
        	
        	//status URUSAN DEPOSIT AGENSI
    		String idstatus = "200";
        	
    		modelOperations.updateStatus(id_permohonan,idUser,idstatus);
          
    	}//close update status URUSAN DEPOSIT AGENSI 
    	
    	private void updateSuburusanHakmilik_deposit(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
    	    
    		Hashtable h = new Hashtable();
    		
    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_hakmilik", id_hakmilik);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));
    		
    		modelOperations.updateSuburusanHakmilik_deposit(h,id_suburusanstatushakmilik,"16102107");
    	
    	}//close addSuburusanHakmilik_deposit
    	
    	private void updateSuburusanStatusFailPPT_deposit(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
        	
    		Hashtable h = new Hashtable();
    	
    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));
    		
    		//update suburusanstatusfailppt
    		modelOperations.updateSuburusanStatusFailPPT_deposit(h,id_suburusanstatusfailppt,"16102107");
    		
    	}//close updateSuburusanStatusFailPPT
    	
    	
    	// URUSAN MAHKAMAH
    	private void updateStatus_borangO(HttpSession session) throws Exception{

    		String id_permohonan = getParam("id_permohonan");
    		String idUser = (String) session.getAttribute("_ekptg_user_id");

    		//status BORANG O [AGENSI]
    		String idstatus = "1610249";

    		modelOperations.updateStatus_borangO(id_permohonan,idUser,idstatus);

    	}//close update status borang O					
    					
    					
    	private void updateSuburusanStatusFailPPT_borangO(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

    		Hashtable h = new Hashtable();

    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));

    		//update suburusanstatusfailppt
    		modelOperations.updateSuburusanStatusFailPPT_borangO(h,id_suburusanstatusfailppt,"1610249");

    	}//close updateSuburusanStatusFailPPT borang O



    	private void updateSuburusanHakmilik_borangO(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

    		Hashtable h = new Hashtable();

    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_hakmilik", id_hakmilik);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));

    		modelOperations.updateSuburusanHakmilik_borangO(h,id_suburusanstatushakmilik,"1610249");

    	}//close addSuburusanHakmilik borang O    	
 
    	private void updateSuburusanStatusFailPPT_tarikbalik(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

    		Hashtable h = new Hashtable();

    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));

    		//update suburusanstatusfailppt
    		modelOperations.updateSuburusanStatusFailPPT_tarikbalik(h,id_suburusanstatusfailppt,"16102117");

    	}//close updateSuburusanStatusFailPPT_tarikbalik

    	private void updateSuburusanHakmilik_tarikbalik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

    		Hashtable h = new Hashtable();

    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_hakmilik", id_hakmilik);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));

    		modelOperations.updateSuburusanHakmilik_tarikbalik(h,id_suburusanstatushakmilik,"16102117");

    	}//close addSuburusanHakmilik_tarikbalik	    	
    	
    	private void updateStatus_pembatalanOlehMT(HttpSession session) throws Exception{

    		String id_permohonan = getParam("id_permohonan");
    		String idUser = (String) session.getAttribute("_ekptg_user_id");

    		//status pembatalanOlehMT (AGENSI)
    		String idstatus = "1610235";

    		modelOperations.updateStatus_pembatalanOlehMT(id_permohonan,idUser,idstatus);

    	}//close update status
    					
    					
    					
    	private void updateSuburusanStatusFailPPT_pembatalanOlehMT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

    		Hashtable h = new Hashtable();

    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));

    		//update suburusanstatusfailppt_updateStatus_pembatalanOlehMT
    		modelOperations.updateSuburusanStatusFailPPT_pembatalanOlehMT(h,id_suburusanstatusfailppt,"16102122");

    	}//close updateSuburusanStatusFailPPT_updateStatus_pembatalanOlehMT



    	private void updateSuburusanHakmilik_pembatalanOlehMT(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

    		Hashtable h = new Hashtable();

    		h.put("id_permohonan", id_permohonan);
    		h.put("id_fail", id_fail);
    		h.put("id_hakmilik", id_hakmilik);
    		h.put("id_user", session.getAttribute("_ekptg_user_id"));

    		modelOperations.updateSuburusanHakmilik_pembatalanOlehMT(h,id_suburusanstatushakmilik,"16102122");

    	}//close addSuburusanHakmilik
    	
    	public void insertPopupReg(String nama_class, String tajuk_class,
    			String group, Db db) throws Exception {
    		// Db db = null;
    		try {
    			// db = new Db();
    			Statement stmt = db.getStatement();
    			String sql = " INSERT INTO MODULE ( "
    					+ " MODULE_ID, MODULE_TITLE, MODULE_CLASS,  "
    					+ " MODULE_GROUP, MODULE_DESCRIPTION)  " + " VALUES ('"
    					+ nama_class + "','" + tajuk_class + "','" + nama_class
    					+ "','" + group + "','') ";
    			myLogger.info("REG CLASS :" + sql.toUpperCase());
    			stmt.executeUpdate(sql);

    			sql = " INSERT INTO ROLE_MODULE ( "
    					+ " MODULE_ID, USER_ROLE) "
    					+ " SELECT '"
    					+ nama_class
    					+ "' AS MODULE_ID,NAME AS USER_ROLE FROM ROLE WHERE UPPER(NAME) LIKE '%PPT%'";
    			myLogger.info("REG ROLE CLASS :" + sql.toUpperCase());
    			stmt.executeUpdate(sql);

    		} finally {
    			// if (db != null)
    			// db.close();
    		}
    	}

    	public int checkRegPopup(String class_name, Db db) throws Exception {

    		// Db db = null;
    		int total = 0;
    		String sql = "";
    		ResultSet rs = null;
    		try {
    			// db = new Db();
    			sql = " SELECT COUNT(*) AS CHECK_COUNT FROM ROLE_MODULE WHERE MODULE_ID = '"
    					+ class_name + "'";
    			rs = db.getStatement().executeQuery(sql);
    			if (rs.next()) {
    				total = rs.getInt(1);
    			}
    		} finally {
    			// Close the database connection
    			// if ( db != null ) db.close();
    			// if (rs != null) rs.close();
    		}
    		return total;
    	}


}

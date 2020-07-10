package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Enumeration;
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

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.BantahanDaftar;
import ekptg.model.ppt.BantahanDaftarOnline;
import ekptg.model.ppt.BantahanDaftarOperations;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8BorangFData;
import ekptg.model.ppt.PPTHeader;

public class FrmBantahanSenaraiOnline extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6180324014456793132L;
	static Logger myLogger = Logger.getLogger(FrmBantahanSenaraiOnline.class);
	
	// MODEL
	BantahanDaftar model = new BantahanDaftar();	
	BantahanDaftarOperations modelOperations = new BantahanDaftarOperations();
	FrmUPTSek8BorangFData modelBorangE = new FrmUPTSek8BorangFData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	// MODEL ONLINE
	BantahanDaftarOnline modelOnlinePB = new BantahanDaftarOnline();	
	
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
	
	public String doTemplate2() throws Exception
    {
		HttpSession session = this.request.getSession(); 		
		
    	//get user login detail
		String usid = "";  
   		usid = (String)session.getAttribute("_ekptg_user_id");  
    	userData(usid);
    	String userIdNeg = userData(usid); 
		
    	String vm = "";   	
    	Vector listHeader = null;
    	Vector list = null;     	
    	Vector listA = null;
    	Vector listB = null;
    	Vector listC = null;
    	Vector listD = null;
    	Vector listE = null;
    	Vector listF = null;
//    	Vector listG = null;
    	Vector listH = null;
    	Vector listI = null;
    	Vector listJ = null;   	
    	Vector getDataBorangO = null;
    	Vector view_details_dokumen = null;
    	Vector listDokumen = null;
    	Vector maklumatBantahan = null;   
    	
    	Vector dataBorangE = new Vector();
    	Vector dataNamaPengarah = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	
    	dataNamaPengarah.clear();
    	listMaklumatTanah.clear();
    	
    	String bolehsimpan = "";
    	String doPost = (String)session.getAttribute("doPost");
    	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command");
    	myLogger.info("SUBMIT :: "+submit);
    	this.context.put("Util",new lebah.util.Util());	// UNTUK FORMAT UTIL.DECIMAL (EX: 12,000.00)
   		
//    	String location = getParam("location");
//    	context.put("location",location);   
//    	
//    	String point = getParam("point");
//    	context.put("point",point);  
    	
    	String id_fail = getParam("id_fail");	
   		context.put("id_fail", id_fail);
   		
   		String id_permohonan = getParam("id_permohonan");
   		context.put("id_permohonan", id_permohonan);
   		
   		if(id_permohonan!=null)
   		{
   		Vector list_sub = null;
		list_sub = header.listPerjalananFail(id_permohonan);
		this.context.put("list_sub_header", list_sub);
   		}
   		
   		String id_hakmilik = getParam("id_hakmilik");
   		context.put("id_hakmilik", id_hakmilik);
   		
   		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
   		context.put("id_pihakberkepentingan", id_pihakberkepentingan);
   		
   		String id_hakmilikpb = getParam("id_hakmilikpb");
   		context.put("id_hakmilikpb", id_hakmilikpb);
   		
   		String status_bantahan = getParam("status_bantahan");
   		context.put("status_bantahan", status_bantahan);
   		
   		String id_bantahan = getParam("id_bantahan");
   		context.put("id_bantahan", id_bantahan);
   		
//   		myLogger.info("id_permohonan :: "+id_permohonan);
//   		myLogger.info("id_hakmilik :: "+id_hakmilik);
//   		myLogger.info("id_pihakberkepentingan :: "+id_pihakberkepentingan);
//   		myLogger.info("id_hakmilikpb :: "+id_hakmilikpb);
//   		myLogger.info("status_bantahan :: "+status_bantahan);   		
   		
		listHeader = model.getHeader(id_fail,id_permohonan); 
		context.put("Header", listHeader);
		String idNegeriMhn = "";
		if (listHeader.size()!=0){
			Hashtable h = (Hashtable) listHeader.get(0);
			idNegeriMhn = h.get("id_negeri").toString();
			context.put("idNegeriMhn", idNegeriMhn);
		}
		
		//GET MAX NO SIASATAN
		listH = model.getNoSiasatan(id_permohonan,id_hakmilik);
		context.put("getNoSiasatan", listH);
		String _MaxIdSiasatan = "";
		if(listH.size()!=0){
			Hashtable b = (Hashtable) listH.get(0);
			_MaxIdSiasatan = (String)b.get("id_siasatan");	
		}			
		//END
		
		//GET MAX NO WARTA
		listJ = model.getNoWarta(id_permohonan);
		context.put("getNoSiasatan", listJ);
		String id_warta = "";
		if(listJ.size()!=0){
			Hashtable e = (Hashtable) listJ.get(0);
			id_warta = e.get("id_warta").toString();	
		}		
		//END
	
	
		Hashtable statusFail = model.getStatusFail(id_permohonan);	
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
		//END
		
		// HEADER
		String negeriMMK = "";
    	header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_fail = (String)dh.get("id_fail");
			negeriMMK = (String)dh.get("id_projekNegeri");	
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
	    
    	// GET TOTAL HAKMILIK
		modelUPT.setListMaklumatTanah(id_permohonan,"","");
 		listMaklumatTanah = modelUPT.getListMaklumatTanah();
 		context.put("saiz_listTanah", listMaklumatTanah.size());	
 		
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
		
		String skrinListOnlineInternal = "app/ppt/frmBantahanSenaraiOnlineInternal.jsp";
		String skrinDaftarOnline = "app/ppt/frmBantahanDaftarOnlineInternal.jsp";
	
		
    	if("viewBorangN".equals(submit)){ 
    		
       		// GET MAKLUMAT ONLINE BANTAHAN YG DAH BUAT
    		
			maklumatBantahan = modelOnlinePB.getMaklumatBantahanKaunter(id_permohonan,id_hakmilikpb,id_bantahan);
			context.put("getMaklumatBantahan",maklumatBantahan );
			if (maklumatBantahan.size()!=0 ){				
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
				String flag_pampasan = (String)b.get("flag_pampasan");								
				
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
			
    		//GETHAKMILIK
    		Hashtable getHakmilik =  modelOnlinePB.getHakmilikKaunter(id_hakmilik,id_hakmilikpb);
    		String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
    		id_hakmilikpb = (String) getHakmilik.get("id_hakmilikpb");
    		id_pihakberkepentingan = (String) getHakmilik.get("id_pihakberkepentingan");
    		String no_lotpt = (String) getHakmilik.get("no_lotpt");
    		String nama_pb = (String) getHakmilik.get("nama_pb");
    		String alamat1 = (String) getHakmilik.get("alamat1");
    		String alamat2= (String) getHakmilik.get("alamat2");
    		String alamat3 = (String) getHakmilik.get("alamat3");
    		String poskod = (String) getHakmilik.get("poskod");
    		String id_bandar = (String) getHakmilik.get("id_bandar");
    		String id_negeri = (String) getHakmilik.get("id_negeri");
    		
    		context.put("id_pihakberkepentingan", id_pihakberkepentingan);
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		context.put("txtNoHakmilik", no_hakmilik);
    		context.put("txtNoLot", no_lotpt);
    		context.put("txtNamaPembantah", nama_pb);
    		context.put("txtAlamat1", alamat1);
    		context.put("txtAlamat2", alamat2);
    		context.put("txtAlamat3", alamat3);
    		context.put("txtPoskod", poskod);
    		context.put("id_bandar", id_bandar);
    		context.put("id_negeri", id_negeri);

    		//DROP DOWN
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
    		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px disabled"));
    		
    		//CLEAR
    		context.put("clearForm", "");
    		context.put("mode", "disabled");
    		context.put("button", "view");
    		context.put("flag", "semak");

   		
    		vm = skrinDaftarOnline; 
    		
    	}else if("Kaunter_sahkanPermohonan".equals(submit)){
    		
    		Kaunter_sahkanPermohonan(id_bantahan,usid);
    		
      		// GET MAKLUMAT ONLINE BANTAHAN YG DAH BUAT    		
			maklumatBantahan = modelOnlinePB.getMaklumatBantahanKaunter(id_permohonan,id_hakmilikpb,id_bantahan);
			context.put("getMaklumatBantahan",maklumatBantahan );
			String flag_online = "";
			if (maklumatBantahan.size()!=0 ){				
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
				String flag_pampasan = (String)b.get("flag_pampasan");								
				flag_online = (String)b.get("flag_online");
				
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
			
    		//GETHAKMILIK
    		Hashtable getHakmilik =  modelOnlinePB.getHakmilikKaunter(id_hakmilik,id_hakmilikpb);
    		String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
    		id_hakmilikpb = (String) getHakmilik.get("id_hakmilikpb");
    		id_pihakberkepentingan = (String) getHakmilik.get("id_pihakberkepentingan");
    		String no_lotpt = (String) getHakmilik.get("no_lotpt");
    		String nama_pb = (String) getHakmilik.get("nama_pb");
    		String alamat1 = (String) getHakmilik.get("alamat1");
    		String alamat2= (String) getHakmilik.get("alamat2");
    		String alamat3 = (String) getHakmilik.get("alamat3");
    		String poskod = (String) getHakmilik.get("poskod");
    		String id_bandar = (String) getHakmilik.get("id_bandar");
    		String id_negeri = (String) getHakmilik.get("id_negeri");
    		
    		context.put("id_pihakberkepentingan", id_pihakberkepentingan);
    		context.put("id_hakmilikpb", id_hakmilikpb);
    		context.put("txtNoHakmilik", no_hakmilik);
    		context.put("txtNoLot", no_lotpt);
    		context.put("txtNamaPembantah", nama_pb);
    		context.put("txtAlamat1", alamat1);
    		context.put("txtAlamat2", alamat2);
    		context.put("txtAlamat3", alamat3);
    		context.put("txtPoskod", poskod);
    		context.put("id_bandar", id_bandar);
    		context.put("id_negeri", id_negeri);

    		//DROP DOWN
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
    		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px disabled"));
    		
    		//CLEAR
    		context.put("clearForm", "");
    		context.put("mode", "disabled");
    		context.put("button", "view");
    		context.put("flag", "semak");
    		if(flag_online.equals("2")){
    			context.put("flagOnline", "2");
    		}else{
    			context.put("flagOnline", "");
    		}
    		
    		vm = skrinDaftarOnline;
    		
    	}else if("block_bantahan".equals(submit)){
    		
			//CHECKING SAMAADA PIHAK BERKEPENTINGAN SUDAH BUAT BANTAHAN ATAU TIDAK			
			Hashtable getFlagBantahan =  model.getFlagBantahan(id_hakmilikpb);
			String flag_bantahan = getFlagBantahan.get("flag_bantahan").toString();
			//END			
				if(!flag_bantahan.equals("1")){					
					//SYARAT BANTAHAN: CHECKING 42 HARI DRPD TARIKH AWARD & TARIKH TERIMA BORANG H
					listI = model.getMaklumatChecking(id_permohonan,_MaxIdSiasatan,id_hakmilikpb);
					context.put("getMaklumatChecking", listI);
					if(listI.size()!=0){
						Hashtable c = (Hashtable) listI.get(0);
						String flag_hadir = c.get("flag_hadir").toString();
						if(flag_hadir.equals("1")){
							String tarikh_award = c.get("tarikh_sedia_award").toString();
							context.put("Checking_tarikh", tarikh_award);
							context.put("date", "_award");
						}else{
							String tarikh_terimaborangh = c.get("tarikh_terimaborangh").toString();
							context.put("Checking_tarikh", tarikh_terimaborangh);
							context.put("date", "_borangH");
						}
					}
					//END					
				}
    		
	   		context.put("selectHakmilik",HTML.SelectHakmilik(id_hakmilik,id_pihakberkepentingan,"socHakmilik","style=width:auto disabled tabindex=5 onChange=\"doChangeNoLot();\" "));
    		context.put("selectNoLot",HTML.SelectNoLotByHakmilik(id_hakmilik,"socNoLot","style=width:auto disabled tabindex=7 "));
    		context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah", null, "style=width:auto disabled tabindex=11", null));    
    		context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan, "socPihakBantah", Utils.parseLong(id_pihakberkepentingan), "style=width:auto disabled"));
		
    		listE = model.getSenaraiPB(id_permohonan);
    		context.put("getSenaraiPB", listE);
    		context.put("list_size", listE.size());
//    		setupPagePB(session,action,listE);
    		
    		vm = "app/ppt/frmBantahanPapar.jsp";
    		
    	}else if("cariNamaPB".equals(submit)){
    		
    		String carianNamaPB = getParam("carianNamaPB");
    		context.put("carianNamaPB", carianNamaPB.trim());
    		
    		String carianNoLot = getParam("carianNoLot");
    		context.put("carianNoLot", carianNoLot.trim());
    		
    		listE = model.setCarianPB(id_permohonan,carianNamaPB,carianNoLot);    		
     		context.put("getSenaraiPB", listE);
     		context.put("list_size", listE.size());
//     		setupPagePB(session,action,listE);
 
     		context.put("clearForm", "");
    		
     		vm = "app/ppt/frmBantahanSenaraiPB.jsp";
    	
    	}else if("papar_online".equals(submit)){    	
    		
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeriProjek",Utils.parseLong(userIdNeg),null,"style=width:300px onChange=\"doChangeProjekNegeri();\""));
			context.put("selectBandar", HTML.SelectBandar("socBandar", null, "class=medium "));									
			context.put("selectPihakBantah", HTML.SelectJenisPb("socJenisPB",null,"style=width:300px"));			
			
    		if(userIdNeg!=""){
    			context.put("selectDaerah",HTML.SelectDaerahByNegeri(userIdNeg,"socDaerah",null,null,"id=socDaerah style=width:300px"));
    		}else{
    			context.put("selectDaerah",HTML.SelectDaerah("socDaerah",null,null,"id=socDaerah style=width:300px"));
    		}
			
			context.put("flag", "semak");
			context.put("clearForm", "yes");
			context.put("clear", "");    		
    		
    		vm = "app/ppt/frmBantahanDaftarOnline.jsp";   
    		
		}else if("doChangeProjekNegeri".equals(submit)){
        		
			String id_negeriprojek = getParam("socNegeriProjek");
        		
        	//get and set data
        	getAndSetDataOnchangeNegeri(id_negeriprojek,"new");

	        vm = "app/ppt/frmBantahanDaftarOnline.jsp";
    		
			
		}else if("add_bantahan".equals(submit)){							
			
			if (doPost.equals("true")){
				
				// INSERT TBLPPTBANTAHAN & INSERT TBLPPTSUBURUSANSTATUSBANTAHAN & UPDATE TBLPPTHAKMILIKPB
				daftarBantahan(usid);
				
				// UPDATE TBLPPTPERMOHONAN
				updateStatusDalamProses(id_permohonan,usid);
				
				// UPDATE TBLPPTSUBURUSANHAKMILIK
				updateSuburusanHakmilik(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);
				
				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
				updateSuburusanStatusFailPPT(session,id_permohonan,id_fail,id_suburusanstatusfailppt);
			}
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			context.put("getMaklumatBantahan", list);
			if (list.size()!=0 ){				
				Hashtable b = (Hashtable) list.get(0);
				String idNegeri = (String)b.get("id_negeri");
				String idBandar = (String)b.get("id_bandar");
				String jenis_pembantah = (String)b.get("jenis_pembantah");
				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
				String flag_pampasan = (String)b.get("flag_pampasan");
				String desc_status_bantahan = (String)b.get("desc_status_bantahan");
			
				context.put("desc_status_bantahan", desc_status_bantahan);
				context.put("selectHakmilik", HTML.SelectHakmilik(id_hakmilik,id_pihakberkepentingan, "socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
				context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah",Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));				
				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
				context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
				context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,"socPihakBantah", Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));								
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
				
				//GET TARIKH AWARD & TARIKH BORANG H
				listF = model.getTarikhPenting(id_hakmilikpb);
				context.put("getTarikhPenting",listF);
			
				context.put("flag","semak");
				context.put("mode", "disabled");
				context.put("clearForm", "");
				context.put("button", "view");				
										
			}else{
				context.put("status",true);
			}
			
			vm = "app/ppt/frmBantahanMaster.jsp"; 
		
		}else if(("dalamProses".equals(submit)) || ("bantahan".equals(submit))){	
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
			//CHECKING JUMLAH PAMPASAN SEKSYEN 8
			listA = model.getMaklumatPampasan(id_hakmilikpb);
			if(listA.size()!=0){
				Hashtable d = (Hashtable) listA.get(0);
				double amaun_bayaran = Double.parseDouble(d.get("amaun_bayaran").toString());	
//				myLogger.info("AMAUN BAYARAN >>> "+amaun_bayaran);
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
			
			//CHECKING SAMAADA PIHAK BERKEPENTINGAN SUDAH BUAT BANTAHAN ATAU TIDAK			
			Hashtable getFlagBantahan =  model.getFlagBantahan(id_hakmilikpb);
			String flag_bantahan = getFlagBantahan.get("flag_bantahan").toString();
			//END			
				if(!flag_bantahan.equals("1")){					
					//SYARAT BANTAHAN: CHECKING 42 HARI DRPD TARIKH AWARD & TARIKH TERIMA BORANG H
					listI = model.getMaklumatChecking(id_permohonan,_MaxIdSiasatan,id_hakmilikpb);
					context.put("getMaklumatChecking", listI);
					if(listI.size()!=0){
						Hashtable c = (Hashtable) listI.get(0);
						String flag_hadir = c.get("flag_hadir").toString();
						context.put("flag_hadir", flag_hadir);
						if(flag_hadir.equals("1")){
							String tarikh_award = c.get("tarikh_sedia_award").toString();
							context.put("Checking_tarikh", tarikh_award);
							context.put("date", "_award");
						}else{
							String tarikh_terimaborangh = c.get("tarikh_terimaborangh").toString();
							context.put("Checking_tarikh", tarikh_terimaborangh);
							context.put("date", "_borangH");
						}
					}
					//END					
				}			

			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			context.put("getMaklumatBantahan", list);
			if (list.size()!=0 ){				
				Hashtable b = (Hashtable) list.get(0);
				String idNegeri = b.get("id_negeri").toString();
				String idBandar = b.get("id_bandar").toString();
				String jenis_pembantah = b.get("jenis_pembantah").toString();
//				String id_bantahan = (String)b.get("id_bantahan");
				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
				String flag_pampasan = (String)b.get("flag_pampasan");

				if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
		     		listDokumen = model.senaraiDokumenBantahan(id_bantahan);
		    		context.put("listDokumen", listDokumen);
		    		context.put("listDokumen_size", listDokumen.size());	    		
				}else{				
					context.put("listDokumen", "");
					context.put("listDokumen_size", 0);
				}
				
				if(id_hakmilik!=""){
					context.put("selectHakmilik", HTML.SelectHakmilik(id_hakmilik, id_pihakberkepentingan,"socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
					context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
					context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah",Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));				
					context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
					context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
					context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,"socPihakBantah", Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));								
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
					
					//GET TARIKH AWARD & TARIKH BORANG H
					listF = model.getTarikhPenting(id_hakmilikpb);
					context.put("getTarikhPenting",listF);
					
					// GET MAKLUMAT NO LOT, NO HAKMILIK
					listD = model.getHakmilik(id_hakmilikpb);
					context.put("getHakmilik", listD);
					
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
			
			vm = ("app/ppt/frmBantahanMaster.jsp");		
			
		}else if ("kemaskiniBantahan".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
			//CHECKING JUMLAH PAMPASAN SEKSYEN 8
			listA = model.getMaklumatPampasan(id_hakmilikpb);
			if(listA.size()!=0){
				Hashtable d = (Hashtable) listA.get(0);
				double amaun_bayaran = Double.parseDouble(d.get("amaun_bayaran").toString());
				myLogger.info("AMAUN BAYARAN >>> "+amaun_bayaran);
				//PAMPASAN <= 3000.00 : ALASAN 3 DAN 4 
				if(amaun_bayaran <= 3000.00){
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
			}
			//END
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			context.put("getMaklumatBantahan", list);			
			if(list.size()!=0){
				Hashtable b = (Hashtable) list.get(0);
				String idNegeri = b.get("id_negeri").toString();
				String idBandar = b.get("id_bandar").toString();
				String jenis_pembantah = b.get("jenis_pembantah").toString();
				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
				String flag_pampasan = (String)b.get("flag_pampasan");
				
				context.put("selectHakmilik", HTML.SelectHakmilik(id_hakmilik, id_pihakberkepentingan,"socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled "));
				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
				context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah",Utils.parseLong(id_pihakberkepentingan),"style=width:auto onChange=\"doChangeAlamatPembantah();\" disabled "));				
				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
				context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
				context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,"socPihakBantah", Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));								
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
			listF = model.getTarikhPenting(id_hakmilikpb);
			context.put("getTarikhPenting",listF);
						
			context.put("flag", "semak");
			context.put("mode", "");
			context.put("clearForm", "");
			context.put("button", "edit");
//			context.put("alasan1", "");
//			context.put("alasan2", "");
//			context.put("alasan3", "");
//			context.put("alasan4", "");
			
			vm = ("app/ppt/frmBantahanMaster.jsp");	
			
		}else if ("simpanBantahan".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);			
			try{				
				list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);				
				Hashtable b = (Hashtable) list.get(0);	
//				String id_bantahan = b.get("id_bantahan").toString();
				
				updateBantahan(usid,id_bantahan);	
									
				list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
				context.put("getMaklumatBantahan", list);
				String idNegeri = (String)b.get("id_negeri");
				String idBandar = (String)b.get("id_bandar");
				String jenis_pembantah = (String)b.get("jenis_pembantah");
				
				context.put("selectHakmilik", HTML.SelectHakmilik(id_hakmilik, id_pihakberkepentingan,"socHakmilik", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));
				context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah",Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));				
				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
				context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
				context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan,"socPihakBantah", Utils.parseLong(id_pihakberkepentingan),"style=width:auto disabled"));								
				if (jenis_pembantah.equals("1")){
					setValueJenisPembantah("1","");
				}else{
					setValueJenisPembantah("","2");
				}
				
				context.put("flag", "semak");
				context.put("mode", "disabled");
				context.put("button", "view");
				context.put("clearForm","");	
				
				vm = ("app/ppt/frmBantahanMaster.jsp");	
			
			} catch (Exception e) {
				throw new Exception("PENGEMASKINIAN FAIL TIDAK DAPAT DITERUSKAN.SILA CUBA LAGI. :" +e.getMessage());
			}			
			
			
			
		}else if ("deposit".equals(submit)) {
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			context.put("getMaklumatBantahan", list);

			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
//				String id_bantahan = a.get("id_bantahan").toString();
				double amaun_tuntutan = Double.parseDouble(a.get("amaun_tuntutan").toString());
//				myLogger.info("amaun tuntutan >>>> "+amaun_tuntutan);
				if(id_bantahan!=""){
					
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
					
					listA = model.getMaklumatPampasan(id_hakmilikpb);
					context.put("getMaklumatPampasan", listA);
					
					if (listA.size()!=0){
						Hashtable b =(Hashtable) listA.get(0);
						String cara_bayarPampasan = b.get("cara_bayar").toString();
						context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", Utils.parseLong(cara_bayarPampasan), "style=width:auto tabindex=5 disabled "));
					}else{
						context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan",null, "style=width:auto disabled"));
						context.put("clearForm", "yes");
						context.put("button", "add");
					}
					
					listB = model.getMaklumatDeposit(id_hakmilikpb);
					context.put("getMaklumatDeposit", listB);
					if (listB.size()!=0){
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
			vm = "app/ppt/frmBantahanDeposit.jsp";
			
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
			
			
			listA = model.getMaklumatPampasan(id_hakmilikpb);
			context.put("getMaklumatPampasan", listA);			
			if (listA.size()!=0){
				Hashtable b =(Hashtable) listA.get(0);
				String cara_bayarPampasan = b.get("cara_bayar").toString();
				context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", Utils.parseLong(cara_bayarPampasan), "style=width:auto tabindex=5 disabled "));
			}else{
				context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan",null, "style=width:auto disabled"));
			}						
			//END

			context.put("flag", "");
			context.put("mode", "");
			context.put("clearForm", "");
			context.put("button", "add");
			
			vm = "app/ppt/frmBantahanDeposit.jsp";
			
		}else if("add_deposit".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab",selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			if (list.size()!=0){
				Hashtable b = (Hashtable) list.get(0);
//				String id_bantahan = (String)b.get("id_bantahan");
				String desc_status_bantahan =(String)b.get("desc_status_bantahan");
				
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
				
				context.put("desc_status_bantahan", desc_status_bantahan);
			
				listA = model.getMaklumatPampasan(id_hakmilikpb);
				context.put("getMaklumatPampasan", listA);
				
				if (listA.size()!=0){
					Hashtable d =(Hashtable) listA.get(0);
					String cara_bayarPampasan = (String)d.get("cara_bayar").toString();
					context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", Utils.parseLong(cara_bayarPampasan), "style=width:auto tabindex=5 disabled "));
				}else{
					context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan",null, "style=width:auto disabled"));
					context.put("clearForm", "yes");
					context.put("button", "add");
				}
					
				listB = model.getMaklumatDeposit(id_hakmilikpb);
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
			vm = "app/ppt/frmBantahanDeposit.jsp";
			
		}else if("urusanDeposit".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
//				String id_bantahan = a.get("id_bantahan").toString();
				context.put("id_bantahan", id_bantahan);
				if(id_bantahan!=""){
					
					listA = model.getMaklumatPampasan(id_hakmilikpb);
					context.put("getMaklumatPampasan", listA);									
					if (listA.size()!=0){
						Hashtable b =(Hashtable) listA.get(0);
						String cara_bayarPampasan = b.get("cara_bayar").toString();
						context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", Utils.parseLong(cara_bayarPampasan), "style=width:auto tabindex=5 disabled "));
					}else{
						context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", null, "style=width:auto tabindex=5 disabled "));
					}
					
					listB = model.getMaklumatDeposit(id_hakmilikpb);
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
			vm = "app/ppt/frmBantahanDeposit.jsp";
			
		}else if("kemaskiniDeposit".equals(submit)){
			
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
			
				listA = model.getMaklumatPampasan(id_hakmilikpb);
				context.put("getMaklumatPampasan", listA);	
				if (listA.size()!=0){
					Hashtable b =(Hashtable) listA.get(0);
					String cara_bayarPampasan = (String) b.get("cara_bayar").toString();
//					String id_bankPampasan = (String) b.get("id_bank").toString();
					context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", Utils.parseLong(cara_bayarPampasan), "style=width:auto tabindex=5 disabled "));
//					context.put("selectBankPampasan", HTML.selectBank("socBankPampasan", Utils.parseLong(id_bankPampasan), "style=width:auto tabindex=7 disabled "));
				}
				
				listB = model.getMaklumatDeposit(id_hakmilikpb);
				context.put("getMaklumatDeposit", listB);				
				if (listB.size()!=0){
					Hashtable c = (Hashtable) listB.get(0);
					String cara_bayar = (String) c.get("cara_bayar").toString();
					String id_bank = (String) c.get("id_bank").toString();
					context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Utils.parseLong(cara_bayar), "style=width:auto onChange=\"doChangeCaraBayaranUpdate();\" ", null));
//					context.put("selectBank",HTML.SelectBank("socBank", Utils.parseLong(id_bank), "style=width:auto "));
					context.put("flag", "semak");
					context.put("mode", "disabled");
					context.put("button", "view");
				}else{
					context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", null, "style=width:auto onChange=\"doChangeCaraBayaran();\" "));
//					context.put("selectBank", HTML.SelectBank("socBank", null, "style=width:auto"));						
					context.put("flag", "");
					context.put("mode", "");
					context.put("clearForm", "yes");
					context.put("button", "add");
				}	
			}

			context.put("flag","semak");
			context.put("mode","");
			context.put("button", "edit");
			
			vm = "app/ppt/frmBantahanDeposit.jsp";
			
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
			
			listA = model.getMaklumatPampasan(id_hakmilikpb);
			context.put("getMaklumatPampasan", listA);			
			if (listA.size()!=0){
				Hashtable b =(Hashtable) listA.get(0);
				String cara_bayarPampasan = b.get("cara_bayar").toString();
//				String id_bankPampasan = b.get("id_bank").toString();
				context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", Utils.parseLong(cara_bayarPampasan), "style=width:auto tabindex=5 disabled "));
//				context.put("selectBankPampasan", HTML.selectBank("socBankPampasan", Utils.parseLong(id_bankPampasan), "style=width:auto tabindex=7 disabled "));
			}else{
				context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan",null, "style=width:auto disabled"));
//				context.put("selectBankPampasan", HTML.selectBank("socBankPampasan", null, "style=width:auto disabled"));
			}						
			//END

			context.put("flag", "semak");
			context.put("mode", "");
			context.put("clearForm", "");
			context.put("button", "edit");
			
			vm = "app/ppt/frmBantahanDeposit.jsp";			
			
		}else if("update_deposit".equals(submit)){
			selectedtab = "1";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
//				String id_bantahan = (String) a.get("id_bantahan");
				
				Hashtable getIdBorangO = model.getIdBorangO(id_hakmilikpb,id_bantahan);	
				String idBorangO = getIdBorangO.get("id_borango").toString();
				
				update_deposit(usid,id_bantahan,idBorangO);		
				
				listA = model.getMaklumatPampasan(id_hakmilikpb);
				context.put("getMaklumatPampasan", listA);	
				if (listA.size()!=0){
					Hashtable b =(Hashtable) listA.get(0);
					String cara_bayarPampasan = (String) b.get("cara_bayar").toString();
//					String id_bankPampasan = (String) b.get("id_bank").toString();
					context.put("selectCaraBayarPampasan", HTML.selectCaraBayar("socCaraBayarPampasan", Utils.parseLong(cara_bayarPampasan), "style=width:auto tabindex=5 disabled "));
//					context.put("selectBankPampasan", HTML.selectBank("socBankPampasan", Utils.parseLong(id_bankPampasan), "style=width:auto tabindex=7 disabled "));
				}
				
				listB = model.getMaklumatDeposit(id_hakmilikpb);
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
			
			vm = "app/ppt/frmBantahanDeposit.jsp";
			
		}else if("borangO".equals(submit)){		
			
			selectedtab = "2";
			context.put("selectedtab",selectedtab);
			
	    	id_fail = getParam("id_fail");	
	   		context.put("id_fail", id_fail);
	
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			context.put("getMaklumatBantahan", list);
//			String id_bantahan = "";
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);		
				id_bantahan = (String)a.get("id_bantahan");
			}else{
				context.put("status", true);
			}				
		
			Hashtable checkingIdMahkamah = model.getCheckingIdMahkamah(id_hakmilikpb,id_bantahan);	
			String _cIdMahkamah = "";			
			if (checkingIdMahkamah.size()!=0){
				_cIdMahkamah = checkingIdMahkamah.get("id_mahkamah").toString();			
			}
			if ( _cIdMahkamah == "" ){				//				
				String id_pejabat = getParam("socMahkamahTinggi");
				
				if (idNegeriMhn!=""){	
//					myLogger.info("testing >>> "+idNegeriMhn);
					context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", null, "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));					
				}else{	
					context.put("selectMahkamahTinggi", HTML.SelectMahkamah("socMahkamahTinggi", null, "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
				}
					
//			if(idNegeriMhn != "16"){
//			if(idNegeriMhn.equals("16")){
//				myLogger.info("CASE: PUTRAJAYA >>>>> ");
//				context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", null, "style=width:auto onChange=\"doChangeNegeriMahkamah();\"  "));
//			}else{	
//				context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeriMahkamah", null, "style=width:auto disabled "));
//			}
				
				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandarMahkamah", null, "style=width:auto disabled "));				
				context.put("mode", "");
				context.put("clearForm", "yes");
				context.put("flag", "");
				context.put("button", "edit");
				
			}else{
				
//				myLogger.info("ADA MAKLUMAT BORANG O >>>> ");
				Hashtable getIdBorangO = model.getIdBorangO(id_hakmilikpb,id_bantahan);	
				String idBorangO = getIdBorangO.get("id_borango").toString();
				
				getDataBorangO = model.getDataBorangO(idBorangO);
				context.put("getDataBorangO", getDataBorangO);
				
				Hashtable b = (Hashtable) getDataBorangO.get(0);
				String id_mahkamah = b.get("id_mahkamah").toString();
				String id_bandar = b.get("id_bandar").toString();
				String id_negeri = b.get("id_negeri").toString();
				String tarikhBorangO = b.get("tarikh_borango").toString();
				String checking6BulanBorangO = model.checking6BulanBorangO(tarikhBorangO);
				
				// CONVERT STRING TO INT
				int BorangODiffInt = Integer.parseInt(checking6BulanBorangO);	
//				myLogger.info("diff days...."+BorangODiffInt);
				
				//CHECKING DARI TARIKH TERIMA BORANG O PERLU DIPROSES DLM TEMPOH 6 BULAN
				//CHECKING DIKIRA DARI BULAN KE-5 (140 HARI)
				if(BorangODiffInt > 139){					
					context.put("statusBorangO", "true");
				}else{
					context.put("statusBorangO", "");
				}
				
				if(!idNegeriMhn.equals("16")){
					context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeri), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto disabled "));
					context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
					context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));										
				}else{
//					myLogger.info("test .....");
					context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(id_negeri), "style=width:auto disabled onChange=\"doChangeNegeriMahkamah();\"  "));
					context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeri), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto disabled onChange=\"doChangeAlamatMahkamah();\" "));
					context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
				}
			
				context.put("flag", "semak");
				context.put("mode", "disabled");
				context.put("clearForm", "");
				context.put("button","view");
	
			}			
		
			vm = "app/ppt/frmBantahanBorangO.jsp";
			
			
		}else if("doChangeNegeriMahkamah".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab", selectedtab);
			
			String id_negeriMahkamah = getParam("socNegeriMahkamah");
			context.put("id_negeriMahkamah", id_negeriMahkamah);
//			myLogger.info("ID Negeri Mahkamah = "+id_negeriMahkamah);
			
			context.put("clearForm", "");
			context.put("mode", "");
			context.put("flag", "");
			context.put("button", "edit");
			
			context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(id_negeriMahkamah), "style=width:auto onChange=\"doChangeNegeriMahkamah();\"  "));
			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriMahkamah), "socMahkamahTinggi", null, "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
			context.put("selectBandarMahkamah", HTML.SelectBandar("socBandarMahkamah", null, "style=width:auto disabled "));
			
			vm = "app/ppt/frmBantahanBorangO.jsp";
			
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
			
//			myLogger.info("id negerimhn ----- "+idNegeriMhn);
//			myLogger.info("id_negeriMahkamah ----- "+id_negeriMahkamah);
			
			if(!idNegeriMhn.equals("16")){
//				myLogger.info("negeri mohon.....");
				
				context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(idNegeriMhn), "style=width:auto disabled  "));
				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_pejabat), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
				listC = model.getAlamatMahkamah(idNegeriMhn,id_pejabat);
			}else{
//				myLogger.info("negeri mahkamah.....");
				
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
			vm = "app/ppt/frmBantahanBorangO.jsp";
			
		}else if ("simpanBorangO".equals(submit)){
			try{
			selectedtab = "2";
			context.put("selectedtab", selectedtab);

			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);	
//			String id_bantahan = "";
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
				id_bantahan = a.get("id_bantahan").toString();
			}else{
				context.put("status",true);
			}
			
			
			Hashtable getIdBorangO = model.getIdBorangO(id_hakmilikpb,id_bantahan);	
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
			
			getDataBorangO = model.getDataBorangO(idBorangO);
			context.put("getDataBorangO", getDataBorangO);
			
			Hashtable b = (Hashtable) getDataBorangO.get(0);
			String id_mahkamah = (String)b.get("id_mahkamah");
			String id_bandar = (String)b.get("id_bandar");
			String id_negeri = (String)b.get("id_negeri");
			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto disabled "));
			context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
			context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));			
			
			context.put("flag", "semak");
			context.put("mode", "disabled");
			context.put("button", "view");
			context.put("clearForm", "");
			
			} catch (Exception e) {
				throw new Exception(" PERMOHONAN BANTAHAN TIDAK DAPAT DITERUSKAN. :" +e.getMessage());
			}			
			
			vm = "app/ppt/frmBantahanBorangO.jsp";
			
		}else if("kemaskiniBorangO".equals(submit)){
			
			selectedtab = "2";
			context.put("selectedtab",selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);	
//			String id_bantahan = "";
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);	
				id_bantahan = (String)a.get("id_bantahan");
			}else{
				context.put("status", true);
			}				
			
			Hashtable getIdBorangO = model.getIdBorangO(id_hakmilikpb,id_bantahan);	
			String idBorangO = getIdBorangO.get("id_borango").toString();
			getDataBorangO = model.getDataBorangO(idBorangO);
			context.put("getDataBorangO", getDataBorangO);
			
			Hashtable b = (Hashtable) getDataBorangO.get(0);
			String id_mahkamah = (String)b.get("id_mahkamah");
			String id_bandar = (String)b.get("id_bandar");
			String id_negeri = (String)b.get("id_negeri");
//			context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
//			context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
//			context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));			
			
			if(!idNegeriMhn.equals("16")){
				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(idNegeriMhn), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
				context.put("selectNegeriMahkamah", HTML.SelectNegeri("socNegeri", Utils.parseLong(id_negeri), "style=width:auto disabled "));			
			}else{
//				myLogger.info("daerah mohon putrajaya .....");
				context.put("selectNegeriMahkamah", HTML.SelectNegeriByMahkamah("socNegeriMahkamah", Utils.parseLong(id_negeri), "style=width:auto onChange=\"doChangeNegeriMahkamah();\"  "));
				context.put("selectMahkamahTinggi", HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeri), "socMahkamahTinggi", Utils.parseLong(id_mahkamah), "style=width:auto onChange=\"doChangeAlamatMahkamah();\" "));
				context.put("selectBandarMahkamah", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:auto disabled "));
			}
			
			context.put("flag", "semak");
			context.put("button","edit");
			context.put("mode", "");
			vm = "app/ppt/frmBantahanBorangO.jsp";
			
		}else if("susulanBantahan".equals(submit)){	
			
			selectedtab = "3";
			context.put("selectedtab",selectedtab);
			
			Hashtable getIdAward = model.getIdAward(id_hakmilikpb);	
			String id_award = "";
			if (getIdAward.size()!=0){
				id_award = getIdAward.get("id_award").toString();
			}
			context.put("id_award", id_award);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);			
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
//				String id_bantahan = a.get("id_bantahan").toString();
//				context.put("id_bantahan", id_bantahan);
//				myLogger.info("id bantahan >>> "+id_bantahan);
				
				Hashtable getKeteranganPampasan = model.getKeteranganPampasan(id_hakmilikpb);
				String keterangan_pampasan = "";
				if (getKeteranganPampasan.size()!=0){
					keterangan_pampasan = getKeteranganPampasan.get("keterangan").toString();
				}
				context.put("txtKeteranganPampasan", keterangan_pampasan);
				
				listA = model.getMaklumatSusulan(id_bantahan);
				context.put("getMaklumatSusulan", listA);
				if (listA.size()!=0){					
					Hashtable b = (Hashtable) listA.get(0);
					String keputusan_mahkamah = b.get("keputusan_mahkamah").toString();
					String flag_pulang_deposit = b.get("flag_pulang_deposit").toString();						
					String no_rujukan_tanah = b.get("no_rujukan_tanah").toString();		
//					myLogger.info("NO RUJUKAN TANAH >> "+no_rujukan_tanah);				
					if (!no_rujukan_tanah.equals("")){
						String tarikh_terimaPerintah = lebah.util.Util.getDateTime((Date)b.get("tarikhTerimaPerintah_convert"), "dd/MM/yyyy");
						String checkingDateTerimaPerintah =  model.checkingDateTerimaPerintah(tarikh_terimaPerintah);							
						// CONVERT STRING TO INT
						int PerintahDateDiffInt = Integer.parseInt(checkingDateTerimaPerintah);						
//						myLogger.info("PerintahDateDiffInt >> "+PerintahDateDiffInt);
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

			vm = "app/ppt/frmBantahanSusulan.jsp";	
			
		}else if ("kemaskiniSusulan".equals(submit)){
			selectedtab = "3";
			context.put("selectedtab", selectedtab);
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);			
			if (list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
//				String id_bantahan = a.get("id_bantahan").toString();
				
				listA = model.getMaklumatSusulan(id_bantahan);
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

			vm = "app/ppt/frmBantahanSusulan.jsp";
		
		}else if("simpanSusulan".equals(submit)){			
			
			selectedtab = "3";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
//			String id_bantahan = "";
			String desc_status_bantahan = "";
			if(list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
				id_bantahan = (String)a.get("id_bantahan");
				desc_status_bantahan = (String)a.get("desc_status_bantahan");
			}else{
				context.put("status", true);
			}
			
			
			Hashtable getIdBorangO = model.getIdBorangO(id_hakmilikpb,id_bantahan);	
			String idBorangO = getIdBorangO.get("id_borango").toString();
			
			if (doPost.equals("true")){
				simpanSusulan(usid,idBorangO,id_bantahan,id_hakmilikpb);
				updateStatusUrusanMahkamah(id_permohonan,usid);
				urusanMahkamah_tblrujsuburusanstatusbantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
			}
			
			// UPDATE STATUS SEMASA BANTAHAN
			context.put("desc_status_bantahan", desc_status_bantahan);
			
			listA = model.getMaklumatSusulan(id_bantahan);
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

			vm = "app/ppt/frmBantahanSusulan.jsp";
			
		
		}else if("tarikBalikBantahan".equals(submit)){
			
			selectedtab = "4";
			context.put("selectedtab", selectedtab);
			
//			if ( (status_bantahan.equals("184")) || (status_bantahan.equals("220")) ){
//				myLogger.info("TAK BOLEH BT TARIK BALIK!");
//				context.put("button", "");
//				context.put("clearForm", "yes");
//				context.put("mode", "disabled");
//				context.put("flag","");
//			}else{
				
//				myLogger.info("SELAIN STATUS 184");				
				list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);	
//				String id_bantahan = "";
				String desc_status_bantahan = "";
				if(list.size()!=0){
					Hashtable a = (Hashtable) list.get(0);
					id_bantahan = (String)a.get("id_bantahan");
					desc_status_bantahan = (String)a.get("desc_status_bantahan");
					context.put("desc_status_bantahan", desc_status_bantahan);
				}else{
					context.put("status", true);
				}		
				
				if( id_bantahan.equals("") || id_bantahan.equals(null) ){	
					
					context.put("button", "edit");
					context.put("clearForm", "yes");
					context.put("mode", "");
					context.put("flag","");		
					
				}else{		
					
					listA = model.getMaklumatTarikBalik(id_bantahan);
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

//			}

			vm = "app/ppt/frmBantahanTarikBalik.jsp";
			
		}else if("simpanTarikBalik".equals(submit)){
			
			selectedtab = "4";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);	
//			String id_bantahan = "";
			String desc_status_bantahan = "";
			if(list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
				id_bantahan = (String)a.get("id_bantahan");
				desc_status_bantahan = (String) a.get("desc_status_bantahan");
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
			context.put("desc_status_bantahan", desc_status_bantahan);
		
			listA = model.getMaklumatTarikBalik(id_bantahan);
			context.put("getMaklumatTarikBalik", listA);
			
			context.put("flag","semak");
			context.put("clearForm", "");
			context.put("button", "view");
			context.put("mode","disabled");
			
			vm = "app/ppt/frmBantahanTarikBalik.jsp";			
			
		}else if ("kemaskiniTarikBalik".equals(submit)){
			selectedtab = "4";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
//			String id_bantahan = "";
			if(list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
				id_bantahan = (String)a.get("id_bantahan");
			}else{
				context.put("status", true);
			}
						
			listA = model.getMaklumatTarikBalik(id_bantahan);
			context.put("getMaklumatTarikBalik",listA);
			
			context.put("flag","semak");
			context.put("button","edit");
			context.put("mode", "");
			context.put("clearForm", "");
			
			vm = "app/ppt/frmBantahanTarikBalik.jsp";
			
		}else if("batalBantahan".equals(submit)){
			
			selectedtab = "5";
			context.put("selectedtab", selectedtab);
			
//			if (status_bantahan.equals("184")){				
//				myLogger.info("TAK BOLEH BT BATAL MAHKAMAH!");
//				context.put("button", "");
//				context.put("clearForm", "yes");
//				context.put("mode", "disabled");
//				context.put("flag","");
//			}else{				
//				myLogger.info("SELAIN STATUS 184");	
				list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
//				String id_bantahan = "";
				String desc_status_bantahan = "";
				if(list.size()!=0){
					Hashtable a = (Hashtable) list.get(0);
					id_bantahan = (String)a.get("id_bantahan");
					desc_status_bantahan = (String)a.get("desc_status_bantahan");
					context.put("desc_status_bantahan", desc_status_bantahan);
				}else{
					context.put("status", true);
				}		
				
				if( id_bantahan.equals("") || id_bantahan.equals(null) ){	
					
					context.put("button", "edit");
					context.put("clearForm", "yes");
					context.put("mode", "");
					context.put("flag","");		
					
				}else{		
					
					listA = model.getMaklumatBatalMahkamah(id_bantahan);
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
//			}

			vm = "app/ppt/frmBantahanPembatalan.jsp";
			
		}else if("simpan_batalMahkamah".equals(submit)){
			
			selectedtab = "5";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);	
//			String id_bantahan = "";
			String desc_status_bantahan = "";
			if(list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
				id_bantahan = (String)a.get("id_bantahan");
				desc_status_bantahan = (String)a.get("desc_status_bantahan");
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
			context.put("desc_status_bantahan", desc_status_bantahan);
			
			listA = model.getMaklumatBatalMahkamah(id_bantahan);
			context.put("getMaklumatBatalMahkamah", listA);
			
			context.put("flag","semak");
			context.put("clearForm", "");
			context.put("button", "view");
			context.put("mode","disabled");
			
			vm = "app/ppt/frmBantahanPembatalan.jsp";
			
		}else if("kemaskiniBatalBantahan".equals(submit)){
			
			selectedtab = "5";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
//			String id_bantahan = "";
			if(list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
				id_bantahan = (String)a.get("id_bantahan");
			}else{
				context.put("status", true);
			}
						
			listA = model.getMaklumatBatalMahkamah(id_bantahan);
			context.put("getMaklumatBatalMahkamah",listA);
			
			context.put("flag","semak");
			context.put("button","edit");
			context.put("mode", "");
			context.put("clearForm", "");
			
			vm = "app/ppt/frmBantahanPembatalan.jsp";
		
		}else if("upload_dokumen".equals(submit)){

			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
//			String id_bantahan = "";
			if(list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
				id_bantahan = (String)a.get("id_bantahan");
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
	     		listDokumen = model.senaraiDokumenBantahan(id_bantahan);
	    		context.put("listDokumen", listDokumen);
	    		context.put("listDokumen_size", listDokumen.size());	    		
			}else{				
				context.put("listDokumen", "");
				context.put("listDokumen_size", 0);
			}
    
			vm = "app/ppt/frmBantahanDokumen.jsp";
			
		}else if("kemaskini_dokumen".equals(submit)){
			
			String id_dokumen = getParam("id_dokumen");
			view_details_dokumen = model.view_details_dokumen(id_dokumen);	
			
			context.put("view_details_dokumen",view_details_dokumen);
			this.context.put("readmode", "edit");
			
			vm = "app/ppt/frmBantahanDokumen.jsp";
			
		}else if("hapus_dokumen_papar".equals(submit)){
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
			context.put("getMaklumatBantahan", list);
//			String id_bantahan = "";
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
	     		listDokumen = model.senaraiDokumenBantahan(id_bantahan);
	    		context.put("listDokumen", listDokumen);
	    		context.put("listDokumen_size", listDokumen.size());	    		
			}else{				
				context.put("listDokumen", "");
				context.put("listDokumen_size", 0);
			}
            
            vm = "app/ppt/frmBantahanDokumen.jsp";	
  
            
		}else if("tambah_dokumen".equals(submit)){
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);	
			context.put("getMaklumatBantahan", list);
//			String id_bantahan = "";
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
	     		listDokumen = model.senaraiDokumenBantahan(id_bantahan);
	    		context.put("listDokumen", listDokumen);
	    		context.put("listDokumen_size", listDokumen.size());	    		
			}else{				
				context.put("listDokumen", "");
				context.put("listDokumen_size", 0);
			}
            
           	vm = "app/ppt/frmBantahanDokumen.jsp";
		
        
		}else if("view_Dokumen_Details".equals(submit)){
			
			String id_dokumen = getParam("id_dokumen");			
			view_details_dokumen = model.view_details_dokumen(id_dokumen);				
			context.put("view_details_dokumen",view_details_dokumen);			
			this.context.put("readmode", "view");
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);
//			String id_bantahan = "";
			if(list.size()!=0){
				Hashtable a = (Hashtable) list.get(0);
				id_bantahan = (String)a.get("id_bantahan");
			}else{
				context.put("status", true);
			}
			
           if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
	     		listDokumen = model.senaraiDokumenBantahan(id_bantahan);
	    		context.put("listDokumen", listDokumen);
	    		context.put("listDokumen_size", listDokumen.size());	    		
			}else{				
				context.put("listDokumen", "");
				context.put("listDokumen_size", 0);
			}			
			
			vm = "app/ppt/frmBantahanDokumen.jsp";					
			
		}else if("hapus_dokumen".equals(submit)){
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);			
//			String id_bantahan = "";
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
	     		listDokumen = model.senaraiDokumenBantahan(id_bantahan);
	    		context.put("listDokumen", listDokumen);
	    		context.put("listDokumen_size", listDokumen.size());	    		
			}else{				
				context.put("listDokumen", "");
				context.put("listDokumen_size", 0);
			}

			vm = "app/ppt/frmBantahanDokumen.jsp";	
			
			
			
		}else if("hapusDokumenMaster".equals(submit)){
			
			selectedtab = "0";
			context.put("selectedtab", selectedtab);
			
			list = model.getMaklumatBantahan(id_hakmilikpb,_MaxIdSiasatan,id_warta);			
//			String id_bantahan = "";
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
	     		listDokumen = model.senaraiDokumenBantahan(id_bantahan);
	    		context.put("listDokumen", listDokumen);
	    		context.put("listDokumen_size", listDokumen.size());	    		
			}else{				
				context.put("listDokumen", "");
				context.put("listDokumen_size", 0);
			}
            
			vm = "app/ppt/frmBantahanMaster.jsp";	
			
		
			
		}else if("batal_dokumen".equals(submit)){
			
			String id_dokumen = getParam("id_dokumen");
			view_details_dokumen = model.view_details_dokumen(id_dokumen);				
			context.put("view_details_dokumen",view_details_dokumen);
			this.context.put("readmode", "edit");
			
			vm = "app/ppt/frmBantahanDokumen.jsp";	
			
		}else if("kembali".equals(submit)){	
			
			listE = model.getSenaraiPB(id_permohonan);
			context.put("getSenaraiPB", list);
    		if (listE.size()!=0){
    			context.put("selectHakmilik",HTML.SelectHakmilik(id_hakmilik,id_pihakberkepentingan,"socHakmilik","style=width:auto tabindex=5 onChange=\"doChangeNoLot();\""));
    		}
			
    		context.put("selectPihakBantah", HTML.SelectPihakBantah("socPihakBantah", null,"class=medium tabindex=6 disabled"));
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=medium disabled "));
    		context.put("selectBandar",HTML.SelectBandar("socBandar",null,"class=medium disabled"));	
    		context.put("selectHakmilik",HTML.SelectHakmilik(id_hakmilik,id_pihakberkepentingan,"socHakmilik","style=width:auto tabindex=5 onChange=\"doChangeNoLot();\""));
    		context.put("selectNoLot",HTML.SelectNoLotByHakmilik(null,"socNoLot","style=width:auto tabindex=7 disabled"));
    		context.put("selectNamaPembantah", HTML.SelectNamaPembantahByHakmilik(null, "socNamaPembantah", null, "style=width:auto tabindex=11 disabled", null));   		
    		
//    		setupPagePB(session,action,listE);
    		
			vm = "app/ppt/frmBantahanDaftar.jsp";				
			
		}else if("doChangeAlamatPembantah".equals(submit)){
			
			//GET & POST VALUE IN FIELDS CONTENTS					
    		if (getParam("sbcBantahan").equals("1")){
    			setValuesbcBantahan("checked","","","");
			} else if (getParam("sbcBantahan").equals("2")){
				setValuesbcBantahan("","checked","","");
			} else if (getParam("sbcBantahan").equals("3")){
				setValuesbcBantahan("","","checked","");
			} else if (getParam("sbcBantahan").equals("4")){
				setValuesbcBantahan("","","","checked");
			}
			context.put("TEMPchecked1",checkedsbcBantahan1);
			context.put("TEMPchecked2",checkedsbcBantahan2);
			context.put("TEMPchecked3",checkedsbcBantahan3);
			context.put("TEMPchecked4",checkedsbcBantahan4);													
			String jenis_pembantah = getParam("jenis_pembantah");			
			if (getParam("jenis_pembantah").equals("1")){
				setValueJenisPembantah("1","");
			}else{
				setValueJenisPembantah("","2");
			}			
			//END			
			id_hakmilik = getParam("socHakmilik");
			id_pihakberkepentingan = getParam("socNamaPembantah");
			if(id_hakmilik!=""){
				context.put("selectHakmilik",HTML.SelectHakmilik(id_hakmilik,id_pihakberkepentingan,"socHakmilik",Utils.parseLong(id_hakmilik),"style=width:auto onChange=\"doChangeNoLot();\" "));
				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(id_hakmilik, "socNoLot", Utils.parseLong(id_hakmilik), "style=width:auto disabled"));			
			}else{
				context.put("selectHakmilik",HTML.SelectHakmilik(id_hakmilik,id_pihakberkepentingan,"socHakmilik","style=width:auto disabled onChange=\"doChangeNoLot();\" "));
				context.put("selectNoLot", HTML.SelectNoLotByHakmilik(null, "socNoLot",null, "style=width:auto disabled"));			
			}			
			if(id_pihakberkepentingan!=""){
				context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan, "socPihakBantah", Utils.parseLong(id_pihakberkepentingan), "style=width:auto disabled"));
				context.put("selectNamaPembantah", HTML.SelectNamaPembantahByHakmilik(id_hakmilik, "socNamaPembantah", Utils.parseLong(id_pihakberkepentingan), "style=width:auto onChange=\"doChangeAlamatPembantah();\" "));
			}else{
				context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(null, "socPihakBantah", null, "style=width:auto disabled"));
				context.put("selectNamaPembantah", HTML.SelectNamaPembantahByHakmilik(null, "socNamaPembantah", null, "style=width:auto onChange=\"doChangeAlamatPembantah();\" "));
			}						
			listC = model.getAlamatPembantah(id_pihakberkepentingan);
			context.put("getAlamatPembantah", listC);
			if(listC.size()!=0){
				Hashtable a = (Hashtable) listC.get(0);   
				String idNegeri = a.get("id_negeri").toString();
				if (idNegeri!=""){
					context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
				}else{
					context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=medium disabled"));
				}
				String idBandar = a.get("id_bandar").toString();
				if (idBandar!=""){
					context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
				}else{
					context.put("selectBandar", HTML.SelectBandar("socBandar", null, "class=medium disabled"));
				}				
			}	
			id_hakmilikpb = getParam("id_hakmilikpb");
			listD = model.getHakmilik(id_hakmilikpb);
			context.put("getHakmilik", listD);
			context.put("flag", "semak");
			context.put("clearForm", "");
			context.put("clear", "");
			vm = "app/ppt/frmBantahanDaftar.jsp";
			
		} else if ("batal".equals(submit)){	
			
    		listE = model.getSenaraiPB(id_permohonan);
    		if (listE.size()!=0){
    			context.put("selectHakmilik",HTML.SelectHakmilik(id_hakmilik,id_pihakberkepentingan,"socHakmilik","style=width:auto tabindex=5 onChange=\"doChangeNoLot();\""));
    			context.put("selectNoLot",HTML.SelectNoLotByHakmilik(id_hakmilik,"socNoLot","style=width:auto tabindex=7"));
    			context.put("selectNamaPembantah", HTML.SelectNamaPembantahByIdPihakBerkepentingan(id_hakmilikpb, "socNamaPembantah", null, "style=width:auto tabindex=11", null));
    			context.put("selectPihakBantah", HTML.SelectPihakBantahByIdPihakBerkepentingan(id_pihakberkepentingan, "socPihakBantah", Utils.parseLong(id_pihakberkepentingan), "style=width:auto "));
    		}else{
    			context.put("selectHakmilik",HTML.SelectHakmilik(id_hakmilik,id_pihakberkepentingan,"socHakmilik","style=width:auto onChange=\"doChangeNoLot();\""));
    			context.put("selectNoLot",HTML.SelectNoLotByHakmilik(null,"socHakmilik","style=width:auto disabled"));
    			context.put("selectNamaPembantah", HTML.SelectNamaPembantahByHakmilik(null, "socNamaPembantah"));
    			context.put("selectPihakBantah", HTML.SelectPihakBantah("socPihakBantah", null,"class=medium"));
    		}
			
    		//GET ALAMAT NAMA PEMBANTAH
			listC = model.getAlamatPembantah(id_pihakberkepentingan);
			context.put("getAlamatPembantah", listC);
			if(listC.size()!=0){
				Hashtable a = (Hashtable) listC.get(0);   
				String idNegeri = a.get("id_negeri").toString();
				if (idNegeri!=""){
					context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"class=medium disabled"));
				}else{
					context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=medium disabled"));
				}
				String idBandar = a.get("id_bandar").toString();
				if (idBandar!=""){
					context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "class=medium disabled"));
				}else{
					context.put("selectBandar", HTML.SelectBandar("socBandar", null, "class=medium disabled"));
				}				
			}
			listD = model.getHakmilik(id_hakmilikpb);
			context.put("getHakmilik", listD);
    			
			context.put("clearForm", "yes");
			context.put("flag", "semak");
			vm = "app/ppt/frmBantahanDaftar.jsp";
			
		} else if ("kembaliList".equals(submit)){	
			
           	//GET LIST DATA PB
    		listE = model.getSenaraiPB(id_permohonan);
    		context.put("getSenaraiPB", listE);
    		context.put("list_size", listE.size());
//    		setupPagePB(session,action,listE);
    		
    		vm = "app/ppt/frmBantahanSenaraiPB.jsp"; 
    		
		} else if ("kembaliListBantahan".equals(submit)){	
			
           	//GET LIST DATA
    		list = modelOnlinePB.getListPemohonPBOnlihe();		
    		context.put("PermohonanList", list);
    		context.put("list_size", list.size());  	    		          		
    		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",null,"style=width:470px"));
    		setupPageBantahan(session,action,list);
    		
    		vm = "app/ppt/frmBantahanSenaraiOnlineInternal.jsp"; 
    		
    	}else if("Cari".equals(submit)){

			carianBantahan(usid,userIdNeg);
			list = model.getListCarianPBOnline(userIdNeg);

    		//data & size default list
    		context.put("PermohonanList", list);
    		context.put("list_size", list.size());  
    		setupPageBantahan(session,action,list);

    		//maintain searching value
    		this.context.put("txtNoFail", getParam("txtNoFail"));
    		String idKementerian = getParam("socKementerian");
    		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(idKementerian),"style=width:470px"));
    		
			vm = "app/ppt/frmBantahanSenaraiOnlineInternal.jsp";    		
    		
    	}else {    		
    		
    		String txtNoFail = "";
			context.put("txtNoFail", "");
			
           	//GET LIST DATA
    		list = modelOnlinePB.getListPemohonPBOnlihe();	
    		
    		context.put("PermohonanList", list);
    		context.put("list_size", list.size());  	    		          		
    		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",null,"style=width:470px"));   		
    		setupPageBantahan(session,action,list);
    		
    		vm = skrinListOnlineInternal;    		
    	}   	    	
    	return vm;
    }


// METHOD	
	
	private void Kaunter_sahkanPermohonan(String id_bantahan,String usid) throws Exception {
		modelOnlinePB.Kaunter_sahkanPermohonan(id_bantahan,usid);
		
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetDataOnchangeNegeri(String id_negeriprojek,String mode) throws Exception{

		context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
		context.put("txtNoRujukanPTG", getParam("txtNoRujukanPTG"));
		context.put("txtNoRujukanPTD", getParam("txtNoRujukanPTD"));
		context.put("txtTujuan", getParam("txtTujuan"));
		context.put("txtRujukanKementerian", getParam("txtRujukanKementerian"));
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txdTarikhKehendaki", getParam("txdTarikhKehendaki"));
		context.put("sorFlagPeruntukan", getParam("sorFlagPeruntukan"));
		context.put("sorFlagSegera", getParam("sorFlagSegera"));
		context.put("sorJenisProjek", getParam("sorJenisProjek"));
		
		String id_daerah = getParam("socDaerah");
		
		//dropdown by
		if(id_negeriprojek!=""){
			context.put("selectDaerah",HTML.SelectDaerahByNegeri(id_negeriprojek,"socDaerah",Utils.parseLong(id_daerah),null,"id=socDaerah style=width:300px"));
    	}else{
    		context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(id_daerah),null,"id=socDaerah style=width:300px"));
        }
		
		//dropdown
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",null,"class=disabled disabled style=width:325px"));
		
	}//close getAndSetDataKementerian
	
	
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
	
	
	private void updateSuburusanHakmilik_deposit(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
	    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelOperations.updateSuburusanHakmilik_deposit(h,id_suburusanstatushakmilik,"1501");
	
	}//close addSuburusanHakmilik_deposit
	
	private void updateSuburusanStatusFailPPT_deposit(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT_deposit(h,id_suburusanstatusfailppt,"1501");
		
	}//close updateSuburusanStatusFailPPT	
	
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1499");
		
	}//close updateSuburusanStatusFailPPT

	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelOperations.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"1499");
	
	}//close addSuburusanHakmilik

	private void carianBantahan(String usid, String userIdNeg) throws Exception {
		
		String txtNoFail = getParam("txtNoFail");
		String tarikh_permohonan = getParam("tarikh_permohonan");
		model.setCarianFailPBOnline(usid,txtNoFail,tarikh_permohonan);	
		
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
	
	 private void saveData(FileItem item) throws Exception {
		 	HttpSession session = request.getSession();		
	  		Db db = null;
	        try {
	        	
	        	long id_Dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");	        	
	        	db = new Db();			      
			     
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	SQLRenderer r = new SQLRenderer();
	        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
	        			"(id_Dokumen,id_bantahan,nama_Fail,jenis_Mime,content,tajuk,keterangan,id_masuk,id_kemaskini) " +
	        			"values(?,?,?,?,?,?,?,?,?)");
	        	ps.setLong(1, id_Dokumen);
	        	ps.setString(2, getParam("id_bantahan"));
	        	ps.setString(3,item.getName());
	        	ps.setString(4,item.getContentType());
	        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
	        	ps.setString(6, getParam("txtnamadokumen"));
	        	ps.setString(7, getParam("txtketerangandokumen"));	        	
	        	ps.setString(8,(String) session.getAttribute("_ekptg_user_id"));	        	      	
	        	ps.setString(9,(String) session.getAttribute("_ekptg_user_id"));        	
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
	 
	public void setupPageBantahan(HttpSession session,String action,Vector list) {
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
//	public void setupPagePB(HttpSession session,String action,Vector listE) {
//		try {		
//		this.context.put("totalRecords",listE.size());
//		int page = getParam("page") == "" ? 1:getParamAsInteger("page");		
//		int itemsPerPage;
//		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
//			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
//		} else {
//			itemsPerPage = (Integer)this.context.get("itemsPerPage");
//		}	    
//	    if ("getNext".equals(action)) {
//	    	page++;
//	    } else if ("getPrevious".equals(action)) {
//	    	page--;
//	    } else if ("getPage".equals(action)) {
//	    	page = getParamAsInteger("value");
//	    } else if ("doChangeItemPerPage".equals(action)) {
//	       itemsPerPage = getParamAsInteger("itemsPerPage");
//	    }	    	
//	    Paging paging = new Paging(session,listE,itemsPerPage);		
//		if (page > paging.getTotalPages()) page = 1; //reset page number
//		this.context.put("getSenaraiPB",paging.getPage(page));
//	    this.context.put("page", new Integer(page));
//	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
//	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
//	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
//	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
//	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));	        
//		} catch (Exception e) {
//			e.printStackTrace();
//			this.context.put("error",e.getMessage());
//		}	
//	}

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
	public void daftarBantahan(String usid) throws Exception{
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
	    String socPihakPembantah = getParam("socPihakBantah");	
	    String idKementerian = getParam("id_kementerian");
	    String idAgensi = getParam("id_agensi");	
	    String id_hakmilik = getParam("id_hakmilik");
	    String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
	    String jenis_pembantah = getParam("jenis_pembantah");
	    String flag_syarat = getParam("flag_syarat");
	    String ukuran_luas = getParam("ukuran_luas");
	    String amaun_pampasan = getParam("amaun_pampasan");
	    String terima_pampasan = getParam("terima_pampasan");
	    String umpuk_pampasan = getParam("umpuk_pampasan");
	    String id_hakmilikpb = getParam("id_hakmilikpb");
	    String txtAmaunTuntutan = getParam("txtAmaunTuntutan");
	    String id_permohonan = getParam("id_permohonan");
	    
	    HttpSession session = request.getSession();
	    modelOperations.daftarBantahan(session,txtNoBantahan,txdTkhMasuk,txdTkhTerimaBrgN,txdBrgN,txtNoLot,txtNoPt,
	    		txdTkhBrgH,txdTkhAward,txtNoHakmilik,txtNama,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,
	    		txtKptgnAtasTnh,txtAlasanBantahan,socPihakPembantah,usid,idKementerian,idAgensi,id_hakmilik,
	    		id_pihakberkepentingan,jenis_pembantah,flag_syarat,ukuran_luas,amaun_pampasan,
	    		terima_pampasan,umpuk_pampasan,id_hakmilikpb,txtAmaunTuntutan,id_permohonan,"");    
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
//		String socBank = getParam("socBank");
		String txdTkhPulang = getParam("txdTkhPulang");
		String socStatusPulang = getParam("socStatusPulang");
		String txdTkhHantar = getParam("txdTkhHantar");
		String txtNamaPenghantar = getParam("txtNamaPenghantar");
		String txtNamaBank = getParam("txtNamaBank");
		String txtCatatanPlgDeposit = getParam("txtCatatanPlgDeposit");
		modelOperations.add_deposit(usid,id_bantahan,txdTkhTerimaResit,txdTkhResit,txtNoResit,txtAmaunResit,
				socCaraBayar,txtNoCek,txtNoAkaun,txtNamaBank,txdTkhPulang,socStatusPulang,txdTkhHantar,
				txtNamaPenghantar,txtCatatanPlgDeposit);		
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
	private void updateBantahan(String usid, String id_bantahan) throws Exception {
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
	    String idKementerian = getParam("id_kementerian");
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
	    modelOperations.updateBantahan(id_bantahan,txtNoBantahan,txdTkhMasuk,txdTkhTerimaBrgN,txdBrgN,
	    		txtNoLot,txtNoPt,txdTkhBrgH,txdTkhAward,txtNoHakmilik,txtNama,txtAlamat1,txtAlamat2,txtAlamat3,
	    		txtPoskod,txtKptgnAtasTnh,txtAlasanBantahan,sbcBantahan,socPihakPembantah,usid,idKementerian,idAgensi,
	    		id_hakmilik,id_pihakberkepentingan,
	    		jenis_pembantah,flag_syarat,ukuran_luas,amaun_pampasan,terima_pampasan,
	    		umpuk_pampasan,txtAmaunTuntutan,""); 
	}
	private void simpanSusulan(String usid,String idBorangO,String id_bantahan,String id_hakmilikpb) throws Exception {
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
		modelOperations.simpanSusulan(usid,idBorangO,id_bantahan,id_hakmilikpb,txtNoProsiding,
				sorKeputusanMahkamah,sorStatusPulangDep,txdTkhPerintah,txdTkhTerimaPerintah,
				txtAmaunPampasanBantahan,txtKosPengapitHakim,txtTempohBayaran,
				unitTempohBayaran,id_award,txtKeteranganPampasan,"","","","","","");
	}
	private void updateStatusUrusanMahkamah(String id_permohonan, String usid) throws Exception {
		modelOperations.updateStatusUrusanMahkamah(id_permohonan,usid);		
	}	
	private void update_deposit(String usid, String id_bantahan, String idBorangO) throws Exception {
		String txdTkhTerimaResit = getParam("txdTkhTerimaResit");
		String txdTkhResit = getParam("txdTkhResit");
		String txtNoResit = getParam("txtNoResit");
		String txtAmaunResit = getParam("txtAmaunResit");
		String socCaraBayar = getParam("socCaraBayar");
		String txtNoCek = getParam("txtNoCek");
		String txtNoAkaun = getParam("txtNoAkaun");
//		String socBank = getParam("socBank");
		String txtNamaBank = getParam("txtNamaBank");
		String txdTkhPulang = getParam("txdTkhPulang");
		String socStatusPulang = getParam("socStatusPulang");
		String txdTkhHantar = getParam("txdTkhHantar");
		String txtNamaPenghantar = getParam("txtNamaPenghantar");
		String txtCatatanPlgDeposit = getParam("txtCatatanPlgDeposit");
		modelOperations.update_deposit(usid,id_bantahan,idBorangO,txdTkhTerimaResit,txdTkhResit,
				txtNoResit,txtAmaunResit,socCaraBayar,txtNoCek,txtNoAkaun,txtNamaBank,txdTkhPulang,
				socStatusPulang,txdTkhHantar,txtNamaPenghantar,txtCatatanPlgDeposit);		
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
	
	// UPDATE STATUS URUSAN DEPOSIT
	private void updateStatus(HttpSession session) throws Exception{
    	
		String id_permohonan = getParam("id_permohonan");
    	String idUser = (String) session.getAttribute("_ekptg_user_id");
    	
    	//status URUSAN DEPOSIT BANTAHAN
		String idstatus = "183";
    	
		modelOperations.updateStatus(id_permohonan,idUser,idstatus);
      
	}//close update status URUSAN DEPOSIT
	
	// URUSAN MAHKAMAH
	private void updateStatus_borangO(HttpSession session) throws Exception{

		String id_permohonan = getParam("id_permohonan");
		String idUser = (String) session.getAttribute("_ekptg_user_id");

		//status BORANG O
		String idstatus = "1610248";

		modelOperations.updateStatus_borangO(id_permohonan,idUser,idstatus);

	}//close update status borang O					
					
					
	private void updateSuburusanStatusFailPPT_borangO(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT_borangO(h,id_suburusanstatusfailppt,"1502");

	}//close updateSuburusanStatusFailPPT borang O



	private void updateSuburusanHakmilik_borangO(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelOperations.updateSuburusanHakmilik_borangO(h,id_suburusanstatushakmilik,"16103947");

	}//close addSuburusanHakmilik borang O
	
	
	private void updateSuburusanStatusFailPPT_tarikbalik(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT_tarikbalik(h,id_suburusanstatusfailppt,"16103947");

	}//close updateSuburusanStatusFailPPT_tarikbalik

	private void updateSuburusanHakmilik_tarikbalik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelOperations.updateSuburusanHakmilik_tarikbalik(h,id_suburusanstatushakmilik,"1503");

	}//close addSuburusanHakmilik_tarikbalik	
	
	
	private void updateStatus_pembatalanOlehMT(HttpSession session) throws Exception{

		String id_permohonan = getParam("id_permohonan");
		String idUser = (String) session.getAttribute("_ekptg_user_id");

		//status pembatalanOlehMT
		String idstatus = "220";

		modelOperations.updateStatus_pembatalanOlehMT(id_permohonan,idUser,idstatus);

	}//close update status
					
					
					
	private void updateSuburusanStatusFailPPT_pembatalanOlehMT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt_updateStatus_pembatalanOlehMT
		modelOperations.updateSuburusanStatusFailPPT_pembatalanOlehMT(h,id_suburusanstatusfailppt,"16102121");

	}//close updateSuburusanStatusFailPPT_updateStatus_pembatalanOlehMT



	private void updateSuburusanHakmilik_pembatalanOlehMT(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelOperations.updateSuburusanHakmilik_pembatalanOlehMT(h,id_suburusanstatushakmilik,"16102121");

	}//close addSuburusanHakmilik


	
	
			
}

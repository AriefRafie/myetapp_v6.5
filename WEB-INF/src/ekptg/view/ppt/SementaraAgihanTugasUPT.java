package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraAgihanTugasUPTData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiAgihanTugasUPTData;

public class SementaraAgihanTugasUPT extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(SementaraAgihanTugasUPT.class);
	FrmHakmilikSementaraSenaraiAgihanTugasUPTData listAgihanTugasUPT = new FrmHakmilikSementaraSenaraiAgihanTugasUPTData();
	FrmHakmilikSementaraAgihanTugasUPTData agihanTugasUPT = new FrmHakmilikSementaraAgihanTugasUPTData();
	
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String vm = "";
		String tarikhagih = "";
    	String nofail = "";  
    	String cStatus = "0";
    	String action = getParam("action");    	
    	context.put("action",action);
    	myLogger.info("action >> "+action);
    	
	    String submit = getParam("command");
	    context.put("id_fail", getParam("id_fail"));
		String id_fail = getParam("id_fail");
		context.put("id_permohonan", getParam("id_permohonan"));
		String id_permohonan = getParam("id_permohonan");
    	Vector list = null;
    	Vector namajawatan = null;

    	
    	
    	if("view_item".equals(action)){ //id_tugas belum wujud
			
    		vm = "app/ppt/frmHakmilikSementaraAgihanTugasUPT.jsp"; 		
    		
			context.put("default", "yes");

			Vector getrecord_permohonan = agihanTugasUPT.getRecord(id_fail,id_permohonan);
			Hashtable h1 = (Hashtable)getrecord_permohonan.get(0);
			context.put("noFail",h1.get("no_fail"));
			context.put("noPermohonan", h1.get("no_permohonan"));
			context.put("status",h1.get("keterangan"));
			context.put("id_tugas", h1.get("id_tugas"));
			context.put("id_status", h1.get("id_status"));
			
			String id_status = h1.get("id_status").toString();
			
			String id_tugas = h1.get("id_tugas").toString();
			context.put("id_tugas",id_tugas);
			
			if(id_status.equals("127")){
				context.put("pegawaiPengagih", session.getAttribute("_portal_username"));
				context.put("txdTarikhSerahTugas", "");
				context.put("addtxtjawatan","");
				context.put("txtCatatan", "");
				context.put("readonly", "");
				context.put("disabled", "");
				context.put("SelectPegawai",HTML.SelectPegawai("pegawai",null,"style=width:240px onChange=\"doChangeidPegawai();\""));
			}
			else if (id_status.equals("148")){
				Vector getUserName = agihanTugasUPT.getRecordEdit(id_permohonan,id_fail,id_tugas);
				Hashtable h2 = (Hashtable) getUserName.get(0);
				context.put("pegawaiPengagih", h2.get("user_name"));
				context.put("txdTarikhSerahTugas", h2.get("tarikh_agih"));
				context.put("addPegawaiPenerima", h2.get("nama_pegawai"));
				context.put("addtxtjawatan",h2.get("jawatan"));
				context.put("txtCatatan", h2.get("perihal_agih"));
				context.put("readonly", "readonly");
				context.put("disabled", "disabled");
				context.put("SelectPegawai",HTML.SelectPegawai("pegawai",Utils.parseLong(h2.get("id_pegawaipenerima").toString()),"style=width:240px class=disabled disabled"));

			}
			
		
		}
    	else if("Simpan_Agihan".equals(action)){
			
			    
		    String ekptg_user_id = getParam("ekptg_user_id");//--pegawai pengagih
		    String txdTarikhSerahTugas = getParam("txdTarikhSerahTugas");
		    String socPegawai = getParam("pegawai");
		    String txtCatatan = getParam("txtCatatan");
		    //String txtNamaPegawai = getParam("txtNamaPegawai");
			
		    add_maklumatAgihan(session);
		    edit_status_agihanTugas(session);		    
			
			if (!"".equals(getParam("txdTarikhMohon")));
			tarikhagih = getParam("txdTarikhMohon");
				
			if (!"".equals(getParam("no_fail")));
				nofail = getParam("no_fail");
			
			listAgihanTugasUPT.setList(nofail,tarikhagih, cStatus);
			list = listAgihanTugasUPT.getList();
				
		    this.context.put("PermohonanList", list);
		    this.context.put("list_size", list.size());
		    this.context.put("CarianFail", nofail);  
		    this.context.put("CarianTarikhMohon", tarikhagih);	
		    setupPage(session,action,list);
		    
		    vm = "app/ppt/frmHakmilikSementaraSenaraiAgihanTugasUPT.jsp";		
		    
		}
    	else if("doChangeidPegawai".equals(submit)){
			
    		vm = "app/ppt/frmHakmilikSementaraAgihanTugasUPT.jsp"; 		

    		String idPegawai = getParam("pegawai");
    		int idK = Integer.parseInt(idPegawai);
    		
    		namajawatan = agihanTugasUPT.getNamaJawatan(idPegawai);
    		
    		
    		Hashtable AK = (Hashtable) namajawatan.get(0);
 
    		String jawatan;       		
    		jawatan = AK.get("jawatan").toString();
    		
    		 
				
//			context.put("id_tugas", getParam("id_tugas"));
//			String id_tugas = getParam("id_tugas");
			
			context.put("id_status", getParam("id_status"));
			String id_status = getParam("id_status");			
			
			Vector getrecord_permohonan = agihanTugasUPT.getRecord(id_fail,id_permohonan);
			Hashtable h1 = (Hashtable)getrecord_permohonan.get(0);
			context.put("noFail",h1.get("no_fail"));
			context.put("noPermohonan", h1.get("no_permohonan"));
			context.put("status",h1.get("keterangan"));
			context.put("id_tugas", h1.get("id_tugas"));
			context.put("id_status", h1.get("id_status"));
			context.put("pegawaiPengagih", session.getAttribute("_portal_username"));
    
    		//----------------get and post all field content---------------------//

    		context.put("txdTarikhSerahTugas", getParam("txdTarikhSerahTugas"));
    		idPegawai = getParam("pegawai");
    		context.put("addtxtjawatan", jawatan);
    		context.put("txtCatatan", getParam("txtCatatan"));
    		
    		//context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Utils.parseLong(idPegawai),"class=mediumselect"));
    		context.put("SelectPegawai",HTML.SelectPegawai("pegawai",Utils.parseLong(idPegawai),"style=width:240px onChange=\"doChangeidPegawai();\""));
			
    		context.put("default", "yes");
			
			
    	}
		else{
			vm = "app/ppt/frmHakmilikSementaraSenaraiAgihanTugasUPT.jsp";			
			
			if (!"".equals(getParam("txdTarikhMohon")));
			tarikhagih = getParam("txdTarikhMohon");
				
			if (!"".equals(getParam("no_fail")));
				nofail = getParam("no_fail");
				
			if(!"".equals(getParam("socStatus")))
				cStatus = getParam("socStatus");
			
			listAgihanTugasUPT.setList(nofail,tarikhagih, cStatus);
			list = listAgihanTugasUPT.getList();
				
		    this.context.put("PermohonanList", list);
		    this.context.put("list_size", list.size());
		    this.context.put("CarianFail", nofail);  
		    this.context.put("CarianTarikhMohon", tarikhagih);
		    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));

		    setupPage(session,action,list);
		}
	    return vm;
	   
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
	 
	 	private void add_maklumatAgihan(HttpSession session) throws Exception {
			
			String id_permohonan = getParam("id_permohonan");
			String id_fail = getParam("id_fail");
		    String ekptg_user_id = getParam("ekptg_user_id"); //*save - pegawai_pengagih(id_pegawai)
		    String txdTarikhSerahTugas = getParam("txdTarikhSerahTugas"); 
		    String pegawaiPenerima = getParam("pegawai"); //*save - pegawai_penerima(id_pegawaipenerima)
		    String txtCatatan = getParam("txtCatatan");
		    //String txtNamaPegawai = getParam("txtNamaPegawai");		    
		    Hashtable h = null;
		    h = new Hashtable();

		    h.put("id_permohonan", id_permohonan);
		    h.put("id_fail", id_fail);
		    h.put("ekptg_user_id", ekptg_user_id);
		    h.put("txdTarikhSerahTugas", txdTarikhSerahTugas);
		    h.put("pegawaiPenerima", pegawaiPenerima);
		    h.put("txtCatatan", txtCatatan);
		    //h.put("txtNamaPegawai", txtNamaPegawai);

		    FrmHakmilikSementaraAgihanTugasUPTData.add_agihan_tugas(h);
		}
	 	
	 	private void edit_status_agihanTugas(HttpSession session) throws Exception {
			String id_permohonan = getParam("id_permohonan");
		    
		    Hashtable h = null;
		    h = new Hashtable();

		    h.put("id_permohonan", id_permohonan);

		    FrmHakmilikSementaraAgihanTugasUPTData.edit_status(h);
		}
}

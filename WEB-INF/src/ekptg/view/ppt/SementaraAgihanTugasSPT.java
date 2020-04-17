package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraAgihanTugasSPTData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiAgihanTugasSPTData;


public class SementaraAgihanTugasSPT extends AjaxBasedModule {
	
	FrmHakmilikSementaraSenaraiAgihanTugasSPTData listAgihanTugas = new FrmHakmilikSementaraSenaraiAgihanTugasSPTData();
	FrmHakmilikSementaraAgihanTugasSPTData agihanTugas = new FrmHakmilikSementaraAgihanTugasSPTData();
	 
	public String doTemplate2() throws Exception
	 {
		 HttpSession session = this.request.getSession();
	     String vm = "";
	     String tarikhagih = "";
	     String nofail = "";  
	     String cStatus = "0";
	     String action = getParam("action");
	     String submit = getParam("command");
	     
	     
	     Vector listAgihan = null;
	     Vector alamatKementerian = null;
	     Vector namajawatan = null;
	  
	     
	     if("view_item".equals(action)){ //id_tugas belum wujud
	    	 	
	    	 	vm = "app/ppt/frmHakmilikSementaraAgihanTugasSPT.jsp"; 	
				context.put("default", "yes");
						 
				context.put("id_fail", getParam("id_fail"));
				String id_fail = getParam("id_fail");

				context.put("id_permohonan", getParam("id_permohonan"));
				String id_permohonan = getParam("id_permohonan");
				
				Vector getrecord_permohonan = agihanTugas.getRecord(id_fail,id_permohonan);
				Hashtable h1 = (Hashtable)getrecord_permohonan.get(0);
				context.put("noFail",h1.get("no_fail"));
				context.put("noPermohonan", h1.get("no_permohonan"));
				context.put("status",h1.get("keterangan"));
				context.put("id_tugas", h1.get("id_tugas"));
				context.put("id_status", h1.get("id_status"));
				
				String id_tugas = h1.get("id_tugas").toString();//context.put("id_tugas", h1.get("id_tugas"));
				if (id_tugas != ""){
					
					//String id_tugas1 = Integer.parseInt(id_tugas);
					Vector getUserName = agihanTugas.getRecordEdit(id_permohonan,id_fail,id_tugas);
					Hashtable h2 = (Hashtable) getUserName.get(0);
					
					context.put("pegawaiPengagih", h2.get("user_name"));
					context.put("txdTarikhSerahTugas", h1.get("tarikh_agih"));
					context.put("addPegawaiPenerima", h1.get("nama_pegawai"));
					context.put("addtxtjawatan",h1.get("jawatan"));
					context.put("txtCatatan", h1.get("perihal_agih"));
					context.put("readonly", "readonly");
					context.put("disabled", "disabled");

				}
				else{
					context.put("pegawaiPengagih", session.getAttribute("_portal_username"));
					context.put("txdTarikhSerahTugas", "");
					context.put("addPegawaiPenerima", "");
					context.put("addtxtjawatan","");
					context.put("txtCatatan", "");
					context.put("SelectNegeri",HTML.SelectNegeri("negeri","style=width:250px onChange=\"doChangeidNegeri();\""));
					context.put("SelectPegawai",HTML.SelectPegawai("pegawai",null,"style=width:240px onChange=\"doChangeidPegawai();\""));
					context.put("readonly", "");
					context.put("disabled", "");
				}

			
			}else if("view_item_tugas".equals(action)){	//id_tugas telah wujud
				
				vm = "app/ppt/frmHakmilikSementaraAgihanTugasSPT.jsp";
				
				context.put("default", "yes");
				
				context.put("id_permohonan", getParam("id_permohonan"));
				String id_permohonan = getParam("id_permohonan");
				
				context.put("id_fail", getParam("id_fail"));
				String id_fail = getParam("id_fail");
				
				context.put("id_tugas", getParam("id_tugas"));
				String id_tugas = getParam("id_tugas");
				
				Vector getrecord_permohonan = agihanTugas.getRecordEdit(id_permohonan,id_fail,id_tugas);
				Hashtable h = (Hashtable) getrecord_permohonan.get(0);
				
				
				context.put("selectPegawai",HTML.SelectPegawai("socPegawai",null,"style=width:240px"));			

			}
			else if("Simpan_Agihan".equals(action)){
				
				this.context.put("id_fail", getParam("id_fail"));
				int id_fail = Integer.parseInt(getParam("id_fail"));
				
				this.context.put("id_permohonan", getParam("id_permohonan"));
				int id_permohonan = Integer.parseInt(getParam("id_permohonan"));
				    
			    String ekptg_user_id = getParam("ekptg_user_id");
			    String txdTarikhSerahTugas = getParam("txdTarikhSerahTugas");
			    String pegawai = getParam("pegawai");
			    String txtCatatan = getParam("txtCatatan");
			    String id_pegawaipenerima = getParam("id_pegawai");
				
			    add_maklumatAgihan(session);
			    edit_status_agihanTugas(session);
			    
			    vm = "app/ppt/frmHakmilikSementaraSenaraiAgihanTugasSPT.jsp";	
				
			    if (!"".equals(getParam("txdTarikhMohon")));
			       tarikhagih = getParam("txdTarikhMohon");
					
			    if (!"".equals(getParam("no_fail")));
					nofail = getParam("no_fail");
				
				listAgihanTugas.setList(nofail, tarikhagih, cStatus);
				listAgihan = listAgihanTugas.getList();
				context.put("PermohonanList", listAgihan);
				context.put("list_size", listAgihan.size());
				context.put("CarianFail", nofail);  
				context.put("CarianTarikhMohon", tarikhagih);
				setupPage(session,action,listAgihan);

			}else if("Kembali_skrin1".equals(submit)){			

				   vm = "app/ppt/frmHakmilikSementaraSenaraiAgihanTugasSPT.jsp";	
					
				    if (!"".equals(getParam("txdTarikhMohon")));
				       tarikhagih = getParam("txdTarikhMohon");
						
				    if (!"".equals(getParam("no_fail")));
						nofail = getParam("no_fail");
					
					listAgihanTugas.setList(nofail, tarikhagih, cStatus);
					listAgihan = listAgihanTugas.getList();
					context.put("PermohonanList", listAgihan);
					context.put("list_size", listAgihan.size());
					context.put("CarianFail", nofail);  
					context.put("CarianTarikhMohon", tarikhagih);
					setupPage(session,action,listAgihan);
			}
			else if("doChangeidNegeri".equals(submit)){
				
				vm = "app/ppt/frmHakmilikSementaraAgihanTugasSPT.jsp";	
	    		String idKementerian = getParam("negeri");
	    		int idK = Integer.parseInt(idKementerian);
	    		
	    		alamatKementerian = agihanTugas.getAlamatKementerian(idKementerian);
	    	
	    		Hashtable AK = (Hashtable) alamatKementerian.get(0);
	    		String AK_negeri;
	    		String namapegawai;
	    		String jawatan;
	    		String idpegawai;
	    		
	    		AK_negeri = AK.get("id_negeri").toString();
	    		namapegawai = AK.get("nama_pegawai").toString();
	    		jawatan = AK.get("jawatan").toString();
	    		idpegawai = AK.get("id_pegawai").toString();
	    		
	    		context.put("id_fail", getParam("id_fail"));
				String id_fail = getParam("id_fail");

				context.put("id_permohonan", getParam("id_permohonan"));
				String id_permohonan = getParam("id_permohonan");
	    		Vector getrecord_permohonan = agihanTugas.getRecord(id_fail,id_permohonan);
				Hashtable h = (Hashtable)getrecord_permohonan.get(0);
				context.put("noFail",h.get("no_fail"));
				context.put("noPermohonan", h.get("no_permohonan"));
				context.put("status",h.get("keterangan"));
				context.put("id_tugas", h.get("id_tugas"));
				context.put("id_status", h.get("id_status"));
	    
	    		//----------------get and post all field content---------------------//
	    		context.put("txtNamaPegawai", getParam("txtNamaPegawai"));
	    		context.put("txdTarikhSerahTugas", getParam("txdTarikhSerahTugas"));
	    		context.put("addPegawaiPenerima", namapegawai);
	    		context.put("addtxtjawatan", jawatan);
	    		context.put("id_pegawai", idpegawai);
	    		context.put("txtCatatan", getParam("txtCatatan"));
	    		context.put("pegawaiPengagih", session.getAttribute("_portal_username"));

	    		String idNegeri = AK_negeri;
	    		   		
	    		context.put("SelectNegeri",HTML.SelectNegeri("negeri",Utils.parseLong(idNegeri),null,"onChange=\"doChangeidNegeri2();\" "));		
	    		
		      		
				
				
			}else if("doChangeidPegawai".equals(submit)){
				
				vm = "app/ppt/frmHakmilikSementaraAgihanTugasSPT.jsp";	
				
	    		String idPegawai = getParam("pegawai");
	    		int idK = Integer.parseInt(idPegawai);
	    		
	    		namajawatan = agihanTugas.getNamaJawatan(idPegawai);
	    		
	    		Hashtable AK = (Hashtable) namajawatan.get(0);
	 
	    		String jawatan;       		
	    		jawatan = AK.get("jawatan").toString();
	    
	    		//----------------get and post all field content---------------------//

	    		context.put("txdTarikhSerahTugas", getParam("txdTarikhSerahTugas"));
	    		idPegawai = getParam("pegawai");
	    		context.put("txtjawatan", jawatan);
	    		context.put("txtCatatan", getParam("txtCatatan"));
	    		
	    		//context.put("selectPegawai",HTML.SelectPegawai("socPegawai",Utils.parseLong(idPegawai),"class=mediumselect"));
	    		context.put("SelectPegawai",HTML.SelectPegawai("pegawai",Utils.parseLong(idPegawai),"style=width:240px onChange=\"doChangeidPegawai();\""));
				
	    		context.put("default", "yes");

			}
			else{
			     vm = "app/ppt/frmHakmilikSementaraSenaraiAgihanTugasSPT.jsp";	
			     
			     if (!"".equals(getParam("txdTarikhMohon")));
					tarikhagih = getParam("txdTarikhMohon");
					
				 if (!"".equals(getParam("no_fail")));
					nofail = getParam("no_fail");
					
					listAgihanTugas.setList(nofail, tarikhagih, cStatus);
					listAgihan = listAgihanTugas.getList();
					context.put("PermohonanList", listAgihan);
				    context.put("list_size", listAgihan.size());
				    context.put("CarianFail", nofail);  
				    context.put("CarianTarikhMohon", tarikhagih);
				    setupPage(session,action,listAgihan);
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
		    String pegawai = getParam("pegawai"); //*save - pegawai_penerima(id_pegawaipenerima)
		    String txtCatatan = getParam("txtCatatan");
		    String id_pegawaipenerima = getParam("id_pegawai");		    
		    
		    Hashtable h = null;
		    h = new Hashtable();

		    h.put("id_permohonan", id_permohonan);
		    h.put("id_fail", id_fail);
		    h.put("ekptg_user_id", ekptg_user_id);
		    h.put("txdTarikhSerahTugas", txdTarikhSerahTugas);
		    h.put("txtCatatan", txtCatatan);
		    h.put("id_pegawaipenerima", id_pegawaipenerima);

		    FrmHakmilikSementaraAgihanTugasSPTData.add_agihan_tugas(h);
		}
	   	private void edit_status_agihanTugas(HttpSession session) throws Exception {
			String id_permohonan = getParam("id_permohonan");
		    
		    Hashtable h = null;
		    h = new Hashtable();

		    h.put("id_permohonan", id_permohonan);

		    FrmHakmilikSementaraAgihanTugasSPTData.edit_status(h);
		}
}

package ekptg.view.pfd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.pfd.FrmDaftarPergerakanFailData;
import ekptg.model.pfd.FrmKemaskiniPergerakanFailData;
import ekptg.model.pfd.FrmLogDokumenData;

	public class PergerakanFail extends AjaxBasedModule{
		
		public String doTemplate2() throws Exception

	    {
	
			 String vm = "";
			 String noFail = "";
			 String noFailLama = "";
	         String tajukFail = "";
	         String idFail = getParam("idFail");
	 		 context.put("idFail", idFail);
	         String negeri = getParam("socNegeriD");
	         String seksyen = getParam("socSeksyenD");
	         String status = getParam("socStatusD");
	         String tarikhDaftar = "";
			 Vector list = new Vector();
			 Vector senarai = new Vector();
			 Vector paparSeksyen = null;
			 String submit = getParam("command");
			 String action1 = getParam("action1");
			 String action = getParam("action");
				
				if ("doChangeItemPerPage".equals(action) ||
						"getPage".equals(action)) {
					action1 = action;
				}
			 String ac = getParam("ac");
			 String mode = getParam("mode");
			 HttpSession session = this.request.getSession();
			 this.context.put("Util",new Utils());
			 Date now = new Date();
	         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	         context.put("emel","notsuccess");
			 
			 System.out.println(getParam("ac"));
			 
			 if ("paparPergerakan".equals(action1)){
				 vm = "app/pfd/frmKemaskiniPergerakanFail.jsp";
				 
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));
					
				String user_id = (String)session.getAttribute("_ekptg_user_id");
				context.put("nama_pemohon",user_id);
					
				listPergerakanPemohonFailUser(session);
				senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFailUser(idFail);
		    	this.context.put("senaraiPergerakanFailUser",senarai);
	    			

			}
			
			else if ("tabPinjamFail".equals(action1)){
				vm = "app/pfd/frmKemaskiniPinjamFail.jsp";
				this.context.put("mode", "New");
				context.put("readonly","");
				context.put("disabled","");
				context.put("action1", "");
				
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));
				
				context.put("user_Name",(String)session.getAttribute("_portal_username"));
				
				listPTFail(session);
	    		list = FrmKemaskiniPergerakanFailData.getListPTFail();
	    		this.context.put("SenaraiPTFail",list);
	    		
			    context.put("tarikhFailMsk", "");
			    context.put("tujuan","");
			    
				Vector paparTarikhPinjamDari = FrmKemaskiniPergerakanFailData.paparTarikhPinjamDari(idFail);
				Hashtable hB = (Hashtable)paparTarikhPinjamDari.get(0);
			    if(hB.get("tempohPinjamDari").equals("")){
			    	context.put("tempohPinjamDari",sdf.format(now));
			    }
			    else	
			    {
			    	//String idFail2 = getParam("idFail");
			    	Vector paparTarikhPinjamHingga = FrmKemaskiniPergerakanFailData.paparTarikhPinjamHingga(idFail);
					Hashtable hC = (Hashtable)paparTarikhPinjamHingga.get(0);
			    	context.put("tempohPinjamDari",hC.get("tempohPinjamHingga"));
			    	
			    }
			    
			    
			    Vector paparTarikhPinjamHingga = FrmKemaskiniPergerakanFailData.paparTarikhPinjamHingga(idFail);
				Hashtable hD = (Hashtable)paparTarikhPinjamHingga.get(0);
			    if(hD.get("tempohPinjamHingga").equals("")){
			    	Vector paparTarikhPinjamHinggaAdd3Kosong = FrmKemaskiniPergerakanFailData.paparTarikhPinjamHinggaAdd3Kosong(idFail);
					Hashtable hF = (Hashtable)paparTarikhPinjamHinggaAdd3Kosong.get(0);
				    context.put("tempohPinjamHingga",hF.get("tempohPinjamHingga"));
			    }
			    else
			    { 
				    Vector paparTarikhPinjamHinggaAdd3 = FrmKemaskiniPergerakanFailData.paparTarikhPinjamHinggaAdd3(idFail);
					Hashtable hE = (Hashtable)paparTarikhPinjamHinggaAdd3.get(0);
				    context.put("tempohPinjamHingga",hE.get("tempohPinjamHingga"));
			    }
			 
//			    Vector paparTarikhPinjamHingga = FrmKemaskiniPergerakanFailData.paparTarikhPinjamHingga(idFail);
//				Hashtable hC = (Hashtable)paparTarikhPinjamHingga.get(0);
//			    if(hC.get("tempohPinjamDari").equals("")){
//			    	context.put("tempohPinjamDari",sdf.format(now));
//			    }
//			    else	
//			    {
//			    	context.put("tempohPinjamDari",hC.get("tempohPinjamHingga"));
//			    	
//			    }
			 

				
			}
			 
			else if ("tabSahFail".equals(action1)){
				vm = "app/pfd/frmKemaskiniPengesahanPinjamanFailTab.jsp";
				this.context.put("mode", "baru");
				context.put("readonly","");
				context.put("disabled","");
				context.put("action1", "");
				
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));
				
				context.put("user_Name",(String)session.getAttribute("_portal_username"));
				context.put("user_Name",(String)session.getAttribute("_ekptg_user_role"));
				
			}
			
			else if ("simpanPinjamFail".equals(action1)){
				vm = "app/pfd/frmKemaskiniPinjamFail.jsp";
				this.context.put("mode", "View");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("action1", "");
				
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));
				   		
	    		String idPergerakanfail =simpanPergerakanFail(session);
			 	
				Vector paparSimpanPergerakanFail = FrmKemaskiniPergerakanFailData.paparSimpanPergerakanFail(idPergerakanfail);			 
				Hashtable hB = (Hashtable)paparSimpanPergerakanFail.get(0);
				
				
				context.put("idPergerakanfail", hB.get("idPergerakanfail"));
				context.put("user_Name", hB.get("nama_pegawai_pinjamfail"));
				context.put("selectidorang", hB.get("IDPTFAIL"));
				context.put("tujuan", hB.get("tujuan"));
				context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
				context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));


//				listPTFail(session);
//	    		list = FrmKemaskiniPergerakanFailData.getListPTFail();
//	    		this.context.put("SenaraiPTFail",list);
//	    		
//	    		list = FrmKemaskiniPergerakanFailData.getPTFail(idFail);
//	    		Hashtable x = (Hashtable)list.elementAt(0);
//	 			this.context.put("selectidorang",x.get("user_id"));
				
				
			}
			
//			else if ("emelPinjamFail".equals(action1)){
//				vm = "app/pfd/frmKemaskiniPinjamFail.jsp";
//				this.context.put("mode", "View");
//				context.put("readonly","readonly");
//				context.put("disabled","disabled");
//				context.put("action1", "");
//				context.put("emel","success");
//				
//				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
//				Hashtable hA = (Hashtable)paparMaklumat.get(0);
//					
//				context.put("idFail", hA.get("idFail"));
//				context.put("noFail", hA.get("noFail"));
//				context.put("tajukFail", hA.get("tajukFail"));
//				context.put("status", hA.get("status"));
//				   		
//	    		String idPergerakanfail =getParam("idPergerakanfail");
//			 	
//				Vector paparSimpanPergerakanFail = FrmKemaskiniPergerakanFailData.paparSimpanPergerakanFail(idPergerakanfail);			 
//				Hashtable hB = (Hashtable)paparSimpanPergerakanFail.get(0);
//				
//				
//				context.put("idPergerakanfail", hB.get("idPergerakanfail"));
//				context.put("user_Name", hB.get("nama_pegawai_pinjamfail"));
//				context.put("selectidorang", hB.get("IDPTFAIL"));
//				context.put("tujuan", hB.get("tujuan"));
//				context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
//				context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));
//
//
////				listPTFail(session);
////	    		list = FrmKemaskiniPergerakanFailData.getListPTFail();
////	    		this.context.put("SenaraiPTFail",list);
////	    		
////	    		list = FrmKemaskiniPergerakanFailData.getPTFail(idFail);
////	    		Hashtable x = (Hashtable)list.elementAt(0);
////	 			this.context.put("selectidorang",x.get("user_id"));
//				
//				
//			}
			 
			else if ("kemaskiniPinjamFail".equals(action1)){
				vm = "app/pfd/frmKemaskiniPinjamFail.jsp";
				this.context.put("mode", "update");
				context.put("readonly","");
				context.put("disabled","");
				context.put("action1", "");
				
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));
				   		
				String idPergerakanfail = getParam("idPergerakanfail");
			 	
				Vector paparSimpanPergerakanFail = FrmKemaskiniPergerakanFailData.paparSimpanPergerakanFail(idPergerakanfail);			 
				Hashtable hB = (Hashtable)paparSimpanPergerakanFail.get(0);
				
				
				context.put("idPergerakanfail", hB.get("idPergerakanfail"));
				context.put("user_Name", hB.get("nama_pegawai_pinjamfail"));
				context.put("selectidorang", hB.get("IDPTFAIL"));
				context.put("tujuan", hB.get("tujuan"));
				context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
				context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));


				listPTFail(session);
	    		list = FrmKemaskiniPergerakanFailData.getListPTFail();
	    		this.context.put("SenaraiPTFail",list);
	    		
//	    		list = FrmKemaskiniPergerakanFailData.getPTFail(idPergerakanfail);
//	    		Hashtable x = (Hashtable)list.elementAt(0);
//	 			this.context.put("selectidorang",x.get("user_id"));
				
				
			}
			 
			else if ("updatePinjamFail".equals(action1)){
				vm = "app/pfd/frmKemaskiniPinjamFail.jsp";
				this.context.put("mode", "PaparUpdate");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("action1", "");
				
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));
				   		  		
	    		update(session);
	    		
	    		String idPergerakanfail = getParam("idPergerakanfail");
			 	
				Vector paparSimpanPergerakanFail = FrmKemaskiniPergerakanFailData.paparSimpanPergerakanFail(idPergerakanfail);			 
				Hashtable hB = (Hashtable)paparSimpanPergerakanFail.get(0);
				
				
				context.put("idPergerakanfail", hB.get("idPergerakanfail"));
				context.put("user_Name", hB.get("nama_pegawai_pinjamfail"));
				context.put("selectidorang", hB.get("IDPTFAIL"));
				context.put("tujuan", hB.get("tujuan"));
				context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
				context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));


				listPTFail(session);
	    		list = FrmKemaskiniPergerakanFailData.getListPTFail();
	    		this.context.put("SenaraiPTFail",list);
	    		
//	    		list = FrmKemaskiniPergerakanFailData.getPTFail(idPergerakanfail);
//	    		Hashtable x = (Hashtable)list.elementAt(0);
//	 			this.context.put("selectidorang",x.get("user_id"));
				
				
			}
			 
			else if ("hapusPinjamFail".equals(action1)){
				vm = "app/pfd/frmKemaskiniPinjamFail.jsp";
				this.context.put("mode", "New");
				this.context.put("action1", "tabSahFail");
				context.put("readonly","");
				context.put("disabled","");
				
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));
				  
				hapus(session);
				
				context.put("selectidorang","");
				context.put("tujuan","");
				context.put("tempohPinjamDari", "");
				context.put("tempohPinjamHingga","");
		
				
				
			}
			 
			else if ("paparPinjamFail".equals(action1)){
				vm = "app/pfd/frmKemaskiniPinjamFail.jsp";
				this.context.put("mode", "View");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("action1", "");
				
				idFail = getParam("idFail");
	    		String idPergerakanfail = getParam("idPergerakanfail");
				
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));

			 	
				Vector paparSimpanPergerakanFail = FrmKemaskiniPergerakanFailData.paparSimpanPergerakanFail(idPergerakanfail);			 
				Hashtable hB = (Hashtable)paparSimpanPergerakanFail.get(0);
				
				
				context.put("idPergerakanfail", hB.get("idPergerakanfail"));
				context.put("user_Name", hB.get("nama_pegawai_pinjamfail"));
				context.put("selectidorang", hB.get("IDPTFAIL"));
				context.put("tujuan", hB.get("tujuan"));
				context.put("tempohPinjamDari", hB.get("tempohPinjamDari"));
				context.put("tempohPinjamHingga", hB.get("tempohPinjamHingga"));


				listPTFail(session);
	    		list = FrmKemaskiniPergerakanFailData.getListPTFail();
	    		this.context.put("SenaraiPTFail",list);
	    		
//	    		list = FrmKemaskiniPergerakanFailData.getPTFail(idPergerakanfail);
//	    		Hashtable x = (Hashtable)list.elementAt(0);
//	 			this.context.put("selectidorang",x.get("user_id"));
				
				
			}
			 
			else if ("simpanPulangFail".equals(action1)){
				vm = "app/pfd/frmKemaskiniPulangFail.jsp";
				this.context.put("mode", "baru");
				context.put("readonly","");
				context.put("disabled","");
				context.put("action1", "");
				
				Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
				Hashtable hA = (Hashtable)paparMaklumat.get(0);
					
				context.put("idFail", hA.get("idFail"));
				context.put("noFail", hA.get("noFail"));
				context.put("tajukFail", hA.get("tajukFail"));
				context.put("status", hA.get("status"));
				
				listPTFail(session);
	    		list = FrmKemaskiniPergerakanFailData.getListPTFail();
	    		this.context.put("SenaraiPTFail",list);
	    		
//	//    		String idPergerakanfail =simpanPulangPergerakanFail(session);
//			 	
//				Vector paparPergerakanFail = FrmKemaskiniPergerakanFailData.paparPergerakanFail(idPergerakanfail);
//					 
//				Hashtable hB = (Hashtable)paparPergerakanFail.get(0);
//						
//				context.put("idPergerakanfail", hB.get("idPergerakanfail"));
//				context.put("idFail", hB.get("idFail"));
//				context.put("user_Name", hB.get("user_Name"));
//				context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(hB.get("idPenghantar").toString()),"disabled"));
//				context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(hB.get("idPenerima").toString()),"disabled"));
//				context.put("tarikhFailMsk", hB.get("tarikhMasuk"));
//				context.put("tarikhFailKeluar", hB.get("tarikhKeluar"));
//				context.put("tempohSdpDari", hB.get("tarikhSDPdari"));
//				context.put("tempohSdpHingga", hB.get("tarikhSDPhingga"));
//				context.put("catatan", hB.get("catatan"));

				
			}
			 
			else if ("tambahSenaraiPF".equals(action1)){
				 
					vm = "app/pfd/frmKemaskiniPinjamFail.jsp";
					this.context.put("mode", "baru");
					context.put("readonly","");
					context.put("disabled","");
					context.put("action1", "");
					
					Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
					Hashtable hA = (Hashtable)paparMaklumat.get(0);
						
					context.put("idFail", hA.get("idFail"));
					context.put("noFail", hA.get("noFail"));
					context.put("tajukFail", hA.get("tajukFail"));
					context.put("status", hA.get("status"));
					
//					view_LatestPergerakanPegawai(session);
//					Vector paparLatestPergerakanPegawai = FrmKemaskiniPergerakanFailData.getDataLatestPergerakanPegawai(idFail);
//					Hashtable h = (Hashtable)paparLatestPergerakanPegawai.get(0);
//					
//					System.out.println(h.get("idPenerima"));
//					String a = "";
//					if (h.get("idPenerima") == "0"){
//						a = HTML.SelectPegawai("socPegawaiA");
//					}
//					else if(h.get("idPenerima") != null){
//						a = HTML.SelectPegawai("socPegawaiA",Long.parseLong(h.get("idPenerima").toString()),"readonly");
//					}
//					System.out.println(a);
//					context.put("selectPegawaiA",a);
//					
//					context.put("user_Name",(String)session.getAttribute("_portal_username"));
//					context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
//				    context.put("tarikhFailMsk", "");
//				    context.put("tarikhFailKeluar","");
//				    context.put("tempohSdpDari","");
//				    context.put("tempohSdpHingga","");
//				    context.put("catatan","");
//				    context.put("bilPergerakan","bilPergerakan");
						
					
					
					
					//context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA"));
					
					// check ada tak pergerakan fail sebelum ni. kalau ada, letak nama previous penerima sebagai penghantar (by default)
					//String previousPegawai = FrmKemaskiniPergerakanFailData.getPreviousPenerimaFail(idFail);
					//context.put("namaPenghantar", previousPegawai);
//					context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB"));
//			    	context.put("tarikhFailMsk", "");
//			    	context.put("tarikhFailKeluar","");
//			    	context.put("tempohSdpDari","");
//			    	context.put("tempohSdpHingga","");
//			    	context.put("catatan","");

					
					

					 
					listPergerakanFail(session);
		    		list = FrmKemaskiniPergerakanFailData.getListPergerakanFail();
		    		this.context.put("Senaraipergerakan",list);
			}
			 
			 else if ("simpanPF".equals(action1)){

					vm = "app/pfd/frmKemaskiniEditPergerakanFail.jsp";
					context.put("mode", "papar");
					context.put("readonly","disabled");
					context.put("disabled","disabled");
					 
					Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
					Hashtable hA = (Hashtable)paparMaklumat.get(0);
							
					context.put("idFail", hA.get("idFail"));
					context.put("noFail", hA.get("noFail"));
					context.put("tajukFail", hA.get("tajukFail"));
					context.put("status", hA.get("status"));	
						
					String idPergerakanfail =simpanPergerakanFail(session);
					 	
					Vector paparPergerakanFail = FrmKemaskiniPergerakanFailData.paparPergerakanFail(idPergerakanfail);
						 
					Hashtable hB = (Hashtable)paparPergerakanFail.get(0);
							
					context.put("idPergerakanfail", hB.get("idPergerakanfail"));
					context.put("idFail", hB.get("idFail"));
					context.put("user_Name", hB.get("namaKerani"));
					context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(hB.get("idPenghantar").toString()),"disabled"));
					context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(hB.get("idPenerima").toString()),"disabled"));
					context.put("tarikhFailMsk", hB.get("tarikhMasuk"));
					context.put("tarikhFailKeluar", hB.get("tarikhKeluar"));
					context.put("tempohSdpDari", hB.get("tarikhSDPdari"));
					context.put("tempohSdpHingga", hB.get("tarikhSDPhingga"));
					context.put("catatan", hB.get("catatan"));
					 
					listPergerakanFail(session);
		    		list = FrmKemaskiniPergerakanFailData.getListPergerakanFail();
		    		this.context.put("Senaraipergerakan",list);
			}
			 
			 
			 else if ("kemaskiniPergerakanfail".equals(action1)){

					vm = "app/pfd/frmKemaskiniEditPergerakanFail.jsp";
					context.put("mode", "kemaskini");
					context.put("readonly","");
					context.put("disabled","");
					 
					Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
					Hashtable hA = (Hashtable)paparMaklumat.get(0);
							
					context.put("idFail", hA.get("idFail"));
					context.put("noFail", hA.get("noFail"));
					context.put("tajukFail", hA.get("tajukFail"));
					context.put("status", hA.get("status"));	
						
					String idPergerakanfail = getParam("idPergerakanfail");
				 	
					Vector paparPergerakanFail = FrmKemaskiniPergerakanFailData.paparPergerakanFail(idPergerakanfail);
						 
					Hashtable hB = (Hashtable)paparPergerakanFail.get(0);
							
					
					context.put("idFail", hB.get("idFail"));
					context.put("user_Name", hB.get("namaKerani"));
					context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(hB.get("idPenghantar").toString()),""));
					context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(hB.get("idPenerima").toString()),""));
					context.put("tarikhFailMsk", hB.get("tarikhMasuk"));
					context.put("tarikhFailKeluar", hB.get("tarikhKeluar"));
					context.put("tempohSdpDari", hB.get("tarikhSDPdari"));
					context.put("tempohSdpHingga", hB.get("tarikhSDPhingga"));
					context.put("catatan", hB.get("catatan"));
					 
					listPergerakanFail(session);
		    		list = FrmKemaskiniPergerakanFailData.getListPergerakanFail();
		    		this.context.put("Senaraipergerakan",list);
			 }
			 
			 else if ("updatePergerakanfail".equals(action1)){

					updatePergerakanfail(session);
				 	vm = "app/pfd/frmKemaskiniEditPergerakanFail.jsp";
					context.put("mode", "paparUpdate");
					context.put("readonly","readonly");
					context.put("disabled","disabled");
					 
					Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
					Hashtable hA = (Hashtable)paparMaklumat.get(0);
							
					context.put("idFail", hA.get("idFail"));
					context.put("noFail", hA.get("noFail"));
					context.put("tajukFail", hA.get("tajukFail"));
					context.put("status", hA.get("status"));	
						
					String idPergerakanfail = getParam("idPergerakanfail");
				 	
					Vector paparPergerakanFail = FrmKemaskiniPergerakanFailData.paparPergerakanFail(idPergerakanfail);
						 
					Hashtable hB = (Hashtable)paparPergerakanFail.get(0);
							
					
					context.put("idFail", hB.get("idFail"));
					context.put("user_Name", hB.get("namaKerani"));
					context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(hB.get("idPenghantar").toString()),"disabled"));
					//context.put("namaPenghantar", hB.get("namaPenghantar"));
					context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(hB.get("idPenerima").toString()),"disabled"));
					context.put("tarikhFailMsk", hB.get("tarikhMasuk"));
					context.put("tarikhFailKeluar", hB.get("tarikhKeluar"));
					context.put("tempohSdpDari", hB.get("tarikhSDPdari"));
					context.put("tempohSdpHingga", hB.get("tarikhSDPhingga"));
					context.put("catatan", hB.get("catatan"));
					
					listPergerakanFail(session);
		    		list = FrmKemaskiniPergerakanFailData.getListPergerakanFail();
		    		this.context.put("Senaraipergerakan",list);
			 }
			 
			 else if ("paparPergerakanfail".equals(action1)){

				 	vm = "app/pfd/frmKemaskiniEditPergerakanFail.jsp";
					context.put("mode", "papar");
					context.put("readonly","readonly");
					context.put("disabled","disabled");
					 
					Vector paparMaklumat = FrmKemaskiniPergerakanFailData.paparMaklumatFail(idFail);
					Hashtable hA = (Hashtable)paparMaklumat.get(0);
							
					context.put("idFail", hA.get("idFail"));
					context.put("noFail", hA.get("noFail"));
					context.put("tajukFail", hA.get("tajukFail"));
					context.put("status", hA.get("status"));	
						
					String idPergerakanfail = getParam("idPergerakanfail");
				 	
					Vector paparPergerakanFail = FrmKemaskiniPergerakanFailData.paparPergerakanFail(idPergerakanfail);
						 
					Hashtable hB = (Hashtable)paparPergerakanFail.get(0);
							
					context.put("idPergerakanfail", hB.get("idPergerakanfail"));
					context.put("idFail", hB.get("idFail"));
					context.put("user_Name", hB.get("namaKerani"));
					context.put("selectPegawaiA",HTML.SelectPegawai("socPegawaiA",Long.parseLong(hB.get("idPenghantar").toString()),"disabled"));
					context.put("selectPegawaiB",HTML.SelectPegawai("socPegawaiB",Long.parseLong(hB.get("idPenerima").toString()),"disabled"));
					context.put("tarikhFailMsk", hB.get("tarikhMasuk"));
					context.put("tarikhFailKeluar", hB.get("tarikhKeluar"));
					context.put("tempohSdpDari", hB.get("tarikhSDPdari"));
					context.put("tempohSdpHingga", hB.get("tarikhSDPhingga"));
					context.put("catatan", hB.get("catatan"));
					 
					listPergerakanFail(session);
		    		list = FrmKemaskiniPergerakanFailData.getListPergerakanFail();
		    		this.context.put("Senaraipergerakan",list);
			 }	 
			 else
			 {
					String user_name = (String)session.getAttribute("_portal_username");
	  	    		String user_id = (String)session.getAttribute("_ekptg_user_id");
	  	    		String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
	  	    		String portal_role = (String)session.getAttribute("_portal_role");
	  	    		String myrole = (String)session.getAttribute("myrole");

	  	    		view_Seksyen(session);
		         	paparSeksyen = FrmLogDokumenData.getDataSeksyen();
		         	Hashtable hA = (Hashtable) paparSeksyen.get(0);
		         	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
		         	String id_negeri = String.valueOf(hA.get("id_negeri"));
		         	context.put("idSeksyen",(hA.get("id_seksyen")));
		         	context.put("idNegeri",(hA.get("id_negeri")));
		         	
	  	    		//context.put("user_Role",user_role );
	  	    		context.put("user_Name", user_name);
	  	    		context.put("user_Id", user_id);
   	    		
		    	 vm = "app/pfd/frmDaftarPergerakanFail.jsp";
		    	 
		    	 this.context.put("JumlahFail",FrmDaftarPergerakanFailData.totalFail(session));
		    	 
		    	 if (!"".equals(getParam("txtNoFail")))
       	    		noFail = getParam("txtNoFail");
		    	 if (!"".equals(getParam("txtNoFailLama")))
		  	    		noFailLama = getParam("txtNoFailLama");
     			if (!"".equals(getParam("txtTajukFail")))
     				tajukFail = getParam("txtTajukFail");
     			if (!"".equals(getParam("socNegeriD")))
     				negeri = getParam("socNegeriD");
     			if (!"".equals(getParam("socSeksyenD")))
     				seksyen = getParam("socSeksyenD");
     			if (!"".equals(getParam("socStatusD")))
     				status = getParam("socStatusD");
     			if (!"".equals(getParam("txdTarikhDaftar")))
     				tarikhDaftar = getParam("txdTarikhDaftar");
     			
    	    	FrmDaftarPergerakanFailData.setCarianFail(noFail,noFailLama,tajukFail,negeri,seksyen,status,tarikhDaftar, user_id, myrole, user_negeri, id_seksyen);
     	    	list = FrmDaftarPergerakanFailData.getList();
     	    	this.context.put("SenaraiFail", list);
     	    	this.context.put("selectNegeriD",HTML.SelectNegeri("socNegeriD",Utils.parseLong(negeri),""));
     	    	this.context.put("selectSeksyenD",HTML.SelectSeksyen("socSeksyenD",Utils.parseLong(seksyen),""));
     	    	this.context.put("selectStatusD",HTML.SelectStatusFail("socStatusD",Utils.parseLong(status),""));
     	    	this.context.put("txtNoFail", noFail);
     	    	this.context.put("txtNoFailLama", noFailLama);
     	    	this.context.put("txtTajukFail", tajukFail);
     	    	this.context.put("txdTarikhDaftar", tarikhDaftar);
     	    	
     	    	setupPage(session,action1,list);
	    } 
		     
			 
 		     return vm;
	    }


		private void hapus(HttpSession session) throws Exception {
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String user_name = (String)session.getAttribute("_portal_username");
    		String idFail = getParam("idFail");
    		String idPergerakanfail = getParam("idPergerakanfail");
    	
    		Hashtable h = new Hashtable();
    		
			h.put("id_Fail",getParam("idFail"));
			h.put("idPergerakanfail",getParam("idPergerakanfail"));
			h.put("id_Masuk",user_id);
			
			FrmKemaskiniPergerakanFailData.hapus(h);
		}


		private String update(HttpSession session) throws Exception {
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String user_name = (String)session.getAttribute("_portal_username");
    		String idFail = getParam("idFail");
    		String idPergerakanfail = getParam("idPergerakanfail");
    	
    		Hashtable h = new Hashtable();
    		
    		h.put("id_pegawai_pinjamfail",user_id);
    		h.put("nama_pegawai_pinjamfail",user_name);
    		h.put("id_pegawai_PTfail", getParam("socPTFail"));
    		//h.put("nama_pegawai_PTfail", getParam("namaPenghantar"));
			h.put("tujuan",getParam("txtTujuan"));
			h.put("tempoh_Pinjam_Dari",getParam("txtTempohPinjamDari"));
			h.put("tempoh_Pinjam_Hingga",getParam("txtTempohPinjamHingga"));
			h.put("status_pinjaman_fail","1");
			h.put("id_Fail",getParam("idFail"));
			h.put("idPergerakanfail",getParam("idPergerakanfail"));
			h.put("id_Masuk",user_id);
			
			return FrmKemaskiniPergerakanFailData.update(h);
			
		}


		private void view_Seksyen(HttpSession session) throws Exception {
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			FrmLogDokumenData.setSeksyen(user_id);
			
		}


		private void listPergerakanPemohonFailUser(HttpSession session) throws Exception {
		    
		    String user_id = (String)session.getAttribute("_ekptg_user_id");
		    String idFail = getParam("idFail");
		    FrmKemaskiniPergerakanFailData.setListPergerakanPemohonFailUser(idFail);
			
		}


		private void listPTFail(HttpSession session) throws Exception {
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String user_negeri = (String)session.getAttribute("_ekptg_user_negeri");
		    FrmKemaskiniPergerakanFailData.setListPTFail(user_id, user_negeri);
			
		}


		private void view_LatestPergerakanPegawai(HttpSession session) throws Exception {
			String id = getParam("idFail");
	    	System.out.println("idFail"+id);
	    	FrmKemaskiniPergerakanFailData.setDataLatestPergerakanPegawai(id);
			
		}



		private void updatePergerakanfail(HttpSession session) throws Exception {
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String user_name = (String)session.getAttribute("_portal_username");
    		String idPergerakanfail = getParam("idPergerakanfail");
    	
    		Hashtable h = new Hashtable();
    		
    		h.put("idPergerakanfail",idPergerakanfail);
    		h.put("nama_kerani",user_name);
    		h.put("idPegawaipenghantar", Integer.parseInt(getParam("socPegawaiA")));
    		//h.put("namaPenghantar", getParam("namaPenghantar"));
			h.put("idPegawaipenerima",Integer.parseInt(getParam("socPegawaiB")));
			h.put("tarikh_Fail_Masuk",getParam("txtTarikhFailMsk"));
			h.put("tarikh_Fail_Keluar",getParam("txtTarikhFailKeluar"));
			h.put("tempoh_Sdp_Dari",getParam("txtTempohSDPDari"));
			h.put("tempoh_Sdp_Hingga",getParam("txtTempohSDPHingga"));
			h.put("catatan",getParam("txtCatatan"));
			h.put("id_Fail",getParam("idFail"));
			h.put("id_Kemaskini",user_id);
			
    		FrmKemaskiniPergerakanFailData.update(h);
		}

		public void setupPage(HttpSession session,String action1,Vector list) {
			try {
			
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
			}
		    
		    if ("getNext".equals(action1)) {
		    	page++;
		    } else if ("getPrevious".equals(action1)) {
		    	page--;
		    } else if ("getPage".equals(action1)) {
		    	page = getParamAsInteger("value");
		    } else if ("doChangeItemPerPage".equals(action1)) {
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
		private void view_pergerakan(HttpSession session) throws Exception {
	    	int id = Integer.parseInt(getParam("idPergerakanfail"));
		    FrmKemaskiniPergerakanFailData.setDataPergerakanFail(id);
		   
		  }
		private void check_fail(HttpSession session) throws Exception {
			int id = Integer.parseInt(getParam("idFail"));
		    FrmKemaskiniPergerakanFailData.setNoFail(id);
		   
		  }
		private void view_fail(HttpSession session) throws Exception {
	    	String id = getParam("idFail");
		    FrmKemaskiniPergerakanFailData.setDataFail(id);
		   
		  }
		private void listPergerakanFail(HttpSession session) throws Exception {
	    	int id = Integer.parseInt(getParam("idFail"));
		    FrmKemaskiniPergerakanFailData.setListPergerakanFail(id);
		   
		  }
		private String simpanPergerakanFail(HttpSession session)throws Exception{
			
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String user_name = (String)session.getAttribute("_portal_username");
    		String idFail = getParam("idFail");
    	
    		Hashtable h = new Hashtable();
    		
    		h.put("id_pegawai_pinjamfail",user_id);
    		h.put("nama_pegawai_pinjamfail",user_name);
    		h.put("id_pegawai_PTfail", getParam("socPTFail"));
    		//h.put("nama_pegawai_PTfail", getParam("namaPenghantar"));
			h.put("tujuan",getParam("txtTujuan"));
			h.put("tempoh_Pinjam_Dari",getParam("txtTempohPinjamDari"));
			h.put("tempoh_Pinjam_Hingga",getParam("txtTempohPinjamHingga"));
			h.put("status_pinjaman_fail","1");
			h.put("id_Fail",getParam("idFail"));
			h.put("id_Masuk",user_id);
			
    		return FrmKemaskiniPergerakanFailData.add(h);
			
		}
		

}

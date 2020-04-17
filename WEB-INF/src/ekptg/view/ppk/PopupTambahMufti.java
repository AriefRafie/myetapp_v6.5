/**
 * 
 */
package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmPrmhnnSek8BicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;
//import lebah.portal.AjaxBasedModule;

/**
 * @author elly
 *
 */

	
public class PopupTambahMufti extends VTemplate{
		
		static Logger myLogger = Logger.getLogger(PopupTambahMufti.class);
		
		public Template doTemplate() throws Exception{
			
				FrmPrmhnnSek8BicaraData logic3 = new FrmPrmhnnSek8BicaraData();
				FrmPrmhnnSek8KptsanBicaraData logic4 = new FrmPrmhnnSek8KptsanBicaraData();
				
				Vector list = new Vector();
				Vector PerintahTangguhROTS = new Vector();
			
				HttpSession session = request.getSession();
				String submit = getParam("command2");  
        		String submit3 = getParam("command3");
				
				String vm = "";		
			
				vm = "app/ppk/frmPopupTambahMufti.jsp";
				
				
	    		String id = getParam("idPermohonan");
	    		context.put("idPermohonan", id);
	    		
	    		String id_perbicaraan = getParam("id_perbicaraan");
	    		context.put("id_perbicaraan", getParam("id_perbicaraan"));
	    		
	    		String ekptg_user_id = getParam("ekptg_user_id");
	    		context.put("ekptg_user_id", getParam("ekptg_user_id"));
	    		
	    		String id_perintah = "";
	    		if (id_perintah == ""){
	    			id_perintah = getParam("id_perintah");
		    		context.put("id_perintah", id_perintah);
	    		}else{
	    			context.put("id_perintah", "");
	    		}
	    		
	    		
	    		//* DROP DOWN 
	    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:305 onChange=\"doChangeidNegeri();\" "));
	    		context.put("selectBandar", HTML.SelectBandar("socBandar",null," disabled"));
	    		context.put("selectPegawai", HTML.SelectPegawai("socBandar", null, " disabled"));
	    		// CLOSE
	    		
	    		myLogger.info("idPermohonan(TAMBAHMUFTI) = " +getParam("idPermohonan"));  
	    		myLogger.info("id_perbicaraan(TAMBAHMUFTI) = " +getParam("id_perbicaraan")); 
	    		myLogger.info("id_perintah(TAMBAHMUFTI) = " +getParam("id_perintah")); 
	    		
	    		
				//* GET ID_BORANGJ
				Hashtable getIdBorangJ = logic3.getIdBorangJ(id_perbicaraan);	
				String idBorangJ = "";
				if ( getIdBorangJ.size() != 0 ){
					idBorangJ = getIdBorangJ.get("id_borangj").toString();
				}else{
					context.put("idBorangJ", "");
				}
				// CLOSE
	    		
	    		//* GET INFO TANGGUH (ALAMAT PEJABAT, POSKOD ETC)
				PerintahTangguhROTS = logic3.setTangguhROTS(id_perbicaraan,id_perintah,idBorangJ);
	    		String alamatpejabat = "";
				if ( PerintahTangguhROTS.size() != 0 ){
					
					Hashtable e = (Hashtable) PerintahTangguhROTS.get(0);
					this.context.put("infoPerintahTangguhROTS", PerintahTangguhROTS);
					alamatpejabat = e.get("alamat_pejabat").toString();
					
	    		}else{
	    			this.context.put("infoPerintahTangguhROTS", "");
	    		}
	    		// CLOSE
				
	    		//* CHECKING MODE
	    		if ( alamatpejabat != "" ){
	    			context.put("modePM", "edit");
	    		}else{
	    			context.put("modePM", "add");
	    		}
	    		// CLOSE
	    		
	            
	        	if(submit.equals("doChangeidNegeri")){
	
	        		//get info pemohon	        		
	        		String usid="";  
	           		usid=(String)session.getAttribute("_ekptg_user_id");
	           		
	        		logic3.setListSemak(id,usid);
	        		list = logic3.getListSemak();
	        		Hashtable ls = (Hashtable) list.get(0);
	        		String idSimati = (String)ls.get("idSimati");
	        		String idStatus = (String)ls.get("id_Status");
	        		String idPejabatJKPTG = (String)ls.get("id_pejabatjkptg");
	        		String idNegeriMhn = (String)ls.get("pmidnegeri"); 
	        		//close		        		
	        		
	        		//* DROP DOWN
	        		String idnegeri = getParam("socNegeri");
	        		String idUnitPSK = getParam("socPegawai");
	        		
	        		context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri), "style=width:305 onChange=\"doChangeidNegeri();\" "));
	        		context.put("selectBandar", HTML.SelectBandarByNegeri(idnegeri, "socBandar", null, "style=width:305 "));
	        		context.put("selectPegawai", HTML.SelectPegawaiPengendaliByJKPTG(idPejabatJKPTG, "socPegawai", Utils.parseLong(idUnitPSK), "style=width:305 "));
	        		// CLOSE
	        		
	             	vm = "app/ppk/frmPopupTambahMufti.jsp";
	             	
	             	
	           	}else if(submit3.equals("SimpanPejMufti")){
	           		
	        		//GET INFO PEMOHON	        		
	        		String usid="";  
	           		usid=(String)session.getAttribute("_ekptg_user_id");
	           		
	        		logic3.setListSemak(id,usid);
	        		list = logic3.getListSemak();
	        		Hashtable ls = (Hashtable) list.get(0);
	        		String idSimati = (String)ls.get("idSimati");
	        		String idStatus = (String)ls.get("id_Status");
	        		String idPejabatJKPTG = (String)ls.get("id_pejabatjkptg");
	        		String idNegeriMhn = (String)ls.get("pmidnegeri"); 
	        		//CLOSE
	        		
	        		String idUnitPSK = getParam("socPegawai");
	           		
	           		//* DROP DOWN
	           		context.put("selectPegawai", HTML.SelectPegawaiPengendaliByJKPTG(idPejabatJKPTG, "socPegawai", Utils.parseLong(idUnitPSK), "style=width:305 "));
	           		// CLOSE
	           		
	           		//* GET VALUE
	           		String idNegeri = getParam("socNegeri");
	           		String txtnamapej = getParam("txtnamapej");
	           		String txtAlamat1 = getParam("txtAlamat1");
	           		String txtAlamat2 = getParam("txtAlamat2");
	           		String txtAlamat3 = getParam("txtAlamat3");
	           		String txtPoskod = getParam("txtPoskod");
	           		String idBandar = getParam("socBandar");
	           		String txtTelefon = getParam("txtTelefon");
	           		String txtfax = getParam("txtfax");
	           		// CLOSE
	           		 
	           		if ( id_perintah != "" ){
	           			
		           		//* INSERT TBLPPKBORANGJ & UPDATE TBLPPKPERINTAH
		           		insertPejMuftiIDP(id_perintah,id_perbicaraan,ekptg_user_id,idNegeri,txtnamapej,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,idBandar,txtTelefon,txtfax);
		           		// CLOSE
	           			
	           		}else{
	           			
	           			//* INSERT TBLPPKBORANGJ & INSERT TBLPPKPERINTAH
	           			insertPejMufti(id_perbicaraan,ekptg_user_id,idNegeri,txtnamapej,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,idBandar,txtTelefon,txtfax);
	           			// CLOSE
	
	           		}
	           		

	           		//* CALL FLAG
	           		context.put("addMode", "no");
					context.put("viewMode", "no");
					context.put("viewEditMode", "no");
					context.put("editMode", "yes");
					context.put("flag", "permohonan");
					context.put("button", "");
					context.put("jenispejabat", "");
					// CLOSE

	           		
	           		vm = "app/ppk/frmPopupTambahMufti.jsp";
	        	}
	          
	            Template template = engine.getTemplate(vm);
	            return template;
	    }

		private void insertPejMuftiIDP(String id_perintah,String id_perbicaraan,String ekptg_user_id,String idNegeri,String txtnamapej,String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,String idBandar,String txtTelefon,String txtfax ) throws Exception {	

			FrmPrmhnnSek8BicaraData.insertPejMuftiIDP(id_perintah,id_perbicaraan,ekptg_user_id,idNegeri,txtnamapej,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,idBandar,txtTelefon,txtfax );
		
		}
		
		private void insertPejMufti(String id_perbicaraan,String ekptg_user_id,String idNegeri,String txtnamapej,String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,String idBandar,String txtTelefon,String txtfax ) throws Exception {	

			FrmPrmhnnSek8BicaraData.insertPejMufti(id_perbicaraan,ekptg_user_id,idNegeri,txtnamapej,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,idBandar,txtTelefon,txtfax );
		
		}		
}

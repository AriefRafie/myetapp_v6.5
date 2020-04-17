package ekptg.view.online;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.online.FrmSemakanStatusPermohonanData;

public class FrmSemakanStatusPermohonan extends AjaxBasedModule{


	public String doTemplate2() throws Exception {
		
		String vm = "app/online/frmSemakanStatusPermohonan.jsp";
		String semakan = getParam("socSeksyen");
		this.context.put("semakan",semakan);
		String checkRadio = getParam("checkedRadio");
		String action = getParam("action");
		this.context.put("action",action);
		Vector list = new Vector();
		String carianPPK = "";
		String noPermohonanOnline = "";
		String noFail = "";
		String namaPemohon = "";
		String noKPPemohon = "";
		String noHakmilik = "";
		String tkhPermohonan = "";
		String urusanPermohonan = "";
		String statusFail = getParam("socStatus");
		HttpSession session = this.request.getSession();
		
		if ("".equals(semakan)){
			
			this.context.put("hide","0");
			this.context.put("selected0","selected");
			this.context.put("selected1","");
			this.context.put("selected2","");
			this.context.put("selected3","");
			this.context.put("selected4","");
		}
		else if ("0".equals(semakan)){
			this.context.put("hide","1");
			this.context.put("selected0","selected");
			this.context.put("selected1","");
			this.context.put("selected2","");
			this.context.put("selected3","");
			this.context.put("selected4","");
			
		}
		else if ("1".equals(semakan)){
			this.context.put("hide","1");
			this.context.put("selected0","");
			this.context.put("selected1","selected");
			this.context.put("selected2","");
			this.context.put("selected3","");
			this.context.put("selected4","");
			
			if ("".equals(checkRadio)){
				this.context.put("radioChecked1","checked");
				this.context.put("radioChecked2","");
				this.context.put("radioChecked3","");
				this.context.put("radioChecked4","");
			}
			if ("noFail".equals(checkRadio)){
				this.context.put("radioChecked1","checked");
				this.context.put("radioChecked2","");
				this.context.put("radioChecked3","");
				this.context.put("radioChecked4","");
				this.context.put("checkedRadio",checkRadio);
				
				 if (("next".equals(action)) || ("previous".equals(action))){
					 if (!"".equals(getParam("txtCarianPPK"))){
				  	    	carianPPK = getParam("txtCarianPPK");}
						
						FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
						list = FrmSemakanStatusPermohonanData.getListPPK();
						prepareItemForDisplay(session,list,10,action);
						this.context.put("txtCarianPPK", carianPPK);
				 }
				 else{
					 if (!"".equals(getParam("txtCarianPPK"))){
				  	    	carianPPK = getParam("txtCarianPPK");}
						
						FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
						list = FrmSemakanStatusPermohonanData.getListPPK();
						prepareItemForDisplay(session,list,10,"first");
						this.context.put("txtCarianPPK", carianPPK);
				 }
				
			}	
			else if ("noPermohonanOnline".equals(checkRadio)){
				this.context.put("radioChecked1","");
				this.context.put("radioChecked2","checked");
				this.context.put("radioChecked3","");
				this.context.put("radioChecked4","");
				this.context.put("checkedRadio",checkRadio);
				

				 if (("next".equals(action)) || ("previous".equals(action))){
					 if (!"".equals(getParam("txtCarianPPK"))){
				  	    	carianPPK = getParam("txtCarianPPK");}
						
						FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
						list = FrmSemakanStatusPermohonanData.getListPPK();
						prepareItemForDisplay(session,list,10,action);
						this.context.put("txtCarianPPK", carianPPK);
				 }
				 else{
					 if (!"".equals(getParam("txtCarianPPK"))){
				  	    	carianPPK = getParam("txtCarianPPK");}
						
						FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
						list = FrmSemakanStatusPermohonanData.getListPPK();
						prepareItemForDisplay(session,list,10,"first");
						this.context.put("txtCarianPPK", carianPPK);
				 }
			}
			else if ("noKPPemohon".equals(checkRadio)){
				this.context.put("radioChecked1","");
				this.context.put("radioChecked2","");
				this.context.put("radioChecked3","checked");
				this.context.put("radioChecked4","");
				this.context.put("checkedRadio",checkRadio);

				 if (("next".equals(action)) || ("previous".equals(action))){
					 if (!"".equals(getParam("txtCarianPPK"))){
				  	    	carianPPK = getParam("txtCarianPPK");}
						
						FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
						list = FrmSemakanStatusPermohonanData.getListPPK();
						prepareItemForDisplay(session,list,10,action);
						this.context.put("txtCarianPPK", carianPPK);
				 }
				 else{
					 if (!"".equals(getParam("txtCarianPPK"))){
				  	    	carianPPK = getParam("txtCarianPPK");}
						
						FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
						list = FrmSemakanStatusPermohonanData.getListPPK();
						prepareItemForDisplay(session,list,10,"first");
						this.context.put("txtCarianPPK", carianPPK);
				 }		
				
			}
			else if ("noSijilMati".equals(checkRadio)){
				this.context.put("radioChecked1","");
				this.context.put("radioChecked2","");
				this.context.put("radioChecked3","");
				this.context.put("radioChecked4","checked");
				this.context.put("checkedRadio",checkRadio);
				

				 if (("next".equals(action)) || ("previous".equals(action))){
					 if (!"".equals(getParam("txtCarianPPK"))){
				  	    	carianPPK = getParam("txtCarianPPK");}
						
						FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
						list = FrmSemakanStatusPermohonanData.getListPPK();
						prepareItemForDisplay(session,list,10,action);
						this.context.put("txtCarianPPK", carianPPK);
				 }
				 else{
					 if (!"".equals(getParam("txtCarianPPK"))){
				  	    	carianPPK = getParam("txtCarianPPK");}
						
						FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
						list = FrmSemakanStatusPermohonanData.getListPPK();
						prepareItemForDisplay(session,list,10,"first");
						this.context.put("txtCarianPPK", carianPPK);
				 }
			}
				FrmSemakanStatusPermohonanData.setCarianPermohonanPPK(checkRadio, carianPPK);
				list = FrmSemakanStatusPermohonanData.getListPPK();
				this.context.put("SenaraiPPK", list);
				prepareItemForDisplay(session,list,10,"first");
				this.context.put("txtCarianPPK", carianPPK);

	
		}
		else if ("2".equals(semakan)){
			this.context.put("hide","2");
			this.context.put("selected0","");
			this.context.put("selected1","");
			this.context.put("selected2","selected");
			this.context.put("selected3","");
			this.context.put("selected4","");
			this.context.put("txtNoFail", noFail);
		    this.context.put("txtNoPermohonanOnline", noPermohonanOnline);
		    this.context.put("txdTarikhPermohonan", tkhPermohonan);
			this.context.put("selectStatus",HTML.SelectStatus("socStatus",null,null));
			
			 if (("next".equals(action)) || ("previous".equals(action))){
	      	    	
	      	    	if (!"".equals(getParam("txtNoPermohonanOnline"))){
			  	    	noPermohonanOnline = getParam("txtNoPermohonanOnline");}
					if (!"".equals(getParam("txtNoFail"))){
						noFail = getParam("txtNoFail");}
					if (!"".equals(getParam("txdTarikhPermohonan"))){
						tkhPermohonan = getParam("txdTarikhPermohonan");}
					if (!"".equals(getParam("socStatus"))){
						statusFail = getParam("socStatus");}
					
					FrmSemakanStatusPermohonanData.setCarianPermohonanPPT(noPermohonanOnline, noFail, tkhPermohonan, statusFail);
					list = FrmSemakanStatusPermohonanData.getListPPT();
					prepareItemForDisplay(session,list,10,action);
					this.context.put("txtNoFail", noFail);
	  		    	this.context.put("txtNoPermohonanOnline", noPermohonanOnline);
	  		    	this.context.put("txdTarikhPermohonan", tkhPermohonan);
	  		    	this.context.put("selectStatus",HTML.SelectStatus("socStatus",Utils.parseLong(statusFail),null));
		
	      		  
	      	    }
			 else{
				if (!"".equals(getParam("txtNoPermohonanOnline"))){
		  	    	noPermohonanOnline = getParam("txtNoPermohonanOnline");}
				if (!"".equals(getParam("txtNoFail"))){
					noFail = getParam("txtNoFail");}
				if (!"".equals(getParam("txdTarikhPermohonan"))){
					tkhPermohonan = getParam("txdTarikhPermohonan");}
				if (!"".equals(getParam("socStatus"))){
					statusFail = getParam("socStatus");}
				
				FrmSemakanStatusPermohonanData.setCarianPermohonanPPT(noPermohonanOnline, noFail, tkhPermohonan, statusFail);
				list = FrmSemakanStatusPermohonanData.getListPPT();
				prepareItemForDisplay(session,list,10,"first");
				this.context.put("txtNoFail", noFail);
  		    	this.context.put("txtNoPermohonanOnline", noPermohonanOnline);
  		    	this.context.put("txdTarikhPermohonan", tkhPermohonan);
  		    	this.context.put("selectStatus",HTML.SelectStatus("socStatus",Utils.parseLong(statusFail),null));
	
			 }
		}
		else if ("3".equals(semakan)){
			this.context.put("hide","3");
			this.context.put("selected0","");
			this.context.put("selected1","");
			this.context.put("selected2","");
			this.context.put("selected3","selected");
			this.context.put("selected4","");
			this.context.put("selectStatus",HTML.SelectStatus("socStatus",null,null));
			
			 if (("next".equals(action)) || ("previous".equals(action))){
				 
				 
				 if (!"".equals(getParam("txtNoFail"))){
						noFail = getParam("txtNoFail");}
				 if (!"".equals(getParam("txtNamaPemohon"))){
						namaPemohon = getParam("txtNamaPemohon");}
				 if (!"".equals(getParam("txtNoKP"))){
						noKPPemohon = getParam("txtNoKP");}
				 if (!"".equals(getParam("socStatus"))){
						statusFail = getParam("socStatus");}
				 
				 FrmSemakanStatusPermohonanData.setCarianPermohonanPHP(noFail, namaPemohon,noKPPemohon,statusFail);
				 list = FrmSemakanStatusPermohonanData.getListPHP();
				 prepareItemForDisplay(session,list,10,action);
				 this.context.put("txtNoFail", noFail);
	  		     this.context.put("txtNamaPemohon", namaPemohon);
	  		     this.context.put("txtNoKP", noKPPemohon);
	  		     this.context.put("selectStatus",HTML.SelectStatus("socStatus",Utils.parseLong(statusFail),null));
		
				 
			 }
			 else{
				 
				 
				 if (!"".equals(getParam("txtNoFail"))){
						noFail = getParam("txtNoFail");}
				 if (!"".equals(getParam("txtNamaPemohon"))){
						namaPemohon = getParam("txtNamaPemohon");}
				 if (!"".equals(getParam("txtNoKP"))){
						noKPPemohon = getParam("txtNoKP");}
				 if (!"".equals(getParam("socStatus"))){
						statusFail = getParam("socStatus");}
				 
				 FrmSemakanStatusPermohonanData.setCarianPermohonanPHP(noFail, namaPemohon,noKPPemohon,statusFail);
				 list = FrmSemakanStatusPermohonanData.getListPHP();
				 prepareItemForDisplay(session,list,10,"first");
				 this.context.put("txtNoFail", noFail);
	  		     this.context.put("txtNamaPemohon", namaPemohon);
	  		     this.context.put("txtNoKP", noKPPemohon);
	  		     this.context.put("selectStatus",HTML.SelectStatus("socStatus",Utils.parseLong(statusFail),null));
		
			 }
			
		}
		else if ("4".equals(semakan)){
			this.context.put("hide","4");
			this.context.put("selected0","");
			this.context.put("selected1","");
			this.context.put("selected2","");
			this.context.put("selected3","");
			this.context.put("selected4","selected");
			this.context.put("selectStatus",HTML.SelectStatus("socStatus",null,null));

			 if (("next".equals(action)) || ("previous".equals(action))){
				
				 if (!"".equals(getParam("txtNoFail"))){
						noFail = getParam("txtNoFail");}
				 if (!"".equals(getParam("txtNoHakmilik"))){
						noHakmilik = getParam("txtNoHakmilik");}
				 if (!"".equals(getParam("socStatus"))){
						statusFail = getParam("socStatus");}
				 
				 FrmSemakanStatusPermohonanData.setCarianPermohonanHTP(noFail, noHakmilik, statusFail);
				 list = FrmSemakanStatusPermohonanData.getListHTP();
				 prepareItemForDisplay(session,list,10,action);
				 this.context.put("txtNoFail", noFail);
	  		     this.context.put("txtNoHakmilik", noHakmilik);
	  		   	 this.context.put("selectStatus",HTML.SelectStatus("socStatus",Utils.parseLong(statusFail),null));
		
			 }
			 else{
				 if (!"".equals(getParam("txtNoFail"))){
						noFail = getParam("txtNoFail");}
				 if (!"".equals(getParam("txtNoHakmilik"))){
						noHakmilik = getParam("txtNoHakmilik");}
				 if (!"".equals(getParam("socStatus"))){
						statusFail = getParam("socStatus");}
				 
				 FrmSemakanStatusPermohonanData.setCarianPermohonanHTP(noFail, noHakmilik, statusFail);
				 list = FrmSemakanStatusPermohonanData.getListHTP();
				 prepareItemForDisplay(session,list,10,"first");
				 this.context.put("txtNoFail", noFail);
	  		     this.context.put("txtNoHakmilik", noHakmilik);
	  		     this.context.put("selectStatus",HTML.SelectStatus("socStatus",Utils.parseLong(statusFail),null));
		
			 }
			
			
		}
		return vm;
	}
	
	 private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String action)
	  {
	    int x;
	    int startno = 0;
	    if (action == null) action = "first";
	    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
	    if (action.equals("previous"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x > 0) {
	          startno -= x;
	          startno -= cntItemPage;
	        } else {
	          startno -= cntItemPage * 2;
	          if (startno < 0) startno = 0;
	        }
	      } else {
	        startno -= cntItemPage * 2;
	        if (startno < 0) startno = 0;
	      }
	    else if (action.equals("first"))
	      startno = 0;
	    	
	    else if (action.equals("last"))
	      x = cntItemPage;
	    else if (action.equals("back"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x == 0) x = cntItemPage;
	        startno -= x;
	      } else {
	        startno -= cntItemPage;
	        if (startno < 0) startno = 0;

	      }
	    
	    Vector moduleVector = new Vector();
	    int i = 0; int cnt = 0;
	    if (objVector.size() > 0) {
	      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
	        moduleVector.addElement(objVector.elementAt(i));

	        ++i; ++cnt;
	      }

	    }

	    session.setAttribute("_portal_moduleVector", moduleVector);
	   
	    this.context.put("startnoInt", new Integer(startno));
	    this.context.put("totalInt", new Integer(objVector.size()));
	    
	   
	    startno = i;
	   
	    session.setAttribute("_portal_startno", new Integer(startno));
	    
	  }
}

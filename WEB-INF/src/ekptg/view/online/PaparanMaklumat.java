package ekptg.view.online;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.online.PaparanMaklumatData;

public class PaparanMaklumat extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception {
		
		String vm = "app/online/frmCarianPaparanMaklumat.jsp";
		String semakan = getParam("socSeksyen");
		this.context.put("semakan",semakan);
		String action = getParam("action");
		this.context.put("action",action);
		Vector list = new Vector();
		String negeri = getParam("socNegeri");
		String daerah = getParam("socDaerah");
		String mukim = getParam("socMukim");
		String noHakmilik = "";
		String noWarta = "";
		String noSiriPerjanjian = "";
		String noSiriLesen = "";
		HttpSession session = this.request.getSession();
		this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null));
		this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",null,null));
		this.context.put("selectMukim",HTML.SelectMukim("socMukim",null,null));
		
		if ("".equals(semakan)){
			
			this.context.put("hide","0");
			this.context.put("selected0","selected");
			this.context.put("selected1","");
			this.context.put("selected2","");
			
		}
		else if ("0".equals(semakan)){
			this.context.put("hide","1");
			this.context.put("selected0","selected");
			this.context.put("selected1","");
			this.context.put("selected2","");
			
		}
		else if ("1".equals(semakan)){
			this.context.put("hide","1");
			this.context.put("selected0","");
			this.context.put("selected1","selected");
			this.context.put("selected2","");
			this.context.put("txtNoFailPenyewaan", noSiriPerjanjian);
		    this.context.put("txtNoLesen", noSiriLesen);
		    
			if (("next".equals(action)) || ("previous".equals(action))){
				vm = "app/online/frmCarianPaparanMaklumat.jsp";
				if (!"".equals(getParam("txtNoSiriPerjanjian"))){
					noSiriPerjanjian = getParam("txtNoSiriPerjanjian");}
				if (!"".equals(getParam("txtNoLesen"))){
					noSiriLesen = getParam("txtNoLesen");}
				
				
				PaparanMaklumatData.setCarianPaparanMaklumatPHP(noSiriPerjanjian, noSiriLesen);
				list = PaparanMaklumatData.getListPHP();
				prepareItemForDisplay(session,list,10,action);
				this.context.put("txtNoSiriPerjanjian", noSiriPerjanjian);
			    this.context.put("txtNoLesen", noSiriLesen);
  		    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),null));
  				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(daerah),null));
  				this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(mukim),null));
				
				
			}
			else if ("paparPHP".equals(action)){
				vm = "app/online/frmPaparanMaklumat.jsp";
		    	
		    	this.context.put("semakan",getParam("semakan"));
		    	this.context.put("noSiriPerjanjian", getParam("noSiriPerjanjian"));
		    	this.context.put("noSiriLesen",getParam("noSiriLesen"));
		    	
		    	if (getParam("noSiriPerjanjian") != ""){
		    		PaparanMaklumatData.paparDataPenyewaan(Integer.parseInt(getParam("idPermohonan").toString()));
		    		list = PaparanMaklumatData.getDataPenyewaan();
		    		Hashtable hPaparSewa = (Hashtable)list.get(0);
		    		this.context.put("noSiriPerjanjian",hPaparSewa.get("no_Siri_Perjanjian"));
		    		this.context.put("noLot",hPaparSewa.get("no_Lot"));
		    		this.context.put("kadarSewa",hPaparSewa.get("kadar_Sewa"));
		    		this.context.put("statusPerjanjian",hPaparSewa.get("status_Perjanjian"));
		    	}
		    	else if (getParam("noSiriLesen")!= ""){
		    		PaparanMaklumatData.paparDataLesen(Integer.parseInt(getParam("idPermohonan").toString()));
		    		list = PaparanMaklumatData.getDataLesen();
		    		Hashtable hPaparLesen = (Hashtable)list.get(0);
		    		this.context.put("noSiriLesen",hPaparLesen.get("no_Siri_Lesen"));
		    		this.context.put("lokasi",hPaparLesen.get("lokasi"));
		    		this.context.put("tempoh",hPaparLesen.get("tempoh"));
		    		this.context.put("statusLesen",hPaparLesen.get("status_Lesen"));
		    	}
		    	
			}
			 else if ("kembali".equals(action)){
			    	vm = "app/online/frmCarianPaparanMaklumat.jsp";
			    	PaparanMaklumatData.setCarianPaparanMaklumatPHP(noSiriPerjanjian, noSiriLesen);
					list = PaparanMaklumatData.getListPHP();
					prepareItemForDisplay(session,list,10,"first");
					this.context.put("txtNoSiriPerjanjian", noSiriPerjanjian);
				    this.context.put("txtNoLesen", noSiriLesen);
	  		    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),null));
	  				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(daerah),null));
	  				this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(mukim),null));
				
			    }
			else{
				vm = "app/online/frmCarianPaparanMaklumat.jsp";
				if (!"".equals(getParam("txtNoSiriPerjanjian"))){
					noSiriPerjanjian = getParam("txtNoSiriPerjanjian");}
				if (!"".equals(getParam("txtNoLesen"))){
					noSiriLesen = getParam("txtNoLesen");}
				
				
				PaparanMaklumatData.setCarianPaparanMaklumatPHP(noSiriPerjanjian, noSiriLesen);
				list = PaparanMaklumatData.getListPHP();
				prepareItemForDisplay(session,list,10,"first");
				this.context.put("txtNoSiriPerjanjian", noSiriPerjanjian);
			    this.context.put("txtNoLesen", noSiriLesen);
  		    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),null));
  				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(daerah),null));
  				this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(mukim),null));
			}

		}
		else if ("2".equals(semakan)){
			this.context.put("hide","2");
			this.context.put("selected0","");
			this.context.put("selected1","");
			this.context.put("selected2","selected");
			this.context.put("txtNoHakmilik", noHakmilik);
		    this.context.put("txtNoWarta", noWarta);
		    
		    if (("next".equals(action)) || ("previous".equals(action))){
		    	vm = "app/online/frmCarianPaparanMaklumat.jsp";
				if (!"".equals(getParam("txtNoHakmilik"))){
					noHakmilik = getParam("txtNoHakmilik");}
				if (!"".equals(getParam("txtNoWartaRizab"))){
					noWarta = getParam("txtNoWartaRizab");}
				
				
				PaparanMaklumatData.setCarianPaparanMaklumatHTP(noHakmilik, noWarta);
				list = PaparanMaklumatData.getListHTP();
				prepareItemForDisplay(session,list,10,action);
				this.context.put("txtNoHakmilik", noHakmilik);
			    this.context.put("txtNoWarta", noWarta);
  		    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),null));
  				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(daerah),null));
  				this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(mukim),null));
				
				
			}
		    else if ("paparHTP".equals(action)){
		    	vm = "app/online/frmPaparanMaklumat.jsp";
		    	
		    	this.context.put("semakan",getParam("semakan"));
		    	this.context.put("noHakmilik", getParam("noHakmilik"));
		    	this.context.put("noWarta",getParam("noWarta"));
		    	
		    	if (getParam("noHakmilik")!= ""){
		    		PaparanMaklumatData.paparDataHakmilik(Integer.parseInt(getParam("idHakmilik").toString()));
		    		list = PaparanMaklumatData.getDataHakmilik();
		    		Hashtable hPaparHakmilik = (Hashtable)list.get(0);
		    		this.context.put("noHakmilik",hPaparHakmilik.get("no_Hakmilik"));
		    		this.context.put("jenisHakmilik",hPaparHakmilik.get("keterangan"));
		    		this.context.put("kegunaanTanahHakmilik",hPaparHakmilik.get("syarat"));
		    		this.context.put("hakmilikAsal",hPaparHakmilik.get("hakmilik_Asal"));
		    	}
		    	else if (getParam("noWarta")!= ""){
		    		PaparanMaklumatData.paparDataWarta(Integer.parseInt(getParam("idHakmilik").toString()));
		    		list = PaparanMaklumatData.getDataWarta();
		    		Hashtable hPaparWarta = (Hashtable)list.get(0);
		    		this.context.put("noWarta",hPaparWarta.get("no_Warta"));
		    		this.context.put("lokasi",hPaparWarta.get("lokasi"));
		    		this.context.put("kegunaanTanahRizab",hPaparWarta.get("syarat"));
		    		this.context.put("statusSah",hPaparWarta.get("status_Sah"));
		    		
		    		
		    	}
		    	
		    	
		    }
		    else if ("kembali".equals(action)){
		    	vm = "app/online/frmCarianPaparanMaklumat.jsp";
		    	PaparanMaklumatData.setCarianPaparanMaklumatHTP(noHakmilik, noWarta);
				list = PaparanMaklumatData.getListHTP();
				prepareItemForDisplay(session,list,10,"first");
				this.context.put("txtNoHakmilik", noHakmilik);
			    this.context.put("txtNoWarta", noWarta);
  		    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),null));
  				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(daerah),null));
  				this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(mukim),null));
			
		    }
			else{
				vm = "app/online/frmCarianPaparanMaklumat.jsp";
				if (!"".equals(getParam("txtNoHakmilik"))){
					noHakmilik = getParam("txtNoHakmilik");}
				if (!"".equals(getParam("txtNoWartaRizab"))){
					noWarta = getParam("txtNoWartaRizab");}
				
				
				PaparanMaklumatData.setCarianPaparanMaklumatHTP(noHakmilik, noWarta);
				list = PaparanMaklumatData.getListHTP();
				prepareItemForDisplay(session,list,10,"first");
				this.context.put("txtNoHakmilik", noHakmilik);
			    this.context.put("txtNoWarta", noWarta);
  		    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeri),null));
  				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(daerah),null));
  				this.context.put("selectMukim",HTML.SelectMukim("socMukim",Utils.parseLong(mukim),null));
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

package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmPrmhnnSek8BicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;

public class FrmSek8PrmhnnRulerOfTheState extends VTemplate {

	FrmPrmhnnSek8BicaraData logic3 = new FrmPrmhnnSek8BicaraData();
	Vector listSimati = new Vector();
	
    public Template doTemplate() throws Exception
    {
       	HttpSession session = this.request.getSession();
       	
    	String vm = "";
    	String geturusan = "0";
    	String getlaporantanah = "0";
    	String tarikhmohon = "";
    	String nofail = ""; 
    	String readability1 = "";
    	String readability2 = "";
    	String disability1 = "";
    	String disability2 = "";
    	
    	Vector list = new Vector();
    	
    	list.clear();
    	  	
    	String submit = getParam("command"); 
    	System.out.println("submit ===> "+submit);

		if("getSenaraiMahkamah".equals(submit)){
			
		    String id = getParam("id");
		    String id_perbicaraan = getParam("id_perbicaraan");
		    System.out.println("IDDD :::"+id);
		    System.out.println("id_perbicaraan == "+id_perbicaraan);
  	    	
		    FrmPrmhnnSek8KptsanBicaraData.setData(id);
  	    	list = FrmPrmhnnSek8KptsanBicaraData.getData();
  	    	this.context.put("View", list);  	    
		    logic3.setDataSimati(id);
		    listSimati = logic3.getDataSimati();
		    this.context.put("listSimati", listSimati);		       
		    
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
		    //this.context.put("readmode", disability1);
		    
			Vector listnegeri = FrmPrmhnnSek8KptsanBicaraData.getListnegeri();				
			this.context.put("listnegeri", listnegeri);
			
			Vector getrecord_infoperbicaraan = FrmPrmhnnSek8KptsanBicaraData.setInfoBicara(id,id_perbicaraan);
			Hashtable h = (Hashtable) getrecord_infoperbicaraan.get(0);
			this.context.put("dataPerbicaraan", getrecord_infoperbicaraan);
		   
			//context.put("selectPegawai",HTML.SelectPegawaiPengendali("socPegawaiPengendali",null,"disabled class=mediumselect"));
			
			long idUnitPsk = Long.parseLong(h.get("id_unitpsk").toString());
    		String namaPegawai = h.get("nama_pegawai").toString();
    		
			context.put("selectPegawai",HTML.SelectPegawaiPengendali("socPegawaiPengendali",idUnitPsk,"class=mediumselect"));		
			System.out.println("id idUnitPsk (KEMASKINI) = " +getParam("socPegawaiPengendali"));
    		
    		context.put("idDaerah", idUnitPsk);
    		context.put("namaDaerah", namaPegawai);  

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";			
			
		}else if("popupMT".equals(submit)){	
					
			String id = getParam("id");
		    String id_perbicaraan = getParam("id_perbicaraan");
//		    System.out.println("IDDD :::"+id);
//		    System.out.println("id_perbicaraan == "+id_perbicaraan);
  	    	
		    FrmPrmhnnSek8KptsanBicaraData.setData(id);
  	    	list = FrmPrmhnnSek8KptsanBicaraData.getData();
  	    	this.context.put("View", list);  	    
		    logic3.setDataSimati(id);
		    listSimati = logic3.getDataSimati();
		    this.context.put("listSimati", listSimati);		       
		    
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
		    //this.context.put("readmode", disability1);
		    
			Vector listnegeri = FrmPrmhnnSek8KptsanBicaraData.getListnegeri();				
			this.context.put("listnegeri", listnegeri);
			
			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";	
						
		}else{

			vm = "app/ppk/frmPrmhnnRulerOfTheState.jsp";		

		}    	     	
        Template template = this.engine.getTemplate(vm);
        return template;
    }
}
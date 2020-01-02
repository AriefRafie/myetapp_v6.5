package ekptg.view.online;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.online.FrmKemaskiniPengesahanPembayaranData;
import ekptg.model.online.FrmSejarahPembayaranData;

public class PembayaranSecaraOnline extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception
    {
		String vm = "";
		String submit = getParam("command");
		String action = getParam("action");
		this.context.put("action",action);
		String radioCheck1 = getParam("radioCheck1");
		String radioCheck2 = getParam("radioCheck2");
 		this.context.put("radioCheck1",radioCheck1);
		this.context.put("radioCheck2",radioCheck2);
		HttpSession session = this.request.getSession();
        Vector list = new Vector();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dariBulan = "";
        String keBulan = "";
        String user_id = (String)session.getAttribute("_ekptg_user_id");
        
        if ("seterusnya1".equals(action)){
        	vm = "app/online/frmKemaskiniKaedahPembayaran.jsp";
        	this.context.put("noAkaunBil", getParam("txtNoAkaunBil"));
        	this.context.put("jenisBayaran",getParam("socJenisBayaranA"));
        	this.context.put("amaunBayaran",getParam("txtAmaunBayaran"));
        	
        	if ("".equals(radioCheck2)){
         		this.context.put("radioChecked3","checked");
         		this.context.put("radioChecked4","");
         		this.context.put("kaedahPembayaran", "1");
	
         	}
        	else if ("debit".equals(radioCheck2)){
         		this.context.put("radioChecked3","checked");
         		this.context.put("radioChecked4","");
         		this.context.put("kaedahPembayaran", "1");
         		this.context.put("modePembayaran", getParam("modePembayaran"));
            	this.context.put("noAkaunBil", getParam("noAkaunBil"));
            	this.context.put("jenisBayaran",getParam("id_Jenisbayaran"));
            	this.context.put("amaunBayaran",getParam("amaunBayaran"));
	
         	}
         	else if ("credit".equals(radioCheck2)){
         		this.context.put("radioChecked3","");
         		this.context.put("radioChecked4","checked");
         		this.context.put("kaedahPembayaran", "2");
         		this.context.put("modePembayaran", getParam("modePembayaran"));
            	this.context.put("noAkaunBil", getParam("noAkaunBil"));
            	this.context.put("jenisBayaran",getParam("id_Jenisbayaran"));
            	this.context.put("amaunBayaran",getParam("amaunBayaran"));
          	
         	}
         	this.context.put("tarikhEfektif", sdf.format(now));
         	this.context.put("selectBank",HTML.SelectBank("socBank",null,null));
         	
        }
        else if ("seterusnya2".equals(action)){
        	
        	this.context.put("modePembayaran", getParam("modePembayaran"));
        	this.context.put("noAkaunBil", getParam("noAkaunBil"));
        	this.context.put("jenisBayaran",getParam("id_Jenisbayaran"));
        	this.context.put("amaunBayaran",getParam("amaunBayaran"));
        	String jenisBayaran = getParam("id_Jenisbayaran");
        	
        	vm = "app/online/frmKemaskiniPengesahanPembayaran.jsp";
        	this.context.put("bank",getParam("socBank"));
        	this.context.put("noAkaunPembayar",getParam("txtNoAkaunPembayar"));
        	this.context.put("namaPemegangAkaun",getParam("txtNamaPemegangAkaun"));
        	this.context.put("noAkaunPenerima",getParam("txtNoAkaunPenerima"));
        	this.context.put("emel",getParam("txtEmel"));
        	
        	
         	this.context.put("selectJenisBayaranB",HTML.SelectJenisBayaran("socJenisBayaranB",Utils.parseLong(jenisBayaran),"disabled"));

        }
        else if ("setuju".equals(action)){
        	vm = "app/online/frmKemaskiniPengesahanPembayaran.jsp";

        	simpanPembayaran(session);
        	vm = "app/online/frmSejarahPembayaran.jsp";
        	if (!"".equals(getParam("txdDariTarikh")))
	  	    	dariBulan = getParam("txdDariTarikh");
			if (!"".equals(getParam("txdKeTarikh")))
				keBulan = getParam("txdKeTarikh");
        	FrmSejarahPembayaranData.setCarianBayaran(dariBulan, keBulan, user_id);
        	list = FrmSejarahPembayaranData.getList();
        	prepareItemForDisplay(session,list,10,"first");
        	this.context.put("txdDariBulan", dariBulan);
		    this.context.put("txdKeBulan", keBulan);
		    
	
        }
        else if ("cari".equals(action)){
        	vm = "app/online/frmSejarahPembayaran.jsp";
        	if (!"".equals(getParam("txdDariTarikh")))
	  	    	dariBulan = getParam("txdDariTarikh");
			if (!"".equals(getParam("txdKeTarikh")))
				keBulan = getParam("txdKeTarikh");
        	FrmSejarahPembayaranData.setCarianBayaran(dariBulan, keBulan,user_id);
        	list = FrmSejarahPembayaranData.getList();
        	prepareItemForDisplay(session,list,10,"first");
        	this.context.put("txdDariBulan", dariBulan);
		    this.context.put("txdKeBulan", keBulan);
        }
        else{
        	vm = "app/online/frmKemaskiniPembayaran.jsp";
         	this.context.put("selectJenisBayaranA",HTML.SelectJenisBayaran("socJenisBayaranA",null,null));
         	
         	if ("".equals(radioCheck1)){
         		this.context.put("radioChecked1","checked");
         		this.context.put("radioChecked2","");
            	this.context.put("modePembayaran", "1");

         		
         	}
         	else if ("fpx".equals(radioCheck1)){
         		this.context.put("radioChecked1","checked");
         		this.context.put("radioChecked2","");
         		this.context.put("modePembayaran", "1");
         		
         	}
         	else if ("internetBank".equals(radioCheck1)){
         		this.context.put("radioChecked1","");
         		this.context.put("radioChecked2","checked");
         		this.context.put("modePembayaran", "2");
         		
         	}

        }
        return vm;
        
    }

	public void simpanPembayaran(HttpSession session)throws Exception{
    	String user_id = (String)session.getAttribute("_ekptg_user_id");

		Hashtable h = new Hashtable();
		h.put("mod_Pembayaran", Integer.parseInt(getParam("modePembayaran")));
		h.put("no_Akaun_Bil",getParam("noAkaunBil"));
		h.put("id_Jenisbayaran",Integer.parseInt(getParam("id_Jenisbayaran").toString()));
		h.put("amaun_Bayaran",getParam("amaunBayaran"));
		h.put("kaedahPembayaran", getParam("kaedahPembayaran"));
		h.put("bank",getParam("bank"));
		h.put("noAkaunPembayar",getParam("noAkaunPembayar"));
		h.put("namaPemegangAkaun",getParam("namaPemegangAkaun"));
		h.put("noAkaunPenerima",getParam("noAkaunPenerima"));
		h.put("emel",getParam("emel"));
		h.put("id_Masuk",user_id);
		
		System.out.println("ModeBayaran :" +getParam("modePembayaran"));
		FrmKemaskiniPengesahanPembayaranData.addPembayaran(h);
		
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

package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSemakan;

public class FrmPajakanKecilSenaraiPermohonan extends VTemplate{

	 public Template doTemplate()throws Exception {
	    HttpSession session = this.request.getSession();

	    String template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";
	    String submit = getParam("command");
	    String fail = getParam("fail");
	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:submit::"+submit);
    
		this.context.put("semak", new ekptg.model.htp.FrmSemakan());
		this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh"));

	    if ("add".equals(submit)) {
	      addCaraBayar(session);
	    }else if ("kemaskinip".equals(submit)) {
	    	template_name = "app/htp/frmSewaanSemakPermohonan.jsp";	
			String id = getParam("id_kemaskini");
			Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
		    this.context.put("permohonanInfo", permohonan);
		    this.context.put("util", new lebah.util.Util());
		    this.context.put("semakclass", new ekptg.model.htp.FrmSemakan());
		    // Senarai semakan
		    this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh"));
		    System.out.println("FrmPajakanKecilSenaraiPermohonan:senaraiSemakan::"+FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh").size());

		    //String[] cbsemaks = this.request.getParameterValues("cbsemaks");
			Hashtable map = new Hashtable();
			Vector semakanSenarai = new Vector();
			semakanSenarai = FrmSemakan.getSenaraiSemakan("frmSewaanSemakPermoh");
			//if (cbsemaks != null)
		    System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
			/*for (int i = 0; i < semakanSenarai.size(); ++i) { 
				Hashtable obj = new Hashtable();
	            obj = (Hashtable)semakanSenarai.elementAt(i);
			    System.out.println("FrmPajakanKecilSenaraiPermohonan:cbsemaks[i]::"+obj.get("id"));
				//semakanHantar = FrmSemakan.getSenaraiSemakanHantar(id);
				    System.out.println("FrmPajakanKecilSenaraiPermohonan:FrmSemakan.isSemakan(id, cbsemaks[i])::"+FrmSemakan.isSemakan(id,""+obj.get("id")));
				 			
					if(FrmSemakan.isSemakan(id, ""+obj.get("id")))
						map.put("status", "true");
					else
						map.put("status", "false");	
			}*/


	    }else if("semakankemaskini".equals(submit))    {
	    	template_name = "app/htp/frmSewaanSemakPermohonan.jsp";	
			String id = getParam("idkemaskini");
			String[] cbsemaks = this.request.getParameterValues("cbsemaks");
    	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
        	FrmSemakan frmSemak = new FrmSemakan();
        	frmSemak.semakanHapusByPermohonan(id);
	        for (int i = 0; i < cbsemaks.length; i++) { 
	        	frmSemak = new FrmSemakan();
	    	    System.out.println("FrmPajakanKecilSenaraiPermohonan:semakankemaskini::cbsemaks:::"+cbsemaks[i]);
	    	    frmSemak.semakanTambah(cbsemaks[i], id);           
	        }
  	    	

	    } else if ("delete".equals(submit)) {
	      deleteCaraBayar(session);
	    }else if("semakanseterus".equals(submit)) {
	    	template_name = "app/htp/frmSewaanPemilik.jsp";	
	    }else if("pemilikseterus".equals(submit)) {
	    	template_name = "app/htp/frmSewaanMaklumat.jsp";	
	    }else if("maklumatseterus".equals(submit)) {
	    	template_name = "app/htp/frmSewaanSemakPKP.jsp";	
	    }else if("semakanpkpseterus".equals(submit)) {
	    	template_name = "app/htp/frmSewaanDeraf.jsp";	
	    }else if("pkfail".equals(submit)){
	    	template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";	
	    }else if("pkpermohonan".equals(submit)){
	    	Vector senaraiList = FrmPajakanKecilSenaraiPermohonanData.getList(fail);
	    	this.context.put("senaraiList", senaraiList);	    	
	    	template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";	
	    }
 
	    Template template = this.engine.getTemplate(template_name);
	    return template;
	  }

	 
	  private void addCaraBayar(HttpSession session) throws Exception {
	    String kod_cara_bayar = getParam("kod_cara_bayar");
	    String keterangan = getParam("keterangan");
	    String id_masuk = getParam("id_masuk");
	    //String tarikh_masuk = getParam("tarikh_masuk");
	    Date tarikh_masukd = new Date();
	    /*if(getParam("tarikh_masuk")!=null){
	    	tarikh_masukd = new Date(getParam("tarikh_masuk"));
	    }*/
	    //CaraBayarData.add(kod_cara_bayar, keterangan, Long.parseLong(id_masuk),tarikh_masukd);
	  }

	  private void updatePermohonan(HttpSession session)throws Exception{
		  String id = getParam("id_kemaskini");
		    //String kod_cara_bayar = getParam("kod_cara_bayar");
		    //String keterangan = getParam("keterangan");
		    //String id_kemaskini = getParam("id_kemaskini");
		    //Date tarikh_kemaskini = getParam("tarikh_kemaskini");
		    //Date tarikh_kemaskini = new Date();
		    //CaraBayarData.update(Long.parseLong(id_carabayar), kod_cara_bayar,
		    	//	keterangan,Long.parseLong(id_kemaskini),tarikh_kemaskini);
	  }

	  private void deleteCaraBayar(HttpSession session) throws Exception {
	    String carabayar_id = getParam("id_carabayar");
	    //CaraBayarData.delete(carabayar_id);
	  }
	  
	  /*private Tblpermohonan PermohonanInfo(String idpermohonan) throws Exception{
  
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_permohonan");
	      r.add("id_fail");
	      r.add("id_jkptg");
	      r.add("no_permohonan");
	      r.add("no_perserahan");
	      r.add("tarikh_surat");
	      r.add("tarikh_terima");
	      r.add("tujuan");
	      r.add("id_masuk");
	      r.add("tarikh_masuk");
	      r.add("id_kemaskini");
	      r.add("tarikh_kemaskini");
	      r.add("id_permohonan",idpermohonan);
	      sql = r.getSQLSelect("tblpermohonan");
	      ResultSet rs = stmt.executeQuery(sql);
	      Tblpermohonan p = new Tblpermohonan();
	      while (rs.next()) {
	    	  //Tblrujnegeri f = new Tblrujnegeri();
	    	  p.setIdPermohonan(rs.getLong(1));
	    	  p.setIdFail(rs.getLong(2));
	    	  p.setIdJkptg(rs.getLong(3));
	    	  p.setNoPermohonan(rs.getString(4));
	    	  p.setNoPerserahan(rs.getString(5));
	    	  p.setTarikhSurat(rs.getDate(6));
	     	  p.setTarikhTerima(rs.getDate(7));
	    	  p.setTujuan(rs.getString(8));
	    	  p.setIdMasuk(rs.getLong(9));
	    	  p.setTarikhMasuk(rs.getDate(10));
	    	  p.setIdKemaskini(rs.getLong(11));
	    	  p.setTarikhKemaskini(rs.getDate(12));
	    	  //list.addElement(f);
	      }
	      return p;
	    } finally {
	      if (db != null) db.close();
	    }
	  }*/
}

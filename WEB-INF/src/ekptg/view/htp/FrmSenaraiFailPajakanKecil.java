package ekptg.view.htp;

import java.util.Date;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class FrmSenaraiFailPajakanKecil extends VTemplate{

	 public Template doTemplate()
	    throws Exception
	  {
	    HttpSession session = this.request.getSession();

	    String template_name = "app/htp/frmSenaraiFailPajakanKecil.jsp";
	    String submit = getParam("command");
	    if ("add".equals(submit)) {
	      addCaraBayar(session);
	    }
	    
	    else if ("update".equals(submit)) {
	      updateCaraBayar(session);
	    }
	    else if ("delete".equals(submit)) {
	      deleteCaraBayar(session);
	    }

	    //Vector senaraiList = FrmSenaraiFailPajakanKecilData.getList();
	    //this.context.put("senaraiList", senaraiList);
	    //Vector insList = InstitutionData.getList();
	    //this.context.put("institutionList", insList);

	    //session.setAttribute("token", Token.get());
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

	  private void updateCaraBayar(HttpSession session)throws Exception{
		    String id_carabayar = getParam("id_carabayar");
		    String kod_cara_bayar = getParam("kod_cara_bayar");
		    String keterangan = getParam("keterangan");
		    String id_kemaskini = getParam("id_kemaskini");
		    //Date tarikh_kemaskini = getParam("tarikh_kemaskini");
		    Date tarikh_kemaskini = new Date();
		    //CaraBayarData.update(Long.parseLong(id_carabayar), kod_cara_bayar,
		    	//	keterangan,Long.parseLong(id_kemaskini),tarikh_kemaskini);
	  }

	  private void deleteCaraBayar(HttpSession session) throws Exception {
	    String carabayar_id = getParam("id_carabayar");
	    //CaraBayarData.delete(carabayar_id);
	  }
}

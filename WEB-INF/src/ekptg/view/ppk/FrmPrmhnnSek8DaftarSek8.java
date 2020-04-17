package ekptg.view.ppk;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8Data;

public class FrmPrmhnnSek8DaftarSek8 extends VTemplate {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public Template doTemplate() throws Exception
	{
		HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmPrmhnnSek8DaftarSek8.jsp";
		String submit = getParam("command");
		
		if("Simpan".equals(submit)){
			vm = "app/ppk/frmPrmhnnSek8DaftarSek8.jsp";
		}
		
		Template template = this.engine.getTemplate(vm);
		return template;
	}
		
	private void add(HttpSession session) throws Exception {
		String no_Fail = getParam("no_Fail");
	    String tajuk_Fail = getParam("tajuk_Fail");
	    String no_kp_baru = getParam("txtNoKPBaruSimati1");
	    ///String id_masuk = getParam("id_masuk");
	    //String tarikh_masuk = getParam("tarikh_masuk");
	    //Date tarikh_masukd = new Date();
	    /*if(getParam("tarikh_masuk")!=null){
	    	tarikh_masukd = new Date(getParam("tarikh_masuk"));
	    }*/
	    //FrmPrmhnnSek8DaftarSek8Data.add(kod_cara_bayar, keterangan, Long.parseLong(id_masuk),tarikh_masukd);
	    Hashtable h = null;
	    h = new Hashtable();
	    h.put("no_Fail", no_Fail);
	    h.put("tajuk_Fail", tajuk_Fail);
	    h.put("no_kp_baru", no_kp_baru);
		FrmPrmhnnSek8DaftarSek8Data.add(h);
	
	}
	
}

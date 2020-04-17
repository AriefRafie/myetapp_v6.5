package ekptg.view.htp.rekod;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.rekod.FrmTanahKementerianBean;
import ekptg.model.htp.rekod.ITanahKementerian;

public class FrmRekodTransaksiHakmilikAgensi extends AjaxBasedModule {

	private ITanahKementerian iHakmilikKementerian = null; 
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.rekod.FrmRekodTransaksiHakmilikAgensi.class);

	@Override
	public String doTemplate2() throws Exception {		
		String vm = "";
		HttpSession session = this.request.getSession();	
		vm = "app/htp/rekod/frmRekodTransaksiHakmilikAgensi.jsp";
		// VIEW TRANSAKSI CUKAI
		viewTransaksi(getParam("idHakmilik"));
		return vm;
	   
	}

	// VIEW TRANSAKSI CUKAI BY ID
	private void viewTransaksi(String idHakmilik) throws Exception {
		Vector list =null;
		list = getHakmilik().getMaklumat(idHakmilik);
		this.context.put("SenaraiTransaksi",list);
	}
	
	private ITanahKementerian getHakmilik(){
		if(iHakmilikKementerian== null)
			iHakmilikKementerian = new FrmTanahKementerianBean();
		return iHakmilikKementerian;
	}
	
}
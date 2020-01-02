package ekptg.fpx;

import java.text.DecimalFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.fpx.entity.DataFpx;
import ekptg.helpers.HTML;

public class FpxAdminModule extends AjaxBasedModule{
	private final String PATH="app/fpx/admin/";
	private String vm = PATH +"index.jsp";
	private IFpx fpxBean = new FpxManagerBean();
	private IFpxAnalatic analatic = new FpxAnalatic();
	@Override
	public String doTemplate2() throws Exception {
		String action = getParam("action");
		HttpSession session = request.getSession();
		String command = getParam("command");
		DecimalFormat nf = new DecimalFormat("0.00");
		if(command.equals("view")){
			String idFpx = getParam("noTransaksi");
			DataFpx fpx = fpxBean.get(idFpx);
			context.put("fpx",fpx );
			context.put("nilai", FpxUtility.generateWordEncodeURL(fpx.getAmount()));
			vm = PATH +"view.jsp";
		}else if(command.equals("search")){			
			Vector<DataFpx> v = fpxBean.search(getParam("nokp"), "", "", getParam("nama"),getParam("socJenisBayaran"));
			context.put("SenaraiFail", v);
			context.put("nama", getParam("nama"));
			context.put("nokp", getParam("nokp"));
			setupPage(session, action, v);
			context.put("selectJenisBayaran",HTML.SelectJenisBayaranFPX("socJenisBayaran",null,null,"style=width:325px"));
		}else{
			context.put("selectJenisBayaran",HTML.SelectJenisBayaranFPX("socJenisBayaran",null,null,"style=width:325px"));
			Vector<DataFpx> v = fpxBean.search("", "", "", "","");
			context.put("SenaraiFail", v);
			context.put("nama", "");
			context.put("nokp", "");
			setupPage(session, action, v);
		}
		context.put("nf", nf);
		context.put("analatic", analatic.getTodayStatistic());
		return vm;
	}
	
	

}

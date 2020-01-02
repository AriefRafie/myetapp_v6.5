package ekptg.fpx;

import java.util.Date;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;

@SuppressWarnings("serial")
public class FpxReportRingkasanTransaksi extends AjaxBasedModule{
	
	private final String PATH="app/fpx/";
	private String vm = PATH +"frmReportRingkasanTransaksi.jsp";

	@Override
	public String doTemplate2() throws Exception {
		String tahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
		context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px"));
		return vm;
	}
	
}

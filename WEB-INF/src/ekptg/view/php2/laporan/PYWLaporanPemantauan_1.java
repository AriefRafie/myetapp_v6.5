package ekptg.view.php2.laporan;

import ekptg.model.htp.UtilHTML;
import lebah.portal.AjaxBasedModule;

public class PYWLaporanPemantauan_1 extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String doTemplate2() throws Exception {
		
		String idStatus = getParam("socStatus");
    	if (idStatus == null || idStatus.trim().length() == 0){
    		idStatus = "99999";
    	}	
		
		String socStatus = UtilHTML.selectStatusLaporanPenyewaan("4", "socStatus" ,idStatus, "", "");
		
		this.context.put("flagStatus", getParam("flagStatus"));
		this.context.put("socNegeri", getParam("socNegeri"));
		this.context.put("socDaerah", getParam("socDaerah"));
		this.context.put("socKementerian", getParam("socKementerian"));
		this.context.put("socAgensi", getParam("socAgensi"));
		this.context.put("txtTahun", getParam("txtTahun"));
		
		return "app/php2/laporan/PYW_LaporanPemantauan.jsp";
	}
}

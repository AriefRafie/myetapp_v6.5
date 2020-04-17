package ekptg.view.htp.pembelian;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.Rundingan;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.IRundingan;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.pembelian.RundinganBean;

public class RundinganPembelianModule extends AjaxModule {
	private IPembelian iPembelian = null;
	private IRundingan iRundigan = null;
	private String selectedTab="0";
	private static final String PATH="app/htp/pembelian/rundingan/";
	private String vm = PATH+"index.jsp";
	private String userID = null;
	private Permohonan permohonan = null;
	private HtpPermohonan htpPermohonan = null;
	private Rundingan rundingan = null;
	@Override
	public String doAction() throws Exception {
		HttpSession ses = request.getSession();
		String command = getParam("command");
		String action = getParam("action");
		if(command.equals("searchFail")){
			String noFail = getParam("noFail");
			String carian = getParam("carian");
			String idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			
			Vector<?> v = getIPembelian().findFail(carian, noFail, idNegeri);
			context.put("lists", v);
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			setupPage(ses,action, v);
			vm = PATH+"index.jsp";
		}
		else if(command.equals("detail")){
			String rundinganMode = "edit";
			String mode =" readonly class=\"disabled\"";
			getPermohonanInfo();
		
			rundingan =  getIRundigan().findByPermohonan(getParam("idPermohonan"));
			if(rundingan == null){
				rundinganMode = "new";
				mode ="";
			}
			context.put("mode", mode);
			context.put("rundinganMode", rundinganMode);
			context.put("rundingan", rundingan);
			selectedTab="0";
			vm = PATH+"rundingan.jsp";
		}
		else if(command.equals("simpanRundingan")){
			getPermohonanInfo();
			getRundinganDetails();
			rundingan = getIRundigan().saveRundingan(rundingan);
			context.put("rundingan", rundingan);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("rundinganMode", "");
			selectedTab="0";
			vm = PATH+"rundingan.jsp";
		}
		else if(command.equals("kemaskiniRundingan")){
			String rundinganMode = "update";
			String mode ="";
			getPermohonanInfo();
		
			rundingan =  getIRundigan().findByPermohonan(getParam("idPermohonan"));
			context.put("mode", mode);
			context.put("rundinganMode", rundinganMode);
			context.put("rundingan", rundingan);
			selectedTab="0";
			vm = PATH+"rundingan.jsp";
		}
		else if(command.equals("updateRundingan")){
			getPermohonanInfo();
			getRundinganDetails();
			rundingan = getIRundigan().updateRundingan(rundingan);
			context.put("rundingan", rundingan);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("rundinganMode", "");
			selectedTab="0";
			vm = PATH+"rundingan.jsp";
		}
		else{
			String noFail = getParam("noFail");
			String carian = getParam("carian");
			String idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			
			Vector<?> vr = getIPembelian().findFail(carian, noFail, idNegeri);
			context.put("lists", vr);
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			setupPage(ses,action, vr);
			//context.put("socNegeri",HTML.SelectNegeri("socNegeri"));
			selectedTab="0";
			vm = PATH+"index.jsp";
		}
		context.put("page","4");
		context.put("selectedTab", selectedTab);
		return vm;
	}
	public void getRundinganDetails(){
		String idRundingan = getParam("IDMAKLUMATMSYRT");
		String unitBersamaan = getParam("unit_bersamaan");
		String hargaBersamaan = getParam("harga_bersamaan");
		String nilaiTanah = getParam("nilai_tnh");
		String nilaiBangunan = getParam("nilai_bgn");
		String hargaBeli = getParam("harga_beli");
		String hargaSetuju = getParam("harga_setuju");
		String tempohSerah = getParam("tempoh_serah");
		String keputusan = getParam("keputusan");
		String ulasan = getParam("ulasan");
		permohonan = new Permohonan();
		permohonan.setIdPermohonan(getParam("idPermohonan"));
		rundingan = new Rundingan();
		rundingan.setIdMaklumatMysrt(idRundingan);
		rundingan.setHargaBersamaan(hargaBersamaan);
		rundingan.setUnitBersamaan(unitBersamaan);
		rundingan.setNilaiTanah(nilaiTanah);
		rundingan.setNilaiBangunan(nilaiBangunan);
		rundingan.setHargaTawaran(hargaBeli);
		rundingan.setHargaSetuju(hargaSetuju);
		rundingan.setTempohSerahan(tempohSerah);
		rundingan.setKeputusan(keputusan);
		rundingan.setUlasan(ulasan);
		rundingan.setPermohonan(permohonan);
		
		context.put("rundingan", rundingan);
	}
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 
	private IRundingan getIRundigan(){
		if (iRundigan==null){
			iRundigan=new RundinganBean();
		}
		return iRundigan;
	} 
	
	public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("lists",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}


}

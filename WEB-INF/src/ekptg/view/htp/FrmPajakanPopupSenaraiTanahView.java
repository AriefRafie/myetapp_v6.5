
package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmPajakanPopupSenaraiTanahData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikPajakan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.pajakan.IPajakanUtama;
import ekptg.model.htp.pajakan.PajakanUtamaBean;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.rekod.FrmHakmilikUrusanLainBean;
import ekptg.model.htp.rekod.FrmHakmilikUrusanPajakanBean;
import ekptg.model.htp.rekod.FrmHakmilikUrusanPenswastaanBean;
import ekptg.model.htp.rekod.FrmHakmilikUrusanPenyewaanBean;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.rekod.ITanahCarian;
import ekptg.model.htp.rekod.ITanahUrusan;
/**
 *
 *
 */
public class FrmPajakanPopupSenaraiTanahView extends AjaxBasedModule {

	private final String PATH="app/htp/";
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPajakanPopupSenaraiTanahView.class);
	private ITanahUrusan iHakmilikStatusLain = null;;
	private ITanahUrusan iHakmilikStatusP = null;
	private ITanahUrusan iHakmilikStatusS = null;
	private ITanahUrusan iHakmilikStatus = null;

	FrmPajakanPopupSenaraiTanahData logic = new FrmPajakanPopupSenaraiTanahData();
	FrmHakmilikUrusanPenyewaanBean SewaBean = new FrmHakmilikUrusanPenyewaanBean();
	private HtpPermohonan htpPermohonan = null;
	private HakmilikInterface iHakmilik = null;
	private IPajakanUtama iPajakanUtama = null;
	private IPembelian iPembelian = null;
	private HakmilikUrusan hUrusan = null;
	String idHakmilikUrusan = "";
	String readability = "";
	String disability = "";
	HakMilik hakmilik = null;
	HakMilik hakmilikTemp= null;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionPopup = getParam("actionPopup");
	    String submit = getParam("command");
	    String hitButton = getParam("hitButton");
	    String idHakmilik = getParam("idHakmilik");
	    idHakmilikUrusan = getParam("idhakmilikurusan");
	    String idPermohonan = getParam("idPermohonan");
	    String idLuasAsal = "0";

	    //VECTOR
        Vector<Hashtable<String,String>> list = null;
        Vector<Hashtable<String,String>> beanMaklumatTanah = null;

	    //GET DROPDOWN PARAM
        String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		String jenisHakmilik = getParam("socJenisHakmilik");
		if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0){
			jenisHakmilik = "99999";
		}
		String jenisLot = getParam("socJenisLot");
		if (jenisLot == null || jenisLot.trim().length() == 0){
			jenisLot = "99999";
		}
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan, "");

		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			//idNegeri = "99999";
			idNegeri = String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdNegeri());
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0){
			idMukim = "99999";
		}

		if ("simpanHakmilik".equals(hitButton)){
			getHakmilikUrusanValues();
			logic.saveHakmilikUrusan(idHakmilik, idPermohonan, hUrusan, session);
			idHakmilikUrusan = logic.getIdHakmilikUrusan();
			
		}else{

		}
		myLog.info("actionPopup="+actionPopup);
		myLog.info("mode="+getParam("mode"));

	    if (actionPopup.equals("papar")){
	    	myLog.info("masuk papar");
			disability = "";
	   		readability = "";
	   		/**GO TO DETAIL MAKLUMAT TANAH */
        	vm = PATH+"pajakan/fail/tanah/frmPajakanPopupMaklumatTanah.jsp";
			String mode = getParam("mode");
        	beanMaklumatTanah = new Vector<Hashtable<String,String>>();
			logic.setMaklumatTanah(idHakmilik);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			Hashtable<String,String> mt = (Hashtable<String,String>)beanMaklumatTanah.get(0);
			myLog.info("mt.get(luas) >>> "+mt.get("luas"));
			String luas = mt.get("luas");
			String luasAsal = "";
			if(luas == null){
				luas ="";
			}else{
				luas = mt.get("luas").trim();
				luasAsal = mt.get("luasAsal");
			}
			this.context.put("txtLuasLama",luas);
			this.context.put("txtLuasLamaAsal",luasAsal);

			hakmilik = new HakMilik();
			hakmilikTemp = getIHakmilik().getHakmilik(idHakmilik);
			hakmilik.setIdNegeri(hakmilikTemp.getNegeri().getIdNegeri());
			hakmilik.setIdDaerah(hakmilikTemp.getDaerah().getIdDaerah());
			hakmilik.setIdMukim(hakmilikTemp.getMukim().getIdMukim());
			hakmilik.setNoHakmilik(hakmilikTemp.getNoHakmlik());
			hakmilik.setIdJenisHakmilik(hakmilikTemp.getRujJenisHakmilik().getIdJenishakmilik());
			hakmilik.setIdPermohonan(Long.parseLong(idPermohonan));
			idLuasAsal = String.valueOf(hakmilikTemp.getIdLuas());
			//myLog.info("idPermohonan="+idPermohonan);
			//this.context.put("txtLuasLama",hakmilik.getLuasString());

			if(getPU().isHakmilikPajakan(hakmilik)==true){
				disability = "disabled";
		   		readability = "readOnly";

		   	}

			if ("doChangeKodLuas".equals(mode)) {
//		    	myLog.info("doChangeKodLuas:");
				idLuasAsal = getParam("socLuas");
//				RESET LUAS
				this.context.put("txtLuas","");
				this.context.put("txtLuas1","");

			}

			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			this.context.put("disability",disability);
		   	this.context.put("disabled",disability);
		   	this.context.put("mode",mode);
		   	this.context.put("readability",readability);
		   	this.context.put("readonly",readability);
			this.context.put("socLuas",idLuasAsal);


			Vector<Hashtable<String, String>> senaraiUrusan = getHakmilikUrusan().getMaklumat(idHakmilik);
			myLog.info("senaraiUrusan size="+senaraiUrusan.size());
			context.put("senaraiUrusanLain", senaraiUrusan);

			Vector<Hashtable<String, String>> listPHPPenyewaan = SewaBean.getMaklumatPHPPenyewaan(idHakmilik);
			context.put("listPHPPenyewaan", listPHPPenyewaan);

			Vector<Hashtable<String, String>> vecPajakan = getHakmilikPajakan().getMaklumat(idHakmilik);
			myLog.info("vecPajakan="+vecPajakan.size());
			context.put("senaraiPajakan", vecPajakan);

			Vector<Hashtable<String, String>> vecSwasta = getHakmilikPenswastaan().getMaklumat(idHakmilik);
			myLog.info("vecSwasta="+vecSwasta.size());
			this.context.put("swasta", vecSwasta);

	    }else if (actionPopup.equals("kemaskini")){
	    	myLog.info("kemaskini:mode="+getParam("mode"));
			disability = "";
	   		readability = "";
//	    	GO TO DETAIL MAKLUMAT TANAH
        	vm = PATH+"pajakan/fail/tanah/frmPajakanPopupMaklumatTanahKemaskini.jsp";
			String mode = getParam("mode");
		   	this.context.put("mode",mode);

		   	beanMaklumatTanah = new Vector<Hashtable<String,String>>();
			HakmilikPajakan hp = logic.getMaklumatTanahPajakan(idHakmilikUrusan,getParam("lot"));
//			myLog.info("idLuasBersamaan="+hp.getIdLuasBersamaanPajakan());
//			this.context.put("socLuas_",String.valueOf(hp.getIdLuasBersamaanPajakan()));
//			this.context.put("socLuas",String.valueOf(hp.getIdLuasPajakan()));
			idHakmilik = String .valueOf(hp.getIdHakmilik());
			idLuasAsal = String.valueOf(hp.getIdLuasPajakan());
			
			String luasLama = hp.getLuasStringPajakan();
			String luasLamaAsal = String.valueOf(hp.getLuasAsal());
			
			if (mode.equals("doChangeKodLuas")) {
//				RESET LUAS
				idLuasAsal = getParam("socLuas");
				this.context.put("txtLuas","");
				this.context.put("txtLuas1","");

			}
			this.context.put("beanMaklumatTanah", hp);
			this.context.put("disability",disability);
		   	this.context.put("disabled",disability);
		   	this.context.put("readability",readability);
		   	this.context.put("readonly",readability);
			this.context.put("socLuas",idLuasAsal);

			if(mode.equals("senaraitanah")){
	        	vm = PATH+"pajakan/fail/tanah/frmPajakanPopupSenaraiTanah.jsp";
	        	//myLog.info("idPermohonan="+idPermohonan);
	        	if ("1".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "selected");
					this.context.put("selected2", "");
					this.context.put("idJenisTanah", idJenisTanah);

	        	} else if ("2".equals(idJenisTanah)) {
					this.context.put("selected", "");
					this.context.put("selected1", "");
					this.context.put("selected2", "selected");
					this.context.put("idJenisTanah", idJenisTanah);

	        	} else {
	        		this.context.put("selected", "selected");
					this.context.put("selected1", "");
					this.context.put("selected2", "");
					this.context.put("idJenisTanah", "0");

	        	}

	        	if ("doChangeNegeri".equals(submit)){
	        		idDaerah = "99999";
	        		idMukim = "99999";
	        	}
	        	if ("doChangeDaerah".equals(submit)){
	        		idMukim = "99999";
	        	}

	        	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), " style=\"width:200\""));
				this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), " style=\"width:200\""));
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\" style=\"width:200\""));
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), "", " onChange=\"doChangeDaerah();\" style=\"width:200\""));
				this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Long.parseLong(idMukim), " style=\"width:200\""));

	        	this.context.put("txtPeganganHakmilik", getParam("txtPeganganHakmilik"));
				this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
				this.context.put("txtNoLot", getParam("txtNoLot"));
				this.context.put("txtNoWarta", getParam("txtNoWarta"));
				this.context.put("tarikhWarta", getParam("tarikhWarta"));

				list = new Vector<Hashtable<String,String>>();
	        	logic.carianTanah(idJenisTanah, getParam("txtPeganganHakmilik"), jenisHakmilik, getParam("txtNoHakmilik"), jenisLot,
	        			getParam("txtNoLot"), getParam("txtNoWarta"), getParam("tarikhWarta"), idNegeri, idDaerah, idMukim);
	    		list = logic.getSenaraiTanah();
				this.context.put("SenaraiTanah", list);

	        	setupPage(session,action,list);

			}else if(mode.equals("baru")){
	        	vm = PATH+"pajakan/fail/tanah/frmPajakanPopupMaklumatTanah.jsp";
				mode = getParam("mode");
			   	this.context.put("mode",mode);
				this.context.put("socLuas",getParam("socLuas"));
	        	beanMaklumatTanah = new Vector<Hashtable<String,String>>();
				//myLog.info("idHakmilik="+idHakmilik);
				logic.setMaklumatTanah(idHakmilik);
				beanMaklumatTanah = logic.getBeanMaklumatTanah();

				if ("doChangeKodLuas".equals(mode)) {
					this.context.put("txtLuas","");
					this.context.put("txtLuas1","");
				}
				this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
				this.context.put("socLuas",getParam("socLuas"));

		    }
			this.context.put("txtLuasLama",luasLama);
			this.context.put("txtLuasLamaAsal",luasLamaAsal);

			Vector<Hashtable<String, String>> senaraiUrusan = getHakmilikUrusan().getMaklumat(idHakmilik);
			myLog.info("senaraiUrusan size="+senaraiUrusan.size());
			context.put("senaraiUrusanLain", senaraiUrusan);

			Vector<Hashtable<String, String>> listPHPPenyewaan = SewaBean.getMaklumatPHPPenyewaan(idHakmilik);
			context.put("listPHPPenyewaan", listPHPPenyewaan);

			Vector<Hashtable<String, String>> vecPajakan = getHakmilikPajakan().getMaklumat(idHakmilik);
			myLog.info("vecPajakan="+vecPajakan.size());
			context.put("senaraiPajakan", vecPajakan);

			Vector<Hashtable<String, String>> vecSwasta = getHakmilikPenswastaan().getMaklumat(idHakmilik);
			myLog.info("vecSwasta="+vecSwasta.size());
			this.context.put("swasta", vecSwasta);

	    } else {
        	//myLog.info("idPermohonan="+idPermohonan);
//	    	GO TO LIST TANAH
        	vm = PATH+"pajakan/fail/tanah/frmPajakanPopupSenaraiTanah.jsp";
        	if ("1".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
				this.context.put("idJenisTanah", idJenisTanah);

        	} else if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("idJenisTanah", idJenisTanah);

        	} else {
        		this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("idJenisTanah", "0");

        	}

        	if ("doChangeNegeri".equals(submit)){
        		idDaerah = "99999";
        		idMukim = "99999";
        	}
        	if ("doChangeDaerah".equals(submit)){
        		idMukim = "99999";
        	}

        	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), " style=\"width:200\""));
			this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), " style=\"width:200\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\" style=\"width:200\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), "", " onChange=\"doChangeDaerah();\" style=\"width:200\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Long.parseLong(idMukim), " style=\"width:200\""));

        	this.context.put("txtPeganganHakmilik", getParam("txtPeganganHakmilik"));
			this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("tarikhWarta", getParam("tarikhWarta"));

			list = new Vector<Hashtable<String,String>>();
        	logic.carianTanah(idJenisTanah, getParam("txtPeganganHakmilik"), jenisHakmilik, getParam("txtNoHakmilik"), jenisLot,
        			getParam("txtNoLot"), getParam("txtNoWarta"), getParam("tarikhWarta"), idNegeri, idDaerah, idMukim);
    		list = logic.getSenaraiTanah();
			this.context.put("SenaraiTanah", list);

        	setupPage(session,action,list);
	    }

		this.context.put("disability_","disabled");
		this.context.put("socLuas_", "2");
	    this.context.put("idPermohonan", idPermohonan);
	    this.context.put("Utils", new ekptg.helpers.Utils());

		return vm;
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
				this.context.put("SenaraiTanah",paging.getPage(page));
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

	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	}

	private void getHakmilikUrusanValues(){
		String idLuas = getParam("socLuas");
		String luas = getParam("txtLuasGabung");
		String luasAsal = getParam("txtluasgabungasal");
		//String idLuasBersamaan= getParam("socluasbersamaan");
		String luasBersamaan = getParam("txtLuas");
		idHakmilikUrusan = getParam("idhakmilikurusan");
		hUrusan = new HakmilikUrusan();
		hUrusan.setIdLuas(idLuas);
		hUrusan.setLuas(luas);
		hUrusan.setLuasAsal(luasAsal);
		hUrusan.setIdLuasBersamaan("2");
		hUrusan.setLuasBersamaan(luasBersamaan);
		hUrusan.setIdhakmilikurusan(idHakmilikUrusan);

	}

	private IPajakanUtama getPU(){
		if(iPajakanUtama == null)
			iPajakanUtama = new PajakanUtamaBean();
		return iPajakanUtama;
	}

	private HakmilikInterface getIHakmilik(){
		if (iHakmilik==null){
			iHakmilik=new HakmilikBean();
		}
		return iHakmilik;
	}

	private ITanahUrusan getHakmilikPajakan() {
		if (iHakmilikStatusP == null)
			iHakmilikStatusP = new FrmHakmilikUrusanPajakanBean();
		return iHakmilikStatusP;
	}

	private ITanahUrusan getHakmilikPenswastaan() {
		if (iHakmilikStatusS == null)
			iHakmilikStatusS = new FrmHakmilikUrusanPenswastaanBean();
		return iHakmilikStatusS;
	}

	private ITanahUrusan getHakmilikPenyewaan() {
		if (iHakmilikStatus == null)
			iHakmilikStatus = new FrmHakmilikUrusanPenyewaanBean();
		return iHakmilikStatus;
	}

	private ITanahUrusan getHakmilikUrusan() {
		if (iHakmilikStatusLain == null)
			iHakmilikStatusLain = new FrmHakmilikUrusanLainBean();
		return iHakmilikStatusLain;
	}

}

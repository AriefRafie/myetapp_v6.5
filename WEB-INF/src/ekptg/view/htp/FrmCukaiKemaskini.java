package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmCukaiKemaskiniDataV1;
import ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.cukai.FrmCukaiBayaranBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.cukai.entity.CukaiTemp;
import ekptg.model.htp.entity.HakMilik;

public class FrmCukaiKemaskini extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	private static final String PATH="app/htp/cukai/";
	private HakMilik hakmilik = null;
	private ICukai iCukai = null;
 	private IHtp iHTP = null;  
	private Long idHakmilikUrusan = 0L;
	private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	private String result = new String();
	private String tahunCukaiSemasa = lebah.util.Util.getDateTime(new Date(), "yyyy");
	private String tahunCukai = lebah.util.Util.getDateTime(new Date(), "yyyy");
	static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmCukaiKemaskini.class);
	String noHakmilikStr = null;
	String user_id = "";
	
	public String doTemplate2()throws Exception {		 
	    HttpSession session = this.request.getSession();
	    String template_name = "";
      	String disability = "disabled";
      	String readability = "";
    	String style1 = "";
		String style2 = "";
	    this.context.put("util", new lebah.util.Util());
	    String submit = getParam("command");
//	    myLog.info("submit="+submit);
	    this.context.put("command1", submit);
	    String pageMode = getParam("pagemode");
	    this.context.put("pagemode", pageMode);
	    String action1 = getParam("action1");
	    this.context.put("action1", action1);
		String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
	    myLog.info("submit::"+submit+",pageMode::"+pageMode+",action1::"+action1);
		Vector lists = new Vector();
		
//		Long noHakmilik = null;
		Long idJenisHakmilik = null;
		Long idNegeri = null;
		Long idDaerah = null;
		Long idMukim = null;
		Long idKementerian = null;
		int idHakmilikCukaiInt = 0;
//		myLog.info(submit);
		tahunCukai = getParam("tahuncukai").equals("")?tahunCukai:getParam("tahuncukai");
		user_id =(String)session.getAttribute("_ekptg_user_id");
		
	    if("kemaskiniCukai".equals(submit)){
	    	myLog.info(":kemaskiniCukai::idHakmilikCukai="+getParam("idHakmilikCukai"));
		    template_name = PATH+"frmCukaiKemaskiniV1.jsp";
		    int idHakmilikCukai = Integer.parseInt((getParam("idHakmilikCukai")=="") ? "0" : getParam("idHakmilikCukai"));
		    String noHakmilikStr = (getParam("noHakmilik")=="") ? "0" : getParam("noHakmilik");
		    int intJenisHakmilik = Integer.parseInt((getParam("idJenisHakmilik")=="") ? "0" : getParam("idJenisHakmilik"));
		    int intKementerian = Integer.parseInt((getParam("idKementerian")=="") ? "0" : getParam("idKementerian"));
		    int intNegeri = Integer.parseInt((getParam("idNegeri")=="") ? "0" : getParam("idNegeri"));
		    int intDaerah = Integer.parseInt((getParam("idDaerah")=="") ? "0" : getParam("idDaerah"));
		    int intMukim = Integer.parseInt((getParam("idMukim")=="") ? "0" : getParam("idMukim"));
			
			this.context.put("noHakmilik", noHakmilikStr);
			this.context.put("idjenishakmilik", intJenisHakmilik);
			this.context.put("idKementerian", intKementerian);
			this.context.put("idNegeri", intNegeri);
			this.context.put("idDaerah", intDaerah);
			this.context.put("idMukim", intMukim);
			
			if("simpan".equals(pageMode)){
//				noHakmilik = Long.parseLong(getParam("noHakmilik"));
//				noHakmilikStr = getParam("noHakmilik");
//				idJenisHakmilik = Long.parseLong(getParam("idJenisHakmilik"));
//				idKementerian = Long.parseLong(getParam("idKementerian"));
//				idNegeri = Long.parseLong(getParam("idNegeri"));
//				idDaerah = Long.parseLong(getParam("idDaerah"));
//				idMukim = Long.parseLong(getParam("idMukim"));
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
//			    idHakmilikCukai = SimpanKCukai(session);
			    String idHakmilikCukaiStr = kemaskiniCukai(session);
			    myLog.info("::simpan::idHakmilikCukaiStr :"+idHakmilikCukaiStr);
//			    ViewCukai(String.valueOf(idHakmilikCukaiStr));
				hakmilik = getICukai().getCukaiHakmilik(idHakmilikCukaiStr);
				this.context.put("hakmilik",hakmilik);
				myLog.info("::simpan::hakmilik :"+hakmilik);
				viewEditCukai(hakmilik);
	
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result); 
			    this.context.put("pagemode", "viewCukaiV1");

			}else if("viewCukai".equals(pageMode)){
				myLog.info("viewCukai");
				noHakmilikStr = getParam("noHakmilik");
				idJenisHakmilik = Long.parseLong(getParam("idJenisHakmilik"));
				idKementerian = Long.parseLong(getParam("idKementerian"));
				idNegeri = Long.parseLong(getParam("idNegeri"));
				idDaerah = Long.parseLong(getParam("idDaerah"));
				idMukim = Long.parseLong(getParam("idMukim"));
				String idHakmilikCukaiStr = getParam("idhakmilikcukai");
				style2 = "none";
				
				Hashtable pen = null;
				//pen = FrmCukaiKemaskiniDataV1.getListKCukaiViewV1(Integer.parseInt(idHakmilikCukaiStr),noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim);
				pen = FrmCukaiKemaskiniDataV1.getListKCukaiViewV1(idHakmilikCukaiStr);
				//Hashtable pen = (Hashtable)vecHash.get(0);
				if (!pen.get("idHakmilikCukai").equals("")){
					idHakmilikCukai = Integer.parseInt(pen.get("idHakmilikCukai").toString());
				}else{
			    	idHakmilikCukai = 0;
				}
				ViewKCukai(session,idHakmilikCukai,noHakmilikStr,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim,disability,readability,style1,style2);	
				this.context.put("noHakmilik", noHakmilikStr);
				this.context.put("idjenishakmilik", idJenisHakmilik);
				this.context.put("idKementerian", idKementerian);
				this.context.put("idNegeri", idNegeri);
				this.context.put("idDaerah", idDaerah);
				this.context.put("idMukim", idMukim);
				
			}else if("viewCukaiV1".equals(pageMode)){
//				myLog.info("viewCukaiV1");
				String idHakmilikCukaiStr = getParam("idHakmilikCukai");
				myLog.info("idHakmilikCukaiStr="+idHakmilikCukaiStr);
				style2 = "none";
				this.context.put("style2", style2);
				hakmilik = getICukai().getCukaiHakmilik(idHakmilikCukaiStr);
				this.context.put("hakmilik",hakmilik);
				myLog.info("hakmilik="+hakmilik);
				if(hakmilik == null)
					throw new Exception("[001] REKOD TIDAK DIJUMPAI");
				
				this.context.put("idHakmilik",hakmilik.getIdHakmilik());
//				myLog.info("idHakmilik="+hakmilik.getIdHakmilik());
				this.context.put("noFailPTG",hakmilik.getNoFailPTG());
				this.context.put("noFailPTD",hakmilik.getNoFailPTD());
				//this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
				this.context.put("selectKementerian", hakmilik.getAgensi().getKementerian().getNamaKementerian());
				this.context.put("noFail", hakmilik.getNoFail());
				this.context.put("selectNegeri", hakmilik.getNegeri().getNamaNegeri());
				this.context.put("selectDaerah", hakmilik.getDaerah().getNamaDaerah());
				this.context.put("selectMukim", hakmilik.getMukim().getNamaMukim());
				this.context.put("selectAgensi", hakmilik.getAgensi().getNamaAgensi());
				//this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
				//this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
				//this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
				this.context.put("noHakmilik",hakmilik.getNoHakmlik());
				this.context.put("NoLot",hakmilik.getNoLot());	
				this.context.put("caraPerolehi", hakmilik.getPerolehan());
				
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", hakmilik.getRujJenisHakmilik().getIdJenishakmilik() ,"class=\"disabled\"  style=\"width:250\""));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas", hakmilik.getIdLuas(),"class=\"disabled\""," style=\"width:250\"")); 
				this.context.put("selectLot", HTML.SelectLot("socLot", hakmilik.getIdLuas(),"class=\"disabled\"  style=\"width:250\""));
				this.context.put("idJenisHakmilik", hakmilik.getRujJenisHakmilik().getIdJenishakmilik());

				this.context.put("idKementerian", hakmilik.getAgensi().getKementerian().getIdKementerian());
			
				//maklumat Cukai  				
				this.context.put("Luas", hakmilik.getLuasBersamaan());
				this.context.put("Cukai", Utils.format2Decimal(hakmilik.getHakmilikCukai().getCukai()));
				this.context.put("cukaiTerkini", Utils.format2Decimal(hakmilik.getHakmilikCukai().getCukaiTerkini()));
				this.context.put("cukaiTaliAir", Utils.format2Decimal(hakmilik.getHakmilikCukai().getCukaiTaliAir()));
				this.context.put("cukaiParit",Utils.format2Decimal(hakmilik.getHakmilikCukai().getCukaiParit()));
				this.context.put("Denda", Utils.format2Decimal(hakmilik.getHakmilikCukai().getDenda()));
				this.context.put("bayaranLain", Utils.format2Decimal(hakmilik.getHakmilikCukai().getBayaranLain()));			
				this.context.put("tunggakkan", Utils.format2Decimal(hakmilik.getHakmilikCukai().getTunggakan()));
				this.context.put("pengurangan",Utils.format2Decimal(hakmilik.getHakmilikCukai().getPengurangan()));
				this.context.put("pengecualian",Utils.format2Decimal(hakmilik.getHakmilikCukai().getPengecualian()));
//				this.context.put("lebihan", hakmilik.getHakmilikCukai().getPengecualian());
				this.context.put("kenaBayar",Utils.format2Decimal(hakmilik.getHakmilikCukai().getJumlah()));
				this.context.put("idHakmilikCukai", idHakmilikCukaiStr);
				this.context.put("tarikhDaftar",hakmilik.getTarikhDaftarFormat());
				this.context.put("kegunaanTanah",hakmilik.getKegunaan());				
				this.context.put("idNegeri", hakmilik.getNegeri().getIdNegeri());
				this.context.put("idDaerah", hakmilik.getDaerah().getIdDaerah());
				this.context.put("idMukim",  hakmilik.getMukim().getIdMukim());
				this.context.put("idMukim",  hakmilik.getMukim().getIdMukim());
				this.context.put("statusBayar", hakmilik.getHakmilikCukai().getBayaranCukai().getStatusBayaran());
				this.context.put("noResit", hakmilik.getHakmilikCukai().getBayaranCukai().getResit().getNoRujukan());
				this.context.put("tarikhResit", hakmilik.getHakmilikCukai().getBayaranCukai().getResit().getTarikhFormat());
				
				//myLog.info(hakmilik.getHakmilikCukai().getBayaranCukai().getStatusBayaran());
				
			}else if("editCukaiLama".equals(pageMode)){
				noHakmilikStr = getParam("noHakmilik");
				idJenisHakmilik = Long.parseLong(getParam("idJenisHakmilik"));
				idKementerian = Long.parseLong(getParam("idKementerian"));
				idNegeri = Long.parseLong(getParam("idNegeri"));
				idDaerah = Long.parseLong(getParam("idDaerah"));
				idMukim = Long.parseLong(getParam("idMukim"));
				idHakmilikCukai = Integer.parseInt(getParam("idHakmilikCukai"));
				style2 = "none";
				DataKCukai(session,idHakmilikCukai,noHakmilikStr,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim,disability,readability,style1,style2);	
				this.context.put("noHakmilik", noHakmilikStr);
				this.context.put("idjenishakmilik", idJenisHakmilik);
				this.context.put("idKementerian", idKementerian);
				this.context.put("idNegeri", idNegeri);
				this.context.put("idDaerah", idDaerah);
				this.context.put("idMukim", idMukim);
				
			}else if("editCukai_bak20100725".equals(pageMode)){
				myLog.info("editCukai");
				String idHakmilikCukaiStr = getParam("idHakmilikCukai");
				style2 = "none";
				//this.context.put("readonly", "readonly");
				this.context.put("style2", style2);

				Hashtable pen = null;
				Hashtable hashOut = null;
				Vector list = null;
				//pen = FrmCukaiKemaskiniDataV1.getListKCukaiViewV1(Integer.parseInt(idHakmilikCukaiStr),noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim);
				pen = FrmCukaiKemaskiniDataV1.getListKCukaiViewV1(idHakmilikCukaiStr);
				hashOut = new Hashtable();
				
				//Maklumat Hakmilik
				list = new Vector();
				list = FrmRekodPendaftaranHakmilikRizabData.getPaparMaklumatFailById((String)pen.get("idHakmilik"));
				Hashtable hMaklumatFail = (Hashtable) list.get(0);
				
				this.context.put("idHakmilik",pen.get("idHakmilik"));
				this.context.put("noFailPTG",(String)hMaklumatFail.get("noFailPtg"));
				this.context.put("noFailPTD","");
				//this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
				this.context.put("selectKementerian", (String)hMaklumatFail.get("namaKementerian"));
				this.context.put("noFail", (String)hMaklumatFail.get("noFailSeksyen"));
				this.context.put("selectNegeri", (String)hMaklumatFail.get("namaNegeri"));
				this.context.put("selectDaerah", (String)hMaklumatFail.get("namaDaerah"));
				this.context.put("selectMukim", (String)hMaklumatFail.get("namaMukim"));
				this.context.put("selectAgensi", (String)hMaklumatFail.get("namaAgensi"));
				//this.context.put("txtJenisHakmilik", (String)hMaklumatFail.get("jenisHakmilik"));
				//this.context.put("txtFailKJP", (String)hMaklumatFail.get("noFailKjp"));
				//this.context.put("txtNoWarta", (String)hMaklumatFail.get("noWarta"));
				this.context.put("noHakmilik",(String) hMaklumatFail.get("noHakmilik"));
				this.context.put("NoLot", (String)hMaklumatFail.get("noLot"));	
				this.context.put("caraPerolehi", (String)hMaklumatFail.get("caraPerolehan"));
				
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(String.valueOf(pen.get("idJenisHakmilik"))) ,"class=\"disabled\""));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(String.valueOf(pen.get("idLuas"))),"class=\"disabled\"", null)); 
				this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(String.valueOf(pen.get("idLot"))),"class=\"disabled\""));
				this.context.put("idJenisHakmilik", pen.get("idJenisHakmilik"));

				this.context.put("idKementerian", (String)hMaklumatFail.get("idKementerian"));
			
				//maklumat Cukai  
				
				this.context.put("Luas", pen.get("Luas"));
				this.context.put("cukaiTerkini", pen.get("cukaiTerkini"));
				this.context.put("cukaiTaliAir", pen.get("cukaiTaliAir"));
				this.context.put("cukaiParit", pen.get("cukaiParit"));
				this.context.put("Denda", pen.get("Denda"));
				this.context.put("bayaranLain", pen.get("bayaranLain"));
				this.context.put("Cukai", pen.get("Cukai"));
				this.context.put("lebihan", pen.get("lebihan"));
				this.context.put("tunggakkan", pen.get("tunggakkan"));
				this.context.put("pengecualian", pen.get("pengecualian"));
				this.context.put("pengurangan", pen.get("pengurangan"));
				this.context.put("idHakmilikCukai", idHakmilikCukaiStr);
				
				Vector list1 =null;
				list1 = FrmRekodPendaftaranHakmilikRizabData.getPaparHakmilikRizabById(String.valueOf(pen.get("idHakmilik")));
				Hashtable hHakmilik = (Hashtable) list1.get(0);
				
		    	this.context.put("tarikhDaftar",getParam("txdTarikhDaftar") == "" ?  (String)hHakmilik.get("tarikhDaftar"):getParam("txdTarikhDaftar"));
				this.context.put("kegunaanTanah",getParam("txtKegunaanTanah") == "" ? (String)hHakmilik.get("kegunaanTanah"):getParam("txtKegunaanTanah"));
				
				this.context.put("idNegeri", (String)hHakmilik.get("idNegeriHR"));
				this.context.put("idDaerah",  (String)hHakmilik.get("idDaerahHR"));
				this.context.put("idMukim",  (String)hHakmilik.get("idMukimHR"));
				
			}else if("editCukai".equals(pageMode)){
				myLog.info("editCukai");
				String idHakmilikCukaiStr = getParam("idHakmilikCukai");
				myLog.info("::idHakmilikCukaiStr : "+idHakmilikCukaiStr);
				style2 = "none";
				this.context.put("style2", style2);			
//				hakmilik = getICukai().getCukai(idHakmilikCukaiStr);
				hakmilik = getICukai().getCukaiHakmilik(idHakmilikCukaiStr);
				myLog.info("::hakmilik : "+hakmilik);
				this.context.put("hakmilik",hakmilik);
				viewEditCukai(hakmilik);
	
			}else if("editCukai_20111004".equals(pageMode)){
				myLog.info("editCukai");
				String idHakmilikCukaiStr = getParam("idHakmilikCukai");
				style2 = "none";
				//this.context.put("readonly", "readonly");
				this.context.put("style2", style2);
				
				hakmilik = getICukai().getCukai(idHakmilikCukaiStr);

				this.context.put("idHakmilik",hakmilik.getIdHakmilik());
				this.context.put("noFailPTG",hakmilik.getNoFailPTG());
				this.context.put("noFailPTD",hakmilik.getNoFailPTD());
				//this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
				this.context.put("selectKementerian", hakmilik.getAgensi().getKementerian().getNamaKementerian());
				this.context.put("noFail", hakmilik.getNoFail());
				this.context.put("selectNegeri", hakmilik.getNegeri().getNamaNegeri());
				this.context.put("selectDaerah", hakmilik.getDaerah().getNamaDaerah());
				this.context.put("selectMukim", hakmilik.getMukim().getNamaMukim());
				this.context.put("selectAgensi", hakmilik.getAgensi().getNamaAgensi());
				this.context.put("noHakmilik",hakmilik.getNoHakmlik());
				this.context.put("NoLot",hakmilik.getNoLot());	
				this.context.put("caraPerolehi", hakmilik.getPerolehan());
				
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", hakmilik.getRujJenisHakmilik().getIdJenishakmilik() ,"class=\"disabled\""));
				//this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(String.valueOf(pen.get("idLuas"))),"class=\"disabled\"", null)); 
				//this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(String.valueOf(pen.get("idLot"))),"class=\"disabled\""));
				this.context.put("idJenisHakmilik", hakmilik.getRujJenisHakmilik().getIdJenishakmilik());

				this.context.put("idKementerian", hakmilik.getAgensi().getKementerian().getIdKementerian());
			
				//maklumat Cukai  
				
				this.context.put("Luas", hakmilik.getLuasBersamaan());
				this.context.put("cukaiTerkini", hakmilik.getHakmilikCukai().getCukaiTerkini());
				this.context.put("cukaiTaliAir", hakmilik.getHakmilikCukai().getCukaiTaliAir());
				this.context.put("cukaiParit",hakmilik.getHakmilikCukai().getCukaiParit());
				this.context.put("Denda", hakmilik.getHakmilikCukai().getDenda());
				this.context.put("bayaranLain", hakmilik.getHakmilikCukai().getBayaranLain());
				this.context.put("Cukai", hakmilik.getHakmilikCukai().getCukai());
				this.context.put("lebihan", hakmilik.getHakmilikCukai().getPengecualian());
				this.context.put("tunggakkan", hakmilik.getHakmilikCukai().getPengurangan());
				this.context.put("pengecualian",hakmilik.getHakmilikCukai().getPengecualian());
				this.context.put("pengurangan",hakmilik.getHakmilikCukai().getPengurangan());
				this.context.put("idHakmilikCukai", idHakmilikCukaiStr);
				this.context.put("kegunaanTanah",hakmilik.getKegunaan());		
				this.context.put("idNegeri", hakmilik.getNegeri().getIdNegeri());
				this.context.put("idDaerah", hakmilik.getDaerah().getIdDaerah());
				this.context.put("idMukim",  hakmilik.getMukim().getIdMukim());
				
			}
	    }else if("senarai".equals(submit)){
	    		template_name = PATH+"frmCukaiSenaraiKemaskiniV1.jsp";
		    	String nohakmilik = getParam("txtNoHakmilik")==""?"0":getParam("txtNoHakmilik");
		    	String idjenishakmilik = getParam("socJenisHakmilik")==""?"0":getParam("socJenisHakmilik");
		    	String idnegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
		    	String iddaerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
		    	String idmukim = getParam("socMukim")==""?"0":getParam("socMukim");
		    	idJenisHakmilik = Long.parseLong(idjenishakmilik);
				idNegeri = Long.parseLong(idnegeri);
				idDaerah = Long.parseLong(iddaerah);
				idMukim = Long.parseLong(idmukim);
		    	String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
				
		    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",idJenisHakmilik,"onChange=\"doChangeHakmilik();\""));
		    	this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri",idNegeri,"onChange=\"doChangeNegeri();\"",null,"TBLHTPHAKMILIK"));
	    		this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",idDaerah,"onChange=\"doChangeDaerah();\"",null));
			    this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",idMukim,"onChange=\"doChangeMukim();\"",null));
		    	
			    //lists = FrmCukaiKemaskiniDataV1.getList(noHakmilik,idJenisHakmilik,idNegeri,idDaerah,idMukim);
//			    setupPage(session,action1,lists);
		    	this.context.put("carianNoHakmilik", getParam("txtNoHakmilik"));
		    	this.context.put("idjenishakmilik", idJenisHakmilik);
				this.context.put("idnegeri", idNegeri);
				this.context.put("iddaerah", idDaerah);
				this.context.put("idmukim", idMukim);
		    	this.context.put("carianNoLot", noLot);
				//baru
//				lists = FrmCukaiKemaskiniDataV1.getList(nohakmilik,idJenisHakmilik,idNegeri,idDaerah,idMukim);
				lists = getICukai().senaraiHakmilik(String.valueOf(idNegeri)
						, String.valueOf(idDaerah), String.valueOf(idMukim), tahunCukai, nohakmilik
						, String.valueOf(idJenisHakmilik),noLot);

				this.context.put("senaraiFail", lists);
				setupPageV1(session, action, lists);

	    }else if("kembali".equals(submit)){
	    	template_name = PATH+"frmCukaiSenaraiKemaskiniV1.jsp";	    	
	    	String idnegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
	    	String iddaerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
	    	String idmukim = getParam("socMukim")==""?"0":getParam("socMukim");
	    	String nohakmilik = getParam("txtNoHakmilik")==""?"0":getParam("txtNoHakmilik");
	    	idJenisHakmilik = getParam("socJenisHakmilik")==""?0:Long.parseLong(getParam("socJenisHakmilik"));
	    	idNegeri = Long.parseLong(idnegeri);
	    	idDaerah = Long.parseLong(iddaerah);
	    	idMukim = Long.parseLong(idmukim);
	    	String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");

	    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",idJenisHakmilik,"onChange=\"doChangeHakmilik();\""));
	    	this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri",idNegeri,"onChange=\"doChangeNegeri();\"",null,"TBLHTPHAKMILIK"));
    		this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",idDaerah,"onChange=\"doChangeDaerah();\"",null));
		    this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",idMukim,"onChange=\"doChangeMukim();\"",null));
	    	this.context.put("carianNoLot", noLot);
	    	
			lists = getICukai().senaraiHakmilik(String.valueOf(idNegeri)
					, String.valueOf(idDaerah), String.valueOf(idMukim), tahunCukai, nohakmilik
					, String.valueOf(idJenisHakmilik),noLot);
//	    	this.context.put("senaraiFail", lists);
			setupPage(session, action, lists);
	    	
	    }else{
//	    	myLog.info(": !=submit::else:::user_id="+session.getAttribute("_ekptg_user_id"));
	    	template_name = PATH+"frmCukaiSenaraiKemaskiniV1.jsp";
	    	String nohakmilik = getParam("txtNoHakmilik")==""?"0":getParam("txtNoHakmilik");
	    	String idjenishakmilik = getParam("socJenisHakmilik")==""?"0":getParam("socJenisHakmilik");
	    	String idnegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
	    	String iddaerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
	    	String idmukim = getParam("socMukim")==""?"0":getParam("socMukim");
	    	String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
	    	idJenisHakmilik = Long.parseLong(idjenishakmilik);
			idNegeri = Long.parseLong(idnegeri);
			idDaerah = Long.parseLong(iddaerah);
			idMukim = Long.parseLong(idmukim);
			
	    	this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",idJenisHakmilik,"onChange=\"doChangeHakmilik();\""));
	    	this.context.put("selectNegeri",UtilHTML.SelectNegeri("socNegeri",idNegeri,"onChange=\"doChangeNegeri();\"",null,"TBLHTPHAKMILIK"));
    		this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idnegeri,"socDaerah",idDaerah,"onChange=\"doChangeDaerah();\"",null));
		    this.context.put("selectMukim",HTML.SelectMukimByDaerah(iddaerah,"socMukim",idMukim,"onChange=\"doChangeMukim();\"",null));	    	
	    	this.context.put("carianNoHakmilik", getParam("txtNoHakmilik"));
	    	this.context.put("carianNoLot", noLot);
	    	this.context.put("idjenishakmilik", idJenisHakmilik);
			this.context.put("idnegeri", idNegeri);
			this.context.put("iddaerah", idDaerah);
			this.context.put("idmukim", idMukim);
			//baru
			lists = getICukai().senaraiHakmilik(String.valueOf(idNegeri)
					, String.valueOf(idDaerah), String.valueOf(idMukim), tahunCukai, nohakmilik
					, String.valueOf(idJenisHakmilik),noLot);
			if(submit.indexOf("doChange")!= -1){
				lists = getICukai().senaraiHakmilik(String.valueOf(idNegeri)
						, String.valueOf(idDaerah), String.valueOf(idMukim), tahunCukai, nohakmilik
						, String.valueOf(idJenisHakmilik),noLot);
				// this.context.put("senaraiFail", lists);
			
			}
			setupPage(session, action, lists);

	    }
	    
		this.context.put("tahuncukai", Integer.parseInt(tahunCukaiSemasa));
		this.context.put("tahunparam", Integer.parseInt(tahunCukai));    
	    return template_name;
	  
	}
	
	private int SimpanKCukai(HttpSession session) throws Exception{
		int idHakmilikCukai = 0;
		myLog.info("SimpanKCukai:::idHakmilikCukai::check::"+getParam("idHakmilikCukai"));
		//kemaskini cukai
		Hashtable h = new Hashtable();
		h.put("idHakmilikCukai", Integer.parseInt(getParam("idHakmilikCukai")));
		h.put("idHC", Integer.parseInt(getParam("idHC")));
		h.put("idHakmilik", Integer.parseInt(getParam("idHakmilik")));
		h.put("cukaiTerkini", getParam("txtCukaiTerkini"));
		h.put("cukaiTaliAir", getParam("txtCukaiTaliAir"));
		h.put("cukaiParit", getParam("txtCukaiParit"));
		h.put("Denda", getParam("txtDenda"));
		h.put("bayaranLain", getParam("txtBayaranLain"));
		h.put("Cukai", getParam("txtCukaiPertama"));
		h.put("pengecualian", getParam("txtLebihan"));
		h.put("pengurangan", getParam("txtTunggakkan"));
		h.put("statusBayaran", getParam("txtStatusBayaran"));		
		idHakmilikCukai = FrmCukaiKemaskiniDataV1.update(h);
		result = "kemaskini";
		return idHakmilikCukai;
			
	}
	
	private String kemaskiniCukai(HttpSession session) throws Exception{
		//String idCukai=getParam("idCukai");
		String idCukai = getParam("idHakmilikCukai");
		myLog.info("::kemaskiniCukai::idCukai :"+idCukai);
		String cukaiTerkini = "";
		String cukaiTaliair = "";
		String cukaiParit = "";
		String CukaiLain = "";
		String Tunggakan = "";
		String Denda = "";
		String Lebihan = "";
		String JumBayaran = "";
		String pengurangan = "";
		String pengecualian ="";
		cukaiTerkini = getParam("txtCukaiTerkini")==""?"0":getParam("txtCukaiTerkini");
		cukaiTaliair = getParam("txtCukaiTaliAir")==""?"0":getParam("txtCukaiTaliAir");
		cukaiParit = getParam("txtCukaiParit")==""?"0":getParam("txtCukaiParit");
		Denda = getParam("txtDenda")==""?"0":getParam("txtDenda");
		//CukaiLain = getParam("txtBayaranLain")==""?"0":getParam("txtBayaranLain");
		pengurangan = getParam("txtPengurangan")==""?"0":getParam("txtPengurangan");
		pengecualian = getParam("txtPengecualian")==""?"0":getParam("txtPengecualian");		
		Lebihan = getParam("txtLebihan")==""?"0":getParam("txtLebihan");
		JumBayaran = getParam("txtKenaBayar")==""?"0":getParam("txtKenaBayar"); 
		String noResit = getParam("txtNoResit"); 
		String tarikhResit = getParam("txdTarikhResit"); 
		
		//fix bug. 25/11/2014. syaz. 
		Tunggakan = getParam("txtTunggakkan")==""?"0":getParam("txtTunggakkan");	
		
		
		//FIX BUG!. SYAZ. 25112014. 
		//PROBLEM : GET BAYARAN LAIN FROM SCREEN, SAVE AS CUKAI_LAIN. THEN DISPLAY BACK USING BAYARAN_LAIN. ????
		//SOLUTION : SAVE IN FIELD BAYARAN_LAIN
		String bayaranLain = getParam("txtBayaranLain")==""?"0":getParam("txtBayaranLain");
		
		myLog.info("::CukaiLain : "+CukaiLain);
		myLog.info("::txtTunggakkan : "+Tunggakan);
		
		CukaiTemp temp = new CukaiTemp();
		temp.setCukaiKenaBayar(Double.parseDouble(Utils.RemoveComma(cukaiTerkini)));
		temp.setCukaiTaliAir(Double.valueOf(Utils.RemoveComma(cukaiTaliair)));
		temp.setCukaiParit(Double.valueOf(Utils.RemoveComma(cukaiParit)));
		temp.setDenda(Double.parseDouble(Utils.RemoveComma(Denda)));
		temp.setBayaranLain(Double.valueOf(Utils.RemoveComma(bayaranLain)));
		temp.setTunggakkan(Double.valueOf(Utils.RemoveComma(Tunggakan)));
		temp.setPengurangan(Double.valueOf(Utils.RemoveComma(pengurangan)));
		temp.setPengecualian(Double.valueOf(Utils.RemoveComma(pengecualian)));
		//temp.setLebihan(Double.valueOf(Utils.RemoveComma(Lebihan)));
		temp.setIdCukaiTemp(Long.parseLong(idCukai));
		temp.setTotalcukai(Double.parseDouble(Utils.RemoveComma(JumBayaran)));
		temp.setIdMasuk(Long.parseLong(user_id));
		temp.setIdKemaskini(user_id);
		temp.setNoResit(noResit);
		temp.setTarikhResit(Utils.isNull(tarikhResit));
//		FrmCukaiKemaskiniDataBaru.updateDataManual(temp);
		getICukai().kemaskiniHakmilikCukaiTemp(temp);
//		CukaiTemp cukai = FrmCukaiSenaraiFailExcelUpload.getCukaiTemp(idCukai);
//		System.out.println("cukai lebihan:"+temp.getLebihan());
//		context.put("cukai", cukai);
//		return String.valueOf(cukai.getIdCukaiTemp());
		myLog.info("::kemaskiniCukai::return::idCukai :"+idCukai);
		return idCukai;
		
	}

	private void DataKCukai(HttpSession session,int idHakmilikCukai, String noHakmilik, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim, String disability, String readability, String style1, String style2) throws Exception{
	  	Vector list = new Vector();
		list.clear();
				    
		try{
//			list = FrmCukaiKemaskiniDataV1.getListKCukai(idHakmilikCukai,noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim);
			list = getICukai().getSenaraiCukaiUtkKemaskini(idHakmilikCukai,noHakmilik,idJenisHakmilik
					,idKementerian,idNegeri,idDaerah,idMukim);
			myLog.info("CukaiProcess::DataKCukai::list.size():::"+list.size());
			this.context.put("ResultSimpan", "");
			
			if(list.size() != 0){			    
				this.context.put("cukaikemaskini", list);
				myLog.info("DataKCukai::list::"+list);
				Hashtable cHash = (Hashtable) list.get(0);
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", idJenisHakmilik ,"disabled"));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", idNegeri ,"disabled",null));
				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah", idDaerah ,"disabled",null));
				this.context.put("selectMukim",HTML.SelectMukim("socMukim", idMukim ,"disabled"));
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", idKementerian,"disabled",null));
				this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", ""+idKementerian, idKementerian, "disabled"));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(cHash.get("idLuas").toString()), "disabled", null)); 
				this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(cHash.get("idLot").toString()), "disabled"));
				this.context.put("idHakmilikCukai", cHash.get("idHakmilikCukai"));
				this.context.put("idHakmilik", cHash.get("idHakmilik"));
				System.out.println("kemaskiniCukai::DataKCukai::idHakmilikCukai:::"+cHash.get("idHakmilikCukai"));
				
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("cukaikemaskini", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("kemaskiniCukai::DataKCukai::Exception:: = "+ex);
			ex.printStackTrace();
		}
	}

	private void ViewKCukai(HttpSession session,int idHakmilikCukai, String noHakmilik
			, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim
			, String disability, String readability, String style1, String style2) throws Exception {
	  	Vector list = new Vector();
		list.clear();
				    
		try{
//			list = FrmCukaiKemaskiniDataV1.getListKCukaiView(idHakmilikCukai,noHakmilik,idJenisHakmilik,idKementerian,idNegeri,idDaerah,idMukim);
			list = getICukai().getSenaraiCukaiUtkKemaskini(idHakmilikCukai,noHakmilik,idJenisHakmilik
					,idKementerian,idNegeri,idDaerah,idMukim);
			myLog.info("CukaiProcess::ViewKCukai::list.size():::"+list.size());
			this.context.put("ResultSimpan", "");
			
			if(list.size() != 0){			    
				this.context.put("cukaikemaskini", list);
//				System.out.println("kemaskiniCukai::ViewKCukai::list::"+list);
				Hashtable cHash = (Hashtable) list.get(0);
//				System.out.println("kemaskiniCukai::ViewKCukai::idHakmilikCukai:::"+idHakmilikCukai);
//				System.out.println("kemaskiniCukai::ViewKCukai::noHakmilik:::"+noHakmilik);
//				System.out.println("kemaskiniCukai::ViewKCukai::idJenisHakmilik:::"+idJenisHakmilik);
//				System.out.println("kemaskiniCukai::ViewKCukai::idKementerian:::"+idKementerian);
//				System.out.println("kemaskiniCukai::ViewKCukai::idNegeri:::"+idNegeri);
//				System.out.println("kemaskiniCukai::ViewKCukai::idDaerah:::"+idDaerah);
//				System.out.println("kemaskiniCukai::ViewKCukai::idMukim:::"+idMukim);
//			    
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", idJenisHakmilik ,"disabled"));
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", idNegeri ,"disabled",null));
				this.context.put("selectDaerah",HTML.SelectDaerah("socDaerah", idDaerah ,"disabled",null));
				this.context.put("selectMukim",HTML.SelectMukim("socMukim", idMukim ,"disabled"));
				this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", idKementerian,"disabled",null));
				this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", ""+idKementerian, idKementerian, "disabled"));
				this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(cHash.get("idLuas").toString()), "disabled", null)); 
				this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(cHash.get("idLot").toString()), "disabled"));
				this.context.put("idHakmilikCukai", cHash.get("idHakmilikCukai"));
				this.context.put("idHakmilik", cHash.get("idHakmilik"));
				System.out.println("kemaskiniCukai::ViewKCukai::idHakmilikCukai:::"+cHash.get("idHakmilikCukai"));
				
			    this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			}else{
				this.context.put("cukaikemaskini", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			System.out.println("kemaskiniCukai::ViewKCukai::Exception:: = "+ex);
			ex.printStackTrace();
		}
	}
	
	private void ViewCukai(String idHakmilikCukaiStr)throws Exception{ 
		String style2 = "none";
		//this.context.put("readonly", "readonly");
		this.context.put("style2", style2);
		hakmilik = getICukai().getCukai(idHakmilikCukaiStr);

		this.context.put("idHakmilik",hakmilik.getIdHakmilik());
		this.context.put("noFailPTG",hakmilik.getNoFailPTG());
		this.context.put("noFailPTD",hakmilik.getNoFailPTD());
		//this.context.put("txtTajuk",(String)hMaklumatFail.get("tajukFail"));
		this.context.put("selectKementerian", hakmilik.getAgensi().getKementerian().getNamaKementerian());
		this.context.put("noFail", hakmilik.getNoFail());
		this.context.put("selectNegeri", hakmilik.getNegeri().getNamaNegeri());
		this.context.put("selectDaerah", hakmilik.getDaerah().getNamaDaerah());
		this.context.put("selectMukim", hakmilik.getMukim().getNamaMukim());
		this.context.put("selectAgensi", hakmilik.getAgensi().getNamaAgensi());
		this.context.put("noHakmilik",hakmilik.getNoHakmlik());
		this.context.put("NoLot",hakmilik.getNoLot());	
		this.context.put("caraPerolehi", hakmilik.getPerolehan());
		
		this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", hakmilik.getRujJenisHakmilik().getIdJenishakmilik() ,"class=\"disabled\""));
		//this.context.put("selectLuas", HTML.SelectLuas("socLuas", Long.parseLong(String.valueOf(pen.get("idLuas"))),"class=\"disabled\"", null)); 
		//this.context.put("selectLot", HTML.SelectLot("socLot", Long.parseLong(String.valueOf(pen.get("idLot"))),"class=\"disabled\""));
		this.context.put("idJenisHakmilik", hakmilik.getRujJenisHakmilik().getIdJenishakmilik());

		this.context.put("idKementerian", hakmilik.getAgensi().getKementerian().getIdKementerian());
			
		this.context.put("Luas", hakmilik.getLuasBersamaan());
		this.context.put("cukaiTerkini", hakmilik.getHakmilikCukai().getCukaiTerkini());
		this.context.put("cukaiTaliAir", hakmilik.getHakmilikCukai().getCukaiTaliAir());
		this.context.put("cukaiParit",hakmilik.getHakmilikCukai().getCukaiParit());
		this.context.put("Denda", hakmilik.getHakmilikCukai().getDenda());
		this.context.put("bayaranLain", hakmilik.getHakmilikCukai().getBayaranLain());
		this.context.put("Cukai", hakmilik.getHakmilikCukai().getCukai());
		this.context.put("lebihan", hakmilik.getHakmilikCukai().getPengecualian());
		this.context.put("tunggakkan", hakmilik.getHakmilikCukai().getPengurangan());
		this.context.put("pengecualian",hakmilik.getHakmilikCukai().getPengecualian());
		this.context.put("pengurangan",hakmilik.getHakmilikCukai().getPengurangan());
		this.context.put("idHakmilikCukai", idHakmilikCukaiStr);
		this.context.put("kegunaanTanah",hakmilik.getKegunaan());		
		this.context.put("idNegeri", hakmilik.getNegeri().getIdNegeri());
		this.context.put("idDaerah", hakmilik.getDaerah().getIdDaerah());
		this.context.put("idMukim",  hakmilik.getMukim().getIdMukim());

	}
	
	public void setupPageV1(HttpSession session, String action1, Vector lists) {
		this.context.put("totalRecords",lists.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action1)) {
	    	page++;
	    } else if ("getPrevious".equals(action1)) {
	    	page--;
	    } else if ("getPage".equals(action1)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action1)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
		Paging paging = new Paging(session,lists,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("lists",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	}
	
	public void setupPageXX(HttpSession session, String action, Vector list) {
		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
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

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("senaraiFail", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	private void viewEditCukai(HakMilik hakmilik) throws Exception{
		//try {
		this.context.put("idHakmilik",hakmilik.getIdHakmilik());
		this.context.put("noFailPTG",hakmilik.getNoFailPTG());
		this.context.put("noFailPTD",hakmilik.getNoFailPTD());
		this.context.put("selectKementerian", hakmilik.getAgensi().getKementerian().getNamaKementerian());
		this.context.put("noFail", hakmilik.getNoFail());
		this.context.put("selectNegeri", hakmilik.getNegeri().getNamaNegeri());
		this.context.put("selectDaerah", hakmilik.getDaerah().getNamaDaerah());
		this.context.put("selectMukim", hakmilik.getMukim().getNamaMukim());
		this.context.put("selectAgensi", hakmilik.getAgensi().getNamaAgensi());
		this.context.put("noHakmilik",hakmilik.getNoHakmlik());
		this.context.put("NoLot",hakmilik.getNoLot());	
		this.context.put("caraPerolehi", hakmilik.getPerolehan());
		
		this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik", hakmilik.getRujJenisHakmilik().getIdJenishakmilik() ,"class=\"disabled\"  style=\"width:250\""));
		this.context.put("selectLuas", HTML.SelectLuas("socLuas", hakmilik.getIdLuas(),"class=\"disabled\"", "  style=\"width:250\"")); 
		this.context.put("selectLot", HTML.SelectLot("socLot", hakmilik.getIdLuas(),"class=\"disabled\"  style=\"width:250\""));
		this.context.put("idJenisHakmilik", hakmilik.getRujJenisHakmilik().getIdJenishakmilik());	
		this.context.put("idKementerian", hakmilik.getAgensi().getKementerian().getIdKementerian());
	
		//maklumat Cukai  				
		this.context.put("Luas", hakmilik.getLuasBersamaan());
		this.context.put("Cukai", Utils.format2Decimal(hakmilik.getHakmilikCukai().getCukai()));
		this.context.put("cukaiTerkini", Utils.format2Decimal(hakmilik.getHakmilikCukai().getCukaiTerkini()));
		this.context.put("cukaiTaliAir", Utils.format2Decimal(hakmilik.getHakmilikCukai().getCukaiTaliAir()));
		this.context.put("cukaiParit",Utils.format2Decimal(hakmilik.getHakmilikCukai().getCukaiParit()));
		this.context.put("Denda", Utils.format2Decimal(hakmilik.getHakmilikCukai().getDenda()));
		this.context.put("bayaranLain", Utils.format2Decimal(hakmilik.getHakmilikCukai().getBayaranLain()));			
		this.context.put("tunggakkan", Utils.format2Decimal(hakmilik.getHakmilikCukai().getTunggakan()));
		this.context.put("pengurangan",Utils.format2Decimal(hakmilik.getHakmilikCukai().getPengurangan()));
		this.context.put("pengecualian",Utils.format2Decimal(hakmilik.getHakmilikCukai().getPengecualian()));
		this.context.put("lebihan", Utils.format2Decimal(hakmilik.getHakmilikCukai().getLebihan()));
		this.context.put("kenaBayar",Utils.format2Decimal(hakmilik.getHakmilikCukai().getJumlah()));
		this.context.put("idHakmilikCukai", getParam("idHakmilikCukai"));
		this.context.put("tarikhDaftar",hakmilik.getTarikhDaftarFormat());
		this.context.put("kegunaanTanah",hakmilik.getKegunaan());				
		this.context.put("idNegeri", hakmilik.getNegeri().getIdNegeri());
		this.context.put("idDaerah", hakmilik.getDaerah().getIdDaerah());
		this.context.put("idMukim",  hakmilik.getMukim().getIdMukim());
		this.context.put("statusBayar", hakmilik.getHakmilikCukai().getBayaranCukai().getStatusBayaran());
	
	}
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new FrmCukaiBayaranBean();
		}
		return iCukai;
		
	}
	
}

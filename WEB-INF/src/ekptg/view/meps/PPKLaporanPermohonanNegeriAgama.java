package ekptg.view.meps;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.meps.IMEPPTNegeri;
import ekptg.model.meps.IMEPUmum;
import ekptg.model.meps.IMEPUtils;
import ekptg.model.meps.MEPPKNegeriWarisBean;
import ekptg.model.meps.MEPPTNegeriBean;
import ekptg.model.meps.MEPUtilBean;

public class PPKLaporanPermohonanNegeriAgama
  extends AjaxBasedModule
{
  private IHtp iHTP = null;
  private IMEPUmum iMep = null;
  private IMEPUtils iMepUtil = null;
  private IMEPPTNegeri iMepPPT = null;
  private static final long serialVersionUID = -855108166857901260L;
  static Logger myLog = Logger.getLogger(PPKLaporanPermohonanNegeriAgama.class);
  private String idSubUrusan = "";
  
  public String doTemplate2()
    throws Exception
  {
    HttpSession session = this.request.getSession();
    String vm = "";
    String submit = getParam("command");
    String refence = "no";
    
    Vector listPageDepan = new Vector();
    Vector listKod = new Vector();
    listPageDepan.clear();
    
    String idTahun = "";
    String idTahunHingga = "";
    String idBulan = "";
    String idBulanHingga = "";
    
 // MID
	String ID_NEGERI = "";
	String ID_PEJABATJKPTG = "";
	context.put("ID_NEGERI", "");
	context.put("ID_PEJABATJKPTG", "");
	
	List list_TBLRUJNEGERI = null;
	List listPejabatJKPTG = null;
    
    String userId = (String)session.getAttribute("_ekptg_user_id");
    if (userId == null) {
      throw new Exception(getIHTP().getErrorHTML("[MODUL MEP] SILA LOGIN SEMULA"));
    }
    InternalUser iu = null;
    
    String skrinDepanSenaraiLaporan = "app/meps/ppk/LaporanJumlahPermohonanNegeriAgama.jsp";
    
    String selectedTab = "";
    selectedTab = getParam("tabId");
    if ((selectedTab == null) || ("".equals(selectedTab))) {
      selectedTab = "0";
      
      	ID_NEGERI = getParam("ID_NEGERI");
		context.put("ID_NEGERI", ID_NEGERI);
		ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
		context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
      
    }
    myLog.info("SUBMIT :: " + submit);
    myLog.info("SUBMIT :: socSuburusan=" + getParam("socSuburusan"));
    myLog.info("SUBMIT :: selectedTab=" + selectedTab);
    
    //this.idSubUrusan = (getParam("socSuburusan").equals("") ? null : getParam("socSuburusan"));
    this.idSubUrusan = (getParam("socSuburusan").equals("") || getParam("socSuburusan").equals("-1")) ? null:getParam("socSuburusan");
    this.context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("382", null, "socSuburusan", this.idSubUrusan != null ? Long.valueOf(Long.parseLong(this.idSubUrusan)) : null, ""));
	
    list_TBLRUJNEGERI = getMep().listTableRujukanV3(session,"TBLRUJNEGERI","","");
	this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
	listPejabatJKPTG = getMep().listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
	this.context.put("listPejabatJKPTG",listPejabatJKPTG);
    
    String role = String.valueOf(session.getAttribute("myrole"));
	//if(role.equals("adminppk") || role.equals("usernegeri_ppk"))
	String idNegeri = null;
	if(role.equals("user_ppk")||role.equals("usernegeri_ppk")){
		iu = InternalUserUtil.getSeksyenId(userId);
		idNegeri = iu.getIdNegeri();
		
	}

    this.context.put("ID_NEGERI", ID_NEGERI);
    vm = skrinDepanSenaraiLaporan;
    
    ID_NEGERI = getParam("ID_NEGERI");
	context.put("ID_NEGERI", ID_NEGERI);
	ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
	context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
    
    idTahun = getParam("socTahun").equals("") ? "" : getParam("socTahun");
    idTahunHingga = getParam("socTahunHingga").equals("") ? "" : getParam("socTahunHingga");
    
    idBulan = getParam("socBulan");
    idBulanHingga = getParam("socBulanHingga");
    if (idBulan.equals(""))
    {
      idBulan = "";
    }
    else
    {
      idBulan = getParam("socBulan");
      if (idBulan.length() < 2) {
        idBulan = "0" + idBulan;
      }
    }
    String idBulanHingga_ = "";
    if (idBulanHingga.equals(""))
    {
      idBulanHingga = "";
      idBulanHingga_ = idBulanHingga;
    }
    else
    {
      idBulanHingga = getParam("socBulanHingga");
      idBulanHingga_ = idBulanHingga;
      if (idBulanHingga.length() < 2)
      {
        idBulanHingga = "0" + idBulanHingga;
        idBulanHingga_ = "0" + (Integer.parseInt(idBulanHingga) + 1);
      }
    }
    this.context.put("id_tahun", idTahun);
    this.context.put("idTahunHingga", idTahunHingga);
    this.context.put("id_bulan", idBulan);
    this.context.put("idBulanHingga", idBulanHingga);
    
    list_TBLRUJNEGERI = getMep().listTableRujukanV3(session,"TBLRUJNEGERI","","");
	this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
	listPejabatJKPTG = getMep().listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
	this.context.put("listPejabatJKPTG",listPejabatJKPTG);
	
	myLog.info("BULANHINGGA::: :: " + idBulanHingga);
    
//    this.context.put("selectTahun", getMepUtils().SelectTahun(getTahun(idNegeri), "socTahun", idTahun, null, "style=width:130px"));
//    this.context.put("selectTahunHingga", getMepUtils().SelectTahun(getTahun(idNegeri), "socTahunHingga", idTahunHingga, null, "style=width:130px"));
//    this.context.put("selectBulan", HTML.SelectBulan("socBulan", Utils.parseLong(idBulan), null, "style=width:130px"));
//    this.context.put("selectBulanHingga", HTML.SelectBulan("socBulanHingga", Utils.parseLong(idBulanHingga), null, "style=width:130px"));
    
    if(!idBulan.equals("")){
		listPageDepan = getMep().getJumlahPermohonanNegeriAgama(ID_NEGERI, ID_PEJABATJKPTG, idTahun,idTahunHingga,idBulan,idBulanHingga,idSubUrusan);
	}
	else
	{
		listPageDepan = getMep().getJumlahPermohonanNegeriAgama(ID_NEGERI, ID_PEJABATJKPTG, idTahun,idTahunHingga,idBulan,idBulanHingga,idSubUrusan);
		//listPageDepan = getMep().getJumlahPermohonanNegeriAgama(idTahun,idTahunHingga,idNegeri,idSubUrusan);
		//String socTahun,String socTahunHingga,String idNegeri,String idSubUrusan
	}
	
	Vector v = new Vector();
	Hashtable h = null;
	//Vector vecTahun = getMep().getJumlahPermohonan("","",idNegeri,idSubUrusan);
	for (int i = 0; i < listPageDepan.size(); i++) {
		Hashtable hTahun = (Hashtable) listPageDepan.get(i);
		h = new Hashtable();
		h.put("id",hTahun.get("tahun"));
		h.put("tahun",hTahun.get("tahun"));
		
		ID_NEGERI = getParam("ID_NEGERI");
		context.put("ID_NEGERI", ID_NEGERI);
		ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
		context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
		
		v.addElement(h);				
	}
	context.put("selectTahun",getMepUtils().SelectTahun(v,"socTahun" , idTahun, null, "style=width:130px"));
	context.put("selectTahunHingga",getMepUtils().SelectTahun(v,"socTahunHingga", idTahunHingga, null, "style=width:130px"));
	context.put("selectBulan", HTML.SelectBulan("socBulan", Utils.parseLong(idBulan), null, "style=width:130px"));
    context.put("selectBulanHingga", HTML.SelectBulan("socBulanHingga", Utils.parseLong(idBulanHingga), null, "style=width:130px"));
    
    list_TBLRUJNEGERI = getMep().listTableRujukanV3(session,"TBLRUJNEGERI","","");
	this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
	listPejabatJKPTG = getMep().listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
	this.context.put("listPejabatJKPTG",listPejabatJKPTG);
//    listPageDepan = getMep().getJumlahPermohonan(idTahun, idTahunHingga, idNegeri, this.idSubUrusan);
//    if (!idBulan.equals("")) {
//      listPageDepan = getMep().getJumlahPermohonan(idTahun, idTahunHingga, idBulan, idBulanHingga_, idNegeri, this.idSubUrusan);
//    }
	
    this.context.put("PermohonanList", listPageDepan);
    this.context.put("list_size", Integer.valueOf(listPageDepan.size()));
    
  //GENERATE BAR & PIE CHART
  		//TAB
    
    this.context.put("selectedTab", selectedTab);
    String tajukLaporan = "Laporan Jumlah Permohonan (Islam/Bukan Islam) ";
    if (this.idSubUrusan != null)
    {
      Tblrujsuburusan f = getMepPPT().getSubUrusan(this.idSubUrusan);
      
      tajukLaporan = tajukLaporan + f.getNamaSuburusan() + " ";
    }
    if (!idTahun.trim().equals("")) {
      if (!idTahunHingga.trim().equals("")) {
        tajukLaporan = tajukLaporan + "dari tahun " + idTahun + " sehingga " + idTahunHingga;
      } else {
        tajukLaporan = tajukLaporan + "bagi tahun " + idTahun;
      }
    }
    
    String pej = getMep().getNamaPejabat(session, ID_PEJABATJKPTG);
	if (ID_NEGERI.equals("") || !ID_NEGERI.equals("")) {
		if (!ID_PEJABATJKPTG.equals("")) {
			tajukLaporan += " BAGI "+ pej;					
		} 
	}
    
    if (!submit.equals("")) {
      //this.context.put("xml", getMepUtils().generateXML(getMep().getSQL(), tajukLaporan.toUpperCase()));
    }
    this.context.put("selectedTab", selectedTab);
    this.context.put("tajukLaporan", tajukLaporan.toUpperCase());
    this.context.put("refence", refence);
    this.context.put("EkptgUtil", new Utils());
    return vm;
    
    
    
  }
  
  private Vector getTahun(String ID_NEGERI)
    throws Exception
  {
    Vector v = new Vector();
    Hashtable h = null;
    Vector vecTahun = getMep().getJumlahPermohonan(ID_NEGERI, "", "","", this.idSubUrusan);
    for (int i = 0; i < vecTahun.size(); i++)
    {
      Hashtable hTahun = (Hashtable)vecTahun.get(i);
      h = new Hashtable();
      h.put("id", hTahun.get("tahun"));
      h.put("tahun", hTahun.get("tahun"));
      v.addElement(h);
    }
    return v;
  }
  
  private IMEPUmum getMep()
  {
    if (this.iMep == null) {
      this.iMep = new MEPPKNegeriWarisBean();
    }
    return this.iMep;
  }
  
  private IMEPUtils getMepUtils()
  {
    if (this.iMepUtil == null) {
      this.iMepUtil = new MEPUtilBean();
    }
    return this.iMepUtil;
  }
  
  private IMEPPTNegeri getMepPPT()
  {
    if (this.iMepPPT == null) {
      this.iMepPPT = new MEPPTNegeriBean();
    }
    return this.iMepPPT;
  }
  
  private IHtp getIHTP()
  {
    if (this.iHTP == null) {
      this.iHTP = new HtpBean();
    }
    return this.iHTP;
  }
}

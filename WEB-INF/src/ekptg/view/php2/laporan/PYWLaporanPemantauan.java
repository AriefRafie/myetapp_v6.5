package ekptg.view.php2.laporan;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.HtpLaporanBean;
import ekptg.model.htp.IHtpLaporan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilHTML;

public class PYWLaporanPemantauan extends AjaxBasedModule {
	/**
	 *
	 */
	private IHtpLaporan iLaporan = null;
	private final String PATH="app/php2/laporan/pyw/";
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.php2.laporan.PYWLaporanPemantauan.class);

	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;

	String socNegeri = "";
	String socUnit = "";
	String socStatus = "";
	String socAgensi = "";
	String socDaerahBaru = "";
	String sorTempoh = "1";
    String tarikhMula = "";
    String tarikhAkhir = "";
    String tarikhMulaTahun = "";
    String tarikhAkhirTahun = "";

	public String doTemplate2() throws Exception {

	    HttpSession session = this.request.getSession();
    	String checkBulan = "";
    	String checkTahun = "";
    	String checkTempoh = "";

		String vm = PATH+"pemantauanIndex.jsp";
   		String submit = getParam("command");
		String userId = (String)session.getAttribute("_portal_login");
		Long tempIdNegeri = -1L;
		int userLevel = 0;
		String idNegeri="";

    	String idStatus = getParam("socStatus");
    	if (idStatus == null || idStatus.trim().length() == 0){
    		idStatus = "99999";
    	}

        String defaulTahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
		Long defaultBulan = Long.parseLong(lebah.util.Util.getDateTime(new Date(), "MM"));

        //Carian
        setSOC(tempIdNegeri,idStatus);

		sorTempoh = getParam("sorTempoh");

    	myLog.info("submit="+submit);
    	if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			String tempIdDaerah = getParam("socDaerahBaru").equals("")?"0":getParam("socDaerahBaru");

	        setSOC(tempIdNegeri,idStatus,tempIdDaerah);

    		if(!"".equals(getParam("txdMula"))){
    			defaultBulan = Long.parseLong(getParam("txdMula"));
    			defaulTahun = getParam("txdTahunMula");
    			tarikhMula = HTML.SelectBulan("txdMula",defaultBulan," style=\"width: 100px;\"");
    			tarikhMulaTahun = HTML.SelectTahun("txdTahunMula",defaulTahun," style=\"width: 100px;\"",null);
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			Long defaultBulanh = Long.parseLong(getParam("txdAkhir"));
    			String defaulTahunh = getParam("txdTahunAkhir");
    			setAkhir(defaultBulanh,defaulTahunh);
    		}
//  			vecHash = getILaporan().getLaporanMengikutUrusanLike("7,17,18",null,"L","Negeri");

    	} else if ("PilihUnit".equals(submit)) {
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			String tempIdDaerah = getParam("socDaerahBaru").equals("")?"0":getParam("socDaerahBaru");
			String idKementerian = !getParam("socUnit").equals("-1")?getParam("socUnit"):"0";
			String idAgensi = !getParam("socDaerah").equals("-1")?getParam("socDaerah"):"0";
	        setSOC(tempIdNegeri,idStatus,tempIdDaerah,idAgensi,idKementerian);

	        if(!"".equals(getParam("txdMula"))){
    			defaultBulan = Long.parseLong(getParam("txdMula"));
    			defaulTahun = getParam("txdTahunMula");
    			tarikhMula = HTML.SelectBulan("txdMula",defaultBulan," style=\"width: 100px;\"");
    			tarikhMulaTahun = HTML.SelectTahun("txdTahunMula",defaulTahun," style=\"width: 100px;\"",null);
     		}
    		if(!"".equals(getParam("txdAkhir"))){
    			Long defaultBulanh = Long.parseLong(getParam("txdAkhir"));
    			String defaulTahunh = getParam("txdTahunAkhir");

    			setAkhir(defaultBulanh,defaulTahunh);
//    			tarikhAkhir = HTML.SelectBulan("txdAkhir",defaultBulanh," style=\"width: 100px;\"");
//    			tarikhAkhirTahun = HTML.SelectTahun("txdTahunAkhir",defaulTahunh," style=\"width: 100px;\"",null);
    		}
			paparanRekod(userLevel,userId);

    	} else if (submit.equals("pilihtempoh")) {
        	myLog.info("sorTempoh="+sorTempoh);
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			String tempIdDaerah = getParam("socDaerahBaru").equals("")?"0":getParam("socDaerahBaru");
			String idAgensi = !getParam("socDaerah").equals("-1")?getParam("socDaerah"):"0";
			String idKementerian = !getParam("socUnit").equals("-1")?getParam("socUnit"):"0";

			setSOC(tempIdNegeri,idStatus,tempIdDaerah,idAgensi,idKementerian);
//			setSOC(tempIdNegeri,idSuburusan,tempIdDaerah);

			if(sorTempoh.equals("1")) {
				tarikhMula = HTML.SelectBulan("txdAkhir",defaultBulan," style=\"width: 100px;\"");
				tarikhMulaTahun = HTML.SelectTahun("txdTahunMula",defaulTahun," style=\"width: 100px;\"",null);

			} else if(sorTempoh.equals("2")) {
				tarikhMulaTahun = HTML.SelectTahun("txdAkhir",defaulTahun," style=\"width: 100px;\"",null);

			} else if(sorTempoh.equals("3")) {
				tarikhMula = HTML.SelectBulan("txdMula",defaultBulan," style=\"width: 100px;\"");
    			tarikhMulaTahun = HTML.SelectTahun("txdTahunMula",defaulTahun," style=\"width: 100px;\"",null);
    			tarikhAkhir = HTML.SelectBulan("txdAkhir",defaultBulan," style=\"width: 100px;\"");
    			tarikhAkhirTahun = HTML.SelectTahun("txdTahunAkhir",defaulTahun," style=\"width: 100px;\"",null);
			}

    	} else{
    			paparanRekod(userLevel,userId);

//    		}
    	}

    	String sorLaporan = getParam("sorLaporan");
    	myLog.info("sorLaporan="+sorLaporan);
    	String checkA = "";
    	String checkB = "";
    	String checkC = "";
    	String checkD = "";
    	if(sorLaporan.equals("1")){
			//checked laporan
    		checkA = "checked";
		}else if(sorLaporan.equals("2")){
			//checked
    		checkB = "checked";
		}else if(sorLaporan.equals("3")){
			//checked
    		checkC = "checked";
		}else if(sorLaporan.equals("4")){
			//checked
    		checkD = "checked";
    	}
		context.put("checkA",checkA);
		context.put("checkB",checkB);
		context.put("checkC",checkC);
		context.put("checkD",checkD);

		//
    	if(sorTempoh.equals("1")){
    		checkBulan = "checked";
		}else if(sorTempoh.equals("2")){
			checkTahun = "checked";
		}else if(sorTempoh.equals("3")){
			checkTempoh = "checked";
		}

		context.put("checkBulan",checkBulan);
		context.put("checkTahun",checkTahun);
		context.put("checkTempoh",checkTempoh);

		context.put("senaraiLaporan",vecHash);
		context.put("senaraiRekod",vecRekod);

   		//dropdown
		context.put("selectStatus",socStatus);
		context.put("selectNegeri",socNegeri);
		context.put("selectKementerian",socUnit);
		context.put("selectAgensi",socAgensi);
		context.put("selectDaerahBaru",socDaerahBaru);
//		this.context.put("txdMula", tarikhMula);
//		this.context.put("txdAkhir", tarikhAkhir);

		context.put("socTarikhMula",tarikhMula);
		context.put("socTarikhTamat",tarikhAkhir);
		context.put("socTahunMula",tarikhMulaTahun);
		context.put("socTahunTamat",tarikhAkhirTahun);

		return vm;

	}

	public void setAkhir(Long defaultBulanh,String defaulTahunh) throws Exception{
		if(sorTempoh.equals("1")) {
			tarikhMula = HTML.SelectBulan("txdAkhir",defaultBulanh," style=\"width: 100px;\"");
			tarikhMulaTahun = HTML.SelectTahun("txdTahunMula",defaulTahunh," style=\"width: 100px;\"",null);

		} else if(sorTempoh.equals("2")) {
			tarikhMulaTahun = HTML.SelectTahun("txdAkhir",defaulTahunh," style=\"width: 100px;\"",null);

		} else if(sorTempoh.equals("3")) {
			tarikhAkhir = HTML.SelectBulan("txdAkhir",defaultBulanh," style=\"width: 100px;\"");
			tarikhAkhirTahun = HTML.SelectTahun("txdTahunAkhir",defaulTahunh," style=\"width: 100px;\"",null);

		}
	}

	public void setSOC(Long tempIdNegeri,String idStatus) throws Exception{
		socStatus = UtilHTML.selectStatusLaporanPenyewaan("4", "socStatus" ,idStatus, "", "");
		displayNegeri(tempIdNegeri);
		socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri),"socDaerahBaru");

		socUnit = UtilHTML.selectKementerianLaporanPemantauan("socUnit", Long.parseLong(getParam("socUnit")==""?"-1":getParam("socUnit")), null, "onChange=\"doChangeKementerian()\" style=\"width:400\"");
		socAgensi = UtilHTML.SelectAgensiLaporanPemantauan("socDaerah","-1","-1"," style=\"width:400\"","");

	}

	public void setSOC(Long tempIdNegeri, String idStatus, String idDaerah) throws Exception{
		Long tempIdDaerah = Long.parseLong(idDaerah);
		String idKementerian = getParam("socUnit")==""?"-1":getParam("socUnit");
		String idAgen = getParam("socDaerah")==""?"-1":getParam("socDaerah");

		socStatus = UtilHTML.selectStatusLaporanPenyewaan("4", "socStatus" ,idStatus, "", "");
		socNegeri = UtilHTML.selectNegeriLaporanPemantauan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri),"socDaerahBaru",tempIdDaerah, "");

		socUnit = UtilHTML.selectKementerianLaporanPemantauan("socUnit", Long.parseLong(idKementerian), null, "onChange=\"doChangeKementerian()\" style=\"width:400\"");
		socAgensi = UtilHTML.SelectAgensiLaporanPemantauan("socDaerah",idKementerian,Long.parseLong(idAgen)," style=\"width:400\"","");
	}

	public void setSOC(Long tempIdNegeri, String idStatus, String idDaerah, String idAgen) throws Exception{
		Long tempIdDaerah = Long.parseLong(idDaerah);
		String idKementerian = getParam("socUnit")==""?"-1":getParam("socUnit");
		//String idAgen = getParam("socDaerah")==""?"-1":getParam("socDaerah");

		socStatus = UtilHTML.selectStatusLaporanPenyewaan("4", "socStatus" ,idStatus, "", "");

		displayNegeri(tempIdNegeri);

		socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri),"socDaerahBaru",tempIdDaerah, "");

		socUnit = UtilHTML.selectKementerianLaporanPemantauan("socUnit", Long.parseLong(getParam("socUnit")==""?"-1":getParam("socUnit")), null, "onChange=\"doChangeKementerian()\" style=\"width:400\"");
		socAgensi = UtilHTML.SelectAgensiLaporanPemantauan("socDaerah",idKementerian,Long.parseLong(idAgen)," style=\"width:400\"","");
	}

	public void setSOC(Long tempIdNegeri, String idStatus, String idDaerah, String idAgen,String idKementerian) throws Exception{
		Long tempIdDaerah = Long.parseLong(idDaerah);
		//String idKementerian = getParam("socUnit")==""?"-1":getParam("socUnit");
		//String idAgen = getParam("socDaerah")==""?"-1":getParam("socDaerah");

		socStatus = UtilHTML.selectStatusLaporanPenyewaan("4", "socStatus" ,idStatus, "", "");
		displayNegeri(tempIdNegeri);
		socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri),"socDaerahBaru",tempIdDaerah, "");

		socUnit = UtilHTML.selectKementerianLaporanPemantauan("socUnit", Long.parseLong(idKementerian), null, "");
		socAgensi = UtilHTML.SelectAgensiLaporanPemantauan("socDaerah",idKementerian,Long.parseLong(idAgen)," style=\"width:400\"","");

	}

	public void displayNegeri(Long tempIdNegeri) throws Exception{
		socNegeri = UtilHTML.selectNegeriLaporanPemantauan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
	}

	public void displayNegeriUnit(Long tempIdNegeri,Long idUnit) throws Exception{
		myLog.info(tempIdNegeri);
		socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",idUnit," style=width:340 "," onChange=\"doChangeUnit()\"",String.valueOf(tempIdNegeri));
	}

	public void displayNegeriUnitXNegeri(Long tempIdNegeri,Long idUnit) throws Exception{
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = HTML.SelectKementerian("socUnit",idUnit, null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
	}

	public void displayNegeriUnitAll(Long tempIdNegeri) throws Exception{
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"");
	}

	public void display(Long tempIdNegeri) throws Exception{
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"",""+tempIdNegeri);
	}

	private void paparanRekod(int userLevel,String login) throws Exception{
		vecHash = getILaporan().getLaporanMengikutUrusan("7,17,18",null,"L");

	}

	private IHtpLaporan getILaporan(){
		if(iLaporan==null){
			iLaporan = new HtpLaporanBean();
		}
		return iLaporan;

	}
}
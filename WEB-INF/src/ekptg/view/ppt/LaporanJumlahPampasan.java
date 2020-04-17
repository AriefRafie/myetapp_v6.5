package ekptg.view.ppt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPembatalanInternalData;

@SuppressWarnings("serial")
public class LaporanJumlahPampasan extends AjaxBasedModule{	
	
	static Logger myLogger = Logger.getLogger(LaporanPenarikanBalik.class);
	
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	
	@SuppressWarnings("unchecked")
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		
		Vector dataPejabatJKPTG = new Vector();
		dataPejabatJKPTG.clear();
		
		String vm = ""; 
		String mainpage = "app/ppt/LAPORAN/LaporanJumlahPampasan.jsp";
		String userIdNegeri = (String) session.getAttribute("_ekptg_user_negeri");
		
		myLogger.info(" userIdNegeri ::"+userIdNegeri);
		
		//default
		String jenisTempoh = "";
		String bulanSE = "";
		String tahunSE = "";
		
		//param
		String id_pejabat = "";
		String id_daerah = "";
		String bulan = lebah.util.Util.getDateTime(new Date(), "MM");
		String tahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
		
		String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("doChange".equals(submit)){
    		
    		id_pejabat = getParam("socPejabat"); 		
    		bulan = getParam("socBulan");
    		tahun = getParam("socTahun"); 
    		bulanSE = getParam("socBulanSehingga");
    		tahunSE = getParam("socTahunSehingga"); 
    	
    		jenisTempoh = getParam("sorJenisTempoh"); 
    			
    		String tempIdPejabat = getParam("id_pejabat");
    		if(tempIdPejabat.equals(id_pejabat)){
    			id_daerah = getParam("socDaerah");  
    		}else{
    			id_daerah = "000";
    		}

    		getAndSetDataLaporan(id_pejabat,id_daerah,bulan,tahun,bulanSE,tahunSE);
    		
    	}else{
    		
    		//default
    		id_daerah = "000";
    		jenisTempoh = "sehingga";
    		
			logic.setDataPejabatJKPTG(userIdNegeri);
	     	dataPejabatJKPTG = logic.getDataPejabatJKPTG();
	     	if(dataPejabatJKPTG.size()!=0){
	     		Hashtable dpj = (Hashtable)dataPejabatJKPTG.get(0);
	     		id_pejabat = (String)dpj.get("ID_PEJABATJKPTG");
	     	}			     		
	     	
    		//context.put("selectPejabat",SelectPejabatJKPTGLaporan("socPejabat",Utils.parseLong(id_pejabat),null,"id='socPejabat' style=width:auto onChange=\"doChange()\""));
	     	context.put("selectPejabat",SelectPejabatJKPTGLaporan("socPejabat",Utils.parseLong(userIdNegeri),null,"id='socPejabat' style=width:auto onChange=\"doChange()\""));
    		
	     	/*
    		if(id_pejabat!=""){
    			context.put("selectDaerah",HTML.SelectDaerahLaporanByIdPejabatJKPTG(id_pejabat,"socDaerah",null,null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
    		}else{
    			context.put("selectDaerah",HTML.SelectDaerahLaporan("socDaerah",null,null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
    		}*/
    		
    		context.put("selectBulan",HTML.SelectBulan("socBulan",Utils.parseLong(bulan),null,"id='socBulan' style=width:200px onChange=\"doChange()\""));
    		context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px onChange=\"doChange()\""));
    		
    		//tambahan untuk julat masa
    		context.put("selectBulanSehingga",HTML.SelectBulan("socBulanSehingga",Utils.parseLong(bulan),null,"id='socBulanSehingga' style=width:200px onChange=\"doChange()\""));
    		context.put("selectTahunSehingga",HTML.SelectTahun("socTahunSehingga",tahun,null,"id='socTahunSehingga' style=width:200px onChange=\"doChange()\""));
    		
    	}
    	
    	context.put("jenisTempoh", jenisTempoh);
    	context.put("id_pejabat",id_pejabat);
    	context.put("id_daerah",id_daerah);
    	context.put("bulan",bulan);
    	context.put("tahun",tahun);
    	context.put("bulanSE",bulanSE);
    	context.put("tahunSE",tahunSE);
    	vm = mainpage;
    	return vm;
     
		
	}// close doTemplate2
	
	
	public static String SelectPejabatJKPTGLaporan(String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=''>SILA PILIH PEJABAT</option>\n");
			Vector v = getPejabatJKPTGLaporan();
			Hashtable h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable();
				h = (Hashtable) v.get(i);
				if (h.get("ID_NEGERI").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("ID_NEGERI")
						+ ">" +h.get("nama_pejabat") + " - "
						+ h.get("jenis_pejabat") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}

	
	
	public static Vector getPejabatJKPTGLaporan() throws Exception {
		Db db = null;

		String sql =  " SELECT ID_NEGERI,ID_PEJABATJKPTG,NAMA_PEJABAT,";
			   sql += " CASE";
			   sql += " WHEN ID_JENISPEJABAT = '22' THEN 'UPT'";
			   sql += " WHEN ID_JENISPEJABAT = '21' THEN 'SPT'";
			   sql += " END AS JENIS_PEJABAT";
			   sql += " FROM TBLRUJPEJABATJKPTG";
			   sql += " WHERE ID_JENISPEJABAT IN ('22','21')";
			   sql += " AND ID_SEKSYEN = '1'";
			   
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_NEGERI",rs.getLong("ID_NEGERI"));
				h.put("id_pejabatjkptg",rs.getLong("ID_PEJABATJKPTG"));
				h.put("nama_pejabat",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("jenis_pejabat",rs.getString("JENIS_PEJABAT") == null ? "" : rs.getString("JENIS_PEJABAT").toUpperCase());
				v.addElement(h);
			}
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	private void getAndSetDataLaporan(String id_pejabat, String id_daerah, String bulan, String tahun, String bulanSE, String tahunSE) throws Exception{
		
		context.put("selectPejabat",HTML.SelectPejabatJKPTGLaporan("socPejabat",Utils.parseLong(id_pejabat),null,"id='socPejabat' style=width:auto onChange=\"doChange()\""));
		
		if(id_pejabat!=""){
			context.put("selectDaerah",HTML.SelectDaerahLaporanByIdPejabatJKPTG(id_pejabat,"socDaerah",Utils.parseLong(id_daerah),null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
		}else{
			context.put("selectDaerah",HTML.SelectDaerahLaporan("socDaerah",Utils.parseLong(id_daerah),null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
		}
		
		context.put("selectBulan",HTML.SelectBulan("socBulan",Utils.parseLong(bulan),null,"id='socBulan' style=width:200px onChange=\"doChange()\""));
		context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px onChange=\"doChange()\""));
		
		//tambahan untuk julat masa
		context.put("selectBulanSehingga",HTML.SelectBulan("socBulanSehingga",Utils.parseLong(bulanSE),null,"id='socBulanSehingga' style=width:200px onChange=\"doChange()\""));
		context.put("selectTahunSehingga",HTML.SelectTahun("socTahunSehingga",tahunSE,null,"id='socTahunSehingga' style=width:200px onChange=\"doChange()\""));
		
		
	}//close getAndSetDataLaporan
	

	 
	 
}// close class

package ekptg.view.ppk.laporan;

import java.util.Hashtable;
import java.util.Vector;

//import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.laporan.ILaporan;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.laporan.LaporanPrestasiPegawaiBean;
import ekptg.model.utils.IUserPegawai;
import ekptg.model.utils.UserPegawaiBean;

public class LaporanPrestasiPegawaiPPK extends AjaxBasedModule {
	
	//private final String PATH="app/htp/";
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppk.laporan.LaporanPrestasiPegawaiPPK.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<Hashtable<String,String>> permohonans = null;
	private IUserPegawai iPegawai = null;
	private ILaporan iLaporan = null;

	public String doTemplate2() throws Exception {
		//HttpSession session = this.request.getSession();
	    String tajuk ="";
	   	String data ="";
  		String submit = getParam("command_");
		int tempIdNegeri = getParamAsInteger("idNegeri");
		String idUnit = getParam("idUnit");
		String tahun = getParam("tahun");
		//String userId = (String)session.getAttribute("_portal_login");
		String vm = "app/ppk/laporan/prestasipegawai/index.jsp";
		//myLog.info("submit="+submit);
		//myLog.info("bulan="+getParam("bulan"));
		//myLog.info("command_="+getParam("command_"));
		String laporan ="LAPORAN PRESTASI PEGAWAI PADA TAHUN " + tahun;
		if(tempIdNegeri==0)
			laporan +=" KESELURUHAN";
		else
			laporan +=" BAGI " +PPKUtilData.getNamaUnitPPK(idUnit) +", "+ getNegeri(getParam("idNegeri"));
		
		tajuk = "<tr class=\"table_header\">";
	    	tajuk +="<td rowspan=\"2\" width=\"2%\">NO.</td>";
	    	tajuk +="<td rowspan=\"2\" width=\"20%\">NAMA PEGAWAI</td>";
	    	for (int i = 1; i < 13; i++) {
		    	tajuk +="<td  colspan=\"2\" width=\"6%\" align=\"center\">"+ekptg.report.Utils.getBulanRingkas(i)+"</td>";
	    	}		
	    	tajuk +="<td colspan=\"2\" width=\"6%\" align=\"center\">JUMLAH</td>";
	    	tajuk +="<td colspan=\"2\" width=\"6%\" align=\"center\">PURATA</td>";
	    	tajuk +="<td rowspan=\"2\" width=\"%6\">% SELESAI</td>";
	    	tajuk +="</tr>";
	    	
	    	tajuk += "<tr class=\"table_header\">";
	    	for (int i = 1; i < 13; i++) {
		    	tajuk +="<td width=\"3%\" align=\"center\">B</td>";
		    	tajuk +="<td width=\"3%\" align=\"center\">S</td>";
	    	}		
	    	tajuk +="<td width=\"3%\" align=\"center\">B</td>";
	    	tajuk +="<td width=\"3%\" align=\"center\">S</td>";
	    	tajuk +="<td width=\"3%\" align=\"center\">B</td>";
	    	tajuk +="<td width=\"3%\" align=\"center\">S</td>";
	    	tajuk +="</tr>";		
	    	data = getData(tempIdNegeri,idUnit,tahun);
			
		if(submit.equals("terperinci")){
			String idPegawai = getParam("idUser");
			String bulan = getParam("bulan");
			permohonans = getILaporan().getSenarai(String.valueOf(tempIdNegeri), idUnit,idPegawai, tahun, bulan);
		    
	    }else if (submit.equals("selesai")){
			String idPegawai = getParam("idUser");
			String bulan = getParam("bulan");
		    permohonans = getILaporan().getSenaraiSelesai(String.valueOf(tempIdNegeri), idUnit,idPegawai, tahun, bulan);
			
	    }
	    context.put("laporan", laporan);	    
	    context.put("header", tajuk);	    
	   	context.put("data", data);	    
	    context.put("ListPermohonan", permohonans);
		return vm;

	}
	
	private String getData(int idNegeri,String idUnit,String tahun) throws Exception{
		String tajuk="";
		Vector<Hashtable<String,String>> vPegawai = getIPegawai().getSenaraiPegawai(idNegeri, idUnit, tahun);
    	Hashtable<String,String> h = null;
   	
		for (int i = 0; i < vPegawai.size(); i++) {
			h = (Hashtable<String,String>)vPegawai.get(i);
			String idPegawai = String.valueOf(h.get("idPegawai"));
	    	int jumlah = 0;
	    	int jumlahSelesai = 0;
	 
			tajuk +="<tr>";
			tajuk +="<th width=\"2%\">"+(i+1)+"</th>";
			tajuk +="<td width=\"20%\">"+ h.get("nama") +"</td>";
	    	for (int ii = 1; ii < 13; ii++) {
	    		String bulanMM = ekptg.report.Utils.getBulanNum(ii);
	    		int bil = getILaporan().getBilPerbicaraan(String.valueOf(idNegeri), idUnit, idPegawai, tahun, bulanMM);
	    		int bilSelesai = getILaporan().getBilPerbicaraanSelesai(
	    						String.valueOf(idNegeri), idUnit,idPegawai, tahun, bulanMM);
		    	tajuk +="<td width=\"3%\" align=\"center\">";
		    	if(bil==0)
		    		tajuk += String.valueOf(bil);
		    	else	
		    		tajuk += "<a href=\"javascript:terperinci('"+idNegeri+"','"+idUnit+"','"+tahun+"','"+bulanMM+"','"+idPegawai+"');\" class=\"style1\"> "+bil+"</a>";
		    	
		    	tajuk += "</td>";
		    	tajuk +="<td width=\"3%\" align=\"center\">";
		    	if(bilSelesai==0)
		    		tajuk += String.valueOf(bilSelesai);
		    	else	
		    		tajuk +="<a href=\"javascript:terperinciSelesai('"+idNegeri+"','"+idUnit+"','"+tahun+"','"+bulanMM+"','"+idPegawai+"');\" class=\"style1\"> "+bilSelesai+"</a>";
		    	
		    	tajuk +="</td>";
		    	jumlah += bil;
		    	jumlahSelesai += bilSelesai;
	    	}		
	    	tajuk +="<td width=\"3%\" align=\"center\">"+jumlah+"</td>";
	    	tajuk +="<td width=\"3%\" align=\"center\">"+jumlahSelesai+"</td>";
	    	tajuk +="<td width=\"3%\" align=\"center\">"+(jumlah==0?0:(jumlah/12))+"</td>";
	    	tajuk +="<td width=\"3%\" align=\"center\">"+(jumlahSelesai==0?0:(jumlahSelesai/12))+"</td>";
	    	Double perc = (Double.valueOf(jumlahSelesai)/Double.valueOf(jumlah))*100;
	    	tajuk +="<td width=\"%6\">"+( jumlah==0 && jumlahSelesai==0?0:ekptg.helpers.Utils.format2Decimal(perc))+"</td>";
			tajuk +="</tr>";
    	
		}
    	return tajuk;
    	
	}
	
	private String getNegeri(String idNegeri) throws Exception{
			Vector<Tblrujnegeri> negeri = DB.getNegeri(idNegeri);
			return negeri.get(0).getNamaNegeri();
	}
	
	private IUserPegawai getIPegawai(){
		if(iPegawai==null){
			iPegawai = new UserPegawaiBean();
		}
		return iPegawai;
			
	}
	private ILaporan getILaporan(){
		if(iLaporan==null){
			iLaporan = new LaporanPrestasiPegawaiBean();
		}
		return iLaporan;
			
	}
	

}

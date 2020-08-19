
package ekptg.scheduler;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import lebah.db.Db;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.pajakan.HTPajakanPeringatanBean;
import ekptg.model.php2.utiliti.PHPAPBPeringatanBean;
import ekptg.model.utils.IPeringatan;
import ekptg.model.utils.List;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.view.php2.util.UtilHasil;

public class ModulHTPNotifikasi {
		
	static SimpleDateFormat dfd = new SimpleDateFormat("dd");
	static SimpleDateFormat dfy = new SimpleDateFormat("yyyy");
	static SimpleDateFormat dfm = new SimpleDateFormat("MM");
	private static ICukai iCukai = null;
	private static IPeringatan iPer = null;
	private static IPeringatan iPerapb = null;

	private static String tahunSemasa = "";
	private static String bulanSemasa = "";
	private static String kandungan = "";
	private static String tarikhDaftar = "";
	private static String hari = "";
	private static String emel = "roslizakariasip@gmail.com";
/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("START JOB ON " + new Date());
//		tahunSemasa = dfy.format(new Date());
//		bulanSemasa = dfm.format(new Date());
		//System.out.println("Tahun Semasa = " + dfy.format(new Date()));
		//System.out.println("Bulan Semasa= " + dfm.format(new Date()));		
		//Tahun Semasa 122020
		try {
			if(args[0] != null) {
				hari = args[0].substring(0,2);
				String bulan = args[0].substring(2,4);
				String tahun = args[0].substring(4,8);
				System.out.println("Tahun= " + tahun);
				System.out.println("Bulan= " + bulan);
				System.out.println("Hari= " + hari);
				if(args[1] != null) {
					emel = args[1];
				}				
				submodulCukaiPeringatan(tahun,bulan,emel);
				submodulPajakanPeringatan(tahun,bulan,emel);
					
				// Notifikasi Ulasan
				submodulAPB(tahun,bulan,emel,"",0);
				// Notifikasi Keputusan Dasar(D)
				submodulAPBKeputusan(tahun,bulan,emel,"TBLPHPPMOHONNJDUALPERTAMA",40);

			}else {
				hari = dfd.format(new Date());
				tahunSemasa = dfy.format(new Date());
				bulanSemasa = dfm.format(new Date());
				submodulCukaiPeringatan(tahunSemasa,bulanSemasa,emel);
				submodulPajakanPeringatan(tahunSemasa,bulanSemasa,emel);
				
				// Notifikasi Ulasan
				submodulAPB(tahunSemasa,bulanSemasa,emel,"",0);
				// Notifikasi Keputusan Dasar(D)
				submodulAPBKeputusan(tahunSemasa,bulanSemasa,emel,"TBLPHPPMOHONNJDUALPERTAMA",40);

			}
		}catch(Exception e) {				
			System.out.println("submodulCukaiPeringat:err::" + e.getStackTrace() );
		}
			
		System.out.println("FINISH JOB ON " + new Date());
	
	}
	
	private static void submodulAPBKeputusan(String tahun,String bulan,String emel,String tab,int bilHari)  throws Exception{		
		Vector apbSenarai = getIPeringatanAPB().getSenaraiPeringatanHari("", "3", tab, hari+"/"+bulan, bilHari);
//		System.out.println("submodulPajakanPeringatan 0"+paj.size());

		if(apbSenarai.size() != 0) {
			System.out.println("submodulAPB");
			EmailConfig ec = new EmailConfig();
			String emelSubjek = "Peringatan Submodul APB (Keputusan Dasar)";
			kandungan = EmailConfig.getHeader();
			
			String mp = "";
			for(int i = 0; i < apbSenarai.size();i++){			   
				Hashtable<String,String> m = (Hashtable<String,String>)apbSenarai.get(i);
				mp += (i+1)+"."+m.get("idPermohonan") +"("+ m.get("noFail")+")<br/>";
		    	//hash.put("tujuan",rs.getString("TUJUAN"));
			}			
			kandungan += "Diminta membuat persediaan maklumat bagi tujuan tindakan susulan.<br/>"+mp;
			System.out.println("submodulAPB:02"+kandungan);

			ec.sendTo(emel, emelSubjek, kandungan);

		}
		
	}
	
	private static void submodulAPB(String tahun,String bulan,String emel,String tab,int bilHari)  throws Exception{		
		Vector apbSenarai = getIPeringatanAPB().getSenaraiPeringatanHari("", "3", "", hari+"/"+bulan, bilHari);
//		System.out.println("submodulPajakanPeringatan 0"+paj.size());

		if(apbSenarai.size() != 0) {
			System.out.println("submodulAPB");
			EmailConfig ec = new EmailConfig();
			String emelSubjek = "Peringatan Submodul APB";
			kandungan = EmailConfig.getHeader();
			
			String mp = "";
			for(int i = 0; i < apbSenarai.size();i++){			   
				Hashtable<String,String> m = (Hashtable<String,String>)apbSenarai.get(i);
				mp += (i+1)+"."+m.get("idPermohonan") +"("+ m.get("noFail")+")<br/>";
		    	//hash.put("tujuan",rs.getString("TUJUAN"));
			}			
			kandungan += "Diminta membuat persediaan maklumat bagi tujuan tindakan susulan.<br/>"+mp;
			System.out.println("submodulAPB:02"+kandungan);

			ec.sendTo(emel, emelSubjek, kandungan);

		}
		
	}
	
	private static void submodulPajakanPeringatan(String tahun,String bulan,String emel)  throws Exception{
		
		Vector<Hashtable<String,String>> paj = getIPeringatan().getSenaraiPeringatanHari("", "3", "", hari+"/"+bulan, 30);
		System.out.println("submodulPajakanPeringatan 0"+paj.size());

		if(paj.size() != 0) {
			System.out.println("submodulPajakanPeringatan");
			EmailConfig ec = new EmailConfig();
			String emelSubjek = "Peringatan Submodul Pajakan";
			kandungan = EmailConfig.getHeader();
			
			String mp = "";
			for(int i = 0; i < paj.size();i++){			   
				Hashtable<String,String> m = paj.get(i);
				mp += (i+1)+"."+m.get("nama") +"("+ m.get("noFail")+")<br/>";
		    	//hash.put("tujuan",rs.getString("TUJUAN"));
			}			
			kandungan += "Diminta membuat persediaan maklumat bagi tujuan pembayaran Pajakan.<br/>"+mp;

			System.out.println("submodulPajakanPeringatan:03"+kandungan);

			ec.sendTo(emel, emelSubjek, kandungan);

		}
		
	}
	
	private static void submodulCukaiPeringatan(String tahun,String bulan,String emel)  throws Exception{
		String tahunDepan = String.valueOf((Integer.parseInt(tahun)+1));
		System.out.println("vecCukaiSenarai:01::tahunDepan="+tahunDepan);
		if(tahun.equals(tahunSemasa) && bulan.equals("12")) {
			Vector<Hashtable<String,String>> vecCukaiSenarai = getICukai().getSenaraiNegeriXPenyata(tahun);
			String negeri = "";
			for(int i = 0; i < vecCukaiSenarai.size();i++){			   
				Hashtable<String,String> m = vecCukaiSenarai.get(i);
//				if(i==1)
//					negeri = i+"."+m.get("nama");
//				else
				negeri += i+"."+m.get("nama")+"<br/>";

			}
			if(vecCukaiSenarai.size() != 0) {
				System.out.println("vecCukaiSenarai:02");
				EmailConfig ec = new EmailConfig();
				String emelSubjek = "Peringatan Submodul Pengurusan Cukai";
				kandungan = EmailConfig.getHeader();
				kandungan += "Diminta membuat persediaan maklumat cukai bagi negeri yang terlibat.<br/>"+negeri;
				System.out.println("vecCukaiSenarai:03"+kandungan);

				ec.sendTo(emel, emelSubjek, kandungan);

			}
		}
//		SimpleDateFormat day = new SimpleDateFormat("dd");

		if(getTarikhDaftar(hari+"/"+bulan)) {
			System.out.println("getTarikhDaftar:04");
			java.util.List<Hashtable<String,String>> senaraiTanah = getHakmilikSS(tarikhDaftar,tahun);
			
			if(senaraiTanah.size()!=0) {
				String tanah="";
				for(int i = 0; i < senaraiTanah.size();i++){			   
					Hashtable<String,String> m = senaraiTanah.get(i);
					tanah += m.get("tanah") +"<br/>";
				}
				EmailConfig ec = new EmailConfig();
				String emelSubjek = "Peringatan Submodul Pengurusan Cukai";
				kandungan = EmailConfig.getHeader();
				kandungan += "Diminta membuat persediaan maklumat cukai bagi Hakmilik yang terlibat.<br/>"+tanah;
	//			System.out.println("vecCukaiSenarai:03"+kandungan);
	
				ec.sendTo(emel, emelSubjek, kandungan);
			}
		}

	
	}

	public static boolean getTarikhDaftar(String hbDaftar) throws Exception {
		boolean returnVal = false;
		Db db = null;
		String sql = "SELECT DISTINCT TO_CHAR(TARIKH_DAFTAR,'dd/MM') TARIKH_DAFTAR  " +
		   			 " FROM TBLHTPHAKMILIK " +
		   			 " WHERE " +
//		   			 "ID_NEGERI IN (12,13) AND " +
		  			 " TO_CHAR(TARIKH_DAFTAR,'dd/MM') = '"+hbDaftar+"' "+
		   			 "";
		try {
			db = new Db();
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      while (rs.next()) {
		    	  tarikhDaftar = rs.getString("TARIKH_DAFTAR");
		    	  returnVal = true;
		      }		      

		} finally {
		      if (db != null) db.close();
		}
		return returnVal;
		
	}	
	
	private static java.util.List<Hashtable<String,String>> getHakmilikSS(String hbDaftar,String tahunSemasa) {
		Db db = null;
		String sql = "";
		java.util.List<Hashtable<String,String>> senaraiHakmilik = null;
		//List result = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//result = new ArrayList();
	
			sql = "SELECT RL.KETERANGAN,T.NO_LOT,RJ.KOD_JENIS_HAKMILIK,T.NO_HAKMILIK,T.TARIKH_DAFTAR "
					+ " FROM TBLHTPHAKMILIK T,TBLRUJLOT RL,TBLRUJJENISHAKMILIK RJ "
					+ " WHERE "
					+ " T.ID_NEGERI||T.ID_DAERAH||T.ID_MUKIM||T.ID_LOT||T.NO_LOT||T.ID_JENISHAKMILIK||T.NO_HAKMILIK||NVl(T.NO_BANGUNAN,0)||NVl(T.NO_TINGKAT,0)||NVl(T.NO_PETAK,0)"
					+ " IN ( "
					+"SELECT " 
					+"C.ID_NEGERI||C.ID_DAERAH||C.ID_MUKIM||C.ID_LOT||C.NO_LOT||C.ID_JENISHAKMILIK||C.NO_HAKMILIK "
					+"||C.NO_BANGUNAN||C.NO_TINGKAT||C.NO_PETAK  "
					+"FROM TBLHTPCUKAITEMP C,TBLHTPHAKMILIK T  "
					+"WHERE "
					+"C.ID_NEGERI||C.ID_DAERAH||C.ID_MUKIM||C.ID_LOT||C.NO_LOT||C.ID_JENISHAKMILIK||C.NO_HAKMILIK||C.NO_BANGUNAN||C.NO_TINGKAT||C.NO_PETAK "
					+"=T.ID_NEGERI||T.ID_DAERAH||T.ID_MUKIM||T.ID_LOT||T.NO_LOT||T.ID_JENISHAKMILIK||T.NO_HAKMILIK||NVl(T.NO_BANGUNAN,0)||NVl(T.NO_TINGKAT,0)||NVl(C.NO_PETAK,0) "
					+ " AND TO_CHAR(T.TARIKH_DAFTAR,'DD/MM')='"+hbDaftar+"'"
					+ " AND C.TAHUN ='"+tahunSemasa+"'"
					+ ")"
					+" AND T.ID_LOT = RL.ID_LOT "
					+" AND T.ID_JENISHAKMILIK = RJ.ID_JENISHAKMILIK "
//					+ "AND T.ID_NEGERI IN (12,13) " 
					+""
				+ "ORDER BY T."
				+ "TARIKH_TERIMA DESC";
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			Hashtable<String,String> h = null;
			senaraiHakmilik = new ArrayList<Hashtable<String, String>>();

			while (rs.next()) {
				h = new Hashtable<String,String>();
				i++;
				String tanah = rs.getString(1)+rs.getString(2) +"-"+ rs.getString(3)+rs.getString(4);
				h.put("tanah", i+"."+tanah);
				System.out.println(i + " - " + tanah);
				senaraiHakmilik.add(h);
				//result.add(h);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}		
		return senaraiHakmilik;
		
	}
	
	private static ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	
	private static IPeringatan getIPeringatan(){
		if(iPer==null){
			iPer = new HTPajakanPeringatanBean();
		}
		return iPer;
		
	}
	
	private static IPeringatan getIPeringatanAPB(){
		if(iPerapb==null){
			iPerapb = new PHPAPBPeringatanBean();
		}
		return iPerapb;
		
	}


}

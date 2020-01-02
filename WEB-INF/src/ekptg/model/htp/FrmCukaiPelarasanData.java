package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmCukaiPelarasanData {
	
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Vector getListCukai(Long idCukaiTerperinci, Long noHakmilik, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim)throws Exception {
 	    Db db = null;
 	    String sql = "";
 	    String year = lebah.util.Util.getDateTime(new Date(), "yyyy");
 	    
 	   if (idCukaiTerperinci == 0){
 		  idCukaiTerperinci = null;
	   }
 	     	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();

 	      	sql = "SELECT thh.id_negeri, thh.id_daerah, thh.id_mukim, thh.id_jenishakmilik, thhc.id_hakmilikcukai, ";
 	      	sql +=" thh.no_hakmilik, thh.id_hakmilik, thct.id_cukaiterperinci, thct.no_resit, thct.tarikh_resit,";// thct.tujuan, ";
 	      	sql +=" thct.tahun, thct.tunggakan, thct.cukai_taliair, thct.cukai_parit, thct.denda, thct.bayaran_lain, ";
 	      	sql +=" thct.cukai_kena_bayar, thct.cukai_dibayar, thct.cukai_perlu_bayar, thct.pengurangan, thct.pengecualian ";
 	      	sql +="FROM tblhtphakmilik thh, tblhtphakmilikcukai thhc, tblhtpcukaiterperinci thct ";
 	      	sql +="WHERE thh.id_hakmilik = thhc.id_hakmilik ";
 	      	sql +=" AND thct.id_hakmilikcukai(+) = thhc.id_hakmilikcukai ";
 	      	sql +=" AND thh.no_hakmilik = "+noHakmilik;
 	      	sql +=" AND thh.id_jenishakmilik = "+idJenisHakmilik;
 	      	sql +=" AND thh.id_negeri = "+idNegeri;
 	      	sql +=" AND thh.id_daerah = "+idDaerah;
 	      	sql +=" AND thh.id_mukim = "+idMukim;
 	     
 	      if(idCukaiTerperinci != null){
 	      	sql +=" AND thct.id_cukaiterperinci = "+idCukaiTerperinci;
 	      } 	
 	    	
 	     System.out.println("FrmCukaiKemaskiniData::getListCukai::sql::"+sql);	
 	     ResultSet rs = stmt.executeQuery(sql);
 	      
 	     Vector list = new Vector();
 	     Hashtable h;
 	     int bil = 1;
 	      
 	     while (rs.next()) {
 	    	  h = new Hashtable();
 	    	  h.put("bil", bil);
 	    	  
 	    	  if(rs.getString("id_negeri") != null){
	    		  h.put("idNegeri", rs.getString("id_negeri"));
	    	  }else{
	    		  h.put("idNegeri", "0");
	    	  }
 	    	  if(rs.getString("id_daerah") != null){
	    		  h.put("idDaerah", rs.getString("id_daerah"));
	    	  }else{
	    		  h.put("idDaerah", "0");
	    	  }
 	    	  if(rs.getString("id_mukim") != null){
	    		  h.put("idMukim", rs.getString("id_mukim"));
	    	  }else{
	    		  h.put("idMukim", "0");
	    	  }
 	    	  if(rs.getString("id_jenishakmilik") != null){
	    		  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
	    	  }else{
	    		  h.put("idJenisHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilikcukai") != null){
	    		  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
	    	  }else{
	    		  h.put("idHakmilikCukai", "0");
	    	  }
 	    	  if(rs.getString("no_hakmilik") != null){
	    		  h.put("noHakmilik", rs.getString("no_hakmilik"));
	    	  }else{
	    		  h.put("noHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilik") != null){
	    		  h.put("idHakmilik", rs.getString("id_hakmilik"));
	    	  }else{
	    		  h.put("idHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_cukaiterperinci") != null){
	    		  h.put("idCukaiTerperinci", rs.getString("id_cukaiterperinci"));
	    	  }else{
	    		  h.put("idCukaiTerperinci", "");
	    	  }
 	    	  if(rs.getString("no_resit") != null){
	    		  h.put("noResit", rs.getString("no_resit"));
	    	  }else{
	    		  h.put("noResit", "");
	    	  }
// 	    	  if(rs.getString("tujuan") != null){
//	    		  h.put("catatan", rs.getString("tujuan"));
//	    	  }else{
//	    		  h.put("catatan", "");
//	    	  }
 	    	  if(rs.getString("tarikh_resit") != null){
	    		  h.put("tarikhResit", Format.format(rs.getDate("tarikh_resit")));
	    	  }else{
	    		  h.put("tarikhResit", "DD/MM/YYYY");
	    	  }
 	    	  if(rs.getString("tahun") != null){
	    		  h.put("tahun", rs.getString("tahun"));
	    	  }else{
	    		  h.put("tahun", year);
	    	  }
 	    	  h.put("tunggakan", rs.getString("tunggakan") == null || "0".equals(rs.getString("tunggakan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("tunggakan")));
 	    	  h.put("cukaiTaliAir", rs.getString("cukai_taliair") == null || "0".equals(rs.getString("cukai_taliair").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_taliair")));
 	    	  h.put("cukaiParit", rs.getString("cukai_parit") == null || "0".equals(rs.getString("cukai_parit").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_parit")));
 	    	  h.put("denda", rs.getString("denda") == null || "0".equals(rs.getString("denda").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("denda")));
 	    	  h.put("bayaranLain", rs.getString("bayaran_lain") == null || "0".equals(rs.getString("bayaran_lain").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("bayaran_lain")));
 	    	  h.put("cukaiTahunan", rs.getString("cukai_kena_bayar") == null || "0".equals(rs.getString("cukai_kena_bayar").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_kena_bayar")));
 	    	  h.put("jumlahBayaran", rs.getString("cukai_dibayar") == null || "0".equals(rs.getString("cukai_dibayar").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_dibayar")));
 	    	  h.put("amaunResit", rs.getString("cukai_perlu_bayar") == null || "0".equals(rs.getString("cukai_perlu_bayar").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_perlu_bayar")));
 	    	  h.put("pengurangan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengurangan")));
 	    	  h.put("pengecualian", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengecualian")));
	    	 
 	    	 Double d = 0.0;
 	    	 if(rs.getString("pengurangan") != null || rs.getString("pengecualian") != null){
 	    		Double c;
	    		Double a = Double.parseDouble(rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":rs.getString("pengurangan"));
	    		Double b = Double.parseDouble(rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":rs.getString("pengecualian"));			    		  
	    		System.out.println("::a:::"+a);
	    		System.out.println("::b:::"+b);
 	    		
	    		c = a+b;
	    		if(c!=0){
	    			h.put("bezaBayaran", Util.formatDecimal(c));
	    		}else {
	    			h.put("bezaBayaran", "0"+Util.formatDecimal(d));	
	    		}
 	    	 }else{
	    		  h.put("bezaBayaran", "0"+Util.formatDecimal(d));
	    	  }    	
 	    	  list.addElement(h);
 	    	  bil++;
 	      }
 	     return list;
 	    } finally {
 	      if (db != null) db.close();
 	    }
 	}
	
	public static Vector getListPCukai(Long idCukaiTerperinci, Long noHakmilik, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim)throws Exception {
 	    Db db = null;
 	    String sql = "";
 	    String year = lebah.util.Util.getDateTime(new Date(), "yyyy");
 	    
 	   if (idCukaiTerperinci == 0){
 		  idCukaiTerperinci = null;
	   }
 	     	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();

 	      	sql = "SELECT thh.id_negeri, thh.id_daerah, thh.id_mukim, thh.id_jenishakmilik, thhc.id_hakmilikcukai, ";
 	      	sql +=" thh.no_hakmilik, thh.id_hakmilik, thct.id_cukaiterperinci, thct.no_resit, thct.tarikh_resit,"; //thct.tujuan, ";
 	      	sql +=" thct.tahun, thct.tunggakan, thct.cukai_taliair, thct.cukai_parit, thct.denda, thct.bayaran_lain, ";
 	      	sql +=" thct.cukai_kena_bayar, thct.cukai_dibayar, thct.cukai_perlu_bayar, thct.pengurangan, thct.pengecualian ";
 	      	sql +="FROM tblhtphakmilik thh, tblhtphakmilikcukai thhc, tblhtpcukaiterperinci thct ";
 	      	sql +="WHERE thh.id_hakmilik = thhc.id_hakmilik ";
 	      	sql +=" AND thct.id_hakmilikcukai(+) = thhc.id_hakmilikcukai ";
 	      	sql +=" AND thh.no_hakmilik = "+noHakmilik;
 	      	sql +=" AND thh.id_jenishakmilik = "+idJenisHakmilik;
 	      	sql +=" AND thh.id_negeri = "+idNegeri;
 	      	sql +=" AND thh.id_daerah = "+idDaerah;
 	      	sql +=" AND thh.id_mukim = "+idMukim;
 	     
 	      if(idCukaiTerperinci != null){
 	      	sql +=" AND thct.id_cukaiterperinci = "+idCukaiTerperinci;
 	      } 	
 	    	
 	     System.out.println("FrmCukaiKemaskiniData::getListKCukai::sql::"+sql);	
 	     ResultSet rs = stmt.executeQuery(sql);
 	      
 	     Vector list = new Vector();
 	     Hashtable h;
 	     int bil = 1;
 	      
 	     while (rs.next()) {
 	    	  h = new Hashtable();
 	    	  h.put("bil", bil);
 	    	  
 	    	  if(rs.getString("id_negeri") != null){
	    		  h.put("idNegeri", rs.getString("id_negeri"));
	    	  }else{
	    		  h.put("idNegeri", "0");
	    	  }
 	    	  if(rs.getString("id_daerah") != null){
	    		  h.put("idDaerah", rs.getString("id_daerah"));
	    	  }else{
	    		  h.put("idDaerah", "0");
	    	  }
 	    	  if(rs.getString("id_mukim") != null){
	    		  h.put("idMukim", rs.getString("id_mukim"));
	    	  }else{
	    		  h.put("idMukim", "0");
	    	  }
 	    	  if(rs.getString("id_jenishakmilik") != null){
	    		  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
	    	  }else{
	    		  h.put("idJenisHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilikcukai") != null){
	    		  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
	    	  }else{
	    		  h.put("idHakmilikCukai", "0");
	    	  }
 	    	  if(rs.getString("no_hakmilik") != null){
	    		  h.put("noHakmilik", rs.getString("no_hakmilik"));
	    	  }else{
	    		  h.put("noHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilik") != null){
	    		  h.put("idHakmilik", rs.getString("id_hakmilik"));
	    	  }else{
	    		  h.put("idHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_cukaiterperinci") != null){
	    		  h.put("idCukaiTerperinci", rs.getString("id_cukaiterperinci"));
	    	  }else{
	    		  h.put("idCukaiTerperinci", "");
	    	  }
 	    	  if(rs.getString("no_resit") != null){
	    		  h.put("noResit", rs.getString("no_resit"));
	    	  }else{
	    		  h.put("noResit", "");
	    	  }
// 	    	  if(rs.getString("tujuan") != null){
//	    		  h.put("catatan", rs.getString("tujuan"));
//	    	  }else{
//	    		  h.put("catatan", "");
//	    	  }
 	    	  if(rs.getString("tarikh_resit") != null){
	    		  h.put("tarikhResit", Format.format(rs.getDate("tarikh_resit")));
	    	  }else{
	    		  h.put("tarikhResit", "DD/MM/YYYY");
	    	  }
 	    	  if(rs.getString("tahun") != null){
	    		  h.put("tahun", rs.getString("tahun"));
	    	  }else{
	    		  h.put("tahun", year);
	    	  }
 	    	  h.put("tunggakan", rs.getString("tunggakan") == null || "0".equals(rs.getString("tunggakan").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("tunggakan"))));
 	    	  h.put("cukaiTaliAir", rs.getString("cukai_taliair") == null || "0".equals(rs.getString("cukai_taliair").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai_taliair"))));
 	    	  h.put("cukaiParit", rs.getString("cukai_parit") == null || "0".equals(rs.getString("cukai_parit").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai_parit"))));
 	    	  h.put("denda", rs.getString("denda") == null || "0".equals(rs.getString("denda").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("denda"))));
 	    	  h.put("bayaranLain", rs.getString("bayaran_lain") == null || "0".equals(rs.getString("bayaran_lain").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("bayaran_lain"))));
 	    	  h.put("cukaiTahunan", rs.getString("cukai_kena_bayar") == null || "0".equals(rs.getString("cukai_kena_bayar").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai_kena_bayar"))));
 	    	  h.put("jumlahBayaran", rs.getString("cukai_dibayar") == null || "0".equals(rs.getString("cukai_dibayar").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai_dibayar"))));
 	    	  h.put("amaunResit", rs.getString("cukai_perlu_bayar") == null || "0".equals(rs.getString("cukai_perlu_bayar").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai_perlu_bayar"))));
 	    	  h.put("pengurangan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("pengurangan"))));
 	    	  h.put("pengecualian", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("pengecualian"))));
	    	 
 	    	 Double d = 0.0;
 	    	 if(rs.getString("pengurangan") != null || rs.getString("pengecualian") != null){
 	    		Double c;
	    		Double a = Double.parseDouble(rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":rs.getString("pengurangan"));
	    		Double b = Double.parseDouble(rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":rs.getString("pengecualian"));			    		  
	    		System.out.println("::a:::"+a);
	    		System.out.println("::b:::"+b);
 	    		
	    		c = a+b;
	    		if(c!=0){
	    			h.put("bezaBayaran", Utils.RemoveSymbol(Util.formatDecimal(c)));
	    		}else {
	    			h.put("bezaBayaran", "0"+Util.formatDecimal(d));	
	    		}
 	    	 }else{
	    		  h.put("bezaBayaran", "0"+Util.formatDecimal(d));
	    	  }    	
 	    	  list.addElement(h);
 	    	  bil++;
 	      }
 	     return list;
 	    } finally {
 	      if (db != null) db.close();
 	    }
 	}
	
	public static int simpan(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      
	      long idCukaiTerperinci = DB.getNextID("TBLHTPCUKAITERPERINCI_SEQ");
	      int idHakmilikCukai = (Integer)data.get("idHakmilikCukai");
	      
	      double amaunResit = Double.parseDouble(Utils.RemoveSymbol(data.get("amaunResit").toString()));
	      String noResit = (String)data.get("noResit");
		  String tarikhResit;
		    if(data.get("tarikhResit") != null){
		    	tarikhResit = (String)data.get("tarikhResit");
		    }else{
		    	tarikhResit = "";
		    }
		  String TR = "to_date('" + tarikhResit + "','dd/MM/yyyy')"; 
		  double cukaiTahunan = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTahunan").toString()));
	      double cukaiParit;
	      	if(data.get("cukaiParit") != ""){
	      		cukaiParit = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiParit").toString()));
			}
			else{
				cukaiParit = 0.00;
			}
	      double cukaiTaliAir;
	      	if(data.get("cukaiTaliAir") != ""){
	      		cukaiTaliAir = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTaliAir").toString()));
			}
			else{
				cukaiTaliAir = 0.00;
			}
	      double pengurangan;
	      	if(data.get("pengurangan") != ""){
	      		pengurangan = Double.parseDouble(Utils.RemoveSymbol(data.get("pengurangan").toString()));
			}
			else{
				pengurangan = 0.00;
			}
	      double pengecualian;
	      	if(data.get("pengecualian") != ""){
	      		pengecualian = Double.parseDouble(Utils.RemoveSymbol(data.get("pengecualian").toString()));
			}
			else{
				pengecualian = 0.00;
			}
	      String catatan;
		    if(data.get("catatan") != null)
		    	catatan = (String)data.get("catatan");
		    else
		    	catatan = "";
		  int tahun = (Integer)data.get("tahun");
	      double denda;
	      	if(data.get("denda") != ""){
	      		denda = Double.parseDouble(Utils.RemoveSymbol(data.get("denda").toString()));
			}
			else{
				denda = 0.00;
			}
	      double bayaranLain;
	      	if(data.get("bayaranLain") != ""){
	      		bayaranLain = Double.parseDouble(Utils.RemoveSymbol(data.get("bayaranLain").toString()));
			}
			else{
				bayaranLain = 0.00;
			}
	      double tunggakan;
	      	if(data.get("tunggakan") != ""){
	      		tunggakan = Double.parseDouble(Utils.RemoveSymbol(data.get("tunggakan").toString()));
			}
			else{
				tunggakan = 0.00;
			}
	      double jumlahBayaran = Double.parseDouble(Utils.RemoveSymbol(data.get("jumlahBayaran").toString()));
	      	 			
		  db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_cukaiterperinci", idCukaiTerperinci);
	      r.add("id_hakmilikcukai", idHakmilikCukai);
	      r.add("cukai_perlu_bayar", amaunResit);
	      r.add("no_resit", noResit);
	      r.add("tarikh_resit", r.unquote(TR));
	      r.add("cukai_kena_bayar", cukaiTahunan);
	      r.add("cukai_parit", cukaiParit);
	      r.add("cukai_taliair", cukaiTaliAir);
	      r.add("pengurangan", pengurangan);
	      r.add("pengecualian", pengecualian);
	      r.add("tujuan", catatan);
	      r.add("tahun", tahun);
	      r.add("denda", denda);
	      r.add("bayaran_lain", bayaranLain);
	      r.add("tunggakan", tunggakan);
	      r.add("cukai_dibayar", jumlahBayaran);
	      
	      sql = r.getSQLInsert("TBLHTPCUKAITERPERINCI");
	      System.out.println("FrmCukaiPelarasanData::Insert::TBLHTPCUKAITERPERINCI = "+sql);
	      stmt.executeUpdate(sql);
	      return (int)idCukaiTerperinci;
	    } 
	    finally {
	    	if (db != null) db.close();
	    }
	}
	
	//*** update data in database
	  public static int update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	int idCukaiTerperinci = (Integer)data.get("idCukaiTerperinci");
			    int idHakmilikCukai = (Integer)data.get("idHakmilikCukai");

			    double amaunResit = Double.parseDouble(Utils.RemoveSymbol(data.get("amaunResit").toString()));
			    String noResit = (String)data.get("noResit");
				String tarikhResit;
				if(data.get("tarikhResit") != null){
					tarikhResit = (String)data.get("tarikhResit");
				}else{
				    tarikhResit = "";
				}
				String TR = "to_date('" + tarikhResit + "','dd/MM/yyyy')"; 
				double cukaiTahunan = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTahunan").toString()));
			    double cukaiParit;
			    if(data.get("cukaiParit") != ""){
			    	cukaiParit = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiParit").toString()));
			    }
				else{
					cukaiParit = 0.00;
				}
			    double cukaiTaliAir;
			    if(data.get("cukaiTaliAir") != ""){
			      	cukaiTaliAir = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTaliAir").toString()));
				}
				else{
					cukaiTaliAir = 0.00;
				}
			    double pengurangan;
			    if(data.get("pengurangan") != ""){
			      	pengurangan = Double.parseDouble(Utils.RemoveSymbol(data.get("pengurangan").toString()));
				}
				else{
					pengurangan = 0.00;
				}
			    double pengecualian;
			    if(data.get("pengecualian") != ""){
			      	pengecualian = Double.parseDouble(Utils.RemoveSymbol(data.get("pengecualian").toString()));
				}
				else{
					pengecualian = 0.00;
				}
			    String catatan;
				if(data.get("catatan") != null)
				    catatan = (String)data.get("catatan");
				else
				    catatan = "";
				int tahun = (Integer)data.get("tahun");
			    double denda;
			    if(data.get("denda") != ""){
			      	denda = Double.parseDouble(Utils.RemoveSymbol(data.get("denda").toString()));
				}
				else{
					denda = 0.00;
				}
			    double bayaranLain;
			    if(data.get("bayaranLain") != ""){
			      	bayaranLain = Double.parseDouble(Utils.RemoveSymbol(data.get("bayaranLain").toString()));
				}
				else{
					bayaranLain = 0.00;
				}
			    double tunggakan;
			    if(data.get("tunggakan") != ""){
			      	tunggakan = Double.parseDouble(Utils.RemoveSymbol(data.get("tunggakan").toString()));
				}
				else{
					tunggakan = 0.00;
				}
			    double jumlahBayaran = Double.parseDouble(Utils.RemoveSymbol(data.get("jumlahBayaran").toString()));

			    
				db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_cukaiterperinci", idCukaiTerperinci);
			    r.add("id_hakmilikcukai", idHakmilikCukai);
			    r.add("cukai_perlu_bayar", amaunResit);
			    r.add("no_resit", noResit);
			    r.add("tarikh_resit", r.unquote(TR));
			    r.add("cukai_kena_bayar", cukaiTahunan);
			    r.add("cukai_parit", cukaiParit);
			    r.add("cukai_taliair", cukaiTaliAir);
			    r.add("pengurangan", pengurangan);
			    r.add("pengecualian", pengecualian);
			    r.add("tujuan", catatan);
			    r.add("tahun", tahun);
			    r.add("denda", denda);
			    r.add("bayaran_lain", bayaranLain);
			    r.add("tunggakan", tunggakan);
			    r.add("cukai_dibayar", jumlahBayaran);
			      
			    sql = r.getSQLUpdate("TBLHTPCUKAITERPERINCI");
			    System.out.println("FrmCukaiPelarasanData::Update::TBLHTPCUKAITERPERINCI = "+sql);
			    stmt.executeUpdate(sql);
			    return (int)idCukaiTerperinci;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
}
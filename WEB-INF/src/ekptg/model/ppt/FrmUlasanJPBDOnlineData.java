package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
/*
 * @author 
 * Shiqa
 */


public class FrmUlasanJPBDOnlineData {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLogger = Logger.getLogger(FrmUlasanJPBDOnlineData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static final Log log = LogFactory.getLog(FrmUlasanJPBDOnlineData.class);
	
	private static Vector listcarian = null;
	private static Vector dataMaklumatJPBD = null;
	private static Vector dataMaklumatJPPH = null;
	private static Vector dataMaklumatJPPHSementara = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}
	
	@SuppressWarnings("unchecked")
	public static  Vector getDataMaklumatJPBD(){
		return dataMaklumatJPBD;
	}
	
	@SuppressWarnings("unchecked")
	public static  Vector getDataMaklumatJPPH(){
		return dataMaklumatJPPH;
	}
	
	@SuppressWarnings("unchecked")
	public static  Vector getDataMaklumatJPPHSementara(){
		return dataMaklumatJPPHSementara;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (147,43)";
		
		sql +=" AND (p.id_permohonan in (select distinct hx.id_permohonan from Tblpptulasanteknikal hx "; 
		sql += " where hx.flag_jenis_ulasan in ('1','2') and hx.id_permohonan = p.id_permohonan) ";
		sql += " OR p.id_permohonan in (select distinct wx.id_permohonan from Tblpptwarta wx ";
		sql += " where wx.id_permohonan = p.id_permohonan)) ";
		
		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			if(userIdNegeri.equals("14")){
				sql += "AND f.id_negeri in (14,15,16) ";
			}else{
				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
			}		
		}
		
		
	
	return sql;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		/*
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '52' ";
		    		sql +="AND p.id_status IN (147,43)";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		sql = sqlListPermohonan(userIdNegeri);
		    		sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
		    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
				    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			if(rs.getString("no_fail") == null){
		    				h.put("no_fail","Belum Diluluskan");
		    			}else{
		    				h.put("no_fail",rs.getString("no_fail"));
		    			}
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPermohonan
	
	@SuppressWarnings("unchecked")
	public Vector viewMaklumatPermohonan(String ID_PERMOHONAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				Boolean haveINTData = false;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				String NO_FAIL = "", NO_PERMOHONAN = "", NAMA_NEGERI = "", NAMA_DAERAH = "", TAJUK_PERMOHONAN = "";
				String NO_RUJUKAN_JPBD = "", NO_WARTA = "", DALAM_KAWASAN_PBPT = "", ADA_PELAN_STRUKTUR = "", ADA_PELAN_TEMPATAN = "";
				String TARIKH_LULUS_PELAN_STRUKTUR = "", TARIKH_LULUS_PELAN_TEMPATAN = "", KEGUNAAN_TANAH = "", POTENSI_PEMBANGUNAN = "", NAMA_PBT = "";
				String STATUS_KELULUSAN = "", PERMOHONAN_MEMAJUKAN_TANAH = "", TUJUAN_PERMOHONAN = "", TARIKH_LULUS_TOLAK = "", TARIKH_LUPUT_KELULUSAN = "";
				String CATATAN_LAIN = "", NAMA_PEGAWAI_JPBD = "", JAWATAN_PEGAWAI_JPBD = "", TANDATANGAN_BAGI_PIHAK = "", NAMA_PEGAWAI_ASAL = "";
				String JAWATAN_PEGAWAI_ASAL = "", TARIKH_PERMOHONAN = "", JABATAN = "";

				sql = "SELECT FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, NG.NAMA_NEGERI, DE.NAMA_DAERAH, PMHN.TUJUAN, M.NO_RUJUKAN_JPBD, M.NO_WARTA, " +
					"M.DALAM_KAWASAN_PBPT, M.ADA_PELAN_STRUKTUR, M.ADA_PELAN_TEMPATAN, M.TARIKH_LULUS_PELAN_STRUKTUR, M.TARIKH_LULUS_PELAN_TEMPATAN, " +
					"M.KEGUNAAN_TANAH, M.POTENSI_PEMBANGUNAN, M.NAMA_PBT, M.STATUS_KELULUSAN, M.PERMOHONAN_MEMAJUKAN_TANAH, M.TUJUAN_PERMOHONAN, " +
					"M.TARIKH_LULUS_TOLAK, M.TARIKH_LUPUT_KELULUSAN, M.CATATAN_LAIN, M.NAMA_PEGAWAI_JPBD, M.JAWATAN_PEGAWAI_JPBD, " +
					"M.TANDATANGAN_BAGI_PIHAK, M.NAMA_PEGAWAI_ASAL, M.JAWATAN_PEGAWAI_ASAL, M.TARIKH_PERMOHONAN, M.JABATAN " +
					"FROM TBLINTJPBD M, TBLPPTPERMOHONAN PMHN, TBLPFDFAIL FAIL, TBLRUJNEGERI NG, TBLRUJDAERAH DE " +
					"WHERE M.ID_PERMOHONAN = PMHN.ID_PERMOHONAN AND PMHN.ID_FAIL = FAIL.ID_FAIL AND PMHN.ID_NEGERI = NG.ID_NEGERI(+) AND PMHN.ID_DAERAH = DE.ID_DAERAH(+) " +
					"AND M.ID_PERMOHONAN = " + ID_PERMOHONAN;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					NO_PERMOHONAN = rs.getString(2) == null ? "" : rs.getString(2);
					NAMA_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
					NAMA_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
					TAJUK_PERMOHONAN = rs.getString(5) == null ? "" : rs.getString(5);
					NO_RUJUKAN_JPBD = rs.getString(6) == null ? "" : rs.getString(6);
					NO_WARTA = rs.getString(7) == null ? "" : rs.getString(7);
					DALAM_KAWASAN_PBPT = rs.getString(8) == null ? "" : rs.getString(8); 
					ADA_PELAN_STRUKTUR = rs.getString(9) == null ? "" : rs.getString(9); 
					ADA_PELAN_TEMPATAN = rs.getString(10) == null ? "" : rs.getString(10);
					TARIKH_LULUS_PELAN_STRUKTUR = rs.getDate(11) == null ? "" : sdf.format(rs.getDate(11));
					TARIKH_LULUS_PELAN_TEMPATAN = rs.getDate(12) == null ? "" : sdf.format(rs.getDate(12));
					KEGUNAAN_TANAH = rs.getString(13) == null ? "" : rs.getString(13);
					POTENSI_PEMBANGUNAN = rs.getString(14) == null ? "" : rs.getString(14);
					NAMA_PBT = rs.getString(15) == null ? "" : rs.getString(15);
					STATUS_KELULUSAN = rs.getString(16) == null ? "" : rs.getString(16);
					PERMOHONAN_MEMAJUKAN_TANAH = rs.getString(17) == null ? "" : rs.getString(17);
					TUJUAN_PERMOHONAN = rs.getString(18) == null ? "" : rs.getString(18);
					TARIKH_LULUS_TOLAK = rs.getDate(19) == null ? "" : sdf.format(rs.getDate(19));
					TARIKH_LUPUT_KELULUSAN = rs.getDate(20) == null ? "" : sdf.format(rs.getDate(20));
					CATATAN_LAIN = rs.getString(21) == null ? "" : rs.getString(21);
					NAMA_PEGAWAI_JPBD = rs.getString(22) == null ? "" : rs.getString(22);
					JAWATAN_PEGAWAI_JPBD = rs.getString(23) == null ? "" : rs.getString(23);
					TANDATANGAN_BAGI_PIHAK = rs.getString(24) == null ? "" : rs.getString(24);
					NAMA_PEGAWAI_ASAL = rs.getString(25) == null ? "" : rs.getString(25);
					JAWATAN_PEGAWAI_ASAL = rs.getString(26) == null ? "" : rs.getString(26); 
					TARIKH_PERMOHONAN = rs.getDate(27) == null ? "" : sdf.format(rs.getDate(27));
					JABATAN = rs.getString(28) == null ? "" : rs.getString(28);
				}
				
				if (!haveINTData) {
					sql = "SELECT FAIL.NO_FAIL, PMHN.NO_PERMOHONAN, NG.NAMA_NEGERI, DE.NAMA_DAERAH, PMHN.TUJUAN, " +
					"'', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '' " +
					"FROM TBLPPTPERMOHONAN PMHN, TBLPFDFAIL FAIL, TBLRUJNEGERI NG, TBLRUJDAERAH DE " +
					"WHERE PMHN.ID_FAIL = FAIL.ID_FAIL AND PMHN.ID_NEGERI = NG.ID_NEGERI(+) AND PMHN.ID_DAERAH = DE.ID_DAERAH(+) " +
					"AND PMHN.ID_PERMOHONAN = " + ID_PERMOHONAN;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
						NO_PERMOHONAN = rs.getString(2) == null ? "" : rs.getString(2);
						NAMA_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
						NAMA_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
						TAJUK_PERMOHONAN = rs.getString(5) == null ? "" : rs.getString(5);
						NO_RUJUKAN_JPBD = rs.getString(6) == null ? "" : rs.getString(6);
						NO_WARTA = rs.getString(7) == null ? "" : rs.getString(7);
						DALAM_KAWASAN_PBPT = rs.getString(8) == null ? "" : rs.getString(8); 
						ADA_PELAN_STRUKTUR = rs.getString(9) == null ? "" : rs.getString(9); 
						ADA_PELAN_TEMPATAN = rs.getString(10) == null ? "" : rs.getString(10);
						TARIKH_LULUS_PELAN_STRUKTUR = rs.getDate(11) == null ? "" : sdf.format(rs.getDate(11));
						TARIKH_LULUS_PELAN_TEMPATAN = rs.getDate(12) == null ? "" : sdf.format(rs.getDate(12));
						KEGUNAAN_TANAH = rs.getString(13) == null ? "" : rs.getString(13);
						POTENSI_PEMBANGUNAN = rs.getString(14) == null ? "" : rs.getString(14);
						NAMA_PBT = rs.getString(15) == null ? "" : rs.getString(15);
						STATUS_KELULUSAN = rs.getString(16) == null ? "" : rs.getString(16);
						PERMOHONAN_MEMAJUKAN_TANAH = rs.getString(17) == null ? "" : rs.getString(17);
						TUJUAN_PERMOHONAN = rs.getString(18) == null ? "" : rs.getString(18);
						TARIKH_LULUS_TOLAK = rs.getDate(19) == null ? "" : sdf.format(rs.getDate(19));
						TARIKH_LUPUT_KELULUSAN = rs.getDate(20) == null ? "" : sdf.format(rs.getDate(20));
						CATATAN_LAIN = rs.getString(21) == null ? "" : rs.getString(21);
						NAMA_PEGAWAI_JPBD = rs.getString(22) == null ? "" : rs.getString(22);
						JAWATAN_PEGAWAI_JPBD = rs.getString(23) == null ? "" : rs.getString(23);
						TANDATANGAN_BAGI_PIHAK = rs.getString(24) == null ? "" : rs.getString(24);
						NAMA_PEGAWAI_ASAL = rs.getString(25) == null ? "" : rs.getString(25);
						JAWATAN_PEGAWAI_ASAL = rs.getString(26) == null ? "" : rs.getString(26); 
						TARIKH_PERMOHONAN = rs.getDate(27) == null ? "" : sdf.format(rs.getDate(27));
						JABATAN = rs.getString(28) == null ? "" : rs.getString(28);
					}
				}
				h = new Hashtable();
				h.put("haveINTData", Boolean.toString(haveINTData));
    			h.put("MP_NOFAIL", NO_FAIL);
				h.put("MP_NOPERMOHONAN", NO_PERMOHONAN);
				h.put("MP_NEGERI", NAMA_NEGERI);
				h.put("MP_DAERAH", NAMA_DAERAH);
				h.put("MP_TAJUKPERMOHONAN", TAJUK_PERMOHONAN);
				h.put("JPBD_NORUJUKANJPBD", NO_RUJUKAN_JPBD);
				h.put("JPBD_NOWARTA", NO_WARTA);
				h.put("JPBD_DALAMKAWASANPBPT", DALAM_KAWASAN_PBPT);
				h.put("JPBD_ADAPELANSTRUKTUR", ADA_PELAN_STRUKTUR);
				h.put("JPBD_ADAPELANTEMPATAN", ADA_PELAN_TEMPATAN);
				h.put("JPBD_TARIKHLULUSPELANSTRUKTUR", TARIKH_LULUS_PELAN_STRUKTUR);
				h.put("JPBD_TARIKHLULUSPELANTEMPATAN", TARIKH_LULUS_PELAN_TEMPATAN);
				h.put("JPBD_KEGUNAANTANAH", KEGUNAAN_TANAH);
				h.put("JPBD_POTENSIPEMBANGUNAN", POTENSI_PEMBANGUNAN);
				h.put("JPBD_NAMAPBT", NAMA_PBT);
				h.put("JPBD_STATUSKELULUSAN", STATUS_KELULUSAN);
				h.put("JPBD_PERMOHONANMEMAJUKANTANAH", PERMOHONAN_MEMAJUKAN_TANAH);
				h.put("JPBD_TUJUANPERMOHONAN", TUJUAN_PERMOHONAN);
				h.put("JPBD_TARIKHLULUSTOLAK", TARIKH_LULUS_TOLAK);
				h.put("JPBD_TARIKHLUPUTKELULUSAN", TARIKH_LUPUT_KELULUSAN);
				h.put("JPBD_CATATANLAIN", CATATAN_LAIN);
				h.put("JPBD_NAMAPEGAWAIJPBD", NAMA_PEGAWAI_JPBD);
				h.put("JPBD_JAWATANPEGAWAIJPBD", JAWATAN_PEGAWAI_JPBD);
				h.put("JPBD_TANDATANGANBAGIPIHAK", TANDATANGAN_BAGI_PIHAK);
				h.put("JPBD_NAMAPEGAWAIASAL", NAMA_PEGAWAI_ASAL);
				h.put("JPBD_JAWATANPEGAWAIASAL", JAWATAN_PEGAWAI_ASAL);
				h.put("JPBD_TARIKHPERMOHONAN", TARIKH_PERMOHONAN);
				h.put("JPBD_JABATAN", JABATAN);
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public static void setListCarian(String carianNofail, String carianTarikh, String status, String userIdNegeri)throws Exception {
		
		listcarian = new Vector();
		
	    Db db = null;
	    listcarian.clear();
	    String sql = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	    
	    
	    String nofail = carianNofail.trim();
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		/*
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +="AND p.id_status IN (147,43)";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		*/
	    		
	    		sql = sqlListPermohonan(userIdNegeri);
	    		
	    		//default flag
				boolean setLimit = true; 
				
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						setLimit = false;
						//sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
						sql += " AND (UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
					}
				}//close carian by nofail
		
				//TARIKH
				if (carianTarikh != "" && carianTarikh != null) {
					if (!cariTarikh.equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}//close carian by tarikh
	
	    		//STATUS
				if (status != "" && status != null) {
					setLimit = false;
					sql = sql + " AND UPPER(s.id_status) = '"+status+"'";
				}//close carian by status

				if(setLimit){	
					sql += " AND ROWNUM <= 10 ";				
				}
				
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
			    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	    			if(rs.getString("no_fail") == null){
	    				h.put("no_fail","Belum Diluluskan");
	    			}else{
	    				h.put("no_fail",rs.getString("no_fail"));
	    			}
	    			listcarian.addElement(h);
	    			bil++;
	    			
	      }//close while
	      
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}
// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	
	@SuppressWarnings("unchecked")
	public static void simpanMaklumatJPBD(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		long id_ulasanteknikal = DB.getNextID("TBLPPTULASANTEKNIKAL_SEQ");
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//String txtKodPejabatJPBD = (String)data.get("txtKodPejabatJPBD");
	    		String txtBilSurat = (String)data.get("txtBilSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");	    	
	    		String txdTarikhTerimaJT = (String)data.get("txdTarikhTerimaJT");	 
	    		String txdTarikhSuratJT = (String)data.get("txdTarikhSuratJT");
	    		String txtUlasanJPBD = (String)data.get("txtUlasanJPBD");
	    		
	    		//PENAMBAHAN YATI
	    		String namaJPBD = (String)data.get("namaJPBD");
	    		String emelJPBD = (String)data.get("emelJPBD");
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TTJT = "to_date('" + txdTarikhTerimaJT + "','dd/MM/yyyy')";
	    		String TSJT = "to_date('" + txdTarikhSuratJT + "','dd/MM/yyyy')";
	    		
	    		//flag jpbd
	    		String flag = "1";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_ulasanteknikal", id_ulasanteknikal);
	    		r.add("id_hakmilik", id_hakmilik);
	    		r.add("id_permohonan", id_permohonan);
	    		//r.add("kod_pejabat_jpbd", txtKodPejabatJPBD);
	    		r.add("bil_surat", txtBilSurat);
	    		r.add("flag_jenis_ulasan", flag);
	    		r.add("no_rujukansuratjt", txtNoRujSurat);
	    		r.add("ulasanjt", txtUlasanJPBD);
	    		r.add("tarikh_surat",r.unquote(TS));    	
	    		r.add("tarikh_akhir",r.unquote(TT));
	    		r.add("tarikh_terimajt",r.unquote(TTJT));
	    		r.add("tarikh_suratjt",r.unquote(TSJT));    			    		    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		
	    		//PENAMBAHAN YATI
	    		r.add("nama_jpbd", namaJPBD);
	    		r.add("emel_jpbd", emelJPBD);
	    		sql = r.getSQLInsert("Tblpptulasanteknikal");
	    		stmt.executeUpdate(sql);
	    		myLogger.info("INSERT JPBD---"+sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanMaklumatJPBD
	
	@SuppressWarnings("unchecked")
	public static void updateMaklumatJPBD(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_ulasanteknikal = (String)data.get("id_ulasanteknikal");

	    		//String txtKodPejabatJPBD = (String)data.get("txtKodPejabatJPBD");
	    		String txtBilSurat = (String)data.get("txtBilSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");	    	
	    		String txdTarikhTerimaJT = (String)data.get("txdTarikhTerimaJT");	 
	    		String txdTarikhSuratJT = (String)data.get("txdTarikhSuratJT");
	    		String txtUlasanJPBD = (String)data.get("txtUlasanJPBD");
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TTJT = "to_date('" + txdTarikhTerimaJT + "','dd/MM/yyyy')";
	    		String TSJT = "to_date('" + txdTarikhSuratJT + "','dd/MM/yyyy')";
	    		
	    		//PENAMBAHAN YATI
	    		String namaJPBD = (String)data.get("namaJPBD");
	    		String emelJPBD = (String)data.get("emelJPBD");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_ulasanteknikal", id_ulasanteknikal);
	    		//r.add("kod_pejabat_jpbd", txtKodPejabatJPBD);
	    		r.add("bil_surat", txtBilSurat);
	    		r.add("no_rujukansuratjt", txtNoRujSurat);
	    		r.add("ulasanjt", txtUlasanJPBD);
	    		r.add("tarikh_surat",r.unquote(TS));    	
	    		r.add("tarikh_akhir",r.unquote(TT));
	    		r.add("tarikh_terimajt",r.unquote(TTJT));
	    		r.add("tarikh_suratjt",r.unquote(TSJT));    			    		    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);   
	    		
	    		//PENAMBAHAN YATI
	    		r.add("nama_jpbd", namaJPBD);
	    		r.add("emel_jpbd", emelJPBD);
	    		
	    		sql = r.getSQLUpdate("Tblpptulasanteknikal");
	    		stmt.executeUpdate(sql);
	    		
	    		myLogger.info("update sql -- "+sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateMaklumatJPBD
	
	@SuppressWarnings("unchecked")
	public static void updateMaklumatJPPH(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_ulasanteknikal = (String)data.get("id_ulasanteknikal");

	    		//String txtKodPejabatJPPH = (String)data.get("txtKodPejabatJPPH");
	    		String txtBilSurat = (String)data.get("txtBilSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");	    	
	    		String txdTarikhTerimaJT = (String)data.get("txdTarikhTerimaJT");	 
	    		String txdTarikhSuratJT = (String)data.get("txdTarikhSuratJT");
	    		String txtUlasanJPPH = (String)data.get("txtUlasanJPPH");
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TTJT = "to_date('" + txdTarikhTerimaJT + "','dd/MM/yyyy')";
	    		String TSJT = "to_date('" + txdTarikhSuratJT + "','dd/MM/yyyy')";
	    		
	    		//PENAMBAHAN YATI
	    		String namaJPPH = (String)data.get("namaJPPH");
	    		String emelJPPH = (String)data.get("emelJPPH");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_ulasanteknikal", id_ulasanteknikal);
	    		//r.add("kod_pejabat_jpph", txtKodPejabatJPPH);
	    		r.add("bil_surat", txtBilSurat);
	    		r.add("no_rujukansuratjt", txtNoRujSurat);
	    		r.add("ulasanjt", txtUlasanJPPH);
	    		r.add("tarikh_surat",r.unquote(TS));    	
	    		r.add("tarikh_akhir",r.unquote(TT));
	    		r.add("tarikh_terimajt",r.unquote(TTJT));
	    		r.add("tarikh_suratjt",r.unquote(TSJT));    			    		    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    	
	    		
	    		//PENAMBAHAN YATI
	    		r.add("NAMA_JPPH", namaJPPH);
	    		r.add("EMEL_JPPH", emelJPPH);
	    		
	    		sql = r.getSQLUpdate("Tblpptulasanteknikal");
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateMaklumatJPPH

	@SuppressWarnings("unchecked")
	public static void setDataMaklumatJPBD(String id_permohonan,String id_hakmilik)throws Exception {
		
		dataMaklumatJPBD = new Vector();
		
	    Db db = null;
	    dataMaklumatJPBD.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT id_ulasanteknikal,kod_pejabat_jpbd,no_rujukansuratjt,bil_surat,id_hakmilik, ";
	    		sql += " tarikh_surat,tarikh_akhir,tarikh_terimajt,tarikh_suratjt,ulasanjt, nama_jpbd, emel_jpbd ";
	    		sql += " FROM Tblpptulasanteknikal ";
	    		sql += " WHERE id_permohonan = '"+id_permohonan+"'";
	    		sql += " AND id_hakmilik = '"+id_hakmilik+"'";
	    		sql += " AND NVL(flag_jenis_ulasan,0) = '1' ";
	    	   
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {	    	  
	    			h = new Hashtable();	    	 
	    			h.put("ulasanjt", rs.getString("ulasanjt")== null?"":rs.getString("ulasanjt"));	    			
	    			h.put("id_ulasanteknikal", rs.getString("id_ulasanteknikal")== null?"":rs.getString("id_ulasanteknikal"));
	    			h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
	    			h.put("kod_pejabat_jpbd", rs.getString("kod_pejabat_jpbd")== null?"":rs.getString("kod_pejabat_jpbd"));
	    			h.put("no_rujukansuratjt", rs.getString("no_rujukansuratjt")== null?"":rs.getString("no_rujukansuratjt"));
	    			h.put("bil_surat", rs.getString("bil_surat")== null?"":rs.getString("bil_surat"));	    			
	    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
	    			h.put("tarikh_akhir", rs.getDate("tarikh_akhir")==null?"":Format.format(rs.getDate("tarikh_akhir")));
	    			h.put("tarikh_terimajt", rs.getDate("tarikh_terimajt")==null?"":Format.format(rs.getDate("tarikh_terimajt")));
	    			h.put("tarikh_suratjt", rs.getDate("tarikh_suratjt")==null?"":Format.format(rs.getDate("tarikh_suratjt")));	 
	    			//penambahan yati
	    			h.put("nama_jpbd", rs.getString("nama_jpbd")== null?"":rs.getString("nama_jpbd"));	  
	    			h.put("emel_jpbd", rs.getString("emel_jpbd")== null?"":rs.getString("emel_jpbd"));	  
	    			dataMaklumatJPBD.addElement(h);
	    		}			    
	    		
	    	} catch (Exception re) {
	    		log.error("Error: ", re);
	    		throw re;
	    		} finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataMaklumatJPBD
	
	@SuppressWarnings("unchecked")
	public static void setDataMaklumatJPPH(String id_permohonan,String id_hakmilik)throws Exception {
		
		dataMaklumatJPPH = new Vector();
		
	    Db db = null;
	    dataMaklumatJPPH.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT id_ulasanteknikal,kod_pejabat_jpph,no_rujukansuratjt,bil_surat,id_hakmilik, ";
	    		sql += " tarikh_surat,tarikh_akhir,tarikh_terimajt,tarikh_suratjt,ulasanjt ,FLAG_TUGASAN, nama_jpph, emel_jpph";
	    		sql += " FROM Tblpptulasanteknikal ";
	    		sql += " WHERE id_permohonan = '"+id_permohonan+"'";
	    		sql += " AND id_hakmilik = '"+id_hakmilik+"'";
	    		sql += " AND NVL(flag_jenis_ulasan,0) = '2' ";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	    	
	    		Hashtable h;

	    		while (rs.next()) {	    	  
	    			h = new Hashtable();	 
	    			h.put("ulasanjt", rs.getString("ulasanjt")== null?"":rs.getString("ulasanjt"));
	    			h.put("id_ulasanteknikal", rs.getString("id_ulasanteknikal")== null?"":rs.getString("id_ulasanteknikal"));
	    			h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
	    			h.put("kod_pejabat_jpph", rs.getString("kod_pejabat_jpph")== null?"":rs.getString("kod_pejabat_jpph"));
	    			h.put("no_rujukansuratjt", rs.getString("no_rujukansuratjt")== null?"":rs.getString("no_rujukansuratjt"));
	    			h.put("bil_surat", rs.getString("bil_surat")== null?"":rs.getString("bil_surat"));	    			
	    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
	    			h.put("tarikh_akhir", rs.getDate("tarikh_akhir")==null?"":Format.format(rs.getDate("tarikh_akhir")));
	    			h.put("tarikh_terimajt", rs.getDate("tarikh_terimajt")==null?"":Format.format(rs.getDate("tarikh_terimajt")));
	    			h.put("tarikh_suratjt", rs.getDate("tarikh_suratjt")==null?"":Format.format(rs.getDate("tarikh_suratjt")));	    
	    			h.put("flag_tugasan", rs.getString("FLAG_TUGASAN")== null?"":rs.getString("FLAG_TUGASAN"));
	    			
	    			//penambahan yati
	    			h.put("nama_jpph", rs.getString("NAMA_JPPH")== null?"":rs.getString("NAMA_JPPH"));	
	    			h.put("emel_jpph", rs.getString("EMEL_JPPH")== null?"":rs.getString("EMEL_JPPH"));	
	    			
	    			myLogger.info("namaJPPH--- "+h.put("namajpph", rs.getString("NAMA_JPPH")== null?"":rs.getString("NAMA_JPPH")));
	    			dataMaklumatJPPH.addElement(h);
	    		}			    
	    		
	    	} catch (Exception re) {
	    		log.error("Error: ", re);
	    		throw re;
	    		} finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataMaklumatJPPH
	
	@SuppressWarnings("unchecked")
	public static void setDataMaklumatJPPHSementara(String id_hakmilik)throws Exception {
		
		dataMaklumatJPPHSementara = new Vector();
		
	    Db db = null;
	    dataMaklumatJPPHSementara.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT id_ulasanteknikal,kod_pejabat_jpph,no_rujukansuratjt,bil_surat, ";
	    		sql += " tarikh_surat,tarikh_akhir,tarikh_terimajt,tarikh_suratjt,ulasanjt ";
	    		sql += " FROM Tblpptulasanteknikal ";
	    		sql += " WHERE id_hakmilik = '"+id_hakmilik+"'";
	    		sql += " AND NVL(flag_jenis_ulasan,0) = '2' ";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {	    	  
	    			h = new Hashtable();	 
	    			h.put("ulasanjt", rs.getString("ulasanjt")== null?"":rs.getString("ulasanjt"));
	    			h.put("id_ulasanteknikal", rs.getString("id_ulasanteknikal")== null?"":rs.getString("id_ulasanteknikal"));
	    			h.put("kod_pejabat_jpph", rs.getString("kod_pejabat_jpph")== null?"":rs.getString("kod_pejabat_jpph"));
	    			h.put("no_rujukansuratjt", rs.getString("no_rujukansuratjt")== null?"":rs.getString("no_rujukansuratjt"));
	    			h.put("bil_surat", rs.getString("bil_surat")== null?"":rs.getString("bil_surat"));	    			
	    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
	    			h.put("tarikh_akhir", rs.getDate("tarikh_akhir")==null?"":Format.format(rs.getDate("tarikh_akhir")));
	    			h.put("tarikh_terimajt", rs.getDate("tarikh_terimajt")==null?"":Format.format(rs.getDate("tarikh_terimajt")));
	    			h.put("tarikh_suratjt", rs.getDate("tarikh_suratjt")==null?"":Format.format(rs.getDate("tarikh_suratjt")));	    			
	    			dataMaklumatJPPHSementara.addElement(h);
	    		}			    
	    		
	    	} catch (Exception re) {
	    		log.error("Error: ", re);
	    		throw re;
	    		} finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataMaklumatJPPH(SEMENTARA)
	
	public static void updateStatus(String idpermohonan,String idUser,String idstatus) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan",idpermohonan);
	    		r.add("id_status",idstatus);      			
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",idUser);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateStatus
	
	@SuppressWarnings("unchecked")
	public static void simpanMaklumatJPPH(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		long id_ulasanteknikal = DB.getNextID("TBLPPTULASANTEKNIKAL_SEQ");
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//String txtKodPejabatJPPH = (String)data.get("txtKodPejabatJPPH");
	    		String txtBilSurat = (String)data.get("txtBilSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");	    	
	    		String txdTarikhTerimaJT = (String)data.get("txdTarikhTerimaJT");	 
	    		String txdTarikhSuratJT = (String)data.get("txdTarikhSuratJT");
	    		String txtUlasanJPPH = (String)data.get("txtUlasanJPPH");
	    		
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TTJT = "to_date('" + txdTarikhTerimaJT + "','dd/MM/yyyy')";
	    		String TSJT = "to_date('" + txdTarikhSuratJT + "','dd/MM/yyyy')";
	    		
	    		//PENAMBAHAN YATI
	    		String namaJPPH = (String)data.get("namaJPPH");
	    		String emelJPPH = (String)data.get("emelJPPH");	
	    		
	    		//flag jpbd
	    		String flag = "2";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_ulasanteknikal", id_ulasanteknikal);
	    		r.add("id_hakmilik", id_hakmilik);
	    		r.add("id_permohonan", id_permohonan);
	    		//r.add("kod_pejabat_jpph", txtKodPejabatJPPH);
	    		r.add("bil_surat", txtBilSurat);
	    		r.add("flag_jenis_ulasan", flag);
	    		r.add("ulasanjt", txtUlasanJPPH);
	    		r.add("no_rujukansuratjt", txtNoRujSurat);
	    		r.add("tarikh_surat",r.unquote(TS));    	
	    		r.add("tarikh_akhir",r.unquote(TT));
	    		r.add("tarikh_terimajt",r.unquote(TTJT));
	    		r.add("tarikh_suratjt",r.unquote(TSJT));    			    		    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    
	    		
	    		//penambahan yati
	    		r.add("NAMA_JPPH", namaJPPH);
	    		r.add("EMEL_JPPH", emelJPPH);
	    		
	    		sql = r.getSQLInsert("Tblpptulasanteknikal");
	    		myLogger.info("sql jpph : "+sql);
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanMaklumatJPPH
	
	
	@SuppressWarnings("unchecked")
	public static void simpanMaklumatJPPHSementara(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		long id_ulasanteknikal = DB.getNextID("TBLPPTULASANTEKNIKAL_SEQ");
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//String txtKodPejabatJPPH = (String)data.get("txtKodPejabatJPPH");
	    		String txtBilSurat = (String)data.get("txtBilSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");	    	
	    		String txdTarikhTerimaJT = (String)data.get("txdTarikhTerimaJT");	 
	    		String txdTarikhSuratJT = (String)data.get("txdTarikhSuratJT");
	    		String txtUlasanJPPH = (String)data.get("txtUlasanJPPH");
	    		
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TTJT = "to_date('" + txdTarikhTerimaJT + "','dd/MM/yyyy')";
	    		String TSJT = "to_date('" + txdTarikhSuratJT + "','dd/MM/yyyy')";
	    		
	    		//flag jpbd
	    		String flag = "2";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_ulasanteknikal", id_ulasanteknikal);
	    		r.add("id_hakmilik", id_hakmilik);
	    		r.add("id_permohonan", id_permohonan);
	    		//r.add("kod_pejabat_jpph", txtKodPejabatJPPH);
	    		r.add("bil_surat", txtBilSurat);
	    		r.add("flag_jenis_ulasan", flag);
	    		r.add("ulasanjt", txtUlasanJPPH);
	    		r.add("no_rujukansuratjt", txtNoRujSurat);
	    		r.add("tarikh_surat",r.unquote(TS));    	
	    		r.add("tarikh_akhir",r.unquote(TT));
	    		r.add("tarikh_terimajt",r.unquote(TTJT));
	    		r.add("tarikh_suratjt",r.unquote(TSJT));    			    		    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptulasanteknikal");
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanMaklumatJPPH(SEMENTARA)
	
	@SuppressWarnings("unchecked")
	public static void hantarMaklumatJPPH(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_ulasanteknikal = (String)data.get("id_ulasanteknikal");

	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_ulasanteknikal", id_ulasanteknikal);
	    		r.add("flag_tugasan","H");    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptulasanteknikal");
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateMaklumatJPPH
	
	//penambahan function-yati
	public static Vector listEmelJPBD = null;
	
	public static Vector getListEmelJPBD() {
		return listEmelJPBD;
	}
	
	//get detail user
		@SuppressWarnings("unchecked")
		public static Vector setListEmelJPBD(String id_hakmilik) throws Exception {
		
			Vector listEmelJPBD = new Vector();
			
			Db db = null;
			String sql = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
				
					/*sql =  "SELECT TK.ID_PERMOHONAN, TK.ID_ULASANTEKNIKAL, TK.ID_MASUK, UI.EMEL AS EMEL " +
							"FROM TBLPPTULASANTEKNIKAL TK, USERS_INTERNAL UI " +
							"WHERE UI.USER_ID = TK.ID_MASUK " +
							"AND TK.FLAG_JENIS_ULASAN ='1' " +
							"AND TK.ID_PERMOHONAN = '"+id_permohonan+"' " +
							"UNION " +
							"SELECT TK.ID_PERMOHONAN, TK.ID_ULASANTEKNIKAL, TK.ID_MASUK, UO.EMEL AS EMEL " +
							"FROM TBLPPTULASANTEKNIKAL TK, USERS_ONLINE UO " +
							"WHERE UO.USER_ID = TK.ID_MASUK " +
							"AND TK.FLAG_JENIS_ULASAN ='1' " +
							"AND TK.ID_PERMOHONAN = '"+id_permohonan+"' ";
							*/
					
					//sql = " SELECT UO.USER_ID, HM.ID_MASUK, UO.EMEL FROM TBLPPTHAKMILIK HM, " +
					//		"USERS_ONLINE UO WHERE HM.ID_MASUK = UO.USER_ID AND HM.ID_PERMOHONAN = '"+id_permohonan+"' ";
					
					sql = " SELECT ID_HAKMILIK, NAMA_JPBD, EMEL_JPBD FROM TBLPPTULASANTEKNIKAL WHERE FLAG_JENIS_ULASAN = '1' AND ID_HAKMILIK = '"+id_hakmilik+"' ";
					myLogger.info("GET EMEL : "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h = null;
					while (rs.next()) {
						h = new Hashtable();
						h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
						//h.put("id_kementerian", rs.getString("ID_ULASANTEKNIKAL")== null?"":rs.getString("ID_ULASANTEKNIKAL"));
						h.put("nama_jpbd", rs.getString("NAMA_JPBD")== null?"":rs.getString("NAMA_JPBD"));
						h.put("emel", rs.getString("EMEL_JPBD")== null?"":rs.getString("EMEL_JPBD"));
					
						listEmelJPBD.addElement(h);
						
				}
				return listEmelJPBD;
			}catch (Exception re) {
				throw re;
			} finally {
				if (db != null)
					db.close();
			}
		}//close setEmelJPBD
		
		//penambahan function-yati
		public static Vector listEmelJPPH = null;
		
		public static Vector getListEmelJPPH() {
			return listEmelJPPH;
		}
		
		//get detail user
			@SuppressWarnings("unchecked")
			public static Vector setListEmelJPPH(String id_hakmilik) throws Exception {
			
				Vector listEmelJPPH = new Vector();
				
				Db db = null;
				String sql = "";
				
				try {
						db = new Db();
						Statement stmt = db.getStatement();
					
//						sql =  "SELECT TK.ID_PERMOHONAN, TK.ID_ULASANTEKNIKAL, TK.ID_MASUK, UI.EMEL AS EMEL " +
//								"FROM TBLPPTULASANTEKNIKAL TK, USERS_INTERNAL UI " +
//								"WHERE UI.USER_ID = TK.ID_MASUK " +
//								"AND TK.FLAG_JENIS_ULASAN ='2' " +
//								"AND TK.ID_PERMOHONAN = '"+id_permohonan+"' " +
//								"UNION " +
//								"SELECT TK.ID_PERMOHONAN, TK.ID_ULASANTEKNIKAL, TK.ID_MASUK, UO.EMEL AS EMEL " +
//								"FROM TBLPPTULASANTEKNIKAL TK, USERS_ONLINE UO " +
//								"WHERE UO.USER_ID = TK.ID_MASUK " +
//								"AND TK.FLAG_JENIS_ULASAN ='2' " +
//								"AND TK.ID_PERMOHONAN = '"+id_permohonan+"' ";
						
						sql = " SELECT ID_HAKMILIK, NAMA_JPPH, EMEL_JPPH FROM TBLPPTULASANTEKNIKAL WHERE FLAG_JENIS_ULASAN = '2' AND ID_HAKMILIK = '"+id_hakmilik+"' ";
						myLogger.info("GET EMEL : "+sql);
						ResultSet rs = stmt.executeQuery(sql);
						Hashtable h = null;
						while (rs.next()) {
							h = new Hashtable();
							h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
							//h.put("id_kementerian", rs.getString("ID_ULASANTEKNIKAL")== null?"":rs.getString("ID_ULASANTEKNIKAL"));
							h.put("nama_jpph", rs.getString("NAMA_JPPH")== null?"":rs.getString("NAMA_JPPH"));
							h.put("emel", rs.getString("EMEL_JPPH")== null?"":rs.getString("EMEL_JPPH"));
						
							listEmelJPPH.addElement(h);
							
					}
					return listEmelJPPH;
				}catch (Exception re) {
					throw re;
				} finally {
					if (db != null)
						db.close();
				}
			}//close setEmelKJP
	
}//close class

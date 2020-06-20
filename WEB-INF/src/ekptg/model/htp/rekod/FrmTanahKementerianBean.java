package ekptg.model.htp.rekod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.entity.HakmilikAgensi;

public class FrmTanahKementerianBean implements ITanahKementerian{
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmTanahKementerianBean.class);
	private Db db = null;
	private String sql = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date(); 
	private HakmilikAgensi hakmilikAgensi = null;

	public void kemaskiniTanahAgensiLuas(HakmilikAgensi ha) throws Exception{
		Connection conn = null;
		try{
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK",String.valueOf(ha.getIdHakmilik()));
			r.update("STATUS","Y");
			r.add("ID_LUAS",ha.getIdLuas());
			r.add("LUAS",ha.getLuas());			 
			r.add("LUAS_BERSAMAAN", ha.getLuasBersamaan());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));			  
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(ha.getIdKemaskini())));			  
			sql = r.getSQLUpdate("TBLHTPHAKMILIKAGENSI");
			myLog.info("sql="+sql);
			stmt.executeUpdate(sql);			 
			conn.commit();
			
		}catch(Exception e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new Exception("MASALAH KEMASKINI DATA[1] , SILA SEMAK METHOD kemaskiniTanahAgensiLuas("+this.getClass()+")");
			}
			e.printStackTrace();
			throw new Exception("MASALAH KEMASKINI DATA, SILA SEMAK METHOD kemaskiniTanahAgensiLuas("+this.getClass()+")");
		
		}finally{
			if(db != null)
				db.close();
		}
		
	}

	public void kemaskiniTanahAgensi(HakmilikAgensi ha) throws Exception{
		Connection conn = null;
		//hakmilikAgensi = new HakmilikAgensi();
		try{
			//hakmilik = getHakmilik(idHakmilik);
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_HAKMILIK",String.valueOf(ha.getIdHakmilik()));
			r.update("STATUS","Y");
			r.add("ID_LUAS",ha.getIdLuas());
			r.add("LUAS",ha.getLuas());			 
			r.add("LUAS_BERSAMAAN", ha.getLuasBersamaan());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));			  
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(ha.getIdKemaskini())));			  
			sql = r.getSQLUpdate("TBLHTPHAKMILIKAGENSI");
			  
			myLog.info("kemaskiniTanahAgensi:sql="+sql);
			stmt.executeUpdate(sql);			 
			conn.commit();
			
		}catch(Exception e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new Exception("MASALAH KEMASKINI DATA[1] , SILA SEMAK METHOD kemaskiniTanahAgensi("+this.getClass()+")");
			}
			e.printStackTrace();
			throw new Exception("MASALAH KEMASKINI DATA, SILA SEMAK METHOD kemaskiniTanahAgensi("+this.getClass()+")");
		
		}finally{
			if(db != null)
				db.close();
		}
		//return hakmilikAgensi;
		
	}	
	// INSERT TBLHTPHAKMILIKAGENSI
	
	public String simpan(Hashtable<String,String> data) throws Exception {
//	public String simpan(Db db,Hashtable<String,String> data,String idhakmilik) throws Exception {
		String currentDate = sdf.format(date);
		String idAgensi = String.valueOf(DB.getNextID("TBLHTPHAKMILIKAGENSI_SEQ"));
		String idhakmilik = String.valueOf(data.get("idhakmilik"));
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//r.update("ID_HAKMILIK", data.get("idHakmilik"));
			r.add("ID_HAKMILIKAGENSI", r.unquote(idAgensi));
			r.add("ID_HAKMILIK", r.unquote(idhakmilik));
			r.add("ID_LUAS_BERSAMAAN", 2);
			r.add("LUAS_BERSAMAAN", data.get("txtLuasBersamaan"));
			r.add("ID_LUAS", data.get("socLuas"));
			r.add("LUAS", data.get("txtLuas"));
			r.add("ID_AGENSI", data.get("idAgensi"));
			r.add("ID_KEMENTERIAN", data.get("idKementerian"));
			r.add("STATUS", "Y");
			String tarikhKemaskini = currentDate;
			String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
			r.add("TARIKH_MASUK", r.unquote(txdTarikhKemaskini));
			r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));			  
			r.add("TARIKH_KEMASKINI", r.unquote(txdTarikhKemaskini));
			r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));			  
			sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
			myLog.info("insertHakmilikAgensi:sql="+sql);
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return idAgensi;			    
	}

	public Vector<Hashtable<String,String>> getSenaraiMaklumat(String idHakmilik) throws Exception {
		Vector<Hashtable<String,String>> listMaklumatFail = null;
		try {
			db = new Db();
			listMaklumatFail = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();				
		    sql = getSQLHakmilik(idHakmilik);
			myLog.info("getMaklumat:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN")==null ? "" :rs.getString("NAMA_KEMENTERIAN"));
				h.put("noFailSeksyen", rs.getString("NO_FAIL")==null ? "" :rs.getString("NO_FAIL"));
				h.put("noFailPtg", rs.getString("NO_FAIL_PTG")==null ? "" :rs.getString("NO_FAIL_PTG"));
				h.put("noFailPtd", rs.getString("NO_FAIL_PTD")==null ? "" :rs.getString("NO_FAIL_PTD"));
				h.put("tajukFail", rs.getString("TUJUAN")==null ? "" :rs.getString("TUJUAN"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI")==null ? "" :rs.getString("NAMA_NEGERI"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH")==null ? "" :rs.getString("NAMA_DAERAH"));
				h.put("jenisHakmilik", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN").toUpperCase());
				h.put("namaMukim", rs.getString("NAMA_MUKIM")==null ? "" :rs.getString("NAMA_MUKIM"));	
				h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
				h.put("noFailKjp", rs.getString("NO_FAIL_KJP")==null ? "":rs.getString("NO_FAIL_KJP"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK")==null ? "" :rs.getString("NO_HAKMILIK"));
				h.put("noLot", rs.getString("NO_LOT")==null ? "" :rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA")==null ? "" :rs.getString("NO_WARTA"));
				h.put("caraPerolehan", rs.getString("NAMA_URUSAN")==null ? "" :rs.getString("NAMA_URUSAN"));
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "" :rs.getString("ID_KEMENTERIAN"));
				h.put("hakmilikAsal", rs.getString("HAKMILIK_ASAL")==null ? "":rs.getString("HAKMILIK_ASAL"));
				h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
				h.put("pegawaiAkhir",Utils.isNull(rs.getString("PEGAWAI_AKHIR")));
				h.put("kegunaanTanah",Utils.isNull(rs.getString("KEGUNAAN_TANAH")));
				h.put("idJenisRizab",Utils.isNull(rs.getString("ID_RIZAB")));
				h.put("luasBersamaan",Utils.isNull(rs.getString("LUAS_BERSAMAAN")));
				listMaklumatFail.addElement(h);

			}
			
		} finally {
			if (db != null)
			db.close();
			
		}
		return listMaklumatFail;
	}
	
	public String getSQLHakmilik(String idHakmilik){
		sql = "SELECT F.NO_FAIL "+
		" ,TPH.ID_HAKMILIK "+
		" ,TPH.NO_FAIL_PTG,TPH.NO_FAIL_PTD,TPH.NO_FAIL_KJP "+
		" ,UPPER(TPH.NO_HAKMILIK) NO_HAKMILIK,TPH.NO_WARTA "+
		" ,TPH.NO_BANGUNAN,TPH.NO_TINGKAT,TPH.NO_PETAK "+
		" ,TPH.ID_LOT,TPH.NO_LOT "+
		" ,TPH.HAKMILIK_ASAL,TPH.CATATAN "+
		" ,TPH.KEGUNAAN_TANAH "+
		" ,TPH.ID_RIZAB "+
		" ,TPH.ID_NEGERI,TPH.ID_DAERAH,TPH.ID_MUKIM,TPH.ID_JENISHAKMILIK "+
		" ,RN.NAMA_NEGERI, RD.NAMA_DAERAH, RM.NAMA_MUKIM "+
		" ,TO_CHAR(TPH.TARIKH_TERIMA,'DD/MM/YYYY') TARIKH_TERIMA "+
		" ,TO_CHAR(TPH.TARIKH_DAFTAR,'DD/MM/YYYY') TARIKH_DAFTAR "+
		" ,TPH.TARAF_HAKMILIK,TPH.TEMPOH "+
		" ,TO_CHAR(TPH.TARIKH_LUPUT,'DD/MM/YYYY') TARIKH_LUPUT "+
		" ,NVL(TPH.CUKAI,0) CUKAI "+
		" ,TPH.LOKASI  "+
		" ,TPHA.ID_LUAS,NVL(TPHA.LUAS,0) LUAS,TPHA.ID_LUAS_BERSAMAAN,NVL(TPHA.LUAS_BERSAMAAN,0) LUAS_BERSAMAAN  "+
		" ,TPH.STATUS_RIZAB,TPH.NO_RIZAB "+
		" ,TO_CHAR(TPH.TARIKH_WARTA,'DD/MM/YYYY') TARIKH_WARTA,TPH.KAWASAN_RIZAB "+
		" ,TPH.ID_KATEGORI,TPH.SYARAT,TPH.SEKATAN "+
		" ,TPH.NO_PELAN,TPH.NO_SYIT,TPH.NO_PU "+
		" ,TPH.TARIKH_KEMASKINI "+
		" ,(SELECT USER_NAME FROM USERS WHERE USER_ID=TPH.ID_KEMASKINI) PEGAWAI_AKHIR "+
		" ,CASE "+
		" WHEN P.TUJUAN IS NULL THEN F.TAJUK_FAIL "+
		" WHEN P.TUJUAN IS NOT NULL THEN P.TUJUAN "+
		" ELSE '' "+
		" END TUJUAN "+
		" ,(    SELECT RJHI.KETERANGAN FROM TBLRUJJENISHAKMILIK RJHI  "+
		"     WHERE RJHI.ID_JENISHAKMILIK = TPH.ID_JENISHAKMILIK "+
		" ) KETERANGAN "+
		" ,(     SELECT RUI.NAMA_URUSAN  "+
		"       FROM TBLRUJURUSAN RUI,TBLPFDFAIL FI "+
		"     WHERE FI.ID_URUSAN = RUI.ID_URUSAN "+
		"    AND FI.ID_FAIL = F.ID_FAIL "+
		" ) NAMA_URUSAN "+
		" ,RK.NAMA_KEMENTERIAN,RA.NAMA_AGENSI "+
		" ,NVL(RK.ID_KEMENTERIAN,0) ID_KEMENTERIAN,RA.ID_AGENSI "+
		" FROM "+
		" TBLHTPHAKMILIK TPH "+
		" ,TBLRUJDAERAH RD "+
		" ,TBLRUJMUKIM RM "+
		" ,TBLRUJNEGERI RN "+
		" ,TBLPERMOHONAN P "+
		" ,TBLPFDFAIL F "+
		" ,TBLHTPHAKMILIKAGENSI TPHA,TBLRUJKEMENTERIANMAPPING RKME "+
		" ,TBLRUJKEMENTERIAN RK,TBLRUJAGENSI RA "+
		" WHERE P.ID_PERMOHONAN = TPH.ID_PERMOHONAN "+
		" AND P.ID_FAIL = F.ID_FAIL "+
		" AND TPH.ID_MUKIM = RM.ID_MUKIM "+
		" AND RM.ID_DAERAH = RD.ID_DAERAH "+
		" AND RD.ID_NEGERI = RN.ID_NEGERI "+
		" AND TPH.ID_HAKMILIK = TPHA.ID_HAKMILIK AND TPHA.STATUS = 'T' AND TPHA.FLAG_AKTIF = 'Y' "+
		" AND TPHA.ID_KEMENTERIAN = RKME.ID_KEMENTERIANLAMA AND RKME.STATUS = 'A' "+
		" AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN "+
		" AND TPHA.ID_AGENSI = RA.ID_AGENSI "+
		" AND TPHA.ID_HAKMILIK ='"+ idHakmilik +"'";
		return sql;
		
	}

	
 }


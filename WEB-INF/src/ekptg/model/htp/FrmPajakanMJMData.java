package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPajakanMJMData {
	private static Vector list = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
        private static Logger log = Logger.getLogger(FrmPajakanMJMData.class);
        private static Db db;
        private static Connection conn;
	
	//*** query data from database
	public static void setListMJM(String idPermohonan)throws Exception {
	    list.clear();
	    String sql;
	    try {

	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("jm.id_Jemaahmenteri");
	      r.add("jm.id_Permohonan");
	      r.add("jm.tarikh_Hantar_ksu");
	      r.add("jm.tarikh_Terima_ksu");
	      r.add("jm.tarikh_Hantar_Pemohon");
	      r.add("jm.tarikh_Keputusan");
	      r.add("jm.tarikh_Msyrt_Jemaah");
	      r.add("jm.status_Keputusan");
	      r.add("jm.tindakan_Lanjut");
	      r.add("jm.no_Memorandum");

	      r.add("jm.id_Permohonan", idPermohonan,"=");
	      
	      sql = r.getSQLSelect("Tblhtpjemaahmenteri jm");
	      log.info("FrmPajakanMJMData::setListMJM ="+sql);
	      ResultSet rs = stmt.executeQuery(sql);

	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("idJemaahmenteri", rs.getString("id_Jemaahmenteri"));
	    	  h.put("idPermohonan", rs.getString("id_Permohonan"));
	    	  h.put("tarikhHantarksu", rs.getString("tarikh_Hantar_ksu")==null?"":Format.format(rs.getDate("tarikh_Hantar_ksu")));
	    	  h.put("tarikhTerimaksu", rs.getString("tarikh_Terima_ksu")==null?"":Format.format(rs.getDate("tarikh_Terima_ksu")));
	    	  h.put("tarikhHantarPemohon", rs.getString("tarikh_Hantar_Pemohon")==null?"":Format.format(rs.getDate("tarikh_Hantar_Pemohon")));
	    	  h.put("tarikhKeputusan", rs.getString("tarikh_Keputusan")==null?"":Format.format(rs.getDate("tarikh_Keputusan")));
	    	  h.put("tarikhMsyrtJemaah", rs.getString("tarikh_Msyrt_Jemaah")==null?"":Format.format(rs.getDate("tarikh_Msyrt_Jemaah")));
	    	  h.put("statusKeputusan", rs.getString("status_Keputusan")==null?"":rs.getString("status_Keputusan"));
	    	  h.put("tindakanLanjut", rs.getString("tindakan_Lanjut")==null?"":rs.getString("tindakan_Lanjut"));
	    	  h.put("noMemorandum", rs.getString("no_Memorandum")==null?"":rs.getString("no_Memorandum"));
	    	  list.addElement(h);
	      }

	    } finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getListMJM(){
		  log.info("" +list.size());
		  return list;
	  }
	  
	//*** update data in database
	  public static void updateMJM(Hashtable data) throws Exception {

	    String sql;
	    try
	    {
                db = new Db();
                conn = db.getConnection();
                conn.setAutoCommit(false);
                Statement stmt = db.getStatement();

                SQLRenderer r = new SQLRenderer();

                String idJemaahmenteri = (String) data.get("idJemaahmenteri");
                String IdPermohonan = (String) data.get("idPermohonan");
                String tarikhHantarksu = (String) data.get("tarikhHantarksu");
                String THKSU = "to_date('" + tarikhHantarksu + "','dd/MM/yyyy')";
                String tarikhTerimaksu = (String) data.get("tarikhTerimaksu");
                String TTKSU = "to_date('" + tarikhTerimaksu + "','dd/MM/yyyy')";
                String tarikhHantarPemohon = (String) data.get("tarikhHantarPemohon");
                String THP = "to_date('" + tarikhHantarPemohon + "','dd/MM/yyyy')";
                String tarikhKeputusan = (String) data.get("tarikhKeputusan");
                String TK = "to_date('" + tarikhKeputusan + "','dd/MM/yyyy')";
                String tarikhMsyrtJemaah = (String) data.get("tarikhMsyrtJemaah");
                String TMJ = "to_date('" + tarikhMsyrtJemaah + "','dd/MM/yyyy')";
                String statusKeputusan;
                if (data.get("statusKeputusan") != null) {
                    statusKeputusan = (String) data.get("statusKeputusan");
                } else {
                    statusKeputusan = "";
                }

                String tindakanLanjut;
                if (data.get("tindakanLanjut") != null) {
                    tindakanLanjut = (String) data.get("tindakanLanjut");
                } else {
                    tindakanLanjut = "";
                }

                String noMemorandum;
                if (data.get("noMemorandum") != null) {
                    noMemorandum = (String) data.get("noMemorandum");
                } else {
                    noMemorandum = "";
                }


                r.update("jm.id_Jemaahmenteri", idJemaahmenteri);
                r.update("jm.id_Permohonan", IdPermohonan);
                r.add("jm.tarikh_Hantar_ksu", r.unquote(THKSU));
                r.add("jm.tarikh_Terima_ksu", r.unquote(TTKSU));
                r.add("jm.tarikh_Hantar_Pemohon", r.unquote(THP));
                r.add("jm.tarikh_Keputusan", r.unquote(TK));
                r.add("jm.tarikh_Msyrt_Jemaah", r.unquote(TMJ));
                r.add("jm.status_Keputusan", statusKeputusan);
                r.add("jm.tindakan_Lanjut", tindakanLanjut);
                r.add("jm.no_Memorandum", noMemorandum);

                sql = r.getSQLUpdate("Tblhtpjemaahmenteri jm");
                log.info("FrmPajakanMJMData::Update = " + sql);
                stmt.executeUpdate(sql);
                conn.commit();

        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
	  
	//*** save data in database
    public static void simpanMJM(Hashtable data) throws Exception {

        String sql;
        try {
            long idJemaahmenteri = DB.getNextID("TBLHTPJEMAAHMENTERI_SEQ");
            String IdPermohonan = (String) data.get("idPermohonan");
            String tarikhHantarksu = (String) data.get("tarikhHantarksu");
            String THKSU = "to_date('" + tarikhHantarksu + "','dd/MM/yyyy')";
            String tarikhTerimaksu = (String) data.get("tarikhTerimaksu");
            String TTKSU = "to_date('" + tarikhTerimaksu + "','dd/MM/yyyy')";
            String tarikhHantarPemohon = (String) data.get("tarikhHantarPemohon");
            String THP = "to_date('" + tarikhHantarPemohon + "','dd/MM/yyyy')";
            String tarikhKeputusan = (String) data.get("tarikhKeputusan");
            String TK = "to_date('" + tarikhKeputusan + "','dd/MM/yyyy')";
            String tarikhMsyrtJemaah = (String) data.get("tarikhMsyrtJemaah");
            String TMJ = "to_date('" + tarikhMsyrtJemaah + "','dd/MM/yyyy')";
            String statusKeputusan;
            if (data.get("statusKeputusan") != null) {
                statusKeputusan = (String) data.get("statusKeputusan");
            } else {
                statusKeputusan = "";
            }

            String tindakanLanjut;
            if (data.get("tindakanLanjut") != null) {
                tindakanLanjut = (String) data.get("tindakanLanjut");
            } else {
                tindakanLanjut = "";
            }

            String noMemorandum;
            if (data.get("noMemorandum") != null) {
                noMemorandum = (String) data.get("noMemorandum");
            } else {
                noMemorandum = "";
            }

            db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.add("jm.id_Jemaahmenteri", idJemaahmenteri);
            r.add("jm.id_Permohonan", IdPermohonan);
            r.add("jm.tarikh_Hantar_ksu", r.unquote(THKSU));
            r.add("jm.tarikh_Terima_ksu", r.unquote(TTKSU));
            r.add("jm.tarikh_Hantar_Pemohon", r.unquote(THP));
            r.add("jm.tarikh_Keputusan", r.unquote(TK));
            r.add("jm.tarikh_Msyrt_Jemaah", r.unquote(TMJ));
            r.add("jm.status_Keputusan", statusKeputusan);
            r.add("jm.tindakan_Lanjut", tindakanLanjut);
            r.add("jm.no_Memorandum", noMemorandum);

            sql = r.getSQLInsert("Tblhtpjemaahmenteri jm");
            log.info("FrmPajakanMJMData::Insert = " + sql);
            stmt.executeUpdate(sql);
            conn.commit();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}

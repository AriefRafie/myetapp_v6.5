/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

/**
 *
 * @author Firzan.Fir
 */
public class FrmPembelianPenjualData {
	private static Logger log = Logger.getLogger(FrmPembelianPenjualData.class);
	private static Db db = null;
	private static Connection conn = null;
	private static Vector penjualList = null;
    private static SimpleDateFormat Formatter = new SimpleDateFormat("dd/MM/yyyy");

    	@Deprecated
        public static void SimpanPenjual(Hashtable h) throws Exception{
            try{
                db = new Db();
                conn = db.getConnection();
                conn.setAutoCommit(false);
                Statement stmt = db.getStatement();

                SQLRenderer r = new SQLRenderer();

                long idPemohon = DB.getNextID("TBLHTPPEMOHON_SEQ");
                Date now = new Date();
                SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
                String today = "to_date('" + sdf.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";


                r.add("id_pemohon", idPemohon);
                r.add("id_permohonan", (String)h.get("idPermohonan"));
                
                String ic = (String)h.get("ic");
                if(ic != "" && ic != null){
                    r.add("no_pemohon", ic);
                }
                
                String noPA = (String)h.get("noPA");
                if(noPA != "" && noPA != null){
                     r.add("no_pa", noPA);
                }
                
                
                

                r.add("nama_pemohon", (String)h.get("namaPenjual"));
                r.add("alamat_pemohon1", (String)h.get("alamat1"));
                r.add("alamat_pemohon2", (String)h.get("alamat2"));
                r.add("alamat_pemohon3", (String)h.get("alamat3"));
                r.add("poskod", (String)h.get("poskod"));
                r.add("id_daerah", (String)h.get("daerah"));
                r.add("id_negeri", (String)h.get("negeri"));
                r.add("no_tel", (String)h.get("tel"));
                r.add("no_fax", (String)h.get("fax"));
                r.add("flag_penjualpemilik", (String)h.get("flagPenjual"));
                r.add("id_masuk", (String)h.get("userID"));
                r.add("tarikh_masuk", r.unquote(today));
                r.add("id_kemaskini", (String)h.get("userID"));
                r.add("tarikh_kemaskini", r.unquote(today));

                String sql = r.getSQLInsert("TBLHTPPEMOHON");
                log.info("FrmPembelianPenjualData : SimpanPenjual : " + sql);
                stmt.executeQuery(sql);

                conn.commit();

            }catch(Exception e){
            	conn.rollback();
                e.printStackTrace();
            }
        }
        
        public static void setMaklumatPenjual(String idPermohonan ) throws Exception{
        	String sql;
        	
        	try{
        		penjualList = new Vector();
        		
        		db = new Db();
        		Statement stmt = db.getStatement();
        		
        		sql = "SELECT A.ID_PEMOHON, A.ID_PERMOHONAN, A.NO_PEMOHON, A.NAMA_PEMOHON, ";
        		sql += "A.ALAMAT_PEMOHON1, A.ALAMAT_PEMOHON2, A.ALAMAT_PEMOHON3, A.POSKOD, ";
        		sql += "A.ID_DAERAH, A.ID_NEGERI, A.NO_TEL, A.NO_FAX, A.NO_PA ";
        		sql += "FROM TBLHTPPEMOHON A ";
        		sql += "WHERE A.ID_PERMOHONAN = '" + idPermohonan + "'";
        		
        		log.info("sql penjual : " + sql);
        		
        		ResultSet rs = stmt.executeQuery(sql);
        		
        		while(rs.next()){
        			Hashtable hashPenjual = new Hashtable();
        			hashPenjual.put("idPenjual", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
        			hashPenjual.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
        			
        			//no pemohon is no ic
        			hashPenjual.put("ic", rs.getString("NO_PEMOHON") == null ? "" : rs.getString("NO_PEMOHON"));
        			hashPenjual.put("noPA", rs.getString("NO_PA") == null ? "" : rs.getString("NO_PA"));
        			hashPenjual.put("nama", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
        			hashPenjual.put("alamat1", rs.getString("ALAMAT_PEMOHON1") == null ? "" : rs.getString("ALAMAT_PEMOHON1"));
        			hashPenjual.put("alamat2", rs.getString("ALAMAT_PEMOHON2") == null ? "" : rs.getString("ALAMAT_PEMOHON2"));
        			hashPenjual.put("alamat3", rs.getString("ALAMAT_PEMOHON3") == null ? "" : rs.getString("ALAMAT_PEMOHON3"));
        			hashPenjual.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
        			hashPenjual.put("idDaerah", rs.getString("ID_DAERAH") == null ? "00" : rs.getString("ID_DAERAH"));
        			hashPenjual.put("idNegeri", rs.getString("ID_NEGERI") == null ? "00" : rs.getString("ID_NEGERI"));
        			hashPenjual.put("tel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
        			hashPenjual.put("fax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
        			
        			penjualList.add(hashPenjual);
        		}
        		
        		
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
        	
        }
        
        public static Vector getMaklumatPenjual(){
        	return penjualList;
        }

}

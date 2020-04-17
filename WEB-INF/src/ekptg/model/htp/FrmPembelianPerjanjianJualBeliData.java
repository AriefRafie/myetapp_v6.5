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
public class FrmPembelianPerjanjianJualBeliData {

    	private static Vector list = new Vector();
	private static Logger log = Logger.getLogger(FrmPembelianPerjanjianJualBeliData.class);
	private static Db db = null;
	private static Connection conn = null;
        private static SimpleDateFormat FormatTarikh =  new SimpleDateFormat("dd/MM/yyyy");
        private static Vector listPerjanjian;
        private static Vector listDrafPerjanjian;
        private static Vector maklumatDraf;
        private static Vector listPerjanjianTambahan;
        private static Vector maklumatPerjanjianTambahan;

        @Deprecated
        public static void SimpanDrafKelulusan(Hashtable h) throws Exception{

            String sql;
            Statement stmt;
            try{
                db = new Db();
                conn = db.getConnection();
                conn.setAutoCommit(false);
                stmt = db.getStatement();

                long idDrafPerjanjian = DB.getNextID("TBLHTPDERAFPERJANJIAN_SEQ");

                String tarikhTerima = (String)h.get("tarikhTerima");
                String tarikhHantar = (String)h.get("tarikhHantar");

                String TTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
                String THantar = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";


                SQLRenderer r = new SQLRenderer();
                r.add("id_derafperjanjian", idDrafPerjanjian);
                r.add("id_permohonan", (String)h.get("idPermohonan"));

                if(tarikhTerima != null && tarikhTerima != ""){
                    r.add("tarikh_terima_perjanjian", r.unquote(TTerima));
                }
                else{
                    r.add("tarikh_terima_perjanjian", tarikhTerima);
                }

                if(tarikhHantar != null && !tarikhHantar.equals("")){
                    r.add("tarikh_hantar_deraf", r.unquote(THantar));
                }
                else{
                     r.add("tarikh_hantar_deraf", tarikhHantar);
                }

                r.add("id_masuk", (String)h.get("userID"));

                r.add("ulasan_seksyen", (String)h.get("ulasan"));

                sql = r.getSQLInsert("TBLHTPDERAFPERJANJIAN");

                log.info("FrmPembelianPerjanjianJualBeliData : SimpanDrafKelulusan : " + sql);

                stmt.executeUpdate(sql);


                conn.commit();

            }catch(Exception e){
                conn.rollback();
                e.printStackTrace();
            }

            finally{
                conn.close();
                db.close();
            }
        }
        @Deprecated
        public static void SimpanUpdateDraf(Hashtable h) throws Exception{
        	String sql;
        	try{
        		db = new Db();
        		conn = db.getConnection();
        		conn.setAutoCommit(false);
        		Statement stmt = db.getStatement();
        		
        		SQLRenderer r = new SQLRenderer();
        		r.update("ID_DERAFPERJANJIAN", h.get("idDraf").toString());
        		
        		if(h.get("tarikhTerima") != null && h.get("tarikhTerima") != "" ){
        			r.add("TARIKH_TERIMA_PERJANJIAN", r.unquote("to_date('" + h.get("tarikhTerima") + "','dd/MM/yyyy')"));
        		}
        		
        		if(h.get("tarikhHantar") != null && h.get("tarikhHantar") != "" ){
        			r.add("TARIKH_HANTAR_DERAF", r.unquote("to_date('" + h.get("tarikhHantar") + "','dd/MM/yyyy')"));
        		}
        		
        		r.add("ULASAN_SEKSYEN", h.get("ulasan") );
        		
        		sql = r.getSQLUpdate("TBLHTPDERAFPERJANJIAN");
        		
        		stmt.executeUpdate(sql);
        		
        		conn.commit();
        		
        	}catch(Exception e){
        		conn.rollback();
        		e.printStackTrace();
        	}
        }

        public static void setListDrafKelulusan(String idPermohonan) throws Exception{

            String sql;

            try{
            	listDrafPerjanjian = new Vector();
                db = new Db();
//                conn = db.getConnection();
                Statement stmt = db.getStatement();
                sql = "SELECT A.ID_DERAFPERJANJIAN, A.ID_PERMOHONAN, A.TARIKH_TERIMA_PERJANJIAN, ";
                sql += "A.TARIKH_HANTAR_DERAF, A.ULASAN_SEKSYEN ";
                sql += "FROM TBLHTPDERAFPERJANJIAN A ";
                sql += "WHERE A.ID_PERMOHONAN = '" + idPermohonan + "'";
                
               // PreparedStatement pstmt = conn.prepareStatement(sql);
                
               // pstmt.setLong(1, Long.parseLong(idPermohonan));
                
                ResultSet rs = stmt.executeQuery(sql);
                
                log.info("senarai draf : " + sql);
                
//                ResultSet rs = pstmt.executeQuery();
                
                int bil = 1;
                
                while(rs.next()){
                	Hashtable hashDraf = new Hashtable();
                	hashDraf.put("bil", bil);
                	hashDraf.put("idDraf", rs.getLong("ID_DERAFPERJANJIAN") == 0L ? "" : rs.getLong("ID_DERAFPERJANJIAN") );
                	hashDraf.put("idPermohonan", rs.getLong("ID_PERMOHONAN") == 0L ? "" : rs.getLong("ID_PERMOHONAN") );
                	hashDraf.put("tarikhTerima", rs.getDate("TARIKH_TERIMA_PERJANJIAN") == null ? "" : FormatTarikh.format(rs.getDate("TARIKH_TERIMA_PERJANJIAN")) );
                	hashDraf.put("tarikhHantar", rs.getDate("TARIKH_HANTAR_DERAF") == null ? "" : FormatTarikh.format(rs.getDate("TARIKH_HANTAR_DERAF")) );
                	hashDraf.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN") );
                	listDrafPerjanjian.addElement(hashDraf);
                	
                	bil++;
                }
                
                



            }catch(Exception e){
                e.printStackTrace();

            }
        }
        
        public static Vector getSenaraiDrafPerjanjian(){
        	return listDrafPerjanjian;
        }
        
        public static void setMaklumatDraf(String idDraf) throws Exception{

            String sql;

            try{
            	maklumatDraf = new Vector();
                db = new Db();
                Statement stmt = db.getStatement();
                sql = "SELECT A.ID_DERAFPERJANJIAN, A.ID_PERMOHONAN, A.TARIKH_TERIMA_PERJANJIAN, ";
                sql += "A.TARIKH_HANTAR_DERAF, A.ULASAN_SEKSYEN ";
                sql += "FROM TBLHTPDERAFPERJANJIAN A ";
                sql += "WHERE A.ID_DERAFPERJANJIAN = '" + idDraf + "'";

                ResultSet rs = stmt.executeQuery(sql);
                
                log.info("maklumat draf : " + sql);
   
                while(rs.next()){
                	Hashtable hashDraf = new Hashtable();
                	hashDraf.put("idDraf", rs.getLong("ID_DERAFPERJANJIAN") == 0L ? "" : rs.getLong("ID_DERAFPERJANJIAN") );
                	hashDraf.put("idPermohonan", rs.getLong("ID_PERMOHONAN") == 0L ? "" : rs.getLong("ID_PERMOHONAN") );
                	hashDraf.put("tarikhTerima", rs.getDate("TARIKH_TERIMA_PERJANJIAN") == null ? "" : FormatTarikh.format(rs.getDate("TARIKH_TERIMA_PERJANJIAN")) );
                	hashDraf.put("tarikhHantar", rs.getDate("TARIKH_HANTAR_DERAF") == null ? "" : FormatTarikh.format(rs.getDate("TARIKH_HANTAR_DERAF")) );
                	hashDraf.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN") );
                	maklumatDraf.addElement(hashDraf);
            
                }

            }catch(Exception e){
                e.printStackTrace();

            }
        }
        
        public static Vector getMaklumatDraf(){
        	return maklumatDraf;
        }
        @Deprecated
        public static void SimpanPerjanjianJualBeli(Hashtable h) throws Exception{


            try{
                db = new Db();
                conn = db.getConnection();
                conn.setAutoCommit(false);
                Statement stmt = db.getStatement();
                SQLRenderer r = new SQLRenderer();


                //untuk table htpperjanjian
                long idPerjanjian = DB.getNextID("TBLHTPPERJANJIAN_SEQ");
                Date now = new Date();
		SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
		String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
                String tarikhPerjanjian = (String)h.get("TarikhPerjanjian");
                String tPerjanjian = "to_date('" + tarikhPerjanjian + "','dd/MM/yyyy')";
                String tarikhTandatangan = (String)h.get("TarikhPTP");
                String tTandatangan = "to_date('" + tarikhTandatangan + "','dd/MM/yyyy')";

                r.add("id_perjanjian", idPerjanjian);
                r.add("id_permohonan", (String)h.get("idPermohonan"));
                r.add("no_rujperjanjian", (String)h.get("NoKontrak"));

               

                if (!tarikhPerjanjian.equals(null) && !tarikhPerjanjian.equals("")){
                    r.add("tarikh_perjanjian", r.unquote(tPerjanjian));
                }
                else{
                    r.add("tarikh_perjanjian", tarikhPerjanjian);
                }

                if(!tarikhTandatangan.equals(null)  && !tarikhTandatangan.equals("") ){
                    r.add("tarikh_tandatangan", r.unquote(tTandatangan));
                }
                else{
                    r.add("tarikh_tandatangan", tarikhTandatangan);
                } 
                
                r.add("id_masuk",(String)h.get("userID"));
                r.add("tarikh_masuk", r.unquote(today));
                r.add("id_kemaskini",(String)h.get("userID"));
                r.add("tarikh_kemaskini", r.unquote(today));

                String sql = r.getSQLInsert("TBLHTPPERJANJIAN");
                log.info(sql);
                stmt.executeUpdate(sql);

                SQLRenderer r2 = new SQLRenderer();

                long idPerjanjianPembelian = DB.getNextID("TBLHTPPERJANJIANPEMBELIAN_SEQ");
                String tarikhBorang14a = (String)h.get("TarikhSempurna14A");
                String tBorang14a = "to_date('" + tarikhBorang14a + "','dd/MM/yyyy')";
//                String tarikhSerahBangunan = (String)h.get("tarikhSerahBangunan");
//                String tSB = "to_date('" + tarikhSerahBangunan + "','dd/MM/yyyy')";

                r2.add("id_perjanjianpembelian", idPerjanjianPembelian);
                r2.add("id_perjanjian", idPerjanjian);
                r2.add("nilai_tanah", (String)h.get("nilaiTanah"));
                r2.add("harga_beli", (String)h.get("HargaBeli"));
                r2.add("bil_hakmilikbeli", (String)h.get("jumlahHakmilik"));

                if (!tarikhBorang14a.equals(null) && !tarikhBorang14a.equals("")){
                    r2.add("tarikh_borang14a", r2.unquote(tBorang14a) );
                }else{
                    r2.add("tarikh_borang14a", tarikhBorang14a);
                }
                
                r2.add("harga_tambahan", (String)h.get("HargaTambahan"));

//                if(!tarikhSerahBangunan.equals(null) && !tarikhSerahBangunan.equals("")){
//                    r2.add("tarikh_serah_bangunan", r2.unquote(tSB) );
//                }else{
//                    r2.add("tarikh_serah_bangunan", tarikhSerahBangunan);
//                }
        
                r2.add("nilai_bangunan", (String)h.get("nilaiBangunan"));
                r2.add("bil_unit_beli", (String)h.get("bilUnitBangunan"));
                r2.add("id_masuk", (String)h.get("userID"));
                r2.add("tarikh_masuk", r2.unquote(today));
                r2.add("id_kemaskini", (String)h.get("userID"));
                r2.add("tarikh_kemaskini", r2.unquote(today));

                String sql2 = r2.getSQLInsert("TBLHTPPERJANJIANPEMBELIAN");
                log.info(sql2);
                stmt.executeQuery(sql2);

                
                /*
                SQLRenderer r3 = new SQLRenderer();

                long idPerjanjianTambahan = DB.getNextID("TBLHTPPERJANJIANTAMBAHAN_SEQ");
                String tarikhPerjanjianTambahan = (String)h.get("tarikhPTambahan");
                String tPTambahan = "to_date('" + tarikhPerjanjianTambahan + "','dd/MM/yyyy')";
                String tarikhSerahPerjanjian = (String)h.get("TarikhSerahPTambahan");
                String tSerahPerjanjian = "to_date('" + tarikhSerahPerjanjian + "','dd/MM/yyyy')";

                r3.add("id_perjanjiantambahan", idPerjanjianTambahan);
                r3.add("id_permohonan",(String)h.get("idPermohonan") );
                r3.add("id_perjanjian",idPerjanjian);

                if(!tarikhSerahPerjanjian.equals(null) && !tarikhPerjanjianTambahan.equals("")){
                    r3.add("tarikh_terima", r3.unquote(tSerahPerjanjian));
                }else{
                    r3.add("tarikh_terima", tarikhSerahPerjanjian);
                }

                if(!tarikhPerjanjianTambahan.equals(null) && !tarikhPerjanjianTambahan.equals("")){
                    r3.add("tarikh_perjanjian", r3.unquote(tPTambahan) );
                }else{
                    r3.add("tarikh_perjanjian", tarikhPerjanjianTambahan);
                }

                r3.add("sebab", (String)h.get("Sebab"));
                r3.add("tempoh", (String)h.get("TempohPerjanjian"));
                r3.add("ulasan", (String)h.get("Alasan"));
                r3.add("id_masuk", (String)h.get("userID"));
                r3.add("tarikh_masuk", r3.unquote(today));
                r3.add("id_kemaskini", (String)h.get("userID"));
                r3.add("tarikh_kemaskini", r3.unquote(today));
                
                String sql3 = r3.getSQLInsert("TBLHTPPERJANJIANTAMBAHAN");
                log.info(sql3);
                stmt.executeUpdate(sql3);
				*/
                conn.commit();

            }catch(Exception e){
                conn.rollback();
                e.printStackTrace();
            }
            finally{
               if(conn != null) conn.close();
               if(db != null) db.close();
            }

        }
        
        public static void SimpanUpdatePerjanjianJualBeli(Hashtable h) throws Exception{
        	String sql;
        	try{
        		db = new Db();
        		conn = db.getConnection();
        		conn.setAutoCommit(false);
        		Statement stmt = db.getStatement();
        		
        		//untuk table htpperjanjian
        		SQLRenderer r = new SQLRenderer();
        		r.update("id_permohonan", h.get("idPermohonan").toString());
                r.add("no_rujperjanjian", (String)h.get("NoKontrak"));
                
                String tarikhPerjanjian = (String)h.get("TarikhPerjanjian");
                String tPerjanjian = "to_date('" + tarikhPerjanjian + "','dd/MM/yyyy')";
                String tarikhTandatangan = (String)h.get("TarikhPTP");
                String tTandatangan = "to_date('" + tarikhTandatangan + "','dd/MM/yyyy')";

                if (!h.get("TarikhPerjanjian").equals(null) && !h.get("TarikhPerjanjian").equals("")){
                    r.add("tarikh_perjanjian", r.unquote("to_date('" + h.get("TarikhPerjanjian") + "','dd/MM/yyyy')"));
                }

                if(!h.get("TarikhPTP").equals(null)  && !h.get("TarikhPTP").equals("") ){
                    r.add("tarikh_tandatangan", r.unquote("to_date('" + h.get("TarikhPTP") + "','dd/MM/yyyy')"));
                }
 
                r.add("id_masuk",(String)h.get("userID"));
                r.add("tarikh_masuk", r.unquote("SYSDATE"));
                r.add("id_kemaskini",(String)h.get("userID"));
                r.add("tarikh_kemaskini", r.unquote("SYSDATE"));

                sql = r.getSQLUpdate("TBLHTPPERJANJIAN");
                log.info(sql);
                stmt.executeUpdate(sql);

                //TBLHTPPERJANJIANPEMBELIAN
                r = new SQLRenderer();
                r.update("id_perjanjian", h.get("idPerjanjian").toString());

                String tarikhBorang14a = (String)h.get("TarikhSempurna14A");
                String tBorang14a = "to_date('" + tarikhBorang14a + "','dd/MM/yyyy')";

                r.add("nilai_tanah", (String)h.get("nilaiTanah"));
                r.add("harga_beli", (String)h.get("HargaBeli"));
                r.add("bil_hakmilikbeli", (String)h.get("jumlahHakmilik"));

                if (!h.get("TarikhSempurna14A").equals(null) && !h.get("TarikhSempurna14A").equals("")){
                    r.add("tarikh_borang14a", r.unquote("to_date('" + h.get("TarikhSempurna14A") + "','dd/MM/yyyy')") );
                }
                
                r.add("harga_tambahan", (String)h.get("HargaTambahan"));


                r.add("nilai_bangunan", (String)h.get("nilaiBangunan"));
                r.add("bil_unit_beli", (String)h.get("bilUnitBangunan"));
                r.add("id_masuk", (String)h.get("userID"));
                r.add("tarikh_masuk", r.unquote("SYSDATE"));
                r.add("id_kemaskini", (String)h.get("userID"));
                r.add("tarikh_kemaskini", r.unquote("SYSDATE"));

                sql = r.getSQLUpdate("TBLHTPPERJANJIANPEMBELIAN");
                log.info(sql);
                stmt.executeQuery(sql);
        		
        		conn.commit();
        		
        	}catch(Exception e){
        		conn.rollback();
        		e.printStackTrace();
        	}
        }

        public static void setListPerjanjianJualBeli(String idPermohonan){

            String sql;
            try{
                listPerjanjian = new Vector();
                db = new Db();
                Statement stmt = db.getStatement();

//                sql = "SELECT P.ID_PERJANJIAN, P.NO_RUJPERJANJIAN, P.TARIKH_TANDATANGAN, P.TARIKH_PERJANJIAN,";
//                sql += "PP.NILAI_TANAH, PP.NILAI_BANGUNAN, PP.HARGA_BELI, PP.HARGA_TAMBAHAN, ";
//                sql += "PP.BIL_HAKMILIKBELI, PP.TARIKH_BORANG14A, PP.TARIKH_SERAH_BANGUNAN, ";
//                sql += "PT.TARIKH_TERIMA as tarikh_terimaPerjanjian, PT.TARIKH_PERJANJIAN as tarikh_perjanjiantambahan, PT.SEBAB, PT.TEMPOH, PT.ULASAN ";
//                sql += "FROM TBLHTPPERJANJIAN P, TBLHTPPERJANJIANTAMBAHAN PT, TBLHTPPERJANJIANPEMBELIAN PP ";
//                sql += "WHERE P.ID_PERJANJIAN = PT.ID_PERJANJIAN ";
//                sql += "AND PT.ID_PERJANJIAN = PP.ID_PERJANJIAN ";
//                sql += "AND P.ID_PERMOHONAN = " + idPermohonan;
                
              sql = "SELECT P.ID_PERJANJIAN, P.NO_RUJPERJANJIAN, P.TARIKH_TANDATANGAN, ";
              sql += "P.TARIKH_PERJANJIAN, PP.NILAI_TANAH, PP.NILAI_BANGUNAN, ";
              sql += "PP.HARGA_BELI, PP.HARGA_TAMBAHAN, PP.BIL_HAKMILIKBELI, ";
              sql += "PP.TARIKH_BORANG14A, PP.TARIKH_SERAH_BANGUNAN, PP.BIL_UNIT_BELI ";
              sql += "FROM TBLHTPPERJANJIAN P, TBLHTPPERJANJIANPEMBELIAN PP  ";
              sql += "WHERE P.ID_PERJANJIAN = PP.ID_PERJANJIAN  ";
              sql += "AND P.ID_PERMOHONAN = '" + idPermohonan + "'";

                log.info("perjanjian jual beli : " + sql);
                
                
                ResultSet rs = stmt.executeQuery(sql);

                int bil = 1;
                while(rs.next()){
                    Hashtable h = new Hashtable();

                    h.put("bil", bil);
                    h.put("idPerjanjian",rs.getString("ID_PERJANJIAN") == null ? "" : rs.getString("ID_PERJANJIAN"));
                    h.put("noRujPerjanjian", rs.getString("NO_RUJPERJANJIAN") == null ? "" : rs.getString("NO_RUJPERJANJIAN"));
                    h.put("tarikhPTP", rs.getDate("TARIKH_TANDATANGAN") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_TANDATANGAN")));
                    h.put("tarikhPerjanjian", rs.getDate("TARIKH_PERJANJIAN") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_PERJANJIAN")));
                    h.put("nilaiTanah", rs.getString("NILAI_TANAH") == null ? "" : rs.getString("NILAI_TANAH"));
                    h.put("nilaiBangunan", rs.getString("NILAI_BANGUNAN") == null ? "" : rs.getString("NILAI_BANGUNAN"));
                    h.put("hargaBeli", rs.getString("HARGA_BELI") == null ? "" : rs.getString("HARGA_BELI"));
                    h.put("hargaTambahan", rs.getString("HARGA_TAMBAHAN") == null ? "" : rs.getString("HARGA_TAMBAHAN"));
                    h.put("bilHakmilikBeli", rs.getString("BIL_HAKMILIKBELI") == null ? "" : rs.getString("BIL_HAKMILIKBELI"));
                    h.put("tarikhBorang14a", rs.getDate("TARIKH_BORANG14A") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_BORANG14A")));
                    h.put("tarikhSerahBangunan", rs.getDate("TARIKH_SERAH_BANGUNAN") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_SERAH_BANGUNAN")));
                    h.put("bilUnitBangunan", rs.getString("BIL_UNIT_BELI") == null ? "" : rs.getString("BIL_UNIT_BELI"));
                    
                    //h.put("tarikhTerimaPerjanjian", rs.getDate("tarikh_terimaperjanjian") == null ? "" :FormatTarikh.format(rs.getDate("tarikh_terimaperjanjian")));
                    //h.put("tarikhPerjanjianTambahan", rs.getDate("tarikh_perjanjiantambahan") == null ? "" :FormatTarikh.format(rs.getDate("tarikh_perjanjiantambahan")));
                    //h.put("sebab", rs.getString("sebab") == null ? "" : rs.getString("sebab"));
                    //h.put("tempoh", rs.getString("tempoh") == null ? "" : rs.getString("tempoh"));
                    //h.put("ulasan", rs.getString("ulasan") == null ? "" : rs.getString("ulasan"));
                    listPerjanjian.addElement(h);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public static Vector getListPerjanjianJualBeli(){
            log.info("FrmPembelianPerjanjianJualBeliData : getListPerjanjianJualBeli : " + listPerjanjian.size());
            return listPerjanjian;
        }
        
        public static void setListPerjanjianTambahan(String idPermohonan ) throws Exception{
        	String sql;
        	try{
        		listPerjanjianTambahan = new Vector();
        		db = new Db();
        		Statement stmt = db.getStatement();

        		 sql = "SELECT A.ID_PERJANJIANTAMBAHAN, A.ID_PERMOHONAN, A.ID_PERJANJIAN, ";
                 sql += "A.TARIKH_TERIMA, A.TARIKH_PERJANJIAN, A.SEBAB, A.ULASAN, A.TEMPOH, A.TARIKH_AKHIRPERJANJIAN ";
                 sql += "FROM TBLHTPPERJANJIANTAMBAHAN A ";
                 sql += "WHERE A.ID_PERMOHONAN = '" + idPermohonan + "'";
                 
                 log.info("perjanjian tambahan list : " + sql);
  
                 int bil = 1;
                 
                 ResultSet rs = stmt.executeQuery(sql);
                 
                 while(rs.next()){
                	 Hashtable h = new Hashtable();
                	 h.put("bil", bil);
                	 h.put("idPerjanjiantambahan", rs.getString("ID_PERJANJIANTAMBAHAN") == null ? "" : rs.getString("ID_PERJANJIANTAMBAHAN") );
                	 h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN") );
                	 h.put("idPerjanjian", rs.getString("ID_PERJANJIAN") == null ? "" : rs.getString("ID_PERJANJIAN") );
//                	 h.put("tarikhTerimaPerjanjian", rs.getDate("TARIKH_TERIMA") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_TERIMA")));
                	 h.put("tarikhAkhir", rs.getDate("TARIKH_AKHIRPERJANJIAN") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_AKHIRPERJANJIAN")));
                     h.put("tarikhPTambahan", rs.getDate("TARIKH_PERJANJIAN") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_PERJANJIAN")));
                     h.put("sebab", rs.getString("SEBAB") == null ? "" : rs.getString("SEBAB"));
                     h.put("tempoh", rs.getString("TEMPOH") == null ? "" : rs.getString("TEMPOH"));
                     h.put("alasan", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
                     listPerjanjianTambahan.addElement(h);
                     bil++;
                 }
        		
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        
        public static Vector getSenaraiPerjanjianTambahan(){
        	return listPerjanjianTambahan;
        }
        
        public static void setMaklumatPerjanjianTambahan(String idPerjanjianTambahan) throws Exception{
        	
        	String sql;
        	try{
        		maklumatPerjanjianTambahan = new Vector();
        		db = new Db();
        		Statement stmt = db.getStatement();

        		 sql = "SELECT A.ID_PERJANJIANTAMBAHAN, A.ID_PERMOHONAN, A.ID_PERJANJIAN, A.TARIKH_HANTAR, ";
                 sql += "A.TARIKH_TERIMA, A.TARIKH_PERJANJIAN, A.SEBAB, A.ULASAN, A.TEMPOH, A.TARIKH_AKHIRPERJANJIAN ";
                 sql += "FROM TBLHTPPERJANJIANTAMBAHAN A ";
                 sql += "WHERE A.ID_PERJANJIANTAMBAHAN = '" + idPerjanjianTambahan + "'";
                 
                 log.info("perjanjian tambahan : " + sql);
  
                 ResultSet rs = stmt.executeQuery(sql);
                 
                 while(rs.next()){
                	 Hashtable h = new Hashtable();
                	 
                	 h.put("idPerjanjiantambahan", rs.getString("ID_PERJANJIANTAMBAHAN") == null ? "" : rs.getString("ID_PERJANJIANTAMBAHAN") );
                	 h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN") );
                	 h.put("idPerjanjian", rs.getString("ID_PERJANJIAN") == null ? "" : rs.getString("ID_PERJANJIAN") );
                	 h.put("tarikhTerimaPerjanjian", rs.getDate("TARIKH_TERIMA") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_TERIMA")));
                     h.put("tarikhPTambahan", rs.getDate("TARIKH_PERJANJIAN") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_PERJANJIAN")));
                     h.put("TarikhAkhirPTambahan", rs.getDate("TARIKH_AKHIRPERJANJIAN") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_AKHIRPERJANJIAN")));
                     h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? "" :FormatTarikh.format(rs.getDate("TARIKH_HANTAR")));
                     h.put("sebab", rs.getString("sebab") == null ? "" : rs.getString("sebab"));
                     h.put("tempoh", rs.getString("tempoh") == null ? "" : rs.getString("tempoh"));
                     h.put("alasan", rs.getString("ulasan") == null ? "" : rs.getString("ulasan"));
                     maklumatPerjanjianTambahan.addElement(h);
                 }
        		
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        
        public static Vector getMaklumatPerjanjianTambahan(){
        	return maklumatPerjanjianTambahan;
        }
        
        public static void SimpanMaklumatPerjanjianTambahan(Hashtable h) throws Exception{
        	String sql;
        	String idPerjanjian = null;
        	try{
        		db = new Db();
        		conn = db.getConnection();
        		Statement stmt = db.getStatement();
        		conn.setAutoCommit(false);
        		
        		//TBLHTPPERJANJIAN
        		SQLRenderer r = new SQLRenderer();
        		sql = "SELECT A.ID_PERJANJIAN ";
        		sql += "FROM TBLHTPPERJANJIAN A ";
        		sql += "WHERE A.ID_PERMOHONAN = '" + h.get("idPermohonan").toString() + "'";
        		
        		ResultSet rs = stmt.executeQuery(sql);
        		
        		while (rs.next()){
        			idPerjanjian = rs.getString("ID_PERJANJIAN");
        		}
        		
        	    
        		//TBLHTPPERJANJIANTAMBAHAN
                SQLRenderer r3 = new SQLRenderer();

                long idPerjanjianTambahan = DB.getNextID("TBLHTPPERJANJIANTAMBAHAN_SEQ");

                r3.add("id_perjanjiantambahan", idPerjanjianTambahan);
                r3.add("id_permohonan",(String)h.get("idPermohonan") );
                r3.add("id_perjanjian",idPerjanjian);

//                if(!h.get("TarikhSerahPTambahan").equals(null) && !h.get("TarikhSerahPTambahan").equals("")){
//                    r3.add("tarikh_terima", r3.unquote("to_date('" + h.get("TarikhSerahPTambahan") + "','dd/MM/yyyy')"));
//                }

                if(!h.get("tarikhPTambahan").equals(null) && !h.get("tarikhPTambahan").equals("")){
                    r3.add("tarikh_perjanjian", r3.unquote("to_date('" + h.get("tarikhPTambahan") + "','dd/MM/yyyy')") );
                }
                
                if(!h.get("tarikhAkhir").equals(null) && !h.get("tarikhAkhir").equals("")){
                    r3.add("tarikh_akhirperjanjian", r3.unquote("to_date('" + h.get("tarikhAkhir") + "','dd/MM/yyyy')") );
                }
                
                if (!h.get("tarikhHantar").toString().equals(null) && !h.get("tarikhHantar").toString().equals("")){
                    r.add("tarikh_hantar", r.unquote("to_date('" + h.get("tarikhHantar") + "','dd/MM/yyyy')") );
                }

                r3.add("sebab", (String)h.get("sebab"));
                r3.add("tempoh", (String)h.get("TempohPerjanjian"));
                r3.add("ulasan", (String)h.get("alasan"));
                r3.add("id_masuk", (String)h.get("userID"));
                r3.add("tarikh_masuk", r3.unquote("SYSDATE"));
                r3.add("id_kemaskini", (String)h.get("userID"));
                r3.add("tarikh_kemaskini", r3.unquote("SYSDATE"));
                
                String sql3 = r3.getSQLInsert("TBLHTPPERJANJIANTAMBAHAN");
                log.info(sql3);
                stmt.executeUpdate(sql3);
				
        		
        		conn.commit();
        		
        		
        	}catch(Exception e){
        		conn.rollback();
        		e.printStackTrace();
        	}
        }
        
        public static void UpdateMaklumatPerjanjianTambahan(Hashtable h)throws Exception{
        	String sql;
        	try{
        		db = new Db();
        		conn = db.getConnection();
        		conn.setAutoCommit(false);
        		Statement stmt = db.getStatement();
        		
        		// TBLHTPPERJANJIANTAMBAHAN
        		SQLRenderer r = new SQLRenderer();
        		r.update("ID_PERJANJIANTAMBAHAN", h.get("idPerjanjianTambahan").toString());
               
        		 if (!h.get("tarikhPTambahan").equals(null) && !h.get("tarikhPTambahan").equals("")){
                     r.add("tarikh_perjanjian", r.unquote("to_date('" + h.get("tarikhPTambahan") + "','dd/MM/yyyy')") );
                 }
        		 
        		 if (!h.get("TarikhSerahPTambahan").equals(null) && !h.get("TarikhSerahPTambahan").equals("")){
                     r.add("tarikh_terima", r.unquote("to_date('" + h.get("TarikhSerahPTambahan") + "','dd/MM/yyyy')") );
                 }
        		 
        		 if (!h.get("tarikhHantar").equals(null) && !h.get("tarikhHantar").equals("")){
                     r.add("tarikh_hantar", r.unquote("to_date('" + h.get("tarikhHantar") + "','dd/MM/yyyy')") );
                 }
                
        		 r.add("sebab", (String)h.get("Sebab"));
                 r.add("tempoh", (String)h.get("TempohPerjanjian"));
                 r.add("ulasan", (String)h.get("Alasan"));
 
                r.add("id_masuk",(String)h.get("userID"));
                r.add("tarikh_masuk", r.unquote("SYSDATE"));
                r.add("id_kemaskini",(String)h.get("userID"));
                r.add("tarikh_kemaskini", r.unquote("SYSDATE"));

                sql = r.getSQLUpdate("TBLHTPPERJANJIANTAMBAHAN");
                log.info(sql);
                stmt.executeUpdate(sql);
        		
                conn.commit();
        		
        	}catch(Exception e){
        		conn.rollback();
        		e.printStackTrace();
        	}
        	
        }


}

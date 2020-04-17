package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmPajakanPemohonData {
    private static Vector list = new Vector();
    private static Logger log = Logger.getLogger(FrmPajakanPemohonData.class);
    private static Db db;
    private static Connection conn;
	
    //*** query data from database
    public static void setListPemohon(String idPermohonan) throws Exception {
        list.clear();
        String sql;
        try {
            db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();

            r.add("p.id_Pemohon");
            r.add("p.id_Permohonan");
            r.add("p.no_Pemohon");
            r.add("p.nama_Pemohon");
            r.add("p.alamat_Pemohon1");
            r.add("p.alamat_Pemohon2");
            r.add("p.alamat_Pemohon3");
            r.add("p.Poskod");
            r.add("p.id_Daerah");
            r.add("p.id_Negeri");
            r.add("p.No_Tel");
            r.add("p.No_Fax");

            r.add("p.id_Permohonan", idPermohonan, "=");

            sql = r.getSQLSelect("Tblhtppemohon p");
            ResultSet rs = stmt.executeQuery(sql);
            log.info("FrmPajakanPemohonData : setListPemohon : " + sql);

            Hashtable h;

            while (rs.next()) {
                h = new Hashtable();
                h.put("IdPemohon", rs.getString("id_Pemohon"));
                h.put("NoPemohon", rs.getString("no_Pemohon"));
                h.put("NamaPemohon", rs.getString("nama_Pemohon"));
                h.put("Alamat1", rs.getString("alamat_Pemohon1"));
                if (rs.getString("alamat_Pemohon2") != null) {
                    h.put("Alamat2", rs.getString("alamat_Pemohon2"));
                } else {
                    h.put("Alamat2", "");
                }
                if (rs.getString("alamat_Pemohon3") != null) {
                    h.put("Alamat3", rs.getString("alamat_Pemohon3"));
                } else {
                    h.put("Alamat3", "");
                }
                h.put("Poskod", rs.getString("Poskod"));
                if (rs.getString("id_Daerah") != null) {
                    h.put("IdDaerah", rs.getString("id_Daerah"));
                } else {
                    h.put("IdDaerah", "");
                }
                h.put("IdNegeri", rs.getString("id_Negeri"));
                if (rs.getString("No_Tel") != null) {
                    h.put("NoTel", rs.getString("No_Tel"));
                } else {
                    h.put("NoTel", "");
                }
                if (rs.getString("No_Fax") != null) {
                    h.put("NoFax", rs.getString("No_Fax"));
                } else {
                    h.put("NoFax", "");
                }
                list.addElement(h);
            }

        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
	  
    public static Vector getListPemohon() {
        log.info(list.size());
        return list;
    }
	  
    //*** update data in database
    public static void update(Hashtable data) throws Exception {

        String sql;
        try {
            String IdPemohon = (String) data.get("idPemohon");
            String IdPermohonan = (String) data.get("idPermohonan");
            String NoPemohon = (String) data.get("noPemohon");
            String Nama = (String) data.get("Nama");
            String Alamat1 = (String) data.get("alamat1");
            String Alamat2;
            if (data.get("alamat2") != null) {
                Alamat2 = (String) data.get("alamat2");
            } else {
                Alamat2 = "";
            }
            String Alamat3;
            if (data.get("alamat3") != null) {
                Alamat3 = (String) data.get("alamat3");
            } else {
                Alamat3 = "";
            }

            String Poskod = (String) data.get("poskod");
            int IdDaerah = (Integer) data.get("idDaerah");
            int IdNegeri = (Integer) data.get("idNegeri");
            String NoTelefon;
            if (data.get("noTelefon") != null) {
                NoTelefon = (String) data.get("noTelefon");
            } else {
                NoTelefon = "";
            }
            String NoFax;
            if (data.get("noFax") != null) {
                NoFax = (String) data.get("noFax");
            } else {
                NoFax = "";
            }

            db = new Db();
            Statement stmt = db.getStatement();
            conn = db.getConnection();
            conn.setAutoCommit(false);

            SQLRenderer r = new SQLRenderer();
            r.update("p.id_Pemohon", IdPemohon);
            r.update("p.id_Permohonan", IdPermohonan);
            r.add("p.no_Pemohon", NoPemohon);
            r.add("p.nama_Pemohon", Nama);
            r.add("p.alamat_Pemohon1", Alamat1);
            r.add("p.alamat_Pemohon2", Alamat2);
            r.add("p.alamat_Pemohon3", Alamat3);
            r.add("p.Poskod", Poskod);
            r.add("p.id_Daerah", IdDaerah);
            r.add("p.id_Negeri", IdNegeri);
            r.add("p.No_Tel", NoTelefon);
            r.add("p.No_Fax", NoFax);
            sql = r.getSQLUpdate("Tblhtppemohon p");
            log.info("FrmPajakanPemohonData::Update::Tblhtppemohon = " + sql);
            stmt.executeUpdate(sql);

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
            if (conn.isClosed()){
                conn.close();
            }
        }
    }
	  
	//*** save data in database
    public static void simpan(Hashtable data) throws Exception {
        String sql;
        try {
            long IdPemohon = DB.getNextID("TBLHTPPEMOHON_SEQ");
            String IdPermohonan = (String) data.get("idPermohonan");
            String NoPemohon = (String) data.get("noPemohon");
            String Nama = (String) data.get("Nama");
            String Alamat1 = (String) data.get("alamat1");
            String Alamat2;
            if (data.get("alamat2") != null) {
                Alamat2 = (String) data.get("alamat2");
            } else {
                Alamat2 = "";
            }
            String Alamat3;
            if (data.get("alamat3") != null) {
                Alamat3 = (String) data.get("alamat3");
            } else {
                Alamat3 = "";
            }
            String Poskod = (String) data.get("poskod");
            int IdDaerah = (Integer) data.get("idDaerah");
            int IdNegeri = (Integer) data.get("idNegeri");
            String NoTelefon;
            if (data.get("noTelefon") != null) {
                NoTelefon = (String) data.get("noTelefon");
            } else {
                NoTelefon = "";
            }
            String NoFax;
            if (data.get("noFax") != null) {
                NoFax = (String) data.get("noFax");
            } else {
                NoFax = "";
            }

            db = new Db();
            Statement stmt = db.getStatement();
            conn = db.getConnection();
            conn.setAutoCommit(false);

            SQLRenderer r = new SQLRenderer();
            r.add("p.id_Pemohon", IdPemohon);
            r.add("p.id_Permohonan", IdPermohonan);
            r.add("p.no_Pemohon", NoPemohon);
            r.add("p.nama_Pemohon", Nama);
            r.add("p.alamat_Pemohon1", Alamat1);
            r.add("p.alamat_Pemohon2", Alamat2);
            r.add("p.alamat_Pemohon3", Alamat3);
            r.add("p.Poskod", Poskod);
            r.add("p.id_Daerah", IdDaerah);
            r.add("p.id_Negeri", IdNegeri);
            r.add("p.No_Tel", NoTelefon);
            r.add("p.No_Fax", NoFax);
            sql = r.getSQLInsert("Tblhtppemohon p");
            log.info("FrmPajakanPemohonData::Insert::Tblhtppemohon = " + sql);
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
            if (conn.isClosed()) {
                conn.close();
            }
        }
    }
	  
    //*** copy selected data from table TBLHTPHAKMILIK to table TBLHTPHAKMILIKURUSAN
    public static void copySaveData(String idPermohonan, String idHakmilik) throws Exception {
        long IdHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
        long IdLuas = 0;
        String PeganganHakmilik = new String();
        String NoHakmilik = new String();
        String NoWarta = new String();
        int IdKategori = 0;
        int IdSubKategori = 0;
        int IdMukim = 0;
        int IdDaerah = 0;
        int IdNegeri = 0;
        int IdLot = 0;
        int IdJenisHakmilik = 0;

        String sql;
        try {
            db = new Db();
            Statement stmt = db.getStatement();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            SQLRenderer r = new SQLRenderer();

            r.add("h.id_Hakmilik");
            r.add("h.id_Luas");
            r.add("h.pegangan_Hakmilik");
            r.add("h.no_Hakmilik");
            r.add("h.no_Warta");
            r.add("h.id_Kategori");
            r.add("h.id_SubKategori");
//		      r.add("h.id_JenisTanah");
            r.add("h.id_Mukim");
            r.add("h.id_Daerah");
            r.add("h.id_Negeri");
            r.add("h.id_Lot");
            r.add("h.id_Jenishakmilik");

            r.add("h.id_Hakmilik", idHakmilik, "=");

            sql = r.getSQLSelect("Tblhtphakmilik h");
            log.info("FrmPajakanPemohonData::copySaveData::Tblhtphakmilik = " + sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                IdLuas = rs.getInt("id_Luas");
                PeganganHakmilik = rs.getString("pegangan_Hakmilik");
                NoHakmilik = rs.getString("no_Hakmilik");
                NoWarta = rs.getString("no_Warta") == null ? "" : rs.getString("no_Warta");
                IdKategori = rs.getInt("id_Kategori");
                IdSubKategori = rs.getInt("id_SubKategori");
//				  IdJenisTanah = 0;
                IdMukim = rs.getInt("id_Mukim");
                IdDaerah = rs.getInt("id_Daerah");
                IdNegeri = rs.getInt("id_Negeri");
                IdLot = rs.getInt("id_Lot");
                IdJenisHakmilik = rs.getInt("id_Jenishakmilik");
            }

            Statement stmtInsert = db.getStatement();
            SQLRenderer rinsert = new SQLRenderer();

            rinsert.add("hu.id_Hakmilikurusan", IdHakmilikurusan);
            rinsert.add("hu.id_Permohonan", idPermohonan);
            rinsert.add("hu.id_Luas", IdLuas);
            rinsert.add("hu.pegangan_Hakmilik", PeganganHakmilik);
            rinsert.add("hu.no_Hakmilik", NoHakmilik);
            rinsert.add("hu.no_Warta", NoWarta);
            rinsert.add("hu.id_Kategori", IdKategori);
            rinsert.add("hu.id_SubKategori", IdSubKategori);
            rinsert.add("hu.id_Mukim", IdMukim);
            rinsert.add("hu.id_Daerah", IdDaerah);
            rinsert.add("hu.id_Negeri", IdNegeri);
            rinsert.add("hu.id_Lot", IdLot);
            rinsert.add("hu.id_Jenishakmilik", IdJenisHakmilik);

            sql = rinsert.getSQLInsert("Tblhtphakmilikurusan hu");
            log.info("FrmPajakanPemohonData::copySaveData::Tblhtphakmilikurusan = " + sql);
            stmtInsert.executeQuery(sql);
            conn.commit();

        } finally {
            if (db != null) {
                db.close();
            }
            if (conn.isClosed()) {
                conn.close();
            }
        }
    }

}

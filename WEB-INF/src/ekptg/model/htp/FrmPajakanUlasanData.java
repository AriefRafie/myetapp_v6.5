package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmPajakanUlasanData {

    private static Vector list = new Vector();
    private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
    private static NumberFormat NF = new DecimalFormat("#,###,##0.00");
    private static Logger log = Logger.getLogger(FrmPajakanSemakanData.class);
    private static Db db;
    private static Connection conn;

    //*** query data from database for Ulasan KJP
    public static void setUlasanKJP(String idPermohonan) throws Exception {
        list.clear();
        String sql;
        try {
            setListUlasanJPPH(idPermohonan);
            db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();

            r.add("u.id_Ulasankjp");
            r.add("u.id_Permohonan");
            r.add("u.tarikh_Hantar");
            r.add("u.tarikh_Terima");
            r.add("u.ulasan");

            r.add("u.id_Permohonan", idPermohonan, "=");

            sql = r.getSQLSelect("Tblhtpulasankjp u");
            log.info("FrmPajakanUlasanData::setListUlasanKJP::sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            Hashtable h;

            while (rs.next()) {
                h = new Hashtable();
                h.put("idUlasanKJP", rs.getString("id_Ulasankjp"));
                h.put("idPermohonan", rs.getString("id_Permohonan"));
                h.put("tarikhHantarKJP", Format.format(rs.getDate("tarikh_Hantar")));
                h.put("tarikhTerimaKJP", Format.format(rs.getDate("tarikh_Terima")));
                h.put("ulasanKJP", rs.getString("ulasan") == null ? "" : rs.getString("ulasan"));
                list.addElement(h);
            }

        } catch (Exception e) {
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

    public static Vector getUlasanKJP() {
        log.info("FrmPajakanUlasanData : getUlasanKJP : " + list.size());
        return list;
    }

    //*** update data in database for ulasan KJP
    public static void updateKJP(Hashtable data) throws Exception {
        String sql;
        try {
            String IdUlasankjp = (String) data.get("idUlasankjp");
            String IdPermohonan = (String) data.get("idPermohonan");
            String TarikhHantar = (String) data.get("tarikhHantar");
            String TH = "to_date('" + TarikhHantar + "','dd/MM/yyyy')";
            String TarikhTerima = (String) data.get("tarikhTerima");
            String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
            String ulasan;
            if (data.get("ulasan") != null) {
                ulasan = (String) data.get("ulasan");
            } else {
                ulasan = "";
            }

            db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.update("u.id_Ulasankjp", IdUlasankjp);
            r.update("u.id_Permohonan", IdPermohonan);
            r.add("u.tarikh_Hantar", r.unquote(TH));
            r.add("u.tarikh_Terima", r.unquote(TT));
            r.add("u.ulasan", ulasan);

            sql = r.getSQLUpdate("Tblhtpulasankjp u");
            log.info("FrmPajakanUlasanData::Update::Tblhtpulasankjp = " + sql);
            stmt.executeUpdate(sql);
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

    //*** save data in database for ulasan KJP
    public static void simpanKJP(Hashtable data) throws Exception {

        String sql;
        try {
            long IdUlasankjp = DB.getNextID("TBLHTPULASANKJP_SEQ");
            String IdPermohonan = (String) data.get("idPermohonan");
            String TarikhHantar = (String) data.get("tarikhHantar");
            String TH = "to_date('" + TarikhHantar + "','dd/MM/yyyy')";
            String TarikhTerima = (String) data.get("tarikhTerima");
            String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
            String ulasan;
            if (data.get("ulasan") != null) {
                ulasan = (String) data.get("ulasan");
            } else {
                ulasan = "";
            }

            db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.add("u.id_Ulasankjp", IdUlasankjp);
            r.add("u.id_Permohonan", IdPermohonan);
            r.add("u.tarikh_Hantar", r.unquote(TH));
            r.add("u.tarikh_Terima", r.unquote(TT));
            r.add("u.ulasan", ulasan);
            sql = r.getSQLInsert("Tblhtpulasankjp u");
            log.info("FrmPajakanUlasanData::Insert::Tblhtpulasankjp = " + sql);
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

    //*** query data from database for Ulasan JPPH
    public static void setListUlasanJPPH(String idPermohonan) throws Exception {
        list.clear();
        String sql;
        try {
            db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();

            r.add("ut.id_Ulasanteknikal");
            r.add("ut.id_Permohonan");
            r.add("un.id_Ulasannilai");
            r.add("un.id_Ulasanteknikal");
            r.add("un.tahun_Nilaian");
            r.add("un.nilai_Tanah");
            r.add("un.syor_Bayaran");
            r.add("un.keterangan");
            r.add("ut.id_Ulasanteknikal", r.unquote("un.id_Ulasanteknikal"));
            r.add("ut.id_Permohonan", idPermohonan, "=");

            sql = r.getSQLSelect("Tblhtpulasannilai un, Tblhtpulasanteknikal ut");
            log.info("FrmPajakanUlasanData::setListUlasanJPPH::sql=" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            Hashtable h;

            while (rs.next()) {
                h = new Hashtable();
                h.put("idUlasanteknikal", rs.getString("id_Ulasanteknikal"));
                h.put("idUlasannilai", rs.getString("id_Ulasannilai"));
                h.put("tahunNilaian", rs.getString("tahun_Nilaian"));
                h.put("nilaiTanah", NF.format(rs.getDouble("nilai_Tanah")));
                h.put("syorBayaran", rs.getString("syor_Bayaran") == null ? "" : NF.format(rs.getDouble("syor_Bayaran")));
                h.put("keterangan", rs.getString("keterangan") == null ? "" : rs.getString("keterangan"));
                list.addElement(h);
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static Vector getListUlasanJPPH() {
        log.info("FrmPajakanUlasanData : getListUlasanJPPH :" + list.size());
        return list;
    }

    //*** update data in database for ulasan JPPH
    public static void updateJPPH(Hashtable data) throws Exception {
        String sql;
        try {
            String idUlasannilai = (String) data.get("idUlasannilai");
            String IdPermohonan = (String) data.get("idPermohonan");
            String TahunNilaian = (String) data.get("tahunNilaian");
            String NilaiTanah = (String) data.get("nilaiTanah");
            String SyorBayaran = (String) data.get("SyorBayaran");
            String Keterangan;
            if (data.get("keterangan") != null) {
                Keterangan = (String) data.get("keterangan");
            } else {
                Keterangan = "";
            }

            db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.update("un.id_Ulasannilai", idUlasannilai);
            r.add("un.tahun_Nilaian", TahunNilaian);
            r.add("un.nilai_Tanah", NilaiTanah == "" ? null : Double.parseDouble(Utils.RemoveSymbol(NilaiTanah)));
            r.add("un.syor_Bayaran", SyorBayaran == "" ? null : Double.parseDouble(Utils.RemoveSymbol(SyorBayaran)));
            r.add("un.keterangan", Keterangan);

            sql = r.getSQLUpdate("Tblhtpulasannilai un");
            log.info("FrmPajakanUlasanData::updateJPPH::Tblhtpulasannilai = " + sql);
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

    //*** save data in database
    public static void simpanFirstJPPH(Hashtable data) throws Exception {
        String sql;
        try {
            db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            String IdPermohonan = (String) data.get("idPermohonan");
            int IdPejabat = 127;
            int IdDaerah = 0;
            int IdNegeri = 0;
            int IdAgensi = 0;
            int IdKementerian = 0;

            //query data from TBLRUJPEJABAT
            Statement stmtpej = db.getStatement();
            SQLRenderer rPej = new SQLRenderer();
            rPej.add("id_Pejabat");
            rPej.add("id_Negeri");
            rPej.add("id_Daerah");

            rPej.add("id_Pejabat", IdPejabat);
            sql = rPej.getSQLSelect("Tblrujpejabat");
            ResultSet rsPej = stmtpej.executeQuery(sql);
            while (rsPej.next()) {
                IdDaerah = rsPej.getInt("id_Daerah");
                IdNegeri = rsPej.getInt("id_Negeri");
            }

            //get suitable data from FrmPajakanSemakanData
            Vector listId = new Vector();
            FrmPajakanSemakanData.setSemak(IdPermohonan);
            list = FrmPajakanSemakanData.getSemak();
            Hashtable h = (Hashtable) list.get(0);
            IdAgensi = Integer.parseInt(h.get("idAgensi").toString());
            IdKementerian = Integer.parseInt(h.get("idKementerian").toString());

            long IdUlasanteknikal = DB.getNextID("TBLHTPULASANTEKNIKAL_SEQ");
            String NoRujKJT = (String) data.get("noRujKJT");
            String TarikhHantar = (String) data.get("tarikhHantarJPPH");
            String TH = "to_date('" + TarikhHantar + "','dd/MM/yyyy')";
            String TarikhTerima = (String) data.get("tarikhTerimaJPPH");
            String TT = "to_date('" + TarikhTerima + "','dd/MM/yyyy')";
            int IdPegawai = (Integer) data.get("idPegawai");
            int IdJenisdokumen = 1225;

            //save data for TBLHTPULASANTEKNIKAL
//		  db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.add("ut.id_Ulasanteknikal", IdUlasanteknikal);
            r.add("ut.id_Permohonan", IdPermohonan);
            if (NoRujKJT != "") {
                r.add("ut.no_Rujkjt", NoRujKJT);
            }
            r.add("ut.id_Daerah", IdDaerah);
            r.add("ut.id_Negeri", IdNegeri);
            r.add("ut.id_Agensi", IdAgensi);
            r.add("ut.id_Kementerian", IdKementerian);
            r.add("ut.tarikh_Hantar", r.unquote(TH));
            r.add("ut.tarikh_Terima", r.unquote(TT));
            r.add("ut.id_Pejabat", IdPejabat);
            r.add("ut.nama_Pegawai", IdPegawai);
            r.add("ut.id_Jenisdokumen", IdJenisdokumen);
            r.add("ut.id_Masuk", IdPermohonan);
            sql = r.getSQLInsert("Tblhtpulasanteknikal ut");
            log.info("FrmPajakanUlasanData::Insert::Tblhtpulasankjp = " + sql);
            stmt.executeUpdate(sql);

            long IdUlasannilai = DB.getNextID("TBLHTPULASANNILAI_SEQ");
            int TahunNilai = (Integer) data.get("tahunNilai");
            String NilaiTanah = (String) data.get("nilaiTanah");
            String SyorBayaran = (String) data.get("syorBayaran");
            String Keterangan = (String) data.get("keterangan");

            //save data for TBLHTPULASANNILAI
//		  db = new Db();
            Statement stmtNilai = db.getStatement();
            SQLRenderer rNilai = new SQLRenderer();
            rNilai.add("un.id_Ulasannilai", IdUlasannilai);
            rNilai.add("un.id_Ulasanteknikal", IdUlasanteknikal);
            rNilai.add("un.tahun_Nilaian", TahunNilai);
            rNilai.add("un.nilai_Tanah", NilaiTanah == "" ? null : Double.parseDouble(Utils.RemoveSymbol(NilaiTanah)));
            rNilai.add("un.syor_Bayaran", SyorBayaran == "" ? null : Double.parseDouble(Utils.RemoveSymbol(SyorBayaran)));
            if (Keterangan != "") {
                rNilai.add("un.keterangan", Keterangan);
            }

            sql = rNilai.getSQLInsert("Tblhtpulasannilai un");
            log.info("FrmPajakanUlasanData::Insert::Tblhtpulasannilai = " + sql);
            stmtNilai.executeUpdate(sql);

            conn.commit();


        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static void simpanJPPH(Hashtable data) throws Exception {
        String sql;
        try {
            String IdUlasanteknikal = (String) data.get("idUlasanteknikal");
            long IdUlasannilai = DB.getNextID("TBLHTPULASANNILAI_SEQ");
            int TahunNilai = (Integer) data.get("tahunNilai");
            String NilaiTanah = (String) data.get("nilaiTanah");
            String SyorBayaran = (String) data.get("syorBayaran");
            String Keterangan = (String) data.get("keterangan");

            //save data for TBLHTPULASANNILAI
            db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmtNilai = db.getStatement();
            SQLRenderer rNilai = new SQLRenderer();
            rNilai.add("un.id_Ulasannilai", IdUlasannilai);
            rNilai.add("un.id_Ulasanteknikal", IdUlasanteknikal);
            rNilai.add("un.tahun_Nilaian", TahunNilai);
            rNilai.add("un.nilai_Tanah", NilaiTanah == "" ? null : Double.parseDouble(Utils.RemoveSymbol(NilaiTanah)));
            rNilai.add("un.syor_Bayaran", SyorBayaran == "" ? null : Double.parseDouble(Utils.RemoveSymbol(SyorBayaran)));
            if (Keterangan != "") {
                rNilai.add("un.keterangan", Keterangan);
            }

            sql = rNilai.getSQLInsert("Tblhtpulasannilai un");
            log.info("FrmPajakanUlasanData::Insert::Tblhtpulasannilai = " + sql);
            stmtNilai.executeUpdate(sql);
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
}

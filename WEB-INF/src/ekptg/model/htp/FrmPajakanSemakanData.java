package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmPajakanSemakanData {

    private static Vector list = new Vector();
    private static Vector listHakmilik = new Vector();
    private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger log = Logger.getLogger(FrmPajakanSemakanData.class);
    private static Db db;
    private static Connection conn;

    public static void setSemak(String idPermohonan) throws Exception {
        list.clear();
        String sql;
        try {
            db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();

            r.add("p.id_Fail");
            r.add("p.id_Permohonan");
            r.add("f.id_Negeri");
            r.add("f.id_Kementerian");
            r.add("hp.id_Agensi");
            r.add("f.id_Suburusan");
            r.add("f.tajuk_Fail");
            r.add("f.no_Fail");
            r.add("p.tarikh_Surat");
            r.add("hp.no_Rujukan_KJP");
            r.add("hp.id_Jenistanah");
            r.add("hp.no_Rujukan_Lain");
            r.add("hp.tarikh_Agihan");
            r.add("p.tarikh_Terima");

            r.add("p.id_Fail", r.unquote("f.id_Fail"));
            r.add("p.id_Permohonan", r.unquote("hp.id_Permohonan"));

            r.set("p.id_Permohonan", idPermohonan);

            sql = r.getSQLSelect("Tblpermohonan p, Tblpfdfail f,Tblhtppermohonan hp");
            ResultSet rs = stmt.executeQuery(sql);
            log.info("FrmPajakanSemakanData : setSemak : " + sql);
            Hashtable h;

            while (rs.next()) {
                h = new Hashtable();
                h.put("idFail", rs.getString("id_Fail"));
                h.put("idNegeri", rs.getString("id_Negeri"));
                h.put("idKementerian", rs.getString("id_Kementerian"));
                h.put("idAgensi", rs.getString("id_Agensi"));
                h.put("idSuburusan", rs.getString("id_Suburusan"));
                h.put("tajuk", rs.getString("tajuk_Fail"));
                h.put("noFail", rs.getString("no_Fail"));
                h.put("tSurat", Format.format(rs.getDate("tarikh_Surat")));
                h.put("idJenistanah", rs.getString("id_Jenistanah"));
                h.put("noRujukanKJP", rs.getString("no_Rujukan_KJP"));
                h.put("noRujukan", rs.getString("no_Rujukan_Lain"));
                h.put("tAgihan", Format.format(rs.getDate("tarikh_Agihan")));
                h.put("tPermohonan", rs.getDate("tarikh_Terima") == null ? "" : Format.format(rs.getDate("tarikh_Terima")));
                list.addElement(h);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static Vector getSemak() {
        log.info("FrmPajakanSemakanData : getSemak : " + list.size());
        return list;
    }

    //*** update data from database
    public static String update(Hashtable data) throws Exception {

        String sql;
        String idPermohonan = "";
        try {
            db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            String idFail = (String) data.get("idFail");
            int idAgensi = Integer.parseInt(data.get("idAgensi").toString());
            String tujuan = (String) data.get("Tajuk");

            Statement stmtFail = db.getStatement();
            SQLRenderer rFail = new SQLRenderer();
            rFail.update("id_Fail", idFail);

            rFail.add("tajuk_Fail", tujuan);
            sql = rFail.getSQLUpdate("Tblpfdfail");
            log.info("FrmPajakanSemakanData::Update::TBLPFDFAIL = " + sql);
            stmtFail.executeUpdate(sql);

            idPermohonan = (String) data.get("idPermohonan");
            String idJenistanah = (String) data.get("idJenistanah");
            String TarikhSurKJP = (String) data.get("TarikhSurKJP");
            String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
            String NoFailKJP = (String) data.get("NoFailKJP");
            String NoFailLain = (String) data.get("NoFailLain");
            String TarikhPermohonan = (String) data.get("TarikhPermohonan");
            String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";


            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.update("id_Permohonan", idPermohonan);
            r.add("tarikh_Surat", r.unquote(TSKJP));
            r.add("tarikh_Terima", r.unquote(TP));
            sql = r.getSQLUpdate("Tblpermohonan");
            log.info("FrmPajakanSemakanData::Update::TBLPERMOHONAN = " + sql);
            stmt.executeUpdate(sql);

            Statement stmtHP = db.getStatement();
            SQLRenderer rHP = new SQLRenderer();
            rHP.update("id_Permohonan", idPermohonan);
            rHP.add("id_Agensi", idAgensi);
            rHP.add("id_Jenistanah", idJenistanah);
            rHP.add("no_Rujukan_KJP", NoFailKJP);
            rHP.add("no_Rujukan_Lain", NoFailLain);
            sql = rHP.getSQLUpdate("Tblhtppermohonan");
            log.info("FrmPajakanSemakanData::Update::tarikh_Agihan = " + TSKJP);
            log.info("FrmPajakanSemakanData::Update::TBLHTPPERMOHONAN = " + sql);
            stmtHP.executeUpdate(sql);

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

        return idPermohonan;


    }

    //*** simpan data from database
    public static String simpan(Hashtable data) throws Exception {
        String sql;
        long idPermohonan = 0L;
        try {
            db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
            String kodJabatan = "JKPTG";
            int idTarafKeselamatan = 1;
            int idSeksyen = 3;
            int idUrusan = 3;
            int idSuburusan = 7;
            int idNegeri = (Integer) data.get("idNegeri");
            int idKementerian = (Integer) data.get("idKementerian");
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
            String tarikhDaftarFail = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
            int idStatus = Integer.parseInt("7");
            String tujuan = (String) data.get("Tajuk");
            String kodUrusan = "";
            String kodMampu = "";

            Statement stmturus = db.getStatement();
            SQLRenderer rUrus = new SQLRenderer();
            rUrus.add("id_Urusan");
            rUrus.add("kod_Urusan");
            rUrus.add("id_Urusan", idUrusan);
            sql = rUrus.getSQLSelect("Tblrujurusan");
            ResultSet rsUrus = stmturus.executeQuery(sql);
            while (rsUrus.next()) {
                kodUrusan = rsUrus.getString("kod_Urusan");
            }
            Statement stmtnegeri = db.getStatement();
            SQLRenderer rnegeri = new SQLRenderer();
            rnegeri.add("id_Negeri");
            rnegeri.add("kod_Mampu");
            rnegeri.add("id_Negeri", idNegeri);
            sql = rnegeri.getSQLSelect("Tblrujnegeri");
            ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
            while (rsnegeri.next()) {
                kodMampu = rsnegeri.getString("kod_Mampu");
            }

            String noFail = kodJabatan + "/101/" + kodUrusan + "/" + idKementerian + "/" + kodMampu + "-" + String.format("%06d", File.getSeqNo(idSeksyen, idUrusan, idKementerian, idNegeri));
            log.info("nofail = " + noFail);
            String noFailRoot = "TIADA";
            int lokasi = Integer.parseInt("1");
            int flagFail = Integer.parseInt("1");
            int faharasat = Integer.parseInt("1");

            Statement stmtFail = db.getStatement();
            SQLRenderer rFail = new SQLRenderer();
            rFail.add("id_Fail", idFail);
            rFail.add("kod_Jabatan", kodJabatan);
            rFail.add("id_Tarafkeselamatan", idTarafKeselamatan);
            rFail.add("id_Seksyen", idSeksyen);
            rFail.add("id_Urusan", idUrusan);
            rFail.add("id_Suburusan", idSuburusan);
            rFail.add("tarikh_Daftar_Fail", rFail.unquote(tarikhDaftarFail));
            rFail.add("flag_Fail", flagFail);
            rFail.add("tajuk_Fail", tujuan);
            rFail.add("no_Fail", noFail);
            rFail.add("no_Fail_Root", noFailRoot);
            rFail.add("id_lokasifail", lokasi);
            rFail.add("id_Negeri", idNegeri);
            rFail.add("id_Kementerian", idKementerian);
            rFail.add("id_Status", idStatus);
            rFail.add("id_faharasat", faharasat);
            rFail.add("id_Masuk", idFail);
            rFail.add("tarikh_Masuk", rFail.unquote(tarikhDaftarFail));
            rFail.add("id_Kemaskini", idFail);
            rFail.add("tarikh_Kemaskini", rFail.unquote(tarikhDaftarFail));
            sql = rFail.getSQLInsert("Tblpfdfail");
            log.info("FrmPajakanSemakanData::Insert::TBLPFDFAIL = " + sql);
            stmtFail.executeUpdate(sql);


            idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
            int idJKPTG = 1;
            String noPermohonan = "TIADA";
            String noPerserahan = "TIADA";
            String TarikhSurKJP = (String) data.get("TarikhSurKJP");
            String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";

            long idHtppermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
            int idAgensi = Integer.parseInt(data.get("idAgensi").toString());

            String idJenistanah = (String) data.get("idJenistanah");
            int idPegawai = Integer.parseInt("1");
            String NoFailKJP = (String) data.get("NoFailKJP");
            String NoFailLain = (String) data.get("NoFailLain");
            String TarikhPermohonan = (String) data.get("TarikhPermohonan");
            String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";


            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
            r.add("id_Permohonan", idPermohonan);
            r.add("id_Fail", idFail);
            r.add("id_Jkptg", idJKPTG);
            r.add("no_Permohonan", noPermohonan);
            r.add("no_Perserahan", noPerserahan);
            r.add("tarikh_Surat", r.unquote(TSKJP));
            r.add("tarikh_Terima", r.unquote(TP));
            r.add("tujuan", tujuan);
            sql = r.getSQLInsert("Tblpermohonan");
            log.info("FrmPajakanSemakanData::Insert::TBLPERMOHONAN = " + sql);
            stmt.executeUpdate(sql);

            Statement stmtHP = db.getStatement();
            SQLRenderer rHP = new SQLRenderer();
            rHP.add("id_Htppermohonan", idHtppermohonan);
            rHP.add("id_Permohonan", idPermohonan);
            rHP.add("id_Agensi", idAgensi);
            rHP.add("id_Jenistanah", idJenistanah);
            rHP.add("id_Pegawai", idPegawai);
            rHP.add("no_Rujukan_KJP", NoFailKJP);
            rHP.add("no_Rujukan_Lain", NoFailLain);
            rHP.add("tarikh_Agihan", r.unquote(tarikhDaftarFail));
            sql = rHP.getSQLInsert("Tblhtppermohonan");
            log.info("FrmPajakanSemakanData::Insert::tarikh_Agihan = " + TSKJP);
            log.info("FrmPajakanSemakanData::Insert::TBLHTPPERMOHONAN = " + sql);
            stmtHP.executeUpdate(sql);

            StatusChange_Action(idPermohonan, idSuburusan);

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

        return String.valueOf(idPermohonan);

    }

    public static void StatusChange_Action(long idPermohonan, int idSuburusan) {
        int PPP = Integer.parseInt("15"); //PENERIMAAN PERMOHONAN
//		  int PKRJPN = Integer.parseInt("57"); //PINJAMAN KOS RENDAH JABATAN PERUMAHAN NEGARA
//		  int TPM = Integer.parseInt("60"); //PINJAMAN TMP & JPK
//		  int PHG = Integer.parseInt("63"); //PERLETAKHAKAN HAK GADAIAN
        String aktif = "1";
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
        String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";

        String sql;

        try {
            long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

            db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();

            r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
            r.add("id_Permohonan", idPermohonan);

            if (idSuburusan == 7) {
                r.add("Id_Suburusanstatus", PPP);
            }
//			  else if(idSuburusan == 39){
//				  r.add("Id_Suburusanstatus", PKRJPN);
//			  }else if(idSuburusan == 40){
//				  r.add("Id_Suburusanstatus", TPM);
//			  }else{
//				  r.add("Id_Suburusanstatus", PHG);
//			  }			  
            r.add("aktif", aktif);
            r.add("id_Masuk", idPermohonan);
            r.add("tarikh_Masuk", r.unquote(sekarang));

            sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
            log.info("FrmPajakanSemakanData::StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = " + sql);
            stmt.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //*** Hakmilik tab's under semakan page
    public static void setListHakmilik(String idPeromohonan) throws Exception {
        listHakmilik.clear();
        String sql;

        try {
            db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();

            r.add("hmu.id_Hakmilikurusan");
            r.add("hmu.id_Permohonan");
            r.add("n.nama_Negeri");
            r.add("d.nama_Daerah");
            r.add("m.nama_Mukim");
            r.add("jhm.kod_Jenis_Hakmilik");
            r.add("hmu.no_Hakmilik");
            r.add("hmu.pegangan_Hakmilik");
            r.add("hmu.id_Negeri", r.unquote("n.id_Negeri"));
            r.add("hmu.id_Daerah", r.unquote("d.id_Daerah"));
            r.add("hmu.id_Mukim", r.unquote("m.id_Mukim"));
            r.add("hmu.id_Jenishakmilik", r.unquote("jhm.id_Jenishakmilik"));
            r.add("hmu.id_Permohonan", idPeromohonan);

            sql = r.getSQLSelect("Tblhtphakmilikurusan hmu, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblrujjenishakmilik jhm");
            log.info("FrmPajakanSemakanData::setListHakmilik::sql = " + sql);
            ResultSet rs = stmt.executeQuery(sql);
            Hashtable h;

            while (rs.next()) {
                h = new Hashtable();
                h.put("idHakmilikurusan", rs.getString("id_Hakmilikurusan"));
                h.put("namaNegeri", rs.getString("nama_Negeri"));
                h.put("namaDaerah", rs.getString("nama_Daerah"));
                h.put("namaMukim", rs.getString("nama_Mukim"));
                h.put("kodJenisHakmilik", rs.getString("kod_Jenis_Hakmilik"));
                h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "" : rs.getString("no_Hakmilik"));
                h.put("peganganHakmilik", rs.getString("pegangan_Hakmilik") == null ? "" : rs.getString("pegangan_Hakmilik"));
                listHakmilik.addElement(h);
            }

        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static Vector getListHakmilik() {
        log.info("FrmPajakanSemakanData : getListHakmilik : " + listHakmilik.size());
        return listHakmilik;
    }

    public static void setCariHakmilik(String idPeromohonan, int idDaerah, int idMukim, int idJenishakmilik, String noHakmilik) throws Exception {
        listHakmilik.clear();
        String noHM = "%" + noHakmilik + "%";
        String sql = "";

        try {
            db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();

            r.add("hm.id_Hakmilik");
            r.add("n.nama_Negeri");
            r.add("d.nama_Daerah");
            r.add("m.nama_Mukim");
            r.add("jhm.kod_Jenis_Hakmilik");
            r.add("hm.no_Hakmilik");
            r.add("hm.pegangan_Hakmilik");
            r.add("hm.id_Negeri", r.unquote("n.id_Negeri"));
            r.add("hm.id_Daerah", r.unquote("d.id_Daerah"));
            r.add("hm.id_Mukim", r.unquote("m.id_Mukim"));
            r.add("hm.id_Jenishakmilik", r.unquote("jhm.id_Jenishakmilik"));
            if (idDaerah != 0) {
                r.add("hm.id_Daerah", idDaerah);
            }
            if (idMukim != 0) {
                r.add("hm.id_Mukim", idMukim);
            }

            if (idJenishakmilik != 0) {
                r.add("hm.id_Jenishakmilik", idJenishakmilik);
            }

            if (noHakmilik != "") {
                r.add("hm.no_Hakmilik", noHM, "LIKE");
            }

            sql = r.getSQLSelect("Tblhtphakmilik hm, Tblrujnegeri n, Tblrujdaerah d, Tblrujmukim m, Tblrujjenishakmilik jhm");
            log.info("FrmPajakanSemakanData::setCariHakmilik::sql = " + sql);
            ResultSet rs = stmt.executeQuery(sql);
            Hashtable h;

            while (rs.next()) {
                h = new Hashtable();
                h.put("idHakmilik", rs.getString("id_Hakmilik"));
                h.put("namaNegeri", rs.getString("nama_Negeri"));
                h.put("namaDaerah", rs.getString("nama_Daerah"));
                h.put("namaMukim", rs.getString("nama_Mukim"));
                h.put("kodJenisHakmilik", rs.getString("kod_Jenis_Hakmilik"));
                h.put("noHakmilik", rs.getString("no_Hakmilik") == null ? "" : rs.getString("no_Hakmilik"));
                h.put("peganganHakmilik", rs.getString("pegangan_Hakmilik") == null ? "" : rs.getString("pegangan_Hakmilik"));
                listHakmilik.addElement(h);
            }

        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static Vector getCariHakmilik() {
        log.info("FrmPajakanSemakanData : getCariHakmilik : " + listHakmilik.size());
        return listHakmilik;
    }
}

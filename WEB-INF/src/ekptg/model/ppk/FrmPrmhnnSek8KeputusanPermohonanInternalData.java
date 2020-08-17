/**
 * 
 */
package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

/**
 * @razman
 *
 */
public class FrmPrmhnnSek8KeputusanPermohonanInternalData {
	
	//set	
	private static Vector listKeputusan = new Vector();
	private static Vector listMaklumatMahkamah = new Vector();
	private static Vector listMaklumatMahkamahJ = new Vector();
	private static Vector listMaklumatMahkamahJX = new Vector();
	private static Vector listMaklumatMahkamahJ_ID = new Vector();
	private static Vector listMaklumatMahkamahM = new Vector();
	private static Vector listMaklumatMahkamahMPindah = new Vector();
	private static Vector listMaklumatMahkamahARB = new Vector();
	private static Vector listMaklumatPentadbirTanah = new Vector();
	private static Vector listMaklumatInsolvensi = new Vector();
	private static Vector flag5juta = new Vector();
		
	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8KeputusanPermohonanInternalData.class);
	
	public static Vector getFlag5Juta(){
		return flag5juta;
	}
	
	public static void checkFlag5Juta (String id) throws Exception {
		Db db = null;
		flag5juta.clear();
		String sql = "";
		boolean f5j = false;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT FLAG_5JUTA FROM TBLPPKPERUBAHANAKTA";
			System.out.println("FLAG 5 JUTA: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())	{
				Hashtable h = new Hashtable();
				if (h.put("flag_5juta", rs.getString(1)==null?"":rs.getString(1)) == "T") {
					f5j = true;
				}
			} 
		}
		finally {
			if(db != null)db.close();			
		}
	}
	
	public static Vector getDataKeputusan(){
		System.out.println("-------Read Here----");
		return listKeputusan;
	}
	
	public static Vector getMaklumatMahkamah(){
		return listMaklumatMahkamah;
	}
	
	public static void setMaklumatMahkamah(String id) throws Exception {
		Db db = null;
		String v="08";
		listMaklumatMahkamah.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("kp.id_Permohonan");
			r.add("pej.nama_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
			
			
			
			r.add("kp.id_Permohonan",r.unquote("p.id_Permohonan"));			
			r.add("p.id_Permohonan",id);
			r.add("pej.id_jenispejabat",v);
			r.add("kp.id_Daerah_Mahkamah",r.unquote("d.id_Daerah"));
			r.add("d.id_Daerah",r.unquote("pej.id_Daerah"));	
			r.add("kp.id_Negerimahkamah",r.unquote("n.id_Negeri"));
			r.add("n.id_Negeri",r.unquote("pej.id_Negeri"));	
		    
			sql = r.getSQLSelect("Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d, Tblrujnegeri n");//, Tblrujdaerah d, Tblrujnegeri n");
			
			//sql = "SELECT kp.id_Permohonan, pej.nama_Pejabat  FROM Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d WHERE kp.id_Permohonan = p.id_Permohonan  AND p.id_Permohonan = 323  AND pej.jenis_pejabat = '08'  AND kp.id_Daerah_Mahkamah = d.id_Daerah  AND d.id_Daerah = pej.id_Daerah";
			//sql = "select '123' as id_Permohonan,'AJAE TEST' as nama_Pejabat from DUAL";
			System.out.println("MAHKLAMAH:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				//System.out.println("ada rekod...");
				Hashtable h = new Hashtable();
				//h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("id_Permohonan", rs.getString(1)==null?"":rs.getString(1));
				
				//System.out.println("123 ====>>>"+rs.getString("id_Permohonan"));
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				
				/*
				 * r.add("kp.id_Permohonan");
			r.add("pej.nama_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
				 */
				
				listMaklumatMahkamah.addElement(h);
			}}
			catch (DbException e) {
			
				e.printStackTrace();
			}
			finally {
				if(db != null)db.close();			
		}
	}	
	
	
	
	public static void setMaklumatMahkamah2(String idPerbicaraan) throws Exception {
		Db db = null;
		String v="08";
		listMaklumatMahkamah.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql =    "SELECT pej.nama_pejabat, pej.alamat1, pej.alamat2, " +
				     "pej.alamat3, pej.poskod, pej.id_daerah, pej.id_negeri " +
				     "FROM tblrujpejabat pej where pej.id_pejabat in (SELECT ID_PEJABATMAHKAMAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN = '"+idPerbicaraan+"')";
			
			//sql = "SELECT kp.id_Permohonan, pej.nama_Pejabat  FROM Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d WHERE kp.id_Permohonan = p.id_Permohonan  AND p.id_Permohonan = 323  AND pej.jenis_pejabat = '08'  AND kp.id_Daerah_Mahkamah = d.id_Daerah  AND d.id_Daerah = pej.id_Daerah";
			//sql = "select '123' as id_Permohonan,'AJAE TEST' as nama_Pejabat from DUAL";
			System.out.println("MAHKAMAH:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				//System.out.println("ada rekod...");
				Hashtable h = new Hashtable();
				//h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				//h.put("id_Permohonan", rs.getString(1)==null?"":rs.getString(1));
				
				//System.out.println("123 ====>>>"+rs.getString("id_Permohonan"));
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
								
				//h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				//h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
				
				h.put("daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				
				/*
				 * r.add("kp.id_Permohonan");
			r.add("pej.nama_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
				 */
				
				listMaklumatMahkamah.addElement(h);
			}}
			catch (DbException e) {
			
				e.printStackTrace();
			}
			finally {
				if(db != null)db.close();			
		}
	}	
	
	public static Vector getMaklumatMahkamahM(){
		return listMaklumatMahkamahM;
	}
	public static Vector getMaklumatMahkamahMPindah(){
		return listMaklumatMahkamahMPindah;
	}
	
	public static Vector getMaklumatMahkamahARB(){
		return listMaklumatMahkamahARB;
	}
	
	public static Vector getMaklumatPentadbirTanah(){
		return listMaklumatPentadbirTanah;
	}
	
	public static Vector getMaklumatInsolvensi(){
		return listMaklumatInsolvensi;
	}
	
	public static void setMaklumatMahkamahARB() throws Exception {
		Db db = null;
		listMaklumatMahkamahARB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT PEJ.NAMA_PEJABAT, PEJ.ID_NEGERI, PEJ.ID_DAERAH, PEJ.ID_PEJABAT, PEJ.ALAMAT1, PEJ.ALAMAT2, PEJ.ALAMAT3, PEJ.POSKOD, PEJ.KOD_PEJABAT, " +
					" PEJ.NO_TEL, PEJ.NO_FAX, PEJ.ID_JENISPEJABAT, PEJ.ID_BANDAR, D.NAMA_DAERAH, "+
					" N.NAMA_NEGERI, B.KETERANGAN  FROM TBLRUJPEJABAT PEJ, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B  "+
					" WHERE D.ID_DAERAH = PEJ.ID_DAERAH  AND N.ID_NEGERI = PEJ.ID_NEGERI  AND PEJ.ID_BANDAR = B.ID_BANDAR(+)  "+
					" AND ID_JENISPEJABAT IN (9,61) "+
					" ORDER BY NAMA_NEGERI ";
			
			myLogger.info("SQL listMaklumatMahkamahARB :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				Hashtable h = new Hashtable();				
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat")==null?"":rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));				
				h.put("iddaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));	
				h.put("jenispejabat", rs.getString("id_jenispejabat")==null?"":rs.getString("id_jenispejabat"));
				h.put("kodpejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));				
				h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
				h.put("namabandar", rs.getString("Keterangan")==null?"":rs.getString("Keterangan"));
				listMaklumatMahkamahARB.addElement(h);
			}}
			catch (DbException e) {
			
				e.printStackTrace();
			}
			finally {
				if(db != null)db.close();			
		}
	}	
	
	
	public static void setMaklumatMahkamahARBDb(Db db) throws Exception {
		//Db db = null;
		listMaklumatMahkamahARB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT PEJ.NAMA_PEJABAT, PEJ.ID_NEGERI, PEJ.ID_DAERAH, PEJ.ID_PEJABAT, PEJ.ALAMAT1, PEJ.ALAMAT2, PEJ.ALAMAT3, PEJ.POSKOD, PEJ.KOD_PEJABAT, " +
					" PEJ.NO_TEL, PEJ.NO_FAX, PEJ.ID_JENISPEJABAT, PEJ.ID_BANDAR, D.NAMA_DAERAH, "+
					" N.NAMA_NEGERI, B.KETERANGAN  FROM TBLRUJPEJABAT PEJ, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B  "+
					" WHERE D.ID_DAERAH = PEJ.ID_DAERAH  AND N.ID_NEGERI = PEJ.ID_NEGERI  AND PEJ.ID_BANDAR = B.ID_BANDAR(+)  "+
					" AND ID_JENISPEJABAT IN (9,61,62) "+
					" ORDER BY NAMA_NEGERI ";
			
			myLogger.info("SQL listMaklumatMahkamahARB :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				Hashtable h = new Hashtable();				
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat")==null?"":rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));				
				h.put("iddaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));	
				h.put("jenispejabat", rs.getString("id_jenispejabat")==null?"":rs.getString("id_jenispejabat"));
				h.put("kodpejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));				
				h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
				h.put("namabandar", rs.getString("Keterangan")==null?"":rs.getString("Keterangan"));
				listMaklumatMahkamahARB.addElement(h);
			}}
			
			finally {
				//if(db != null)db.close();			
		}
	}	
	
	public static void setMaklumatPentadbirTanah(Db db) throws Exception {
		//Db db = null;
		listMaklumatPentadbirTanah.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT DISTINCT c.id_negeri, c.id_daerah, c.id_bandar, c.id_pejabat, c.kod_pejabat, c.nama_pejabat, " +
                " c.id_jenispejabat, c.alamat1, c.alamat2, c.alamat3, c.poskod, " +
                " c.no_tel, c.no_fax, d.nama_daerah,  N.NAMA_NEGERI, e.keterangan " +
                " FROM tblrujpejabatjkptg a, tblrujpejabaturusan b, tblrujpejabat c, tblrujdaerah d, tblrujnegeri n, TBLRUJBANDAR E " +
                " WHERE b.id_seksyen = '2' " +
                " AND b.id_jenispejabat = a.id_jenispejabat " +
                " AND b.id_jenispejabat IN ('21', '22', '24') " +
                " AND c.id_jenispejabat = '2' " +
                " AND c.id_pejabat <> '0' " +
                " AND b.id_pejabatjkptg = '45' " +
                " and D.ID_DAERAH = c.ID_DAERAH  AND N.ID_NEGERI = c.ID_NEGERI  AND c.ID_BANDAR = E.ID_BANDAR(+) " +
                " ORDER BY NAMA_NEGERI ";
			
		myLogger.info("SQL setMaklumatPentadbirTanah :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				Hashtable h = new Hashtable();				
				h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
				h.put("id_Pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));				
				h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
				h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));				
				h.put("daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
				h.put("negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));				
				h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("idnegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));	
				h.put("jenispejabat", rs.getString("id_jenispejabat")==null?"":rs.getString("id_jenispejabat"));
				h.put("kodpejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));				
				h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
				h.put("namabandar", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				listMaklumatPentadbirTanah.addElement(h);
			}}
			
			finally {
				//if(db != null)db.close();			
		}
	}	
	
	public static void setMaklumatInsolvensi(Db db) throws Exception {
		//Db db = null;
		listMaklumatInsolvensi.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT PEJ.NAMA_PEJABAT, PEJ.ID_NEGERI, PEJ.ID_DAERAH, PEJ.ID_PEJABAT, PEJ.ALAMAT1, PEJ.ALAMAT2, PEJ.ALAMAT3, PEJ.POSKOD, PEJ.KOD_PEJABAT, " +
					" PEJ.NO_TEL, PEJ.NO_FAX, PEJ.ID_JENISPEJABAT, PEJ.ID_BANDAR, D.NAMA_DAERAH, "+
					" N.NAMA_NEGERI, B.KETERANGAN  FROM TBLRUJPEJABAT PEJ, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B  "+
					" WHERE D.ID_DAERAH = PEJ.ID_DAERAH  AND N.ID_NEGERI = PEJ.ID_NEGERI  AND PEJ.ID_BANDAR = B.ID_BANDAR(+)  "+
					" AND ID_JENISPEJABAT IN (141782) "+
					" ORDER BY NAMA_NEGERI ";
			
			myLogger.info("SQL listMaklumatInsolvensi :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				Hashtable h = new Hashtable();				
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat")==null?"":rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));				
				h.put("iddaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));	
				h.put("jenispejabat", rs.getString("id_jenispejabat")==null?"":rs.getString("id_jenispejabat"));
				h.put("kodpejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));				
				h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
				h.put("namabandar", rs.getString("Keterangan")==null?"":rs.getString("Keterangan"));
				listMaklumatInsolvensi.addElement(h);
			}}
			
			finally {
				//if(db != null)db.close();			
		}
	}	
	
	
	public static void setMaklumatMahkamahM() throws Exception {
		Db db = null;
		//String v="08";
		listMaklumatMahkamahM.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("pej.nama_Pejabat");
			r.add("pej.id_Negeri");
			r.add("pej.id_Daerah");
			r.add("pej.id_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.kod_pejabat");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("pej.id_jenispejabat");
			r.add("pej.id_bandar");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
			r.add("b.Keterangan");
		//	r.add("pej.id_jenispejabat",v);			
			r.add("d.id_Daerah",r.unquote("pej.id_Daerah"));			
			r.add("n.id_Negeri",r.unquote("pej.id_Negeri"));
			r.add("pej.id_Bandar",r.unquote("b.id_Bandar(+)"));
		    
			sql = r.getSQLSelect("Tblrujpejabat pej, Tblrujdaerah d, Tblrujnegeri n, Tblrujbandar b");//, Tblrujdaerah d, Tblrujnegeri n");
			sql += "ORDER BY NAMA_NEGERI";
			
			
			myLogger.info("SQL MAHKAMAH :"+sql.toUpperCase());			//sql = "SELECT kp.id_Permohonan, pej.nama_Pejabat  FROM Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d WHERE kp.id_Permohonan = p.id_Permohonan  AND p.id_Permohonan = 323  AND pej.jenis_pejabat = '08'  AND kp.id_Daerah_Mahkamah = d.id_Daerah  AND d.id_Daerah = pej.id_Daerah";
			//sql = "select '123' as id_Permohonan,'AJAE TEST' as nama_Pejabat from DUAL";
			//System.out.println("MAHKLAMAH UUUUUU:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
			//	//System.out.println("ada rekod...");
				Hashtable h = new Hashtable();
				//h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				//h.put("id_Permohonan", rs.getString(1)==null?"":rs.getString(1));
				
				
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat")==null?"":rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				
				h.put("iddaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				
				
				h.put("jenispejabat", rs.getString("id_jenispejabat")==null?"":rs.getString("id_jenispejabat"));
				
				
				h.put("kodpejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));
				
				h.put("idbandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
				h.put("namabandar", rs.getString("Keterangan")==null?"":rs.getString("Keterangan"));
				
				
				
				
			//	r.add("pej.id_jenispejabat");
				/*
				 * r.add("kp.id_Permohonan");
			r.add("pej.nama_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
				 */
				
				listMaklumatMahkamahM.addElement(h);
			}}
			catch (DbException e) {
			
				e.printStackTrace();
			}
			finally {
				if(db != null)db.close();			
		}
	}	
	
	
	public static void setMaklumatMahkamahMPindah() throws Exception {
		Db db = null;
		//String v="08";
		listMaklumatMahkamahMPindah.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("pej.nama_Pejabat");
			r.add("pej.id_Negeri");
			r.add("pej.id_Daerah");
			r.add("pej.id_Pejabatjkptg");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("pej.id_jenispejabat");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
			r.add("u.id_daerahurus");
			
			
			
		  //  r.add("pej.id_jenispejabat",22);			
			r.add("d.id_Daerah",r.unquote("pej.id_Daerah"));			
			r.add("n.id_Negeri",r.unquote("pej.id_Negeri"));
			r.add("u.ID_PEJABATJKPTG",r.unquote("pej.ID_PEJABATJKPTG"));
			r.add("u.id_jenispejabat",22);	
			r.add("u.id_seksyen",2);	
			/*
			AND u.ID_PEJABATJKPTG = pej.ID_PEJABATJKPTG
			AND u.id_jenispejabat = 22
			AND u.id_seksyen = 2
		    */
			sql = r.getSQLSelect("TBLRUJPEJABATURUSAN u ,Tblrujpejabatjkptg pej, Tblrujdaerah d, Tblrujnegeri n");//, Tblrujdaerah d, Tblrujnegeri n");
		
			//System.out.println("MAHKLAMAH PINDAH:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
		
				Hashtable h = new Hashtable();
				
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabatjkptg")==null?"":rs.getString("id_Pejabatjkptg"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				
				h.put("iddaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				
				h.put("iddaerahurus", rs.getString("id_daerahurus")==null?"":rs.getString("id_daerahurus"));
				
				
				
				
				h.put("jenispejabat", rs.getString("id_jenispejabat")==null?"":rs.getString("id_jenispejabat"));
				
				
			//	r.add("pej.id_jenispejabat");
				/*
				 * r.add("kp.id_Permohonan");
			r.add("pej.nama_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
				 */
				
				listMaklumatMahkamahMPindah.addElement(h);
			}}
			catch (DbException e) {
			
				e.printStackTrace();
			}
			finally {
				if(db != null)db.close();			
		}
	}	
	
	
	
	public static Vector getMaklumatMahkamahJ(){
		return listMaklumatMahkamahJ;
	}
	
	public static void setMaklumatMahkamahJ(String v) throws Exception {
		System.out.println("setMaklumatMahkamahJ");
		Db db = null;
		//String v="08";
		listMaklumatMahkamahJ.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("pej.nama_Pejabat");
			r.add("pej.id_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.id_Negeri");
			r.add("pej.no_Fax");
			r.add("pej.id_jenispejabat");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
			r.add("b.keterangan");
			
			
					
			
			r.add("pej.id_jenispejabat",v);			
			r.add("d.id_Daerah",r.unquote("pej.id_Daerah"));			
			r.add("n.id_Negeri",r.unquote("pej.id_Negeri"));
			r.add("pej.id_bandar",r.unquote("b.id_bandar"));
		    
			sql = r.getSQLSelect("Tblrujpejabat pej, Tblrujdaerah d, Tblrujnegeri n, Tblrujbandar b");//, Tblrujdaerah d, Tblrujnegeri n");
			
			//sql = "SELECT kp.id_Permohonan, pej.nama_Pejabat  FROM Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d WHERE kp.id_Permohonan = p.id_Permohonan  AND p.id_Permohonan = 323  AND pej.jenis_pejabat = '08'  AND kp.id_Daerah_Mahkamah = d.id_Daerah  AND d.id_Daerah = pej.id_Daerah";
			//sql = "select '123' as id_Permohonan,'AJAE TEST' as nama_Pejabat from DUAL";
			System.out.println("MAHKAMAH XX:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				//System.out.println("ada rekod...");
				Hashtable h = new Hashtable();
				//h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				//h.put("id_Permohonan", rs.getString(1)==null?"":rs.getString(1));
				
				h.put("namabandar", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat")==null?"":rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				
				h.put("jenispejabat", rs.getString("id_jenispejabat")==null?"":rs.getString("id_jenispejabat"));
				h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				
				/*
				 * r.add("kp.id_Permohonan");
			r.add("pej.nama_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
				 */
				
				listMaklumatMahkamahJ.addElement(h);
			}}
			catch (DbException e) {
			
				e.printStackTrace();
			}
			finally {
				if(db != null)db.close();			
		}
	}	
	
	
	public static Vector getMaklumatMahkamahJX(){
		return listMaklumatMahkamahJX;
	}
	
	public static void setMaklumatMahkamahJX(String v) throws Exception {
		Db db = null;
		//String v="08";
		listMaklumatMahkamahJX.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("pej.nama_Pejabat");
			r.add("pej.id_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
			
			
			
					
			
			r.add("pej.id_jenispejabat",v);			
			r.add("d.id_Daerah",r.unquote("pej.id_Daerah"));			
			r.add("n.id_Negeri",r.unquote("pej.id_Negeri"));	
		    
			sql = r.getSQLSelect("Tblrujpejabat pej, Tblrujdaerah d, Tblrujnegeri n");//, Tblrujdaerah d, Tblrujnegeri n");
			
			//sql = "SELECT kp.id_Permohonan, pej.nama_Pejabat  FROM Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d WHERE kp.id_Permohonan = p.id_Permohonan  AND p.id_Permohonan = 323  AND pej.jenis_pejabat = '08'  AND kp.id_Daerah_Mahkamah = d.id_Daerah  AND d.id_Daerah = pej.id_Daerah";
			//sql = "select '123' as id_Permohonan,'AJAE TEST' as nama_Pejabat from DUAL";
			//System.out.println("MAHKLAMAH:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				//System.out.println("ada rekod...");
				Hashtable h = new Hashtable();
				//h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				//h.put("id_Permohonan", rs.getString(1)==null?"":rs.getString(1));
				
				
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat")==null?"":rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				
				/*
				 * r.add("kp.id_Permohonan");
			r.add("pej.nama_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
				 */
				
				listMaklumatMahkamahJX.addElement(h);
			}}
			catch (DbException e) {
			
				e.printStackTrace();
			}
			finally {
				if(db != null)db.close();			
		}
	}	
	
	
	public static Vector getMaklumatMahkamahJ_ID(){
		return listMaklumatMahkamahJ_ID;
	}
	
	public static void setMaklumatMahkamahJ_ID(String idp) throws Exception {
		Db db = null;
		//String v="08";
		listMaklumatMahkamahJ_ID.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("pej.nama_Pejabat");
			r.add("pej.id_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
			
			
			
					
			
			r.add("pej.id_pejabat",idp);			
			r.add("d.id_Daerah",r.unquote("pej.id_Daerah"));			
			r.add("n.id_Negeri",r.unquote("pej.id_Negeri"));	
		    
			sql = r.getSQLSelect("Tblrujpejabat pej, Tblrujdaerah d, Tblrujnegeri n");//, Tblrujdaerah d, Tblrujnegeri n");
			
			//sql = "SELECT kp.id_Permohonan, pej.nama_Pejabat  FROM Tblppkkeputusanpermohonan kp, Tblrujpejabat pej, Tblppkpermohonan p, Tblrujdaerah d WHERE kp.id_Permohonan = p.id_Permohonan  AND p.id_Permohonan = 323  AND pej.jenis_pejabat = '08'  AND kp.id_Daerah_Mahkamah = d.id_Daerah  AND d.id_Daerah = pej.id_Daerah";
			//sql = "select '123' as id_Permohonan,'AJAE TEST' as nama_Pejabat from DUAL";
			//System.out.println("MAHKLAMAH:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				//System.out.println("ada rekod...");
				Hashtable h = new Hashtable();
				//h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				//h.put("id_Permohonan", rs.getString(1)==null?"":rs.getString(1));
				
				
				h.put("nama_pejabat", rs.getString("nama_Pejabat")==null?"":rs.getString("nama_Pejabat"));
				h.put("id_Pejabat", rs.getString("id_Pejabat")==null?"":rs.getString("id_Pejabat"));
				h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				
				h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				
				h.put("no_tel", rs.getString("no_Tel")==null?"":rs.getString("no_Tel"));
				h.put("no_fax", rs.getString("no_Fax")==null?"":rs.getString("no_Fax"));
				
				h.put("daerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
				h.put("negeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
				
				/*
				 * r.add("kp.id_Permohonan");
			r.add("pej.nama_Pejabat");
			r.add("pej.alamat1");
			r.add("pej.alamat2");
			r.add("pej.alamat3");
			r.add("pej.poskod");
			r.add("pej.no_Tel");
			r.add("pej.no_Fax");
			r.add("d.nama_Daerah");
			r.add("n.nama_Negeri");
				 */
				
				listMaklumatMahkamahJ_ID.addElement(h);
			}}
			catch (DbException e) {
			
				e.printStackTrace();
			}
			finally {
				if(db != null)db.close();			
		}
	}	
	
	public static void setDataKeputusan(String id) throws Exception {
		Db db = null;
		listKeputusan.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("kp.id_KeputusanPermohonan");
			r.add("kp.tarikh_Hantar_BorangB");
			r.add("kp.tarikh_Terima_BorangC");
			r.add("kp.tarikh_Hantar_Nilaian");
			r.add("kp.tarikh_Terima_Nilaian");
			r.add("kp.catatan");
			r.add("kp.id_Permohonan");
			r.add("kp.keputusan_Permohonan");
			r.add("p.flag_Jenis_BorangC");
			r.add("p.jumlah_Hta_TarikhMohon");
			r.add("p.jumlah_Ha_TarikhMohon");
			r.add("p.jumlah_Harta_Tarikhmohon");
			r.add("p.id_Permohonan");
			
		
			
			
			
			
			
			
			//String sql = "Select nama_pejabat,alamat1,alamat2,alamat3,poskod,no_tel,no_fax from tblrujpejabat where jenis_pejabat = 8 and id_daerah = "+ idDaerah +" and id_negeri = "+ idNegeri +"";
			
			
			r.add("kp.id_Permohonan",r.unquote("p.id_Permohonan"));
			
			r.add("p.id_Permohonan",id);
			
			sql = r.getSQLSelect("Tblppkkeputusanpermohonan kp, Tblppkpermohonan p");
			//System.out.println("MAHKLAMAH:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("id_KeputusanPermohonan", rs.getString("id_KeputusanPermohonan")==null?"":rs.getString("id_KeputusanPermohonan"));
				h.put("tarikh_Hantar_BorangB", rs.getString("tarikh_Hantar_BorangB")==null?"":sdf.format(rs.getDate("tarikh_Hantar_BorangB")));
				h.put("tarikh_Terima_BorangC", rs.getString("tarikh_Terima_BorangC")==null?"":sdf.format(rs.getDate("tarikh_Terima_BorangC")));
				h.put("tarikh_Hantar_Nilaian", rs.getString("tarikh_Hantar_Nilaian")==null?"":sdf.format(rs.getDate("tarikh_Hantar_Nilaian")));
				h.put("tarikh_Terima_Nilaian", rs.getString("tarikh_Terima_Nilaian")==null?"":sdf.format(rs.getDate("tarikh_Terima_Nilaian")));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("keputusan_Permohonan", rs.getString("keputusan_Permohonan")==null?"":rs.getString("keputusan_Permohonan"));
				h.put("flag_Jenis_BorangC", rs.getString("flag_Jenis_BorangC")==null?"":rs.getString("flag_Jenis_BorangC"));
				h.put("jumlah_Hta_TarikhMohon", rs.getString("jumlah_Hta_TarikhMohon")==null?"":rs.getString("jumlah_Hta_TarikhMohon"));
				h.put("jumlah_Ha_TarikhMohon", rs.getString("jumlah_Ha_TarikhMohon")==null?"":rs.getString("jumlah_Ha_TarikhMohon"));
				h.put("jumlah_Harta_Tarikhmohon", rs.getString("jumlah_Harta_Tarikhmohon")==null?"":rs.getString("jumlah_Harta_Tarikhmohon"));
				
			
				
				
				
				/*
				r.add("kp.id_Daerahmahkamah");
				r.add("kp.id_Negerimahkamah");
							
				r.add("pej.nama_pejabat");
				r.add("pej.alamat1");
				r.add("pej.alamat2");
				r.add("pej.alamat3");
				r.add("pej.poskod");
				r.add("pej.no_tel");
				r.add("pej.no_fax");
				*/
				
				listKeputusan.addElement(h);
				
			}
		}finally {
			if(db != null)db.close();			
		}
	}	
		
		private static Vector semakId = new Vector();
		
		public static Vector getSemakId(){
			return semakId;
		}
		
		public static void semakId(String id) throws Exception {
			Db db = null;
			semakId.clear();
			String sql = "Select count(id_permohonan) as cnt from tblppkkeputusanpermohonan where id_permohonan = "+ id +"";
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();			
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("cntid", rs.getString("cnt")==null?"":rs.getString("cnt"));
					semakId.addElement(h);
				}
			}finally {
				if(db != null)db.close();			
			}
	}
		
private static Vector semakIdKeputusan = new Vector();

private static Vector semaktblppksd = new Vector();

		
		public static Vector getSemakIdKeputusan(){
			return semakIdKeputusan;
		}
		
		public static Vector getsemaktblppksd(){
			return semaktblppksd;
		}
		
		public static void semakIdKeputusan(String id) throws Exception {
			Db db = null;
			semakIdKeputusan.clear();
			//String sql = "Select count(id_permohonan) as cnt from tblppkkeputusanpermohonan where id_permohonan = "+ id +"";
			String sql = "Select id_keputusanpermohonan from tblppkkeputusanpermohonan where id_permohonan = "+ id +"";
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();			
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("cntid", rs.getString("id_keputusanpermohonan")==null?"":rs.getString("id_keputusanpermohonan"));
					semakIdKeputusan.addElement(h);
				}
			}finally {
				if(db != null)db.close();			
			}
	}
		
		public static void semaktblppksd(String id) throws Exception {
			Db db = null;
			semaktblppksd.clear();
			//String sql = "Select count(id_permohonan) as cnt from tblppkkeputusanpermohonan where id_permohonan = "+ id +"";
			String sql = "Select count(id_keputusanpermohonan) as cnt from tblppksd where id_keputusanpermohonan = "+ id +"";
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				myLogger.info("semaktblppksd-->"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("cntid", rs.getString("cnt")==null?"":rs.getString("cnt"));
					semaktblppksd.addElement(h);
				}
			}finally {
				if(db != null)db.close();			
			}
	}
	
		
		
private static Vector semakMahkamah = new Vector();
		
		public static Vector getSemakMahkamah(){
			return semakMahkamah;
		}
		
		public static void semakAlamatMahkamah(String idDaerah, String idNegeri) throws Exception {
			Db db = null;
			semakMahkamah.clear();
			String sql = "Select nama_pejabat,alamat1,alamat2,alamat3,poskod,no_tel,no_fax from tblrujpejabat where id_jenispejabat = 8 and id_daerah = "+ idDaerah +" and id_negeri = "+ idNegeri +"";
			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();		
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("namapejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
					h.put("alamat1pejabat", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
					h.put("alamat2pejabat", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
					h.put("alamat3pejabat", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
					h.put("poskodpejabat", rs.getString("poskod")==null?"":rs.getString("poskod"));
					h.put("notel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
					h.put("nofax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
					semakMahkamah.addElement(h);
				}
			}finally {
				if(db != null)db.close();			
			}
	}
		
		public static void insertDataMahkamah(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");    	
		    	String idDaerah=(String)data.get("idDaerah");
		    	String idNegeri=(String)data.get("idNegeri");
		    	String id_Fail=(String)data.get("id_Fail");
		    	
		    	
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
//		    	int id_Daerah = Integer.parseInt(idDaerah);
//		    	int id_Negeri = Integer.parseInt(idNegeri);
//		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	
		    	
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_keputusanpermohonan", idKeputusanPermohonan);
				r.add("id_negerimahkamah", idNegeri);
				r.add("id_daerah_mahkamah", idDaerah);
				r.add("id_permohonan", IdPermohonan);
				sql = r.getSQLInsert("tblppkkeputusanpermohonan");
				stmt.executeUpdate(sql);
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		
		public static void updateDataMahkamah(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	String idDaerah=(String)data.get("idDaerah");
		    	String idNegeri=(String)data.get("idNegeri");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
//		    	int id_Daerah = Integer.parseInt(idDaerah);
//		    	int id_Negeri = Integer.parseInt(idNegeri);
//		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	
		    	
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_negerimahkamah", idNegeri);
				r.add("id_daerah_mahkamah", idDaerah);
				r.update("id_permohonan", IdPermohonan);
				sql = r.getSQLUpdate("tblppkkeputusanpermohonan");
				stmt.executeUpdate(sql);
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		
		public static void insertBorang(HttpSession session,Hashtable data,String Seksyen) throws Exception
		  {
			myLogger.info("insertBorang-->");
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    String sql5 = "";
		    String sql6 = "";
		    String tarikhsuratARB = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");    	
		    	long idKaveatPeguam = DB.getNextID("TBLPPKKAVEATPEGUAM_SEQ");  
		    	if (data.get("tarikhsuratARB") != null) {
		    		tarikhsuratARB = (String)data.get("tarikhsuratARB");
				}
		    	else
		    	{
		    		tarikhsuratARB = "";
		    	}
				
		    	//String tarikhsuratARB = (String)data.get("tarikhsuratARB");
		    	myLogger.info("tarikhsuratARB = "+tarikhsuratARB);
		    	String tarikhHantarBorangB=(String)data.get("tarikhHantarBorangB");
		    	String tarikhTerimaBorangC=(String)data.get("tarikhTerimaBorangC");
		    	String tarikhHantarNilaian=(String)data.get("tarikhHantarNilaian");
		    	String tarikhTerimaNilaian=(String)data.get("tarikhTerimaNilaian");
		    	String keputusanBorangC=(String)data.get("keputusanBorangC");
		    	String penentuanBidangKuasa=(String)data.get("penentuanBidangKuasa");
		    	String salinanArahan=(String)data.get("salinanArahan");
		    	String txtCatatanSD=(String)data.get("txtCatatanSD");
		    	myLogger.info("txtCatatanSD = "+txtCatatanSD);
		    //	String penentuanBidangKuasaTeruskan=(String)data.get("penentuanBidangKuasaTeruskan");
		    	String catatan=(String)data.get("catatan");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	String id_Fail=(String)data.get("id_Fail");
		    	String id_Masuk=(String)data.get("id_Masuk");
		    	String ntarikhHantarBorangB = "to_date('" + tarikhHantarBorangB + "','dd/MM/yyyy')";
		    	String ntarikhTerimaBorangC = "to_date('" + tarikhTerimaBorangC + "','dd/MM/yyyy')";
		    	String ntarikhHantarNilaian = "to_date('" + tarikhHantarNilaian + "','dd/MM/yyyy')";
		    	String ntarikhTerimaNilaian = "to_date('" + tarikhTerimaNilaian + "','dd/MM/yyyy')";
//		    	int id_Permohonan = Integer.parseInt(IdPermohonan);	    	
		    	 String userId = (String)data.get("userId");
//				 int id_Suburusanstatus=(Integer)data.get("id_Suburusanstatus");
//				 int id_Suburusanstatusfail=(Integer)data.get("id_Suburusanstatusfail");
		    	 
				 String id_Suburusanstatus=(String)data.get("id_Suburusanstatus");
				 String id_Suburusanstatusfail=(String)data.get("id_Suburusanstatusfail");
				 
				
			
				 String nofailawal=(String)data.get("nofailawal");
				 String namapemohonawal=(String)data.get("namapemohonawal");
				 String tempatmohonawal=(String)data.get("tempatmohonawal");
				 
				 
				 String jenis_pej=(String)data.get("jenis_pej");
				 String txtNamaKaveat=(String)data.get("txtNamaKaveat");
				 String txtNoKaveat=(String)data.get("txtNoKaveat");
				 String txtNamaFirma=(String)data.get("txtNamaFirma");
				 String txtAlamat1Peguam=(String)data.get("txtAlamat1Peguam");
				 String txtAlamat2Peguam=(String)data.get("txtAlamat2Peguam");
				 String txtAlamat3Peguam=(String)data.get("txtAlamat3Peguam");
				 String txtPoskodPeguam=(String)data.get("txtPoskodPeguam");
				 String socNegeriPeguam=(String)data.get("socNegeriPeguam");
				 String txtBandarPeguam=(String)data.get("txtBandarPeguam");
				 String txtNomborTelefonPeguam=(String)data.get("txtNomborTelefonPeguam");
				 String txtTarikhKaveat = (String)data.get("txtTarikhKaveat");
				 
		    	 String ntxtTarikhKaveat = "to_date('" + txtTarikhKaveat + "','dd/MM/yyyy')";

				 
				 String idDaerah=(String)data.get("idDaerah");
			     String idNegeri=(String)data.get("idNegeri");
			     String tujuanPindah = (String)data.get("tujuanPindah"); //razman add
			     
			     
			     String sql9 ="";
				 String idKeputusanPermohonanString = Long.toString(idKeputusanPermohonan);
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_keputusanpermohonan", idKeputusanPermohonan);
				r.add("id_permohonan", IdPermohonan);
				r.add("tarikh_hantar_borangB", r.unquote(ntarikhHantarBorangB));
				r.add("tarikh_terima_borangC", r.unquote(ntarikhTerimaBorangC));
				r.add("tarikh_hantar_nilaian", r.unquote(ntarikhHantarNilaian));
				r.add("tarikh_terima_nilaian", r.unquote(ntarikhTerimaNilaian));
				r.add("jenis_borangC", keputusanBorangC);			
				r.add("id_Masuk", id_Masuk);
				r.add("flag_salinan_arahan", "1");
				r.add("tarikh_Masuk",r.unquote("sysdate"));	
				r.add("id_Kemaskini", id_Masuk);	
				r.add("tarikh_Kemaskini",r.unquote("sysdate"));	
				r.add("keputusan_permohonan", penentuanBidangKuasa);				
				r.add("id_negerimahkamah",idNegeri);
				r.add("id_daerah_mahkamah",idDaerah);
				r.add("FLAG_SEBABPINDAHMAHKAMAH",tujuanPindah); //razman add
				r.add("catatan", catatan);
				r.add("TARIKH_SURATARB",tarikhsuratARB);
				r.add("CATATAN_SD",txtCatatanSD);
				sql = r.getSQLInsert("tblppkkeputusanpermohonan");		
				
				System.out.println("----1-----");
				
				stmt.executeUpdate(sql);
				System.out.println("----2-----");
				
				if(penentuanBidangKuasa.equals("107"))
				{
					Hashtable h = null;
					h = new Hashtable();
					h.put("jenisSD", "107");
					h.put("txtCatatanSD", txtCatatanSD);
					h.put("IdPermohonan", IdPermohonan);
					h.put("userId", session.getAttribute("_ekptg_user_id"));
					h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
					h.put("id_Fail", id_Fail);
					h.put("idKeputusanPermohonan", idKeputusanPermohonanString);
					String uid = (String) session.getAttribute("_ekptg_user_id");
					
					insertSDTable(session, h, "8");
					

					
				}
			
			     if(jenis_pej.equals("99"))
			     {
			    	
			    	    Statement stmt9 = db.getStatement();
						SQLRenderer r9 = new SQLRenderer();
					    r9.add("id_kaveatpeguam", idKaveatPeguam);
						r9.add("id_permohonan", IdPermohonan);
						r9.add("nama_firma",txtNamaFirma);					
						r9.add("nama_kaveat", txtNamaKaveat);
						r9.add("no_kaveat", txtNoKaveat);
						r9.add("alamat1", txtAlamat1Peguam);
						r9.add("alamat2", txtAlamat2Peguam);
						r9.add("alamat3", txtAlamat3Peguam);
						r9.add("poskod", txtPoskodPeguam);
						r9.add("id_negeri", socNegeriPeguam);
						r9.add("id_bandar", txtBandarPeguam);
						r9.add("no_tel", txtNomborTelefonPeguam);
						r9.add("tarikh_kaveat", r.unquote(ntxtTarikhKaveat));
						
						r9.add("id_Masuk", id_Masuk);
						r9.add("tarikh_Masuk",r.unquote("sysdate"));	
						r9.add("id_Kemaskini", id_Masuk);	
						r9.add("tarikh_Kemaskini",r.unquote("sysdate"));	
						
						sql9 = r9.getSQLInsert("tblppkkaveatpeguam");				
						stmt9.executeUpdate(sql9);
			     }
			     else
			     {			    	 
			     Statement stmtd1 = db.getStatement();						 
			     String sqld1 = "delete from tblppkkaveatpeguam where id_Permohonan = "+ IdPermohonan +" ";
				 stmtd1.executeUpdate(sqld1);			    	 
			     }			
				 
			      Statement stmt1 = db.getStatement();
				  SQLRenderer r1 = new SQLRenderer();
				  
				  r1.update("id_Permohonan",IdPermohonan);	
				  if(penentuanBidangKuasa!="")
				  {					     	
				  //r1.add("id_Status", penentuanBidangKuasa);				  
				  }
				  
				  if((penentuanBidangKuasa.equals("151") || penentuanBidangKuasa.equals("152")) && !jenis_pej.equals("99"))
					{
					  r1.add("id_Pejawal", tempatmohonawal);
					  r1.add("no_Fail_Awal",nofailawal);
					  r1.add("nama_Pemohon_Awal", namapemohonawal);				
					}
				  else
				  {
					  r1.add("id_Pejawal","");
					  r1.add("no_Fail_Awal","");
					  r1.add("nama_Pemohon_Awal","");
				  }
				  
				  if(penentuanBidangKuasa.equals("107"))
					{
					  r1.add("id_status","107");
					}
				  r1.add("id_Permohonan",IdPermohonan);	
				  r1.add("ID_KEMASKINI",userId);
				  r1.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			      String sql1 = r1.getSQLUpdate("tblppkpermohonan");
			      stmt1.executeUpdate(sql1);
			      
			      
			      	/*
					Statement stmtX = db.getStatement();
					SQLRenderer r6 = new SQLRenderer();
					r6.update("id_Suburusanstatusfail",id_Suburusanstatusfail);	
					r6.add("AKTIF",0);				
					r6.add("ID_KEMASKINI",userId);
					r6.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
					stmtX.executeUpdate(sql6);	 
			      
					Statement stmtF = db.getStatement();
					SQLRenderer r5 = new SQLRenderer();
					r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
					r5.add("ID_PERMOHONAN",IdPermohonan);
					*/
			      
			      
			      
			      if(Seksyen.equals("8"))
			      {
			      	//SEKSYEN 8
			        FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					if( penentuanBidangKuasa.equals("151"))
					{
						//r5.add("ID_SUBURUSANSTATUS",453); 
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"453",id_Fail);
					}
					if(penentuanBidangKuasa.equals("152"))
					{
						//r5.add("ID_SUBURUSANSTATUS",454);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"454",id_Fail);
					}
					if(penentuanBidangKuasa.equals("53"))
					{						
						//r5.add("ID_SUBURUSANSTATUS",435);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"406",id_Fail);
					}
					if(penentuanBidangKuasa.equals("50"))
					{						
						//r5.add("ID_SUBURUSANSTATUS",426);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"401",id_Fail);
					}
					if(penentuanBidangKuasa.equals("70"))
					{					
						//r5.add("ID_SUBURUSANSTATUS",273);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"429",id_Fail);
					}
			      }
			      
			      else if(Seksyen.equals("17"))
			      {
				      	//SEKSYEN 8
				        FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						if( penentuanBidangKuasa.equals("151"))
						{
							//r5.add("ID_SUBURUSANSTATUS",453); 
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"453",id_Fail);
						}
						if(penentuanBidangKuasa.equals("152"))
						{
							//r5.add("ID_SUBURUSANSTATUS",454);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"454",id_Fail);
						}
						if(penentuanBidangKuasa.equals("53"))
						{						
							//r5.add("ID_SUBURUSANSTATUS",435);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"435",id_Fail);
						}
						if(penentuanBidangKuasa.equals("50"))
						{						
							//r5.add("ID_SUBURUSANSTATUS",426);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"426",id_Fail);
						}
						if(penentuanBidangKuasa.equals("70"))
						{					
							//r5.add("ID_SUBURUSANSTATUS",273);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"273",id_Fail);
						}
				      }
					
					
					
					
					/*
					r5.add("AKTIF",1);
					r5.add("id_Fail",id_Fail);
					r5.add("ID_MASUK",userId);
					r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
					r5.add("ID_KEMASKINI",userId);
					r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
					sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
					stmtF.executeUpdate(sql5);*/	
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		public static void insertSDTable(HttpSession session, Hashtable data, String seksyen) throws Exception
		  {
			myLogger.info("insertSDTable-->");
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    String sql5 = "";
		    String sql6 = "";
		    
		    try
		    {
		    	long idSD = DB.getNextID("TBLPPKSD_SEQ");    	  	

		    	
				
				String sql9 ="";
				String IdPermohonan = (String) data.get("IdPermohonan");
				String id_Masuk = (String) data.get("id_Masuk");
				String txtCatatanSD = (String) data.get("txtCatatanSD");
				String idKeputusanPermohonan = (String) data.get("idKeputusanPermohonan");
				String id_Fail = (String) data.get("id_Fail");
				
				
				
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_sd", idSD);	
				r.add("id_permohonan", IdPermohonan);		
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk",r.unquote("sysdate"));	
				r.add("id_Kemaskini", id_Masuk);	
				r.add("tarikh_Kemaskini",r.unquote("sysdate"));	
				r.add("catatan", txtCatatanSD);
				r.add("id_keputusanpermohonan", idKeputusanPermohonan);
				sql = r.getSQLInsert("tblppksd");		
				
				System.out.println("----sql insert sdppk-----"+sql);
				
				stmt.executeUpdate(sql);
				System.out.println("----2-----");
				      
			      if(seksyen.equals("8"))
			      {
			      	//SEKSYEN 8
			        FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					
						//r5.add("ID_SUBURUSANSTATUS",453); 
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,id_Masuk,"107","453",id_Fail);
					
			      }
			      
			      else if(seksyen.equals("17"))
			      {
				      	
				      }
					
					
					
					
					
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		public static void updateSDTable(HttpSession session, String idKeputusanPermohonan,String txtCatatanSD,String IdPermohonan,String id_Masuk,String id_Fail) throws Exception
		  {
			myLogger.info("updateSDTable-->");
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    String sql5 = "";
		    String sql6 = "";
		    String Seksyen = "8";
		    try
		    {

				
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_keputusanpermohonan", idKeputusanPermohonan);	
				r.add("id_permohonan", IdPermohonan);		
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk",r.unquote("sysdate"));	
				r.add("id_Kemaskini", id_Masuk);	
				r.add("tarikh_Kemaskini",r.unquote("sysdate"));	
				r.add("catatan", txtCatatanSD);
				
				sql = r.getSQLUpdate("tblppksd");		
				
				System.out.println("----1-----");
				
				stmt.executeUpdate(sql);
				System.out.println("----2-----");
			
			     
			    	
				 
			      
			      if(Seksyen.equals("8"))
			      {
			      	//SEKSYEN 8
			        FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					
						//r5.add("ID_SUBURUSANSTATUS",453); 
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,id_Masuk,"107","9920279602",id_Fail);
					
			      }
			      
			      else if(Seksyen.equals("17"))
			      {
				      	
				      }
					
					
					
					
					
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		public static void insertBorangLama(HttpSession session,Hashtable data,String Seksyen) throws Exception
		  {
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    String sql5 = "";
		    String sql6 = "";
		    String sql9 = "";
		    try
		    {
		    	long idKaveatPeguam = DB.getNextID("TBLPPKKAVEATPEGUAM_SEQ");   
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");    	
		    	String tarikhHantarBorangB=(String)data.get("tarikhHantarBorangB");
		    	String tarikhTerimaBorangC=(String)data.get("tarikhTerimaBorangC");
		    	String tarikhHantarNilaian=(String)data.get("tarikhHantarNilaian");
		    	String tarikhTerimaNilaian=(String)data.get("tarikhTerimaNilaian");
		    	String keputusanBorangC=(String)data.get("keputusanBorangC");
		    	String penentuanBidangKuasa=(String)data.get("penentuanBidangKuasa");
		    //	String penentuanBidangKuasaTeruskan=(String)data.get("penentuanBidangKuasaTeruskan");
		    	String catatan=(String)data.get("catatan");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	String id_Fail=(String)data.get("id_Fail");
		    	String id_Masuk=(String)data.get("id_Masuk");
		    	String ntarikhHantarBorangB = "to_date('" + tarikhHantarBorangB + "','dd/MM/yyyy')";
		    	String ntarikhTerimaBorangC = "to_date('" + tarikhTerimaBorangC + "','dd/MM/yyyy')";
		    	String ntarikhHantarNilaian = "to_date('" + tarikhHantarNilaian + "','dd/MM/yyyy')";
		    	String ntarikhTerimaNilaian = "to_date('" + tarikhTerimaNilaian + "','dd/MM/yyyy')";
//		    	int id_Permohonan = Integer.parseInt(IdPermohonan);	    	
		    	 String userId = (String)data.get("userId");
//				 int id_Suburusanstatus=(Integer)data.get("id_Suburusanstatus");
//				 int id_Suburusanstatusfail=(Integer)data.get("id_Suburusanstatusfail");
		    	 
		    	 String id_Suburusanstatus=(String)data.get("id_Suburusanstatus");
				 String id_Suburusanstatusfail=(String)data.get("id_Suburusanstatusfail");
				 
				
				//id_sta 151,152
				 String nofailawal=(String)data.get("nofailawal");
				 String namapemohonawal=(String)data.get("namapemohonawal");
				 String tempatmohonawal=(String)data.get("tempatmohonawal");
				 
				 
				//id_sta 50
				 String idDaerah=(String)data.get("idDaerah");
			     String idNegeri=(String)data.get("idNegeri");
			   // 	String id_Fail=(String)data.get("id_Fail");
			     
			     String idDaerahPindah=(String)data.get("idDaerahPindah");
			     String idNegeriPindah=(String)data.get("idNegeriPindah");
			     
			     String tujuanPindah = (String)data.get("tujuanPindah"); //razman add
			     
				 String jenis_pej=(String)data.get("jenis_pej");
				 String txtNamaKaveat=(String)data.get("txtNamaKaveat");
				 String txtNoKaveat=(String)data.get("txtNoKaveat");
				 String txtNamaFirma=(String)data.get("txtNamaFirma");
				 String txtAlamat1Peguam=(String)data.get("txtAlamat1Peguam");
				 String txtAlamat2Peguam=(String)data.get("txtAlamat2Peguam");
				 String txtAlamat3Peguam=(String)data.get("txtAlamat3Peguam");
				 String txtPoskodPeguam=(String)data.get("txtPoskodPeguam");
				 String socNegeriPeguam=(String)data.get("socNegeriPeguam");
				 String txtBandarPeguam=(String)data.get("txtBandarPeguam");
				 String txtNomborTelefonPeguam=(String)data.get("txtNomborTelefonPeguam");
				 String txtTarikhKaveat = (String)data.get("txtTarikhKaveat");
				 
		    	 String ntxtTarikhKaveat = "to_date('" + txtTarikhKaveat + "','dd/MM/yyyy')";
		    	 
		    	 

				
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_keputusanpermohonan", idKeputusanPermohonan);
				r.add("id_permohonan", IdPermohonan);
				r.add("tarikh_hantar_borangB", r.unquote(ntarikhHantarBorangB));
				r.add("tarikh_terima_borangC", r.unquote(ntarikhTerimaBorangC));
				r.add("tarikh_hantar_nilaian", r.unquote(ntarikhHantarNilaian));
				r.add("tarikh_terima_nilaian", r.unquote(ntarikhTerimaNilaian));
				r.add("jenis_borangC", keputusanBorangC);
				
				
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk",r.unquote("sysdate"));	
				r.add("id_Kemaskini", id_Masuk);
				r.add("tarikh_Kemaskini",r.unquote("sysdate"));	
				r.add("keputusan_permohonan", penentuanBidangKuasa);
				r.add("id_negerimahkamah",idNegeri);
				r.add("id_daerah_mahkamah",idDaerah);
				r.add("FLAG_SEBABPINDAHMAHKAMAH",tujuanPindah); //razman add
				r.add("catatan", catatan);
				sql = r.getSQLInsert("tblppkkeputusanpermohonan");
				
				stmt.executeUpdate(sql);
			     if(jenis_pej.equals("99"))
			     {
			    	
			    	    Statement stmt9 = db.getStatement();
						SQLRenderer r9 = new SQLRenderer();
					    r9.add("id_kaveatpeguam", idKaveatPeguam);
						r9.add("id_permohonan", IdPermohonan);
						r9.add("nama_firma",txtNamaFirma);					
						r9.add("nama_kaveat", txtNamaKaveat);
						r9.add("no_kaveat", txtNoKaveat);
						r9.add("alamat1", txtAlamat1Peguam);
						r9.add("alamat2", txtAlamat2Peguam);
						r9.add("alamat3", txtAlamat3Peguam);
						r9.add("poskod", txtPoskodPeguam);
						r9.add("id_negeri", socNegeriPeguam);
						r9.add("id_bandar", txtBandarPeguam);
						r9.add("no_tel", txtNomborTelefonPeguam);
						r9.add("tarikh_kaveat",r.unquote(ntxtTarikhKaveat));
						
						r9.add("id_Masuk", id_Masuk);
						r9.add("tarikh_Masuk",r.unquote("sysdate"));	
						r9.add("id_Kemaskini", id_Masuk);	
						r9.add("tarikh_Kemaskini",r.unquote("sysdate"));	
						
						 sql9 = r9.getSQLInsert("tblppkkaveatpeguam");				
						stmt9.executeUpdate(sql9);
						
						
					
			    	  
			     }
			     else
			     {
			    	 
			    	 Statement stmtd1 = db.getStatement();						 
					   String sqld1 = "delete from tblppkkaveatpeguam where id_Permohonan = "+ IdPermohonan +" ";
				       stmtd1.executeUpdate(sqld1);
			    	 
			     }
				
				//System.out.println("masuk ker--->"+penentuanBidangKuasa);
			//	 db = new Db();
				  Statement stmt1 = db.getStatement();
				  SQLRenderer r1 = new SQLRenderer();
				  
				  r1.update("id_Permohonan",IdPermohonan);	
				  if(penentuanBidangKuasa!="")
				  {
					  //System.out.println("penentuanBidangKuasa UPDATE::::::"+penentuanBidangKuasa+"tempatmohonawal::::::"+tempatmohonawal);
					  ////System.out.println("tempatmohonawal::::::"+tempatmohonawal);
					    	
					 // r1.add("id_Status", penentuanBidangKuasa);
					  
					  
				  }
				  
				  r1.add("id_negeritertinggi",idNegeriPindah);
			      r1.add("id_daerahtertinggi",idDaerahPindah);
					
			      if((penentuanBidangKuasa.equals("151") || penentuanBidangKuasa.equals("152")) && !jenis_pej.equals("99"))
					{
					  r1.add("id_Pejawal", tempatmohonawal);
					  r1.add("no_Fail_Awal",nofailawal);
					  r1.add("nama_Pemohon_Awal", namapemohonawal);				
					}
				  else
				  {
					  r1.add("id_Pejawal","");
					  r1.add("no_Fail_Awal","");
					  r1.add("nama_Pemohon_Awal","");
				  }
					
				  
				  r1.add("id_Permohonan",IdPermohonan);	
				  r1.add("ID_KEMASKINI",userId);
				  r1.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			      String sql1 = r1.getSQLUpdate("tblppkpermohonan");
			      stmt1.executeUpdate(sql1);
			      
			      
			      
			        /*
					Statement stmtX = db.getStatement();
					SQLRenderer r6 = new SQLRenderer();
					r6.update("id_Suburusanstatusfail",id_Suburusanstatusfail);	
					r6.add("AKTIF",0);				
					r6.add("ID_KEMASKINI",userId);
					r6.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
					stmtX.executeUpdate(sql6);*/	 
			      
			       /*
					Statement stmtF = db.getStatement();
					SQLRenderer r5 = new SQLRenderer();
					r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
					r5.add("ID_PERMOHONAN",IdPermohonan);*/
					
			      
			      if(Seksyen.equals("8"))
			      {
			      
			      	//SEKSYEN 8
			      
					FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					if( penentuanBidangKuasa.equals("151"))
					{
						//r5.add("ID_SUBURUSANSTATUS",453); 
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"453",id_Fail);
					}				
					
					if(penentuanBidangKuasa.equals("152"))
					{
						//r5.add("ID_SUBURUSANSTATUS",454);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"454",id_Fail);
					}
					
					if(penentuanBidangKuasa.equals("53"))
					{
						//r5.add("ID_SUBURUSANSTATUS",406);
						//r5.add("ID_SUBURUSANSTATUS",435);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"406",id_Fail);
					}
					if(penentuanBidangKuasa.equals("50"))
					{
						//r5.add("ID_SUBURUSANSTATUS",401);
						//r5.add("ID_SUBURUSANSTATUS",426);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"401",id_Fail);
					}
					if(penentuanBidangKuasa.equals("70"))
					{
						// r5.add("ID_SUBURUSANSTATUS",429);
						//r5.add("ID_SUBURUSANSTATUS",273);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"429",id_Fail);
					}					
				
					if(penentuanBidangKuasa.equals("56"))
					{
						//r5.add("ID_SUBURUSANSTATUS",303);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"407",id_Fail);
					}
			      }
			      else  if(Seksyen.equals("17"))
			      {
			    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						if( penentuanBidangKuasa.equals("151"))
						{
							//r5.add("ID_SUBURUSANSTATUS",453); 
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"453",id_Fail);
						}				
						
						if(penentuanBidangKuasa.equals("152"))
						{
							//r5.add("ID_SUBURUSANSTATUS",454);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"454",id_Fail);
						}
						
						if(penentuanBidangKuasa.equals("53"))
						{
							//r5.add("ID_SUBURUSANSTATUS",406);
							//r5.add("ID_SUBURUSANSTATUS",435);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"435",id_Fail);
						}
						if(penentuanBidangKuasa.equals("50"))
						{
							//r5.add("ID_SUBURUSANSTATUS",401);
							//r5.add("ID_SUBURUSANSTATUS",426);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"426",id_Fail);
						}
						if(penentuanBidangKuasa.equals("70"))
						{
							// r5.add("ID_SUBURUSANSTATUS",429);
							//r5.add("ID_SUBURUSANSTATUS",273);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"273",id_Fail);
						}					
					
						if(penentuanBidangKuasa.equals("56"))
						{
							//r5.add("ID_SUBURUSANSTATUS",303);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"303",id_Fail);
						}
			      }
					
					/*
					r5.add("AKTIF",1);
					r5.add("id_Fail",id_Fail);
					
					
					r5.add("ID_MASUK",userId);
					r5.add("TARIKH_MASUK",r.unquote("sysdate"));
					r5.add("ID_KEMASKINI",userId);
					r5.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
					stmtF.executeUpdate(sql5);*/	
					
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
	
		public static void updateBorang(HttpSession session,Hashtable data,String Seksyen) throws Exception
		  {
			myLogger.info("updateBorang-->");
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    String sql5 = "";
		    String sql6 = "";
		    String sql9 = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ"); 
		    	long idKaveatPeguam = DB.getNextID("TBLPPKKAVEATPEGUAM_SEQ");   
		    	
		    //	idKaveatPeguam
		    	String tarikhsuratARB=(String)data.get("tarikhsuratARB");
		    	String idKeputusanPermohonanString = Long.toString(idKeputusanPermohonan);
		    	String tarikhHantarBorangB=(String)data.get("tarikhHantarBorangB");
		    	String tarikhTerimaBorangC=(String)data.get("tarikhTerimaBorangC");
		    	String tarikhHantarNilaian=(String)data.get("tarikhHantarNilaian");
		    	String tarikhTerimaNilaian=(String)data.get("tarikhTerimaNilaian");
		    	String keputusanBorangC=(String)data.get("keputusanBorangC");
		    	String penentuanBidangKuasa=(String)data.get("penentuanBidangKuasa");
		    	String tarikhTerimaSuratAkuan = (String)data.get("tarikhTerimaSuratAkuan");
		    	//String penentuanBidangKuasaTeruskan=(String)data.get("penentuanBidangKuasaTeruskan");
		    	String catatan=(String)data.get("catatan");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	String txtCatatanSD=(String)data.get("txtCatatanSD");
		    	String id_Masuk=(String)data.get("userId");
		    	String ntarikhHantarBorangB = "to_date('" + tarikhHantarBorangB + "','dd/MM/yyyy')";
		    	String ntarikhTerimaBorangC = "to_date('" + tarikhTerimaBorangC + "','dd/MM/yyyy')";
		    	String ntarikhHantarNilaian = "to_date('" + tarikhHantarNilaian + "','dd/MM/yyyy')";
		    	String ntarikhTerimaNilaian = "to_date('" + tarikhTerimaNilaian + "','dd/MM/yyyy')";
		    	
		    	
		    	 String jenis_pej=(String)data.get("jenis_pej");
				 String txtNamaKaveat=(String)data.get("txtNamaKaveat");
				 String txtNoKaveat=(String)data.get("txtNoKaveat");
				 String txtNamaFirma=(String)data.get("txtNamaFirma");
				 String txtAlamat1Peguam=(String)data.get("txtAlamat1Peguam");
				 String txtAlamat2Peguam=(String)data.get("txtAlamat2Peguam");
				 String txtAlamat3Peguam=(String)data.get("txtAlamat3Peguam");
				 String txtPoskodPeguam=(String)data.get("txtPoskodPeguam");
				 String socNegeriPeguam=(String)data.get("socNegeriPeguam");
				 String txtBandarPeguam=(String)data.get("txtBandarPeguam");
				 String txtNomborTelefonPeguam=(String)data.get("txtNomborTelefonPeguam");
		    	 String txtTarikhKaveat = (String)data.get("txtTarikhKaveat");
				 
		    	 String ntxtTarikhKaveat = "to_date('" + txtTarikhKaveat + "','dd/MM/yyyy')";
				 
//		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	 String userId = (String)data.get("userId");
				 String id_Suburusanstatus=(String)data.get("id_Suburusanstatus");
				 String id_Suburusanstatusfail=(String)data.get("id_Suburusanstatusfail");
				 
				 String id_Kemaskini=(String)data.get("id_Kemaskini"); 
				 String id_Fail=(String)data.get("id_Fail");
				 
				// String catatan=(String)data.get("catatan");
				 
				 String nofailawal=(String)data.get("nofailawal");
				 String namapemohonawal=(String)data.get("namapemohonawal");
				 String tempatmohonawal=(String)data.get("tempatmohonawal");
		    	 
				 String idDaerah=(String)data.get("idDaerah");
			     String idNegeri=(String)data.get("idNegeri");
			     String tujuanPindah=(String)data.get("tujuanPindah");//razman add
			     
			     String salinan_arahan = (String) data.get("salinan_arahan");
			     String ntarikhTerimaSuratAkuan = "to_date('" + tarikhTerimaSuratAkuan + "','dd/MM/yyyy')";
			     String idKeputusanPermohonan2 = (String) data.get("idKeputusanPermohonan");
			     String cntResultppksd = (String) data.get("cntResultppksd");
			     
			     myLogger.info("salinan_arahan-->"+salinan_arahan);
			     
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_permohonan", IdPermohonan);
				r.add("tarikh_hantar_borangB", r.unquote(ntarikhHantarBorangB));
				r.add("tarikh_terima_borangC", r.unquote(ntarikhTerimaBorangC));
				r.add("tarikh_hantar_nilaian", r.unquote(ntarikhHantarNilaian));
				r.add("tarikh_terima_nilaian", r.unquote(ntarikhTerimaNilaian));
				r.add("jenis_borangC", keputusanBorangC);
				
		        r.add("keputusan_permohonan", penentuanBidangKuasa);
		        r.add("flag_salinan_arahan",salinan_arahan);
		        
				r.add("id_negerimahkamah",idNegeri);
				r.add("id_daerah_mahkamah",idDaerah);
				r.add("FLAG_SEBABPINDAHMAHKAMAH",tujuanPindah);//razman add
					
			
				
		        
		        r.add("id_Kemaskini", id_Kemaskini);
				r.add("tarikh_Kemaskini",r.unquote("sysdate"));	
		    	
				r.add("catatan", catatan);
				r.add("catatan_sd", txtCatatanSD);
				//r.add("tarikh_suratarb",tarikhsuratARB);
				sql = r.getSQLUpdate("tblppkkeputusanpermohonan");
			    System.out.println("sql update-->"+sql);
				stmt.executeUpdate(sql);
				
				//System.out.println("masuk ker--->"+penentuanBidangKuasa);
				
				
				
				
				
			//	 db = new Db();
				  Statement stmt1 = db.getStatement();
				  SQLRenderer r1 = new SQLRenderer();
				  
				  r1.update("id_Permohonan",IdPermohonan);	
				  
				if(penentuanBidangKuasa!="")
				{
					
					 //System.out.println("penentuanBidangKuasa UPDATE::::::"+penentuanBidangKuasa+"tempatmohonawal::::::"+tempatmohonawal);
					 		//r1.add("id_Status", penentuanBidangKuasa);
			    		
				}

				
					
					System.out.println("penentuanBidangKuasa = "+penentuanBidangKuasa);
					System.out.println("jenis_pej = "+jenis_pej);
					 if((penentuanBidangKuasa.equals("151") || penentuanBidangKuasa.equals("152")) && !jenis_pej.equals("99"))
						{
						  r1.add("id_Pejawal", tempatmohonawal);
						  r1.add("no_Fail_Awal",nofailawal);
						  r1.add("nama_Pemohon_Awal", namapemohonawal);	
						  r1.add("tarikh_terima_surat_akuan",r.unquote(ntarikhTerimaSuratAkuan));
						}
					  else
					  {
						  r1.add("id_Pejawal","");
						  r1.add("no_Fail_Awal","");
						  r1.add("nama_Pemohon_Awal","");
					  }
						
					
					 if(penentuanBidangKuasa.equals("107"))
						{
						  r1.add("id_status","107");
						}	
				
			    	
				 r1.add("ID_KEMASKINI",userId);
					r1.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			    	
				 r1.add("id_Permohonan",IdPermohonan);
			    
			      String sql1 = r1.getSQLUpdate("tblppkpermohonan");
			      System.out.println("sql update permohonan>"+sql1);
			      stmt1.executeUpdate(sql1);
				
			      System.out.println("cntResultppksd --->"+cntResultppksd);
			      System.out.println("idKeputusanPermohonan2 --->"+idKeputusanPermohonan2);
					
					
			      if(penentuanBidangKuasa.equals("107"))
					{
			    	  if (idKeputusanPermohonan2.equals(""))
			    		{
			    		  Hashtable h = null;
							h = new Hashtable();
							h.put("jenisSD", "107");
							h.put("txtCatatanSD", txtCatatanSD);
							h.put("IdPermohonan", IdPermohonan);
							h.put("userId", session.getAttribute("_ekptg_user_id"));
							h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
							h.put("id_Fail", id_Fail);
							String uid = (String) session.getAttribute("_ekptg_user_id");
						
						
							System.out.println("-------checkdulusamadaSDtelahwujudataubelum2----");
							insertSDTable(session, h, "8");
						}
						else
						{
							if(cntResultppksd.equals("0")) //salnizam
							{
								Hashtable h = null;
								h = new Hashtable();
								h.put("jenisSD", "107");
								h.put("txtCatatanSD", txtCatatanSD);
								h.put("IdPermohonan", IdPermohonan);
								h.put("userId", session.getAttribute("_ekptg_user_id"));
								h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
								h.put("id_Fail", id_Fail);
								String uid = (String) session.getAttribute("_ekptg_user_id");
								insertSDTable(session, h, "8");
								String idperbicaraan = addNotis(idKeputusanPermohonan2,id_Masuk);
								System.out.println("-------idperbicaraan----"+idperbicaraan);
								add_maklumatPerintah(idperbicaraan,id_Masuk);
							}
							else
							{
								updateSDTable(session, idKeputusanPermohonan2,txtCatatanSD,IdPermohonan,id_Masuk,id_Fail);
								//komen dahulu part kat bawah ni.. sebab tak perlu insert tarikh bicara yang baharu utk SD yang diupdate,
								//dan tak perlu juga utk create perintah yang baharu jugak
//								String idperbicaraan = addNotis(idKeputusanPermohonan2,id_Masuk);
//								System.out.println("-------idperbicaraan----"+idperbicaraan);
//								add_maklumatPerintah(idperbicaraan,id_Masuk);
							}
						}
						
						

						/*Hashtable h = null;
						h = new Hashtable();
						h.put("jenisSD", getParam("sorPenentuanBidangKuasa"));
						h.put("txtCatatanSD", getParam("txtCatatanSD"));
						h.put("IdPermohonan", getParam("idPermohonan"));
						h.put("userId", session.getAttribute("_ekptg_user_id"));
						h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
						h.put("id_Fail", getParam("id_Fail"));
						String uid = (String) session.getAttribute("_ekptg_user_id");*/
					}
				
			      
			      if(jenis_pej.equals("99"))
				     {
			    	  
			    	  Statement stmtd2 = db.getStatement();						 
					  String sqld3 = "delete from tblppkkaveatpeguam where id_Permohonan = "+ IdPermohonan +" ";
				      stmtd2.executeUpdate(sqld3);
				    	
				    	    Statement stmt9 = db.getStatement();
							SQLRenderer r9 = new SQLRenderer();
						    r9.add("id_kaveatpeguam", idKaveatPeguam);
							r9.add("id_permohonan", IdPermohonan);
							r9.add("nama_firma",txtNamaFirma);					
							r9.add("nama_kaveat", txtNamaKaveat);
							r9.add("no_kaveat", txtNoKaveat);
							r9.add("alamat1", txtAlamat1Peguam);
							r9.add("alamat2", txtAlamat2Peguam);
							r9.add("alamat3", txtAlamat3Peguam);
							r9.add("poskod", txtPoskodPeguam);
							r9.add("id_negeri", socNegeriPeguam);
							r9.add("id_bandar", txtBandarPeguam);
							r9.add("no_tel", txtNomborTelefonPeguam);
							r9.add("tarikh_kaveat",r.unquote(ntxtTarikhKaveat));
							
							r9.add("id_Masuk", userId);
							r9.add("tarikh_Masuk",r.unquote("sysdate"));	
							r9.add("id_Kemaskini", userId);	
							r9.add("tarikh_Kemaskini",r.unquote("sysdate"));	
							
							 sql9 = r9.getSQLInsert("tblppkkaveatpeguam");				
							stmt9.executeUpdate(sql9);
							
							
						//	System.out.println("Bandar Peguam"+sql9);
							
						
				    	  
				     }
				     else
				     {
				    	
				    	   Statement stmtd1 = db.getStatement();						 
						   String sqld1 = "delete from tblppkkaveatpeguam where id_Permohonan = "+ IdPermohonan +" ";
					       stmtd1.executeUpdate(sqld1);
				    	 
				     }
					
			      
				
				
			  /*
					Statement stmtX = db.getStatement();
					SQLRenderer r6 = new SQLRenderer();
					r6.update("id_Suburusanstatusfail",id_Suburusanstatusfail);	
					r6.add("AKTIF",0);				
					r6.add("ID_KEMASKINI",userId);
					r6.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
					stmtX.executeUpdate(sql6);*/	 
			      
			        /*
					Statement stmtF = db.getStatement();
					SQLRenderer r5 = new SQLRenderer();
					r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
					r5.add("ID_PERMOHONAN",IdPermohonan);
					*/
			      
			      if(Seksyen.equals("8"))
			      {
			      	//SEKSYEN 8
			        FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					if( penentuanBidangKuasa.equals("151"))
					{
						//r5.add("ID_SUBURUSANSTATUS",453); 
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"453",id_Fail);
					}
					if(penentuanBidangKuasa.equals("152"))
					{
						//r5.add("ID_SUBURUSANSTATUS",454);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"454",id_Fail);
					}
					if(penentuanBidangKuasa.equals("53"))
					{						
						//r5.add("ID_SUBURUSANSTATUS",435);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"406",id_Fail);
					}
					if(penentuanBidangKuasa.equals("50"))
					{						
						//r5.add("ID_SUBURUSANSTATUS",426);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"401",id_Fail);
					}
					if(penentuanBidangKuasa.equals("70"))
					{					
						//r5.add("ID_SUBURUSANSTATUS",273);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"429",id_Fail);
					}
					if(penentuanBidangKuasa.equals("107"))
					{					
						myLogger.info(":::::::::: id penentuan bidang kuasa = 107 ::::::::::");
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"9920279602",id_Fail);
					}
			      }
			      
			      else if(Seksyen.equals("17"))
			      {
				      	//SEKSYEN 8
				        FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						if( penentuanBidangKuasa.equals("151"))
						{
							//r5.add("ID_SUBURUSANSTATUS",453); 
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"453",id_Fail);
						}
						if(penentuanBidangKuasa.equals("152"))
						{
							//r5.add("ID_SUBURUSANSTATUS",454);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"454",id_Fail);
						}
						if(penentuanBidangKuasa.equals("53"))
						{						
							//r5.add("ID_SUBURUSANSTATUS",435);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"435",id_Fail);
						}
						if(penentuanBidangKuasa.equals("50"))
						{						
							//r5.add("ID_SUBURUSANSTATUS",426);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"426",id_Fail);
						}
						if(penentuanBidangKuasa.equals("70"))
						{					
							//r5.add("ID_SUBURUSANSTATUS",273);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"273",id_Fail);
						}
				      }
					
					
					
				    /*
					r5.add("AKTIF",1);
					r5.add("ID_MASUK",userId);
					r5.add("TARIKH_MASUK",r.unquote("sysdate"));
					r5.add("ID_KEMASKINI",userId);
					r5.add("id_Fail",id_Fail);
					r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
					sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
					stmtF.executeUpdate(sql5);	
			      */
				
				
				
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		public static void add_maklumatPerintah(String id_perbicaraan,String usid) throws Exception {
			
				Connection conn = null;
				Db db = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String sql="";	
			
					try
					{
					db = new Db();
					conn = db.getConnection();
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					/*String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
					String T_date_kiv = "to_date('" + date_kiv + "','dd/MM/yyyy')";*/
					
					myLogger.info(":::::::::: id_perbicaraan = "+id_perbicaraan);
					long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");	
					myLogger.info(":::::::::: id_perintah = "+id_perintah);
					PreparedStatement ps = conn.prepareStatement("insert into TBLPPKPERINTAH " +
		                    "(id_perintah,id_perbicaraan," +
		                    "ID_MASUK,ID_KEMASKINI,FLAG_JENIS_KEPUTUSAN, TARIKH_MASUK,TARIKH_KEMASKINI,tarikh_perintah" +
		                    ") " +
		                    "values(?,?,?,?,0,sysdate,sysdate,sysdate)");
					        ps.setLong(1, id_perintah);
					        ps.setString(2, id_perbicaraan);
					        ps.setString(3, usid);
					        ps.setString(4, usid);	
					        //,"+check_kiv+","+r.unquote(T_date_kiv)+","+catatan_kiv+"
					        ps.executeUpdate();
					 myLogger.info(":::::::::: TUTUP INSERT PERINTAH");
					 
					 //untuk simpan history dokumen KIV - aishahlatip
					/* if(check_kiv.equals("1")){
						 add_kivHistory( id_perintah, id_perbicaraan, usid,
								 check_kiv, date_kiv, catatan_kiv);
					 }*/
					 
					 
					
					/*
					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";						
					
				  //------------------------------- TBLPPKPERINTAH ------------------------------------				    
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("id_perintah",id_perintah);
					r.add("id_perbicaraan",id_perbicaraan);
					r.add("tarikh_perintah", r.unquote(TTP));
					r.add("id_unitpsk",EDITsocPegawaiPengendali);
					r.add("flag_jenis_keputusan",flag_jenis_keputusan);
					r.add("catatan",txtCatatanSelesai);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));

					sql = r.getSQLInsert("Tblppkperintah");		
					myLogger.info("INSERT TBLPPKPERINTAH = "+sql);
					stmt.executeUpdate(sql);*/
					
					
					
					
				}finally {
					if(db != null) db.close();
				}			
		}	
		
		private static String idPerbicaraan = "";
		// Simpan notis
		public static String addNotis(String id_keputusanpermohonan, String id_masuk)throws Exception {

			Connection conn = null;
			Db db = null;
			String sql = "";
			String sql5 = "";
			String sql6 = ""; 

			long id_perbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
			try {

				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();

				// generate other table id
				long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
				
				
				idPerbicaraan = String.valueOf(id_perbicaraan);
				/*String id_permohonan = (String) data.get("id_permohonan");
				String id_fail = (String) data.get("id_fail");
				String id_suburusanstatusfail = (String) data
						.get("id_suburusanstatusfail");
				String id_keputusanpermohonan = (String) data
						.get("id_keputusanpermohonan");
				String id_ppkrujunit = (String) data.get("pegawai");
				String id_pejabatjkptg = (String) data.get("tempat_bicara");

				String jenispejabat = (String) data.get("jenispejabat");

				String negeri = (String) data.get("negeri");
				String id_masuk = (String) data.get("id_masuk");

				String tarikh_bicara = (String) data.get("tarikh_bicara");
				String masa_bicara = (String) data.get("masa_bicara");
				String alamat1 = (String) data.get("alamat_bicara1");
				String alamat2 = (String) data.get("alamat_bicara2");
				String alamat3 = (String) data.get("alamat_bicara3");
				String poskod = (String) data.get("poskod");
				String tarikh_notis = (String) data.get("tarikh_notis");

				// -- 09122009
				String socJenisWaktu = (String) data.get("socJenisWaktu");

				String nama_pegawai = "";
				String nama_pejabat = "";
				String id_pejabat = "";
				String tablePejabat = "";

				String firstNotis = "1";

				String TB = "to_date('" + tarikh_bicara + "','dd/MM/yyyy')";
				String TN = "to_date('" + tarikh_notis + "','dd/MM/yyyy')";

				// get nama pegawai
				SQLRenderer rMT = new SQLRenderer();
				rMT.add("id_unitpsk");
				rMT.add("nama_pegawai");
				rMT.add("id_unitpsk", id_ppkrujunit);
				sql = rMT.getSQLSelect("Tblppkrujunit");

				ResultSet rsMT = stmt.executeQuery(sql);
				while (rsMT.next()) {
					nama_pegawai = rsMT.getString("nama_pegawai");
				}

				String id_bandar = "";

				String keterangan = "";*/

				
				// add data (notis perbicaraan) with bil = 1
				SQLRenderer r = new SQLRenderer();
				r.add("id_perbicaraan", id_perbicaraan);
				r.add("id_keputusanpermohonan", id_keputusanpermohonan);
				r.add("catatan", "Summary Distribution");
				r.add("id_masuk", id_masuk);
				r.add("tarikh_masuk", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("Tblppkperbicaraan");
				stmt.executeUpdate(sql);
				
				//aishahlatip tutup sebab penambahan baru untuk pilihan penyampai laporan 01082017
				// create table notisobmst
				SQLRenderer rMST = new SQLRenderer();
				rMST.add("id_notisobmst", id_notisobmst);
				rMST.add("id_masuk", id_masuk);
				rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
				sql = rMST.getSQLInsert("Tblppknotisobmst");
				stmt.executeUpdate(sql);

				// create child table
				SQLRenderer r1 = new SQLRenderer();
				r1.add("id_perbicaraan", id_perbicaraan);
				r1.add("id_notisobmst", id_notisobmst);
				r1.add("flag_jenis_notis", 0);
				r1.add("id_masuk", id_masuk);
				r1.add("tarikh_masuk", r1.unquote("sysdate"));
				sql = r1.getSQLInsert("Tblppknotisperbicaraan");
				stmt.executeUpdate(sql);

				// tukar status permohonan diteruskan => notis perbicaraan
				int status_notisperbicaraan = 18;

				/*
				 * SQLRenderer rP = new SQLRenderer();
				 * rP.update("id_permohonan",id_permohonan); rP.add("id_status",
				 * status_notisperbicaraan); rP.add("id_kemaskini",id_masuk);
				 * rP.add("tarikh_kemaskini", rP.unquote("sysdate")); sql =
				 * rP.getSQLUpdate("Tblppkpermohonan"); stmt.executeUpdate(sql);
				 */

				// update n add tblrujsuburusanstatus
				/*
				 * SQLRenderer r6 = new SQLRenderer();
				 * r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);
				 * r6.add("AKTIF",0); r6.add("ID_KEMASKINI",id_masuk);
				 * r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate")); sql6 =
				 * r6.getSQLUpdate("tblrujsuburusanstatusfail");
				 * stmt.executeUpdate(sql6);
				 * 
				 * 
				 * SQLRenderer r5 = new SQLRenderer();
				 * r5.add("ID_SUBURUSANSTATUSFAIL"
				 * ,DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				 * r5.add("ID_PERMOHONAN",id_permohonan); r5.add("ID_FAIL",id_fail);
				 * r5.add("ID_SUBURUSANSTATUS",354); r5.add("AKTIF",1);
				 * r5.add("ID_MASUK",id_masuk); r5.add("id_kemaskini",id_masuk);
				 * r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
				 * r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate")); sql5 =
				 * r5.getSQLInsert("tblrujsuburusanstatusfail");
				 * stmt.executeUpdate(sql5);
				 */

				conn.commit();

				/*Statement stmtfail = db.getStatement();
				SQLRenderer rfail = new SQLRenderer();
				String sql_fail = "SELECT F.NO_FAIL,  FROM  TBLPPKPERBICARAAN B,"
						+ " TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F "
						+ " WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN "
						+ " AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN "
						+ " AND P.ID_FAIL = F.ID_FAIL AND B.ID_PERBICARAAN = '"
						+ id_perbicaraan + "'";
				
				String sql_fail = "SELECT F.NO_FAIL, S.NAMA_SIMATI  FROM  TBLPPKPERBICARAAN B, " +
						" TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN P,TBLPFDFAIL F , TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI M " +
						" WHERE B.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN " +
						" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN " +
						" AND P.ID_FAIL = F.ID_FAIL " +
						" AND S.ID_SIMATI = M.ID_SIMATI " +
						" AND M.ID_PERMOHONAN = KP.ID_PERMOHONAN " +
						" AND B.ID_PERBICARAAN = '"+ id_perbicaraan + "'";
				ResultSet rsfail = stmtfail.executeQuery(sql_fail);
				String no_fail = "";
				String nama_simati= "";
				while (rsfail.next()) {
					
					if (rsfail.getString("NO_FAIL") != null) {
						no_fail = rsfail.getString("NO_FAIL");
					}
					
					if (rsfail.getString("NAMA_SIMATI") != null) {
						nama_simati = rsfail.getString("NAMA_SIMATI");
					}
				}

				
				 * r.add("bil_bicara", firstNotis); r.add("masa_bicara",
				 * masa_bicara); r.add("alamat_bicara1", alamat1);
				 * r.add("alamat_bicara2", alamat2); r.add("alamat_bicara3",
				 * alamat3); r.add("poskod", poskod); r.add("peg_pengendali",
				 * nama_pegawai); r.add("tempat_bicara", nama_pejabat);
				 
				
				
				*//** START ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **//*			
				String event_id = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
				String event_text = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + no_fail;
				String event_location = "";
				if (!"".equals(nama_pejabat))
					event_location = event_location + nama_pejabat;
				if (!"".equals(alamat1))
					event_location = event_location + ", " + alamat1;
				if (!"".equals(alamat2))
					event_location = event_location + ", " + alamat2;
				if (!"".equals(alamat3))
					event_location = event_location + ", " + alamat3;
				if (!"".equals(poskod))
					event_location = event_location + ", " + poskod;
				if (!"".equals(keterangan))
					event_location = event_location + " " + keterangan;			
				
				
				//########################## start insert calendar #############################
				String userLoginPegawai = "";
				Statement stmtPegawai = db.getStatement();
				//System.out.println("id_ppkrujunit========"+id_ppkrujunit);
				String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE UPPER(TBLPPKRUJUNIT.NAMA_PEGAWAI) = UPPER(USERS.USER_NAME) AND"
					+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "'";
			
				//aishah edit on 5/5/2017
				String sqlPegawai = "SELECT * FROM TBLPPKRUJUNIT, USERS WHERE TBLPPKRUJUNIT.USER_ID = USERS.USER_ID AND"
						+ " USERS.USER_TYPE = 'internal' AND TBLPPKRUJUNIT.ID_UNITPSK = '" + id_ppkrujunit + "'";
				System.out.println("sqlPegawai2========"+sqlPegawai);
				ResultSet rsPegawai = stmtPegawai.executeQuery(sqlPegawai);		
				if (rsPegawai.next()) {
					if (rsPegawai.getString("USER_LOGIN") != null) {
					userLoginPegawai = rsPegawai.getString("USER_LOGIN");
					
					//aishah edit 26072017
					System.out.println("userLoginPegawai========"+userLoginPegawai);
					myLogger.info("save event  >>>>>> userLoginPegawai : "+userLoginPegawai);
					myLogger.info("save event  >>>>>> id_perbicaraan : "+id_perbicaraan);
					myLogger.info("save event  >>>>>> event_text : "+event_text);
					myLogger.info("save event  >>>>>> event_location : "+event_location);
					myLogger.info("save event  >>>>>> tarikh_bicara : "+tarikh_bicara);
					myLogger.info("save event  >>>>>> masa_bicara : "+masa_bicara);
					myLogger.info("save event  >>>>>> socJenisWaktu : "+socJenisWaktu);
					
					

					
					
					
					
					saveActivityEvent(userLoginPegawai, id_perbicaraan, event_text, event_location, tarikh_bicara, masa_bicara, socJenisWaktu);
					*/
					
				
				
				/** END ADD BY PEJE - REGISTER EVENT TO MYCALENDAR TABLE **/

//				 addCalendarNotis(session, event_id, event_text, tarikh_bicara,id_ppkrujunit, id_perbicaraan + "");
				// saveActivityEvent(session,"09:00 AM","09:30 AM",event_text,nama_pejabat,
				// remark,
				// tarikh_bicara,user_login);
				//########################## end insert calendar #############################
				// :::SUB
				// ID_STATUS : status_notisperbicaraan
				// ID_SUBURUSAN : 354
				/*FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
				logic_A.kemaskiniSubUrusanStatusFail(session, id_permohonan,
						id_masuk, status_notisperbicaraan + "", "354", id_fail);
				

				 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				 Date date = new Date();  
				 myLogger.info("sysdate ==== "+formatter.format(date));  
				 if ((tarikh_bicara.compareTo(formatter.format(date)) > 0 ) || (tarikh_bicara.compareTo(formatter.format(date)) == 0 ) ){
					 myLogger.info("send email  >>>>>> tarikh_bicara : ");
					 hantarEmelTTPegawai(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
					 
					//###############add send email#########################
					//hantarEmel(session,Long.toString(id_perbicaraan),no_fail,nama_simati);
				 }*/
			

			} catch (SQLException se) {
				try {
					conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:" + se2.getMessage());
				}
				throw new Exception("Ralat : Masalah penyimpanan data "
						+ se.getMessage());
			} finally {
				if (db != null)
					db.close();
			}
			return idPerbicaraan;

		}
		
		
		public static void updateBorangLama(HttpSession session,Hashtable data,String Seksyen) throws Exception
		  {
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    String sql5 = "";
		    String sql6 = "";
		    String sql9 = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");
		    	long idKaveatPeguam = DB.getNextID("TBLPPKKAVEATPEGUAM_SEQ");   
		    	String tarikhHantarBorangB=(String)data.get("tarikhHantarBorangB");
		    	String tarikhTerimaBorangC=(String)data.get("tarikhTerimaBorangC");
		    	String tarikhHantarNilaian=(String)data.get("tarikhHantarNilaian");
		    	String tarikhTerimaNilaian=(String)data.get("tarikhTerimaNilaian");
		    	String keputusanBorangC=(String)data.get("keputusanBorangC");
		    	String penentuanBidangKuasa=(String)data.get("penentuanBidangKuasa");
		    	//String penentuanBidangKuasaTeruskan=(String)data.get("penentuanBidangKuasaTeruskan");
		    	String catatan=(String)data.get("catatan");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
		    	String ntarikhHantarBorangB = "to_date('" + tarikhHantarBorangB + "','dd/MM/yyyy')";
		    	String ntarikhTerimaBorangC = "to_date('" + tarikhTerimaBorangC + "','dd/MM/yyyy')";
		    	String ntarikhHantarNilaian = "to_date('" + tarikhHantarNilaian + "','dd/MM/yyyy')";
		    	String ntarikhTerimaNilaian = "to_date('" + tarikhTerimaNilaian + "','dd/MM/yyyy')";
		    	
//		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	String userId = (String)data.get("userId");
				 String id_Suburusanstatus=(String)data.get("id_Suburusanstatus");
				 String id_Suburusanstatusfail=(String)data.get("id_Suburusanstatusfail");
				 
				 String id_Kemaskini=(String)data.get("id_Kemaskini"); 
				 String id_Fail=(String)data.get("id_Fail");
				 
				 
				 
				// String catatan=(String)data.get("catatan");
				 
				 String nofailawal=(String)data.get("nofailawal");
				 String namapemohonawal=(String)data.get("namapemohonawal");
				 String tempatmohonawal=(String)data.get("tempatmohonawal");
		    	
				 String idDaerah=(String)data.get("idDaerah");
			     String idNegeri=(String)data.get("idNegeri");
			     
			     String idDaerahPindah=(String)data.get("idDaerahPindah");
			     String idNegeriPindah=(String)data.get("idNegeriPindah");
			     
			     String tujuanPindah=(String)data.get("tujuanPindah");//razman add
			     
			     String jenis_pej=(String)data.get("jenis_pej");
				 String txtNamaKaveat=(String)data.get("txtNamaKaveat");
				 String txtNoKaveat=(String)data.get("txtNoKaveat");
				 String txtNamaFirma=(String)data.get("txtNamaFirma");
				 String txtAlamat1Peguam=(String)data.get("txtAlamat1Peguam");
				 String txtAlamat2Peguam=(String)data.get("txtAlamat2Peguam");
				 String txtAlamat3Peguam=(String)data.get("txtAlamat3Peguam");
				 String txtPoskodPeguam=(String)data.get("txtPoskodPeguam");
				 String socNegeriPeguam=(String)data.get("socNegeriPeguam");
				 String txtBandarPeguam=(String)data.get("txtBandarPeguam");
				 String txtNomborTelefonPeguam=(String)data.get("txtNomborTelefonPeguam");
			     String txtTarikhKaveat = (String)data.get("txtTarikhKaveat");
			     
		    	 String ntxtTarikhKaveat = "to_date('" + txtTarikhKaveat + "','dd/MM/yyyy')";
		    	 String salinan_arahan = (String) data.get("salinan_arahan");

			   
				 
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_permohonan", IdPermohonan);
				r.add("tarikh_hantar_borangB", r.unquote(ntarikhHantarBorangB));
				r.add("tarikh_terima_borangC", r.unquote(ntarikhTerimaBorangC));
				r.add("tarikh_hantar_nilaian", r.unquote(ntarikhHantarNilaian));
				r.add("tarikh_terima_nilaian", r.unquote(ntarikhTerimaNilaian));
				r.add("jenis_borangC", keputusanBorangC);
				
		        r.add("keputusan_permohonan", penentuanBidangKuasa);
		        
		        
					r.add("id_negerimahkamah",idNegeri);
					r.add("id_daerah_mahkamah",idDaerah);
					
					
					
					r.add("FLAG_SEBABPINDAHMAHKAMAH",tujuanPindah);//razman add
		      
				r.add("FLAG_SALINAN_ARAHAN",salinan_arahan);
		        r.add("id_Kemaskini", id_Kemaskini);
				r.add("tarikh_Kemaskini",r.unquote("sysdate"));	
		    	
				r.add("catatan", catatan);
				sql = r.getSQLUpdate("tblppkkeputusanpermohonan");
			System.out.println("sql update-->"+sql);
				stmt.executeUpdate(sql);
				
				//System.out.println("masuk ker--->"+penentuanBidangKuasa);
				
				
				
				
				
			//	 db = new Db();
				  Statement stmt1 = db.getStatement();
				  SQLRenderer r1 = new SQLRenderer();
				  
				  r1.update("id_Permohonan",IdPermohonan);	
				  
				if(penentuanBidangKuasa!="")
				{
					
					 //System.out.println("penentuanBidangKuasa UPDATE::::::"+penentuanBidangKuasa+"tempatmohonawal::::::"+tempatmohonawal);
					 		//r1.add("id_Status", penentuanBidangKuasa);
			    		
				}

				
				 if((penentuanBidangKuasa.equals("151") || penentuanBidangKuasa.equals("152")) && !jenis_pej.equals("99"))
					{
					  r1.add("id_Pejawal", tempatmohonawal);
					  r1.add("no_Fail_Awal",nofailawal);
					  r1.add("nama_Pemohon_Awal", namapemohonawal);				
					}
				  else
				  {
					  r1.add("id_Pejawal","");
					  r1.add("no_Fail_Awal","");
					  r1.add("nama_Pemohon_Awal","");
				  }
					
				
				
			    	
				 r1.add("ID_KEMASKINI",userId);
					r1.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			    	
				 r1.add("id_Permohonan",IdPermohonan);
			    
				 
				 r1.add("id_negeritertinggi",idNegeriPindah);
			    	r1.add("id_daerahtertinggi",idDaerahPindah);
				 
			      String sql1 = r1.getSQLUpdate("tblppkpermohonan");
			      stmt1.executeUpdate(sql1);
				
				
				
			      
			      if(jenis_pej.equals("99"))
				     {
			    	  
			    	  Statement stmtd2 = db.getStatement();						 
					  String sqld3 = "delete from tblppkkaveatpeguam where id_Permohonan = "+ IdPermohonan +" ";
				      stmtd2.executeUpdate(sqld3);
				    	
				    	    Statement stmt9 = db.getStatement();
							SQLRenderer r9 = new SQLRenderer();
						    r9.add("id_kaveatpeguam", idKaveatPeguam);
							r9.add("id_permohonan", IdPermohonan);
							r9.add("nama_firma",txtNamaFirma);					
							r9.add("nama_kaveat", txtNamaKaveat);
							r9.add("no_kaveat", txtNoKaveat);
							r9.add("alamat1", txtAlamat1Peguam);
							r9.add("alamat2", txtAlamat2Peguam);
							r9.add("alamat3", txtAlamat3Peguam);
							r9.add("poskod", txtPoskodPeguam);
							r9.add("id_negeri", socNegeriPeguam);
							r9.add("id_bandar", txtBandarPeguam);
							r9.add("no_tel", txtNomborTelefonPeguam);
							r9.add("tarikh_kaveat",r.unquote(ntxtTarikhKaveat));
							
							r9.add("id_Masuk", userId);
							r9.add("tarikh_Masuk",r.unquote("sysdate"));	
							r9.add("id_Kemaskini", userId);	
							r9.add("tarikh_Kemaskini",r.unquote("sysdate"));	
							
							 sql9 = r9.getSQLInsert("tblppkkaveatpeguam");				
							stmt9.executeUpdate(sql9);
							
							
						//	System.out.println("Bandar Peguam"+sql9);
							
						
				    	  
				     }
				     else
				     {
				    	
				    	   Statement stmtd1 = db.getStatement();						 
						   String sqld1 = "delete from tblppkkaveatpeguam where id_Permohonan = "+ IdPermohonan +" ";
					       stmtd1.executeUpdate(sqld1);
				    	 
				     }
					
			      
				
				    
			        /*			    
					Statement stmtX = db.getStatement();
					SQLRenderer r6 = new SQLRenderer();
					r6.update("id_Suburusanstatusfail",id_Suburusanstatusfail);	
					r6.add("AKTIF",0);				
					r6.add("ID_KEMASKINI",userId);
					r6.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
					stmtX.executeUpdate(sql6);*/	 
			      
			        /*
					Statement stmtF = db.getStatement();
					SQLRenderer r5 = new SQLRenderer();
					r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
					r5.add("ID_PERMOHONAN",IdPermohonan);
					*/
			       
			      
			      
			      
			      if(Seksyen.equals("8"))
			      {
			      
			      	//SEKSYEN 8
			      
					FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					if( penentuanBidangKuasa.equals("151"))
					{
						//r5.add("ID_SUBURUSANSTATUS",453); 
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"453",id_Fail);
					}				
					
					if(penentuanBidangKuasa.equals("152"))
					{
						//r5.add("ID_SUBURUSANSTATUS",454);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"454",id_Fail);
					}
					
					if(penentuanBidangKuasa.equals("53"))
					{
						//r5.add("ID_SUBURUSANSTATUS",406);
						//r5.add("ID_SUBURUSANSTATUS",435);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"406",id_Fail);
					}
					if(penentuanBidangKuasa.equals("50"))
					{
						//r5.add("ID_SUBURUSANSTATUS",401);
						//r5.add("ID_SUBURUSANSTATUS",426);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"401",id_Fail);
					}
					if(penentuanBidangKuasa.equals("70"))
					{
						// r5.add("ID_SUBURUSANSTATUS",429);
						//r5.add("ID_SUBURUSANSTATUS",273);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"429",id_Fail);
					}					
				
					if(penentuanBidangKuasa.equals("56"))
					{
						//r5.add("ID_SUBURUSANSTATUS",303);
						logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"407",id_Fail);
					}
			      }
			      else  if(Seksyen.equals("17"))
			      {
			    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						if( penentuanBidangKuasa.equals("151"))
						{
							//r5.add("ID_SUBURUSANSTATUS",453); 
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"453",id_Fail);
						}				
						
						if(penentuanBidangKuasa.equals("152"))
						{
							//r5.add("ID_SUBURUSANSTATUS",454);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"454",id_Fail);
						}
						
						if(penentuanBidangKuasa.equals("53"))
						{
							//r5.add("ID_SUBURUSANSTATUS",406);
							//r5.add("ID_SUBURUSANSTATUS",435);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"435",id_Fail);
						}
						if(penentuanBidangKuasa.equals("50"))
						{
							//r5.add("ID_SUBURUSANSTATUS",401);
							//r5.add("ID_SUBURUSANSTATUS",426);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"426",id_Fail);
						}
						if(penentuanBidangKuasa.equals("70"))
						{
							// r5.add("ID_SUBURUSANSTATUS",429);
							//r5.add("ID_SUBURUSANSTATUS",273);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"273",id_Fail);
						}					
					
						if(penentuanBidangKuasa.equals("56"))
						{
							//r5.add("ID_SUBURUSANSTATUS",303);
							logic_A.kemaskiniSubUrusanStatusFail(session,IdPermohonan,userId,penentuanBidangKuasa,"303",id_Fail);
						}
			      }
			      
					
					
				/*
					r5.add("AKTIF",1);
					r5.add("ID_MASUK",userId);
					r5.add("TARIKH_MASUK",r.unquote("sysdate"));
					r5.add("ID_KEMASKINI",userId);
					r5.add("id_Fail",id_Fail);
					r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
					sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
					stmtF.executeUpdate(sql5);	
			     */ 
				
				
				
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		/*
		public static void insertBorang(Hashtable data) throws Exception
		  {
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");    	
		    	String tarikhHantarBorangB=(String)data.get("tarikhHantarBorangB");
		    	String tarikhTerimaBorangC=(String)data.get("tarikhTerimaBorangC");
		    	String tarikhHantarNilaian=(String)data.get("tarikhHantarNilaian");
		    	String tarikhTerimaNilaian=(String)data.get("tarikhTerimaNilaian");
		    	String keputusanBorangC=(String)data.get("keputusanBorangC");
		    	String penentuanBidangKuasa=(String)data.get("penentuanBidangKuasa");
		    	String penentuanBidangKuasaTeruskan=(String)data.get("penentuanBidangKuasaTeruskan");
		    	String catatan=(String)data.get("catatan");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
		    	String ntarikhHantarBorangB = "to_date('" + tarikhHantarBorangB + "','dd/MM/yyyy')";
		    	String ntarikhTerimaBorangC = "to_date('" + tarikhTerimaBorangC + "','dd/MM/yyyy')";
		    	String ntarikhHantarNilaian = "to_date('" + tarikhHantarNilaian + "','dd/MM/yyyy')";
		    	String ntarikhTerimaNilaian = "to_date('" + tarikhTerimaNilaian + "','dd/MM/yyyy')";
		    	
		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("id_keputusanpermohonan", idKeputusanPermohonan);
				r.add("id_permohonan", id_Permohonan);
				r.add("tarikh_hantar_borangB", r.unquote(ntarikhHantarBorangB));
				r.add("tarikh_terima_borangC", r.unquote(ntarikhTerimaBorangC));
				r.add("tarikh_hantar_nilaian", r.unquote(ntarikhHantarNilaian));
				r.add("tarikh_terima_nilaian", r.unquote(ntarikhTerimaNilaian));
				r.add("jenis_borangC", keputusanBorangC);
				
				if(penentuanBidangKuasa=="1" || penentuanBidangKuasa=="53" || penentuanBidangKuasa=="23" || penentuanBidangKuasa=="63" || penentuanBidangKuasa=="23" || penentuanBidangKuasa=="50")
				{
				
				if (penentuanBidangKuasa == "1"){
		    		penentuanBidangKuasa = penentuanBidangKuasaTeruskan;
		    		r.add("keputusan_permohonan", penentuanBidangKuasa);
		    	}else{
		    		if (penentuanBidangKuasa == "53" || penentuanBidangKuasa == "23" || penentuanBidangKuasa == "63"){
		    			r.add("keputusan_permohonan", penentuanBidangKuasa);
		    		}else if (penentuanBidangKuasa == "50") {
		    			r.add("keputusan_permohonan", "50");
		    		}
		    	}
				}
				r.add("catatan", catatan);
				sql = r.getSQLInsert("tblppkkeputusanpermohonan");
				//System.out.println("masuk ker--->"+sql);
				stmt.executeUpdate(sql);
				
				
				  db = new Db();
				  Statement stmt1 = db.getStatement();
				  SQLRenderer r1 = new SQLRenderer();
				  
				  r1.update("id_Permohonan",id_Permohonan);	
				  
				  if(penentuanBidangKuasa=="1" || penentuanBidangKuasa=="53" || penentuanBidangKuasa=="63" || penentuanBidangKuasa=="23" || penentuanBidangKuasa=="50")
						{
				  if (penentuanBidangKuasa == "1"){
			    		penentuanBidangKuasa = penentuanBidangKuasaTeruskan;
			    		r1.add("id_Status", penentuanBidangKuasa);
			    	}else{
			    		if (penentuanBidangKuasa != "53" || penentuanBidangKuasa != "23" || penentuanBidangKuasa != "63"){
			    			r1.add("id_Status", penentuanBidangKuasa);
			    		}else if (penentuanBidangKuasa != "50") {
			    			r1.add("id_Status", "50");
			    		}
			    	}
			    	}
			    	
				 r1.add("id_Permohonan",id_Permohonan);
			    
			      String sql1 = r1.getSQLUpdate("tblppkpermohonan");
			      stmt1.executeUpdate(sql1);
				
				
				
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		
		 public static void updateStatus(Hashtable data) throws Exception {
			    Db db = null;
			    String sql = "";
			    try
			    {     
			  
			     int idpermohonan=(Integer)data.get("idPermohonan");
				 int idstatus=(Integer)data.get("idstatus");
				 
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  r.update("id_Permohonan",idpermohonan);							  
				  r.add("id_Status",14);
			    
			      sql = r.getSQLUpdate("tblppkpermohonan");
			      stmt.executeUpdate(sql);
			      
			      
			      
			      
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }
		
		public static void updateBorang(Hashtable data) throws Exception
		  {
		    Db db = null;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try
		    {
		    	long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");    	
		    	String tarikhHantarBorangB=(String)data.get("tarikhHantarBorangB");
		    	String tarikhTerimaBorangC=(String)data.get("tarikhTerimaBorangC");
		    	String tarikhHantarNilaian=(String)data.get("tarikhHantarNilaian");
		    	String tarikhTerimaNilaian=(String)data.get("tarikhTerimaNilaian");
		    	String keputusanBorangC=(String)data.get("keputusanBorangC");
		    	String penentuanBidangKuasa=(String)data.get("penentuanBidangKuasa");
		    	String penentuanBidangKuasaTeruskan=(String)data.get("penentuanBidangKuasaTeruskan");
		    	
		    	//System.out.println("penentuanBidangKuasa model"+penentuanBidangKuasa);
		    	//System.out.println("penentuanBidangKuasaTeruskan model"+penentuanBidangKuasaTeruskan);
		    	
		    	
		    	String catatan=(String)data.get("catatan");
		    	String IdPermohonan=(String)data.get("IdPermohonan");
		    	
		    	String ntarikhHantarBorangB = "to_date('" + tarikhHantarBorangB + "','dd/MM/yyyy')";
		    	String ntarikhTerimaBorangC = "to_date('" + tarikhTerimaBorangC + "','dd/MM/yyyy')";
		    	String ntarikhHantarNilaian = "to_date('" + tarikhHantarNilaian + "','dd/MM/yyyy')";
		    	String ntarikhTerimaNilaian = "to_date('" + tarikhTerimaNilaian + "','dd/MM/yyyy')";
		    	
		    	int id_Permohonan = Integer.parseInt(IdPermohonan);
		    	
		    	int p1=Integer.parseInt(penentuanBidangKuasa);
		    	int p2=Integer.parseInt((String)data.get("penentuanBidangKuasaTeruskan"));
		    	
		    	
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.update("id_permohonan", id_Permohonan);
				r.add("tarikh_hantar_borangB", r.unquote(ntarikhHantarBorangB));
				r.add("tarikh_terima_borangC", r.unquote(ntarikhTerimaBorangC));
				r.add("tarikh_hantar_nilaian", r.unquote(ntarikhHantarNilaian));
				r.add("tarikh_terima_nilaian", r.unquote(ntarikhTerimaNilaian));
				r.add("jenis_borangC", keputusanBorangC);
				
				
				
					
					if (p1==1)
					{
			    		//penentuanBidangKuasa = penentuanBidangKuasaTeruskan;
			    		r.add("keputusan_permohonan", ""+p2);
			    	}
				    else{
			    		r.add("keputusan_permohonan", ""+p1);
			    	}
		       
				
				
				
				r.add("catatan", catatan);
				sql = r.getSQLUpdate("tblppkkeputusanpermohonan");
				//System.out.println("sql update-->"+sql);
				stmt.executeUpdate(sql);
				
				  db = new Db();
				  Statement stmt1 = db.getStatement();
				  SQLRenderer r1 = new SQLRenderer();
				  
				  r1.update("id_Permohonan",id_Permohonan);	
				  

					if (p1==1){			    	
			    		r1.add("id_Status", ""+p1);
			    	}else{
			    		r1.add("id_Status", ""+p2);
			    	}
				  
				  
			 
		    	
		    	
			//  r1.add("id_Permohonan",id_Permohonan);
			    
			      String sql1 = r1.getSQLUpdate("tblppkpermohonan");
			      stmt1.executeUpdate(sql1);
				
				
				
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  	}
		*/
		private Vector listFail = new Vector();
		
		public Vector getCheckHartaDiselesaikanARB() {
			return listFail;
		}
		
		/*private void insertSDTable(HttpSession session) throws Exception {

			
			Hashtable h = null;
			h = new Hashtable();
			h.put("jenisSD", getParam("sorPenentuanBidangKuasa"));
			h.put("txtCatatanSD", getParam("txtCatatanSD"));
			h.put("IdPermohonan", getParam("idPermohonan"));
			h.put("userId", session.getAttribute("_ekptg_user_id"));
			h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
			h.put("id_Fail", getParam("id_Fail"));
			String uid = (String) session.getAttribute("_ekptg_user_id");
			FrmPrmhnnSek8KeputusanPermohonanInternalData.insertSDTable(session, h,"8");
			FrmPrmhnnSek8KptsanBicaraData.add_maklumatPerintah("99991111111",uid,"","","","","","","");
		}*/
		
		
		private Vector listKaveatPeguam = new Vector();
		
		public Vector getListKaveatPeguam() {
			return listKaveatPeguam;
		}
		
		public void setCheckHartaDiselesaikanARB(String id_Permohonansimati) throws Exception{
			System.out.println("String id_Permohonansimati1 = " + id_Permohonansimati);
			Db db = null;
			listFail.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("String id_Permohonansimati2 = " + id_Permohonansimati);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = "SELECT * FROM TBLPPKHA WHERE ID_JENISHA = '98' AND ID_PERMOHONANSIMATI = '" + id_Permohonansimati + "'" + "";

				System.out.println("setCheckHartaDiselesaikanARB = " + sql);

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				while (rs.next()) {
					h = new Hashtable();
					h.put("id_ha", rs.getString("id_ha")==null?"":rs.getString("id_ha"));
					listFail.addElement(h);
			}
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public void setListKaveatPeguam(String id_Permohonansimati) throws Exception{
			System.out.println("String id_Permohonansimati1 = " + id_Permohonansimati);
			Db db = null;
			listKaveatPeguam.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				sql = "SELECT * FROM TBLPPKKAVEATPEGUAM WHERE ID_PERMOHONAN in (SELECT ID_PERMOHONAN FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI = '"+id_Permohonansimati+"'" + ")";

				System.out.println("setListKaveatPeguam = " + sql);

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;

				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_KAVEATPEGUAM", rs.getString("ID_KAVEATPEGUAM")==null?"":rs.getString("ID_KAVEATPEGUAM"));
					h.put("NAMA_KAVEAT", rs.getString("NAMA_KAVEAT")==null?"":rs.getString("NAMA_KAVEAT"));
					h.put("NO_KAVEAT", rs.getString("NO_KAVEAT")==null?"":rs.getString("NO_KAVEAT"));
					h.put("NAMA_FIRMA", rs.getString("NAMA_FIRMA")==null?"":rs.getString("NAMA_FIRMA"));
					h.put("ALAMAT1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
					h.put("ALAMAT2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
					h.put("ALAMAT3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
					h.put("POSKOD", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("BANDAR", rs.getString("BANDAR")==null?"":rs.getString("BANDAR"));
					h.put("NO_TEL", rs.getString("NO_TEL")==null?"":rs.getString("NO_TEL"));
					h.put("TARIKH_KAVEAT", rs.getString("TARIKH_KAVEAT")==null?"":sdf.format(rs.getDate("TARIKH_KAVEAT")));
					listKaveatPeguam.addElement(h);
			}
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public void updatePenting(Hashtable data) throws Exception {
			Connection conn = null;
			Db db = null;
			String sql = "";
			String sql1 = "";
			try {

				String nama_warga = (String) data.get("nama_warga");
				String id_Permohonansimati = (String) data.get("id_Permohonansimati");
				String idsimati = (String) data.get("idSimati");
				String idob = (String) data.get("idob");
				String namaob = (String) data.get("namaOB");
				String id_Pemohon = (String) data.get("id_Pemohon");
				int jenishutang = (Integer) data.get("jenishutang");
				String nokpbaru = (String) data.get("nokpbaru");
				String nokppenting = (String) data.get("nokppenting");
				String jenisKp = (String) data.get("jenisKp");
				String nokplain = (String) data.get("nokplain");
				String statusOB = (String) data.get("statusOB");
				int taraf = (Integer) data.get("taraf");
				String jantina = (String) data.get("jantina");
				String agama = (String) data.get("agama");
				String warga = (String) data.get("warga");
				String umur = (String) data.get("umur");
				String noberanak = (String) data.get("noberanak");
				String alamat1 = (String) data.get("alamat1");
				String alamat2 = (String) data.get("alamat2");
				String alamat3 = (String) data.get("alamat3");
				String poskod = (String) data.get("poskod");
				String bandar = (String) data.get("bandar");
				int negeri = (Integer) data.get("negeri");
				double nilaihutang = (Double) data.get("nilaihutang");
				String butiranhutang = (String) data.get("butiranhutang");
				String noakaun = (String) data.get("noakaun");
				String alamat1Surat = (String) data.get("alamat1Surat");
				String alamat2Surat = (String) data.get("alamat2Surat");
				String alamat3Surat = (String) data.get("alamat3Surat");
				String poskodSurat = (String) data.get("poskodSurat");
				String bandarSurat = (String) data.get("bandarSurat");
				int negeriSurat = (Integer) data.get("negeriSurat");
				String catatan = (String) data.get("catatan");
				String dob = (String) data.get("dob");
				String notel = (String) data.get("notel");
				String dobpenting = "to_date('" + dob + "','dd/MM/yyyy')";
				String id_Kemaskini = (String) data.get("id_Kemaskini");
				String tarikh_Kemaskini = (String) data.get("tarikh_Kemaskini");
				String jenis_pej = (String) data.get("jenis_pej");
				String no_fax = (String) data.get("no_fax");
				String jenis_pemohon = (String) data.get("jenis_pemohon");
				String no_hp = (String) data.get("no_hp");
				String salinan_arahan = (String) data.get("salinan_arahan");

				String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

				int idbandar = 0;
				int idbandarSurat = 0;
				myLogger.info("TARAF *****" + taraf);
				if (taraf != 6 && taraf != 8) {
					idbandar = (Integer) data.get("idbandar");
					idbandarSurat = (Integer) data.get("idbandarSurat");
				} else {
					//idbandar = (Integer) data.get("bandartetap_x");
					//idbandarSurat = (Integer) data.get("bandarsurat_x");
					
					idbandar = (Integer) data.get("idbandar");
					idbandarSurat = (Integer) data.get("idbandarSurat");
				}
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				r.update("id_Ob", idob);
				// r.add("nama_warga", nama_warga);
				r.add("no_Akaun", noakaun);
				r.add("butiran_Hutang", butiranhutang);
				r.add("nilai_Hutang", nilaihutang);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", namaob.toUpperCase());
				if (taraf != 0) {
					r.add("id_Tarafkptg", taraf);
				}
				if (jenis_pemohon.equals("2") || jenishutang == '2') {
					r.add("tarikh_Lahir", r.unquote(dobpenting));
					r.add("no_Kp_Baru", nokpbaru);
					r.add("no_Kp_Lain", nokplain.toUpperCase());
					r.add("no_Kp_Lama", nokppenting.toUpperCase());
					r.add("jenis_Kp", jenisKp);
					r.add("status_Ob", statusOB);
					r.add("jantina", jantina);
					r.add("jenis_Agama", agama);
					r.add("jenis_Warga", warga);
					r.add("umur", umur);
					r.add("no_Surat_Beranak", noberanak);
					r.add("no_fax", "");
					r.add("no_hp", no_hp);
				} else {

					if (jenishutang == '1') {
						r.add("no_Kp_Lama", nokppenting.toUpperCase());
					} else {
						r.add("no_Kp_Lama", "");
					}

					r.add("tarikh_Lahir", "");
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					// r.add("no_Kp_Lama","");
					r.add("jenis_Kp", "");
					r.add("status_Ob", "");
					r.add("jantina", "");
					r.add("jenis_Agama", "");
					r.add("jenis_Warga", "");
					r.add("umur", "");
					r.add("no_Surat_Beranak", "");
					r.add("no_fax", no_fax);
					r.add("no_hp", "");
				}

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("bandar", bandar);
				r.add("id_bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				r.add("catatan", catatan);
				r.add("no_Tel", notel);
				r.add("id_Kemaskini", id_Kemaskini);
				r.add("alamat1_surat", alamat1Surat.toUpperCase());
				r.add("alamat2_surat", alamat2Surat.toUpperCase());
				r.add("alamat3_surat", alamat3Surat.toUpperCase());
				r.add("bandar_surat", bandarSurat);
				r.add("id_bandarsurat", idbandarSurat);
				r.add("poskod_surat", poskodSurat);
				r.add("id_Negerisurat", negeriSurat);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));

				// System.out.println("PEMIUTANG TANG:"+taraf);
				if (taraf == 2) {
					// System.out.println("PEMIUTANG TANG:");
					r.add("jenis_Pemiutang", jenishutang);
				} else {
					r.add("jenis_Pemiutang", jenis_pemohon);
				}

				if (taraf == 6 || taraf == 8) {
					r.add("id_arb", jenis_pej);
					r.add("flag_salinan_arahan",salinan_arahan);
				} else {
					r.add("id_arb", "");
				}

				r.add("FLAG_DAFTAR", FLAG_DAFTAR);

				sql = r.getSQLUpdate("tblppkob");
				myLogger.info("UPDATE PENTING OB" + sql.toUpperCase());
				stmt.executeUpdate(sql);

				if (id_Pemohon != "0" && id_Pemohon != "") {
					// db1 = new Db();
					Statement stmt1 = db.getStatement();
					SQLRenderer r1 = new SQLRenderer();
					r1.update("id_Pemohon", id_Pemohon);
					r1.add("nama_Pemohon", namaob.toUpperCase());

					if (taraf != 0) {
						r1.add("id_Tarafkptg", taraf);
					}

					if (jenis_pemohon.equals("2") || jenishutang == '2') {
						r1.add("no_Kp_Baru", nokpbaru);
						r1.add("jenis_Kp", jenisKp);
						r1.add("no_Kp_Lain", nokplain.toUpperCase());
						if (umur != "") {
							r1.add("umur", umur);
						}
						r1.add("jantina", jantina);
						r1.add("jenis_Warga", warga);
						r1.add("jenis_Agama", agama);
						r1.add("no_fax", "");
						r1.add("no_hp", no_hp);
						r1.add("no_Kp_Lama", nokppenting.toUpperCase());
					} else {
						r1.add("no_Kp_Baru", "");
						r1.add("jenis_Kp", "");
						r1.add("no_Kp_Lain", "");
						r1.add("umur", "");
						r1.add("jantina", "");
						r1.add("jenis_Warga", "");
						r1.add("jenis_Agama", "");
						r1.add("no_fax", no_fax);
						r1.add("no_hp", "");
						r1.add("no_Kp_Lama", "");
					}

					r1.add("alamat_1", alamat1.toUpperCase());
					r1.add("alamat_2", alamat2.toUpperCase());
					r1.add("alamat_3", alamat3.toUpperCase());
					r1.add("bandar", bandar);
					r1.add("id_bandar", idbandar);
					r1.add("poskod", poskod);
					r1.add("no_Tel", notel);
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					String currentDate = dateFormat.format(date);
					r1.add("status_pemohon", jenis_pemohon);

					if (taraf == 6 || taraf == 8) {
						r1.add("id_arb", jenis_pej);
					} else {
						r1.add("id_arb", "");
					}

					r1.add("id_Negeri", negeri);
					r1.add("alamat1_surat", alamat1Surat.toUpperCase());
					r1.add("alamat2_surat", alamat2Surat.toUpperCase());
					r1.add("alamat3_surat", alamat3Surat.toUpperCase());
					r1.add("bandar_surat", bandarSurat);
					r1.add("id_bandarsurat", idbandarSurat);
					r1.add("poskod_surat", poskodSurat);
					r1.add("id_Negerisurat", negeriSurat);
					r1.add("id_Kemaskini", id_Kemaskini);
					r1.add("tarikh_Kemaskini", r.unquote("sysdate"));

					// sql1 = r1.getSQLUpdate("tblppkpemohon");
					// stmt1.executeUpdate(sql1);
					// update OB xperlu update pemohon //penambabaikkan
				}

				r.clear();
				r.update("id_Ob", idob);
				r.update("id_Permohonansimati", id_Permohonansimati);
				r.add("no_Akaun", noakaun);
				r.add("butiran_Hutang", butiranhutang);
				r.add("nilai_Hutang", nilaihutang);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", namaob.toUpperCase());
				if (taraf != 0) {
					r.add("id_Tarafkptg", taraf);
				}
				if (jenis_pemohon.equals("2") || jenishutang == '2') {
					r.add("tarikh_Lahir", r.unquote(dobpenting));
					r.add("no_Kp_Baru", nokpbaru);
					r.add("no_Kp_Lain", nokplain.toUpperCase());
					r.add("no_Kp_Lama", nokppenting.toUpperCase());
					r.add("jenis_Kp", jenisKp);
					r.add("status_Ob", statusOB);
					r.add("jantina", jantina);
					r.add("jenis_Agama", agama);
					r.add("jenis_Warga", warga);
					r.add("umur", umur);
					r.add("no_Surat_Beranak", noberanak);
					r.add("no_fax", "");
					r.add("no_hp", no_hp);
				} else {

					if (jenishutang == '1') {
						r.add("no_Kp_Lama", nokppenting.toUpperCase());
					} else {
						r.add("no_Kp_Lama", "");
					}

					r.add("tarikh_Lahir", "");
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					// r.add("no_Kp_Lama","");
					r.add("jenis_Kp", "");
					r.add("status_Ob", "");
					r.add("jantina", "");
					r.add("jenis_Agama", "");
					r.add("jenis_Warga", "");
					r.add("umur", "");
					r.add("no_Surat_Beranak", "");
					r.add("no_fax", no_fax);
					r.add("no_hp", "");
				}

				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("bandar", bandar);
				r.add("id_bandar", idbandar);
				r.add("poskod", poskod);
				r.add("id_Negeri", negeri);
				r.add("catatan", catatan);
				r.add("no_Tel", notel);
				r.add("id_Kemaskini", id_Kemaskini);
				r.add("alamat1_surat", alamat1Surat.toUpperCase());
				r.add("alamat2_surat", alamat2Surat.toUpperCase());
				r.add("alamat3_surat", alamat3Surat.toUpperCase());
				r.add("bandar_surat", bandarSurat);
				r.add("id_bandarsurat", idbandarSurat);
				r.add("poskod_surat", poskodSurat);
				r.add("id_Negerisurat", negeriSurat);
				r.add("tarikh_Kemaskini", r.unquote("sysdate"));

				r.add("FLAG_DAFTAR", FLAG_DAFTAR);

				// System.out.println("PEMIUTANG TANG:"+taraf);
				if (taraf == 2) {
					// System.out.println("PEMIUTANG TANG:");
					r.add("jenis_Pemiutang", jenishutang);
				} else {
					r.add("jenis_Pemiutang", jenis_pemohon);
				}

				if (taraf == 6 || taraf == 8) {
					r.add("id_arb", jenis_pej);
				} else {
					r.add("id_arb", "");
				}

				sql = r.getSQLUpdate("tblppkobpermohonan");
				myLogger.info("UPDATE PENTING OB PERMOHONAN" + sql.toUpperCase());
				stmt.executeUpdate(sql);

				conn.commit();
			} catch (SQLException se) {
				/*
				 * try {conn.rollback(); } catch (SQLException se2) { throw new
				 * Exception("Rollback error:"+se2.getMessage()); }
				 * se.printStackTrace(); throw new
				 * Exception("Ralat Jana Fail:"+se.getMessage());
				 */
			} finally {
				if (conn != null)
					conn.close();
				if (db != null)
					db.close();
			}
		}
		
		public void addPenting(Hashtable data) throws Exception {
			Connection conn = null;
			Db db = null;
			String sql = "";
			try {

				String nama_warga = (String) data.get("nama_warga");
				String idsimati = (String) data.get("idSimati");
				
				String id_Permohonansimati = (String) data.get("id_Permohonansimati");
				myLogger.info("###### id_Permohonansimati ###### :" + id_Permohonansimati);
				String namaob = (String) data.get("namaOB");
				String nokpbaru = (String) data.get("nokpbaru");
				String nokppenting = (String) data.get("nokppenting");
				String jenisKp = (String) data.get("jenisKp");
				String nokplain = (String) data.get("nokplain");
				String statusOB = (String) data.get("statusOB");
				int taraf = (Integer) data.get("taraf");
				String jantina = (String) data.get("jantina");
				String agama = (String) data.get("agama");
				String warga = (String) data.get("warga");
				String umur = (String) data.get("umur");
				String noberanak = (String) data.get("noberanak");
				String alamat1 = (String) data.get("alamat1");
				String alamat2 = (String) data.get("alamat2");
				String alamat3 = (String) data.get("alamat3");
				String poskod = (String) data.get("poskod");
				String bandar = (String) data.get("bandar");
				String idbandar = (String) data.get("idbandar");
				int negeri = (Integer) data.get("negeri");
				String alamat1Surat = (String) data.get("alamat1Surat");
				String alamat2Surat = (String) data.get("alamat2Surat");
				String alamat3Surat = (String) data.get("alamat3Surat");
				String poskodSurat = (String) data.get("poskodSurat");
				String bandarSurat = (String) data.get("bandarSurat");
				String idbandarSurat = (String) data.get("idbandarSurat");
				String notel = (String) data.get("notel");
				int jenishutang = (Integer) data.get("jenishutang");
				int negeriSurat = (Integer) data.get("negeriSurat");
				String catatan = (String) data.get("catatan");
				String catatanhutang = (String) data.get("catatanhutang");
				String noakaun = (String) data.get("noakaun");
				double nilaihutang = (Double) data.get("nilaihutang");
				String id_Masuk = (String) data.get("id_Masuk");
				String tarikh_Masuk = (String) data.get("tarikh_Masuk");
				String dob = (String) data.get("dob");
				String dobpenting = "to_date('" + dob + "','dd/MM/yyyy')";
				// baruOB
				String jenis_pej = (String) data.get("jenis_pej");
				String no_fax = (String) data.get("no_fax");
				String jenis_pemohon = (String) data.get("jenis_pemohon");
				String no_hp = (String) data.get("no_hp");

				String FLAG_DAFTAR = (String) data.get("FLAG_DAFTAR");

				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				long id_ob = DB.getNextID(db, "TBLPPKOB_SEQ");

				r.add("id_ob", id_ob);
				// r.add("nama_warga", nama_warga);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", namaob.toUpperCase());
				if (jenis_pemohon.equals("2") || jenishutang == '2') {
					r.add("tarikh_Lahir", r.unquote(dobpenting));
					r.add("no_Kp_Baru", nokpbaru);
					r.add("no_Kp_Lain", nokplain.toUpperCase());
					r.add("no_Kp_Lama", nokppenting.toUpperCase());
					r.add("jenis_Kp", jenisKp);
					r.add("status_Ob", statusOB);

					r.add("jantina", jantina);
					r.add("jenis_Agama", agama);
					r.add("jenis_Warga", warga);
					r.add("umur", "");

					r.add("no_Surat_Beranak", noberanak);
					r.add("no_fax", "");
					r.add("no_hp", no_hp);
				} else {
					r.add("tarikh_Lahir", "");
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					if (jenishutang == '1') {
						r.add("no_Kp_Lama", nokppenting.toUpperCase());
					} else {
						r.add("no_Kp_Lama", "");
					}
					r.add("jenis_Kp", "");
					r.add("status_Ob", "");
					r.add("jantina", "");
					r.add("jenis_Agama", "");
					r.add("jenis_Warga", "");
					r.add("umur", "");
					r.add("no_Surat_Beranak", "");
					r.add("no_fax", no_fax);
					r.add("no_hp", "");
				}
				if (taraf != 0) {
					r.add("id_Tarafkptg", taraf);
				}
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_bandar", idbandar);//idbandar
				r.add("poskod", poskod);
				r.add("alamat1_surat", alamat1Surat.toUpperCase());
				r.add("alamat2_surat", alamat2Surat.toUpperCase());
				r.add("alamat3_surat", alamat3Surat.toUpperCase());
				r.add("id_bandarsurat", idbandarSurat);//id_bandarsurat
				r.add("poskod_surat", poskodSurat);
				r.add("id_Negerisurat", negeriSurat);
				r.add("no_Tel", notel);
				r.add("id_Negeri", negeri);
				r.add("catatan", catatan.toUpperCase());
				r.add("jenis_Pemiutang", jenishutang);
				r.add("butiran_Hutang", catatanhutang);
				// baruOB
				if (taraf == 6 || taraf == 8) {
					r.add("id_arb", jenis_pej);
				} else {
					r.add("id_arb", "");
				}
				r.add("no_Akaun", noakaun);
				r.add("nilai_Hutang", nilaihutang);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));

				r.add("FLAG_DAFTAR", FLAG_DAFTAR);

				sql = r.getSQLInsert("tblppkob");
				myLogger.info("ADD PENTING :" + sql);
				stmt.executeUpdate(sql);

				r.clear();
				r.add("id_ob", id_ob);
				r.add("id_Simati", idsimati);
				r.add("nama_Ob", namaob.toUpperCase());
				if (jenis_pemohon.equals("2") || jenishutang == '2') {
					r.add("tarikh_Lahir", r.unquote(dobpenting));
					r.add("no_Kp_Baru", nokpbaru);
					r.add("no_Kp_Lain", nokplain.toUpperCase());
					r.add("no_Kp_Lama", nokppenting.toUpperCase());
					r.add("jenis_Kp", jenisKp);
					r.add("status_Ob", statusOB);

					r.add("jantina", jantina);
					r.add("jenis_Agama", agama);
					r.add("jenis_Warga", warga);
					r.add("umur", "");

					r.add("no_Surat_Beranak", noberanak);
					r.add("no_fax", "");
					r.add("no_hp", no_hp);
				} else {
					r.add("tarikh_Lahir", "");
					r.add("no_Kp_Baru", "");
					r.add("no_Kp_Lain", "");
					if (jenishutang == '1') {
						r.add("no_Kp_Lama", nokppenting.toUpperCase());
					} else {
						r.add("no_Kp_Lama", "");
					}
					r.add("jenis_Kp", "");
					r.add("status_Ob", "");
					r.add("jantina", "");
					r.add("jenis_Agama", "");
					r.add("jenis_Warga", "");
					r.add("umur", "");
					r.add("no_Surat_Beranak", "");
					r.add("no_fax", no_fax);
					r.add("no_hp", "");
				}
				if (taraf != 0) {
					r.add("id_Tarafkptg", taraf);
				}
				r.add("alamat_1", alamat1.toUpperCase());
				r.add("alamat_2", alamat2.toUpperCase());
				r.add("alamat_3", alamat3.toUpperCase());
				r.add("id_bandar", idbandar); //idbandar
				r.add("poskod", poskod);
				r.add("alamat1_surat", alamat1Surat.toUpperCase());
				r.add("alamat2_surat", alamat2Surat.toUpperCase());
				r.add("alamat3_surat", alamat3Surat.toUpperCase());
				r.add("id_bandarsurat", idbandarSurat); //idbandarSurat
				r.add("poskod_surat", poskodSurat);
				r.add("id_Negerisurat", negeriSurat);
				r.add("no_Tel", notel);
				r.add("id_Negeri", negeri);
				r.add("catatan", catatan.toUpperCase());
				r.add("jenis_Pemiutang", jenishutang);
	
				r.add("butiran_Hutang", catatanhutang);
				// baruOB
				if (taraf == 6 || taraf == 8) {
					r.add("id_arb", jenis_pej);
				} else {
					r.add("id_arb", "");
				}
				r.add("no_Akaun", noakaun);
				r.add("nilai_Hutang", nilaihutang);
				r.add("id_Permohonansimati", id_Permohonansimati);
				r.add("id_Masuk", id_Masuk);
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("FLAG_DAFTAR", FLAG_DAFTAR);

				sql = r.getSQLInsert("tblppkobpermohonan");
				myLogger.info("ADD PENTING PERMOHON :" + sql);
				stmt.executeUpdate(sql);

				conn.commit();

			} catch (SQLException se) {
				try {
					conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:" + se2.getMessage());
				}
				se.printStackTrace();
				throw new Exception("Ralat Jana Fail:" + se.getMessage());
			} finally {
				if (conn != null)
					conn.close();
				if (db != null)
					db.close();
			}
		}
		
		public static Vector checkwujudtakSD(String idPermohonan) throws Exception {
			Db db = null;
			//listcheckpemohonwaris.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				//String idsimati = (String) data.get("idSimati");
				sql = "select id_permohonan from tblppksd where id_permohonan = '" + idPermohonan + "' ";

				System.out.println("SQL CHECK SAMA ADA SD TELAH WUJUD: "+sql);
				Vector v2 = new Vector();
			    Hashtable h = null;
				ResultSet rs = stmt.executeQuery(sql);
				//Hashtable h;
				Integer count = 0;

				while (rs.next()) {

					h = new Hashtable();

					h.put("id_permohonan", rs.getString("id_permohonan") == null ? "" : rs
							.getString("id_permohonan"));
					//listcheckpemohonwaris.addElement(h);
					v2.addElement(h);
				}
				return v2;

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		
		public static Vector checkdulusamadaARBtelahwujudataubelum(Hashtable data) throws Exception {
			Db db = null;
			//listcheckpemohonwaris.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				String idsimati = (String) data.get("idSimati");
				sql = "select id_ob from tblppkob where NAMA_OB = 'AMANAH RAYA BERHAD' and id_simati = '"
						+ idsimati + "' ";

				System.out.println("SQL CHECK SAMA ADA ARB TELAH WUJUD: "+sql);
				Vector v2 = new Vector();
			    Hashtable h = null;
				ResultSet rs = stmt.executeQuery(sql);
				//Hashtable h;
				Integer count = 0;

				while (rs.next()) {

					h = new Hashtable();

					h.put("id_ob", rs.getString("id_ob") == null ? "" : rs
							.getString("id_ob"));
					//listcheckpemohonwaris.addElement(h);
					v2.addElement(h);
				}
				return v2;

			} finally {
				if (db != null)
					db.close();
			}

		}
		
		
		 public static Vector getListNegeriByhta(String idpermohonansimati) throws Exception {
			    Db db = null;
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();    
			      String sql = "SELECT DISTINCT N.ID_NEGERI, N.NAMA_NEGERI,MAXNILAI"
                               +" FROM TBLPPKHTA A, TBLRUJNEGERI N,"
                               +" (SELECT DISTINCT MAX(A.NILAI_HTA_TARIKHMOHON) " 
                               +" AS MAXNILAI FROM TBLPPKHTA A, TBLRUJNEGERI N " 
                               +" WHERE A.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONANSIMATI = '"+idpermohonansimati+"')"
                               +" WHERE A.ID_NEGERI = N.ID_NEGERI AND A.NILAI_HTA_TARIKHMOHON = MAXNILAI AND A.ID_PERMOHONANSIMATI = '"+idpermohonansimati+"' " ;
			      System.out.println("SQL NEGERI PINDAH :" + sql.toUpperCase());
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector v2 = new Vector();
			      Hashtable h = null;
			      while (rs.next()) {
			        h = new Hashtable();
			        h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			        h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
			        v2.addElement(h);
			      }
			      return v2;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }
		 
		 public static Vector getListDaerahByPpkUnit(String id) throws Exception {
			    Db db = null;
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      /*
			      String sql = "select distinct(d.id_daerah),d.kod_daerah,d.nama_daerah from tblrujnegeri n, tblrujdaerah d, tblppkrujunit u, " +
			      		       "tblrujpejabatjkptg p where n.id_negeri = d.id_negeri and n.id_negeri = u.id_negeri and d.id_daerah = p.id_daerah " +
			      		       "and n.id_negeri = "+ id +" ";
			      		       */
			      
			      String sql = "select d.id_daerah,d.kod_daerah,d.nama_daerah,n.nama_negeri from tblRujDaerah d,tblRujNegeri n where d.id_daerah in (select distinct u.id_daerahurus from"
			    		+"  TBLRUJPEJABATURUSAN u,TBLRUJPEJABATJKPTG p, TBLRUJDAERAH d, TBLRUJNEGERI n where  u.id_daerahurus = d.id_daerah and d.id_negeri = n.id_negeri and u.ID_PEJABATJKPTG = p.ID_PEJABATJKPTG and u.id_jenispejabat = 22 "
			    		+"  and d.id_negeri = '"+id+"' and u.id_seksyen = 2) "
			    		+"  and d.id_negeri = n.id_negeri "
			    		+"  ORDER BY kod_daerah ";
			      //System.out.println("getListDaerahByPpkUnit-->>"+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector v2 = new Vector();
			      Hashtable h = null;
			      while (rs.next()) {
			        h = new Hashtable();
			        h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
			        h.put("namaDaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			        v2.addElement(h);
			      }
			      return v2;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }
		 
	
}

package ekptg.model.pdt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmDaftarAktaPindaanData {
	private static Logger myLogger = Logger.getLogger(FrmDaftarAktaPindaanData.class);
	private static Vector listAktaById = new Vector();
	private static Vector listCarianPaparAkta = new Vector();
	private static Vector listPenggalAsalAll= new Vector();
	
	
	public boolean isAktaPindaanExist(String idAkta) throws Exception  {
		
		boolean output=false;
		String sql = "";
		Db db = null;
		try {
			db = new Db(); 
			sql = "Select count(*) as jumlah from TBLPDTAKTAPINDA where id_akta ='"+idAkta+"' ";
			//myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql); 
			if (rs.next()){	
				//if ("1".equals(rs.getString("jenis_agama"))) {
				if (rs.getInt("jumlah") > 0) {
					output = true;
				}
				
			}
		} catch (Exception e) {
			throw new Exception ("error TBLPDTAKTAPINDA :"+e.getMessage());
		}finally {
			if (db != null) db.close();
		}
		return output;		
	}
	
	
	
	
	// CARIAN Akta DAN PAPAR SEMUA Akta
	public void setCarianPaparAktaPinda(String noAkta,
			String namaAkta, String tarikhKuatkuasa,String noAktaPindaan, String namaAktaPinda) throws Exception {
		Db db = null;
		listCarianPaparAkta.clear();
		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT A.ID_AKTA,A.NO_AKTA,A.NAMA_AKTA,A.TARIKH_KUATKUASA"
					+ " FROM TBLPDTAKTA A,TBLPDTAKTAPINDA B " +
							"WHERE A.ID_AKTA = B.ID_AKTA(+) AND A.ID_AKTA IS NOT NULL";
			
			// NO AKTA
			if (noAkta != null) {
				if (!noAkta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_AKTA) LIKE '%' ||'"
							+ noAkta.toUpperCase() + "'|| '%'";
				}
			}
			// NAMA Akta
			if (namaAkta != null) {
				if (!namaAkta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_AKTA) LIKE '%' ||'"
							+ namaAkta.toUpperCase() + "'|| '%'";
				}
			}
			// TARIKH KUATKUASA
			if (tarikhKuatkuasa != null) {
				if (!tarikhKuatkuasa.trim().equals("")) {
					sql = sql
							+ " AND UPPER(A.TARIKH_KUATKUASA) LIKE '%' ||'"
							+ sdf.format(sdf.parse(tarikhKuatkuasa))
									.toUpperCase() + "'|| '%'";
				}
			}
			// NO Akta Pindaan
			if (noAktaPindaan != null) {
				if (!noAktaPindaan.trim().equals("")) {
					sql = sql + " AND UPPER(B.no_akta_pindaan) LIKE '%' ||'"
							+ noAktaPindaan.toUpperCase() + "'|| '%'";
				}
			}
			// NAMA Akta Pinda
			if (namaAktaPinda != null) {
				if (!namaAktaPinda.trim().equals("")) {
					sql = sql + " AND UPPER(B.nama_akta_pindaan) LIKE '%' ||'"
							+ namaAktaPinda.toUpperCase() + "'|| '%'";
				}
			}
			sql = sql + " ORDER BY A.ID_AKTA";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("id_Akta", rs.getString("ID_AKTA"));
				h.put("no_Akta", rs.getString("NO_AKTA") == null ? ""
						: rs.getString("NO_AKTA"));
				h.put("nama_Akta", rs.getString("NAMA_AKTA") == null ? ""
						: rs.getString("NAMA_AKTA"));
				h.put("tarikh_kuatkuasa",
						rs.getDate("TARIKH_KUATKUASA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KUATKUASA")));
				listCarianPaparAkta.addElement(h);
				no++;
				count++;

			}

			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("id_Akta", "");
				h.put("no_Akta", "Tiada rekod.");
				h.put("nama_Akta", "");
				h.put("tarikh_kuatkuasa", "");
				h.put("seksyen","");
				listCarianPaparAkta.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	
	// CARIAN Akta DAN PAPAR SEMUA Akta
	public Vector getCarianPaparAktaPinda(String noAkta, String namaAkta
			, String tarikhKuatkuasa, String noAktaPindaan, String namaAktaPinda, String tag) throws Exception {
		Db db = null;
		listCarianPaparAkta.clear();
		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT A.ID_AKTA,A.NO_AKTA,A.NAMA_AKTA,A.TARIKH_KUATKUASA"
					+ " FROM TBLPDTAKTA A,TBLPDTAKTAPINDA B " +
							"WHERE A.ID_AKTA = B.ID_AKTA(+) AND A.ID_AKTA IS NOT NULL";
			
			// NO AKTA
			if (noAkta != null) {
				if (!noAkta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_AKTA) LIKE '%' ||'"
							+ noAkta.toUpperCase() + "'|| '%'";
				}
			}
			// NAMA Akta
			if (namaAkta != null) {
				if (!namaAkta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA_AKTA) LIKE '%' ||'"
							+ namaAkta.toUpperCase() + "'|| '%'";
				}
			}
			// TARIKH KUATKUASA
			if (tarikhKuatkuasa != null) {
				if (!tarikhKuatkuasa.trim().equals("")) {
					sql = sql
							+ " AND UPPER(A.TARIKH_KUATKUASA) LIKE '%' ||'"
							+ sdf.format(sdf.parse(tarikhKuatkuasa))
									.toUpperCase() + "'|| '%'";
				}
			}
			// NO Akta Pindaan
						if (noAktaPindaan != null) {
							if (!noAktaPindaan.trim().equals("")) {
								sql = sql + " AND UPPER(B.no_akta_pindaan) LIKE '%' ||'"
										+ noAktaPindaan.toUpperCase() + "'|| '%'";
							}
						}
			// NAMA Akta Pinda
			if (namaAktaPinda != null) {
				if (!namaAktaPinda.trim().equals("")) {
					sql = sql + " AND UPPER(B.nama_akta_pindaan) LIKE '%' ||'"
							+ namaAktaPinda.toUpperCase() + "'|| '%'";
				}
			}
			if (!"".equalsIgnoreCase(tag)) {
				sql += " AND B.ID_AKTAPINDA = ( " +
						" SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN " +
						" WHERE SUMBER ='AKTAP' " +
						" AND TAG_DOKUMEN LIKE '%" + tag + "%' )";
			}

			sql = sql + " ORDER BY A.ID_AKTA";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int no = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("no", no);
				h.put("id_Akta", rs.getString("ID_AKTA"));
				h.put("no_Akta", rs.getString("NO_AKTA") == null ? ""
						: rs.getString("NO_AKTA"));
				h.put("nama_Akta", rs.getString("NAMA_AKTA") == null ? ""
						: rs.getString("NAMA_AKTA"));
				h.put("tarikh_kuatkuasa",
						rs.getDate("TARIKH_KUATKUASA") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KUATKUASA")));
				listCarianPaparAkta.addElement(h);
				no++;
				count++;

			}

			if (count == 0) {
				h = new Hashtable();
				h.put("no", "");
				h.put("id_Akta", "");
				h.put("no_Akta", "Tiada rekod.");
				h.put("nama_Akta", "");
				h.put("tarikh_kuatkuasa", "");
				h.put("seksyen","");
				listCarianPaparAkta.addElement(h);
			}

		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
		return listCarianPaparAkta;
		
	}
	// PAPAR SENARAI Akta MENGIKUT ID
	public void setPaparAktaById(String id) throws Exception {

		Db db = null;
		listAktaById.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_Akta");
			r.add("a.no_Akta");
			r.add("a.nama_Akta");
			r.add("a.No_Fail");
			r.add("a.id_Seksyen");
			r.add("a.id_Tarafkeselamatan");
			//r.add("a.id_Seksyen", r.unquote("b.id_Seksyen(+)"));
			//r.add("a.id_Fail", r.unquote("c.id_Fail"));
			r.add("a.id_Akta", id);

			//sql = r.getSQLSelect("Tblpdtakta a,Tblrujseksyen b, Tblpfdfail c");
			sql = r.getSQLSelect("Tblpdtakta a");
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idAkta", Utils.isNull(rs.getString("id_Akta")));
				h.put("noAkta", Utils.isNull(rs.getString("no_Akta")));
				h.put("namaAkta", Utils.isNull(rs.getString("nama_Akta")));
				h.put("idseksyen", Utils.isNull(rs.getString("id_Seksyen")));
				h.put("sorTerbuka", Utils.isNull(rs.getString("id_Tarafkeselamatan")));
				h.put("noFail", Utils.isNull(rs.getString("no_Fail")));
				listAktaById.addElement(h);

			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	// PAPAR SENARAI Akta Pindaa MENGIKUT ID
	public Vector getSenaraiAktaPindaan(String id) throws Exception {

		Db db = null;
		String sql = "";
		Vector v = null;
		try {
			db = new Db();
			v = new Vector();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT id_aktapinda,no_akta_asal,no_akta_pindaan,nama_akta_pindaan," +
					"to_char(TARIKH_DAFTAR_PINDAAN,'dd/MM/YYYY') as TARIKH_DAFTAR_PINDAAN "+
					"FROM TBLPDTAKTAPINDA WHERE ID_AKTA='"+id+"'";
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idAktaPinda", Utils.isNull(rs.getString("id_aktapinda")));
				h.put("noAktaAsal", Utils.isNull(rs.getString("no_akta_asal")));
				h.put("noAktaPindaan", Utils.isNull(rs.getString("no_akta_pindaan")));
				h.put("namaAktaPindaan", Utils.isNull(rs.getString("nama_akta_pindaan")));
				h.put("tarikhAktaPindaan", Utils.isNull(rs.getString("TARIKH_DAFTAR_PINDAAN")));
				v.addElement(h);
			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
		return v;
	}

	
	// TAMBAH AKTA PINDAAN BARU
	public String addAktaPindaan(Hashtable data,String mode) throws Exception {
		Db db = new Db();
		String sql = "";
		String output="";
		try {
			//long idAktaPindaan = DB.getNextID("TBLPDTAKTAPINDA_SEQ");
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			// AKTA ASAL
			r.add("NO_AKTA_ASAL", data.get("noAktaAsal"));				
			// AKTA PINDAAN 
			//r.add("ID_AKTAPINDA", idAktaPindaan);			
			r.add("NO_AKTA_PINDAAN", data.get("noAktaPindaan"));
			r.add("NAMA_AKTA_PINDAAN", data.get("namaAktaPindaan"));
			r.add("TARIKH_KUATKUASA_PINDAAN", r.unquote("to_date('"+ data.get("tarikhKuatkuasaPindaan")+ "','dd/MM/yyyy')"));
			r.add("NO_WARTA", data.get("noWartaPindaan"));
			r.add("TARIKH_WARTA", r.unquote("to_date('"+ data.get("tarikhWartaPindaan")+ "','dd/MM/yyyy')"));
			//r.add("ID_TARAFKESELAMATAN", data.get("idKeselamatan"));
			r.add("ID_FAIL", data.get("idFail"));
			r.add("TARIKH_PENYIARAN_DLMWARTA", r.unquote("to_date('"+ data.get("tarikhPenyiaranDlmWarta")+ "','dd/MM/yyyy')"));
			r.add("TARIKH_PERKENAAN_DIRAJA", r.unquote("to_date('"+ data.get("tarikhPerkenaanDiraja")+ "','dd/MM/yyyy')"));
			r.add("NO_MEMORANDUM_MENTERI", data.get("noMemorandumMenteri"));
			r.add("KPTSN_JEMAAH_MENTERI", data.get("kptsnJemaahMenteri"));
			r.add("KPTSN_MAJLIS_TANAH_NEGARA", data.get("kptsnMajlisTanah"));
			r.add("CATATAN", data.get("catatan"));
			r.add("TARIKH_DAFTAR_PINDAAN", r.unquote("to_date('"+ data.get("tarikhDaftarPindaan")+ "','dd/MM/yyyy')"));
			String idAktaPinda = "";
			if ("doUpdate".equals(mode)) {
				idAktaPinda = String.valueOf(data.get("idAktaPindaan"));
				r.update("ID_AKTAPINDA", data.get("idAktaPindaan"));
				r.add("ID_KEMASKINI", data.get("idMasuk"));
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPDTAKTAPINDA");								
				
			} else {
				long idAktaPindaan = DB.getNextID("TBLPDTAKTAPINDA_SEQ");
				r.add("ID_AKTAPINDA", idAktaPindaan);	
				r.add("ID_AKTA", data.get("idAkta"));
				r.add("TARIKH_MASUK", r.unquote("to_date('"+ data.get("tarikhMasuk")+ "','dd/MM/yyyy')"));
				r.add("ID_MASUK", data.get("idMasuk"));
				sql = r.getSQLInsert("TBLPDTAKTAPINDA");
				output = String.valueOf(idAktaPindaan);
				idAktaPinda = output;
				
			}	
			myLogger.debug(sql);
			stmt.executeUpdate(sql);

			String tagDokumen = (String)data.get("tagDokumen");
			String idTagDokumen = (String)data.get("idTagDokumen");
			if(!tagDokumen.equals("")){
				db = new Db();
				stmt = db.getStatement();
			    r = new SQLRenderer();			      
				if(!idTagDokumen.equals("0")){
					r.update("ID_RUJTAG", data.get("idTagDokumen"));
					r.add("ID_KEMASKINI", data.get("idMasuk"));
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
				
				}else{
					long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
					r.add("ID_RUJTAG",idTagDokumenBaru);
					r.add("ID_DOKUMEN",idAktaPinda);
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("SUMBER","AKTAP");
					r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					r.add("ID_MASUK",data.get("idMasuk"));
					sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
				
				}
			    stmt.executeUpdate(sql);

			}
			
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
		return output;
	}

	
	// PAPAR SEMUA DATA ASAL
	//--------------------------------------------------
	// RETURN LIST AKTA ASAL BY ID
	public Vector getPaparAkta() {

		return listAktaById;
	}
	// RETURN LIST SEMUA PENGGAL ASAL
	public Vector getPaparPenggal() {

		return listPenggalAsalAll;
	}
	
	
	
	// RETURN LIST DAN CARIAN AKTA
	public Vector getCarianPaparAkta() {

		return listCarianPaparAkta;
	}
	
	///////////PINDAAN
	// PAPAR SENARAI Akta MENGIKUT ID
	public Hashtable getAktaPindaan(String id) throws Exception {

		Db db = null;
		String sql = "";
		Hashtable h;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_AktaPinda");
			r.add("a.no_Akta_Pindaan");
			r.add("a.nama_Akta_Pindaan");
			r.add("a.id_Akta", id);
			sql = r.getSQLSelect("TBLPDTAKTAPINDA a");
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			h = new Hashtable();
			while (rs.next()) {
				h.put("idAktaPindaan", Utils.isNull(rs.getString("id_AktaPinda")));
				h.put("noAktaPindaan", Utils.isNull(rs.getString("no_Akta_Pindaan")));
				h.put("namaAktaPindaan", Utils.isNull(rs.getString("nama_Akta_Pindaan")));
				
				myLogger.debug("** noAktaPindaan ** "+h.get("noAktaPindaan"));
			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
		return h;
	}
	
	
	public Hashtable getAktaPindaanDetail(String id) throws Exception {

		Db db = null;
		String sql = "";
		Hashtable h;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("a.id_AktaPinda");
			r.add("a.no_Akta_Pindaan");
			r.add("a.nama_Akta_Pindaan");
			r.add("a.no_warta");
			r.add("to_char(a.tarikh_kuatkuasa_pindaan,'dd/MM/YYYY') as tarikh_kuatkuasa_pindaan");
			r.add("to_char(a.tarikh_warta,'dd/MM/YYYY') as tarikh_warta");
			r.add("to_char(a.TARIKH_PENYIARAN_DLMWARTA,'dd/MM/YYYY') as TARIKH_PENYIARAN_DLMWARTA");
			r.add("to_char(a.TARIKH_PERKENAAN_DIRAJA,'dd/MM/YYYY') as TARIKH_PERKENAAN_DIRAJA");
			r.add("to_char(a.TARIKH_DAFTAR_PINDAAN,'dd/MM/YYYY') as TARIKH_DAFTAR_PINDAAN");
			r.add("NO_FAIL");
			r.add("NO_MEMORANDUM_MENTERI");
			r.add("KPTSN_JEMAAH_MENTERI");
			r.add("KPTSN_MAJLIS_TANAH_NEGARA");
			r.add("CATATAN");
			r.add("NVL((SELECT TAG_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=A.ID_AKTAPINDA AND SUMBER ='AKTAP' ),'') TAG_DOKUMEN");
			r.add("NVL((SELECT ID_RUJTAG FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=A.ID_AKTAPINDA AND SUMBER ='AKTAP' ),'0') ID_DOKUMEN");
			r.add("a.id_AktaPinda", id);
			sql = r.getSQLSelect("TBLPDTAKTAPINDA a");
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			h = new Hashtable();
			while (rs.next()) {
				h.put("idAktaPindaan", Utils.isNull(rs.getString("id_AktaPinda")));
				h.put("noAktaPindaan", Utils.isNull(rs.getString("no_Akta_Pindaan")));
				h.put("namaAktaPindaan", Utils.isNull(rs.getString("nama_Akta_Pindaan")));
				h.put("tarikhKuatkuasaPindaan", Utils.isNull(rs.getString("tarikh_kuatkuasa_pindaan")));
				h.put("noWartaPindaan", Utils.isNull(rs.getString("no_warta")));
				h.put("tarikhWartaPindaan", Utils.isNull(rs.getString("tarikh_warta")));
				h.put("tarikhDaftarPindaan", Utils.isNull(rs.getString("TARIKH_DAFTAR_PINDAAN")));
				h.put("tarikhPerkenaanDiraja", Utils.isNull(rs.getString("TARIKH_PERKENAAN_DIRAJA")));
				h.put("tarikhPenyiaranDlmWarta", Utils.isNull(rs.getString("TARIKH_PENYIARAN_DLMWARTA")));
				
				h.put("noFail", Utils.isNull(rs.getString("NO_FAIL")));
				h.put("noMemorandumMenteri", Utils.isNull(rs.getString("NO_MEMORANDUM_MENTERI")));
				h.put("kptsnJemaahMenteri", Utils.isNull(rs.getString("KPTSN_JEMAAH_MENTERI")));
				h.put("kptsnMajlisTanah", Utils.isNull(rs.getString("KPTSN_MAJLIS_TANAH_NEGARA")));
				h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
				h.put("tagging", Utils.isNull(rs.getString(15)));
				h.put("idTagging", Utils.isNull(rs.getString(16)));
					
			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
		return h;
		
	}

	public void Delete(String idAktaPindaan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_AktaPinda",idAktaPindaan);
			sql = r.getSQLDelete("TBLPDTAKTAPINDA");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}

	}
	
	
	public void DeleteAktaPindaanByIdAkta(String idAkta) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Akta",idAkta);
			sql = r.getSQLDelete("TBLPDTAKTA");
			myLogger.debug(sql);
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		 finally {
			if (db != null)
				db.close();
		}

	}
	
}

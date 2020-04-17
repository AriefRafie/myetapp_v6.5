package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmGadaianPelepasanGadaianData {

	private static Logger log = Logger.getLogger(ekptg.model.htp.FrmGadaianPelepasanGadaianData.class);
	private static Vector<Hashtable> FailGadaian = null;

	private static void setFailSeksyenMelepasGadaian() {

	}

	public static void SenaraiFailGadaian(String urusan, String negeri) throws Exception {
		Db db = null;
		String sql;
		String Like;
		String sqlmapping;

		try {

			if (urusan.equalsIgnoreCase("38")) {
				Like = "memorandum".toUpperCase();
			} else if (urusan.equalsIgnoreCase("39")) {
				Like = "menandatangani".toUpperCase();
			} else if (urusan.equalsIgnoreCase("40")) {
				Like = "menandatangani".toUpperCase();
			} else if (urusan.equalsIgnoreCase("41")) {
				Like = "menandatangani".toUpperCase();
			} else {
				Like = "";
			}

			db = new Db();
			FailGadaian = new Vector<Hashtable>();
			Statement stmt = db.getStatement();

			sqlmapping = "" +
					" Select fm.id_htpfailmapping, fm.id_fail, fm.id_faillama " +
					" from tblhtpfailmapping FM ";
			ResultSet rsmapping = stmt.executeQuery(sqlmapping);
			log.info("rsmapping : " + rsmapping);			
			if(rsmapping.next()){
				
				sql  = "SELECT PFDF.ID_FAIL, S.NAMA_SUBURUSAN, PFDF.TAJUK_FAIL,";
				sql += " PFDF.NO_FAIL, NEG.NAMA_NEGERI ";
				sql += " FROM Tblpfdfail pfdf, tblrujnegeri NEG, tblrujsuburusan S ";
				sql += " WHERE pfdf.id_suburusan = " + urusan;
				sql += " AND PFDF.ID_SUBURUSAN = S.ID_SUBURUSAN ";
				sql += " AND pfdf.id_negeri = " + negeri;
				sql += " AND pfdf.id_negeri = NEG.ID_NEGERI ";
				//sql += " AND PFDF.TAJUK_FAIL LIKE '%" + Like + "%'";
				sql += " AND pfdf.id_fail NOT IN (select fm.id_faillama  from tblhtpfailmapping fm)";

				String sql1 =" SELECT F.ID_FAIL,RSU.ID_SUBURUSAN,RSU.NAMA_SUBURUSAN, "+
				" F.TAJUK_FAIL, F.NO_FAIL, NEG.NAMA_NEGERI "+  
				" FROM Tblpfdfail F, tblrujnegeri NEG, tblrujsuburusan RSU "+
				" WHERE F.id_negeri = NEG.ID_NEGERI "+
				" AND F.ID_SUBURUSAN= RSU.ID_SUBURUSAN "+
				" AND F.ID_URUSAN=108 "+
				" AND F.id_negeri ='"+negeri+"'"+
				" AND RSU.ID_SUBURUSAN NOT IN (45,46,47 ) "+
				" AND F.id_fail NOT IN (select fm.id_faillama  from tblhtpfailmapping FM) "+
				" ORDER BY F.TARIKH_MASUK DESC";
				log.info(sql1);
				ResultSet rs = stmt.executeQuery(sql1);

				Hashtable h;
				int bil = 1;

				while (rs.next()) {
					h = new Hashtable();
					h.put("bil", bil);
					h.put("idFail", rs.getString("id_Fail"));
					h.put("noFail", rs.getString("no_Fail"));
					h.put("tajuk", rs.getString("tajuk_Fail"));
					h.put("negeri", rs.getString("nama_Negeri"));
					h.put("urusan", rs.getString("nama_suburusan"));

					FailGadaian.addElement(h);
					bil++;
				}
				
			}
			
			else{
				
				sql = "SELECT PFDF.ID_FAIL, S.NAMA_SUBURUSAN, PFDF.TAJUK_FAIL,";
				sql += " PFDF.NO_FAIL, NEG.NAMA_NEGERI ";
				sql += "FROM Tblpfdfail pfdf, tblrujnegeri NEG, tblrujsuburusan S ";
				sql += " WHERE pfdf.id_suburusan = " + urusan;
				sql += " AND PFDF.ID_SUBURUSAN = S.ID_SUBURUSAN ";
				sql += " AND pfdf.id_negeri = " + negeri;
				sql += " AND pfdf.id_negeri = NEG.ID_NEGERI ";
				sql += " AND PFDF.TAJUK_FAIL LIKE '%" + Like + "%'";
				

				log.info(sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable h;
				int bil = 1;

				while (rs.next()) {
					h = new Hashtable();
					h.put("bil", bil);
					h.put("idFail", rs.getString("id_Fail"));
					h.put("noFail", rs.getString("no_Fail"));
					h.put("tajuk", rs.getString("tajuk_Fail"));
					h.put("negeri", rs.getString("nama_Negeri"));
					h.put("urusan", rs.getString("nama_suburusan"));

					FailGadaian.addElement(h);
					bil++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (db != null) {
				db.close();
			}
		}
	}

	public static Vector<Hashtable> getFailGadaian() {
		return FailGadaian;
	}

	public static void DaftarFailMelepasGadaian(String idFail,String failGadaian) {
		Db db = null;
		String sql;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// long idFailMap = DB.getNextID("TBLHTPFAILMAPPING_SEQ");
			long idFailMap = DB.getNextID("TBLHTPFAILMAPPING_SEQ");
			r.add("id_htpfailmapping", idFailMap);
			r.add("id_fail", idFail);
			r.add("id_faillama", failGadaian);
			r.add("tarikh_agihan", "");
			r.add("id_masuk", idFailMap);
			r.add("tarikh_masuk", "");
			r.add("id_kemaskini", idFailMap);
			r.add("tarikh_kemaskini", "");
			sql = r.getSQLInsert("Tblhtpfailmapping");
			log.info("FrmGadaianPelepasanGadaianData::Insert::Tblhtpfailmapping = "	+ sql);
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void SimpanPelepasanGadaian(String idFail, String idPermohonan, String tarikhLepasGadai){
		Db db = null;
		String sql;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String TLG = "to_date('" + tarikhLepasGadai + "','dd/MM/yyyy')";
			long idFailMG = DB.getNextID("TBLHTPMAKLUMATGADAIAN_SEQ");
			r.add("id_htpmaklumatgadaian", idFailMG);
			r.add("id_permohonan", r.unquote(idPermohonan));
//			r.add("tarikh_agihan", "");
			r.add("id_masuk", idFailMG);
			r.add("tarikh_masuk", "");
			r.add("id_kemaskini", idFailMG);
			r.add("tarikh_kemaskini", "");
			r.add("tarikh_lepasgadai", r.unquote(TLG));
			r.add("id_fail", r.unquote(idFail));
			
			sql = r.getSQLInsert("TBLHTPMAKLUMATGADAIAN");
			log.info("FrmGadaianPelepasanGadaianData::Insert::TBLHTPMAKLUMATGADAIAN = "	+ sql);
			stmt.executeUpdate(sql);
						
			//update tblrujsuburusan statuus fail (update status)
			
			SQLRenderer rUpdate = new SQLRenderer();
			rUpdate.update("id_permohonan",rUpdate.unquote(idPermohonan));		
			rUpdate.update("id_fail",rUpdate.unquote(idFail));		
			rUpdate.add("aktif", 0);			
			String sql2 = rUpdate.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			
			log.info("FrmGadaianPelepasanGadaianData::Update::TBLRUJSUBURUSANSTATUSFAIL = "	+ sql2);
			stmt.executeUpdate(sql2);
			
			/*
			 * update tblpermohonan so that new permohonan belongs
			 * to new fail
			 */
			
			SQLRenderer rPermohonan = new SQLRenderer();
			rPermohonan.update("id_permohonan",r.unquote(idPermohonan));			
			rPermohonan.add("id_fail", idFail);
			
			String sql3 = rPermohonan.getSQLUpdate("TBLPERMOHONAN");
			log.info("FrmGadaianPelepasanGadaianData::Update::TBLPERMOHONAN = "	+ sql3);
			stmt.executeUpdate(sql3);
	
		} catch (Exception e) {
			e.printStackTrace();
		
		}finally{
			db.close();
			
		}
		
	}


}//close class
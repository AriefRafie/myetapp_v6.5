package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

/**
 * 
 *
 */
public class FrmSenaraiFailSek8Data {
	
	private Vector senaraiFail = new Vector();
	
	public void carianFail(String noFail, HttpSession session) throws Exception {
		Db db = null;
		senaraiFail.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					
					sql = "SELECT H.ID_SIMATI, A.SEKSYEN, A.ID_PERMOHONAN, B.NO_FAIL, C.ID_SUBURUSANSTATUSFAIL, E.KETERANGAN, C.AKTIF, E.ID_STATUS, H.ID_PERMOHONANSIMATI, A.TARIKH_MOHON,B.FLAG_JENIS_FAIL"
						+ " FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B, TBLRUJSUBURUSANSTATUSFAIL C, TBLRUJSUBURUSANSTATUS D, TBLRUJSTATUS E, TBLRUJNEGERI F, TBLRUJDAERAH G, TBLPPKPERMOHONANSIMATI H"
						+ " WHERE G.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '" + userId + "' ";
						
						 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
						
						
						sql += " ) AND C.AKTIF = 1 AND A.ID_STATUS not in (56,999)"
						+ " AND B.ID_FAIL = A.ID_FAIL AND D.ID_SUBURUSANSTATUS = C.ID_SUBURUSANSTATUS AND E.ID_STATUS = D.ID_STATUS AND A.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_PERMOHONAN = H.ID_PERMOHONAN AND A.ID_NEGERIMHN = F.ID_NEGERI AND A.ID_DAERAHMHN = G.ID_DAERAH";
					
					sql = sql + " AND UPPER(B.NO_FAIL) = '" + noFail.trim().toUpperCase() + "'";
					
					sql = sql + " ORDER BY C.ID_SUBURUSANSTATUSFAIL ASC";
					ResultSet rs = stmt.executeQuery(sql);		
					
					if (rs.next()){

						//PENDAFTARAN
						if (rs.getString("ID_STATUS").equals("8") || rs.getString("ID_STATUS").equals("9") || rs.getString("ID_STATUS").equals("14")  || rs.getString("ID_STATUS").equals("169") ){
							//row for pendaftaran
							h = new Hashtable();
							h.put("bil", "1");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PENDAFTARAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							
							senaraiFail.addElement(h);
							count++;
							
						} else
						
						//KEPUTUSAN PERMOHONAN
						if (rs.getString("ID_STATUS").equals("50") || rs.getString("ID_STATUS").equals("53") || rs.getString("ID_STATUS").equals("151") || rs.getString("ID_STATUS").equals("152")){
							//row for pendaftaran
							h = new Hashtable();
							h.put("bil", "1");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "14"); //keputusan permohonan - last status for pendaftaran
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PENDAFTARAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for keputusan permohonan
							h = new Hashtable();
							h.put("bil", "2");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERMOHONAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							count++;
							
						} else
							
						//NOTIS PERBICARAAN
						if (rs.getString("ID_STATUS").equals("18") || rs.getString("ID_STATUS").equals("44") || rs.getString("ID_STATUS").equals("175") || rs.getString("ID_STATUS").equals("172") || rs.getString("ID_STATUS").equals("173") || rs.getString("ID_STATUS").equals("174") || rs.getString("ID_STATUS").equals("176") || rs.getString("ID_STATUS").equals("177")){
							//row for pendaftaran
							h = new Hashtable();
							h.put("bil", "1");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "14"); //keputusan permohonan - last status for pendaftaran
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PENDAFTARAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for keputusan permohonan
							h = new Hashtable();
							h.put("bil", "2");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "53"); //permohonan diteruskan - last status for keputusan permohonan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERMOHONAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for notis perbicaraan
							h = new Hashtable();
							h.put("bil", "3");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","NOTIS PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							count++;
						
						} else
						
						//KEPUTUSAN PERBICARAAN
						if (rs.getString("ID_STATUS").equals("41") || rs.getString("ID_STATUS").equals("47")){
							//row for pendaftaran
							h = new Hashtable();
							h.put("bil", "1");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "14"); //keputusan permohonan - last status for pendaftaran
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PENDAFTARAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for keputusan permohonan
							h = new Hashtable();
							h.put("bil", "2");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "53"); //permohonan diteruskan - last status for keputusan permohonan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERMOHONAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for notis perbicaraan
							h = new Hashtable();
							h.put("bil", "3");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "18"); //notis perbicaraan - last status for notis perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","NOTIS PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for keputusan perbicaraan
							h = new Hashtable();
							h.put("bil", "4");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							count++;
							
						} else
						
						//PERINTAH PERBICARAAN
						if (rs.getString("ID_STATUS").equals("21") || rs.getString("ID_STATUS").equals("25")){
							//row for pendaftaran
							h = new Hashtable();
							h.put("bil", "1");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "14"); //keputusan permohonan - last status for pendaftaran
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PENDAFTARAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for keputusan permohonan
							h = new Hashtable();
							h.put("bil", "2");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "53"); //permohonan diteruskan - last status for keputusan permohonan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERMOHONAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for notis perbicaraan
							h = new Hashtable();
							h.put("bil", "3");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "18"); //notis perbicaraan - last status for notis perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","NOTIS PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for keputusan perbicaraan
							h = new Hashtable();
							h.put("bil", "4");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "41"); //selesai - last status for keputusan keputusan perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							//row for perintah perbicaraan
							h = new Hashtable();
							h.put("bil", "5");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PERINTAH PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
							
							count++;
						} else 
						
						//PERMOHONAN RAYUAN
						if (rs.getString("ID_STATUS").equals("64") || rs.getString("ID_STATUS").equals("163") || rs.getString("ID_STATUS").equals("166") || rs.getString("ID_STATUS").equals("167") || rs.getString("ID_STATUS").equals("180")){
							//row for pendaftaran
							h = new Hashtable();
							h.put("bil", "1");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "14"); //keputusan permohonan - last status for pendaftaran
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PENDAFTARAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
								
							//row for keputusan permohonan
							h = new Hashtable();
							h.put("bil", "2");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "53"); //permohonan diteruskan - last status for keputusan permohonan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERMOHONAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
								
							//row for notis perbicaraan
							h = new Hashtable();
							h.put("bil", "3");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "18"); //notis perbicaraan - last status for notis perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","NOTIS PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
								
							//row for keputusan perbicaraan
							h = new Hashtable();
							h.put("bil", "4");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "41"); //selesai - last status for keputusan keputusan perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
								
							//row for perintah perbicaraan
							h = new Hashtable();
							h.put("bil", "5");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "21"); //selesai - last status for perintah perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PERINTAH PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
								
							//row for permohonan rayuan
							h = new Hashtable();
							h.put("bil", "6");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PERMOHONAN RAYUAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
								
							count++;	
						} else 
							
						//KEPUTUSAN RAYUAN RAYUAN
						if (rs.getString("ID_STATUS").equals("164") || rs.getString("ID_STATUS").equals("165")){
							//row for pendaftaran
							h = new Hashtable();
							h.put("bil", "1");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "14"); //keputusan permohonan - last status for pendaftaran
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PENDAFTARAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
									
							//row for keputusan permohonan
							h = new Hashtable();
							h.put("bil", "2");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "53"); //permohonan diteruskan - last status for keputusan permohonan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERMOHONAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
									
							//row for notis perbicaraan
							h = new Hashtable();
							h.put("bil", "3");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "18"); //notis perbicaraan - last status for notis perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","NOTIS PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
									
							//row for keputusan perbicaraan
							h = new Hashtable();
							h.put("bil", "4");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "41"); //selesai - last status for keputusan keputusan perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
									
							//row for perintah perbicaraan
							h = new Hashtable();
							h.put("bil", "5");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "21"); //selesai - last status for perintah perbicaraan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PERINTAH PERBICARAAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
									
							//row for permohonan rayuan
							h = new Hashtable();
							h.put("bil", "6");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", "163"); //selesai - last status for permohonan rayuan
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","PERMOHONAN RAYUAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
								
							//row for permohonan rayuan
							h = new Hashtable();
							h.put("bil", "7");
							h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
							h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
							h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
							h.put("keterangan","KEPUTUSAN RAYUAN");
							h.put("flagjenisfail", rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs.getString("FLAG_JENIS_FAIL"));
							h.put("seksyen", rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
							h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
							senaraiFail.addElement(h);
									
							count++;	
						}
					}
				}
			}			

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPermohonan", "");
		    	h.put("noFail", "");
		    	h.put("keterangan","Tiada Rekod");
		    	h.put("idStatus", "");
		    	h.put("tarikhMohon", "");
		    	h.put("idPermohonanSimati", "");
		    	h.put("flagjenisfail","");
		    	h.put("seksyen","");
		    	h.put("idSimati","");
				senaraiFail.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getStatusBefore(String idPermohonan) throws Exception{
		Db db = null;
		db = new Db();
		String sql = "";
		
		sql = "SELECT C.ID_STATUS, A.ID_SUBURUSANSTATUSFAIL, A.ID_PERMOHONAN, A.AKTIF"
			+ " FROM TBLRUJSUBURUSANSTATUSFAIL A, TBLRUJSUBURUSANSTATUS B, TBLRUJSTATUS C"
			+ " WHERE B.ID_SUBURUSANSTATUS = A.ID_SUBURUSANSTATUS AND C.ID_STATUS = B.ID_STATUS"
			+ " AND A.AKTIF = 0 AND A.ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY A.ID_SUBURUSANSTATUSFAIL DESC";

		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()){
			return rs.getString("ID_STATUS").toString();
		} else {
			return null;
		}
	}
	
	public String getIdPerbicaraan(String noFail) throws Exception {
		Db db = null;
		db = new Db();
		String sql = "";
		
		sql = "SELECT A.NO_FAIL, D.ID_PERBICARAAN"
			+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKKEPUTUSANPERMOHONAN C, TBLPPKPERBICARAAN D"
			+ " WHERE A.ID_FAIL = B.ID_FAIL AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_KEPUTUSANPERMOHONAN = D.ID_KEPUTUSANPERMOHONAN"
			+ " AND A.NO_FAIL = '" + noFail + "'";

		Statement stmt = db.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()){
			return rs.getString("ID_PERBICARAAN").toString();
		} else {
			return "";
		}
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}
}

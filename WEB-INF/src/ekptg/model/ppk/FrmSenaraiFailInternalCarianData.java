package ekptg.model.ppk;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;

import org.apache.log4j.Logger;


public class FrmSenaraiFailInternalCarianData {
	private static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8SecaraOnlineData.class);
	
	private  Vector list = new Vector();
	public  void  setCarianFail(String usid,String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String chkDataFail = noFail.trim();
	      String chkDataPemohon = namaPemohon.trim();
	      String chkDataSimati = namaSimati.trim();
	      String chkDataIcSimati = icSimati.trim();
	      String chkDataJenisKp = JenisIc;
	      /*
	      sql = "Select f.id_fail,f.no_fail,f.tarikh_daftar_fail,a.id_permohonan,a.tarikh_Mohon,a.tarikh_Masuk,s.keterangan,p.id_simati,p.nama_simati,pp.nama_pemohon "+
	      	"from Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pp, Tblppkpermohonansimati ms "+
	      	"where a.id_status = s.id_status(+) and a.id_fail = f.id_fail(+) and a.id_permohonan = pp.id_permohonan(+) and ms.id_permohonan = a.id_permohonan  and a.id_status = 8 and a.seksyen = 8 and a.id_masuk = '"+usid+"'  and ms.id_simati = p.id_simati";
	      */
	      sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
              + " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
              + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"
			+" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
			+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
			+" WHERE"
			+" D.id_daerah in ( select distinct u.id_daerahurus from"
			+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+usid+"' ";
			
			 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
			
			sql += " )"
			+" AND ST.ID_STATUS = S.ID_STATUS(+)"
			+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
		//	+" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
			+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
			+" AND A.ID_FAIL = F.ID_FAIL(+)"
			+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
			+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
			+" AND P.ID_SIMATI = MS.ID_SIMATI"
			+" AND A.ID_STATUS <> '999'"
			+" AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
			+" AND A.SEKSYEN = 8"
			+" AND STA.AKTIF = 1"
			+" AND (A.FLAG_JENIS_PERMOHONAN = 1)"
			+" AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2)"
			//+" AND F.FLAG_JENIS_FAIL = 1"
		//	+" ORDER BY F.ID_FAIL DESC"
			+"";



	      //NO FAIL
	      if (noFail != "") {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
				}
			}
	    //NAMA PEMOHON
	      if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
				}
			}
	    //NAMA SIMATI
	      if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(P.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
				}
			}
	    //IC SIMATI
	      if (icSimati != "") {
			   if (!icSimati.trim().equals("")) {
					if (chkDataJenisKp.equals("1")){
						sql = sql + " AND UPPER(P.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("2")){
						sql = sql + " AND UPPER(P.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("3")){
						sql = sql + " AND UPPER(P.N0_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
		    	}
			}
	      sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
	      System.out.println("sql--->>>>>"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("tarikhmohon", rs.getString("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_simati")==null?"":rs.getString("nama_simati"));
				//System.out.println("cari data---->>>"+h);
				list.addElement(h);
				bil++;

	      }
	    } finally {
	      if (db != null) db.close();
	    }
	}
	 public  Vector getList(){
		  return list;
	  }


		private  Vector listRPP = new Vector();
		public  void  setCarianFailRPP(String usid,String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc)throws Exception {
			setCarianFailRPProle( usid, noFail,  namaPemohon,  namaSimati,  icSimati,  JenisIc, "");				
		}
		public  void  setCarianFailRPProle(String usid,String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, String role)throws Exception {
		    Db db = null;
		    listRPP.clear();
		    String sql = "";
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      String chkDataFail = noFail.trim();
		      String chkDataPemohon = namaPemohon.trim();
		      String chkDataSimati = namaSimati.trim();
		      String chkDataIcSimati = icSimati.trim();
		      String chkDataJenisKp = JenisIc;
		      /*
		      sql = "Select f.id_fail,f.no_fail,f.tarikh_daftar_fail,a.id_permohonan,a.tarikh_Mohon,a.tarikh_Masuk,s.keterangan,p.id_simati,p.nama_simati,pp.nama_pemohon "+
		      	"from Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pp, Tblppkpermohonansimati ms "+
		      	"where a.id_status = s.id_status(+) and a.id_fail = f.id_fail(+) and a.id_permohonan = pp.id_permohonan(+) and ms.id_permohonan = a.id_permohonan  and a.id_status = 8 and a.seksyen = 8 and a.id_masuk = '"+usid+"'  and ms.id_simati = p.id_simati";
		      */
		  /*    sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
	              + " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
	              + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"
				+" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
				+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
				+" WHERE"
				+" D.id_daerah in ( select distinct u.id_daerahurus from"
				+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+usid+"')"
				+" AND ST.ID_STATUS = S.ID_STATUS(+)"
				+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
				+" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
				+" AND A.ID_FAIL = F.ID_FAIL(+)"
				+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
				+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
				+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
				+" AND P.ID_SIMATI = MS.ID_SIMATI"
			//	+" AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
				+" AND A.SEKSYEN = 8"
				+" AND STA.AKTIF = 1"
				+" AND (A.FLAG_JENIS_PERMOHONAN = 1)"
				//+" AND F.FLAG_JENIS_FAIL = 1"
				//+" AND F.FLAG_JENIS_FAIL = 1"
			//	+" ORDER BY F.ID_FAIL DESC"
				+"";

		   */
		      sql="SELECT * FROM ( SELECT DISTINCT " +
		      		//" A.ID_PERMOHONAN," +
		      		" STA.ID_SUBURUSANSTATUSFAIL,STA.ID_SUBURUSANSTATUS,STA.AKTIF,F.ID_FAIL, " +
		      		" F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL, " +
		      		" S.KETERANGAN, P.ID_SIMATI,"
					+" P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON" +
					//" , " +
					//" PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU,"
					//+" P.NO_KP_BARU "
					" FROM TBLRUJSUBURUSANSTATUSFAIL STA,TBLPPKPERMOHONAN A, TBLPFDFAIL F,"
					+" TBLPPKPERMOHONANSIMATI MS,TBLPPKSIMATI P,TBLRUJDAERAH D,TBLRUJSTATUS S,"
					+" TBLRUJSUBURUSANSTATUS ST,TBLPPKPEMOHON PM"
					+" WHERE A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+" AND A.ID_FAIL = F.ID_FAIL"
					+" AND P.ID_SIMATI = MS.ID_SIMATI"
					+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+" AND A.ID_DAERAHMHN = D.ID_DAERAH(+)"
					+" AND ST.ID_STATUS = S.ID_STATUS(+)"
					+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
				//	+" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
					+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
				//	+" AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
					+" AND (A.SEKSYEN = 8 OR A.SEKSYEN = 17)" +
							"  AND STA.AKTIF = 1 "
			    //  +" AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2)"
				    +" AND A.FLAG_JENIS_PERMOHONAN = 1";
		      
					if(!role.equals("meps") && !role.equals("(MEPS)PPK"))//masukkan role yg kita tak nak filter
					{
						sql += " AND D.ID_DAERAH IN"
					+" (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+usid+"' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
					
					sql +=" )";
					}
				//	+" AND f.no_fail is not null ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";




		      //NO FAIL
		      if (noFail != "") {
					if (!noFail.trim().equals("")) {
						sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
					}
				}
		    //NAMA PEMOHON
		      if (namaPemohon != "") {
					if (!namaPemohon.trim().equals("")) {
						sql = sql + " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
					}
				}
		    //NAMA SIMATI
		      if (namaSimati != "") {
					if (!namaSimati.trim().equals("")) {
						sql = sql + " AND UPPER(P.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
					}
				}
		    //IC SIMATI
		      if (icSimati != "") {
				   if (!icSimati.trim().equals("")) {
						if (chkDataJenisKp.equals("1")){
							sql = sql + " AND UPPER(P.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
						else if (chkDataJenisKp.equals("2")){
							sql = sql + " AND UPPER(P.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
						else if (chkDataJenisKp.equals("3")){
							sql = sql + " AND UPPER(P.N0_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
			    	}
				}
		      sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC) where rownum < 2000 ";
		      //System.out.println("sql--->>>>>"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
					h.put("id_Permohonan", rs.getString("id_Permohonan"));
					h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
					h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
					h.put("tarikhmohon", rs.getString("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
					h.put("tarikhMasuk", rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
					h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
					h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("id_simati", rs.getString("id_Simati"));
					h.put("namasimati", rs.getString("nama_simati")==null?"":rs.getString("nama_simati"));
					//System.out.println("cari data---->>>"+h);
					listRPP.addElement(h);
					bil++;

		      }
		    } finally {
		      if (db != null) db.close();
		    }
		}
		 public  Vector getListRPP(){
			  return listRPP;
		  }



	 private  Vector listKutipan = new Vector();
		public  void  setCarianFailKutipan(String usid,String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc)throws Exception {
		    Db db = null;
		    listKutipan.clear();
		    String sql = "";
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      String chkDataFail = noFail.trim();
		      String chkDataPemohon = namaPemohon.trim();
		      String chkDataSimati = namaSimati.trim();
		      String chkDataIcSimati = icSimati.trim();
		      String chkDataJenisKp = JenisIc;
		      /*
		      sql = "Select f.id_fail,f.no_fail,f.tarikh_daftar_fail,a.id_permohonan,a.tarikh_Mohon,a.tarikh_Masuk,s.keterangan,p.id_simati,p.nama_simati,pp.nama_pemohon "+
		      	"from Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pp, Tblppkpermohonansimati ms "+
		      	"where a.id_status = s.id_status(+) and a.id_fail = f.id_fail(+) and a.id_permohonan = pp.id_permohonan(+) and ms.id_permohonan = a.id_permohonan  and a.id_status = 8 and a.seksyen = 8 and a.id_masuk = '"+usid+"'  and ms.id_simati = p.id_simati";
		      */
		      sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
	              + " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
	              + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"
				+" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
				+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
				+" WHERE"
				+" D.id_daerah in ( select distinct u.id_daerahurus from"
				+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+usid+"' ";
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
				sql+= " )"
				+" AND ST.ID_STATUS = S.ID_STATUS(+)"
				+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
			//	+" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"
				+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
				+" AND A.ID_FAIL = F.ID_FAIL(+)"
				+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
				+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
				+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
				+" AND P.ID_SIMATI = MS.ID_SIMATI"
				+" AND A.ID_STATUS <> '999'"
				+" AND (STA.ID_SUBURUSANSTATUS = 340 OR STA.ID_SUBURUSANSTATUS = 342 OR STA.ID_SUBURUSANSTATUS = 553)"
				+" AND A.SEKSYEN = 8"
				+" AND STA.AKTIF = 1"
				+" AND A.FLAG_JENIS_PERMOHONAN = 1"
				+" AND F.FLAG_JENIS_FAIL = 3"
			//	+" ORDER BY F.ID_FAIL DESC"
				+"";



		      //NO FAIL
		      if (noFail != "") {
					if (!noFail.trim().equals("")) {
						sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
					}
				}
		    //NAMA PEMOHON
		      if (namaPemohon != "") {
					if (!namaPemohon.trim().equals("")) {
						sql = sql + " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
					}
				}
		    //NAMA SIMATI
		      if (namaSimati != "") {
					if (!namaSimati.trim().equals("")) {
						sql = sql + " AND UPPER(P.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
					}
				}
		    //IC SIMATI
		      if (icSimati != "") {
				   if (!icSimati.trim().equals("")) {
						if (chkDataJenisKp.equals("1")){
							sql = sql + " AND UPPER(P.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
						else if (chkDataJenisKp.equals("2")){
							sql = sql + " AND UPPER(P.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
						else if (chkDataJenisKp.equals("3")){
							sql = sql + " AND UPPER(P.N0_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
			    	}
				}
		      sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
		      //System.out.println("sql--->>>>>"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
					h.put("id_Permohonan", rs.getString("id_Permohonan"));
					h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
					h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
					h.put("tarikhmohon", rs.getString("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
					h.put("tarikhMasuk", rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
					h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
					h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("id_simati", rs.getString("id_Simati"));
					h.put("namasimati", rs.getString("nama_simati")==null?"":rs.getString("nama_simati"));
					//System.out.println("cari data---->>>"+h);
					listKutipan.addElement(h);
					bil++;

		      }
		    } finally {
		      if (db != null) db.close();
		    }
		}
		 public  Vector getListKutipan(){
			  return listKutipan;
		  }
		 
		 
		 private  Vector list17_online = new Vector();
			public  void  setCarianFail17_online(String NoFail, String NoKPBaru, String NoKPLama, String NoKPLain)throws Exception {
			    Db db = null;
			    list17_online.clear();
			    String sql = "";
			    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      String chkNoFail = NoFail.trim();
			      String chkNoKPBaru = NoKPBaru.trim();
			      String chkNoKPLama = NoKPLama.trim();
			      String chkNoKPLain = NoKPLain.trim();
			     


					sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL,A.NO_SUBJAKET, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
		                + " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
		                + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"
					+" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+" WHERE"
					//+" D.id_daerah in ( select distinct u.id_daerahurus from"
					//+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"')"
					+"  A.ID_STATUS = S.ID_STATUS(+)"
					+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+" AND A.ID_FAIL = F.ID_FAIL(+)"
					+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+" AND P.ID_SIMATI = MS.ID_SIMATI"
					+" AND A.ID_STATUS IN('21','47','56','70')"
					+" AND A.ID_STATUS <> '999'"
					+" AND A.ID_STATUS <> '50'"
					+" AND A.ID_STATUS <> '169'"
					+" AND STA.AKTIF = 1"
					//+" AND A.FLAG_JENIS_PERMOHONAN = 1"
					+" AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL)"
					+"";


			      //NO FAIL
			      if (chkNoFail != "") {
						if (!chkNoFail.trim().equals("")) {
							sql = sql + " AND UPPER(F.NO_FAIL) = '" + chkNoFail.toUpperCase() + "'";
						}
					}
			      
			      if (chkNoKPBaru != "") {
						if (!chkNoKPBaru.trim().equals("")) {
							sql = sql + " AND UPPER(P.NO_KP_BARU) = '" + chkNoKPBaru.toUpperCase() + "'";
						}
					}
			      
			      if (chkNoKPLama != "") {
						if (!chkNoKPLama.trim().equals("")) {
							sql = sql + " AND UPPER(P.NO_KP_LAMA) = '" + chkNoKPLama.toUpperCase() + "'";
						}
					}
			      
			      if (chkNoKPLain != "") {
						if (!chkNoKPLain.trim().equals("")) {
							sql = sql + " AND UPPER(P.NO_KP_LAIN) = '" + chkNoKPLain.toUpperCase() + "'";
						}
					}
			      
			      sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";

			      
			      myLogger.info("SQL CHECK KP XXXXXXXXXXXXXX :: "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable h;
			      int bil = 1;
			      int count = 0;

			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  	h.put("bil", bil);
						h.put("id_Permohonan", rs.getString("id_Permohonan"));
						h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
						h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
						h.put("tarikh_Mohon", rs.getString("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
						h.put("tarikhMasuk", rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
						h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
						h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
						h.put("id_simati", rs.getString("id_Simati"));
						h.put("namasimati", rs.getString("nama_simati")==null?"":rs.getString("nama_simati"));
						//System.out.println("cari data---->>>"+h);
						list17_online.addElement(h);
			    	  bil++;
			    	 // count++;
			      }
			     
			    } finally {
			      if (db != null) db.close();
			    }
			}
			 public  Vector getList17_online(){
				  return list17_online;
			  }


		private  Vector list17 = new Vector();
		public  void  setCarianFail17(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc,String userid)throws Exception {
		    Db db = null;
		    list17.clear();
		    String sql = "";
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      String chkDataFail = noFail.trim();
		      String chkDataPemohon = namaPemohon.trim();
		      String chkDataSimati = namaSimati.trim();
		      String chkDataIcSimati = icSimati.trim();
		      String chkDataJenisKp = JenisIc;

		      /*	
				sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL,A.NO_SUBJAKET, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
	                + " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
	                + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"
				+" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
				+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
				+" WHERE"
				+" D.id_daerah in ( select distinct u.id_daerahurus from"
				+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"')"
				+" AND A.ID_STATUS = S.ID_STATUS(+)"
				+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"

				+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
				+" AND A.ID_FAIL = F.ID_FAIL(+)"
				+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
				+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
				+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
				+" AND P.ID_SIMATI = MS.ID_SIMATI"

				+" AND A.ID_STATUS IN('21','47','56','70')"
				+" AND A.ID_STATUS <> '999'"
				+" AND STA.AKTIF = 1"

				+" AND A.FLAG_JENIS_PERMOHONAN = 1"
				+" AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL)"
				+"";
			*/
		      boolean setLimit = true;
		      
		      //NO FAIL
		      if (noFail != "") {
					if (!noFail.trim().equals("")) {
						setLimit = false;
					}
				}
		    //NAMA PEMOHON
		      if (namaPemohon != "") {
					if (!namaPemohon.trim().equals("")) {
						setLimit = false;
					}
				}
		    //NAMA SIMATI
		      if (namaSimati != "") {
					if (!namaSimati.trim().equals("")) {
						setLimit = false;
					}
				}
		    //IC SIMATI
		      if (icSimati != "") {
				   if (!icSimati.trim().equals("")) {
					   setLimit = false;
			    	}
				}
		      
		      
sql+= "	      SELECT ID_FAIL, NO_FAIL, NO_SUBJAKET, ID_PERMOHONAN, TARIKH_MOHON, "+
"       TARIKH_MASUK, TARIKH_DAFTAR_FAIL, KETERANGAN, ID_SIMATI, NAMA_SIMATI, "+
"       ID_DAERAHMHN, TARIKH_MOHON_ONLINE, NO_PERMOHONAN_ONLINE, NAMA_PEMOHON, "+
"       NO_KP_BARU, ID_PEMOHON, NO_KP_BARU "+
"  FROM (SELECT DISTINCT A.ID_FAIL, A.NO_FAIL, A.NO_SUBJAKET, A.ID_PERMOHONAN, "+
"                        A.TARIKH_MOHON, A.TARIKH_MASUK, A.TARIKH_DAFTAR_FAIL, "+
"                        S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, "+
"                        A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, "+
"                        A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON, "+
"                        PM.NO_KP_BARU, PM.ID_PEMOHON "+
"                   FROM  "+
"                   (                                     "+  
"                   SELECT ID_STATUS,ID_PEMOHON,ID_PERMOHONAN, ID_FAIL, NO_FAIL, NO_SUBJAKET,TARIKH_MOHON, TARIKH_MASUK, TARIKH_DAFTAR_FAIL, "+
"                   ID_DAERAHMHN, TARIKH_MOHON_ONLINE, NO_PERMOHONAN_ONLINE  FROM "+
"                   ( "+
"                   SELECT PP.ID_STATUS,PP.ID_PEMOHON,PP.ID_PERMOHONAN, FF.ID_FAIL, FF.NO_FAIL, PP.NO_SUBJAKET,PP.TARIKH_MOHON, PP.TARIKH_MASUK, FF.TARIKH_DAFTAR_FAIL, "+
"                   PP.ID_DAERAHMHN, PP.TARIKH_MOHON_ONLINE, PP.NO_PERMOHONAN_ONLINE  "+
"                   FROM  TBLPPKPERMOHONAN PP, TBLPFDFAIL FF  "+
"                   WHERE PP.ID_FAIL = FF.ID_FAIL  "+
"                    AND PP.FLAG_JENIS_PERMOHONAN = 1 "+
"                                       AND (PP.FLAG_PERMOHONAN <> '1' OR PP.FLAG_PERMOHONAN IS NULL) "+
"                                       AND FF.NO_FAIL IS NOT NULL   "+
"                                       AND PP.ID_STATUS IN ('21', '47', '56', '70')  "+
"                   AND  PP.ID_DAERAHMHN IN ( "+
"                           SELECT DISTINCT U.ID_DAERAHURUS "+
"                                      FROM TBLRUJPEJABATURUSAN U, "+
"                                           USERS_INTERNAL UR "+
"                                     WHERE U.ID_PEJABATJKPTG = "+
"                                                            UR.ID_PEJABATJKPTG "+
"                                       AND UR.USER_ID = '"+userid+"' ";

 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
sql += "                                       ) "+
"                                       ORDER BY FF.ID_FAIL DESC)  ";
//"                                       --WHERE ROWNUM < 200        "+  
if(setLimit==true)
{
	sql += " WHERE ROWNUM < 250 ";
}
sql += "                   ) A, "+
"                        TBLRUJSTATUS S, "+
"                        TBLPPKSIMATI P, "+
"                        TBLPPKPEMOHON PM, "+
"                        TBLPPKPERMOHONANSIMATI MS, "+
"                        TBLRUJSUBURUSANSTATUS ST, "+
"                        TBLRUJSUBURUSANSTATUSFAIL STA, "+
"                        TBLRUJDAERAH D "+
"                  WHERE A.ID_STATUS = S.ID_STATUS "+
"                    AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS "+
"                    AND A.ID_PEMOHON = PM.ID_PEMOHON "+
"                    AND A.ID_DAERAHMHN = D.ID_DAERAH "+
"                    AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN "+
"                    AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN "+
"                    AND P.ID_SIMATI = MS.ID_SIMATI "+
"                    AND STA.AKTIF = 1 ";



		      //NO FAIL
		      if (noFail != "") {
					if (!noFail.trim().equals("")) {
						sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
					}
				}
		    //NAMA PEMOHON
		      if (namaPemohon != "") {
					if (!namaPemohon.trim().equals("")) {
						sql = sql + " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
					}
				}
		    //NAMA SIMATI
		      if (namaSimati != "") {
					if (!namaSimati.trim().equals("")) {
						sql = sql + " AND UPPER(P.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
					}
				}
		    //IC SIMATI
		      if (icSimati != "") {
				   if (!icSimati.trim().equals("")) {
						if (chkDataJenisKp.equals("1")){
							sql = sql + " AND UPPER(P.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
						else if (chkDataJenisKp.equals("2")){
							sql = sql + " AND UPPER(P.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
						else if (chkDataJenisKp.equals("3")){
							sql = sql + " AND UPPER(P.N0_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
						}
			    	}
				}
		   //   sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
		      //sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
		      sql += "               ORDER BY A.ID_FAIL DESC "+
		    		  "               ) ";

		      System.out.println(" SENARAI S17 : "+sql);
		      //System.out.println("sql--->>>>>"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h;
		      int bil = 1;
		      int count = 0;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  	h.put("bil", bil);
					h.put("id_Permohonan", rs.getString("id_Permohonan"));
					h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
					h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
					h.put("tarikh_Mohon", rs.getString("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
					h.put("tarikhMasuk", rs.getString("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
					h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
					h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("id_simati", rs.getString("id_Simati"));
					h.put("namasimati", rs.getString("nama_simati")==null?"":rs.getString("nama_simati"));
					//System.out.println("cari data---->>>"+h);
					list17.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil","");
					h.put("id_Permohonan", "");
					h.put("id_Fail", "");
					h.put("no_Fail", "");
					h.put("tarikh_Mohon","");
					h.put("tarikhMasuk","");
					h.put("keterangan","");
					h.put("id_simati", "");
					h.put("namasimati", "");
					list17.addElement(h);
		      }
		    } finally {
		      if (db != null) db.close();
		    }
		}
		 public  Vector getList17(){
			  return list17;
		  }

		 private  Vector listSenarai17 = new Vector();

		 public void updateDatabase() throws Exception
		 {
			 	Db db = null;
			 	Db db3 = null;
			 	Db db2 = null;
			 	Db db4 = null;
			 	Db db2a = null;
			    listSenarai17.clear();
			    String sql = "";
			    String sql2 = "";
			    String sql3 = "";
			    String sql4 = "";
			    try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      
				      sql += " SELECT * FROM TBLRUJSUBURUSANSTATUSFAIL WHERE id_permohonan IN (SELECT id_permohonan FROM TBLRUJSUBURUSANSTATUSFAIL "
				    		  + " WHERE ID_SUBURUSANSTATUS = '340' "
				    		  + " AND AKTIF = '1' "
				    		  + " AND ID_PERMOHONAN in (SELECT ID_PERMOHONAN FROM TBLPPKPERMOHONAN WHERE ID_STATUS = '152' AND  ID_NEGERIMHN='11')) ";
				      System.out.println("sql--->>>>>"+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				      
				      
				      while (rs.next()) {
				    	  db2 = new Db();
				    	  Statement stmt2 = db2.getStatement();
				    	  String id_permohonan = rs.getString("id_Permohonan");
				    	  //String id_permohonan = "99191174433";
				    	  sql2 = "UPDATE TBLRUJSUBURUSANSTATUSFAIL SET AKTIF = '0' WHERE id_permohonan IN ("+id_permohonan+")";
				    	  sql3 = "SELECT TARIKH_KEMASKINI,ID_KEMASKINI,ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN IN ("+id_permohonan+")";
				    	  System.out.println("sql2--->>>>>"+sql2);
				    	  System.out.println("sql3--->>>>>"+sql3);
				    	  System.out.println("----------------");
				    	  try {
				    		  db2a = new Db();
				    		  Statement stmt2a = db2a.getStatement();
				    		  ResultSet rs2 = stmt2a.executeQuery(sql2);
				    		 // while (rs2.next()){
				    			  db3 = new Db();
				    			  Statement stmt3 = db3.getStatement();
				    			  ResultSet rs3 = stmt3.executeQuery(sql3);
						      		while (rs3.next()){
						      			Date tarikh_kemaskini = rs3.getDate("TARIKH_KEMASKINI");
						      			String id_kemaskini = rs3.getString("ID_KEMASKINI");
						      			String id_fail = rs3.getString("ID_FAIL");
						      			System.out.println("----TARIKH KEMASKINI----"+tarikh_kemaskini+"----------------");
						      			System.out.println("-----ID KEMASKINI----"+id_kemaskini+"----------------");
						      			sql4 = "Insert into TBLRUJSUBURUSANSTATUSFAIL "
						    				  + " (ID_PERMOHONAN, ID_SUBURUSANSTATUS, AKTIF, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_FAIL) "
						    				  + " Values "
						    				  + "("+id_permohonan+", 454, '1', "+id_kemaskini+", TO_DATE('"+tarikh_kemaskini+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), "+id_kemaskini+", TO_DATE('"+tarikh_kemaskini+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), "+id_fail+")";
						      			System.out.println("sql4--->>>>>"+sql4);
						      			try {
						      				db4 = new Db();
						      				Statement stmt4 = db4.getStatement();
						      				ResultSet rs4 = stmt4.executeQuery(sql4);
						      				
						      					System.out.println("====================");
						      				
						      		
						      			}finally {
						      				if (db4 != null) db4.close();
						      			}
						      			}   	
				    	 // }  	
						   
				      }finally {
					      if (db2 != null) db2.close();
					    } 
				      
			    
			    }} finally {
				      if (db != null) db.close();
				    }
			    
		 }
		 public  void  setCarianFailSenarai17(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, String userid)throws Exception {
			    Db db = null;
			    listSenarai17.clear();
			    String sql = "";
			    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      String chkDataFail = noFail.trim();
			      String chkDataPemohon = namaPemohon.trim();
			      String chkDataSimati = namaSimati.trim();
			      String chkDataIcSimati = icSimati.trim();
			      String chkDataJenisKp = JenisIc;
			      sql = "SELECT DISTINCT F.ID_FAIL, F.NO_FAIL,A.NO_SUBJAKET, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL,"
		                + " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON,"
		                + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"
					+" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P,"
					+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D"
					+" WHERE"
					+" D.id_daerah in ( select distinct u.id_daerahurus from"
					+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"' ";
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userid+"  ";
					sql +=" )"
					+" AND ST.ID_STATUS = S.ID_STATUS(+)"
					+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"
					+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)"
					+" AND A.ID_FAIL = F.ID_FAIL(+)"
					+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
					+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN"
					+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN"
					+" AND P.ID_SIMATI = MS.ID_SIMATI"
					+" AND (STA.ID_SUBURUSANSTATUS = 430 OR STA.ID_SUBURUSANSTATUS = 432 OR STA.ID_SUBURUSANSTATUS = 534)"
					+" AND A.SEKSYEN = 17"
					+" AND STA.AKTIF = 1"
					+" AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2)"
					+" AND A.FLAG_JENIS_PERMOHONAN = 1"
					+"";

			      //NO FAIL
			      if (noFail != "") {
						if (!noFail.trim().equals("")) {
							sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
						}
					}
			    //NAMA PEMOHON
			      if (namaPemohon != "") {
						if (!namaPemohon.trim().equals("")) {
							sql = sql + " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
						}
					}
			    //NAMA SIMATI
			      if (namaSimati != "") {
						if (!namaSimati.trim().equals("")) {
							sql = sql + " AND UPPER(P.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
						}
					}
			    //IC SIMATI
			      if (icSimati != "") {
					   if (!icSimati.trim().equals("")) {
							if (chkDataJenisKp.equals("1")){
								sql = sql + " AND UPPER(P.NO_KP_BARU) LIKE '%" + icSimati.toUpperCase() + "%'";
							}
							else if (chkDataJenisKp.equals("2")){
								sql = sql + " AND UPPER(P.NO_KP_LAMA) LIKE '%" + icSimati.toUpperCase() + "%'";
							}
							else if (chkDataJenisKp.equals("3")){
								sql = sql + " AND UPPER(P.N0_KP_LAIN) LIKE '%" + icSimati.toUpperCase() + "%'";
							}
				    	}
					}
			      sql = sql + " AND f.no_fail is not null ORDER BY F.ID_FAIL DESC";
			      //System.out.println("sql--->>>>>"+sql);
			      ResultSet rs = stmt.executeQuery(sql);

			      int bil = 1;
			      int count = 0;

			      while (rs.next()) {
			   Hashtable h = new Hashtable();

						h.put("bil", bil);
						h.put("id_Permohonan", rs.getString("id_Permohonan"));
						h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
						h.put("id_Pemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
						h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
						h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
						h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
						h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
						h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
						h.put("id_simati", rs.getString("id_Simati"));
						h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
						h.put("namapemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
						h.put("nokppemohon", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
						h.put("daerahmohon", rs.getString("id_Daerahmhn")==null?"":rs.getString("id_Daerahmhn"));
						h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
						listSenarai17.addElement(h);
			    	  bil++;
			    	  count++;
			      }/*
			      if (count == 0){
			    	  h = new Hashtable();
			    	  h = new Hashtable();
						h.put("bil", "");
						h.put("id_Permohonan", "");
						h.put("id_Fail","");
						h.put("id_Pemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
						h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
						h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
						h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
						h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
						h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
						h.put("id_simati", rs.getString("id_Simati"));
						h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
						h.put("namapemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
						h.put("nokppemohon", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
						h.put("daerahmohon", rs.getString("id_Daerahmhn")==null?"":rs.getString("id_Daerahmhn"));
						h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
						listSenarai17.addElement(h);
			      }*/
			    } finally {
			      if (db != null) db.close();
			    }
			}
			 public  Vector getListSenarai17(){
				  return listSenarai17;
			  }


}

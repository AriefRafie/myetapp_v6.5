package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmSenaraiFailOnlineCarianData {


	private static Vector list = new Vector();
	private static Vector listnama = new Vector();
	private static Vector listkp = new Vector();
	private static Vector listfailnamapemohon = new Vector();
	private static Vector listCARI17 = new Vector();
	
	
	
	public  void setListCarian17(String usid,String idonline,String nama,String kp) throws Exception {
		Db db = null;
		listCARI17.clear();		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			 String chkDataidonline = idonline.trim();
		      String chkDatanama = nama.trim();
		      String chkDatakp = kp.trim();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
	/*
			sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL," 
                + " S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON," 
                + " PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"                 
			+" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
			+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
			+" WHERE" 
			+" D.id_daerah in ( select distinct u.id_daerahurus from"
			+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+usid+"')"
			+" AND ST.ID_STATUS = S.ID_STATUS(+)"
			+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 
		//	+" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)" 
			+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
			+" AND A.ID_FAIL = F.ID_FAIL(+)" 
			+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
			+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
			+" AND P.ID_SIMATI = MS.ID_SIMATI" 
			+" AND STA.ID_SUBURUSANSTATUS = 615" 
			+" AND A.ID_STATUS <> '999'"
			+" AND A.SEKSYEN = 17"  
			+" AND STA.AKTIF = 1" 
			+" AND A.FLAG_JENIS_PERMOHONAN = 0" 
			+"";
	*/	
			
			
			sql="SELECT A.NO_PERMOHONAN_ONLINE,F.ID_FAIL,F_D.ID_FAIL AS ID_FAILDULU,A.ID_PERMOHONAN,A.ID_PERMOHONANTERDAHULU, F.NO_FAIL,  A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL, S.KETERANGAN, " 
				+" P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE,  PM.NAMA_PEMOHON, PM.NO_KP_BARU, "
				+" PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU FROM TBLPPKPERMOHONAN A,TBLPPKPERMOHONAN A_D, TBLPFDFAIL F, TBLPFDFAIL F_D, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
				+" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
				+" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
				+" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+usid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
				
				
				sql += " ) AND ST.ID_STATUS = S.ID_STATUS(+) "
				+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
				+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) "
				+" AND A.ID_FAIL = F.ID_FAIL(+) "
				+" AND A.ID_DAERAHMHN = D.ID_DAERAH "
				+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN "
				+" AND A.ID_PERMOHONANTERDAHULU = A_D.ID_PERMOHONAN "
				+" AND A_D.ID_FAIL = F_D.ID_FAIL(+) "
				+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN "
				+" AND P.ID_SIMATI = MS.ID_SIMATI AND A.ID_STATUS <> '999' "
				+" AND A.SEKSYEN = 17 "				
				 +" AND A.FLAG_JENIS_PERMOHONAN = 0" 
				 +" AND STA.ID_SUBURUSANSTATUS = 615" 
				 +" AND STA.AKTIF = 1 ";
			//	+" AND A.ID_PERMOHONANTERDAHULU IS NOT NULL ORDER BY STA.ID_SUBURUSANSTATUSFAIL";
			
			
			
			//NO FAIL
			if (idonline != "") {
				if (!idonline.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_PERMOHONAN_ONLINE) LIKE '%" + chkDataidonline.toUpperCase() + "%'";
				}
			}
			 //NAMA PEMOHON
		    if (nama != "") {
					if (!nama.trim().equals("")) {
						sql = sql + " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + chkDatanama.toUpperCase() + "%'";
					}
				}

		  //IC SIMATI
		      if (kp != "") {
				   if (!kp.trim().equals("")) {
						
							sql = sql + " AND (UPPER(PM.NO_KP_BARU) LIKE '%" + chkDatakp.toUpperCase() + "%' OR UPPER(PM.NO_KP_LAMA) LIKE '%" + chkDatakp.toUpperCase() + "%' OR UPPER(PM.NO_KP_LAIN) LIKE '%" + chkDatakp.toUpperCase() + "%')";
						
			    	}
				}  
			
			 sql = sql + " ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";
			 ////
			 System.out.println("sql online--->>>>>"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h; 
			int bil = 0;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("id_FailDulu", rs.getString("ID_FAILDULU")==null?"":rs.getString("ID_FAILDULU"));
				
				
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("noonline", rs.getString("no_Permohonan_Online")==null?"":rs.getString("no_Permohonan_Online"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
				h.put("nokppemohon", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn")==null?"":rs.getString("id_Daerahmhn"));
				h.put("tarikhmohononline", rs.getDate("tarikh_Mohon_Online")==null?"":sdf.format(rs.getDate("tarikh_Mohon_Online")));
				
				
				
				listCARI17.addElement(h);
				bil++;	
			}
			//return list;
		}finally {
			if(db != null) db.close();
			
		}
		}
	 public static Vector getListCarian17(){
		  return listCARI17;
	  }
	
	public  void setListCarian(String usid,String idonline,String nama,String kp) throws Exception {
		Db db = null;
		list.clear();		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			String chkDataidonline = idonline.trim();
		    String chkDatanama = nama.trim();
		    String chkDatakp = kp.trim();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
		/*
			sql= " SELECT P.ID_PERMOHONAN,P.TARIKH_MOHON_ONLINE, F.TARIKH_DAFTAR_FAIL, P.ID_FAIL, P.TARIKH_MOHON,P.TARIKH_MASUK, P.TARIKH_MOHON, P.TARIKH_MOHON_ONLINE, P.NO_PERMOHONAN_ONLINE, P.ID_DAERAHMHN,"+
			" S.KETERANGAN, PM.NAMA_PEMOHON,PM.NO_KP_BARU,SM.NAMA_SIMATI,PS.ID_SIMATI,D.NAMA_DAERAH,F.NO_FAIL, F.ID_FAIL, PM.ID_PEMOHON"+
			" FROM TBLPPKPERMOHONAN P, TBLRUJSTATUS S, TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI PS, TBLPPKSIMATI SM, TBLRUJDAERAH D,USERS_INTERNAL U,TBLPFDFAIL F"+				
			" WHERE P.ID_STATUS = 150"+
			" AND P.ID_STATUS = S.ID_STATUS"+
			" AND P.ID_PERMOHONAN = PM.ID_PERMOHONAN"+
			" AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN(+)"+
			" AND PS.ID_SIMATI = SM.ID_SIMATI"+
			" AND D.ID_DAERAH = U.ID_DAERAH"+
			" AND U.ID_DAERAH = P.ID_DAERAHMHN"+
			" AND P.ID_FAIL = F.ID_FAIL"+			
			" AND U.USER_ID = '"+usid+"'"+
		    " AND P.JUMLAH_HTA_TARIKHMOHON > 0"+
			"";
			*/

			sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL," 
			+" S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON," 
			+" PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"                 
            +" ,( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) HARI"                 
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
			+" AND STA.ID_SUBURUSANSTATUS = 614" 
			+" AND A.ID_STATUS <> '999'"
			+" AND A.SEKSYEN = 8"  
			+" AND STA.AKTIF = 1" 
			+" AND A.FLAG_JENIS_PERMOHONAN = 0"
            +" AND ( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) <= 21 " 
			+"" ;
			
			//NO FAIL
			if (idonline != "") {
				if (!idonline.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_PERMOHONAN_ONLINE) LIKE '%" + chkDataidonline.toUpperCase() + "%'";
				}
			}
			//NAMA PEMOHON
		    if (nama != "") {
					if (!nama.trim().equals("")) {
						sql = sql + " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + chkDatanama.toUpperCase() + "%'";
					}
				}
		    //IC SIMATI
		    if (kp != "") {
			   if (!kp.trim().equals("")) {
				   sql = sql + " AND (UPPER(PM.NO_KP_BARU) LIKE '%" + chkDatakp.toUpperCase() + "%' OR UPPER(PM.NO_KP_LAMA) LIKE '%" + chkDatakp.toUpperCase() + "%' OR UPPER(PM.NO_KP_LAIN) LIKE '%" + chkDatakp.toUpperCase() + "%')";
			   }
		    }  
			
		    sql = sql + " ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";
			//System.out.println("sql online--->>>>>"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h; 
			int bil = 0;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("noonline", rs.getString("no_Permohonan_Online")==null?"":rs.getString("no_Permohonan_Online"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
				h.put("nokppemohon", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn")==null?"":rs.getString("id_Daerahmhn"));
				h.put("tarikhmohononline", rs.getDate("tarikh_Mohon_Online")==null?"":sdf.format(rs.getDate("tarikh_Mohon_Online")));
				list.addElement(h);
				bil++;	

			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		
	}
	/**
	 * Senarai permohonan online (keseluruhan)
	 * @param usid
	 * @param idonline
	 * @param nama
	 * @param kp
	 * @throws Exception
	 */
	public Vector getSenaraiCarian(String usid,String idonline,String nama,String kp) throws Exception {
		Db db = null;
		list.clear();		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			String chkDataidonline = idonline.trim();
		    String chkDatanama = nama.trim();
		    String chkDatakp = kp.trim();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
		/*
			sql= " SELECT P.ID_PERMOHONAN,P.TARIKH_MOHON_ONLINE, F.TARIKH_DAFTAR_FAIL, P.ID_FAIL, P.TARIKH_MOHON,P.TARIKH_MASUK, P.TARIKH_MOHON, P.TARIKH_MOHON_ONLINE, P.NO_PERMOHONAN_ONLINE, P.ID_DAERAHMHN,"+
			" S.KETERANGAN, PM.NAMA_PEMOHON,PM.NO_KP_BARU,SM.NAMA_SIMATI,PS.ID_SIMATI,D.NAMA_DAERAH,F.NO_FAIL, F.ID_FAIL, PM.ID_PEMOHON"+
			" FROM TBLPPKPERMOHONAN P, TBLRUJSTATUS S, TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI PS, TBLPPKSIMATI SM, TBLRUJDAERAH D,USERS_INTERNAL U,TBLPFDFAIL F"+				
			" WHERE P.ID_STATUS = 150"+
			" AND P.ID_STATUS = S.ID_STATUS"+
			" AND P.ID_PERMOHONAN = PM.ID_PERMOHONAN"+
			" AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN(+)"+
			" AND PS.ID_SIMATI = SM.ID_SIMATI"+
			" AND D.ID_DAERAH = U.ID_DAERAH"+
			" AND U.ID_DAERAH = P.ID_DAERAHMHN"+
			" AND P.ID_FAIL = F.ID_FAIL"+			
			" AND U.USER_ID = '"+usid+"'"+
		    " AND P.JUMLAH_HTA_TARIKHMOHON > 0"+
			"";
			*/

			sql = "SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, F.TARIKH_DAFTAR_FAIL," 
			+" S.KETERANGAN, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON," 
			+" PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU"                 
            +" ,( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) HARI"                 
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
			
			sql+=" )"
			+" AND ST.ID_STATUS = S.ID_STATUS(+)"
			+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 
		//	+" AND PM.ID_PERMOHONAN = A.ID_PERMOHONAN(+)" 
			+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
			+" AND A.ID_FAIL = F.ID_FAIL(+)" 
			+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
			+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
			+" AND P.ID_SIMATI = MS.ID_SIMATI" 
			+" AND STA.ID_SUBURUSANSTATUS = 614" 
			+" AND A.ID_STATUS <> '999'"
			+" AND A.SEKSYEN = 8"  
			+" AND STA.AKTIF = 1" 
			+" AND A.FLAG_JENIS_PERMOHONAN = 0"
            //+" AND ( TO_DATE(SYSDATE,'dd/mm/yyyy') -  TO_DATE(A.TARIKH_MOHON_ONLINE,'dd/mm/yyyy')) <= 21 " 
			+"" ;
			//NO FAIL
			if (idonline != "") {
				if (!idonline.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_PERMOHONAN_ONLINE) LIKE '%" + chkDataidonline.toUpperCase() + "%'";
				}
			}
			//NAMA PEMOHON
		    if (nama != "") {
					if (!nama.trim().equals("")) {
						sql = sql + " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + chkDatanama.toUpperCase() + "%'";
					}
				}
		    //IC SIMATI
		    if (kp != "") {
			   if (!kp.trim().equals("")) {
				   sql = sql + " AND (UPPER(PM.NO_KP_BARU) LIKE '%" + chkDatakp.toUpperCase() + "%' OR UPPER(PM.NO_KP_LAMA) LIKE '%" + chkDatakp.toUpperCase() + "%' OR UPPER(PM.NO_KP_LAIN) LIKE '%" + chkDatakp.toUpperCase() + "%')";
			   }
		    }  
			
		    sql = sql + " ORDER BY STA.ID_SUBURUSANSTATUSFAIL DESC";
			//System.out.println("sql online--->>>>>"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h; 
			int bil = 0;
			list = new Vector();
			while (rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("id_Permohonan"));
				h.put("id_Fail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
				h.put("no_Fail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
				h.put("noonline", rs.getString("no_Permohonan_Online")==null?"":rs.getString("no_Permohonan_Online"));
				h.put("tarikhmohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
				h.put("tarikhMasuk", rs.getDate("tarikh_Masuk")==null?"":sdf.format(rs.getDate("tarikh_Masuk")));
				h.put("tarikhDaftar", rs.getDate("tarikh_daftar_fail")==null?"":sdf.format(rs.getDate("tarikh_daftar_fail")));
				h.put("keterangan",rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				h.put("id_simati", rs.getString("id_Simati"));
				h.put("namasimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
				h.put("namapemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
				h.put("nokppemohon", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru"));
				h.put("daerahmohon", rs.getString("id_Daerahmhn")==null?"":rs.getString("id_Daerahmhn"));
				h.put("tarikhmohononline", rs.getDate("tarikh_Mohon_Online")==null?"":sdf.format(rs.getDate("tarikh_Mohon_Online")));
				list.addElement(h);
				bil++;	

			}
			return list;
		}finally {
			if(db != null) db.close();
		}
		
	}
	
	public static Vector getListCarian(){
		return list;
	}
	 
	public static void  setCarianFail(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc)throws Exception {
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
	      sql = "Select f.id_fail,f.no_fail,f.tarikh_daftar_fail,a.id_permohonan,a.tarikh_Mohon,a.tarikh_Masuk,s.keterangan,p.id_simati,p.nama_simati,pp.nama_pemohon "+
	      	"from Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, Tblppkpemohon pp, Tblppkpermohonansimati ms "+
	      	"where a.id_status = s.id_status(+) and a.id_fail = f.id_fail(+) and a.id_pemohon = pp.id_pemohon(+) and ms.id_permohonan = a.id_permohonan and ms.id_simati = p.id_simati";
	      
	      //NO FAIL
	      if (noFail != "") {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
				}
			}
	    //NAMA PEMOHON
	      if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
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
	      ////System.out.println("sql--->>>>>"+sql);
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
				////System.out.println("cari data---->>>"+h);
				list.addElement(h);
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
				list.addElement(h);
	      }
	    } finally {
	      if (db != null) db.close();
	    }
	}
	 public static Vector getList(){
		  return list;
	  }
	
}

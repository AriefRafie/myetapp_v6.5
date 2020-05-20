/**
 * 
 */
package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Date; //13/1/2020: arief add

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmPrmhnnSek8KptsanBicaraData {
	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8KptsanBicaraData.class);
	
	private static Vector list = new Vector();
	private static Vector listMS = new Vector();	
	private static Vector listBicara = new Vector();
	private static Vector listWaris = new Vector();
	private static Vector ListExistDataBayaran = new Vector();	
	private static Vector listWaris17 = new Vector();
	private static Vector listBayaran = new Vector();
	private static Vector listBayaran17 = new Vector();
	private static Vector listBayaranDendaLewatPendaftaran = new Vector(); // arief add
	private static Vector maklumatBayaranDendaLewat = new Vector (); //arief add
	private static Vector MaklumatPermohonan17 = new Vector();	
	private static Vector MaklumatBayaran = new Vector();
	private static Vector MaklumatPerintah = new Vector();
	private static Vector listPerintah = new Vector();
	private static Vector listNegeri = new Vector();
	private static Vector listDaerah = new Vector();
	private static Vector listPerintahTangguh = new Vector();
	private static Vector listRots = new Vector();
	private static Vector listMufti = new Vector();	
	private static Vector CheckingWaris = new Vector();
	private static Vector listPerintahTangguhROTS = new Vector();
	private static Vector listPerintahKolateral = new Vector();
	private static Vector listWarisPlanitif = new Vector();
	private static Vector listWarisDefendan = new Vector();
	private static Vector listRotsKeputusan = new Vector();	
	private Vector listgetidpejmhkmh = new Vector();
	private static Vector listWarisUpdate = new Vector();
	private static Vector listTangguhROTS = new Vector();
	private static Vector MaklumatPerintahList = new Vector();
	private static Vector listPerintahDefault = new Vector();
	private static Vector listKolateral = new Vector();
	private static Vector MaklumatTangguh = new Vector();
	private static Vector listCarian = new Vector();
	private static Vector listCarian17 = new Vector();
	private static Vector MaklumatNotis = new Vector();
	private static Vector listTangguhKolateral = new Vector();
	private Vector selectedWaris = new Vector();
	private Vector selectedWaris17 = new Vector();
	private Vector existingWaris = new Vector();
	private Vector listUploadFail = new Vector();
	private static Vector ListSelectedWaris = new Vector();
	private static Vector ListSelectedWaris17 = new Vector();
	private static Vector checkingValue = new Vector();	
	private static Vector dataKeputusan = new Vector();		
	private static Vector dataKeputusanBayaran = new Vector();	
	private static Vector listPerintahTangguhMufti = new Vector();
	private static Vector dataFlagTangguh = new Vector();		
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");	

	 public static Vector getFlagTangguh(){
		return dataFlagTangguh;
	 }	
	 public static Vector getMaklumatPermohonan17(){
		return MaklumatPermohonan17;
	 }			
	 public static Vector getJumlahBayaran17(){
		return listBayaran17;
	 }		
	 public static Vector getListSelectedWaris() {
		return ListSelectedWaris;
	 }	
	 public static Vector getListSelectedWaris17() {
			return ListSelectedWaris17;
	 }		 
	 public static Vector getListTangguhKolateral(){
		return listTangguhKolateral;
	 }	
	 public Vector getFailUpload() {
		return listUploadFail;
	 }	
	 public Vector getMaklumatWaris() {
		return existingWaris;
	 }	
	 public Vector getSelectedWaris() {
		return selectedWaris;
	 }	
	 public Vector getSelectedWaris17() {
			return selectedWaris17;
	 }	 
	 public Vector getSenaraiWaris(){
		return listWaris;
	 }	
	 public Vector getSenaraiWaris17(){
			return listWaris17;
		 }
	 public static Vector getViewNotis(){
		return MaklumatNotis;
	 }	
	 public static Vector getViewTangguh(){
		return MaklumatTangguh;
	 }		
	 public static Vector getViewPerintahList(){
		return MaklumatPerintahList;
	 }	
	 public static Vector getListNegeri(){
		return listNegeri;
	 }		
	 public static Vector getListDaerah(){
		return listDaerah;
	 }
	 public static Vector getData(){
		return list;	 
	 }	 
	 public static Vector getDataBayaran() {
		return MaklumatBayaran;
	 }	 
	 public static Vector getDataPerintah() {
			return MaklumatPerintah;
	 }	 	 
	 public static Vector getDataPerintahView() {
			return listPerintah;
	 }	
	 public static Vector getDataPerintahKolateral() {
			return listPerintahKolateral;
	 }	
	 public static Vector getDataKeputusan() {
			return dataKeputusan;
	 }	
	 public static Vector getDataKeputusanBayaran() {
			return dataKeputusanBayaran;
	 }	 
	 public static Vector getDataKolateralView() {
			return listKolateral;
	 }	 
	 public static Vector getListCarian(){
		return listCarian;
	 }
	 public static Vector getListCarian17(){
		return listCarian17;
	 }	
	 public static Vector getListSemakWithData(){
		return listSemakWithData;
	 }
	 //arief add OPEN
	 public static Vector getListBayaranDendaLewatPendaftaran() {
		 return listBayaranDendaLewatPendaftaran;
	 }
	 public static Vector getMaklumatBayaranDendaLewat() {
		 return maklumatBayaranDendaLewat;
	 }
	 //arief add CLOSE
	 
	 
	public static void setData(String id) throws Exception{
		Db db = null;
		list.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			r.add("f.id_Fail");
			r.add("f.no_Fail");
			r.add("d.id_Daerah");
			r.add("p.id_Permohonan");
			r.add("p.tarikh_Mohon");
			r.add("s.no_Kp_Baru");
			r.add("s.no_Kp_Lama");
			r.add("s.jenis_Kp");
			r.add("s.no_kp_lain");
			r.add("s.id_Simati");
			r.add("s.nama_Simati");
			r.add("s.tarikh_Mati");
			r.add("pm.id_Pemohon");
			r.add("pm.nama_Pemohon");
			r.add("pm.no_kp_baru");
			r.add("pm.no_kP_lama");
			r.add("pm.jenis_Kp");
			r.add("pm.no_Kp_lain");
			r.add("pm.alamat_1");
			r.add("pm.alamat_2");
			r.add("pm.alamat_3");
			r.add("pm.poskod");
			r.add("pm.bandar");
			r.add("n.id_Negeri");
			r.add("n.nama_Negeri");
			r.add("d.nama_Daerah");
			r.add("p.seksyen");
			r.add("st.keterangan");
			r.add("p.id_status");
			r.add("u.nama_pejabat");
			r.add("pm.id_negeri");
		    
			r.add("f.id_Negeri",r.unquote("n.id_Negeri(+)"));
			r.add("p.id_Daerahmhn",r.unquote("d.id_Daerah(+)"));
			r.add("p.id_Fail",r.unquote("f.id_Fail"));
			r.add("pm.id_Pemohon",r.unquote("p.id_Pemohon"));
			r.add("ps.id_Permohonan",r.unquote("p.id_Permohonan"));
			r.add("st.id_status",r.unquote("p.id_status"));
			r.add("d.id_daerah",r.unquote("u.id_daerah(+)"));
			r.add("s.id_simati",r.unquote("ps.id_simati"));
			
			r.add("p.id_Permohonan",id);
			
			sql = r.getSQLSelect("Tblpfdfail f, Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, Tblppkpemohon pm, Tblrujstatus st, tblrujpejabatjkptg u, Tblppkpermohonansimati ps");
		//	myLogger.info("sql data 11111111111 -->"+sql);
			ResultSet rs = stmt.executeQuery(sql);
		    Hashtable h;
			
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
		    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
		    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
		    	  h.put("tarikhMohon", sdf.format(rs.getDate("tarikh_Mohon"))==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
		    	  h.put("noKpBaru1", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(0,6));
		    	  h.put("noKpBaru2", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(6,8));
		    	  h.put("noKpBaru3", rs.getString("no_Kp_Baru")==null?"":rs.getString("no_Kp_Baru").substring(8,12));
		    	  h.put("noKpLama", rs.getString("no_Kp_Lama")==null?"":rs.getString("no_Kp_Lama"));
		    	  h.put("jenisKp", rs.getString(8)==null?"":rs.getString(8));
		    	  h.put("noKpLain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
		    	  h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
		    	  h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
		    	  h.put("tarikhMati",  sdf.format(rs.getDate("tarikh_Mati"))==null?"": sdf.format(rs.getDate("tarikh_Mati")));
		    	  h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
		    	  h.put("namaPemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
		    	  h.put("noKpBaruPemohon1", rs.getString(15)==null?"":rs.getString(15).substring(0,6));
		    	  h.put("noKpBaruPemohon2", rs.getString(15)==null?"":rs.getString(15).substring(6,8));
		    	  h.put("noKpBaruPemohon3", rs.getString(15)==null?"":rs.getString(15).substring(8,12));
		    	  h.put("noKpLamaPemohon", rs.getString(16)==null?"":rs.getString(16));
		    	  h.put("jenisKpPemohon", rs.getString(17)==null?"":rs.getString(17));
		    	  h.put("noKpLainPemohon", rs.getString(18)==null?"":rs.getString(18));
		    	  h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
		    	  h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
		    	  h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
		    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
		    	  h.put("idnegeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
		    	  h.put("namanegeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
		    	  h.put("namadaerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
		    	  h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	  h.put("namaPejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
		    	  h.put("pmidnegeri", rs.getString(31)==null?"":rs.getString(31));
		    	  
		    	  list.addElement(h);
		}      
	}
	finally {
		if(db != null)db.close();
		}
}

	//CARIAN SEKSYEN 8
	public static void setCarianFail(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, String usid, String statusFail)throws Exception {
	    Db db = null;
	    listCarian.clear();
	    String sql = "";
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String chkDataFail = noFail.trim();
	      String chkDataPemohon = namaPemohon.trim();
	      String chkDataSimati = namaSimati.trim();
	      String chkDataIcSimati = icSimati.trim();
	      String chkDataJenisKp = JenisIc;
	      String chkstatusFail = statusFail;
	      
	  
			//SYARAT
			sql = "SELECT PP.NAMA_PEMOHON, MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
				+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
				+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
				+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
				+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
				+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "' ";
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
				sql += " ) " 
				+ " AND P.ID_STATUS = S.ID_STATUS "
				+ " AND P.ID_FAIL = F.ID_FAIL(+) "
				+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
				+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
				+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
				+ " AND M.ID_SIMATI = MS.ID_SIMATI " 
				+ " AND P.ID_PEMOHON = PP.ID_PEMOHON "
				+ " AND (P.ID_STATUS=18 OR P.ID_STATUS=41 OR P.ID_STATUS=44 OR P.ID_STATUS=47 OR P.ID_STATUS=172 OR P.ID_STATUS=173 OR P.ID_STATUS=174 OR P.ID_STATUS=175 OR P.ID_STATUS=176 OR P.ID_STATUS=177) "
				+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
				+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
				+ " AND STA.AKTIF = '1' "
				+ " AND P.ID_STATUS <> '999' " 
				+ " AND P.SEKSYEN = '8' "
				+ " AND P.FLAG_JENIS_PERMOHONAN = '1' "
				+ " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";
	     
			//NO FAIL
			if(noFail != null)
			{
				if(!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
				}
			}//close if nofail
  
			//NAMA PEMOHON
			if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
				}
			}//close if pemohon
  
			//NAMA SIMATI
			if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(M.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
				}
			}//close if nama simati
	  
			//IC SIMATI
			if (icSimati != "") {
				if (!icSimati.trim().equals("")) {
					if (chkDataJenisKp.equals("1")){
						sql = sql + " AND UPPER(M.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("2")){
						sql = sql + " AND UPPER(M.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("3")){
						sql = sql + " AND UPPER(M.NO_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
				}
				
			}//close if ic simati  
			
			

  
			//sorting
			sql += " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
			
			ResultSet rs = stmt.executeQuery(sql);
  
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				
				h = new Hashtable();
	  
				h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS"));
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikhmohon", rs.getString("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("namasimati",rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("keputusan_permohonan", rs.getString("KEPUTUSAN_PERMOHONAN"));
		
				listCarian.addElement(h);
				bil++;
			}
			//return list;
	} finally {
		if (db != null) db.close();
	}

}//close carian
	
	//CARIAN SEKSYEN 17
	public static void setCarianFail17(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, String usid)throws Exception {
	    Db db = null;
	    listCarian17.clear();
	    String sql = "";
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String chkDataFail = noFail.trim();
	      String chkDataPemohon = namaPemohon.trim();
	      String chkDataSimati = namaSimati.trim();
	      String chkDataIcSimati = icSimati.trim();
	      String chkDataJenisKp = JenisIc;
	      
	      //SYARAT
	      sql = "SELECT PP.NAMA_PEMOHON, MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
		      + " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
		      + " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
		      + " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
		      + " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
		      + " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "' ";
			   sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
			  sql += " ) " 
		      + " AND P.ID_STATUS = S.ID_STATUS "
		      + " AND P.ID_FAIL = F.ID_FAIL(+) "
		      + " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
		      + " AND P.ID_DAERAHMHN = D.ID_DAERAH "
		      + " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
		      + " AND M.ID_SIMATI = MS.ID_SIMATI " 
		      + " AND P.ID_PEMOHON = PP.ID_PEMOHON "
		      + " AND (P.ID_STATUS=18 OR P.ID_STATUS=41 OR P.ID_STATUS=44 OR P.ID_STATUS=47 OR P.ID_STATUS=172 OR P.ID_STATUS=173 OR P.ID_STATUS=174 OR P.ID_STATUS=175 OR P.ID_STATUS=176 OR P.ID_STATUS=177) "
		      + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
		      + " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
		      + " AND STA.AKTIF = '1' "
		      + " AND P.ID_STATUS <> '999' " 
		      + " AND P.SEKSYEN = '17' "
	          + " AND P.FLAG_JENIS_PERMOHONAN = '1' "
	      	  + " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";
	     
	      //NO FAIL
	      if(noFail != null)
	      	{
	    	  if(!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
				}
	      	}//close if nofail
	      
	      //NAMA PEMOHON
	      if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
				}
			}//close if pemohon
	      
	      //NAMA SIMATI
	      if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(M.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
				}
			}//close if nama simati
	   	  
	      //IC SIMATI
	      if (icSimati != "") {
			   if (!icSimati.trim().equals("")) {
					if (chkDataJenisKp.equals("1")){
						sql = sql + " AND UPPER(M.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("2")){
						sql = sql + " AND UPPER(M.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("3")){
						sql = sql + " AND UPPER(M.NO_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
		    	}
			}//close if ic simati  
	      
	      //sorting
	      sql += " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  	h.put("bil", bil);
				h.put("id_Permohonan", rs.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS"));
				h.put("id_Fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_Fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikhmohon", rs.getString("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("namasimati",rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("id_simati", rs.getString("ID_SIMATI"));
				
			  listCarian17.addElement(h);
	    	  bil++;
	    	  
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	
		
	
	 private static Vector listSemakWithData = new Vector();
	 

	
	 public static void setListSemakWithData(String id_keputusanpermohonan)throws Exception {
			//myLogger.info("[setlistsemak ==" + id_keputusanpermohonan);
		 	Db db = null;
			listSemakWithData.clear();
			String sql = "";
			String id_perbicaraan = "";
			
			try{
				//get latest notis by tarikh masuk
			      db = new Db();
			      Statement stmtMT = db.getStatement();
			      SQLRenderer rMT = new SQLRenderer();		
			      
			      //Alter by elly on 17 April,to handle case where max id perbicaraan is not really the
			      //latest perbicaraan			      
			      
//			      sql = "SELECT distinct max(b.id_perbicaraan)as id_perbicaraan ";
//			      sql +="FROM Tblppkperbicaraan b ";
//			      sql +="WHERE b.id_keputusanpermohonan = '"+id_keputusanpermohonan+"'";
			      
				  sql =  " SELECT MAX(id_perbicaraan) as id_perbicaraan FROM (";
				  sql += " SELECT distinct a.id_perbicaraan FROM Tblppkperbicaraan a ";
				  sql += " WHERE ";
				  sql += " tarikh_masuk = (select max(tarikh_masuk) from tblppkperbicaraan b where b.id_keputusanpermohonan=a.id_keputusanpermohonan) ";
				  sql += " and a.id_keputusanpermohonan = '" + id_keputusanpermohonan + "'";
				  sql += " ) ";	
			      
			      myLogger.debug("get latest notis:"+sql);
			      ResultSet rsMT = stmtMT.executeQuery(sql);			     
			      
			      while (rsMT.next()) {
			    	  id_perbicaraan = rsMT.getString("id_perbicaraan");			    	
			      }
		
				//db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("pb.id_perbicaraan");
				r.add("pb.id_unitpsk");
				r.add("pb.id_keputusanpermohonan");
				r.add("pb.tarikh_notis");
				r.add("pb.tarikh_bicara");
				r.add("pb.masa_bicara");
				
				r.add("pb.tempat_bicara");
				r.add("pb.bil_bicara");
				r.add("pb.alamat_bicara1");
				r.add("pb.alamat_bicara2");
				r.add("pb.alamat_bicara3");
				
				r.add("pb.poskod");
				r.add("pb.id_negeribicara");
				r.add("pb.peg_pengendali");
				r.add("pb.tarikh_masuk");
							
				r.add("pb.id_keputusanpermohonan",id_keputusanpermohonan);
				r.add("pb.id_perbicaraan",id_perbicaraan);
								
				sql = r.getSQLSelect("Tblppkperbicaraan pb");
				myLogger.info("[sqlMAX]:semakwithdata = " +sql);
				ResultSet rs = stmt.executeQuery(sql);
			    Hashtable h;
				
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("id_perbicaraan", rs.getString("id_perbicaraan"));			    	 
			    	  h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
			    	  h.put("tarikh_notis", rs.getDate("tarikh_notis")==null?"": Format.format(rs.getDate("tarikh_notis")));
			    	  //h.put("tarikh_notis",  Format.format(rs.getDate("tarikh_notis"))==null?"": Format.format(rs.getDate("tarikh_notis")));
			    	  h.put("tarikh_bicara", rs.getDate("tarikh_bicara")==null?"": Format.format(rs.getDate("tarikh_bicara")));
			    	  //h.put("tarikh_bicara",  Format.format(rs.getDate("tarikh_bicara"))==null?"": Format.format(rs.getDate("tarikh_bicara")));			    	  
			    	  h.put("masa_bicara", rs.getString("masa_bicara")==null?"":rs.getString("masa_bicara"));
			    	  h.put("tempat_bicara", rs.getString("tempat_bicara")==null?"":rs.getString("tempat_bicara"));
			    	  h.put("bil_bicara", rs.getString("bil_bicara")==null?"":rs.getString("bil_bicara"));
			    	  h.put("alamat_bicara1", rs.getString("alamat_bicara1")==null?"":rs.getString("alamat_bicara1"));
			    	  h.put("alamat_bicara2", rs.getString("alamat_bicara2")==null?"":rs.getString("alamat_bicara2"));
			    	  h.put("alamat_bicara3", rs.getString("alamat_bicara3")==null?"":rs.getString("alamat_bicara3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("id_negeribicara", rs.getString("id_negeribicara")==null?"":rs.getString("id_negeribicara"));
			    	  h.put("peg_pengendali", rs.getString("peg_pengendali")==null?"":rs.getString("peg_pengendali"));
			    	  
			    	  listSemakWithData.addElement(h);
			      	}      
				}
				finally{
					if(db != null)db.close();
				}
		}//close semakwithdata
	
	

	 public static Vector getListnegeri() throws Exception {
		    Db db = null;
//		    String sql = "select a.id_agensi, a.kod_agensi, a.nama_agensi, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.jawatan, a.id_negeri from tblrujagensi a order by a.id_negeri";
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Negeri");
		      r.add("nama_Negeri");
		      r.add("kod_Negeri");
		      
		     
		      sql = r.getSQLSelect("Tblrujnegeri","kod_Negeri");
		   //   myLogger.info("GET MAHKAMAH = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("id_Negeri", rs.getInt("id_Negeri"));
		     
		       
		        if(rs.getString("nama_Negeri") == null){
		        	h.put("nama_Negeri", "");
		        }else{
		        	h.put("nama_Negeri", rs.getString("nama_Negeri"));
		        }
		        if(rs.getString("kod_Negeri") == null){
		        	h.put("kod_Negeri", "");
		        }else{
		        	h.put("kod_Negeri", rs.getString("kod_Negeri"));
		        }
		       /* 
		        if(rs.getString("description") == null){
		        	h.put("description", "");
		        }else{
		        	h.put("description", rs.getString("description"));
		        }
		       */
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

	public static Vector getDaerahByNegeriUser(String userid)throws Exception {
	    Db db = null;
	    String sql = "Select d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri From users_internal u, tblrujdaerah d, tblrujpejabaturusan r  Where u.id_negeri = d.id_negeri and d.id_daerah = r.id_daerah and d.id_negeri = r.id_negeri and u.user_id = "+ userid +" group by d.id_daerah,d.kod_daerah,d.nama_daerah,d.id_negeri order by d.id_daerah";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("id_jenisnopb");
	      r.add("keterangan");
	
	      //sql = r.getSQLSelect(sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector listDaerahByUser = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("iddaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	    	  h.put("namadaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		    	  listDaerahByUser.addElement(h);
	      }
	      return listDaerahByUser;
	    } finally {
	      if (db != null) db.close();
	    }
	  }

		
		public static Vector setInfoBicara(String id, String id_perbicaraan) throws Exception {
			
		    Db db = null;
		    listBicara.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("p.id_Permohonan");
		      r.add("po.id_Pemohon");
		      r.add("bc.id_perbicaraan");
		      r.add("bc.id_unitpsk");
		      r.add("pr.nama_pegawai");
		      r.add("bc.tarikh_bicara");
		      r.add("p.id_fail");
		      r.add("bc.masa_bicara");

		      r.add("p.id_Pemohon",r.unquote("po.id_Pemohon"));
		      r.add("pr.id_unitpsk",r.unquote("bc.id_unitpsk"));

		      r.add("p.id_Permohonan",id);
		      r.add("bc.id_perbicaraan", id_perbicaraan, "=");

		      sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkpemohon po, Tblppkperbicaraan bc, Tblppkrujunit pr");
		      myLogger.info("SQL == "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;
		      
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
		    	h.put("id_Pemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
		    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
		    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
		    	h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
		    	h.put("tarikh_bicara", rs.getDate("tarikh_bicara")==null?"":Format.format(rs.getDate("tarikh_bicara")));
		    	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
		    	h.put("masa_bicara", rs.getString("masa_bicara")==null?"":rs.getString("masa_bicara"));
		    	
		    	listBicara.addElement(h);
		    	bil++;
		      }
		      return listBicara;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}	
		
		public static Vector setSenaraiWaris(String idpermohonan,String id_permohonansimati) throws Exception {		
		    Db db = null;
		    listWaris.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		     /* 
			sql = " SELECT b.id_permohonan, a.id_ob, a.id_simati, a.id_permohonansimati,a.nama_ob, a.no_kp_baru, a.no_kp_lain, a.no_kp_lama, a.status_hidup,a.id_saudara, d.keterangan "
				+ " FROM tblppkob a,tblppkpermohonansimati b,tblppkpermohonan c,tblppkrujsaudara d "
				+ " WHERE a.id_permohonansimati = b.id_permohonansimati AND b.id_permohonan = c.id_permohonan "
				+ " AND NVL(a.id_saudara, 0) = d.id_saudara AND a.status_hidup = 0 " 
				+ " AND b.id_permohonan = '"+ idpermohonan +"' ";
				*/
		      
		      sql = " SELECT b.id_permohonan, a.id_ob, a.id_simati, a.id_permohonansimati,a.nama_ob, a.no_kp_baru, a.no_kp_lain, a.no_kp_lama, a.status_hidup,a.id_saudara, d.keterangan "
					+ " FROM tblppkob a1,tblppkobpermohonan a," +
							" tblppkpermohonansimati b,tblppkpermohonan c,tblppkrujsaudara d "
					+ " WHERE a1.id_ob = a.id_ob and a.id_permohonansimati = '"+id_permohonansimati+"'" +
							" and a1.id_permohonansimati = b.id_permohonansimati AND b.id_permohonan = c.id_permohonan "
					+ " AND NVL(a.id_saudara, 0) = d.id_saudara AND a.status_hidup = 0 " 
					+ " AND b.id_permohonan = '"+ idpermohonan +"' ";  
			  myLogger.info("LISTKAN WARIS 17 TESTING :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);	      
		   
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
		    	h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
		    	h.put("id_simati", rs.getString("id_simati")==null?"":rs.getString("id_simati"));
		    	h.put("id_permohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
		    	h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
		    	h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
		    	h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
		    	h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));
		    	h.put("status_hidup", rs.getString("status_hidup")==null?"":rs.getString("status_hidup"));
		    	h.put("id_saudara", rs.getString("id_saudara")==null?"":rs.getString("id_saudara"));
		    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	
		    	listWaris.addElement(h);
		    	bil++;
		      }
		      return listWaris;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}					

		public static Vector setSenaraiWaris17(String id_perbicaraan,String id_permohonansimati) throws Exception {
			
		    Db db = null;
		    listWaris17.clear();
		    String sql = "";		    
		    
			sql = " SELECT pob.id_ob,pob.nama_ob, pob.no_kp_baru, pob.no_kp_lain, pob.no_kp_lama,pob.status_hidup, pob.id_saudara, d.keterangan "
				+ " FROM tblppknotisob nob, tblppkob pob1, tblppkobpermohonan pob," +
						" tblppkrujsaudara d "
				+ " WHERE pob1.id_ob = pob.id_ob " +
						" and pob.id_permohonansimati = '"+id_permohonansimati+"' " +
								" and nob.id_ob = pob1.id_ob AND NVL(pob.id_saudara,0) = d.id_saudara "
				+ " AND pob.status_hidup = 0 AND NVL(nob.flag_cetak, 0) = 0 " 
				+ " AND nob.id_perbicaraan = '"+ id_perbicaraan +"' ";		
		    
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      myLogger.info("SQL SET SENARAI SEMUA WARIS = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
		    	h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
		    	h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
		    	h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
		    	h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));
		    	h.put("status_hidup", rs.getString("status_hidup")==null?"":rs.getString("status_hidup"));
		    	h.put("id_saudara", rs.getString("id_saudara")==null?"":rs.getString("id_saudara"));
		    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	
		    	listWaris17.addElement(h);
		    	bil++;
		      }
		      return listWaris17;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}
/** 13/1/2020: arief add bagi lewat pendaftaran	(function ini akan beroperasi selepas 1 tahun daripada tarikh Akta dikuatkuasakan)**/	
		public static Vector listBayaranDendaLewatPendaftaran (String tarikhmohon) throws Exception
		{
			Db db = null;
		    listBayaranDendaLewatPendaftaran.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("jumlahBayaranDendaLewatPendaftaran");		      
		      r.add("tarikh_mohon", tarikhmohon);

		      sql = r.getSQLSelect("Tblppkpermohonan");
		      myLogger.info("TARIKH PERMOHONAN ::"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bilDendaLewat = 1;
		      
		      
		      while (rs.next()) {
		    	 h = new Hashtable();
		    	 h.put("tarikh_mohon", rs.getString("tarikh_mohon")==null?"":Integer.parseInt(rs.getString("tarikh_mohon")));
		    	 
		    	 listBayaranDendaLewatPendaftaran.addElement(h);
		    	 bilDendaLewat++;
		      }
		      return listBayaranDendaLewatPendaftaran;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

		public static Vector setJumlahBayaran(String idpermohonan) throws Exception {
			
		    Db db = null;
		    listBayaran.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      //r.add("jumlahBayaranDendaLewatPendaftaran");
		      r.add("jumlah_harta_tarikhmohon");		      

		      //r.add("tarikh_mohon", tarikhmohon);
		      r.add("id_permohonan",idpermohonan);

		      sql = r.getSQLSelect("Tblppkpermohonan");
		      myLogger.info("JUM HARTA TARIKH MOHON ::"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		        h.put("jumlah_harta_tarikhmohon", rs.getString("jumlah_harta_tarikhmohon")==null?"":Double.parseDouble(rs.getString("jumlah_harta_tarikhmohon")));
		        
		    	listBayaran.addElement(h);
		    	bil++;
		      }
		      return listBayaran;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}			
		
		public static void setJumlahBayaran17(String id_permohonansimati) throws Exception {
			Db db = null;
			
			listBayaran17.clear();
			/*String sql = " select sum(a) AS sumharta from (  "+
						 " select nilai_hta_tarikhmohon as a from tblppkhta where id_permohonansimati = '" + id_permohonansimati + "' "+
						 " UNION "+
						 " select nilai_ha_tarikhmohon as a from tblppkha where id_permohonansimati = '" + id_permohonansimati + "' ) ";	
			*/
			
			String sql = " select sum(a) AS sumharta from (  "+
			 " select sum(a.nilai_hta_tarikhmohon) as a " +
			 " from tblppkhta a1,tblppkhtapermohonan a " +
			 " where a1.id_hta = a.id_hta and a1.id_permohonansimati = a.id_permohonansimati" +
			 " and a1.id_permohonansimati = '" + id_permohonansimati + "' "+
			 " UNION "+
			 " select sum(nilai_ha_tarikhmohon) as a " +
			 " from tblppkha where id_permohonansimati = '" + id_permohonansimati + "' ) ";	
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				myLogger.info("SQL PAPAR HARTA TERKINI SEKSYEN 17 = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("sumharta", rs.getString("sumharta")==null?"0":Double.parseDouble(rs.getString("sumharta")));
				
					listBayaran17.addElement(h);
					bil++;	
				}
				
			}finally {
				if(db != null) db.close();
			}	
		}		
		
		public static void setMaklumatPermohonan17(String id) throws Exception {
			Db db = null;
			MaklumatPermohonan17.clear();
			String sql = " select batal_kuasa_pentadbir,batal_p_amanah,harta_tinggal from tblppkpermohonan where id_permohonan = '" + id + "' ";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				myLogger.info("MAKLUMAT PERMOHONAN TERKINI SEKSYEN 17 = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;				
				while (rs.next()){
					h = new Hashtable();
					h.put("batal_kuasa_pentadbir", rs.getString("batal_kuasa_pentadbir")==null?"":rs.getString("batal_kuasa_pentadbir"));
					h.put("batal_p_amanah", rs.getString("batal_p_amanah")==null?"":rs.getString("batal_p_amanah"));
					h.put("harta_tinggal", rs.getString("harta_tinggal")==null?"":rs.getString("harta_tinggal"));					
					MaklumatPermohonan17.addElement(h);
					bil++;	
				}		
			}finally {
				if(db != null) db.close();
			}	
		}				

		public static void add_BayaranPerintah(String idpermohonan,String usid,
				String id_jenisbayaranPerintah,String txtJumBayaran,String txtNomborResitPerintah,
				String txdTarikhBayaranPerintah) throws Exception {
			
				Db db = null;			
				String sql2 = "";			
				try
				{			
//					long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
//					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
					String TBP = "to_date('" + txdTarikhBayaranPerintah + "','dd/MM/yyyy')";		
					
				  //------------------------------- TBLPPKPERINTAH ------------------------------------
				    
//					db = new Db();
//					Statement stmt = db.getStatement();
//					SQLRenderer r = new SQLRenderer();
//					r.add("id_perintah",id_perintah);
//					r.add("id_perbicaraan",id_perbicaraan);
//					r.add("tarikh_perintah", r.unquote(TTP));
//					r.add("id_unitpsk",EDITsocPegawaiPengendali);
//					r.add("flag_jenis_keputusan",flag_jenis_keputusan);
//					r.add("catatan",txtCatatanSelesai);
//					r.add("id_masuk",usid);
//					r.add("tarikh_masuk",r.unquote("sysdate"));
//
//					sql = r.getSQLInsert("Tblppkperintah");		
//					myLogger.info("SQL INSERT TBLPPKPERINTAH = "+sql);
//					stmt.executeUpdate(sql);
								
					//--------------------------------- TBLPPKBAYARAN -----------------------------------------
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",id_jenisbayaranPerintah);
					r2.add("no_resit",txtNomborResitPerintah);
					r2.add("tarikh_bayaran", r2.unquote(TBP));
					r2.add("jumlah_bayaran",txtJumBayaran);
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));					
				
					sql2 = r2.getSQLInsert("Tblppkbayaran");
					myLogger.info("INSERT TBLPPKBAYARAN = "+sql2);
					stmt2.executeUpdate(sql2);				
					
				}finally {
					if(db != null) db.close();
				}			
		}

		public static void add_maklumatPerintah(String id_perbicaraan,String usid,String txdTarikhPerintah,
				String EDITsocPegawaiPengendali,String flag_jenis_keputusan,String txtCatatanSelesai,
				String check_kiv,String date_kiv,String catatan_kiv) throws Exception {
			
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
					
					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
					String T_date_kiv = "to_date('" + date_kiv + "','dd/MM/yyyy')";
					
					myLogger.info(":::::::::: BUKA INSERT PERINTAH");
					long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");					
					PreparedStatement ps = conn.prepareStatement("insert into TBLPPKPERINTAH " +
		                    "(id_perintah,id_perbicaraan,id_unitpsk,flag_jenis_keputusan,catatan," +
		                    "ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI,tarikh_perintah," +
		                    "check_kiv,date_kiv,catatan_kiv) " +
		                    "values(?,?,?,?,?,?,?,sysdate,sysdate,"+r.unquote(TTP)+",?,"+r.unquote(T_date_kiv)+",?)");
					        ps.setLong(1, id_perintah);
					        ps.setString(2, id_perbicaraan);
					        ps.setString(3, EDITsocPegawaiPengendali);
					        ps.setString(4, flag_jenis_keputusan);
					        ps.setString(5, txtCatatanSelesai);
					        ps.setString(6, usid);
					        ps.setString(7, usid);	
					        
					        ps.setString(8, check_kiv);
					        ps.setString(9, catatan_kiv);
					        //,"+check_kiv+","+r.unquote(T_date_kiv)+","+catatan_kiv+"
					        ps.executeUpdate();
					 myLogger.info(":::::::::: TUTUP INSERT PERINTAH");
					 
					 //untuk simpan history dokumen KIV - aishahlatip
					 if(check_kiv.equals("1")){
						 add_kivHistory( id_perintah, id_perbicaraan, usid,
								 check_kiv, date_kiv, catatan_kiv);
					 }
					 
					 
					
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
		
		
		public static void add_kivHistory(long id_perintah,String id_perbicaraan,String usid,
				String check_kiv,String date_kiv,String catatan_kiv) throws Exception {
			
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
						
					
						String T_date_kiv = "to_date('" + date_kiv + "','dd/MM/yyyy')";
						
						myLogger.info(":::::::::: BUKA INSERT TBLPPKKIV_HIST");
						
						PreparedStatement ps = conn.prepareStatement("insert into TBLPPKKIV_HIST " +
			                    "(ID_KIVHISTORY,ID_PERINTAH,ID_PERBICARAAN,DATE_KIV,CHECK_KIV," +
			                    "CATATAN_KIV,ID_MASUK,TARIKH_MASUK) " +
			                    "values(TBLPPKKIV_HIST_SEQ.nextval,?,?,"+r.unquote(T_date_kiv)+",?,?,?,sysdate)");
						        ps.setLong(1, id_perintah);
						        ps.setString(2, id_perbicaraan);
						        ps.setString(3, check_kiv);
						        ps.setString(4, catatan_kiv);
						        ps.setString(5, usid);
			
						        ps.executeUpdate();
						
						
				
					
				}finally {
					if(db != null) db.close();
					
				}			
		}
		
		
		public static void add_BayaranPerintah17(String idSimati,String idpermohonan,String id_perbicaraan,String usid,String txdTarikhPerintah,
				String EDITsocPegawaiPengendali,String flag_jenis_keputusan,String txtCatatanSelesai,String id_jenisbayaranPerintah,
				String txtJumBayaran,String txtNomborResitPerintah,String txdTarikhBayaranPerintah,
				String check_kiv,String date_kiv,String catatan_kiv) throws Exception {
			
			Db db = null;
			String sql = "";
			String sql2 = "";
			
				try
				{			
					long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					String TBP = "to_date('" + txdTarikhBayaranPerintah + "','dd/MM/yyyy')";		
					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
					
					String date_kiv_T = "to_date('" + date_kiv + "','dd/MM/yyyy')";
					
				    //------- TBLPPKPERINTAH ------------------------------------
				    
					/*
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
					sql = r.getSQLInsert("tblppkperintah");		
					myLogger.info("SQL INSERT TBLPPKPERINTAH = "+sql);
					stmt.executeUpdate(sql);
					*/
					db = new Db();
					Statement stmt = db.getStatement();
					 SQLRenderer r = new SQLRenderer();
					 Connection conn = db.getConnection();					
					 PreparedStatement ps = conn.prepareStatement("insert into tblppkperintah " +
			                    "(id_perintah,id_perbicaraan,id_unitpsk,flag_jenis_keputusan,catatan," +
			                    " ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI,tarikh_perintah,date_kiv,check_kiv,catatan_kiv) " +
			                    "values(?,?,?,?,?,?,?,sysdate,sysdate,"+r.unquote(TTP)+","+r.unquote(date_kiv_T)+",?,?)");
						        ps.setLong(1, id_perintah);
						        ps.setString(2, id_perbicaraan);
						        ps.setString(3, EDITsocPegawaiPengendali);
						        ps.setString(4, flag_jenis_keputusan);
						        ps.setString(5, txtCatatanSelesai);		
						        ps.setString(6, usid);	
						        ps.setString(7, usid);	
						        ps.setString(8, check_kiv);	
						        ps.setString(9, catatan_kiv);
						        ps.executeUpdate();
										
					//--------------------------------- TBLPPKBAYARAN -----------------------------------------
					
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",id_jenisbayaranPerintah);
					r2.add("no_resit",txtNomborResitPerintah);
					r2.add("tarikh_bayaran", r2.unquote(TBP));
					r2.add("jumlah_bayaran",txtJumBayaran);
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));					
				
					sql2 = r2.getSQLInsert("tblppkbayaran");
					myLogger.info("SQL INSERT TBLPPKBAYARAN PERINTAH = "+sql2);
					stmt2.executeUpdate(sql2);
					myLogger.info("XXXXX");
					generateRowForPembahagianPerintahSebelum(idSimati, String.valueOf(id_perintah), usid);

				}finally {
					if(db != null) db.close();
				}			
		}

			public static void add_BayaranPusaka(String usid,String idpermohonan,String id_perbicaraan,String txdTarikhPerintah,
					String EDITsocPegawaiPengendali,String flag_jenis_keputusan,String txtCatatanSelesai,String id_jenisbayaranCukaiPusaka,
					String txtJumBayaranPusaka,String txtNomborResitPusaka,String txdTarikhBayaranPusaka) throws Exception {
				
				Db db = null;
				String sql2 = "";
				
					try
					{			
						long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 	
						String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
						String TBP = "to_date('" + txdTarikhBayaranPusaka + "','dd/MM/yyyy')";			
											
						db = new Db();
						Statement stmt2 = db.getStatement();
						SQLRenderer r2 = new SQLRenderer();
						r2.add("id_bayaran",id_bayaran);
						r2.add("id_permohonan",idpermohonan);
						r2.add("id_jenisbayaran",id_jenisbayaranCukaiPusaka);
						r2.add("no_resit",txtNomborResitPusaka);
						r2.add("tarikh_bayaran", r2.unquote(TBP));
						r2.add("jumlah_bayaran",txtJumBayaranPusaka);
						r2.add("id_masuk",usid);
						r2.add("tarikh_masuk",r2.unquote("sysdate"));
					
						sql2 = r2.getSQLInsert("Tblppkbayaran");	
						myLogger.info("INSERT TBLPPKBAYARAN PUSAKA = "+sql2);
						stmt2.executeUpdate(sql2);					
						
					}finally {
						if(db != null) db.close();
					}			
			
		}
			
			public static void add_BayaranPusaka17(String usid,String idpermohonan,String id_perbicaraan,String txdTarikhPerintah,
		    		String EDITsocPegawaiPengendali,String flag_jenis_keputusan,String txtCatatanSelesai,String id_jenisbayaranCukaiPusaka,
		    		String txtJumBayaranPusaka,String txtNomborResitPusaka,String txdTarikhBayaranPusaka) throws Exception {
				Db db = null;
				String sql2 = "";
				
					try
					{			
						//long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
						long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
						String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
						String TBP = "to_date('" + txdTarikhBayaranPusaka + "','dd/MM/yyyy')";			
											
						db = new Db();
						Statement stmt2 = db.getStatement();
						SQLRenderer r2 = new SQLRenderer();
						r2.add("id_bayaran",id_bayaran);
						r2.add("id_permohonan",idpermohonan);
						r2.add("id_jenisbayaran",id_jenisbayaranCukaiPusaka);
						r2.add("no_resit",txtNomborResitPusaka);
						r2.add("tarikh_bayaran", r2.unquote(TBP));
						r2.add("jumlah_bayaran",txtJumBayaranPusaka);
						r2.add("id_masuk",usid);
						r2.add("tarikh_masuk",r2.unquote("sysdate"));
					
						sql2 = r2.getSQLInsert("tblppkbayaran");	
						myLogger.info("SQL INSERT TBLPPKBAYARAN PUSAKA = "+sql2);
						stmt2.executeUpdate(sql2);					
						
					}finally {
						if(db != null) db.close();
					}			
			
		}			

		public static void add_BayaranBaitulMal(String usid,String idpermohonan,String id_perbicaraan,String txdTarikhPerintah,
	    		String EDITsocPegawaiPengendali,String flag_jenis_keputusan,String txtCatatanSelesai,String id_jenisbayaranBaitulMal,String txtJumBayaranBaitulmal,
	    		String txtNomborResitBaitulmal,String txdTarikhBayaranBaitulmal) throws Exception {
			
			Db db = null;
			String sql = "";
			
				try
				{			
					//long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
					String TBP = "to_date('" + txdTarikhBayaranBaitulmal + "','dd/MM/yyyy')";		
										
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					//r.add("id_bayaran",id_bayaran);
					r.add("id_permohonan",idpermohonan);
					r.add("id_jenisbayaran",id_jenisbayaranBaitulMal);
					r.add("no_resit",txtNomborResitBaitulmal);
					r.add("tarikh_bayaran", r.unquote(TBP));
					r.add("jumlah_bayaran",txtJumBayaranBaitulmal);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
				
					sql = r.getSQLInsert("Tblppkbayaran");	
					myLogger.info("SQL INSERT TBLPPKBAYARAN BAITULMAL = "+sql);
					stmt.executeUpdate(sql);					
					
				}finally {
					if(db != null) db.close();
				}			
		}
		
		public static void add_BayaranBaitulMal17(String usid,String id_perbicaraan,String idpermohonan,String txdTarikhPerintah,
	    		String EDITsocPegawaiPengendali,String flag_jenis_keputusan,String txtCatatanSelesai,String id_jenisbayaranBaitulMal,
	    		String txtJumBayaranBaitulmal,String txtNomborResitBaitulmal,String txdTarikhBayaranBaitulmal) throws Exception {
			Db db = null;
			String sql2 = "";
			
				try
				{			
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 	
					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
					String TBP = "to_date('" + txdTarikhBayaranBaitulmal + "','dd/MM/yyyy')";		
										
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",id_jenisbayaranBaitulMal);
					r2.add("no_resit",txtNomborResitBaitulmal);
					r2.add("tarikh_bayaran", r2.unquote(TBP));
					r2.add("jumlah_bayaran",txtJumBayaranBaitulmal);
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));
				
					sql2 = r2.getSQLInsert("tblppkbayaran");	
					myLogger.info("SQL INSERT TBLPPKBAYARAN BAITULMAL = "+sql2);
					stmt2.executeUpdate(sql2);					
					
				}finally {
					if(db != null) db.close();
				}			
		}		
		
		public static void edit_status_selesai(String idpermohonan,String usid) throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {	
		    	String id_status = "41";
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idpermohonan);
			    
			    r.add("id_status", 41);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("Tblppkpermohonan");	
			    myLogger.info("EDIT IDSTATUS TBLPPKPERMOHONAN = "+sql);
			    stmt.executeUpdate(sql);
			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}

		public static void edit_statusTblrujsuburusanstatusfail17(String usid,String idpermohonan,String id_perbicaraan,
				String idsuburusanstatusfail,String idFail) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");			

				db = new Db();			
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL				  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("UPDATE TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);
				  stmt2.executeUpdate(sql2);
				  			  
				  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idpermohonan);
					r.add("id_suburusanstatus",410);
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));
					
					sql = r.getSQLInsert("Tblrujsuburusanstatusfail");	
					myLogger.info("INSERT TBLRUJSUBURUSANSTATUSFAIL 17 --> "+sql);
					stmt.executeUpdate(sql);			  				  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		
		public static void edit_statusTblrujsuburusanstatusfail(String usid,String idpermohonan,String id_perbicaraan,
				String idsuburusanstatusfail,String idFail) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");			
				db = new Db();			
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL				  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("UPDATE TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);
				  stmt2.executeUpdate(sql2);
				  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idpermohonan);
					r.add("id_suburusanstatus",373);
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));					

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
					myLogger.info("INSERT TBLRUJSUBURUSANSTATUSFAIL 8 --> "+sql);
					stmt.executeUpdate(sql);			  				  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		
		
		
		
		 public static Hashtable getListStatusID(String idpermohonan) throws Exception {
			 	//myLogger.debug("ajae debug here");
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("fs.id_suburusanstatusfail");
			      r.add("p.id_Permohonan");
			      r.add("fs.aktif");
			      r.add("p.id_status");
			      r.add("fs.id_fail");
			      
			      r.add("fs.id_permohonan",r.unquote("p.id_permohonan"));
			      r.add("fs.aktif",r.unquote("1"));
			      
			      r.add("p.id_Permohonan",idpermohonan);
			      
			      sql = r.getSQLSelect("Tblppkpermohonan p, Tblrujsuburusanstatusfail fs");
			    //  myLogger.info("SQL status id = "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable h = new Hashtable();
			      if (rs.next()) {
				    	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail")==null?"":rs.getString("id_suburusanstatusfail"));
				    	h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				    	h.put("aktif", rs.getString("aktif")==null?"":rs.getString("aktif"));
				    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
				    	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
			      }
			      return h;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }

		public static void setViewBicara(String id) throws Exception {
			Db db = null;
			MaklumatBayaran.clear();
			/*String sql = " SELECT a.id_permohonan,  "+
						 " (SELECT jumlah_bayaran FROM tblppkbayaran where id_jenisbayaran = 24 and id_permohonan = a.id_permohonan ) as bayaran_perintah, "+
						 " (SELECT jumlah_bayaran FROM tblppkbayaran where id_jenisbayaran = 26 and id_permohonan = a.id_permohonan) as bayaran_pusaka, "+
						 " (SELECT jumlah_bayaran FROM tblppkbayaran where id_jenisbayaran = 29 and id_permohonan = a.id_permohonan) as bayaran_baitulmal, "+						 
						 " (SELECT no_resit FROM tblppkbayaran where id_jenisbayaran = 24 and id_permohonan = a.id_permohonan ) as NoResit_perintah, "+
						 " (SELECT no_resit FROM tblppkbayaran where id_jenisbayaran = 26 and id_permohonan = a.id_permohonan) as NoResit_pusaka, "+
						 " (SELECT no_resit FROM tblppkbayaran where id_jenisbayaran = 29 and id_permohonan = a.id_permohonan) as NoResit_baitulmal, "+						 
						 " (SELECT tarikh_bayaran FROM tblppkbayaran where id_jenisbayaran = 24 and id_permohonan = a.id_permohonan ) as TarikhBayaran_perintah, "+
						 " (SELECT tarikh_bayaran FROM tblppkbayaran where id_jenisbayaran = 26 and id_permohonan = a.id_permohonan) as TarikhBayaran_pusaka, "+
						 " (SELECT tarikh_bayaran FROM tblppkbayaran where id_jenisbayaran = 29 and id_permohonan = a.id_permohonan) as TarikhBayaran_baitulmal, "+						 
						 " (SELECT id_bayaran FROM tblppkbayaran where id_jenisbayaran = 24 and id_permohonan = a.id_permohonan) as id_bayaran_perintah, "+
						 " (SELECT id_bayaran FROM tblppkbayaran where id_jenisbayaran = 26 and id_permohonan = a.id_permohonan) as id_bayaran_pusaka, "+
						 " (SELECT id_bayaran FROM tblppkbayaran where id_jenisbayaran = 29 and id_permohonan = a.id_permohonan) as id_bayaran_baitulmal "+
						 " FROM tblppkbayaran a, tblppkpermohonan b "+
						 " WHERE a.id_permohonan = b.id_permohonan and b.id_permohonan = '"+ id +"' "+
						 " GROUP BY a.id_permohonan ";		*/			
			
			String sql = "SELECT   A.ID_PERMOHONAN, "+
				" (SELECT JUMLAH_BAYARAN AS JUMLAH_BAYARAN  FROM (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 24 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS BAYARAN_PERINTAH, "+
				" (SELECT JUMLAH_BAYARAN AS JUMLAH_BAYARAN FROM (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 26 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS BAYARAN_PUSAKA, "+
				" (SELECT JUMLAH_BAYARAN AS JUMLAH_BAYARAN FROM (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 29 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS BAYARAN_BAITULMAL, "+
				" (SELECT NO_RESIT FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 24 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS NORESIT_PERINTAH, "+
				" (SELECT NO_RESIT FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 26 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS NORESIT_PUSAKA, "+
				" (SELECT NO_RESIT FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 29 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS NORESIT_BAITULMAL, "+
				" (SELECT TARIKH_BAYARAN FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 24 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS TARIKHBAYARAN_PERINTAH, "+
				" (SELECT TARIKH_BAYARAN FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 26 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS TARIKHBAYARAN_PUSAKA, "+
				" (SELECT TARIKH_BAYARAN FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 29 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS TARIKHBAYARAN_BAITULMAL, "+
				" (SELECT ID_BAYARAN FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 24 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS ID_BAYARAN_PERINTAH, "+
				" (SELECT ID_BAYARAN FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 26 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS ID_BAYARAN_PUSAKA, "+
				" (SELECT ID_BAYARAN FROM  (SELECT * FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN = 29 AND ID_PERMOHONAN = '"+ id +"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) WHERE ROWNUM = 1 ) AS ID_BAYARAN_BAITULMAL "+
				" FROM TBLPPKBAYARAN A, TBLPPKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_PERMOHONAN = '"+ id +"' "+
				" GROUP BY A.ID_PERMOHONAN ";
			myLogger.info("VIEW BAYARAN :: "+sql);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_permohonan"));
					h.put("bayaran_perintah", rs.getString("bayaran_perintah")==null?"":Double.parseDouble(rs.getString("bayaran_perintah")));
					h.put("bayaran_pusaka", rs.getString("bayaran_pusaka")==null?"":Double.parseDouble(rs.getString("bayaran_pusaka")));
					h.put("bayaran_baitulmal", rs.getString("bayaran_baitulmal")==null?"":Double.parseDouble(rs.getString("bayaran_baitulmal")));
					h.put("NoResit_perintah", rs.getString("NoResit_perintah")==null?"":rs.getString("NoResit_perintah"));
					h.put("NoResit_pusaka", rs.getString("NoResit_pusaka")==null?"":rs.getString("NoResit_pusaka"));
					h.put("NoResit_baitulmal", rs.getString("NoResit_baitulmal")==null?"":rs.getString("NoResit_baitulmal"));
					h.put("TarikhBayaran_perintah", rs.getDate("TarikhBayaran_perintah")==null?"":Format.format(rs.getDate("TarikhBayaran_perintah")));
					h.put("TarikhBayaran_pusaka", rs.getDate("TarikhBayaran_pusaka")==null?"":Format.format(rs.getDate("TarikhBayaran_pusaka")));
					h.put("TarikhBayaran_baitulmal", rs.getDate("TarikhBayaran_baitulmal")==null?"":Format.format(rs.getDate("TarikhBayaran_baitulmal")));
					h.put("id_bayaran_perintah", rs.getString("id_bayaran_perintah")==null?"":rs.getString("id_bayaran_perintah"));
					h.put("id_bayaran_pusaka", rs.getString("id_bayaran_pusaka")==null?"":rs.getString("id_bayaran_pusaka"));
					h.put("id_bayaran_baitulmal", rs.getString("id_bayaran_baitulmal")==null?"":rs.getString("id_bayaran_baitulmal"));
					
					MaklumatBayaran.addElement(h);
					bil++;	
				}
			}finally {
				if(db != null) db.close();
			}	
		}

		public static void setViewPerintah(String id, String id_perbicaraan) throws Exception {
			Db db = null;
			MaklumatPerintah.clear();
			String sql = " SELECT c.id_permohonan, a.id_perbicaraan, b.id_perintah, a.id_keputusanpermohonan, a.id_unitpsk,  "+
						 " b.tarikh_perintah, b.flag_jenis_keputusan, b.catatan,b.check_kiv,b.date_kiv,b.catatan_kiv "+					 
						 " FROM Tblppkperbicaraan a, Tblppkperintah b, Tblppkkeputusanpermohonan c "+
						 " WHERE a.id_perbicaraan = b.id_perbicaraan AND c.id_keputusanpermohonan = a.id_keputusanpermohonan "+
						 " AND c.id_permohonan = "+ id +" AND a.id_perbicaraan = '"+ id_perbicaraan +"' ";						
			
			myLogger.info("SQL SETVIEWPERINTAH 12 = "+sql);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				//int bil = 1;
				String catatan = "";
				while (rs.next()){
					h = new Hashtable();
					//h.put("bil", bil);
//					catatan = rs.getString("catatan")==null?"-":rs.getString("catatan");
//					catatan = catatan.replaceAll("<BR />","\n");
//					catatan = catatan.replaceAll("<br />","\n");
//					//catatan = catatan.replaceAll("\\<.*?\\>", "");
					
					h.put("id_permohonan", rs.getString("id_permohonan"));
					h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
					h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
					h.put("id_keputusanpermohonan", rs.getString("id_keputusanpermohonan")==null?"-":rs.getString("id_keputusanpermohonan"));
					h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
					h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
					h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));					
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					
					h.put("check_kiv", rs.getString("check_kiv")==null?"":rs.getString("check_kiv"));
					h.put("date_kiv", rs.getDate("date_kiv")==null?"":Format.format(rs.getDate("date_kiv")));
					h.put("catatan_kiv", rs.getString("catatan_kiv")==null?"":rs.getString("catatan_kiv"));
					
					MaklumatPerintah.addElement(h);
					//bil++;	
				}
			}finally {
				if(db != null) db.close();
			}	
		}

		public static Vector setInfoPerintah(String idpermohonan, String id_perbicaraan) throws Exception {
			
		    Db db = null;
		    listPerintah.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("c.id_permohonan");
		      r.add("a.id_perbicaraan");
		      r.add("b.id_perintah");
		      r.add("a.id_keputusanpermohonan");
		      //r.add("a.id_unitpsk");//* a.id_unitpsk --> FROM TBLPPKPERBICARAAN
		      r.add("b.id_unitpsk");//* b.id_unitpsk --> FROM TBLPPKPERINTAH
		      r.add("b.tarikh_perintah");
		      r.add("b.flag_jenis_keputusan");
		      r.add("b.catatan");	
		      r.add("b.flag_tangguh");
		      r.add("b.flag_batal");
		      r.add("b.sebab_batal");
		      r.add("b.sebab_tangguh");
		      r.add("b.keputusan_mahkamah");
		      //r.add("b.id_pejabatmahkamah");
		      
		      
		      r.add("b.check_kiv");
		      r.add("b.date_kiv");
		      r.add("b.catatan_kiv");

		      r.add("a.id_perbicaraan",r.unquote("b.id_perbicaraan"));
		      r.add("c.id_keputusanpermohonan",r.unquote("a.id_keputusanpermohonan"));

		      r.add("c.id_permohonan",idpermohonan);
		      r.add("a.id_perbicaraan", id_perbicaraan, "=");

		      sql = r.getSQLSelect("Tblppkperbicaraan a, Tblppkperintah b, Tblppkkeputusanpermohonan c ");
		      myLogger.info("SQL 12344343 = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
		    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
		    	h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
		    	h.put("id_keputusanpermohonan", rs.getString("id_keputusanpermohonan")==null?"":rs.getString("id_keputusanpermohonan"));
		    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
		    	h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
		    	h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
		    	h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
		    	h.put("flag_tangguh", rs.getString("flag_tangguh")==null?"":rs.getString("flag_tangguh"));
		    	h.put("flag_batal", rs.getString("flag_batal")==null?"":rs.getString("flag_batal"));
		    	h.put("sebab_batal", rs.getString("sebab_batal")==null?"":rs.getString("sebab_batal"));
		    	h.put("sebab_tangguh", rs.getString("sebab_tangguh")==null?"":rs.getString("sebab_tangguh"));
		    	h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
		    	//h.put("id_pejabatmahkamah", rs.getString("id_pejabatmahkamah")==null?"":rs.getString("id_pejabatmahkamah"));
		    	
		    	h.put("check_kiv", rs.getString("check_kiv")==null?"":rs.getString("check_kiv"));
		    	h.put("date_kiv", rs.getDate("date_kiv")==null?"":Format.format(rs.getDate("date_kiv")));
		    	h.put("catatan_kiv", rs.getString("catatan_kiv")==null?"":rs.getString("catatan_kiv"));
		    	
		    	
		    	listPerintah.addElement(h);
		    	bil++;
		      }
		      return listPerintah;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

		/*public static void updateMaklumatSelesai(String usid,String idpermohonan,String id_perintah,String id_perbicaraan,
	    		String id_bayaran_perintah,String id_bayaran_pusaka,String id_bayaran_baitulmal,String EDITflag_jenis_keputusan,String txtJumBayaranEDIT,
	    		String txtNomborResitPerintahEDIT,String txdTarikhBayaranPerintahEDIT,String txtJumBayaranPusakaEDIT,String txtNomborResitPusakaEDIT,
	    		String txdTarikhBayaranPusakaEDIT,String txtJumBayaranBaitulmalEDIT,String txtNomborResitBaitulmalEDIT,String txdTarikhBayaranBaitulmalEDIT,
	    		String txtCatatanSelesaiEDIT,String EDITsocPegawaiPengendali,String txdTarikhPerintahEDIT,String check_kiv,String date_kiv,String catatan_kiv) throws Exception {
		    
			Db db = null;
		    String sql = "";
		    String sql2 = "";
		    String sql3 = "";
		    try
		    { 
			  String TPERINTAH = "to_date('" + txdTarikhBayaranPerintahEDIT + "','dd/MM/yyyy')";
			  String TPUSAKA = "to_date('" + txdTarikhBayaranPusakaEDIT + "','dd/MM/yyyy')";
			  String TBAITULMAL = "to_date('" + txdTarikhBayaranBaitulmalEDIT + "','dd/MM/yyyy')";
			  String TPERINTAHBICARA = "to_date('" + txdTarikhPerintahEDIT + "','dd/MM/yyyy')";	
			  String T_date_kiv = "to_date('" + date_kiv + "','dd/MM/yyyy')";
		    
		      db = new Db();	
		      
//				Statement stmt3 = db.getStatement();
//				SQLRenderer r3 = new SQLRenderer();
//				r3.update("id_perbicaraan", id_perbicaraan);
//				
//				r3.add("tarikh_bicara",r3.unquote(TPERINTAHBICARA));
//				r3.add("id_kemaskini",usid);
//				r3.add("tarikh_kemaskini",r3.unquote("sysdate"));
//				
//				sql3 = r3.getSQLUpdate("tblppkperbicaraan");
//				myLogger.info("SQL TBLPPKPERBICARAAN = "+sql3);
//				stmt3.executeUpdate(sql3);

		      
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  r2.update("id_perintah", id_perintah);
			  r2.update("id_perbicaraan", id_perbicaraan);			  
			  r2.add("tarikh_perintah", r2.unquote(TPERINTAHBICARA));		
			  r2.add("id_unitpsk", EDITsocPegawaiPengendali);
			  r2.add("flag_jenis_keputusan", 0);	
			  r2.add("catatan", txtCatatanSelesaiEDIT);	
			  r2.add("id_kemaskini", usid);	
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));			  
			  sql2 = r2.getSQLUpdate("Tblppkperintah");
			  myLogger.info("UPDATE MAKLUMATSELESAI TBLPPKPERINTAH == "+sql2);
		      stmt2.executeUpdate(sql2);
		      
		      
		      Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  Connection conn = db.getConnection();
		      PreparedStatement ps = conn.prepareStatement("UPDATE Tblppkperintah SET id_unitpsk = ?, " +
		      		"flag_jenis_keputusan = ?, catatan = ?, ID_KEMASKINI = ?," +
		      		" TARIKH_KEMASKINI = sysdate, tarikh_perintah = "+r2.unquote(TPERINTAHBICARA)+"," +
		      				" date_kiv = "+r2.unquote(T_date_kiv)+",check_kiv = ?,catatan_kiv = ? " +
		      				" WHERE id_perintah = ? AND id_perbicaraan = ? ");
		        ps.setString(1, EDITsocPegawaiPengendali);
		        ps.setString(2, "0");
		        ps.setString(3, txtCatatanSelesaiEDIT);
		        ps.setString(4, usid);
		        ps.setString(5, check_kiv);		       					        
		        ps.setString(6, catatan_kiv);		        
		        ps.setString(7, id_perintah);
		        ps.setString(8, id_perbicaraan);
		        
		        ps.executeUpdate();	
		        
		        Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  r2.update("id_perintah", id_perintah);
				  r2.update("id_perbicaraan", id_perbicaraan);			  
				  r2.add("tarikh_perintah", r2.unquote(TPERINTAHBICARA));	
				  r2.add("date_kiv", r2.unquote(T_date_kiv));
				  r2.add("check_kiv", check_kiv);
				  r2.add("catatan_kiv", catatan_kiv);
				  r2.add("flag_jenis_keputusan", "0");	
				  r2.add("catatan", txtCatatanSelesaiEDIT);	
				  r2.add("id_kemaskini", usid);	
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));	
				  r2.add("ID_UNITPSK", EDITsocPegawaiPengendali);
				  sql2 = r2.getSQLUpdate("Tblppkperintah");
				  myLogger.info("UPDATE MAKLUMATSELESAI TBLPPKPERINTAH == "+sql2);
			      stmt2.executeUpdate(sql2);
		       
			  System.out.println("check_kiv==="+check_kiv);
		     id_bayaran_perintah 
		    if ( id_bayaran_perintah != "0" ){
	  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_perintah);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranEDIT));
				  r.add("no_resit", txtNomborResitPerintahEDIT);
				  r.add("tarikh_bayaran", r.unquote(TPERINTAH));	
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran")
				  +"and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
				  		"WHERE ID_JENISBAYARAN = 24 AND ID_PERMOHONAN = '"+idpermohonan+"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
				  		"WHERE ROWNUM = 1 )";
				  myLogger.info("ID BAYARAN PERINTAH == "+sql);
			      stmt.executeUpdate(sql);		  
		    }
		    
		     id_bayaran_pusaka 
		    if ( id_bayaran_pusaka != "0" ){
		    			  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_pusaka);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranPusakaEDIT));
				  r.add("no_resit", txtNomborResitPusakaEDIT);
				  r.add("tarikh_bayaran", r.unquote(TPUSAKA));
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran") + "and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
			  		"WHERE ID_JENISBAYARAN = 26 AND ID_PERMOHONAN = '"+idpermohonan+"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
			  		"WHERE ROWNUM = 1 )";
				  myLogger.info("ID BAYARAN PUSAKA == "+sql);
			      stmt.executeUpdate(sql);	  
		    }
		    
		     id_bayaran_baitulmal 
		    if ( id_bayaran_baitulmal != "0" ){
		    			  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_baitulmal);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranBaitulmalEDIT));
				  r.add("no_resit", txtNomborResitBaitulmalEDIT);
				  r.add("tarikh_bayaran", r.unquote(TBAITULMAL));
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran") + "and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
			  		"WHERE ID_JENISBAYARAN = 29 AND ID_PERMOHONAN = '"+idpermohonan+"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
			  		"WHERE ROWNUM = 1 )";
					  
				  myLogger.info("ID BAYARAN BAITULMAL == "+sql);
			      stmt.executeUpdate(sql);	  
		    }	
		    
		    if(check_kiv.equals("1")){//aishahlatip simpan history KIV
		    	int idKIVhistory  = checkExistKIV( id_perintah ,id_perbicaraan);
		    	
		    	if(idKIVhistory == 0){
		    		
		    		 add_kivHistory( Long.parseLong(id_perintah), id_perbicaraan, usid,
							 check_kiv, date_kiv, catatan_kiv);
		    		
		    	}
		    	
		    }

		    }finally {
		      if (db != null) db.close();
		    }
		  }//close updateMaklumatPermohonan	
*/		
		public static void updateMaklumatSelesai(String usid,String idpermohonan,String id_perintah,String id_perbicaraan,
	    		String id_bayaran_perintah,String id_bayaran_pusaka,String id_bayaran_baitulmal,String EDITflag_jenis_keputusan,String txtJumBayaranEDIT,
	    		String txtNomborResitPerintahEDIT,String txdTarikhBayaranPerintahEDIT,String txtJumBayaranPusakaEDIT,String txtNomborResitPusakaEDIT,
	    		String txdTarikhBayaranPusakaEDIT,String txtJumBayaranBaitulmalEDIT,String txtNomborResitBaitulmalEDIT,String txdTarikhBayaranBaitulmalEDIT,
	    		String txtCatatanSelesaiEDIT,String EDITsocPegawaiPengendali,String txdTarikhPerintahEDIT,String check_kiv,String date_kiv,String catatan_kiv, String txtJustifikasiPegawai) throws Exception {
		    
			Db db = null;
		    String sql = "";
		    String sql2 = "";
		    String sql3 = "";
		    try
		    { 
			  String TPERINTAH = "to_date('" + txdTarikhBayaranPerintahEDIT + "','dd/MM/yyyy')";
			  String TPUSAKA = "to_date('" + txdTarikhBayaranPusakaEDIT + "','dd/MM/yyyy')";
			  String TBAITULMAL = "to_date('" + txdTarikhBayaranBaitulmalEDIT + "','dd/MM/yyyy')";
			  String TPERINTAHBICARA = "to_date('" + txdTarikhPerintahEDIT + "','dd/MM/yyyy')";	
			  String T_date_kiv = "to_date('" + date_kiv + "','dd/MM/yyyy')";
		    
		      db = new Db();	
		      
//				Statement stmt3 = db.getStatement();
//				SQLRenderer r3 = new SQLRenderer();
//				r3.update("id_perbicaraan", id_perbicaraan);
//				
//				r3.add("tarikh_bicara",r3.unquote(TPERINTAHBICARA));
//				r3.add("id_kemaskini",usid);
//				r3.add("tarikh_kemaskini",r3.unquote("sysdate"));
//				
//				sql3 = r3.getSQLUpdate("tblppkperbicaraan");
//				System.out.println("SQL TBLPPKPERBICARAAN = "+sql3);
//				stmt3.executeUpdate(sql3);

		      /*
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  r2.update("id_perintah", id_perintah);
			  r2.update("id_perbicaraan", id_perbicaraan);			  
			  r2.add("tarikh_perintah", r2.unquote(TPERINTAHBICARA));		
			  r2.add("id_unitpsk", EDITsocPegawaiPengendali);
			  r2.add("flag_jenis_keputusan", 0);	
			  r2.add("catatan", txtCatatanSelesaiEDIT);	
			  r2.add("id_kemaskini", usid);	
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));			  
			  sql2 = r2.getSQLUpdate("Tblppkperintah");
			  System.out.println("UPDATE MAKLUMATSELESAI TBLPPKPERINTAH == "+sql2);
		      stmt2.executeUpdate(sql2);
		      */
		      /*
		      Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  Connection conn = db.getConnection();
		      PreparedStatement ps = conn.prepareStatement("UPDATE Tblppkperintah SET id_unitpsk = ?, " +
		      		"flag_jenis_keputusan = ?, catatan = ?, ID_KEMASKINI = ?," +
		      		" TARIKH_KEMASKINI = sysdate, tarikh_perintah = "+r2.unquote(TPERINTAHBICARA)+"," +
		      				" date_kiv = "+r2.unquote(T_date_kiv)+",check_kiv = ?,catatan_kiv = ? " +
		      				" WHERE id_perintah = ? AND id_perbicaraan = ? ");
		        ps.setString(1, EDITsocPegawaiPengendali);
		        ps.setString(2, "0");
		        ps.setString(3, txtCatatanSelesaiEDIT);
		        ps.setString(4, usid);
		        ps.setString(5, check_kiv);		       					        
		        ps.setString(6, catatan_kiv);		        
		        ps.setString(7, id_perintah);
		        ps.setString(8, id_perbicaraan);
		        
		        ps.executeUpdate();	
		        */
		        Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  r2.update("id_perintah", id_perintah);
				  r2.update("id_perbicaraan", id_perbicaraan);			  
				  r2.add("tarikh_perintah", r2.unquote(TPERINTAHBICARA));	
				  r2.add("date_kiv", r2.unquote(T_date_kiv));
				  r2.add("check_kiv", check_kiv);
				  r2.add("catatan_kiv", catatan_kiv);
				  r2.add("flag_jenis_keputusan", "0");	
				  r2.add("catatan", txtCatatanSelesaiEDIT);	
				  r2.add("id_kemaskini", usid);	
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));	
				  r2.add("ID_UNITPSK", EDITsocPegawaiPengendali);
				  r2.add("JUSTIFIKASI_PEGAWAI",txtJustifikasiPegawai);
				  sql2 = r2.getSQLUpdate("Tblppkperintah");
				  System.out.println("UPDATE MAKLUMATSELESAI TBLPPKPERINTAH == "+sql2);
			      stmt2.executeUpdate(sql2);
		       
			  System.out.println("check_kiv==="+check_kiv);
		    /* id_bayaran_perintah */
		    if ( id_bayaran_perintah != "0" ){
	  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_perintah);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranEDIT));
				  r.add("no_resit", txtNomborResitPerintahEDIT);
				  r.add("tarikh_bayaran", r.unquote(TPERINTAH));	
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran")
				  +"and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
				  		"WHERE ID_JENISBAYARAN = 24 AND ID_PERMOHONAN = '"+idpermohonan+"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
				  		"WHERE ROWNUM = 1 )";
				  System.out.println("ID BAYARAN PERINTAH == "+sql);
			      stmt.executeUpdate(sql);		  
		    }
		    
		    /* id_bayaran_pusaka */
		    if ( id_bayaran_pusaka != "0" ){
		    			  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_pusaka);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranPusakaEDIT));
				  r.add("no_resit", txtNomborResitPusakaEDIT);
				  r.add("tarikh_bayaran", r.unquote(TPUSAKA));
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran") + "and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
			  		"WHERE ID_JENISBAYARAN = 26 AND ID_PERMOHONAN = '"+idpermohonan+"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
			  		"WHERE ROWNUM = 1 )";
				  System.out.println("ID BAYARAN PUSAKA == "+sql);
			      stmt.executeUpdate(sql);	  
		    }
		    
		    /* id_bayaran_baitulmal */
		    if ( id_bayaran_baitulmal != "0" ){
		    			  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_baitulmal);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranBaitulmalEDIT));
				  r.add("no_resit", txtNomborResitBaitulmalEDIT);
				  r.add("tarikh_bayaran", r.unquote(TBAITULMAL));
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran") + "and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
			  		"WHERE ID_JENISBAYARAN = 29 AND ID_PERMOHONAN = '"+idpermohonan+"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
			  		"WHERE ROWNUM = 1 )";
					  
				  System.out.println("ID BAYARAN BAITULMAL == "+sql);
			      stmt.executeUpdate(sql);	  
		    }	
		    
		    if(check_kiv.equals("1")){//aishahlatip simpan history KIV
		    	int idKIVhistory  = checkExistKIV( id_perintah ,id_perbicaraan);
		    	
		    	if(idKIVhistory == 0){
		    		
		    		 add_kivHistory( Long.parseLong(id_perintah), id_perbicaraan, usid,
							 check_kiv, date_kiv, catatan_kiv);
		    		
		    	}
		    	
		    }

		    }finally {
		      if (db != null) db.close();
		    }
		  }//close updateMaklumatPermohonan	
		
		public static void updateMaklumatSelesai17(String usid,String idpermohonan,String id_perintah,String id_perbicaraan,
				String id_bayaran_perintah,String id_bayaran_pusaka,String id_bayaran_baitulmal,String EDITflag_jenis_keputusan,String txtJumBayaranEDIT,
	    		String txtNomborResitPerintahEDIT,String txdTarikhBayaranPerintahEDIT,String txtJumBayaranPusakaEDIT,String txtNomborResitPusakaEDIT,
	    		String txdTarikhBayaranPusakaEDIT,String txtJumBayaranBaitulmalEDIT,String txtNomborResitBaitulmalEDIT,String txdTarikhBayaranBaitulmalEDIT,
	    		String txtCatatanSelesaiEDIT,String EDITsocPegawaiPengendali,String txdTarikhPerintahEDIT,String check_kiv,String date_kiv,String catatan_kiv) throws Exception {
		    
			Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    { 
			  String TPERINTAH = "to_date('" + txdTarikhBayaranPerintahEDIT + "','dd/MM/yyyy')";
			  String TPUSAKA = "to_date('" + txdTarikhBayaranPusakaEDIT + "','dd/MM/yyyy')";
			  String TBAITULMAL = "to_date('" + txdTarikhBayaranBaitulmalEDIT + "','dd/MM/yyyy')";
			  String TPERINTAHBICARA = "to_date('" + txdTarikhPerintahEDIT + "','dd/MM/yyyy')";	
			  String T_date_kiv = "to_date('" + date_kiv + "','dd/MM/yyyy')";
			  
			  db = new Db();		  
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  Connection conn = db.getConnection();
		      PreparedStatement ps = conn.prepareStatement("UPDATE Tblppkperintah SET id_unitpsk = ?, " +
		      		"flag_jenis_keputusan = ?, catatan = ?, ID_KEMASKINI = ?," +
		      		" TARIKH_KEMASKINI = sysdate, tarikh_perintah = "+r2.unquote(TPERINTAHBICARA)+"," +
		      		" date_kiv = "+r2.unquote(T_date_kiv)+",check_kiv = ?,catatan_kiv = ? " +
		      				" WHERE id_perintah = ? AND id_perbicaraan = ? ");
		        ps.setString(1, EDITsocPegawaiPengendali);
		        ps.setString(2, "0");
		        ps.setString(3, txtCatatanSelesaiEDIT);
		        ps.setString(4, usid);		        
		        ps.setString(5, check_kiv);		       					        
		        ps.setString(6, catatan_kiv);		        
		        ps.setString(7, id_perintah);
		        ps.setString(8, id_perbicaraan);
		        ps.executeUpdate();
		    
			  /*
		      db = new Db();		  
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  r2.update("id_perintah", id_perintah);
			  r2.update("id_perbicaraan", id_perbicaraan);			  
			  r2.add("tarikh_perintah", r2.unquote(TPERINTAHBICARA));		
			  r2.add("id_unitpsk", EDITsocPegawaiPengendali);
			  r2.add("flag_jenis_keputusan", 0);	
			  r2.add("catatan", txtCatatanSelesaiEDIT);	
			  r2.add("id_kemaskini", usid);	
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));			  
			  sql2 = r2.getSQLUpdate("TBLPPKPERINTAH");
		      stmt2.executeUpdate(sql2);*/
			  
			  
			  
			  
			  
		    /* id_bayaran_perintah */
		    if ( id_bayaran_perintah != "0" ){
	  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_perintah);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranEDIT));
				  r.add("no_resit", txtNomborResitPerintahEDIT);
				  r.add("tarikh_bayaran", r.unquote(TPERINTAH));	
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran")+ "and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
			  		"WHERE ID_JENISBAYARAN = 24 AND ID_PERMOHONAN = '"+idpermohonan+"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
			  		"WHERE ROWNUM = 1 )";
				  myLogger.info("ID BAYARAN PERINTAH == "+sql);
			      stmt.executeUpdate(sql);		  
		    }
		    
		    /* id_bayaran_pusaka */
		    if ( id_bayaran_pusaka != "0" ){
		    			  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_pusaka);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranPusakaEDIT));
				  r.add("no_resit", txtNomborResitPusakaEDIT);
				  r.add("tarikh_bayaran", r.unquote(TPUSAKA));
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran")+ "and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
			  		"WHERE ID_JENISBAYARAN = 26 AND ID_PERMOHONAN = '"+idpermohonan+"' " +
			  				"ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
			  		"WHERE ROWNUM = 1 )";
				  myLogger.info("ID BAYARAN PUSAKA == "+sql);
			      stmt.executeUpdate(sql);	  
		    }
		    
		    /* id_bayaran_baitulmal */
		    if ( id_bayaran_baitulmal != "0" ){
		    			  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_permohonan", idpermohonan);
				  r.update("id_bayaran", id_bayaran_baitulmal);
				  
				  r.add("jumlah_bayaran", Utils.RemoveComma(txtJumBayaranBaitulmalEDIT));
				  r.add("no_resit", txtNomborResitBaitulmalEDIT);
				  r.add("tarikh_bayaran", r.unquote(TBAITULMAL));
				  r.add("id_kemaskini", usid);	
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkbayaran")+ "and id_bayaran in (SELECT id_bayaran FROM (SELECT * FROM TBLPPKBAYARAN " +
			  		"WHERE ID_JENISBAYARAN = 29 AND ID_PERMOHONAN = '"+idpermohonan+"' ORDER BY TARIKH_BAYARAN,ID_BAYARAN ASC ) " +
			  		"WHERE ROWNUM = 1 )";
				  myLogger.info("ID BAYARAN BAITULMAL == "+sql);
			      stmt.executeUpdate(sql);	  
		    }		    

		    }finally {
		      if (db != null) db.close();
		    }
		  }//close updateMaklumatPermohonan			

		public static void add_BayaranPusakaEDIT(String usid,String idpermohonan,String txtJumBayaranPusakaEDIT,
	    		String txtNomborResitPusakaEDIT,String txdTarikhBayaranPusakaEDIT) throws Exception {
			Db db = null;
			String sql2 = "";
			
				try
				{			
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					String TBP = "to_date('" + txdTarikhBayaranPusakaEDIT + "','dd/MM/yyyy')";		
					
					//--------------------------------- TBLPPKBAYARAN -----------------------------------------
										
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",26);
					r2.add("no_resit",txtNomborResitPusakaEDIT);
					r2.add("tarikh_bayaran", r2.unquote(TBP));
					r2.add("jumlah_bayaran",Utils.RemoveComma(txtJumBayaranPusakaEDIT));
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));					
				
					sql2 = r2.getSQLInsert("Tblppkbayaran");		
					stmt2.executeUpdate(sql2);
					
				}finally {
					if(db != null) db.close();
				}			
		}


		public static void add_BayaranBaitulMalEDIT(String usid,String idpermohonan,
				String txtJumBayaranBaitulmalEDIT,String txtNomborResitBaitulmalEDIT,String txdTarikhBayaranBaitulmalEDIT) throws Exception {
			
			Db db = null;
			String sql2 = "";
				try
				{			
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					String TBB = "to_date('" + txdTarikhBayaranBaitulmalEDIT + "','dd/MM/yyyy')";			
					
					//--------------------------------- TBLPPKBAYARAN -----------------------------------------
										
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",29);
					r2.add("no_resit",txtNomborResitBaitulmalEDIT);
					r2.add("tarikh_bayaran", r2.unquote(TBB));
					r2.add("jumlah_bayaran",Utils.RemoveComma(txtJumBayaranBaitulmalEDIT));
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));					
				
					sql2 = r2.getSQLInsert("Tblppkbayaran");		
					stmt2.executeUpdate(sql2);
					
				}finally {
					if(db != null) db.close();
				}			
		}	
		
	public static void setListNegeri() throws Exception {
		Db db = null;
		listNegeri.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("n.id_negeri");
			r.add("n.nama_negeri");
			
			r.add("p.id_negeri",r.unquote("n.id_negeri"));
			
			sql = r.getSQLSelect("tblrujpejabat p, tblrujnegeri n");
			sql = sql + " and p.id_jenispejabat = 8 group by n.id_negeri, n.nama_negeri";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				listNegeri.addElement(h);
			}
		}finally {
			if(db != null) db.close();
		}
	}

	public static void setListDaerah(int idnegeri) throws Exception {
		Db db = null;
		listDaerah.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("d.id_daerah");
			r.add("d.nama_daerah");
			
			r.add("p.id_negeri",r.unquote("n.id_negeri"));
			r.add("p.id_daerah",r.unquote("d.id_daerah"));
			
			sql = r.getSQLSelect("tblrujpejabat p, tblrujnegeri n, tblrujdaerah d");
			sql = sql + " and p.id_jenispejabat = 8 and p.id_negeri = "+ idnegeri +" group by d.id_daerah, d.nama_daerah";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("namaDaerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
				listDaerah.addElement(h);
			}
		}finally {
			if(db != null) db.close();
		}
	}

	private static Vector semakMahkamah = new Vector();
	
	public static Vector getSemakMahkamah(){
		return semakMahkamah;
	}
	
	public static void semakAlamatMahkamah(int idDaerah, int idNegeri) throws Exception {
		Db db = null;
		semakMahkamah.clear();
		String sql = "Select id_pejabat,nama_pejabat,alamat1,alamat2,alamat3,poskod,no_tel,no_fax from tblrujpejabat where id_jenispejabat = 8 and id_daerah = "+ idDaerah +" and id_negeri = "+ idNegeri +"";
		//myLogger.info("SQL semakAlamatMahkamah = "+sql);
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();		
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("id_pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
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
	
	//*Menunggu Keputusan Rujukan Mahkamah Tinggi
	public static void insertDataMahkamah(String usid,String id_perbicaraan,String id_pejabat,String id_unitpsk,
    		String txdTarikhPerintahAdd,String jenisPerintah) throws Exception	{
	    
		Db db = null;
	    String sql = "";
	    try
	    {
			long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
			//long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");
			String TR = "to_date('" + txdTarikhPerintahAdd + "','dd/MM/yyyy')";
			String flag_jenis_keputusan = "2";							//* ID UTK BATAL
			/*
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();			 
			 Connection conn = db.getConnection();					
			 PreparedStatement ps = conn.prepareStatement("insert into tblppkperintah " +
	                    "(id_perintah,id_perbicaraan,tarikh_perintah,flag_batal,flag_jenis_keputusan," +
	                    "id_jenisperintah,id_unitpsk,jenis_keluar_perintah,id_pejabatmahkamah," +
	                    " ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI) " +
	                    "values(?,?,"+r.unquote(TR)+",?,?,?,?,?,?,?,?,sysdate,sysdate)");
				        ps.setLong(1, id_perintah);
				        ps.setString(2, id_perbicaraan);
				        ps.setString(3, "1");
				        ps.setString(4, "2");
				        ps.setString(5, "6");	
				        ps.setString(6, id_unitpsk);
				        ps.setString(7, jenisPerintah);
				        ps.setString(8, id_pejabat);
				        ps.setString(9, usid);
				        ps.setString(10, usid);	
				        ps.executeUpdate();*/
			
		    //INSERT TBLPPKPERINTAH
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_perintah",id_perintah);
			r.add("id_perbicaraan",id_perbicaraan);
			r.add("tarikh_perintah", r.unquote(TR));
			r.add("flag_batal",1);										/* ID UTK Menunggu Keputusan Rujukan Mahkamah Tinggi */
			r.add("flag_jenis_keputusan",2);							/* ID utk BATAL */
			r.add("id_jenisperintah",6);								/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
			r.add("id_unitpsk",id_unitpsk);
			r.add("jenis_keluar_perintah",jenisPerintah);
			r.add("id_pejabatmahkamah",id_pejabat);
			r.add("id_masuk",usid);
			r.add("tarikh_masuk",r.unquote("sysdate"));
			sql = r.getSQLInsert("Tblppkperintah");		
			System.out.println(":: INSERT TBLPPKPERINTAH :: = "+sql);
			stmt.executeUpdate(sql);	
			System.out.println(":: lepas INSERT TBLPPKPERINTAH :: = ");
			
			
			
    	} finally {
    		if (db != null) db.close();
    	}
}
	
	//*Menunggu Keputusan Rujukan Mahkamah Tinggi
	public static void insertDataMahkamah_updateTblppkperintah(String usid,String id_perbicaraan,String id_perintah,
    		String id_pejabat,String id_unitpsk,String txdTarikhPerintahAdd,String jenisPerintah) throws Exception	{
	    
		Db db = null;
	    String sql = "";
	    try
	    {
			String TR = "to_date('" + txdTarikhPerintahAdd + "','dd/MM/yyyy')";

			//UPDATE TBLPPKPERINTAH
			
			
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		   /*
			  Connection conn = db.getConnection();
		      PreparedStatement ps = conn.prepareStatement("UPDATE Tblppkperintah " +
		      		" SET tarikh_perintah = "+r.unquote(TR)+", " +
		      		"flag_batal = ?, flag_jenis_keputusan = ?,id_jenisperintah = ?, " +
		      		"id_unitpsk = ?,jenis_keluar_perintah = ?,id_pejabatmahkamah = ?,"+
		      		"ID_KEMASKINI = ?,TARIKH_KEMASKINI = sysdate "+
		      				" WHERE id_perintah = ? AND id_perbicaraan = ? ");
		        ps.setString(1, "1");
		        ps.setString(2, "2");
		        ps.setString(3, "6");
		        ps.setString(4, id_unitpsk);
		        ps.setString(5, jenisPerintah);
		        ps.setString(6, id_pejabat);
		        ps.setString(7, usid);	
		        ps.setString(8, id_perintah);		       					        
		        ps.setString(9, id_perbicaraan);		        
		        ps.executeUpdate();	
		    */
		    
		    
		    
		    r.update("id_perintah", id_perintah);
		    r.update("id_perbicaraan", id_perbicaraan);		    
			r.add("tarikh_perintah", r.unquote(TR));
			r.add("flag_batal",1);								/* ID utk Menunggu Keputusan Rujukan Mahkamah Tinggi */
			r.add("flag_jenis_keputusan",2);					/* ID utk BATAL */
			r.add("id_jenisperintah",6);						/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
			r.add("id_unitpsk",id_unitpsk);
			r.add("jenis_keluar_perintah",jenisPerintah);
			r.add("id_pejabatmahkamah",id_pejabat);
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("tblppkperintah");
		    myLogger.info("UPDATE TBLPPKPERINTAH = "+sql);
		    stmt.executeUpdate(sql);
		      
			
    	} finally {
    		if (db != null) db.close();
    	}
}	


	public static Vector setPerintahTangguhMufti(String id_perbicaraan) throws Exception  {
		
	    Db db = null;
	    listPerintahTangguhMufti.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("a.id_perintah");   
	      r.add("a.id_unitpsk");          
	      
	      r.add("a.id_unitpsk",r.unquote("b.id_unitpsk"));

	      r.add("a.id_perbicaraan", id_perbicaraan, "=");

	      sql = r.getSQLSelect("Tblppkperintah a, Tblppkrujunit b");
	      myLogger.info("SQL SETPERINTAHTANGGUH MUFTI = "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	   
	      Hashtable h;

	      while (rs.next()) {
	    	h = new Hashtable();
	        h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
	    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
	    	
	    	listPerintahTangguhMufti.addElement(h);
	      }
	      return listPerintahTangguhMufti;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}
	
	
	
	
	public static Vector setPerintahTangguh(String id_perbicaraan) throws Exception  {
		
	    Db db = null;
	    listPerintahTangguh.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("a.id_perintah");
	      r.add("a.id_perbicaraan"); 
	      r.add("a.flag_jenis_keputusan");     
	      r.add("a.id_unitpsk");    
	      r.add("b.nama_pegawai");  
	      r.add("a.jenis_keluar_perintah");  
	      r.add("a.tarikh_perintah");
	      r.add("a.id_pejabatmahkamah");
	      r.add("c.id_negeri");
	      r.add("d.nama_negeri");
	      r.add("c.id_daerah");
	      r.add("e.nama_daerah");
	      r.add("c.nama_pejabat");
	      r.add("c.alamat1");
	      r.add("c.alamat2");
	      r.add("c.alamat3");
	      r.add("c.poskod");
	      r.add("c.no_tel");
	      r.add("c.no_fax");	      
	      
	      r.add("a.id_unitpsk",r.unquote("b.id_unitpsk"));
	      r.add("a.id_pejabatmahkamah",r.unquote("c.id_pejabat"));
	      r.add("d.id_negeri",r.unquote("c.id_negeri"));
	      r.add("e.id_daerah",r.unquote("c.id_daerah"));

	      r.add("a.id_perbicaraan", id_perbicaraan, "=");

	      sql = r.getSQLSelect("Tblppkperintah a, Tblppkrujunit b, Tblrujpejabat c, Tblrujnegeri d, Tblrujdaerah e");
	      myLogger.info("SQL SETPERINTAHTANGGUH = "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	   
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
	    	h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
	    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
	    	h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
	    	h.put("jenis_keluar_perintah", rs.getString("jenis_keluar_perintah")==null?"":rs.getString("jenis_keluar_perintah"));
	    	h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
	    	h.put("id_pejabatmahkamah", rs.getString("id_pejabatmahkamah")==null?"":rs.getString("id_pejabatmahkamah"));
	    	h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
	    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
	    	h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
	    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
	    	h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
	    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
	    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	    	h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
	    	h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
	    	
	    	listPerintahTangguh.addElement(h);
	    	bil++;
	      }
	      return listPerintahTangguh;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	}

	public static void edit_status_batalMT(String idpermohonan,String usid) throws Exception {
		
	    Db db = null;
	    String sql = "";	    
	    try
	    {		
	    	String id_status = "47"; /* STATUS: BATAL */
			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", idpermohonan);
		    
		    r.add("id_status", 47);
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));

		    sql = r.getSQLUpdate("tblppkpermohonan");
		    myLogger.info("SQL BATAL = "+sql);
		    stmt.executeUpdate(sql);
		    
	    	}finally {
	    		if (db != null) db.close();
	    	}
    	}
	
	
	public static void edit_statusTblrujsuburusanstatusfail_BatalMT(String idpermohonan,String idsuburusanstatusfail,
			String usid,String idFail) throws Exception {
	    
		Db db = null;
	    String sql = "";
	    String sql2 = "";
	    try
	    {		    	
	    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		
			db = new Db();		
				
			//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
							  
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  
			  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

			  r2.add("aktif",0);
			  r2.add("id_kemaskini",usid);
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
			  
			  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
			  myLogger.info("SQL UPDATE BATAL --> "+sql2);	
			  stmt2.executeUpdate(sql2);		  
			  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL
			  
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("id_suburusanstatusfail",id_suburusanstatusfail);
				r.add("id_permohonan",idpermohonan);
				r.add("id_suburusanstatus",398);
				r.add("aktif",1);
				r.add("id_fail",idFail);
				r.add("id_masuk",usid);
				r.add("tarikh_masuk",r.unquote("sysdate"));
				r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));				

				sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
				myLogger.info("SQL INSERT BATAL = "+sql);
				stmt.executeUpdate(sql);
			  
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	
	public static void edit_statusTblrujsuburusanstatusfail_BatalMT17(String usid,String idpermohonan,
			String idsuburusanstatusfail,String idFail) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	   
	    try
	    {		    	
	    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		

			db = new Db();		
				
			//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
							  
			  Statement stmt2 = db.getStatement();
			  SQLRenderer r2 = new SQLRenderer();
			  
			  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

			  r2.add("aktif",0);
			  r2.add("id_kemaskini",usid);
			  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
			  
			  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
			  myLogger.info("SQL UPDATE TBLRUJSUBURUSANSTATUSFAIL===BATAL17 --> "+sql2);	
			  stmt2.executeUpdate(sql2);
			  	
			  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL
			  
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("id_suburusanstatusfail",id_suburusanstatusfail);
				r.add("id_permohonan",idpermohonan);
				r.add("id_suburusanstatus",425);
				r.add("aktif",1);
				r.add("id_fail",idFail);
				r.add("id_masuk",usid);
				r.add("tarikh_masuk",r.unquote("sysdate"));
				r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));				

				sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
				myLogger.info("SQL INSERT edit_statusTblrujsuburusanstatusfail_Tangguh17 = "+sql);
				stmt.executeUpdate(sql);
			  
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }	
	
	
	public static void edit_status_tangguh(String idpermohonan,String usid,String idsuburusanstatusfail) throws Exception {
	    Db db = null;
	    String sql = "";
	    
	    try
	    {	
	    	String id_status = "174"; /* ID STATUS: TANGGUH PERBICARAAN (RUJUKAN MT) */
			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", idpermohonan);
		    
		    r.add("id_status", 174);
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));

		    sql = r.getSQLUpdate("tblppkpermohonan");
		    myLogger.info("SQL TANGGUH (RUJUKAN MT) = "+sql);
		    stmt.executeUpdate(sql); 
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}
    	}

		public static void edit_statusTblrujsuburusanstatusfail_Tangguh(String idpermohonan,String usid,
				String idFail,String idsuburusanstatusfail) throws Exception {
		    
			Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		
				db = new Db();		
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
								  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("SQL UPDATE TANGGUH --> "+sql2);	
				  stmt2.executeUpdate(sql2);				  					  
				  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idpermohonan);
					r.add("id_suburusanstatus",777);	/* ID_SUBURUSANSTATUS: TANGGUH PERBICARAAN (RUJUKAN MT) */
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));					
	
					sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
					myLogger.info("SQL INSERT TBLRUJSUBURUSANSTATUSFAIL TANGGUH (RUJUKAN ROTS) = "+sql);
					stmt.executeUpdate(sql);				  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		public static void edit_statusTblrujsuburusanstatusfail_Tangguh17(String usid,String idpermohonan,
				String idsuburusanstatusfail,String idFail) throws Exception {
		   
			Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");	

				db = new Db();		
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
								  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("SQL UPDATE TBLRUJSUBURUSANSTATUSFAIL=Tangguh --> "+sql2);	
				  stmt2.executeUpdate(sql2);
				  			  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idpermohonan);
					r.add("id_suburusanstatus",781);	/* ID_SUBURUSANSTATUS: TANGGUH PERBICARAAN (RUJUKAN MT) */
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));					
	
					sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
					myLogger.info("SEKSYEN 17 TBLRUJSUBURUSANSTATUSFAIL(RUJUKAN ROTS) = "+sql);
					stmt.executeUpdate(sql);				  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
		
		
	public static void updateDataMahkamah(String usid,String id_perintah,String id_perbicaraan,
    		String jenisPerintah,String txdTarikhPerintahEdit,String id_pejabat,String id_unitpsk) throws Exception	{
		
	    Db db = null;
	    String sql = "";
	    try
	    {
	    	String TP = "to_date('" + txdTarikhPerintahEdit + "','dd/MM/yyyy')";
	    	
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			SQLRenderer r2 = new SQLRenderer();
			  Connection conn = db.getConnection();
		      PreparedStatement ps = conn.prepareStatement("UPDATE Tblppkperintah " +
		      		"SET tarikh_perintah = "+r.unquote(TP)+", id_unitpsk = ?, id_pejabatmahkamah = ?," +
		      		"jenis_keluar_perintah = ?,flag_batal = ?, " +
		      		"ID_KEMASKINI = ?, TARIKH_KEMASKINI = sysdate " +
		      		"WHERE id_perintah = ? AND id_perbicaraan = ? ");
		        ps.setString(1, id_unitpsk);
		        ps.setString(2, id_pejabat);
		        ps.setString(3, jenisPerintah);
		        ps.setString(4, "1");
		        ps.setString(5, usid);		       					        
		        ps.setString(6, id_perintah);
		        ps.setString(7, id_perbicaraan);
		        ps.executeUpdate();	*/

			
			r.add("tarikh_perintah", r.unquote(TP));
			r.add("id_unitpsk", id_unitpsk);
			r.add("id_pejabatmahkamah", id_pejabat);
			r.add("jenis_keluar_perintah", jenisPerintah);
			r.add("flag_batal",1);								//* ID UTK Pindah ke Mahkamah Tinggi kerana ada wasiat
			r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));			
			r.update("id_perbicaraan", id_perbicaraan);
			r.update("id_perintah", id_perintah);			
			sql = r.getSQLUpdate("tblppkperintah");
			myLogger.info("UPDATE DATA MAHKAMAH = "+sql);
			stmt.executeUpdate(sql);
			
			
	    	} finally {
	    		if (db != null) db.close();
	    	}
	  	}

		public static void add_MaklumatBatal(String usid,String idpermohonan,String id_perbicaraan,String txdTarikhPerintah,
				String EDITsocPegawaiPengendali,String flag_batal,String flag_jenis_keputusan,String txtCatatanBatal) throws Exception {
			
			Db db = null;
			String sql = "";
			
				try
				{			
					long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";
					int id_jenisperintah = 8;			
					
				  //------------------------------- TBLPPKPERINTAH ------------------------------------
				    
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();			
					/*
					 Connection conn = db.getConnection();					
					 PreparedStatement ps = conn.prepareStatement("insert into tblppkperintah " +
			                    "(id_perintah,id_perbicaraan,tarikh_perintah,id_unitpsk,flag_batal," +
			                    "flag_jenis_keputusan,id_jenisperintah,sebab_batal," +
			                    " ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI) " +
			                    "values(?,?,"+r.unquote(TTP)+",?,?,?,?,?,?,?,?,sysdate,sysdate)");
						        ps.setLong(1, id_perintah);
						        ps.setString(2, id_perbicaraan);
						        ps.setString(3, EDITsocPegawaiPengendali);
						        ps.setString(4, flag_batal);
						        ps.setString(5, "2");	
						        ps.setString(6, "8");
						        ps.setString(7, txtCatatanBatal);
						        ps.setString(8, usid);
						        ps.setString(9, usid);						        
						        ps.executeUpdate();*/
					
					r.add("id_perintah",id_perintah);
					r.add("id_perbicaraan",id_perbicaraan);
					r.add("tarikh_perintah", r.unquote(TTP));
					r.add("id_unitpsk",EDITsocPegawaiPengendali);
					r.add("flag_batal",flag_batal);
					r.add("flag_jenis_keputusan",2);	/* ID utk BATAL */
					r.add("id_jenisperintah",8);		/* ID utk PERINTAH BATAL KUASA TADBIR [TBLPPKRUJJENISPERINTAH] */
					r.add("sebab_batal",txtCatatanBatal);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					sql = r.getSQLInsert("tblppkperintah");		
					myLogger.info(":: INSERT TBLPPKPERINTAH :: = "+sql);
					stmt.executeUpdate(sql);				
					
				}finally {
					if(db != null) db.close();
				}			
		}

		public static void edit_status_batal(String idpermohonan,String usid) throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {
				
		    	String id_status = "47";
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idpermohonan);
			    
			    r.add("id_status", 47);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("tblppkpermohonan");
			  
			    stmt.executeUpdate(sql);
			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}


			public static void edit_statusTblrujsuburusanstatusfail_batal(String usid,String idpermohonan,
		    		String id_perbicaraan,String idsuburusanstatusfail,String idFail) throws Exception {
			    Db db = null;
			    String sql = "";
			    String sql2 = "";
			   
			    try
			    {		    	
			    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		

					db = new Db();				
						
					//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
							  
					  Statement stmt2 = db.getStatement();
					  SQLRenderer r2 = new SQLRenderer();
					  
					  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
					  
					  r2.add("aktif",0);
					  r2.add("id_kemaskini",usid);
					  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
					  
					  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
					  myLogger.info("sql update TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);	
					  stmt2.executeUpdate(sql2);					 
					  
						//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
					  
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						r.add("id_suburusanstatusfail",id_suburusanstatusfail);
						r.add("id_permohonan",idpermohonan);
						r.add("id_suburusanstatus",398);
						r.add("aktif",1);
						r.add("id_fail",idFail);
						r.add("id_masuk",usid);
						r.add("tarikh_masuk",r.unquote("sysdate"));
						r.add("id_kemaskini",usid);
						r.add("tarikh_kemaskini",r.unquote("sysdate"));						

						sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
						stmt.executeUpdate(sql);					  
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }


			
			public static void edit_statusTblrujsuburusanstatusfail_batal17(String usid,String idpermohonan,
					String id_perbicaraan,String idsuburusanstatusfail,String idFail) throws Exception {
			    Db db = null;
			    String sql = "";
			    String sql2 = "";
			   
			    try
			    {		    	
			    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");			     				  

					db = new Db();						
						
					//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
							  
					  Statement stmt2 = db.getStatement();
					  SQLRenderer r2 = new SQLRenderer();
					  
					  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
					  
					  r2.add("aktif",0);
					  r2.add("id_kemaskini",usid);
					  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
					  
					  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
					  myLogger.info("UPDATE TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);	
					  stmt2.executeUpdate(sql2);
					 					  
						//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
					  
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						r.add("id_suburusanstatusfail",id_suburusanstatusfail);
						r.add("id_permohonan",idpermohonan);
						r.add("id_suburusanstatus",425);
						r.add("aktif",1);
						r.add("id_fail",idFail);
						r.add("id_masuk",usid);
						r.add("tarikh_masuk",r.unquote("sysdate"));
						r.add("id_kemaskini",usid);
						r.add("tarikh_kemaskini",r.unquote("sysdate"));						

						sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
						stmt.executeUpdate(sql);					  
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }			

			public static void updateMaklumatBatal(String usid,String id_perintah,
					String id_perbicaraan,String idpermohonan,String flag_jenis_keputusan,String EDITsocPegawaiPengendali, 
		    		String txdTarikhPerintahEDIT,String txtCatatanBatal,String flag_batal) throws Exception {
			    
				Db db = null;
			    String sql = "";	   
			    try
			    {		   	
				  String TPERINTAH = "to_date('" + txdTarikhPerintahEDIT + "','dd/MM/yyyy')";
			    
			      db = new Db();		  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				 
				  r.update("id_perintah", id_perintah);
				  r.update("id_perbicaraan", id_perbicaraan);				  
				  r.add("id_unitpsk", EDITsocPegawaiPengendali);
				  r.add("flag_jenis_keputusan", 2);	
				  r.add("flag_batal", flag_batal);	
				  r.add("sebab_batal", txtCatatanBatal);
				  r.add("tarikh_perintah", r.unquote(TPERINTAH));
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));			  
				  sql = r.getSQLUpdate("Tblppkperintah");
				  myLogger.info("SQL UPDATE BATAL = "+sql);
			      stmt.executeUpdate(sql);		
			        	    		  
			
			    }finally {
			      if (db != null) db.close();
			    }
			}

			public static void updateMaklumatTangguh(String idpermohonan,String usid,String id_perintah,
		    		String id_perbicaraan,String EDITsocPegawaiPengendali,String txdTarikhPerintahEDIT,
		    		String flag_tangguh,String txtCatatanTangguh,String txtPendapatTangguh,String txtTempoh) throws Exception {
			    
				Db db = null;
			    String sql = "";			   
			    try
			    {		   	
				  String TPERINTAH = "to_date('" + txdTarikhPerintahEDIT + "','dd/MM/yyyy')";				
			    
			      db = new Db();		  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_perintah", id_perintah);
				  r.update("id_perbicaraan", id_perbicaraan);
				  
				  r.add("id_unitpsk", EDITsocPegawaiPengendali);
				  r.add("flag_tangguh", flag_tangguh);	
				  r.add("sebab_tangguh", txtCatatanTangguh);	
				  r.add("keputusan_mahkamah", txtPendapatTangguh);
				  r.add("tempoh_tunggu_faraid", txtTempoh);
				  r.add("tarikh_perintah", r.unquote(TPERINTAH));
				  r.add("id_kemaskini", usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  sql = r.getSQLUpdate("Tblppkperintah");
				  
				  myLogger.info("SQL TANGGUH = "+sql  );
			      stmt.executeUpdate(sql);		    	    		  
			
			    }finally {
			      if (db != null) db.close();
			    }
			}

			public static void deleteWarisMSterdahulu(String usid,String idBorangJ) throws Exception {
				 Db db = null;
				 String sql = "";
				 
				  try {					  
				    
					  //HAPUS ID_OB TERDAHULU
					  db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				     				     
				      r.add("id_borangj", idBorangJ);
				      
				      sql = r.getSQLDelete("Tblppkborangjdtl");
				      myLogger.info("DELETE WARIS MS :: "+sql);
				      stmt.executeUpdate(sql);									

				    } finally {
				      if (db != null) db.close();
				    }
				  }		

			
			public static void deleteWarisTerdahuluKolateral( String IdKolateralmst ) throws Exception {
				 Db db = null;
				 String sql = "";
				 
				  try {					  
									      
					  //HAPUS ID_OB TERDAHULU
					  db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				     				     
				      r.add("id_kolateralmst", IdKolateralmst);
				      
				      sql = r.getSQLDelete("Tblppkkolateraldtl");
				      myLogger.info("SQL DELETE MAKLUMAT WARIS TBLPPKKOLATERALDTL :: "+sql);
				      stmt.executeUpdate(sql);									

				    } finally {
				      if (db != null) db.close();
				    }
				  }		
			
			
			public static void updateWarisMufti(String usid,String idBorangJ,String idOB) throws Exception {
				 Db db = null;
				 String sql = "";
				 
				  try {					  
					long id_borangjdtl = DB.getNextID("TBLPPKBORANGJDTL_SEQ");								
					
					  //INSERT ID_OB YANG TERKINI 	
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_borangjdtl", id_borangjdtl);
				      r.add("id_borangj", idBorangJ);
				      r.add("id_ob", idOB);					      
				      r.add("id_masuk",usid);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      r.add("id_kemaskini",usid);
				      r.add("tarikh_kemaskini",r.unquote("sysdate"));
				      
				      sql = r.getSQLInsert("TBLPPKBORANGJDTL");
				      myLogger.info("INSERT BORANG JDTL MUFTI = "+sql);
				      stmt.executeUpdate(sql);		

				    } finally {
				      if (db != null) db.close();
				    }
				  }					
			
			public static void updateWarisMS(String usid,String idBorangJ, String idOB) throws Exception {
				 Db db = null;
				 String sql = "";
				 
				  try {					  
					long id_borangjdtl = DB.getNextID("TBLPPKBORANGJDTL_SEQ");			
					
					  //INSERT ID_OB YANG TERKINI 	
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_borangjdtl", id_borangjdtl);
				      r.add("id_borangj", idBorangJ);
				      r.add("id_ob", idOB);					      
				      r.add("id_masuk",usid);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      r.add("id_kemaskini",usid);
				      r.add("tarikh_kemaskini",r.unquote("sysdate"));
				      
				      sql = r.getSQLInsert("TBLPPKBORANGJDTL");
				      myLogger.info("INSERT BORANG JDTL = "+sql);
				      stmt.executeUpdate(sql);		

				    } finally {
				      if (db != null) db.close();
				    }
				  }		

			public static void addLaporan(String usid,String idBorangJ,String idOB) throws Exception {
					 Db db = null;
					 String sql = "";
					 
					  try {  
						long id_borangjdtl = DB.getNextID("TBLPPKBORANGJDTL_SEQ");
					      
						  //INSERT TBLPPKBORANGJDTL 
					      db = new Db();
					      Statement stmt = db.getStatement();
					      SQLRenderer r = new SQLRenderer();
					      r.add("id_borangjdtl", id_borangjdtl);
					      r.add("id_borangj", idBorangJ);
					      r.add("id_ob", idOB);					      
					      r.add("id_masuk",usid);
					      r.add("tarikh_masuk",r.unquote("sysdate"));
					      r.add("id_kemaskini",usid);
					      r.add("tarikh_kemaskini",r.unquote("sysdate"));
					      
					      sql = r.getSQLInsert("TBLPPKBORANGJDTL");
					      myLogger.info("SQL INSERT BORANG JDTL = "+sql);
					      stmt.executeUpdate(sql);					      
     
					    } finally {
					      if (db != null) db.close();
					    }
					  }


		public static void updateLaporan(String idBorangJ,String usid,String idOB) throws Exception {
			 Db db = null;
			 String sql = "";
			  try {  
				long id_borangjdtl = DB.getNextID("TBLPPKBORANGJDTL_SEQ");
			      
				  //INSERT TBLPPKBORANGJDTL [ID MASUK & ID KEMASKINI SEKALI SBB SENARAI WARIS HANYA ADA HAPUS DAN TAMBAH]
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("id_borangjdtl", id_borangjdtl);
			      r.add("id_borangj", idBorangJ);
			      r.add("id_ob", idOB);					      
			      r.add("id_masuk",usid);
			      r.add("tarikh_masuk",r.unquote("sysdate"));
			      r.add("id_kemaskini",usid);
			      r.add("tarikh_kemaskini",r.unquote("sysdate"));
			      
			      sql = r.getSQLInsert("TBLPPKBORANGJDTL");
			      myLogger.info("SQL BORANG JDTL = "+sql);
			      stmt.executeUpdate(sql);			

			    } finally {
			      if (db != null) db.close();
			    }
			  }			
			

		public static void updateKolateralDTLplantif( String IdKolateralmst, String usid, String idOB) throws Exception {
			 Db db = null;
			 String sql = "";
		
			  try {
				  
				long id_kolateraldtl = DB.getNextID("TBLPPKKOLATERALDTL_SEQ");			      
			      
				  //INSERT TBLPPKKOLATERALDTL [ID MASUK & ID KEMASKINI SEKALI SBB SENARAI WARIS HANYA ADA HAPUS DAN TAMBAH]
			      db = new Db();
			      Statement stmt2 = db.getStatement();
			      SQLRenderer r2 = new SQLRenderer();
			      r2.add("id_kolateraldtl", id_kolateraldtl);
			      r2.add("id_kolateralmst", IdKolateralmst);
			      r2.add("id_ob", idOB);	
			      r2.add("jenis_ob", "PL" );
			      r2.add("id_masuk",usid);			      
			      r2.add("tarikh_masuk",r2.unquote("sysdate"));
			      r2.add("id_kemaskini",usid);
			      r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
			      
			      sql = r2.getSQLInsert("TBLPPKKOLATERALDTL");
			      myLogger.info("SQL INSERT BORANG JDTL PLANTIF = "+sql);
			      stmt2.executeUpdate(sql);			

			    } finally {
			      if (db != null) db.close();
			    }
			  }			
					
		public static void updateKolateralDTLdefendan( String IdKolateralmst, String usid, String idOB) throws Exception {
			 Db db = null;
			 String sql = "";
		
			  try {
				  
				long id_kolateraldtl = DB.getNextID("TBLPPKKOLATERALDTL_SEQ");			      
			      
				  //INSERT TBLPPKKOLATERALDTL [ID MASUK & ID KEMASKINI SEKALI SBB SENARAI WARIS HANYA ADA HAPUS DAN TAMBAH]
			      db = new Db();
			      Statement stmt2 = db.getStatement();
			      SQLRenderer r2 = new SQLRenderer();
			      r2.add("id_kolateraldtl", id_kolateraldtl);
			      r2.add("id_kolateralmst", IdKolateralmst);
			      r2.add("id_ob", idOB);	
			      r2.add("jenis_ob", "DF" );
			      r2.add("id_masuk",usid);			      
			      r2.add("tarikh_masuk",r2.unquote("sysdate"));
			      r2.add("id_kemaskini",usid);
			      r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
			      
			      sql = r2.getSQLInsert("TBLPPKKOLATERALDTL");
			      myLogger.info("SQL INSERT BORANG JDTL DEFENDAN = "+sql);
			      stmt2.executeUpdate(sql);			

			    } finally {
			      if (db != null) db.close();
			    }
			  }					
		
			public static Vector setROTS(String idBorangJ,String id_perbicaraan) throws Exception  {
					
				    Db db = null;
				    listRots.clear();
				    String sql = "";
				    try {
				      Vector localVector1;
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      
				      r.add("a.id_borangj");
				      r.add("a.id_perbicaraan"); 
				      r.add("a.jenis_rujukan"); 
				      r.add("a.id_mahkamah"); 				      
				      r.add("a.catatan1"); 	
				      r.add("a.keputusan_mahkamah");
				      r.add("a.tarikh_bicara"); 
				      r.add("a.tarikh_mohon"); 

				      r.add("a.id_borangj", idBorangJ, "=");
				      r.add("a.id_perbicaraan", id_perbicaraan, "=");

				      sql = r.getSQLSelect("Tblppkborangj a");
				      myLogger.info("SQL ROTS SYARIAH = "+sql);
				      ResultSet rs = stmt.executeQuery(sql);
				   
				      Hashtable h;
				      int bil = 1;			      

				      while (rs.next()) {
				    	h = new Hashtable();
				    	h.put("bil", bil);
				        h.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
				    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
				    	h.put("jenis_rujukan", rs.getString("jenis_rujukan")==null?"":rs.getString("jenis_rujukan"));
				    	h.put("id_mahkamah", rs.getString("id_mahkamah")==null?"":rs.getString("id_mahkamah"));
				    	h.put("catatan1", rs.getString("catatan1")==null?"":rs.getString("catatan1"));
				    	h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
				    	h.put("tarikh_bicara", rs.getDate("tarikh_bicara")==null?"":Format.format(rs.getDate("tarikh_bicara")));
				    	h.put("tarikh_mohon", rs.getDate("tarikh_mohon")==null?"":Format.format(rs.getDate("tarikh_mohon")));
				    	
				    	listRots.addElement(h);
				    	bil++;
				      }
				      return listRots;
				    }
				    finally {
				      if (db != null) db.close();
				    }
			}

			public static Vector setMufti(String idBorangJ,String id_perbicaraan) throws Exception  {
				
			    Db db = null;
			    listMufti.clear();
			    String sql = "";
			    try {
			      Vector localVector1;
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("a.id_borangj");
			      r.add("a.id_perbicaraan"); 
			      r.add("a.jenis_rujukan"); 
			      r.add("a.nama_pejabat"); 		
			      r.add("a.alamat1"); 	
			      r.add("a.alamat2");
			      r.add("a.alamat3"); 
			      r.add("a.poskod"); 	
			      r.add("a.id_bandar"); 
			      r.add("a.id_negeri"); 
			      r.add("a.no_tel"); 
			      r.add("a.no_fax"); 	
			      r.add("a.flag_rujukan"); 	
			      r.add("a.catatan1"); 	
			      r.add("a.keputusan_mahkamah");
			      r.add("a.tarikh_bicara"); 
			      r.add("a.tarikh_mohon"); 

			      r.add("a.id_borangj", idBorangJ, "=");
			      r.add("a.id_perbicaraan", id_perbicaraan, "=");

			      sql = r.getSQLSelect("Tblppkborangj a");
			      myLogger.info("SQL PEJABAT MUFTI = "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			   
			      Hashtable h;
			      int bil = 1;			      

			      while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			        h.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
			    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
			    	h.put("jenis_rujukan", rs.getString("jenis_rujukan")==null?"":rs.getString("jenis_rujukan"));
			    	h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
			    	h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			    	h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
			    	h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
			    	h.put("flag_rujukan", rs.getString("flag_rujukan")==null?"":rs.getString("flag_rujukan"));
			    	h.put("catatan1", rs.getString("catatan1")==null?"":rs.getString("catatan1"));
			    	h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
			    	h.put("tarikh_bicara", rs.getDate("tarikh_bicara")==null?"":Format.format(rs.getDate("tarikh_bicara")));
			    	h.put("tarikh_mohon", rs.getDate("tarikh_mohon")==null?"":Format.format(rs.getDate("tarikh_mohon")));
			    	
			    	listMufti.addElement(h);
			    	bil++;
			      }
			      return listMufti;
			    }
			    finally {
			      if (db != null) db.close();
			    }
		}			
			
	
		public static Hashtable getIdBorangJ(String id_perbicaraan) throws Exception {
			    Hashtable v = new Hashtable();
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			 
			      r.add("a.id_perbicaraan");
			      r.add("b.id_borangj");
			      
			      r.add("a.id_perbicaraan",r.unquote("b.id_perbicaraan"));
			      
			      r.add("a.id_perbicaraan",id_perbicaraan);
			      
			      sql = r.getSQLSelect("Tblppkperbicaraan a, Tblppkborangj b");			      
			      myLogger.info("SQL ID BORANG J = "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      
			      if (rs.next()) {
			        //Hashtable h = new Hashtable();
			    	v.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
			    	v.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
			        
			        //v.addElement(h);
			      }
			      return v;
			    }
			    finally {
			      if (db != null) db.close();
			    }
		  }

			public static Vector getIdBorangJDTL(String id_perbicaraan,String idBorangJ) throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();   
			 
			      r.add("a.id_perbicaraan");
			      r.add("b.id_borangj");
			      r.add("c.id_borangjdtl");
			      
			      r.add("a.id_perbicaraan",r.unquote("b.id_perbicaraan"));
			      r.add("c.id_borangj",r.unquote("b.id_borangj"));
			      
			      r.add("a.id_perbicaraan",id_perbicaraan);
			      r.add("b.id_borangj",idBorangJ);
			      
			      sql = r.getSQLSelect("Tblppkperbicaraan a, Tblppkborangj b, tblppkborangjdtl c");
			      myLogger.info("SQL ID BORANG JDTL = "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector v = new Vector();
			      
			      while (rs.next()) {
			        Hashtable h = new Hashtable();
			    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
			    	h.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
			    	h.put("id_borangjdtl", rs.getString("id_borangjdtl")==null?"":rs.getString("id_borangjdtl"));
			    	
			        v.addElement(h);
			      }
			      return v;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			  }
			
			public void setMaklumatWaris(String idBorangJ,String id_perbicaraan,String id_permohonansimati) throws Exception {
			    Db db = null;
			    existingWaris.clear();
			    
				String sql = " SELECT a.id_borangj, a.id_perbicaraan, c.id_ob, e.nama_ob, e.no_kp_baru,e.no_kp_lama, e.no_kp_lain, sa.keterangan "+
				 " FROM tblppkborangj a,tblppkborangjdtl c," +
				 " tblppkob e1,tblppkobpermohonan e," +
				 " tblppkrujsaudara sa "+					 
				 " WHERE e.id_ob = e1.id_ob and e.id_permohonansimati = '"+id_permohonansimati+"' " +
				 		" and c.id_borangj = a.id_borangj AND c.id_ob = e1.id_ob "+
				 " AND NVL(e.id_saudara, 0) = sa.id_saudara AND a.id_borangj = '"+ idBorangJ +"' "+
				 " AND a.id_perbicaraan = '"+ id_perbicaraan +"' ";
			   
				
				
				
				
			    
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
		      
			      myLogger.info("SQL MAKLUMAT WARIS = "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      
					Hashtable h;
					int bil = 1;
					Integer count = 0;
			      
			      while (rs.next()) {
			    	h = new Hashtable();
			        h.put("bil", bil);
			    	h.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
			    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
			    	h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
			    	h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
			    	h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
			    	h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));
			    	h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
			    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	
			    	existingWaris.addElement(h);
					bil++;
					count++;
			      }
			      //return v;
			    }
			    finally {
			      if (db != null) db.close();
			    }
		}			

			public static Vector checkingSenaraiWaris(int id) throws Exception {
				
			    Db db = null;
			    CheckingWaris.clear();
			    String sql = "";
			    try {
			      Vector localVector1;
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("a.id_ob");
			      r.add("a.id_permohonansimati");
			      r.add("b.id_permohonan");
			      r.add("a.status_hidup");

			      r.add("a.id_permohonansimati",r.unquote("b.id_permohonansimati"));
			      r.add("a.status_hidup",0); //[0]- hidup dan [1]-mati

			      r.add("b.id_permohonan",id);

			      sql = r.getSQLSelect("Tblppkob a, Tblppkpermohonansimati b");
			      myLogger.info("---------SQL CHECKING SENARAI WARIS = "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      Hashtable h;
			      int bil = 1;
			      while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			        h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
			    	h.put("id_permohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
			    	h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
			    	h.put("status_hidup", rs.getString("status_hidup")==null?"":rs.getString("status_hidup"));
			    	
			    	CheckingWaris.addElement(h);
			    	bil++;
			      }
			      return CheckingWaris;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			}

			
		public Vector setPerintahTangguhROTS(String id_perbicaraan) throws Exception  {
			    Db db = null;
			    listPerintahTangguhROTS.clear();
			    String sql = "";
			    
			    try {
			      Vector localVector1;
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("a.id_perintah");
			      r.add("a.id_perbicaraan"); 
			      r.add("a.flag_jenis_keputusan");     
			      r.add("a.id_unitpsk");    
			      r.add("b.nama_pegawai");  
			      r.add("a.jenis_keluar_perintah");  
			      r.add("a.tarikh_perintah");
			      r.add("j.id_mahkamah");
			      r.add("c.id_negeri");
			      r.add("d.nama_negeri");
			      r.add("c.id_daerah");
			      r.add("e.nama_daerah");
			      r.add("c.nama_pejabat");
			      r.add("c.alamat1");
			      r.add("c.alamat2");
			      r.add("c.alamat3");
			      r.add("c.poskod");
			      r.add("c.no_tel");
			      r.add("c.no_fax");
			      r.add("j.catatan1");
			      r.add("j.keputusan_mahkamah");
			      r.add("j.jenis_rujukan");
			      r.add("j.id_borangj");
			      
			      r.add("a.id_unitpsk",r.unquote("b.id_unitpsk"));
			      r.add("j.id_mahkamah",r.unquote("c.id_pejabat"));
			      r.add("d.id_negeri",r.unquote("c.id_negeri"));
			      r.add("e.id_daerah",r.unquote("c.id_daerah"));

			      r.add("a.id_perbicaraan", id_perbicaraan, "=");

			      sql = r.getSQLSelect("Tblppkperintah a, Tblppkrujunit b, Tblrujpejabat c, Tblrujnegeri d, Tblrujdaerah e, Tblppkborangj j");
			      myLogger.info("SQL PERINTAH TANGGUH ROTS = "+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			   
			      Hashtable h;
			      int bil = 1;

			      while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			        h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
			    	h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
			    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
			    	h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
			    	h.put("jenis_keluar_perintah", rs.getString("jenis_keluar_perintah")==null?"":rs.getString("jenis_keluar_perintah"));
			    	h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
			    	h.put("id_mahkamah", rs.getString("id_mahkamah")==null?"":rs.getString("id_mahkamah"));
			    	h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
			    	h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
			    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			    	h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
			    	h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
			    	h.put("catatan1", rs.getString("catatan1")==null?"":rs.getString("catatan1"));
			    	h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
			    	h.put("jenis_rujukan", rs.getString("jenis_rujukan")==null?"":rs.getString("jenis_rujukan"));
			    	h.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
			    	
			    	listPerintahTangguhROTS.addElement(h);
			    	bil++;
			      }
			      return listPerintahTangguhROTS;
			    }
			    finally {
			      if (db != null) db.close();
			    }
			}


		public static void add_MaklumatKolateral(String idpermohonan,String usid,String id_perbicaraan,
				String txdTarikhPerakuanAdd,String txdTarikhBicara,String txtMasaBicara,String socTempatBicara,String txtCatatanAdd,
				String socNegeriBicara,String socPegawaiPengendali) throws Exception {
			
				Db db = null;
				String sql = "";
				String sql2 = "";		
				try
				{			
					long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");		
					long id_kolateralmst = DB.getNextID("TBLPPKKOLATERALMST_SEQ");	

					String TP = "to_date('" + txdTarikhPerakuanAdd + "','dd/MM/yyyy')";
					String TB = "to_date('" + txdTarikhBicara + "','dd/MM/yyyy')";	
					
				  //-------- TBLPPKPERINTAH --------
				    
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("id_perintah",id_perintah);
					r.add("id_perbicaraan",id_perbicaraan);
					r.add("tarikh_perintah", r.unquote(TB));
					//r.add("id_unitpsk",socPegawaiPengendali); 
					r.add("flag_tangguh",6);
					r.add("flag_jenis_keputusan",1);	/* ID utk TANGGUH */
					r.add("id_jenisperintah",6);		/* ID utk PERINTAH BATAL KUASA TADBIR [TBLPPKRUJJENISPERINTAH] */
					r.add("id_pejabatmahkamah",socTempatBicara);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));

					sql = r.getSQLInsert("Tblppkperintah");		
					myLogger.info("::KOLATERAL INSERT TBLPPKPERINTAH :: = "+sql);
					stmt.executeUpdate(sql);				
					
				  //------------------------------- TBLPPKKOLATERALMST ------------------------------------

					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_kolateralmst",id_kolateralmst);
					r2.add("id_perbicaraan",id_perbicaraan);
					r2.add("tarikh_bicara", r2.unquote(TB));
					r2.add("sebab_pertelingkahan",txtCatatanAdd);	
					r2.add("tarikh_perakuan", r2.unquote(TP));
					r2.add("masa_bicara",txtMasaBicara);
					r2.add("id_unitpsk",socPegawaiPengendali);
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));

					sql2 = r2.getSQLInsert("Tblppkkolateralmst");		
					myLogger.info("::KOLATERAL INSERT TBLPPKKOLATERALMST :: = "+sql2);
					stmt.executeUpdate(sql2);				
					
				}finally {
					if(db != null) db.close();
				}			
		}


		public static Vector getIdKolSateralmst(String id_perbicaraan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("a.id_kolateralmst");
		      
		      r.add("a.id_perbicaraan",r.unquote("b.id_perbicaraan"));
		      
		      r.add("a.id_perbicaraan",id_perbicaraan);     
		 		      
		      sql = r.getSQLSelect("Tblppkkolateralmst a, Tblppkperbicaraan b");
		     //myLogger.info("SQL ID KOLATERALMST = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		    	h.put("id_kolateralmst", rs.getString("id_kolateralmst")==null?"":rs.getString("id_kolateralmst"));
		        
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

	
		public static void addIDOBPlanitif(String usid,String IdKolateralmst,String idOB) throws Exception {
			 Db db = null;
			 String sql = "";
			  try {
				  
				long id_kolateraldtl = DB.getNextID("TBLPPKKOLATERALDTL_SEQ");
				String jenis_ob = "PL";	/* PL - PLANITIF	*/											   
			      
				  //INSERT TBKPPKKOLATERALDTL 
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("id_kolateraldtl", id_kolateraldtl);
			      r.add("id_kolateralmst", IdKolateralmst);
			      r.add("id_ob", idOB);	
			      r.add("jenis_ob", jenis_ob);
			      r.add("id_masuk",usid);
			      r.add("tarikh_masuk",r.unquote("sysdate"));
			      
			      sql = r.getSQLInsert("TBLPPKKOLATERALDTL");
			      myLogger.info("INSERT TBLPPKKOLATERALDTL PLANITIF = "+sql);
			      stmt.executeUpdate(sql);					      

			    } finally {
			      if (db != null) db.close();
			    }
		}

		
		public static void addIDOBDefendan(String usid,String IdKolateralmst,String idOB) throws Exception {
			 Db db = null;
			 String sql = "";
			  try {
				  
				long id_kolateraldtl = DB.getNextID("TBLPPKKOLATERALDTL_SEQ");
				String jenis_ob = "DF";	/* DF - DEFENDAN	*/
			      
				  //INSERT TBKPPKKOLATERALDTL 
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      r.add("id_kolateraldtl", id_kolateraldtl);
			      r.add("id_kolateralmst", IdKolateralmst);
			      r.add("id_ob", idOB);	
			      r.add("jenis_ob", jenis_ob);
			      r.add("id_masuk",usid);
			      r.add("tarikh_masuk",r.unquote("sysdate"));
			      
			      sql = r.getSQLInsert("TBLPPKKOLATERALDTL");
			      myLogger.info("INSERT TBLPPKKOLATERALDTL DEFENDAN = "+sql);
			      stmt.executeUpdate(sql);					      

			    } finally {
			      if (db != null) db.close();
			    }
		}

	
		public static void edit_status_tangguhKolateral(String idpermohonan,String idsuburusanstatusfail,
				String usid) throws Exception {
			
		    Db db = null;
		    String sql = "";	    
		    try
		    {
				
		    	String id_status = "172"; /* STATUS: TANGGUH PERBICARAAN (KOLATERAL) */
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idpermohonan);
			    
			    r.add("id_status", 172);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("Tblppkpermohonan");
			    myLogger.info("EDIT STATUS TANGGUH = "+sql);
			    stmt.executeUpdate(sql);
			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}


		public static void edit_statusTblrujsuburusanstatusfail_TangguhKolateral(String idpermohonan,String usid,
				String is_perbicaraan,String idsuburusanstatusfail,String idFail) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");	
				db = new Db();
										
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
					  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  stmt2.executeUpdate(sql2);				  
				  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL 			
				  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				
				  r.add("id_suburusanstatusfail",id_suburusanstatusfail);
				  r.add("id_permohonan",idpermohonan);
				  r.add("id_suburusanstatus",775);
				  r.add("aktif",1);
				  r.add("id_fail",idFail);
				  r.add("id_masuk",usid);
				  r.add("tarikh_masuk",r.unquote("sysdate"));
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));				  

				  sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
				  myLogger.info("SQL TBLRUJSUBURUSANSTATUSFAIL = "+sql);
				  stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		public static void edit_statusTblrujsuburusanstatusfail_TangguhKolateral17(String idpermohonan,String usid,
				String id_perbicaraan,String idsuburusanstatusfail,String idFail) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		
		    	db = new Db();
										
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
					  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("UPDATE TBLRUJSUBURUSANSTATUSFAIL 17 --> "+sql2);	
				  stmt2.executeUpdate(sql2);				  
				  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL 			
				  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				
				  r.add("id_suburusanstatusfail",id_suburusanstatusfail);
				  r.add("id_permohonan",idpermohonan);
				  r.add("id_suburusanstatus",779);
				  r.add("aktif",1);
				  r.add("id_fail",idFail);
				  r.add("id_masuk",usid);
				  r.add("tarikh_masuk",r.unquote("sysdate"));
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));				  

				  sql = r.getSQLInsert("Tblrujsuburusanstatusfail");	
				  //myLogger.info("sql update edit_statusTblrujsuburusanstatusfail_TangguhKolateral 17 = "+sql);
				  stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
		public static Vector setPerintahKolateral(String id_permohonan, String id_perbicaraan) throws Exception {
			
		    Db db = null;
		    listPerintahKolateral.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("c.id_permohonan");
		      r.add("a.id_perbicaraan");
		      r.add("b.id_perintah");
		      r.add("a.id_keputusanpermohonan");
		      r.add("e.id_unitpsk");
		      r.add("b.tarikh_perintah");
		      r.add("b.flag_jenis_keputusan");
		      r.add("b.catatan");	
		      r.add("b.flag_tangguh");
		      r.add("b.flag_batal");
		      r.add("b.sebab_batal");
		      r.add("b.sebab_tangguh");
		      r.add("b.keputusan_mahkamah");
		      r.add("b.id_pejabatmahkamah");
		      r.add("d.alamat1");
		      r.add("d.alamat2");
		      r.add("d.alamat3");
		      r.add("d.poskod");
		      r.add("d.id_negeri");    
		      r.add("e.tarikh_bicara");
		      r.add("a.masa_bicara");
		      r.add("e.sebab_pertelingkahan");	
		      r.add("e.id_kolateralmst");	
		      r.add("e.tarikh_perakuan");	

		      r.add("a.id_perbicaraan",r.unquote("b.id_perbicaraan"));
		      r.add("c.id_keputusanpermohonan",r.unquote("a.id_keputusanpermohonan"));
		      r.add("d.id_pejabatjkptg",r.unquote("b.id_pejabatmahkamah"));
		      r.add("e.id_perbicaraan",r.unquote("a.id_perbicaraan"));
		         
		      r.add("c.id_permohonan",id_permohonan);
		      r.add("a.id_perbicaraan", id_perbicaraan, "=");

		      sql = r.getSQLSelect("Tblppkperbicaraan a, Tblppkperintah b, Tblppkkeputusanpermohonan c, Tblrujpejabatjkptg d, Tblppkkolateralmst e ");
		      myLogger.info("SQL KOLATERAL = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
		    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
		    	h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
		    	h.put("id_keputusanpermohonan", rs.getString("id_keputusanpermohonan")==null?"":rs.getString("id_keputusanpermohonan"));
		    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"0":rs.getString("id_unitpsk"));
		    	h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
		    	h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
		    	h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
		    	h.put("flag_tangguh", rs.getString("flag_tangguh")==null?"":rs.getString("flag_tangguh"));
		    	h.put("flag_batal", rs.getString("flag_batal")==null?"":rs.getString("flag_batal"));
		    	h.put("sebab_batal", rs.getString("sebab_batal")==null?"":rs.getString("sebab_batal"));
		    	h.put("sebab_tangguh", rs.getString("sebab_tangguh")==null?"":rs.getString("sebab_tangguh"));
		    	h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
		    	h.put("id_pejabatmahkamah", rs.getString("id_pejabatmahkamah")==null?"":rs.getString("id_pejabatmahkamah"));
		    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	h.put("tarikh_bicara", rs.getDate("tarikh_bicara")==null?"":Format.format(rs.getDate("tarikh_bicara")));
		    	h.put("masa_bicara", rs.getString("masa_bicara")==null?"":rs.getString("masa_bicara"));
		    	h.put("sebab_pertelingkahan", rs.getString("sebab_pertelingkahan")==null?"":rs.getString("sebab_pertelingkahan"));
		    	h.put("id_kolateralmst", rs.getString("id_kolateralmst")==null?"":rs.getString("id_kolateralmst"));
		    	h.put("tarikh_perakuan", rs.getDate("tarikh_perakuan")==null?"":Format.format(rs.getDate("tarikh_perakuan")));		    	
		    	
		    	listPerintahKolateral.addElement(h);
		    	bil++;
		      }
		      return listPerintahKolateral;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}


		public static Vector setSenaraiWarisPlanitif(String id_perbicaraan,String id_permohonansimati) throws Exception {
			
		    Db db = null;
		    listWarisPlanitif.clear();
		    /*
			String sql = " SELECT c.id_ob, d.nama_ob, d.no_kp_baru, d.id_saudara, e.keterangan "+
			 " FROM tblppkperintah a,tblppkkolateralmst b,tblppkkolateraldtl c,tblppkob d,tblppkrujsaudara e "+					 
			 " WHERE NVL(d.id_saudara,0) = e.id_saudara AND a.id_perbicaraan = b.id_perbicaraan "+
			 " AND b.id_kolateralmst = c.id_kolateralmst AND d.id_ob = c.id_ob "+
			 " AND c.jenis_ob = 'PL' AND a.id_perbicaraan = '"+ id_perbicaraan +"' ";
			 
			 		    */
		    
		    String sql = " SELECT c.id_ob, d.nama_ob, d.no_kp_baru, d.id_saudara, e.keterangan "+
			 " FROM tblppkperintah a,tblppkkolateralmst b,tblppkkolateraldtl c," +
			 " tblppkob d1,tblppkobpermohonan d,tblppkrujsaudara e "+					 
			 " WHERE d.id_ob = d1.id_ob and d.id_permohonansimati = '"+id_permohonansimati+"' " +
			 		" and NVL(d.id_saudara,0) = e.id_saudara AND a.id_perbicaraan = b.id_perbicaraan "+
			 " AND b.id_kolateralmst = c.id_kolateralmst AND d1.id_ob = c.id_ob "+
			 " AND c.jenis_ob = 'PL' AND a.id_perbicaraan = '"+ id_perbicaraan +"' ";
		    
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		     
		      
//		      r.add("C.ID_OB");
//		      r.add("D.NAMA_OB");
//		      r.add("D.NO_KP_BARU");
//		      r.add("D.ID_SAUDARA");
//		      r.add("E.KETERANGAN"); 
//
//		      r.add("D.ID_SAUDARA",r.unquote("E.ID_SAUDARA"));
//		      r.add("A.ID_PERBICARAAN",r.unquote("B.ID_PERBICARAAN"));
//		      r.add("B.ID_KOLATERALMST",r.unquote("C.ID_KOLATERALMST"));
//		      r.add("D.ID_OB",r.unquote("C.ID_OB"));
//		      r.add("C.JENIS_OB","PL");		
//
//		      r.add("A.ID_PERBICARAAN",id_perbicaraan);
//
//		      sql = r.getSQLSelect("TBLPPKPERINTAH A, TBLPPKKOLATERALMST B, TBLPPKKOLATERALDTL C, TBLPPKOB D, TBLPPKRUJSAUDARA E ");
		      
		      myLogger.info("SQL SET SENARAI WARIS PLANITIF = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);		      
		   
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("id_ob", rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
		        h.put("nama_ob", rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
		    	h.put("no_kp_baru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
		    	h.put("id_saudara", rs.getString("ID_SAUDARA")==null?"":rs.getString("ID_SAUDARA"));
		    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    	//h.put("jenis_ob", rs.getString("JENIS_OB")==null?"":rs.getString("JENIS_OB"));
		    	
		    	listWarisPlanitif.addElement(h);
		    	bil++;
		      }
		      return listWarisPlanitif;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

	
		public static Vector setSenaraiWarisDefendan(String id_perbicaraan,String id_permohonansimati) throws Exception {
			
		    Db db = null;
		    listWarisDefendan.clear();
		    /*
			String sql = " SELECT c.id_ob, d.nama_ob, d.no_kp_baru, d.id_saudara, e.keterangan "+
			 " FROM tblppkperintah a,tblppkkolateralmst b,tblppkkolateraldtl c,tblppkob d,tblppkrujsaudara e "+					 
			 " WHERE NVL(d.id_saudara,0) = e.id_saudara AND a.id_perbicaraan = b.id_perbicaraan "+
			 " AND b.id_kolateralmst = c.id_kolateralmst AND d.id_ob = c.id_ob "+
			 " AND c.jenis_ob = 'DF' AND a.id_perbicaraan = '"+ id_perbicaraan +"' ";
			 */	
		    
		    String sql = " SELECT c.id_ob, d.nama_ob, d.no_kp_baru, d.id_saudara, e.keterangan "+
			 " FROM tblppkperintah a,tblppkkolateralmst b,tblppkkolateraldtl c," +
			 " tblppkobpermohonan d,tblppkob d1,tblppkrujsaudara e "+					 
			 " WHERE d.id_ob = d1.id_ob and d.id_permohonansimati = '"+id_permohonansimati+"' " +
			 		" and NVL(d.id_saudara,0) = e.id_saudara AND a.id_perbicaraan = b.id_perbicaraan "+
			 " AND b.id_kolateralmst = c.id_kolateralmst AND d1.id_ob = c.id_ob "+
			 " AND c.jenis_ob = 'DF' AND a.id_perbicaraan = '"+ id_perbicaraan +"' ";
		    
		    
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
//		      r.add("C.ID_OB");
//		      r.add("D.NAMA_OB");
//		      r.add("D.NO_KP_BARU");
//		      r.add("D.ID_SAUDARA");
//		      r.add("E.KETERANGAN"); 
//
//		      r.add("D.ID_SAUDARA",r.unquote("E.ID_SAUDARA"));
//		      r.add("A.ID_PERBICARAAN",r.unquote("B.ID_PERBICARAAN"));
//		      r.add("B.ID_KOLATERALMST",r.unquote("C.ID_KOLATERALMST"));
//		      r.add("D.ID_OB",r.unquote("C.ID_OB"));
//		      r.add("C.JENIS_OB","DF");		
//
//		      r.add("A.ID_PERBICARAAN",id_perbicaraan);
//
//		      sql = r.getSQLSelect("TBLPPKPERINTAH A, TBLPPKKOLATERALMST B, TBLPPKKOLATERALDTL C, TBLPPKOB D, TBLPPKRUJSAUDARA E ");

		      myLogger.info("SQL SET SENARAI WARIS DEFENDAN = "+sql);		      
		      ResultSet rs = stmt.executeQuery(sql);		      
		   
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("id_ob", rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
		        h.put("nama_ob", rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
		    	h.put("no_kp_baru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
		    	h.put("id_saudara", rs.getString("ID_SAUDARA")==null?"":rs.getString("ID_SAUDARA"));
		    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    	
		    	listWarisDefendan.addElement(h);
		    	bil++;
		      }
		      return listWarisDefendan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

	
//		public static void add_MaklumatKeputusan(String usid,String id_perbicaraan,String txdTarikhHantarAdd,
//				String txdTarikhTerimaAdd,String txtKeputusanAdd,String txtCatatanAdd,String dokumen_sokongan) throws Exception {
//			
//			Db db = null;
//			String sql = "";
//				try
//				{			
//					long id_borangj = DB.getNextID("TBLPPKBORANGJDTL_SEQ");							
//					String TH = "to_date('" + txdTarikhHantarAdd + "','dd/MM/yyyy')";
//					String TT = "to_date('" + txdTarikhTerimaAdd + "','dd/MM/yyyy')";		
//					
//				  //------------------------------- TBLPPKBORANGJ ------------------------------------
//				    
//					db = new Db();
//					Statement stmt = db.getStatement();
//					SQLRenderer r = new SQLRenderer();
//					r.add("id_borangj",id_borangj);
//					r.add("id_perbicaraan",id_perbicaraan);
//					r.add("tarikh_hantar_borangj", r.unquote(TH));
//					r.add("tarikh_terima_borangj", r.unquote(TT));
//					r.add("jenis_rujukan",0);		//* default = 0
//					r.add("catatan2",txtKeputusanAdd);
//					r.add("catatan3",txtCatatanAdd);
//					r.add("catatan4",dokumen_sokongan);
//					r.add("id_masuk",usid);
//					r.add("tarikh_masuk",r.unquote("sysdate"));
//
//					sql = r.getSQLInsert("Tblppkborangj");		
//					myLogger.info("::KEPUTUSAN INSERT TBLPPKBORANGJDTL :: = "+sql);
//					stmt.executeUpdate(sql);				
//					
//				}finally {
//					if(db != null) db.close();
//				}			
//		}


		public static Vector setROTSkeputusan(String id_perbicaraan) throws Exception  {
			
		    Db db = null;
		    listRotsKeputusan.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("J.TARIKH_HANTAR_BORANGJ");
		      r.add("J.TARIKH_TERIMA_BORANGJ"); 
		      r.add("J.CATATAN2"); 
		      r.add("J.CATATAN3"); 				      
		      r.add("J.CATATAN4");
		      r.add("J.CATATAN5");
  		      
			  r.add("J.ID_PERBICARAAN",r.unquote("BC.ID_PERBICARAAN"));

		      r.add("BC.ID_PERBICARAAN", id_perbicaraan, "=");

		      sql = r.getSQLSelect("TBLPPKBORANGJ J, TBLPPKPERBICARAAN BC ");
		      myLogger.info("SQL ROTS KEPUTUSAN 3333333333 = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;			      

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("tarikh_hantar_borangj", rs.getDate("TARIKH_HANTAR_BORANGJ")==null?"":Format.format(rs.getDate("TARIKH_HANTAR_BORANGJ")));
		        h.put("tarikh_terima_borangj", rs.getDate("TARIKH_TERIMA_BORANGJ")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_BORANGJ")));
		    	h.put("catatan2", rs.getString("CATATAN2")==null?"":rs.getString("CATATAN2"));
		    	h.put("catatan3", rs.getString("CATATAN3")==null?"":rs.getString("CATATAN3"));
		    	h.put("catatan4", rs.getString("CATATAN4")==null?"":rs.getString("CATATAN4"));
		    	h.put("catatan5", rs.getString("CATATAN5")==null?"":rs.getString("CATATAN5"));
		    	
		    	listRotsKeputusan.addElement(h);
		    	bil++;
		      }
		      return listRotsKeputusan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}

		//* UPDATE PERMOHONAN - ROTS
		
		public static void updateROTSmaklumat(String idpermohonan,String id_perbicaraan,String usid,String idBorangJ,String txdTarikhRujukanAdd,
	    		String txtFaktaGuamanAdd,String txtPendapatAdd,String socPegawai,String id_pejabat,String socNegeri) throws Exception {
		    
			Db db = null;
		    String sql = ""; 
		    try
		    {		    	
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";				
				  
			    db = new Db();
			    //------ UPDATE TBLPPKBORANGJ
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_borangj", idBorangJ);
			    r.update("id_perbicaraan", id_perbicaraan);		    
			    
			    r.add("tarikh_mohon", r.unquote(TR)); 
			    r.add("id_negerimahkamah",socNegeri);
			    r.add("id_mahkamah",id_pejabat);
			    r.add("catatan1",txtFaktaGuamanAdd);
			    r.add("keputusan_mahkamah",txtPendapatAdd);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("Tblppkborangj");
			    myLogger.info("SQL UPDATE TBLPPKBORANGJ = "+sql);
			    stmt.executeUpdate(sql);
			    
		    	}finally {
		    		if (db != null) db.close();
		    	}
	    	}


		public static void add_maklumat_tangguh(String usid,String idpermohonan,String id_perbicaraan,String txdTarikhPerintah,
				String EDITsocPegawaiPengendali,String flag_jenis_keputusan,String txtTempoh,String flag_tangguh,
				String txtCatatanTangguh,String txtPendapatTangguh) throws Exception {
			
			Db db = null;
			String sql = "";
			String sql2 = "";
			Connection conn = null;
				try
				{	
				    
					db = new Db();
					conn = db.getConnection();
				    conn.setAutoCommit(false);
				    
					long id_perintah = DB.getNextID(db,"TBLPPKPERINTAH_SEQ");
					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";		
					
				  //------------------------------- TBLPPKPERINTAH ------------------------------------

					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("id_perintah",id_perintah);
					r.add("id_perbicaraan",id_perbicaraan);
					r.add("tarikh_perintah", r.unquote(TTP));
					r.add("id_unitpsk",EDITsocPegawaiPengendali);
					r.add("flag_jenis_keputusan",flag_jenis_keputusan);
					r.add("tempoh_tunggu_faraid",txtTempoh);
					r.add("flag_tangguh",flag_tangguh);
					r.add("sebab_tangguh",txtCatatanTangguh);
					r.add("keputusan_mahkamah",txtPendapatTangguh);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					myLogger.info("INSERT TANGGUH TBLPPKPERINTAH = "+sql);
					sql = r.getSQLInsert("Tblppkperintah");							
					stmt.executeUpdate(sql);	
					conn.commit();
					
				} catch (SQLException se) {
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	se.printStackTrace();
			    	throw new Exception("Ralat Kemasukan Maklumat Tangguh Perbicaraan:"+se.getMessage());
			    }finally {
			    	if (conn != null) conn.close();
					if (db != null)	db.close();
				}		
		}


		public static void edit_status_BicaraTangguh(String idpermohonan,String idsuburusanstatusfail,String usid) throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {
		    	String id_status = "44"; /* STATUS: TANGGUH */
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idpermohonan);
			    
			    r.add("id_status", 44);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("Tblppkpermohonan");
			    myLogger.info("SQL STATUS TANGGUH = "+sql);
			    stmt.executeUpdate(sql);
			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}

		public static void edit_BicaraTangguhStatusTblrujsuburusanstatusfail(String idpermohonan,String id_perbicaraan,
				String usid,String idsuburusanstatusfail,String idFail) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";	   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");	
				db = new Db();		
					
				//** update TBLRUJSUBURUSANSTATUSFAIL
				  				  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("UPDATE TANGGUH TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);	
				  stmt2.executeUpdate(sql2);
				  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL 
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idpermohonan);
					r.add("id_suburusanstatus",374);
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));					

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
					myLogger.info("INSERT TANGGUH TBLRUJSUBURUSANSTATUSFAIL --> "+sql);	
					stmt.executeUpdate(sql);			  				  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }


		public static void edit_BicaraTangguhStatusTblrujsuburusanstatusfail17(String idpermohonan,String id_perbicaraan,
				String usid,String idsuburusanstatusfail,String idFail) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");							

				db = new Db();		
					
				//** update TBLRUJSUBURUSANSTATUSFAIL
				  				  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("TANGGUH TBLRUJSUBURUSANSTATUSFAIL 17--> "+sql2);	
				  stmt2.executeUpdate(sql2);				  
				  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL 
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idpermohonan);
					r.add("id_suburusanstatus",420);
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));					

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
					myLogger.info("BICARA TANGGUH TBLRUJSUBURUSANSTATUSFAIL 17 --> "+sql);	
					stmt.executeUpdate(sql);
				  				  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }


		public Vector setIDPejabatMahkamah(String id_perintah,String id_perbicaraan) throws Exception  {
			
		    Db db = null;
		    listgetidpejmhkmh.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("A.ID_PEJABATMAHKAMAH");
		      r.add("A.FLAG_TANGGUH"); 
		      r.add("A.FLAG_JENIS_KEPUTUSAN");

		      r.add("A.ID_PERINTAH", id_perintah, "=");
		      r.add("A.ID_PERBICARAAN", id_perbicaraan, "=");

		      sql = r.getSQLSelect("TBLPPKPERINTAH A");
		      myLogger.info("SQL SETIDPEJABATMAHKAMAH = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_pejabatmahkamah", rs.getString("ID_PEJABATMAHKAMAH")==null?"":rs.getString("ID_PEJABATMAHKAMAH"));
		    	h.put("flag_tangguh", rs.getString("FLAG_TANGGUH")==null?"":rs.getString("FLAG_TANGGUH"));
		    	h.put("flag_jenis_keputusan", rs.getString("FLAG_JENIS_KEPUTUSAN")==null?"":rs.getString("FLAG_JENIS_KEPUTUSAN"));
		    	
		    	listgetidpejmhkmh.addElement(h);
		    	bil++;
		      }
		      return listgetidpejmhkmh;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

		public Vector getIdPerintah(String id_perbicaraan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("B.ID_PERINTAH");
		      r.add("A.ID_KEPUTUSANPERMOHONAN");
		      
		      r.add("A.ID_PERBICARAAN",r.unquote("B.ID_PERBICARAAN"));
		      
		      r.add("A.ID_PERBICARAAN",id_perbicaraan);
		      
		      sql = r.getSQLSelect("TBLPPKPERBICARAAN A, TBLPPKPERINTAH B");
		      //myLogger.info("SQL GET ID PERINTAH = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		    	h.put("id_perintah", rs.getString("ID_PERINTAH")==null?"":rs.getString("ID_PERINTAH"));
		    	h.put("id_keputusanpermohonan", rs.getString("ID_KEPUTUSANPERMOHONAN")==null?"":rs.getString("ID_KEPUTUSANPERMOHONAN"));
		        
		        v.addElement(h);
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		public static void updateBorangJ_updateTblppkperintahMufti(String idBorangJ, String usid, String id_perintah, String id_perbicaraan,
				String txdTarikhRujukanAdd, String idNegeriMufti, String jenis_rujukan, String txtnamapej, String txtAlamat1, String txtAlamat2,
				String txtAlamat3, String txtPoskod, String txtTelefon, String txtfax, String idUnitPSK, String txtFaktaGuamanAdd, 
				String txtPendapatAdd ) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {		
					
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";	
				
				//UPDATE TBLPPKPERINTAH
				    db = new Db();
				    Statement stmt2 = db.getStatement();
				    SQLRenderer r2 = new SQLRenderer();
				    r2.update("id_perintah", id_perintah);
				    r2.update("id_perbicaraan", id_perbicaraan);
				    
				    r2.add("flag_tangguh",5);
					r2.add("flag_jenis_keputusan",1);			/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);				/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",idUnitPSK);
				    r2.add("id_kemaskini",usid);
					r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
	
				    sql2 = r2.getSQLUpdate("tblppkperintah");
				    myLogger.info("SQL UPDATE MUFTI TBLPPKPERINTAH = "+sql2);
				    stmt2.executeUpdate(sql2);
			      
			    //UPDATE TBLPPKBORANGJ 
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.update("id_borangj", idBorangJ);				      
				      r.update("id_perbicaraan", id_perbicaraan);
				      
				      r.add("tarikh_mohon", r.unquote(TR));
				      r.add("id_negeri", idNegeriMufti);
				      r.add("nama_pejabat", txtnamapej); 
				      r.add("alamat1", txtAlamat1); 
				      r.add("alamat2", txtAlamat2); 
				      r.add("alamat3", txtAlamat3); 
				      r.add("poskod", txtPoskod); 
				      r.add("no_tel", txtTelefon); 
				      r.add("no_fax", txtfax); 
				      r.add("catatan1", txtFaktaGuamanAdd);
				      r.add("keputusan_mahkamah", txtPendapatAdd);
				      r.add("flag_rujukan", 2);					/* 1 - CATER PEJABAT MUFTI */
				      r.add("id_kemaskini",usid);
				      r.add("tarikh_kemaskini",r.unquote("sysdate"));
				      
				      sql = r.getSQLUpdate("TBLPPKBORANGJ");
				      myLogger.info("SQL EDIT BORANG J MUFTI = "+sql);
				      stmt.executeUpdate(sql);
				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}				
		
		
		
		
		public static void updateBorangJ_updateTblppkperintahMS(String idBorangJ,String usid,
				String id_perintah,String id_perbicaraan,String txdTarikhRujukanAdd,String idNegeriMahkamah,
				String jenis_rujukan,String idMahkamah,String idUnitPSK,String txtFaktaGuamanAdd,
				String txtPendapatAdd ) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {		
					
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";	
				
				//UPDATE TBLPPKPERINTAH
				    db = new Db();
				    Statement stmt2 = db.getStatement();
				    SQLRenderer r2 = new SQLRenderer();
				    r2.update("id_perintah", id_perintah);
				    r2.update("id_perbicaraan", id_perbicaraan);
				    
				    //r2.add("id_negeri",idNegeriMahkamah);
				    r2.add("flag_tangguh",5);
					r2.add("flag_jenis_keputusan",1);			/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);				/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",idUnitPSK);
					//r2.add("id_pejabatmahkamah",idMahkamah);
				    r2.add("id_kemaskini",usid);
					r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
	
				    sql2 = r2.getSQLUpdate("tblppkperintah");
				    myLogger.info("SQL UPDATE MS TBLPPKPERINTAH = "+sql2);
				    stmt2.executeUpdate(sql2);
			      
			    //UPDATE TBLPPKBORANGJ 
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.update("id_borangj", idBorangJ);				      
				      r.update("id_perbicaraan", id_perbicaraan);
				      
				      r.add("tarikh_mohon", r.unquote(TR));
				      r.add("id_negerimahkamah", idNegeriMahkamah);
				      r.add("id_mahkamah", idMahkamah); 
				      r.add("catatan1", txtFaktaGuamanAdd);
				      r.add("keputusan_mahkamah", txtPendapatAdd);
				      //r.add("jenis_rujukan", jenis_rujukan);
				      r.add("flag_rujukan", 1);					/* 1 - CATER MAHKAMAH SYARIAH */
				      r.add("id_kemaskini",usid);
				      r.add("tarikh_kemaskini",r.unquote("sysdate"));
				      
				      sql = r.getSQLUpdate("TBLPPKBORANGJ");
				      myLogger.info("SQL EDIT BORANG J MS = "+sql);
				      stmt.executeUpdate(sql);
				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}		

		
		public static void insertBorangJ_updateTblppkperintahMufti(String usid,String id_perintah,String id_perbicaraan,
				String txdTarikhRujukanAdd,String idNegeriMufti,String jenis_rujukan, String txtnamapej, String txtAlamat1,
				String txtAlamat2,String txtAlamat3,String txtPoskod,String idBandar,
				String txtTelefon,String txtfax,String idUnitPSK,String txtFaktaGuamanAdd,String txtPendapatAdd ) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {		
				long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");					
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";	
				
				//UPDATE TBLPPKPERINTAH
				    db = new Db();
				    Statement stmt2 = db.getStatement();
				    SQLRenderer r2 = new SQLRenderer();
				    r2.update("id_perintah", id_perintah);
				    r2.update("id_perbicaraan", id_perbicaraan);
				    
				    r2.add("flag_tangguh",5);
					r2.add("flag_jenis_keputusan",1);			/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);				/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",idUnitPSK);					
				    r2.add("id_kemaskini",usid);
					r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
	
				    sql2 = r2.getSQLUpdate("tblppkperintah");
				    myLogger.info("SQL UPDATE MUFTI TBLPPKPERINTAH = "+sql2);
				    stmt2.executeUpdate(sql2);
			      
			    //INSERT TBLPPKBORANGJ 
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_borangj", id_borangj);
				      r.add("id_perbicaraan", id_perbicaraan);
				      r.add("tarikh_mohon", r.unquote(TR));
				      r.add("id_negeri", idNegeriMufti);
				      r.add("nama_pejabat", txtnamapej); 
				      r.add("alamat1",txtAlamat1);
				      r.add("alamat2",txtAlamat2);
				      r.add("alamat3",txtAlamat3);
				      r.add("poskod",txtPoskod);
				      r.add("id_bandar",idBandar);
				      r.add("no_tel",txtTelefon);
				      r.add("no_fax",txtfax);
				      r.add("catatan1", txtFaktaGuamanAdd);
				      r.add("keputusan_mahkamah", txtPendapatAdd);
				      r.add("jenis_rujukan", jenis_rujukan);
				      r.add("flag_rujukan", 2);					/* 2 - CATER PEJABAT MUFTI */
				      r.add("id_masuk",usid);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      
				      sql = r.getSQLInsert("TBLPPKBORANGJ");
				      myLogger.info("SQL INSERT MUFTI BORANG J = "+sql);
				      stmt.executeUpdate(sql);
				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}				
		
		
		public static void insertBorangJ_updateTblppkperintahMS(String usid,
				String id_perintah,String id_perbicaraan,String txdTarikhRujukanAdd,String idNegeriMahkamah,
				String jenis_rujukan,String idMahkamah,String idUnitPSK,String txtFaktaGuamanAdd,
				String txtPendapatAdd ) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {		
				long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");
					
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";	
				
				//UPDATE TBLPPKPERINTAH
				    db = new Db();
				    Statement stmt2 = db.getStatement();
				    SQLRenderer r2 = new SQLRenderer();
				    r2.update("id_perintah", id_perintah);
				    r2.update("id_perbicaraan", id_perbicaraan);
				    
				    //r2.add("id_negeri",idNegeriMahkamah);
				    r2.add("flag_tangguh",5);
					r2.add("flag_jenis_keputusan",1);			/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);				/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",idUnitPSK);
					//r2.add("id_pejabatmahkamah",idMahkamah);
				    r2.add("id_kemaskini",usid);
					r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
	
				    sql2 = r2.getSQLUpdate("tblppkperintah");
				    myLogger.info("SQL UPDATE ROTS TBLPPKPERINTAH = "+sql2);
				    stmt2.executeUpdate(sql2);
			      
			    //INSERT TBLPPKBORANGJ 
//				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_borangj", id_borangj);
				      r.add("id_perbicaraan", id_perbicaraan);
				      r.add("tarikh_mohon", r.unquote(TR));
				      r.add("id_negerimahkamah", idNegeriMahkamah);
				      r.add("id_mahkamah", idMahkamah); 
				      r.add("catatan1", txtFaktaGuamanAdd);
				      r.add("keputusan_mahkamah", txtPendapatAdd);
				      r.add("jenis_rujukan", jenis_rujukan);
				      r.add("flag_rujukan", 1);					/* 1 - CATER MAHKAMAH SYARIAH */
				      r.add("id_masuk",usid);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      
				      sql = r.getSQLInsert("TBLPPKBORANGJ");
				      myLogger.info("SQL INSERT ROTS BORANG J = "+sql);
				      stmt.executeUpdate(sql);
				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}		
		

		public static void insertBorangJMufti( String usid,String id_perbicaraan, String txdTarikhRujukanAdd, String idNegeri,
				String jenis_rujukan, String txtnamapej, String txtAlamat1,String txtAlamat2,String txtAlamat3,String txtPoskod,String idBandar,
				String txtTelefon,String txtfax,String idUnitPSK,String txtFaktaGuamanAdd,String txtPendapatAdd ) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {
				long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
				long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");
				
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";

			    //INSERT TBLPPKPERINTAH
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_perintah",id_perintah);
					r2.add("id_perbicaraan",id_perbicaraan);
					r2.add("flag_tangguh",5);
					r2.add("flag_jenis_keputusan",1);			/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);				/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",idUnitPSK);
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));

					sql2 = r2.getSQLInsert("tblppkperintah");		
					myLogger.info(":: INSERT TBLPPKPERINTAH MUFTI :: = "+sql2);
					stmt2.executeUpdate(sql2);	
			
			      
					//INSERT TBLPPKBORANGJ 
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_borangj", id_borangj);
				      r.add("id_perbicaraan", id_perbicaraan);
				      r.add("tarikh_mohon", r.unquote(TR));
				      r.add("id_negeri", idNegeri); 
				      r.add("nama_pejabat", txtnamapej); 
				      r.add("alamat1", txtAlamat1);
				      r.add("alamat2", txtAlamat2);
				      r.add("alamat3", txtAlamat3);
				      r.add("poskod", txtPoskod);
				      r.add("id_bandar", idBandar);
				      r.add("no_tel", txtTelefon);
				      r.add("no_fax", txtfax);
				      r.add("catatan1", txtFaktaGuamanAdd);
				      r.add("keputusan_mahkamah", txtPendapatAdd);
				      r.add("jenis_rujukan", jenis_rujukan);
				      r.add("flag_rujukan", 2);					/* 2 - CATER PEJABAT MUFTI */
				      r.add("id_masuk",usid);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      
				      sql = r.getSQLInsert("TBLPPKBORANGJ");
				      myLogger.info("SQL INSERT BORANG J MUFTI = "+sql);
				      stmt.executeUpdate(sql);
				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}
				
		
		
		
		public static void insertBorangJMS( String usid, String id_perbicaraan,String txdTarikhRujukanAdd,
	    		String idNegeriMahkamah,String jenis_rujukan, String idMahkamah, String idUnitPSK, 
	    		String txtFaktaGuamanAdd,String txtPendapatAdd ) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {
				long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
				long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");
				
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";

			    //INSERT TBLPPKPERINTAH
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_perintah",id_perintah);
					r2.add("id_perbicaraan",id_perbicaraan);
					//r2.add("id_negeri",idNegeriMahkamah);
					r2.add("flag_tangguh",5);
					r2.add("flag_jenis_keputusan",1);			/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);				/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",idUnitPSK);
					//r2.add("id_pejabatmahkamah",idMahkamah);
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));

					sql2 = r2.getSQLInsert("tblppkperintah");		
					myLogger.info(":: INSERT TBLPPKPERINTAH :: = "+sql2);
					stmt2.executeUpdate(sql2);	
			
			      
					//INSERT TBLPPKBORANGJ 
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_borangj", id_borangj);
				      r.add("id_perbicaraan", id_perbicaraan);
				      r.add("tarikh_mohon", r.unquote(TR));
				      r.add("id_negerimahkamah", idNegeriMahkamah); 
				      r.add("id_mahkamah", idMahkamah); 
				      r.add("catatan1", txtFaktaGuamanAdd);
				      r.add("keputusan_mahkamah", txtPendapatAdd);
				      r.add("jenis_rujukan", jenis_rujukan);
				      r.add("flag_rujukan", 1);					/* 1 - CATER MAHKAMAH SYARIAH */
				      r.add("id_masuk",usid);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      
				      sql = r.getSQLInsert("TBLPPKBORANGJ");
				      myLogger.info("SQL INSERT BORANG J = "+sql);
				      stmt.executeUpdate(sql);
				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}
		
		
		
		
		//* Menunggu Keputusan Rujukan Mahkamah Syariah (ROTS)
		
		
		
		public static void insertBorangJ_updateTblppkperintah(String idpermohonan,String id_perbicaraan, 
				String usid,String id_perintah,String txdTarikhRujukanAdd,String txtFaktaGuamanAdd,
				String jenis_rujukan,String socPegawai,String socNegeri,String id_pejabat,
				String txtPendapatAdd) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {
				long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");	
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";		
				
				//UPDATE TBLPPKPERINTAH
				    db = new Db();
				    Statement stmt2 = db.getStatement();
				    SQLRenderer r2 = new SQLRenderer();
				    r2.update("id_perintah", id_perintah);
				    r2.update("id_perbicaraan", id_perbicaraan);
				    
					r2.add("tarikh_perintah", r2.unquote(TR));
					r2.add("flag_tangguh",5);
					r2.add("flag_jenis_keputusan",1);			/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);				/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",socPegawai);
				    r2.add("id_kemaskini",usid);
					r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
	
				    sql2 = r2.getSQLUpdate("tblppkperintah");
				    myLogger.info("SQL UPDATE ROTS TBLPPKPERINTAH = "+sql2);
				    stmt2.executeUpdate(sql2);
			      
			    //INSERT TBLPPKBORANGJ 
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_borangj", id_borangj);
				      r.add("id_perbicaraan", id_perbicaraan);
				      r.add("tarikh_mohon", r.unquote(TR));
				      r.add("id_negerimahkamah", socNegeri); 
				      r.add("id_mahkamah", id_pejabat); 
				      r.add("catatan1", txtFaktaGuamanAdd);
				      r.add("keputusan_mahkamah", txtPendapatAdd);
				      r.add("jenis_rujukan", jenis_rujukan);
				      r.add("id_masuk",usid);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      
				      sql = r.getSQLInsert("TBLPPKBORANGJ");
				      myLogger.info("SQL INSERT BORANG J = "+sql);
				      stmt.executeUpdate(sql);
				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}


		//* Menunggu Keputusan Rujukan Mahkamah Syariah (ROTS)
		public static void insertBorangJ(String id_perbicaraan,String usid,String socNegeri,
	    		String txdTarikhRujukanAdd,String txtFaktaGuamanAdd,String txtPendapatAdd,String jenis_rujukan,
	    		String socPegawai,String id_pejabat) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {
				long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
				long id_borangj = DB.getNextID("TBLPPKBORANGJ_SEQ");					
				String TR = "to_date('" + txdTarikhRujukanAdd + "','dd/MM/yyyy')";		
				
			    //INSERT TBLPPKPERINTAH
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_perintah",id_perintah);
					r2.add("id_perbicaraan",id_perbicaraan);
					r2.add("tarikh_perintah", r2.unquote(TR));
					r2.add("flag_tangguh",5);
					r2.add("flag_jenis_keputusan",1);			/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);				/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",socPegawai);
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));

					sql2 = r2.getSQLInsert("tblppkperintah");		
					myLogger.info(":: INSERT TBLPPKPERINTAH :: = "+sql2);
					stmt2.executeUpdate(sql2);				
			      
					//INSERT TBLPPKBORANGJ 
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_borangj", id_borangj);
				      r.add("id_perbicaraan", id_perbicaraan);
				      r.add("tarikh_mohon", r.unquote(TR));
				      r.add("id_negerimahkamah", socNegeri); 
				      r.add("id_mahkamah", id_pejabat); 
				      r.add("catatan1", txtFaktaGuamanAdd);
				      r.add("keputusan_mahkamah", txtPendapatAdd);
				      r.add("jenis_rujukan", jenis_rujukan);
				      r.add("id_masuk",usid);
				      r.add("tarikh_masuk",r.unquote("sysdate"));
				      
				      sql = r.getSQLInsert("TBLPPKBORANGJ");
				      myLogger.info("SQL INSERT BORANG J = "+sql);
				      stmt.executeUpdate(sql);
				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}


		//* Pertelingkahan Kolateral
		public static void insertkolateralmst_updateTblppkperintah(String idpermohonan,String usid,String id_perbicaraan,
				String id_perintah,String txdTarikhPerakuanAdd,String txdTarikhBicara,String txtMasaBicara,String socTempatBicara,
				String txtCatatanAdd,String socNegeriBicara,String socPegawaiPengendali) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {		
				long id_kolateralmst = DB.getNextID("TBLPPKKOLATERALMST_SEQ");	
				String TP = "to_date('" + txdTarikhPerakuanAdd + "','dd/MM/yyyy')";
				String TB = "to_date('" + txdTarikhBicara + "','dd/MM/yyyy')";	    	
				
				//UPDATE TBLPPKPERINTAH
				    db = new Db();
				    Statement stmt2 = db.getStatement();
				    SQLRenderer r2 = new SQLRenderer();
				    r2.update("id_perintah", id_perintah);
				    r2.update("id_perbicaraan", id_perbicaraan);
				    
					r2.add("tarikh_perintah", r2.unquote(TB));
					r2.add("flag_tangguh",6);
					r2.add("flag_jenis_keputusan",1);	/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);		/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					r2.add("id_unitpsk",socPegawaiPengendali);
					r2.add("id_pejabatmahkamah",socTempatBicara);
				    r2.add("id_kemaskini",usid);
					r2.add("tarikh_kemaskini",r2.unquote("sysdate"));				
	
				    sql2 = r2.getSQLUpdate("Tblppkperintah");
				    myLogger.info("UPDATE KOLATERAL TBLPPKPERINTAH = "+sql2);
				    stmt2.executeUpdate(sql2);
			      
			    //INSERT TBLPPKKOLATERALMST 
				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
			      
				    r.add("id_kolateralmst",id_kolateralmst);
				    r.add("id_perbicaraan",id_perbicaraan);
				    r.add("tarikh_bicara", r.unquote(TB));
				    r.add("tarikh_perakuan", r.unquote(TP));
				    r.add("masa_bicara", txtMasaBicara);
				    r.add("sebab_pertelingkahan",txtCatatanAdd);
				    r.add("id_negeribicara",socNegeriBicara);
				    r.add("id_unitpsk",socPegawaiPengendali);				    
				    r.add("id_masuk",usid);
				    r.add("tarikh_masuk",r.unquote("sysdate"));
			      
				    sql = r.getSQLInsert("TBLPPKKOLATERALMST");
				    myLogger.info("SQL INSERT ID_KOLATERALMST = "+sql);
				    stmt.executeUpdate(sql);

	    	} finally {
	    		if (db != null) db.close();
	    	}
	}

		public static void updateKolateral_updateTblppkperintah( String id_perbicaraan, String id_perintah, String usid, String IdKolateralmst, 
				String txdTarikhPerakuanAdd, String EDITsocPegawaiPengendali, String txdTarikhBicara, 
				String txtMasaBicara, String socTempatBicara, String socNegeri, String txtCatatanAdd ) throws Exception	{
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		    try
		    {	
				//long id_kolateralmst = DB.getNextID("TBLPPKKOLATERALMST_SEQ");	
				String TP = "to_date('" + txdTarikhPerakuanAdd + "','dd/MM/yyyy')";			
				String TB = "to_date('" + txdTarikhBicara + "','dd/MM/yyyy')";

				//UPDATE TBLPPKPERINTAH
				    db = new Db();
				    Statement stmt2 = db.getStatement();
				    SQLRenderer r2 = new SQLRenderer();
				    r2.update("id_perintah", id_perintah);
				    r2.update("id_perbicaraan", id_perbicaraan);
				    
					r2.add("tarikh_perintah", r2.unquote(TB));
					r2.add("flag_tangguh",6);
					r2.add("flag_jenis_keputusan",1);	/* ID utk TANGGUH */
					r2.add("id_jenisperintah",6);		/* ID utk PERINTAH MAHKAMAH TINGGI [TBLPPKRUJJENISPERINTAH] */
					//r2.add("id_unitpsk",EDITsocPegawaiPengendali);		
					r2.add("id_pejabatmahkamah",socTempatBicara);
				    r2.add("id_kemaskini",usid);
					r2.add("tarikh_kemaskini",r2.unquote("sysdate"));				
	
				    sql2 = r2.getSQLUpdate("tblppkperintah");
				    myLogger.info("SQL UPDATE TBLPPKPERINTAH KOLATERAL = "+sql2);
				    stmt2.executeUpdate(sql2);
			      
			    //UPDATE TBLPPKKOLATERALMST 

				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
			      
				    r.update("id_kolateralmst",IdKolateralmst);
				    
				    r.add("tarikh_bicara", r.unquote(TB));
				    r.add("tarikh_perakuan", r.unquote(TP));
				    r.add("masa_bicara", txtMasaBicara);
				    r.add("sebab_pertelingkahan",txtCatatanAdd);
				    r.add("id_negeribicara",socNegeri);
				    r.add("id_unitpsk",EDITsocPegawaiPengendali);				    
				    r.add("id_kemaskini",usid);
				    r.add("tarikh_kemaskini",r.unquote("sysdate"));
			      
				    sql = r.getSQLUpdate("TBLPPKKOLATERALMST");
				    myLogger.info("SQL UPDATE KOLATERALMST = "+sql);
				    stmt.executeUpdate(sql);

	    	} finally {
	    		if (db != null) db.close();
	    	}
	}		

		public static Vector setSenaraiWarisUpdate(String id_perbicaraan,String id_permohonansimati) throws Exception {
			
		    Db db = null;
		    listWarisUpdate.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("C.ID_OB");
		      r.add("D.NAMA_OB");
		      r.add("D.NO_KP_BARU");
		      r.add("D.ID_SAUDARA");
		      r.add("E.KETERANGAN");
		      r.add("D.ID_SAUDARA",r.unquote("E.ID_SAUDARA"));
		      r.add("A.ID_PERBICARAAN",r.unquote("B.ID_PERBICARAAN"));
		      r.add("B.ID_KOLATERALMST",r.unquote("C.ID_KOLATERALMST"));
		      r.add("D1.ID_OB",r.unquote("C.ID_OB"));
		      //r.add("C.JENIS_OB","PL");
		      r.add("A.ID_PERBICARAAN",id_perbicaraan);
		      
		      r.add("D1.ID_OB",r.unquote("D.ID_OB"));
		      r.add("D.ID_PERMOHONANSIMATI",id_permohonansimati);
		      
		      

		      sql = r.getSQLSelect("TBLPPKPERINTAH A, TBLPPKKOLATERALMST B, TBLPPKKOLATERALDTL C, " +
		      		" TBLPPKOB D1, TBLPPKOBPERMOHONAN D, TBLPPKRUJSAUDARA E ");
		      myLogger.info("-------------SQL SET SENARAI WARIS UPDATE = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);		      
		   
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("id_ob", rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
		        h.put("nama_ob", rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
		    	h.put("no_kp_baru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
		    	h.put("id_saudara", rs.getString("ID_SAUDARA")==null?"":rs.getString("ID_SAUDARA"));
		    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    	
		    	listWarisUpdate.addElement(h);
		    	bil++;
		      }
		      return listWarisUpdate;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}


		public static Hashtable setInfoBicaraList(String idpermohonan) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("p.id_Permohonan");
		      r.add("po.id_Pemohon");
		      r.add("bc.id_perbicaraan");
		      r.add("bc.id_unitpsk");
		      r.add("pr.nama_pegawai");
		      r.add("bc.tarikh_bicara");
		      r.add("p.id_fail");
		      r.add("bc.masa_bicara");

		      r.add("p.id_Pemohon",r.unquote("po.id_pemohon"));
		      r.add("pr.id_unitpsk",r.unquote("bc.id_unitpsk"));
		      r.add("BC.ID_KEPUTUSANPERMOHONAN",r.unquote("KP.ID_KEPUTUSANPERMOHONAN"));
		      r.add("KP.ID_PERMOHONAN",r.unquote("P.ID_PERMOHONAN"));

		      r.add("p.id_Permohonan",idpermohonan);
		      //r.add("bc.id_perbicaraan", id_perbicaraan, "=");
		      
		      sql = r.getSQLSelect("Tblppkpermohonan p, Tblppkpemohon po, Tblppkrujunit pr, tblppkperbicaraan bc, tblppkkeputusanpermohonan kp");
		      myLogger.info("ELLY TESTING 123 == "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
			        h.put("id_Permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			    	h.put("id_Pemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
			    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
			    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
			    	h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
			    	h.put("tarikh_bicara", rs.getDate("tarikh_bicara")==null?"":Format.format(rs.getDate("tarikh_bicara")));
			    	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
			    	h.put("masa_bicara", rs.getString("masa_bicara")==null?"":rs.getString("masa_bicara"));
		      }
		      return h;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

		
		public static void setViewPerintahList(String idpermohonan) throws Exception {
			Db db = null;
			MaklumatPerintahList.clear();
			
			String sql = " SELECT c.id_permohonan,a.id_perbicaraan,b.id_perintah,a.id_keputusanpermohonan, b.id_unitpsk, b.tarikh_perintah,  "+
						 " b.flag_jenis_keputusan, b.catatan, b.flag_tangguh, b.flag_batal,b.sebab_batal, b.sebab_tangguh, b.keputusan_mahkamah, a.bil_bicara "+					 
						 " FROM tblppkperbicaraan a, tblppkperintah b, tblppkkeputusanpermohonan c,tblppkpermohonan d "+
						 " WHERE a.id_perbicaraan = b.id_perbicaraan AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND A.ID_KEPUTUSANPERMOHONAN = C.ID_KEPUTUSANPERMOHONAN "+
						 " AND c.id_permohonan = "+ idpermohonan +" ";
			
			myLogger.info("SQL test :: "+sql);
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
		
				String catatan = "";
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_permohonan"));
					h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"-":rs.getString("id_perbicaraan"));
					h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
					h.put("id_keputusanpermohonan", rs.getString("id_keputusanpermohonan")==null?"":rs.getString("id_keputusanpermohonan"));
					h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
					h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
					h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
					//h.put("catatan", rs.getString("catatan")==null?"-":rs.getString("catatan"));
					
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					
//					catatan = rs.getString("catatan");					
//					myLogger.debug("CATATAN:"+catatan);
//					catatan = catatan.replaceAll("\\<.*?\\>", "");
//					myLogger.debug("CATATAN xx:"+catatan);
					
					h.put("flag_tangguh", rs.getString("flag_tangguh")==null?"":rs.getString("flag_tangguh"));
					h.put("flag_batal", rs.getString("flag_batal")==null?"":rs.getString("flag_batal"));
					h.put("sebab_batal", rs.getString("sebab_batal")==null?"":rs.getString("sebab_batal"));
					h.put("sebab_tangguh", rs.getString("sebab_tangguh")==null?"":rs.getString("sebab_tangguh"));
					h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
					h.put("bil_bicara", rs.getString("bil_bicara")==null?"-":rs.getString("bil_bicara"));
					
					MaklumatPerintahList.addElement(h);
					bil++;	
				}
			}finally {
				if(db != null) db.close();
			}	
		}

		public static void setViewNotis(String idpermohonan) throws Exception {
			Db db = null;
			MaklumatNotis.clear();
			
			String sql = " SELECT c.id_permohonan,a.id_keputusanpermohonan, "+
						 " a.bil_bicara "+					 
						 " FROM tblppkperbicaraan a, tblppkkeputusanpermohonan c,tblppkpermohonan d "+
						 " WHERE C.ID_PERMOHONAN = D.ID_PERMOHONAN AND A.ID_KEPUTUSANPERMOHONAN = C.ID_KEPUTUSANPERMOHONAN "+
						 " AND c.id_permohonan = "+ idpermohonan +" ";
			myLogger.info("setViewNotis : "+sql);
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_permohonan"));
					//h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"-":rs.getString("id_perbicaraan"));
					//h.put("id_perintah", rs.getString("id_perintah")==null?"-":rs.getString("id_perintah"));
					h.put("id_keputusanpermohonan", rs.getString("id_keputusanpermohonan")==null?"-":rs.getString("id_keputusanpermohonan"));
					//h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"-":rs.getString("id_unitpsk"));
					//h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
					//h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"-":rs.getString("flag_jenis_keputusan"));
					//h.put("catatan", rs.getString("catatan")==null?"-":rs.getString("catatan"));
					//h.put("flag_tangguh", rs.getString("flag_tangguh")==null?"-":rs.getString("flag_tangguh"));
					//h.put("flag_batal", rs.getString("flag_batal")==null?"-":rs.getString("flag_batal"));
					//h.put("sebab_batal", rs.getString("sebab_batal")==null?"-":rs.getString("sebab_batal"));
					//h.put("sebab_tangguh", rs.getString("sebab_tangguh")==null?"-":rs.getString("sebab_tangguh"));
					//h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"-":rs.getString("keputusan_mahkamah"));
					h.put("bil_bicara", rs.getString("bil_bicara")==null?"-":rs.getString("bil_bicara"));
					
					MaklumatNotis.addElement(h);
					bil++;	
				}
			}finally {
				if(db != null) db.close();
			}	
		}

		public static Vector setInfoPerintahList(String idpermohonan) throws Exception {
			
		    Db db = null;
		    listPerintahDefault.clear();
		    
			String sql = "SELECT c.id_permohonan, a.id_perbicaraan, b.id_perintah,a.id_keputusanpermohonan, b.id_unitpsk, b.tarikh_perintah, a.bil_bicara, "+
			 " b.flag_jenis_keputusan, b.catatan, b.flag_tangguh, b.flag_batal,b.sebab_batal, b.sebab_tangguh, b.keputusan_mahkamah, a.bil_bicara," +
			 " b.check_kiv,b.date_kiv,b.catatan_kiv  "+					 
			 " FROM tblppkperbicaraan a, tblppkperintah b, tblppkkeputusanpermohonan c, TBLPPKPERMOHONAN d "+
			 " WHERE a.id_perbicaraan = b.id_perbicaraan AND D.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_KEPUTUSANPERMOHONAN = A.ID_KEPUTUSANPERMOHONAN "+
			 " AND A.bil_bicara = (select max(bil_bicara) from tblppkperbicaraan where ID_KEPUTUSANPERMOHONAN=C.ID_KEPUTUSANPERMOHONAN) AND c.id_permohonan = '"+ idpermohonan +"' ";						
			
			myLogger.info("SQL SETINFOPERINTAHLIST ::"+sql); 
			
		    try {
		    	
		      db = new Db();
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
			  
		      Hashtable h;
		      int bil = 1;
			      
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
		    	h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
		    	h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
		    	h.put("id_keputusanpermohonan", rs.getString("id_keputusanpermohonan")==null?"":rs.getString("id_keputusanpermohonan"));
		    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
		    	h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
		    	h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
		    	h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
		    	h.put("flag_tangguh", rs.getString("flag_tangguh")==null?"":rs.getString("flag_tangguh"));
		    	h.put("flag_batal", rs.getString("flag_batal")==null?"":rs.getString("flag_batal"));
		    	h.put("sebab_batal", rs.getString("sebab_batal")==null?"":rs.getString("sebab_batal"));
		    	h.put("sebab_tangguh", rs.getString("sebab_tangguh")==null?"":rs.getString("sebab_tangguh"));
		    	h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
		    	h.put("bil_bicara", rs.getString("bil_bicara")==null?"":rs.getString("bil_bicara"));
		    	
		    	h.put("check_kiv", rs.getString("check_kiv")==null?"":rs.getString("check_kiv"));
		    	h.put("date_kiv", rs.getDate("date_kiv")==null?"":Format.format(rs.getDate("date_kiv")));
		    	h.put("catatan_kiv", rs.getString("catatan_kiv")==null?"":rs.getString("catatan_kiv"));
		    	
		    	
		    	listPerintahDefault.addElement(h);
		    	bil++;
		      }
		      return listPerintahDefault;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}


		 public static Vector getDataPerintahViewList() {
				return listPerintahDefault;
		 }


		public static Vector setInfoKolateral(String id_perbicaraan) throws Exception {
			
		    Db db = null;
		    listKolateral.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("bc.id_perbicaraan");
		      r.add("p.id_perintah");
		      r.add("k.sebab_pertelingkahan");
		      r.add("k.tarikh_bicara");
		      r.add("p.id_unitpsk");
		      r.add("p.tarikh_perintah");
		      r.add("p.id_pejabatmahkamah");
		      r.add("bc.masa_bicara");	
		      r.add("bc.id_negeribicara");     

		      r.add("bc.id_perbicaraan",r.unquote("p.id_perbicaraan"));
		      r.add("bc.id_perbicaraan",r.unquote("k.id_perbicaraan"));

		      //r.add("c.id_permohonan",id);
		      r.add("a.id_perbicaraan", id_perbicaraan, "=");

		      sql = r.getSQLSelect("tblppkperbicaraan bc, tblppkperintah p, tblppkkolateralmst k ");
		      //myLogger.info("SQL KOLATERAL = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
		    	h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
		    	h.put("sebab_pertelingkahan", rs.getString("sebab_pertelingkahan")==null?"":rs.getString("sebab_pertelingkahan"));
		    	h.put("tarikh_bicara", rs.getDate("tarikh_bicara")==null?"":Format.format(rs.getDate("tarikh_bicara")));
		    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
		    	h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
		    	h.put("id_pejabatmahkamah", rs.getString("id_pejabatmahkamah")==null?"":rs.getString("id_pejabatmahkamah"));
		    	h.put("masa_bicara", rs.getString("masa_bicara")==null?"":rs.getString("masa_bicara"));
		    	h.put("id_negeribicara", rs.getString("id_negeribicara")==null?"":rs.getString("id_negeribicara"));

		    	listKolateral.addElement(h);
		    	bil++;
		      }
		      return listKolateral;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}


		public static Vector setViewTangguhList(String idpermohonan, String id_perbicaraan) throws Exception {
			Db db = null;
			MaklumatTangguh.clear();
			
			String sql = " SELECT c.id_permohonan, a.id_perbicaraan, b.id_perintah,a.id_keputusanpermohonan, b.id_unitpsk, b.tarikh_perintah,  "+
						 " b.flag_jenis_keputusan, b.catatan, b.flag_tangguh, b.flag_batal,b.sebab_batal, b.sebab_tangguh, b.keputusan_mahkamah, a.bil_bicara "+					 
						 " FROM tblppkperbicaraan a, tblppkperintah b, tblppkkeputusanpermohonan c,tblppkpermohonan d "+
						 " WHERE a.id_perbicaraan = b.id_perbicaraan AND C.ID_PERMOHONAN = D.ID_PERMOHONAN AND A.ID_KEPUTUSANPERMOHONAN = C.ID_KEPUTUSANPERMOHONAN "+
						 " AND c.id_permohonan = "+ idpermohonan +" " ;
						 	//	"AND b.id_perbicaraan = '"+ id_perbicaraan +"'  ";
			myLogger.info("SQL ELLY TEST:: "+sql);
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_permohonan"));
					h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
					h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
					h.put("id_keputusanpermohonan", rs.getString("id_keputusanpermohonan")==null?"-":rs.getString("id_keputusanpermohonan"));
					h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
					h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
					h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					h.put("flag_tangguh", rs.getString("flag_tangguh")==null?"":rs.getString("flag_tangguh"));
					h.put("flag_batal", rs.getString("flag_batal")==null?"":rs.getString("flag_batal"));
					h.put("sebab_batal", rs.getString("sebab_batal")==null?"":rs.getString("sebab_batal"));
					h.put("sebab_tangguh", rs.getString("sebab_tangguh")==null?"":rs.getString("sebab_tangguh"));
					h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
					h.put("bil_bicara", rs.getString("bil_bicara")==null?"":rs.getString("bil_bicara"));
					
					MaklumatTangguh.addElement(h);
					bil++;	
				}
				return MaklumatTangguh;
			}finally {
				if(db != null) db.close();
			}	
		}
	
		 public static Vector getAlamatTempatBicara(int idBicara)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			     
			      sql = "SELECT k.alamat1, k.alamat2, k.alamat3, k.poskod, k.id_negeri, k.no_tel, k.no_fax ";
			      sql +="FROM Tblrujpejabatjkptg k ";
			      sql +="WHERE k.id_pejabatjkptg = " +idBicara ;
			      

			      //System.out.println("[SQL]:Alamat Tempat bicara = " +sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector list = new Vector();
			      
			      Hashtable h = null;
			     
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			    	  h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
			    	  h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
			    	  list.addElement(h);
			    	  
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
			  }//Get alamat tempat bicara	
		 
		 
		 
			//-- Get alamat mahkamah 
		 	//-- 130909
		 
//		 public static Vector getAlamatMahkamah(int idNegeri)throws Exception {
//			    Db db = null;
//			    String sql = "";
//			    try {
//			      db = new Db();
//			      Statement stmt = db.getStatement();
//			     
//			      sql = "SELECT k.alamat1, k.alamat2, k.alamat3, k.poskod, k.id_negeri ";
//			      sql +="FROM Tblrujpejabatjkptg k ";
//			      sql +="WHERE k.id_pejabatjkptg = " +idBicara ;
//			      
//
//			      System.out.println("[SQL]:Alamat Tempat Mahkamah = " +sql);
//			      ResultSet rs = stmt.executeQuery(sql);
//			      Vector list = new Vector();
//			      
//			      Hashtable h = null;
//			     
//			      while (rs.next()) {
//			    	  h = new Hashtable();
//			    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
//			    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
//			    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
//			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
//			    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
//			    	  list.addElement(h);
//			    	  
//			      }
//			      return list;
//			    } finally {
//			      if (db != null) db.close();
//			    }
//			  }//Get alamat tempat bicara
//		 
		 

		//-- Get alamat mahkamah --//
		//-- 130909
		 
		 public static Vector getAlamatMahkamah(String idNegeriTempatBicara)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			     
//			      sql = "SELECT pj.id_pejabat, pj.kod_pejabat, pj.nama_pejabat, pj.alamat1,pj.alamat2, pj.alamat3, pj.poskod, pj.no_tel, pj.no_fax, da.nama_daerah, pj.id_negeri ";
//			      sql +="FROM tblrujpejabat pj, tblrujdaerah da ";
//			      sql +="WHERE pj.id_jenispejabat = 8 AND pj.id_daerah = da.id_daerah AND pj.id_negeri = " +idNegeriTempatBicara+ " ORDER BY LPAD (id_pejabat, 10) ";			     
			      
			      sql = "SELECT pj.id_pejabat, pj.kod_pejabat, pj.nama_pejabat, pj.alamat1,pj.alamat2, pj.alamat3, pj.poskod, pj.no_tel, pj.no_fax, da.nama_daerah, pj.id_negeri, n.nama_negeri ";
			      sql +="FROM tblrujpejabat pj, tblrujdaerah da, tblrujnegeri n  ";
			      sql +="WHERE pj.id_jenispejabat = 8 AND pj.id_negeri = n.id_negeri AND pj.id_daerah = da.id_daerah AND pj.id_negeri = " +idNegeriTempatBicara+ " ORDER BY LPAD (id_pejabat, 10) ";
			      
			      
			      myLogger.info("[SQL]:Alamat Tempat Mahkamah elly = " +sql);
			      
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector list = new Vector();
			      
			      Hashtable h = null;
			     
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("id_pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
			    	  h.put("kod_pejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));
			    	  h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
			    	  h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
			    	  h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			    	  list.addElement(h);
			    	  
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
			  }//Get alamat tempat bicara		 
		 

			//-- Get alamat mahkamah --//
			//-- 230909
			 
			 public static Vector getAlamatMahkamahSyariah(String idNegeriTempatBicara)throws Exception {
				    Db db = null;
				    String sql = "";
				    listMS.clear();
				    try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				     			      
				      sql = "SELECT pj.id_pejabat, pj.kod_pejabat, pj.nama_pejabat, pj.alamat1,pj.alamat2, pj.alamat3, pj.poskod, pj.no_tel, pj.no_fax, da.nama_daerah, pj.id_negeri, n.nama_negeri ";
				      sql +="FROM tblrujpejabat pj, tblrujdaerah da, tblrujnegeri n  ";
				      sql +="WHERE pj.id_jenispejabat = 41 AND pj.id_negeri = n.id_negeri AND pj.id_daerah = da.id_daerah AND pj.id_negeri = " +idNegeriTempatBicara+ " ORDER BY LPAD (id_pejabat, 10) ";
		      
				     // myLogger.info("[SQL]:Alamat Tempat Mahkamah Syariah = " +sql);
				      
				      ResultSet rs = stmt.executeQuery(sql);
				      Vector listMS = new Vector();
				      
				      Hashtable h = null;
				     
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  h.put("id_pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
				    	  h.put("kod_pejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));
				    	  h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
				    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				    	  h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
				    	  h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
				    	  h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
				    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				    	  listMS.addElement(h);
				    	  
				      }
				      
				      return listMS;
				      
				    } finally {
				      if (db != null) db.close();
				    }
				  }//Get alamat tempat bicara		 
		 

		 
			//-- Get alamat mahkamah --//
		 public static Vector getAlamatTempatMahkamah(String idNegeriTempatBicara,String idPejabat)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			     
			      sql = "SELECT pj.id_pejabat, pj.kod_pejabat, pj.nama_pejabat, pj.alamat1,pj.alamat2, pj.alamat3, pj.poskod, pj.no_tel, pj.no_fax, da.nama_daerah, pj.id_negeri ";
			      sql +="FROM tblrujpejabat pj, tblrujdaerah da ";
			      sql +="WHERE pj.id_jenispejabat = 8 AND pj.id_daerah = da.id_daerah AND pj.id_negeri = " +idNegeriTempatBicara+ " AND pj.id_pejabat = " +idPejabat+ " ORDER BY LPAD (id_pejabat, 10) ";
			     
			     // myLogger.info("[SQL]:Alamat Tempat Mahkamah = " +sql);
			      
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector list = new Vector();
			      
			      Hashtable h = null;
			     
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("id_pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
			    	  h.put("kod_pejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));
			    	  h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
			    	  h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
			    	  h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			    	  list.addElement(h);
			    	  
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
			  }//CLOSE alamat tempat bicara		 

		 
			//-- Get alamat mahkamah syariah 230909 --//
		 
		 public static Vector getAlamatTempatMahkamahSyariah(String idNegeriTempatBicara,String idPejabat)throws Exception {
			    Db db = null;
			    String sql = "";
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			     
			      sql = "SELECT pj.id_pejabat, pj.kod_pejabat, pj.nama_pejabat, pj.alamat1,pj.alamat2, pj.alamat3, pj.poskod, pj.no_tel, pj.no_fax, da.nama_daerah, pj.id_negeri ";
			      sql +="FROM tblrujpejabat pj, tblrujdaerah da ";
			      sql +="WHERE pj.id_jenispejabat = 41 AND pj.id_daerah = da.id_daerah AND pj.id_negeri = " +idNegeriTempatBicara+ " AND pj.id_pejabat = " +idPejabat+ " ORDER BY LPAD (id_pejabat, 10) ";
			     
			     // myLogger.info("[SQL]:Alamat Tempat Mahkamah = " +sql);
			      
			      ResultSet rs = stmt.executeQuery(sql);
			      Vector list = new Vector();
			      
			      Hashtable h = null;
			     
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("id_pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
			    	  h.put("kod_pejabat", rs.getString("kod_pejabat")==null?"":rs.getString("kod_pejabat"));
			    	  h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
			    	  h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
			    	  h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			    	  list.addElement(h);
			    	  
			      }
			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
			  }//CLOSE alamat tempat mahkamah syariah		 
		 
		 

		public Vector setTangguhROTS(String id_perbicaraan, String id_perintah, String idBorangJ) throws Exception  {
		    Db db = null;
		    listTangguhROTS.clear();
		    String sql = "";
		    
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("a.id_perintah");
		      r.add("a.id_perbicaraan"); 
		      r.add("a.flag_jenis_keputusan");     
		      r.add("a.id_unitpsk");    
		      r.add("b.nama_pegawai");  
		      r.add("a.jenis_keluar_perintah");  
		      r.add("a.tarikh_perintah");
		      r.add("j.id_mahkamah");
		      r.add("j.id_negerimahkamah");
		      r.add("d.nama_negeri");
		      r.add("c.id_daerah");
		      r.add("e.nama_daerah");
		      r.add("c.nama_pejabat");
		      r.add("c.alamat1");
		      r.add("c.alamat2");
		      r.add("c.alamat3");
		      r.add("c.poskod");
		      r.add("c.no_tel");
		      r.add("c.no_fax");
		      r.add("j.catatan1");
		      r.add("j.keputusan_mahkamah");
		      r.add("j.jenis_rujukan");
		      r.add("j.id_borangj");
		      r.add("j.tarikh_hantar_borangj");
		      r.add("j.tarikh_terima_borangj");
		      r.add("j.catatan2");
		      r.add("j.catatan3");
		      r.add("j.catatan4");
		      r.add("j.catatan5");
		      r.add("a.flag_tangguh");
		      r.add("j.tarikh_mohon");
		      r.add("j.flag_rujukan");
		      
		      r.add("a.id_unitpsk",r.unquote("b.id_unitpsk"));
		      r.add("j.id_mahkamah",r.unquote("c.id_pejabat"));
		      r.add("d.id_negeri",r.unquote("c.id_negeri"));
		      r.add("e.id_daerah",r.unquote("c.id_daerah"));

		      r.add("a.id_perbicaraan", id_perbicaraan, "=");
		      r.add("a.id_perintah", id_perintah, "=");
		      r.add("j.id_borangj", idBorangJ, "=");

		      sql = r.getSQLSelect("Tblppkperintah a, Tblppkrujunit b, Tblrujpejabat c, Tblrujnegeri d, Tblrujdaerah e, Tblppkborangj j");
		      //myLogger.info("SQL TANGGUH ROTS LATEST = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
		    	h.put("flag_jenis_keputusan", rs.getString("flag_jenis_keputusan")==null?"":rs.getString("flag_jenis_keputusan"));
		    	h.put("id_unitpsk", rs.getString("id_unitpsk")==null?"":rs.getString("id_unitpsk"));
		    	h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
		    	h.put("jenis_keluar_perintah", rs.getString("jenis_keluar_perintah")==null?"":rs.getString("jenis_keluar_perintah"));
		    	h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
		    	h.put("id_mahkamah", rs.getString("id_mahkamah")==null?"":rs.getString("id_mahkamah"));
		    	h.put("id_negerimahkamah", rs.getString("id_negerimahkamah")==null?"":rs.getString("id_negerimahkamah"));
		    	h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
		    	h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
		    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		    	h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
		    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
		    	h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
		    	h.put("catatan1", rs.getString("catatan1")==null?"":rs.getString("catatan1"));
		    	h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
		    	h.put("jenis_rujukan", rs.getString("jenis_rujukan")==null?"":rs.getString("jenis_rujukan"));
		    	h.put("id_borangj", rs.getString("id_borangj")==null?"":rs.getString("id_borangj"));
		    	h.put("tarikh_hantar_borangj", rs.getDate("tarikh_hantar_borangj")==null?"":Format.format(rs.getDate("tarikh_hantar_borangj")));
		    	h.put("tarikh_terima_borangj", rs.getDate("tarikh_terima_borangj")==null?"":Format.format(rs.getDate("tarikh_terima_borangj")));
		    	h.put("catatan2", rs.getString("catatan2")==null?"":rs.getString("catatan2"));
		    	h.put("catatan3", rs.getString("catatan3")==null?"":rs.getString("catatan3"));
		    	h.put("catatan4", rs.getString("catatan4")==null?"":rs.getString("catatan4"));
		    	h.put("catatan5", rs.getString("catatan5")==null?"":rs.getString("catatan5"));
		    	h.put("flag_tangguh", rs.getString("flag_tangguh")==null?"":rs.getString("flag_tangguh"));
		    	h.put("tarikh_mohon", rs.getDate("tarikh_mohon")==null?"":Format.format(rs.getDate("tarikh_mohon")));
		    	h.put("flag_rujukan", rs.getString("flag_rujukan")==null?"":rs.getString("flag_rujukan"));
		    	
		    	listTangguhROTS.addElement(h);
		    	bil++;
		      }
		      return listTangguhROTS;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

		public static Vector getMaklumatDokumen(String idFail) throws Exception {

			Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("lam.id_lampiran");
		      r.add("lam.id_dokumen");
		      r.add("lam.content");
		      r.add("doc.tajuk_dokumen");
		      r.add("doc.catatan");   
		      r.add("lam.jenis_mime");

		      r.add("lam.id_dokumen",r.unquote("doc.id_dokumen"));

		      r.add("doc.id_fail",idFail);
		      
		      sql = r.getSQLSelect("tblpfdrujlampiran lam, tblpfddokumen doc ");
		    
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		      int bil = 1;
		      
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("bil", bil);
		    	h.put("id_lampiran", rs.getString("id_lampiran")==null?"":rs.getString("id_lampiran"));
		    	h.put("id_dokumen", rs.getString("id_dokumen")==null?"":rs.getString("id_dokumen"));
		    	h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	h.put("tajuk_dokumen", rs.getString("tajuk_dokumen")==null?"":rs.getString("tajuk_dokumen"));
		    	h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
		    	h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	
		        v.addElement(h);
		        bil++;
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}		

		public static void updateKeputusanROTSmaklumat(String idpermohonan,String usid,
	    		String id_perbicaraan,String idBorangJ,String txdTarikhHantarAdd,String txdTarikhTerimaAdd,
	    		String txtKeputusanAdd,String txtCatatanAdd) throws Exception {
		    
			Db db = null;
		    String sql = "";		    
		    try
		    {	
				String TR = "to_date('" + txdTarikhHantarAdd + "','dd/MM/yyyy')";
				String TT = "to_date('" + txdTarikhTerimaAdd + "','dd/MM/yyyy')";
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_borangj", idBorangJ);
			    r.update("id_perbicaraan", id_perbicaraan);		    
			    
			    r.add("tarikh_hantar_borangj", r.unquote(TR));
			    r.add("tarikh_terima_borangj", r.unquote(TT)); 
			    r.add("catatan4", txtKeputusanAdd);
			    r.add("catatan5", txtCatatanAdd);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("tblppkborangj");
			    myLogger.info("SQL UPDATE TBLPPKBORANGJ = "+sql);
			    stmt.executeUpdate(sql);
			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}

		public void setListSelectedWaris(HttpSession session,String idPermohonan,String id_permohonansimati) throws Exception {
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			selectedWaris.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT b.id_permohonan, a.id_ob, a.id_simati, a.id_permohonansimati,a.nama_ob, a.no_kp_baru, a.no_kp_lain, a.no_kp_lama, a.status_hidup,a.id_saudara, d.keterangan"
					+ " FROM tblppkob a1, tblppkobpermohonan a," +
							" tblppkpermohonansimati b,tblppkpermohonan c,tblppkrujsaudara d"
					+ " WHERE a.id_permohonansimati = '"+id_permohonansimati+"' " +
							" and a.id_ob = a1.id_ob " +
							" and a1.id_permohonansimati = b.id_permohonansimati " +
							" AND b.id_permohonan = c.id_permohonan " +
							" AND a.id_saudara = d.id_saudara AND a.status_hidup = 0" 
					+ " AND b.id_permohonan = '"+ idPermohonan +"' ";
	
				
				if (session.getAttribute("listSelectedWaris") != null){
					sql = sql + "AND a.id_ob IN (" + session.getAttribute("listSelectedWaris") + ")";
				} else {
					sql = sql + "AND a.id_ob = ''";
				}
							
				sql = sql + " ORDER BY a.id_ob ASC";
				ResultSet rs = stmt.executeQuery(sql);
				//myLogger.info("SQL POPUP WARIS :: "+sql);
				Hashtable h;
				int bil = 1;
				Integer count = 0;

				while (rs.next()) {
					h = new Hashtable();				
			        h.put("bil", bil);
			    	h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
			    	h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
			    	h.put("id_simati", rs.getString("id_simati")==null?"":rs.getString("id_simati"));
			    	h.put("id_permohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
			    	h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
			    	h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
			    	h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
			    	h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));
			    	h.put("status_hidup", rs.getString("status_hidup")==null?"":rs.getString("status_hidup"));
			    	h.put("id_saudara", rs.getString("id_saudara")==null?"":rs.getString("id_saudara"));
			    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	
					selectedWaris.addElement(h);
					bil++;
					count++;
				}
				
				session.removeAttribute("listSelectedWaris");
				
			} finally {
				if (db != null)
					db.close();
			}
		}	
		
		public void setListSelectedWaris17(HttpSession session,String id_perbicaraan,String id_permohonansimati) throws Exception {
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			selectedWaris17.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = " SELECT a.id_ob, a.id_simati, a.id_permohonansimati,a.nama_ob, a.no_kp_baru, a.no_kp_lain, a.no_kp_lama, a.status_hidup, a.id_saudara, d.keterangan "
					+ " FROM tblppkob a1,tblppkobpermohonan a," +
							" tblppkrujsaudara d,tblppknotisob nob "
					+ " WHERE a1.id_ob = a.id_ob " +
							" and a.id_permohonansimati = '"+id_permohonansimati+"' " +
									" and NVL(a.id_saudara, 0) = d.id_saudara " +
									" AND nob.id_ob = a1.id_ob AND NVL(a.status_hidup, 0) = 0 " +
									" AND NVL (nob.flag_cetak, 0) = 0 " 
					+ " AND nob.id_perbicaraan = '"+ id_perbicaraan +"' ";
				
				if (session.getAttribute("listSelectedWaris") != null){
					sql = sql + "AND a.id_ob IN (" + session.getAttribute("listSelectedWaris") + ")";
				} else {
					sql = sql + "AND a.id_ob = ''";
				}
							
				sql = sql + " ORDER BY a.id_ob ASC";
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("POPUP WARIS SEKSYEN 17 :: "+sql);
				Hashtable h;
				int bil = 1;
				Integer count = 0;

				while (rs.next()) {
					h = new Hashtable();				
			        h.put("bil", bil);
			    	//h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
			    	h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
			    	h.put("id_simati", rs.getString("id_simati")==null?"":rs.getString("id_simati"));
			    	h.put("id_permohonansimati", rs.getString("id_permohonansimati")==null?"":rs.getString("id_permohonansimati"));
			    	h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
			    	h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
			    	h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
			    	h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));
			    	h.put("status_hidup", rs.getString("status_hidup")==null?"":rs.getString("status_hidup"));
			    	h.put("id_saudara", rs.getString("id_saudara")==null?"":rs.getString("id_saudara"));
			    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	
					selectedWaris17.addElement(h);
					bil++;
					count++;
				}
				
				session.removeAttribute("listSelectedWaris");
				
			} finally {
				if (db != null)
					db.close();
			}
		}				 		
					
		public void setUploadFail(String idFail) throws Exception {
		    Db db = null;
		    listUploadFail.clear();
		    String sql = "";
		    
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      
				sql = " SELECT lam.id_lampiran, lam.id_dokumen, lam.content, lam.nama_fail, "
					+ " lam.jenis_mime, doc.catatan, doc.tajuk_dokumen "
					+ " FROM tblpfdrujlampiran lam, tblpfddokumen doc " 
					+ " WHERE lam.id_dokumen = doc.id_dokumen AND doc.id_fail = '"+ idFail +"' ";			
				sql = sql + " ORDER BY lam.id_lampiran DESC ";   		       
	
		      myLogger.info("SQL UPLOAD FAIL XXXXX = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      
				Hashtable h;
				int bil = 1;
				Integer count = 0;
		      
		      while (rs.next()) {
		    	h = new Hashtable();
		        h.put("bil", bil);
		    	h.put("id_lampiran", rs.getString("id_lampiran")==null?"":rs.getString("id_lampiran"));
		    	h.put("id_dokumen", rs.getString("id_dokumen")==null?"":rs.getString("id_dokumen"));
		    	h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
		    	h.put("tajuk_dokumen", rs.getString("tajuk_dokumen")==null?"":rs.getString("tajuk_dokumen"));
		    	
		    	listUploadFail.addElement(h);
				bil++;
				count++;
		      }
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}
		

		public static void edit_status_ROTSkeputusan(String idPermohonan,String usid) throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {				
				//int id_status = 175;	
		    	String id_status = "177";	//* SELESAI (RUJUKAN KPD RULER OF THE STATE)
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idPermohonan);
			    
			    r.add("id_status", id_status);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("tblppkpermohonan");
			  
			    stmt.executeUpdate(sql);
			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}

		public static void edit_status_Syariah(String idPermohonan,String usid) throws Exception {
		    Db db = null;
		    String sql = "";		    
		    try
		    {	
		    	String id_status = "176";	//* TANGGUH PERBICARAN (RUJUKAN KPD RULER OF THE STATE)
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idPermohonan);
			    
			    r.add("id_status", 176);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("tblppkpermohonan");
			  
			    stmt.executeUpdate(sql);			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}

		public static void edit_statusTblrujsuburusanstatusfail_ROTSkeputusan(String idsuburusanstatusfail, 
				String usid, String id_perbicaraan, String id_fail, String idPermohonan) throws Exception {
		    
			Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");				    		     				  
				db = new Db();					
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL						  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
				  
				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("sql ROTS KEPUTUSAN --> "+sql2);	
				  stmt2.executeUpdate(sql2);
				 	  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
				
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idPermohonan);
					r.add("id_suburusanstatus",814);	//* SELESAI (RUJUKAN KPD RULER OF THE STATE)
					r.add("aktif",1);
					r.add("id_fail",id_fail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));	

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
					stmt.executeUpdate(sql);					  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		
		public static void edit_statusTblrujsuburusanstatusfail_Syariah(String idsuburusanstatusfail, String usid, String id_perbicaraan, String id_fail, String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");				    		     				  
				db = new Db();					
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL						  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
				  
				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("sql update TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);	
				  stmt2.executeUpdate(sql2);				 
				  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
				
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idPermohonan);
					r.add("id_suburusanstatus",813);
					r.add("aktif",1);
					r.add("id_fail",id_fail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));						

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
					stmt.executeUpdate(sql);					  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		public static void edit_statusTblrujsuburusanstatusfail_Syariah17(String idsuburusanstatusfail, String usid, String id_perbicaraan, String id_fail, String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");				    		     				  
				db = new Db();					
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL						  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
				  
				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("sql update TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);	
				  stmt2.executeUpdate(sql2);				 
				  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
				
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idPermohonan);
					r.add("id_suburusanstatus",853);
					r.add("aktif",1);
					r.add("id_fail",id_fail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));	

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
					stmt.executeUpdate(sql);					  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
		
		public static void edit_status_tblrujsuburusanstatusfailMS(String idsuburusanstatusfail, String usid, String id_perbicaraan, String idFail, String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		
		    		     				  
				db = new Db();					
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
						  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
				  
				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("sql update TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);	
				  stmt2.executeUpdate(sql2);
				 
				  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idPermohonan);
					r.add("id_suburusanstatus",813); //* ID SUBURUSAN TANGGUH PERBICARAAN (RUJUKAN KPD ROTS)
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));	

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
					stmt.executeUpdate(sql);					  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		public static void edit_status_tblrujsuburusanstatusfailMS17(String idsuburusanstatusfail, String usid, String id_perbicaraan, String idFail, String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		
		    		     				  
				db = new Db();					
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
						  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
				  
				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("sql update TBLRUJSUBURUSANSTATUSFAIL --> "+sql2);	
				  stmt2.executeUpdate(sql2);
				 		  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idPermohonan);
					r.add("id_suburusanstatus",813); //* ID SUBURUSAN TANGGUH PERBICARAAN (RUJUKAN KPD ROTS)					
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));	

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
					stmt.executeUpdate(sql);					  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		public static void edit_status_tblrujsuburusanstatusfailMufti(String idsuburusanstatusfail, String usid, String id_perbicaraan, String idFail, String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");				    		     				  

				db = new Db();					
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
						  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
				  
				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("SQL UPDATE TBLRUJSUBURUSANSTATUSFAIL MUFTI --> "+sql2);	
				  stmt2.executeUpdate(sql2);
				 			  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idPermohonan);
					r.add("id_suburusanstatus",813);
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));	

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
					stmt.executeUpdate(sql);					  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		
		public static void edit_status_tblrujsuburusanstatusfailMufti17(String idsuburusanstatusfail, String usid, String id_perbicaraan, String idFail,String idPermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");				    		     				  

				db = new Db();					
					
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
						  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);
				  
				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  myLogger.info("SQL UPDATE TBLRUJSUBURUSANSTATUSFAIL MUFTI --> "+sql2);	
				  stmt2.executeUpdate(sql2);
				 
				  
					//** INSERT TBLRUJSUBURUSANSTATUSFAIL	
				  
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("id_suburusanstatusfail",id_suburusanstatusfail);
					r.add("id_permohonan",idPermohonan);
					r.add("id_suburusanstatus",853);
					r.add("aktif",1);
					r.add("id_fail",idFail);
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));	

					sql = r.getSQLInsert("tblrujsuburusanstatusfail");				
					stmt.executeUpdate(sql);					  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }		
		
		
		public static void edit_status_tblppkpermohonanMS(String idPermohonan,String usid) throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {				
				String id_status = "176";	//* ID STATUS: TANGGUH PERBICARAAN (RUJUKANN KEPADA ROTS)
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idPermohonan);
			    
			    r.add("id_status", 176);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("tblppkpermohonan");			  
			    stmt.executeUpdate(sql);			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}
		
		public static void edit_status_tblppkpermohonanMufti(String idPermohonan,String usid) throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {				
				String id_status = "176";
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idPermohonan);
			    
			    r.add("id_status", 176);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("tblppkpermohonan");
			  
			    stmt.executeUpdate(sql);
			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}		
		
		public static void setListTangguhKolateral(String idkp)throws Exception {
			
			Db db = null;
			listTangguhKolateral.clear();
			String sql = "";
			
			try{
				
				db = new Db();
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("b.id_perbicaraan");
				r.add("b.tarikh_bicara");
				r.add("b.masa_bicara");
				r.add("b.tempat_bicara");
				r.add("b.bil_bicara");
				r.add("b.alamat_bicara1");
				r.add("b.alamat_bicara2");
				r.add("b.alamat_bicara3");
				r.add("b.bandar");
				r.add("b.poskod");
				r.add("pt.keputusan_mahkamah");
				r.add("b.tarikh_notis");
				r.add("pt.flag_tangguh");
				r.add("pt.sebab_tangguh");
				
				r.add("pt.id_perbicaraan",r.unquote("b.id_perbicaraan(+)"));
				
				r.add("pt.flag_tangguh",6);
				r.add("b.id_keputusanpermohonan",idkp);
				
				sql = r.getSQLSelect("Tblppkperbicaraan b, Tblppkperintah pt","bil_bicara");
				myLogger.info("[sql]:SENARAI TANGGUH KOLATERAL = " +sql);
				ResultSet rs = stmt.executeQuery(sql);
			    Hashtable h;
			    int bil = 1;
				
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("bil", bil);
			    	  h.put("id_perbicaraan", rs.getString("id_perbicaraan"));
			    	  h.put("masa_bicara", rs.getString("masa_bicara")==null?"":rs.getString("masa_bicara"));
			    	  h.put("tempat_bicara", rs.getString("tempat_bicara")==null?"":rs.getString("tempat_bicara"));
			    	  h.put("bil_bicara", rs.getString("bil_bicara")==null?"":rs.getString("bil_bicara"));
			    	  h.put("alamat_bicara1", rs.getString("alamat_bicara1")==null?"":rs.getString("alamat_bicara1"));
			    	  h.put("alamat_bicara2", rs.getString("alamat_bicara2")==null?"":rs.getString("alamat_bicara2"));
			    	  h.put("alamat_bicara3", rs.getString("alamat_bicara3")==null?"":rs.getString("alamat_bicara3"));
			    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			    	  //h.put("keputusan_mahkamah", rs.getString("keputusan_mahkamah")==null?"":rs.getString("keputusan_mahkamah"));
			    	  h.put("tarikh_bicara", Format.format(rs.getDate("tarikh_bicara"))==null?"":Format.format(rs.getDate("tarikh_bicara")));
			    	  h.put("tarikh_notis", Format.format(rs.getDate("tarikh_notis"))==null?"":Format.format(rs.getDate("tarikh_notis")));
			    	  h.put("sebab_tangguh", rs.getString("sebab_tangguh")==null?"-":rs.getString("sebab_tangguh"));
			    	  
			    	  if(rs.getString("flag_tangguh") == null || rs.getString("flag_tangguh") ==""){
				    		h.put("flag_tangguh","Sebab-sebab Lain");
				    	}else{
				    		String flagT = rs.getString("flag_tangguh");
				    		int flag = Integer.parseInt(flagT);
				    		System.out.println("[rs.next]:flag tangguh = " +flagT+"= "+flag );
				    		if(flag == 1){
				    			h.put("flag_tangguh","Pemohon / Waris Tidak Hadir ");
				    		}else if(flag == 2){
				    			h.put("flag_tangguh","Senarai Waris Tidak Lengkap");
				    		}else if(flag == 3){
				    			h.put("flag_tangguh","Menunggu Keputusan Rujukan Mahkamah Tinggi");
				    		}else if(flag == 4){
				    			h.put("flag_tangguh","Bukti Kematian Tidak Lengkap");
				    		}else if(flag == 5){
				    			h.put("flag_tangguh","Menunggu Keputusan Rujukan Mahkamah Syariah");
				    		}else if(flag == 6){
				    			h.put("flag_tangguh","Pertelingkahan Kolateral");
				    		}else if(flag == 7){
				    			h.put("flag_tangguh","Menunggu Sijil Faraid");
				    		}else if(flag == 8){
				    			h.put("flag_tangguh","Menunggu Surat Akaun Persetujuan");
				    		}else if(flag == 9){
				    			h.put("flag_tangguh","Sebab-sebab Lain");
				    		}
				    	}
			    	  
			    	  if(rs.getString("keputusan_mahkamah") == null || rs.getString("keputusan_mahkamah") ==""){
				    		h.put("keputusan_mahkamah","-");
				    	}else{
				    		h.put("keputusan_mahkamah",rs.getString("keputusan_mahkamah"));
				    	}
			    	  
			    	  listTangguhKolateral.addElement(h);
			    	  bil++;
			      	}      
				}
				finally{
					if(db != null)db.close();
				}
		}//close bicara		
		
	 public Vector getObList(String id_perbicaraan,String id_permohonansimati)throws Exception {
		 
		    Db db = null;
		    listWarisUpdate.clear();
		    /*
			String sql = " SELECT c.id_ob, d.nama_ob, d.no_kp_baru, d.id_saudara, e.keterangan,c.jenis_ob "+
			 " FROM tblppkperintah a,tblppkkolateralmst b,tblppkkolateraldtl c,tblppkob d,tblppkrujsaudara e "+					 
			 " WHERE NVL(d.id_saudara,0) = e.id_saudara AND a.id_perbicaraan = b.id_perbicaraan "+
			 " AND b.id_kolateralmst = c.id_kolateralmst AND d.id_ob = c.id_ob "+
			 " AND a.id_perbicaraan  = '"+ id_perbicaraan +"' ";
			 */
		    
		    String sql = " SELECT c.id_ob, d.nama_ob, d.no_kp_baru, d.id_saudara, e.keterangan,c.jenis_ob "+
			 " FROM tblppkperintah a,tblppkkolateralmst b,tblppkkolateraldtl c," +
			 " tblppkob d1,tblppkobpermohonan d," +
			 " tblppkrujsaudara e "+					 
			 " WHERE d.id_ob = d1.id_ob and d.id_permohonansimati = '"+id_permohonansimati+"'  " +
			 		" and NVL(d.id_saudara,0) = e.id_saudara AND a.id_perbicaraan = b.id_perbicaraan "+
			 " AND b.id_kolateralmst = c.id_kolateralmst AND d1.id_ob = c.id_ob "+
			 " AND a.id_perbicaraan  = '"+ id_perbicaraan +"' ";
		    
		    myLogger.info("LIST OB KOLETERAL :"+sql);

		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
//		      r.add("C.ID_OB");
//		      r.add("D.NAMA_OB");
//		      r.add("D.NO_KP_BARU");
//		      r.add("D.ID_SAUDARA");
//		      r.add("E.KETERANGAN"); 
//		      r.add("C.JENIS_OB");	
//		      
//		      r.add("D.ID_SAUDARA",r.unquote("E.ID_SAUDARA"));
//		      r.add("A.ID_PERBICARAAN",r.unquote("B.ID_PERBICARAAN"));
//		      r.add("B.ID_KOLATERALMST",r.unquote("C.ID_KOLATERALMST"));
//		      r.add("D.ID_OB",r.unquote("C.ID_OB"));
//		      r.add("C.JENIS_OB","PL");		
		      
//		      r.add("A.ID_PERBICARAAN",id_perbicaraan);
//
//		      sql = r.getSQLSelect("TBLPPKPERINTAH A, TBLPPKKOLATERALMST B, TBLPPKKOLATERALDTL C, TBLPPKOB D, TBLPPKRUJSAUDARA E ");
		      
		      myLogger.info("SQL SET SENARAI WARIS = "+sql);
		      ResultSet rs = stmt.executeQuery(sql);		      
		   
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("id_ob", rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
		        h.put("nama_ob", rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
		    	h.put("no_kp_baru", rs.getString("NO_KP_BARU")==null?"":rs.getString("NO_KP_BARU"));
		    	h.put("id_saudara", rs.getString("ID_SAUDARA")==null?"":rs.getString("ID_SAUDARA"));
		    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));		    
		    	h.put("jenis_ob", rs.getString("JENIS_OB")==null?"":rs.getString("JENIS_OB"));
		    	
		    	listWarisUpdate.addElement(h);
		    	bil++;
		      }
		      return listWarisUpdate;
		    }
		    finally {
		      if (db != null) db.close();
		    }	
	 }

		public static void setSelectedWaris(String idBorangj, String id_perbicaraan,String id_permohonansimati) throws Exception {
			Db db = null;
			ListSelectedWaris.clear();
//			String sql = " SELECT a.nama_ob, a.id_ob, a.no_kp_baru, a.no_kp_lain, a.no_kp_lama,a.status_hidup, a.id_saudara, d.keterangan, "+
//						 " (SELECT COUNT (*) FROM tblppkborangjdtl WHERE id_ob = a.id_ob AND id_borangj = "+ idBorangj +" ) AS flag "+
//						 " FROM tblppkob a,tblppkpermohonansimati b,tblppkpermohonan c,tblppkrujsaudara d,tblppknotisob nob "+
//						 " WHERE a.id_permohonansimati = b.id_permohonansimati AND b.id_permohonan = c.id_permohonan AND a.id_saudara = d.id_saudara "+						 
//						 " AND nob.id_ob = a.id_ob AND a.status_hidup = 0 AND nob.id_perbicaraan = "+id_perbicaraan+" ";				

			String sql = " SELECT a.nama_ob, a.id_ob, a.no_kp_baru, a.no_kp_lain, a.no_kp_lama,a.status_hidup, a.id_saudara, d.keterangan, "+
						 //" (SELECT COUNT (*)FROM tblppkborangjdtl WHERE id_ob = a.id_ob AND id_borangj = '"+ idBorangj +"') AS flag "+
						 " COUNT(DISTINCT CASE WHEN C.ID_OB IS NOT NULL THEN c.id_ob END) AS flag "+
						 " FROM tblppkob a1,tblppkobpermohonan a," +
						 "" +
						 " tblppkrujsaudara d,tblppkborangj e,tblppkborangjdtl c "+
						 " WHERE a1.id_ob = a.id_ob and a.id_permohonansimati = '"+id_permohonansimati+"' " +
						 		" and a.id_saudara = d.id_saudara " +
						 		" AND NVL (a.id_saudara, 0) = d.id_saudara " +
						 		" AND c.id_borangj(+) = e.id_borangj "+
						 //" AND c.id_ob = a1.id_ob " +
						 " AND a.status_hidup = 0 AND e.id_perbicaraan = '"+id_perbicaraan+"' ";
			sql += " GROUP BY  a.nama_ob, a.id_ob, a.no_kp_baru, a.no_kp_lain, a.no_kp_lama,a.status_hidup, a.id_saudara, d.keterangan ";

			myLogger.info("TEST ELLY===="+sql);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				myLogger.info("SQL SELECTED WARIS = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
			    	h.put("bil", bil);
			    	h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
			        h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
			    	h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
			    	h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
			    	h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));		    
			    	h.put("status_hidup", rs.getString("status_hidup")==null?"":rs.getString("status_hidup"));
			    	h.put("id_saudara", rs.getString("id_saudara")==null?"":rs.getString("id_saudara"));
			    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	h.put("flag", rs.getString("flag")==null?"":rs.getString("flag"));
					
					ListSelectedWaris.addElement(h);
					bil++;	
				}
			}finally {
				if(db != null) db.close();
			}	
		}

		public static void setSelectedWaris17(String idBorangj, String id_perbicaraan,String id_permohonansimati) throws Exception {
			Db db = null;
			ListSelectedWaris17.clear();

			String sql = " SELECT a.nama_ob, a.id_ob, a.no_kp_baru, a.no_kp_lain, a.no_kp_lama,a.status_hidup, a.id_saudara, d.keterangan, "+
						 " (SELECT COUNT (*)FROM tblppkborangjdtl WHERE id_ob = a.id_ob AND id_borangj = '"+ idBorangj +"') AS flag "+
						 " FROM tblppkob a1,tblppkobpermohonan a," +
						 "" +
						 " tblppkrujsaudara d,tblppkborangj e,tblppkborangjdtl c "+
						 " WHERE a1.id_ob = a.id_ob and a.id_permohonansimati = '"+id_permohonansimati+"' " +
						 		" and  a.id_saudara = d.id_saudara " +
						 		" AND NVL (a.id_saudara, 0) = d.id_saudara " +
						 		" AND c.id_borangj = e.id_borangj "+
						 "AND c.id_ob = a1.id_ob AND a.status_hidup = 0 AND e.id_perbicaraan = '"+id_perbicaraan+"' ";

			myLogger.info("TEST ELLY===="+sql);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
			    	h.put("bil", bil);
			    	h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
			        h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
			    	h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
			    	h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
			    	h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));		    
			    	h.put("status_hidup", rs.getString("status_hidup")==null?"":rs.getString("status_hidup"));
			    	h.put("id_saudara", rs.getString("id_saudara")==null?"":rs.getString("id_saudara"));
			    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	h.put("flag", rs.getString("flag")==null?"":rs.getString("flag"));
					
					ListSelectedWaris17.addElement(h);
					bil++;	
				}
			}finally {
				if(db != null) db.close();
			}	
		}		
		
			
		public static void addKeputusan_Editblppkkolateralmst( String idpermohonan, String IdKolateralmst, String usid, String flag_keputusan, 
				String txtBayaranPerintahKol, String txtNomborResitPerintahKol, String txtCatatanKeputusan, 
				String txdTarikhBayaranPerintahKol ) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");				    		     				  
				String TT = "to_date('" + txdTarikhBayaranPerintahKol + "','dd/MM/yyyy')";
		    	
				db = new Db();					
					
				//** UPDATE TBLPPKKOLATERALMST						 
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  r.update("id_kolateralmst", IdKolateralmst);
				  
				  r.add("keputusan",flag_keputusan);
				  r.add("catatan_keputusan",txtCatatanKeputusan);
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkkolateralmst");
				  myLogger.info("SQL UPDATE TBLPPKKOLATERALMST --> "+sql);	
				  stmt.executeUpdate(sql);				  			  	
				  
				  	r.clear();				 	
				  	
				  	// INSERT TBLPPKBAYARAN
				  	
					r.add("id_bayaran",idBayaran);
					r.add("id_permohonan",idpermohonan);
					r.add("id_jenisbayaran",17);
					r.add("no_resit",txtNomborResitPerintahKol);
					r.add("tarikh_bayaran", r.unquote(TT));
					r.add("jumlah_bayaran",Utils.RemoveComma(txtBayaranPerintahKol));
					r.add("id_masuk",usid);
					r.add("tarikh_masuk",r.unquote("sysdate"));					  

					sql = r.getSQLInsert("Tblppkbayaran");	
					myLogger.info("INSERT TBLPPKBAYARAN = "+sql);
					stmt.executeUpdate(sql);					
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }	
		
		public static void updateKeputusan_tblppkkolateralmst( String idpermohonan, String IdKolateralmst, String usid, String flag_keputusan, 
				String txtBayaranPerintahKol, String txtNomborResitPerintahKol, String txtCatatanKeputusan, 
				String txdTarikhBayaranPerintahKol, String idBayaran ) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    					    		     				  
				String TT = "to_date('" + txdTarikhBayaranPerintahKol + "','dd/MM/yyyy')";
		    	
				db = new Db();					
					
				//** UPDATE TBLPPKKOLATERALMST						 
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  r.update("id_kolateralmst", IdKolateralmst);
				  
				  r.add("keputusan",flag_keputusan);
				  r.add("catatan_keputusan",txtCatatanKeputusan);
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkkolateralmst");
				  myLogger.info("SQL UPDATE TBLPPKKOLATERALMST --> "+sql);	
				  stmt.executeUpdate(sql);
				  			  
				 			  
					//** update TBLPPKBAYARAN					  
//					Statement stmt2 = db.getStatement();
//					SQLRenderer r2 = new SQLRenderer();		
//				  
				  	r.clear();
				  	
					r.update("id_bayaran",idBayaran);	
					
					r.add("no_resit",txtNomborResitPerintahKol);
					r.add("tarikh_bayaran", r.unquote(TT));
					r.add("jumlah_bayaran",Utils.RemoveComma(txtBayaranPerintahKol));
					r.add("id_kemaskini",usid);
					r.add("tarikh_kemaskini",r.unquote("sysdate"));						
					sql2 = r.getSQLUpdate("Tblppkbayaran");		
					myLogger.info("UPDATE TBLPPKBAYARAN --> "+sql2);
					stmt.executeUpdate(sql2);	
				  						
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
		
		public static void updateKeputusan( String idpermohonan, String IdKolateralmst, String usid, String flag_keputusan, 
				String txtBayaranPerintahKol, String txtNomborResitPerintahKol, String txtCatatanKeputusan, String txdTarikhBayaranPerintahKol ) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");				    		     				  

				String TT = "to_date('" + txdTarikhBayaranPerintahKol + "','dd/MM/yyyy')";				
					
				//** UPDATE TBLPPKKOLATERALMST
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  
				  r.update("id_kolateralmst", IdKolateralmst);
				  
				  r.add("catatan_keputusan",txtCatatanKeputusan);
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  
				  sql = r.getSQLUpdate("Tblppkkolateralmst");
				  myLogger.info("SQL UPDATE TBLPPKKOLATERALMST --> "+sql);	
				  stmt.executeUpdate(sql);				  
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }		
		
		public static void edit_status_KeputusanKolateral( String idpermohonan, String usid ) throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {		
				String id_status = "173"; /* STATUS: SELESAI PERBICARAAN (KOLATERAL) */
				  
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", idpermohonan);
			    
			    r.add("id_status", 173);
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("tblppkpermohonan");
			    myLogger.info("SQL KEPUTUSAN IDSTATUS = "+sql);
			    stmt.executeUpdate(sql);
			    
		    	}
		    	finally {
		    		if (db != null) db.close();
		    	}
	    	}		


		public static void edit_status_tblrujsuburusanstatusfail_KeputusanKolateral( String idpermohonan, 
				String idsuburusanstatusfail, String idFail, String usid) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

				db = new Db();
										
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
					  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  stmt2.executeUpdate(sql2);				  
				  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL 			
				  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				
				  r.add("id_suburusanstatusfail",id_suburusanstatusfail);
				  r.add("id_permohonan",idpermohonan);
				  r.add("id_suburusanstatus",776);
				  r.add("aktif",1);
				  r.add("id_fail",idFail);
				  r.add("id_masuk",usid);
				  r.add("tarikh_masuk",r.unquote("sysdate"));
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));				  

				  sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
				  myLogger.info("SQL KEPUTUSAN IDSUBURUSANSTATUSFAIL = "+sql);
				  stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }		

		public static void edit_status_tblrujsuburusanstatusfail_KeputusanKolateral17( String idpermohonan, 
				String idsuburusanstatusfail, String idFail, String usid) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    String sql2 = "";
		   
		    try
		    {		    	
		    	long id_suburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");

				db = new Db();
										
				//** UPDATE TBLRUJSUBURUSANSTATUSFAIL
					  
				  Statement stmt2 = db.getStatement();
				  SQLRenderer r2 = new SQLRenderer();
				  
				  r2.update("id_suburusanstatusfail", idsuburusanstatusfail);

				  r2.add("aktif",0);
				  r2.add("id_kemaskini",usid);
				  r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
				  
				  sql2 = r2.getSQLUpdate("Tblrujsuburusanstatusfail");
				  stmt2.executeUpdate(sql2);				  
				  
				//** INSERT TBLRUJSUBURUSANSTATUSFAIL 			
				  
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				
				  r.add("id_suburusanstatusfail",id_suburusanstatusfail);
				  r.add("id_permohonan",idpermohonan);
				  r.add("id_suburusanstatus",780);
				  r.add("aktif",1);
				  r.add("id_fail",idFail);
				  r.add("id_masuk",usid);
				  r.add("tarikh_masuk",r.unquote("sysdate"));
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));				  

				  sql = r.getSQLInsert("tblrujsuburusanstatusfail");	
				  myLogger.info("SQL KEPUTUSAN IDSUBURUSANSTATUSFAIL = "+sql);
				  stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }				
		
		public static Vector setCheckingIdPermohonan(String idpermohonan) throws Exception {
			
		    Db db = null;
		    checkingValue.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_bayaran");
		      r.add("id_jenisbayaran");
		      r.add("no_resit");
		      r.add("jumlah_bayaran");
		      r.add("tarikh_bayaran");

		      r.add("id_permohonan",idpermohonan);

		      sql = r.getSQLSelect("Tblppkbayaran");
		      myLogger.info("SQL BAYARAN == "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;
		      int bil = 1;
		      
		      while (rs.next()) {
		    	h = new Hashtable();
		        h.put("idBayaran", rs.getString("id_bayaran")==null?"":rs.getString("id_bayaran"));
		    	h.put("idJenisBayaran", rs.getString("id_jenisbayaran")==null?"":rs.getString("id_jenisbayaran"));
		    	h.put("noResit", rs.getString("no_resit")==null?"":rs.getString("no_resit"));		 
		    	h.put("jumlahBayaran", rs.getString("jumlah_bayaran")==null?"0":Double.parseDouble(rs.getString("jumlah_bayaran")));
		    	h.put("tarikhBayaran", rs.getString("tarikh_bayaran")==null?"":Format.format(rs.getDate("tarikh_bayaran")));
		    	
		    	checkingValue.addElement(h);
		    	bil++;
		      }
		      return checkingValue;
		    }
		    finally {
		      if (db != null) db.close();
		    }			
	}		
		
		public static void BayaranPusakaEDIT(String usid,String idBayaran,String noResit,
				String jumlahBayaran,String tarikhBayaran) throws Exception	{
		    
			Db db = null;
		    String sql = "";
		    try
		    {
				String TB = "to_date('" + tarikhBayaran + "','dd/MM/yyyy')";

				//UPDATE TBLPPKBAYARAN
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			  
			    r.update("id_bayaran", idBayaran);
			    
			    r.add("id_jenisbayaran",26);
			    r.add("jumlah_bayaran",Utils.RemoveComma(jumlahBayaran));
			    r.add("no_resit",noResit);
			    r.add("tarikh_bayaran", r.unquote(TB));
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("Tblppkbayaran");
			    myLogger.info("UPDATE TBLPPKBAYARAN PUSAKA = "+sql);
			    stmt.executeUpdate(sql);
			      		
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}	
		
		public static void BayaranBaitulMalEDIT(String usid,String idBayaran,String noResit,
				String jumlahBayaran,String tarikhBayaran) throws Exception	{
		    
			Db db = null;
		    String sql = "";
		    try
		    {
				String TB = "to_date('" + tarikhBayaran + "','dd/MM/yyyy')";

				//UPDATE TBLPPKBAYARAN
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			  
			    r.update("id_bayaran", idBayaran);
			    
			    r.add("id_jenisbayaran",29);
			    r.add("jumlah_bayaran",Utils.RemoveComma(jumlahBayaran));
			    r.add("no_resit",noResit);
			    r.add("tarikh_bayaran", r.unquote(TB));
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("Tblppkbayaran");
			    myLogger.info("UPDATE TBLPPKBAYARAN BAITULMAL = "+sql);
			    stmt.executeUpdate(sql);
			      		
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}			
				
		public static void BayaranPerintahEDIT(String usid,String idBayaran,String noResit,
				String jumlahBayaran,String tarikhBayaran) throws Exception	{
		    
			Db db = null;
		    String sql = "";
		    try
		    {
				String TB = "to_date('" + tarikhBayaran + "','dd/MM/yyyy')";

				//UPDATE TBLPPKBAYARAN
			    db = new Db();
			    Statement stmt = db.getStatement();
			    SQLRenderer r = new SQLRenderer();
			  
			    r.update("id_bayaran", idBayaran);
			    
			    r.add("id_jenisbayaran",24);
			    r.add("jumlah_bayaran",Utils.RemoveComma(jumlahBayaran));
			    r.add("no_resit",noResit);
			    r.add("tarikh_bayaran", r.unquote(TB));
			    r.add("id_kemaskini",usid);
				r.add("tarikh_kemaskini",r.unquote("sysdate"));

			    sql = r.getSQLUpdate("Tblppkbayaran");
			    myLogger.info("UPDATE TBLPPKBAYARAN PERINTAH = "+sql);
			    stmt.executeUpdate(sql);
			      		
	    	} finally {
	    		if (db != null) db.close();
	    	}
	}	

		public static Vector setExistDataBayaran(String idpermohonan) throws Exception {		
		    Db db = null;
		    ListExistDataBayaran.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
			  sql = " SELECT ID_BAYARAN FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN IN (24,26,29) AND ID_PERMOHONAN = "+ idpermohonan +" ";
			  
			  myLogger.info("CHECKING DATA BAYARAN :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);	      
		   
		      Hashtable h;
		      int bil = 1;
		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("id_bayaran", rs.getString("id_bayaran")==null?"":rs.getString("id_bayaran"));
		    	
		    	ListExistDataBayaran.addElement(h);
		    	bil++;
		      }
		      return ListExistDataBayaran;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}						
		
		// PEJE PART PERINTAH
		
		public static void createIdPerintah(String id_perbicaraan, String usid,String txdTarikhPerintah,
				String EDITsocPegawaiPengendali, String txtCatatanSelesai) throws Exception {
			
			Db db = null;
			String sql = "";						
				try
				{			
					long id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
					String TTP = "to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')";	
					
				  //------------------------------- TBLPPKPERINTAH ------------------------------------
				    
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					 Connection conn = db.getConnection();					
					 PreparedStatement ps = conn.prepareStatement("insert into tblppkperintah " +
			                    "(id_perintah,id_perbicaraan,tarikh_perintah,id_unitpsk,flag_jenis_keputusan," +
			                    "catatan," +
			                    " ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI) " +
			                    "values(?,?,"+r.unquote(TTP)+",?,?,?,?,?,sysdate,sysdate)");
						        ps.setLong(1, id_perintah);
						        ps.setString(2, id_perbicaraan);
						        ps.setString(3, EDITsocPegawaiPengendali);
						        ps.setString(4, "0");
						        ps.setString(5, txtCatatanSelesai);							       
						        ps.setString(6, usid);
						        ps.setString(7, usid);	
						        ps.executeUpdate();
					
//					r.add("id_perintah",id_perintah);
//					r.add("id_perbicaraan",id_perbicaraan);
//					r.add("tarikh_perintah", r.unquote(TTP));
//					r.add("id_unitpsk",EDITsocPegawaiPengendali);
//					r.add("flag_jenis_keputusan",0);
//					r.add("catatan",txtCatatanSelesai);
//					r.add("id_masuk",usid);
//					r.add("tarikh_masuk",r.unquote("sysdate"));
//					sql = r.getSQLInsert("Tblppkperintah");		
//					myLogger.info("SQL INSERT TBLPPKPERINTAH = "+sql);
//					stmt.executeUpdate(sql);
												
				}finally {
					if(db != null) db.close();
				}			
		}	
		
		public static void addMaklumatBayaranPerintah(String usid, String idpermohonan, 
				String txtJumBayaran, String txtNomborResitPerintah, String txdTarikhBayaranPerintah) throws Exception {
			
			Db db = null;
			String sql = "";
			String sql2 = "";
			
				try
				{			
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					String TBP = "to_date('" + txdTarikhBayaranPerintah + "','dd/MM/yyyy')";		
	
					//--------------------------------- TBLPPKBAYARAN -----------------------------------------
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",24);
					r2.add("no_resit",txtNomborResitPerintah);
					r2.add("tarikh_bayaran", r2.unquote(TBP));
					r2.add("jumlah_bayaran",Utils.RemoveComma(txtJumBayaran));
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));					
				
					sql2 = r2.getSQLInsert("Tblppkbayaran");
					myLogger.info("SQL INSERT TBLPPKBAYARAN PERINTAH = "+sql2);
					stmt2.executeUpdate(sql2);				
					
				}finally {
					if(db != null) db.close();
				}			
		}
		
		public static void addMaklumatBayaranBaitulMal(String usid, String idpermohonan, 
				String txtJumBayaran, String txtNomborResitPerintah, String txdTarikhBayaranPerintah) throws Exception {
			
			Db db = null;
			String sql = "";
			String sql2 = "";
			
				try
				{			
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					String TBP = "to_date('" + txdTarikhBayaranPerintah + "','dd/MM/yyyy')";		
	
					//--------------------------------- TBLPPKBAYARAN -----------------------------------------
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",29);
					r2.add("no_resit",txtNomborResitPerintah);
					r2.add("tarikh_bayaran", r2.unquote(TBP));
					r2.add("jumlah_bayaran",Utils.RemoveComma(txtJumBayaran));
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));					
				
					sql2 = r2.getSQLInsert("Tblppkbayaran");
					myLogger.info("SQL INSERT TBLPPKBAYARAN BAITUL MAL = "+sql2);
					stmt2.executeUpdate(sql2);				
					
				}finally {
					if(db != null) db.close();
				}			
		}				

		public static void addMaklumatBayaranPusaka(String usid, String idpermohonan, 
				String txtJumBayaran, String txtNomborResitPerintah, String txdTarikhBayaranPerintah) throws Exception {
			
			Db db = null;
			String sql = "";
			String sql2 = "";
			
				try
				{			
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					String TBP = "to_date('" + txdTarikhBayaranPerintah + "','dd/MM/yyyy')";		
	
					//--------------------------------- TBLPPKBAYARAN -----------------------------------------
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",26);
					r2.add("no_resit",txtNomborResitPerintah);
					r2.add("tarikh_bayaran", r2.unquote(TBP));
					r2.add("jumlah_bayaran",Utils.RemoveComma(txtJumBayaran));
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));					
				
					sql2 = r2.getSQLInsert("Tblppkbayaran");
					myLogger.info("SQL INSERT TBLPPKBAYARAN PUSAKA = "+sql2);
					stmt2.executeUpdate(sql2);				
					
				}finally {
					if(db != null) db.close();
				}			
		}	
		
		public static Vector setDataKeputusan(String idpermohonan) throws Exception {
			
		    Db db = null;
		    dataKeputusan.clear();
		    
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      String sql = " SELECT b.id_bayaran,b.tarikh_bayaran,b.jumlah_bayaran, b.no_resit "+
		      			   " FROM tblppkbayaran b "+
		      			   " WHERE b.id_permohonan = '"+ idpermohonan +"' and b.id_jenisbayaran = 17 ";
		      myLogger.info("DATA ID BAYARAN :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);		   
		      Hashtable h;		     

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("id_bayaran", rs.getString("id_bayaran")==null?"":rs.getString("id_bayaran"));
		    	h.put("tarikh_bayaran", rs.getDate("tarikh_bayaran")==null?"":Format.format(rs.getDate("tarikh_bayaran")));		    		    
		    	h.put("jumlahBayaran", rs.getString("jumlah_bayaran")==null?"0":Double.parseDouble(rs.getString("jumlah_bayaran")));
		    	h.put("no_resit", rs.getString("no_resit")==null?"":rs.getString("no_resit"));
		 
		    	dataKeputusan.addElement(h);
		      }
		      return dataKeputusan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

		public static Vector setDataKeputusanBayaran(String id_perbicaraan) throws Exception {
			
		    Db db = null;
		    dataKeputusanBayaran.clear();
		    
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      String sql = " SELECT a.catatan_keputusan FROM tblppkkolateralmst a WHERE a.id_perbicaraan = '"+ id_perbicaraan +"' ";
		      myLogger.info("CATATAN KEPUTUSAN :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		   
		      Hashtable h;		     

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("catatan_keputusan", rs.getString("catatan_keputusan")==null?"":rs.getString("catatan_keputusan"));
		    	
		    	dataKeputusanBayaran.addElement(h);
		      }
		      return dataKeputusanBayaran;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}
		
		public static void hapusExistingMaklumatBayaran( String idpermohonan ) throws Exception {
			 Db db = null;
			 String sql = " DELETE FROM TBLPPKBAYARAN WHERE ID_JENISBAYARAN IN (24,26,29) AND ID_PERMOHONAN = "+ idpermohonan +" ";
			 
			  try {					  								      
				  
				  db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();			     				     
			      
			      //sql = r.getSQLDelete("Tblppkbayaran");
			      myLogger.info("DELETE TBLPPKBAYARAN :: "+sql);
			      stmt.executeUpdate(sql);									

			    } finally {
			      if (db != null) db.close();
			    }
			  }				
		
		// -------------------------- START ADDED BY PEJE -------------------------------
		//TODO
		private static void generateRowForPembahagianPerintahSebelum(String idSimati, String idPerintah, String usid) throws Exception {

			String idPermohonanSimati = "";
			
			String idHTAMSTNew = "";
			String idPerintahHTAOBMST = "";
			Vector listPilihanHTA = new Vector();
			listPilihanHTA.clear();
			
			String idHAMSTNew = "";
			String idPerintahHAOBMST = "";
			Vector listPilihanHA = new Vector();
			listPilihanHA.clear();
			
			System.out.println("ID PERINTAH abc :: "+idPerintah);
			
			idPermohonanSimati = getIdPermohonanSimati(idSimati, idPerintah);
			
			listPilihanHTA = getListPilihanHTA(idPermohonanSimati);
			
			for (int i = 0; i < listPilihanHTA.size(); i++){
				Hashtable hash = (Hashtable) listPilihanHTA.get(i);
				String idHTA = (String)hash.get("idHTA");
				idPerintahHTAOBMST = getIdPerintahHTAOBMST(idHTA);
				
				if (idPerintahHTAOBMST != null && !"".equals(idPerintahHTAOBMST)){
					System.out.println("idPerintahHTAOBMST:"+idPerintahHTAOBMST);
					idHTAMSTNew = insertIntoTblPPKPerintahHTAOBMST(idPerintahHTAOBMST, idPerintah, usid);
					copyHTADTL(idPerintahHTAOBMST, idHTAMSTNew, usid, getIdJenisPerintahHTA(idPerintahHTAOBMST));
				}
			}
			
			listPilihanHA = getListPilihanHA(idPermohonanSimati);
			
			for (int i = 0; i < listPilihanHA.size(); i++){
				Hashtable hash = (Hashtable) listPilihanHA.get(i);
				String idHA = (String)hash.get("idHA");
				idPerintahHAOBMST = getIdPerintahHAOBMST(idHA);
				
				if (idPerintahHAOBMST != null && !"".equals(idPerintahHAOBMST)){
					System.out.println("idPerintahHAOBMST:"+idPerintahHAOBMST);
					idHAMSTNew = insertIntoTblPPKPerintahHAOBMST(idPerintahHAOBMST, idPerintah, usid);
					copyHADTL(idPerintahHAOBMST, idHAMSTNew, usid, getIdJenisPerintahHA(idPerintahHAOBMST));
				}
			}
		}
		
		private static String getIdPermohonanSimati(String idSimati, String idPerintah) throws Exception {
			Db db = null;
			String sql = "";

			try {				
				db = new Db();
				Statement stmt = db.getStatement();
				
				//GET ID PERMOHONANSIMATI
				sql = "SELECT D.ID_PERMOHONANSIMATI FROM TBLPPKPERINTAH A, TBLPPKPERBICARAAN B, TBLPPKKEPUTUSANPERMOHONAN C, TBLPPKPERMOHONANSIMATI D "
						+ "WHERE B.ID_PERBICARAAN = A.ID_PERBICARAAN AND C.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN AND C.ID_PERMOHONAN = D.ID_PERMOHONAN"
						+ " AND A.ID_PERINTAH = '" + idPerintah + "' AND D.ID_SIMATI = '" + idSimati + "'";

				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					return rs.getString("ID_PERMOHONANSIMATI");
				} else {
					return "";
				}
			
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		private static Vector getListPilihanHTA(String idPermohonanSimati) throws Exception{
			
			Db db = null;
			Vector list = new Vector();
			list.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
				myLogger.info("getListPilihanHTA :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				while (rs.next()){
					if (rs.getString("ID_HTA") != null){
						if (rs.getString("ID_HTA").trim().length() > 0){
							h = new Hashtable();
							h.put("idHTA", rs.getString("ID_HTA"));
							list.addElement(h);
						}
					}
				}
				
			} finally {
				if (db != null)
					db.close();
			}
			
			return list;
		}
		
		private static Vector getListPilihanHA(String idPermohonanSimati) throws Exception{
			
			Db db = null;
			Vector list = new Vector();
			list.clear();
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				while (rs.next()){
					if (rs.getString("ID_HA") != null){
						if (rs.getString("ID_HA").trim().length() > 0){
							h = new Hashtable();
							h.put("idHA", rs.getString("ID_HA"));
							list.addElement(h);
						}
					}
				}
				
			} finally {
				if (db != null)
					db.close();
			}
			
			return list;
		}
		
		private static String getIdPerintahHTAOBMST(String idHTA) throws Exception {
			Db db = null;
			String sql = "";

			try {				
				db = new Db();
				Statement stmt = db.getStatement();
				
				//GET ID PERINTAHHTAOBMST
				sql = "SELECT ID_PERINTAHOBMST FROM TBLPPKHTA WHERE ID_HTA = '" + idHTA + "'";

				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					return rs.getString("ID_PERINTAHOBMST");
				} else {
					return "";
				}
			
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		private static String getIdPerintahHAOBMST(String idHA) throws Exception {
			Db db = null;
			String sql = "";

			try {				
				db = new Db();
				Statement stmt = db.getStatement();
				
				//GET ID PERINTAHHAOBMST
				sql = "SELECT ID_PERINTAHOBMST FROM TBLPPKHA WHERE ID_HA = '" + idHA + "'";

				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					return rs.getString("ID_PERINTAHOBMST");
				} else {
					return "";
				}
			
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		private static String insertIntoTblPPKPerintahHTAOBMST(String idHTAOBMST, String idPerintahNew, String userId) throws Exception {
			Db db = null;
			String sql = "";
			String sqlInsert = "";
			String idHTAMSTNew = "";

			try {
				
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT * FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idHTAOBMST + "'";
				myLogger.info("insertIntoTblPPKPerintahHTAOBMST :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);				
			
				if (rs.next()){
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHTAOBMST
					long idPerintahHTAOBMST = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");
					idHTAMSTNew = String.valueOf(idPerintahHTAOBMST);
					
					r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
					r.add("ID_HTA", rs.getString("ID_HTA"));
					r.add("ID_PERINTAH", idPerintahNew);
					r.add("CATATAN", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
					r.add("TARIKH_JUALAN", rs.getDate("TARIKH_JUALAN") == null ? "" : rs.getDate("TARIKH_JUALAN"));
					r.add("AMAUN", rs.getString("AMAUN") == null ? "" : rs.getString("AMAUN"));
					r.add("JENIS_LELONG", rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
					r.add("HARGA_RIZAB", rs.getString("HARGA_RIZAB") == null ? "" : rs.getString("HARGA_RIZAB"));
					r.add("ID_JENISPERINTAH", rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));					
					r.add("FLAG_HARTA", "L");		

					r.add("ID_MASUK", userId);			
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
					stmt.executeUpdate(sqlInsert);	
				}
			
			} finally {
				if (db != null)
					db.close();
			}
			return idHTAMSTNew;
		}
		
		private static String insertIntoTblPPKPerintahHAOBMST(String idHAOBMST, String idPerintahNew, String userId) throws Exception {
			Db db = null;
			String sql = "";
			String sqlInsert = "";
			String idHAMSTNew = "";

			try {
				
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT * FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idHAOBMST + "'";
		
				ResultSet rs = stmt.executeQuery(sql);
			
				if (rs.next()){
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHTAOBMST
					long idPerintahHAOBMST = DB.getNextID("TBLPPKPERINTAHHAOBMST_SEQ");
					idHAMSTNew = String.valueOf(idPerintahHAOBMST);
					
					r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
					r.add("ID_HA", rs.getString("ID_HA"));
					r.add("ID_PERINTAH", idPerintahNew);
					r.add("CATATAN", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
					r.add("TARIKH_JUALAN", rs.getDate("TARIKH_JUALAN") == null ? "" : rs.getDate("TARIKH_JUALAN"));
					r.add("AMAUN", rs.getString("AMAUN") == null ? "" : rs.getString("AMAUN"));
					r.add("JENIS_LELONG", rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
					r.add("HARGA_RIZAB", rs.getString("HARGA_RIZAB") == null ? "" : rs.getString("HARGA_RIZAB"));
					r.add("ID_JENISPERINTAH", rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));
					r.add("FLAG_HARTA", "L");
					
					r.add("ID_MASUK", userId);			
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHAOBMST");

					stmt.executeUpdate(sqlInsert);
					
				}
			
			} finally {
				if (db != null)
					db.close();
			}
			return idHAMSTNew;
		}
		
		private static void copyHTADTL(String idHTAMSTOld, String idHTAMSTNew, String userId, String idJenisPerintah) throws Exception {
            Db db = null;
            String sql = "";

            try {
                  
                  db = new Db();
                  Statement stmt = db.getStatement();
                  
                  sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idHTAMSTOld + "'";
                  
                  if (!"2".equals(idJenisPerintah)){
                        sql = sql + " AND STATUS_TADBIR IS NOT NULL";
                  }                       
                  
                  ResultSet rs = stmt.executeQuery(sql);
            
                  while (rs.next()){
                        insertIntoTblPPKPerintahHTAOBDTL((String)rs.getString("ID_PERINTAHHTAOBDTL"), idHTAMSTNew, userId);                           
                  }
            
            } finally {
                  if (db != null)
                        db.close();
            }
      }

		
		private static void insertIntoTblPPKPerintahHTAOBDTL(String idHTADTLOld, String idHTAMSTNew, String userId) throws Exception {
			Db db = null;
			String sql = "";
			String sqlInsert = "";

			try {
				
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT * FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idHTADTLOld + "'";
		
				ResultSet rs = stmt.executeQuery(sql);
			
				if (rs.next()){					
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHTAOBDTL
					long idPerintahHTAOBDTL = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
					
					r.add("ID_PERINTAHHTAOBDTL", idPerintahHTAOBDTL);					
					r.add("ID_PERINTAHHTAOBMST", idHTAMSTNew);

					r.add("ID_OB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
					r.add("BA", rs.getString("BA") == null ? "" : rs.getString("BA"));
					r.add("BB", rs.getString("BB") == null ? "" : rs.getString("BB"));
					r.add("STATUS_TADBIR", rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
					r.add("CATATAN", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
					r.add("ID_PA1", rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
					r.add("ID_PA2", rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
					r.add("ID_PA3", rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
					r.add("ID_PA4", rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
					
					r.add("ID_MASUK", userId);			
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
					stmt.executeUpdate(sqlInsert);						
				}
			
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		private static void copyHADTL(String idHAMSTOld, String idHAMSTNew, String userId, String idJenisPerintah) throws Exception {
            Db db = null;
            String sql = "";

            try {
                  
                  db = new Db();
                  Statement stmt = db.getStatement();
                  
                  sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idHAMSTOld + "'";
                  
                  if (!"2".equals(idJenisPerintah)){
                        sql = sql + " AND STATUS_TADBIR IS NOT NULL";
                  }
      
                  ResultSet rs = stmt.executeQuery(sql);
            
                  while (rs.next()){
                        insertIntoTblPPKPerintahHAOBDTL(rs.getString("ID_PERINTAHHAOBDTL").toString(), idHAMSTNew, userId);                            
                  }
            
            } finally {
                  if (db != null)
                        db.close();
            }
      }
		
		private static void insertIntoTblPPKPerintahHAOBDTL(String idHADTLOld, String idHAMSTNew, String userId) throws Exception {
			Db db = null;
			String sql = "";

			try {
				
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT * FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBDTL = '" + idHADTLOld + "'";
		
				ResultSet rs = stmt.executeQuery(sql);
			
				if (rs.next()){
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHAOBDTL
					long idPerintahHAOBDTL = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
					
					r.add("ID_PERINTAHHAOBDTL", idPerintahHAOBDTL);					
					r.add("ID_PERINTAHHAOBMST", idHAMSTNew);

					r.add("ID_OB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
					r.add("BA", rs.getString("BA") == null ? "" : rs.getString("BA"));
					r.add("BB", rs.getString("BB") == null ? "" : rs.getString("BB"));
					r.add("STATUS_TADBIR", rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
					r.add("CATATAN", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
					r.add("ID_PA1", rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
					r.add("ID_PA2", rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
					r.add("ID_PA3", rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
					r.add("ID_PA4", rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
					
					r.add("ID_MASUK", userId);			
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
					stmt.executeUpdate(sql);						
				}
			
			} finally {
				if (db != null)
					db.close();
			}
		}

		public static void add_BayaranPerintahEDIT(String usid,String idpermohonan,String txtJumBayaranEDIT,
	    		String txtNomborResitPerintahEDIT,String txdTarikhBayaranPerintahEDIT) throws Exception {
			Db db = null;
			String sql2 = "";
			
				try
				{			
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					String TBP = "to_date('" + txdTarikhBayaranPerintahEDIT + "','dd/MM/yyyy')";		
					
					//--------------------------------- TBLPPKBAYARAN -----------------------------------------
										
					db = new Db();
					Statement stmt2 = db.getStatement();
					SQLRenderer r2 = new SQLRenderer();
					r2.add("id_bayaran",id_bayaran);
					r2.add("id_permohonan",idpermohonan);
					r2.add("id_jenisbayaran",24);
					r2.add("no_resit",txtNomborResitPerintahEDIT);
					r2.add("tarikh_bayaran", r2.unquote(TBP));
					r2.add("jumlah_bayaran",Utils.RemoveComma(txtJumBayaranEDIT));
					r2.add("id_masuk",usid);
					r2.add("tarikh_masuk",r2.unquote("sysdate"));	
					r2.add("id_kemaskini",usid);
					r2.add("tarikh_kemaskini",r2.unquote("sysdate"));						
										
					sql2 = r2.getSQLInsert("Tblppkbayaran");		
					myLogger.info("SQL INSERT BYRAN PERINTAH :: "+sql2);
					stmt2.executeUpdate(sql2);
					
				}finally {
					if(db != null) db.close();
				}			
		}			
		
		private static String getIdJenisPerintahHTA(String idPerintahHTAOBMST) throws Exception{
			
			Db db = null;
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_JENISPERINTAH FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				if (rs.next()){
					return rs.getString("ID_JENISPERINTAH");
				} else {
					return "";
				}
				
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		private static String getIdJenisPerintahHA(String idPerintahHAOBMST) throws Exception{
			
			Db db = null;
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_JENISPERINTAH FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				if (rs.next()){
					return rs.getString("ID_JENISPERINTAH");
				} else {
					return "";
				}
				
			} finally {
				if (db != null)
					db.close();
			}
		}
	
		// -------------------------- END ADDED BY PEJE -------------------------------
		
		
		
		private static int checkExistKIV(String idperintah, String idPerbicaraan) throws Exception{
			
			Db db = null;
			String sql = "";
			int idKIVhistory = 0;
			
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				 
				sql = "SELECT ID_KIVHISTORY FROM TBLPPKKIV_HIST WHERE ID_PERINTAH = '" + idperintah + "' AND ID_PERBICARAAN= "+idPerbicaraan;
				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					idKIVhistory = rs.getInt("ID_KIVHISTORY");
				} 
				return idKIVhistory;
				
			} finally {
				if (db != null)
					db.close();
			}
		}
		
			 
}	

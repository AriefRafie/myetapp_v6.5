package ekptg.model.ppt;
//yati edited 23/1/2017
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */


public class FrmSek8PampasanData {
	static Logger myLogger = Logger.getLogger(FrmSek8PampasanData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static final Log log = LogFactory.getLog(FrmSek8PampasanData.class);
	
	private static Vector listcarian = null;
	private static Vector dataByHM = null;
	private static Vector listPenyediaanPampasan = null;
	private static Vector dataPenyediaanPampasan = null;
	private static Vector listMaklumatTanahWithSiasatan = null;
	private static Vector dataBorangG = null;
	private static Vector listDataBorangG = null; //PENAMBAHAN YATI
	private static Vector dokumenImejG = null; //PENAMBAHAN YATI
	private static Vector dokumenImejH = null; //PENAMBAHAN YATI
	private static Vector dokumenPampasan = null; //PENAMBAHAN YATI
	private static Vector dataDokumenBorangG = null;
	private static Vector dataIdSiasatan = null;
	private static Vector totalPampasan = null;
	private static Vector listBorangH = null;
	private static Vector dataBorangH = null;
	private static Vector dataWarta = null;
	private static Vector dataPBAward = null;
	private static Vector listBentukPampasan = null;
	private static Vector dataBentukPampasan = null;
	private static Vector dataIdHakmilikpb = null;
	private static Vector listSuratAgensi = null;
	private static Vector dataSuratAgensi = null;
	private static Vector listPenerimaanCek = null;
	private static Vector dataPBBorangH = null;
	private static Vector dataPenerimaanCek = null;
	private static Vector listEFT = null;
	private static Vector dataEFT = null;
	private static Vector dataAfidevit = null;
	private static Vector dataPejabat = null;
	private static Vector dataIdAward = null;
	
	
	public static Vector getListCarian(){
		return listcarian;
	}
	
	public Vector getDataByHM(){
		return dataByHM;
	}
	
	public static  Vector getlistPenyediaanPampasan(){
		return listPenyediaanPampasan;
	}
	
	public static  Vector getDataPenyediaanPampasanB(){
		return dataPenyediaanPampasan;
	}
	public Vector getListMaklumatTanahWithSiasatan(){
		return listMaklumatTanahWithSiasatan;
	}
	public Vector getDataBorangG(){
		return dataBorangG;
	}
	public Vector getListBorangG(){ //penambahan yati
		return listDataBorangG;
	}
	public Vector getImejBorangG(){ //penambahan yati
		return dokumenImejG;
	}
	public Vector getImejBorangH(){ //penambahan yati
		return dokumenImejH;
	}
	public Vector getDokumenPampasan(){ //penambahan yati
		return dokumenPampasan;
	}
	public Vector getIdSiasatan(){
		return dataIdSiasatan;
	}
	public Vector getTotalPampasan(){
		return totalPampasan;
	}
	public Vector getListBorangH(){
		return listBorangH;
	}
	public Vector getDataBorangH(){
		return dataBorangH;
	}
	public Vector getDataWarta(){
		return dataWarta;
	}
	public Vector getDataPBAward(){
		return dataPBAward;
	}
	public static  Vector getlistBentukPampasan(){
		return listBentukPampasan;
	}
	public static  Vector getDataBentukPampasan(){
		return dataBentukPampasan;
	}
	public static  Vector getIdHakmilikpb(){
		return dataIdHakmilikpb;
	}
	public static  Vector getlistSuratAgensi(){
		return listSuratAgensi;
	}
	public static  Vector getdataSuratAgensi(){
		return dataSuratAgensi;
	}
	public static Vector getListPenerimaanCek(){
		return listPenerimaanCek;
	}
	public Vector getDataPBBorangH(){
		return dataPBBorangH;
	}
	public static  Vector getdataPenerimaanCek(){
		return dataPenerimaanCek;
	}
	public static Vector getListEFT(){
		return listEFT;
	}
	public static Vector getDataEFT(){
		return dataEFT;
	}
	public static  Vector getDataAfidevit(){
		return dataAfidevit;
	}
	public Vector getDataPejabat(){
		return dataPejabat;
	}
	public static  Vector getDataIdAward(){
		return dataIdAward;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{
		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (62,68,72,74,187,204,181,182,183,184,186,199,200,201,203,205,220,1610235)";
		
		sql += " and (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptsiasatan sx, Tblpptborangg bgx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = sx.id_hakmilik and bgx.id_siasatan = sx.id_siasatan ";
		sql += " and sx.id_siasatan = (select max(sx1.id_siasatan) from Tblpptsiasatan sx1 where sx1.id_hakmilik = hx.id_hakmilik)) ";
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptsiasatan sx, Tblpptaward ax "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = sx.id_hakmilik and ax.id_siasatan = sx.id_siasatan ";
		sql += " and sx.id_siasatan = (select max(sx1.id_siasatan) from Tblpptsiasatan sx1 where sx1.id_hakmilik = hx.id_hakmilik)))  ";                                                                              

		
		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			if(userIdNegeri.equals("14")){
				sql += "AND f.id_negeri in (14,15,16) ";
			}else{
				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
			}		
		}
		return sql;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		/*
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '52' ";
		    		sql +="AND p.id_status IN (62,68,72,74,187,204,181,182,183,184,186,199,200,201,203,205,220,1610235)";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		sql = sqlListPermohonan(userIdNegeri);
		    		sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
		    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
				    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			if(rs.getString("no_fail") == null){
		    				h.put("no_fail","Belum Diluluskan");
		    			}else{
		    				h.put("no_fail",rs.getString("no_fail"));
		    			}
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPermohonan
	
	@SuppressWarnings("unchecked")
	public static void setListCarian(String carianNofail, String carianTarikh, String status, String userIdNegeri)throws Exception {
		
		listcarian = new Vector();
		
	    Db db = null;
	    listcarian.clear();
	    String sql = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	    
	    
	    String nofail = carianNofail.trim();
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		/*
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +="AND p.id_status IN (62,68,72,74,187,204,181,182,183,184,186,199,200,201,203,205,220,1610235)";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		*/
	    		sql = sqlListPermohonan(userIdNegeri);
	    		//default flag
				boolean setLimit = true;
				
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						setLimit = false;
						//sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
						sql += " AND (UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
					}
				}//close carian by nofail
		
				//TARIKH
				if (carianTarikh != "" && carianTarikh != null) {
					if (!cariTarikh.equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}//close carian by tarikh
	
	    		//STATUS
				if (status != "" && status != null) {
					setLimit = false;
					sql = sql + " AND UPPER(s.id_status) = '"+status+"'";
				}//close carian by status

				if(setLimit){	
					sql += " AND ROWNUM <= 10 ";				
				}
				
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
			    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	    			if(rs.getString("no_fail") == null){
	    				h.put("no_fail","Belum Diluluskan");
	    			}else{
	    				h.put("no_fail",rs.getString("no_fail"));
	    			}
	    			listcarian.addElement(h);
	    			bil++;
	    			
	      }//close while
	      
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	@SuppressWarnings("unchecked")
	public void setDataByHM(String idHakmilik) throws Exception {
		
		dataByHM = new Vector();
		
		Db db = null;
		dataByHM.clear();
		String sql = "";
		String sqlx = "";
		String id_siasatan = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
			
				sqlx = " SELECT DISTINCT MAX(ID_SIASATAN)AS ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK = '"+idHakmilik+"'";
				ResultSet rx = stmt.executeQuery(sqlx);
				
				while(rx.next()) {
					id_siasatan = rx.getString("ID_SIASATAN");
				}
				
				sql = " SELECT DISTINCT A.ID_PERMOHONAN, B.ID_HAKMILIK, C.ID_TANAH, E.ID_SIASATAN, ";
				sql += " C.BAYAR_FEE, C.BAYAR_TANAH, C.AMAUN_PECAHPISAH_JPPH, C.AMAUN_PENJEJASAN_JPPH, C.NAIK_NILAI_JPPH, B.NO_LOT,";
				sql += " E.NO_KES, E.TARIKH_SIASATAN, B.LUAS_AMBIL, B.ID_KATEGORITANAH, C.HARGA_SEUNIT_SO,";
				sql += " (SELECT SUM(NILAI_BANGUNAN) FROM TBLPPTBANGUNAN WHERE ID_HAKMILIK = '"+idHakmilik+"')AS NILAI_BANGUNAN, ";
				sql += " CASE "; 
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NULL THEN B.NO_LOT ";  
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN LT.KETERANGAN || B.NO_PT "; 
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NOT NULL THEN LT.KETERANGAN || B.NO_PT  "; 
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NOT NULL THEN LT.KETERANGAN || B.NO_PT || CHR(32) || CHR(40) || B.NO_LOT || CHR(41) ";  
				sql += " ELSE '' ";  
				sql += " END AS NO_LOTPT "; 
				sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTTANAH C, TBLPPTSIASATAN E, TBLRUJLOT LT ";
				sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK(+) = B.ID_HAKMILIK ";
				sql += " AND E.ID_HAKMILIK(+) = B.ID_HAKMILIK ";
				sql += " AND B.ID_LOT = LT.ID_LOT(+) ";
				sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";
				if(id_siasatan!="" && id_siasatan!=null){
					sql += " AND E.ID_SIASATAN = '"+id_siasatan+"'";
				}
				
				ResultSet rs = stmt.executeQuery(sql);
	
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
					h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
					h.put("id_tanah", rs.getString("ID_TANAH")==null?"":rs.getString("ID_TANAH"));
					h.put("id_siasatan", rs.getString("ID_SIASATAN")==null?"":rs.getString("ID_SIASATAN"));
					h.put("no_lot", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("id_kategoritanah", rs.getString("ID_KATEGORITANAH")==null?"":rs.getString("ID_KATEGORITANAH"));
					
					//nilai (double)
					h.put("luas_ambil", rs.getString("LUAS_AMBIL")==null?"":Utils.formatLuasHM(rs.getDouble("LUAS_AMBIL")));
					h.put("nilai_bangunan", rs.getString("NILAI_BANGUNAN")==null?0:rs.getDouble("NILAI_BANGUNAN"));
					h.put("bayar_fee", rs.getString("BAYAR_FEE")==null?0:rs.getDouble("BAYAR_FEE"));
					h.put("bayar_tanah", rs.getString("BAYAR_TANAH")==null?0:rs.getDouble("BAYAR_TANAH"));
					h.put("bayar_pecahpisah", rs.getString("AMAUN_PECAHPISAH_JPPH")==null?0:rs.getDouble("AMAUN_PECAHPISAH_JPPH"));
					h.put("bayar_penjejasan", rs.getString("AMAUN_PENJEJASAN_JPPH")==null?0:rs.getDouble("AMAUN_PENJEJASAN_JPPH"));
					h.put("naik_nilai_jpph", rs.getString("NAIK_NILAI_JPPH")==null?0:rs.getDouble("NAIK_NILAI_JPPH"));
					
					double nilai_tanah = 0;
					if(rs.getString("LUAS_AMBIL") != null && rs.getString("HARGA_SEUNIT_SO") != null){
	    				
						nilai_tanah = (rs.getDouble("HARGA_SEUNIT_SO") * rs.getDouble("LUAS_AMBIL"));
						h.put("nilai_tanah", nilai_tanah);
						
	    			}else{
	    				h.put("nilai_tanah", nilai_tanah);
	    			}
					
					h.put("no_kes", rs.getString("NO_KES")==null?"":rs.getString("NO_KES"));
					h.put("tarikh_siasatan", rs.getDate("TARIKH_SIASATAN")==null?"":Format.format(rs.getDate("TARIKH_SIASATAN")));
					dataByHM.addElement(h);
			
				}
			} catch (Exception re) {
				log.error("Error: ", re);
				throw re;
				}
			finally {
				if(db != null) db.close();
			}
		
	}//close setDataByHM
	
	@SuppressWarnings("unchecked")
	public static void simpanMaklumatPampasan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_pihakberkepentingan = (String)data.get("id_pihakberkepentingan");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		String id_siasatan = (String)data.get("id_siasatan");
	    		
	    		//data pb (update)
	    		String txtNoPB = (String)data.get("txtNoPB");
	    		String socJenisPB = (String)data.get("socJenisPB");
	    		String txtSyorAtas = (String)data.get("txtSyorAtas");
	    		String txtSyorBawah = (String)data.get("txtSyorBawah");
	    		
	    		//data tblpptaward (insert)
	    		String txtLuasAmbil = (String)data.get("txtLuasAmbil");
	    		String unitLuas = (String)data.get("unitLuas");
	    		String socStatusPenerima = (String)data.get("socStatusPenerima");
	    		String txtFeeAward = (String)data.get("txtFeeAward");
	    		String txtTanahAward = (String)data.get("txtTanahAward");
	    		String txtBangunanAward = (String)data.get("txtBangunanAward");
	    		String txtPecahpisahAward = (String)data.get("txtPecahpisahAward");
	    		String txtPenjejasanAward = (String)data.get("txtPenjejasanAward");
	    		String txtNaikAward = (String)data.get("txtNaikAward");
	    		String txtLainPampasan = (String)data.get("txtLainPampasan");
	    		String txtJumlahPampasan = (String)data.get("txtJumlahPampasan");
	    		
	    		//insert
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_siasatan", id_siasatan);	
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("id_pihakberkepentingan", id_pihakberkepentingan);	
	    		r.add("luas_ambil", txtLuasAmbil);	
	    		r.add("id_unitluas_ambil", unitLuas);		
	    		r.add("status_penerima", socStatusPenerima);	
	    		r.add("bayar_fee", txtFeeAward);	
	    		r.add("bayar_tanah", txtTanahAward);	
	    		r.add("bayar_bangunan", txtBangunanAward);	
	    		r.add("bayar_pecahpisah", txtPecahpisahAward);	
	    		r.add("bayar_penjejasan", txtPenjejasanAward);	
	    		r.add("naik_nilai", txtNaikAward);	
	    		r.add("bayar_lain2", txtLainPampasan);	
	    		r.add("bayar_pampasan", txtJumlahPampasan);	
	    		r.add("tarikh_sedia_award",r.unquote("sysdate"));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptaward");
	    		stmt.executeUpdate(sql);
	    		
	    		//update
	    		SQLRenderer rx = new SQLRenderer();
	    		rx.update("id_hakmilikpb", id_hakmilikpb);
	    		rx.add("id_jenispb", socJenisPB);		  
	    		rx.add("syer_atas", txtSyorAtas);		 
	    		rx.add("syer_bawah", txtSyorBawah);		 
	    		rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
	    		rx.add("id_kemaskini",id_user);    		
	    		sql = rx.getSQLUpdate("tblppthakmilikpb");
	    		stmt.executeUpdate(sql);
	    		
	    		//update
	    		SQLRenderer rz = new SQLRenderer();
	    		rz.update("id_pihakberkepentingan", id_pihakberkepentingan);
	    		rz.add("no_pb", txtNoPB);		  
	    		rz.add("tarikh_kemaskini",rz.unquote("sysdate"));
	    		rz.add("id_kemaskini",id_user);    		    		
	    		sql = rz.getSQLUpdate("tblpptpihakberkepentingan");
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanMaklumatPampasan
	
	@SuppressWarnings("unchecked")
	public static void setlistPenyediaanPampasan(String idHakmilik)throws Exception {
			
			listPenyediaanPampasan = new Vector();
			
		    Db db = null;
		    listPenyediaanPampasan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT A.ID_PERMOHONAN,B.ID_HAKMILIK,C.ID_SIASATAN,D.ID_AWARD,E.ID_HAKMILIKPB,F.ID_PIHAKBERKEPENTINGAN, ";
		    		sql += " B.NO_HAKMILIK,F.NO_PB,F.NAMA_PB,E.ID_JENISPB,G.SYER_ATAS,G.SYER_BAWAH, ";
		    		sql += " C.NO_KES,C.TARIKH_SIASATAN, ";
		    		sql += " D.LUAS_AMBIL,D.ID_UNITLUAS_AMBIL,D.STATUS_PENERIMA,D.BAYAR_FEE,D.BAYAR_TANAH,D.BAYAR_PECAHPISAH, ";
		    		sql += " D.BAYAR_PENJEJASAN,D.NAIK_NILAI, D.BAYAR_PAMPASAN , (SELECT SUM(C.JUMLAH_SUBAWARD) FROM TBLPPTSUBAWARD C ";
		    		sql += " WHERE D.ID_AWARD = C.ID_AWARD ";
		    		sql += " AND C.FLAG_JENIS_AWARD != 'BAYAR_LAIN' ";
		    		sql += " AND C.FLAG_JENIS_AWARD != 'BAYAR_PENILAI') AS BAYAR";
		    		sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTSIASATAN C, TBLPPTAWARD D, TBLPPTHAKMILIKPB E, TBLPPTPIHAKBERKEPENTINGAN F, ";
		    		sql += " TBLPPTBAHAGIANPB G, TBLPPTSUBAWARD H ";
					sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
					sql += " AND E.ID_HAKMILIK = B.ID_HAKMILIK ";
					sql += " AND E.ID_PIHAKBERKEPENTINGAN = F.ID_PIHAKBERKEPENTINGAN ";
					sql += " AND C.ID_HAKMILIK(+) = B.ID_HAKMILIK ";
					sql += " AND E.ID_BAHAGIANPB = G.ID_BAHAGIANPB(+) ";
					sql += " AND D.ID_SIASATAN = C.ID_SIASATAN ";
					sql += " AND D.ID_HAKMILIKPB(+) = E.ID_HAKMILIKPB ";
					sql += " AND D.ID_AWARD = H.ID_AWARD ";
					//sql += " AND FLAG_JENIS_AWARD = 'BAYAR_PENILAI' ";
					sql += " AND H.FLAG_JENIS_AWARD NOT IN ('BAYAR_LAIN','BAYAR_PENILAI') ";
					sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		    		
		    		myLogger.info("SQL PAMPASAN : "+sql);
		      
		    		Hashtable h;
		    		int bil = 1;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("id_award", rs.getString("ID_AWARD")== null?"":rs.getString("ID_AWARD"));
		    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
		    			h.put("id_siasatan", rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));	
		    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
		    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
		    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
		    			h.put("syer_atas", rs.getString("SYER_ATAS")== null?"":rs.getString("SYER_ATAS"));
		    			h.put("syer_bawah", rs.getString("SYER_BAWAH")== null?"":rs.getString("SYER_BAWAH"));
		    			h.put("no_kes", rs.getString("NO_KES")== null?"":rs.getString("NO_KES"));
		    			h.put("bayar_pampasan", rs.getString("BAYAR_PAMPASAN")== null?"":rs.getDouble("BAYAR_PAMPASAN"));
		    			h.put("bayar", rs.getString("BAYAR")== null?"":rs.getDouble("BAYAR"));
		    			h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
		    			listPenyediaanPampasan.addElement(h);
		    			bil++;

		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setlistPenyediaanPampasan
	
	@SuppressWarnings("unchecked")
	public static void setDataPenyediaanPampasan(String id_award,String idHakmilik)throws Exception {
			
		dataPenyediaanPampasan = new Vector();
			
		    Db db = null;
		    dataPenyediaanPampasan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql += " SELECT DISTINCT A.ID_PERMOHONAN,B.ID_HAKMILIK,C.ID_SIASATAN,D.ID_AWARD,E.ID_HAKMILIKPB,F.ID_PIHAKBERKEPENTINGAN, ";  
		    		sql += " F.NO_PB,F.NAMA_PB,E.ID_JENISPB,H.SYER_ATAS,H.SYER_BAWAH,  C.NO_KES,C.TARIKH_SIASATAN, "; 
		    		sql += " D.LUAS_AMBIL,D.ID_UNITLUAS_AMBIL,D.STATUS_PENERIMA,D.BAYAR_FEE,D.BAYAR_TANAH,D.BAYAR_PECAHPISAH, ";  
		    		sql += " D.BAYAR_PENJEJASAN,D.NAIK_NILAI,D.BAYAR_PAMPASAN,D.BAYAR_LAIN2,  G.BAYAR_FEE AS ASAL_BAYAR_FEE, ";
		    		sql += " G.BAYAR_TANAH AS ASAL_BAYAR_TANAH,G.AMAUN_PECAHPISAH_JPPH AS ASAL_BAYAR_PECAHPISAH, B.LUAS_AMBIL AS LUASHM_AMBIL, "; 
		    		sql += " G.AMAUN_PENJEJASAN_JPPH AS ASAL_BAYAR_PENJEJASAN,G.NAIK_NILAI_JPPH, D.BAYAR_BANGUNAN, G.HARGA_SEUNIT_SO,"; 
		    		sql += " (SELECT SUM(NILAI_BANGUNAN) FROM TBLPPTBANGUNAN WHERE ID_HAKMILIK = '"+idHakmilik+"')AS ASAL_BAYAR_BANGUNAN ";
		    		sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTSIASATAN C, ";
		    		sql += " TBLPPTAWARD D, TBLPPTHAKMILIKPB E, TBLPPTPIHAKBERKEPENTINGAN F, TBLPPTTANAH G, TBLPPTBAHAGIANPB H ";
		    		sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN "; 
		    		sql += " AND E.ID_HAKMILIK = B.ID_HAKMILIK "; 
		    		sql += " AND E.ID_PIHAKBERKEPENTINGAN = F.ID_PIHAKBERKEPENTINGAN "; 
		    		sql += " AND G.ID_HAKMILIK(+) = B.ID_HAKMILIK "; 
		    		sql += " AND C.ID_HAKMILIK(+) = B.ID_HAKMILIK ";  
		    		sql += " AND D.ID_SIASATAN = C.ID_SIASATAN "; 
		    		sql += " AND E.ID_BAHAGIANPB = H.ID_BAHAGIANPB(+) "; 
		    		sql += " AND D.ID_HAKMILIKPB(+) = E.ID_HAKMILIKPB ";  
		    		sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";
					sql += " AND D.ID_AWARD = '"+id_award+"'";
		      
					ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_award", rs.getString("ID_AWARD")== null?"":rs.getString("ID_AWARD"));
		    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
		    			h.put("id_siasatan", rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));	
		    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
		    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
		    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
		    			h.put("id_jenispb", rs.getString("ID_JENISPB")== null?"":rs.getString("ID_JENISPB"));
		    			h.put("syer_atas", rs.getString("SYER_ATAS")== null?"":rs.getString("SYER_ATAS"));
		    			h.put("syer_bawah", rs.getString("SYER_BAWAH")== null?"":rs.getString("SYER_BAWAH"));
		    			h.put("no_kes", rs.getString("NO_KES")== null?"":rs.getString("NO_KES"));
		    			h.put("tarikh_siasatan", rs.getDate("TARIKH_SIASATAN")==null?"":Format.format(rs.getDate("TARIKH_SIASATAN")));
		    			h.put("id_unitluas_ambil", rs.getString("ID_UNITLUAS_AMBIL")== null?"":rs.getString("ID_UNITLUAS_AMBIL"));
		    			h.put("status_penerima", rs.getString("STATUS_PENERIMA")== null?"":rs.getString("STATUS_PENERIMA"));
		    			
		    			//double
		    			h.put("luas_ambil", rs.getString("LUAS_AMBIL")== null?"":rs.getDouble("LUAS_AMBIL"));
		    			
		    			h.put("bayar_fee", rs.getString("BAYAR_FEE")== null?"":rs.getString("BAYAR_FEE"));
		    			h.put("bayar_tanah", rs.getString("BAYAR_TANAH")== null?"":rs.getString("BAYAR_TANAH"));
		    			h.put("bayar_pecahpisah", rs.getString("BAYAR_PECAHPISAH")== null?"":rs.getString("BAYAR_PECAHPISAH"));
		    			h.put("bayar_penjejasan", rs.getString("BAYAR_PENJEJASAN")== null?"":rs.getString("BAYAR_PENJEJASAN"));
		    			h.put("naik_nilai", rs.getString("NAIK_NILAI")== null?"":rs.getString("NAIK_NILAI"));
		    			h.put("bayar_lain2", rs.getString("BAYAR_LAIN2")== null?"":rs.getString("BAYAR_LAIN2"));
		    			h.put("bayar_pampasan", rs.getString("BAYAR_PAMPASAN")== null?"":rs.getString("BAYAR_PAMPASAN"));
		    			h.put("bayar_bangunan", rs.getString("BAYAR_BANGUNAN")== null?"":rs.getString("BAYAR_BANGUNAN"));

		    			double nilai_tanah = 0;
						if(rs.getString("LUASHM_AMBIL") != null && rs.getString("HARGA_SEUNIT_SO") != null){
		    				
							nilai_tanah = (rs.getDouble("HARGA_SEUNIT_SO") * rs.getDouble("LUASHM_AMBIL"));
							h.put("nilai_tanah", nilai_tanah);
							
		    			}else{
		    				h.put("nilai_tanah", nilai_tanah);
		    			}
		    			
		    			h.put("asal_bayar_bangunan", rs.getString("ASAL_BAYAR_BANGUNAN")== null?"":rs.getString("ASAL_BAYAR_BANGUNAN"));
		    			h.put("asal_bayar_fee", rs.getString("ASAL_BAYAR_FEE")== null?"":rs.getString("ASAL_BAYAR_FEE"));
		    			h.put("asal_bayar_tanah", rs.getString("ASAL_BAYAR_TANAH")== null?"0":rs.getString("ASAL_BAYAR_TANAH"));
		    			h.put("asal_bayar_pecahpisah", rs.getString("ASAL_BAYAR_PECAHPISAH")== null?"":rs.getString("ASAL_BAYAR_PECAHPISAH"));
		    			h.put("asal_bayar_penjejasan", rs.getString("ASAL_BAYAR_PENJEJASAN")== null?"":rs.getString("ASAL_BAYAR_PENJEJASAN"));
		    			h.put("asal_naik_nilai", rs.getString("NAIK_NILAI_JPPH")== null?"":rs.getString("NAIK_NILAI_JPPH"));
		    			
		    			dataPenyediaanPampasan.addElement(h);
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataPenyediaanPampasan
	
	@SuppressWarnings("unchecked")
	public static void updateMaklumatPampasan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_award = (String)data.get("id_award");
	    		String id_pihakberkepentingan = (String)data.get("id_pihakberkepentingan");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		String id_siasatan = (String)data.get("id_siasatan");
	    		
	    		//data pb (update)
	    		String txtNoPB = (String)data.get("txtNoPB");
	    		String socJenisPB = (String)data.get("socJenisPB");
	    		String txtSyorAtas = (String)data.get("txtSyorAtas");
	    		String txtSyorBawah = (String)data.get("txtSyorBawah");
	    		
	    		//data tblpptaward (insert)
	    		String txtLuasAmbil = (String)data.get("txtLuasAmbil");
	    		String unitLuas = (String)data.get("unitLuas");
	    		String socStatusPenerima = (String)data.get("socStatusPenerima");
	    		String txtFeeAward = (String)data.get("txtFeeAward");
	    		String txtTanahAward = (String)data.get("txtTanahAward");
	    		String txtBangunanAward = (String)data.get("txtBangunanAward");
	    		String txtPecahpisahAward = (String)data.get("txtPecahpisahAward");
	    		String txtPenjejasanAward = (String)data.get("txtPenjejasanAward");
	    		String txtNaikAward = (String)data.get("txtNaikAward");
	    		String txtLainPampasan = (String)data.get("txtLainPampasan");
	    		String txtJumlahPampasan = (String)data.get("txtJumlahPampasan");
	    		
	    		//insert
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_award", id_award);
	    		r.add("id_siasatan", id_siasatan);	
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("id_pihakberkepentingan", id_pihakberkepentingan);	
	    		r.add("luas_ambil", txtLuasAmbil);	
	    		r.add("id_unitluas_ambil", unitLuas);		
	    		r.add("status_penerima", socStatusPenerima);	
	    		r.add("bayar_fee", txtFeeAward);	
	    		r.add("bayar_tanah", txtTanahAward);	
	    		r.add("bayar_bangunan", txtBangunanAward);	
	    		r.add("bayar_pecahpisah", txtPecahpisahAward);	
	    		r.add("bayar_penjejasan", txtPenjejasanAward);	
	    		r.add("naik_nilai", txtNaikAward);	
	    		r.add("bayar_lain2", txtLainPampasan);	
	    		r.add("bayar_pampasan", txtJumlahPampasan);	
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptaward");
	    		stmt.executeUpdate(sql);
	    		
	    		//update
	    		SQLRenderer rx = new SQLRenderer();
	    		rx.update("id_hakmilikpb", id_hakmilikpb);
	    		rx.add("id_jenispb", socJenisPB);		  
	    		rx.add("syer_atas", txtSyorAtas);		 
	    		rx.add("syer_bawah", txtSyorBawah);		 
	    		rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
	    		rx.add("id_kemaskini",id_user);    		
	    		sql = rx.getSQLUpdate("tblppthakmilikpb");
	    		stmt.executeUpdate(sql);
	    		
	    		//update
	    		SQLRenderer rz = new SQLRenderer();
	    		rz.update("id_pihakberkepentingan", id_pihakberkepentingan);
	    		rz.add("no_pb", txtNoPB);		  
	    		rz.add("tarikh_kemaskini",rz.unquote("sysdate"));
	    		rz.add("id_kemaskini",id_user);    		
	    		sql = rz.getSQLUpdate("tblpptpihakberkepentingan");
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateMaklumatPampasan
	
	@SuppressWarnings("unchecked")
	public void setListMaklumatTanahWithSiasatan(String idPermohonan,String lot) throws Exception{
		
		listMaklumatTanahWithSiasatan = new Vector();
		
		Db db = null;
		listMaklumatTanahWithSiasatan.clear();
		String sql = "";
		String noLOT = lot.trim();
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT  m.seksyen,m.no_subjaket,m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, jh.kod_jenis_hakmilik, "; 
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, "; 
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, "; 
				sql += " m.flag_ambil_segera, u.user_name as nama_pegawai, m.id_unitluasambil_convert, "; 
				sql += "(select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
				sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
				sql += " CASE "; 
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";  
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt  ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt  "; 
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) "; 
				sql += " ELSE ''  ";
				sql += " END AS NO_LOTPT   ";                 
				sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, Tblpptsiasatan ss, Users u ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+)    ";
				sql += " AND m.id_negeri = n.id_negeri ";  
				sql += " AND ls.id_luas(+) = m.id_unitluaslot ";   
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+) ";
				sql += " AND ss.id_hakmilik(+) = m.id_hakmilik  ";
				sql += " AND m.id_lot = lt.id_lot(+)  ";
				sql += " AND m.id_mukim = mk.id_mukim(+)  ";  
				sql += " AND m.id_pegawai = u.user_id(+)  ";  
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y'  ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.FLAG_STOP_SIASATAN,0) <> 'Y'";
				sql += " AND ss.id_siasatan is not null  ";
				sql += " AND (select count(aw.id_award) from tblpptaward aw where aw.id_siasatan = ss.id_siasatan) > 0 ";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				//NO LOT / NAMA PB
				if (lot != "" && lot != null) {
					if (!noLOT.equals("")) {
						sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' " +
							   " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' " +
							   " OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%' "+
							   " OR M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
		                       " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
		                       " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
		                       " AND M1.ID_HAKMILIK = M.ID_HAKMILIK "+ 
		                       " AND upper(PB1.NAMA_PB) LIKE ('%"+noLOT.toUpperCase()+"%'))) ";
					}
				}//close if nolot
				
				//sql += " ORDER BY LPAD(m.no_subjaket,10) asc, LPAD(m.no_lot,10) asc, LPAD(m.no_pt,10) asc, LPAD(NO_LOTPT,10) asc, mk.nama_mukim asc ";
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
				myLogger.info("LIST HAKMILIK :"+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
					h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
					h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
					h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"-":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					if(rs.getString("id_unitluasambil_convert") != null){
						
						if(rs.getString("id_unitluasambil_convert").equals("1")){
							h.put("unitByKategori", "Hektar");
						}else{
							h.put("unitByKategori", "Meter Persegi");
						}			
					}else{
						h.put("unitByKategori", "");
					}

					if(rs.getString("id_unitluasambil_convert") != null){						
						if(rs.getString("id_unitluasambil_convert").equals("1")){	
							
							if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
								double luasAmbil = rs.getDouble("luas_ambil") * 10000;
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}			
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"-":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
					h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getDouble("luas_lot"));
					h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					
					h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
					h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
					h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
					
					h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
					h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
					h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));
					
					h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
					h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
					h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
					
					listMaklumatTanahWithSiasatan.addElement(h);
					bil++;	
				}
			//return list;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setListMaklumatTanahWithSiasatan
	
	
	
	
	
public int setListMaklumatTanahWithSiasatan_count(String idPermohonan,String lot) throws Exception{
		
		
		Db db = null;
		String sql = "";
		String noLOT = lot.trim();
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT count(*) as total   ";                 
				sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, Tblpptsiasatan ss, Users u ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+)    ";
				sql += " AND m.id_negeri = n.id_negeri ";  
				sql += " AND ls.id_luas(+) = m.id_unitluaslot ";   
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+) ";
				sql += " AND ss.id_hakmilik(+) = m.id_hakmilik  ";
				sql += " AND m.id_lot = lt.id_lot(+)  ";
				sql += " AND m.id_mukim = mk.id_mukim(+)  ";  
				sql += " AND m.id_pegawai = u.user_id(+)  ";  
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y'  ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.FLAG_STOP_SIASATAN,0) <> 'Y'";
				sql += " AND ss.id_siasatan is not null  ";
				sql += " AND (select count(aw.id_award) from tblpptaward aw where aw.id_siasatan = ss.id_siasatan) > 0 ";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				//NO LOT / NAMA PB
				if (lot != "" && lot != null) {
					if (!noLOT.equals("")) {
						sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' " +
							   " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' " +
							   " OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%' "+
							   " OR M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
		                       " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
		                       " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
		                       " AND M1.ID_HAKMILIK = M.ID_HAKMILIK "+ 
		                       " AND upper(PB1.NAMA_PB) LIKE ('%"+noLOT.toUpperCase()+"%'))) ";
					}
				}//close if nolot
				
				//sql += " ORDER BY LPAD(m.no_subjaket,10) asc, LPAD(m.no_lot,10) asc, LPAD(m.no_pt,10) asc, LPAD(NO_LOTPT,10) asc, mk.nama_mukim asc ";
				//sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
				
				ResultSet rs = stmt.executeQuery(sql);	
				int total = 0;
				
				while (rs.next()){
					total = rs.getInt("total");
					
					
				}
			return total;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setListMaklumatTanahWithSiasatan
	
	@SuppressWarnings("unchecked") 
	public static void setDataBorangG(String idSiasatan)throws Exception {
			
		dataBorangG = new Vector();
			
		    Db db = null;
		    dataBorangG.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT A.ID_HAKMILIK,B.ID_SIASATAN,C.ID_BORANGG, A.JUMLAH_AWARD, B.NO_SIASATAN, C.TARIKH_BORANGG, C.TARIKH_BORANGH, D.ID_BORANGH ";
		    		sql += " FROM TBLPPTHAKMILIK A, TBLPPTSIASATAN B, TBLPPTBORANGG C, TBLPPTBORANGH D ";
		    		sql += " WHERE A.ID_HAKMILIK = B.ID_HAKMILIK ";
		    		sql += " AND B.ID_SIASATAN = C.ID_SIASATAN ";
		    		sql += " AND C.ID_BORANGG = D.ID_BORANGG(+) ";
		    		sql += " AND C.ID_SIASATAN= '"+idSiasatan+"'";
		    		
		    			      
		    		ResultSet rs = stmt.executeQuery(sql);
		    		myLogger.info("list-- "+sql);
		    		Hashtable h;
		    
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
		    			h.put("id_siasatan", rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));
		    			h.put("id_borangg", rs.getString("ID_BORANGG")== null?"":rs.getString("ID_BORANGG"));
		    			h.put("id_borangh", rs.getString("ID_BORANGH")== null?"":rs.getString("ID_BORANGH"));
		    			
		    			if(rs.getString("JUMLAH_AWARD") != null){
		    				
		    				double TP = rs.getDouble("JUMLAH_AWARD");
		    				String total = Utils.format2Decimal(TP);
		    				h.put("jumlah_award",total);
		    				
		    			}else{
		    				h.put("jumlah_award", "0.00");
		    			}
		    			
		    			h.put("jumlah_award", rs.getString("JUMLAH_AWARD")== null?"":rs.getDouble("JUMLAH_AWARD"));
		    			h.put("tarikh_borangg", rs.getDate("TARIKH_BORANGG")==null?"":Format.format(rs.getDate("TARIKH_BORANGG")));
						h.put("tarikh_borangh", rs.getDate("TARIKH_BORANGH")==null?"":Format.format(rs.getDate("TARIKH_BORANGH")));
						h.put("no_siasatan", rs.getString("NO_SIASATAN")== null?"":rs.getString("NO_SIASATAN"));
												
		    			dataBorangG.addElement(h);
		    			myLogger.info("xxx"+h.put("tarikh_borangg", rs.getDate("TARIKH_BORANGG")==null?"":Format.format(rs.getDate("TARIKH_BORANGG"))));
		    		
		    			
		      }//close while
		    		
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataDokumenBorangG
	
	
	@SuppressWarnings("unchecked") 
	public static void setDokumenImejG(String id_borangg)throws Exception {
			
		dokumenImejG = new Vector();
			
		    Db db = null;
		    dokumenImejG.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT A.ID_BORANGG, B.ID_PPTIMEJBORANG, B.ID_JENISDOKUMEN, B.JENIS_MIME " +
		    				"FROM TBLPPTBORANGG A, TBLPPTIMEJBORANG B " +
		    				"WHERE A.ID_PPTIMEJBORANG = B.ID_PPTIMEJBORANG " +
		    				"AND B.ID_JENISDOKUMEN = '1163' " +
		    				"AND A.ID_BORANGG = '"+id_borangg+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_borangg", rs.getString("ID_BORANGG")== null?"":rs.getString("ID_BORANGG"));
		    			h.put("id_pptimejborangg", rs.getString("ID_PPTIMEJBORANG")== null?"":rs.getString("ID_PPTIMEJBORANG"));
		    			h.put("id_jenisdokumen", rs.getString("ID_JENISDOKUMEN")== null?"":rs.getString("ID_JENISDOKUMEN"));
		    			h.put("jenis_mime", rs.getString("JENIS_MIME")== null?"":rs.getString("JENIS_MIME"));
	  		
						dokumenImejG.addElement(h);
		    			
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close dokumenImejG
	
	@SuppressWarnings("unchecked") 
	public static void setDokumenImejH(String id_borangg)throws Exception {
			
		dokumenImejH = new Vector();
			
		    Db db = null;
		    dokumenImejH.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT A.ID_BORANGG, B.ID_PPTIMEJBORANG, B.ID_JENISDOKUMEN, B.JENIS_MIME " +
		    				"FROM TBLPPTBORANGH A, TBLPPTIMEJBORANG B " +
		    				"WHERE A.ID_PPTIMEJBORANG = B.ID_PPTIMEJBORANG " +
		    				"AND B.ID_JENISDOKUMEN = '1164'" +
		    				"AND A.ID_BORANGG = '"+id_borangg+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_borangg", rs.getString("ID_BORANGG")== null?"":rs.getString("ID_BORANGG"));
		    			h.put("id_pptimejborangh", rs.getString("ID_PPTIMEJBORANG")== null?"":rs.getString("ID_PPTIMEJBORANG"));
		    			h.put("id_jenisdokumen", rs.getString("ID_JENISDOKUMEN")== null?"":rs.getString("ID_JENISDOKUMEN"));
		    			h.put("jenis_mime", rs.getString("JENIS_MIME")== null?"":rs.getString("JENIS_MIME"));
		    		
						dokumenImejH.addElement(h);
						
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close dokumenImejH
	
	
	@SuppressWarnings("unchecked") 
	public static void setDokumenPampasan(String idHakmilik)throws Exception {
			
		dokumenPampasan = new Vector();
			
		    Db db = null;
		    dokumenPampasan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT A.ID_DOKUMEN, A.ID_JENISDOKUMEN, A.NAMA_FAIL " +
		    				"FROM TBLPPTDOKUMEN A " +
		    				"WHERE A.ID_JENISDOKUMEN = '1525'" +
		    				"AND ID_HAKMILIK = '"+idHakmilik+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    
		    		while (rs.next()) {
		    			h = new Hashtable();
		    		
		    			h.put("idDokumen", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
		    			h.put("id_jenisdokumen", rs.getString("ID_JENISDOKUMEN")== null?"":rs.getString("ID_JENISDOKUMEN"));
		    			h.put("nama_fail", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
		    			
		    			dokumenPampasan.addElement(h);
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close dokumenImejH
	@SuppressWarnings("unchecked")
	public static void setDataDokumenBorangG(String idHakmilik)throws Exception {
			
		dataDokumenBorangG = new Vector();
			
		    Db db = null;
		    dataDokumenBorangG.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT ID_DOKUMEN , NAMA_FAIL FROM TBLPPTDOKUMEN WHERE ID_HAKMILIK  = '"+idHakmilik+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_dokumen", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
		    			h.put("nama_fail", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
		    			
		    			dataDokumenBorangG.addElement(h);
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataDokumenBorangG
	
	@SuppressWarnings("unchecked")
	public static void setIdSiasatan(String idHakmilik)throws Exception {
			
		dataIdSiasatan = new Vector();
			
		    Db db = null;
		    dataIdSiasatan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = " SELECT A.ID_SIASATAN FROM TBLPPTSIASATAN A, TBLPPTHAKMILIK B ";
		    		sql += " WHERE A.ID_HAKMILIK = B.ID_HAKMILIK ";
		    		sql += " AND A.id_siasatan = (SELECT MAX(A1.id_siasatan) FROM TBLPPTSIASATAN A1 WHERE A1.ID_HAKMILIK = B.ID_HAKMILIK) "; 
		    		sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_siasatan", rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));
		    			dataIdSiasatan.addElement(h);
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setIdSiasatan
	
	@SuppressWarnings("unchecked")
	
	public static void setTotalPampasan(String idSiasatan)throws Exception {  //perubahan query yati 
			
		totalPampasan = new Vector();
			
		    Db db = null;
		    totalPampasan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql =  " SELECT SUM(D.JUMLAH_SUBAWARD) AS TOTAL_PAMPASAN, SUM(C.BAYAR_PAMPASAN) " ;
		    		sql += " FROM TBLPPTSUBAWARD D , TBLPPTAWARD C, TBLPPTSIASATAN B ";
		    		sql += " WHERE B.ID_SIASATAN = C.ID_SIASATAN ";
		    		sql += " AND C.ID_AWARD = D.ID_AWARD ";
		    		sql += " AND D.FLAG_JENIS_AWARD != 'BAYAR_LAIN' ";
		    		sql += " AND D.FLAG_JENIS_AWARD != 'BAYAR_PENILAI' ";
		    		sql += " AND B.ID_SIASATAN = '"+idSiasatan+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		    		
		    		myLogger.info("SQL PAMPASAN LIST :"+sql);
		      
		    		Hashtable h;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			
		    			if(rs.getString("TOTAL_PAMPASAN") != null){
		    				
		    				double TP = rs.getDouble("TOTAL_PAMPASAN");
		    				String total = Utils.format2Decimal(TP);
		    				h.put("total_pampasan",total);
		    				
		    			}else{
		    				h.put("total_pampasan", "0.00");
		    			}
		    			
		    			totalPampasan.addElement(h);
		    		
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setTotalPampasan
	
	@SuppressWarnings("unchecked") // penambahbaikan yati
	public static void simpanDataBorangG(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    long id_borangg= 0;
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_siasatan = (String)data.get("id_siasatan");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String txtJumlahAward = (String)data.get("txtJumlahAward");
	    		String txdTarikhBorangG = (String)data.get("txdTarikhBorangG");
	    		String txdTarikhBorangH = (String)data.get("txdTarikhBorangH");
	
	    		String TG = "to_date('" + txdTarikhBorangG + "','dd/MM/yyyy')";
	    		String TH = "to_date('" + txdTarikhBorangH + "','dd/MM/yyyy')";
	    		
	    		//penambahan yati
	    		String check_id_borangg = (String) data.get("id_borangg");
	    		
	    		if(check_id_borangg.equals(""))
				{
	    			id_borangg = DB.getNextID(db, "TBLPPTBORANGG_SEQ");
				}
				else
				{
					id_borangg = Long.parseLong(check_id_borangg);
				}
	    		
	    		//insert tblborang
	    		SQLRenderer r = new SQLRenderer();	
	    		r.add("id_borangg", id_borangg);
	    		r.add("id_siasatan", id_siasatan);
	    		r.add("tarikh_borangg",r.unquote(TG));
	    		r.add("tarikh_borangh",r.unquote(TH));	    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);  
	    		if(check_id_borangg.equals(""))
				{
				sql = r.getSQLInsert("tblpptborangg");
				}
				stmt.executeUpdate(sql);
				
				SQLRenderer rz = new SQLRenderer();	
				//rz.add("id_borangh",id_borangh);
				rz.add("id_borangg",id_borangg);
				//rz.add("tarikh_borangg",rz.unquote(TG));
	    		rz.add("tarikh_borangh",rz.unquote(TH));	 		
				sql = rz.getSQLInsert("tblpptborangh");
				myLogger.info("INSERT TBLPPTBORANGH : "+sql);
				stmt.executeUpdate(sql);	
//				
//	    		//update tblppthakmilik
	    		SQLRenderer rx = new SQLRenderer();	    		
	    		rx.update("id_hakmilik", id_hakmilik);
	    		rx.add("jumlah_award",txtJumlahAward);	    		
	    		rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
	    		rx.add("id_kemaskini",id_user);    		
	    		sql = rx.getSQLUpdate("tblppthakmilik");
	    		stmt.executeUpdate(sql);
	    		
	    		myLogger.info("SQL--- : "+sql);
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanDataBorangG
	
	@SuppressWarnings("unchecked")
	public static void updateDataBorangG(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    long id_borangg= 0;
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	
	    		String id_user = (String)data.get("id_user");	    	
	    		//String id_borangg = (String)data.get("id_borangg");
	    		String idImejBorangG = (String)data.get("idImejBorangG");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String txtJumlahAward = (String)data.get("txtJumlahAward");
	    		String txdTarikhBorangG = (String)data.get("txdTarikhBorangG");
	    		String txdTarikhBorangH = (String)data.get("txdTarikhBorangH");
	
	    		String TG = "to_date('" + txdTarikhBorangG + "','dd/MM/yyyy')";
	    		String TH = "to_date('" + txdTarikhBorangH + "','dd/MM/yyyy')";
	    		
	    		//penambahan yati
	    		String check_id_borangg = (String) data.get("id_borangg");
		
	    		if(check_id_borangg.equals(""))
				{
	    			id_borangg = DB.getNextID(db, "TBLPPTBORANGG_SEQ");
				}
				else
				{
					id_borangg = Long.parseLong(check_id_borangg);
				}
	    		
	    		//insert tblborang	    		
	    		SQLRenderer r = new SQLRenderer();	    		
	    		//r.update("id_borangg", id_borangg);
	    		
	    		if(check_id_borangg.equals(""))
				{
					r.add("id_borangg", id_borangg);
				}
				else
				{
					r.update("id_borangg", id_borangg);
				}
	    		r.add("id_pptimejborang", idImejBorangG);
	    		r.add("tarikh_borangg",r.unquote(TG));
	    		r.add("tarikh_borangh",r.unquote(TH));	    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptborangg");
	    		myLogger.info("kemaskini- --- :"+sql);
	    		stmt.executeUpdate(sql);
	    		
	    		//update tblppthakmilik
	    		SQLRenderer rx = new SQLRenderer();	    		
	    		rx.update("id_hakmilik", id_hakmilik);
	    		rx.add("jumlah_award",txtJumlahAward);	    		
	    		rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
	    		rx.add("id_kemaskini",id_user);    		
	    		sql = rx.getSQLUpdate("tblppthakmilik");
	    		stmt.executeUpdate(sql);

	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateDataBorangG
	
	@SuppressWarnings("unchecked")
	public static void setListBorangH(String id_borangg)throws Exception {
			
		listBorangH = new Vector();
			
		    Db db = null;
		    listBorangH.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT D.ID_HAKMILIKPB, B.ID_BORANGH, B.TARIKH_SERAH, B.TARIKH_TERIMA, B.TARIKH_AKHIR_BAYARAGENSI, B.KEPUTUSAN, ";
		    		sql += " D.ID_PIHAKBERKEPENTINGAN, C.NAMA_PB, "; 		    
		    		sql += " B.HUBUNGAN, B.NAMA_PENGHANTAR, B.MASA_TAMPAL, B.JENIS_WAKTU, ";
		    		sql += " B.TEMPAT_TAMPAL, B.JENIS_SERAHAN, B.FLAG_SERAH, B.NAMA_PENERIMA, B.NO_KP_PENERIMA, B.CATATAN, ";
		    		sql += " B.ID_JENISNOPB, B.TARIKH_HANTAR ";		    		
		    		sql += " FROM TBLPPTBORANGG A, TBLPPTBORANGH B, TBLPPTPIHAKBERKEPENTINGAN C, TBLPPTHAKMILIKPB D ";
		    		sql += " WHERE B.ID_BORANGG = A.ID_BORANGG ";
		    		sql += " AND B.ID_HAKMILIKPB = D.ID_HAKMILIKPB ";
		    		sql += " AND D.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN(+)";
		    		sql += " AND A.ID_BORANGG = '"+id_borangg+"'";
		    		sql += " ORDER BY NAMA_PB ";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int bil = 1;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
		    			h.put("id_borangh", rs.getString("ID_BORANGH")== null?"":rs.getString("ID_BORANGH"));
		    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
		    			h.put("keputusan", rs.getString("KEPUTUSAN")== null?"":rs.getString("KEPUTUSAN"));
		    			
		    			if(rs.getString("KEPUTUSAN") != null && rs.getString("KEPUTUSAN") != ""){
		    				if(rs.getString("KEPUTUSAN").equals("1")){
		    					h.put("jenis_keputusan","MENERIMA TAWARAN");
		    				}else if(rs.getString("KEPUTUSAN").equals("2")){
		    					h.put("jenis_keputusan","MENERIMA TAWARAN DENGAN BANTAHAN");
		    				}else if(rs.getString("KEPUTUSAN").equals("3")){
		    					h.put("jenis_keputusan","TIDAK MENERIMA TAWARAN");
		    				}else if(rs.getString("KEPUTUSAN").equals("4")){
		    					h.put("jenis_keputusan","TIDAK BERKENAAN");
		    				}else{
		    					h.put("jenis_keputusan","");
		    				}
		    			}else{
		    				h.put("jenis_keputusan","");
		    			}
		    			
		    			h.put("tarikh_serah", rs.getDate("TARIKH_SERAH")==null?"":Format.format(rs.getDate("TARIKH_SERAH")));
						h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
						h.put("tarikh_akhir_bayar", rs.getDate("TARIKH_AKHIR_BAYARAGENSI")==null?"":Format.format(rs.getDate("TARIKH_AKHIR_BAYARAGENSI")));
						
						h.put("tarikh_hantar", rs.getDate("TARIKH_HANTAR")==null?"":Format.format(rs.getDate("TARIKH_HANTAR")));
						h.put("hubungan", rs.getString("HUBUNGAN")== null?"":rs.getString("HUBUNGAN"));
						h.put("nama_penghantar", rs.getString("NAMA_PENGHANTAR")== null?"":rs.getString("NAMA_PENGHANTAR"));
						h.put("masa_tampal", rs.getString("MASA_TAMPAL")== null?"":rs.getString("MASA_TAMPAL"));
						h.put("jenis_waktu", rs.getString("JENIS_WAKTU")== null?"":rs.getString("JENIS_WAKTU"));
						h.put("tempat_tampal", rs.getString("TEMPAT_TAMPAL")== null?"":rs.getString("TEMPAT_TAMPAL"));
						h.put("jenis_serahan", rs.getString("JENIS_SERAHAN")== null?"":rs.getString("JENIS_SERAHAN"));
						h.put("flag_serah", rs.getString("FLAG_SERAH")== null?"":rs.getString("FLAG_SERAH"));
						h.put("nama_penerima", rs.getString("NAMA_PENERIMA")== null?"<b>Tidak Dapat Diserahkan</b>":rs.getString("NAMA_PENERIMA"));
						h.put("no_kp_penerima", rs.getString("NO_KP_PENERIMA")== null?"":rs.getString("NO_KP_PENERIMA"));
						h.put("catatan", rs.getString("CATATAN")== null?"":rs.getString("CATATAN"));
						h.put("id_jenisnopb", rs.getString("ID_JENISNOPB")== null?"":rs.getString("ID_JENISNOPB"));
						
						listBorangH.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setListBorangH
	
	
	@SuppressWarnings("unchecked")
	public static int setListBorangH_count(String id_borangg)throws Exception {
			
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT count(*) as total FROM TBLPPTBORANGG A, TBLPPTBORANGH B, TBLPPTPIHAKBERKEPENTINGAN C, TBLPPTHAKMILIKPB D ";
		    		sql += " WHERE B.ID_BORANGG = A.ID_BORANGG ";
		    		sql += " AND B.ID_HAKMILIKPB = D.ID_HAKMILIKPB ";
		    		sql += " AND D.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN(+)";
		    		sql += " AND A.ID_BORANGG = '"+id_borangg+"'";
		    		sql += " ORDER BY NAMA_PB ";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		int total = 0;
		    		
		    		while (rs.next()) {
		    			
		    			total = rs.getInt("total");
		    			
		    			myLogger.info("sql list borang h--" +sql);
		    			
		    			
		      }//close while
		      return total;
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setListBorangH
	
	@SuppressWarnings("unchecked")
	public static void simpanPenerimaanBorangH(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    
	    long id_borangh= 0;
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_borangg = (String)data.get("id_borangg");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		//String id_borangh = (String) data.get("id_borangh");	
	    		
	    		//data bukti penyampaian
	    		String txtNamaHantar = (String)data.get("txtNamaHantar");
	    		String socJenisSerah = (String)data.get("socJenisSerah");
	    		String socStatusSerah = (String)data.get("socStatusSerah");
	    		String txdTarikhSerah = (String)data.get("txdTarikhSerah");
	    		
	    		//data penerima
	    		String txtNamaTerima = (String)data.get("txtNamaTerima");
	    		String txtNoKP = (String)data.get("txtNoKP");
	    		String socJenisNoKP = (String)data.get("socJenisNoKP");
	    		String txtHubungan = (String)data.get("txtHubungan");
	    		
	    		//data tampal
	    		String txtMasaTampal = (String)data.get("txtMasaTampal");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtTempatTampal = (String)data.get("txtTempatTampal");

	    		String txtCatatan = (String)data.get("txtCatatan");
	    		
	    		String TS = "to_date('" + txdTarikhSerah + "','dd/MM/yyyy')";
	    		
	    		String check_id_borangh = (String) data.get("id_borangh");
	    				
	    		if(check_id_borangh.equals(""))
				{
	    			id_borangh = DB.getNextID(db, "TBLPPTBORANGH_SEQ");
				}
				else
				{
					id_borangh = Long.parseLong(check_id_borangh);
				}
	    		
	    		//insert
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_borangh", id_borangh);		
	    		r.add("id_borangg", id_borangg);	
	    		r.add("id_hakmilikpb", id_hakmilikpb);	    		
	    		r.add("hubungan", txtHubungan);	
	    		r.add("nama_penghantar", txtNamaHantar);
	    		r.add("masa_tampal", txtMasaTampal);	
	    		r.add("jenis_waktu", socJenisWaktu);	
	    		r.add("tempat_tampal", txtTempatTampal);	
	    		r.add("jenis_serahan", socJenisSerah);
	    		r.add("flag_serah", socStatusSerah);
	    		r.add("nama_penerima", txtNamaTerima);
	    		r.add("no_kp_penerima", txtNoKP);
	    		r.add("catatan", txtCatatan);
	    		r.add("id_jenisnopb", socJenisNoKP);	        		
	    		r.add("tarikh_hantar",r.unquote(TS));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptborangh");
	    	
	    		stmt.executeUpdate(sql);
	    		
	    		
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanPenerimaanBorangH
	
	@SuppressWarnings("unchecked")
	public static void setDataBorangH(String id_borangh)throws Exception {
			
		dataBorangH = new Vector();
			
		    Db db = null;
		    dataBorangH.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		        		
		    		sql = "SELECT DISTINCT D.ID_HAKMILIKPB, B.ID_BORANGH, B.TARIKH_SERAH, B.TARIKH_TERIMA, B.TARIKH_AKHIR_BAYARAGENSI, B.KEPUTUSAN, ";
		    		sql += " D.ID_PIHAKBERKEPENTINGAN, C.ID_JENISNOPB AS ID_KODNOPB, C.NAMA_PB, B.HUBUNGAN, B.NAMA_PENGHANTAR, B.MASA_TAMPAL, B.JENIS_WAKTU, ";
		    		sql += " B.TEMPAT_TAMPAL, B.JENIS_SERAHAN, B.FLAG_SERAH, B.NAMA_PENERIMA, B.NO_KP_PENERIMA, B.CATATAN, ";
		    		sql += " B.ID_JENISNOPB, B.TARIKH_HANTAR, C.NO_PB ";
		    		sql += " FROM TBLPPTBORANGG A, TBLPPTBORANGH B, TBLPPTPIHAKBERKEPENTINGAN C, TBLPPTHAKMILIKPB D ";
		    		sql += " WHERE B.ID_BORANGG = A.ID_BORANGG ";
		    		sql += " AND B.ID_HAKMILIKPB = D.ID_HAKMILIKPB ";
		    		sql += " AND D.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN(+)";
		    		sql += " AND B.ID_BORANGH = '"+id_borangh+"'";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
		    			h.put("id_borangh", rs.getString("ID_BORANGH")== null?"":rs.getString("ID_BORANGH"));
		    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
		    			h.put("keputusan", rs.getString("KEPUTUSAN")== null?"":rs.getString("KEPUTUSAN"));
		    			h.put("tarikh_serah", rs.getDate("TARIKH_SERAH")==null?"":Format.format(rs.getDate("TARIKH_SERAH")));
						h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
						h.put("tarikh_akhir_bayar", rs.getDate("TARIKH_AKHIR_BAYARAGENSI")==null?"":Format.format(rs.getDate("TARIKH_AKHIR_BAYARAGENSI")));
						h.put("tarikh_hantar", rs.getDate("TARIKH_HANTAR")==null?"":Format.format(rs.getDate("TARIKH_HANTAR")));
						
						h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));	
						h.put("id_kodnopb", rs.getString("ID_KODNOPB")==null?"":rs.getString("ID_KODNOPB"));	
						h.put("hubungan", rs.getString("HUBUNGAN")== null?"":rs.getString("HUBUNGAN"));
						h.put("nama_penghantar", rs.getString("NAMA_PENGHANTAR")== null?"":rs.getString("NAMA_PENGHANTAR"));
						h.put("masa_tampal", rs.getString("MASA_TAMPAL")== null?"":rs.getString("MASA_TAMPAL"));
						h.put("jenis_waktu", rs.getString("JENIS_WAKTU")== null?"":rs.getString("JENIS_WAKTU"));
						h.put("tempat_tampal", rs.getString("TEMPAT_TAMPAL")== null?"":rs.getString("TEMPAT_TAMPAL"));
						h.put("jenis_serahan", rs.getString("JENIS_SERAHAN")== null?"":rs.getString("JENIS_SERAHAN"));
						h.put("status_serahan", rs.getString("FLAG_SERAH")== null?"":rs.getString("FLAG_SERAH"));
						h.put("nama_penerima", rs.getString("NAMA_PENERIMA")== null?"":rs.getString("NAMA_PENERIMA"));
						h.put("no_kp_penerima", rs.getString("NO_KP_PENERIMA")== null?"":rs.getString("NO_KP_PENERIMA"));
						h.put("catatan", rs.getString("CATATAN")== null?"":rs.getString("CATATAN"));
						h.put("id_jenisnopb", rs.getString("ID_JENISNOPB")== null?"":rs.getString("ID_JENISNOPB"));
						
						dataBorangH.addElement(h);
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataBorangH
	
	public static void hapusMaklumat(String id_award) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptaward where id_award = '"+id_award+"'";
	    		stmt.executeUpdate(sql);
  	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusMaklumat
	
	public static void hapusBorangH(String id_borangh) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptborangh where id_borangh = '"+id_borangh+"'";
	    		stmt.executeUpdate(sql);
	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusMaklumat
	
	@SuppressWarnings("unchecked")
	public static void updatePenerimaanBorangH(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_borangh = (String)data.get("id_borangh");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		
	    		String txtNamaHantar = (String)data.get("txtNamaHantar");
	    		String socJenisSerah = (String)data.get("socJenisSerah");
	    		String socStatusSerah = (String)data.get("socStatusSerah");
	    		String txdTarikhSerah = (String)data.get("txdTarikhSerah");
	    		String txtCatatan = (String)data.get("txtCatatan");
	    		
	    		String txtNamaTerima = (String)data.get("txtNamaTerima");
	    		String txtNoKP = (String)data.get("txtNoKP");	 
	    		String socJenisNoKP = (String)data.get("socJenisNoKP");
	    		String txtHubungan = (String)data.get("txtHubungan");
	    		
	    		//data tampal
	    		String txtMasaTampal = (String)data.get("txtMasaTampal");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtTempatTampal = (String)data.get("txtTempatTampal");
	    		
	    		String TS = "to_date('" + txdTarikhSerah + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_borangh", id_borangh);	
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("nama_penghantar", txtNamaHantar);
	    		r.add("hubungan", txtHubungan);
	    		r.add("masa_tampal", txtMasaTampal);
	    		r.add("jenis_waktu", socJenisWaktu);
	    		r.add("tempat_tampal", txtTempatTampal);
	    		r.add("jenis_serahan", socJenisSerah);
	    		r.add("flag_serah", socStatusSerah);
	    		r.add("nama_penerima", txtNamaTerima);
	    		r.add("no_kp_penerima", txtNoKP);
	    		r.add("catatan", txtCatatan);
	    		r.add("id_jenisnopb", socJenisNoKP);	
	    		r.add("tarikh_hantar",r.unquote(TS));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptborangh");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updatePenerimaanBorangH
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);	
	    		r.add("id_status", "68");
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateStatus
	
	@SuppressWarnings("unchecked")
	public void setDataWarta(String idpermohonan) throws Exception {
		
		dataWarta = new Vector();
		
		Db db = null;
		dataWarta.clear();
		String sql = "";
		String sqlz = "";
		String id_warta = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
			
				sqlz = " SELECT DISTINCT MAX(ID_WARTA)AS ID_WARTA FROM TBLPPTWARTA WHERE ID_PERMOHONAN = '"+idpermohonan+"'";
				ResultSet rz = stmt.executeQuery(sqlz);
				
				while(rz.next()) {
					id_warta = rz.getString("ID_WARTA");
				}
		
				sql = "SELECT DISTINCT A.ID_WARTA, A.NO_WARTA, A.TARIKH_WARTA " +
					  " FROM TBLPPTWARTA A WHERE A.ID_WARTA = '"+id_warta+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("sql list xxx : "+sql);
	
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("id_warta", rs.getString("ID_WARTA")==null?"":rs.getString("ID_WARTA"));
					h.put("no_warta", rs.getString("NO_WARTA")==null?"":rs.getString("NO_WARTA"));
					h.put("tarikh_warta", rs.getDate("TARIKH_WARTA")==null?"":Format.format(rs.getDate("TARIKH_WARTA")));
					dataWarta.addElement(h);		
				}
			} catch (Exception re) {
				log.error("Error: ", re);
				throw re;
				}
			finally {
				if(db != null) db.close();
			}
		
	}//close setDataBentukBayaran
	
	 @SuppressWarnings("unchecked")
	  public static void setDataPBAward(String idSiasatan,String idpb)throws Exception { //PERUBAHAN QUERY YATI
			
		 dataPBAward = new Vector();
			
		    Db db = null;
		    dataPBAward.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT A.ID_AWARD,B.ID_PIHAKBERKEPENTINGAN,C.ID_HAKMILIKPB,D.ID_HAKMILIK,E.ID_SIASATAN, ";
		    		sql += " A.LUAS_AMBIL,A.ID_UNITLUAS_AMBIL,I.SYER_ATAS,I.SYER_BAWAH,A.STATUS_PENERIMA,A.BAYAR_PAMPASAN,F.KETERANGAN, ";
		    		sql += " H.KEPUTUSAN, H.TARIKH_HANTAR, (SELECT SUM (C.JUMLAH_SUBAWARD) FROM TBLPPTSUBAWARD C WHERE A.ID_AWARD = C.ID_AWARD " ;
		    		sql += " AND C.FLAG_JENIS_AWARD != 'BAYAR_LAIN' " ;
		    		sql += " AND C.FLAG_JENIS_AWARD != 'BAYAR_PENILAI') AS BAYAR_AWARD ";
		    		sql += " FROM TBLPPTAWARD A, TBLPPTPIHAKBERKEPENTINGAN B, TBLPPTHAKMILIKPB C, TBLPPTHAKMILIK D,TBLPPTSIASATAN E, ";
		    		sql += " TBLRUJLUAS F, TBLPPTBORANGG G, TBLPPTBORANGH H, TBLPPTBAHAGIANPB I, TBLPPTSUBAWARD J ";
		    		sql += " WHERE C.ID_HAKMILIK = D.ID_HAKMILIK ";
		    		sql += " AND E.ID_HAKMILIK = D.ID_HAKMILIK ";
					sql += " AND A.ID_SIASATAN = E.ID_SIASATAN ";
					sql += " AND C.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN ";
					sql += " AND A.ID_HAKMILIKPB = C.ID_HAKMILIKPB ";
					sql += " AND A.ID_UNITLUAS_AMBIL = F.ID_LUAS(+)";
					sql += " AND G.ID_SIASATAN = E.ID_SIASATAN";
					sql += " AND C.ID_BAHAGIANPB = I.ID_BAHAGIANPB(+) ";
					sql += " AND H.ID_BORANGG = G.ID_BORANGG";
					sql += " AND A.ID_AWARD = J.ID_AWARD ";
					//sql += " AND J.FLAG_JENIS_AWARD = 'BAYAR_PENILAI' ";
					sql += " AND B.ID_PIHAKBERKEPENTINGAN = '"+idpb+"'";
					sql += " AND E.ID_SIASATAN = '"+idSiasatan+"'";
					
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		myLogger.info("SQL LIST PB AWARD : "+sql );
		    		
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();	    		
		    			h.put("id_award", rs.getString("ID_AWARD")== null?"":rs.getString("ID_AWARD"));
		    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
		    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
		    			h.put("id_siasatan", rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));
		    			h.put("luas_ambil", rs.getString("LUAS_AMBIL")== null?0:rs.getDouble("LUAS_AMBIL"));
		    			h.put("id_unitluas_ambil", rs.getString("ID_UNITLUAS_AMBIL")== null?"":rs.getString("ID_UNITLUAS_AMBIL"));
		    			h.put("syer_atas", rs.getString("SYER_ATAS")== null?"":rs.getString("SYER_ATAS"));
		    			h.put("syer_bawah", rs.getString("SYER_BAWAH")== null?"":rs.getString("SYER_BAWAH"));
		    			h.put("unit_luas", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
		    			h.put("tarikh_hantar", rs.getDate("TARIKH_HANTAR")==null?"":Format.format(rs.getDate("TARIKH_HANTAR")));
		    			
		    			if(rs.getString("KEPUTUSAN") != null){
		    				
		    				if(rs.getString("KEPUTUSAN").equals("1")){
		    					h.put("tawaran","MENERIMA TAWARAN");
		    				}else if(rs.getString("KEPUTUSAN").equals("2")){
		    					h.put("tawaran","MENERIMA TAWARAN DENGAN BANTAHAN");
		    				}else if(rs.getString("KEPUTUSAN").equals("3")){
		    					h.put("tawaran","TIDAK MENERIMA TAWARAN");
		    				}else if(rs.getString("KEPUTUSAN").equals("4")){
		    					h.put("tawaran","TIDAK BERKENAAN");
		    				}else{
		    					h.put("tawaran","");
		    				}
		    				
		    			}else{
		    				h.put("tawaran","");
		    			}
		    			
		    			h.put("bayar_pampasan", rs.getString("BAYAR_AWARD")== null?0:rs.getDouble("BAYAR_AWARD"));
		    			//h.put("bayar_pampasan", rs.getString("BAYAR_PAMPASAN")== null?0:rs.getDouble("BAYAR_PAMPASAN"));
		    			dataPBAward.addElement(h);

		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setSizeExistPB
	
	@SuppressWarnings("unchecked")
	public static void setlistBentukPampasan(String idHakmilik)throws Exception { // perubahan query yati
				
		listBentukPampasan = new Vector();
				
			    Db db = null;
			    listBentukPampasan.clear();
			    String sql = "";
			    
			    try {
			    	
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT DISTINCT A.ID_PERMOHONAN,B.ID_HAKMILIK,C.ID_SIASATAN,D.ID_AWARD,E.ID_HAKMILIKPB,F.ID_PIHAKBERKEPENTINGAN,G.ID_DOKUMEN, ";
			    		sql += " B.NO_HAKMILIK,F.NO_PB,F.NAMA_PB,G.NAMA_FAIL,D.BAYAR_PAMPASAN,B.NO_LOT, ";
			    		sql += "(SELECT SUM (C.JUMLAH_SUBAWARD) ";
			    		sql += " FROM TBLPPTSUBAWARD C ";
			    		sql += " WHERE D.ID_AWARD = C.ID_AWARD ";
			    		sql += " AND C.FLAG_JENIS_AWARD != 'BAYAR_LAIN' ";
			    		sql += " AND C.FLAG_JENIS_AWARD != 'BAYAR_PENILAI') AS BAYAR_AWARD "; 
			    		sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTSIASATAN C, TBLPPTAWARD D, TBLPPTHAKMILIKPB E,TBLPPTPIHAKBERKEPENTINGAN F,TBLPPTDOKUMEN G, TBLPPTSUBAWARD H "; 
			    		sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN "; 
			    		sql += " AND E.ID_HAKMILIK = B.ID_HAKMILIK "; 
			    		sql += " AND E.ID_PIHAKBERKEPENTINGAN = F.ID_PIHAKBERKEPENTINGAN "; 
			    		sql += " AND C.ID_HAKMILIK(+) = B.ID_HAKMILIK "; 
						sql += " AND D.ID_SIASATAN = C.ID_SIASATAN "; 
						sql += " AND D.ID_HAKMILIKPB(+) = E.ID_HAKMILIKPB "; 
						sql += " AND G.ID_AWARD(+) = D.ID_AWARD ";
						sql += " AND D.ID_AWARD = H.ID_AWARD ";
						//sql += " AND H.FLAG_JENIS_AWARD = 'BAYAR_PENILAI' ";
						sql += " AND G.ID_DOKUMEN IS NOT NULL ";
						sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";

						ResultSet rs = stmt.executeQuery(sql);
						
						myLogger.info("SQL BENTUK PAMPASAN : "+sql);
			      
			    		Hashtable h;
			    		int bil = 1;
			    		
			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("bil", bil);
			    			h.put("id_dokumen", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
			    			h.put("id_award", rs.getString("ID_AWARD")== null?"":rs.getString("ID_AWARD"));
			    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
			    			h.put("id_siasatan", rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));	
			    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
			    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
			    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
			    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
			    			h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
			    			h.put("nama_fail", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
			    			h.put("bayar_pampasan", rs.getString("BAYAR_PAMPASAN")== null?"":rs.getDouble("BAYAR_PAMPASAN"));
			    			h.put("bayar_award", rs.getString("BAYAR_AWARD")== null?"":rs.getDouble("BAYAR_AWARD"));
			    			h.put("no_lot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
			    			listBentukPampasan.addElement(h);
			    			bil++;

			      }//close while
			      
			    } catch (Exception re) {
			    	log.error("Error: ", re);
			    	throw re;
			    	}// 
			    finally {
			      if (db != null) db.close();
			    }
			    
	}//close setlistPenyediaanPampasan
	 
	@SuppressWarnings("unchecked")
	public static void setdataBentukPampasan(String id_award)throws Exception { //perubahan query yati
				
		dataBentukPampasan = new Vector();
				
			    Db db = null;
			    dataBentukPampasan.clear();
			    String sql = "";
			    
			    try {
			    	
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT DISTINCT A.ID_PERMOHONAN,B.ID_HAKMILIK,C.ID_SIASATAN,D.ID_AWARD,E.ID_HAKMILIKPB,F.ID_PIHAKBERKEPENTINGAN,G.ID_DOKUMEN, ";
			    		sql += " B.NO_HAKMILIK,F.NO_PB,F.NAMA_PB,G.NAMA_FAIL,D.BAYAR_PAMPASAN,B.NO_LOT,D.LUAS_AMBIL, "; 
			    		sql += " D.ID_UNITLUAS_AMBIL,K.SYER_ATAS,K.SYER_BAWAH,D.STATUS_PENERIMA,H.KETERANGAN,J.KEPUTUSAN, (SELECT SUM (C.JUMLAH_SUBAWARD) " ;
			    		sql += " FROM TBLPPTSUBAWARD C WHERE D.ID_AWARD = C.ID_AWARD AND C.FLAG_JENIS_AWARD != 'BAYAR_LAIN' AND C.FLAG_JENIS_AWARD != 'BAYAR_PENILAI') AS BAYAR_AWARD  ";
			    		sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTSIASATAN C, TBLPPTAWARD D, TBLPPTHAKMILIKPB E,TBLPPTPIHAKBERKEPENTINGAN F,TBLPPTDOKUMEN G, "; 
			    		sql += " TBLRUJLUAS H,TBLPPTBORANGG I,TBLPPTBORANGH J, TBLPPTBAHAGIANPB K, TBLPPTSUBAWARD L ";
			    		sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN "; 
			    		sql += " AND E.ID_HAKMILIK = B.ID_HAKMILIK "; 
			    		sql += " AND E.ID_PIHAKBERKEPENTINGAN = F.ID_PIHAKBERKEPENTINGAN "; 
			    		sql += " AND C.ID_HAKMILIK(+) = B.ID_HAKMILIK "; 
						sql += " AND D.ID_SIASATAN = C.ID_SIASATAN "; 
						sql += " AND D.ID_HAKMILIKPB(+) = E.ID_HAKMILIKPB "; 
						sql += " AND G.ID_AWARD(+) = D.ID_AWARD ";
						sql += " AND I.ID_SIASATAN = C.ID_SIASATAN";
						sql += " AND J.ID_BORANGG = I.ID_BORANGG";
						sql += " AND E.ID_BAHAGIANPB = K.ID_BAHAGIANPB(+)";
						sql += " AND D.ID_UNITLUAS_AMBIL = H.ID_LUAS(+)";
						sql += " AND G.ID_DOKUMEN IS NOT NULL ";
						sql += " AND D.ID_AWARD = L.ID_AWARD ";
						//sql += " AND L.FLAG_JENIS_AWARD = 'BAYAR_PENILAI' ";
						sql += " AND D.ID_AWARD = '"+id_award+"'";

						ResultSet rs = stmt.executeQuery(sql);
			      
						myLogger.info("SQL BENTUK PAMPASAN 2 :"+sql);
			    		Hashtable h;
		
			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("id_award", rs.getString("ID_AWARD")== null?"":rs.getString("ID_AWARD"));
			    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
			    			h.put("id_siasatan", rs.getString("ID_SIASATAN")== null?"":rs.getString("ID_SIASATAN"));	
			    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
			    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
			    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
			    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
			    			h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
			    			h.put("nama_fail", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
			    			//h.put("bayar_pampasan", rs.getString("BAYAR_PAMPASAN")== null?"":rs.getDouble("BAYAR_PAMPASAN"));
			    			h.put("no_lot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
			    			h.put("syer_atas", rs.getString("SYER_ATAS")== null?"":rs.getString("SYER_ATAS"));
			    			h.put("syer_bawah", rs.getString("SYER_BAWAH")== null?"":rs.getString("SYER_BAWAH"));
			    			h.put("unit_luas", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
			    			h.put("luas_ambil", rs.getString("LUAS_AMBIL")== null?"":rs.getString("LUAS_AMBIL"));
			    			
			    			if(rs.getString("BAYAR_PAMPASAN") != null){
			    				
			    				double TP = rs.getDouble("BAYAR_PAMPASAN");
			    				String total = Utils.format2Decimal(TP);
			    				h.put("bayar_pampasan",total);
			    				
			    			}else{
			    				h.put("bayar_pampasan", "0.00");
			    			}
			    			
			    			if(rs.getString("BAYAR_AWARD") != null){
			    				
			    				double TA = rs.getDouble("BAYAR_AWARD");
			    				String total = Utils.format2Decimal(TA);
			    				h.put("bayar_award",total);
			    				
			    			}else{
			    				h.put("bayar_award", "0.00");
			    			}
			    			
			    			if(rs.getString("KEPUTUSAN") != null){
			    				
			    				if(rs.getString("KEPUTUSAN").equals("1")){
			    					h.put("tawaran","MENERIMA TAWARAN");
			    				}else if(rs.getString("KEPUTUSAN").equals("2")){
			    					h.put("tawaran","MENERIMA TAWARAN DENGAN BANTAHAN");
			    				}else if(rs.getString("KEPUTUSAN").equals("3")){
			    					h.put("tawaran","TIDAK MENERIMA TAWARAN");
			    				}else if(rs.getString("KEPUTUSAN").equals("4")){
			    					h.put("tawaran","TIDAK BERKENAAN");
			    				}else{
			    					h.put("tawaran","");
			    				}
			    				
			    			}else{
			    				h.put("tawaran","");
			    			}
			    			dataBentukPampasan.addElement(h);
			    			
			      }//close while
			      
			    } catch (Exception re) {
			    	log.error("Error: ", re);
			    	throw re;
			    	}// 
			    finally {
			      if (db != null) db.close();
			    }
			    
	}//close setdataBentukPampasan
	
	@SuppressWarnings("unchecked")
	public void setIdHakmilikpb(String idHakmilik,String idpb) throws Exception {
		
		dataIdHakmilikpb = new Vector();
		
		Db db = null;
		dataIdHakmilikpb.clear();
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql = " SELECT DISTINCT ID_HAKMILIKPB FROM TBLPPTHAKMILIKPB" +
					  " WHERE ID_PIHAKBERKEPENTINGAN = '"+idpb+"'" +
					  " AND ID_HAKMILIK = '"+idHakmilik+"'" ;
				
				ResultSet rs = stmt.executeQuery(sql);
	
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
					dataIdHakmilikpb.addElement(h);		
				}
			} catch (Exception re) {
				log.error("Error: ", re);
				throw re;
				}
			finally {
				if(db != null) db.close();
			}
		
	}//close setIdHakmilikpb
	
	public static void hapusDokumen(String id_award) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM TBLPPTDOKUMEN where id_award = '"+id_award+"'";
	    		stmt.executeUpdate(sql);
	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusDokumen
	
	@SuppressWarnings("unchecked")
	public static void simpanMaklumatSuratAgensi(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");
	    		String txtNamaBank = (String)data.get("txtNamaBank");
	    		String txtTempatAmbil = (String)data.get("txtTempatAmbil");
	    		
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhAmbil = (String)data.get("txdTarikhAmbil");
	    		String txdTarikhSedia = (String)data.get("txdTarikhSedia");
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TA = "to_date('" + txdTarikhAmbil + "','dd/MM/yyyy')";
	    		String TSB = "to_date('" + txdTarikhSedia + "','dd/MM/yyyy')";
	    		
	    		//insert tblborang
	    		SQLRenderer r = new SQLRenderer();	    		
	    		r.add("id_hakmilik", id_hakmilik);
	    		r.add("tarikh_surat",r.unquote(TS));
	    		r.add("tarikh_terima",r.unquote(TT));	 
	    		r.add("tarikh_bayaran",r.unquote(TSB));
	    		r.add("tarikh_ambil_cek",r.unquote(TA));	    		
	    		r.add("no_rujukan_surat", txtNoRujSurat);
	    		r.add("nama_bank", txtNamaBank);
	    		r.add("tempat_ambil", txtTempatAmbil);    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptterimabayaran");
	    		
	    		stmt.executeUpdate(sql);
	    		
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanMaklumatSuratAgensi
	
	@SuppressWarnings("unchecked")
	public static void setlistSuratAgensi(String idHakmilik)throws Exception {
				
		listSuratAgensi = new Vector();
				
			    Db db = null;
			    listSuratAgensi.clear();
			    String sql = "";
			    
			    try {
			    	
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT DISTINCT ID_TERIMABAYARAN,ID_HAKMILIK,NO_RUJUKAN_SURAT, ";
			    		sql += " TARIKH_TERIMA,TARIKH_SURAT,TARIKH_AMBIL_CEK,TEMPAT_AMBIL,NAMA_BANK ";
			    		sql += " FROM TBLPPTTERIMABAYARAN ";
			    		sql += " WHERE ROWNUM < 20";
			    		sql += " AND ID_HAKMILIK = '"+idHakmilik+"'";

						ResultSet rs = stmt.executeQuery(sql);
						
					
			      
			    		Hashtable h;
			    		int bil = 1;
			    		
			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("bil", bil);
			    			h.put("id_terimabayaran", rs.getString("ID_TERIMABAYARAN")== null?"":rs.getString("ID_TERIMABAYARAN"));
			    			h.put("no_rujukan_surat", rs.getString("NO_RUJUKAN_SURAT")== null?"":rs.getString("NO_RUJUKAN_SURAT"));
			    			h.put("tempat_ambil", rs.getString("TEMPAT_AMBIL")== null?"":rs.getString("TEMPAT_AMBIL"));
			    			h.put("nama_bank", rs.getString("NAMA_BANK")== null?"":rs.getString("NAMA_BANK"));
			    			h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
			    			h.put("tarikh_surat", rs.getDate("TARIKH_SURAT")==null?"":Format.format(rs.getDate("TARIKH_SURAT")));
			    			h.put("tarikh_ambil_cek", rs.getDate("TARIKH_AMBIL_CEK")==null?"":Format.format(rs.getDate("TARIKH_AMBIL_CEK")));
			    		
			    			listSuratAgensi.addElement(h);
			    			
			    		
			    			bil++;
			    			

			      }//close while
			      
			    } catch (Exception re) {
			    	log.error("Error: ", re);
			    	throw re;
			    	}// 
			    finally {
			      if (db != null) db.close();
			    }
			    
	}//close setlistSuratAgensi
	
	@SuppressWarnings("unchecked")
	public Vector setlistSuratAgensiOnline(String findNoFail, String findNamaProjek, String findNoCekEFT, 
			String findKaedahBayar, String NO_PB)throws Exception {
			
		Vector listSuratAgensiOnline = new Vector();
				
				Db db = null;
			    String sql = "";
			    
			    try {
			    	
			    	db = new Db();
		    		Statement stmt = db.getStatement();
			      
			    		sql = " SELECT DISTINCT D.CARA_BAYAR, A.ID_TERIMABAYARAN, A.ID_HAKMILIK, "
						    + " A.NO_RUJUKAN_SURAT, A.TARIKH_TERIMA, A.TARIKH_SURAT, "
						    + " A.TARIKH_AMBIL_CEK, A.TEMPAT_AMBIL, A.NAMA_BANK, "
						    + " C.ID_HAKMILIKPB, D.ID_BAYARAN, D.PENERIMA_CEK, D.NAMA_WAKIL, "
						    + " TO_CHAR (D.AMAUN_BAYARAN, '999,999,999,990.99' ) AS AMAUN_BAYARAN, "
						    + " D.NO_WAKIL, D.TARIKH_SERAH_CEK, D.BIL_HARI_LEWAT, "
						    + " TO_CHAR (D.DENDA_LEWAT, '999,999,999,990.99') AS DENDA_LEWAT, "
						    + " D.JENIS_AWARD, D.FLAG_SERAH_CEK, "
						    + " D.MASA_AMBIL_CEK, D.JENIS_WAKTU_AMBIL_CEK, "
						    + " D.NO_RUJUKAN_SURATEFT, "
						    + " D.TARIKH_SURAT_EFT, D.NO_BAUCER, D.PERIHAL, D.AMAUN_EFT, "
						    + " E.ID_PIHAKBERKEPENTINGAN, E.NAMA_PB, E.NO_PB, "
						    + " E.ID_JENISNOPB AS ID_JENISNOWAKILPB, F.TARIKH_AKHIR_BAYARAGENSI, "
						    + " (" +
						    "CASE WHEN D.CARA_BAYAR = 1 THEN D.TARIKH_CEK "
						    + " ELSE D.TARIKH_TERIMA_EFT "
						    + " END) TARIKH_TERIMA_EFTCEK, "
						    + " (CASE WHEN D.CARA_BAYAR = 1 THEN D.NO_BAYARAN "
						    + " ELSE D.NO_EFT "
						    + " END) NO_RUJUKAN, "
						    + " B.NO_HAKMILIK, B.NO_LOT, UPPER (P.TUJUAN) AS PROJEK, FAIL.NO_FAIL "
						    + " FROM TBLPPTTERIMABAYARAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLPPTBAYARAN D, "
						    + " TBLPPTPIHAKBERKEPENTINGAN E, TBLPPTBORANGH F, USERS_ONLINE UO, TBLPPTPERMOHONAN P, TBLPFDFAIL FAIL "
						    + " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK AND C.ID_HAKMILIKPB = D.ID_HAKMILIKPB "
						    + " AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB AND C.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN "
						    + " AND A.ID_TERIMABAYARAN(+) = D.ID_TERIMABAYARAN AND UO.NO_KP_BARU = E.NO_PB "
						    + " AND P.ID_PERMOHONAN = B.ID_PERMOHONAN AND FAIL.ID_FAIL = P.ID_FAIL "
						    + " AND UO.NO_KP_BARU = '"+NO_PB+"' ";
			    		
			    		
			    		if (findNoFail != null) {
							if (!findNoFail.trim().equals("")) {
								sql = sql + " AND UPPER(FAIL.NO_FAIL) LIKE '%' ||'"
										+ findNoFail.trim().toUpperCase() + "'|| '%'";
							}
						}
						if (findNamaProjek != null) {
							if (!findNamaProjek.trim().equals("")) {
								sql = sql + " AND UPPER(P.TUJUAN) LIKE '%' ||'"
										+ findNamaProjek.trim().toUpperCase() + "'|| '%'";
							}
						}
						if (findNoCekEFT != null) {
							if (!findNoCekEFT.trim().equals("")) {							
								sql = sql + " AND (CASE "+
					                " WHEN D.CARA_BAYAR = 1 AND UPPER(D.NO_BAYARAN) LIKE '%"+findNoCekEFT.trim().toUpperCase()+"%' " +
					                " OR D.CARA_BAYAR != 1 AND UPPER(D.NO_EFT) LIKE '%"+findNoCekEFT.trim().toUpperCase()+"%' "+
					                " THEN 1 "+
					                " END "+
					                " ) = 1 ";
							}
						}
						if (findKaedahBayar != null) {
							if (!findKaedahBayar.trim().equals("")) {
								sql = sql + " AND UPPER(D.CARA_BAYAR) LIKE '%' ||'"
										+ findKaedahBayar.trim().toUpperCase() + "'|| '%'";
							}
						}
						myLogger.info(" setlistSuratAgensiOnline :" + sql.toUpperCase());
						
						ResultSet rs = stmt.executeQuery(sql);
						
			    		Hashtable h;
			    		int bil = 1;
			    		
			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("bil", bil);
			    			h.put("CARA_BAYAR", rs.getString("CARA_BAYAR")== null?"":rs.getString("CARA_BAYAR"));
			    			h.put("ID_TERIMABAYARAN", rs.getString("ID_TERIMABAYARAN")== null?"":rs.getString("ID_TERIMABAYARAN"));
			    			h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
			    			h.put("NO_RUJUKAN_SURAT", rs.getString("NO_RUJUKAN_SURAT")== null?"":rs.getString("NO_RUJUKAN_SURAT"));
			    			h.put("TEMPAT_AMBIL", rs.getString("TEMPAT_AMBIL")== null?"":rs.getString("TEMPAT_AMBIL"));
			    			h.put("NAMA_BANK", rs.getString("NAMA_BANK")== null?"":rs.getString("NAMA_BANK"));
			    			h.put("ID_HAKMILIKPB", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
			    			h.put("ID_BAYARAN", rs.getString("ID_BAYARAN")== null?"":rs.getString("ID_BAYARAN"));
			    			h.put("PENERIMA_CEK", rs.getString("PENERIMA_CEK")== null?"":rs.getString("PENERIMA_CEK"));
			    			h.put("NAMA_WAKIL", rs.getString("NAMA_WAKIL")== null?"":rs.getString("NAMA_WAKIL"));
			    			h.put("AMAUN_BAYARAN", rs.getString("AMAUN_BAYARAN")== null?"":rs.getString("AMAUN_BAYARAN"));
			    			h.put("NO_WAKIL", rs.getString("NO_WAKIL")== null?"":rs.getString("NO_WAKIL"));
			    			h.put("BIL_HARI_LEWAT", rs.getString("BIL_HARI_LEWAT")== null?"":rs.getString("BIL_HARI_LEWAT"));
			    			h.put("DENDA_LEWAT", rs.getString("DENDA_LEWAT")== null?"":rs.getString("DENDA_LEWAT"));
			    			h.put("JENIS_AWARD", rs.getString("JENIS_AWARD")== null?"":rs.getString("JENIS_AWARD"));
			    			h.put("FLAG_SERAH_CEK", rs.getString("FLAG_SERAH_CEK")== null?"":rs.getString("FLAG_SERAH_CEK"));
			    			h.put("MASA_AMBIL_CEK", rs.getString("MASA_AMBIL_CEK")== null?"":rs.getString("MASA_AMBIL_CEK"));
			    			h.put("JENIS_WAKTU_AMBIL_CEK", rs.getString("JENIS_WAKTU_AMBIL_CEK")== null?"":rs.getString("JENIS_WAKTU_AMBIL_CEK"));
			    			h.put("NO_RUJUKAN_SURATEFT", rs.getString("NO_RUJUKAN_SURATEFT")== null?"":rs.getString("NO_RUJUKAN_SURATEFT"));
			    			h.put("NO_BAUCER", rs.getString("NO_BAUCER")== null?"":rs.getString("NO_BAUCER"));
			    			h.put("PERIHAL", rs.getString("PERIHAL")== null?"":rs.getString("PERIHAL"));
			    			h.put("AMAUN_EFT", rs.getString("AMAUN_EFT")== null?"":rs.getString("AMAUN_EFT"));
			    			h.put("ID_PIHAKBERKEPENTINGAN", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
			    			h.put("NAMA_PB", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
			    			h.put("NO_PB", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
			    			h.put("ID_JENISNOWAKILPB", rs.getString("ID_JENISNOWAKILPB")== null?"":rs.getString("ID_JENISNOWAKILPB"));
			    			h.put("TARIKH_TERIMA", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
			    			h.put("TARIKH_SURAT", rs.getDate("TARIKH_SURAT")==null?"":Format.format(rs.getDate("TARIKH_SURAT")));
			    			h.put("TARIKH_AMBIL_CEK", rs.getDate("TARIKH_AMBIL_CEK")==null?"":Format.format(rs.getDate("TARIKH_AMBIL_CEK")));
			    			h.put("TARIKH_SERAH_CEK", rs.getDate("TARIKH_SERAH_CEK")==null?"":Format.format(rs.getDate("TARIKH_SERAH_CEK")));
			    			h.put("TARIKH_SURAT_EFT", rs.getDate("TARIKH_SURAT_EFT")==null?"":Format.format(rs.getDate("TARIKH_SURAT_EFT")));
			    			h.put("TARIKH_AKHIR_BAYARAGENSI", rs.getDate("TARIKH_AKHIR_BAYARAGENSI")==null?"":Format.format(rs.getDate("TARIKH_AKHIR_BAYARAGENSI")));
			    			h.put("TARIKH_TERIMA_EFTCEK", rs.getDate("TARIKH_TERIMA_EFTCEK")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_EFTCEK")));
			    			h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
			    			h.put("NO_LOT", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
			    			h.put("PROJEK", rs.getString("PROJEK")== null?"":rs.getString("PROJEK"));
			    			h.put("NO_FAIL", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
			    			h.put("NO_RUJUKAN", rs.getString("NO_RUJUKAN")== null?"":rs.getString("NO_RUJUKAN"));
			    			//h.put("TUJUAN", rs.getString("TUJUAN")== null?"":rs.getString("TUJUAN"));
			    			
			    			listSuratAgensiOnline.addElement(h);	
							bil++;
						}
						
						return listSuratAgensiOnline;
					}catch (Exception re) {
						throw re;
					} finally {
						if (db != null)
							db.close();
					}
	}//close setlistSuratAgensi
	
	public Vector dropdown_caraBayar() throws Exception {
		Vector cara_bayar = new Vector();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT DISTINCT "
				 +" (CASE "
				 +" WHEN CARA_BAYAR = '1' "
				 +" THEN 'CEK' "
				 +" WHEN CARA_BAYAR ='2' "
				 +" THEN 'EFT' "
				 +" END) AS CARA_BAYAR "
				 +" FROM TBLPPTBAYARAN "
				 +" WHERE CARA_BAYAR IN ('1','2') ";
					
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("CARA_BAYAR", rs.getString("CARA_BAYAR") == null ? "" : rs.getString("CARA_BAYAR"));			
				cara_bayar.addElement(h);	
				
			}
			return cara_bayar;
		}catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}	
	
	@SuppressWarnings("unchecked")
	public Vector setStatusBicaraOnline(String findNoFail, String findNamaProjek, String findNoSiasatan, 
			String NO_PB)throws Exception {
			
		Vector listBicaraOnline = new Vector();
				
				Db db = null;
			    String sql = "";
			    
			    try {
			    	
			    	db = new Db();
		    		Statement stmt = db.getStatement();
			      
			    		sql = " SELECT FAIL.NO_FAIL, S.BIL_SIASATAN, S.NO_SIASATAN,S.STATUS_SIASATAN, S.STATUS, S.TARIKH_SIASATAN, S.MASA_SIASATAN, S.JENIS_WAKTU_SIASATAN, ";
			    		sql += " S.TARIKH_PERINTAH, UPPER(JPB.KETERANGAN) AS JENIS_PB, ";
			    		sql += " UPPER(P.TUJUAN) AS PROJEK, TO_CHAR(JHM.KOD_JENIS_HAKMILIK || HM.NO_HAKMILIK) AS NO_HAKMILIK, HM.NO_LOT, ";
			    		sql += " UPPER(PB.NAMA_PB) AS NAMA_PB, S.TEMPAT_BICARA, S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.BANDAR,S.NEGERI ";
			    		sql += " FROM (SELECT SS.STATUS_SIASATAN, SS.BIL_SIASATAN,SS.ID_SIASATAN,SS.ID_HAKMILIK, SS.NO_SIASATAN, SS.TARIKH_SIASATAN,SS.TARIKH_PERINTAH, SS.MASA_SIASATAN, ";
			    		sql += " (CASE WHEN SS.STATUS_SIASATAN = 1 THEN 'SIASATAN PERMULAAN' ";
			    		sql += " WHEN SS.STATUS_SIASATAN = 2 THEN 'DITUNDA' ";
			    		sql += " WHEN SS.STATUS_SIASATAN = 3 THEN 'DIBATALKAN' ";
			    		sql += " WHEN SS.STATUS_SIASATAN = 4 THEN 'ULANG SIASATAN' ";
			    		sql += " WHEN SS.STATUS_SIASATAN = 5 THEN 'SAMBUNG SIASATAN' ";
			    		sql += " WHEN SS.STATUS_SIASATAN = 6 THEN 'SELESAI' ";
			    		sql += " WHEN SS.STATUS_SIASATAN = 7 THEN 'TANGGUH SIASATAN' ";
			    		sql += " ELSE '' END) AS STATUS, ";
			    		sql += " (CASE WHEN SS.JENIS_WAKTU_SIASATAN = 1 THEN 'PAGI' ";
			    		sql += " WHEN SS.JENIS_WAKTU_SIASATAN = 2 THEN 'TENGAH HARI' ";
			    		sql += " WHEN SS.JENIS_WAKTU_SIASATAN = 3 THEN 'PETANG' ELSE '' END) AS JENIS_WAKTU_SIASATAN, ";
			    		sql += " SS.ID_PERMOHONAN, ";
			    		sql += " UPPER(SS.TEMPAT) AS TEMPAT_BICARA, UPPER(SS.ALAMAT1) AS ALAMAT1, UPPER(SS.ALAMAT2) AS ALAMAT2,  UPPER(SS.ALAMAT3) AS ALAMAT3, ";
			    		sql += " UPPER(SS.POSKOD) AS POSKOD,  UPPER(BB.KETERANGAN) AS BANDAR,UPPER(NN.NAMA_NEGERI) AS NEGERI ";
			    		sql += " FROM (SELECT HM.ID_HAKMILIK,MAX(S.BIL_SIASATAN) AS BIL ";
			    		sql += " FROM TBLPPTSIASATAN S, TBLPPTHAKMILIK HM ";
			    		sql += " WHERE S.ID_HAKMILIK = HM.ID_HAKMILIK ";
			    		sql += " GROUP BY HM.ID_HAKMILIK) HM_MAIN, TBLPPTSIASATAN SS, TBLRUJNEGERI NN, TBLRUJBANDAR BB ";
			    		sql += " WHERE HM_MAIN.ID_HAKMILIK = SS.ID_HAKMILIK ";
			    		sql += " AND SS.ID_NEGERI = NN.ID_NEGERI(+) AND SS.ID_BANDAR = BB.ID_BANDAR(+) ";
			    		sql += " AND (CASE WHEN SS.BIL_SIASATAN = HM_MAIN.BIL THEN 1 ELSE 0 END) = 1) S, TBLPPTPERMOHONAN P, TBLPPTHAKMILIK HM, ";
			    		sql += " TBLRUJJENISHAKMILIK JHM, TBLPPTHAKMILIKPB HPB, TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB, TBLPFDFAIL FAIL ";
			    		sql += " WHERE  S.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_HAKMILIK = HM.ID_HAKMILIK ";
			    		sql += " AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK ";
			    		sql += " AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND HPB.ID_JENISPB = JPB.ID_JENISPB ";
			    		sql += " AND FAIL.ID_FAIL = P.ID_FAIL ";
			    		sql += " AND PB.NO_PB = '"+NO_PB+"' ";
			  
			    		if (findNoFail != null) {
							if (!findNoFail.trim().equals("")) {
								sql = sql + " AND UPPER(FAIL.NO_FAIL) LIKE '%' ||'"
										+ findNoFail.trim().toUpperCase() + "'|| '%'";
							}
						}
						if (findNamaProjek != null) {
							if (!findNamaProjek.trim().equals("")) {
								sql = sql + " AND UPPER(P.TUJUAN) LIKE '%' ||'"
										+ findNamaProjek.trim().toUpperCase() + "'|| '%'";
							}
						}
						if (findNoSiasatan != null) {
							if (!findNoSiasatan.trim().equals("")) {
								sql = sql + " AND UPPER(S.NO_SIASATAN) LIKE '%' ||'"
										+ findNoSiasatan.trim().toUpperCase() + "'|| '%'";
							}
						}
						
						myLogger.info(" setStatusBicaraOnline :" + sql.toUpperCase());

						ResultSet rs = stmt.executeQuery(sql);
						
			    		Hashtable h;
			    		int bil = 1;
			    		
			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("BIL", bil);
			    			h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN")== null?"":rs.getString("BIL_SIASATAN"));
			    			h.put("NO_SIASATAN", rs.getString("NO_SIASATAN")== null?"":rs.getString("NO_SIASATAN"));
			    			h.put("STATUS_SIASATAN", rs.getString("STATUS_SIASATAN")== null?"":rs.getString("STATUS_SIASATAN"));
			    			h.put("STATUS", rs.getString("STATUS")== null?"":rs.getString("STATUS"));
			    			h.put("MASA_SIASATAN", rs.getString("MASA_SIASATAN")== null?"":rs.getString("MASA_SIASATAN"));
			    			h.put("JENIS_WAKTU_SIASATAN", rs.getString("JENIS_WAKTU_SIASATAN")== null?"":rs.getString("JENIS_WAKTU_SIASATAN"));
			    			h.put("JENIS_PB", rs.getString("JENIS_PB")== null?"":rs.getString("JENIS_PB"));
			    			h.put("PROJEK", rs.getString("PROJEK")== null?"":rs.getString("PROJEK"));
			    			h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
			    			h.put("NO_LOT", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
			    			h.put("NAMA_PB", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
			    			h.put("TEMPAT_BICARA", rs.getString("TEMPAT_BICARA")== null?"":rs.getString("TEMPAT_BICARA"));
			    			h.put("ALAMAT1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
			    			h.put("ALAMAT2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
			    			h.put("ALAMAT3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
			    			h.put("POSKOD", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));
			    			h.put("BANDAR", rs.getString("BANDAR")== null?"":rs.getString("BANDAR"));
			    			h.put("NEGERI", rs.getString("NEGERI")== null?"":rs.getString("NEGERI"));
			    			h.put("STATUS_SIASATAN", rs.getString("STATUS_SIASATAN")== null?"":rs.getString("STATUS_SIASATAN"));
			    			h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN")== null?"":rs.getString("BIL_SIASATAN"));
			    			h.put("TARIKH_SIASATAN", rs.getDate("TARIKH_SIASATAN")==null?"":Format.format(rs.getDate("TARIKH_SIASATAN")));
			    			h.put("TARIKH_PERINTAH", rs.getDate("TARIKH_PERINTAH")==null?"":Format.format(rs.getDate("TARIKH_PERINTAH")));
			    			h.put("NO_FAIL", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
			    			
			    			listBicaraOnline.addElement(h);	
							bil++;
						}
					
						return listBicaraOnline;
					}catch (Exception re) {
						throw re;
					} finally {
						if (db != null)
							db.close();
					}
	}//close setlistSuratAgensi
	
	@SuppressWarnings("unchecked")
	public static void setdataSuratAgensi(String id_terimabayaran)throws Exception {
				
		dataSuratAgensi = new Vector();
				
			    Db db = null;
			    dataSuratAgensi.clear();
			    String sql = "";
			    
			    try {
			    	
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT DISTINCT ID_TERIMABAYARAN,ID_HAKMILIK,NO_RUJUKAN_SURAT, ";
			    		sql += " TARIKH_TERIMA,TARIKH_SURAT,TARIKH_AMBIL_CEK,TEMPAT_AMBIL,NAMA_BANK, TARIKH_BAYARAN ";
			    		sql += " FROM TBLPPTTERIMABAYARAN ";
			    		sql += " WHERE ID_TERIMABAYARAN = '"+id_terimabayaran+"'";

						ResultSet rs = stmt.executeQuery(sql);
			      
			    		Hashtable h;
			    		
			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("id_terimabayaran", rs.getString("ID_TERIMABAYARAN")== null?"":rs.getString("ID_TERIMABAYARAN"));
			    			h.put("no_rujukan_surat", rs.getString("NO_RUJUKAN_SURAT")== null?"":rs.getString("NO_RUJUKAN_SURAT"));
			    			h.put("tempat_ambil", rs.getString("TEMPAT_AMBIL")== null?"":rs.getString("TEMPAT_AMBIL"));
			    			h.put("nama_bank", rs.getString("NAMA_BANK")== null?"":rs.getString("NAMA_BANK"));
			    			h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
			    			h.put("tarikh_surat", rs.getDate("TARIKH_SURAT")==null?"":Format.format(rs.getDate("TARIKH_SURAT")));
			    			h.put("tarikh_ambil_cek", rs.getDate("TARIKH_AMBIL_CEK")==null?"":Format.format(rs.getDate("TARIKH_AMBIL_CEK")));	
			    			h.put("tarikh_bayaran", rs.getDate("TARIKH_BAYARAN")==null?"":Format.format(rs.getDate("TARIKH_BAYARAN")));
			    			dataSuratAgensi.addElement(h);
			    			
			      }//close while
			      
			    } catch (Exception re) {
			    	log.error("Error: ", re);
			    	throw re;
			    	}// 
			    finally {
			      if (db != null) db.close();
			    }
			    
	}//close setdataSuratAgensi
	
	@SuppressWarnings("unchecked")
	public Vector setdataSuratAgensiOnline(String id_terimabayaran)throws Exception {
				
		Vector dataSuratAgensiOnline = new Vector();
				
			    Db db = null;
			    String sql = "";
			    
			    try {
			    	
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT DISTINCT ID_TERIMABAYARAN,ID_HAKMILIK,NO_RUJUKAN_SURAT, ";
			    		sql += " TARIKH_TERIMA,TARIKH_SURAT,TARIKH_AMBIL_CEK,TEMPAT_AMBIL,NAMA_BANK, TARIKH_BAYARAN ";
			    		sql += " FROM TBLPPTTERIMABAYARAN ";
			    		sql += " WHERE ID_TERIMABAYARAN = '"+id_terimabayaran+"'";

			    		
						ResultSet rs = stmt.executeQuery(sql);
						
						Hashtable h;
						int bil = 1;
						
			    		while (rs.next()) {
			    			h = new Hashtable();
							h.put("bil", bil);
			    			h.put("id_terimabayaran", rs.getString("ID_TERIMABAYARAN")== null?"":rs.getString("ID_TERIMABAYARAN"));
			    			h.put("no_rujukan_surat", rs.getString("NO_RUJUKAN_SURAT")== null?"":rs.getString("NO_RUJUKAN_SURAT"));
			    			h.put("tempat_ambil", rs.getString("TEMPAT_AMBIL")== null?"":rs.getString("TEMPAT_AMBIL"));
			    			h.put("nama_bank", rs.getString("NAMA_BANK")== null?"":rs.getString("NAMA_BANK"));
			    			h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
			    			h.put("tarikh_surat", rs.getDate("TARIKH_SURAT")==null?"":Format.format(rs.getDate("TARIKH_SURAT")));
			    			h.put("tarikh_ambil_cek", rs.getDate("TARIKH_AMBIL_CEK")==null?"":Format.format(rs.getDate("TARIKH_AMBIL_CEK")));	
			    			h.put("tarikh_bayaran", rs.getDate("TARIKH_BAYARAN")==null?"":Format.format(rs.getDate("TARIKH_BAYARAN")));
			    			
			    			dataSuratAgensiOnline.addElement(h);	
							bil++;
						}
					
						return dataSuratAgensiOnline;
					}catch (Exception re) {
						throw re;
					} finally {
						if (db != null)
							db.close();
					}
	}//close setdataSuratAgensi
	
	@SuppressWarnings("unchecked")
	public static void updateMaklumatSuratAgensi(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_terimabayaran = (String)data.get("id_terimabayaran");
	    		
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");
	    		String txtNamaBank = (String)data.get("txtNamaBank");
	    		String txtTempatAmbil = (String)data.get("txtTempatAmbil");
	    		
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhAmbil = (String)data.get("txdTarikhAmbil");
	    		String txdTarikhSedia = (String)data.get("txdTarikhSedia");
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TA = "to_date('" + txdTarikhAmbil + "','dd/MM/yyyy')";
	    		String TSB = "to_date('" + txdTarikhSedia + "','dd/MM/yyyy')";
	    		
	    		//insert tblborang
	    		SQLRenderer r = new SQLRenderer();	    		
	    		r.update("id_terimabayaran", id_terimabayaran);
	    		r.add("tarikh_surat",r.unquote(TS));
	    		r.add("tarikh_terima",r.unquote(TT));	   
	    		r.add("tarikh_ambil_cek",r.unquote(TA));	   
	    		r.add("tarikh_bayaran",r.unquote(TSB));	
	    		r.add("no_rujukan_surat", txtNoRujSurat);
	    		r.add("nama_bank", txtNamaBank);
	    		r.add("tempat_ambil", txtTempatAmbil);    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptterimabayaran");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateMaklumatSuratAgensi
	
	public static void hapusMaklumatSuratAgensi(String id_terimabayaran) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptterimabayaran where id_terimabayaran = '"+id_terimabayaran+"'";
	    		stmt.executeUpdate(sql);
	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusMaklumatSuratAgensi
	
	@SuppressWarnings("unchecked")
	public static void setListPenerimaanCek(String id_terimabayaran,String flagJenisBayar)throws Exception { //pembetulan query yati
			
		listPenerimaanCek = new Vector();
			
		    Db db = null;
		    listPenerimaanCek.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT A.ID_HAKMILIK, B.ID_HAKMILIKPB, C.ID_TERIMABAYARAN, D.ID_BAYARAN, E.ID_PIHAKBERKEPENTINGAN, ";
		    		sql += " E.NAMA_PB, E.NO_PB, D.TARIKH_CEK, D.PENERIMA_CEK, G.SYER_ATAS, G.SYER_BAWAH, F.BAYAR_PAMPASAN, D.NAMA_WAKIL, ";
		    		sql += " D.CARA_BAYAR, D.NO_RUJUKAN_SURATEFT, (SELECT SUM (C.JUMLAH_SUBAWARD) ";
                    sql += " FROM TBLPPTSUBAWARD C ";
                    sql += " WHERE F.ID_AWARD = C.ID_AWARD ";
                    sql += " AND C.FLAG_JENIS_AWARD != 'BAYAR_LAIN' ";
                    sql += " AND C.FLAG_JENIS_AWARD != 'BAYAR_PENILAI') AS BAYAR_AWARD ";
		    		sql += " FROM TBLPPTHAKMILIK A, TBLPPTHAKMILIKPB B, TBLPPTTERIMABAYARAN C, TBLPPTBAYARAN D, TBLPPTPIHAKBERKEPENTINGAN E, ";
		    		sql += " TBLPPTAWARD F , TBLPPTBAHAGIANPB G, TBLPPTSUBAWARD H ";
		    		sql += " WHERE B.ID_HAKMILIK = A.ID_HAKMILIK ";
		    		sql += " AND C.ID_HAKMILIK = A.ID_HAKMILIK ";
		    		sql += " AND B.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN";
		    		sql += " AND D.ID_TERIMABAYARAN = C.ID_TERIMABAYARAN ";
		    		sql += " AND D.ID_HAKMILIKPB = B.ID_HAKMILIKPB ";
		    		sql += " AND B.ID_BAHAGIANPB = G.ID_BAHAGIANPB(+) ";
		    		sql += " AND F.ID_HAKMILIKPB = B.ID_HAKMILIKPB";
		    		sql += " AND F.ID_AWARD = H.ID_AWARD ";
		    		//sql += " AND H.FLAG_JENIS_AWARD = 'BAYAR_PENILAI' ";
		    		sql += " AND ROWNUM < 20";
					sql += " AND C.ID_TERIMABAYARAN = '"+id_terimabayaran+"'";
		      
					if(flagJenisBayar!=""){
						if(flagJenisBayar.equals("1")){
							sql += " AND NVL(D.CARA_BAYAR,0) <> '2'";
						}else{
							sql += " AND NVL(D.CARA_BAYAR,0) <> '1'";
						}
					}
					
		    		ResultSet rs = stmt.executeQuery(sql);
		    		
		    		myLogger.info("SQL LIST BAYARAN PAMPASAN : "+sql);
		      
		    		Hashtable h;
		    		int bil = 1;
		    		
		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("bil",bil);
		    			h.put("id_bayaran", rs.getString("ID_BAYARAN")== null?"":rs.getString("ID_BAYARAN"));
		    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
		    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
		    			h.put("id_terimabayaran", rs.getString("ID_TERIMABAYARAN")== null?"":rs.getString("ID_TERIMABAYARAN"));
		    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));	
		    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
		    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
		    			h.put("syer_atas", rs.getString("SYER_ATAS")== null?"":rs.getString("SYER_ATAS"));
		    			h.put("syer_bawah", rs.getString("SYER_BAWAH")== null?"":rs.getString("SYER_BAWAH"));
		    			h.put("penerima_cek", rs.getString("PENERIMA_CEK")== null?"":rs.getString("PENERIMA_CEK"));
		    			h.put("tarikh_cek", rs.getDate("TARIKH_CEK")==null?"":Format.format(rs.getDate("TARIKH_CEK")));	    	
		    			h.put("nama_wakil", rs.getString("NAMA_WAKIL")== null?"":rs.getString("NAMA_WAKIL"));
		    			h.put("cara_bayar", rs.getString("CARA_BAYAR")== null?"":rs.getString("CARA_BAYAR"));
		    			h.put("no_rujukan_surateft", rs.getString("NO_RUJUKAN_SURATEFT")== null?"":rs.getString("NO_RUJUKAN_SURATEFT"));
		    			
		    			h.put("bayar_pampasan", rs.getString("BAYAR_PAMPASAN")== null?0:rs.getDouble("BAYAR_PAMPASAN"));
		    			h.put("bayar_award", rs.getString("BAYAR_AWARD")== null?0:rs.getDouble("BAYAR_AWARD"));
		    			
		    			listPenerimaanCek.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataPenerimaanCek
	
	@SuppressWarnings("unchecked")
	public static void setListEFT(String id_hakmilik)throws Exception {
			
		listEFT = new Vector();
			
		    Db db = null;
		    listEFT.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT C.ID_HAKMILIKPB, D.ID_PIHAKBERKEPENTINGAN, E.ID_BAYARAN, D.NAMA_PB, D.NO_PB, E.NO_RUJUKAN_SURATEFT ";
		    		sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLPPTPIHAKBERKEPENTINGAN D, TBLPPTBAYARAN E ";
		    		sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
		    		sql += " AND C.ID_HAKMILIK = B.ID_HAKMILIK ";
		    		sql += " AND C.ID_PIHAKBERKEPENTINGAN = D.ID_PIHAKBERKEPENTINGAN ";
		    		sql += " AND E.ID_HAKMILIKPB = C.ID_HAKMILIKPB ";
					sql += " AND E.CARA_BAYAR = '2' ";
					sql += " AND B.ID_HAKMILIK = '"+id_hakmilik+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int bil = 1;
		    		
		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("bil",bil);
		    			h.put("id_bayaran", rs.getString("ID_BAYARAN")== null?"":rs.getString("ID_BAYARAN"));
		    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
		    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
		    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
		    			h.put("no_rujukan_surateft", rs.getString("NO_RUJUKAN_SURATEFT")== null?"":rs.getString("NO_RUJUKAN_SURATEFT"));
		    			listEFT.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setListEFT
	
	
	@SuppressWarnings("unchecked")
	public void setDataPBBorangH(String idHakmilik,String id_pihakberkepentingan) throws Exception {
		
		dataPBBorangH = new Vector();
		
		Db db = null;
		dataPBBorangH.clear();
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql = "SELECT DISTINCT C.NAMA_PENYATA_BANK, A.ID_HAKMILIK, B.ID_HAKMILIKPB, C.ID_PIHAKBERKEPENTINGAN, D.ID_BORANGH, ";
				sql += " C.NAMA_PB, C.NO_PB, C.ID_JENISNOPB, D.TARIKH_AKHIR_BAYARAGENSI, D.TARIKH_HANTAR ";
				sql += " FROM TBLPPTHAKMILIK A, TBLPPTHAKMILIKPB B, TBLPPTPIHAKBERKEPENTINGAN C, TBLPPTBORANGH D ";
				sql += " WHERE B.ID_HAKMILIK = A.ID_HAKMILIK ";
				sql += " AND B.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
				sql += " AND D.ID_HAKMILIKPB = B.ID_HAKMILIKPB ";
				sql += " AND B.ID_PIHAKBERKEPENTINGAN = '"+id_pihakberkepentingan+"'";
				sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
	
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("nama_penyata_bank", rs.getString("NAMA_PENYATA_BANK")==null?"":rs.getString("NAMA_PENYATA_BANK"));
					h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
					h.put("id_borangh", rs.getString("ID_BORANGH")==null?"":rs.getString("ID_BORANGH"));
					h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
					h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
					h.put("id_jenisnopb", rs.getString("ID_JENISNOPB")==null?"":rs.getString("ID_JENISNOPB"));
					h.put("tarikh_akhir_bayar", rs.getDate("TARIKH_AKHIR_BAYARAGENSI")==null?"":Format.format(rs.getDate("TARIKH_AKHIR_BAYARAGENSI")));
					h.put("tarikh_serahan", rs.getDate("TARIKH_HANTAR")==null?"":Format.format(rs.getDate("TARIKH_HANTAR")));
					dataPBBorangH.addElement(h);
				}
			} catch (Exception re) {
				log.error("Error: ", re);
				throw re;
				}
			finally {
				if(db != null) db.close();
			}
		
	}//close setDataPBBorangH
	
	@SuppressWarnings("unchecked")
	public static void simpanPenerimaanCek(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_terimabayaran = (String)data.get("id_terimabayaran");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		
	    		String txtBilLewat = (String)data.get("txtBilLewat");
	    		String txtDendaLewat = (String)data.get("txtDendaLewat");
	    		String sorJenisAward = (String)data.get("sorJenisAward");
	    		String sorFlagSerah = (String)data.get("sorFlagSerah");
	    		String txtPenerimaCek = (String)data.get("txtPenerimaCek");
	    		String txtNoCek = (String)data.get("txtNoCek");
	    		String txtAmaunCek = (String)data.get("txtAmaunCek");
	    		String txdTarikhCek = (String)data.get("txdTarikhCek");
	    		String txdTarikhAmbilCek = (String)data.get("txdTarikhAmbilCek");
	    		String txtMasaAmbil = (String)data.get("txtMasaAmbil");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtTempatAmbil = (String)data.get("txtTempatAmbil");
	    		
	    		String TAC = "to_date('" + txdTarikhAmbilCek + "','dd/MM/yyyy')";
	    		String TC = "to_date('" + txdTarikhCek + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();	    		
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("id_terimabayaran", id_terimabayaran);
	    		r.add("tarikh_ambil_cek",r.unquote(TAC));	   
	    		r.add("tarikh_cek",r.unquote(TC));	    		
	    		r.add("bil_hari_lewat", txtBilLewat);
	    		r.add("denda_lewat", txtDendaLewat);
	    		r.add("jenis_award", sorJenisAward);    
	    		r.add("flag_serah_cek", sorFlagSerah);
	    		r.add("penerima_cek", txtPenerimaCek);
	    		r.add("no_bayaran", txtNoCek); 
	    		r.add("amaun_bayaran", txtAmaunCek);
	    		r.add("masa_ambil_cek", txtMasaAmbil);
	    		r.add("jenis_waktu_ambil_cek", socJenisWaktu); 
	    		r.add("tempat_ambil", txtTempatAmbil);
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptbayaran");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanPenerimaanCek
	
	@SuppressWarnings("unchecked")
	public static void setdataPenerimaanCek(String id_bayaran)throws Exception {
				
		dataPenerimaanCek = new Vector();
				
			    Db db = null;
			    dataPenerimaanCek.clear();
			    String sql = "";
			    
			    try {
			    		   
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT DISTINCT A.ID_HAKMILIK, B.ID_HAKMILIKPB, C.ID_TERIMABAYARAN, D.ID_BAYARAN, E.ID_PIHAKBERKEPENTINGAN, ";
			    		sql += " E.NAMA_PB, E.NO_PB, E.ID_JENISNOPB, D.TARIKH_CEK, D.PENERIMA_CEK, D.NAMA_WAKIL, D.NO_WAKIL, D.TARIKH_SERAH_CEK, ";
			    		sql += " D.TARIKH_AMBIL_CEK, D.BIL_HARI_LEWAT, D.DENDA_LEWAT, D.JENIS_AWARD, D.FLAG_SERAH_CEK, D.NO_BAYARAN, D.ID_JENISNOPB AS ID_JENISNOWAKIL, ";
			    		sql += " D.AMAUN_BAYARAN, D.MASA_AMBIL_CEK, D.JENIS_WAKTU_AMBIL_CEK, D.TEMPAT_AMBIL, F.TARIKH_AKHIR_BAYARAGENSI, ";
			    		sql += " D.NO_RUJUKAN_SURATEFT, D.NO_EFT, D.TARIKH_TERIMA_EFT, D.TARIKH_SURAT_EFT, D.NO_BAUCER, D.PERIHAL, ";
			    		sql += " D.AMAUN_EFT "; 
			    		sql += " FROM TBLPPTHAKMILIK A, TBLPPTHAKMILIKPB B, TBLPPTTERIMABAYARAN C, TBLPPTBAYARAN D, TBLPPTPIHAKBERKEPENTINGAN E, ";
			    		sql += " TBLPPTBORANGH F ";
			    		sql += " WHERE B.ID_HAKMILIK = A.ID_HAKMILIK ";
			    		sql += " AND C.ID_HAKMILIK = A.ID_HAKMILIK ";
			    		sql += " AND B.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN";
			    		sql += " AND D.ID_TERIMABAYARAN = C.ID_TERIMABAYARAN ";
			    		sql += " AND D.ID_HAKMILIKPB = B.ID_HAKMILIKPB ";
			    		sql += " AND F.ID_HAKMILIKPB = B.ID_HAKMILIKPB ";
						sql += " AND D.ID_BAYARAN = '"+id_bayaran+"'";
			      
			    		ResultSet rs = stmt.executeQuery(sql);
			    		
			    		myLogger.info("SQL DATA PENERIMAAN CEK : "+sql);
			      
			    		Hashtable h;
			    	
			    		while (rs.next()) {
			    			
			    			h = new Hashtable();
			    			h.put("id_bayaran", rs.getString("ID_BAYARAN")== null?"":rs.getString("ID_BAYARAN"));
			    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
			    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
			    			h.put("id_terimabayaran", rs.getString("ID_TERIMABAYARAN")== null?"":rs.getString("ID_TERIMABAYARAN"));
			    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));	
			    			h.put("id_jenisnopb", rs.getString("ID_JENISNOPB")== null?"":rs.getString("ID_JENISNOPB"));
			    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
			    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
			    			h.put("penerima_cek", rs.getString("PENERIMA_CEK")== null?"":rs.getString("PENERIMA_CEK"));
			    			h.put("tarikh_cek", rs.getDate("TARIKH_CEK")==null?"":Format.format(rs.getDate("TARIKH_CEK")));	    
			    			h.put("tarikh_ambil_cek", rs.getDate("TARIKH_AMBIL_CEK")==null?"":Format.format(rs.getDate("TARIKH_AMBIL_CEK")));
			    			h.put("bil_hari_lewat", rs.getString("BIL_HARI_LEWAT")== null?"":rs.getString("BIL_HARI_LEWAT"));
			    			h.put("jenis_award", rs.getString("JENIS_AWARD")== null?"":rs.getString("JENIS_AWARD"));
			    			h.put("denda_lewat", rs.getString("DENDA_LEWAT")== null?0:rs.getDouble("DENDA_LEWAT"));			    			
			    			h.put("flag_serah_cek", rs.getString("FLAG_SERAH_CEK")== null?"":rs.getString("FLAG_SERAH_CEK"));
			    			h.put("no_bayaran", rs.getString("NO_BAYARAN")== null?"":rs.getString("NO_BAYARAN"));
			    			h.put("masa_ambil_cek", rs.getString("MASA_AMBIL_CEK")== null?"":rs.getString("MASA_AMBIL_CEK"));
			    			h.put("jenis_waktu_ambil_cek", rs.getString("JENIS_WAKTU_AMBIL_CEK")== null?"":rs.getString("JENIS_WAKTU_AMBIL_CEK"));
			    			h.put("tempat_ambil", rs.getString("TEMPAT_AMBIL")== null?"":rs.getString("TEMPAT_AMBIL"));
			    			h.put("nama_wakil", rs.getString("NAMA_WAKIL")== null?"":rs.getString("NAMA_WAKIL"));
			    			h.put("no_wakil", rs.getString("NO_WAKIL")== null?"":rs.getString("NO_WAKIL"));
			    			h.put("tarikh_serah_cek", rs.getDate("TARIKH_SERAH_CEK")==null?"":Format.format(rs.getDate("TARIKH_SERAH_CEK")));
			    			h.put("id_jenisnowakil", rs.getString("ID_JENISNOWAKIL")== null?"":rs.getString("ID_JENISNOWAKIL"));
			    			h.put("tarikh_akhir_bayar", rs.getDate("TARIKH_AKHIR_BAYARAGENSI")==null?"":Format.format(rs.getDate("TARIKH_AKHIR_BAYARAGENSI")));
			    			h.put("no_rujukan_surateft", rs.getString("NO_RUJUKAN_SURATEFT")== null?"":rs.getString("NO_RUJUKAN_SURATEFT"));
			    			h.put("no_eft", rs.getString("NO_EFT")== null?"":rs.getString("NO_EFT"));
			    			h.put("no_baucer", rs.getString("NO_BAUCER")== null?"":rs.getString("NO_BAUCER"));
			    			h.put("perihal", rs.getString("PERIHAL")== null?"":rs.getString("PERIHAL"));
			    			h.put("tarikh_terima_eft", rs.getDate("TARIKH_TERIMA_EFT")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_EFT")));
			    			h.put("tarikh_surat_eft", rs.getDate("TARIKH_SURAT_EFT")==null?"":Format.format(rs.getDate("TARIKH_SURAT_EFT")));
			    			h.put("amaun_eft", rs.getString("AMAUN_EFT")== null?0:rs.getDouble("AMAUN_EFT"));
			    			h.put("amaun_bayaran", rs.getString("AMAUN_BAYARAN")== null?0:rs.getDouble("AMAUN_BAYARAN"));
			    			//h.put("denda_lewat", rs.getString("DENDA_LEWAT")== null?0:rs.getDouble("DENDA_LEWAT"));
			    			
			    			dataPenerimaanCek.addElement(h);
	
			      }//close while
			      
			    } catch (Exception re) {
			    	log.error("Error: ", re);
			    	throw re;
			    	}// 
			    finally {
			      if (db != null) db.close();
			    }
			    
	}//close setdataPenerimaanCek
	
	@SuppressWarnings("unchecked")
	public Vector setdataPenerimaanCekOnline(String id_bayaran)throws Exception {
				
				Vector dataPenerimaanCekOnline = new Vector();
				
				Db db = null;
			    String sql = "";
			    
			    try {
			    	
			    	db = new Db();
		    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT DISTINCT A.ID_HAKMILIK, B.ID_HAKMILIKPB, C.ID_TERIMABAYARAN, D.ID_BAYARAN, E.ID_PIHAKBERKEPENTINGAN, ";
			    		sql += " E.NAMA_PB, E.NO_PB, E.ID_JENISNOPB, D.TARIKH_CEK, D.PENERIMA_CEK, D.NAMA_WAKIL, D.NO_WAKIL, D.TARIKH_SERAH_CEK, ";
			    		sql += " D.TARIKH_AMBIL_CEK, D.BIL_HARI_LEWAT, D.DENDA_LEWAT, D.JENIS_AWARD, D.FLAG_SERAH_CEK, D.NO_BAYARAN, D.ID_JENISNOPB AS ID_JENISNOWAKIL, ";
			    		sql += " D.AMAUN_BAYARAN, D.MASA_AMBIL_CEK, D.JENIS_WAKTU_AMBIL_CEK, D.TEMPAT_AMBIL, F.TARIKH_AKHIR_BAYARAGENSI, ";
			    		sql += " D.NO_RUJUKAN_SURATEFT, D.NO_EFT, D.TARIKH_TERIMA_EFT, D.TARIKH_SURAT_EFT, D.NO_BAUCER, D.PERIHAL, ";
			    		sql += " D.AMAUN_EFT "; 
			    		sql += " FROM TBLPPTHAKMILIK A, TBLPPTHAKMILIKPB B, TBLPPTTERIMABAYARAN C, TBLPPTBAYARAN D, TBLPPTPIHAKBERKEPENTINGAN E, ";
			    		sql += " TBLPPTBORANGH F ";
			    		sql += " WHERE B.ID_HAKMILIK = A.ID_HAKMILIK ";
			    		sql += " AND C.ID_HAKMILIK = A.ID_HAKMILIK ";
			    		sql += " AND B.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN";
			    		sql += " AND D.ID_TERIMABAYARAN = C.ID_TERIMABAYARAN ";
			    		sql += " AND D.ID_HAKMILIKPB = B.ID_HAKMILIKPB ";
			    		sql += " AND F.ID_HAKMILIKPB = B.ID_HAKMILIKPB ";
						sql += " AND D.ID_BAYARAN = '"+id_bayaran+"'";
			      
						ResultSet rs = stmt.executeQuery(sql);
						
					
			      
			    		Hashtable h;
			    		int bil = 1;
			    		
			    		while (rs.next()) {
			    			
			    			h = new Hashtable();
			    			h.put("bil",bil);
			    			h.put("id_bayaran", rs.getString("ID_BAYARAN")== null?"":rs.getString("ID_BAYARAN"));
			    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
			    			h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
			    			h.put("id_terimabayaran", rs.getString("ID_TERIMABAYARAN")== null?"":rs.getString("ID_TERIMABAYARAN"));
			    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));	
			    			h.put("id_jenisnopb", rs.getString("ID_JENISNOPB")== null?"":rs.getString("ID_JENISNOPB"));
			    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
			    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
			    			h.put("penerima_cek", rs.getString("PENERIMA_CEK")== null?"":rs.getString("PENERIMA_CEK"));
			    			h.put("tarikh_cek", rs.getDate("TARIKH_CEK")==null?"":Format.format(rs.getDate("TARIKH_CEK")));	    
			    			h.put("tarikh_ambil_cek", rs.getDate("TARIKH_AMBIL_CEK")==null?"":Format.format(rs.getDate("TARIKH_AMBIL_CEK")));
			    			h.put("bil_hari_lewat", rs.getString("BIL_HARI_LEWAT")== null?"":rs.getString("BIL_HARI_LEWAT"));
			    			h.put("jenis_award", rs.getString("JENIS_AWARD")== null?"":rs.getString("JENIS_AWARD"));
			    			h.put("denda_lewat", rs.getString("DENDA_LEWAT")== null?0:rs.getDouble("DENDA_LEWAT"));			    			
			    			h.put("flag_serah_cek", rs.getString("FLAG_SERAH_CEK")== null?"":rs.getString("FLAG_SERAH_CEK"));
			    			h.put("no_bayaran", rs.getString("NO_BAYARAN")== null?"":rs.getString("NO_BAYARAN"));
			    			h.put("masa_ambil_cek", rs.getString("MASA_AMBIL_CEK")== null?"":rs.getString("MASA_AMBIL_CEK"));
			    			h.put("jenis_waktu_ambil_cek", rs.getString("JENIS_WAKTU_AMBIL_CEK")== null?"":rs.getString("JENIS_WAKTU_AMBIL_CEK"));
			    			h.put("tempat_ambil", rs.getString("TEMPAT_AMBIL")== null?"":rs.getString("TEMPAT_AMBIL"));
			    			h.put("nama_wakil", rs.getString("NAMA_WAKIL")== null?"":rs.getString("NAMA_WAKIL"));
			    			h.put("no_wakil", rs.getString("NO_WAKIL")== null?"":rs.getString("NO_WAKIL"));
			    			h.put("tarikh_serah_cek", rs.getDate("TARIKH_SERAH_CEK")==null?"":Format.format(rs.getDate("TARIKH_SERAH_CEK")));
			    			h.put("id_jenisnowakil", rs.getString("ID_JENISNOWAKIL")== null?"":rs.getString("ID_JENISNOWAKIL"));
			    			h.put("tarikh_akhir_bayar", rs.getDate("TARIKH_AKHIR_BAYARAGENSI")==null?"":Format.format(rs.getDate("TARIKH_AKHIR_BAYARAGENSI")));
			    			h.put("no_rujukan_surateft", rs.getString("NO_RUJUKAN_SURATEFT")== null?"":rs.getString("NO_RUJUKAN_SURATEFT"));
			    			h.put("no_eft", rs.getString("NO_EFT")== null?"":rs.getString("NO_EFT"));
			    			h.put("no_baucer", rs.getString("NO_BAUCER")== null?"":rs.getString("NO_BAUCER"));
			    			h.put("perihal", rs.getString("PERIHAL")== null?"":rs.getString("PERIHAL"));
			    			h.put("tarikh_terima_eft", rs.getDate("TARIKH_TERIMA_EFT")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_EFT")));
			    			h.put("tarikh_surat_eft", rs.getDate("TARIKH_SURAT_EFT")==null?"":Format.format(rs.getDate("TARIKH_SURAT_EFT")));
			    			h.put("amaun_eft", rs.getString("AMAUN_EFT")== null?0:rs.getDouble("AMAUN_EFT"));
			    			h.put("amaun_bayaran", rs.getString("AMAUN_BAYARAN")== null?0:rs.getDouble("AMAUN_BAYARAN"));
			    			//h.put("denda_lewat", rs.getString("DENDA_LEWAT")== null?0:rs.getDouble("DENDA_LEWAT"));
			    			
			    			dataPenerimaanCekOnline.addElement(h);	
							bil++;
						}
						
						return dataPenerimaanCekOnline;
					}catch (Exception re) {
						throw re;
					} finally {
						if (db != null)
							db.close();
					}
	}//close setdataPenerimaanCek
	
	public static void hapusPenerimaanCek(String id_bayaran) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM TBLPPTBAYARAN WHERE ID_BAYARAN = '"+id_bayaran+"'";
	    		stmt.executeUpdate(sql);
	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusPenerimaanCek
	
	@SuppressWarnings("unchecked")
	public static void updatePenerimaanCek(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_bayaran = (String)data.get("id_bayaran");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		
	    		String txtBilLewat = (String)data.get("txtBilLewat");
	    		String txtDendaLewat = (String)data.get("txtDendaLewat");
	    		String sorJenisAward = (String)data.get("sorJenisAward");
	    		String sorFlagSerah = (String)data.get("sorFlagSerah");
	    		String txtPenerimaCek = (String)data.get("txtPenerimaCek");
	    		String txtNoCek = (String)data.get("txtNoCek");
	    		String txtAmaunCek = (String)data.get("txtAmaunCek");
	    		String txdTarikhCek = (String)data.get("txdTarikhCek");
	    		String txdTarikhAmbilCek = (String)data.get("txdTarikhAmbilCek");
	    		String txtMasaAmbil = (String)data.get("txtMasaAmbil");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtTempatAmbil = (String)data.get("txtTempatAmbil");
	    		
	    		String TAC = "to_date('" + txdTarikhAmbilCek + "','dd/MM/yyyy')";
	    		String TC = "to_date('" + txdTarikhCek + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();	  
	    		r.update("id_bayaran", id_bayaran);
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("tarikh_ambil_cek",r.unquote(TAC));	   
	    		r.add("tarikh_cek",r.unquote(TC));	    		
	    		r.add("bil_hari_lewat", txtBilLewat);
	    		r.add("denda_lewat", txtDendaLewat);
	    		r.add("jenis_award", sorJenisAward);    
	    		r.add("flag_serah_cek", sorFlagSerah);
	    		r.add("penerima_cek", txtPenerimaCek);
	    		r.add("no_bayaran", txtNoCek); 
	    		r.add("amaun_bayaran", txtAmaunCek);
	    		r.add("masa_ambil_cek", txtMasaAmbil);
	    		r.add("jenis_waktu_ambil_cek", socJenisWaktu); 
	    		r.add("tempat_ambil", txtTempatAmbil);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptbayaran");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updatePenerimaanCek
	
	@SuppressWarnings("unchecked")
	public static void updatePenyerahanCek(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_bayaran = (String)data.get("id_bayaran");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		
	    		String txtNamaWakil = (String)data.get("txtNamaWakil");
	    		String txtNoWakil = (String)data.get("txtNoWakil");
	    		String txdTarikhSerahCek = (String)data.get("txdTarikhSerahCek");
	    		String socJenisNoWakil = (String)data.get("socJenisNoWakil");
	    		
	    		String TSC = "to_date('" + txdTarikhSerahCek + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();	  
	    		r.update("id_bayaran", id_bayaran);
	    		r.add("cara_bayar","1");
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("tarikh_serah_cek",r.unquote(TSC));	   	    	
	    		r.add("nama_wakil", txtNamaWakil);
	    		r.add("no_wakil", txtNoWakil); 
	    		r.add("id_jenisnopb", socJenisNoWakil);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptbayaran");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updatePenyerahanCek
	
	@SuppressWarnings("unchecked")
	public static void updateEFT(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_bayaran = (String)data.get("id_bayaran");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		
	    		String txtNoRujSuratEft = (String)data.get("txtNoRujSuratEft");
	    		String txtNoEft = (String)data.get("txtNoEft");
	    		String txtAmaun = (String)data.get("txtAmaun");
	    		String txdTarikhTerimaSurat = (String)data.get("txdTarikhTerimaSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txtNoBaucer = (String)data.get("txtNoBaucer");
	    		String txtPerihal = (String)data.get("txtPerihal");
	    		
	    		String TTS = "to_date('" + txdTarikhTerimaSurat + "','dd/MM/yyyy')";
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();	  
	    		r.update("id_bayaran", id_bayaran);
	    		r.add("cara_bayar","2");
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("tarikh_terima_eft",r.unquote(TTS));	   	
	    		r.add("tarikh_surat_eft",r.unquote(TS));	 
	    		r.add("no_rujukan_surateft", txtNoRujSuratEft);
	    		r.add("no_eft", txtNoEft); 
	    		r.add("amaun_eft", txtAmaun);
	    		r.add("no_baucer", txtNoBaucer); 
	    		r.add("perihal", txtPerihal);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptbayaran");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateEFT
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data,String idpermohonan) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		
	    		//status bayaran pampasan
	    		String status = "72";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", idpermohonan);	   
	    		r.add("id_status",status);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateStatus
	
	@SuppressWarnings("unchecked")
	public static void simpanPBEFT(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		
	    		String txtNoRujSuratEft = (String)data.get("txtNoRujSuratEft");
	    		String txtNoEft = (String)data.get("txtNoEft");
	    		String txtAmaun = (String)data.get("txtAmaun");
	    		String txdTarikhTerimaSurat = (String)data.get("txdTarikhTerimaSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSuratEFT");
	    		String txtNoBaucer = (String)data.get("txtNoBaucer");
	    		String txtPerihal = (String)data.get("txtPerihal");
	    		
	    		String TTS = "to_date('" + txdTarikhTerimaSurat + "','dd/MM/yyyy')";
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();	  
	    		r.add("cara_bayar","2");
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("tarikh_terima_eft",r.unquote(TTS));	   	
	    		r.add("tarikh_surat_eft",r.unquote(TS));	 
	    		r.add("no_rujukan_surateft", txtNoRujSuratEft);
	    		r.add("no_eft", txtNoEft); 
	    		    		
	    		r.add("bil_hari_lewat", (String)data.get("txtBilLewat"));
	    		r.add("denda_lewat", (String)data.get("txtDendaLewat"));
	    		r.add("jenis_award", (String)data.get("sorJenisAward"));    
	    		r.add("amaun_bayaran", txtAmaun);
	    		r.add("no_baucer", txtNoBaucer); 
	    		r.add("perihal", txtPerihal);
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptbayaran");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanPBEFT

	@SuppressWarnings("unchecked")
	public static void updatePBEFT(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_bayaran = (String)data.get("id_bayaran");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		
	    		String txtNoRujSuratEft = (String)data.get("txtNoRujSuratEft");
	    		String txtNoEft = (String)data.get("txtNoEft");
	    		String txtAmaun = (String)data.get("txtAmaun");
	    		String txdTarikhTerimaSurat = (String)data.get("txdTarikhTerimaSurat");
	    		String txdTarikhSurat = (String)data.get("txdTarikhSuratEFT");
	    		String txtNoBaucer = (String)data.get("txtNoBaucer");
	    		String txtPerihal = (String)data.get("txtPerihal");
	    		
	    		String TTS = "to_date('" + txdTarikhTerimaSurat + "','dd/MM/yyyy')";
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();	  
	    		r.update("id_bayaran",id_bayaran);
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("tarikh_terima_eft",r.unquote(TTS));	   	
	    		r.add("tarikh_surat_eft",r.unquote(TS));	 
	    		r.add("no_rujukan_surateft", txtNoRujSuratEft);
	    		r.add("no_eft", txtNoEft); 
	    		r.add("bil_hari_lewat", (String)data.get("txtBilLewat"));
	    		r.add("denda_lewat", (String)data.get("txtDendaLewat"));
	    		r.add("jenis_award", (String)data.get("sorJenisAward"));  
	    		r.add("amaun_bayaran", txtAmaun);
	    		r.add("no_baucer", txtNoBaucer); 
	    		r.add("perihal", txtPerihal);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptbayaran");
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updatePBEFT
	
	@SuppressWarnings("unchecked")
	public static void setDataEFT(String id_bayaran)throws Exception {
			
		dataEFT = new Vector();
			
		    Db db = null;
		    dataEFT.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT C.ID_HAKMILIKPB, D.ID_PIHAKBERKEPENTINGAN, E.ID_BAYARAN, D.NAMA_PB, D.NO_PB, E.NO_RUJUKAN_SURATEFT, ";
		    		sql += " D.ID_JENISNOPB, E.BIL_HARI_LEWAT, E.DENDA_LEWAT, E.JENIS_AWARD,E.NO_EFT, E.AMAUN_BAYARAN, E.TARIKH_TERIMA_EFT, E.TARIKH_SURAT_EFT, E.NO_BAUCER, E.PERIHAL";
		    		sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLPPTPIHAKBERKEPENTINGAN D, TBLPPTBAYARAN E ";
		    		sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
		    		sql += " AND C.ID_HAKMILIK = B.ID_HAKMILIK ";
		    		sql += " AND C.ID_PIHAKBERKEPENTINGAN = D.ID_PIHAKBERKEPENTINGAN ";
		    		sql += " AND E.ID_HAKMILIKPB = C.ID_HAKMILIKPB ";
					sql += " AND E.CARA_BAYAR = '2' ";
					sql += " AND E.ID_BAYARAN = '"+id_bayaran+"'";
		     
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("id_bayaran", rs.getString("ID_BAYARAN")== null?"":rs.getString("ID_BAYARAN"));
		    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    			h.put("id_jenisnopb", rs.getString("ID_JENISNOPB")== null?"":rs.getString("ID_JENISNOPB"));
		    			h.put("no_eft", rs.getString("NO_EFT")== null?"":rs.getString("NO_EFT"));
		    			
		    			if(rs.getString("AMAUN_BAYARAN") != null){
		    				
		    				double TP = rs.getDouble("AMAUN_BAYARAN");
		    				h.put("amaun_bayaran",TP);
		    				
		    			}else{
		    				h.put("amaun_bayaran", "0.00");
		    			}
		    			
		    			h.put("tarikh_terima_eft", rs.getDate("TARIKH_TERIMA_EFT")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_EFT")));
		    			h.put("tarikh_surat_eft", rs.getDate("TARIKH_SURAT_EFT")==null?"":Format.format(rs.getDate("TARIKH_SURAT_EFT")));
		    			h.put("no_baucer", rs.getString("NO_BAUCER")== null?"":rs.getString("NO_BAUCER"));
		    			h.put("perihal", rs.getString("PERIHAL")== null?"":rs.getString("PERIHAL"));
		    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
		    			h.put("no_pb", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
		    			h.put("no_rujukan_surateft", rs.getString("NO_RUJUKAN_SURATEFT")== null?"":rs.getString("NO_RUJUKAN_SURATEFT"));
		    			h.put("bil_hari_lewat", rs.getString("BIL_HARI_LEWAT")== null?"":rs.getString("BIL_HARI_LEWAT"));
		    			h.put("jenis_award", rs.getString("JENIS_AWARD")== null?"":rs.getString("JENIS_AWARD"));
		    			h.put("denda_lewat", rs.getString("DENDA_LEWAT")== null?0:rs.getDouble("DENDA_LEWAT"));
		    			dataEFT.addElement(h);
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataEFT
	
	public static void hapusEFT(String id_bayaran) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptbayaran where id_bayaran = '"+id_bayaran+"'";
	    		stmt.executeUpdate(sql);
	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusEFT
	
	
	@SuppressWarnings("unchecked")
	public static void setDataAfidevit(String id_award)throws Exception {
				
		dataAfidevit = new Vector();
				
			    Db db = null;
			    dataAfidevit.clear();
			    String sql = "";
			    
			    try {
			    		   
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT A.id_afidavit, B.ID_PEJABAT, ";
			    		sql += " A.TUJUAN,A.PERKARA_RUJUKAN, B.ID_NEGERI, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.ID_BANDAR, B.POSKOD ";
			    		sql += " FROM TBLPPTAFIDAVIT A, TBLRUJPEJABAT B ";
			    		sql += " WHERE A.ID_MAHKAMAH = B.ID_PEJABAT(+) ";
			    		sql += " AND A.ID_AWARD = '"+id_award+"'";
			      
			    		ResultSet rs = stmt.executeQuery(sql);
			      
			    		Hashtable h;
			    	
			    		while (rs.next()) {
			    			
			    			h = new Hashtable();
			    			h.put("id_afidavit", rs.getString("id_afidavit")== null?"":rs.getString("id_afidavit"));
			    			h.put("id_pejabat", rs.getString("ID_PEJABAT")== null?"":rs.getString("ID_PEJABAT"));
			    			h.put("tujuan", rs.getString("TUJUAN")== null?"":rs.getString("TUJUAN"));
			    			h.put("perkara_rujukan", rs.getString("PERKARA_RUJUKAN")== null?"":rs.getString("PERKARA_RUJUKAN"));
			    			h.put("id_negeri", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
			    			h.put("alamat1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
			    			h.put("alamat2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
			    			h.put("alamat3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
			    			h.put("id_bandar", rs.getString("ID_BANDAR")== null?"":rs.getString("ID_BANDAR"));
			    			h.put("poskod", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));
			    			dataAfidevit.addElement(h);
	
			      }//close while
			      
			    } catch (Exception re) {
			    	log.error("Error: ", re);
			    	throw re;
			    	}// 
			    finally {
			      if (db != null) db.close();
			    }
			    
	}//close setDataAfidevit
	
	@SuppressWarnings("unchecked")
	  public static void setDataPejabat(String id_pejabat)throws Exception {
			
		dataPejabat = new Vector();
			
		    Db db = null;
		    dataPejabat.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT ALAMAT1,ALAMAT2,ALAMAT3,POSKOD,ID_NEGERI,ID_BANDAR FROM TBLRUJPEJABAT WHERE ID_PEJABAT = '"+id_pejabat+"'";
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();	    		
		    			h.put("alamat1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
		    			h.put("alamat2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
		    			h.put("alamat3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
		    			h.put("poskod", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));
		    			h.put("id_negeri", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
		    			h.put("id_bandar", rs.getString("ID_BANDAR")== null?"":rs.getString("ID_BANDAR"));
		    			
		    			dataPejabat.addElement(h);

		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setSizeExistPB
	
	@SuppressWarnings("unchecked")
	public static void simpanAfidevit(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_award = (String)data.get("id_award");
	    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
	    		
	    		String txtTujuan = (String)data.get("txtTujuan");
	    		String txtPerkara = (String)data.get("txtPerkara");
	    		String socMahkamah = (String)data.get("socMahkamah");
	    		
	    		//insert
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_award", id_award);	
	    		r.add("id_hakmilikpb", id_hakmilikpb);
	    		r.add("tujuan", txtTujuan);	
	    		r.add("perkara_rujukan", txtPerkara);	
	    		r.add("id_mahkamah", socMahkamah);	
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("TBLPPTAFIDAVIT");
	    		stmt.executeUpdate(sql);
	    		
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanAfidevit
	
	@SuppressWarnings("unchecked")
	public static void updateAfidevit(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_afidavit = (String)data.get("id_afidavit");

	    		String txtTujuan = (String)data.get("txtTujuan");
	    		String txtPerkara = (String)data.get("txtPerkara");
	    		String socMahkamah = (String)data.get("socMahkamah");
	    		
	    		//insert
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_afidavit", id_afidavit);	
	    		r.add("tujuan", txtTujuan);	
	    		r.add("perkara_rujukan", txtPerkara);	
	    		r.add("id_mahkamah", socMahkamah);	
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTAFIDAVIT");
	    		stmt.executeUpdate(sql);
	    		
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateAfidevit
	
	@SuppressWarnings("unchecked")
	public static void setDataIdAward(String id_hakmilikpb)throws Exception {
				
		dataIdAward = new Vector();
				
			    Db db = null;
			    dataIdAward.clear();
			    String sql = "";
			    
			    try {
			    		   
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			      
			    		sql = "SELECT A.ID_AWARD FROM TBLPPTAWARD A WHERE A.ID_HAKMILIKPB = '"+id_hakmilikpb+"'";
			      
			    		ResultSet rs = stmt.executeQuery(sql);
			      
			    		Hashtable h;
			    	
			    		while (rs.next()) {
			    			
			    			h = new Hashtable();
			    			h.put("id_award", rs.getString("ID_AWARD")== null?"":rs.getString("ID_AWARD"));
			    			dataIdAward.addElement(h);
	
			      }//close while
			      
			    } catch (Exception re) {
			    	log.error("Error: ", re);
			    	throw re;
			    	}// 
			    finally {
			      if (db != null) db.close();
			    }
			    
	}//close setDataIdAward
	
	@SuppressWarnings("unchecked")
	public static Vector getListBorangG(String idpermohonan)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = " SELECT A.TARIKH_BORANGG, COUNT(*)AS BIL_LOT "+
		    		" FROM TBLPPTBORANGG A, TBLPPTSIASATAN B, TBLPPTHAKMILIK C, TBLPPTPERMOHONAN D "+
		    		" WHERE A.ID_SIASATAN = B.ID_SIASATAN "+
		    		" AND B.ID_HAKMILIK = C.ID_HAKMILIK "+
		    		" AND C.ID_PERMOHONAN = D.ID_PERMOHONAN "+
		    		" AND D.ID_PERMOHONAN = '"+idpermohonan+"' "+
		    		" GROUP BY A.TARIKH_BORANGG ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("TARIKH_BORANGG", rs.getDate("TARIKH_BORANGG")==null?"":Format.format(rs.getDate("TARIKH_BORANGG")));
		    			h.put("BIL_LOT", rs.getString("BIL_LOT")== null?"":rs.getString("BIL_LOT"));
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPermohonan

	public static Vector getDataDokumenBorangG() {
		return dataDokumenBorangG;
	}

	public static void setDataDokumenBorangG(Vector dataDokumenBorangG) {
		FrmSek8PampasanData.dataDokumenBorangG = dataDokumenBorangG;
	}
	
	//penambahan yati-EMEL KJP 23/1/2017
	public static Vector listEmelKJP = null;
	
	public static Vector getListEmelKJP() {
		return listEmelKJP;
	}
	
	//get detail user
		@SuppressWarnings("unchecked")
		public static Vector setListEmelKJP(String id_permohonan) throws Exception {
		
			Vector listEmelKJP = new Vector();
			
			Db db = null;
			String sql = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
				
					sql =  "SELECT C.ID_PERMOHONAN, A.ID_KEMENTERIAN,  B.EMEL FROM TBLPFDFAIL A, TBLRUJKEMENTERIAN B, TBLPPTPERMOHONAN C WHERE A.ID_KEMENTERIAN = B.ID_KEMENTERIAN AND A.ID_FAIL = C.ID_FAIL AND C.ID_PERMOHONAN = '"+id_permohonan+"' ";
					//myLogger.info("GET EMEL : "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h = null;
					while (rs.next()) {
						h = new Hashtable();
						h.put("id_permohonan", rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
						h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")== null?"":rs.getString("ID_KEMENTERIAN"));
						h.put("emel", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
					
						listEmelKJP.addElement(h);
						
				}
				return listEmelKJP;
			}catch (Exception re) {
				throw re;
			} finally {
				if (db != null)
					db.close();
			}
		}//close setEmelKJP
		
		
		
		//getID_BorangG
		public static Vector getID_BorangG(String id_siasatan) throws Exception {
			
			Vector borangG = new Vector();
			
			Db db = null;
			String sql = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
				
					sql =  "SELECT ID_BORANGG FROM TBLPPTBORANGG WHERE ID_SIASATAN = '"+id_siasatan+"'";
					//myLogger.info("GET EMEL : "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h = null;
					while (rs.next()) {
						h = new Hashtable();
						
						h.put("id_BorangG", rs.getString("ID_BORANGG")== null?"":rs.getString("ID_BORANGG"));
					
						borangG.addElement(h);
						
				}
				return borangG;
			}catch (Exception re) {
				throw re;
			} finally {
				if (db != null)
					db.close();
			}
		}

	
}//close class

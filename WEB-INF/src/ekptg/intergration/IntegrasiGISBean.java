package ekptg.intergration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.entities.Agensi;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Kementerian;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Seksyen;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.integrasi.entities.MaklumaTanahGIS;

public class IntegrasiGISBean implements IIntegrasi {
	private static Logger myLog = Logger.getLogger(ekptg.intergration.IntegrasiGISBean.class);
	private static Vector<MaklumaTanahGIS> senaraiCarian = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void simpan(String noFail,int statusTanah) throws Exception{		
		Db db = null;
		Connection conn = null;
	    String sql = "";
	    try	{	    	
	    	db = new Db();
	    	conn = db.getConnection();
	    	conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();		  
	    	r.add("NO_FAIL", noFail);
	    	r.add("STATUS_TANAH", statusTanah);
	    	r.add("TARIKH_MASUK", r.unquote("sysdate"));
	    	sql = r.getSQLInsert("TBLINTGIS");
		    myLog.info("simpan:sql = "+sql);
	    	stmt.executeUpdate(sql);
	    	conn.commit();
			
	    }catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    
	    }finally {
	    	if (db != null) db.close();
		    if (conn != null) conn.close();
	    
	    }
	    	
	}
	@Override
	public void simpan(String noFail,int statusTanah,String idHakmilik,String ph) throws Exception{		
		Db db = null;
		Connection conn = null;
	    String sql = "";
	    try	{	    	
	    	db = new Db();
	    	conn = db.getConnection();
	    	conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();	
	    	r.add("NO_FAIL", noFail);
	    	r.add("STATUS_TANAH", statusTanah);
	    	r.add("UPI", getUPI(idHakmilik));
	    	r.add("TARIKH_MASUK", r.unquote("sysdate"));
	    	sql = r.getSQLInsert("TBLINTGIS");
		    myLog.info("simpans:sql = "+sql);
	    	stmt.executeUpdate(sql);
	    	conn.commit();
			
	    }catch(Exception e){
	    	conn.rollback();
	    	e.printStackTrace();
	    
	    }finally {
	    	if (db != null) db.close();
		    if (conn != null) conn.close();
	    
	    }
	    	
	}
	@Override
	public Vector<MaklumaTanahGIS> getSenarai(
			String idNegeri, String idDaerah,String idMukim
			,String noFail
			,String idJenisHakmilik,String noHakmilikWarta
			,String idLot,String noLot
			,String idAgensi, String idKementerian
			,String tajuk,String idUrusan,String tarikhBukaFail ) throws Exception {
	    Db db = null;
	    senaraiCarian = new Vector<MaklumaTanahGIS>();
	    try {
	    	db = new Db();
	    	Statement stmt = db.getStatement();  
	    	String sql = "SELECT * "+
	    	   " FROM VGIS_SENARAI_CHARTING V "+
	           //" WHERE V.LATITUDE = 'TIADA' "+
	           " WHERE V.NO_FAIL IS NOT NULL "+
	           "";
	          
			//idJenisTanah
//			if (idJenisTanah != null) {
//				if (!idJenisTanah.trim().equals("")) {
//					if (Integer.parseInt(idJenisTanah) == 1){
//						sql = sql + " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
//					} else if (Integer.parseInt(idJenisTanah) == 2){
//						sql = sql + " AND NVL(A.NO_WARTA,' ') <> ' ' ";
//					}
//				}
//			}
			//id negeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")) {
					if (!idNegeri.trim().equals("-1")) {
						sql = sql + " AND V.KOD_NEGERI IN (" +
								" SELECT KOD_NEGERI FROM TBLRUJNEGERI WHERE " +
								" ID_NEGERI= '" + idNegeri + "')";
					}
				}
			}
			//id daerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")) {
					if (!idDaerah.trim().equals("-1")) {
						sql = sql + " AND V.KOD_DAERAH = '" + idDaerah + "'";
					}
				}
			}
			//id mukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")) {
					if (!idMukim.trim().equals("-1")) {
						sql = sql + " AND V.KOD_MUKIM = '" + idMukim + "'";
					}
				}
			}	      
			//no fail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(V.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
				}
			}
			//id jenishakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")) {
					if (!idJenisHakmilik.trim().equals("-1") && !idJenisHakmilik.trim().equals("00")) {
						sql = sql + " AND V.KOD_HAKMILIK = '" + idJenisHakmilik + "'";
					}
				}
			} 
			//no hakmilik
			if (noHakmilikWarta != null) {
				if (!noHakmilikWarta.trim().equals("") && !noHakmilikWarta.trim().equals("-1")) {
					sql = sql + " AND UPPER(V.NO_HAKMILIK) LIKE '%' ||'" + noHakmilikWarta.toUpperCase() + "'|| '%'";
				}
			}       
			//id lot
			if (idLot != null) {
				if (!idLot.trim().equals("")) {
					if (!idLot.trim().equals("-1")) {
						sql = sql + " AND V.KOD_LOT = '" + idLot + "'";
					}
				}
			} 
			//no lot
			if (noLot != null) {
				if (!noLot.trim().equals("") && !noLot.trim().equals("-1")) {
					sql = sql + " AND UPPER(V.NO_LOT) LIKE '%' ||'" + noLot.toUpperCase() + "'|| '%'";
				}
			} 
			//id Kementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")) {
					if (!idKementerian.trim().equals("-1")) {
						//20/09/2010
						sql = sql + " AND V.KOD_KEMENTERIAN = '" + idKementerian + "'";
					}
				}
			}
			//no agensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")) {
					if (!idAgensi.trim().equals("-1")) {
						sql = sql + " AND V.KOD_AGENSI = '" + idAgensi + "'";
					}
				}
			} 
			//idStatus
//			if (idStatus != null) {
//				if (!idStatus.trim().equals("")) {
//					if (idStatus.trim().equals("1")) {
//						//AKTIF
//						sql = sql + " AND (A.STATUS_SAH IN (" +
//						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
//						"))";						
//						
//					} else if (idStatus.trim().equals("2")) {
//						sql = sql + " AND (A.STATUS_SAH IN (" +
//						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=0" +
//						"))";						
//					
//					}else{
//						//AKTIF
//						sql = sql + " AND (A.STATUS_SAH IN (" +
//						"SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1" +
//						"))";						
//					
//					}
//					
//				}
//				
//			}
		      if (tarikhBukaFail != null && !"-1".equals(tarikhBukaFail) && !"".equals(tarikhBukaFail)) {
		    	  sql = sql + " AND TO_CHAR(V.TARIKH_DAFTAR,'dd/MM/yyyy') = '"+tarikhBukaFail+"' ";
		      }
			
			//sql = sql +" ORDER BY C.NO_FAIL DESC ";
			//myLog.info("getSenarai:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			MaklumaTanahGIS mt = null;
	      	int bil = 1;

	      	while (rs.next()) {
	      		mt = new MaklumaTanahGIS();
	      		mt.setBil(bil);
	      		mt.setNoFail(rs.getString("NO_FAIL"));
	      		mt.setKegunaan(rs.getString("TUJUAN"));
	      		//myLog.info(""+rs.getString("TARIKH_DAFTAR"));
      			if(rs.getString("TARIKH_DAFTAR")==null){
    	      		mt.setTarikhDaftar(new Date("01/01/1900"));
    	      		mt.setTarikhDaftarStr("TIADA");
      			}else{
      				mt.setTarikhDaftar(rs.getDate("TARIKH_DAFTAR"));
    	      		mt.setTarikhDaftarStr(sdf.format(rs.getDate("TARIKH_DAFTAR")));

      			}
      			Negeri negeri = new Negeri();
	      		negeri.setKodNegeri(rs.getString("KOD_NEGERI"));
	      		negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
	      		mt.setNegeri(negeri);
	      		Daerah daerah = new Daerah();
	      		daerah.setKodDaerah(rs.getString("KOD_DAERAH"));
	      		mt.setDaerah(daerah);
	      		Mukim mukim = new Mukim();
	      		mukim.setKodMukim(rs.getString("KOD_MUKIM"));
	      		mt.setMukim(mukim);
	      		Seksyen sek = new Seksyen();
	      		sek.setKodSeksyen(rs.getString("SEK"));
	      		mt.setSeksyen(sek);
	      		mt.setLuasBersamaan(rs.getDouble("LUAS"));
	      		mt.setLuasString(rs.getString("KETERANGAN_LUAS"));
	      		Tblrujlot lot = new Tblrujlot();
	      		lot.setKodLot(rs.getString("KOD_LOT"));
	      		mt.setRujLot(lot);
	      		mt.setNoLot(rs.getString("NO_LOT"));
	      		lot.setKeterangan(rs.getString("KOD_LOT"));
	      		Tblrujjenishakmilik jh = new Tblrujjenishakmilik(); 
	      		jh.setKodJenisHakmilik(rs.getString("KOD_HAKMILIK"));
	      		mt.setRujJenisHakmilik(jh);
	      		mt.setNoHakmilik(rs.getString("NO_HAKMILIK"));
	      		mt.setNoWarta(rs.getString("NO_WARTA"));
	      		mt.setLatitude(rs.getString("LATITUDE"));
	      		mt.setLongitude(rs.getString("LONGITUDE"));
	      		mt.setIdMasuk(rs.getLong("ID_MASUK"));
	      		Agensi agensi = new Agensi();
	      		agensi.setKodAgensi(rs.getString("KOD_AGENSI"));
	      		Kementerian kem = new Kementerian();
	      		kem.setKodKementerian(rs.getString("KOD_KEMENTERIAN"));
	      		agensi.setKementerian(kem);
	      		mt.setAgensi(agensi);
	      		mt.setStatusGIS(rs.getInt("STATUS_TANAH"));
	      		mt.setStatusTanahGIS(rs.getString("KETERANGAN_STATUSTANAH"));
	      		mt.setUPI(rs.getString("UPI"));
	      		mt.setIdGIS(rs.getString("ID_GIS"));
	      		senaraiCarian.addElement(mt);
	      		bil++;
	    	  
	      	}

	    } finally {
	      if (db != null) db.close();
	    
	    }		
	    return senaraiCarian;
	    
	}	
	
    public String getUPI(String idHakmilik) throws Exception{		
    	Db db = null;
    	Connection conn = null;
    	String sql = "";
    	String returnVal = "";
    	try	{	    	
    		db = new Db();
    		conn = db.getConnection();
       		Statement stmt = db.getStatement();
    		SQLRenderer r = new SQLRenderer();		  
    		r.add("GETUPI(RN.KOD_NEGERI,RD.KOD_DAERAH_UPI,RM.KOD_MUKIM_UPI,'000',RJH.STATUS_HAKMILIK ,MT.NO_LOT,MT.NO_HAKMILIK,RJH.KOD_JENIS_HAKMILIK) UPI");
    		r.add("MT.ID_JENISHAKMILIK", r.unquote("RJH.ID_JENISHAKMILIK"));
    		r.add("MT.ID_NEGERI", r.unquote("RN.ID_NEGERI"));
    		r.add("MT.ID_DAERAH", r.unquote("RD.ID_DAERAH"));
    		r.add("MT.ID_MUKIM", r.unquote("RM.ID_MUKIM"));
    		r.add("MT.ID_HAKMILIK", r.unquote(idHakmilik));
            //" AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK "+
    	    sql = r.getSQLSelect("TBLHTPHAKMILIK MT,TBLRUJJENISHAKMILIK RJH,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM");
			myLog.info("getUPI:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);	    
			
    	    if(rs.next()){
    	    	returnVal = rs.getString("UPI");
    	    }
    		    
    	}catch(Exception e){
    		conn.rollback();
   	    	e.printStackTrace();
    		   
    	}finally {
    		if (db != null) db.close();
    	    if (conn != null) conn.close();
    		    
    	}
    	return returnVal;  
    	
    }
    
    public void deleteGIS(String idGIS) throws Exception {
		String sql = "";
		Db db = new Db();
		try {
			db = new Db();
			
			Statement stmt = db.getStatement();
			sql = "DELETE FROM TBLINTGIS WHERE ID IN (" + idGIS + ")";
			//System.out.println("sql delete TBLINTGIS" +sql);			
			stmt.executeUpdate(sql);
			
		}catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
    		
	
}

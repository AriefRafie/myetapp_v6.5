package ekptg.model.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.view.admin.utils.Converter;
import ekptg.view.utils.DaerahJagaanInformation;
import ekptg.view.utils.PegawaiInfoDetail;
import ekptg.view.utils.PegawaiInformation;
import ekptg.view.utils.PejabatDaerahInformation;
import ekptg.view.utils.PejabatInformation;

/**
 * 
 * @author Azam
 * @modified Mohd Nazrul
 */
public class FrmPejabatJKPTGData {
	static Logger myLogger = Logger.getLogger(FrmPejabatJKPTGData.class);
	
	private static FrmPejabatJKPTGData instance = null;
	
	public static FrmPejabatJKPTGData getInstance() {
		    if (instance == null) instance = new FrmPejabatJKPTGData();
		    return instance;
		  }
	  
	public FrmPejabatJKPTGData() {
		
	}
	
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM tblrujpejabatjkptg WHERE ID_PEJABATJKPTG='"+id+"' ";
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	
	public boolean insert(Hashtable parameters) {
		  String sql = "";
		  String name = "";
		  String value ="";
		  Db db = null;
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++) {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  r.add(name.replace("Form_",""),value);
			  }
			  r.add("tarikh_masuk",r.unquote("SYSDATE"));
			  r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			  sql = r.getSQLInsert("tblrujpejabatjkptg");
			  myLogger.info(sql);
			  stmt.executeUpdate(sql);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  finally {
			  if (db != null) db.close();
		  }
		  
		  return true;
	}
	
	  public boolean update(Hashtable parameters,String id) {
		  String sql="";
		  String name="";
		  String value="";
		  if (id != null) {
			  sql = "Update tblrujpejabatjkptg SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  sql = sql + name.replace("Form_","") + "='"+ value + "'" + (x<parameters.size()?",":"") ;
			   }
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE ID_PEJABATJKPTG = '" +id+ "' ";
			  myLogger.info(sql);
			  Db db = null;
			  try {
				  db = new Db();
				  Statement stmt = db.getStatement();
				  stmt.executeUpdate(sql);
	
			  } catch (Exception e) { 
				  e.printStackTrace();
				  return false;
			  }
			  finally {
				  if (db != null) db.close();
			  }
		  }
		  return true;
	  }
	
	public Hashtable getPejabatJKPTGDetails(String id) {
		Hashtable details_data = null;
		Db db = null;
		try {
			db = new Db();
			details_data = new Hashtable();
			Statement stmt = db.getStatement();
			String sql = "Select * from tblrujpejabatjkptg WHERE ID_PEJABATJKPTG='"+id+"' ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				details_data.put("id_negeri",checkIsNull(rs.getString("id_negeri")));
				details_data.put("id_seksyen",checkIsNull(rs.getString("id_seksyen")));
				details_data.put("id_daerah",checkIsNull(rs.getString("id_daerah")));
				
				details_data.put("nama_pejabat",checkIsNull(rs.getString("nama_pejabat")));
				details_data.put("alamat1",checkIsNull(rs.getString("alamat1")));
				details_data.put("alamat2",checkIsNull(rs.getString("alamat2")));
				details_data.put("alamat3",checkIsNull(rs.getString("alamat3")));
				details_data.put("poskod",checkIsNull(rs.getString("poskod")));
				details_data.put("prefix",checkIsNull(rs.getString("prefix")));
				details_data.put("no_tel",checkIsNull(rs.getString("no_tel")));
				details_data.put("no_fax",checkIsNull(rs.getString("no_fax")));
				details_data.put("kod_jkptg",checkIsNull(rs.getString("kod_jkptg")));
				details_data.put("alamat",checkIsNull(rs.getString("nama_pejabat"))+"\n<br>"+
						checkIsNull(rs.getString("alamat1"))+"\n<br>"+
						checkIsNull(rs.getString("alamat2"))+"\n<br>"+
						checkIsNull(rs.getString("alamat3")));
				details_data.put("emel",checkIsNull(rs.getString("emel")));
			}	
			return details_data;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (db != null) db.close();
		}
		return details_data;
		
	}
	
	public Vector getListing(String id_seksyen,String id_negeri) 
	throws Exception{
			//Vector list = null;
			Db db = null;
			Vector lists = new Vector();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(getSQL(id_seksyen,id_negeri));
				Hashtable record = null;
				//new Hashtable();
				while(rs.next()) {
					record = new Hashtable();
					record.put("id_pejabatjkptg",rs.getString("id_pejabatjkptg"));
					record.put("nama_pejabat",checkIsNull(rs.getString("nama_pejabat")));
					record.put("negeri",getShortName(checkIsNull(rs.getString("negeri"))));
					record.put("daerah",checkIsNull(rs.getString("daerah")));
					record.put("seksyen",checkIsNull(rs.getString("seksyen")));
					record.put("alamat",checkIsNull(rs.getString("nama_pejabat"))+"\n"+
										checkIsNull(rs.getString("alamat1"))+"\n"+
										checkIsNull(rs.getString("alamat2"))+"\n"+
										checkIsNull(rs.getString("alamat3")));
					lists.addElement(record);
				}
				//list = DB.getPejabatJKPTGFilter(id_seksyen,id_negeri,id_daerah);
				//this.context.put("pejabatlists",list);
			} catch (Exception e) {
				
			}finally  {
				if (db != null) db.close();
			}
			return lists;
			
	}

	public Vector getListingDaerahJagaan(String id_pejabatjkptg) 
	throws Exception{
			//Vector list = null;
			Db db = null;
			Vector lists = new Vector();
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "select id_daerah,kod_daerah,nama_daerah from tblRujDaerah where id_daerah in ("+
					"select distinct id_daerahurus from TBLRUJPEJABATURUSAN where id_pejabatjkptg='" + id_pejabatjkptg + "') order by kod_daerah";
				myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable record = null;
				//new Hashtable();
				while(rs.next()) {
					record = new Hashtable();
					//record.put("id_pejabatjkptg",rs.getString("id_pejabatjkptg"));
					record.put("id_daerahurus",checkIsNull(rs.getString("id_daerah")));
					record.put("kod_daerahurus",checkIsNull(rs.getString("kod_daerah")));
					record.put("nama_daerahurus",checkIsNull(rs.getString("nama_daerah")));
					lists.addElement(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return lists;
			
	}
	
	public List<DaerahJagaanInformation> getListDaerahJagaan(String id_pejabatjkptg) 
			throws Exception{
			
			Converter cv = new Converter();
			List<DaerahJagaanInformation> result = new ArrayList<DaerahJagaanInformation>();
			Db db = null;
			DaerahJagaanInformation dji = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT UR.ID_PEJABATURUSAN,DA.ID_DAERAH, DA.KOD_DAERAH, DA.NAMA_DAERAH, UR.ID_PEJABATJKPTG " +
					" FROM TBLRUJPEJABATURUSAN UR, TBLRUJDAERAH DA " +
					" WHERE UR.ID_DAERAHURUS = DA.ID_DAERAH  " +
					" AND UR.ID_JENISPEJABAT=22 " +
					" AND UR.ID_PEJABATJKPTG = '"+id_pejabatjkptg+"' " +
					" ORDER BY ID_DAERAH";
				myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					dji = new DaerahJagaanInformation(
							cv.getStringValue(rs.getString("ID_PEJABATURUSAN")), 
							cv.getStringValue(rs.getString("ID_DAERAH")),
							cv.getStringValue(rs.getString("KOD_DAERAH")),
							cv.getStringValue(rs.getString("NAMA_DAERAH")),
							cv.getStringValue(rs.getString("ID_PEJABATJKPTG"))
							);

					result.add(dji);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return result;
			
	}
	
	public void deleteDaerahJagaan(String id_pejabatUrusan) 
	throws Exception{
			
			Converter cv = new Converter();
			List<DaerahJagaanInformation> result = new ArrayList<DaerahJagaanInformation>();
			Db db = null;
			DaerahJagaanInformation dji = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "DELETE FROM TBLRUJPEJABATURUSAN WHERE ID_PEJABATURUSAN = '"+id_pejabatUrusan+"'";
				myLogger.info(sql);
				stmt.executeUpdate(sql);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			
	}
	
	public void deleteDaerahPegawai(String id_unitpsk) 
	throws Exception{
			
			Converter cv = new Converter();
			List<DaerahJagaanInformation> result = new ArrayList<DaerahJagaanInformation>();
			Db db = null;
			DaerahJagaanInformation dji = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "DELETE FROM TBLPPKRUJUNIT WHERE ID_UNITPSK = '"+id_unitpsk+"'";
				myLogger.info(sql);
				stmt.executeUpdate(sql);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			
	}
	
	public void addDaerahPegawai(String kod_pegawai,String id_pejabat,String id_negeri, String nama_pejabat, String keterangan_unit, String nama_pegawai, String jawatan, String alamat1,
			String alamat2, String alamat3, String poskod, String no_tel, String no_tel_sam, String id_masuk, String id_bandar) 
	throws Exception{
			
			Converter cv = new Converter();
			//cv.getStringValue(txt);
			List<DaerahJagaanInformation> result = new ArrayList<DaerahJagaanInformation>();
			Db db = null;
	        try {		        	
	        	//long id_esdokumen = DB.getNextID("TBLESDOKUMEN_SEQ");	
	        	
	        		db = new Db();	
	        
	        		        	
	        Connection con = db.getConnection();
	        con.setAutoCommit(false);
			DaerahJagaanInformation dji = null;
			String sql="";
			
				Statement stmt = db.getStatement();
			/*	sql = "INSERT INTO TBLPPKRUJUNIT (KOD,ID_JKPTG, ID_NEGERI, NAMA_PEJABAT, KETERANGAN_UNIT_PSK, NAMA_PEGAWAI, " +
						"JAWATAN, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD, NO_TEL, NO_TEL_SAMBUNGAN, ID_MASUK, TARIKH_MASUK, ID_BANDAR, STATUS_PEG) " +
						"VALUES ('"+kod_pegawai+"','"+id_pejabat+"', '"+id_negeri+"', '"+nama_pejabat+"', '"+keterangan_unit+"', '"+nama_pegawai+"', " +
						"'"+jawatan+"', '"+alamat1+"', '"+alamat2+"', '"+alamat3+"', '"+poskod+"', '"+no_tel+"', " +
								"'"+no_tel_sam+"', '"+id_masuk+"', SYSDATE, '"+id_bandar+"', 1)";*/
				
				
				PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPPKRUJUNIT (KOD, ID_JKPTG, ID_NEGERI, NAMA_PEJABAT, " +
						" KETERANGAN_UNIT_PSK, NAMA_PEGAWAI, JAWATAN, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD, NO_TEL, NO_TEL_SAMBUNGAN, " +
						" ID_MASUK, TARIKH_MASUK, ID_BANDAR, STATUS_PEG) " +
						" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,'1') ");
				//myLogger.info(ps);
	        	ps.setString(1, kod_pegawai );
	        	ps.setString(2, id_pejabat );
	        	ps.setString(3,id_negeri );
	        	ps.setString(4,nama_pejabat ); 
	        	
	        	ps.setString(5, keterangan_unit );
	        	ps.setString(6, nama_pegawai );
	        	ps.setString(7, jawatan );
	        	ps.setString(8,alamat1 ); 
	        	
	        	ps.setString(9, alamat2 );
	        	ps.setString(10, alamat3 );
	        	ps.setString(11, poskod );
	        	ps.setString(12, no_tel ); 
	        	
	        	ps.setString(13, no_tel_sam );
	        	ps.setString(14, id_masuk );
	        	ps.setString(15, id_bandar );
	        	
	        	//myLogger.info(" 2 --- "+sql);
	        	
	        	ps.executeUpdate();
				
	        	con.commit(); 
				//myLogger.info(sql);
				//stmt.executeUpdate(sql);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			
	}
	
	public PejabatInformation getListingPejabat(String id_pejabatjkptg) 
	throws Exception{
			//Vector list = null;
			Converter cv = new Converter();
			Db db = null;

			List<PejabatInformation> result = new ArrayList<PejabatInformation>();
			PejabatInformation pi = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT PE.ID_PEJABATJKPTG, PE.NAMA_PEJABAT, PE.ALAMAT1, PE.ALAMAT2, PE.ALAMAT3, PE.POSKOD, PE.ID_BANDAR, PE.ID_NEGERI,  NE.NAMA_NEGERI " +
					 "FROM TBLRUJPEJABATJKPTG PE, TBLRUJNEGERI  NE WHERE PE.ID_NEGERI = NE.ID_NEGERI AND  ID_PEJABATJKPTG = '"+id_pejabatjkptg+"' ";
				
				myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()) {
					pi = new PejabatInformation(
							cv.getStringValue(rs.getString("ID_PEJABATJKPTG")),
							cv.getStringValue(rs.getString("NAMA_PEJABAT")), 
							cv.getStringValue(rs.getString("ALAMAT1")), 
							cv.getStringValue(rs.getString("ALAMAT2")),
							cv.getStringValue(rs.getString("ALAMAT3")), 
							cv.getStringValue(rs.getString("POSKOD")),
							cv.getStringValue(rs.getString("ID_BANDAR")), 
							cv.getStringValue(rs.getString("ID_NEGERI")),
							cv.getStringValue(rs.getString("NAMA_NEGERI")));
					result.add(pi);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return pi;
			
	}
	
	public PegawaiInfoDetail getPegawaiInfo(String id_unitpsk) 
	throws Exception{
			//Vector list = null;
			Converter cv = new Converter();
			Db db = null;

			PegawaiInfoDetail pi = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = " SELECT KOD,ID_JKPTG, ID_UNITPSK, KETERANGAN_UNIT_PSK, NAMA_PEGAWAI, JAWATAN,  NO_TEL, NO_TEL_SAMBUNGAN  FROM TBLPPKRUJUNIT WHERE ID_UNITPSK = '"+id_unitpsk+"' ";
				
				myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()) {
					
					pi = new PegawaiInfoDetail(
							cv.getStringValue(rs.getString("KOD")),
							cv.getStringValue(rs.getString("ID_JKPTG")),
							cv.getStringValue(rs.getString("ID_UNITPSK")),
							cv.getStringValue(rs.getString("KETERANGAN_UNIT_PSK")),
							cv.getStringValue(rs.getString("NAMA_PEGAWAI")),
							cv.getStringValue(rs.getString("JAWATAN")), 
							cv.getStringValue(rs.getString("NO_TEL")),
							cv.getStringValue(rs.getString("NO_TEL_SAMBUNGAN")));
										
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return pi;
			
	}
	
	public List<PejabatDaerahInformation> getPejabatUrusInfo(String id_negeri,String id_pejabat) 
	throws Exception{
			//Vector list = null;
			Converter cv = new Converter();
			Db db = null;

			List<PejabatDaerahInformation> result = new ArrayList<PejabatDaerahInformation>();
			PejabatDaerahInformation jdi = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT PE.ID_JENISPEJABAT, PE.ID_PEJABAT, PE.ID_NEGERI, PE.ID_DAERAH,  DA.NAMA_DAERAH " +
						"FROM TBLRUJPEJABAT PE, TBLRUJDAERAH DA WHERE PE.ID_NEGERI = '"+id_negeri+"' AND PE.ID_JENISPEJABAT = '2' " +
						"AND PE.ID_DAERAH = DA.ID_DAERAH "+
						"AND PE.ID_DAERAH NOT IN ( select id_daerahurus from " +
						"tblrujpejabaturusan where " +
						//AZAM ADD ON 8/9/2010
						"id_pejabatjkptg='"+id_pejabat+"')";
				
				//myLogger.debug("id pejabat:"+id_pejabat);
				//myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()) {
					
					jdi = new PejabatDaerahInformation(
							cv.getStringValue(rs.getString("ID_JENISPEJABAT")),
							cv.getStringValue(rs.getString("ID_PEJABAT")),
							cv.getStringValue(rs.getString("ID_NEGERI")), 
							cv.getStringValue(rs.getString("ID_DAERAH")),
							cv.getStringValue(rs.getString("NAMA_DAERAH")));
					result.add(jdi);			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return result;
			
	}
	
	public PejabatDaerahInformation getPejabatUrusInfoDetail(String id_negeri, String id_pejabat) 
	throws Exception{
			//Vector list = null;
			Converter cv = new Converter();
			Db db = null;

			List<PejabatDaerahInformation> result = new ArrayList<PejabatDaerahInformation>();
			PejabatDaerahInformation jdi = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT PE.ID_JENISPEJABAT, PE.ID_PEJABAT, PE.ID_NEGERI, PE.ID_DAERAH,  DA.NAMA_DAERAH " +
						"FROM TBLRUJPEJABAT PE, TBLRUJDAERAH DA WHERE PE.ID_NEGERI = '"+id_negeri+"' AND PE.ID_JENISPEJABAT = '2' " +
						"AND PE.ID_DAERAH = DA.ID_DAERAH AND PE.ID_PEJABAT = '"+id_pejabat+"'";
				
				myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()) {
					
					jdi = new PejabatDaerahInformation(
							cv.getStringValue(rs.getString("ID_JENISPEJABAT")),
							cv.getStringValue(rs.getString("ID_PEJABAT")),
							cv.getStringValue(rs.getString("ID_NEGERI")), 
							cv.getStringValue(rs.getString("ID_DAERAH")),
							cv.getStringValue(rs.getString("NAMA_DAERAH")));
					result.add(jdi);			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return jdi;
			
	}

	public void addDaerah(String pid_jenispejabat, String pid_pejabat, String pid_negeri, String pid_daerah, String uid_jenispejabat, String uid_pejabat,
			String uid_negeri, String uid_daerah, String pid_seksyen, String id_masuk) 
	throws Exception{
			//Vector list = null;
			Converter cv = new Converter();
			Db db = null;

			List<PejabatDaerahInformation> result = new ArrayList<PejabatDaerahInformation>();
			PejabatDaerahInformation jdi = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "INSERT INTO TBLRUJPEJABATURUSAN (ID_JENISPEJABAT, ID_PEJABATJKPTG, ID_NEGERI, ID_DAERAH, " +
						"ID_JENISPEJABATURUS, ID_PEJABATURUS, ID_NEGERIURUS, ID_DAERAHURUS, ID_SEKSYEN, ID_MASUK, TARIKH_MASUK) VALUES " +
						"('"+pid_jenispejabat+"', '"+pid_pejabat+"', '"+pid_negeri+"', '"+pid_daerah+"',  '"+uid_jenispejabat+"'," +
								" '"+uid_pejabat+"', '"+uid_negeri+"', '"+uid_daerah+"', '"+pid_seksyen+"', '"+id_masuk+"', SYSDATE) ";
				
				myLogger.info(sql);
				stmt.executeUpdate(sql);

			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			
	}
	
	public void updatePegawaiInfo(String id_unitpsk, String id_negeri, String id_jkptg, String nama_pegawai, String nama_pejabat, String keterangan, String jawatan, 
			String alamat1, String alamat2, String alamat3, String poskod, String no_tel, String no_tel_sam, String id_kemaskini) 
	throws Exception{
			
			Converter cv = new Converter();
			Db db = null;

			String sql="";
			try {
				db = new Db();
				StringBuffer sb = new StringBuffer();
				Statement stmt = db.getStatement();
				sb.append("UPDATE TBLPPKRUJUNIT SET ");
				//sql = "UPDATE TBLPPKRUJUNIT SET";
				if(!cv.getStringValue(id_negeri).equals("")){
					//System.out.println("ID_NEGERI " + id_negeri);
					sb.append("ID_NEGERI = '"+id_negeri+"', ");
				}
				if(!cv.getStringValue(id_jkptg).equals("")){
					//System.out.println("ID_JKPTG " +id_jkptg);
					sb.append("ID_JKPTG = '"+id_jkptg+"', ");
				}
				if(!cv.getStringValue(nama_pejabat).equals("")){
					//System.out.println("NAMA_PEJABAT " +nama_pejabat);
					sb.append("NAMA_PEJABAT= '"+nama_pejabat+"', ");
				}
				if(!cv.getStringValue(keterangan).equals("")){
					//System.out.println("KETERANGAN_UNIT_PSK " +keterangan);
					sb.append("KETERANGAN_UNIT_PSK= '"+keterangan+"',");
				}
				if(!cv.getStringValue(nama_pegawai).equals("")){
					//System.out.println("NAMA_PEGAWAI " +nama_pegawai);
					sb.append("NAMA_PEGAWAI= '"+nama_pegawai+"', ");
				}
				if(!cv.getStringValue(jawatan).equals("")){
					//System.out.println("JAWATAN " +jawatan);
					sb.append("JAWATAN= '"+jawatan+"', ");
				}
				if(!cv.getStringValue(alamat1).equals("")){
					//System.out.println("ALAMAT1 " +alamat1);
					sb.append("ALAMAT1= '"+alamat1+"', ");
				}
				if(!cv.getStringValue(alamat2).equals("")){
					//System.out.println("ALAMAT2 " +alamat2);
					sb.append("ALAMAT2= '"+alamat2+"', ");
				}
				if(!cv.getStringValue(alamat3).equals("")){
					//System.out.println("ALAMAT3 " +alamat3);
					sb.append("ALAMAT3= '"+alamat3+"', ");
				}
				if(!cv.getStringValue(poskod).equals("")){
					//System.out.println("POSKOD " +poskod);
					sb.append("POSKOD= '"+poskod+"', ");
				}
				if(!cv.getStringValue(no_tel).equals("")){
					//System.out.println("NO_TEL " +no_tel);
					sb.append("NO_TEL= '"+no_tel+"', ");
				}
				if(!cv.getStringValue(no_tel_sam).equals("")){
					//System.out.println("NO_TEL_SAMBUNGAN " +no_tel_sam);
					sb.append("NO_TEL_SAMBUNGAN= '"+no_tel_sam+"', ");
				}
				sb.append("ID_KEMASKINI = '"+id_kemaskini+"', TARIKH_KEMASKINI = SYSDATE WHERE ID_UNITPSK = '"+id_unitpsk+"'");

				myLogger.info(sql);
				sql = sb.toString();
				//
				stmt.executeUpdate(sql);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			
	}
	
	/**
	 * ADD NEW FOR PEGAWAI BASED ON PEJABAT ID
	 * @param id_pejabatjkptg
	 * @return {@link List} {@link PegawaiInformation}
	 */
	public List<PegawaiInformation> getListingPegawai(String id_pejabatjkptg) 
	throws Exception{
			//Vector list = null;
			Converter cv = new Converter();
			Db db = null;

			List<PegawaiInformation> result = new ArrayList<PegawaiInformation>();
			PegawaiInformation pi = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT PR.ID_UNITPSK, PR.ID_JKPTG,  PR.NAMA_PEGAWAI, PR.JAWATAN " +
						"FROM TBLPPKRUJUNIT PR, TBLRUJPEJABATJKPTG PE " +
						"WHERE PR.ID_JKPTG = PE.ID_PEJABATJKPTG " +
						//" AND PR.STATUS_PEG = '1' " +
						" AND ID_JKPTG = '"+id_pejabatjkptg+"' ORDER BY PR.ID_UNITPSK";
				myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()) {
					pi = new PegawaiInformation(
							cv.getStringValue(rs.getString("ID_UNITPSK")),
							cv.getStringValue(rs.getString("ID_JKPTG")),
							cv.getStringValue(rs.getString("NAMA_PEGAWAI")),
							cv.getStringValue(rs.getString("JAWATAN")));
					result.add(pi);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return result;
			
	}
	
	public PejabatInformation getPejabatInfo(String id_pejabatjkptg) 
	throws Exception{
			//Vector list = null;
			Converter cv = new Converter();
			Db db = null;

			PejabatInformation pi = null;
			String sql="";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "SELECT PE.ID_JENISPEJABAT, PE.ID_PEJABATJKPTG,  PE.NAMA_PEJABAT, PE.ALAMAT1, PE.ALAMAT2, PE.ALAMAT3, PE.POSKOD," +
						" PE.ID_NEGERI, NE.NAMA_NEGERI, PE.ID_DAERAH, DA.NAMA_DAERAH, PE.ID_SEKSYEN, SE.NAMA_SEKSYEN " +
						"FROM TBLRUJPEJABATJKPTG PE, TBLRUJSEKSYEN SE, TBLRUJNEGERI NE, TBLRUJDAERAH DA WHERE" +
						" PE.ID_SEKSYEN = SE.ID_SEKSYEN AND PE.ID_NEGERI = NE.ID_NEGERI " +
						"AND PE.ID_DAERAH = DA.ID_DAERAH AND ID_PEJABATJKPTG = '"+id_pejabatjkptg+"'";
				myLogger.info(sql);
				ResultSet rs = stmt.executeQuery(sql);

				while(rs.next()) {
					
					pi = new PejabatInformation(
							cv.getStringValue(rs.getString("ID_JENISPEJABAT")),
							cv.getStringValue(rs.getString("ID_PEJABATJKPTG")),
							cv.getStringValue(rs.getString("NAMA_PEJABAT")),
							cv.getStringValue(rs.getString("ALAMAT1")), 
							cv.getStringValue(rs.getString("ALAMAT2")), 
							cv.getStringValue(rs.getString("ALAMAT3")),
							cv.getStringValue(rs.getString("POSKOD")),
							cv.getStringValue(rs.getString("ID_NEGERI")),
							cv.getStringValue(rs.getString("NAMA_NEGERI")),
							cv.getStringValue(rs.getString("ID_DAERAH")),
							cv.getStringValue(rs.getString("NAMA_DAERAH")),
							cv.getStringValue(rs.getString("ID_SEKSYEN")),
							cv.getStringValue(rs.getString("NAMA_SEKSYEN"))
							);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return pi;
			
	}
	

	public String getSQL(String id_seksyen,String id_negeri) {
		String sqlWhere = "";
		String sql = "select id_pejabatjkptg, nama_pejabat, alamat1, alamat2, alamat3,"+ 
		"poskod, id_negeri, no_tel, no_fax," +
		"(SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI=A.ID_NEGERI) AS negeri," +
		"(SELECT NAMA_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH=A.ID_DAERAH) AS daerah, " +
		"(SELECT KOD_SEKSYEN FROM TBLRUJSEKSYEN WHERE ID_SEKSYEN=A.ID_SEKSYEN ) AS seksyen " +
	    "from tblrujpejabatjkptg A ";
		
		Hashtable parameters = new Hashtable();
		if (id_negeri != null && !"".equals(id_negeri)) {
			parameters.put("id_negeri", id_negeri);
		}
		if (id_seksyen != null && !"".equals(id_seksyen)) {
			parameters.put("id_seksyen", id_seksyen);
		}
		  int x = 1;
		  String name,value ;
		  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
		   {
			  name = (String)e.nextElement();
			  value = (String)parameters.get(name);
			  if (x==1) sqlWhere = " WHERE ";
			  sqlWhere = sqlWhere + name + "='"+ value + "'" + (x<parameters.size()?" AND ":"") ;
		   }
		myLogger.info(sql+sqlWhere);
		return sql+sqlWhere;
	}
	
	public String getNamaPejabatJKPTG(String id) {
		Db db = null;
		String sql="",nama="";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "Select nama_pejabat From TBLRUJPEJABATJKPTG WHERE id_pejabatjkptg='"+id+"' "; 
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				nama = checkIsNull(rs.getString("nama_pejabat"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally  {
			if (db != null) db.close();
		}
		return nama;
	}
	
	public String getAlamatPejabatJKPTG(String id) {
		Db db = null;
		String sql="",alamat="";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "Select alamat1,alamat2,alamat3,poskod From TBLRUJPEJABATJKPTG WHERE id_pejabatjkptg='"+id+"' "; 
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				alamat = checkIsNull(rs.getString("alamat1"))+"\n"+
						checkIsNull(rs.getString("alamat2"))+"\n"+
						checkIsNull(rs.getString("alamat3"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally  {
			if (db != null) db.close();
		}
		return alamat;
	}
	///////////////////////////////
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	
	public String getShortName(String nicename) {
		nicename = nicename.replace("WILAYAH PERSEKUTUAN","");
		return nicename;
	}
	
}

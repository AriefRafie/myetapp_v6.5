package ekptg.model.ppk.online;


import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class StatusPermohonanFacade implements IStatusPermohonan{
	private static Logger myLogger = Logger.getLogger(StatusPermohonanFacade.class);
	@Override
	public Vector<StatusPermohonanPPK> getPermohonanList(String noKP){
		String SQL ="SELECT permohonan.TARIKH_MOHON,permohonan.SEKSYEN,fail.NO_FAIL,status.KETERANGAN FROM "
			+"TBLPPKPERMOHONAN permohonan, TBLPPKPEMOHON pemohon,TBLPFDFAIL fail,tblrujsuburusanstatusfail sf,tblrujsuburusanstatus sus,tblrujstatus status "
			+"WHERE sf.id_fail = fail.id_fail AND permohonan.id_permohonan = sf.id_permohonan AND sus.id_status = status.id_status AND sus.id_suburusanstatus = sf.id_suburusanstatus "
			+"AND permohonan.ID_PEMOHON = pemohon.ID_PEMOHON AND fail.ID_FAIL = permohonan.ID_FAIL AND pemohon.NO_KP_BARU='"+noKP+"' AND permohonan.NO_PERMOHONAN_ONLINE IS NULL AND sf.aktif ='1'";
		
		Db db = null;
		Vector<StatusPermohonanPPK> v = new Vector<StatusPermohonanPPK>();
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stat = db.getStatement();
			ResultSet rs = stat.executeQuery(SQL);
			while(rs.next()){
				
				StatusPermohonanPPK status = new StatusPermohonanPPK();
				
				status.setNoFail(rs.getString("NO_FAIL"));
				status.setSeksyen(rs.getString("SEKSYEN"));
				status.setStatus(rs.getString("KETERANGAN"));
				
				Date tarikhPohon = rs.getDate("TARIKH_MOHON");
				if(tarikhPohon != null)
					status.setTarikhMohon(formatter.format(tarikhPohon));
				v.addElement(status);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return v;
	}

	@Override
	public Vector<StatusPermohonanPPK> getPermohonanSiMatiList(String noKPSiMati) {
		String SQL ="SELECT F.NO_FAIL,S.NAMA_SIMATI,P.SEKSYEN,ST.KETERANGAN AS CURRENT_STATUS,P.TARIKH_MOHON FROM TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P,TBLPPKSIMATI S,TBLRUJSTATUS ST,TBLPFDFAIL F "
						+"WHERE PSM.ID_SIMATI = S.ID_SIMATI AND F.ID_FAIL = P.ID_FAIL "
						+"AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN "
						+"AND P.ID_STATUS = ST.ID_STATUS "
						+"AND S.NO_KP_BARU = '"+noKPSiMati+"'";

		
		Db db = null;
		Vector<StatusPermohonanPPK> v = new Vector<StatusPermohonanPPK>();
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		try{
			db = new Db();
			Statement stat = db.getStatement();
			System.out.println(SQL );
			ResultSet rs = stat.executeQuery(SQL);
			while(rs.next()){
				
				StatusPermohonanPPK status = new StatusPermohonanPPK();
				
				status.setNoFail(rs.getString("NO_FAIL"));
				status.setSeksyen(rs.getString("SEKSYEN"));
				status.setStatus(rs.getString("CURRENT_STATUS"));
				
				Date tarikhPohon = rs.getDate("TARIKH_MOHON");
				if(tarikhPohon != null)
					status.setTarikhMohon(formatter.format(tarikhPohon));
				v.addElement(status);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return v;
	}

	@Override
	public boolean isSeksyen17Selesai(String kpbarusimati, String kplamasimati,String kplainsimati, String noPermohonansimati, String userid) {
		Db db = null;
		String kpBaru = kpbarusimati.trim();
		String kpLama = kplamasimati.trim();
		String kpLain = kplainsimati.trim();
		String noPermohonan = noPermohonansimati.trim();
		try{
			db = new Db();
			String sql =" SELECT P.ID_PERMOHONAN,P.NO_SUBJAKET,P.ID_STATUS " +
					"FROM TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P,TBLPPKSIMATI S,TBLRUJSTATUS ST,TBLPFDFAIL F " +
					"WHERE PSM.ID_SIMATI = S.ID_SIMATI AND F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND P.ID_STATUS = ST.ID_STATUS AND P.SEKSYEN=17 ";
			
			String sqlwhere = "";
			
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(S.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(S.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(S.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
					}
					
				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(S.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(S.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (noPermohonan != "") {
				if (!noPermohonan.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_PERMOHONAN_ONLINE) = '" + noPermohonan.toUpperCase() + "'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_PERMOHONAN_ONLINE) = '" + noPermohonan.toUpperCase() + "'";
					}
				}
			}
			
			if (sqlwhere != "") {
				sql = sql + " AND ( "+sqlwhere+" )";
			} 
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				String status = rs.getString("ID_STATUS");
				myLogger.info("status "+status);
				if(status.equals("21")){
					return true;
				}else if(status.equals("160")){
					System.out.println("seksyen 17 baru pohon");
					myLogger.info("seksyen 17 baru pohon");
					return true;
				}
				else{
					System.out.println("seksyen 17 belum selesai");
					myLogger.info("seksyen 17 belum selesai");
					return false;
				}
				
			}else{
				System.out.println("tiada seksyen 17");
				myLogger.info("tiada seksyen 17");
				return true;
			}
		}
		catch(Exception e){
			myLogger.error(e.getMessage());
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return true;
	}
	
	@Override
	public Vector getSeksyen17Permohonan(String kpbarusimati, String kplamasimati,String kplainsimati, String noPermohonansimati, String userid) {
		Db db = null;
		String kpBaru = kpbarusimati.trim();
		String kpLama = kplamasimati.trim();
		String kpLain = kplainsimati.trim();
		String noPermohonan = noPermohonansimati.trim();
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		Vector v = new Vector();
		try{
			db = new Db();
			String sql =" SELECT P.no_permohonan_online,P.id_pemohon,P.TARIKH_MASUK,ST.KETERANGAN,P.TARIKH_MOHON " +
					"FROM TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN P,TBLPPKSIMATI S,TBLRUJSTATUS ST,TBLPFDFAIL F " +
					"WHERE PSM.ID_SIMATI = S.ID_SIMATI AND F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND P.ID_STATUS = ST.ID_STATUS AND P.SEKSYEN=17 ";
			
			String sqlwhere = "";
			
			if (kpBaru != "") {
				if (!kpBaru.trim().equals("")) {
					sqlwhere = sqlwhere + " UPPER(S.NO_KP_BARU) LIKE '%" + kpBaru.toUpperCase() + "%'";
				}
			}
			if (kpLama != "") {
				if (!kpLama.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(S.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(S.NO_KP_LAMA) LIKE '%" + kpLama.toUpperCase() + "%'";
					}
					
				}
			}
			if (kpLain != "") {
				if (!kpLain.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(S.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
					} else {
						sqlwhere = sqlwhere + " UPPER(S.NO_KP_LAIN) LIKE '%" + kpLain.toUpperCase() + "%'";
					}
				}
			}
			if (noPermohonan != "") {
				if (!noPermohonan.trim().equals("")) {
					if (sqlwhere != "") {
						sqlwhere = sqlwhere + " OR UPPER(P.NO_PERMOHONAN_ONLINE) = '" + noPermohonan.toUpperCase() + "'";
					} else {
						sqlwhere = sqlwhere + " UPPER(P.NO_PERMOHONAN_ONLINE) = '" + noPermohonan.toUpperCase() + "'";
					}
				}
			}
			
			if (sqlwhere != "") {
				sql = sql + " AND ( "+sqlwhere+" )";
			} 
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				StatusPermohonanPPK status = new StatusPermohonanPPK();
				status.setNoFail(rs.getString("no_permohonan_online"));
				status.setStatus(rs.getString("KETERANGAN"));
				Date tarikhPohon = rs.getDate("TARIKH_MOHON");
				if(tarikhPohon != null)
					status.setTarikhMohon(formatter.format(tarikhPohon));
				v.addElement(status);
				
			}
		}catch(Exception e){
				myLogger.error(e.getMessage());
				e.printStackTrace();
		}
		finally{
				db.close();
		}
		System.out.println(v.size());
		return v;
	}
}

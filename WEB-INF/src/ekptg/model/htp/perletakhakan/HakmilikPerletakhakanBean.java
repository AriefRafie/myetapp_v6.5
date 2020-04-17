package ekptg.model.htp.perletakhakan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikAgensi;
import ekptg.model.htp.entity.HakmilikCukai;
import ekptg.model.htp.rekod.FrmRekodUtilData;

public class HakmilikPerletakhakanBean implements IHakmilikPerletakhakan {
	
	private IHakmilikPerletakhakan iHakmilik = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.perletakhakan.HakmilikPerletakhakanBean.class);
	
	private HakMilik copyToRecord(HakMilik hakmilik){
		Db db = null;
		Connection conn = null;
		String sql =null;
		try{
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			 long idHakmilikBaru = DB.getNextID(db,"TBLHTPHAKMILIKURUSAN_SEQ"); 
			 hakmilik.setIdHakmilik(idHakmilikBaru);
			 r.add("ID_HAKMILIKURUSAN",hakmilik.getIdHakmilik());
			 r.add("ID_PERMOHONAN",hakmilik.getIdPermohonan());
			 r.add("ID_LUAS",hakmilik.getIdLuas());
			 r.add("PEGANGAN_HAKMILIK",hakmilik.getPeganganHakmilik());
			 r.add("ID_SUBKATEGORI",hakmilik.getIdSubkategori());
			 r.add("ID_KATEGORI",hakmilik.getIdKategori());
			 r.add("ID_NEGERI",hakmilik.getIdNegeri());
			 r.add("ID_DAERAH",hakmilik.getIdDaerah());
			 r.add("ID_MUKIM",hakmilik.getIdMukim());
			 r.add("NO_LOT",hakmilik.getNoLot());
			 r.add("ID_LOT", hakmilik.getIdJenisLot());			 
			 r.add("LUAS", hakmilik.getLuasString());			 
			 r.add("ID_JENISHAKMILIK",hakmilik.getIdJenisHakmilik());
			 r.add("NO_HAKMILIK",hakmilik.getNoHakmlik());			
			 r.add("CUKAI_TERKINI",hakmilik.getHakmilikCukai().getCukaiTerkini());			
			 r.add("NO_PELAN",hakmilik.getNoPelan());			 
			 r.add("NO_SYIT",hakmilik.getNoSyit());
			 r.add("LOKASI",hakmilik.getLokasi());			 
			 r.add("LUAS_BERSAMAAN",hakmilik.getLuasBersamaan());
			 r.add("SEKATAN",hakmilik.getSekatan());	
			 r.add("SYARAT",hakmilik.getSyarat());	
	 
			 r.add("ID_LUAS_BERSAMAAN","2");
		 	 r.add("ID_MASUK",hakmilik.getIdMasuk());

			 sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
			 myLog.info(sql);
			 stmt.executeUpdate(sql);
			 conn.commit();
			
		}catch(Exception e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return hakmilik;
	}

	@Override
	public Vector<HakMilik> transferRecord(String idPermohonan) {
		Db db = null;
		Vector<HakMilik> v = new Vector<HakMilik>();
		try{
			db = new Db();
			FrmRekodUtilData frmRekodUtilData = FrmRekodUtilData.getInstance();
			Statement stmt = db.getStatement();
			String sql ="SELECT A.ID_PERMOHONAN,A.ID_NEGERI,A.ID_DAERAH,A.ID_MUKIM" +
					" ,A.ID_JENISHAKMILIK,A.NO_HAKMILIK" +
					" ,A.ID_LOT,A.NO_LOT" +
					" ,A.ID_LUAS,A.LUAS,A.ID_LUAS_BERSAMAAN,A.LUAS_BERSAMAAN " +
					" ,A.PEGANGAN_HAKMILIK,A.ID_SUBKATEGORI,A.ID_KATEGORI" +
					//",B.ID_AGENSI
					" ,A.CUKAI_TERKINI,A.NO_PELAN,A.NO_SYIT,A.LOKASI,A.SEKATAN,A.SYARAT " +
					//" ,B.NO_RUJUKAN_KJP,B.NO_RUJUKAN_PTG,B.NO_RUJUKAN_PTD,F.NO_FAIL " +
					" FROM TBLHTPHAKMILIK A,tblpfdfail f,tblpermohonan p " +
					" WHERE " +
					" p.id_fail=f.id_fail AND p.id_permohonan = A.id_permohonan AND F.id_urusan=5 " +
					" AND " +
					" A.ID_PERMOHONAN NOT IN" +
//					"( "+
//					" SELECT "+
//					" DISTINCT(TPH.ID_PERMOHONAN),F.NO_FAIL "+
//					" FROM tblhtphakmilik tph,tblpfdfail f,tblpermohonan p "+
//					" where  "+
//					" p.id_fail=f.id_fail AND p.id_permohonan = tph.id_permohonan AND id_urusan=5 "+
//					" AND id_urusan=5 "+
//					" AND TPH.ID_PERMOHONAN NOT IN "+
//					" ( "+
					"     (   select HU.ID_PERMOHONAN  "+
					"         from tblhtphakmilikurusan hu "+
					"         where hu.id_permohonan in  "+
					"        ( "+
					"            select p.id_permohonan  "+
					"             from tblpfdfail f,tblpermohonan p "+
					"             where p.id_fail=f.id_fail  "+
					"             AND id_urusan=5  "+
					"         ) "+
					"         group by HU.ID_PERMOHONAN "+    
					"     ) "+
//					" ) "+
//					" GROUP BY TPH.ID_PERMOHONAN,F.NO_FAIL "+
//					")  " +
					//" AND B.ID_PERMOHONAN = P.ID_PERMOHONAN " +
					//" AND P.ID_FAIL = F.ID_FAIL " +
					"";
			myLog.info("sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				HakMilik temp = new HakMilik();
				HakmilikAgensi agensi = new HakmilikAgensi();
				HakmilikCukai cukai = new HakmilikCukai();
				
				temp.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				temp.setIdLuas(rs.getLong("ID_LUAS"));
				temp.setPeganganHakmilik(rs.getString("PEGANGAN_HAKMILIK"));
				temp.setIdSubkategori(rs.getLong("ID_SUBKATEGORI"));
				temp.setIdKategori(rs.getLong("ID_KATEGORI"));
				temp.setIdNegeri(rs.getLong("ID_NEGERI"));
				temp.setIdDaerah(rs.getLong("ID_DAERAH"));
				temp.setIdMukim(rs.getLong("ID_MUKIM"));
				temp.setIdJenisLot(rs.getLong("ID_LOT"));
				temp.setNoLot(rs.getString("NO_LOT"));				
				temp.setLuasString(Utils.isNull(rs.getString("LUAS")));
				temp.setIdJenisHakmilik(rs.getLong("ID_JENISHAKMILIK"));
				temp.setNoHakmlik(rs.getString("NO_HAKMILIK"));
				temp.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				cukai.setCukaiTerkini(rs.getDouble("CUKAI_TERKINI"));
				temp.setHakmilikCukai(cukai);
				temp.setNoPelan(Utils.isNull(rs.getString("NO_PELAN")));
				temp.setNoSyit(Utils.isNull(rs.getString("NO_SYIT")));
				temp.setLokasi(Utils.isNull(rs.getString("LOKASI")));
				temp.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				temp.setSekatan(rs.getString("SEKATAN"));
				temp.setSyarat(rs.getString("SYARAT"));
				 
				v.addElement(temp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		//Insert into TBLHTPHAKMILIK
		doProcess(v);
		return v;
		
	}
	
	private void doProcess(Vector<HakMilik> v){
		if(v != null && !v.isEmpty()){
			for(HakMilik temp : v){
				copyToRecord(temp);
			}
		}
	}
	
	}

package ekptg.intergration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.intergration.entity.BorangK;
import ekptg.intergration.entity.HTPBorangK;
import ekptg.model.htp.rekod.FrmHakmilikRizabBorangKBean;
import ekptg.model.htp.rekod.IHakmilikRizab;

public class BorangKIntergrationBean implements IBorangKIntergration {
	private IHakmilikRizab iHakmilikRizab = null;
	private static Logger myLog = Logger.getLogger(ekptg.intergration.BorangKIntergrationBean.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private Db db = null;
	private Connection conn = null;		
	private Vector<BorangK> v = null;
	private BorangK borang = null;

	@Override
	public Vector<BorangK> getAvailableList(String noFailPPT)  {
		v = new Vector<BorangK>();
		try{
			db = new Db();
			db.getConnection();
			Statement stmt = db.getStatement();
			String sql = "SELECT e.tarikh_hantar_htp, a.no_fail, a.id_fail, a.id_kementerian, b.id_permohonan, b.id_agensi, "+
					" c.tarikh_borangk, e.id_hakmilik, e.no_hakmilik, e.id_lot, e.no_lot, "+
					" e.id_unitluaslot_convert id_luas, e.luas_lot, e.id_daerah, "+
					" e.id_mukim, e.id_negeri, e.id_jenishakmilik, e.flag_endosan, "+
					" c.id_borangk "+
					" FROM tblpfdfail a, "+
					" tblpptpermohonan b, "+
					" tblpptborangk c, "+
					" tblppthakmilikborangk d, "+
					" tblppthakmilik e "+
					" WHERE a.id_fail = b.id_fail "+
					" AND c.id_permohonan = b.id_permohonan "+
					" AND d.id_borangk = c.id_borangk "+
					" AND e.id_hakmilik = d.id_hakmilik "+
					" AND e.flag_endosan IS NOT NULL "+
					" AND nvl(e.flag_hantar_htp,'0') = '1' ";
			
			if(noFailPPT != null && noFailPPT != ""){
				sql +=" AND UPPER(A.NO_FAIL) LIKE '%"+noFailPPT.toUpperCase().trim()+"%'";
			}
			
			
			ResultSet rs = stmt.executeQuery(sql);			
			while(rs.next()){
				BorangK borang = new BorangK();
				//borang.setIdBorangK(rs.getString("ID_BORANGK"));
				borang.setIdHakmilik(rs.getString("ID_HAKMILIK"));
				borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				//borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK"));
				borang.setNoLot(rs.getString("NO_LOT"));
				borang.setNoFail(rs.getString("NO_FAIL"));
				//borang.setNoWarta(rs.getString("NO_WARTA"));
				borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK")==null?"":Format.format(rs.getDate("TARIKH_BORANGK")).toString());
				borang.setTarikhTerimaBorangK(rs.getString("tarikh_hantar_htp")==null?"":Format.format(rs.getDate("tarikh_hantar_htp")).toString());
				v.addElement(borang);
			}
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)
				db.close();
		}
		return v;
	
	}
	
	@Override
	public Vector<BorangK> getAvailableList(String noFailPPT, String idKementerian, String noLot) {
		v = new Vector<BorangK>();
		try{
			db = new Db();
			db.getConnection();
			Statement stmt = db.getStatement();
			
			
			String sql = "SELECT e.tarikh_hantar_htp, a.no_fail, a.id_fail, a.id_kementerian, b.id_permohonan, b.id_agensi, "+
					" c.tarikh_borangk, e.id_hakmilik, e.no_hakmilik, e.id_lot, e.no_lot, "+
					" e.id_unitluaslot_convert id_luas, e.luas_lot, e.id_daerah, "+
					" e.id_mukim, e.id_negeri, e.id_jenishakmilik, e.flag_endosan, "+
					" c.id_borangk "+
					" FROM tblpfdfail a, "+
					" tblpptpermohonan b, "+
					" tblpptborangk c, "+
					" tblppthakmilikborangk d, "+
					" tblppthakmilik e "+
					" WHERE a.id_fail = b.id_fail "+
					" AND c.id_permohonan = b.id_permohonan "+
					" AND d.id_borangk = c.id_borangk "+
					" AND e.id_hakmilik = d.id_hakmilik "+
					" AND e.flag_endosan IS NOT NULL "+
					" AND nvl(e.flag_hantar_htp,'0') = '1' ";
			
			//		" AND a.no_fail = 'JKPTG(S).SEL/06/881/12/2012/46' ";
			/*String sql = "" +
					" SELECT ID_HAKMILIK,NO_HAKMILIK,NO_LOT " +
					" ,TO_CHAR(TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK " +
					" ,NO_FAIL,TARIKH_DAFTAR "+
			 		" FROM VIEWBORANGKHTP " +
			 		" WHERE NO_FAIL LIKE '%"+noFailPPT+"%'" +
			 		""; */
			
			if(noFailPPT != null && noFailPPT != ""){
				sql +=" AND UPPER(A.NO_FAIL) LIKE '%"+noFailPPT.toUpperCase().trim()+"%'";
			}
			
			if(idKementerian != null){
				sql +=" AND A.ID_KEMENTERIAN = '"+idKementerian+"'";
			}
			
			if(!noLot.equals("") ){
				sql +=" AND E.NO_LOT LIKE '%"+noLot.toUpperCase().trim()+"%'";
			}			
			
			sql += " ORDER BY c.tarikh_borangk DESC ";
			
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);			
			while(rs.next()){
				BorangK borang = new BorangK();
				//borang.setIdBorangK(rs.getString("ID_BORANGK"));
				borang.setIdHakmilik(rs.getString("ID_HAKMILIK"));
				borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				//borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK"));
				borang.setNoLot(rs.getString("NO_LOT"));
				borang.setNoFail(rs.getString("NO_FAIL"));
				//borang.setNoWarta(rs.getString("NO_WARTA"));
				borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK")==null?"":Format.format(rs.getDate("TARIKH_BORANGK")).toString());
				borang.setTarikhTerimaBorangK(rs.getString("tarikh_hantar_htp")==null?"":Format.format(rs.getDate("tarikh_hantar_htp")).toString());
				v.addElement(borang);
			}
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)
				db.close();
		}
		return v;
	
	}


	@Override
	public void simpanHTPBorangK(String idHakmilikPPT,String idPermohonanHTP,String idUser) throws Exception{ 
		Hashtable<String, String> h = null;
		String sql = "";
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			h = (Hashtable<String, String>)getIHakmilikRizab().getCarianHakmilikRizab(idHakmilikPPT);
			if(h!=null){
				borang = new BorangK();
				borang.setNoFail(Utils.isNull(String.valueOf(h.get("noFail"))));
				borang.setIdHakmilik(String.valueOf(h.get("idHakmilik")));
				borang.setNoHakmilik(Utils.isNull(String.valueOf(h.get("noHakmilik"))));
				borang.setIdJenisHakmilik(String.valueOf(h.get("idJenisHakmilik"))==null?"0"
						:String.valueOf(h.get("idJenisHakmilik")));
				if(!String.valueOf(h.get("noLot")).equals("")){
					borang.setIdLot("1");
					borang.setNoLot(Utils.isNull(getNoLot(String.valueOf(h.get("noLot")))));

				}else{
					borang.setIdLot(String.valueOf(h.get("idLot")));
					borang.setNoLot(Utils.isNull(String.valueOf(h.get("noPT"))));
					
				}
				
				borang.setIdNegeri(String.valueOf(h.get("idNegeri")));
				borang.setIdDaerah(String.valueOf(h.get("idDaerah")));
				borang.setIdMukim(String.valueOf(h.get("idMukim")));
				borang.setIdBorangK(String.valueOf(h.get("idBorangK")));
				borang.setTarikhBorangK(Utils.isNull(String.valueOf(h.get("idTarikhBorangK"))));
				borang.setIdLuas(String.valueOf(h.get("idLuas")).equals("")?"0":String.valueOf(h.get("idLuas")));
				borang.setLuas(String.valueOf(h.get("Luas")).equals("")?Double.parseDouble("0"):
					Double.parseDouble(String.valueOf(h.get("Luas"))));
				
			}
			
			if(borang != null){
				SQLRenderer r = new SQLRenderer();			  
				  //r.add("id_hakmilikurusan", idHakmilikUrusan);
				long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
				r.add("ID_HAKMILIKURUSAN",idHakmilikurusan);
				r.add("ID_PERMOHONAN", idPermohonanHTP);
				r.add("ID_NEGERI",borang.getIdNegeri());
				r.add("ID_DAERAH",borang.getIdDaerah());
				r.add("ID_MUKIM",borang.getIdMukim());
				r.add("NO_HAKMILIK",borang.getNoHakmilik());				
				r.add("ID_JENISHAKMILIK",borang.getIdJenisHakmilik());
				r.add("ID_LOT",borang.getIdLot());
				r.add("NO_LOT",borang.getNoLot());				  
				r.add("ID_LUAS",borang.getIdLuas());			  
				r.add("LUAS",borang.getLuas());			  
				r.add("ID_LUAS_BERSAMAAN",borang.getIdLuas());
				r.add("LUAS_BERSAMAAN",borang.getLuas());
				r.add("ID_MASUK", idUser);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("ID_KEMASKINI",idUser);
			    r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				  //r.add("pegangan_hakmilik",peganganHakmilik);
				  //r.add("id_subkategori",idsubketegori);
				  //r.add("id_kategori",idketegori);
				sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
			    
			    stmt.executeUpdate(sql);
			      
			    r = new SQLRenderer();
			    r.add("ID_MASUK", idUser);
			    r.add("ID_PERMOHONAN", idPermohonanHTP);
			    r.add("ID_BORANGK",borang.getIdBorangK());
			    r.add("ID_HAKMILIK",idHakmilikurusan );
			    r.add("TARIKH_MASUK",r.unquote("sysdate"));
			    r.add("ID_KEMASKINI", idUser);
			    r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			    sql = r.getSQLInsert("TBLHTPINFOBORANGK");
			    
			    stmt.executeUpdate(sql);
			    
			}
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("MASALAH PENYIMPANAN DATA, SILA SEMAK METHOD simpanHTPBorangK(ekptg.intergration.IBorangKIntergration)");
		
		}finally{
			if(db != null)
				db.close();
		}
		
	}
	
	@Override
	public void simpanBorangK(String idHakmilikPPT,String idPermohonanHTP,String idUser) throws Exception{ 
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			String sql ="SELECT * FROM  VIEWBORANGKHTP WHERE ID_HAKMILIK="+idHakmilikPPT;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				borang = new BorangK();
				borang.setIdHakmilik(rs.getString("ID_HAKMILIK"));
				borang.setNoHakmilik(Utils.isNull(rs.getString("NO_HAKMILIK")));
				borang.setTarikhBorangK(Utils.isNull(rs.getString("TARIKH_BORANGK")));
				borang.setNoLot(Utils.isNull(rs.getString("NO_LOT")));
				borang.setIdLot(rs.getString("ID_LOT")==null?"0":rs.getString("ID_LOT"));
				borang.setNoFail(Utils.isNull(rs.getString("NO_FAIL")));
				borang.setIdDaerah(rs.getString("ID_DAERAH"));
				borang.setIdMukim(rs.getString("ID_MUKIM"));
				borang.setIdNegeri(rs.getString("ID_NEGERI"));
				//borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				borang.setIdJenisHakmilik(rs.getString("ID_JENISHAKMILIK")==null?"0":rs.getString("ID_JENISHAKMILIK"));
				borang.setIdLuas(rs.getString("ID_LUAS")==null?"0":rs.getString("ID_LUAS"));
				borang.setLuas(rs.getString("LUAS_LOT")==null?Double.parseDouble("0"):rs.getDouble("LUAS_LOT"));
				borang.setIdBorangK(rs.getString("ID_BORANGK"));
				
			}
			
			if(borang != null){
				SQLRenderer r = new SQLRenderer();			  
				  //r.add("id_hakmilikurusan", idHakmilikUrusan);
				long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
				r.add("ID_HAKMILIKURUSAN",idHakmilikurusan);
				r.add("ID_PERMOHONAN", idPermohonanHTP);
				r.add("ID_NEGERI",borang.getIdNegeri());
				r.add("ID_DAERAH",borang.getIdDaerah());
				r.add("ID_MUKIM",borang.getIdMukim());
				r.add("ID_LOT",borang.getIdLot());
				r.add("NO_LOT",borang.getNoLot());				  
				r.add("LUAS",borang.getLuas());			  
				  //r.add("id_luas_bersamaan",kodluas);
				  //r.add("Luas_bersamaan",Luas);
				r.add("ID_MASUK", idUser);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("ID_KEMASKINI",idUser);
			    r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				  //r.add("pegangan_hakmilik",peganganHakmilik);
				  //r.add("id_subkategori",idsubketegori);
				  //r.add("id_kategori",idketegori);
				  //r.add("id_jenishakmilik",idjenishakmilik);
				sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
			    
			    stmt.executeUpdate(sql);
			      
			    r = new SQLRenderer();
			    r.add("ID_MASUK", idUser);
			    r.add("ID_PERMOHONAN", idPermohonanHTP);
			    r.add("ID_BORANGK",borang.getIdBorangK());
			    r.add("ID_HAKMILIK",idHakmilikurusan );
			    r.add("TARIKH_MASUK",r.unquote("sysdate"));
			    r.add("ID_KEMASKINI", idUser);
			    r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			    sql = r.getSQLInsert("TBLHTPINFOBORANGK");
			    
			    stmt.executeUpdate(sql);
			    
			}
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("MASALAH PENYIMPANAN DATA, SILA SEMAK METHOD simpanHTPBorangK(ekptg.intergration.IBorangKIntergration)");
		
		}finally{
			if(db != null)
				db.close();
		}
		
	}

	@Override
	public Vector<HTPBorangK> getHTPBorangKList(String idPermohonanHTP) {
		Vector<HTPBorangK> v = new Vector<HTPBorangK>();
		try{
			db = new Db();
			db.getConnection();
			Statement stmt = db.getStatement();
						
			String sql = "SELECT A.ID_BORANGK,to_char(B.TARIKH_BORANGK,'dd/MM/yyyy') AS TARIKH_BORANGK ,to_char(C.TARIKH_CATATAN,'dd/MM/yyyy') AS TARIKH_CATATAN,D.NO_LOT,D.NO_HAKMILIK "
				+"FROM TBLHTPINFOBORANGK A,TBLPPTBORANGK B,TBLPPTENDOSANBORANGK C,TBLHTPHAKMILIKURUSAN D "
				+"WHERE A.ID_BORANGK = B.ID_BORANGK AND C.ID_BORANGK = B.ID_BORANGK AND A.ID_HAKMILIK = D.ID_HAKMILIKURUSAN AND A.ID_PERMOHONAN ="+idPermohonanHTP;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				HTPBorangK k = new HTPBorangK();
				k.setIdPermohonan(idPermohonanHTP);
				k.setTarikhBorangK(rs.getString("TARIKH_BORANGK"));
				k.setTarikhBorangK(rs.getString("TARIKH_BORANGK")==null?"":Format.format(rs.getDate("TARIKH_BORANGK")).toString());
				k.setTarikhEndosan(rs.getString("TARIKH_CATATAN"));
				k.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				k.setNoLot(rs.getString("NO_LOT"));
				v.addElement(k);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			if(db != null)
				db.close();
		}
		return v;
		
	}

	@Override
	public Vector<BorangK> getAvailableList(String noFailPPT,String idKementerian) {
		v = new Vector<BorangK>();
			try{
				db = new Db();
				db.getConnection();
				Statement stmt = db.getStatement();
							
				String sql = "SELECT ID_HAKMILIK,NO_HAKMILIK,NO_LOT,TO_CHAR(TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK,NO_FAIL,TARIKH_DAFTAR ";
				sql = sql+" FROM VIEWBORANGKHTP WHERE " +
						" ID_KEMENTERIAN= '"+idKementerian+"'"+
						" AND NO_FAIL LIKE '%"+noFailPPT+"%'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					borang = new BorangK();
					//borang.setIdBorangK(rs.getString("ID_BORANGK"));
					borang.setIdHakmilik(rs.getString("ID_HAKMILIK"));
					borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
					borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK"));
					borang.setNoLot(rs.getString("NO_LOT"));
					borang.setNoFail(rs.getString("NO_FAIL"));
					//borang.setNoWarta(rs.getString("NO_WARTA"));
					borang.setTarikhDaftar(rs.getString("TARIKH_DAFTAR")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR")).toString());
					v.addElement(borang);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				if(db != null)
					db.close();
			}
			return v;
		}	
	
	 
	private IHakmilikRizab getIHakmilikRizab(){
		if (iHakmilikRizab == null){
			iHakmilikRizab = new FrmHakmilikRizabBorangKBean();
		}
		return iHakmilikRizab;
	}
	
	private String getNoLot(String str){
		String kod = "";
		if(str.contains("LOT")){
			kod = Utils.ltrim(str.substring(3,str.length()));			
    	}else{
    		kod = Utils.ltrim(str);
     	}
		return kod;
		
	}

	@Override
	public Vector<BorangK> getAvailableList() {
		v = new Vector<BorangK>();
		try{
			db = new Db();
			db.getConnection();
			Statement stmt = db.getStatement();
			String sql = "SELECT e.tarikh_hantar_htp, a.no_fail, a.id_fail, a.id_kementerian, b.id_permohonan, b.id_agensi, "+
					" c.tarikh_borangk, e.id_hakmilik, e.no_hakmilik, e.id_lot, e.no_lot, "+
					" e.id_unitluaslot_convert id_luas, e.luas_lot, e.id_daerah, "+
					" e.id_mukim, e.id_negeri, e.id_jenishakmilik, e.flag_endosan, "+
					" c.id_borangk "+
					" FROM tblpfdfail a, "+
					" tblpptpermohonan b, "+
					" tblpptborangk c, "+
					" tblppthakmilikborangk d, "+
					" tblppthakmilik e "+
					" WHERE a.id_fail = b.id_fail "+
					" AND c.id_permohonan = b.id_permohonan "+
					" AND d.id_borangk = c.id_borangk "+
					" AND e.id_hakmilik = d.id_hakmilik "+
					" AND e.flag_endosan IS NOT NULL "+
					" AND nvl(e.flag_hantar_htp,'0') = '1' ";

			System.out.println("sql no params : "+sql);
			ResultSet rs = stmt.executeQuery(sql);			
			while(rs.next()){
				borang = new BorangK();
				//borang.setIdBorangK(rs.getString("ID_BORANGK"));
				borang.setIdHakmilik(rs.getString("ID_HAKMILIK"));
				borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				//borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK"));
				borang.setNoLot(rs.getString("NO_LOT"));
				borang.setNoFail(rs.getString("NO_FAIL"));
				//borang.setNoWarta(rs.getString("NO_WARTA"));
				borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK")==null?"":Format.format(rs.getDate("TARIKH_BORANGK")).toString());
				borang.setTarikhTerimaBorangK(rs.getString("tarikh_hantar_htp")==null?"":Format.format(rs.getDate("tarikh_hantar_htp")).toString());
				v.addElement(borang);
			}
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			if(db != null)
				db.close();
		}
		return v;
	}
	
	
}

package ekptg.model.htp.pembelian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.HtpPerjanjian;
import ekptg.model.htp.entity.PerjanjianPembelian;
import ekptg.model.htp.entity.PerjanjianPindahMilik;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.UploadPerjanjian;

public class PerjanjianBean implements IPerjanjianPembelian {

	//@Override
	public PerjanjianPembelian getPerjanjianByPermohonan(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		PerjanjianPembelian perjanjian = null;
		HtpPerjanjian htpPerjanjian = null;
		try{
			db = new Db();
            conn = db.getConnection();
            Statement stmt = db.getStatement();
            
            r = new SQLRenderer();
            r.add("A.id_perjanjian");
            r.add("A.id_permohonan");
            r.add("A.no_rujperjanjian");
            r.add("TO_CHAR(A.tarikh_perjanjian,'DD/MM/YYYY') AS tarikh_perjanjian");    
            r.add("A.tarikh_tandatangan"); 
            r.add("B.id_perjanjianpembelian");
            r.add("B.nilai_tanah");
            r.add("B.harga_beli");
            r.add("B.bil_hakmilikbeli");  
            r.add("B.tarikh_borang14a");
            r.add("B.harga_tambahan");
            r.add("B.nilai_bangunan");
            r.add("B.bil_unit_beli");
            r.add("B.id_perjanjian",r.unquote("A.id_perjanjian"));
            r.set("A.id_permohonan",idPermohonan);
            sql = r.getSQLSelect("TBLHTPPERJANJIAN A,TBLHTPPERJANJIANPEMBELIAN B ");
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
            	htpPerjanjian = new HtpPerjanjian();
            	perjanjian = new PerjanjianPembelian();
            	
            	htpPerjanjian.setIdPerjanjian(rs.getLong("id_perjanjian"));
            	htpPerjanjian.setNoRujukanPerjanjian(rs.getString("no_rujperjanjian"));
            	htpPerjanjian.setTarikhPerjanjian(rs.getString("tarikh_perjanjian"));
            	htpPerjanjian.setTarikhTandatangan(rs.getString("tarikh_tandatangan"));
            	
            	perjanjian.setIdPerjanjianPembelian(rs.getLong("id_perjanjianpembelian"));
            	perjanjian.setNilaiTanah(Utils.format2Decimal((rs.getDouble("nilai_tanah"))));
            	perjanjian.setHargaBeli(Utils.format2Decimal((rs.getDouble("harga_beli"))));
            	perjanjian.setBilHakmilikBeli(String.valueOf(rs.getInt("bil_hakmilikbeli")));
            	perjanjian.setTarikhBorang14A(rs.getString("tarikh_borang14a"));
            	perjanjian.setHargaTambahan(Utils.format2Decimal((rs.getDouble("harga_tambahan"))));
            	perjanjian.setNilaiBangunan(Utils.format2Decimal((rs.getDouble("nilai_bangunan"))));
            	perjanjian.setBilUnitBeli(String.valueOf(rs.getInt("bil_unit_beli")));
            	
            	perjanjian.setHtpPerjanjian(htpPerjanjian);
            	
            }
            
    		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return perjanjian;
	}

	//@Override
	public PerjanjianPembelian simpanPerjanjian(PerjanjianPembelian perjanjian) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            HtpPerjanjian htpPerjanjian = perjanjian.getHtpPerjanjian();
            
            String tarikhPerjanjian = htpPerjanjian.getTarikhPerjanjian();
            String tPerjanjian = "to_date('" + tarikhPerjanjian + "','dd/MM/yyyy')";
            String tarikhTandatangan = htpPerjanjian.getTarikhPerjanjian();
            String tTandatangan = "to_date('" + tarikhTandatangan + "','dd/MM/yyyy')";
            
            Date now = new Date();
    		SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
    		String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
            
            long idPerjanjian = DB.getNextID("TBLHTPPERJANJIAN_SEQ");
            htpPerjanjian.setIdPerjanjian(idPerjanjian);
            
            r = new SQLRenderer();
            r.add("id_perjanjian",htpPerjanjian.getIdPerjanjian());
            r.add("id_permohonan", htpPerjanjian.getPermohonan().getIdPermohonan());
            r.add("no_rujperjanjian", htpPerjanjian.getNoRujukanPerjanjian());
            if (tarikhPerjanjian !=null && !tarikhPerjanjian.equals("")){
                r.add("tarikh_perjanjian", r.unquote(tPerjanjian));
            }
            else{
                r.add("tarikh_perjanjian", tarikhPerjanjian);
            }

            if(tarikhTandatangan != null  && !tarikhTandatangan.equals("") ){
                r.add("tarikh_tandatangan", r.unquote(tTandatangan));
            }
            else{
                r.add("tarikh_tandatangan", tarikhTandatangan);
            } 
            
            //r.add("id_masuk",htpPerjanjian.getIdMasuk());
            //r.add("tarikh_masuk", r.unquote(today));
            //r.add("id_kemaskini",htpPerjanjian.getIdMasuk());
            r.add("tarikh_kemaskini", r.unquote("sysdate"));
            r.add("ID_NEGERI",0);
            sql = r.getSQLInsert("TBLHTPPERJANJIAN");
            
            stmt.executeUpdate(sql);
            
            
            long idPerjanjianPembelian = DB.getNextID("TBLHTPPERJANJIANPEMBELIAN_SEQ");
            perjanjian.setIdPerjanjianPembelian(idPerjanjianPembelian);
            String tarikhBorang14a = perjanjian.getTarikhBorang14A();
            String tBorang14a = "to_date('" + tarikhBorang14a + "','dd/MM/yyyy')";
            
            r = new SQLRenderer();
            
            r.add("id_perjanjianpembelian", perjanjian.getIdPerjanjianPembelian());
            r.add("id_perjanjian", htpPerjanjian.getIdPerjanjian());
            r.add("nilai_tanah", perjanjian.getNilaiTanah());
            r.add("harga_beli", perjanjian.getHargaBeli());
            r.add("bil_hakmilikbeli", perjanjian.getBilHakmilikBeli());
            
            if (tarikhBorang14a != null && !tarikhBorang14a.equals("")){
                r.add("tarikh_borang14a", r.unquote(tBorang14a) );
            }else{
                r.add("tarikh_borang14a", tarikhBorang14a);
            }
            
            r.add("harga_tambahan", perjanjian.getHargaTambahan());
            
            r.add("nilai_bangunan", perjanjian.getNilaiBangunan());
            r.add("bil_unit_beli", perjanjian.getBilUnitBeli());
           // r.add("id_masuk", perjanjian.getIdMasuk());
            r.add("tarikh_masuk", r.unquote("sysdate"));
            //r.add("id_kemaskini", perjanjian.getIdKemaskini());
            r.add("tarikh_kemaskini", r.unquote("sysdate"));

            sql = r.getSQLInsert("TBLHTPPERJANJIANPEMBELIAN");
            
            stmt.executeQuery(sql);
            
            conn.commit();
            
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return perjanjian;
	}
	
	public void hapusPerjanjian(String idDokumenPerjanjianAttach) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            sql ="DELETE FROM TBLHTPDOKUMENPERJANJIANATTACH WHERE ID_DOKUMENPERJANJIANATTACH ="+idDokumenPerjanjianAttach;
            stmt.executeQuery(sql);
            conn.commit();
            
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
	}

	//@Override
	public PerjanjianPembelian updatePerjanjian(PerjanjianPembelian perjanjian) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            HtpPerjanjian htpPerjanjian = perjanjian.getHtpPerjanjian();
            
            String tarikhPerjanjian = htpPerjanjian.getTarikhPerjanjian();
            String tPerjanjian = "to_date('" + tarikhPerjanjian + "','dd/MM/yyyy')";
//            String tarikhTandatangan = htpPerjanjian.getTarikhTandatangan();
//            String tTandatangan = "to_date('" + tarikhTandatangan + "','dd/MM/yyyy')";
            
            Date now = new Date();
    		SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
    		String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
    		
    		r = new SQLRenderer();
            r.update("id_perjanjian",htpPerjanjian.getIdPerjanjian());
            r.add("no_rujperjanjian", htpPerjanjian.getNoRujukanPerjanjian());
            if (tarikhPerjanjian !=null && !tarikhPerjanjian.equals("")){
                r.add("tarikh_perjanjian", r.unquote(tPerjanjian));
            }
            else{
                r.add("tarikh_perjanjian", tarikhPerjanjian);
            }

//            if(tarikhTandatangan != null  && !tarikhTandatangan.equals("") ){
//                r.add("tarikh_tandatangan", r.unquote(tTandatangan));
//            }
//            else{
//                r.add("tarikh_tandatangan", tarikhTandatangan);
//            } 
            
            //r.add("id_kemaskini",htpPerjanjian.getIdMasuk());
            r.add("tarikh_kemaskini", r.unquote("sysdate"));

            sql = r.getSQLUpdate("TBLHTPPERJANJIAN");
            
            stmt.executeUpdate(sql);
            
            String tarikhBorang14a = perjanjian.getTarikhBorang14A();
            String tBorang14a = "to_date('" + tarikhBorang14a + "','dd/MM/yyyy')";
            
            r = new SQLRenderer();
            
            r.update("id_perjanjianpembelian", perjanjian.getIdPerjanjianPembelian());
            r.add("id_perjanjian", htpPerjanjian.getIdPerjanjian());
            r.add("nilai_tanah", perjanjian.getNilaiTanah());
            r.add("harga_beli", perjanjian.getHargaBeli());
            r.add("bil_hakmilikbeli", perjanjian.getBilHakmilikBeli());
            
            if (tarikhBorang14a !=null && !tarikhBorang14a.equals("")){
                r.add("tarikh_borang14a", r.unquote(tBorang14a) );
            }else{
                r.add("tarikh_borang14a", tarikhBorang14a);
            }
            
            r.add("harga_tambahan", Double.parseDouble(perjanjian.getHargaTambahan()));
            
            r.add("nilai_bangunan", perjanjian.getNilaiBangunan());
            r.add("bil_unit_beli", perjanjian.getBilUnitBeli());
           // r.add("id_kemaskini", perjanjian.getIdMasuk());
            r.add("tarikh_kemaskini", r.unquote("sysdate"));

            sql = r.getSQLUpdate("TBLHTPPERJANJIANPEMBELIAN");
            
            stmt.executeQuery(sql);
            
            conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return perjanjian;
	}

	public Vector getPindahMilikByPermohonan(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		Vector v = new Vector();
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
            r = new SQLRenderer();
			
			r.add("A.ID_PERMOHONAN");
			r.add("A.ID_HAKMILIKURUSAN");
			r.add("A.ID_PINDAHMILIK");
			r.add("TO_CHAR(A.TARIKHTERIMA,'DD/MM/YYYY') AS TARIKHTERIMA");
			r.add("TO_CHAR(A.TARIKHHANTAR,'DD/MM/YYYY') AS TARIKHHANTAR");
			r.add("TO_CHAR(A.TARIKHTANDATANGAN,'DD/MM/YYYY') AS TARIKHTANDATANGAN");
			r.add("B.NO_HAKMILIK");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("A.ID_HAKMILIKURUSAN",r.unquote("B.ID_HAKMILIKURUSAN"));
			r.set("A.ID_PERMOHONAN",idPermohonan);
			r.add("B.ID_JENISHAKMILIK",r.unquote("C.ID_JENISHAKMILIK"));
			sql=r.getSQLSelect("TBLHTPPERJANJIANPINDAHMILIK A,TBLHTPHAKMILIKURUSAN B,tblrujjenishakmilik C");
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				PerjanjianPindahMilik pindahMilik = new PerjanjianPindahMilik();
				HakmilikUrusan hm = new HakmilikUrusan();
				hm.setKodjenishakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				hm.setIdhakmilikurusan(rs.getString("ID_HAKMILIKURUSAN"));
				hm.setNohakmilik(rs.getString("NO_HAKMILIK"));
				pindahMilik.setIdPindahMilik(rs.getString("ID_PINDAHMILIK"));
				pindahMilik.setTarikhTerima(rs.getString("TARIKHTERIMA"));
				pindahMilik.setTarikhHantar(rs.getString("TARIKHHANTAR"));
				pindahMilik.setTarikhTandatangan(rs.getString("TARIKHTANDATANGAN"));
				pindahMilik.setIdPermohonan(idPermohonan);
				pindahMilik.setHakmilikUrusan(hm);
				v.addElement(pindahMilik);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return v;
	}
	
	public  PerjanjianPindahMilik getPerjanjianPermohonan(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		PerjanjianPindahMilik pindahMilik = null;
		
		//Vector v = new Vector();
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
            r = new SQLRenderer();
			
			r.add("A.ID_PERMOHONAN");
			r.add("B.ID_PERJANJIAN");
			r.add("A.ID_DOKUMENPERJANJIAN");
			r.add("TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKHTERIMA");
			r.add("TO_CHAR(A.TARIKH_HANTAR,'DD/MM/YYYY') AS TARIKHHANTAR");
			r.add("TO_CHAR(B.TARIKH_TANDATANGAN,'DD/MM/YYYY') AS TARIKHTANDATANGAN");
			r.add("A.ID_PERMOHONAN",r.unquote("B.ID_PERMOHONAN"));
			r.set("A.ID_PERMOHONAN",idPermohonan);
			sql=r.getSQLSelect("tblhtpperjanjian B,tblhtpdokumenperjanjian A");
			//System.out.println("sini=="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				pindahMilik = new PerjanjianPindahMilik();
				//HakmilikUrusan hm = new HakmilikUrusan();
				pindahMilik.setTarikhTerima(rs.getString("TARIKHTERIMA"));
				pindahMilik.setTarikhHantar(rs.getString("TARIKHHANTAR"));
				pindahMilik.setTarikhTandatangan(rs.getString("TARIKHTANDATANGAN"));
				pindahMilik.setIdDokumenPerjanjian(rs.getString("ID_DOKUMENPERJANJIAN"));
				//pindahMilik.setIdPermohonan(rs.getString("ID_PERMOHONAN"));
				//v.addElement(pindahMilik);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pindahMilik;
	}

	public PerjanjianPindahMilik simpanPerjanjianBorang(PerjanjianPindahMilik pindahMilik){
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
			long id = DB.getNextID("TBLHTPPERJANJIANBORANG_SEQ");
			pindahMilik.setIdPindahMilik(String.valueOf(id));
			
			r = new SQLRenderer();
			r.add("ID_PERMOHONAN",Long.parseLong(pindahMilik.getIdPermohonan()));
			if(pindahMilik.getTarikhTerima() != null &&!pindahMilik.getTarikhTerima().equals(""))
				r.add("TARIKHTERIMA",r.unquote("to_date('" + pindahMilik.getTarikhTerima() + "','dd/MM/yyyy HH:MI:SS AM')"));
			if(pindahMilik.getTarikhHantar() != null && !pindahMilik.getTarikhHantar().equals(""))				
				r.add("TARIKHHANTAR",r.unquote("to_date('" + pindahMilik.getTarikhHantar() + "','dd/MM/yyyy HH:MI:SS AM')"));
			
			if(pindahMilik.getTarikhTandatangan() != null &&!pindahMilik.getTarikhTandatangan().equals(""))
				r.add("TARIKHTANDATANGAN",r.unquote("to_date('" + pindahMilik.getTarikhTandatangan() + "','dd/MM/yyyy HH:MI:SS AM')"));
			
			sql=r.getSQLInsert("TBLHTPPERJANJIANBORANG");
			
			stmt.executeUpdate(sql);
			
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return pindahMilik;
		
	}
	public PerjanjianPindahMilik simpanPindahMilik(PerjanjianPindahMilik pindahMilik) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
			long id = DB.getNextID("TBLHTPPERJANJIANPINDAH_SEQ");
			pindahMilik.setIdPindahMilik(String.valueOf(id));
			
			r = new SQLRenderer();
			r.add("ID_PINDAHMILIK",Long.parseLong(pindahMilik.getIdPindahMilik()));
			r.add("ID_PERMOHONAN",Long.parseLong(pindahMilik.getIdPermohonan()));
			r.add("ID_HAKMILIKURUSAN",r.unquote(pindahMilik.getHakmilikUrusan().getIdhakmilikurusan()));
			if(pindahMilik.getTarikhTerima() != null &&!pindahMilik.getTarikhTerima().equals(""))
				r.add("TARIKHTERIMA",r.unquote("to_date('" + pindahMilik.getTarikhTerima() + "','dd/MM/yyyy HH:MI:SS AM')"));
			if(pindahMilik.getTarikhHantar() != null && !pindahMilik.getTarikhHantar().equals(""))				
				r.add("TARIKHHANTAR",r.unquote("to_date('" + pindahMilik.getTarikhHantar() + "','dd/MM/yyyy HH:MI:SS AM')"));
			
			if(pindahMilik.getTarikhTandatangan() != null &&!pindahMilik.getTarikhTandatangan().equals(""))
				r.add("TARIKHTANDATANGAN",r.unquote("to_date('" + pindahMilik.getTarikhTandatangan() + "','dd/MM/yyyy HH:MI:SS AM')"));
			
			sql=r.getSQLInsert("TBLHTPPERJANJIANPINDAHMILIK");
			
			stmt.executeUpdate(sql);
			
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return pindahMilik;
	}
	
	public PerjanjianPindahMilik simpanPerjanjian(PerjanjianPindahMilik pindahMilik) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
			long idHtpDokPerjanjian = DB.getNextID("TBLHTPDOKUMENPERJANJIAN_SEQ");
			
			r = new SQLRenderer();
			r.add("ID_DOKUMENPERJANJIAN",idHtpDokPerjanjian);
			r.add("ID_PERMOHONAN",Long.parseLong(pindahMilik.getIdPermohonan()));
			if(pindahMilik.getTarikhTerima() != null &&!pindahMilik.getTarikhTerima().equals(""))
				r.add("TARIKH_TERIMA",r.unquote("to_date('" + pindahMilik.getTarikhTerima() + "','dd/MM/yyyy HH:MI:SS AM')"));
			if(pindahMilik.getTarikhHantar() != null && !pindahMilik.getTarikhHantar().equals(""))				
				r.add("TARIKH_HANTAR",r.unquote("to_date('" + pindahMilik.getTarikhHantar() + "','dd/MM/yyyy HH:MI:SS AM')"));

			
			sql=r.getSQLInsert("TBLHTPDOKUMENPERJANJIAN");
			
			stmt.executeUpdate(sql);
//------------------------------------------------------------			
			
			r = new SQLRenderer();
			
			long idHtpPerjanjian = DB.getNextID("TBLHTPPERJANJIAN_SEQ");
			
			r.add("ID_PERJANJIAN",idHtpPerjanjian);
			r.add("ID_PERMOHONAN",Long.parseLong(pindahMilik.getIdPermohonan()));
			if(pindahMilik.getTarikhTandatangan() != null &&!pindahMilik.getTarikhTandatangan().equals(""))
			r.add("TARIKH_TANDATANGAN",r.unquote("to_date('" + pindahMilik.getTarikhTandatangan() + "','dd/MM/yyyy HH:MI:SS AM')"));
			r.add("ID_NEGERI","0");

			
			sql=r.getSQLInsert("TBLHTPPERJANJIAN");

//			r.update("ID_PERMOHONAN",Long.parseLong(pindahMilik.getIdPermohonan()));
//			if(pindahMilik.getTarikhTandatangan() != null &&!pindahMilik.getTarikhTandatangan().equals(""))
//			r.add("TARIKH_TANDATANGAN",r.unquote("to_date('" + pindahMilik.getTarikhTandatangan() + "','dd/MM/yyyy HH:MI:SS AM')"));
//			
//			sql=r.getSQLUpdate("TBLHTPPERJANJIAN");
			
			stmt.executeUpdate(sql);
			
			
			
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return pindahMilik;
	}

	public PerjanjianPindahMilik updatePerjanjian(PerjanjianPindahMilik pindahMilik) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
			//long idHtpDokPerjanjian = DB.getNextID("TBLHTPDOKUMENPERJANJIAN_SEQ");
			
			r = new SQLRenderer();
			r.add("ID_DOKUMENPERJANJIAN",Long.parseLong(pindahMilik.getIdDokumenPerjanjian()));
			r.update("ID_PERMOHONAN",Long.parseLong(pindahMilik.getIdPermohonan()));
			if(pindahMilik.getTarikhTerima() != null &&!pindahMilik.getTarikhTerima().equals(""))
				r.add("TARIKH_TERIMA",r.unquote("to_date('" + pindahMilik.getTarikhTerima() + "','dd/MM/yyyy HH:MI:SS AM')"));
			if(pindahMilik.getTarikhHantar() != null && !pindahMilik.getTarikhHantar().equals(""))				
				r.add("TARIKH_HANTAR",r.unquote("to_date('" + pindahMilik.getTarikhHantar() + "','dd/MM/yyyy HH:MI:SS AM')"));

			
			sql=r.getSQLUpdate("TBLHTPDOKUMENPERJANJIAN");
			
			stmt.executeUpdate(sql);
//------------------------------------------------------------			
			
			r = new SQLRenderer();

			r.update("ID_PERMOHONAN",Long.parseLong(pindahMilik.getIdPermohonan()));
			if(pindahMilik.getTarikhTandatangan() != null &&!pindahMilik.getTarikhTandatangan().equals(""))
			r.add("TARIKH_TANDATANGAN",r.unquote("to_date('" + pindahMilik.getTarikhTandatangan() + "','dd/MM/yyyy HH:MI:SS AM')"));
			
			sql=r.getSQLUpdate("TBLHTPPERJANJIAN");
			
			stmt.executeUpdate(sql);
			
			
			
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		
		return pindahMilik;
	}
	
	public PerjanjianPindahMilik updatePindahMilik(PerjanjianPindahMilik pindahMilik) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
            String t1 = "to_date('" + pindahMilik.getTarikhTerima() + "','dd/MM/yyyy HH:MI:SS AM')";
            String t2 = "to_date('" + pindahMilik.getTarikhHantar() + "','dd/MM/yyyy HH:MI:SS AM')";
            String t3 = "to_date('" + pindahMilik.getTarikhTandatangan() + "','dd/MM/yyyy HH:MI:SS AM')";
            r = new SQLRenderer();
			r.update("ID_PINDAHMILIK",Long.parseLong(pindahMilik.getIdPindahMilik()));
			r.add("ID_PERMOHONAN",Long.parseLong(pindahMilik.getIdPermohonan()));
			if(!pindahMilik.getTarikhTerima().equals(""))
				r.add("TARIKHTERIMA",r.unquote(t1));
			if(!pindahMilik.getTarikhHantar().equals(""))				
				r.add("TARIKHHANTAR",r.unquote(t2));
			
			if(!pindahMilik.getTarikhTandatangan().equals(""))
				r.add("TARIKHTANDATANGAN",r.unquote(t3));
			
			sql=r.getSQLUpdate("TBLHTPPERJANJIANPINDAHMILIK");
			stmt.executeUpdate(sql);
			
			conn.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pindahMilik;
	}

	//@Override
	public PerjanjianPindahMilik getPindahMilik(String idPindahMilik) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		PerjanjianPindahMilik pindahMilik = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            
            r = new SQLRenderer();
			
			r.add("A.ID_PERMOHONAN");
			r.add("A.ID_HAKMILIKURUSAN");
			r.add("A.ID_PINDAHMILIK");
			r.add("TO_CHAR(A.TARIKHTERIMA,'DD/MM/YYYY') AS TARIKHTERIMA");
			r.add("TO_CHAR(A.TARIKHHANTAR,'DD/MM/YYYY') AS TARIKHHANTAR");
			r.add("TO_CHAR(A.TARIKHTANDATANGAN,'DD/MM/YYYY') AS TARIKHTANDATANGAN");
			r.set("A.ID_PINDAHMILIK",idPindahMilik);
			sql=r.getSQLSelect("TBLHTPPERJANJIANPINDAHMILIK A");
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				pindahMilik = new PerjanjianPindahMilik();
				HakmilikUrusan hm = new HakmilikUrusan();
				hm.setIdhakmilikurusan(idPindahMilik);
				pindahMilik.setIdPindahMilik(idPindahMilik);
				pindahMilik.setTarikhTerima(rs.getString("TARIKHTERIMA"));
				pindahMilik.setTarikhHantar(rs.getString("TARIKHHANTAR"));
				pindahMilik.setTarikhTandatangan(rs.getString("TARIKHTANDATANGAN"));
				pindahMilik.setIdPermohonan(rs.getString("ID_PERMOHONAN"));
				pindahMilik.setHakmilikUrusan(hm);
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pindahMilik;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getPerjanjianUpload(String idPermohonan){
		Db  db= null;
		String sql;
		Hashtable HDPerjanjian;
		Vector pindahMilik = null;
		UploadPerjanjian Uperjanjian = null;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "select tarikh_tandatangan,tarikh_terima from TBLHTPPERJANJIAN where id_permohonan =" + idPermohonan;
			System.out.println("==getPerjanjianUpload=="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			pindahMilik = new Vector();
			int bil = 1;

			while(rs.next()){
				Uperjanjian = new UploadPerjanjian();
				Permohonan permohonan= new Permohonan();
				//Uperjanjian.setIdDokumenPerjanjian(Long.parseLong(rs.getString("id_dokumenperjanjian")));
				Uperjanjian.setTarikhTerima(rs.getString("tarikh_terima"));
				Uperjanjian.setTarikhTandatangan(rs.getString("tarikh_tandatangan"));
				pindahMilik.addElement(Uperjanjian);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pindahMilik;
		
	}
	
	@SuppressWarnings("unchecked")
	public Vector getPerjanjianAttach(String idDokumenPerjanjian){
		Db  db= null;
		String sql;
		Hashtable HDPerjanjian;
		Vector v = null;
		PerjanjianPindahMilik pindahMilik = null;
		UploadPerjanjian Uperjanjian = null;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "select id_dokumenperjanjianattach,id_dokumenperjanjian,content_derafperjanjian,mimetype,nama_fail from TBLHTPDOKUMENPERJANJIANATTACH where id_dokumenperjanjian =" + idDokumenPerjanjian;
			sql += " order by  id_dokumenperjanjianattach desc";
			System.out.println("==getPerjanjianAttach=="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			v = new Vector();
			int bil = 1;

			while(rs.next()){
				Uperjanjian = new UploadPerjanjian();
				pindahMilik = new PerjanjianPindahMilik();
				Permohonan permohonan= new Permohonan();
				Uperjanjian.setIdDokumenPerjanjianAttach(rs.getLong("id_dokumenperjanjianattach"));
				Uperjanjian.setIdDokumenPerjanjian(rs.getString("id_dokumenperjanjian"));
				//permohonan.setIdPermohonan(rs.getString("id_permohonan"));
				Uperjanjian.setContent(rs.getBytes("content_derafperjanjian"));
				Uperjanjian.setMIMEType(rs.getString("mimetype"));
				Uperjanjian.setNamaFail(rs.getString("nama_fail"));
				pindahMilik.setPerjanjianAttch(Uperjanjian);
				v.addElement(pindahMilik);
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
	
}

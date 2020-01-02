package ekptg.model.htp.permohonan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPBayaran;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.Bayaran;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.entity.Resit;

public class HTPBayaranPermohonanBean implements IHTPBayaran {
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.permohonan.HTPBayaranPermohonanBean.class);
	private String sql = "";
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private Tblrujsuburusanstatusfail rsusf = null;
	private IHtp iHTP = null;  	
	private static Db db = null;
	private static Connection conn = null;
	private Vector caraBayaran = null;
	
	@Override
	public String selectCaraBayaran(String selectName,Long selectedValue
			, String disability, String jsFunction ,String idBayaran) throws Exception{		
		StringBuffer sb = null;		
		try{
			sb = new StringBuffer();
			Vector jCBayar = new Vector();
			Hashtable hCBayar = null;
			String select = "";			
			sb.append("<select name ='"+ selectName +"' "  );
			
			if(!disability.equals(null) && !disability.equals("")){
				sb.append(disability);
			}			
			if(!jsFunction.equals(null) && !jsFunction.equals("")){
				sb.append(jsFunction);
			}
			
			sb.append(" > ");
			sb.append("<option value = \"\"> SILA PILIH </option>");
						
			jCBayar = getCaraBayaran(idBayaran);
			for(int i = 0; i < jCBayar.size(); i++){
				hCBayar = new Hashtable();
				hCBayar = (Hashtable)jCBayar.get(i);
				Long idCaraBayaran = Long.parseLong(hCBayar.get("idCaraBayar").toString());
				if(idCaraBayaran.equals(selectedValue)){
					select = "selected";
				} else {
					select = "";
				}
				
				sb.append("<option value = '" + idCaraBayaran + "' " + select
						+ " > " + hCBayar.get("kod").toString() + " - "
						+ hCBayar.get("keterangan").toString() + " </option>\n" );
			}

			sb.append("</select>");
			
		}catch(Exception e){
			e.printStackTrace();
		
		}				
		return sb.toString();
	
	}

	
	public Vector getCaraBayaran(String id) throws Exception{		
		try{
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_CARABAYAR, A.KOD_CARABAYAR, A.KETERANGAN " +
				" FROM TBLRUJCARABAYAR A " +
				"";
			if(!id.equals(""))
				sql += " WHERE A.ID_CARABAYAR IN ("+id+")";
				
			sql += "ORDER BY A.KETERANGAN ASC " +
				" ";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable hashCaraBayaran = null;
			caraBayaran = new Vector();
			while(rs.next()){
				hashCaraBayaran = new Hashtable();
				hashCaraBayaran.put("idCaraBayar", rs.getString("ID_CARABAYAR") == null ? "" : rs.getString("ID_CARABAYAR"));
				hashCaraBayaran.put("kod", rs.getString("KOD_CARABAYAR") == null ? "" : rs.getString("KOD_CARABAYAR"));
				hashCaraBayaran.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				caraBayaran.addElement(hashCaraBayaran);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			db.close();
		}
		return caraBayaran;
	}
	
	@Override
	public Bayaran getPembayaranByPermohonan(String idPermohonan)throws Exception {
		Bayaran bayaran = null;
		Resit resit = null;

		try{
			db = new Db();
			Statement stmt = db.getStatement();
		    sql = " select TPB.id_bayaran,TPB.jumlah_bayaran" +
	  		" ,TPB.nama_bank,NVL(TPB.id_carabayar,0) id_carabayar" +
	  		" ,(" +
	  		"	select kod_carabayar||' - '||keterangan from tblrujcarabayar where id_carabayar= TPB.id_carabayar" +
	  		"	) cara_bayar "+
	  		" ,TPB.no_baucer" +
	  		" ,NVL(TO_CHAR(TPB.tarikh_baucer,'dd/mm/yyyy') ,'01/01/1900') tarikh_baucerstr,TPB.tarikh_baucer " +
	  		" ,NVL(TO_CHAR(TPB.tarikh_terima,'dd/mm/yyyy') ,'01/01/1900') tarikh_terimastr,TPB.tarikh_terima " +
	  		" ,TPB.no_resit" +
	  		" ,NVL(TO_CHAR(TPB.tarikh_resit,'dd/mm/yyyy') ,'01/01/1900') tarikh_resitstr,TPB.tarikh_resit " +
	  		" from tblhtpbayaran TPB where TPB.id_jenisbayaran = 20 " +
	  		" and TPB.id_permohonan = '"+idPermohonan+"'";
		    myLog.info(sql);		  
		    ResultSet rs1 = stmt.executeQuery(sql);
		    while(rs1.next()){
				bayaran = new Bayaran();
				resit = new Resit();
				bayaran.setIdBayaran(Long.parseLong(rs1.getString("id_bayaran")));
				bayaran.setNoRujukan(Utils.isNull(rs1.getString("no_baucer")));
				bayaran.setTarikhBayaranStr(rs1.getString("tarikh_baucerstr"));
				bayaran.setTarikhBayaran(rs1.getDate("tarikh_baucer"));
				bayaran.setBank(Utils.isNull(rs1.getString("nama_bank")));
				bayaran.setJumlah(rs1.getDouble("jumlah_bayaran"));
				bayaran.setIdCaraBayaran(Long.parseLong(rs1.getString("id_carabayar")));
				bayaran.setCaraBayar(Utils.isNull(rs1.getString("cara_bayar")));
				bayaran.setTarikhTerimaBayaran(rs1.getDate("tarikh_terima"));
				bayaran.setTarikhTerimaBayaranStr(rs1.getString("tarikh_terimastr"));
				resit.setNoRujukan(Utils.isNull(rs1.getString("no_resit")));
				resit.setTarikhStr(rs1.getString("tarikh_resitstr"));
				resit.setTarikh(rs1.getDate("tarikh_resit"));
				bayaran.setResit(resit);

		    }
		    return bayaran;
			
		}finally{
			if (db != null) db.close();
		}	
	}

	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
}

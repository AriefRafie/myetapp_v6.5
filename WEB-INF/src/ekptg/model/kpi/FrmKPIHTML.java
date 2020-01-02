package ekptg.model.kpi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblrujurusan;

public class FrmKPIHTML {

	
	static Logger mylog = Logger.getLogger(FrmKPIHTML.class);
	/**
	 * Senarai keterangan KPI untuk SOC
	 */

	public static String SelectKeterangan(String selectName,String jsFunction) throws Exception {
		return SelectKeterangan(selectName, null, null,jsFunction);
	}
	
	public static String SelectKeterangan(String selectName,Long selectedValue, String jsFunction)
	throws Exception {
		return SelectKeterangan(selectName, selectedValue, null,jsFunction);
	}

	public static String SelectKeterangan(String selectName,
			Long selectedValue, String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">Sila Pilih </option>\n");
			Vector v = FrmKPIData.getSenaraiKeterangan();
			Hashtable f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
			
				f = (Hashtable) v.get(i);
				if (f.get("idkpiketerangan").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("idkpiketerangan") + ">"
						+ f.get("keterangan") + "</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}	
	
	public static String SelectUrusan(String selectName, String idSeksyen ) throws Exception {
		return SelectUrusan(selectName, null, null,idSeksyen );
	}

	public static String SelectUrusan(String selectName, Long selectedValue, String disability, String idSeksyen) throws Exception {
		return SelectUrusan(selectName, selectedValue, disability, null, idSeksyen) ;
	}

	public static String SelectUrusan(String selectName, Long selectedValue,
			String disability, String jsFunction, String idSeksyen) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector v = FrmKPIData.getUrusanSeksyen(idSeksyen);
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public static String SelectStatusFailByUrusan(String selectName,Long selectedValue, 
			String disability, String jsFunction,String idUrusan,String idSuburusan) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' class=\"autoselect\" ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector v = FrmKPIData.getStatusFailBySuburusan(idUrusan, idSuburusan);
			Hashtable f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
			
				f = (Hashtable) v.get(i);
				if (f.get("idsuburusanstatus").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("idsuburusanstatus") + ">"
						+ f.get("namasuburusan") + "->>"+ f.get("status") +"</option>\n");
			}
			sb.append("</select");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}	
	
	public static String getKPIMengikutNegeri(String selectName, Long selectedValue,
			String idUrusan,String idSuburusan,String idPejabat,String tarikhMula,String tarikhTamat) throws Exception {
		
		StringBuffer sb = new StringBuffer("");
		try {
			//sb.append("<table width=\"100%\" border=\"0\" align=\"center\">");
			//Vector v = DB.getNegeri();;
			Vector v = FrmKPIData.getNegeriKPI(idUrusan, idSuburusan,tarikhMula,tarikhTamat);
			Hashtable f = null;
			String s = "";
			int bilanganPermohonan=0;
			int bilanganSelesai = 0;
			Double peratusan = 0.0;
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable) v.get(i);
				String idNegeri = ""+f.get("idnegeri");
				String namaNegeri = (String)f.get("negeri");
				bilanganPermohonan = (Integer)f.get("permohonan");
				bilanganSelesai = (Integer)f.get("selesai");						

				if(bilanganSelesai!=0){
					peratusan = (((Double)f.get("harisasarandalaman")/(Double)f.get("harisebenarsiap"))*100);					
				}else
					peratusan = 0.0;
				double purataKitaran = 0.0 ;
				if(bilanganSelesai!=0){
					purataKitaran = ((Double)f.get("harisebenar")/bilanganSelesai);
				}else
					purataKitaran = 0.0;					
				/**/
				Vector<?> vecSenarai = null;
				//vecSenarai = FrmKPIData.getKeteranganTerperinci(idUrusan,idSuburusan);
				vecSenarai = FrmKPIData.getKeteranganUntukUtama(idUrusan,idSuburusan);
		          //#set( $i = $velocityCount )
				String rows ="";
		          if ( (i % 2) != 1 )
		               rows = "row2" ;
		          else
		               rows = "row1" ;
		          
				//sb.append("<tr class=\"table_header\">");
				//sb.append("<td width=\"35%\" align=\"center\"><strong>"+namaNegeri+"</strong></td>");
				//sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
				sb.append("<tr class=\""+rows+"\">");
				sb.append("<td width=\"30%\" align=\"left\" nowrap ><strong>");
				sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPINegeri?command=bydaerah&idnegeri="+f.get("idnegeri")+"&idurusan="+idUrusan+"&idsuburusan="+idSuburusan+"&tarikhmula="+tarikhMula+"&tarikhtamat="+tarikhTamat+"')\">");
				sb.append(""+namaNegeri+"</a></strong></td>");
				sb.append("<td width=\"5%\" align=\"center\"><strong>"+bilanganPermohonan+"</strong></td>");
				
				for (int ii = 0; ii < vecSenarai.size(); ii++) {
					Hashtable<?, ?> hash = (Hashtable<?, ?>) vecSenarai.get(ii);
					if((Integer)hash.get("giliran")== 2){
						int intKPIL = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"5",idNegeri,tarikhMula,tarikhTamat);
						int intKPIL15 = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"5-14",idNegeri,tarikhMula,tarikhTamat);
						int intKPIL16 = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"16",idNegeri,tarikhMula,tarikhTamat);

						sb.append("<td  width=\"1%\" align=\"center\">"+intKPIL+"</td>");		
						sb.append("<td  width=\"2%\" align=\"center\">"+intKPIL15+"</td>");
						sb.append("<td  width=\"1%\" align=\"center\">"+intKPIL16+"</td>");
					}
					//giliran2 = giliran2+praGiliran2;
					if((Integer)hash.get("giliran")== 3){
						int intKPIL = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"8",idNegeri,tarikhMula,tarikhTamat);
						int intKPIL15 = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"15",idNegeri,tarikhMula,tarikhTamat);
						int intKPIL16 = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"16",idNegeri,tarikhMula,tarikhTamat);

						sb.append("<td  width=\"1%\" align=\"center\">"+intKPIL+"</td>");		
						sb.append("<td  width=\"2%\" align=\"center\">"+intKPIL15+"</td>");
						sb.append("<td  width=\"1%\" align=\"center\">"+intKPIL16+"</td>");
					}
					if((Integer)hash.get("giliran")== 4){
						int intKPIL = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"30",idNegeri,tarikhMula,tarikhTamat);
						int intKPIL15 = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"60",idNegeri,tarikhMula,tarikhTamat);
						int intKPIL16 = FrmKPIData.getKPIGiliranBilanganUtama(""+hash.get("idsuburusanstatus"),"61",idNegeri,tarikhMula,tarikhTamat);

						sb.append("<td  width=\"1%\" align=\"center\">"+intKPIL+"</td>");		
						sb.append("<td  width=\"2%\" align=\"center\">"+intKPIL15+"</td>");
						sb.append("<td  width=\"1%\" align=\"center\">"+intKPIL16+"</td>");
					}

				}
				//mylog.info("getKPIMengikutNegeri-purataKitaran:"+purataKitaran);
				sb.append("<td width=\"10%\" align=\"center\" ><strong>"+bilanganSelesai+"</strong></td>");
				sb.append("<td width=\"15%\" align=\"center\" ><strong>"+ekptg.helpers.Utils.format1Decimal(peratusan)+"</strong></td>");
				sb.append("<td width=\"15%\"align=\"center\" ><strong>"+ekptg.helpers.Utils.format1Decimal(purataKitaran)+"</strong></td>");			
				sb.append("</tr>");				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	 public static Vector getStatusByKPISuburusan(String id)throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("kpu.id_kpiurusan");
		      r.add("kpu.sasaran_masa");
		      r.add("kpu.jenis_sasaran");		      
		      r.add("kpu.id_masuk");
		      r.add("kpu.tarikh_masuk");
		      r.add("kpu.id_kemaskini");
		      r.add("kpu.tarikh_kemaskini");
			  r.add("kps.id_suburusanstatus");
			  //r.add("ru.nama_urusan");
		      //r.add("kpu.status_giliran");
		      //r.add("kpu.id_kpiketerangan",r.unquote("kpk.id_kpiketerangan"));
		      //r.add("kpu.id_suburusan",r.unquote("rsu.id_suburusan"));
		      //r.add("rsu.id_urusan",r.unquote("ru.id_urusan"));
		      //r.add("rsu.id_suburusan",r.unquote(idSuburusan));
		      //r.add("ru.id_urusan",r.unquote(idUrusan));
		      r.add("kpu.id_kpiurusan",r.unquote("kps.id_kpiurusan"));	         
		      r.add("kpu.id_kpiurusan",r.unquote(id));	         
      
		      //sql = r.getSQLSelect("tblkpiketerangan kpk ,tblkpiurusan kpu ,tblrujsuburusan rsu,tblrujurusan ru");
		      sql = r.getSQLSelect("tblkpiurusan kpu ,tblkpistatus kps");
		      //sql += " order by kpu.tarikh_kemaskini";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      while (rs.next()) {
		        Hashtable cb = new Hashtable();
		        cb.put("idkpiurusan", rs.getLong("id_kpiurusan"));
		        cb.put("sasaranmasa",rs.getString("sasaran_masa"));
		        if(rs.getString("jenis_sasaran")=="1"){
		        	cb.put("jenissasaran","JAM");		        
		        	
		        }else{
		        	cb.put("jenissasaran","HARI");		        
		        }
		        cb.put("idmasuk",rs.getString("id_masuk"));
		        cb.put("tarikhmasuk",lebah.util.Util.getDateTime(rs.getDate("tarikh_masuk"), "dd/MM/yyyy"));
		        cb.put("idkemaskini",rs.getString("id_kemaskini"));
		        cb.put("tarikhkemaskini",lebah.util.Util.getDateTime(rs.getDate("tarikh_kemaskini"), "dd/MM/yyyy"));
		        cb.put("idsuburusanstatus",rs.getString("id_suburusanstatus"));
		        //cb.put("urusan",rs.getString("nama_urusan"));
		        //cb.put("giliran",rs.getInt("status_giliran"));
		        list.addElement(cb);
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }

	 public static String getKPIGiliranTerperinciHTML(String idUrusan,String idSuburusan,String idNegeri
			 ,String txdMula,String txdAkhir) throws Exception {
		 StringBuffer sb = new StringBuffer("");
		 try {
			 sb.append("<table width=\"100%\" border=\"0\" align=\"center\">");
			 Vector<Hashtable<String, Comparable>> v = FrmKPIData.getKeteranganUntukUtama(idUrusan,idSuburusan);
			 
			 Hashtable<String, Comparable> f = null;
			 int bil=0;
			 
			 for (int i = 0; i < v.size(); i++) {
				 f = v.get(i);
				 if((Integer)f.get("giliran")== 2){
					 bil=bil+1;
					 sb.append("<tr>");
					 sb.append("<td align=\"left\" class=row2><strong>"+bil+"."+f.get("keterangan")+"</strong></td>");
					 sb.append("<td align=\"center\" class=row2></td>");
					 sb.append("</tr>");
						
					 int intKPIL = FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"5",idNegeri);
					 if(intKPIL != 0 ){
						 sb.append("<tr>");
						 sb.append("<td width=\"70%\" align=\"right\" class=row2><strong><5</strong></td>");
						 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=5')\">");
						 sb.append(""+intKPIL+"</a></strong></td>");
						 sb.append("</tr>");							
					 }else{
						 sb.append("<tr>");
						 sb.append("<td width=\"70%\" align=\"right\" class=row2><strong><5</strong></td>");
						 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
						 sb.append("</tr>");	
					 }	
							
					 int intKPIL15 = FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"5-14",idNegeri);
					 if(intKPIL15 == 0 ){
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>6-14</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
						 sb.append("</tr>");
					 }else{
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>6-14</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=yellow><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=5-14')\">");
						 sb.append(""+intKPIL15+"</a></strong></td>");
						 sb.append("</tr>");
					 }
						
					 int intKPIL16 = FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"16",idNegeri);
					 if(intKPIL16 == 0 ){
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>>15</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=red><strong>0</strong></td>");
						 sb.append("</tr>");
					 }else{
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>>15</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=red><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=16')\">");
						 sb.append(""+intKPIL16+"</a></strong></td>");
						 sb.append("</tr>");
					 }
				 }else if((Integer)f.get("giliran")== 3){
					 bil=bil+1;
					 sb.append("<tr>");
					 sb.append("<td align=\"left\" class=row2><strong>"+bil+"."+f.get("keterangan")+"</strong></td>");
					 sb.append("<td align=\"center\" class=row2></td>");
					 sb.append("</tr>");
							
					 int intKPIL= FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"8",idNegeri);
					 if(intKPIL != 0 ){
						 sb.append("<tr>");
						 sb.append("<td width=\"70%\" align=\"right\" class=row2><strong><7</strong></td>");
						 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=8')\">");
						 sb.append(""+intKPIL+"</a></strong></td>");
						 sb.append("</tr>");							
					 }else{
						 sb.append("<tr>");
						 sb.append("<td width=\"70%\" align=\"right\" class=row2><strong><7</strong></td>");
						 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
						 sb.append("</tr>");	
					 }	
								
					 int intKPIL15= FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"15",idNegeri);
					 if(intKPIL15 == 0 ){
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>8-14</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
						 sb.append("</tr>");
					 }else{
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>8-14</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=yellow><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=15')\">");
						 sb.append(""+intKPIL15+"</a></strong></td>");
						 sb.append("</tr>");
					 }
							
					 int intKPIL16= FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"16",idNegeri);
					 if(intKPIL16  == 0 ){
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>>15</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=red><strong>0</strong></td>");
						 sb.append("</tr>");
					 }else{
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>>15</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=red><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=16')\">");
						 sb.append(""+intKPIL16+"</a></strong></td>");
						 sb.append("</tr>");
					 }						
												
				 }	else if((Integer)f.get("giliran")== 4){
					 bil=bil+1;
					 sb.append("<tr>");
					 sb.append("<td align=\"left\" class=row2><strong>"+bil+"."+f.get("keterangan")+"</strong></td>");
					 sb.append("<td align=\"center\" class=row2></td>");
					 sb.append("</tr>");
						
					 int intKPIL = FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"30",idNegeri);
					 if(intKPIL != 0 ){
						 sb.append("<tr>");
						 sb.append("<td width=\"70%\" align=\"right\" class=row2><strong><30</strong></td>");
						 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=30')\">");
						 sb.append(""+intKPIL+"</a></strong></td>");
						 sb.append("</tr>");							
					 }else{
						 sb.append("<tr>");
						 sb.append("<td width=\"70%\" align=\"right\" class=row2><strong><30</strong></td>");
						 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
						 sb.append("</tr>");	
					 }	
							
					 int intKPIL15 = FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"60",idNegeri);
					 if(intKPIL15 == 0 ){
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>31-60</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
						 sb.append("</tr>");
					 }else{
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>31-60</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=yellow><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=60')\">");
						 sb.append(""+intKPIL15+"</a></strong></td>");
						 sb.append("</tr>");
					 }
					 
					 int intKPIL16 = FrmKPIData.getKPIGiliranTerperinciCount(""+f.get("idsuburusanstatus"),"61",idNegeri);
					 if(intKPIL16 == 0 ){
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>>60</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=red><strong>0</strong></td>");
						 sb.append("</tr>");
					 }else{
						 sb.append("<tr>");
						 sb.append("<td align=\"right\" class=row2><strong>>60</strong></td>");
						 sb.append("<td align=\"center\" bgcolor=red><strong>");
						 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=61')\">");
						 sb.append(""+intKPIL16+"</a></strong></td>");
						 sb.append("</tr>");
					 }
						
				 }

			 }
			 
			 sb.append("</table>");
		 } catch (Exception ex) {
			 ex.printStackTrace();
			 throw ex;
		 }

		 return sb.toString();

	 }

		public static String getKPIMengikutPejabat(String selectName, Long selectedValue,
				String idUrusan,String idSuburusan,String idNegeri,String tarikhMula
				,String tarikhTamat) throws Exception {		
			StringBuffer sb = new StringBuffer("");
			try {
				Vector<?> v = FrmKPIData.getMengikutPejabat(idNegeri, idSuburusan,tarikhMula,tarikhTamat);
				Hashtable<?, ?> f = null;
				double peratusan = 0.0;
				double purataKitaran = 0.0 ;
				int bilanganSelesai = 0 ;
				for (int i = 0; i < v.size(); i++) {
					f = (Hashtable<?, ?>) v.get(i);
					String idPejabat = ""+f.get("idpejabat");
					String namaPejabat = (String)f.get("namapejabat");
					int bilPermohonan = (Integer)f.get("permohonan");
					bilanganSelesai = (Integer)f.get("selesai");
					
					if(bilanganSelesai!=0){
						peratusan = (((Double)f.get("harisasarandalaman")/(Double)f.get("harisiapsebenar"))*100);					
					}else{
						peratusan = 0.0;	}
					if(bilanganSelesai!=0){
						purataKitaran = ((Double)f.get("harisebenar")/bilanganSelesai);
					}else{
						purataKitaran = 0.0;	}
					/**/
					Vector<?> vecSenarai = null;
					//vecSenarai = FrmKPIData.getKeteranganTerperinci(idUrusan,idSuburusan);
					vecSenarai = FrmKPIData.getKeteranganUntukUtama(idUrusan,idSuburusan);
					String rows ="";
			          if ( (i % 2) != 1 )
			               rows = "row2" ;
			          else
			               rows = "row1" ;
			          
					sb.append("<tr class=\""+rows+"\">");
					
					//sb.append("<tr class=\"table_header\">");
					sb.append("<td width=\"35%\" align=\"center\"><strong>"+namaPejabat+"</strong></td>");
					//sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
					//sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPINegeri?command=bydaerah&idnegeri="+f.get("iddaerah")+"&hari=5')\">");
					//sb.append(""+namaDaerah+"</a></strong></td>");

					sb.append("<td width=\"5%\" align=\"center\"><strong>"+bilPermohonan+"</strong></td>");
					
					for (int ii = 0; ii < vecSenarai.size(); ii++) {
						Hashtable<?, ?> hash = (Hashtable<?, ?>) vecSenarai.get(ii);
						if((Integer)hash.get("giliran")== 2){
							//Vector vecKPI = getStatusByKPISuburusan(""+hash.get("idkpiurusan"));
							//Hashtable idKPI = (Hashtable)vecKPI.get(0);
							//08-10 praGiliran2 = FrmKPIData.getKPIGiliranTerperinciCount((String)idKPI.get("idsuburusanstatus"),null,""+f.get("idnegeri"));

							//Vector vecKPI = FrmKPIData.getStatusByKPISuburusan(""+hash.get("idkpiurusan"));
							//Hashtable idKPI = (Hashtable)vecKPI.get(0);
							
							Vector<?> vecKPIL = FrmKPIData.getKPIGiliranTerperinci(""+hash.get("idsuburusanstatus"),"5",idNegeri,idPejabat,tarikhMula,tarikhTamat);
							Vector<?> vecKPIL15 = FrmKPIData.getKPIGiliranTerperinci(""+hash.get("idsuburusanstatus"),"5-14",idNegeri,idPejabat,tarikhMula,tarikhTamat);
							Vector<?> vecKPIL16 = FrmKPIData.getKPIGiliranTerperinci(""+hash.get("idsuburusanstatus"),"16",idNegeri,idPejabat,tarikhMula,tarikhTamat);
							
							sb.append("<td  width=\"1%\" align=\"center\">"+vecKPIL.size()+"</td>");		
							sb.append("<td  width=\"2%\" align=\"center\">"+vecKPIL15.size()+"</td>");
							sb.append("<td  width=\"1%\" align=\"center\">"+vecKPIL16.size()+"</td>");
						}
						//giliran2 = giliran2+praGiliran2;
						if((Integer)hash.get("giliran")== 3){
							//Vector vecKPI = getStatusByKPISuburusan(""+hash.get("idkpiurusan"));
							//Hashtable idKPI = (Hashtable)vecKPI.get(0);
		
							//Vector vecKPI = FrmKPIData.getStatusByKPISuburusan(""+hash.get("idkpiurusan"));
							//Hashtable idKPI = (Hashtable)vecKPI.get(0);
							
							Vector vecKPIL = FrmKPIData.getKPIGiliranTerperinci(""+hash.get("idsuburusanstatus"),"8",idNegeri,idPejabat,tarikhMula,tarikhTamat);
							Vector vecKPIL15 = FrmKPIData.getKPIGiliranTerperinci(""+hash.get("idsuburusanstatus"),"15",idNegeri,idPejabat,tarikhMula,tarikhTamat);
							Vector vecKPIL16 = FrmKPIData.getKPIGiliranTerperinci(""+hash.get("idsuburusanstatus"),"16",idNegeri,idPejabat,tarikhMula,tarikhTamat);

							sb.append("<td  width=\"1%\" align=\"center\">"+vecKPIL.size()+"</td>");		
							sb.append("<td  width=\"2%\" align=\"center\">"+vecKPIL15.size()+"</td>");
							sb.append("<td  width=\"1%\" align=\"center\">"+vecKPIL16.size()+"</td>");
						}
						if((Integer)hash.get("giliran")== 4){
							//Vector vecKPI = getStatusByKPISuburusan(""+hash.get("idkpiurusan"));
							//Hashtable idKPI = (Hashtable)vecKPI.get(0);
							//int giliran4 = FrmKPIData.getKPIGiliranTerperinciCount((String)idKPI.get("idsuburusanstatus"),null,(String)f.get("idnegeri"));
							//Vector vecKPI = FrmKPIData.getStatusByKPISuburusan(""+hash.get("idkpiurusan"));
							//Hashtable idKPI = (Hashtable)vecKPI.get(0);
							
							int vecKPIL = FrmKPIData.getKPIGiliranBilanganMengikutPejabat(""+hash.get("idsuburusanstatus"),"30",idNegeri,idPejabat,tarikhMula,tarikhTamat);
							int vecKPIL15 = FrmKPIData.getKPIGiliranBilanganMengikutPejabat(""+hash.get("idsuburusanstatus"),"60",idNegeri,idPejabat,tarikhMula,tarikhTamat);
							int vecKPIL16 = FrmKPIData.getKPIGiliranBilanganMengikutPejabat(""+hash.get("idsuburusanstatus"),"61",idNegeri,idPejabat,tarikhMula,tarikhTamat);

							sb.append("<td  width=\"1%\" align=\"center\">"+vecKPIL+"</td>");		
							sb.append("<td  width=\"2%\" align=\"center\">"+vecKPIL15+"</td>");
							sb.append("<td  width=\"1%\" align=\"center\">"+vecKPIL16+"</td>");
						}

					}/**/
							//mylog.info("getKPIMengikutNegeri-purataKitaran:"+purataKitaran);
					sb.append("<td width=\"10%\" align=\"center\" ><strong>"+bilanganSelesai+"</strong></td>");
					sb.append("<td width=\"15%\" align=\"center\" ><strong>"+ekptg.helpers.Utils.format1Decimal(peratusan)+"</strong></td>");
					sb.append("<td width=\"15%\"align=\"center\" ><strong>"+ekptg.helpers.Utils.format1Decimal(purataKitaran)+"</strong></td>");			
					sb.append("</tr>");				
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			}

			return sb.toString();
		}

		 public static String getKPIGiliranTerperinciHTMLUtama(String idUrusan,String idSuburusan,String idNegeri
				 ,String txdMula,String txdAkhir) throws Exception {
			 StringBuffer sb = new StringBuffer("");
			 try {
				 sb.append("<table width=\"100%\" border=\"0\" align=\"center\">");
				 Vector<Hashtable<String, Comparable>> v = FrmKPIData.getKeteranganUntukUtama(idUrusan,idSuburusan);
				 
				 Hashtable<String, Comparable> f = null;
				 int bil=0;
				 String strParameter = "&idnegeri="+idNegeri+"&txdMula="+txdMula+"&txdAkhir="+txdAkhir;
				 for (int i = 0; i < v.size(); i++) {
					 f = v.get(i);
					 String rows ="";
					 if ( (bil % 2) != 1 )
						 rows = "row1" ;
					 else
						 rows = "row2" ;

					 String strKeteranganX = "";
					 String strKeteranganPilih = "";
					 if((Integer)f.get("pilihan")==1)
						 strKeteranganPilih = " (pilihan) ";
					 else	 
						 strKeteranganPilih = "  ";
						 
					 if((Integer)f.get("giliran")== 2){
						 bil=bil+1;
						 sb.append("<tr>");
						 sb.append("<td align=\"\" class="+rows+" >" );
						 sb.append("	<table >");
						 sb.append("	<tr>");
						 sb.append("		<td align=\"\" class="+rows+" ><strong>"+bil+"."+f.get("keteranganheader")+strKeteranganPilih+"</strong></td>");
						 sb.append("			<td align=\"center\"></td>");
						 sb.append("	</tr>");			 	
							
						int intKPIL = FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"5",idNegeri,txdMula,txdAkhir);
						if(intKPIL != 0 ){
							 sb.append("<tr>");
							 sb.append("	<td width=\"70%\" align=\"right\" class="+rows+"><strong><5</strong></td>");
							 sb.append("	<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
							 sb.append("		<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=5"+strParameter+"')\">");
							 sb.append(""+intKPIL+"</a></strong></td>");
							 sb.append("</tr>");							
						 }else{
							 sb.append("<tr>");
							 sb.append("	<td width=\"70%\" align=\"right\" class="+rows+"><strong><5</strong></td>");
							 sb.append("	<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
							 sb.append("</tr>");	
						 }	
					
						 int intKPIL15 = FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"5-14",idNegeri,txdMula,txdAkhir);
						 if(intKPIL15 == 0 ){
							 sb.append("<tr>");
							 sb.append("	<td align=\"right\" class="+rows+"><strong>6-14</strong></td>");
							 sb.append("	<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("	<td align=\"right\" class="+rows+"><strong>6-14</strong></td>");
							 sb.append("	<td align=\"center\" bgcolor=yellow><strong>");
							 sb.append("		<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=5-14"+strParameter+"')\">");
							 sb.append(""+intKPIL15+"</a></strong></td>");
							 sb.append("</tr>");
						 }
							
						 int intKPIL16 = FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"16",idNegeri,txdMula,txdAkhir);
						 if(intKPIL16 == 0 ){
							 sb.append("<tr>");
							 sb.append("	<td align=\"right\" class="+rows+"><strong>>15</strong></td>");
							 sb.append("	<td align=\"center\" bgcolor=red><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("	<td align=\"right\" class="+rows+"><strong>>15</strong></td>");
							 sb.append("	<td align=\"center\" bgcolor=red><strong>");
							 sb.append("		<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=16"+strParameter+"')\">");
							 sb.append(""+intKPIL16+"</a></strong></td>");
							 sb.append("</tr>");
						 } /**/
						 	sb.append("</table>");
							sb.append("</td>");

							 
						 sb.append("</tr>");

					 }else if((Integer)f.get("giliran")== 3){
						 bil=bil+1;
						 sb.append("<tr>");
						 sb.append("<td align=\"left\" class="+rows+" bgcolor=\"#ebbc5d\">" );
						 sb.append("	<table bordercolor=\"#FAF8CC\">");
						 sb.append("	<tr>");
						 sb.append("		<td align=\"left\" class="+rows+" ><strong>"+bil+"."+f.get("keteranganheader")+strKeteranganPilih+"</strong></td>");
						 sb.append("			<td align=\"center\"></td>");
						 sb.append("	</tr>");			 	

								
						 int intKPIL= FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"8",idNegeri,txdMula,txdAkhir);
						 if(intKPIL != 0 ){
							 sb.append("<tr>");
							 sb.append("<td width=\"70%\" align=\"right\" class="+rows+"><strong><7</strong></td>");
							 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=8"+strParameter+"')\">");
							 sb.append(""+intKPIL+"</a></strong></td>");
							 sb.append("</tr>");							
						 }else{
							 sb.append("<tr>");
							 sb.append("<td width=\"70%\" align=\"right\" class="+rows+"><strong><7</strong></td>");
							 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
							 sb.append("</tr>");	
						 }	
									
						 int intKPIL15= FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"15",idNegeri,txdMula,txdAkhir);
						 if(intKPIL15 == 0 ){
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>8-14</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>8-14</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=yellow><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=15"+strParameter+"')\">");
							 sb.append(""+intKPIL15+"</a></strong></td>");
							 sb.append("</tr>");
						 }
								
						 int intKPIL16= FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"16",idNegeri,txdMula,txdAkhir);
						 if(intKPIL16  == 0 ){
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>>15</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=red><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>>15</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=red><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=16"+strParameter+"')\">");
							 sb.append(""+intKPIL16+"</a></strong></td>");
							 sb.append("</tr>");
						 }	
						 	sb.append("</table>");
							sb.append("</td>");
						 sb.append("</tr>");
													
					 }	else if((Integer)f.get("giliran")== 4){
						 bil=bil+1;
						 sb.append("<tr>");
						 sb.append("<td  class="+rows+" bgcolor=\"#ebbc5d\">" );
						 sb.append("	<table bordercolor=\"#FAF8CC\">");
						 sb.append("	<tr>");
						 sb.append("		<td align=\"left\" class="+rows+" ><strong>"+bil+"."+f.get("keteranganheader")+strKeteranganPilih+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></td>");
						 sb.append("			<td align=\"center\"></td>");
						 sb.append("	</tr>");			 	

						
						 int intKPIL = FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"30",idNegeri,txdMula,txdAkhir);
						 if(intKPIL != 0 ){
							 sb.append("<tr>");
							 sb.append("<td width=\"70%\" align=\"right\" class="+rows+"><strong><30</strong></td>");
							 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=30"+strParameter+"')\">");
							 sb.append(""+intKPIL+"</a></strong></td>");
							 sb.append("</tr>");							
						 }else{
							 sb.append("<tr>");
							 sb.append("<td width=\"70%\" align=\"right\" class="+rows+"><strong><30</strong></td>");
							 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
							 sb.append("</tr>");	
						 }	
								
						 int intKPIL15 = FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"60",idNegeri,txdMula,txdAkhir);
						 if(intKPIL15 == 0 ){
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>31-60</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>31-60</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=yellow><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=60"+strParameter+"')\">");
							 sb.append(""+intKPIL15+"</a></strong></td>");
							 sb.append("</tr>");
						 }
						 
						 int intKPIL16 = FrmKPIData.getKPIGiliranBilanganUtama(""+f.get("idsuburusanstatus"),"61",idNegeri,txdMula,txdAkhir);
						 if(intKPIL16 == 0 ){
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>>60</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=red><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>>60</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=red><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=61"+strParameter+"')\">");
							 sb.append(""+intKPIL16+"</a></strong></td>");
							 sb.append("</tr>");
						 }	 /**/
						 	sb.append("</table>");
							sb.append("</td>");
						 sb.append("</tr>");

							
					 }

				 }
				 
				 sb.append("</table>");
			 } catch (Exception ex) {
				 ex.printStackTrace();
				 throw ex;
			 }

			 return sb.toString();

		 }

		 public static String getKPIGiliranTerperinciHTMLUtamaHTP(String idUrusan,String idSuburusan,String idNegeri
				 ,String txdMula,String txdAkhir) throws Exception {
			 StringBuffer sb = new StringBuffer("");
			 try {
				 sb.append("<table width=\"100%\" border=\"0\" align=\"center\">");
				 Vector<Hashtable<String, Comparable>> v = FrmKPIData.getKeteranganUntukUtamaHTP(idUrusan,idSuburusan);
				 
				 Hashtable<String, Comparable> f = null;
				 int bil=0;
				 String strParameter = "&idnegeri="+idNegeri+"&txdMula="+txdMula+"&txdAkhir="+txdAkhir;
				 for (int i = 0; i < v.size(); i++) {
					 f = v.get(i);
					 String rows ="";
					 if ( (bil % 2) != 1 )
						 rows = "row1" ;
					 else
						 rows = "row2" ;

					 String strKeteranganX = "";
					 String strKeteranganPilih = "";
					 if((Integer)f.get("pilihan")==1)
						 strKeteranganPilih = " (pilihan) ";
					 else	 
						 strKeteranganPilih = "  ";
						 
					 if((Integer)f.get("giliran")== 2){
						 bil=bil+1;
						 sb.append("<tr>");
						 sb.append("<td align=\"\" class="+rows+" >" );
						 sb.append("	<table >");
						 sb.append("	<tr>");
						 sb.append("		<td align=\"\" class="+rows+" ><strong>"+bil+"."+f.get("keteranganheader")+strKeteranganPilih+"</strong></td>");
						 sb.append("			<td align=\"center\"></td>");
						 sb.append("	</tr>");			 	
							
						int intKPIL = FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"5",idNegeri,txdMula,txdAkhir);
						if(intKPIL != 0 ){
							 sb.append("<tr>");
							 sb.append("	<td width=\"70%\" align=\"right\" class="+rows+"><strong><5</strong></td>");
							 sb.append("	<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
							 sb.append("		<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=5"+strParameter+"')\">");
							 sb.append(""+intKPIL+"</a></strong></td>");
							 sb.append("</tr>");							
						 }else{
							 sb.append("<tr>");
							 sb.append("	<td width=\"70%\" align=\"right\" class="+rows+"><strong><5</strong></td>");
							 sb.append("	<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
							 sb.append("</tr>");	
						 }	
					
						 int intKPIL15 = FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"5-14",idNegeri,txdMula,txdAkhir);
						 if(intKPIL15 == 0 ){
							 sb.append("<tr>");
							 sb.append("	<td align=\"right\" class="+rows+"><strong>6-14</strong></td>");
							 sb.append("	<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("	<td align=\"right\" class="+rows+"><strong>6-14</strong></td>");
							 sb.append("	<td align=\"center\" bgcolor=yellow><strong>");
							 sb.append("		<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=5-14"+strParameter+"')\">");
							 sb.append(""+intKPIL15+"</a></strong></td>");
							 sb.append("</tr>");
						 }
							
						 int intKPIL16 = FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"16",idNegeri,txdMula,txdAkhir);
						 if(intKPIL16 == 0 ){
							 sb.append("<tr>");
							 sb.append("	<td align=\"right\" class="+rows+"><strong>>15</strong></td>");
							 sb.append("	<td align=\"center\" bgcolor=red><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("	<td align=\"right\" class="+rows+"><strong>>15</strong></td>");
							 sb.append("	<td align=\"center\" bgcolor=red><strong>");
							 sb.append("		<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=16"+strParameter+"')\">");
							 sb.append(""+intKPIL16+"</a></strong></td>");
							 sb.append("</tr>");
						 } /**/
						 	sb.append("</table>");
							sb.append("</td>");

							 
						 sb.append("</tr>");

					 }else if((Integer)f.get("giliran")== 3){
						 bil=bil+1;
						 sb.append("<tr>");
						 sb.append("<td align=\"left\" class="+rows+" bgcolor=\"#ebbc5d\">" );
						 sb.append("	<table bordercolor=\"#FAF8CC\">");
						 sb.append("	<tr>");
						 sb.append("		<td align=\"left\" class="+rows+" ><strong>"+bil+"."+f.get("keteranganheader")+strKeteranganPilih+"</strong></td>");
						 sb.append("			<td align=\"center\"></td>");
						 sb.append("	</tr>");			 	

								
						 int intKPIL= FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"8",idNegeri,txdMula,txdAkhir);
						 if(intKPIL != 0 ){
							 sb.append("<tr>");
							 sb.append("<td width=\"70%\" align=\"right\" class="+rows+"><strong><7</strong></td>");
							 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=8"+strParameter+"')\">");
							 sb.append(""+intKPIL+"</a></strong></td>");
							 sb.append("</tr>");							
						 }else{
							 sb.append("<tr>");
							 sb.append("<td width=\"70%\" align=\"right\" class="+rows+"><strong><7</strong></td>");
							 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
							 sb.append("</tr>");	
						 }	
									
						 int intKPIL15= FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"15",idNegeri,txdMula,txdAkhir);
						 if(intKPIL15 == 0 ){
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>8-14</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>8-14</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=yellow><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=15"+strParameter+"')\">");
							 sb.append(""+intKPIL15+"</a></strong></td>");
							 sb.append("</tr>");
						 }
								
						 int intKPIL16= FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"16",idNegeri,txdMula,txdAkhir);
						 if(intKPIL16  == 0 ){
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>>15</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=red><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>>15</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=red><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=16"+strParameter+"')\">");
							 sb.append(""+intKPIL16+"</a></strong></td>");
							 sb.append("</tr>");
						 }	
						 	sb.append("</table>");
							sb.append("</td>");
						 sb.append("</tr>");
													
					 }	else if((Integer)f.get("giliran")== 4){
						 bil=bil+1;
						 sb.append("<tr>");
						 sb.append("<td  class="+rows+" bgcolor=\"#ebbc5d\">" );
						 sb.append("	<table bordercolor=\"#FAF8CC\">");
						 sb.append("	<tr>");
						 sb.append("		<td align=\"left\" class="+rows+" ><strong>"+bil+"."+f.get("keteranganheader")+strKeteranganPilih+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></td>");
						 sb.append("			<td align=\"center\"></td>");
						 sb.append("	</tr>");			 	

						
						 int intKPIL = FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"30",idNegeri,txdMula,txdAkhir);
						 if(intKPIL != 0 ){
							 sb.append("<tr>");
							 sb.append("<td width=\"70%\" align=\"right\" class="+rows+"><strong><30</strong></td>");
							 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=30"+strParameter+"')\">");
							 sb.append(""+intKPIL+"</a></strong></td>");
							 sb.append("</tr>");							
						 }else{
							 sb.append("<tr>");
							 sb.append("<td width=\"70%\" align=\"right\" class="+rows+"><strong><30</strong></td>");
							 sb.append("<td width=\"30%\" align=\"center\" bgcolor=green><strong>0</strong></td>");
							 sb.append("</tr>");	
						 }	
								
						 int intKPIL15 = FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"60",idNegeri,txdMula,txdAkhir);
						 if(intKPIL15 == 0 ){
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>31-60</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=yellow><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>31-60</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=yellow><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=60"+strParameter+"')\">");
							 sb.append(""+intKPIL15+"</a></strong></td>");
							 sb.append("</tr>");
						 }
						 
						 int intKPIL16 = FrmKPIData.getKPIGiliranBilanganUtamaHTP(String.valueOf(f.get("idsuburusanstatus")),"61",idNegeri,txdMula,txdAkhir);
						 if(intKPIL16 == 0 ){
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>>60</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=red><strong>0</strong></td>");
							 sb.append("</tr>");
						 }else{
							 sb.append("<tr>");
							 sb.append("<td align=\"right\" class="+rows+"><strong>>60</strong></td>");
							 sb.append("<td align=\"center\" bgcolor=red><strong>");
							 sb.append("<a class=\"style1\" href=\"javascript:showWindow('ekptg.view.kpi.FrmKPIUtamaTerperinci?command=terperinci&idsuburusanstatus="+f.get("idsuburusanstatus")+"&hari=61"+strParameter+"')\">");
							 sb.append(""+intKPIL16+"</a></strong></td>");
							 sb.append("</tr>");
						 }	 /**/
						 	sb.append("</table>");
							sb.append("</td>");
						 sb.append("</tr>");

							
					 }

				 }
				 
				 sb.append("</table>");
			 } catch (Exception ex) {
				 ex.printStackTrace();
				 throw ex;
			 }

			 return sb.toString();

		 }
		 
	 
}
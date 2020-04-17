package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.utils.UniqueStringId;

public class FrmTerimePohonPendaftaranData {
	
	 public static Vector<Hashtable<String, String>> getList(String search)throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      r.add("p.id_Fail");
		      r.add("f.no_Fail");
		      r.add("f.tajuk_Fail");
		      r.add("s.keterangan");

		      r.add("p.id_Fail",r.unquote("f.id_Fail"));
		      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
		      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
		      r.add("us.id_Status",r.unquote("s.id_Status"));

		      r.add("f.id_Urusan","309");
		      r.add("sf.aktif","1");
		      r.add("f.no_Fail","%"+search+"%","like");
		      sql = r.getSQLSelect("Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s");
			  //System.out.println("FrmSenaraiFailPajakanKecilData:getList::sql:::"+sql);

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
		      Hashtable<String, String> h;

		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("id", rs.getString("id_Fail"));
		    	  h.put("no", rs.getString("no_Fail"));
		    	  h.put("tajuk", rs.getString("tajuk_Fail"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  list.addElement(h);

			        /*Tblpermohonan per = new Tblpermohonan();
			        per.setIdPermohonan(rs.getLong("id_Fail"));

			        Tblfail fail = new Tblfail();
			        fail.setKodFail(rs.getString("no_Fail"));
			        fail.setTajukFail(rs.getString("tajuk_Fail"));

			        Tblrujstatus status = new Tblrujstatus();
			        status.setKeterangan("keterangan");

			        list.addElement(per);*/
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
		 
		 public static Hashtable<String, ?> getFailInfo(String idfail)throws Exception {
			 Db db = null;
			 String sql = "";
			 new SimpleDateFormat("dd/MM/yyyy");
		 
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("a.id_Fail");
			      r.add("a.no_Fail");
			      r.add("b.id_Negeri");
			      r.add("c.id_Seksyen");
			      r.add("d.id_Urusan");
			      r.add("e.id_Suburusan");
			      r.add("f.id_Tarafkeselamatan");
			      r.add("a.tajuk_Fail");
			      r.add("a.id_Status");
			      r.add("h.id_Lokasifail");
			      r.add("i.id_Faharasat");
			      r.add("a.tarikh_Daftar_Fail");
			      r.add("a.id_Kementerian");
			      
			      r.add("a.id_Negeri",r.unquote("b.id_Negeri"));
			      r.add("a.id_Seksyen",r.unquote("c.id_Seksyen"));
			      r.add("a.id_Urusan",r.unquote("d.id_Urusan"));
			      r.add("a.id_Suburusan",r.unquote("e.id_Suburusan(+)"));
			      r.add("a.id_Tarafkeselamatan",r.unquote("f.id_Tarafkeselamatan(+)"));
			      r.add("a.id_Status",r.unquote("g.id_Status"));
			      r.add("a.id_Lokasifail",r.unquote("h.id_Lokasifail(+)"));
			      r.add("a.id_Faharasat",r.unquote("i.id_Faharasat(+)"));
			      
			      r.add("a.id_Fail",idfail);
			     
			    
			      sql = r.getSQLSelect("Tblpfdfail a, Tblrujnegeri b, Tblrujseksyen c, Tblrujurusan d, Tblrujsuburusan e, Tblpfdrujtarafkeselamatan f, Tblrujstatus g, Tblpfdrujlokasifail h, Tblpfdrujfaharasat i");
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h = null;
			      
			      while (rs.next()) {
			    	  h = new Hashtable<Object, Object>();
			    	  h.put("idFail", rs.getString("id_Fail"));
			    	  h.put("noFail", rs.getString("no_Fail"));
			    	  h.put("idNegeri",rs.getString("id_Negeri")== null?0:rs.getString("id_Negeri"));
			    	  h.put("idSeksyen",rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
			    	  h.put("idUrusan",rs.getString("id_Urusan")== null?0:rs.getString("id_Urusan"));
			    	  h.put("idSuburusan",rs.getString("id_Suburusan")== null?0:rs.getString("id_Suburusan"));
			    	  h.put("idTarafkeselamatan",rs.getString("id_Tarafkeselamatan")== null?0:rs.getString("id_Tarafkeselamatan"));
			    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
			    	  h.put("idStatus",rs.getString("id_Status")== null?0:rs.getString("id_Status"));
			    	  h.put("idLokasifail", rs.getString("id_Lokasifail")== null?0:rs.getString("id_Lokasifail"));
			    	  h.put("idFaharasat", rs.getString("id_Faharasat")== null?0:rs.getString("id_Faharasat"));
			    	  h.put("tarikhDaftar",rs.getDate("tarikh_Daftar_Fail"));
			    	   //h.put("tarikhDaftar",lebah.util.Util.getDateTime(rs.getDate("tarikh_Daftar_Fail"), "dd/MM/yyyy"));
			    	  h.put("idKementerian",rs.getString("id_Kementerian"));
			    	  //  list.addElement(h);
			      }
			      return h;
			 }
			 finally {
			      if (db != null) db.close();
			    }  
			  }

		  public static void add(String kod_cara_bayar, String keterangan, Long id_masuk,
				  Date tarikh_masuk) throws Exception {
		    Db db = null;
		    String sql = "";
		    if(tarikh_masuk==null){
		    	tarikh_masuk = new Date(); }
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      r.add("id_carabayar", UniqueStringId.get());
		      r.add("kod_cara_bayar", kod_cara_bayar);
		      r.add("keterangan", keterangan);
		      r.add("id_masuk", id_masuk);
		      r.add("tarikh_masuk", tarikh_masuk);
		      r.add("id_kemaskini", id_masuk);
		      r.add("tarikh_kemaskini", tarikh_masuk);
		      sql = r.getSQLInsert("tblrujcarabayar");
		      stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }


		  public static void update(Long id_carabayar, String kod_cara_bayar,
				  String keterangan, Long id_kemaskini, Date tarikhKemaskini) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.clear();

		      r.update("id_carabayar", ""+id_carabayar);
		      r.add("kod_cara_bayar", kod_cara_bayar);
		      r.add("keterangan", keterangan);
		      r.add("id_kemaskini", id_kemaskini);
		      r.add("tarikh_kemaskini", tarikhKemaskini);
		      sql = r.getSQLUpdate("tblrujcarabayar");
		      stmt.executeUpdate(sql);

		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }

		  public static void delete(String id_carabayar)
		    throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      //boolean found = false;
		      //sql = "select id_carabayar from faculty_subject where faculty_id = '" + id_carabayar + "'";
		      //ResultSet rs = stmt.executeQuery(sql);
		      //if (rs.next()) found = true;
		      //if (found)
		      sql = "delete from tblrujcarabayar where id_carabayar = " + id_carabayar;
		      stmt.executeUpdate(sql);
		    }
		    finally
		    {
		      if (db != null) db.close();
		    }
		  }
		  
		  @SuppressWarnings("unchecked")
		public static Vector<Hashtable<?, ?>> getFileCount(int idnegeri, int idurusan)throws Exception {
			    Db db = null;
			    String sql = "";
			    new SimpleDateFormat("dd/MM/yyyy");
			    try {
			      db = new Db();
				    System.out.println("FrmPajakanKecil::getFileCount 1");

			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
		      
			      r.add("a.id_Fail");
			      /*r.add("a.no_Fail");
			      r.add("a.tajuk_Fail");
			      r.add("a.tarikh_Daftar_Fail");
			      r.add("b.nama_Negeri");
			      r.add("c.kod_Seksyen");
			      r.add("d.keterangan");
			      r.add("e.keterangan as status");
			      */
			      r.add("a.id_Negeri",idnegeri);
			      r.add("a.id_Urusan",idurusan);
			      //r.add("a.id_Status",r.unquote("d.id_Status"));
			      //r.add("a.id_Fail",r.unquote("f.id_Fail(+)"));
			      //r.add("f.id_Status",r.unquote("e.id_Status(+)"));
			     
			
			      sql = r.getSQLSelect("Tblpfdfail a");
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h;
			      Vector list = new Vector();

			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  //h.put("bil", bil);
			    	  h.put("id_Fail",rs.getString("id_Fail"));
			    	  /*h.put("no_Fail", rs.getString("no_Fail"));
			    	  h.put("tajuk_Fail", rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
			    	  h.put("tarikh_Daftar_Fail",sdf.format(rs.getDate("tarikh_Daftar_Fail"))== null? "":sdf.format(rs.getDate("tarikh_Daftar_Fail")));
			    	  h.put("nama_Negeri", rs.getString("nama_Negeri"));
			    	  h.put("kod_Seksyen",rs.getString("kod_Seksyen"));
			    	  h.put("keterangan1", rs.getString("keterangan"));
			    	  h.put("keterangan2", rs.getString("status")== null? "":rs.getString("status"));
			    	  */
			    	  list.addElement(h);
			    	  //bil++;
			    	  
			    	  
			      }
				    System.out.println("FrmPajakanKecil::getFileCount 2");

			      return list;
			    } finally {
			      if (db != null) db.close();
			    }
			}
		  
			public static String getNegeriByMampu(int idnegeri) throws Exception {
			    Db db = null;
			    String sql = "Select id_negeri,kod_negeri,nama_negeri,kod_mampu" +
			    		" from tblrujnegeri where id_negeri="+idnegeri;
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      Tblrujnegeri f = null;
				    //System.out.println("FrmPajakanKecil::getNegeriByMampu 1");
				    while (rs.next()) {
			    	  f = new Tblrujnegeri();
			    	  f.setKodMampu(rs.getString("kod_mampu"));
			      }
				   //System.out.println("FrmPajakanKecil::getNegeriByMampu 2"+f.getKodMampu());
			      return f.getKodMampu();
			    } finally {
			      if (db != null) db.close();
			    }
			}

			public static String getKementerianByMampu(int idkementerian) throws Exception {
			    Db db = null;
			    String sql = "Select id_kementerian,kod_kementerian" +
			    		" from tblrujkementerian where id_kementerian="+idkementerian;
			    try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      Tblrujkementerian f = null;
				    while (rs.next()) {
			    	  f = new Tblrujkementerian();
			    	  f.setKodKementerian(rs.getString("kod_kementerian"));
			      }
			      return f.getKodKementerian();
			    } finally {
			      if (db != null) db.close();
			    }
			}
			
			
			 public static void janaFail(Hashtable<?, ?> data) throws Exception{
				 	Db db = null;
				    String sql = "";
				   
				    
				    new Date();
				    try{	 
				    	  //long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
				    	  long idFail = (Long)data.get("id_Fail");
				    	  String noFailLama = (String)data.get("no_Fail");
					      int negeri = (Integer)data.get("id_Negeri");
					      String seksyen = (String)data.get("id_Seksyen");
					      int urusan = (Integer)data.get("id_Urusan");
					      String suburusan = (String)data.get("id_Suburusan");
					      String taraf = (String)data.get("id_Tarafkeselamatan");
					      String tajukFail = (String)data.get("tajuk_Fail");
					      String status = (String)data.get("id_Status");
					      int flagFail = (Integer)data.get("flag_Fail");
					      int kementerian = (Integer)data.get("id_Kementerian");
					      int idmasuk = (Integer)data.get("id_Masuk");
	      
					      db = new Db();
					      Statement stmt = db.getStatement();
					      SQLRenderer r = new SQLRenderer();
					      /*ID_FAIL              NUMBER CONSTRAINT TBLPFDFAIL_C01 NOT NULL,
					      -KOD_JABATAN          VARCHAR2(10 BYTE),
					      ID_TARAFKESELAMATAN  NUMBER,
					      ID_SEKSYEN           NUMBER,
					      ID_URUSAN            NUMBER,
					      ID_SUBURUSAN         NUMBER,
					      TARIKH_DAFTAR_FAIL   DATE,
					      TAJUK_FAIL           VARCHAR2(300 BYTE),
					      NO_FAIL              VARCHAR2(400 BYTE),
					      -NO_FAIL_ROOT         VARCHAR2(400 BYTE),
					      -LOKASI               VARCHAR2(100 BYTE),
					      ID_NEGERI            NUMBER,
					      ID_KEMENTERIAN       NUMBER,
					      -FAHARASAT            VARCHAR2(100 BYTE),
					      FLAG_FAIL            NUMBER,
					      ID_STATUS            NUMBER,
					      -CATATAN              VARCHAR2(4000 BYTE),
					      ID_MASUK             NUMBER,
					      -TARIKH_MASUK         DATE,
					      -ID_KEMASKINI         NUMBER,
					      -TARIKH_KEMASKINI     DATE, */
					      r.add("id_Fail",idFail);
					      //r.add("kod_jabatan","JKPTG");
					      r.add("id_Tarafkeselamatan", taraf);
					      r.add("id_Seksyen", seksyen);
					      r.add("id_Urusan", urusan);
					      r.add("id_Suburusan", suburusan);
					      r.add("tarikh_Daftar_Fail",r.unquote("sysdate")); 
					      r.add("tajuk_Fail", tajukFail);
					      r.add("no_Fail", noFailLama);
					      r.add("no_fail_root", noFailLama);
					      r.add("id_Lokasifail", "1");
					      //r.add("lokasi", "TIADA");
					      r.add("id_Negeri", negeri);
					      r.add("id_Kementerian", kementerian);
					      r.add("id_Faharasat", "1");
					      //r.add("Faharasat", "TIADA");
					      r.add("flag_Fail", flagFail);
					      r.add("id_Status", status);
					      r.add("catatan", "TIADA");
					      r.add("id_Masuk",idmasuk);				      
					      r.add("tarikh_Masuk",r.unquote("sysdate")); 
					      r.add("id_Kemaskini",idmasuk);				      
					      r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
					      
					      sql = r.getSQLInsert("tblpfdfail");
					      System.out.println("FrmSenaraiFailPajakanKecilData:janaFail::sql:::"+sql);
					      stmt.executeUpdate(sql);
					    } finally {
					      if (db != null) db.close();
					    }
					     
					      
				    }

			  public static void StatusChange_Action(long idPermohonan, int idSuburusan){
				  int PPP = Integer.parseInt("31"); //PINJAMAN PERUMAHAN PERBENDAHARAAN
				  int PKRJPN = Integer.parseInt("57"); //PINJAMAN KOS RENDAH JABATAN PERUMAHAN NEGARA
				  int TPM = Integer.parseInt("60"); //PINJAMAN TMP & JPK
				  int PHG = Integer.parseInt("63"); //PERLETAKHAKAN HAK GADAIAN
				  String aktif = "1";
				  Date now = new Date();
				  SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
				  String sekarang = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
				  
				  Db db = null;
				  String sql = "";
				  
				  try{
					  long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
					  
					  db = new Db();
					  Statement stmt = db.getStatement();
					  SQLRenderer r = new SQLRenderer();
					  
					  r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
					  r.add("id_Permohonan", idPermohonan);
					  
					  if(idSuburusan == 38){
						  r.add("Id_Suburusanstatus", PPP);
					  }else if(idSuburusan == 39){
						  r.add("Id_Suburusanstatus", PKRJPN);
					  }else if(idSuburusan == 40){
						  r.add("Id_Suburusanstatus", TPM);
					  }else{
						  r.add("Id_Suburusanstatus", PHG);
					  }			  
					  r.add("aktif",aktif);
					  r.add("id_Masuk", idPermohonan);
					  r.add("tarikh_Masuk", r.unquote(sekarang));
					  
					  sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
				      System.out.println("FrmGadaianSemakanData::StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
				      stmt.executeUpdate(sql);
				  }catch(Exception ex){
					  System.out.println("FrmGadaianSemakanData::StatusChange_Action::ex = "+ex);
				  }finally{
					  if (db != null) db.close();
				  }		  
			  }  
			  
			public static String SelectNegeri(String selectName) throws Exception	{
				return SelectNegeri(selectName,null,null,null);
			}
			
			public static String SelectNegeri(String selectName,String jsFunction) throws Exception	{
				return SelectNegeri(selectName,null,null,jsFunction);	
			}
			
			public static String SelectNegeri(String selectName,Long selectedValue) throws Exception	{
				return SelectNegeri(selectName,selectedValue,null,null);
			}
			
			public static String SelectNegeri(String selectName,Long selectedValue, String disability) throws Exception	{
				return SelectNegeri(selectName,selectedValue,disability,null);
			}	
			public static String SelectNegeri(String selectName,Long selectedValue, String disability,String jsFunction) throws Exception {
				StringBuffer sb = new StringBuffer("");
				try {
					sb.append("<select name='"+ selectName +"'");
					if (disability != null) sb.append(disability);
					if (jsFunction != null) sb.append(jsFunction);
					sb.append(" > ");
					sb.append("<option value=>Sila Pilih Negeri</option>\n");
					Vector<Tblrujnegeri>  v = getNegeriPajakan("(12,13,15)");
					Tblrujnegeri f = null;
					String s = "";
					for(int i=0; i< v.size() ; i++) {
						f = (Tblrujnegeri)v.get(i);
						if (f.getIdNegeri().equals(selectedValue)) {
							s = "selected";
						} else {
							s = "";
						}
						sb.append("<option "+s+" value="+ f.getIdNegeri() +">" + f.getKodMampu() + "-" + f.getNamaNegeri()+ "</option>\n");
					}
					sb.append("</select>");
				} catch (Exception ex) {
				      ex.printStackTrace();
				      throw ex;
				}
				
				return sb.toString();	
			}
			/*Created on 21/05/2009
			 * by Mohamad Rosli
			 */
			   public static Vector<Tblrujnegeri> getNegeriPajakan(String id_Negeri) throws Exception {
				    Db db = null;
				    String sql = " SELECT id_Negeri, kod_Negeri, nama_Negeri,kod_mampu "+
				    "FROM tblrujnegeri WHERE id_Negeri in (12,13,15)";
				    try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_Negeri");
				      r.add("kod_Negeri");
				      r.add("nama_Negeri");
				      r.add("kod_Mampu");
				      if(id_Negeri!=null){
				    	  r.add("id_Negeri","15"," = 12 or id_Negeri=13 or id_Negeri=");
				    	  //r.add("id_Negeri in(","13,12)","");
				    	  //r.add(") id_Negeri","12 "," in");
				    	  
				    	  //r.add("id_Negeri",r.unquote("(12,13,15)"));
				    	  //sa = r.unquote("(12,13,15)").toString();
				    	 //r.add("id_Negeri",r.unquote("(12,13,15)"));
				      }
				      sql = r.getSQLSelect(" tblrujnegeri", "id_negeri");
				      //System.out.print("getNegeriPajakan:s::"+sa);
				      //System.out.print("getNegeriPajakan:"+sql);
				      /**/ResultSet rs = stmt.executeQuery(sql);

				      Vector<Tblrujnegeri> v = new Vector<Tblrujnegeri>();
				      Tblrujnegeri s = null;
				      while (rs.next()) {
				        s = new Tblrujnegeri();
				        s.setIdNegeri(rs.getLong("id_Negeri"));
				        s.setKodNegeri(rs.getString("kod_Negeri"));
				        s.setNamaNegeri(rs.getString("nama_Negeri"));    
				        s.setKodMampu(rs.getString("kod_Mampu"));    
				        v.addElement(s);
				      }
				      return v;
				    }
				    finally {
				      if (db != null) db.close();
				    }
				  }


}

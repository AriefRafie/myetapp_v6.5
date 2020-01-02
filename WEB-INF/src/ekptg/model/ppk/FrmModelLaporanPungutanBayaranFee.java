package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
/*
 * @author
 * Muhamad Syazreen bin Yahaya
 */

public class FrmModelLaporanPungutanBayaranFee {

	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector list = new Vector();
	private static Vector listData = new Vector();
	
	public static Vector getUnitJkptg(){
		return list;
	}
	public static Vector getDataSemak(){
		return listData;
	}
	
	// data unit jkptg
	public static void setUnitJkptg(String id_jkptg)throws Exception {
		Db db = null;
		list.clear();
		String sql = "";

		try{	
		    db = new Db();
			Statement stmt = db.getStatement();
			
			sql =  "Select id_pejabatjkptg,id_daerah,nama_pejabat,alamat1 from tblrujpejabatjkptg";
			sql += " where id_seksyen = 2 ";
			sql += " and id_pejabatjkptg = "+id_jkptg ;
			
			ResultSet rx = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rx.next()) {				
				h = new Hashtable();
				h.put("id_daerah", rx.getString("id_daerah")==null?"":rx.getString("id_daerah"));				
				list.addElement(h);					
			  	}      
			}
			finally{
				if(db != null)db.close();
			}
	}//close unit jkptg
	 
	//semak by id_negeri n id_daerah
	public static void setDataSemak(String id_negeri,String id_daerah,String tahun,String bulan, String idjkptg)throws Exception {
		Db db = null;
		listData.clear();
		String sql = "";

		
		try{	
		    db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT f.nama_pejabat, f.alamat1,NVL (SUM (AA.bayaran_perintah),'0') as bayaran_perintah, "+
					"NVL(SUM (BB.bayaran_salinan_perintah),'0') as bayaran_salinan_perintah,NVL (SUM (CC.fee_rayuan),'0') as fee_rayuan, "+
					"NVL (SUM (DD.bayaran_salinan_dokumen),'0') as bayaran_salinan_dokumen,NVL (SUM (EE.duti),'0') as duti, "+
					"NVL (SUM (FF.bayaran_baitulmal),'0') as bayaran_baitulmal "+
                
                                                      
					"FROM tblrujpejabatjkptg f, tblrujpejabaturusan g, "+ 
                             
                            

                   "(SELECT NVL (SUM (a.jumlah_bayaran),0) as bayaran_perintah,b.id_negerimhn, b.id_daerahmhn "+
                             
                   "FROM tblppkbayaran a,tblppkpermohonan b, tblpfdfail c "+
                   "WHERE a.id_jenisbayaran = 24 "+
                    "and a.id_permohonan = b.id_permohonan "+
                    "and b.id_fail = c.id_fail "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+tahun+"' "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+bulan+"' "+
                    "group by b.id_negerimhn, b.id_daerahmhn) AA, "+
                    
                   "(SELECT NVL (SUM (a.jumlah_bayaran), 0) as bayaran_salinan_perintah,b.id_negerimhn, b.id_daerahmhn "+
                   "FROM tblppkbayaran a, "+
                 		"tblppkpermohonan b, "+
                        "tblpfdfail c "+
                   "WHERE a.id_jenisbayaran = 25 "+
                    "AND c.id_fail = b.id_fail "+
                    "AND a.id_permohonan = b.id_permohonan "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+tahun+"' "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+bulan+"' "+
                    "group by b.id_negerimhn, b.id_daerahmhn) BB, "+
                                                          
                   "(SELECT NVL (SUM (a.jumlah_bayaran), 0) as fee_rayuan,b.id_negerimhn, b.id_daerahmhn "+
                   "FROM tblppkbayaran a, "+
                        "tblppkpermohonan b, "+
                        "tblpfdfail c "+
                   "WHERE a.id_jenisbayaran = 3 "+
                    "AND c.id_fail = b.id_fail "+
                    "AND a.id_permohonan = b.id_permohonan "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+tahun+"' "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+bulan+"' "+
                    "group by b.id_negerimhn, b.id_daerahmhn) CC, "+
                                                                
                   "(SELECT NVL (SUM (a.jumlah_bayaran), 0) as bayaran_salinan_dokumen,b.id_negerimhn, b.id_daerahmhn "+
                   "FROM tblppkbayaran a, "+
                        "tblppkpermohonan b, "+
                        "tblpfdfail c "+
                   "WHERE a.id_jenisbayaran = 24 "+
                    "AND c.id_fail = b.id_fail "+
                    "AND a.id_permohonan = b.id_permohonan "+
                    "AND a.id_jenisbayaran IN (5, 6) "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+tahun+"' "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+bulan+"' "+
                    "group by b.id_negerimhn, b.id_daerahmhn) DD, "+
                                                           
                   "(SELECT NVL (SUM (a.jumlah_bayaran), 0) as duti,b.id_negerimhn, b.id_daerahmhn "+
                   "FROM tblppkbayaran a, "+
                        "tblppkpermohonan b, "+
                        "tblpfdfail c "+
                   "WHERE a.id_jenisbayaran = 26 "+
                    "AND c.id_fail = b.id_fail "+
                    "AND a.id_permohonan = b.id_permohonan "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+tahun+"' "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+bulan+"' "+
                    "group by b.id_negerimhn, b.id_daerahmhn) EE, "+
                    
                   "(SELECT NVL (SUM (a.jumlah_bayaran), 0) as bayaran_baitulmal,b.id_negerimhn, b.id_daerahmhn "+
                   "FROM tblppkbayaran a, "+
                        "tblppkpermohonan b, "+
                        "tblpfdfail c "+
                   "WHERE a.id_jenisbayaran = 29 "+
                    "AND c.id_fail = b.id_fail "+
                    "AND a.id_permohonan = b.id_permohonan "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+tahun+"' "+
                    "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+bulan+"' "+
                    "group by b.id_negerimhn, b.id_daerahmhn) FF "+
                                                       
			          "WHERE f.id_pejabatjkptg = '"+idjkptg+ "' "+
			            "and f.id_pejabatjkptg = g.id_pejabatjkptg "+
			            "AND f.id_seksyen = g.id_seksyen "+
			            "and g.id_negeriurus = AA.id_negerimhn(+) "+
			            "and g.id_daerahurus = AA.id_daerahmhn(+) "+
			            "and g.id_negeriurus = BB.id_negerimhn(+) "+
			            "and g.id_daerahurus = BB.id_daerahmhn(+) "+
			            "and g.id_negeriurus = CC.id_negerimhn(+) "+
			            "and g.id_daerahurus = CC.id_daerahmhn(+) "+
			            "and g.id_negeriurus = DD.id_negerimhn(+) "+
			            "and g.id_daerahurus = DD.id_daerahmhn(+) "+
			            "and g.id_negeriurus = EE.id_negerimhn(+) "+
			            "and g.id_daerahurus = EE.id_daerahmhn(+) "+
			            "and g.id_negeriurus = FF.id_negerimhn(+) "+
			            "and g.id_daerahurus = FF.id_daerahmhn(+) "+
			            "and f.id_seksyen = 2 "+
			            
			            "group by f.nama_pejabat, f.alamat1";
            
            
            
//				"SELECT distinct f.nama_pejabat, f.alamat1, f.alamat2, f.alamat3, f.poskod, f.no_tel, "+
//			       "f.id_daerah, f.id_negeri, "+
//			       "(select nvl(sum(a.jumlah_bayaran),0) "+  
//			             "from tblppkbayaran a, tblppkpermohonan b, tblpfdfail c "+ 
//			             "where a.id_jenisbayaran = 24 "+ 
//			             "and c.id_fail = b.id_fail "+
//			             "and a.id_permohonan = b.id_permohonan "+ 
//			             "and b.id_negerimhn = '"+id_negeri +"'"+
//			             "and b.id_daerahmhn = '"+id_daerah +"'"+
//			             "and c.id_seksyen = 2 "+
//			             "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+year+"' "+
//			             "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+month+"'"+")"+
//			                                                      "AS pengeluaran_perintah, "+
//			       "(SELECT NVL (SUM (a.jumlah_bayaran), 0) "+ 
//			         "from tblppkbayaran a, tblppkpermohonan b, tblpfdfail c "+ 
//			             "where a.id_jenisbayaran = 25 "+
//			             "and c.id_fail = b.id_fail "+
//			             "and a.id_permohonan = b.id_permohonan "+ 
//			             "and b.id_negerimhn = '"+id_negeri+ "'"+
//			             "and b.id_daerahmhn = '"+id_daerah+ "'"+
//			             "and c.id_seksyen = 2 "+
//			             "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+year+"' "+
//			             "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+month+"'" + ") AS salinan_perintah, "+
//			             
//			       "(SELECT NVL (SUM (a.jumlah_bayaran), 0) "+
//			          "from tblppkbayaran a, tblppkpermohonan b, tblpfdfail c "+ 
//			             "where a.id_jenisbayaran = 3 "+
//			             "and c.id_fail = b.id_fail "+
//			             "and a.id_permohonan = b.id_permohonan "+
//			             "and b.id_negerimhn = '"+id_negeri+"'"+ 
//			             "and b.id_daerahmhn = '"+id_daerah+"'"+ 
//			             "and c.id_seksyen = 2 "+
//			           "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+year+"'"+
//			           "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+month+"'"+") AS fee_rayuan, "+
//			       "(SELECT NVL (SUM (a.jumlah_bayaran), 0) "+
//			          "from tblppkbayaran a, tblppkpermohonan b, tblpfdfail c "+ 
//			             "where a.id_jenisbayaran = 24 "+ 
//			             "and c.id_fail = b.id_fail "+
//			             "and a.id_permohonan = b.id_permohonan "+
//			             "and b.id_negerimhn = '"+id_negeri+"'"+ 
//			             "and b.id_daerahmhn = '"+id_daerah+"'"+ 
//			             "and c.id_seksyen = 2 "+
//			           "AND a.id_jenisbayaran IN (5, 6) "+
//			           "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+year+"'"+
//			           "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+month+"'"+") AS salinan_dokumen, "+
//			       "(SELECT NVL (SUM (a.jumlah_bayaran), 0) "+
//			         "from tblppkbayaran a, tblppkpermohonan b, tblpfdfail c "+ 
//			             "where a.id_jenisbayaran = 26 "+ 
//			             "and c.id_fail = b.id_fail "+
//			             "and a.id_permohonan = b.id_permohonan "+
//			             "and b.id_negerimhn = '"+id_negeri+"'"+ 
//			             "and b.id_daerahmhn = '"+id_daerah+"'"+ 
//			             "and c.id_seksyen = 2 "+
//			           "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+year+"'"+
//			           "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+month+"'"+") AS duti, "+
//			       "(SELECT NVL (SUM (a.jumlah_bayaran), 0) "+
//			          "from tblppkbayaran a, tblppkpermohonan b, tblpfdfail c "+ 
//			             "where a.id_jenisbayaran = 29 "+ 
//			             "and c.id_fail = b.id_fail "+
//			             "and a.id_permohonan = b.id_permohonan "+
//			             "and b.id_negerimhn = '"+id_negeri+"' "+
//			             "and b.id_daerahmhn = '"+id_daerah+"' "+ 
//			             "and c.id_seksyen = 2 "+
//			           "AND TO_CHAR (a.tarikh_bayaran, 'yyyy') = '"+year+"' "+
//			           "AND TO_CHAR (a.tarikh_bayaran, 'mm') = '"+month+"'"+") AS baitulmal "+
//			       "FROM tblrujpejabatjkptg f,tblrujpejabaturusan g "+
//					 "WHERE f.id_pejabatjkptg = 23 "+
//					 "AND F.ID_NEGERI = '"+id_negeri+ "' "+
//					 "AND f.id_daerah = '"+id_daerah+ "' "+
//					 "AND F.ID_SEKSYEN = g.id_seksyen";
 
			

						
			System.out.println("[sql]: "+sql);
			ResultSet rx = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rx.next()) {				
				h = new Hashtable();
				h.put("pengeluaran_perintah", rx.getString("bayaran_perintah"));				
				h.put("salinan_perintah", rx.getString("bayaran_salinan_perintah"));				
				h.put("fee_rayuan", rx.getString("fee_rayuan"));				
				h.put("salinan_dokumen", rx.getString("bayaran_salinan_dokumen"));				
				h.put("duti", rx.getString("duti"));				
				h.put("baitulmal", rx.getString("bayaran_baitulmal"));				
				listData.addElement(h);					
			  	}      
			}
			finally{
				if(db != null)db.close();
			}
	}//close semak
	
	public String getBulan(int i){
		String m="";
		String[]names = {"01", "02","03", "04", "05","06", "07", "08","09", "10", "11","12"};
		m = names[i-1];
		return m;
	}
	
	public String getTahun(int bulan,int i){
		String m="";
		if(bulan==1) 
			m = ""+(i-1);
		else
			m = ""+i;
		return m;
	}
	
}//close class

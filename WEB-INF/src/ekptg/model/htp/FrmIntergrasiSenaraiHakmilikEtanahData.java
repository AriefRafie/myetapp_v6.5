/**
 * 
 */
package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

/**
 * @author mohd faizal
 *
 */
public class FrmIntergrasiSenaraiHakmilikEtanahData {
	
	private Vector senaraiHakmilik = null;	
	private Vector beanMaklumatHakmilik = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void carianHakmilik(String idHakmilik) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT *"
					
				+ " FROM INT_HTPHAKMILIK HM";
			
			//idHakmilik
			if (idHakmilik != null) {
				if (!idHakmilik.trim().equals("")) {
					sql = sql + " WHERE HM.ID_HAKMILIK = '" + idHakmilik.trim().toUpperCase() + "'";
				}
			}
						
			sql = sql + " ORDER BY HM.TARIKH_TERIMA ASC";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));		
				h.put("idLuas", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				h.put("luas", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));		
				h.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));					
				h.put("noSyit", rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT").toUpperCase());
				h.put("noPelan", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN").toUpperCase());					
				h.put("syarat", rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("SYARAT_NYATA").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());				
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());				
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("idSubKategori", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));				
				h.put("lokasi", rs.getString("LOKASI") == null ? "" : rs.getString("LOKASI").toUpperCase());
				h.put("noBangunan", rs.getString("NO_BANGUNAN") == null ? "" : rs.getString("NO_BANGUNAN"));				
				h.put("noTingkat", rs.getString("NO_TINGKAT") == null ? "" : rs.getString("NO_TINGKAT"));	
				h.put("noPetak", rs.getString("NO_PETAK") == null ? "" : rs.getString("NO_PETAK"));	
				h.put("cukai", rs.getString("CUKAI") == null ? "" : rs.getString("CUKAI"));	
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR")));				
				h.put("tarafHakmilik", rs.getString("TARAF_HAKMILIK") == null ? "" : rs.getString("TARAF_HAKMILIK"));	
				h.put("tempoh", rs.getString("TEMPOH") == null ? "" : rs.getString("TEMPOH"));	
				h.put("tarikhLuput", rs.getDate("TARIKH_LUPUT") == null ? "" : sdf.format(rs.getDate("TARIKH_LUPUT")));					
				h.put("noPU", rs.getString("NO_PU") == null ? "" : rs.getString("NO_PU"));	
				h.put("statusPajakan", rs.getString("STATUS_PAJAKAN") == null ? "" : rs.getString("STATUS_PAJAKAN"));	
				h.put("tarikhTamatPajakan", rs.getDate("TARIKH_TAMAT_PAJAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT_PAJAKAN")));					
				h.put("statusRizab", rs.getString("STATUS_RIZAB") == null ? "" : rs.getString("STATUS_RIZAB"));	
				h.put("idJenisRizab", rs.getString("ID_JENISRIZAB") == null ? "" : rs.getString("ID_JENISRIZAB"));	
				h.put("kawasanRizab", rs.getString("KAWASAN_RIZAB") == null ? "" : rs.getString("KAWASAN_RIZAB"));	
				h.put("noFailPTD", rs.getString("NO_FAIL_PTD") == null ? "" : rs.getString("NO_FAIL_PTD"));	
				h.put("noFailPTG", rs.getString("NO_FAIL_PTG") == null ? "" : rs.getString("NO_FAIL_PTG"));	
				h.put("statusHakmilik", rs.getString("STATUS_HAKMILIK") == null ? "" : rs.getString("STATUS_HAKMILIK"));	
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));	
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));	
				h.put("flagTerima", rs.getString("FLAG_TERIMA") == null ? "" : rs.getString("FLAG_TERIMA"));
				senaraiHakmilik.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}		
	}
	
	public void setMaklumatHakmilik(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT *"
				
				+ " FROM INT_HTPHAKMILIK HM"
				
				+ " WHERE HM.ID_HAKMILIK = '" + idHakmilik + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
		
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));		
				h.put("idLuas", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				h.put("luas", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));				
				h.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));					
				h.put("noSyit", rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT").toUpperCase());
				h.put("noPelan", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN").toUpperCase());					
				h.put("syarat", rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("SYARAT_NYATA").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());				
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());				
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("idSubKategori", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));				
				h.put("lokasi", rs.getString("LOKASI") == null ? "" : rs.getString("LOKASI").toUpperCase());
				h.put("noBangunan", rs.getString("NO_BANGUNAN") == null ? "" : rs.getString("NO_BANGUNAN"));				
				h.put("noTingkat", rs.getString("NO_TINGKAT") == null ? "" : rs.getString("NO_TINGKAT"));	
				h.put("noPetak", rs.getString("NO_PETAK") == null ? "" : rs.getString("NO_PETAK"));	
				h.put("cukai", rs.getString("CUKAI") == null ? "" : rs.getString("CUKAI"));	
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR")));				
				h.put("tarafHakmilik", rs.getString("TARAF_HAKMILIK") == null ? "" : rs.getString("TARAF_HAKMILIK"));	
				h.put("tempoh", rs.getString("TEMPOH") == null ? "" : rs.getString("TEMPOH"));	
				h.put("tarikhLuput", rs.getDate("TARIKH_LUPUT") == null ? "" : sdf.format(rs.getDate("TARIKH_LUPUT")));					
				h.put("noPU", rs.getString("NO_PU") == null ? "" : rs.getString("NO_PU"));	
				h.put("statusPajakan", rs.getString("STATUS_PAJAKAN") == null ? "" : rs.getString("STATUS_PAJAKAN"));	
				h.put("tarikhTamatPajakan", rs.getDate("TARIKH_TAMAT_PAJAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT_PAJAKAN")));					
				h.put("statusRizab", rs.getString("STATUS_RIZAB") == null ? "" : rs.getString("STATUS_RIZAB"));	
				h.put("idJenisRizab", rs.getString("ID_JENISRIZAB") == null ? "" : rs.getString("ID_JENISRIZAB"));	
				h.put("kawasanRizab", rs.getString("KAWASAN_RIZAB") == null ? "" : rs.getString("KAWASAN_RIZAB"));	
				h.put("noFailPTD", rs.getString("NO_FAIL_PTD") == null ? "" : rs.getString("NO_FAIL_PTD"));	
				h.put("noFailPTG", rs.getString("NO_FAIL_PTG") == null ? "" : rs.getString("NO_FAIL_PTG"));	
				h.put("statusHakmilik", rs.getString("STATUS_HAKMILIK") == null ? "" : rs.getString("STATUS_HAKMILIK"));	
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));	
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));	
				h.put("flagTerima", rs.getString("FLAG_TERIMA") == null ? "" : rs.getString("FLAG_TERIMA"));	

				beanMaklumatHakmilik.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getSenaraiHakmilik() {
		return senaraiHakmilik;
	}
	public void setSenaraiHakmilik(Vector senaraiHakmilik) {
		this.senaraiHakmilik = senaraiHakmilik;
	}
	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}
	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}

}

package ekptg.model.online.aduan;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class ComplaintActivityBean implements IComplaintActivityObserver {
	protected static Logger log = Logger.getLogger(ComplaintActivityBean.class);
	@Override
	public void setActivity(IComplaintActivityHandler activity) {
		log.info("setting aduan activity");
		String aktiviti = "";
		String aktivityType = activity.getActivityType();
		if(aktivityType.equals("ADUAN_BARU_ONLINE")){
			aktiviti ="penerimaan aduan secara online";
		}
		if(aktivityType.equals("SEMAKKAN_SEKSYEN")){
			aktiviti = "diagih kepada seksyen yang bertanggungjawab";
		}
		if(aktivityType.equals("ADUAN_BARU_OFFLINE")){
			aktiviti = "Penerimaan aduan secara manual";
		}
		if(aktivityType.equals("SEKSYEN_RESPON")){
			aktiviti = activity.activityRemarks()+" telah memberi maklumbalas kepada agihan";
		}
		if(aktivityType.equals("TUTUP_ADUAN")){
			aktiviti = "aduan telah diselesaikan";
		}
		if(aktivityType.equals("PTG_RESPONSE")){
			aktiviti = "maklumbalas dari PTG telah diperolehi";
		}
		Db db = null;
		try{
			Date now = new Date();
			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
	    	String today = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
	    	
			db = new Db();
			Statement stat = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long aktivitiId = DB.getNextID("TBLONLINEAKTIVITIADUAN_SEQ");
			r.add("ID_AKTIVITIADUAN",aktivitiId);
			r.add("TARIKH_MASUK",r.unquote(today));
			r.add("ID_MASUK",activity.loginId());
			r.add("ID_EADUAN",activity.getComplaintNo());
			r.add("AKTIVITI",aktiviti);
			String sql = r.getSQLInsert("TBLONLINEAKTIVITIADUAN");
			stat.executeUpdate(sql);
			
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
	}

}

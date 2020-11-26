package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.CacheManager;
import ekptg.engine.CachedObject;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging2;
import ekptg.helpers.Utils;
import ekptg.model.RazTemplete;
import ekptg.model.ppk.BicaraInteraktifData;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8BicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;
import ekptg.view.ppk.FrmTukarPegawaiBI2;

public class FrmTukarPegawaiBI2Data {

}


/*
 * List fail tukar pegawai 2:
 * 1. ekptg.view.ppk.FrmTukarPegawaiBI2
 * 2. app/ppk/TukarPegawai2/FrmPrmhnnTukarPegawai2.jsp
 * 3. app/ppk/TukarPegawai2/historyTukarPegawai2.jsp
 * 4. app/ppk/TukarPegawai2/listTukarPegawai2.jsp
 * 5. ekptg.model.ppk.FrmTukarPegawaiBI2Data
 * */
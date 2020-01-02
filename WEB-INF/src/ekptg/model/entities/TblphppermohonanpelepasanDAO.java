package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppermohonanpelepasan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphppermohonanpelepasan
 * @author MyEclipse Persistence Tools
 */

public class TblphppermohonanpelepasanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppermohonanpelepasanDAO.class);
	// property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String KETERANGAN = "keterangan";
	public static final String NO_RUJUKAN_SURAT = "noRujukanSurat";
	public static final String LOKASI = "lokasi";
	public static final String NAMA_PROJEK = "namaProjek";
	public static final String FLAG_JENIS_PROJEK = "flagJenisProjek";
	public static final String TAJUK_PERMOHONAN = "tajukPermohonan";
	public static final String ID_UNITLUASMHN = "idUnitluasmhn";
	public static final String LUAS_MHN = "luasMhn";
	public static final String ID_UNITLUASBAKI = "idUnitluasbaki";
	public static final String LUAS_BAKI = "luasBaki";
	public static final String KEMAJUAN_TANAH = "kemajuanTanah";
	public static final String FLAG_GUNA = "flagGuna";
	public static final String ID_UNITLUASDILULUSKAN = "idUnitluasdiluluskan";
	public static final String LUAS_DILULUSKAN = "luasDiluluskan";
	public static final String ID_UNITLUASBAKIDILULUSKAN = "idUnitluasbakidiluluskan";
	public static final String LUAS_BAKI_DILULUSKAN = "luasBakiDiluluskan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblphppermohonanpelepasan transientInstance) {
		log.debug("saving Tblphppermohonanpelepasan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppermohonanpelepasan persistentInstance) {
		log.debug("deleting Tblphppermohonanpelepasan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppermohonanpelepasan findById(java.lang.Long id) {
		log.debug("getting Tblphppermohonanpelepasan instance with id: " + id);
		try {
			Tblphppermohonanpelepasan instance = (Tblphppermohonanpelepasan) getSession()
					.get("ekptg.model.entities.Tblphppermohonanpelepasan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppermohonanpelepasan instance) {
		log.debug("finding Tblphppermohonanpelepasan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppermohonanpelepasan").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tblphppermohonanpelepasan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppermohonanpelepasan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByNoRujukanSurat(Object noRujukanSurat) {
		return findByProperty(NO_RUJUKAN_SURAT, noRujukanSurat);
	}

	public List findByLokasi(Object lokasi) {
		return findByProperty(LOKASI, lokasi);
	}

	public List findByNamaProjek(Object namaProjek) {
		return findByProperty(NAMA_PROJEK, namaProjek);
	}

	public List findByFlagJenisProjek(Object flagJenisProjek) {
		return findByProperty(FLAG_JENIS_PROJEK, flagJenisProjek);
	}

	public List findByTajukPermohonan(Object tajukPermohonan) {
		return findByProperty(TAJUK_PERMOHONAN, tajukPermohonan);
	}

	public List findByIdUnitluasmhn(Object idUnitluasmhn) {
		return findByProperty(ID_UNITLUASMHN, idUnitluasmhn);
	}

	public List findByLuasMhn(Object luasMhn) {
		return findByProperty(LUAS_MHN, luasMhn);
	}

	public List findByIdUnitluasbaki(Object idUnitluasbaki) {
		return findByProperty(ID_UNITLUASBAKI, idUnitluasbaki);
	}

	public List findByLuasBaki(Object luasBaki) {
		return findByProperty(LUAS_BAKI, luasBaki);
	}

	public List findByKemajuanTanah(Object kemajuanTanah) {
		return findByProperty(KEMAJUAN_TANAH, kemajuanTanah);
	}

	public List findByFlagGuna(Object flagGuna) {
		return findByProperty(FLAG_GUNA, flagGuna);
	}

	public List findByIdUnitluasdiluluskan(Object idUnitluasdiluluskan) {
		return findByProperty(ID_UNITLUASDILULUSKAN, idUnitluasdiluluskan);
	}

	public List findByLuasDiluluskan(Object luasDiluluskan) {
		return findByProperty(LUAS_DILULUSKAN, luasDiluluskan);
	}

	public List findByIdUnitluasbakidiluluskan(Object idUnitluasbakidiluluskan) {
		return findByProperty(ID_UNITLUASBAKIDILULUSKAN,
				idUnitluasbakidiluluskan);
	}

	public List findByLuasBakiDiluluskan(Object luasBakiDiluluskan) {
		return findByProperty(LUAS_BAKI_DILULUSKAN, luasBakiDiluluskan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblphppermohonanpelepasan instances");
		try {
			String queryString = "from Tblphppermohonanpelepasan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppermohonanpelepasan merge(
			Tblphppermohonanpelepasan detachedInstance) {
		log.debug("merging Tblphppermohonanpelepasan instance");
		try {
			Tblphppermohonanpelepasan result = (Tblphppermohonanpelepasan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppermohonanpelepasan instance) {
		log.debug("attaching dirty Tblphppermohonanpelepasan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppermohonanpelepasan instance) {
		log.debug("attaching clean Tblphppermohonanpelepasan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
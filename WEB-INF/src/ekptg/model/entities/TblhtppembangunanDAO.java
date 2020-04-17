package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppembangunan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppembangunan
 * @author MyEclipse Persistence Tools
 */

public class TblhtppembangunanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtppembangunanDAO.class);
	// property constants
	public static final String ID_HAKMILIKPEGANGAN = "idHakmilikpegangan";
	public static final String JENIS_BINAAN = "jenisBinaan";
	public static final String NO_RUJJKR = "noRujjkr";
	public static final String HARGA_BINAAN = "hargaBinaan";
	public static final String CATATAN = "catatan";
	public static final String UNIT_LUAS = "unitLuas";
	public static final String LUAS = "luas";
	public static final String LUAS_HEKTAR = "luasHektar";
	public static final String LUAS_JALAN = "luasJalan";
	public static final String LUAS_PADANG = "luasPadang";
	public static final String LUAS_BGN = "luasBgn";
	public static final String LUAS_PARKING = "luasParking";
	public static final String LUAS_LAIN = "luasLain";
	public static final String LUAS_ASAL = "luasAsal";
	public static final String ID_MASUK = "idMasuk";

	public void save(Tblhtppembangunan transientInstance) {
		log.debug("saving Tblhtppembangunan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppembangunan persistentInstance) {
		log.debug("deleting Tblhtppembangunan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppembangunan findById(java.lang.Long id) {
		log.debug("getting Tblhtppembangunan instance with id: " + id);
		try {
			Tblhtppembangunan instance = (Tblhtppembangunan) getSession().get(
					"ekptg.model.entities.Tblhtppembangunan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppembangunan instance) {
		log.debug("finding Tblhtppembangunan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppembangunan").add(
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
		log.debug("finding Tblhtppembangunan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppembangunan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdHakmilikpegangan(Object idHakmilikpegangan) {
		return findByProperty(ID_HAKMILIKPEGANGAN, idHakmilikpegangan);
	}

	public List findByJenisBinaan(Object jenisBinaan) {
		return findByProperty(JENIS_BINAAN, jenisBinaan);
	}

	public List findByNoRujjkr(Object noRujjkr) {
		return findByProperty(NO_RUJJKR, noRujjkr);
	}

	public List findByHargaBinaan(Object hargaBinaan) {
		return findByProperty(HARGA_BINAAN, hargaBinaan);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByUnitLuas(Object unitLuas) {
		return findByProperty(UNIT_LUAS, unitLuas);
	}

	public List findByLuas(Object luas) {
		return findByProperty(LUAS, luas);
	}

	public List findByLuasHektar(Object luasHektar) {
		return findByProperty(LUAS_HEKTAR, luasHektar);
	}

	public List findByLuasJalan(Object luasJalan) {
		return findByProperty(LUAS_JALAN, luasJalan);
	}

	public List findByLuasPadang(Object luasPadang) {
		return findByProperty(LUAS_PADANG, luasPadang);
	}

	public List findByLuasBgn(Object luasBgn) {
		return findByProperty(LUAS_BGN, luasBgn);
	}

	public List findByLuasParking(Object luasParking) {
		return findByProperty(LUAS_PARKING, luasParking);
	}

	public List findByLuasLain(Object luasLain) {
		return findByProperty(LUAS_LAIN, luasLain);
	}

	public List findByLuasAsal(Object luasAsal) {
		return findByProperty(LUAS_ASAL, luasAsal);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findAll() {
		log.debug("finding all Tblhtppembangunan instances");
		try {
			String queryString = "from Tblhtppembangunan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppembangunan merge(Tblhtppembangunan detachedInstance) {
		log.debug("merging Tblhtppembangunan instance");
		try {
			Tblhtppembangunan result = (Tblhtppembangunan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppembangunan instance) {
		log.debug("attaching dirty Tblhtppembangunan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppembangunan instance) {
		log.debug("attaching clean Tblhtppembangunan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
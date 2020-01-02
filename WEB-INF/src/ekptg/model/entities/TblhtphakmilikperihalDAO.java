package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtphakmilikperihal entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtphakmilikperihal
 * @author MyEclipse Persistence Tools
 */

public class TblhtphakmilikperihalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtphakmilikperihalDAO.class);
	// property constants
	public static final String ID_HAKMILIKPEGANGAN = "idHakmilikpegangan";
	public static final String LUAS_PETAK = "luasPetak";
	public static final String KEGUNAAN_TAPAK = "kegunaanTapak";
	public static final String LUAS_BANGUNAN = "luasBangunan";
	public static final String LUAS_PARKING = "luasParking";
	public static final String LUAS_LAIN = "luasLain";
	public static final String LUAS_BELUMDIBANGUNKAN = "luasBelumdibangunkan";
	public static final String KEGUNAAN_TANAH = "kegunaanTanah";
	public static final String LUAS_JALAN = "luasJalan";
	public static final String LUAS_PADANG = "luasPadang";
	public static final String STATUS = "status";
	public static final String ID_MASUK = "idMasuk";

	public void save(Tblhtphakmilikperihal transientInstance) {
		log.debug("saving Tblhtphakmilikperihal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtphakmilikperihal persistentInstance) {
		log.debug("deleting Tblhtphakmilikperihal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtphakmilikperihal findById(java.lang.Long id) {
		log.debug("getting Tblhtphakmilikperihal instance with id: " + id);
		try {
			Tblhtphakmilikperihal instance = (Tblhtphakmilikperihal) getSession()
					.get("ekptg.model.entities.Tblhtphakmilikperihal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtphakmilikperihal instance) {
		log.debug("finding Tblhtphakmilikperihal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtphakmilikperihal").add(
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
		log.debug("finding Tblhtphakmilikperihal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtphakmilikperihal as model where model."
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

	public List findByLuasPetak(Object luasPetak) {
		return findByProperty(LUAS_PETAK, luasPetak);
	}

	public List findByKegunaanTapak(Object kegunaanTapak) {
		return findByProperty(KEGUNAAN_TAPAK, kegunaanTapak);
	}

	public List findByLuasBangunan(Object luasBangunan) {
		return findByProperty(LUAS_BANGUNAN, luasBangunan);
	}

	public List findByLuasParking(Object luasParking) {
		return findByProperty(LUAS_PARKING, luasParking);
	}

	public List findByLuasLain(Object luasLain) {
		return findByProperty(LUAS_LAIN, luasLain);
	}

	public List findByLuasBelumdibangunkan(Object luasBelumdibangunkan) {
		return findByProperty(LUAS_BELUMDIBANGUNKAN, luasBelumdibangunkan);
	}

	public List findByKegunaanTanah(Object kegunaanTanah) {
		return findByProperty(KEGUNAAN_TANAH, kegunaanTanah);
	}

	public List findByLuasJalan(Object luasJalan) {
		return findByProperty(LUAS_JALAN, luasJalan);
	}

	public List findByLuasPadang(Object luasPadang) {
		return findByProperty(LUAS_PADANG, luasPadang);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findAll() {
		log.debug("finding all Tblhtphakmilikperihal instances");
		try {
			String queryString = "from Tblhtphakmilikperihal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtphakmilikperihal merge(Tblhtphakmilikperihal detachedInstance) {
		log.debug("merging Tblhtphakmilikperihal instance");
		try {
			Tblhtphakmilikperihal result = (Tblhtphakmilikperihal) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtphakmilikperihal instance) {
		log.debug("attaching dirty Tblhtphakmilikperihal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtphakmilikperihal instance) {
		log.debug("attaching clean Tblhtphakmilikperihal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
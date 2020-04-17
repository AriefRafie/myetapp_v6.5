package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtphakmilikcukai entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtphakmilikcukai
 * @author MyEclipse Persistence Tools
 */

public class TblhtphakmilikcukaiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtphakmilikcukaiDAO.class);
	// property constants
	public static final String ID_HAKMILIKPEGANGAN = "idHakmilikpegangan";
	public static final String LUAS = "luas";
	public static final String CUKAI = "cukai";
	public static final String LUAS_PETAK = "luasPetak";
	public static final String KOD_BAYARANCUKAI = "kodBayarancukai";
	public static final String CUKAI_TALIAIR = "cukaiTaliair";
	public static final String CUKAI_PARIT = "cukaiParit";
	public static final String DENDA = "denda";
	public static final String PENGURANGAN = "pengurangan";
	public static final String PENGECUALIAN = "pengecualian";
	public static final String STATUS_PROSESCUKAI = "statusProsescukai";
	public static final String BAYARAN_LAIN = "bayaranLain";
	public static final String STATUS = "status";
	public static final String ID_MASUK = "idMasuk";
	public static final String TARIKH_MASUK = "tarikhMasuk";

	public void save(Tblhtphakmilikcukai transientInstance) {
		log.debug("saving Tblhtphakmilikcukai instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtphakmilikcukai persistentInstance) {
		log.debug("deleting Tblhtphakmilikcukai instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtphakmilikcukai findById(java.lang.Long id) {
		log.debug("getting Tblhtphakmilikcukai instance with id: " + id);
		try {
			Tblhtphakmilikcukai instance = (Tblhtphakmilikcukai) getSession()
					.get("ekptg.model.entities.Tblhtphakmilikcukai", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtphakmilikcukai instance) {
		log.debug("finding Tblhtphakmilikcukai instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtphakmilikcukai").add(
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
		log.debug("finding Tblhtphakmilikcukai instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtphakmilikcukai as model where model."
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

	public List findByLuas(Object luas) {
		return findByProperty(LUAS, luas);
	}

	public List findByCukai(Object cukai) {
		return findByProperty(CUKAI, cukai);
	}

	public List findByLuasPetak(Object luasPetak) {
		return findByProperty(LUAS_PETAK, luasPetak);
	}

	public List findByKodBayarancukai(Object kodBayarancukai) {
		return findByProperty(KOD_BAYARANCUKAI, kodBayarancukai);
	}

	public List findByCukaiTaliair(Object cukaiTaliair) {
		return findByProperty(CUKAI_TALIAIR, cukaiTaliair);
	}

	public List findByCukaiParit(Object cukaiParit) {
		return findByProperty(CUKAI_PARIT, cukaiParit);
	}

	public List findByDenda(Object denda) {
		return findByProperty(DENDA, denda);
	}

	public List findByPengurangan(Object pengurangan) {
		return findByProperty(PENGURANGAN, pengurangan);
	}

	public List findByPengecualian(Object pengecualian) {
		return findByProperty(PENGECUALIAN, pengecualian);
	}

	public List findByStatusProsescukai(Object statusProsescukai) {
		return findByProperty(STATUS_PROSESCUKAI, statusProsescukai);
	}

	public List findByBayaranLain(Object bayaranLain) {
		return findByProperty(BAYARAN_LAIN, bayaranLain);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByTarikhMasuk(Object tarikhMasuk) {
		return findByProperty(TARIKH_MASUK, tarikhMasuk);
	}

	public List findAll() {
		log.debug("finding all Tblhtphakmilikcukai instances");
		try {
			String queryString = "from Tblhtphakmilikcukai";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtphakmilikcukai merge(Tblhtphakmilikcukai detachedInstance) {
		log.debug("merging Tblhtphakmilikcukai instance");
		try {
			Tblhtphakmilikcukai result = (Tblhtphakmilikcukai) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtphakmilikcukai instance) {
		log.debug("attaching dirty Tblhtphakmilikcukai instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtphakmilikcukai instance) {
		log.debug("attaching clean Tblhtphakmilikcukai instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
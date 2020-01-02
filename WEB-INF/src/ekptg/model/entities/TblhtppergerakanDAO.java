package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppergerakan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppergerakan
 * @author MyEclipse Persistence Tools
 */

public class TblhtppergerakanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtppergerakanDAO.class);
	// property constants
	public static final String ID_HAKMILIKPEGANGAN = "idHakmilikpegangan";
	public static final String KETERANGAN = "keterangan";
	public static final String BIL_SALINAN = "bilSalinan";
	public static final String STATUS = "status";
	public static final String ID_MASUK = "idMasuk";

	public void save(Tblhtppergerakan transientInstance) {
		log.debug("saving Tblhtppergerakan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppergerakan persistentInstance) {
		log.debug("deleting Tblhtppergerakan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppergerakan findById(java.lang.Long id) {
		log.debug("getting Tblhtppergerakan instance with id: " + id);
		try {
			Tblhtppergerakan instance = (Tblhtppergerakan) getSession().get(
					"ekptg.model.entities.Tblhtppergerakan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppergerakan instance) {
		log.debug("finding Tblhtppergerakan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppergerakan").add(
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
		log.debug("finding Tblhtppergerakan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppergerakan as model where model."
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

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByBilSalinan(Object bilSalinan) {
		return findByProperty(BIL_SALINAN, bilSalinan);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findAll() {
		log.debug("finding all Tblhtppergerakan instances");
		try {
			String queryString = "from Tblhtppergerakan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppergerakan merge(Tblhtppergerakan detachedInstance) {
		log.debug("merging Tblhtppergerakan instance");
		try {
			Tblhtppergerakan result = (Tblhtppergerakan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppergerakan instance) {
		log.debug("attaching dirty Tblhtppergerakan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppergerakan instance) {
		log.debug("attaching clean Tblhtppergerakan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
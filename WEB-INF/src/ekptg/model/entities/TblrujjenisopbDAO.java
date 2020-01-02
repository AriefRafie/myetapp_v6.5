package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujjenisopb entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujjenisopb
 * @author MyEclipse Persistence Tools
 */

public class TblrujjenisopbDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujjenisopbDAO.class);
	// property constants
	public static final String KOD_NOPB = "kodNopb";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblrujjenisopb transientInstance) {
		log.debug("saving Tblrujjenisopb instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujjenisopb persistentInstance) {
		log.debug("deleting Tblrujjenisopb instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujjenisopb findById(java.lang.Long id) {
		log.debug("getting Tblrujjenisopb instance with id: " + id);
		try {
			Tblrujjenisopb instance = (Tblrujjenisopb) getSession().get(
					"ekptg.model.entities.Tblrujjenisopb", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujjenisopb instance) {
		log.debug("finding Tblrujjenisopb instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujjenisopb").add(
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
		log.debug("finding Tblrujjenisopb instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujjenisopb as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodNopb(Object kodNopb) {
		return findByProperty(KOD_NOPB, kodNopb);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblrujjenisopb instances");
		try {
			String queryString = "from Tblrujjenisopb";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujjenisopb merge(Tblrujjenisopb detachedInstance) {
		log.debug("merging Tblrujjenisopb instance");
		try {
			Tblrujjenisopb result = (Tblrujjenisopb) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujjenisopb instance) {
		log.debug("attaching dirty Tblrujjenisopb instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujjenisopb instance) {
		log.debug("attaching clean Tblrujjenisopb instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
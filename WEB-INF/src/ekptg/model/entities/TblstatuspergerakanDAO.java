package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblstatuspergerakan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblstatuspergerakan
 * @author MyEclipse Persistence Tools
 */

public class TblstatuspergerakanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblstatuspergerakanDAO.class);
	// property constants
	public static final String KOD_STATUS_PERGERAKAN = "kodStatusPergerakan";
	public static final String STATUS_PERGERAKAN = "statusPergerakan";

	public void save(Tblstatuspergerakan transientInstance) {
		log.debug("saving Tblstatuspergerakan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblstatuspergerakan persistentInstance) {
		log.debug("deleting Tblstatuspergerakan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblstatuspergerakan findById(java.lang.Long id) {
		log.debug("getting Tblstatuspergerakan instance with id: " + id);
		try {
			Tblstatuspergerakan instance = (Tblstatuspergerakan) getSession()
					.get("ekptg.model.entities.Tblstatuspergerakan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblstatuspergerakan instance) {
		log.debug("finding Tblstatuspergerakan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblstatuspergerakan").add(
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
		log.debug("finding Tblstatuspergerakan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblstatuspergerakan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodStatusPergerakan(Object kodStatusPergerakan) {
		return findByProperty(KOD_STATUS_PERGERAKAN, kodStatusPergerakan);
	}

	public List findByStatusPergerakan(Object statusPergerakan) {
		return findByProperty(STATUS_PERGERAKAN, statusPergerakan);
	}

	public List findAll() {
		log.debug("finding all Tblstatuspergerakan instances");
		try {
			String queryString = "from Tblstatuspergerakan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblstatuspergerakan merge(Tblstatuspergerakan detachedInstance) {
		log.debug("merging Tblstatuspergerakan instance");
		try {
			Tblstatuspergerakan result = (Tblstatuspergerakan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblstatuspergerakan instance) {
		log.debug("attaching dirty Tblstatuspergerakan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblstatuspergerakan instance) {
		log.debug("attaching clean Tblstatuspergerakan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblstatusfail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblstatusfail
 * @author MyEclipse Persistence Tools
 */

public class TblstatusfailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblstatusfailDAO.class);
	// property constants
	public static final String KOD_STATUS_FAIL = "kodStatusFail";
	public static final String STATUS_FAIL = "statusFail";

	public void save(Tblstatusfail transientInstance) {
		log.debug("saving Tblstatusfail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblstatusfail persistentInstance) {
		log.debug("deleting Tblstatusfail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblstatusfail findById(java.lang.Long id) {
		log.debug("getting Tblstatusfail instance with id: " + id);
		try {
			Tblstatusfail instance = (Tblstatusfail) getSession().get(
					"ekptg.model.entities.Tblstatusfail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblstatusfail instance) {
		log.debug("finding Tblstatusfail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblstatusfail").add(
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
		log.debug("finding Tblstatusfail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblstatusfail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodStatusFail(Object kodStatusFail) {
		return findByProperty(KOD_STATUS_FAIL, kodStatusFail);
	}

	public List findByStatusFail(Object statusFail) {
		return findByProperty(STATUS_FAIL, statusFail);
	}

	public List findAll() {
		log.debug("finding all Tblstatusfail instances");
		try {
			String queryString = "from Tblstatusfail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblstatusfail merge(Tblstatusfail detachedInstance) {
		log.debug("merging Tblstatusfail instance");
		try {
			Tblstatusfail result = (Tblstatusfail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblstatusfail instance) {
		log.debug("attaching dirty Tblstatusfail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblstatusfail instance) {
		log.debug("attaching clean Tblstatusfail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
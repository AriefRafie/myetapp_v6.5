package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblstatus entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblstatus
 * @author MyEclipse Persistence Tools
 */

public class TblstatusDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblstatusDAO.class);
	// property constants
	public static final String KOD_STATUS = "kodStatus";
	public static final String STATUS = "status";

	public void save(Tblstatus transientInstance) {
		log.debug("saving Tblstatus instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblstatus persistentInstance) {
		log.debug("deleting Tblstatus instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblstatus findById(java.lang.Long id) {
		log.debug("getting Tblstatus instance with id: " + id);
		try {
			Tblstatus instance = (Tblstatus) getSession().get(
					"ekptg.model.entities.Tblstatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblstatus instance) {
		log.debug("finding Tblstatus instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblstatus").add(
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
		log.debug("finding Tblstatus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblstatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodStatus(Object kodStatus) {
		return findByProperty(KOD_STATUS, kodStatus);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all Tblstatus instances");
		try {
			String queryString = "from Tblstatus";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblstatus merge(Tblstatus detachedInstance) {
		log.debug("merging Tblstatus instance");
		try {
			Tblstatus result = (Tblstatus) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblstatus instance) {
		log.debug("attaching dirty Tblstatus instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblstatus instance) {
		log.debug("attaching clean Tblstatus instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
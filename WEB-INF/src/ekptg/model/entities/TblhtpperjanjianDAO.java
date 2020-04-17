package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpperjanjian entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpperjanjian
 * @author MyEclipse Persistence Tools
 */

public class TblhtpperjanjianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpperjanjianDAO.class);

	// property constants

	public void save(Tblhtpperjanjian transientInstance) {
		log.debug("saving Tblhtpperjanjian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpperjanjian persistentInstance) {
		log.debug("deleting Tblhtpperjanjian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpperjanjian findById(ekptg.model.entities.TblhtpperjanjianId id) {
		log.debug("getting Tblhtpperjanjian instance with id: " + id);
		try {
			Tblhtpperjanjian instance = (Tblhtpperjanjian) getSession().get(
					"ekptg.model.entities.Tblhtpperjanjian", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpperjanjian instance) {
		log.debug("finding Tblhtpperjanjian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpperjanjian").add(
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
		log.debug("finding Tblhtpperjanjian instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpperjanjian as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Tblhtpperjanjian instances");
		try {
			String queryString = "from Tblhtpperjanjian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpperjanjian merge(Tblhtpperjanjian detachedInstance) {
		log.debug("merging Tblhtpperjanjian instance");
		try {
			Tblhtpperjanjian result = (Tblhtpperjanjian) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpperjanjian instance) {
		log.debug("attaching dirty Tblhtpperjanjian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpperjanjian instance) {
		log.debug("attaching clean Tblhtpperjanjian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
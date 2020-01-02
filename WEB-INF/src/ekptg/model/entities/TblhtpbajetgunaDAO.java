package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpbajetguna entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpbajetguna
 * @author MyEclipse Persistence Tools
 */

public class TblhtpbajetgunaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpbajetgunaDAO.class);
	// property constants
	public static final String GUNA = "guna";
	public static final String BAKI = "baki";

	public void save(Tblhtpbajetguna transientInstance) {
		log.debug("saving Tblhtpbajetguna instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpbajetguna persistentInstance) {
		log.debug("deleting Tblhtpbajetguna instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpbajetguna findById(java.lang.Long id) {
		log.debug("getting Tblhtpbajetguna instance with id: " + id);
		try {
			Tblhtpbajetguna instance = (Tblhtpbajetguna) getSession().get(
					"ekptg.model.entities.Tblhtpbajetguna", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpbajetguna instance) {
		log.debug("finding Tblhtpbajetguna instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpbajetguna").add(
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
		log.debug("finding Tblhtpbajetguna instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpbajetguna as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGuna(Object guna) {
		return findByProperty(GUNA, guna);
	}

	public List findByBaki(Object baki) {
		return findByProperty(BAKI, baki);
	}

	public List findAll() {
		log.debug("finding all Tblhtpbajetguna instances");
		try {
			String queryString = "from Tblhtpbajetguna";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpbajetguna merge(Tblhtpbajetguna detachedInstance) {
		log.debug("merging Tblhtpbajetguna instance");
		try {
			Tblhtpbajetguna result = (Tblhtpbajetguna) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpbajetguna instance) {
		log.debug("attaching dirty Tblhtpbajetguna instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpbajetguna instance) {
		log.debug("attaching clean Tblhtpbajetguna instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
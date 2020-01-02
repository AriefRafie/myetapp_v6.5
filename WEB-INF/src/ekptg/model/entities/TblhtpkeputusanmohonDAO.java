package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpkeputusanmohon entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpkeputusanmohon
 * @author MyEclipse Persistence Tools
 */

public class TblhtpkeputusanmohonDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpkeputusanmohonDAO.class);

	// property constants

	public void save(Tblhtpkeputusanmohon transientInstance) {
		log.debug("saving Tblhtpkeputusanmohon instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpkeputusanmohon persistentInstance) {
		log.debug("deleting Tblhtpkeputusanmohon instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpkeputusanmohon findById(
			ekptg.model.entities.TblhtpkeputusanmohonId id) {
		log.debug("getting Tblhtpkeputusanmohon instance with id: " + id);
		try {
			Tblhtpkeputusanmohon instance = (Tblhtpkeputusanmohon) getSession()
					.get("ekptg.model.entities.Tblhtpkeputusanmohon", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpkeputusanmohon instance) {
		log.debug("finding Tblhtpkeputusanmohon instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpkeputusanmohon").add(
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
		log.debug("finding Tblhtpkeputusanmohon instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpkeputusanmohon as model where model."
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
		log.debug("finding all Tblhtpkeputusanmohon instances");
		try {
			String queryString = "from Tblhtpkeputusanmohon";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpkeputusanmohon merge(Tblhtpkeputusanmohon detachedInstance) {
		log.debug("merging Tblhtpkeputusanmohon instance");
		try {
			Tblhtpkeputusanmohon result = (Tblhtpkeputusanmohon) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpkeputusanmohon instance) {
		log.debug("attaching dirty Tblhtpkeputusanmohon instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpkeputusanmohon instance) {
		log.debug("attaching clean Tblhtpkeputusanmohon instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
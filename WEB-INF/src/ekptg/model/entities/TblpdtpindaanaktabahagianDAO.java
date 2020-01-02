package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpindaanaktabahagian entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtpindaanaktabahagian
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpindaanaktabahagianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtpindaanaktabahagianDAO.class);

	// property constants

	public void save(Tblpdtpindaanaktabahagian transientInstance) {
		log.debug("saving Tblpdtpindaanaktabahagian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpindaanaktabahagian persistentInstance) {
		log.debug("deleting Tblpdtpindaanaktabahagian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanaktabahagian findById(java.lang.Long id) {
		log.debug("getting Tblpdtpindaanaktabahagian instance with id: " + id);
		try {
			Tblpdtpindaanaktabahagian instance = (Tblpdtpindaanaktabahagian) getSession()
					.get("ekptg.model.entities.Tblpdtpindaanaktabahagian", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpindaanaktabahagian instance) {
		log.debug("finding Tblpdtpindaanaktabahagian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpindaanaktabahagian").add(
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
		log.debug("finding Tblpdtpindaanaktabahagian instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpindaanaktabahagian as model where model."
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
		log.debug("finding all Tblpdtpindaanaktabahagian instances");
		try {
			String queryString = "from Tblpdtpindaanaktabahagian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanaktabahagian merge(
			Tblpdtpindaanaktabahagian detachedInstance) {
		log.debug("merging Tblpdtpindaanaktabahagian instance");
		try {
			Tblpdtpindaanaktabahagian result = (Tblpdtpindaanaktabahagian) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpindaanaktabahagian instance) {
		log.debug("attaching dirty Tblpdtpindaanaktabahagian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpindaanaktabahagian instance) {
		log.debug("attaching clean Tblpdtpindaanaktabahagian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
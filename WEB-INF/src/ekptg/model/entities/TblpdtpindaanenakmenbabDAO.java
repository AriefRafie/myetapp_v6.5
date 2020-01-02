package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpindaanenakmenbab entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtpindaanenakmenbab
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpindaanenakmenbabDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtpindaanenakmenbabDAO.class);

	// property constants

	public void save(Tblpdtpindaanenakmenbab transientInstance) {
		log.debug("saving Tblpdtpindaanenakmenbab instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpindaanenakmenbab persistentInstance) {
		log.debug("deleting Tblpdtpindaanenakmenbab instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanenakmenbab findById(java.lang.Long id) {
		log.debug("getting Tblpdtpindaanenakmenbab instance with id: " + id);
		try {
			Tblpdtpindaanenakmenbab instance = (Tblpdtpindaanenakmenbab) getSession()
					.get("ekptg.model.entities.Tblpdtpindaanenakmenbab", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpindaanenakmenbab instance) {
		log.debug("finding Tblpdtpindaanenakmenbab instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpindaanenakmenbab").add(
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
		log.debug("finding Tblpdtpindaanenakmenbab instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpindaanenakmenbab as model where model."
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
		log.debug("finding all Tblpdtpindaanenakmenbab instances");
		try {
			String queryString = "from Tblpdtpindaanenakmenbab";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanenakmenbab merge(
			Tblpdtpindaanenakmenbab detachedInstance) {
		log.debug("merging Tblpdtpindaanenakmenbab instance");
		try {
			Tblpdtpindaanenakmenbab result = (Tblpdtpindaanenakmenbab) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpindaanenakmenbab instance) {
		log.debug("attaching dirty Tblpdtpindaanenakmenbab instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpindaanenakmenbab instance) {
		log.debug("attaching clean Tblpdtpindaanenakmenbab instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
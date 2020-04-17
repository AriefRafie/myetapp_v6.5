package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpindaanenakmenbahagian entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtpindaanenakmenbahagian
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpindaanenakmenbahagianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtpindaanenakmenbahagianDAO.class);

	// property constants

	public void save(Tblpdtpindaanenakmenbahagian transientInstance) {
		log.debug("saving Tblpdtpindaanenakmenbahagian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpindaanenakmenbahagian persistentInstance) {
		log.debug("deleting Tblpdtpindaanenakmenbahagian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanenakmenbahagian findById(java.lang.Long id) {
		log.debug("getting Tblpdtpindaanenakmenbahagian instance with id: "
				+ id);
		try {
			Tblpdtpindaanenakmenbahagian instance = (Tblpdtpindaanenakmenbahagian) getSession()
					.get("ekptg.model.entities.Tblpdtpindaanenakmenbahagian",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpindaanenakmenbahagian instance) {
		log.debug("finding Tblpdtpindaanenakmenbahagian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpindaanenakmenbahagian").add(
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
		log
				.debug("finding Tblpdtpindaanenakmenbahagian instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpindaanenakmenbahagian as model where model."
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
		log.debug("finding all Tblpdtpindaanenakmenbahagian instances");
		try {
			String queryString = "from Tblpdtpindaanenakmenbahagian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanenakmenbahagian merge(
			Tblpdtpindaanenakmenbahagian detachedInstance) {
		log.debug("merging Tblpdtpindaanenakmenbahagian instance");
		try {
			Tblpdtpindaanenakmenbahagian result = (Tblpdtpindaanenakmenbahagian) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpindaanenakmenbahagian instance) {
		log.debug("attaching dirty Tblpdtpindaanenakmenbahagian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpindaanenakmenbahagian instance) {
		log.debug("attaching clean Tblpdtpindaanenakmenbahagian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
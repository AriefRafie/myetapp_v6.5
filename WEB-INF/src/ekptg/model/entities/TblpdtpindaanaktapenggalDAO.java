package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpindaanaktapenggal entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtpindaanaktapenggal
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpindaanaktapenggalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtpindaanaktapenggalDAO.class);

	// property constants

	public void save(Tblpdtpindaanaktapenggal transientInstance) {
		log.debug("saving Tblpdtpindaanaktapenggal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpindaanaktapenggal persistentInstance) {
		log.debug("deleting Tblpdtpindaanaktapenggal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanaktapenggal findById(java.lang.Long id) {
		log.debug("getting Tblpdtpindaanaktapenggal instance with id: " + id);
		try {
			Tblpdtpindaanaktapenggal instance = (Tblpdtpindaanaktapenggal) getSession()
					.get("ekptg.model.entities.Tblpdtpindaanaktapenggal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpindaanaktapenggal instance) {
		log.debug("finding Tblpdtpindaanaktapenggal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpindaanaktapenggal").add(
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
		log.debug("finding Tblpdtpindaanaktapenggal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpindaanaktapenggal as model where model."
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
		log.debug("finding all Tblpdtpindaanaktapenggal instances");
		try {
			String queryString = "from Tblpdtpindaanaktapenggal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanaktapenggal merge(
			Tblpdtpindaanaktapenggal detachedInstance) {
		log.debug("merging Tblpdtpindaanaktapenggal instance");
		try {
			Tblpdtpindaanaktapenggal result = (Tblpdtpindaanaktapenggal) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpindaanaktapenggal instance) {
		log.debug("attaching dirty Tblpdtpindaanaktapenggal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpindaanaktapenggal instance) {
		log.debug("attaching clean Tblpdtpindaanaktapenggal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
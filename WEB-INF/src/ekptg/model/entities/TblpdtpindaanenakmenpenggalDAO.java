package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpindaanenakmenpenggal entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtpindaanenakmenpenggal
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpindaanenakmenpenggalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtpindaanenakmenpenggalDAO.class);

	// property constants

	public void save(Tblpdtpindaanenakmenpenggal transientInstance) {
		log.debug("saving Tblpdtpindaanenakmenpenggal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpindaanenakmenpenggal persistentInstance) {
		log.debug("deleting Tblpdtpindaanenakmenpenggal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanenakmenpenggal findById(java.lang.Long id) {
		log
				.debug("getting Tblpdtpindaanenakmenpenggal instance with id: "
						+ id);
		try {
			Tblpdtpindaanenakmenpenggal instance = (Tblpdtpindaanenakmenpenggal) getSession()
					.get("ekptg.model.entities.Tblpdtpindaanenakmenpenggal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpindaanenakmenpenggal instance) {
		log.debug("finding Tblpdtpindaanenakmenpenggal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpindaanenakmenpenggal").add(
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
				.debug("finding Tblpdtpindaanenakmenpenggal instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpindaanenakmenpenggal as model where model."
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
		log.debug("finding all Tblpdtpindaanenakmenpenggal instances");
		try {
			String queryString = "from Tblpdtpindaanenakmenpenggal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanenakmenpenggal merge(
			Tblpdtpindaanenakmenpenggal detachedInstance) {
		log.debug("merging Tblpdtpindaanenakmenpenggal instance");
		try {
			Tblpdtpindaanenakmenpenggal result = (Tblpdtpindaanenakmenpenggal) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpindaanenakmenpenggal instance) {
		log.debug("attaching dirty Tblpdtpindaanenakmenpenggal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpindaanenakmenpenggal instance) {
		log.debug("attaching clean Tblpdtpindaanenakmenpenggal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
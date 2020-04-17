package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpindaanaktapara entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtpindaanaktapara
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpindaanaktaparaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtpindaanaktaparaDAO.class);

	// property constants

	public void save(Tblpdtpindaanaktapara transientInstance) {
		log.debug("saving Tblpdtpindaanaktapara instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpindaanaktapara persistentInstance) {
		log.debug("deleting Tblpdtpindaanaktapara instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanaktapara findById(java.lang.Long id) {
		log.debug("getting Tblpdtpindaanaktapara instance with id: " + id);
		try {
			Tblpdtpindaanaktapara instance = (Tblpdtpindaanaktapara) getSession()
					.get("ekptg.model.entities.Tblpdtpindaanaktapara", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpindaanaktapara instance) {
		log.debug("finding Tblpdtpindaanaktapara instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpindaanaktapara").add(
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
		log.debug("finding Tblpdtpindaanaktapara instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpindaanaktapara as model where model."
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
		log.debug("finding all Tblpdtpindaanaktapara instances");
		try {
			String queryString = "from Tblpdtpindaanaktapara";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanaktapara merge(Tblpdtpindaanaktapara detachedInstance) {
		log.debug("merging Tblpdtpindaanaktapara instance");
		try {
			Tblpdtpindaanaktapara result = (Tblpdtpindaanaktapara) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpindaanaktapara instance) {
		log.debug("attaching dirty Tblpdtpindaanaktapara instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpindaanaktapara instance) {
		log.debug("attaching clean Tblpdtpindaanaktapara instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
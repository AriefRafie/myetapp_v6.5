package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpindaanenakmen entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtpindaanenakmen
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpindaanenakmenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtpindaanenakmenDAO.class);

	// property constants

	public void save(Tblpdtpindaanenakmen transientInstance) {
		log.debug("saving Tblpdtpindaanenakmen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpindaanenakmen persistentInstance) {
		log.debug("deleting Tblpdtpindaanenakmen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanenakmen findById(java.lang.Long id) {
		log.debug("getting Tblpdtpindaanenakmen instance with id: " + id);
		try {
			Tblpdtpindaanenakmen instance = (Tblpdtpindaanenakmen) getSession()
					.get("ekptg.model.entities.Tblpdtpindaanenakmen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpindaanenakmen instance) {
		log.debug("finding Tblpdtpindaanenakmen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpindaanenakmen").add(
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
		log.debug("finding Tblpdtpindaanenakmen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpindaanenakmen as model where model."
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
		log.debug("finding all Tblpdtpindaanenakmen instances");
		try {
			String queryString = "from Tblpdtpindaanenakmen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpindaanenakmen merge(Tblpdtpindaanenakmen detachedInstance) {
		log.debug("merging Tblpdtpindaanenakmen instance");
		try {
			Tblpdtpindaanenakmen result = (Tblpdtpindaanenakmen) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpindaanenakmen instance) {
		log.debug("attaching dirty Tblpdtpindaanenakmen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpindaanenakmen instance) {
		log.debug("attaching clean Tblpdtpindaanenakmen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
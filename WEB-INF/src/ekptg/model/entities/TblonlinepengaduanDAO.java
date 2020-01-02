package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblonlinepengaduan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblonlinepengaduan
 * @author MyEclipse Persistence Tools
 */

public class TblonlinepengaduanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblonlinepengaduanDAO.class);

	// property constants

	public void save(Tblonlinepengaduan transientInstance) {
		log.debug("saving Tblonlinepengaduan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblonlinepengaduan persistentInstance) {
		log.debug("deleting Tblonlinepengaduan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblonlinepengaduan findById(java.math.BigDecimal id) {
		log.debug("getting Tblonlinepengaduan instance with id: " + id);
		try {
			Tblonlinepengaduan instance = (Tblonlinepengaduan) getSession()
					.get("ekptg.model.entities.Tblonlinepengaduan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblonlinepengaduan instance) {
		log.debug("finding Tblonlinepengaduan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblonlinepengaduan").add(
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
		log.debug("finding Tblonlinepengaduan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblonlinepengaduan as model where model."
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
		log.debug("finding all Tblonlinepengaduan instances");
		try {
			String queryString = "from Tblonlinepengaduan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblonlinepengaduan merge(Tblonlinepengaduan detachedInstance) {
		log.debug("merging Tblonlinepengaduan instance");
		try {
			Tblonlinepengaduan result = (Tblonlinepengaduan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblonlinepengaduan instance) {
		log.debug("attaching dirty Tblonlinepengaduan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblonlinepengaduan instance) {
		log.debug("attaching clean Tblonlinepengaduan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
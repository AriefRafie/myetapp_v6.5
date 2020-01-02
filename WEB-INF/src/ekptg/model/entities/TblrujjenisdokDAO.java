package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujjenisdok entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujjenisdok
 * @author MyEclipse Persistence Tools
 */

public class TblrujjenisdokDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujjenisdokDAO.class);

	// property constants

	public void save(Tblrujjenisdok transientInstance) {
		log.debug("saving Tblrujjenisdok instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujjenisdok persistentInstance) {
		log.debug("deleting Tblrujjenisdok instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujjenisdok findById(ekptg.model.entities.TblrujjenisdokId id) {
		log.debug("getting Tblrujjenisdok instance with id: " + id);
		try {
			Tblrujjenisdok instance = (Tblrujjenisdok) getSession().get(
					"ekptg.model.entities.Tblrujjenisdok", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujjenisdok instance) {
		log.debug("finding Tblrujjenisdok instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujjenisdok").add(
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
		log.debug("finding Tblrujjenisdok instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujjenisdok as model where model."
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
		log.debug("finding all Tblrujjenisdok instances");
		try {
			String queryString = "from Tblrujjenisdok";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujjenisdok merge(Tblrujjenisdok detachedInstance) {
		log.debug("merging Tblrujjenisdok instance");
		try {
			Tblrujjenisdok result = (Tblrujjenisdok) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujjenisdok instance) {
		log.debug("attaching dirty Tblrujjenisdok instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujjenisdok instance) {
		log.debug("attaching clean Tblrujjenisdok instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
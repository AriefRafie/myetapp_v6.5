package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujjawatan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujjawatan
 * @author MyEclipse Persistence Tools
 */

public class TblrujjawatanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujjawatanDAO.class);
	// property constants
	public static final String KOD_JAWATAN = "kodJawatan";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblrujjawatan transientInstance) {
		log.debug("saving Tblrujjawatan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujjawatan persistentInstance) {
		log.debug("deleting Tblrujjawatan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujjawatan findById(java.lang.Long id) {
		log.debug("getting Tblrujjawatan instance with id: " + id);
		try {
			Tblrujjawatan instance = (Tblrujjawatan) getSession().get(
					"ekptg.model.entities.Tblrujjawatan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujjawatan instance) {
		log.debug("finding Tblrujjawatan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujjawatan").add(
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
		log.debug("finding Tblrujjawatan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujjawatan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJawatan(Object kodJawatan) {
		return findByProperty(KOD_JAWATAN, kodJawatan);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblrujjawatan instances");
		try {
			String queryString = "from Tblrujjawatan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujjawatan merge(Tblrujjawatan detachedInstance) {
		log.debug("merging Tblrujjawatan instance");
		try {
			Tblrujjawatan result = (Tblrujjawatan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujjawatan instance) {
		log.debug("attaching dirty Tblrujjawatan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujjawatan instance) {
		log.debug("attaching clean Tblrujjawatan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
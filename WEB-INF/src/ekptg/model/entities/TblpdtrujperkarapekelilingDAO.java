package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtrujperkarapekeliling entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtrujperkarapekeliling
 * @author MyEclipse Persistence Tools
 */

public class TblpdtrujperkarapekelilingDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtrujperkarapekelilingDAO.class);
	// property constants
	public static final String KOD_PERKARA_PEKELILING = "kodPerkaraPekeliling";
	public static final String PERKARA_PEKELILING = "perkaraPekeliling";

	public void save(Tblpdtrujperkarapekeliling transientInstance) {
		log.debug("saving Tblpdtrujperkarapekeliling instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtrujperkarapekeliling persistentInstance) {
		log.debug("deleting Tblpdtrujperkarapekeliling instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtrujperkarapekeliling findById(java.lang.Long id) {
		log.debug("getting Tblpdtrujperkarapekeliling instance with id: " + id);
		try {
			Tblpdtrujperkarapekeliling instance = (Tblpdtrujperkarapekeliling) getSession()
					.get("ekptg.model.entities.Tblpdtrujperkarapekeliling", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtrujperkarapekeliling instance) {
		log.debug("finding Tblpdtrujperkarapekeliling instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtrujperkarapekeliling").add(
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
		log.debug("finding Tblpdtrujperkarapekeliling instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtrujperkarapekeliling as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodPerkaraPekeliling(Object kodPerkaraPekeliling) {
		return findByProperty(KOD_PERKARA_PEKELILING, kodPerkaraPekeliling);
	}

	public List findByPerkaraPekeliling(Object perkaraPekeliling) {
		return findByProperty(PERKARA_PEKELILING, perkaraPekeliling);
	}

	public List findAll() {
		log.debug("finding all Tblpdtrujperkarapekeliling instances");
		try {
			String queryString = "from Tblpdtrujperkarapekeliling";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtrujperkarapekeliling merge(
			Tblpdtrujperkarapekeliling detachedInstance) {
		log.debug("merging Tblpdtrujperkarapekeliling instance");
		try {
			Tblpdtrujperkarapekeliling result = (Tblpdtrujperkarapekeliling) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtrujperkarapekeliling instance) {
		log.debug("attaching dirty Tblpdtrujperkarapekeliling instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtrujperkarapekeliling instance) {
		log.debug("attaching clean Tblpdtrujperkarapekeliling instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
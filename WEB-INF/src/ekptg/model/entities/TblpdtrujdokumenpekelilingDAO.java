package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtrujdokumenpekeliling entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtrujdokumenpekeliling
 * @author MyEclipse Persistence Tools
 */

public class TblpdtrujdokumenpekelilingDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtrujdokumenpekelilingDAO.class);
	// property constants
	public static final String JENIS_DOKUMEN_PEKELILING = "jenisDokumenPekeliling";
	public static final String KOD_JENIS_DOKUMENPEKELILING = "kodJenisDokumenpekeliling";

	public void save(Tblpdtrujdokumenpekeliling transientInstance) {
		log.debug("saving Tblpdtrujdokumenpekeliling instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtrujdokumenpekeliling persistentInstance) {
		log.debug("deleting Tblpdtrujdokumenpekeliling instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtrujdokumenpekeliling findById(java.lang.Long id) {
		log.debug("getting Tblpdtrujdokumenpekeliling instance with id: " + id);
		try {
			Tblpdtrujdokumenpekeliling instance = (Tblpdtrujdokumenpekeliling) getSession()
					.get("ekptg.model.entities.Tblpdtrujdokumenpekeliling", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtrujdokumenpekeliling instance) {
		log.debug("finding Tblpdtrujdokumenpekeliling instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtrujdokumenpekeliling").add(
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
		log.debug("finding Tblpdtrujdokumenpekeliling instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtrujdokumenpekeliling as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJenisDokumenPekeliling(Object jenisDokumenPekeliling) {
		return findByProperty(JENIS_DOKUMEN_PEKELILING, jenisDokumenPekeliling);
	}

	public List findByKodJenisDokumenpekeliling(Object kodJenisDokumenpekeliling) {
		return findByProperty(KOD_JENIS_DOKUMENPEKELILING,
				kodJenisDokumenpekeliling);
	}

	public List findAll() {
		log.debug("finding all Tblpdtrujdokumenpekeliling instances");
		try {
			String queryString = "from Tblpdtrujdokumenpekeliling";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtrujdokumenpekeliling merge(
			Tblpdtrujdokumenpekeliling detachedInstance) {
		log.debug("merging Tblpdtrujdokumenpekeliling instance");
		try {
			Tblpdtrujdokumenpekeliling result = (Tblpdtrujdokumenpekeliling) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtrujdokumenpekeliling instance) {
		log.debug("attaching dirty Tblpdtrujdokumenpekeliling instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtrujdokumenpekeliling instance) {
		log.debug("attaching clean Tblpdtrujdokumenpekeliling instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
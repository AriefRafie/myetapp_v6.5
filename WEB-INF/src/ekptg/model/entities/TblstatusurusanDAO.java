package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblstatusurusan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblstatusurusan
 * @author MyEclipse Persistence Tools
 */

public class TblstatusurusanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblstatusurusanDAO.class);
	// property constants
	public static final String KOD_STATUS_URUSAN = "kodStatusUrusan";
	public static final String STATUS_URUSAN = "statusUrusan";

	public void save(Tblstatusurusan transientInstance) {
		log.debug("saving Tblstatusurusan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblstatusurusan persistentInstance) {
		log.debug("deleting Tblstatusurusan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblstatusurusan findById(java.lang.Long id) {
		log.debug("getting Tblstatusurusan instance with id: " + id);
		try {
			Tblstatusurusan instance = (Tblstatusurusan) getSession().get(
					"ekptg.model.entities.Tblstatusurusan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblstatusurusan instance) {
		log.debug("finding Tblstatusurusan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblstatusurusan").add(
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
		log.debug("finding Tblstatusurusan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblstatusurusan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodStatusUrusan(Object kodStatusUrusan) {
		return findByProperty(KOD_STATUS_URUSAN, kodStatusUrusan);
	}

	public List findByStatusUrusan(Object statusUrusan) {
		return findByProperty(STATUS_URUSAN, statusUrusan);
	}

	public List findAll() {
		log.debug("finding all Tblstatusurusan instances");
		try {
			String queryString = "from Tblstatusurusan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblstatusurusan merge(Tblstatusurusan detachedInstance) {
		log.debug("merging Tblstatusurusan instance");
		try {
			Tblstatusurusan result = (Tblstatusurusan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblstatusurusan instance) {
		log.debug("attaching dirty Tblstatusurusan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblstatusurusan instance) {
		log.debug("attaching clean Tblstatusurusan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
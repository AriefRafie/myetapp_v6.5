package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblcapaianfail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblcapaianfail
 * @author MyEclipse Persistence Tools
 */

public class TblcapaianfailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblcapaianfailDAO.class);
	// property constants
	public static final String KOD_FAIL = "kodFail";
	public static final String KOD_KUMPULAN = "kodKumpulan";

	public void save(Tblcapaianfail transientInstance) {
		log.debug("saving Tblcapaianfail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblcapaianfail persistentInstance) {
		log.debug("deleting Tblcapaianfail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblcapaianfail findById(java.lang.Long id) {
		log.debug("getting Tblcapaianfail instance with id: " + id);
		try {
			Tblcapaianfail instance = (Tblcapaianfail) getSession().get(
					"ekptg.model.entities.Tblcapaianfail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblcapaianfail instance) {
		log.debug("finding Tblcapaianfail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblcapaianfail").add(
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
		log.debug("finding Tblcapaianfail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblcapaianfail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodFail(Object kodFail) {
		return findByProperty(KOD_FAIL, kodFail);
	}

	public List findByKodKumpulan(Object kodKumpulan) {
		return findByProperty(KOD_KUMPULAN, kodKumpulan);
	}

	public List findAll() {
		log.debug("finding all Tblcapaianfail instances");
		try {
			String queryString = "from Tblcapaianfail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblcapaianfail merge(Tblcapaianfail detachedInstance) {
		log.debug("merging Tblcapaianfail instance");
		try {
			Tblcapaianfail result = (Tblcapaianfail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblcapaianfail instance) {
		log.debug("attaching dirty Tblcapaianfail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblcapaianfail instance) {
		log.debug("attaching clean Tblcapaianfail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
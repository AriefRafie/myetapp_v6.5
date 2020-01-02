package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpergerakanfail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpergerakanfail
 * @author MyEclipse Persistence Tools
 */

public class TblpergerakanfailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpergerakanfailDAO.class);
	// property constants
	public static final String KOD_FAIL = "kodFail";
	public static final String KOD_PENGHANTAR = "kodPenghantar";
	public static final String KOD_PENERIMA = "kodPenerima";
	public static final String KOD_STATUS_PERGERAKAN = "kodStatusPergerakan";
	public static final String CATATAN = "catatan";

	public void save(Tblpergerakanfail transientInstance) {
		log.debug("saving Tblpergerakanfail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpergerakanfail persistentInstance) {
		log.debug("deleting Tblpergerakanfail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpergerakanfail findById(java.lang.Long id) {
		log.debug("getting Tblpergerakanfail instance with id: " + id);
		try {
			Tblpergerakanfail instance = (Tblpergerakanfail) getSession().get(
					"ekptg.model.entities.Tblpergerakanfail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpergerakanfail instance) {
		log.debug("finding Tblpergerakanfail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpergerakanfail").add(
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
		log.debug("finding Tblpergerakanfail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpergerakanfail as model where model."
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

	public List findByKodPenghantar(Object kodPenghantar) {
		return findByProperty(KOD_PENGHANTAR, kodPenghantar);
	}

	public List findByKodPenerima(Object kodPenerima) {
		return findByProperty(KOD_PENERIMA, kodPenerima);
	}

	public List findByKodStatusPergerakan(Object kodStatusPergerakan) {
		return findByProperty(KOD_STATUS_PERGERAKAN, kodStatusPergerakan);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findAll() {
		log.debug("finding all Tblpergerakanfail instances");
		try {
			String queryString = "from Tblpergerakanfail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpergerakanfail merge(Tblpergerakanfail detachedInstance) {
		log.debug("merging Tblpergerakanfail instance");
		try {
			Tblpergerakanfail result = (Tblpergerakanfail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpergerakanfail instance) {
		log.debug("attaching dirty Tblpergerakanfail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpergerakanfail instance) {
		log.debug("attaching clean Tblpergerakanfail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
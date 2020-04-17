package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblstatuspengguna entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblstatuspengguna
 * @author MyEclipse Persistence Tools
 */

public class TblstatuspenggunaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblstatuspenggunaDAO.class);
	// property constants
	public static final String KOD_STATUS_PENGGUNA = "kodStatusPengguna";
	public static final String STATUS_PENGGUNA = "statusPengguna";

	public void save(Tblstatuspengguna transientInstance) {
		log.debug("saving Tblstatuspengguna instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblstatuspengguna persistentInstance) {
		log.debug("deleting Tblstatuspengguna instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblstatuspengguna findById(java.lang.Long id) {
		log.debug("getting Tblstatuspengguna instance with id: " + id);
		try {
			Tblstatuspengguna instance = (Tblstatuspengguna) getSession().get(
					"ekptg.model.entities.Tblstatuspengguna", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblstatuspengguna instance) {
		log.debug("finding Tblstatuspengguna instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblstatuspengguna").add(
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
		log.debug("finding Tblstatuspengguna instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblstatuspengguna as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodStatusPengguna(Object kodStatusPengguna) {
		return findByProperty(KOD_STATUS_PENGGUNA, kodStatusPengguna);
	}

	public List findByStatusPengguna(Object statusPengguna) {
		return findByProperty(STATUS_PENGGUNA, statusPengguna);
	}

	public List findAll() {
		log.debug("finding all Tblstatuspengguna instances");
		try {
			String queryString = "from Tblstatuspengguna";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblstatuspengguna merge(Tblstatuspengguna detachedInstance) {
		log.debug("merging Tblstatuspengguna instance");
		try {
			Tblstatuspengguna result = (Tblstatuspengguna) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblstatuspengguna instance) {
		log.debug("attaching dirty Tblstatuspengguna instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblstatuspengguna instance) {
		log.debug("attaching clean Tblstatuspengguna instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
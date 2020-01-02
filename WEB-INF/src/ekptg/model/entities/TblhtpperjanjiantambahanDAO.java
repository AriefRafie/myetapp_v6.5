package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpperjanjiantambahan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblhtpperjanjiantambahan
 * @author MyEclipse Persistence Tools
 */

public class TblhtpperjanjiantambahanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpperjanjiantambahanDAO.class);
	// property constants
	public static final String ID_PERJANJIAN = "idPerjanjian";
	public static final String SEBAB = "sebab";
	public static final String TEMPOH = "tempoh";
	public static final String ULASAN = "ulasan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpperjanjiantambahan transientInstance) {
		log.debug("saving Tblhtpperjanjiantambahan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpperjanjiantambahan persistentInstance) {
		log.debug("deleting Tblhtpperjanjiantambahan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpperjanjiantambahan findById(java.lang.Long id) {
		log.debug("getting Tblhtpperjanjiantambahan instance with id: " + id);
		try {
			Tblhtpperjanjiantambahan instance = (Tblhtpperjanjiantambahan) getSession()
					.get("ekptg.model.entities.Tblhtpperjanjiantambahan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpperjanjiantambahan instance) {
		log.debug("finding Tblhtpperjanjiantambahan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpperjanjiantambahan").add(
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
		log.debug("finding Tblhtpperjanjiantambahan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpperjanjiantambahan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPerjanjian(Object idPerjanjian) {
		return findByProperty(ID_PERJANJIAN, idPerjanjian);
	}

	public List findBySebab(Object sebab) {
		return findByProperty(SEBAB, sebab);
	}

	public List findByTempoh(Object tempoh) {
		return findByProperty(TEMPOH, tempoh);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpperjanjiantambahan instances");
		try {
			String queryString = "from Tblhtpperjanjiantambahan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpperjanjiantambahan merge(
			Tblhtpperjanjiantambahan detachedInstance) {
		log.debug("merging Tblhtpperjanjiantambahan instance");
		try {
			Tblhtpperjanjiantambahan result = (Tblhtpperjanjiantambahan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpperjanjiantambahan instance) {
		log.debug("attaching dirty Tblhtpperjanjiantambahan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpperjanjiantambahan instance) {
		log.debug("attaching clean Tblhtpperjanjiantambahan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
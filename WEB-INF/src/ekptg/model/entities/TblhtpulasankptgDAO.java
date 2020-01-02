package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpulasankptg entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpulasankptg
 * @author MyEclipse Persistence Tools
 */

public class TblhtpulasankptgDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpulasankptgDAO.class);
	// property constants
	public static final String STATUS = "status";
	public static final String ULASAN = "ulasan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpulasankptg transientInstance) {
		log.debug("saving Tblhtpulasankptg instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpulasankptg persistentInstance) {
		log.debug("deleting Tblhtpulasankptg instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpulasankptg findById(java.lang.Long id) {
		log.debug("getting Tblhtpulasankptg instance with id: " + id);
		try {
			Tblhtpulasankptg instance = (Tblhtpulasankptg) getSession().get(
					"ekptg.model.entities.Tblhtpulasankptg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpulasankptg instance) {
		log.debug("finding Tblhtpulasankptg instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpulasankptg").add(
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
		log.debug("finding Tblhtpulasankptg instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpulasankptg as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
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
		log.debug("finding all Tblhtpulasankptg instances");
		try {
			String queryString = "from Tblhtpulasankptg";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpulasankptg merge(Tblhtpulasankptg detachedInstance) {
		log.debug("merging Tblhtpulasankptg instance");
		try {
			Tblhtpulasankptg result = (Tblhtpulasankptg) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpulasankptg instance) {
		log.debug("attaching dirty Tblhtpulasankptg instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpulasankptg instance) {
		log.debug("attaching clean Tblhtpulasankptg instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
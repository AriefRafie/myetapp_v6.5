package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpbayaransewa entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpbayaransewa
 * @author MyEclipse Persistence Tools
 */

public class TblphpbayaransewaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpbayaransewaDAO.class);
	// property constants
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String KADAR_SEWA = "kadarSewa";

	public void save(Tblphpbayaransewa transientInstance) {
		log.debug("saving Tblphpbayaransewa instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpbayaransewa persistentInstance) {
		log.debug("deleting Tblphpbayaransewa instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpbayaransewa findById(java.lang.Long id) {
		log.debug("getting Tblphpbayaransewa instance with id: " + id);
		try {
			Tblphpbayaransewa instance = (Tblphpbayaransewa) getSession().get(
					"ekptg.model.entities.Tblphpbayaransewa", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpbayaransewa instance) {
		log.debug("finding Tblphpbayaransewa instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpbayaransewa").add(
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
		log.debug("finding Tblphpbayaransewa instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpbayaransewa as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByKadarSewa(Object kadarSewa) {
		return findByProperty(KADAR_SEWA, kadarSewa);
	}

	public List findAll() {
		log.debug("finding all Tblphpbayaransewa instances");
		try {
			String queryString = "from Tblphpbayaransewa";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpbayaransewa merge(Tblphpbayaransewa detachedInstance) {
		log.debug("merging Tblphpbayaransewa instance");
		try {
			Tblphpbayaransewa result = (Tblphpbayaransewa) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpbayaransewa instance) {
		log.debug("attaching dirty Tblphpbayaransewa instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpbayaransewa instance) {
		log.debug("attaching clean Tblphpbayaransewa instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
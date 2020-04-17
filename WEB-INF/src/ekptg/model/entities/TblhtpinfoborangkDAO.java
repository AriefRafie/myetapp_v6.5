package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpinfoborangk entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpinfoborangk
 * @author MyEclipse Persistence Tools
 */

public class TblhtpinfoborangkDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpinfoborangkDAO.class);
	// property constants
	public static final String ID_BORANGK = "idBorangk";
	public static final String ID_HTPHAKMILIK = "idHtphakmilik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpinfoborangk transientInstance) {
		log.debug("saving Tblhtpinfoborangk instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpinfoborangk persistentInstance) {
		log.debug("deleting Tblhtpinfoborangk instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpinfoborangk findById(java.lang.Long id) {
		log.debug("getting Tblhtpinfoborangk instance with id: " + id);
		try {
			Tblhtpinfoborangk instance = (Tblhtpinfoborangk) getSession().get(
					"ekptg.model.entities.Tblhtpinfoborangk", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpinfoborangk instance) {
		log.debug("finding Tblhtpinfoborangk instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpinfoborangk").add(
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
		log.debug("finding Tblhtpinfoborangk instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpinfoborangk as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdBorangk(Object idBorangk) {
		return findByProperty(ID_BORANGK, idBorangk);
	}

	public List findByIdHtphakmilik(Object idHtphakmilik) {
		return findByProperty(ID_HTPHAKMILIK, idHtphakmilik);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpinfoborangk instances");
		try {
			String queryString = "from Tblhtpinfoborangk";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpinfoborangk merge(Tblhtpinfoborangk detachedInstance) {
		log.debug("merging Tblhtpinfoborangk instance");
		try {
			Tblhtpinfoborangk result = (Tblhtpinfoborangk) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpinfoborangk instance) {
		log.debug("attaching dirty Tblhtpinfoborangk instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpinfoborangk instance) {
		log.debug("attaching clean Tblhtpinfoborangk instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
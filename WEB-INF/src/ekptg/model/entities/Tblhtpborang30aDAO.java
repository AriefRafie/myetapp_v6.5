package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpborang30a entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpborang30a
 * @author MyEclipse Persistence Tools
 */

public class Tblhtpborang30aDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(Tblhtpborang30aDAO.class);
	// property constants
	public static final String NO_PESERAHAN = "noPeserahan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpborang30a transientInstance) {
		log.debug("saving Tblhtpborang30a instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpborang30a persistentInstance) {
		log.debug("deleting Tblhtpborang30a instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpborang30a findById(java.lang.Long id) {
		log.debug("getting Tblhtpborang30a instance with id: " + id);
		try {
			Tblhtpborang30a instance = (Tblhtpborang30a) getSession().get(
					"ekptg.model.entities.Tblhtpborang30a", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpborang30a instance) {
		log.debug("finding Tblhtpborang30a instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpborang30a").add(
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
		log.debug("finding Tblhtpborang30a instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpborang30a as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoPeserahan(Object noPeserahan) {
		return findByProperty(NO_PESERAHAN, noPeserahan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpborang30a instances");
		try {
			String queryString = "from Tblhtpborang30a";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpborang30a merge(Tblhtpborang30a detachedInstance) {
		log.debug("merging Tblhtpborang30a instance");
		try {
			Tblhtpborang30a result = (Tblhtpborang30a) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpborang30a instance) {
		log.debug("attaching dirty Tblhtpborang30a instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpborang30a instance) {
		log.debug("attaching clean Tblhtpborang30a instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
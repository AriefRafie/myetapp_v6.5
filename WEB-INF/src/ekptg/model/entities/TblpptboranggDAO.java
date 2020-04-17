package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangg entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangg
 * @author MyEclipse Persistence Tools
 */

public class TblpptboranggDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptboranggDAO.class);
	// property constants
	public static final String ID_SIASATAN = "idSiasatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangg transientInstance) {
		log.debug("saving Tblpptborangg instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangg persistentInstance) {
		log.debug("deleting Tblpptborangg instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangg findById(java.lang.Long id) {
		log.debug("getting Tblpptborangg instance with id: " + id);
		try {
			Tblpptborangg instance = (Tblpptborangg) getSession().get(
					"ekptg.model.entities.Tblpptborangg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangg instance) {
		log.debug("finding Tblpptborangg instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangg").add(
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
		log.debug("finding Tblpptborangg instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangg as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdSiasatan(Object idSiasatan) {
		return findByProperty(ID_SIASATAN, idSiasatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblpptborangg instances");
		try {
			String queryString = "from Tblpptborangg";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangg merge(Tblpptborangg detachedInstance) {
		log.debug("merging Tblpptborangg instance");
		try {
			Tblpptborangg result = (Tblpptborangg) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangg instance) {
		log.debug("attaching dirty Tblpptborangg instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangg instance) {
		log.debug("attaching clean Tblpptborangg instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtenakmenpindaseksyen entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblpdtenakmenpindaseksyen
 * @author MyEclipse Persistence Tools
 */

public class TblpdtenakmenpindaseksyenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtenakmenpindaseksyenDAO.class);
	// property constants
	public static final String NO_SEKSYEN = "noSeksyen";
	public static final String NAMA_SEKSYEN = "namaSeksyen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtenakmenpindaseksyen transientInstance) {
		log.debug("saving Tblpdtenakmenpindaseksyen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtenakmenpindaseksyen persistentInstance) {
		log.debug("deleting Tblpdtenakmenpindaseksyen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpindaseksyen findById(java.lang.Long id) {
		log.debug("getting Tblpdtenakmenpindaseksyen instance with id: " + id);
		try {
			Tblpdtenakmenpindaseksyen instance = (Tblpdtenakmenpindaseksyen) getSession()
					.get("ekptg.model.entities.Tblpdtenakmenpindaseksyen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtenakmenpindaseksyen instance) {
		log.debug("finding Tblpdtenakmenpindaseksyen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtenakmenpindaseksyen").add(
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
		log.debug("finding Tblpdtenakmenpindaseksyen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtenakmenpindaseksyen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoSeksyen(Object noSeksyen) {
		return findByProperty(NO_SEKSYEN, noSeksyen);
	}

	public List findByNamaSeksyen(Object namaSeksyen) {
		return findByProperty(NAMA_SEKSYEN, namaSeksyen);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtenakmenpindaseksyen instances");
		try {
			String queryString = "from Tblpdtenakmenpindaseksyen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpindaseksyen merge(
			Tblpdtenakmenpindaseksyen detachedInstance) {
		log.debug("merging Tblpdtenakmenpindaseksyen instance");
		try {
			Tblpdtenakmenpindaseksyen result = (Tblpdtenakmenpindaseksyen) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtenakmenpindaseksyen instance) {
		log.debug("attaching dirty Tblpdtenakmenpindaseksyen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtenakmenpindaseksyen instance) {
		log.debug("attaching clean Tblpdtenakmenpindaseksyen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
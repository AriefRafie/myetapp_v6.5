package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppenyediaulasanjpph entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphppenyediaulasanjpph
 * @author MyEclipse Persistence Tools
 */

public class TblphppenyediaulasanjpphDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppenyediaulasanjpphDAO.class);
	// property constants
	public static final String NAMA_PENYEDIA = "namaPenyedia";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphppenyediaulasanjpph transientInstance) {
		log.debug("saving Tblphppenyediaulasanjpph instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppenyediaulasanjpph persistentInstance) {
		log.debug("deleting Tblphppenyediaulasanjpph instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppenyediaulasanjpph findById(java.lang.Long id) {
		log.debug("getting Tblphppenyediaulasanjpph instance with id: " + id);
		try {
			Tblphppenyediaulasanjpph instance = (Tblphppenyediaulasanjpph) getSession()
					.get("ekptg.model.entities.Tblphppenyediaulasanjpph", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppenyediaulasanjpph instance) {
		log.debug("finding Tblphppenyediaulasanjpph instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppenyediaulasanjpph").add(
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
		log.debug("finding Tblphppenyediaulasanjpph instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppenyediaulasanjpph as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaPenyedia(Object namaPenyedia) {
		return findByProperty(NAMA_PENYEDIA, namaPenyedia);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphppenyediaulasanjpph instances");
		try {
			String queryString = "from Tblphppenyediaulasanjpph";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppenyediaulasanjpph merge(
			Tblphppenyediaulasanjpph detachedInstance) {
		log.debug("merging Tblphppenyediaulasanjpph instance");
		try {
			Tblphppenyediaulasanjpph result = (Tblphppenyediaulasanjpph) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppenyediaulasanjpph instance) {
		log.debug("attaching dirty Tblphppenyediaulasanjpph instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppenyediaulasanjpph instance) {
		log.debug("attaching clean Tblphppenyediaulasanjpph instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppemeganglesenperjanjian entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphppemeganglesenperjanjian
 * @author MyEclipse Persistence Tools
 */

public class TblphppemeganglesenperjanjianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppemeganglesenperjanjianDAO.class);
	// property constants
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphppemeganglesenperjanjian transientInstance) {
		log.debug("saving Tblphppemeganglesenperjanjian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppemeganglesenperjanjian persistentInstance) {
		log.debug("deleting Tblphppemeganglesenperjanjian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppemeganglesenperjanjian findById(java.lang.Long id) {
		log.debug("getting Tblphppemeganglesenperjanjian instance with id: "
				+ id);
		try {
			Tblphppemeganglesenperjanjian instance = (Tblphppemeganglesenperjanjian) getSession()
					.get("ekptg.model.entities.Tblphppemeganglesenperjanjian",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppemeganglesenperjanjian instance) {
		log.debug("finding Tblphppemeganglesenperjanjian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppemeganglesenperjanjian").add(
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
		log
				.debug("finding Tblphppemeganglesenperjanjian instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppemeganglesenperjanjian as model where model."
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

	public List findAll() {
		log.debug("finding all Tblphppemeganglesenperjanjian instances");
		try {
			String queryString = "from Tblphppemeganglesenperjanjian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppemeganglesenperjanjian merge(
			Tblphppemeganglesenperjanjian detachedInstance) {
		log.debug("merging Tblphppemeganglesenperjanjian instance");
		try {
			Tblphppemeganglesenperjanjian result = (Tblphppemeganglesenperjanjian) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppemeganglesenperjanjian instance) {
		log.debug("attaching dirty Tblphppemeganglesenperjanjian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppemeganglesenperjanjian instance) {
		log.debug("attaching clean Tblphppemeganglesenperjanjian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
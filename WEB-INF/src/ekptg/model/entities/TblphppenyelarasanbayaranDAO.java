package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppenyelarasanbayaran entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphppenyelarasanbayaran
 * @author MyEclipse Persistence Tools
 */

public class TblphppenyelarasanbayaranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppenyelarasanbayaranDAO.class);
	// property constants
	public static final String BAKI_BAWAKEHADAPAN = "bakiBawakehadapan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphppenyelarasanbayaran transientInstance) {
		log.debug("saving Tblphppenyelarasanbayaran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppenyelarasanbayaran persistentInstance) {
		log.debug("deleting Tblphppenyelarasanbayaran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppenyelarasanbayaran findById(java.lang.Long id) {
		log.debug("getting Tblphppenyelarasanbayaran instance with id: " + id);
		try {
			Tblphppenyelarasanbayaran instance = (Tblphppenyelarasanbayaran) getSession()
					.get("ekptg.model.entities.Tblphppenyelarasanbayaran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppenyelarasanbayaran instance) {
		log.debug("finding Tblphppenyelarasanbayaran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppenyelarasanbayaran").add(
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
		log.debug("finding Tblphppenyelarasanbayaran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppenyelarasanbayaran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBakiBawakehadapan(Object bakiBawakehadapan) {
		return findByProperty(BAKI_BAWAKEHADAPAN, bakiBawakehadapan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphppenyelarasanbayaran instances");
		try {
			String queryString = "from Tblphppenyelarasanbayaran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppenyelarasanbayaran merge(
			Tblphppenyelarasanbayaran detachedInstance) {
		log.debug("merging Tblphppenyelarasanbayaran instance");
		try {
			Tblphppenyelarasanbayaran result = (Tblphppenyelarasanbayaran) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppenyelarasanbayaran instance) {
		log.debug("attaching dirty Tblphppenyelarasanbayaran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppenyelarasanbayaran instance) {
		log.debug("attaching clean Tblphppenyelarasanbayaran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
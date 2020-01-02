package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblkategorifail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblkategorifail
 * @author MyEclipse Persistence Tools
 */

public class TblkategorifailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblkategorifailDAO.class);
	// property constants
	public static final String KOD_KATEGORI_FAIL = "kodKategoriFail";
	public static final String KATEGORI_FAIL = "kategoriFail";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblkategorifail transientInstance) {
		log.debug("saving Tblkategorifail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblkategorifail persistentInstance) {
		log.debug("deleting Tblkategorifail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblkategorifail findById(java.lang.Long id) {
		log.debug("getting Tblkategorifail instance with id: " + id);
		try {
			Tblkategorifail instance = (Tblkategorifail) getSession().get(
					"ekptg.model.entities.Tblkategorifail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblkategorifail instance) {
		log.debug("finding Tblkategorifail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblkategorifail").add(
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
		log.debug("finding Tblkategorifail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblkategorifail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodKategoriFail(Object kodKategoriFail) {
		return findByProperty(KOD_KATEGORI_FAIL, kodKategoriFail);
	}

	public List findByKategoriFail(Object kategoriFail) {
		return findByProperty(KATEGORI_FAIL, kategoriFail);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblkategorifail instances");
		try {
			String queryString = "from Tblkategorifail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblkategorifail merge(Tblkategorifail detachedInstance) {
		log.debug("merging Tblkategorifail instance");
		try {
			Tblkategorifail result = (Tblkategorifail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblkategorifail instance) {
		log.debug("attaching dirty Tblkategorifail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblkategorifail instance) {
		log.debug("attaching clean Tblkategorifail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
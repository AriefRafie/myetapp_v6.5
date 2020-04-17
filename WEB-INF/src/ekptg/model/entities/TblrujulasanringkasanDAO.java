package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujulasanringkasan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujulasanringkasan
 * @author MyEclipse Persistence Tools
 */

public class TblrujulasanringkasanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujulasanringkasanDAO.class);
	// property constants
	public static final String KOD_ULASANRINGKASAN = "kodUlasanringkasan";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblrujulasanringkasan transientInstance) {
		log.debug("saving Tblrujulasanringkasan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujulasanringkasan persistentInstance) {
		log.debug("deleting Tblrujulasanringkasan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujulasanringkasan findById(java.lang.Long id) {
		log.debug("getting Tblrujulasanringkasan instance with id: " + id);
		try {
			Tblrujulasanringkasan instance = (Tblrujulasanringkasan) getSession().get(
					"ekptg.model.entities.Tblrujulasanringkasan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujulasanringkasan instance) {
		log.debug("finding Tblrujulasanringkasan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujulasanringkasan").add(
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
		log.debug("finding Tblrujulasanringkasan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujulasanringkasan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodUlasanringkasan(Object kodUlasanringkasan) {
		return findByProperty(KOD_ULASANRINGKASAN, kodUlasanringkasan);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblrujulasanringkasan instances");
		try {
			String queryString = "from Tblrujulasanringkasan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujulasanringkasan merge(Tblrujulasanringkasan detachedInstance) {
		log.debug("merging Tblrujulasanringkasan instance");
		try {
			Tblrujulasanringkasan result = (Tblrujulasanringkasan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujulasanringkasan instance) {
		log.debug("attaching dirty Tblrujulasanringkasan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujulasanringkasan instance) {
		log.debug("attaching clean Tblrujulasanringkasan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
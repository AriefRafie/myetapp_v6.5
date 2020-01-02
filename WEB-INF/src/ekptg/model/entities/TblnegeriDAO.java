package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblnegeri entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblnegeri
 * @author MyEclipse Persistence Tools
 */

public class TblnegeriDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblnegeriDAO.class);
	// property constants
	public static final String KOD_NEGERI = "kodNegeri";
	public static final String KOD_NEGARA = "kodNegara";
	public static final String NAMA_NEGERI = "namaNegeri";

	public void save(Tblnegeri transientInstance) {
		log.debug("saving Tblnegeri instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblnegeri persistentInstance) {
		log.debug("deleting Tblnegeri instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblnegeri findById(java.lang.Long id) {
		log.debug("getting Tblnegeri instance with id: " + id);
		try {
			Tblnegeri instance = (Tblnegeri) getSession().get(
					"ekptg.model.entities.Tblnegeri", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblnegeri instance) {
		log.debug("finding Tblnegeri instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblnegeri").add(
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
		log.debug("finding Tblnegeri instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblnegeri as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodNegeri(Object kodNegeri) {
		return findByProperty(KOD_NEGERI, kodNegeri);
	}

	public List findByKodNegara(Object kodNegara) {
		return findByProperty(KOD_NEGARA, kodNegara);
	}

	public List findByNamaNegeri(Object namaNegeri) {
		return findByProperty(NAMA_NEGERI, namaNegeri);
	}

	public List findAll() {
		log.debug("finding all Tblnegeri instances");
		try {
			String queryString = "from Tblnegeri";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblnegeri merge(Tblnegeri detachedInstance) {
		log.debug("merging Tblnegeri instance");
		try {
			Tblnegeri result = (Tblnegeri) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblnegeri instance) {
		log.debug("attaching dirty Tblnegeri instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblnegeri instance) {
		log.debug("attaching clean Tblnegeri instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
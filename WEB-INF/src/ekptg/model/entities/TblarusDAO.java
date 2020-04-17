package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblarus entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblarus
 * @author MyEclipse Persistence Tools
 */

public class TblarusDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblarusDAO.class);
	// property constants
	public static final String KOD_LANGKAH = "kodLangkah";
	public static final String KOD_ARUS = "kodArus";
	public static final String NAMA_ARUS = "namaArus";
	public static final String KETERANGAN = "keterangan";

	public void save(Tblarus transientInstance) {
		log.debug("saving Tblarus instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblarus persistentInstance) {
		log.debug("deleting Tblarus instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblarus findById(java.lang.Long id) {
		log.debug("getting Tblarus instance with id: " + id);
		try {
			Tblarus instance = (Tblarus) getSession().get(
					"ekptg.model.entities.Tblarus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblarus instance) {
		log.debug("finding Tblarus instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblarus").add(
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
		log.debug("finding Tblarus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblarus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodLangkah(Object kodLangkah) {
		return findByProperty(KOD_LANGKAH, kodLangkah);
	}

	public List findByKodArus(Object kodArus) {
		return findByProperty(KOD_ARUS, kodArus);
	}

	public List findByNamaArus(Object namaArus) {
		return findByProperty(NAMA_ARUS, namaArus);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findAll() {
		log.debug("finding all Tblarus instances");
		try {
			String queryString = "from Tblarus";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblarus merge(Tblarus detachedInstance) {
		log.debug("merging Tblarus instance");
		try {
			Tblarus result = (Tblarus) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblarus instance) {
		log.debug("attaching dirty Tblarus instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblarus instance) {
		log.debug("attaching clean Tblarus instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
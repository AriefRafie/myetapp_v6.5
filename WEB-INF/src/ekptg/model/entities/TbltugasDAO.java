package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tbltugas entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tbltugas
 * @author MyEclipse Persistence Tools
 */

public class TbltugasDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TbltugasDAO.class);
	// property constants
	public static final String KOD_KUMPULAN = "kodKumpulan";
	public static final String KOD_LANGKAH = "kodLangkah";

	public void save(Tbltugas transientInstance) {
		log.debug("saving Tbltugas instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tbltugas persistentInstance) {
		log.debug("deleting Tbltugas instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tbltugas findById(java.lang.Long id) {
		log.debug("getting Tbltugas instance with id: " + id);
		try {
			Tbltugas instance = (Tbltugas) getSession().get(
					"ekptg.model.entities.Tbltugas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tbltugas instance) {
		log.debug("finding Tbltugas instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tbltugas").add(
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
		log.debug("finding Tbltugas instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tbltugas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodKumpulan(Object kodKumpulan) {
		return findByProperty(KOD_KUMPULAN, kodKumpulan);
	}

	public List findByKodLangkah(Object kodLangkah) {
		return findByProperty(KOD_LANGKAH, kodLangkah);
	}

	public List findAll() {
		log.debug("finding all Tbltugas instances");
		try {
			String queryString = "from Tbltugas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tbltugas merge(Tbltugas detachedInstance) {
		log.debug("merging Tbltugas instance");
		try {
			Tbltugas result = (Tbltugas) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tbltugas instance) {
		log.debug("attaching dirty Tbltugas instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tbltugas instance) {
		log.debug("attaching clean Tbltugas instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
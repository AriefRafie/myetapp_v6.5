package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblfail entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblfail
 * @author MyEclipse Persistence Tools
 */

public class TblfailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblfailDAO.class);
	// property constants
	public static final String KOD_FAIL = "kodFail";
	public static final String TAJUK_FAIL = "tajukFail";
	public static final String CATATAN = "catatan";
	public static final String KOD_PENGGUNA = "kodPengguna";
	public static final String KOD_STATUS_FAIL = "kodStatusFail";
	public static final String KOD_KATEGORI_KESELAMATAN = "kodKategoriKeselamatan";
	public static final String KOD_KATEGORI_FAIL = "kodKategoriFail";

	public void save(Tblfail transientInstance) {
		log.debug("saving Tblfail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblfail persistentInstance) {
		log.debug("deleting Tblfail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblfail findById(java.lang.Long id) {
		log.debug("getting Tblfail instance with id: " + id);
		try {
			Tblfail instance = (Tblfail) getSession().get(
					"ekptg.model.entities.Tblfail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblfail instance) {
		log.debug("finding Tblfail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblfail").add(
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
		log.debug("finding Tblfail instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblfail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodFail(Object kodFail) {
		return findByProperty(KOD_FAIL, kodFail);
	}

	public List findByTajukFail(Object tajukFail) {
		return findByProperty(TAJUK_FAIL, tajukFail);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByKodPengguna(Object kodPengguna) {
		return findByProperty(KOD_PENGGUNA, kodPengguna);
	}

	public List findByKodStatusFail(Object kodStatusFail) {
		return findByProperty(KOD_STATUS_FAIL, kodStatusFail);
	}

	public List findByKodKategoriKeselamatan(Object kodKategoriKeselamatan) {
		return findByProperty(KOD_KATEGORI_KESELAMATAN, kodKategoriKeselamatan);
	}

	public List findByKodKategoriFail(Object kodKategoriFail) {
		return findByProperty(KOD_KATEGORI_FAIL, kodKategoriFail);
	}

	public List findAll() {
		log.debug("finding all Tblfail instances");
		try {
			String queryString = "from Tblfail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblfail merge(Tblfail detachedInstance) {
		log.debug("merging Tblfail instance");
		try {
			Tblfail result = (Tblfail) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblfail instance) {
		log.debug("attaching dirty Tblfail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblfail instance) {
		log.debug("attaching clean Tblfail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
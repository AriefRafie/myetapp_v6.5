package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpdokumenperjanjian entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpdokumenperjanjian
 * @author MyEclipse Persistence Tools
 */

public class TblhtpdokumenperjanjianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpdokumenperjanjianDAO.class);
	// property constants
	public static final String FLAG = "flag";
	public static final String PIHAK = "pihak";
	public static final String ULASAN = "ulasan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpdokumenperjanjian transientInstance) {
		log.debug("saving Tblhtpdokumenperjanjian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpdokumenperjanjian persistentInstance) {
		log.debug("deleting Tblhtpdokumenperjanjian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpdokumenperjanjian findById(java.lang.Long id) {
		log.debug("getting Tblhtpdokumenperjanjian instance with id: " + id);
		try {
			Tblhtpdokumenperjanjian instance = (Tblhtpdokumenperjanjian) getSession()
					.get("ekptg.model.entities.Tblhtpdokumenperjanjian", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpdokumenperjanjian instance) {
		log.debug("finding Tblhtpdokumenperjanjian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpdokumenperjanjian").add(
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
		log.debug("finding Tblhtpdokumenperjanjian instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpdokumenperjanjian as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFlag(Object flag) {
		return findByProperty(FLAG, flag);
	}

	public List findByPihak(Object pihak) {
		return findByProperty(PIHAK, pihak);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpdokumenperjanjian instances");
		try {
			String queryString = "from Tblhtpdokumenperjanjian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpdokumenperjanjian merge(
			Tblhtpdokumenperjanjian detachedInstance) {
		log.debug("merging Tblhtpdokumenperjanjian instance");
		try {
			Tblhtpdokumenperjanjian result = (Tblhtpdokumenperjanjian) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpdokumenperjanjian instance) {
		log.debug("attaching dirty Tblhtpdokumenperjanjian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpdokumenperjanjian instance) {
		log.debug("attaching clean Tblhtpdokumenperjanjian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
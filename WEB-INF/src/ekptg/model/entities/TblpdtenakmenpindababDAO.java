package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtenakmenpindabab entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtenakmenpindabab
 * @author MyEclipse Persistence Tools
 */

public class TblpdtenakmenpindababDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtenakmenpindababDAO.class);
	// property constants
	public static final String NAMA_BAB = "namaBab";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtenakmenpindabab transientInstance) {
		log.debug("saving Tblpdtenakmenpindabab instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtenakmenpindabab persistentInstance) {
		log.debug("deleting Tblpdtenakmenpindabab instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpindabab findById(java.lang.Long id) {
		log.debug("getting Tblpdtenakmenpindabab instance with id: " + id);
		try {
			Tblpdtenakmenpindabab instance = (Tblpdtenakmenpindabab) getSession()
					.get("ekptg.model.entities.Tblpdtenakmenpindabab", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtenakmenpindabab instance) {
		log.debug("finding Tblpdtenakmenpindabab instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtenakmenpindabab").add(
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
		log.debug("finding Tblpdtenakmenpindabab instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtenakmenpindabab as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaBab(Object namaBab) {
		return findByProperty(NAMA_BAB, namaBab);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtenakmenpindabab instances");
		try {
			String queryString = "from Tblpdtenakmenpindabab";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpindabab merge(Tblpdtenakmenpindabab detachedInstance) {
		log.debug("merging Tblpdtenakmenpindabab instance");
		try {
			Tblpdtenakmenpindabab result = (Tblpdtenakmenpindabab) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtenakmenpindabab instance) {
		log.debug("attaching dirty Tblpdtenakmenpindabab instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtenakmenpindabab instance) {
		log.debug("attaching clean Tblpdtenakmenpindabab instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
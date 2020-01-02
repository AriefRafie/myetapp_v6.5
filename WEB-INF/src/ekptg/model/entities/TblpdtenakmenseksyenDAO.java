package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtenakmenseksyen entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtenakmenseksyen
 * @author MyEclipse Persistence Tools
 */

public class TblpdtenakmenseksyenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtenakmenseksyenDAO.class);
	// property constants
	public static final String NO_SEKSYEN = "noSeksyen";
	public static final String NAMA_SEKSYEN = "namaSeksyen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtenakmenseksyen transientInstance) {
		log.debug("saving Tblpdtenakmenseksyen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtenakmenseksyen persistentInstance) {
		log.debug("deleting Tblpdtenakmenseksyen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenseksyen findById(java.lang.Long id) {
		log.debug("getting Tblpdtenakmenseksyen instance with id: " + id);
		try {
			Tblpdtenakmenseksyen instance = (Tblpdtenakmenseksyen) getSession()
					.get("ekptg.model.entities.Tblpdtenakmenseksyen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtenakmenseksyen instance) {
		log.debug("finding Tblpdtenakmenseksyen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtenakmenseksyen").add(
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
		log.debug("finding Tblpdtenakmenseksyen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtenakmenseksyen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoSeksyen(Object noSeksyen) {
		return findByProperty(NO_SEKSYEN, noSeksyen);
	}

	public List findByNamaSeksyen(Object namaSeksyen) {
		return findByProperty(NAMA_SEKSYEN, namaSeksyen);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtenakmenseksyen instances");
		try {
			String queryString = "from Tblpdtenakmenseksyen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenseksyen merge(Tblpdtenakmenseksyen detachedInstance) {
		log.debug("merging Tblpdtenakmenseksyen instance");
		try {
			Tblpdtenakmenseksyen result = (Tblpdtenakmenseksyen) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtenakmenseksyen instance) {
		log.debug("attaching dirty Tblpdtenakmenseksyen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtenakmenseksyen instance) {
		log.debug("attaching clean Tblpdtenakmenseksyen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
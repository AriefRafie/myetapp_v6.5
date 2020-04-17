package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtaktapindaseksyen entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtaktapindaseksyen
 * @author MyEclipse Persistence Tools
 */

public class TblpdtaktapindaseksyenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtaktapindaseksyenDAO.class);
	// property constants
	public static final String NO_SEKSYEN = "noSeksyen";
	public static final String NAMA_SEKSYEN = "namaSeksyen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtaktapindaseksyen transientInstance) {
		log.debug("saving Tblpdtaktapindaseksyen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtaktapindaseksyen persistentInstance) {
		log.debug("deleting Tblpdtaktapindaseksyen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtaktapindaseksyen findById(java.lang.Long id) {
		log.debug("getting Tblpdtaktapindaseksyen instance with id: " + id);
		try {
			Tblpdtaktapindaseksyen instance = (Tblpdtaktapindaseksyen) getSession()
					.get("ekptg.model.entities.Tblpdtaktapindaseksyen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtaktapindaseksyen instance) {
		log.debug("finding Tblpdtaktapindaseksyen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtaktapindaseksyen").add(
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
		log.debug("finding Tblpdtaktapindaseksyen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtaktapindaseksyen as model where model."
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
		log.debug("finding all Tblpdtaktapindaseksyen instances");
		try {
			String queryString = "from Tblpdtaktapindaseksyen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtaktapindaseksyen merge(Tblpdtaktapindaseksyen detachedInstance) {
		log.debug("merging Tblpdtaktapindaseksyen instance");
		try {
			Tblpdtaktapindaseksyen result = (Tblpdtaktapindaseksyen) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtaktapindaseksyen instance) {
		log.debug("attaching dirty Tblpdtaktapindaseksyen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtaktapindaseksyen instance) {
		log.debug("attaching clean Tblpdtaktapindaseksyen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtaktaseksyen entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtaktaseksyen
 * @author MyEclipse Persistence Tools
 */

public class TblpdtaktaseksyenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtaktaseksyenDAO.class);
	// property constants
	public static final String NO_SEKSYEN = "noSeksyen";
	public static final String NAMA_SEKSYEN = "namaSeksyen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtaktaseksyen transientInstance) {
		log.debug("saving Tblpdtaktaseksyen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtaktaseksyen persistentInstance) {
		log.debug("deleting Tblpdtaktaseksyen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtaktaseksyen findById(java.lang.Long id) {
		log.debug("getting Tblpdtaktaseksyen instance with id: " + id);
		try {
			Tblpdtaktaseksyen instance = (Tblpdtaktaseksyen) getSession().get(
					"ekptg.model.entities.Tblpdtaktaseksyen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtaktaseksyen instance) {
		log.debug("finding Tblpdtaktaseksyen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtaktaseksyen").add(
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
		log.debug("finding Tblpdtaktaseksyen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtaktaseksyen as model where model."
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
		log.debug("finding all Tblpdtaktaseksyen instances");
		try {
			String queryString = "from Tblpdtaktaseksyen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtaktaseksyen merge(Tblpdtaktaseksyen detachedInstance) {
		log.debug("merging Tblpdtaktaseksyen instance");
		try {
			Tblpdtaktaseksyen result = (Tblpdtaktaseksyen) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtaktaseksyen instance) {
		log.debug("attaching dirty Tblpdtaktaseksyen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtaktaseksyen instance) {
		log.debug("attaching clean Tblpdtaktaseksyen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
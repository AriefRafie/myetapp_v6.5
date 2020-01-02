package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujseksyen entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujseksyen
 * @author MyEclipse Persistence Tools
 */

public class TblrujseksyenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujseksyenDAO.class);
	// property constants
	public static final String KOD_SEKSYEN = "kodSeksyen";
	public static final String NAMA_SEKSYEN = "namaSeksyen";
	public static final String VERSI_SEKSYEN = "versiSeksyen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujseksyen transientInstance) {
		log.debug("saving Tblrujseksyen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujseksyen persistentInstance) {
		log.debug("deleting Tblrujseksyen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujseksyen findById(java.lang.Long id) {
		log.debug("getting Tblrujseksyen instance with id: " + id);
		try {
			Tblrujseksyen instance = (Tblrujseksyen) getSession().get(
					"ekptg.model.entities.Tblrujseksyen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujseksyen instance) {
		log.debug("finding Tblrujseksyen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujseksyen").add(
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
		log.debug("finding Tblrujseksyen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujseksyen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodSeksyen(Object kodSeksyen) {
		return findByProperty(KOD_SEKSYEN, kodSeksyen);
	}

	public List findByNamaSeksyen(Object namaSeksyen) {
		return findByProperty(NAMA_SEKSYEN, namaSeksyen);
	}

	public List findByVersiSeksyen(Object versiSeksyen) {
		return findByProperty(VERSI_SEKSYEN, versiSeksyen);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujseksyen instances");
		try {
			String queryString = "from Tblrujseksyen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujseksyen merge(Tblrujseksyen detachedInstance) {
		log.debug("merging Tblrujseksyen instance");
		try {
			Tblrujseksyen result = (Tblrujseksyen) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujseksyen instance) {
		log.debug("attaching dirty Tblrujseksyen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujseksyen instance) {
		log.debug("attaching clean Tblrujseksyen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujpfdseksyen entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujpfdseksyen
 * @author MyEclipse Persistence Tools
 */

public class TblrujpfdseksyenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujpfdseksyenDAO.class);
	// property constants
	public static final String KOD_SEKSYEN = "kodSeksyen";
	public static final String NAMA_SEKSYEN = "namaSeksyen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujpfdseksyen transientInstance) {
		log.debug("saving Tblrujpfdseksyen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujpfdseksyen persistentInstance) {
		log.debug("deleting Tblrujpfdseksyen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujpfdseksyen findById(java.lang.Long id) {
		log.debug("getting Tblrujpfdseksyen instance with id: " + id);
		try {
			Tblrujpfdseksyen instance = (Tblrujpfdseksyen) getSession().get(
					"ekptg.model.entities.Tblrujpfdseksyen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujpfdseksyen instance) {
		log.debug("finding Tblrujpfdseksyen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujpfdseksyen").add(
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
		log.debug("finding Tblrujpfdseksyen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujpfdseksyen as model where model."
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

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujpfdseksyen instances");
		try {
			String queryString = "from Tblrujpfdseksyen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujpfdseksyen merge(Tblrujpfdseksyen detachedInstance) {
		log.debug("merging Tblrujpfdseksyen instance");
		try {
			Tblrujpfdseksyen result = (Tblrujpfdseksyen) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujpfdseksyen instance) {
		log.debug("attaching dirty Tblrujpfdseksyen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujpfdseksyen instance) {
		log.debug("attaching clean Tblrujpfdseksyen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
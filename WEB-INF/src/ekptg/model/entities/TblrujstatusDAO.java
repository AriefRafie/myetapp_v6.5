package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujstatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujstatus
 * @author MyEclipse Persistence Tools
 */

public class TblrujstatusDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujstatusDAO.class);
	// property constants
	public static final String KOD_STATUS = "kodStatus";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_SEKSYEN = "idSeksyen";

	public void save(Tblrujstatus transientInstance) {
		log.debug("saving Tblrujstatus instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujstatus persistentInstance) {
		log.debug("deleting Tblrujstatus instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujstatus findById(java.lang.Long id) {
		log.debug("getting Tblrujstatus instance with id: " + id);
		try {
			Tblrujstatus instance = (Tblrujstatus) getSession().get(
					"ekptg.model.entities.Tblrujstatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujstatus instance) {
		log.debug("finding Tblrujstatus instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujstatus").add(
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
		log.debug("finding Tblrujstatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujstatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodStatus(Object kodStatus) {
		return findByProperty(KOD_STATUS, kodStatus);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdSeksyen(Object idSeksyen) {
		return findByProperty(ID_SEKSYEN, idSeksyen);
	}

	public List findAll() {
		log.debug("finding all Tblrujstatus instances");
		try {
			String queryString = "from Tblrujstatus";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujstatus merge(Tblrujstatus detachedInstance) {
		log.debug("merging Tblrujstatus instance");
		try {
			Tblrujstatus result = (Tblrujstatus) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujstatus instance) {
		log.debug("attaching dirty Tblrujstatus instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujstatus instance) {
		log.debug("attaching clean Tblrujstatus instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
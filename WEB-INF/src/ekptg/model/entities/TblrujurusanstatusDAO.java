package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujurusanstatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujurusanstatus
 * @author MyEclipse Persistence Tools
 */

public class TblrujurusanstatusDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujurusanstatusDAO.class);
	// property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_STATUS_FAIL = "idStatusFail";
	public static final String ID_URUSAN = "idUrusan";
	public static final String AKTIF = "aktif";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujurusanstatus transientInstance) {
		log.debug("saving Tblrujurusanstatus instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujurusanstatus persistentInstance) {
		log.debug("deleting Tblrujurusanstatus instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujurusanstatus findById(java.lang.Long id) {
		log.debug("getting Tblrujurusanstatus instance with id: " + id);
		try {
			Tblrujurusanstatus instance = (Tblrujurusanstatus) getSession()
					.get("ekptg.model.entities.Tblrujurusanstatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujurusanstatus instance) {
		log.debug("finding Tblrujurusanstatus instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujurusanstatus").add(
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
		log.debug("finding Tblrujurusanstatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujurusanstatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByIdStatusFail(Object idStatusFail) {
		return findByProperty(ID_STATUS_FAIL, idStatusFail);
	}

	public List findByIdUrusan(Object idUrusan) {
		return findByProperty(ID_URUSAN, idUrusan);
	}

	public List findByAktif(Object aktif) {
		return findByProperty(AKTIF, aktif);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujurusanstatus instances");
		try {
			String queryString = "from Tblrujurusanstatus";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujurusanstatus merge(Tblrujurusanstatus detachedInstance) {
		log.debug("merging Tblrujurusanstatus instance");
		try {
			Tblrujurusanstatus result = (Tblrujurusanstatus) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujurusanstatus instance) {
		log.debug("attaching dirty Tblrujurusanstatus instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujurusanstatus instance) {
		log.debug("attaching clean Tblrujurusanstatus instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
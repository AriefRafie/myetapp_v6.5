package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujsuburusanstatusfail entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblrujsuburusanstatusfail
 * @author MyEclipse Persistence Tools
 */

public class TblrujsuburusanstatusfailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujsuburusanstatusfailDAO.class);
	// property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_SUBURUSANSTATUS = "idSuburusanstatus";
	public static final String URL = "url";
	public static final String AKTIF = "aktif";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_FAIL = "idFail";

	public void save(Tblrujsuburusanstatusfail transientInstance) {
		log.debug("saving Tblrujsuburusanstatusfail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujsuburusanstatusfail persistentInstance) {
		log.debug("deleting Tblrujsuburusanstatusfail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujsuburusanstatusfail findById(java.lang.Long id) {
		log.debug("getting Tblrujsuburusanstatusfail instance with id: " + id);
		try {
			Tblrujsuburusanstatusfail instance = (Tblrujsuburusanstatusfail) getSession()
					.get("ekptg.model.entities.Tblrujsuburusanstatusfail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujsuburusanstatusfail instance) {
		log.debug("finding Tblrujsuburusanstatusfail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujsuburusanstatusfail").add(
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
		log.debug("finding Tblrujsuburusanstatusfail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujsuburusanstatusfail as model where model."
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

	public List findByIdSuburusanstatus(Object idSuburusanstatus) {
		return findByProperty(ID_SUBURUSANSTATUS, idSuburusanstatus);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
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
	
	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}
	
	public List findAll() {
		log.debug("finding all Tblrujsuburusanstatusfail instances");
		try {
			String queryString = "from Tblrujsuburusanstatusfail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujsuburusanstatusfail merge(
			Tblrujsuburusanstatusfail detachedInstance) {
		log.debug("merging Tblrujsuburusanstatusfail instance");
		try {
			Tblrujsuburusanstatusfail result = (Tblrujsuburusanstatusfail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujsuburusanstatusfail instance) {
		log.debug("attaching dirty Tblrujsuburusanstatusfail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujsuburusanstatusfail instance) {
		log.debug("attaching clean Tblrujsuburusanstatusfail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
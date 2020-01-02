package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpjadualkedualesenapb entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpjadualkedualesenapb
 * @author MyEclipse Persistence Tools
 */

public class TblphpjadualkedualesenapbDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpjadualkedualesenapbDAO.class);
	// property constants
	public static final String ID_FAIL = "idFail";
	public static final String NO_SIRI_LESEN = "noSiriLesen";
	public static final String LOKASI = "lokasi";
	public static final String TEMPOH = "tempoh";
	public static final String ID_TEMPOH = "idTempoh";
	public static final String LUAS = "luas";
	public static final String ID_UNITLUAS = "idUnitluas";
	public static final String STATUS_LESEN = "statusLesen";
	public static final String ID_JENISSEWA = "idJenissewa";
	public static final String JENIS_LESEN = "jenisLesen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpjadualkedualesenapb transientInstance) {
		log.debug("saving Tblphpjadualkedualesenapb instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpjadualkedualesenapb persistentInstance) {
		log.debug("deleting Tblphpjadualkedualesenapb instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpjadualkedualesenapb findById(java.lang.Long id) {
		log.debug("getting Tblphpjadualkedualesenapb instance with id: " + id);
		try {
			Tblphpjadualkedualesenapb instance = (Tblphpjadualkedualesenapb) getSession()
					.get("ekptg.model.entities.Tblphpjadualkedualesenapb", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpjadualkedualesenapb instance) {
		log.debug("finding Tblphpjadualkedualesenapb instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpjadualkedualesenapb").add(
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
		log.debug("finding Tblphpjadualkedualesenapb instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpjadualkedualesenapb as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findByNoSiriLesen(Object noSiriLesen) {
		return findByProperty(NO_SIRI_LESEN, noSiriLesen);
	}

	public List findByLokasi(Object lokasi) {
		return findByProperty(LOKASI, lokasi);
	}

	public List findByTempoh(Object tempoh) {
		return findByProperty(TEMPOH, tempoh);
	}

	public List findByIdTempoh(Object idTempoh) {
		return findByProperty(ID_TEMPOH, idTempoh);
	}

	public List findByLuas(Object luas) {
		return findByProperty(LUAS, luas);
	}

	public List findByIdUnitluas(Object idUnitluas) {
		return findByProperty(ID_UNITLUAS, idUnitluas);
	}

	public List findByStatusLesen(Object statusLesen) {
		return findByProperty(STATUS_LESEN, statusLesen);
	}

	public List findByIdJenissewa(Object idJenissewa) {
		return findByProperty(ID_JENISSEWA, idJenissewa);
	}

	public List findByJenisLesen(Object jenisLesen) {
		return findByProperty(JENIS_LESEN, jenisLesen);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpjadualkedualesenapb instances");
		try {
			String queryString = "from Tblphpjadualkedualesenapb";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpjadualkedualesenapb merge(
			Tblphpjadualkedualesenapb detachedInstance) {
		log.debug("merging Tblphpjadualkedualesenapb instance");
		try {
			Tblphpjadualkedualesenapb result = (Tblphpjadualkedualesenapb) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpjadualkedualesenapb instance) {
		log.debug("attaching dirty Tblphpjadualkedualesenapb instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpjadualkedualesenapb instance) {
		log.debug("attaching clean Tblphpjadualkedualesenapb instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
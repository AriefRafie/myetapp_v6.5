package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpkoordinatpermohonan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpkoordinatpermohonan
 * @author MyEclipse Persistence Tools
 */

public class TblphpkoordinatpermohonanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpkoordinatpermohonanDAO.class);
	// property constants
	public static final String ID_STATUSKOORDINAT = "idStatuskoordinat";
	public static final String BILANGAN_TITIK = "bilanganTitik";
	public static final String DARJAH_U = "darjahU";
	public static final String DARJAH_T = "darjahT";
	public static final String MINIT_U = "minitU";
	public static final String MINIT_T = "minitT";
	public static final String SAAT_U = "saatU";
	public static final String SAAT_T = "saatT";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpkoordinatpermohonan transientInstance) {
		log.debug("saving Tblphpkoordinatpermohonan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpkoordinatpermohonan persistentInstance) {
		log.debug("deleting Tblphpkoordinatpermohonan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpkoordinatpermohonan findById(java.lang.Long id) {
		log.debug("getting Tblphpkoordinatpermohonan instance with id: " + id);
		try {
			Tblphpkoordinatpermohonan instance = (Tblphpkoordinatpermohonan) getSession()
					.get("ekptg.model.entities.Tblphpkoordinatpermohonan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpkoordinatpermohonan instance) {
		log.debug("finding Tblphpkoordinatpermohonan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpkoordinatpermohonan").add(
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
		log.debug("finding Tblphpkoordinatpermohonan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpkoordinatpermohonan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdStatuskoordinat(Object idStatuskoordinat) {
		return findByProperty(ID_STATUSKOORDINAT, idStatuskoordinat);
	}

	public List findByBilanganTitik(Object bilanganTitik) {
		return findByProperty(BILANGAN_TITIK, bilanganTitik);
	}

	public List findByDarjahU(Object darjahU) {
		return findByProperty(DARJAH_U, darjahU);
	}

	public List findByDarjahT(Object darjahT) {
		return findByProperty(DARJAH_T, darjahT);
	}

	public List findByMinitU(Object minitU) {
		return findByProperty(MINIT_U, minitU);
	}

	public List findByMinitT(Object minitT) {
		return findByProperty(MINIT_T, minitT);
	}

	public List findBySaatU(Object saatU) {
		return findByProperty(SAAT_U, saatU);
	}

	public List findBySaatT(Object saatT) {
		return findByProperty(SAAT_T, saatT);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpkoordinatpermohonan instances");
		try {
			String queryString = "from Tblphpkoordinatpermohonan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpkoordinatpermohonan merge(
			Tblphpkoordinatpermohonan detachedInstance) {
		log.debug("merging Tblphpkoordinatpermohonan instance");
		try {
			Tblphpkoordinatpermohonan result = (Tblphpkoordinatpermohonan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpkoordinatpermohonan instance) {
		log.debug("attaching dirty Tblphpkoordinatpermohonan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpkoordinatpermohonan instance) {
		log.debug("attaching clean Tblphpkoordinatpermohonan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
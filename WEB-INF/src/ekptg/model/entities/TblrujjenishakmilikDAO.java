package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujjenishakmilik entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujjenishakmilik
 * @author MyEclipse Persistence Tools
 */

public class TblrujjenishakmilikDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujjenishakmilikDAO.class);
	// property constants
	public static final String KOD_JENIS_HAKMILIK = "kodJenisHakmilik";
	public static final String KETERANGAN = "keterangan";
	public static final String STATUS_HAKMILIK = "statusHakmilik";
	public static final String SIMPANAN = "simpanan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujjenishakmilik transientInstance) {
		log.debug("saving Tblrujjenishakmilik instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujjenishakmilik persistentInstance) {
		log.debug("deleting Tblrujjenishakmilik instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujjenishakmilik findById(java.lang.Long id) {
		log.debug("getting Tblrujjenishakmilik instance with id: " + id);
		try {
			Tblrujjenishakmilik instance = (Tblrujjenishakmilik) getSession()
					.get("ekptg.model.entities.Tblrujjenishakmilik", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujjenishakmilik instance) {
		log.debug("finding Tblrujjenishakmilik instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujjenishakmilik").add(
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
		log.debug("finding Tblrujjenishakmilik instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujjenishakmilik as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJenisHakmilik(Object kodJenisHakmilik) {
		return findByProperty(KOD_JENIS_HAKMILIK, kodJenisHakmilik);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByStatusHakmilik(Object statusHakmilik) {
		return findByProperty(STATUS_HAKMILIK, statusHakmilik);
	}

	public List findBySimpanan(Object simpanan) {
		return findByProperty(SIMPANAN, simpanan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujjenishakmilik instances");
		try {
			String queryString = "from Tblrujjenishakmilik";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujjenishakmilik merge(Tblrujjenishakmilik detachedInstance) {
		log.debug("merging Tblrujjenishakmilik instance");
		try {
			Tblrujjenishakmilik result = (Tblrujjenishakmilik) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujjenishakmilik instance) {
		log.debug("attaching dirty Tblrujjenishakmilik instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujjenishakmilik instance) {
		log.debug("attaching clean Tblrujjenishakmilik instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujenishakmilik entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujenishakmilik
 * @author MyEclipse Persistence Tools
 */

public class TblrujenishakmilikDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujenishakmilikDAO.class);
	// property constants
	public static final String KOD_JENIS_HM = "kodJenisHm";
	public static final String KETERANGAN = "keterangan";
	public static final String STATUS_HM = "statusHm";
	public static final String OWNER_HM = "ownerHm";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujenishakmilik transientInstance) {
		log.debug("saving Tblrujenishakmilik instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujenishakmilik persistentInstance) {
		log.debug("deleting Tblrujenishakmilik instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujenishakmilik findById(java.lang.Long id) {
		log.debug("getting Tblrujenishakmilik instance with id: " + id);
		try {
			Tblrujenishakmilik instance = (Tblrujenishakmilik) getSession()
					.get("ekptg.model.entities.Tblrujenishakmilik", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujenishakmilik instance) {
		log.debug("finding Tblrujenishakmilik instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujenishakmilik").add(
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
		log.debug("finding Tblrujenishakmilik instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujenishakmilik as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJenisHm(Object kodJenisHm) {
		return findByProperty(KOD_JENIS_HM, kodJenisHm);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByStatusHm(Object statusHm) {
		return findByProperty(STATUS_HM, statusHm);
	}

	public List findByOwnerHm(Object ownerHm) {
		return findByProperty(OWNER_HM, ownerHm);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujenishakmilik instances");
		try {
			String queryString = "from Tblrujenishakmilik";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujenishakmilik merge(Tblrujenishakmilik detachedInstance) {
		log.debug("merging Tblrujenishakmilik instance");
		try {
			Tblrujenishakmilik result = (Tblrujenishakmilik) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujenishakmilik instance) {
		log.debug("attaching dirty Tblrujenishakmilik instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujenishakmilik instance) {
		log.debug("attaching clean Tblrujenishakmilik instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
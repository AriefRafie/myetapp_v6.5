package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tbljenisunitorganisasi entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tbljenisunitorganisasi
 * @author MyEclipse Persistence Tools
 */

public class TbljenisunitorganisasiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TbljenisunitorganisasiDAO.class);
	// property constants
	public static final String KOD_JENIS_UNIT_ORGANISASI = "kodJenisUnitOrganisasi";
	public static final String JENIS_UNIT_ORGANISASI = "jenisUnitOrganisasi";

	public void save(Tbljenisunitorganisasi transientInstance) {
		log.debug("saving Tbljenisunitorganisasi instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tbljenisunitorganisasi persistentInstance) {
		log.debug("deleting Tbljenisunitorganisasi instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tbljenisunitorganisasi findById(java.lang.Long id) {
		log.debug("getting Tbljenisunitorganisasi instance with id: " + id);
		try {
			Tbljenisunitorganisasi instance = (Tbljenisunitorganisasi) getSession()
					.get("ekptg.model.entities.Tbljenisunitorganisasi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tbljenisunitorganisasi instance) {
		log.debug("finding Tbljenisunitorganisasi instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tbljenisunitorganisasi").add(
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
		log.debug("finding Tbljenisunitorganisasi instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tbljenisunitorganisasi as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJenisUnitOrganisasi(Object kodJenisUnitOrganisasi) {
		return findByProperty(KOD_JENIS_UNIT_ORGANISASI, kodJenisUnitOrganisasi);
	}

	public List findByJenisUnitOrganisasi(Object jenisUnitOrganisasi) {
		return findByProperty(JENIS_UNIT_ORGANISASI, jenisUnitOrganisasi);
	}

	public List findAll() {
		log.debug("finding all Tbljenisunitorganisasi instances");
		try {
			String queryString = "from Tbljenisunitorganisasi";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tbljenisunitorganisasi merge(Tbljenisunitorganisasi detachedInstance) {
		log.debug("merging Tbljenisunitorganisasi instance");
		try {
			Tbljenisunitorganisasi result = (Tbljenisunitorganisasi) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tbljenisunitorganisasi instance) {
		log.debug("attaching dirty Tbljenisunitorganisasi instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tbljenisunitorganisasi instance) {
		log.debug("attaching clean Tbljenisunitorganisasi instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
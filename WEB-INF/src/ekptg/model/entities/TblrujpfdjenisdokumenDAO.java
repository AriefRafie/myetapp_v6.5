package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujpfdjenisdokumen entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujpfdjenisdokumen
 * @author MyEclipse Persistence Tools
 */

public class TblrujpfdjenisdokumenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujpfdjenisdokumenDAO.class);
	// property constants
	public static final String KOD_JENIS_DOKUMEN = "kodJenisDokumen";
	public static final String KOD_SEKSYEN = "kodSeksyen";
	public static final String NAMA_DOKUMEN = "namaDokumen";
	public static final String KOD_PROGRAM = "kodProgram";
	public static final String KOD_LAPORAN = "kodLaporan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujpfdjenisdokumen transientInstance) {
		log.debug("saving Tblrujpfdjenisdokumen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujpfdjenisdokumen persistentInstance) {
		log.debug("deleting Tblrujpfdjenisdokumen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujpfdjenisdokumen findById(java.lang.Long id) {
		log.debug("getting Tblrujpfdjenisdokumen instance with id: " + id);
		try {
			Tblrujpfdjenisdokumen instance = (Tblrujpfdjenisdokumen) getSession()
					.get("ekptg.model.entities.Tblrujpfdjenisdokumen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujpfdjenisdokumen instance) {
		log.debug("finding Tblrujpfdjenisdokumen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujpfdjenisdokumen").add(
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
		log.debug("finding Tblrujpfdjenisdokumen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujpfdjenisdokumen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodJenisDokumen(Object kodJenisDokumen) {
		return findByProperty(KOD_JENIS_DOKUMEN, kodJenisDokumen);
	}

	public List findByKodSeksyen(Object kodSeksyen) {
		return findByProperty(KOD_SEKSYEN, kodSeksyen);
	}

	public List findByNamaDokumen(Object namaDokumen) {
		return findByProperty(NAMA_DOKUMEN, namaDokumen);
	}

	public List findByKodProgram(Object kodProgram) {
		return findByProperty(KOD_PROGRAM, kodProgram);
	}

	public List findByKodLaporan(Object kodLaporan) {
		return findByProperty(KOD_LAPORAN, kodLaporan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujpfdjenisdokumen instances");
		try {
			String queryString = "from Tblrujpfdjenisdokumen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujpfdjenisdokumen merge(Tblrujpfdjenisdokumen detachedInstance) {
		log.debug("merging Tblrujpfdjenisdokumen instance");
		try {
			Tblrujpfdjenisdokumen result = (Tblrujpfdjenisdokumen) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujpfdjenisdokumen instance) {
		log.debug("attaching dirty Tblrujpfdjenisdokumen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujpfdjenisdokumen instance) {
		log.debug("attaching clean Tblrujpfdjenisdokumen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
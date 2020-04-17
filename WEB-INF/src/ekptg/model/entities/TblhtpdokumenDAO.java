package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpdokumen entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpdokumen
 * @author MyEclipse Persistence Tools
 */

public class TblhtpdokumenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpdokumenDAO.class);
	// property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String JENIS_DOKUMEN = "jenisDokumen";
	public static final String PIHAK = "pihak";
	public static final String ULASAN = "ulasan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpdokumen transientInstance) {
		log.debug("saving Tblhtpdokumen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpdokumen persistentInstance) {
		log.debug("deleting Tblhtpdokumen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpdokumen findById(java.lang.Long id) {
		log.debug("getting Tblhtpdokumen instance with id: " + id);
		try {
			Tblhtpdokumen instance = (Tblhtpdokumen) getSession().get(
					"ekptg.model.entities.Tblhtpdokumen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpdokumen instance) {
		log.debug("finding Tblhtpdokumen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpdokumen").add(
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
		log.debug("finding Tblhtpdokumen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpdokumen as model where model."
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

	public List findByJenisDokumen(Object jenisDokumen) {
		return findByProperty(JENIS_DOKUMEN, jenisDokumen);
	}

	public List findByPihak(Object pihak) {
		return findByProperty(PIHAK, pihak);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpdokumen instances");
		try {
			String queryString = "from Tblhtpdokumen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpdokumen merge(Tblhtpdokumen detachedInstance) {
		log.debug("merging Tblhtpdokumen instance");
		try {
			Tblhtpdokumen result = (Tblhtpdokumen) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpdokumen instance) {
		log.debug("attaching dirty Tblhtpdokumen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpdokumen instance) {
		log.debug("attaching clean Tblhtpdokumen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
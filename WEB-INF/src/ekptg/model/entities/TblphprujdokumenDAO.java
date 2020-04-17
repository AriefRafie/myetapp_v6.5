package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphprujdokumen entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphprujdokumen
 * @author MyEclipse Persistence Tools
 */

public class TblphprujdokumenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblphprujdokumenDAO.class);
	// property constants
	public static final String KOD_DOKUMEN = "kodDokumen";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphprujdokumen transientInstance) {
		log.debug("saving Tblphprujdokumen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphprujdokumen persistentInstance) {
		log.debug("deleting Tblphprujdokumen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphprujdokumen findById(java.lang.Long id) {
		log.debug("getting Tblphprujdokumen instance with id: " + id);
		try {
			Tblphprujdokumen instance = (Tblphprujdokumen) getSession().get(
					"ekptg.model.entities.Tblphprujdokumen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphprujdokumen instance) {
		log.debug("finding Tblphprujdokumen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphprujdokumen").add(
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
		log.debug("finding Tblphprujdokumen instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblphprujdokumen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodDokumen(Object kodDokumen) {
		return findByProperty(KOD_DOKUMEN, kodDokumen);
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

	public List findAll() {
		log.debug("finding all Tblphprujdokumen instances");
		try {
			String queryString = "from Tblphprujdokumen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphprujdokumen merge(Tblphprujdokumen detachedInstance) {
		log.debug("merging Tblphprujdokumen instance");
		try {
			Tblphprujdokumen result = (Tblphprujdokumen) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphprujdokumen instance) {
		log.debug("attaching dirty Tblphprujdokumen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphprujdokumen instance) {
		log.debug("attaching clean Tblphprujdokumen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujpdtdokumenpekeliling entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblrujpdtdokumenpekeliling
 * @author MyEclipse Persistence Tools
 */

public class TblrujpdtdokumenpekelilingDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujpdtdokumenpekelilingDAO.class);
	// property constants
	public static final String JENIS_DOKUMEN_PEKELILING = "jenisDokumenPekeliling";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujpdtdokumenpekeliling transientInstance) {
		log.debug("saving Tblrujpdtdokumenpekeliling instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujpdtdokumenpekeliling persistentInstance) {
		log.debug("deleting Tblrujpdtdokumenpekeliling instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujpdtdokumenpekeliling findById(java.lang.Long id) {
		log.debug("getting Tblrujpdtdokumenpekeliling instance with id: " + id);
		try {
			Tblrujpdtdokumenpekeliling instance = (Tblrujpdtdokumenpekeliling) getSession()
					.get("ekptg.model.entities.Tblrujpdtdokumenpekeliling", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujpdtdokumenpekeliling instance) {
		log.debug("finding Tblrujpdtdokumenpekeliling instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujpdtdokumenpekeliling").add(
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
		log.debug("finding Tblrujpdtdokumenpekeliling instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujpdtdokumenpekeliling as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJenisDokumenPekeliling(Object jenisDokumenPekeliling) {
		return findByProperty(JENIS_DOKUMEN_PEKELILING, jenisDokumenPekeliling);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujpdtdokumenpekeliling instances");
		try {
			String queryString = "from Tblrujpdtdokumenpekeliling";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujpdtdokumenpekeliling merge(
			Tblrujpdtdokumenpekeliling detachedInstance) {
		log.debug("merging Tblrujpdtdokumenpekeliling instance");
		try {
			Tblrujpdtdokumenpekeliling result = (Tblrujpdtdokumenpekeliling) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujpdtdokumenpekeliling instance) {
		log.debug("attaching dirty Tblrujpdtdokumenpekeliling instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujpdtdokumenpekeliling instance) {
		log.debug("attaching clean Tblrujpdtdokumenpekeliling instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
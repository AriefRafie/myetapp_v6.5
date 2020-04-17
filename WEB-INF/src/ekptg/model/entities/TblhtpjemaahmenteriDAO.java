package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpjemaahmenteri entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpjemaahmenteri
 * @author MyEclipse Persistence Tools
 */

public class TblhtpjemaahmenteriDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpjemaahmenteriDAO.class);
	// property constants
	public static final String NO_RUJUKAN_KPTSN = "noRujukanKptsn";
	public static final String ULASAN = "ulasan";
	public static final String STATUS_KEPUTUSAN = "statusKeputusan";
	public static final String TINDAKAN_LANJUT = "tindakanLanjut";
	public static final String NO_MEMORANDUM = "noMemorandum";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpjemaahmenteri transientInstance) {
		log.debug("saving Tblhtpjemaahmenteri instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpjemaahmenteri persistentInstance) {
		log.debug("deleting Tblhtpjemaahmenteri instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpjemaahmenteri findById(java.lang.Long id) {
		log.debug("getting Tblhtpjemaahmenteri instance with id: " + id);
		try {
			Tblhtpjemaahmenteri instance = (Tblhtpjemaahmenteri) getSession()
					.get("ekptg.model.entities.Tblhtpjemaahmenteri", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpjemaahmenteri instance) {
		log.debug("finding Tblhtpjemaahmenteri instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpjemaahmenteri").add(
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
		log.debug("finding Tblhtpjemaahmenteri instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpjemaahmenteri as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoRujukanKptsn(Object noRujukanKptsn) {
		return findByProperty(NO_RUJUKAN_KPTSN, noRujukanKptsn);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByStatusKeputusan(Object statusKeputusan) {
		return findByProperty(STATUS_KEPUTUSAN, statusKeputusan);
	}

	public List findByTindakanLanjut(Object tindakanLanjut) {
		return findByProperty(TINDAKAN_LANJUT, tindakanLanjut);
	}

	public List findByNoMemorandum(Object noMemorandum) {
		return findByProperty(NO_MEMORANDUM, noMemorandum);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpjemaahmenteri instances");
		try {
			String queryString = "from Tblhtpjemaahmenteri";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpjemaahmenteri merge(Tblhtpjemaahmenteri detachedInstance) {
		log.debug("merging Tblhtpjemaahmenteri instance");
		try {
			Tblhtpjemaahmenteri result = (Tblhtpjemaahmenteri) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpjemaahmenteri instance) {
		log.debug("attaching dirty Tblhtpjemaahmenteri instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpjemaahmenteri instance) {
		log.debug("attaching clean Tblhtpjemaahmenteri instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
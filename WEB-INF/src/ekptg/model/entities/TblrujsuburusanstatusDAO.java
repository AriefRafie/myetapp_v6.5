package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujsuburusanstatus entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujsuburusanstatus
 * @author MyEclipse Persistence Tools
 */

public class TblrujsuburusanstatusDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujsuburusanstatusDAO.class);
	// property constants
	public static final String ID_SUBURUSAN = "idSuburusan";
	public static final String ID_STATUS = "idStatus";
	public static final String AKTIF = "aktif";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujsuburusanstatus transientInstance) {
		log.debug("saving Tblrujsuburusanstatus instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujsuburusanstatus persistentInstance) {
		log.debug("deleting Tblrujsuburusanstatus instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujsuburusanstatus findById(java.lang.Long id) {
		log.debug("getting Tblrujsuburusanstatus instance with id: " + id);
		try {
			Tblrujsuburusanstatus instance = (Tblrujsuburusanstatus) getSession()
					.get("ekptg.model.entities.Tblrujsuburusanstatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujsuburusanstatus instance) {
		log.debug("finding Tblrujsuburusanstatus instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujsuburusanstatus").add(
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
		log.debug("finding Tblrujsuburusanstatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujsuburusanstatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdSuburusan(Object idSuburusan) {
		return findByProperty(ID_SUBURUSAN, idSuburusan);
	}

	public List findByIdStatus(Object idStatus) {
		return findByProperty(ID_STATUS, idStatus);
	}

	public List findByAktif(Object aktif) {
		return findByProperty(AKTIF, aktif);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujsuburusanstatus instances");
		try {
			String queryString = "from Tblrujsuburusanstatus";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujsuburusanstatus merge(Tblrujsuburusanstatus detachedInstance) {
		log.debug("merging Tblrujsuburusanstatus instance");
		try {
			Tblrujsuburusanstatus result = (Tblrujsuburusanstatus) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujsuburusanstatus instance) {
		log.debug("attaching dirty Tblrujsuburusanstatus instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujsuburusanstatus instance) {
		log.debug("attaching clean Tblrujsuburusanstatus instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
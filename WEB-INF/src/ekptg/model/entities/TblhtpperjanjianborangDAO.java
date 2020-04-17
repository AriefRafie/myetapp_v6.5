package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpperjanjianborang entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpperjanjianborang
 * @author MyEclipse Persistence Tools
 */

public class TblhtpperjanjianborangDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpperjanjianborangDAO.class);
	// property constants
	public static final String ID_PERJANJIAN = "idPerjanjian";
	public static final String JENIS_BORANG = "jenisBorang";
	public static final String NO_PESERAHAN_SPTB = "noPeserahanSptb";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpperjanjianborang transientInstance) {
		log.debug("saving Tblhtpperjanjianborang instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpperjanjianborang persistentInstance) {
		log.debug("deleting Tblhtpperjanjianborang instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpperjanjianborang findById(java.lang.Long id) {
		log.debug("getting Tblhtpperjanjianborang instance with id: " + id);
		try {
			Tblhtpperjanjianborang instance = (Tblhtpperjanjianborang) getSession()
					.get("ekptg.model.entities.Tblhtpperjanjianborang", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpperjanjianborang instance) {
		log.debug("finding Tblhtpperjanjianborang instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpperjanjianborang").add(
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
		log.debug("finding Tblhtpperjanjianborang instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpperjanjianborang as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPerjanjian(Object idPerjanjian) {
		return findByProperty(ID_PERJANJIAN, idPerjanjian);
	}

	public List findByJenisBorang(Object jenisBorang) {
		return findByProperty(JENIS_BORANG, jenisBorang);
	}

	public List findByNoPeserahanSptb(Object noPeserahanSptb) {
		return findByProperty(NO_PESERAHAN_SPTB, noPeserahanSptb);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpperjanjianborang instances");
		try {
			String queryString = "from Tblhtpperjanjianborang";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpperjanjianborang merge(Tblhtpperjanjianborang detachedInstance) {
		log.debug("merging Tblhtpperjanjianborang instance");
		try {
			Tblhtpperjanjianborang result = (Tblhtpperjanjianborang) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpperjanjianborang instance) {
		log.debug("attaching dirty Tblhtpperjanjianborang instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpperjanjianborang instance) {
		log.debug("attaching clean Tblhtpperjanjianborang instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
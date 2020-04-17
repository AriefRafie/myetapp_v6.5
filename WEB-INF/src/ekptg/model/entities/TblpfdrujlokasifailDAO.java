package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfdrujlokasifail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpfdrujlokasifail
 * @author MyEclipse Persistence Tools
 */

public class TblpfdrujlokasifailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpfdrujlokasifailDAO.class);
	// property constants
	public static final String LOKASI_FAIL = "lokasiFail";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpfdrujlokasifail transientInstance) {
		log.debug("saving Tblpfdrujlokasifail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfdrujlokasifail persistentInstance) {
		log.debug("deleting Tblpfdrujlokasifail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfdrujlokasifail findById(java.lang.Long id) {
		log.debug("getting Tblpfdrujlokasifail instance with id: " + id);
		try {
			Tblpfdrujlokasifail instance = (Tblpfdrujlokasifail) getSession()
					.get("ekptg.model.entities.Tblpfdrujlokasifail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfdrujlokasifail instance) {
		log.debug("finding Tblpfdrujlokasifail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfdrujlokasifail").add(
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
		log.debug("finding Tblpfdrujlokasifail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpfdrujlokasifail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLokasiFail(Object lokasiFail) {
		return findByProperty(LOKASI_FAIL, lokasiFail);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpfdrujlokasifail instances");
		try {
			String queryString = "from Tblpfdrujlokasifail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfdrujlokasifail merge(Tblpfdrujlokasifail detachedInstance) {
		log.debug("merging Tblpfdrujlokasifail instance");
		try {
			Tblpfdrujlokasifail result = (Tblpfdrujlokasifail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfdrujlokasifail instance) {
		log.debug("attaching dirty Tblpfdrujlokasifail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfdrujlokasifail instance) {
		log.debug("attaching clean Tblpfdrujlokasifail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
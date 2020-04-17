package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfdminitarahan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpfdminitarahan
 * @author MyEclipse Persistence Tools
 */

public class TblpfdminitarahanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpfdminitarahanDAO.class);
	// property constants
	public static final String MINIT_ARAHAN = "minitArahan";
	public static final String ID_DOKUMEN = "idDokumen";
        public static final String STATUS_TINDAKAN = "statusTindakan";

	public void save(Tblpfdminitarahan transientInstance) {
		log.debug("saving Tblpfdminitarahan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfdminitarahan persistentInstance) {
		log.debug("deleting Tblpfdminitarahan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfdminitarahan findById(java.lang.Long id) {
		log.debug("getting Tblpfdminitarahan instance with id: " + id);
		try {
			Tblpfdminitarahan instance = (Tblpfdminitarahan) getSession().get(
					"ekptg.model.entities.Tblpfdminitarahan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfdminitarahan instance) {
		log.debug("finding Tblpfdminitarahan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfdminitarahan").add(
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
		log.debug("finding Tblpfdminitarahan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpfdminitarahan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMinitArahan(Object minitArahan) {
		return findByProperty(MINIT_ARAHAN, minitArahan);
	}

	public List findByIdDokumen(Object idDokumen) {
		return findByProperty(ID_DOKUMEN, idDokumen);
	}
        
        public List findByStatusTindakan(Object statusTindakan) {
                return findByProperty(STATUS_TINDAKAN, statusTindakan);
        }
	public List findAll() {
		log.debug("finding all Tblpfdminitarahan instances");
		try {
			String queryString = "from Tblpfdminitarahan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfdminitarahan merge(Tblpfdminitarahan detachedInstance) {
		log.debug("merging Tblpfdminitarahan instance");
		try {
			Tblpfdminitarahan result = (Tblpfdminitarahan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfdminitarahan instance) {
		log.debug("attaching dirty Tblpfdminitarahan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfdminitarahan instance) {
		log.debug("attaching clean Tblpfdminitarahan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
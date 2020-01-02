package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpmoa entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpmoa
 * @author MyEclipse Persistence Tools
 */

public class TblhtpmoaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpmoaDAO.class);
	// property constants
	public static final String NO_RUJUKAN_MOA = "noRujukanMoa";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpmoa transientInstance) {
		log.debug("saving Tblhtpmoa instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpmoa persistentInstance) {
		log.debug("deleting Tblhtpmoa instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpmoa findById(java.lang.Long id) {
		log.debug("getting Tblhtpmoa instance with id: " + id);
		try {
			Tblhtpmoa instance = (Tblhtpmoa) getSession().get(
					"ekptg.model.entities.Tblhtpmoa", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpmoa instance) {
		log.debug("finding Tblhtpmoa instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpmoa").add(
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
		log.debug("finding Tblhtpmoa instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblhtpmoa as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoRujukanMoa(Object noRujukanMoa) {
		return findByProperty(NO_RUJUKAN_MOA, noRujukanMoa);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpmoa instances");
		try {
			String queryString = "from Tblhtpmoa";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpmoa merge(Tblhtpmoa detachedInstance) {
		log.debug("merging Tblhtpmoa instance");
		try {
			Tblhtpmoa result = (Tblhtpmoa) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpmoa instance) {
		log.debug("attaching dirty Tblhtpmoa instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpmoa instance) {
		log.debug("attaching clean Tblhtpmoa instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
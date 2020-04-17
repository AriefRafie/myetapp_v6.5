package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkkolateralmst entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkkolateralmst
 * @author MyEclipse Persistence Tools
 */

public class TblppkkolateralmstDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkkolateralmstDAO.class);
	// property constants
	public static final String SEBAB_PERTELINGKAHAN = "sebabPertelingkahan";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkkolateralmst transientInstance) {
		log.debug("saving Tblppkkolateralmst instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkkolateralmst persistentInstance) {
		log.debug("deleting Tblppkkolateralmst instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkkolateralmst findById(java.lang.Long id) {
		log.debug("getting Tblppkkolateralmst instance with id: " + id);
		try {
			Tblppkkolateralmst instance = (Tblppkkolateralmst) getSession()
					.get("ekptg.model.entities.Tblppkkolateralmst", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkkolateralmst instance) {
		log.debug("finding Tblppkkolateralmst instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkkolateralmst").add(
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
		log.debug("finding Tblppkkolateralmst instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkkolateralmst as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySebabPertelingkahan(Object sebabPertelingkahan) {
		return findByProperty(SEBAB_PERTELINGKAHAN, sebabPertelingkahan);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkkolateralmst instances");
		try {
			String queryString = "from Tblppkkolateralmst";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkkolateralmst merge(Tblppkkolateralmst detachedInstance) {
		log.debug("merging Tblppkkolateralmst instance");
		try {
			Tblppkkolateralmst result = (Tblppkkolateralmst) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkkolateralmst instance) {
		log.debug("attaching dirty Tblppkkolateralmst instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkkolateralmst instance) {
		log.debug("attaching clean Tblppkkolateralmst instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
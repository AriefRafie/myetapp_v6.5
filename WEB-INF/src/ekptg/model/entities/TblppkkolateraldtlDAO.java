package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkkolateraldtl entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkkolateraldtl
 * @author MyEclipse Persistence Tools
 */

public class TblppkkolateraldtlDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkkolateraldtlDAO.class);
	// property constants
	public static final String JENIS_OB = "jenisOb";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkkolateraldtl transientInstance) {
		log.debug("saving Tblppkkolateraldtl instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkkolateraldtl persistentInstance) {
		log.debug("deleting Tblppkkolateraldtl instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkkolateraldtl findById(java.lang.Long id) {
		log.debug("getting Tblppkkolateraldtl instance with id: " + id);
		try {
			Tblppkkolateraldtl instance = (Tblppkkolateraldtl) getSession()
					.get("ekptg.model.entities.Tblppkkolateraldtl", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkkolateraldtl instance) {
		log.debug("finding Tblppkkolateraldtl instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkkolateraldtl").add(
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
		log.debug("finding Tblppkkolateraldtl instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkkolateraldtl as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJenisOb(Object jenisOb) {
		return findByProperty(JENIS_OB, jenisOb);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkkolateraldtl instances");
		try {
			String queryString = "from Tblppkkolateraldtl";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkkolateraldtl merge(Tblppkkolateraldtl detachedInstance) {
		log.debug("merging Tblppkkolateraldtl instance");
		try {
			Tblppkkolateraldtl result = (Tblppkkolateraldtl) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkkolateraldtl instance) {
		log.debug("attaching dirty Tblppkkolateraldtl instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkkolateraldtl instance) {
		log.debug("attaching clean Tblppkkolateraldtl instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
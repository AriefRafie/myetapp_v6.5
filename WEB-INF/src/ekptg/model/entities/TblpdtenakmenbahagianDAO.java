package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtenakmenbahagian entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtenakmenbahagian
 * @author MyEclipse Persistence Tools
 */

public class TblpdtenakmenbahagianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtenakmenbahagianDAO.class);
	// property constants
	public static final String NAMA_BAHAGIAN = "namaBahagian";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtenakmenbahagian transientInstance) {
		log.debug("saving Tblpdtenakmenbahagian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtenakmenbahagian persistentInstance) {
		log.debug("deleting Tblpdtenakmenbahagian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenbahagian findById(java.lang.Long id) {
		log.debug("getting Tblpdtenakmenbahagian instance with id: " + id);
		try {
			Tblpdtenakmenbahagian instance = (Tblpdtenakmenbahagian) getSession()
					.get("ekptg.model.entities.Tblpdtenakmenbahagian", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtenakmenbahagian instance) {
		log.debug("finding Tblpdtenakmenbahagian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtenakmenbahagian").add(
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
		log.debug("finding Tblpdtenakmenbahagian instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtenakmenbahagian as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaBahagian(Object namaBahagian) {
		return findByProperty(NAMA_BAHAGIAN, namaBahagian);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtenakmenbahagian instances");
		try {
			String queryString = "from Tblpdtenakmenbahagian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenbahagian merge(Tblpdtenakmenbahagian detachedInstance) {
		log.debug("merging Tblpdtenakmenbahagian instance");
		try {
			Tblpdtenakmenbahagian result = (Tblpdtenakmenbahagian) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtenakmenbahagian instance) {
		log.debug("attaching dirty Tblpdtenakmenbahagian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtenakmenbahagian instance) {
		log.debug("attaching clean Tblpdtenakmenbahagian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtenakmenpenggal entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtenakmenpenggal
 * @author MyEclipse Persistence Tools
 */

public class TblpdtenakmenpenggalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtenakmenpenggalDAO.class);
	// property constants
	public static final String NAMA_PENGGAL = "namaPenggal";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtenakmenpenggal transientInstance) {
		log.debug("saving Tblpdtenakmenpenggal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtenakmenpenggal persistentInstance) {
		log.debug("deleting Tblpdtenakmenpenggal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpenggal findById(java.lang.Long id) {
		log.debug("getting Tblpdtenakmenpenggal instance with id: " + id);
		try {
			Tblpdtenakmenpenggal instance = (Tblpdtenakmenpenggal) getSession()
					.get("ekptg.model.entities.Tblpdtenakmenpenggal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtenakmenpenggal instance) {
		log.debug("finding Tblpdtenakmenpenggal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtenakmenpenggal").add(
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
		log.debug("finding Tblpdtenakmenpenggal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtenakmenpenggal as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaPenggal(Object namaPenggal) {
		return findByProperty(NAMA_PENGGAL, namaPenggal);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtenakmenpenggal instances");
		try {
			String queryString = "from Tblpdtenakmenpenggal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtenakmenpenggal merge(Tblpdtenakmenpenggal detachedInstance) {
		log.debug("merging Tblpdtenakmenpenggal instance");
		try {
			Tblpdtenakmenpenggal result = (Tblpdtenakmenpenggal) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtenakmenpenggal instance) {
		log.debug("attaching dirty Tblpdtenakmenpenggal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtenakmenpenggal instance) {
		log.debug("attaching clean Tblpdtenakmenpenggal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
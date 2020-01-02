package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtaktapenggal entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtaktapenggal
 * @author MyEclipse Persistence Tools
 */

public class TblpdtaktapenggalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtaktapenggalDAO.class);
	// property constants
	public static final String NAMA_PENGGAL = "namaPenggal";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtaktapenggal transientInstance) {
		log.debug("saving Tblpdtaktapenggal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtaktapenggal persistentInstance) {
		log.debug("deleting Tblpdtaktapenggal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtaktapenggal findById(java.lang.Long id) {
		log.debug("getting Tblpdtaktapenggal instance with id: " + id);
		try {
			Tblpdtaktapenggal instance = (Tblpdtaktapenggal) getSession().get(
					"ekptg.model.entities.Tblpdtaktapenggal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtaktapenggal instance) {
		log.debug("finding Tblpdtaktapenggal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtaktapenggal").add(
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
		log.debug("finding Tblpdtaktapenggal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtaktapenggal as model where model."
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
		log.debug("finding all Tblpdtaktapenggal instances");
		try {
			String queryString = "from Tblpdtaktapenggal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtaktapenggal merge(Tblpdtaktapenggal detachedInstance) {
		log.debug("merging Tblpdtaktapenggal instance");
		try {
			Tblpdtaktapenggal result = (Tblpdtaktapenggal) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtaktapenggal instance) {
		log.debug("attaching dirty Tblpdtaktapenggal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtaktapenggal instance) {
		log.debug("attaching clean Tblpdtaktapenggal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
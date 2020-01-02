package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppknotabicara entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppknotabicara
 * @author MyEclipse Persistence Tools
 */

public class TblppknotabicaraDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppknotabicaraDAO.class);
	// property constants
	public static final String NO_JILID = "noJilid";
	public static final String DIR = "dir";
	public static final String BIL = "bil";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppknotabicara transientInstance) {
		log.debug("saving Tblppknotabicara instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppknotabicara persistentInstance) {
		log.debug("deleting Tblppknotabicara instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppknotabicara findById(java.lang.Long id) {
		log.debug("getting Tblppknotabicara instance with id: " + id);
		try {
			Tblppknotabicara instance = (Tblppknotabicara) getSession().get(
					"ekptg.model.entities.Tblppknotabicara", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppknotabicara instance) {
		log.debug("finding Tblppknotabicara instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppknotabicara").add(
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
		log.debug("finding Tblppknotabicara instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppknotabicara as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoJilid(Object noJilid) {
		return findByProperty(NO_JILID, noJilid);
	}

	public List findByDir(Object dir) {
		return findByProperty(DIR, dir);
	}

	public List findByBil(Object bil) {
		return findByProperty(BIL, bil);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppknotabicara instances");
		try {
			String queryString = "from Tblppknotabicara";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppknotabicara merge(Tblppknotabicara detachedInstance) {
		log.debug("merging Tblppknotabicara instance");
		try {
			Tblppknotabicara result = (Tblppknotabicara) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppknotabicara instance) {
		log.debug("attaching dirty Tblppknotabicara instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppknotabicara instance) {
		log.debug("attaching clean Tblppknotabicara instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
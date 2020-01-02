package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkhubunganob entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkhubunganob
 * @author MyEclipse Persistence Tools
 */

public class TblppkhubunganobDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkhubunganobDAO.class);
	// property constants
	public static final String ID_PARENTOB = "idParentob";
	public static final String ID_SAUDARA = "idSaudara";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkhubunganob transientInstance) {
		log.debug("saving Tblppkhubunganob instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkhubunganob persistentInstance) {
		log.debug("deleting Tblppkhubunganob instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkhubunganob findById(java.lang.Long id) {
		log.debug("getting Tblppkhubunganob instance with id: " + id);
		try {
			Tblppkhubunganob instance = (Tblppkhubunganob) getSession().get(
					"ekptg.model.entities.Tblppkhubunganob", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkhubunganob instance) {
		log.debug("finding Tblppkhubunganob instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkhubunganob").add(
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
		log.debug("finding Tblppkhubunganob instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkhubunganob as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdParentob(Object idParentob) {
		return findByProperty(ID_PARENTOB, idParentob);
	}

	public List findByIdSaudara(Object idSaudara) {
		return findByProperty(ID_SAUDARA, idSaudara);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkhubunganob instances");
		try {
			String queryString = "from Tblppkhubunganob";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkhubunganob merge(Tblppkhubunganob detachedInstance) {
		log.debug("merging Tblppkhubunganob instance");
		try {
			Tblppkhubunganob result = (Tblppkhubunganob) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkhubunganob instance) {
		log.debug("attaching dirty Tblppkhubunganob instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkhubunganob instance) {
		log.debug("attaching clean Tblppkhubunganob instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
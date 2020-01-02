package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkborangphubob entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkborangphubob
 * @author MyEclipse Persistence Tools
 */

public class TblppkborangphubobDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkborangphubobDAO.class);
	// property constants
	public static final String ID_PARENTBORANGPOB = "idParentborangpob";
	public static final String ID_SAUDARA = "idSaudara";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkborangphubob transientInstance) {
		log.debug("saving Tblppkborangphubob instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkborangphubob persistentInstance) {
		log.debug("deleting Tblppkborangphubob instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkborangphubob findById(java.lang.Long id) {
		log.debug("getting Tblppkborangphubob instance with id: " + id);
		try {
			Tblppkborangphubob instance = (Tblppkborangphubob) getSession()
					.get("ekptg.model.entities.Tblppkborangphubob", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkborangphubob instance) {
		log.debug("finding Tblppkborangphubob instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkborangphubob").add(
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
		log.debug("finding Tblppkborangphubob instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkborangphubob as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdParentborangpob(Object idParentborangpob) {
		return findByProperty(ID_PARENTBORANGPOB, idParentborangpob);
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
		log.debug("finding all Tblppkborangphubob instances");
		try {
			String queryString = "from Tblppkborangphubob";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkborangphubob merge(Tblppkborangphubob detachedInstance) {
		log.debug("merging Tblppkborangphubob instance");
		try {
			Tblppkborangphubob result = (Tblppkborangphubob) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkborangphubob instance) {
		log.debug("attaching dirty Tblppkborangphubob instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkborangphubob instance) {
		log.debug("attaching clean Tblppkborangphubob instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
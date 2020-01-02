package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtagendamesyuarat entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtagendamesyuarat
 * @author MyEclipse Persistence Tools
 */

public class TblpdtagendamesyuaratDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtagendamesyuaratDAO.class);
	// property constants
	public static final String AGENDA_MESYUARAT = "agendaMesyuarat";

	public void save(Tblpdtagendamesyuarat transientInstance) {
		log.debug("saving Tblpdtagendamesyuarat instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtagendamesyuarat persistentInstance) {
		log.debug("deleting Tblpdtagendamesyuarat instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtagendamesyuarat findById(java.math.BigDecimal id) {
		log.debug("getting Tblpdtagendamesyuarat instance with id: " + id);
		try {
			Tblpdtagendamesyuarat instance = (Tblpdtagendamesyuarat) getSession()
					.get("ekptg.model.entities.Tblpdtagendamesyuarat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtagendamesyuarat instance) {
		log.debug("finding Tblpdtagendamesyuarat instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtagendamesyuarat").add(
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
		log.debug("finding Tblpdtagendamesyuarat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtagendamesyuarat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAgendaMesyuarat(Object agendaMesyuarat) {
		return findByProperty(AGENDA_MESYUARAT, agendaMesyuarat);
	}

	public List findAll() {
		log.debug("finding all Tblpdtagendamesyuarat instances");
		try {
			String queryString = "from Tblpdtagendamesyuarat";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtagendamesyuarat merge(Tblpdtagendamesyuarat detachedInstance) {
		log.debug("merging Tblpdtagendamesyuarat instance");
		try {
			Tblpdtagendamesyuarat result = (Tblpdtagendamesyuarat) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtagendamesyuarat instance) {
		log.debug("attaching dirty Tblpdtagendamesyuarat instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtagendamesyuarat instance) {
		log.debug("attaching clean Tblpdtagendamesyuarat instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
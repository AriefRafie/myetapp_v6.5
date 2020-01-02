package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpseqpfdfail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpseqpfdfail
 * @author MyEclipse Persistence Tools
 */

public class TblphpseqpfdfailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblphpseqpfdfailDAO.class);
	// property constants
	public static final String TAHUN = "tahun";
	public static final String NO_TURUTAN = "noTurutan";

	public void save(Tblphpseqpfdfail transientInstance) {
		log.debug("saving Tblphpseqpfdfail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpseqpfdfail persistentInstance) {
		log.debug("deleting Tblphpseqpfdfail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpseqpfdfail findById(ekptg.model.entities.TblphpseqpfdfailId id) {
		log.debug("getting Tblphpseqpfdfail instance with id: " + id);
		try {
			Tblphpseqpfdfail instance = (Tblphpseqpfdfail) getSession().get(
					"ekptg.model.entities.Tblphpseqpfdfail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpseqpfdfail instance) {
		log.debug("finding Tblphpseqpfdfail instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpseqpfdfail").add(
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
		log.debug("finding Tblphpseqpfdfail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpseqpfdfail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTahun(Object tahun) {
		return findByProperty(TAHUN, tahun);
	}

	public List findByNoTurutan(Object noTurutan) {
		return findByProperty(NO_TURUTAN, noTurutan);
	}

	public List findAll() {
		log.debug("finding all Tblphpseqpfdfail instances");
		try {
			String queryString = "from Tblphpseqpfdfail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpseqpfdfail merge(Tblphpseqpfdfail detachedInstance) {
		log.debug("merging Tblphpseqpfdfail instance");
		try {
			Tblphpseqpfdfail result = (Tblphpseqpfdfail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpseqpfdfail instance) {
		log.debug("attaching dirty Tblphpseqpfdfail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpseqpfdfail instance) {
		log.debug("attaching clean Tblphpseqpfdfail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
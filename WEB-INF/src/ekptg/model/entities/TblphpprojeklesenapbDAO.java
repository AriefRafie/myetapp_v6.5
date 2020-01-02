package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpprojeklesenapb entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpprojeklesenapb
 * @author MyEclipse Persistence Tools
 */

public class TblphpprojeklesenapbDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpprojeklesenapbDAO.class);
	// property constants
	public static final String NAMA_PROJEK = "namaProjek";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpprojeklesenapb transientInstance) {
		log.debug("saving Tblphpprojeklesenapb instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpprojeklesenapb persistentInstance) {
		log.debug("deleting Tblphpprojeklesenapb instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpprojeklesenapb findById(java.lang.Long id) {
		log.debug("getting Tblphpprojeklesenapb instance with id: " + id);
		try {
			Tblphpprojeklesenapb instance = (Tblphpprojeklesenapb) getSession()
					.get("ekptg.model.entities.Tblphpprojeklesenapb", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpprojeklesenapb instance) {
		log.debug("finding Tblphpprojeklesenapb instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpprojeklesenapb").add(
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
		log.debug("finding Tblphpprojeklesenapb instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpprojeklesenapb as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaProjek(Object namaProjek) {
		return findByProperty(NAMA_PROJEK, namaProjek);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpprojeklesenapb instances");
		try {
			String queryString = "from Tblphpprojeklesenapb";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpprojeklesenapb merge(Tblphpprojeklesenapb detachedInstance) {
		log.debug("merging Tblphpprojeklesenapb instance");
		try {
			Tblphpprojeklesenapb result = (Tblphpprojeklesenapb) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpprojeklesenapb instance) {
		log.debug("attaching dirty Tblphpprojeklesenapb instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpprojeklesenapb instance) {
		log.debug("attaching clean Tblphpprojeklesenapb instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
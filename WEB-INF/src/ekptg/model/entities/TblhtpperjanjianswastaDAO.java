package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpperjanjianswasta entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpperjanjianswasta
 * @author MyEclipse Persistence Tools
 */

public class TblhtpperjanjianswastaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpperjanjianswastaDAO.class);
	// property constants
	public static final String ID_PERJANJIAN = "idPerjanjian";
	public static final String NILAI_TANAH = "nilaiTanah";
	public static final String NILAIAN_PROJEK = "nilaianProjek";
	public static final String CARA_LAKSANAFEE = "caraLaksanafee";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpperjanjianswasta transientInstance) {
		log.debug("saving Tblhtpperjanjianswasta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpperjanjianswasta persistentInstance) {
		log.debug("deleting Tblhtpperjanjianswasta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpperjanjianswasta findById(java.lang.Long id) {
		log.debug("getting Tblhtpperjanjianswasta instance with id: " + id);
		try {
			Tblhtpperjanjianswasta instance = (Tblhtpperjanjianswasta) getSession()
					.get("ekptg.model.entities.Tblhtpperjanjianswasta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpperjanjianswasta instance) {
		log.debug("finding Tblhtpperjanjianswasta instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpperjanjianswasta").add(
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
		log.debug("finding Tblhtpperjanjianswasta instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpperjanjianswasta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPerjanjian(Object idPerjanjian) {
		return findByProperty(ID_PERJANJIAN, idPerjanjian);
	}

	public List findByNilaiTanah(Object nilaiTanah) {
		return findByProperty(NILAI_TANAH, nilaiTanah);
	}

	public List findByNilaianProjek(Object nilaianProjek) {
		return findByProperty(NILAIAN_PROJEK, nilaianProjek);
	}

	public List findByCaraLaksanafee(Object caraLaksanafee) {
		return findByProperty(CARA_LAKSANAFEE, caraLaksanafee);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpperjanjianswasta instances");
		try {
			String queryString = "from Tblhtpperjanjianswasta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpperjanjianswasta merge(Tblhtpperjanjianswasta detachedInstance) {
		log.debug("merging Tblhtpperjanjianswasta instance");
		try {
			Tblhtpperjanjianswasta result = (Tblhtpperjanjianswasta) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpperjanjianswasta instance) {
		log.debug("attaching dirty Tblhtpperjanjianswasta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpperjanjianswasta instance) {
		log.debug("attaching clean Tblhtpperjanjianswasta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
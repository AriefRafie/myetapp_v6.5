package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppajakan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppajakan
 * @author MyEclipse Persistence Tools
 */

public class TblhtppajakanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtppajakanDAO.class);
	// property constants
	public static final String TUJUAN = "tujuan";
	public static final String TEMPOH_PAJAKAN = "tempohPajakan";
	public static final String CARA_BAYAR = "caraBayar";
	public static final String KATEGORI_CUKAI = "kategoriCukai";
	public static final String KADAR_CUKAI = "kadarCukai";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtppajakan transientInstance) {
		log.debug("saving Tblhtppajakan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppajakan persistentInstance) {
		log.debug("deleting Tblhtppajakan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppajakan findById(java.lang.Long id) {
		log.debug("getting Tblhtppajakan instance with id: " + id);
		try {
			Tblhtppajakan instance = (Tblhtppajakan) getSession().get(
					"ekptg.model.entities.Tblhtppajakan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppajakan instance) {
		log.debug("finding Tblhtppajakan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppajakan").add(
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
		log.debug("finding Tblhtppajakan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppajakan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTujuan(Object tujuan) {
		return findByProperty(TUJUAN, tujuan);
	}

	public List findByTempohPajakan(Object tempohPajakan) {
		return findByProperty(TEMPOH_PAJAKAN, tempohPajakan);
	}

	public List findByCaraBayar(Object caraBayar) {
		return findByProperty(CARA_BAYAR, caraBayar);
	}

	public List findByKategoriCukai(Object kategoriCukai) {
		return findByProperty(KATEGORI_CUKAI, kategoriCukai);
	}

	public List findByKadarCukai(Object kadarCukai) {
		return findByProperty(KADAR_CUKAI, kadarCukai);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtppajakan instances");
		try {
			String queryString = "from Tblhtppajakan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppajakan merge(Tblhtppajakan detachedInstance) {
		log.debug("merging Tblhtppajakan instance");
		try {
			Tblhtppajakan result = (Tblhtppajakan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppajakan instance) {
		log.debug("attaching dirty Tblhtppajakan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppajakan instance) {
		log.debug("attaching clean Tblhtppajakan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
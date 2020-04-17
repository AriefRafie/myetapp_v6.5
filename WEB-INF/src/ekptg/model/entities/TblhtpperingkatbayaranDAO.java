package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpperingkatbayaran entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpperingkatbayaran
 * @author MyEclipse Persistence Tools
 */

public class TblhtpperingkatbayaranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtpperingkatbayaranDAO.class);
	// property constants
	public static final String PERINGKAT_BAYARAN = "peringkatBayaran";
	public static final String TAHUN_CUKAI = "tahunCukai";
	public static final String ID_PEGAWAI = "idPegawai";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpperingkatbayaran transientInstance) {
		log.debug("saving Tblhtpperingkatbayaran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpperingkatbayaran persistentInstance) {
		log.debug("deleting Tblhtpperingkatbayaran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpperingkatbayaran findById(java.lang.Long id) {
		log.debug("getting Tblhtpperingkatbayaran instance with id: " + id);
		try {
			Tblhtpperingkatbayaran instance = (Tblhtpperingkatbayaran) getSession()
					.get("ekptg.model.entities.Tblhtpperingkatbayaran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpperingkatbayaran instance) {
		log.debug("finding Tblhtpperingkatbayaran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpperingkatbayaran").add(
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
		log.debug("finding Tblhtpperingkatbayaran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpperingkatbayaran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPeringkatBayaran(Object peringkatBayaran) {
		return findByProperty(PERINGKAT_BAYARAN, peringkatBayaran);
	}

	public List findByTahunCukai(Object tahunCukai) {
		return findByProperty(TAHUN_CUKAI, tahunCukai);
	}

	public List findByIdPegawai(Object idPegawai) {
		return findByProperty(ID_PEGAWAI, idPegawai);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpperingkatbayaran instances");
		try {
			String queryString = "from Tblhtpperingkatbayaran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpperingkatbayaran merge(Tblhtpperingkatbayaran detachedInstance) {
		log.debug("merging Tblhtpperingkatbayaran instance");
		try {
			Tblhtpperingkatbayaran result = (Tblhtpperingkatbayaran) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpperingkatbayaran instance) {
		log.debug("attaching dirty Tblhtpperingkatbayaran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpperingkatbayaran instance) {
		log.debug("attaching clean Tblhtpperingkatbayaran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
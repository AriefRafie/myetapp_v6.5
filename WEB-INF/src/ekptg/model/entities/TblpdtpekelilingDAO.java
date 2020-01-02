package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtpekeliling entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtpekeliling
 * @author MyEclipse Persistence Tools
 */

public class TblpdtpekelilingDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpdtpekelilingDAO.class);
	// property constants
	public static final String KATEGORI_PEKELILING = "kategoriPekeliling";
	public static final String BIL_PEKELILING = "bilPekeliling";
	public static final String TAJUK_PEKELILING = "tajukPekeliling";
	public static final String ID_FAIL = "idFail";
	public static final String SEKSYEN_URUSETIA = "seksyenUrusetia";
	public static final String STATUS = "status";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DOKUMEN_PEKELILING = "idDokumenPekeliling";

	public void save(Tblpdtpekeliling transientInstance) {
		log.debug("saving Tblpdtpekeliling instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtpekeliling persistentInstance) {
		log.debug("deleting Tblpdtpekeliling instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtpekeliling findById(java.lang.Long id) {
		log.debug("getting Tblpdtpekeliling instance with id: " + id);
		try {
			Tblpdtpekeliling instance = (Tblpdtpekeliling) getSession().get(
					"ekptg.model.entities.Tblpdtpekeliling", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtpekeliling instance) {
		log.debug("finding Tblpdtpekeliling instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtpekeliling").add(
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
		log.debug("finding Tblpdtpekeliling instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtpekeliling as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKategoriPekeliling(Object kategoriPekeliling) {
		return findByProperty(KATEGORI_PEKELILING, kategoriPekeliling);
	}

	public List findByBilPekeliling(Object bilPekeliling) {
		return findByProperty(BIL_PEKELILING, bilPekeliling);
	}

	public List findByTajukPekeliling(Object tajukPekeliling) {
		return findByProperty(TAJUK_PEKELILING, tajukPekeliling);
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findBySeksyenUrusetia(Object seksyenUrusetia) {
		return findByProperty(SEKSYEN_URUSETIA, seksyenUrusetia);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDokumenPekeliling(Object idDokumenPekeliling) {
		return findByProperty(ID_DOKUMEN_PEKELILING, idDokumenPekeliling);
	}

	public List findAll() {
		log.debug("finding all Tblpdtpekeliling instances");
		try {
			String queryString = "from Tblpdtpekeliling";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtpekeliling merge(Tblpdtpekeliling detachedInstance) {
		log.debug("merging Tblpdtpekeliling instance");
		try {
			Tblpdtpekeliling result = (Tblpdtpekeliling) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtpekeliling instance) {
		log.debug("attaching dirty Tblpdtpekeliling instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtpekeliling instance) {
		log.debug("attaching clean Tblpdtpekeliling instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtdokumen entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtdokumen
 * @author MyEclipse Persistence Tools
 */

public class TblpdtdokumenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpdtdokumenDAO.class);
	// property constants
	public static final String NO_DOKUMEN = "noDokumen";
	public static final String TAJUK_DOKUMEN = "tajukDokumen";
	public static final String KATEGORI_DOKUMEN = "kategoriDokumen";
	public static final String ID_FAIL = "idFail";
	public static final String SEKSYEN_URUSETIA = "seksyenUrusetia";
	public static final String KANDUNGAN_DOKUMEN = "kandunganDokumen";
	public static final String CATATAN = "catatan";
	public static final String DIR_DOKUMEN = "dirDokumen";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblpdtdokumen transientInstance) {
		log.debug("saving Tblpdtdokumen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtdokumen persistentInstance) {
		log.debug("deleting Tblpdtdokumen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtdokumen findById(java.lang.Long id) {
		log.debug("getting Tblpdtdokumen instance with id: " + id);
		try {
			Tblpdtdokumen instance = (Tblpdtdokumen) getSession().get(
					"ekptg.model.entities.Tblpdtdokumen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtdokumen instance) {
		log.debug("finding Tblpdtdokumen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtdokumen").add(
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
		log.debug("finding Tblpdtdokumen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtdokumen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoDokumen(Object noDokumen) {
		return findByProperty(NO_DOKUMEN, noDokumen);
	}

	public List findByTajukDokumen(Object tajukDokumen) {
		return findByProperty(TAJUK_DOKUMEN, tajukDokumen);
	}

	public List findByKategoriDokumen(Object kategoriDokumen) {
		return findByProperty(KATEGORI_DOKUMEN, kategoriDokumen);
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findBySeksyenUrusetia(Object seksyenUrusetia) {
		return findByProperty(SEKSYEN_URUSETIA, seksyenUrusetia);
	}

	public List findByKandunganDokumen(Object kandunganDokumen) {
		return findByProperty(KANDUNGAN_DOKUMEN, kandunganDokumen);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByDirDokumen(Object dirDokumen) {
		return findByProperty(DIR_DOKUMEN, dirDokumen);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblpdtdokumen instances");
		try {
			String queryString = "from Tblpdtdokumen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtdokumen merge(Tblpdtdokumen detachedInstance) {
		log.debug("merging Tblpdtdokumen instance");
		try {
			Tblpdtdokumen result = (Tblpdtdokumen) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtdokumen instance) {
		log.debug("attaching dirty Tblpdtdokumen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtdokumen instance) {
		log.debug("attaching clean Tblpdtdokumen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
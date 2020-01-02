package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangi entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangi
 * @author MyEclipse Persistence Tools
 */

public class TblpptborangiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptborangiDAO.class);
	// property constants
	public static final String JENIS_AMBILSEGERA = "jenisAmbilsegera";
	public static final String NO_RUJUKAN_SURAT = "noRujukanSurat";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangi transientInstance) {
		log.debug("saving Tblpptborangi instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangi persistentInstance) {
		log.debug("deleting Tblpptborangi instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangi findById(java.lang.Long id) {
		log.debug("getting Tblpptborangi instance with id: " + id);
		try {
			Tblpptborangi instance = (Tblpptborangi) getSession().get(
					"ekptg.model.entities.Tblpptborangi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangi instance) {
		log.debug("finding Tblpptborangi instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangi").add(
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
		log.debug("finding Tblpptborangi instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangi as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJenisAmbilsegera(Object jenisAmbilsegera) {
		return findByProperty(JENIS_AMBILSEGERA, jenisAmbilsegera);
	}

	public List findByNoRujukanSurat(Object noRujukanSurat) {
		return findByProperty(NO_RUJUKAN_SURAT, noRujukanSurat);
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblpptborangi instances");
		try {
			String queryString = "from Tblpptborangi";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangi merge(Tblpptborangi detachedInstance) {
		log.debug("merging Tblpptborangi instance");
		try {
			Tblpptborangi result = (Tblpptborangi) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangi instance) {
		log.debug("attaching dirty Tblpptborangi instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangi instance) {
		log.debug("attaching clean Tblpptborangi instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangh entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangh
 * @author MyEclipse Persistence Tools
 */

public class TblpptboranghDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptboranghDAO.class);
	// property constants
	public static final String JENIS_BORANGH = "jenisBorangh";
	public static final String TARIKH_CETAK_SEMULA = "tarikhCetakSemula";
	public static final String KEPUTUSAN = "keputusan";
	public static final String ID_BORANGG = "idBorangg";
	public static final String STATUS_CEK = "statusCek";
	public static final String ID_PIHAKBERKEPENTINGAN = "idPihakberkepentingan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangh transientInstance) {
		log.debug("saving Tblpptborangh instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangh persistentInstance) {
		log.debug("deleting Tblpptborangh instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangh findById(java.lang.Long id) {
		log.debug("getting Tblpptborangh instance with id: " + id);
		try {
			Tblpptborangh instance = (Tblpptborangh) getSession().get(
					"ekptg.model.entities.Tblpptborangh", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangh instance) {
		log.debug("finding Tblpptborangh instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangh").add(
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
		log.debug("finding Tblpptborangh instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangh as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJenisBorangh(Object jenisBorangh) {
		return findByProperty(JENIS_BORANGH, jenisBorangh);
	}

	public List findByTarikhCetakSemula(Object tarikhCetakSemula) {
		return findByProperty(TARIKH_CETAK_SEMULA, tarikhCetakSemula);
	}

	public List findByKeputusan(Object keputusan) {
		return findByProperty(KEPUTUSAN, keputusan);
	}

	public List findByIdBorangg(Object idBorangg) {
		return findByProperty(ID_BORANGG, idBorangg);
	}

	public List findByStatusCek(Object statusCek) {
		return findByProperty(STATUS_CEK, statusCek);
	}

	public List findByIdPihakberkepentingan(Object idPihakberkepentingan) {
		return findByProperty(ID_PIHAKBERKEPENTINGAN, idPihakberkepentingan);
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
		log.debug("finding all Tblpptborangh instances");
		try {
			String queryString = "from Tblpptborangh";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangh merge(Tblpptborangh detachedInstance) {
		log.debug("merging Tblpptborangh instance");
		try {
			Tblpptborangh result = (Tblpptborangh) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangh instance) {
		log.debug("attaching dirty Tblpptborangh instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangh instance) {
		log.debug("attaching clean Tblpptborangh instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
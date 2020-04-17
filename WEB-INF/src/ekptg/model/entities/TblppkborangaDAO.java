package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkboranga entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkboranga
 * @author MyEclipse Persistence Tools
 */

public class TblppkborangaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkborangaDAO.class);
	// property constants
	public static final String JUMLAH_HTA_TARIKHMOHON = "jumlahHtaTarikhmohon";
	public static final String JUMLAH_HTA_TARIKHMATI = "jumlahHtaTarikhmati";
	public static final String JUMLAH_HA_TARIKHMOHON = "jumlahHaTarikhmohon";
	public static final String JUMLAH_HA_TARIKHMATI = "jumlahHaTarikhmati";
	public static final String JUMLAH_HARTA_TARIKHMOHON = "jumlahHartaTarikhmohon";
	public static final String JUMLAH_HARTA_TARIKHMATI = "jumlahHartaTarikhmati";
	public static final String CATATAN = "catatan";
	public static final String ID_NEGERIMHN = "idNegerimhn";
	public static final String ID_DAERAHMHN = "idDaerahmhn";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkboranga transientInstance) {
		log.debug("saving Tblppkboranga instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkboranga persistentInstance) {
		log.debug("deleting Tblppkboranga instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkboranga findById(java.lang.Long id) {
		log.debug("getting Tblppkboranga instance with id: " + id);
		try {
			Tblppkboranga instance = (Tblppkboranga) getSession().get(
					"ekptg.model.entities.Tblppkboranga", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkboranga instance) {
		log.debug("finding Tblppkboranga instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkboranga").add(
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
		log.debug("finding Tblppkboranga instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkboranga as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJumlahHtaTarikhmohon(Object jumlahHtaTarikhmohon) {
		return findByProperty(JUMLAH_HTA_TARIKHMOHON, jumlahHtaTarikhmohon);
	}

	public List findByJumlahHtaTarikhmati(Object jumlahHtaTarikhmati) {
		return findByProperty(JUMLAH_HTA_TARIKHMATI, jumlahHtaTarikhmati);
	}

	public List findByJumlahHaTarikhmohon(Object jumlahHaTarikhmohon) {
		return findByProperty(JUMLAH_HA_TARIKHMOHON, jumlahHaTarikhmohon);
	}

	public List findByJumlahHaTarikhmati(Object jumlahHaTarikhmati) {
		return findByProperty(JUMLAH_HA_TARIKHMATI, jumlahHaTarikhmati);
	}

	public List findByJumlahHartaTarikhmohon(Object jumlahHartaTarikhmohon) {
		return findByProperty(JUMLAH_HARTA_TARIKHMOHON, jumlahHartaTarikhmohon);
	}

	public List findByJumlahHartaTarikhmati(Object jumlahHartaTarikhmati) {
		return findByProperty(JUMLAH_HARTA_TARIKHMATI, jumlahHartaTarikhmati);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdNegerimhn(Object idNegerimhn) {
		return findByProperty(ID_NEGERIMHN, idNegerimhn);
	}

	public List findByIdDaerahmhn(Object idDaerahmhn) {
		return findByProperty(ID_DAERAHMHN, idDaerahmhn);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkboranga instances");
		try {
			String queryString = "from Tblppkboranga";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkboranga merge(Tblppkboranga detachedInstance) {
		log.debug("merging Tblppkboranga instance");
		try {
			Tblppkboranga result = (Tblppkboranga) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkboranga instance) {
		log.debug("attaching dirty Tblppkboranga instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkboranga instance) {
		log.debug("attaching clean Tblppkboranga instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpborangk2k3 entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpborangk2k3
 * @author MyEclipse Persistence Tools
 */

public class Tblphpborangk2k3DAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(Tblphpborangk2k3DAO.class);
	// property constants
	public static final String ID_BARGE = "idBarge";
	public static final String LOKASI_DIBEKALKAN = "lokasiDibekalkan";
	public static final String AKUAN_KASTAM = "akuanKastam";
	public static final String TUJUAN = "tujuan";
	public static final String KUANTITI = "kuantiti";
	public static final String ID_UNITISIPADU = "idUnitisipadu";
	public static final String ANGGARAN_ROYALTI = "anggaranRoyalti";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpborangk2k3 transientInstance) {
		log.debug("saving Tblphpborangk2k3 instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpborangk2k3 persistentInstance) {
		log.debug("deleting Tblphpborangk2k3 instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpborangk2k3 findById(java.lang.Long id) {
		log.debug("getting Tblphpborangk2k3 instance with id: " + id);
		try {
			Tblphpborangk2k3 instance = (Tblphpborangk2k3) getSession().get(
					"ekptg.model.entities.Tblphpborangk2k3", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpborangk2k3 instance) {
		log.debug("finding Tblphpborangk2k3 instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpborangk2k3").add(
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
		log.debug("finding Tblphpborangk2k3 instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpborangk2k3 as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdBarge(Object idBarge) {
		return findByProperty(ID_BARGE, idBarge);
	}

	public List findByLokasiDibekalkan(Object lokasiDibekalkan) {
		return findByProperty(LOKASI_DIBEKALKAN, lokasiDibekalkan);
	}

	public List findByAkuanKastam(Object akuanKastam) {
		return findByProperty(AKUAN_KASTAM, akuanKastam);
	}

	public List findByTujuan(Object tujuan) {
		return findByProperty(TUJUAN, tujuan);
	}

	public List findByKuantiti(Object kuantiti) {
		return findByProperty(KUANTITI, kuantiti);
	}

	public List findByIdUnitisipadu(Object idUnitisipadu) {
		return findByProperty(ID_UNITISIPADU, idUnitisipadu);
	}

	public List findByAnggaranRoyalti(Object anggaranRoyalti) {
		return findByProperty(ANGGARAN_ROYALTI, anggaranRoyalti);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpborangk2k3 instances");
		try {
			String queryString = "from Tblphpborangk2k3";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpborangk2k3 merge(Tblphpborangk2k3 detachedInstance) {
		log.debug("merging Tblphpborangk2k3 instance");
		try {
			Tblphpborangk2k3 result = (Tblphpborangk2k3) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpborangk2k3 instance) {
		log.debug("attaching dirty Tblphpborangk2k3 instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpborangk2k3 instance) {
		log.debug("attaching clean Tblphpborangk2k3 instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
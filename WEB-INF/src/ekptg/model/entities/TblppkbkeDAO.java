package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkbke entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkbke
 * @author MyEclipse Persistence Tools
 */

public class TblppkbkeDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkbkeDAO.class);
	// property constants
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERIUNITPSK = "idNegeriunitpsk";
	public static final String ALASAN1 = "alasan1";
	public static final String ALASAN2 = "alasan2";
	public static final String ALASAN3 = "alasan3";
	public static final String ALASAN4 = "alasan4";
	public static final String ALASAN5 = "alasan5";
	public static final String ALASAN6 = "alasan6";
	public static final String ALASAN7 = "alasan7";
	public static final String ALASAN_LAIN = "alasanLain";
	public static final String KEPUTUSAN_PEGAWAI = "keputusanPegawai";
	public static final String KEPUTUSAN_KPTG_PTG = "keputusanKptgPtg";
	public static final String CATATAN_PEGAWAI = "catatanPegawai";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkbke transientInstance) {
		log.debug("saving Tblppkbke instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkbke persistentInstance) {
		log.debug("deleting Tblppkbke instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkbke findById(java.lang.Long id) {
		log.debug("getting Tblppkbke instance with id: " + id);
		try {
			Tblppkbke instance = (Tblppkbke) getSession().get(
					"ekptg.model.entities.Tblppkbke", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkbke instance) {
		log.debug("finding Tblppkbke instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkbke").add(
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
		log.debug("finding Tblppkbke instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblppkbke as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdNegeriunitpsk(Object idNegeriunitpsk) {
		return findByProperty(ID_NEGERIUNITPSK, idNegeriunitpsk);
	}

	public List findByAlasan1(Object alasan1) {
		return findByProperty(ALASAN1, alasan1);
	}

	public List findByAlasan2(Object alasan2) {
		return findByProperty(ALASAN2, alasan2);
	}

	public List findByAlasan3(Object alasan3) {
		return findByProperty(ALASAN3, alasan3);
	}

	public List findByAlasan4(Object alasan4) {
		return findByProperty(ALASAN4, alasan4);
	}

	public List findByAlasan5(Object alasan5) {
		return findByProperty(ALASAN5, alasan5);
	}

	public List findByAlasan6(Object alasan6) {
		return findByProperty(ALASAN6, alasan6);
	}

	public List findByAlasan7(Object alasan7) {
		return findByProperty(ALASAN7, alasan7);
	}

	public List findByAlasanLain(Object alasanLain) {
		return findByProperty(ALASAN_LAIN, alasanLain);
	}

	public List findByKeputusanPegawai(Object keputusanPegawai) {
		return findByProperty(KEPUTUSAN_PEGAWAI, keputusanPegawai);
	}

	public List findByKeputusanKptgPtg(Object keputusanKptgPtg) {
		return findByProperty(KEPUTUSAN_KPTG_PTG, keputusanKptgPtg);
	}

	public List findByCatatanPegawai(Object catatanPegawai) {
		return findByProperty(CATATAN_PEGAWAI, catatanPegawai);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkbke instances");
		try {
			String queryString = "from Tblppkbke";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkbke merge(Tblppkbke detachedInstance) {
		log.debug("merging Tblppkbke instance");
		try {
			Tblppkbke result = (Tblppkbke) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkbke instance) {
		log.debug("attaching dirty Tblppkbke instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkbke instance) {
		log.debug("attaching clean Tblppkbke instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
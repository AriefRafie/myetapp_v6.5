package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkborangj entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkborangj
 * @author MyEclipse Persistence Tools
 */

public class TblppkborangjDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkborangjDAO.class);
	// property constants
	public static final String JENIS_RUJUKAN = "jenisRujukan";
	public static final String SEBAB_TANGGUH = "sebabTangguh";
	public static final String ID_NEGERIMAHKAMAH = "idNegerimahkamah";
	public static final String ID_DAERAH_MAHKAMAH = "idDaerahMahkamah";
	public static final String ID_MAHKAMAH = "idMahkamah";
	public static final String CATATAN1 = "catatan1";
	public static final String CATATAN2 = "catatan2";
	public static final String CATATAN3 = "catatan3";
	public static final String CATATAN4 = "catatan4";
	public static final String CATATAN5 = "catatan5";
	public static final String KEPUTUSAN_MAHKAMAH = "keputusanMahkamah";
	public static final String CATATAN_LAIN = "catatanLain";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkborangj transientInstance) {
		log.debug("saving Tblppkborangj instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkborangj persistentInstance) {
		log.debug("deleting Tblppkborangj instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkborangj findById(java.lang.Long id) {
		log.debug("getting Tblppkborangj instance with id: " + id);
		try {
			Tblppkborangj instance = (Tblppkborangj) getSession().get(
					"ekptg.model.entities.Tblppkborangj", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkborangj instance) {
		log.debug("finding Tblppkborangj instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkborangj").add(
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
		log.debug("finding Tblppkborangj instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkborangj as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJenisRujukan(Object jenisRujukan) {
		return findByProperty(JENIS_RUJUKAN, jenisRujukan);
	}

	public List findBySebabTangguh(Object sebabTangguh) {
		return findByProperty(SEBAB_TANGGUH, sebabTangguh);
	}

	public List findByIdNegerimahkamah(Object idNegerimahkamah) {
		return findByProperty(ID_NEGERIMAHKAMAH, idNegerimahkamah);
	}

	public List findByIdDaerahMahkamah(Object idDaerahMahkamah) {
		return findByProperty(ID_DAERAH_MAHKAMAH, idDaerahMahkamah);
	}

	public List findByIdMahkamah(Object idMahkamah) {
		return findByProperty(ID_MAHKAMAH, idMahkamah);
	}

	public List findByCatatan1(Object catatan1) {
		return findByProperty(CATATAN1, catatan1);
	}

	public List findByCatatan2(Object catatan2) {
		return findByProperty(CATATAN2, catatan2);
	}

	public List findByCatatan3(Object catatan3) {
		return findByProperty(CATATAN3, catatan3);
	}

	public List findByCatatan4(Object catatan4) {
		return findByProperty(CATATAN4, catatan4);
	}

	public List findByCatatan5(Object catatan5) {
		return findByProperty(CATATAN5, catatan5);
	}

	public List findByKeputusanMahkamah(Object keputusanMahkamah) {
		return findByProperty(KEPUTUSAN_MAHKAMAH, keputusanMahkamah);
	}

	public List findByCatatanLain(Object catatanLain) {
		return findByProperty(CATATAN_LAIN, catatanLain);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkborangj instances");
		try {
			String queryString = "from Tblppkborangj";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkborangj merge(Tblppkborangj detachedInstance) {
		log.debug("merging Tblppkborangj instance");
		try {
			Tblppkborangj result = (Tblppkborangj) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkborangj instance) {
		log.debug("attaching dirty Tblppkborangj instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkborangj instance) {
		log.debug("attaching clean Tblppkborangj instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptterimabayaran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptterimabayaran
 * @author MyEclipse Persistence Tools
 */

public class TblpptterimabayaranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpptterimabayaranDAO.class);
	// property constants
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String NO_RUJUKAN_SURAT = "noRujukanSurat";
	public static final String ID_BANK = "idBank";
	public static final String AMAUN_CEK = "amaunCek";
	public static final String MASA_AMBIL_CEK = "masaAmbilCek";
	public static final String TEMPAT_AMBIL = "tempatAmbil";
	public static final String JENIS_AWARD = "jenisAward";
	public static final String NO_BORANGK = "noBorangk";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptterimabayaran transientInstance) {
		log.debug("saving Tblpptterimabayaran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptterimabayaran persistentInstance) {
		log.debug("deleting Tblpptterimabayaran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptterimabayaran findById(java.lang.Long id) {
		log.debug("getting Tblpptterimabayaran instance with id: " + id);
		try {
			Tblpptterimabayaran instance = (Tblpptterimabayaran) getSession()
					.get("ekptg.model.entities.Tblpptterimabayaran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptterimabayaran instance) {
		log.debug("finding Tblpptterimabayaran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptterimabayaran").add(
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
		log.debug("finding Tblpptterimabayaran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptterimabayaran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByNoRujukanSurat(Object noRujukanSurat) {
		return findByProperty(NO_RUJUKAN_SURAT, noRujukanSurat);
	}

	public List findByIdBank(Object idBank) {
		return findByProperty(ID_BANK, idBank);
	}

	public List findByAmaunCek(Object amaunCek) {
		return findByProperty(AMAUN_CEK, amaunCek);
	}

	public List findByMasaAmbilCek(Object masaAmbilCek) {
		return findByProperty(MASA_AMBIL_CEK, masaAmbilCek);
	}

	public List findByTempatAmbil(Object tempatAmbil) {
		return findByProperty(TEMPAT_AMBIL, tempatAmbil);
	}

	public List findByJenisAward(Object jenisAward) {
		return findByProperty(JENIS_AWARD, jenisAward);
	}

	public List findByNoBorangk(Object noBorangk) {
		return findByProperty(NO_BORANGK, noBorangk);
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
		log.debug("finding all Tblpptterimabayaran instances");
		try {
			String queryString = "from Tblpptterimabayaran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptterimabayaran merge(Tblpptterimabayaran detachedInstance) {
		log.debug("merging Tblpptterimabayaran instance");
		try {
			Tblpptterimabayaran result = (Tblpptterimabayaran) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptterimabayaran instance) {
		log.debug("attaching dirty Tblpptterimabayaran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptterimabayaran instance) {
		log.debug("attaching clean Tblpptterimabayaran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
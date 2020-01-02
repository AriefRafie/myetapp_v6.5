package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangm entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangm
 * @author MyEclipse Persistence Tools
 */

public class TblpptborangmDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptborangmDAO.class);
	// property constants
	public static final String NAMA_MAHKAMAH = "namaMahkamah";
	public static final String ALAMAT_MAHKAMAH1 = "alamatMahkamah1";
	public static final String ALAMAT_MAHKAMAH2 = "alamatMahkamah2";
	public static final String ALAMAT_MAHKAMAH3 = "alamatMahkamah3";
	public static final String TUJUAN = "tujuan";
	public static final String PERKARA_RUJUKAN = "perkaraRujukan";
	public static final String KEPUTUSAN_MAHKAMAH = "keputusanMahkamah";
	public static final String PERINTAH_MAHKAMAH = "perintahMahkamah";
	public static final String PAMPASAN_ASAL = "pampasanAsal";
	public static final String PAMPASAN_BANTAHAN = "pampasanBantahan";
	public static final String BEZA_PAMPASAN = "bezaPampasan";
	public static final String NO_RUJUKAN_PROSIDING = "noRujukanProsiding";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangm transientInstance) {
		log.debug("saving Tblpptborangm instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangm persistentInstance) {
		log.debug("deleting Tblpptborangm instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangm findById(java.lang.Long id) {
		log.debug("getting Tblpptborangm instance with id: " + id);
		try {
			Tblpptborangm instance = (Tblpptborangm) getSession().get(
					"ekptg.model.entities.Tblpptborangm", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangm instance) {
		log.debug("finding Tblpptborangm instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangm").add(
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
		log.debug("finding Tblpptborangm instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangm as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaMahkamah(Object namaMahkamah) {
		return findByProperty(NAMA_MAHKAMAH, namaMahkamah);
	}

	public List findByAlamatMahkamah1(Object alamatMahkamah1) {
		return findByProperty(ALAMAT_MAHKAMAH1, alamatMahkamah1);
	}

	public List findByAlamatMahkamah2(Object alamatMahkamah2) {
		return findByProperty(ALAMAT_MAHKAMAH2, alamatMahkamah2);
	}

	public List findByAlamatMahkamah3(Object alamatMahkamah3) {
		return findByProperty(ALAMAT_MAHKAMAH3, alamatMahkamah3);
	}

	public List findByTujuan(Object tujuan) {
		return findByProperty(TUJUAN, tujuan);
	}

	public List findByPerkaraRujukan(Object perkaraRujukan) {
		return findByProperty(PERKARA_RUJUKAN, perkaraRujukan);
	}

	public List findByKeputusanMahkamah(Object keputusanMahkamah) {
		return findByProperty(KEPUTUSAN_MAHKAMAH, keputusanMahkamah);
	}

	public List findByPerintahMahkamah(Object perintahMahkamah) {
		return findByProperty(PERINTAH_MAHKAMAH, perintahMahkamah);
	}

	public List findByPampasanAsal(Object pampasanAsal) {
		return findByProperty(PAMPASAN_ASAL, pampasanAsal);
	}

	public List findByPampasanBantahan(Object pampasanBantahan) {
		return findByProperty(PAMPASAN_BANTAHAN, pampasanBantahan);
	}

	public List findByBezaPampasan(Object bezaPampasan) {
		return findByProperty(BEZA_PAMPASAN, bezaPampasan);
	}

	public List findByNoRujukanProsiding(Object noRujukanProsiding) {
		return findByProperty(NO_RUJUKAN_PROSIDING, noRujukanProsiding);
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
		log.debug("finding all Tblpptborangm instances");
		try {
			String queryString = "from Tblpptborangm";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangm merge(Tblpptborangm detachedInstance) {
		log.debug("merging Tblpptborangm instance");
		try {
			Tblpptborangm result = (Tblpptborangm) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangm instance) {
		log.debug("attaching dirty Tblpptborangm instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangm instance) {
		log.debug("attaching clean Tblpptborangm instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
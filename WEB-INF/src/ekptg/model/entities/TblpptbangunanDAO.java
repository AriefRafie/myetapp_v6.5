package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptbangunan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptbangunan
 * @author MyEclipse Persistence Tools
 */

public class TblpptbangunanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptbangunanDAO.class);
	// property constants
	public static final String NO_BANGUNAN = "noBangunan";
	public static final String JENIS_BANGUNAN = "jenisBangunan";
	public static final String SAIZ_BANGUNAN = "saizBangunan";
	public static final String NILAI_BANGUNAN = "nilaiBangunan";
	public static final String NO_UNIT_BANGUNAN = "noUnitBangunan";
	public static final String PEMILIK = "pemilik";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String ID_JENISPB = "idJenispb";
	public static final String ID_JENISNOPB = "idJenisnopb";
	public static final String NO_PEMILIK = "noPemilik";
	public static final String NO_TEL = "noTel";
	public static final String ULASAN = "ulasan";
	public static final String PERIHAL_KEPENTINGAN_LAIN2 = "perihalKepentinganLain2";
	public static final String ID_PIHAKBERKEPENTINGAN = "idPihakberkepentingan";
	public static final String KOS_PINDAH = "kosPindah";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptbangunan transientInstance) {
		log.debug("saving Tblpptbangunan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptbangunan persistentInstance) {
		log.debug("deleting Tblpptbangunan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptbangunan findById(java.lang.Long id) {
		log.debug("getting Tblpptbangunan instance with id: " + id);
		try {
			Tblpptbangunan instance = (Tblpptbangunan) getSession().get(
					"ekptg.model.entities.Tblpptbangunan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptbangunan instance) {
		log.debug("finding Tblpptbangunan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptbangunan").add(
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
		log.debug("finding Tblpptbangunan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptbangunan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoBangunan(Object noBangunan) {
		return findByProperty(NO_BANGUNAN, noBangunan);
	}

	public List findByJenisBangunan(Object jenisBangunan) {
		return findByProperty(JENIS_BANGUNAN, jenisBangunan);
	}

	public List findBySaizBangunan(Object saizBangunan) {
		return findByProperty(SAIZ_BANGUNAN, saizBangunan);
	}

	public List findByNilaiBangunan(Object nilaiBangunan) {
		return findByProperty(NILAI_BANGUNAN, nilaiBangunan);
	}

	public List findByNoUnitBangunan(Object noUnitBangunan) {
		return findByProperty(NO_UNIT_BANGUNAN, noUnitBangunan);
	}

	public List findByPemilik(Object pemilik) {
		return findByProperty(PEMILIK, pemilik);
	}

	public List findByAlamat1(Object alamat1) {
		return findByProperty(ALAMAT1, alamat1);
	}

	public List findByAlamat2(Object alamat2) {
		return findByProperty(ALAMAT2, alamat2);
	}

	public List findByAlamat3(Object alamat3) {
		return findByProperty(ALAMAT3, alamat3);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
	}

	public List findByIdJenispb(Object idJenispb) {
		return findByProperty(ID_JENISPB, idJenispb);
	}

	public List findByIdJenisnopb(Object idJenisnopb) {
		return findByProperty(ID_JENISNOPB, idJenisnopb);
	}

	public List findByNoPemilik(Object noPemilik) {
		return findByProperty(NO_PEMILIK, noPemilik);
	}

	public List findByNoTel(Object noTel) {
		return findByProperty(NO_TEL, noTel);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByPerihalKepentinganLain2(Object perihalKepentinganLain2) {
		return findByProperty(PERIHAL_KEPENTINGAN_LAIN2,
				perihalKepentinganLain2);
	}

	public List findByIdPihakberkepentingan(Object idPihakberkepentingan) {
		return findByProperty(ID_PIHAKBERKEPENTINGAN, idPihakberkepentingan);
	}

	public List findByKosPindah(Object kosPindah) {
		return findByProperty(KOS_PINDAH, kosPindah);
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
		log.debug("finding all Tblpptbangunan instances");
		try {
			String queryString = "from Tblpptbangunan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptbangunan merge(Tblpptbangunan detachedInstance) {
		log.debug("merging Tblpptbangunan instance");
		try {
			Tblpptbangunan result = (Tblpptbangunan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptbangunan instance) {
		log.debug("attaching dirty Tblpptbangunan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptbangunan instance) {
		log.debug("attaching clean Tblpptbangunan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
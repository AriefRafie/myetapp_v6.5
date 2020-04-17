package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkborangppenghutang entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkborangppenghutang
 * @author MyEclipse Persistence Tools
 */

public class TblppkborangppenghutangDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkborangppenghutangDAO.class);
	// property constants
	public static final String NAMA_PENGHUTANG = "namaPenghutang";
	public static final String NO_KP_BARU = "noKpBaru";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String NO_KP_LAIN = "noKpLain";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String BANDAR = "bandar";
	public static final String POSKOD = "poskod";
	public static final String NO_AKAUN = "noAkaun";
	public static final String NAMA_BANK = "namaBank";
	public static final String JUMLAH_HUTANG = "jumlahHutang";
	public static final String CATATAN = "catatan";
	public static final String ID_NEGERI = "idNegeri";
	public static final String JENIS_PENGHUTANG = "jenisPenghutang";
	public static final String JENIS_AGAMA = "jenisAgama";
	public static final String JENIS_WARGA = "jenisWarga";
	public static final String JENIS_KP = "jenisKp";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkborangppenghutang transientInstance) {
		log.debug("saving Tblppkborangppenghutang instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkborangppenghutang persistentInstance) {
		log.debug("deleting Tblppkborangppenghutang instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkborangppenghutang findById(java.lang.Long id) {
		log.debug("getting Tblppkborangppenghutang instance with id: " + id);
		try {
			Tblppkborangppenghutang instance = (Tblppkborangppenghutang) getSession()
					.get("ekptg.model.entities.Tblppkborangppenghutang", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkborangppenghutang instance) {
		log.debug("finding Tblppkborangppenghutang instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkborangppenghutang").add(
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
		log.debug("finding Tblppkborangppenghutang instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkborangppenghutang as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaPenghutang(Object namaPenghutang) {
		return findByProperty(NAMA_PENGHUTANG, namaPenghutang);
	}

	public List findByNoKpBaru(Object noKpBaru) {
		return findByProperty(NO_KP_BARU, noKpBaru);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
	}

	public List findByNoKpLain(Object noKpLain) {
		return findByProperty(NO_KP_LAIN, noKpLain);
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

	public List findByBandar(Object bandar) {
		return findByProperty(BANDAR, bandar);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByNoAkaun(Object noAkaun) {
		return findByProperty(NO_AKAUN, noAkaun);
	}

	public List findByNamaBank(Object namaBank) {
		return findByProperty(NAMA_BANK, namaBank);
	}

	public List findByJumlahHutang(Object jumlahHutang) {
		return findByProperty(JUMLAH_HUTANG, jumlahHutang);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByJenisPenghutang(Object jenisPenghutang) {
		return findByProperty(JENIS_PENGHUTANG, jenisPenghutang);
	}

	public List findByJenisAgama(Object jenisAgama) {
		return findByProperty(JENIS_AGAMA, jenisAgama);
	}

	public List findByJenisWarga(Object jenisWarga) {
		return findByProperty(JENIS_WARGA, jenisWarga);
	}

	public List findByJenisKp(Object jenisKp) {
		return findByProperty(JENIS_KP, jenisKp);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkborangppenghutang instances");
		try {
			String queryString = "from Tblppkborangppenghutang";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkborangppenghutang merge(
			Tblppkborangppenghutang detachedInstance) {
		log.debug("merging Tblppkborangppenghutang instance");
		try {
			Tblppkborangppenghutang result = (Tblppkborangppenghutang) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkborangppenghutang instance) {
		log.debug("attaching dirty Tblppkborangppenghutang instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkborangppenghutang instance) {
		log.debug("attaching clean Tblppkborangppenghutang instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkpemohon entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkpemohon
 * @author MyEclipse Persistence Tools
 */

public class TblppkpemohonDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkpemohonDAO.class);
	// property constants
	public static final String NAMA_PEMOHON = "namaPemohon";
	public static final String NO_KP_BARU = "noKpBaru";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String JENIS_KP = "jenisKp";
	public static final String NO_KP_LAIN = "noKpLain";
	public static final String UMUR = "umur";
	public static final String JANTINA = "jantina";
	public static final String JENIS_AGAMA = "jenisAgama";
	public static final String JENIS_WARGA = "jenisWarga";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String BANDAR = "bandar";
	public static final String POSKOD = "poskod";
	public static final String NO_HP = "noHp";
	public static final String NO_TEL = "noTel";
	public static final String EMEL = "emel";
	public static final String NO_FAX = "noFax";
	public static final String CATATAN = "catatan";
	public static final String ID_NEGERI = "idNegeri";
	public static final String STATUS_PEGUAM = "statusPeguam";
	public static final String STATUS_PEMOHON = "statusPemohon";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkpemohon transientInstance) {
		log.debug("saving Tblppkpemohon instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkpemohon persistentInstance) {
		log.debug("deleting Tblppkpemohon instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkpemohon findById(java.lang.Long id) {
		log.debug("getting Tblppkpemohon instance with id: " + id);
		try {
			Tblppkpemohon instance = (Tblppkpemohon) getSession().get(
					"ekptg.model.entities.Tblppkpemohon", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkpemohon instance) {
		log.debug("finding Tblppkpemohon instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkpemohon").add(
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
		log.debug("finding Tblppkpemohon instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkpemohon as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaPemohon(Object namaPemohon) {
		return findByProperty(NAMA_PEMOHON, namaPemohon);
	}

	public List findByNoKpBaru(Object noKpBaru) {
		return findByProperty(NO_KP_BARU, noKpBaru);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
	}

	public List findByJenisKp(Object jenisKp) {
		return findByProperty(JENIS_KP, jenisKp);
	}

	public List findByNoKpLain(Object noKpLain) {
		return findByProperty(NO_KP_LAIN, noKpLain);
	}

	public List findByUmur(Object umur) {
		return findByProperty(UMUR, umur);
	}

	public List findByJantina(Object jantina) {
		return findByProperty(JANTINA, jantina);
	}

	public List findByJenisAgama(Object jenisAgama) {
		return findByProperty(JENIS_AGAMA, jenisAgama);
	}

	public List findByJenisWarga(Object jenisWarga) {
		return findByProperty(JENIS_WARGA, jenisWarga);
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

	public List findByNoHp(Object noHp) {
		return findByProperty(NO_HP, noHp);
	}

	public List findByNoTel(Object noTel) {
		return findByProperty(NO_TEL, noTel);
	}

	public List findByEmel(Object emel) {
		return findByProperty(EMEL, emel);
	}

	public List findByNoFax(Object noFax) {
		return findByProperty(NO_FAX, noFax);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByStatusPeguam(Object statusPeguam) {
		return findByProperty(STATUS_PEGUAM, statusPeguam);
	}

	public List findByStatusPemohon(Object statusPemohon) {
		return findByProperty(STATUS_PEMOHON, statusPemohon);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkpemohon instances");
		try {
			String queryString = "from Tblppkpemohon";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkpemohon merge(Tblppkpemohon detachedInstance) {
		log.debug("merging Tblppkpemohon instance");
		try {
			Tblppkpemohon result = (Tblppkpemohon) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkpemohon instance) {
		log.debug("attaching dirty Tblppkpemohon instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkpemohon instance) {
		log.debug("attaching clean Tblppkpemohon instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
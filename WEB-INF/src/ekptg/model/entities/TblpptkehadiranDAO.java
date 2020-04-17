package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptkehadiran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptkehadiran
 * @author MyEclipse Persistence Tools
 */

public class TblpptkehadiranDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptkehadiranDAO.class);
	// property constants
	public static final String ID_SIASATAN = "idSiasatan";
	public static final String ID_PIHAKBERKEPENTINGAN = "idPihakberkepentingan";
	public static final String NAMA = "nama";
	public static final String NAMA_KP = "namaKp";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_AKAUN = "noAkaun";
	public static final String ID_BANK = "idBank";
	public static final String NO_KP = "noKp";
	public static final String FLAG_HADIR = "flagHadir";
	public static final String ID_JENISPB = "idJenispb";
	public static final String ID_JENISNOPB = "idJenisnopb";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String PERIHAL_JENIS_LAINPB = "perihalJenisLainpb";
	public static final String JENIS_LAINPB = "jenisLainpb";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptkehadiran transientInstance) {
		log.debug("saving Tblpptkehadiran instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptkehadiran persistentInstance) {
		log.debug("deleting Tblpptkehadiran instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptkehadiran findById(java.lang.Long id) {
		log.debug("getting Tblpptkehadiran instance with id: " + id);
		try {
			Tblpptkehadiran instance = (Tblpptkehadiran) getSession().get(
					"ekptg.model.entities.Tblpptkehadiran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptkehadiran instance) {
		log.debug("finding Tblpptkehadiran instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptkehadiran").add(
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
		log.debug("finding Tblpptkehadiran instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptkehadiran as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdSiasatan(Object idSiasatan) {
		return findByProperty(ID_SIASATAN, idSiasatan);
	}

	public List findByIdPihakberkepentingan(Object idPihakberkepentingan) {
		return findByProperty(ID_PIHAKBERKEPENTINGAN, idPihakberkepentingan);
	}

	public List findByNama(Object nama) {
		return findByProperty(NAMA, nama);
	}

	public List findByNamaKp(Object namaKp) {
		return findByProperty(NAMA_KP, namaKp);
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

	public List findByNoAkaun(Object noAkaun) {
		return findByProperty(NO_AKAUN, noAkaun);
	}

	public List findByIdBank(Object idBank) {
		return findByProperty(ID_BANK, idBank);
	}

	public List findByNoKp(Object noKp) {
		return findByProperty(NO_KP, noKp);
	}

	public List findByFlagHadir(Object flagHadir) {
		return findByProperty(FLAG_HADIR, flagHadir);
	}

	public List findByIdJenispb(Object idJenispb) {
		return findByProperty(ID_JENISPB, idJenispb);
	}

	public List findByIdJenisnopb(Object idJenisnopb) {
		return findByProperty(ID_JENISNOPB, idJenisnopb);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
	}

	public List findByPerihalJenisLainpb(Object perihalJenisLainpb) {
		return findByProperty(PERIHAL_JENIS_LAINPB, perihalJenisLainpb);
	}

	public List findByJenisLainpb(Object jenisLainpb) {
		return findByProperty(JENIS_LAINPB, jenisLainpb);
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
		log.debug("finding all Tblpptkehadiran instances");
		try {
			String queryString = "from Tblpptkehadiran";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptkehadiran merge(Tblpptkehadiran detachedInstance) {
		log.debug("merging Tblpptkehadiran instance");
		try {
			Tblpptkehadiran result = (Tblpptkehadiran) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptkehadiran instance) {
		log.debug("attaching dirty Tblpptkehadiran instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptkehadiran instance) {
		log.debug("attaching clean Tblpptkehadiran instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
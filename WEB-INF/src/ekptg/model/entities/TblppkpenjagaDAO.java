package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkpenjaga entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkpenjaga
 * @author MyEclipse Persistence Tools
 */

public class TblppkpenjagaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkpenjagaDAO.class);
	// property constants
	public static final String ID_OBMINOR = "idObminor";
	public static final String NO_KP_BARU = "noKpBaru";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String JENIS_KP = "jenisKp";
	public static final String NO_KP_LAIN = "noKpLain";
	public static final String NAMA_PENJAGA = "namaPenjaga";
	public static final String JANTINA = "jantina";
	public static final String JENIS_AGAMA = "jenisAgama";
	public static final String JENIS_WARGA = "jenisWarga";
	public static final String UMUR = "umur";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String BANDAR = "bandar";
	public static final String ID_NEGERI = "idNegeri";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkpenjaga transientInstance) {
		log.debug("saving Tblppkpenjaga instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkpenjaga persistentInstance) {
		log.debug("deleting Tblppkpenjaga instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkpenjaga findById(java.lang.Long id) {
		log.debug("getting Tblppkpenjaga instance with id: " + id);
		try {
			Tblppkpenjaga instance = (Tblppkpenjaga) getSession().get(
					"ekptg.model.entities.Tblppkpenjaga", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkpenjaga instance) {
		log.debug("finding Tblppkpenjaga instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkpenjaga").add(
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
		log.debug("finding Tblppkpenjaga instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkpenjaga as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdObminor(Object idObminor) {
		return findByProperty(ID_OBMINOR, idObminor);
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

	public List findByNamaPenjaga(Object namaPenjaga) {
		return findByProperty(NAMA_PENJAGA, namaPenjaga);
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

	public List findByUmur(Object umur) {
		return findByProperty(UMUR, umur);
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

	public List findByBandar(Object bandar) {
		return findByProperty(BANDAR, bandar);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkpenjaga instances");
		try {
			String queryString = "from Tblppkpenjaga";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkpenjaga merge(Tblppkpenjaga detachedInstance) {
		log.debug("merging Tblppkpenjaga instance");
		try {
			Tblppkpenjaga result = (Tblppkpenjaga) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkpenjaga instance) {
		log.debug("attaching dirty Tblppkpenjaga instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkpenjaga instance) {
		log.debug("attaching clean Tblppkpenjaga instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
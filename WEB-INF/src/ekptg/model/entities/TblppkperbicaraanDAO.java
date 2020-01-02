package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkperbicaraan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkperbicaraan
 * @author MyEclipse Persistence Tools
 */

public class TblppkperbicaraanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkperbicaraanDAO.class);
	// property constants
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_UNITPSK = "idUnitpsk";
	public static final String TEMPAT_BICARA = "tempatBicara";
	public static final String BIL_BICARA = "bilBicara";
	public static final String ALAMAT_BICARA1 = "alamatBicara1";
	public static final String ALAMAT_BICARA2 = "alamatBicara2";
	public static final String ALAMAT_BICARA3 = "alamatBicara3";
	public static final String BANDAR = "bandar";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERIBICARA = "idNegeribicara";
	public static final String PEG_PENGENDALI = "pegPengendali";
	public static final String JENIS_KEPUTUSAN = "jenisKeputusan";
	public static final String CATATAN = "catatan";
	public static final String SEBAB_TANGGUH = "sebabTangguh";
	public static final String SEBAB_BATAL = "sebabBatal";
	public static final String KEPUTUSAN_MAHKAMAH = "keputusanMahkamah";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkperbicaraan transientInstance) {
		log.debug("saving Tblppkperbicaraan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkperbicaraan persistentInstance) {
		log.debug("deleting Tblppkperbicaraan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkperbicaraan findById(java.lang.Long id) {
		log.debug("getting Tblppkperbicaraan instance with id: " + id);
		try {
			Tblppkperbicaraan instance = (Tblppkperbicaraan) getSession().get(
					"ekptg.model.entities.Tblppkperbicaraan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkperbicaraan instance) {
		log.debug("finding Tblppkperbicaraan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkperbicaraan").add(
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
		log.debug("finding Tblppkperbicaraan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkperbicaraan as model where model."
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

	public List findByIdUnitpsk(Object idUnitpsk) {
		return findByProperty(ID_UNITPSK, idUnitpsk);
	}

	public List findByTempatBicara(Object tempatBicara) {
		return findByProperty(TEMPAT_BICARA, tempatBicara);
	}

	public List findByBilBicara(Object bilBicara) {
		return findByProperty(BIL_BICARA, bilBicara);
	}

	public List findByAlamatBicara1(Object alamatBicara1) {
		return findByProperty(ALAMAT_BICARA1, alamatBicara1);
	}

	public List findByAlamatBicara2(Object alamatBicara2) {
		return findByProperty(ALAMAT_BICARA2, alamatBicara2);
	}

	public List findByAlamatBicara3(Object alamatBicara3) {
		return findByProperty(ALAMAT_BICARA3, alamatBicara3);
	}

	public List findByBandar(Object bandar) {
		return findByProperty(BANDAR, bandar);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByIdNegeribicara(Object idNegeribicara) {
		return findByProperty(ID_NEGERIBICARA, idNegeribicara);
	}

	public List findByPegPengendali(Object pegPengendali) {
		return findByProperty(PEG_PENGENDALI, pegPengendali);
	}

	public List findByJenisKeputusan(Object jenisKeputusan) {
		return findByProperty(JENIS_KEPUTUSAN, jenisKeputusan);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findBySebabTangguh(Object sebabTangguh) {
		return findByProperty(SEBAB_TANGGUH, sebabTangguh);
	}

	public List findBySebabBatal(Object sebabBatal) {
		return findByProperty(SEBAB_BATAL, sebabBatal);
	}

	public List findByKeputusanMahkamah(Object keputusanMahkamah) {
		return findByProperty(KEPUTUSAN_MAHKAMAH, keputusanMahkamah);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkperbicaraan instances");
		try {
			String queryString = "from Tblppkperbicaraan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkperbicaraan merge(Tblppkperbicaraan detachedInstance) {
		log.debug("merging Tblppkperbicaraan instance");
		try {
			Tblppkperbicaraan result = (Tblppkperbicaraan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkperbicaraan instance) {
		log.debug("attaching dirty Tblppkperbicaraan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkperbicaraan instance) {
		log.debug("attaching clean Tblppkperbicaraan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
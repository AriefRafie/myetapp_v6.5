package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfddokumen entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpfddokumen
 * @author MyEclipse Persistence Tools
 */

public class TblpfddokumenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpfddokumenDAO.class);
	// property constants
	public static final String ID_FAIL = "idFail";
	public static final String ID_SUBJAKET = "idSubjaket";
	public static final String NAMA_PENGIRIM = "namaPengirim";
	public static final String NO_RUJUKAN_DOKUMEN = "noRujukanDokumen";
	public static final String BIL_MINIT_DOKUMEN = "bilMinitDokumen";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_JENISDOKUMEN = "idJenisdokumen";
	public static final String NAMA_PENERIMA = "namaPenerima";
	public static final String NAMA_PEGAWAI = "namaPegawai";
	public static final String ID_DB = "idDb";
	public static final String FLAG_DOKUMEN = "flagDokumen";

	public void save(Tblpfddokumen transientInstance) {
		log.debug("saving Tblpfddokumen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfddokumen persistentInstance) {
		log.debug("deleting Tblpfddokumen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfddokumen findById(java.lang.Long id) {
		log.debug("getting Tblpfddokumen instance with id: " + id);
		try {
			Tblpfddokumen instance = (Tblpfddokumen) getSession().get(
					"ekptg.model.entities.Tblpfddokumen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfddokumen instance) {
		log.debug("finding Tblpfddokumen instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfddokumen").add(
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
		log.debug("finding Tblpfddokumen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpfddokumen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findByIdSubjaket(Object idSubjaket) {
		return findByProperty(ID_SUBJAKET, idSubjaket);
	}

	public List findByNamaPengirim(Object namaPengirim) {
		return findByProperty(NAMA_PENGIRIM, namaPengirim);
	}

	public List findByNoRujukanDokumen(Object noRujukanDokumen) {
		return findByProperty(NO_RUJUKAN_DOKUMEN, noRujukanDokumen);
	}

	public List findByBilMinitDokumen(Object bilMinitDokumen) {
		return findByProperty(BIL_MINIT_DOKUMEN, bilMinitDokumen);
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

	public List findByIdJenisdokumen(Object idJenisdokumen) {
		return findByProperty(ID_JENISDOKUMEN, idJenisdokumen);
	}

	public List findByNamaPenerima(Object namaPenerima) {
		return findByProperty(NAMA_PENERIMA, namaPenerima);
	}

	public List findByNamaPegawai(Object namaPegawai) {
		return findByProperty(NAMA_PEGAWAI, namaPegawai);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findByFlagDokumen(Object flagDokumen) {
		return findByProperty(FLAG_DOKUMEN, flagDokumen);
	}

	public List findAll() {
		log.debug("finding all Tblpfddokumen instances");
		try {
			String queryString = "from Tblpfddokumen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfddokumen merge(Tblpfddokumen detachedInstance) {
		log.debug("merging Tblpfddokumen instance");
		try {
			Tblpfddokumen result = (Tblpfddokumen) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfddokumen instance) {
		log.debug("attaching dirty Tblpfddokumen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfddokumen instance) {
		log.debug("attaching clean Tblpfddokumen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
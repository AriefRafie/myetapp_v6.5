package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujppkunit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujppkunit
 * @author MyEclipse Persistence Tools
 */

public class TblrujppkunitDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujppkunitDAO.class);
	// property constants
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_JKPTG = "idJkptg";
	public static final String NAMA_PEJABAT = "namaPejabat";
	public static final String KETERANGAN_UNIT_PSK = "keteranganUnitPsk";
	public static final String NAMA_PEGAWAI = "namaPegawai";
	public static final String JAWATAN = "jawatan";
	public static final String STATUS_PEG = "statusPeg";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String ALAMAT4 = "alamat4";
	public static final String POSKOD = "poskod";
	public static final String NO_TEL = "noTel";
	public static final String NO_TEL_SAMBUNGAN = "noTelSambungan";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujppkunit transientInstance) {
		log.debug("saving Tblrujppkunit instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujppkunit persistentInstance) {
		log.debug("deleting Tblrujppkunit instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujppkunit findById(java.lang.Long id) {
		log.debug("getting Tblrujppkunit instance with id: " + id);
		try {
			Tblrujppkunit instance = (Tblrujppkunit) getSession().get(
					"ekptg.model.entities.Tblrujppkunit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujppkunit instance) {
		log.debug("finding Tblrujppkunit instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujppkunit").add(
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
		log.debug("finding Tblrujppkunit instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujppkunit as model where model."
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

	public List findByIdJkptg(Object idJkptg) {
		return findByProperty(ID_JKPTG, idJkptg);
	}

	public List findByNamaPejabat(Object namaPejabat) {
		return findByProperty(NAMA_PEJABAT, namaPejabat);
	}

	public List findByKeteranganUnitPsk(Object keteranganUnitPsk) {
		return findByProperty(KETERANGAN_UNIT_PSK, keteranganUnitPsk);
	}

	public List findByNamaPegawai(Object namaPegawai) {
		return findByProperty(NAMA_PEGAWAI, namaPegawai);
	}

	public List findByJawatan(Object jawatan) {
		return findByProperty(JAWATAN, jawatan);
	}

	public List findByStatusPeg(Object statusPeg) {
		return findByProperty(STATUS_PEG, statusPeg);
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

	public List findByAlamat4(Object alamat4) {
		return findByProperty(ALAMAT4, alamat4);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByNoTel(Object noTel) {
		return findByProperty(NO_TEL, noTel);
	}

	public List findByNoTelSambungan(Object noTelSambungan) {
		return findByProperty(NO_TEL_SAMBUNGAN, noTelSambungan);
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
		log.debug("finding all Tblrujppkunit instances");
		try {
			String queryString = "from Tblrujppkunit";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujppkunit merge(Tblrujppkunit detachedInstance) {
		log.debug("merging Tblrujppkunit instance");
		try {
			Tblrujppkunit result = (Tblrujppkunit) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujppkunit instance) {
		log.debug("attaching dirty Tblrujppkunit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujppkunit instance) {
		log.debug("attaching clean Tblrujppkunit instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
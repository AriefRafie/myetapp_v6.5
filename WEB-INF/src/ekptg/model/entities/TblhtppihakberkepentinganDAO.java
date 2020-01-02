package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppihakberkepentingan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblhtppihakberkepentingan
 * @author MyEclipse Persistence Tools
 */

public class TblhtppihakberkepentinganDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblhtppihakberkepentinganDAO.class);
	// property constants
	public static final String ID_HAKMILIKURUSAN = "idHakmilikurusan";
	public static final String ID_JENISNOPB = "idJenisnopb";
	public static final String ID_JENISPB = "idJenispb";
	public static final String NO_RUJUKAN = "noRujukan";
	public static final String NAMA = "nama";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_TEL = "noTel";
	public static final String NO_FAX = "noFax";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblhtppihakberkepentingan transientInstance) {
		log.debug("saving Tblhtppihakberkepentingan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppihakberkepentingan persistentInstance) {
		log.debug("deleting Tblhtppihakberkepentingan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppihakberkepentingan findById(java.lang.Long id) {
		log.debug("getting Tblhtppihakberkepentingan instance with id: " + id);
		try {
			Tblhtppihakberkepentingan instance = (Tblhtppihakberkepentingan) getSession()
					.get("ekptg.model.entities.Tblhtppihakberkepentingan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppihakberkepentingan instance) {
		log.debug("finding Tblhtppihakberkepentingan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppihakberkepentingan").add(
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
		log.debug("finding Tblhtppihakberkepentingan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppihakberkepentingan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdHakmilikurusan(Object idHakmilikurusan) {
		return findByProperty(ID_HAKMILIKURUSAN, idHakmilikurusan);
	}

	public List findByIdJenisnopb(Object idJenisnopb) {
		return findByProperty(ID_JENISNOPB, idJenisnopb);
	}

	public List findByIdJenispb(Object idJenispb) {
		return findByProperty(ID_JENISPB, idJenispb);
	}

	public List findByNoRujukan(Object noRujukan) {
		return findByProperty(NO_RUJUKAN, noRujukan);
	}

	public List findByNama(Object nama) {
		return findByProperty(NAMA, nama);
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

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByNoTel(Object noTel) {
		return findByProperty(NO_TEL, noTel);
	}

	public List findByNoFax(Object noFax) {
		return findByProperty(NO_FAX, noFax);
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
		log.debug("finding all Tblhtppihakberkepentingan instances");
		try {
			String queryString = "from Tblhtppihakberkepentingan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppihakberkepentingan merge(
			Tblhtppihakberkepentingan detachedInstance) {
		log.debug("merging Tblhtppihakberkepentingan instance");
		try {
			Tblhtppihakberkepentingan result = (Tblhtppihakberkepentingan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppihakberkepentingan instance) {
		log.debug("attaching dirty Tblhtppihakberkepentingan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppihakberkepentingan instance) {
		log.debug("attaching clean Tblhtppihakberkepentingan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptbebanan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptbebanan
 * @author MyEclipse Persistence Tools
 */

public class TblpptbebananDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptbebananDAO.class);
	// property constants
	public static final String JILID = "jilid";
	public static final String FOLIO = "folio";
	public static final String ID_KODBEBANAN = "idKodbebanan";
	public static final String ID_JENISNOPB = "idJenisnopb";
	public static final String NO_PB = "noPb";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String NAMA = "nama";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptbebanan transientInstance) {
		log.debug("saving Tblpptbebanan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptbebanan persistentInstance) {
		log.debug("deleting Tblpptbebanan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptbebanan findById(java.lang.Long id) {
		log.debug("getting Tblpptbebanan instance with id: " + id);
		try {
			Tblpptbebanan instance = (Tblpptbebanan) getSession().get(
					"ekptg.model.entities.Tblpptbebanan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptbebanan instance) {
		log.debug("finding Tblpptbebanan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptbebanan").add(
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
		log.debug("finding Tblpptbebanan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptbebanan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJilid(Object jilid) {
		return findByProperty(JILID, jilid);
	}

	public List findByFolio(Object folio) {
		return findByProperty(FOLIO, folio);
	}

	public List findByIdKodbebanan(Object idKodbebanan) {
		return findByProperty(ID_KODBEBANAN, idKodbebanan);
	}

	public List findByIdJenisnopb(Object idJenisnopb) {
		return findByProperty(ID_JENISNOPB, idJenisnopb);
	}

	public List findByNoPb(Object noPb) {
		return findByProperty(NO_PB, noPb);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
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

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
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
		log.debug("finding all Tblpptbebanan instances");
		try {
			String queryString = "from Tblpptbebanan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptbebanan merge(Tblpptbebanan detachedInstance) {
		log.debug("merging Tblpptbebanan instance");
		try {
			Tblpptbebanan result = (Tblpptbebanan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptbebanan instance) {
		log.debug("attaching dirty Tblpptbebanan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptbebanan instance) {
		log.debug("attaching clean Tblpptbebanan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
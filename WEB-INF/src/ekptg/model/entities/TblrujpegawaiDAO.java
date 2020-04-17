package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;


/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujpegawai entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujpegawai
 * @author MyEclipse Persistence Tools
 */

public class TblrujpegawaiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujpegawaiDAO.class);
	// property constants
	public static final String ID_NEGERI = "idNegeri";
        public static final String ID_MUKIM = "idMukim";
	public static final String NAMA_PEGAWAI = "namaPegawai";
	public static final String NO_PEKERJA = "noPekerja";
	public static final String NO_KP = "noKp";
	public static final String JAWATAN = "jawatan";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String NO_TEL_PEJABAT = "noTelPejabat";
	public static final String NO_TEL_RUMAH = "noTelRumah";
	public static final String NO_TEL_BIMBIT = "noTelBimbit";
        public static final String EMEL = "emel";


	public void save(Tblrujpegawai transientInstance) {
		log.debug("saving Tblrujpegawai instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujpegawai persistentInstance) {
		log.debug("deleting Tblrujpegawai instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujpegawai findById(java.lang.Long id) {
		log.debug("getting Tblrujpegawai instance with id: " + id);
		try {
			Tblrujpegawai instance = (Tblrujpegawai) getSession().get(
					"ekptg.model.entities.Tblrujpegawai", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujpegawai instance) {
		log.debug("finding Tblrujpegawai instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujpegawai").add(
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
		log.debug("finding Tblrujpegawai instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujpegawai as model where model."
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
        
        public List findByIdMukim(Object idMukim) {
                return findByProperty(NAMA_PEGAWAI, idMukim);
        }

	public List findByNamaPegawai(Object namaPegawai) {
		return findByProperty(NAMA_PEGAWAI, namaPegawai);
	}

	public List findByNoPekerja(Object noPekerja) {
		return findByProperty(NO_PEKERJA, noPekerja);
	}

	public List findByNoKp(Object noKp) {
		return findByProperty(NO_KP, noKp);
	}

	public List findByJawatan(Object jawatan) {
		return findByProperty(JAWATAN, jawatan);
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

	public List findByNoTelPejabat(Object noTelPejabat) {
		return findByProperty(NO_TEL_PEJABAT, noTelPejabat);
	}

	public List findByNoTelRumah(Object noTelRumah) {
		return findByProperty(NO_TEL_RUMAH, noTelRumah);
	}

	public List findByNoTelBimbit(Object noTelBimbit) {
		return findByProperty(NO_TEL_BIMBIT, noTelBimbit);
	}
        public List findByEmel(Object emel) {
                return findByProperty(EMEL, emel);
        }

	public List findAll() {
		log.debug("finding all Tblrujpegawai instances");
		try {
			String queryString = "from Tblrujpegawai";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujpegawai merge(Tblrujpegawai detachedInstance) {
		log.debug("merging Tblrujpegawai instance");
		try {
			Tblrujpegawai result = (Tblrujpegawai) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujpegawai instance) {
		log.debug("attaching dirty Tblrujpegawai instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujpegawai instance) {
		log.debug("attaching clean Tblrujpegawai instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
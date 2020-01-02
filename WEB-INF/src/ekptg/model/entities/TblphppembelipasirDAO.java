package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppembelipasir entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphppembelipasir
 * @author MyEclipse Persistence Tools
 */

public class TblphppembelipasirDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppembelipasirDAO.class);
	// property constants
	public static final String NAMA = "nama";
	public static final String ID_JENISPENGENALAN = "idJenispengenalan";
	public static final String NO_KP = "noKp";
	public static final String NO_KP_LAMA = "noKpLama";
	public static final String ID_WARNAKP = "idWarnakp";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_BANDAR = "idBandar";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_TEL_RUMAH = "noTelRumah";
	public static final String NO_TEL_PEJABAT = "noTelPejabat";
	public static final String EXT_TEL = "extTel";
	public static final String NO_FAX = "noFax";
	public static final String NO_TEL_BIMBIT = "noTelBimbit";
	public static final String EMEL = "emel";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphppembelipasir transientInstance) {
		log.debug("saving Tblphppembelipasir instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppembelipasir persistentInstance) {
		log.debug("deleting Tblphppembelipasir instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppembelipasir findById(java.lang.Long id) {
		log.debug("getting Tblphppembelipasir instance with id: " + id);
		try {
			Tblphppembelipasir instance = (Tblphppembelipasir) getSession()
					.get("ekptg.model.entities.Tblphppembelipasir", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppembelipasir instance) {
		log.debug("finding Tblphppembelipasir instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppembelipasir").add(
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
		log.debug("finding Tblphppembelipasir instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppembelipasir as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNama(Object nama) {
		return findByProperty(NAMA, nama);
	}

	public List findByIdJenispengenalan(Object idJenispengenalan) {
		return findByProperty(ID_JENISPENGENALAN, idJenispengenalan);
	}

	public List findByNoKp(Object noKp) {
		return findByProperty(NO_KP, noKp);
	}

	public List findByNoKpLama(Object noKpLama) {
		return findByProperty(NO_KP_LAMA, noKpLama);
	}

	public List findByIdWarnakp(Object idWarnakp) {
		return findByProperty(ID_WARNAKP, idWarnakp);
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

	public List findByIdBandar(Object idBandar) {
		return findByProperty(ID_BANDAR, idBandar);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByNoTelRumah(Object noTelRumah) {
		return findByProperty(NO_TEL_RUMAH, noTelRumah);
	}

	public List findByNoTelPejabat(Object noTelPejabat) {
		return findByProperty(NO_TEL_PEJABAT, noTelPejabat);
	}

	public List findByExtTel(Object extTel) {
		return findByProperty(EXT_TEL, extTel);
	}

	public List findByNoFax(Object noFax) {
		return findByProperty(NO_FAX, noFax);
	}

	public List findByNoTelBimbit(Object noTelBimbit) {
		return findByProperty(NO_TEL_BIMBIT, noTelBimbit);
	}

	public List findByEmel(Object emel) {
		return findByProperty(EMEL, emel);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphppembelipasir instances");
		try {
			String queryString = "from Tblphppembelipasir";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppembelipasir merge(Tblphppembelipasir detachedInstance) {
		log.debug("merging Tblphppembelipasir instance");
		try {
			Tblphppembelipasir result = (Tblphppembelipasir) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppembelipasir instance) {
		log.debug("attaching dirty Tblphppembelipasir instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppembelipasir instance) {
		log.debug("attaching clean Tblphppembelipasir instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
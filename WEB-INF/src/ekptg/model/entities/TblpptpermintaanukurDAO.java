package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptpermintaanukur entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptpermintaanukur
 * @author MyEclipse Persistence Tools
 */

public class TblpptpermintaanukurDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpptpermintaanukurDAO.class);
	// property constants
	public static final String NO_RUJUKAN_PTG = "noRujukanPtg";
	public static final String NO_PELAN = "noPelan";
	public static final String NO_PU = "noPu";
	public static final String NO_SP = "noSp";
	public static final String NO_PA = "noPa";
	public static final String CATATAN = "catatan";
	public static final String NAMA_PEGAWAI = "namaPegawai";
	public static final String JENIS_PELARASAN = "jenisPelarasan";
	public static final String AMAUN_PU = "amaunPu";
	public static final String ID_UNITLUASPU = "idUnitluaspu";
	public static final String LUAS_PU = "luasPu";
	public static final String FAEDAH_SEBELUM = "faedahSebelum";
	public static final String FAEDAH_SELEPAS = "faedahSelepas";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptpermintaanukur transientInstance) {
		log.debug("saving Tblpptpermintaanukur instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptpermintaanukur persistentInstance) {
		log.debug("deleting Tblpptpermintaanukur instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptpermintaanukur findById(java.lang.Long id) {
		log.debug("getting Tblpptpermintaanukur instance with id: " + id);
		try {
			Tblpptpermintaanukur instance = (Tblpptpermintaanukur) getSession()
					.get("ekptg.model.entities.Tblpptpermintaanukur", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptpermintaanukur instance) {
		log.debug("finding Tblpptpermintaanukur instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptpermintaanukur").add(
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
		log.debug("finding Tblpptpermintaanukur instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptpermintaanukur as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoRujukanPtg(Object noRujukanPtg) {
		return findByProperty(NO_RUJUKAN_PTG, noRujukanPtg);
	}

	public List findByNoPelan(Object noPelan) {
		return findByProperty(NO_PELAN, noPelan);
	}

	public List findByNoPu(Object noPu) {
		return findByProperty(NO_PU, noPu);
	}

	public List findByNoSp(Object noSp) {
		return findByProperty(NO_SP, noSp);
	}

	public List findByNoPa(Object noPa) {
		return findByProperty(NO_PA, noPa);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByNamaPegawai(Object namaPegawai) {
		return findByProperty(NAMA_PEGAWAI, namaPegawai);
	}

	public List findByJenisPelarasan(Object jenisPelarasan) {
		return findByProperty(JENIS_PELARASAN, jenisPelarasan);
	}

	public List findByAmaunPu(Object amaunPu) {
		return findByProperty(AMAUN_PU, amaunPu);
	}

	public List findByIdUnitluaspu(Object idUnitluaspu) {
		return findByProperty(ID_UNITLUASPU, idUnitluaspu);
	}

	public List findByLuasPu(Object luasPu) {
		return findByProperty(LUAS_PU, luasPu);
	}

	public List findByFaedahSebelum(Object faedahSebelum) {
		return findByProperty(FAEDAH_SEBELUM, faedahSebelum);
	}

	public List findByFaedahSelepas(Object faedahSelepas) {
		return findByProperty(FAEDAH_SELEPAS, faedahSelepas);
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
		log.debug("finding all Tblpptpermintaanukur instances");
		try {
			String queryString = "from Tblpptpermintaanukur";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptpermintaanukur merge(Tblpptpermintaanukur detachedInstance) {
		log.debug("merging Tblpptpermintaanukur instance");
		try {
			Tblpptpermintaanukur result = (Tblpptpermintaanukur) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptpermintaanukur instance) {
		log.debug("attaching dirty Tblpptpermintaanukur instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptpermintaanukur instance) {
		log.debug("attaching clean Tblpptpermintaanukur instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
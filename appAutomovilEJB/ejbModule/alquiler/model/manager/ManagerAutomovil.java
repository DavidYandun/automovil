package alquiler.model.manager;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import alquiler.model.entities.Automovil;

/**
 * Session Bean implementation class ManagerAutomovil
 */
@Stateless
@LocalBean
public class ManagerAutomovil {
	@PersistenceContext(unitName = "alquilerDS")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerAutomovil() {
	}

	public void crearLista() {
		Automovil a = new Automovil();
		a.setAlquilado(false);
		a.setAnio(2012);
		a.setColor("BLANCO");
		a.setGasolina(4);
		a.setMarca("TOYOTA");
		a.setPlaca("IBA3244");
		em.persist(a);// guardamos el nuevo automovil
		Automovil b = new Automovil();
		b.setAlquilado(true);
		b.setAnio(2010);
		b.setColor("GRIS");
		b.setGasolina(8);
		b.setMarca("CHEVROLET");
		b.setPlaca("PHQ1276");
		em.persist(b);
	}

	public void agregarAutomovil(String placa, int anio, String color, String marca) throws Exception {
		if (marca == null || marca.length() == 0)
			throw new Exception("Debe especificar la marca.");
		Automovil a = new Automovil();
		a.setAlquilado(false);
		a.setAnio(anio);
		a.setColor(color);
		a.setGasolina(0);
		a.setMarca(marca);
		a.setPlaca(placa);
		em.persist(a);
	}

	public Automovil findAutomovil(String placa) throws Exception {
		Automovil a = em.find(Automovil.class, placa);
		return a;
	}

	public void alquilarAutomovil(String placa) throws Exception {
		// buscamos el automovil:
		Automovil a = findAutomovil(placa);
		if (a == null)
			throw new Exception("No existe el automovil especificado.");
		// verificamos si aun no ha sido alquilado:
		if (a.getAlquilado())
			throw new Exception("El automovil ya fue alquilado.");
		// caso contrario lo alquilamos y actualizamos la informacion:
		a.setAlquilado(true);
		em.merge(a);
	}
	
	public void desalojarAutomovil(String placa) throws Exception {
		// buscamos el automovil:
		Automovil a = findAutomovil(placa);
		if (a == null)
			throw new Exception("No existe el automovil especificado.");
		// verificamos si ya ha sido alquilado:
		if (!a.getAlquilado())
			throw new Exception("El automovil ya está disponible.");
		// caso contrario lo desalojamos y actualizamos la informacion:
		a.setAlquilado(false);
		em.merge(a);
	}

	public void eliminarAutomovil(String placa) throws Exception {
		// buscamos el automovil:
		Automovil a = findAutomovil(placa);
		if (a == null)
			throw new Exception("No existe el automovil especificado.");
		// lo eliminamos:
		em.remove(a);
	}

	public void editarAutomovil(String placa, String color, int gasolina) throws Exception {
		// buscamos el automovil:
		Automovil a = findAutomovil(placa);
		if (a == null)
			throw new Exception("No existe el automovil especificado.");
		// actualizamos ciertos campos especificados en los parametros del metodo:
		a.setColor(color);
		a.setGasolina(gasolina);
		em.merge(a);
	}

	public List<Automovil> findAllAutomoviles() {
		Query q;
		List<Automovil> listado;
		String sentenciaSQL;
		sentenciaSQL = "SELECT o FROM Automovil o ORDER BY o.placa";
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}

	public int sumaGasolina() {
		List<Automovil> lista = findAllAutomoviles();
		int suma = 0;
		for (Automovil a : lista) {
			suma += a.getGasolina();
		}
		return suma;
	}
}

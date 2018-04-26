package alquiler.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import alquiler.model.entities.Automovil;
import alquiler.model.manager.ManagerAutomovil;
import alquiler.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerAutomovil {
	private String placa;
	private int anio;
	private String color;
	private String marca;
	private List<Automovil> lista;
	private int gasolina;
	private int totalGasolina;
	@EJB
	private ManagerAutomovil managerAutomovil;

	@PostConstruct
	public void iniciar() {
		lista = managerAutomovil.findAllAutomoviles();
		totalGasolina = managerAutomovil.sumaGasolina();
		anio = 2017;
	}

	public void actionListenerAgregar() {
		try {
			managerAutomovil.agregarAutomovil(placa, anio, color, marca);
			lista = managerAutomovil.findAllAutomoviles();
			totalGasolina = managerAutomovil.sumaGasolina();
			JSFUtil.crearMensajeInfo("Automóvil registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		placa = "";
		anio = 2016;
		color = "";
		marca = "";
	}

	public void actionListenerReset() {
		managerAutomovil.crearLista();
		lista = managerAutomovil.findAllAutomoviles();
		totalGasolina = managerAutomovil.sumaGasolina();
	}

	public void actionListenerAlquilar() {
		try {
			managerAutomovil.alquilarAutomovil(placa);
			lista = managerAutomovil.findAllAutomoviles();
			JSFUtil.crearMensajeInfo("Alquiler realizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerDesalojar() {
		try {
			managerAutomovil.desalojarAutomovil(placa);
			lista = managerAutomovil.findAllAutomoviles();
			JSFUtil.crearMensajeInfo("Desalojo realizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerAlquilarFila(Automovil automovil) {
		try {
			managerAutomovil.alquilarAutomovil(automovil.getPlaca());
			lista = managerAutomovil.findAllAutomoviles();
			JSFUtil.crearMensajeInfo("Alquiler realizado con éxito.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerDesalojarFila(Automovil automovil) {
		try {
			managerAutomovil.desalojarAutomovil(automovil.getPlaca());
			lista = managerAutomovil.findAllAutomoviles();
			JSFUtil.crearMensajeInfo("Desalojo realizado con éxito.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminar(String placa) {
		try {
			managerAutomovil.eliminarAutomovil(placa);
			lista = managerAutomovil.findAllAutomoviles();
			totalGasolina = managerAutomovil.sumaGasolina();
			JSFUtil.crearMensajeInfo("Automóvil de placa " + placa + " eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargar(Automovil automovil) {
		placa = automovil.getPlaca();
		color = automovil.getColor();
		gasolina = automovil.getGasolina();
	}

	public void actionListenerActualizar() {

		try {
			managerAutomovil.editarAutomovil(placa, color, gasolina);
			lista = managerAutomovil.findAllAutomoviles();
			totalGasolina = managerAutomovil.sumaGasolina();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public List<Automovil> getLista() {
		return lista;
	}

	public void setLista(List<Automovil> lista) {
		this.lista = lista;
	}

	public int getGasolina() {
		return gasolina;
	}

	public void setGasolina(int gasolina) {
		this.gasolina = gasolina;
	}

	public int getTotalGasolina() {
		return totalGasolina;
	}

	public void setTotalGasolina(int totalGasolina) {
		this.totalGasolina = totalGasolina;
	}
}

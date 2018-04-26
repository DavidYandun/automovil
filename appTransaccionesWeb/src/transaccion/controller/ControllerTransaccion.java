package transaccion.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import transaccion.model.entities.FacturaCab;
import transaccion.model.manager.ManagerFacturas;

@SessionScoped
@ManagedBean
public class ControllerTransaccion {
	
	@EJB
	private ManagerFacturas managerFacturas;
	private int codigoProducto;
	private int cantidad;
	private FacturaCab fcTmp;
	private String cedulaCliente;
	private String numeroFactura;
	
	

	public void CrearFactura() {
		managerFacturas.testFactura();
	}
	//metodo que solo guarda el detalle en memoria
	public void actionListenerAgregarDetalle() {
		fcTmp=managerFacturas.agregarDetalle(fcTmp, codigoProducto, cantidad);
	}
	
	
	public void actionListenerGuardarFactura() {
		try {
			fcTmp=managerFacturas.guardarFactura(fcTmp, cedulaCliente, numeroFactura);
			System.out.println("Factura guardada: "+fcTmp.getNumeroFactura());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public FacturaCab getFcTmp() {
		return fcTmp;
	}
	public void setFcTmp(FacturaCab fcTmp) {
		this.fcTmp = fcTmp;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	
	
}
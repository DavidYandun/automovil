package transaccion.model.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import transaccion.model.entities.Cliente;
import transaccion.model.entities.FacturaCab;
import transaccion.model.entities.FacturaDet;
import transaccion.model.entities.Producto;

/**
 * Session Bean implementation class ManagerFacturas
 */
@Stateless
@LocalBean
public class ManagerFacturas {
	@PersistenceContext(unitName = "facturacionDS")
	private EntityManager em;

	public ManagerFacturas() {
		// TODO Auto-generated constructor stub
	}

	public void testFactura() {
		// de acuerdo a las relaciones, primero buscamos la info del cliente
		Cliente cliente;
		cliente = em.find(Cliente.class, "2280000824");
		// genero cabecera
		FacturaCab fc = new FacturaCab();
		fc.setBaseCero(new BigDecimal(100));
		fc.setFechaEmision(new Date());
		fc.setCliente(cliente);
		fc.setNumeroFactura("099-099-09999999");
		fc.setSubtotal(new BigDecimal(150));
		fc.setValorIva(new BigDecimal(6));
		fc.setTotal(new BigDecimal(156));

		// genero detalles
		FacturaDet fd;
		fd = new FacturaDet();
		fd.setCantidad(10);
		Producto producto = em.find(Producto.class, 102534);
		fd.setProducto(producto);
		fd.setPrecioUnitarioVenta(producto.getPrecioUnitario());
		fd.setNumeroFacturaDet(999);

		fd.setFacturaCab(fc);
		fc.setFacturaDets(new ArrayList<FacturaDet>());

		fc.addFacturaDet(fd);// relacion bidireccional

		// segundo detalle
		fd = new FacturaDet();
		fd.setCantidad(2);
		Producto producto2 = em.find(Producto.class, 102534);
		fd.setProducto(producto2);
		fd.setPrecioUnitarioVenta(producto.getPrecioUnitario());
		fd.setNumeroFacturaDet(1000);
		fd.setFacturaCab(fc);
		fc.addFacturaDet(fd);// relacion bidireccional

		em.persist(fc);
	}

	public FacturaCab agregarDetalle(FacturaCab fc, int codigoProducto, int cantidad) {
		if (fc == null) {// para la primera invocación del metodo
			fc = new FacturaCab();
			fc.setFacturaDets(new ArrayList<FacturaDet>());
		}
		// recuperar infomracion del producto
		Producto p = em.find(Producto.class, codigoProducto);
		FacturaDet df = new FacturaDet();
		// llenar datos de nuevo detalle
		df.setProducto(p);
		df.setCantidad(cantidad);
		df.setPrecioUnitarioVenta(p.getPrecioUnitario());
		df.setFacturaCab(fc);// asociacion al objeto padre
		return fc;
	}
	
	public FacturaCab guardarFactura(FacturaCab fc, String cedulaCliente, String numeroFactura) throws Exception{
		if(fc==null)
			throw new Exception("Se debe almacenar al menos un producto en la factura");
		if(cedulaCliente==null||cedulaCliente.length()==0)
			throw new Exception("Se debe ingresar la cedula");
		//buscamos la informacion del cliente
		Cliente cliente= em.find(Cliente.class, cedulaCliente);
		fc.setFechaEmision(new Date());
		fc.setCliente(cliente);
		fc.setBaseCero(new BigDecimal(10));
		fc.setSubtotal(new BigDecimal(2000));
		fc.setValorIva(new BigDecimal(120.34));
		fc.setTotal(new BigDecimal(3000));
		fc.setNumeroFactura(numeroFactura);
		em.persist(fc);
		return fc;
		
	}
	
}

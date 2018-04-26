package transaccion.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the evento database table.
 * 
 */
@Entity
@Table(name="evento")
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EVENTO_IDEVENTO_GENERATOR", sequenceName="SEQ_EVENTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVENTO_IDEVENTO_GENERATOR")
	@Column(name="id_evento", unique=true, nullable=false)
	private Integer idEvento;

	@Column(length=200)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_evento", nullable=false)
	private Date fechaEvento;

	@Column(name="id_usuario", nullable=false, length=20)
	private String idUsuario;

	@Column(nullable=false, length=100)
	private String metodo;

	public Evento() {
	}

	public Integer getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaEvento() {
		return this.fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMetodo() {
		return this.metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

}
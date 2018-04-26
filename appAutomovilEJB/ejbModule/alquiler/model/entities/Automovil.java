package alquiler.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the automovil database table.
 * 
 */
@Entity
@Table(name="automovil")
@NamedQuery(name="Automovil.findAll", query="SELECT a FROM Automovil a")
public class Automovil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String placa;

	private Boolean alquilado;

	private Integer anio;

	@Column(length=30)
	private String color;

	private Integer gasolina;

	@Column(length=50)
	private String marca;

	public Automovil() {
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Boolean getAlquilado() {
		return this.alquilado;
	}

	public void setAlquilado(Boolean alquilado) {
		this.alquilado = alquilado;
	}

	public Integer getAnio() {
		return this.anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getGasolina() {
		return this.gasolina;
	}

	public void setGasolina(Integer gasolina) {
		this.gasolina = gasolina;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
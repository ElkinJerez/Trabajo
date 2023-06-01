package entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="factura_items")
public class ItemaFactura implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer cnatidad;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handeler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private productos productos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCnatidad() {
		return cnatidad;
	}

	public void setCnatidad(Integer cnatidad) {
		this.cnatidad = cnatidad;
	}

	private static final long serialVersionUID = 1L;
}

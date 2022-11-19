package cat.itacademy.barcelonactiva.fedoraeva.anna.s04.t02.n01.S04T02N01FedoraevaAnna.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fruits")

public class Fruit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "quantitatQuilos")
	private int quantitatQuilos;
	

	public Fruit() {

	}

	public Fruit(String nombre, int quantitatQuilos) {
		this.nombre = nombre;
		this.quantitatQuilos = quantitatQuilos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getQuantitatQuilos() {
		return quantitatQuilos;
	}

	public void setQuantitatQuilos(int quantitatQuilos) {
		this.quantitatQuilos = quantitatQuilos;
	}

	@Override
	public String toString() {
		return "Fruit [id=" + id + ", nombre=" + nombre + ", quantitatQuilos=" + quantitatQuilos + "]";
	}



}

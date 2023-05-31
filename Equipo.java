import java.util.ArrayList;

public class Equipo {

	private int id;
	private String nombre;
	private ArrayList<Integer> plantilla = new ArrayList<Integer>();
	private int puntuacion;

	public Equipo() {
		super();
	}

	public Equipo(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Integer> getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(ArrayList<Integer> plantilla) {
		this.plantilla = plantilla;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", plantilla=" + plantilla + ", puntuacion=" + puntuacion + ", id= " + id + "]";
	}

//	Se añade un id a la lista de ids del equipo
	public void añadirId(int idJugador) {
		plantilla.add(idJugador);
	}
}
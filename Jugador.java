public class Jugador {


	private int id;
	private String nombre;
	private int dorsal;
	private String posicion;
	private int equipo_id;
	
	public Jugador() {
		super();
	}

	public Jugador(String nombre, int dorsal, String posicion) {
		super();
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.posicion = posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEquipo_id() {
		return equipo_id;
	}

	public void setEquipo_id(int equipo_id) {
		this.equipo_id = equipo_id;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", dorsal=" + dorsal + ", posicion=" + posicion
				+ ", equipo_id=" + equipo_id + "]";
	}	
	
}
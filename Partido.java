public class Partido {

	private int equipoA;
	private int puntuacionEquipoA;
	private int equipoB;
	private int puntuacionEquipoB;
	private int jornada;

	public Partido() {
		super();
	}

	public Partido(int equipoA, int equipoB, int jornada) {
		super();
		this.equipoA = equipoA;
		this.equipoB = equipoB;
		this.jornada = jornada;
	}

	public Partido(int equipoA, int equipoB) {
		super();
		this.equipoA = equipoA;
		this.equipoB = equipoB;
	}

	public int getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(int equipoA) {
		this.equipoA = equipoA;
	}

	public int getPuntuacionEquipoA() {
		return puntuacionEquipoA;
	}

	public void setPuntuacionEquipoA(int puntuacionEquipoA) {
		this.puntuacionEquipoA = puntuacionEquipoA;
	}

	public int getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(int equipoB) {
		this.equipoB = equipoB;
	}

	public int getPuntuacionEquipoB() {
		return puntuacionEquipoB;
	}

	public void setPuntuacionEquipoB(int puntuacionEquipoB) {
		this.puntuacionEquipoB = puntuacionEquipoB;
	}

	public int getJornada() {
		return jornada;
	}

	public void setJornada(int jornada) {
		this.jornada = jornada;
	}

	@Override
	public String toString() {
		return "Partido [equipoA=" + equipoA + ", equipoB=" + equipoB + ", jornada=" + jornada + "]\n";
	}

}
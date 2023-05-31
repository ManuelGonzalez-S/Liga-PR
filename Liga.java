import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Liga {
	private String nombre;
	private ArrayList<Equipo> grupos = new ArrayList<Equipo>();
	private ArrayList<Partido> enfrentamientos = new ArrayList<Partido>();
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	public Liga() {
		super();
	}

	public Liga(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Equipo> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<Equipo> grupos) {
		this.grupos = grupos;
	}

	public ArrayList<Partido> getEnfrentamientos() {
		return enfrentamientos;
	}

	public void setEnfrentamientos(ArrayList<Partido> enfrentamientos) {
		this.enfrentamientos = enfrentamientos;
	}

	@Override
	public String toString() {
		return "Liga [nombre=" + nombre + ", grupos=" + grupos + ", enfrentamientos=" + enfrentamientos + "]";
	}
	
	public String toStringGrupos() {
		return "" + grupos;
	}
	
	/*-EQUIPOS--------------------------------------------------------------------------------------------------------------*/
	
//	Muestra los equipos por nombre
	public void verEquipos() {
		for (Equipo miEquipo : grupos) {
			System.out.println(miEquipo.getNombre());
		}
	}
	
//	Añadir equipo al array
	public void addEquipo(String nombre) {

		try {
			grupos.add(new Equipo(nombre));
			grupos.get(grupos.size()-1).setId(grupos.size());
		} catch (Exception e) {
			System.out.println("No se pudo crear el equipo " + nombre);
		}
		
	}

//	Se borra el equipo por el nombre que recibe por parametro
	public void borrarEquipo(String nombreBusca) {

		int contador = 0;
		int longitud = grupos.size();
		boolean encontrado = false;

//		Busca mientras que no se haya encontrado el equipo
		do {
			
//			Si coinciden, sale del bucle
			if (nombreBusca.equals(grupos.get(contador).getNombre())) {
				grupos.remove(contador);
				encontrado = true;
			} else {
				contador++;
			}

		} while (contador < longitud && !encontrado);
	}
	
//	Metodo para modificar la informacion del equipo
	public void modificarEquipo() {
		int contador = 0;
		boolean encontrado = false;
		String nombreAct;
		String nombreNuevo;
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Introduce el nombre del equipo a modificar");
			nombreAct = sc.nextLine();
			System.out.println("Introduce el nombre nuevo para " + nombreAct);
			nombreNuevo = sc.nextLine();

//			Busca al equipo por el nombre y lo cambia
			do {
				if (nombreAct.equals(grupos.get(contador).getNombre())) {
					grupos.get(contador).setNombre(nombreNuevo);
					encontrado = true;
				} else {
					contador++;
				}

			} while (contador < grupos.size() && !encontrado);
			
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error");
		}
	}
	
	/*-JUGADORES--------------------------------------------------------------------------------------------------------------*/
	
	/*
	 * Aqui no hacemos try catch ya que el String acepta cualquier caracter
	 * 
	 * Realizamos la peticion tanto de nombre como posicion para la correspondiente
	 * agregacion del jugador
	 */
	public static String pedirNombre() {
		Scanner lector = new Scanner(System.in);
		System.out.println("Ingresa el nombre!");
		return lector.nextLine();
	}

	/* Aquí si agregamos try catch para que unicamente se introduzcan numeros */
	public static int pedirDorsal() {
		int dorsal = 0;
		boolean esCorrecto = true;
		do {
			try {
				esCorrecto = true;
				Scanner lector = new Scanner(System.in);
				System.out.println("Ingresa el dorsal!");
				dorsal = lector.nextInt();
				lector.nextLine();

			} catch (InputMismatchException e) {
				System.out.println("Ha introducido caracteres no numericos");
				esCorrecto = false;
			} catch (Exception e) {
				System.out.println("Ha habido algun error al introducir el dorsal");
			} finally {
			}
		} while (!esCorrecto);
		return dorsal;

	}

	public static String pedirPosicion() {
		String posicion;
		boolean esCorrecto = true;
		Scanner lector = new Scanner(System.in);
		do {
			esCorrecto = true;
			System.out.println("Ingresa la posicion!");
			posicion = lector.nextLine();
			if (posicion.length() == 0) {
				esCorrecto = false;
				System.out.println("Se olvidó de agregar la posición, vuelve a introducirla por favor!");
			}

		} while (!esCorrecto);
		return posicion;

	}

	/*
	 * Metodo que selecciona un jugador y un equipo, y los asocia a través de unos
	 * ids como si de una base de datos se tratara
	 */
	public void seleccionarJugadorEquipoYAsociar() {

		int id_equipo;
		String nombreEquipo;
		int eleccionUsuario;
		boolean valido = false;
		String nombreJugador;
		int dorsalJugador;
		Jugador miJugador;
		Equipo miEquipo;

		Scanner sc = new Scanner(System.in);

		try {

//			Valida que dentro de grupos y jugadores haya datos, si no no se ejecuta el código
			if (grupos.size() > 0 && jugadores.size() > 0) {

				System.out.println("Seleccione un jugador disponible: ");
				System.out.println("1.- Por nombre");
				System.out.println("2.- Por dorsal");

//				Pide un número mientras que no sea válido ( ni 1 ni 2)
				do {

					eleccionUsuario = sc.nextInt();
					sc.nextLine();

					if (eleccionUsuario < 1 || eleccionUsuario > 2) {
						System.out.println("Numero introducido no válido, porfavor compruebe el valor");
						System.out.println();
					}

				} while (eleccionUsuario < 1 || eleccionUsuario > 2);

//				Si selecciona el jugador por nombre, el código continua por aqui
				if (eleccionUsuario == 1) {

//					Se le muestran los nombres para que sea más facil para el usuario
					System.out.println("Introduzca el nombre del jugador");
					listarNombre();

//					Pide y busca el jugador por el nombre
					do {

						nombreJugador = sc.nextLine();

//						Método que busca al jugador, si lo encuentra devuelve un objeto
//						Si no, devuelve null y se vuelve a pedir el nombre del jugador
						miJugador = seleccionarJugadorPorNombre(nombreJugador);

					} while (miJugador == null);

//				En caso de querer seleccionar por dorsal, se ejecuta este código
				} else {

//					Se muestran los dorsales para que sea más facil para el usuario
					System.out.println("Seleccione el dorsal del jugador");
					listarDorsal();

//					Se pide el dorsal y se busca dentro de los disponibles
					do {

						dorsalJugador = sc.nextInt();
						sc.nextLine();

//						Se busca el jugador por dorsal
//						Si se encuentra, el método devuelve un objeto, sino devuelve null
						miJugador = seleccionarJugadorPorDorsal(dorsalJugador);

					} while (miJugador == null);
				}

//				Una vez que ya tenemos seleccionado el jugador, nos queda seleccionar el equipo al que queremos asociarlo
				System.out.println("Ahora seleccionemos el equipo");
				System.out.println("Quiere seleccionar el equipo por:");
				System.out.println("1.- ID");
				System.out.println("2.- Nombre");

//				Se pide la eleccion del usuario mientras que no sea ni 1 ni 2
				do {

					eleccionUsuario = sc.nextInt();
					sc.nextLine();

					if (eleccionUsuario < 1 || eleccionUsuario > 2) {
						System.out.println("Valor no introducido");
					}

				} while (eleccionUsuario < 1 || eleccionUsuario > 2);

//				Mostrar el nombre e id de los equipos para posteriori
				for (Equipo miGrupo : grupos) {
					System.out.println(miGrupo.getNombre() + ": " + miGrupo.getId());
				}

//				Si se decide elegir al equipo por id, se continua por aqui
				if (eleccionUsuario == 1) {

					do {

						System.out.println("Ingresa el id del equipo al que quieres asociar el jugador:");
						id_equipo = sc.nextInt();
						sc.nextLine();

//						Metodo que busca el id dentro de todos los grupos y devuelve un objeto del tipo equipo si es encontrado
//						De no ser así, devuelve valor null y se vuelve a pedir el id
						miEquipo = idDentroDeGrupos(id_equipo);
						if (miEquipo != null) {
							valido = true;

						} else {
							System.out.println(
									"Id introducido no válido, porfavor compruebe de nuevo los ids disponibles y no intente romper el programa");
						}

					} while (!valido);

//				En caso de querer seleccionar por nombre, se ejecuta esta parte del código
				} else {

//					Se pide el nombre del equipo mientras que no se encuentre dentro de la lista o sea un nombre inválido
					do {

						System.out.println("Ingresa el nombre del equipo al que quieres asociar el jugador:");
						nombreEquipo = sc.nextLine();

//						Metodo que busca dentro de los equipos y devuelve un objeto
//						De no ser así devuelve null
						miEquipo = nombreDentroDeEquipos(nombreEquipo);

						if (miEquipo != null) {
							valido = true;

						} else {
							System.out.println(
									"Nombre introducido no válido, porfavor compruebe de nuevo los nombres disponibles y no intente romper el programa");
						}

					} while (!valido);

				}

//				Asociar el equipo y el jugador (ya ambos seleccionados)
				asociarJugadorYEquipo(miJugador, miEquipo);

//			En caso de no haber datos disponibles, se muestra un mensaje de aviso al usuario
			} else {
				System.out.println("No hay equipos o jugadores suficientes");
			}

		} catch (InputMismatchException e) {
			System.out.println(
					"No ha introducido numeros, porfavor intente de nuevo la operacion y no rompa el programa");
		} catch (Exception e) {
			System.out.println("No se pudo realizar la operacion, por favor intente de nuevo la operacion");
		}
	}

//	Metodo que busca dentro de la plantilla el String que recibe por parámetro
	public Jugador seleccionarJugadorPorNombre(String nombreBusca) {

		boolean encontrado = false;

		int i = 0;
		int longitud = jugadores.size();

		Jugador miJugador;

		do {

//			Si coinciden, se sale del bucle
			if (jugadores.get(i).getNombre().equals(nombreBusca)) {
				encontrado = true;

//			Si no, se aumenta el contador para comprobar el siguiente objeto
			} else {
				i++;
			}

		} while (!encontrado && i < longitud);

//		Si se ha encontrado, se le da valor al objeto
		if (encontrado) {
			miJugador = jugadores.get(i);

//		Si no, se le da valor null y se muestra un mensaje de error
		} else {
			miJugador = null;
			System.out.println("Jugador no encontrado, compruebe que su nombre está correctamente escrito");
		}

		return miJugador;
	}

	/*
	 * Metodo que recibe por parametro un int y busca dentro de los dorsales del
	 * equipo
	 */
	public Jugador seleccionarJugadorPorDorsal(int dorsalBusca) {

		boolean encontrado = false;

		int i = 0;

		int longitud = jugadores.size();

		Jugador miJugador;

		do {

//			Si coinciden los dorsales, se sale del bucle
			if (jugadores.get(i).getDorsal() == dorsalBusca) {
				encontrado = true;

//			Si no, se aumenta el contador para comprobar el siguiente dorsal
			} else {
				i++;
			}

		} while (!encontrado && i < longitud);

//		Una vez fuera del bucle, si se ha encontrado una coincidencia se le da valor al objeto
		if (encontrado) {
			miJugador = jugadores.get(i);

//		Si no, se le da valor nulo y se muestra un mensaje de error
		} else {
			miJugador = null;
			System.out.println("Dorsal no encontrado, porfavor intentelo de nuevo");
		}

		return miJugador;
	}

	/*
	 * Metodo que busca dentro de la lista de equipos llamada grupos por id
	 */
	public Equipo idDentroDeGrupos(int id_equipoBusca) {

		boolean encontrado = false;

		int i = 0;

		int tamañoGrupos = grupos.size();

		Equipo miEquipo;

		do {

//			Comprueba si son iguales, y de ser así se sale del bucle
			if (grupos.get(i).getId() == id_equipoBusca) {
				encontrado = true;

//			Si no, se aumenta el contador para comprobar el siguiente id del equipo
			} else {
				i++;
			}

		} while (!encontrado && i < tamañoGrupos);

//		Si se ha encontrado, se le da valor al objeto
		if (encontrado) {
			miEquipo = grupos.get(i);

//		Si no, se le da valor nulo
		} else {
			miEquipo = null;
		}

		return miEquipo;

	}

	/*
	 * Metodo que busca dentro de los equipos el objeto que tenga el mismo nombre
	 * recibido por parametro
	 */
	public Equipo nombreDentroDeEquipos(String nombreBusca) {

		int i = 0;

		boolean encontrado = false;

		int longitud = grupos.size();

		Equipo miEquipo;

		do {

//			Comprueba si coinciden los nombres, y de ser así se sale del bucle
			if (grupos.get(i).getNombre().equals(nombreBusca)) {
				encontrado = true;

//			Si no, se aumenta el contador en uno
			} else {
				i++;
			}

		} while (!encontrado && i < longitud);

//		Si el equipo ha sido encontrado, se le da valor al objeto ya creado
		if (encontrado) {
			miEquipo = grupos.get(i);

//		Si no, se le da valor nulo
		} else {
			miEquipo = null;
		}

		return miEquipo;
	}

	
	/*
	 * Metodo que asocia entre sí un objeto del tipo Jugador y otro del tipo Equipo que recibe por parámetro
	 * */
	public void asociarJugadorYEquipo(Jugador miJugador, Equipo miEquipo) {

		try {

//			Establece el equipo_id del jugador
			miJugador.setEquipo_id(miEquipo.getId());

//			Añade un id a la lista de ids de jugadores de Equipo
			miEquipo.añadirId(miJugador.getId());

//			Se da un mensaje de confirmacion
			System.out.println("Jugador asociado al equipo exitosamente!!");
			
		} catch (Exception e) {
//			Si por lo que fuese diese error, se avisa al usuario
			System.out.println("No se pudo asociar :(");
			System.out.println("Por favor, intentelo de nuevo en unos instantes :D");
		}

	}

	// Método para añadir jugador
	public void añadirJugador() {
		Jugador item = new Jugador();
		Scanner lector = new Scanner(System.in);
		
		System.out.println("Introduce el nombre del nuevo jugador");
		item.setNombre(lector.nextLine());
		
		System.out.println("Introduce el dorsal de " + item.getNombre());
		item.setDorsal(lector.nextInt());
		lector.nextLine();
		
		System.out.println("¿Quieres introducir una posición a " + item.getNombre() + "? (S o N)");
		if (lector.next().toUpperCase().charAt(0) == 'S') {
			lector.nextLine();
			System.out.println("Introduce la posición de " + item.getNombre());
			item.setPosicion(lector.nextLine());
		}
		
		jugadores.add(item);
	}

	// Método para listar las diferentes opciones compuesto por un switch, dentro
	// contiene los diferentes métodos
	public void listar() {
		Scanner lector = new Scanner(System.in);
		int respuesta;
		System.out.println("1- Listar todo");
		System.out.println("2- Listar nombres");
		System.out.println("3- Listar dorsales");
		System.out.println("4- Listar posiciones");
		respuesta = lector.nextInt();

		switch (respuesta) {
		case 1:
			listarTodo();
			break;
		case 2:
			listarNombre();
			break;
		case 3:
			listarDorsal();
			;
			break;
		case 4:
			listarPosicion();
			break;
		}
	}

	// Métodos para listar
	// Todo
	public void listarTodo() {
		for (Jugador elemento : jugadores) {
			System.out.println(elemento.toString());
		}
	}

	// Nombre
	public void listarNombre() {
		for (Jugador elemento : jugadores) {
			System.out.println(elemento.getNombre());
		}
	}

	// Dorsal
	public void listarDorsal() {
		for (Jugador elemento : jugadores) {
			System.out.println(elemento.getDorsal());
		}
	}

	// Posición
	public void listarPosicion() {
		for (Jugador elemento : jugadores) {
			System.out.println(elemento.getPosicion().toString());
		}
	}

	// Método para despedir un jugador. Se compone de un bucle doWhile que recorre
	// la plantilla hasta encontrar el nombre y lo borra
	public void despedir() {
		int respuesta = 0;

		Scanner lector = new Scanner(System.in);
		System.out.println("Como identificas a tu jugardor?");
		System.out.println("1-Nombre");
		System.out.println("2-Dorsal");
		respuesta = lector.nextInt();
		lector.nextLine();
		switch (respuesta) {
		case 1:
			despedirNombre();
			break;
		case 2:
			despedirDorsal();
			break;
		}
	}

	// Método para modificar un jugador. Recorremos la plantilla hasta encontrar ya
	// sea el nombre o el dorsal
	// y lo modifica por el valor nuevo que el usuario introduzca

	public void modificar() {
		int respuesta = 0;
		Scanner lector = new Scanner(System.in);
		System.out.println("A partir de que quieres modificar?");
		System.out.println("1-Nombre");
		System.out.println("2-Dorsal");
		respuesta = lector.nextInt();
		lector.nextLine();
		switch (respuesta) {
		case 1:
			modificarNombre();
			break;
		case 2:
			modificarDorsal();
			break;
		}
	}

	// Metodo para despedir un jugador a partir del nombre
	public void despedirNombre() {
		String nombre;
		int contador = 0;
		int longitud = jugadores.size();
		boolean encontrado = false;
		Scanner lector = new Scanner(System.in);

		System.out.println("Cual es el nombre de la persona quieres despedir, mala persona?");
		nombre = lector.nextLine();
		do {
			if (jugadores.get(contador).getNombre().equals(nombre)) {
				encontrado = true;
				jugadores.remove(contador);
			}
			contador++;
		} while (!encontrado && contador < longitud);
		if (!encontrado) {
			System.out.println("El nombre no esta en la lista");
		}
	}

	// Método para despedir a un jugador a partir del dorsal
	public void despedirDorsal() {
		int dorsal = 0;
		int contador = 0;
		int longitud = jugadores.size();
		boolean encontrado = false;
		boolean esCorrecto = true;
		Scanner lector = new Scanner(System.in);

		do {
			try {
				encontrado = false;
				esCorrecto = true;
				System.out.println("Cual es el dorsal de la persona quieres despedir, mala persona?");
				dorsal = lector.nextInt();
				do {
					if (jugadores.get(contador).getDorsal() == dorsal) {
						encontrado = true;
						jugadores.remove(contador);
					}
					contador++;
				} while (!encontrado && contador < longitud);
				if (!encontrado) {
					System.out.println("El dorsal no esta en la lista");
				}
			} catch (InputMismatchException e) {
				System.out.println("Ha introducido caracteres no numericos");
				esCorrecto = false;
			} catch (Exception e) {
			} finally {
			}
		} while (!esCorrecto || !encontrado);

	}

	// Método para modificar a un jugador a partir del nombre
	public void modificarNombre() {
		String nombreAModificar;
		String nombreNuevo = " ";
		int contador = 0;
		int longitud = jugadores.size();
		boolean encontrado = false;
		Scanner lector = new Scanner(System.in);

		System.out.println("Cual es el nombre de la persona quieres modificar?");
		nombreAModificar = lector.nextLine();
		do {
			if (jugadores.get(contador).getNombre().equals(nombreAModificar)) {
				encontrado = true;
				System.out.println("Introduce el nuevo nombre");
				nombreNuevo = lector.nextLine();
				jugadores.get(contador).setNombre(nombreNuevo);
			}
			contador++;
		} while (!encontrado && contador < longitud);
		if (!encontrado) {
			System.out.println("El nombre no esta en la lista");
		}
	}

	// Método para modificar a un jugador a partir del dorsal
	public void modificarDorsal() {
		int DorsalAModificar;
		int DorsalNuevo = 0;
		int contador = 0;
		int longitud = jugadores.size();
		boolean encontrado = false;
		Scanner lector = new Scanner(System.in);

		System.out.println("Cual es el dorsal de la persona quieres modificar?");
		DorsalAModificar = lector.nextInt();
		lector.nextLine();
		do {
			if (jugadores.get(contador).getDorsal() == DorsalAModificar) {
				encontrado = true;
				System.out.println("Introduce el nuevo dorsal");
				DorsalNuevo = lector.nextInt();
				jugadores.get(contador).setDorsal(DorsalNuevo);
			}
			contador++;
		} while (!encontrado && contador < longitud);
		if (!encontrado) {
			System.out.println("El dorsal no esta en la lista");
		}
	}
	
	public void quitarJugadorEquipo() {
		
		int id_equipo = 0;
		int id_jugador = 0;
		int contador = 0;
		boolean encontrado = false;
		String nombre;
		
		Scanner lector = new Scanner(System.in);

		System.out.println("¿Cual es el nombre de la persona quieres quitar del equipo?");
		nombre = lector.nextLine();
		
		do {
			if (jugadores.get(contador).getNombre().equals(nombre)) {
				encontrado = true;
				id_jugador = contador;
				id_equipo = jugadores.get(contador).getEquipo_id();
			}
			contador++;
		} while (!encontrado && contador < jugadores.size());
		
		if (encontrado) {
			jugadores.get(contador).setEquipo_id(-1);
			
			grupos.get(id_equipo).getPlantilla().remove(grupos.get(id_equipo).getPlantilla().indexOf(id_jugador));
			
			System.out.println("Jugador eliminado correctamente del equipo");
		}else {
			System.err.println("Jugador no encontrado");
		}
		 
	}
	
	/*-CHEAT-CODE--------------------------------------------------------------------------------------------------------------*/
	
	public int saberGrupos() {
		int numeroGrupos;
		
		Scanner lector = new Scanner(System.in);
		
		System.out.println("Introduce el numero de grupos");
		numeroGrupos = lector.nextInt();
		lector.nextLine();
		
		return numeroGrupos;
	}
	
	/*-CHEAT-CODE--------------------------------------------------------------------------------------------------------------*/
	
	public int saberJornadas(int numeroGrupos) {
		int jornadas;
		
		if (numeroGrupos % 2 == 0) {
			jornadas = numeroGrupos - 1;
			System.out.println("Las semanas necesarias son: " + (jornadas));
		} else {
			jornadas = numeroGrupos;
			System.out.println("Las semanas necesarias son: " + (jornadas));
		}
		
		return jornadas;
	}
	
	/*-CHEAT-CODE--------------------------------------------------------------------------------------------------------------*/
	
	public void rellenarEquipos(int numeroGrupos ) {
		
		if (numeroGrupos%2==0) {
			for (int i = 0; i < numeroGrupos; i++) {
				
				addEquipo("Grupo " + (i+1));

			}
		} else {
			for (int i = 0; i < numeroGrupos; i++) {
				
				addEquipo("Grupo " + (i+1));

			}
			addEquipo("Descanso");
		}
		
	}

	/*-CHEAT-CODE--------------------------------------------------------------------------------------------------------------*/

	public void empezar(int numGrupos) {
		for (int i = 1; i <= numGrupos; i++) {
			grupos.add(new Equipo("Grupo "+i));
		}
		if(numGrupos%2!=0) {
			grupos.add(new Equipo("Descanso"));
		}

	}
	
	/*-COMBATES--------------------------------------------------------------------------------------------------------------*/

	/*
	 * En este método se realiza la generecion de los enfrentamientos en funcion del número de grupos.
	 * */
	public void generarCombates(int numeroJornadas) {
		int auxb; //SIEMPRE ES PAR
		int a;
		int b;
		int tamaño;
		
//		Vaciar el array de enfrentamientos por si el usuario genera mas de una vez los combates
		enfrentamientos.clear();
		
		if (grupos.size()%2 != 0) {
			addEquipo("Descanso");
		}
		
		auxb = grupos.size();
		
//		Genera la primera jornada, y a partir de ahí, solo hay que restar 1 a cada jornada nueva
		for (int i = 0; i < auxb/2; i++) {
			enfrentamientos.add(new Partido(i, auxb-i-1, 0));
		}
		
		for (int i = 1; i < numeroJornadas; i++) {
			for (int j = 0; j < auxb/2; j++) {
				tamaño = enfrentamientos.size()-auxb/2;
				a = enfrentamientos.get(tamaño).getEquipoA();
				b = enfrentamientos.get(tamaño).getEquipoB();
				
				if (a == 1 ) {
					a = grupos.size();
				}else if (b == 1) {
					b = grupos.size();
				}
				
				if (a == 0 && j == 0) {
					enfrentamientos.add(new Partido(a, b-1, i));
				}else {
					enfrentamientos.add(new Partido(a-1, b-1, i));
				}
				
			}
		}
		
//		Muestra los enfrentamientos 
		for (Partido partido : enfrentamientos) {
			System.out.println(grupos.get(partido.getEquipoA()).getNombre() + " VS " + grupos.get(partido.getEquipoB()).getNombre() + ". En la jornada: " + partido.getJornada());
		}
	}
	
/*-ASIGNAR-PUNTUACIONES--------------------------------------------------------------------------------------------------------------*/
	
	public void asignarPuntuacionTodos() {
		Scanner sc = new Scanner(System.in);
		int puntuacionAsignar, lenEnfrentamientos, contador = 0;
		char eleccion;
		boolean seguir = true, error = false;

		lenEnfrentamientos = enfrentamientos.size();

		//Si no hay grupos o enfrentamientos te avisa y no te deja asignar, en caso contrario entra en la parte para asignar puntuaciones a los enfrentamientos
		if(enfrentamientos.size()<=0 || grupos.size()<=0) {
			System.out.println("No hay grupos o enfrentamientos");
		}else {

			do {
				// Evaluacion de que los equipos no tengan puntuación asignada usando el método asignarPuntuacion
				if ((enfrentamientos.get(contador).getPuntuacionEquipoA() == 0 && enfrentamientos.get(contador).getPuntuacionEquipoA() == 0)
				&& (!grupos.get(enfrentamientos.get(contador).getEquipoA()).getNombre().equals("Descanso") 
				&& !grupos.get(enfrentamientos.get(contador).getEquipoB()).getNombre().equals("Descanso"))) {
					do {// Bucle que se repite mientras el usuario quiera seguir metiendo resultados
						error = false;
						try {
							// Pedimos datos mostrando el número de la jornada, el numero de enfrentamiento
							System.out.println("Jornada " + (enfrentamientos.get(contador).getJornada() + 1) + ", Enfrentamiento Nº" + (contador + 1));
	
							System.out.println("Cuantos puntos quieres asignar al equipo " + grupos.get(enfrentamientos.get(contador).getEquipoA()).getNombre() + "?");
							puntuacionAsignar = sc.nextInt();
							sc.nextLine();
	
							enfrentamientos.get(contador).setPuntuacionEquipoA(puntuacionAsignar);
							grupos.get(enfrentamientos.get(contador).getEquipoA()).setPuntuacion(puntuacionAsignar);
	
							System.out.println("Cuantos puntos quieres asignar al equipo " + grupos.get(enfrentamientos.get(contador).getEquipoB()).getNombre() + "?");
							puntuacionAsignar = sc.nextInt();
							sc.nextLine();
	
							enfrentamientos.get(contador).setPuntuacionEquipoB(puntuacionAsignar);
							grupos.get(enfrentamientos.get(contador).getEquipoB()).setPuntuacion(puntuacionAsignar);
	
							// Guardamos las variables en caso de que no salte al catch por algun error
	
						} catch (InputMismatchException e) {
							System.err.println("No se admiten caracteres para asignar la puntuacion");
							sc.nextLine();
							error = true;
						} catch (Exception e) {
							System.err.println("No se pudo asignar la puntuacion");
							error = true;
						}//Si entra en algún error se vuelve a ejecutar la parte de pedir datos
					} while (error);
					
					System.out.println("¿Deseas seguir introduciendo datos? S/N");
					eleccion = sc.next().toUpperCase().charAt(0);
					if (eleccion == 'N') {
						seguir = false;
						System.out.println("Gracias por introducir resultados");
					}
				}
				contador++;
			} while (seguir && contador < lenEnfrentamientos);
		}
	}

	public void asignarPuntuacion() {
		//Este método es parecido al anterior solo que en este se pide el número de enfrentamiento para asignar resultados solo a ese enfrentamiento
		Scanner sc = new Scanner(System.in);
		int enfrentamiento=-1;
		int puntuacionAsignar;
		boolean error = false;

		do {
			try {
				System.out.println("Elige el enfrentamiento al que asignar puntuacion:");
				for (int i = 0; i < enfrentamientos.size(); i++) {
				System.out.println((i+1) + " -> " + grupos.get(enfrentamientos.get(i).getEquipoA()).getNombre() + " VS " + grupos.get(enfrentamientos.get(i).getEquipoB()).getNombre() + " en la jornada: " + (enfrentamientos.get(i).getJornada()+1));
				}
				enfrentamiento = sc.nextInt()-1;
				sc.nextLine();
				
				error = true;
			}catch (InputMismatchException e) {
				System.err.println("Introduce un número");
			} catch (Exception e) {
				System.err.println("Ha ocurrido un error");
			}
		} while (!error);
		
		do {
			error = false;
			try {
				//lo mismo que en la anterior, se piden datos hasta que no entre en ningún catch

				System.out.println("Jornada " + (enfrentamientos.get(enfrentamiento).getJornada() + 1) + ", Enfrentamiento Nº" + (enfrentamiento + 1));

				System.out.println("Cuantos puntos quieres asignar al equipo " + grupos.get(enfrentamientos.get(enfrentamiento).getEquipoA()).getNombre() + "?");
				puntuacionAsignar = sc.nextInt();
				sc.nextLine();

				enfrentamientos.get(enfrentamiento).setPuntuacionEquipoA(puntuacionAsignar);
				grupos.get(enfrentamientos.get(enfrentamiento).getEquipoA()).setPuntuacion(puntuacionAsignar);

				System.out.println("Cuantos puntos quieres asignar al equipo " + grupos.get(enfrentamientos.get(enfrentamiento).getEquipoB()).getNombre() + "?");
				puntuacionAsignar = sc.nextInt();
				sc.nextLine();

				enfrentamientos.get(enfrentamiento).setPuntuacionEquipoB(puntuacionAsignar);
				grupos.get(enfrentamientos.get(enfrentamiento).getEquipoB()).setPuntuacion(puntuacionAsignar);

			} catch (InputMismatchException e) {
				System.out.println("No se admiten caracteres para asignar la puntuacion");
				sc.nextLine();
				error = true;
			} catch (Exception e) {
				System.out.println("No se pudo asignar la puntuacion");
				error = true;
			}
		} while (error);
		System.out.println(enfrentamientos.get(enfrentamiento).toString());
	}

	/*-MOSTRAR-CLASIFICACION--------------------------------------------------------------------------------------------------------------*/

	public void mostrarClasificacion() {
		ArrayList<Double> aux = new ArrayList<Double>();
		double suma;
		int posicion;
		String concatenacion, strSplit;

		//Recorre todas las puntuaciones y le concatena al final la posicion en el array
		for (int i = 0; i < grupos.size(); i++) {
			concatenacion = grupos.get(i).getPuntuacion() + "0." + i;
			suma = Double.parseDouble(concatenacion);
			aux.add(suma);
		}

		//Ordenamos las posiciones de mayor a menor y como tenemos guardado el índice podemos accceder al nombre del grupo
		Collections.sort(aux);
		//Lo ordenamos de mayor a menor
		Collections.reverse(aux);

		for (double item : aux) {
			//Recorriendo todo el array auxiliar nos quedamos con la posicion y pintamos en orden su posicion y de ahí sacamos su índice
			strSplit = Double.toString(item);//esta variable valdría su puntuacion y su índice en el array de grupos (ej. 100.1 puntuacion 100 y su índice es 1)
			posicion = Integer.parseInt(strSplit.substring(strSplit.indexOf(".") + 1));
			System.out.println(grupos.get(posicion).getPuntuacion() + " puntos para el equipo: " + grupos.get(posicion).getNombre());
			//Mostramos en base al índice su puntuacion y su nombre
		}
	}
}
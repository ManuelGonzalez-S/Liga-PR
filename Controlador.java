import java.util.InputMismatchException;
import java.util.Scanner;

public class Controlador {

	public static void main(String[] args) {

		int numeroGrupos;
		int numeroJornadas;
		boolean fin = false;
		String entrada;
		int opcion;

		Liga liga1 = new Liga();
		Scanner lector = new Scanner(System.in);

		numeroGrupos = liga1.saberGrupos();
		numeroJornadas = liga1.saberJornadas(numeroGrupos);

		liga1.rellenarEquipos(numeroGrupos);
		
		liga1.generarCombates(numeroJornadas);

		do {
			System.out.println("A -> Gestionar Equipo\n" 
				+ "B -> Gestionar Jugador\n" 
				+ "C -> Generar Enfrentamientos\n"
				+ "D -> Introducir Resultados de Enfrentamientos\n" 
				+ "E -> Mostrar la Clasificación\n"
				+ "F -> Salir");
			
			entrada = lector.nextLine();

			switch (entrada.toLowerCase()) {
			case "a":
				try {
					System.out.println("1 -> Ver Equipo\n"
							+ "2 -> Añadir Equipo\n"
							+ "3 -> Borrar un Jugador de un Equipo\n"
							+ "4 -> Borrar Equipo\n"
							+ "5 -> Modificar Equipo\n"
							+ "6 -> Añadir jugador a Equipo");
					opcion = lector.nextInt();
					lector.nextLine();
					
					switch (opcion) {
					case 1:
						liga1.verEquipos();
						break;
					case 2:
						System.out.println("Introduce el nombre del equipo:");
						liga1.addEquipo(lector.nextLine());
						break;
					case 3:
						//
						
						break;
					case 4:
						System.out.println("Introduce el nombre del equipo:");
						liga1.borrarEquipo(lector.nextLine());
						break;
					case 5:
						liga1.modificarEquipo();
						break;
					case 6:
						liga1.seleccionarJugadorEquipoYAsociar();
						break;
						
					default:
						System.err.println("Opción seleccionada no válida");
						break;
					}
				}catch (InputMismatchException e) {
					System.err.println("Introduce un número");
				} catch (Exception e) {
					System.err.println("Ha ocurrido un error");
				}
				break;
				
			case "b":
				try {
					System.out.println("1 -> Ver Jugador\n"
							+ "2 -> Añadir jugador\n"
							+ "3 -> Borrar un Jugador\n"
							+ "4 -> Editar Jugador\n"
							+ "5 -> Añadir jugador a Equipo");
					opcion = lector.nextInt();
					lector.nextLine();
					
					switch (opcion) {
					case 1:
						liga1.listar();
						break;
					case 2:
						// Faltan pedir los datos
						liga1.añadirJugador();
						break;
					case 3:
						liga1.despedir();
						break;
					case 4:
						liga1.modificar();
						break;
					case 5:
						//Faltan los atributos
						liga1.asociarJugadorYEquipo(null, null);
						break;
						
					default:
						System.err.println("Opción no válida");
						break;
					}
				}catch (InputMismatchException e) {
					System.err.println("Introduce un número");
				} catch (Exception e) {
					System.err.println("Ha ocurrido un error");
				}
				break;
				
			case "c":
				liga1.generarCombates(numeroJornadas);
				break;
			case "d":
				liga1.asignarPuntuacionTodos();
				break;
			case "e":
				liga1.mostrarClasificacion();
				break;
			case "f":
				fin = true;
				break;

			default:
				System.err.println("Opción seleccionada no válida");
				break;
			}
		} while (!fin);
		lector.close();
	}

}
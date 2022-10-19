package com.its.pasajeros;

import java.util.Scanner;

public class App {

	private String[] pasajeros = new String[1];
	private String[] comun = new String[1];
	private String[] docente = new String[1];
	private String[] estudiante = new String[1];
	private String[] jubilado = new String[1];
	private String[] pensionista = new String[1];
	private String[] paradas = new String[30];
	private int[] conexiones = new int[90];
	private Scanner input = new Scanner(System.in);

	public String boldify(String texto) {

		return "\033[1m" + texto + "\033[0m";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.menuPrincipal();
	}

	public void menuPrincipal() {
		int userInput;
		do {
			System.out.printf(boldify("%nSeleccione un item del menu")
					+ "%n1: Menu de pasajeros. %n2: Menu de paradas. %n3: Menu de conexiones. %n0: Salir del programa.%n");
			userInput = input.nextInt();
			switch (userInput) {
			case 1:
				menuPasajeros();
				break;
			case 2:
				menuParadas();
				break;
				case 3:
					menuConexion();
					break;		
			}
		} while (userInput != 0);
	}

	
	public void menuConexion() {
		int userInput;
		do {
			System.out.printf(boldify("Menu de conexiones.") + "%n1: registrar conexion. %n0. Volver al menu principal");
			userInput = input.nextInt();
			switch (userInput) {
			case 1:
				registrarConexion();
				break;
			}
		} while (userInput != 0);
	}
	 

	public void menuParadas() {
		int userInput;
		do {
			System.out.printf(boldify("Seleccione un item del menu")
					+ "%n1: Registrar parada. %n2: Mostrar parada. %n0: Volver al menu principal.");
			userInput = input.nextInt();
			switch (userInput) {
			case 1:
				registrarParada();
				break;
			case 2:
				mostrarParada();
				break;
			}
		} while (userInput != 0);
	}

	public void menuPasajeros() {
		int userInput;
		do {
			System.out.printf(boldify("%nSeleccione un item del menu")
					+ "%n1: Mostrar todos los pasajeros. %n2: Buscar un pasajero. %n3: Registrar un pasajero. %n0: Volver al menu principal.%n");
			userInput = input.nextInt();
			switch (userInput) {
			case 1:
				mostrarPasajeros(pasajeros);
				break;
			case 2:
				buscarPasajero();
				break;
			case 3:
				registrarPasajero();
				break;
			}
		} while (userInput != 0);
	}

	public Boolean existeParada(String code) {
		
		for(int i = 0; i < paradas.length - 1; i += 2) {
			if(paradas[i] == code) {
				return true;
			}
		}
		
		return false;
	}
		
	public void buscarPasajero() {
		/**
		 * mostrar un pasajero
		 */

		System.out.printf(boldify("%nPara buscar un pasajero ingrese")
				+ "%n - numero de cedula sin puntos ni guiones. %n - O el nombre del pasajero. %n - O el tipo de pasajero para ver todos los de dicha categoria."
				+ boldify("%nIngrese el termino de busqueda ahora o ingrese 0 para volver al menu de pasajeros:%n"));

		String query = input.next();
		// WHILE NO ESTA FUNCIONANDO COMO SE ESPERA: siempre entra en default aunque el
		// usuario ingrese 0
		while (query != "0") {
			switch (query) {
			case "comun":
				mostrarPasajeros(comun);
			case "docente":
				mostrarPasajeros(docente);
			case "estudiante":
				mostrarPasajeros(estudiante);
			case "jubilado":
				mostrarPasajeros(jubilado);
			case "pensionista":
				mostrarPasajeros(pensionista);
			default: {
				if (pasajeros[0] == null) {
					System.out.println("No hay registros");
				} else {
					Boolean encontrado = false;
					int i = 0;
					while (!encontrado) {
						if (pasajeros[i] != null && i < pasajeros.length) {
							String[] registro = pasajeros[0].split("@");
							if (query.equals(registro[0]) || query.equals(registro[1])) {
								encontrado = true;
								System.out.println("Datos del pasajero:");
								System.out.println("Ci: " + registro[0] + " Nombre: " + registro[1] + " Tipo: " + registro[2] + ".");
							}
							i++;
						} else {
							encontrado = true;
							System.out.println("No existen coincidencias");
						}
					}
				}
				menuPasajeros();
			}
			}
		}
		menuPasajeros();
	}

	public void mostrarParada() {
		if(paradas[0] == null) {
			System.out.println("No existe ninguna parada registrada");
		} else {
			int i = 0;
			String str = "";
			while(paradas[i] != null) {
				str += i % 2 == 0 ? paradas[i] : " "+paradas[i]+".";				
				if(i % 2 != 0) System.out.println(str);
				i++;
			}
		}
		menuParadas();
	}
	
	public void mostrarPasajeros(String[] array) {
		/**
		 * mostrar todos los pasajeros
		 */
		if (array[0] == null) {
			System.out.println("No existen registros");
		} else {
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					System.out.println(array[i]);
				}
			}
		}
		menuPasajeros();
	}
	
	public void registrarConexion() {
		String origen, destino, km;
		System.out.println("Ingrese el codigo de la parada de origen");
		origen = input.next();
		Boolean existeOrigen = existeParada(origen);
		
		if (!existeOrigen) {
			System.out.printf("No existe ninguna parada registrada con el codigo proporcionado.");
			menuConexion();
		}
		
		System.out.println("Ingrese el codigo de la parada destino");
		destino = input.next();
		Boolean existeDestino = existeParada(destino);
		
		if (!existeDestino) {
			System.out.printf("No existe ninguna parada registrada con el codigo proporcionado.");
			menuConexion();
		}
		
		System.out.println("Ingrese los kilometros");
		km = input.next();
		Boolean isValidKm = km.matches("^\\d*\\.?\\d+$");
		
		if(!isValidKm) {
			System.out.println("El dato ingresado es incorrecto intentelo nuevamente.");
			menuConexion();
		}
		
		
		
	}
	
	public void registrarParada() {
		if(paradas[paradas.length - 1] != null) {
			System.out.println("No hay cupos para registrar una parada.");
		} else {
			String parada, codigo;
			System.out.println("Ingrese el codigo de la parada.");
			codigo = input.next(); // Todo: Validar numero
			System.out.println("Ingrese el nombre de la parada.");
			parada = input.nextLine();
			
			Boolean existe = existeParada(codigo);
			int lastIdx = 0;
			while(paradas[lastIdx] != null && lastIdx < paradas.length) {
				lastIdx++;
			}
			
			if (!existe) {
				paradas[lastIdx] = codigo;
				paradas[lastIdx + 1] = parada;
				System.out.println("La parada se ha registrado con exito.");
			} else {
				System.out.println("La parada ya existe en el sistema.");
			}
		}
		menuParadas();
	}
	
	public void registrarPasajero() {
		
		/**
		 * PEDIR Y VALIDAR DATOS
		 */
		String delimitador = "@";
		String registro = "";

		System.out.println(boldify("Ingrese la cedula sin puntos ni guiones."));
		registro = input.next();
		boolean isValidCi = registro.matches("^[1-9]\\d{7}$");
		if (!isValidCi) {
			System.out.println("El dato ingresado es incorrecto intentelo nuevamente.");
			registrarPasajero();
		}

		System.out.println(boldify("Ingresar el nombre:"));
		registro += delimitador + input.next();
		System.out
				.println(boldify("Ingresar el tipo de pasajero:") + " (comun, pensionista, jubilado, docente, estudiante)");
		String tipo = input.next();

		switch (tipo) {
		case "comun":
		case "pensionista":
		case "jubilado":
		case "docente":
		case "estudiante":
			registro += delimitador + tipo;
			break;
		default:
			System.out.printf("%nEl tipo de pasajero es incorrecto."
					+ boldify("%nLas opciones validas son:" + " comun, pensionista, jubilado, docente, estudiante%n"));
			registrarPasajero();
		}

		/**
		 * PEDIR Y VALIDAR DATOS
		 */

		if (pasajeros[pasajeros.length - 1] != null) {
			System.out.println("No hay mas cupos.");
			menuPasajeros();
		}
		// si hay cupos
		String[] datoArray = registro.split(delimitador);
		switch (datoArray[datoArray.length - 1]) {
		case "comun": {
			registrarTipoPasajero(comun, registro);
			break;
		}
		case "pensionista": {
			registrarTipoPasajero(pensionista, registro);
			break;
		}
		case "jubilado": {
			registrarTipoPasajero(jubilado, registro);
			break;
		}
		case "docente": {
			registrarTipoPasajero(docente, registro);
			break;
		}
		case "estudiante": {
			registrarTipoPasajero(estudiante, registro);
			break;
		}
		default:
			menuPasajeros();
		}
	}
	
	private void registrarTipoPasajero(String[] arr, String dato) {
		if (arr[arr.length - 1] != null) {
			System.out.println("No hay mas cupos para esta categoria, intente en otra.");
			return;
		}
		// else: hay cupos identificar posicion vacia
		int iPasajeros = 0; // indice en el array principal
		while (pasajeros[iPasajeros] != null) {
			iPasajeros++;
		}
		int iTipo = 0; // indice en el array del tipo de pasajero
		while (arr[iTipo] != null) {
			iTipo++;
		}
		// es null, esta vacio
		pasajeros[iPasajeros] = dato;
		arr[iTipo] = dato;
		System.out.println("El pasajero <<" + dato + ">> ha sido registrado correctamente. ");
		menuPasajeros();
	}

}

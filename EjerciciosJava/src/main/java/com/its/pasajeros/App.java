package com.its.pasajeros;

import java.util.Scanner;

public class App {

	private String[] pasajerosTodos = new String[2];
	private String[] pasajerosCatA = new String[1];
	private String[] pasajerosCatB = new String[1];
	private String[] pasajerosCatC = new String[1];
	private String[] pasajerosCatD = new String[1];
	private String[] pasajerosCatE = new String[1];
	private String[] pasajerosCatF = new String[1];
	private String[] paradas = new String[30];
	private String[] conexiones = new String[90];
	private String[] lineas = new String[50];
	
	private String d = "@"; // delimitador
	private Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.menuPrincipal();
	}

	public void menuPrincipal() {
		int userInput;
		do {
			System.out.printf("%nSeleccione un item del menu. %n1: Menu de pasajeros. %n2: Menu de paradas. %n3: Menu de conexiones. %n0: Salir del programa.%n");
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
			System.out.printf("Menu de conexiones. %n1: registrar conexion. %n2: registrar linea. %n3 mostrar conexion con mayor distancia. %n4 mostrar conexion con menor distancia. %n0. Volver al menu principal");
			userInput = input.nextInt();
			switch (userInput) {
				case 1: {
					registrarConexion();
					break;
				}
				case 2: {
					registrarLinea();
					break;
				}
				case 3: {
					mostrarParadaConMayorDistancia();
					break;
				}
				case 4: {
					mostrarParadaConMenorDistancia();
					break;
				}
			}
		} while (userInput != 0);
	}

	public void menuParadas() {
		int userInput;
		do {
			System.out.printf("Seleccione un item del menu. %n1: Registrar parada. %n2: Mostrar parada. %n0: Volver al menu principal.");
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
			System.out.printf("%nSeleccione un item del menu. %n1: Mostrar todos los pasajeros. %n2: Buscar un pasajero. %n3: Registrar un pasajero. %n0: Volver al menu principal.%n");
			userInput = input.nextInt();
			switch (userInput) {
			case 1:
				mostrarPasajeros(pasajerosTodos);
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

		for (int i = 0; i < paradas.length - 1; i += 2) {
			if (paradas[i] == code) {
				return true;
			}
		}

		return false;
	}

	public void buscarPasajero() {
		/**
		 * mostrar un pasajero
		 */

		System.out.printf(
				"%nPara buscar un pasajero ingrese: %n - numero de cedula sin puntos ni guiones. %n - O el nombre del pasajero. %n - O la categoria del pasajero para verlos todos. %nIngrese el termino de busqueda ahora o ingrese 0 para volver al menu de pasajeros:%n");

		String query = input.nextLine();
		// WHILE NO ESTA FUNCIONANDO COMO SE ESPERA: siempre entra en default aunque el
		// usuario ingrese 0
		while (query != "0") {
			switch (query.toUpperCase()) {
			case "A":
				mostrarPasajeros(pasajerosCatA);
			case "B":
				mostrarPasajeros(pasajerosCatB);
			case "C":
				mostrarPasajeros(pasajerosCatC);
			case "D":
				mostrarPasajeros(pasajerosCatD);
			case "E":
				mostrarPasajeros(pasajerosCatE);
			case "F":
				mostrarPasajeros(pasajerosCatF);
			default: {
				if (pasajerosTodos[0] == null) {
					System.out.println("No hay registros que mostrar.");
				} else {
					Boolean encontrado = false;
					int i = 0;
					while (!encontrado) {
						if (pasajerosTodos[i] != null && i < pasajerosTodos.length) {
							String[] registro = pasajerosTodos[i].split("@");
							if (query.equals(registro[0]) || query.equals(registro[1])) {
								encontrado = true;
								System.out.println("Datos del pasajero:");
								System.out.println("Ci: " + registro[0] + " Nombre: " + registro[1] + " Telefono: " + registro[2]
										+ " Categoria: " + registro[3] + ".");
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
		if (paradas[0] == null) {
			System.out.println("No existe ninguna parada registrada");
		} else {
			int i = 0;
			String str = "";
			while (paradas[i] != null) {
				str += i % 2 == 0 ? paradas[i] : " " + paradas[i] + ".";
				if (i % 2 != 0)
					System.out.println(str);
				i++;
			}
		}
		menuParadas();
	}

	public void mostrarParadaConMayorDistancia() {
		int maxPrev = 0;
		int maxI = 0;
		for (int i=2; i<conexiones.length; i +=3) {
			int num = Integer.parseUnsignedInt(conexiones[i]);
			if(num > maxPrev) {
				maxPrev = num;
				maxI = i;
			}
		}
		System.out.printf("La conexion con mayor distancia entre paradas es: %nOrigen: " + conexiones[maxI -2] + " Destino: " + conexiones[maxI - 1] + " Distancia: " + conexiones[maxI] + "km.");
		menuPrincipal();
	}
	
	public void mostrarParadaConMenorDistancia() {
		int minPrev = 0;
		int minI = 0;
		for (int i=2; i<conexiones.length; i +=3) {
			int num = Integer.parseUnsignedInt(conexiones[i]);
			if(num < minPrev) {
				minPrev = num;
				minI = i;
			}
		}
		System.out.printf("La conexion con menor distancia entre paradas es: %nOrigen: " + conexiones[minI -2] + " Destino: " + conexiones[minI - 1] + " Distancia: " + conexiones[minI] + "km.");
		menuPrincipal();
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
					String[] registro = array[i].split(d);
					System.out.println("Ci: " + registro[0] + " Nombre: " + registro[1] + " Telefono: " + registro[2]
							+ " Categoria: " + registro[3] + ".");
				}
			}
		}
		menuPasajeros();
	}

	public void registrarConexion() {
		if(conexiones[conexiones.length - 3] != null) {
			System.out.println("No hay cupos para mas conexiones.");
			menuPrincipal();
		}
		// ingresar / validar datos
		String origen, destino, km;
		Boolean existeOrigen, existeDestino, isValidKm;
		
		System.out.println("Ingrese el codigo de la parada de origen");
		origen = input.nextLine();
		existeOrigen = existeParada(origen);

		if (!existeOrigen) {
			System.out.printf("No existe ninguna parada registrada con el codigo proporcionado.");
			menuConexion();
		}

		System.out.println("Ingrese el codigo de la parada destino");
		destino = input.nextLine();
		existeDestino = existeParada(destino);

		if (!existeDestino) {
			System.out.printf("No existe ninguna parada registrada con el codigo proporcionado.");
			menuConexion();
		}

		System.out.println("Ingrese los kilometros");
		km = input.nextLine();		
		isValidKm = isValidNum(km);

		if (!isValidKm) {
			System.out.println("El dato ingresado es incorrecto intentelo nuevamente.");
			menuConexion();
		}

		// Checkear si existe la conexion
		Boolean existeConexion = false;
		int iConexion = 0;
		while(!existeConexion) {
			if (origen == conexiones[iConexion] && destino == conexiones[iConexion + 1]) {
				existeConexion = true;
			}
			iConexion += 3;
		}
		
		if(!existeConexion) {
			// registrarla
			int idx = encontrarIndice(conexiones);
			conexiones[idx] = origen;
			conexiones[idx+1] = destino;
			conexiones[idx+2] = km;
			System.out.println("La conexion se ha registrado correctamente.");
		} else {
			System.out.println("La conexion ya existe en la base de datos.");
		}
	}

	public void registrarLinea() {
		String origen, destino, linea;
		Boolean existeConexion = false;
		Boolean existeLinea = false;
		double minutos, costo;
		
		System.out.println("Ingrese el codigo de la parada de origen");
		origen = input.nextLine();

		System.out.println("Ingrese el codigo de la parada de destino");
		destino = input.nextLine();
		
		System.out.println("Ingrese el codigo de la linea.");
		linea = input.nextLine();
		
		System.out.println("Ingrese el tiempo de viaje.");
		minutos = input.nextDouble();
		
		System.out.println("Ingrese el costo del pasaje.");
		costo = input.nextDouble();
		
		int idx = 0;
		while(!existeConexion && idx < conexiones.length -3) {
			if(conexiones[idx] == origen && conexiones[idx + 1] == destino) {
				existeConexion = true;
			}
		}
		
		for(int i=0; i<lineas.length; i++) {
			String[] registro = lineas[i].split(d);
			if(registro[2] == linea) {
				existeLinea = true;
			}
		}
		
		if(existeConexion && !existeLinea) {
			int indice = encontrarIndice(lineas);
			lineas[indice] = origen + d + destino + d + linea + d + minutos + costo;
			System.out.println("La linea se registro correctamente");
			menuPrincipal();
		}

	}
	public void registrarParada() {
		if (paradas[paradas.length - 1] != null) {
			System.out.println("No hay cupos para registrar una parada.");
		} else {
			String codigo, parada;
			System.out.println("Ingrese el codigo de la parada.");
			codigo = input.nextLine();
			System.out.println("Ingrese el nombre de la parada.");
			parada = input.nextLine();

			Boolean existe = existeParada(codigo);
			int lastIdx = 0;
			while (paradas[lastIdx] != null && lastIdx < paradas.length) {
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
		// TODO: chequear que el pasajero no exista (por la cedula)
		if (pasajerosTodos[pasajerosTodos.length - 1] != null) {
			System.out.println("No hay cupos para registrar nuevos pasajeros.");
			menuPasajeros();
		}
		/**
		 * PEDIR Y VALIDAR DATOS
		 */
		String cedula;
		String nombre;
		String categoria;
		String telefono;
		String registro;

		System.out.println("Ingrese la cédula sin puntos ni guiones.");
		cedula = input.next();
		Boolean isValidCi = cedula.matches("^[1-9]\\d{7}$");
		if(!isValidCi) {
			System.out.println("ERROR: debe ingresar un número de cédula válido.");
			menuPasajeros();
		} else {
			for(int i = 0; i < pasajerosTodos.length; i++) {
				String[] item = pasajerosTodos[i].split(d);
				if(item[0] == cedula) {
					System.out.println("ERROR: El numero de cédula ya está registrado.");
					menuPasajeros();
				}
			}
		}
		System.out.println("Ingresar el nombre:");
		nombre = input.next();

		System.out.println("Ingresar el telefono:");
		telefono = input.next();
		Boolean isValidTel = telefono.matches("\\d{7}"); // TODO: checkear regexp
		if(!isValidTel) {
			System.out.println("ERROR: debe ingresar un número de telefono válido.");
			menuPasajeros();
		}
		
		System.out.printf(
				"Ingresar categoria del pasajero: %nA: Comun. %nB: Jubilado. %nC: Pensionista. %nD: Estudiante \"A\". %nE: Estudiante \"B\". %nF: Docente.");
		categoria = input.next();
		categoria = categoria.toUpperCase();
		Boolean isValidCat = categoria.matches("[A-F]");
		if(!isValidCat) {
			System.out.println("ERROR: debe ingresar una categoría válida.");
			menuPasajeros();
		}
		
		registro = cedula + d + nombre + d + telefono + d + categoria;

		switch (categoria) {
		case "A": {
			registrarCategoria(pasajerosCatA, registro);
			break;
		}
		case "B": {
			registrarCategoria(pasajerosCatB, registro);
			break;
		}
		case "C": {
			registrarCategoria(pasajerosCatC, registro);
			break;
		}
		case "D": {
			registrarCategoria(pasajerosCatD, registro);
			break;
		}
		case "E": {
			registrarCategoria(pasajerosCatE, registro);
			break;
		}
		case "F": {
			registrarCategoria(pasajerosCatF, registro);
			break;
		}
		default:
			menuPasajeros(); // no deberia entrar nunca en default
		}
	}

	// Agregadas
	private int encontrarIndice(String[] arr) {
		int num = -1;
		int i = 0;
		while (num < 0) {
			if(arr[i] == null) {
				num = i;
			}
		}
		return num;
	}
	private Boolean isValidNum(String num) {
		if (num.matches("^\\d*\\.?\\d+$")) {
			return Integer.parseUnsignedInt(num) > 0;
		}
		return false;
	}
	private void registrarCategoria(String[] arr, String dato) {
		if (arr[arr.length - 1] != null) {
			System.out.println("No hay mas cupos para esta categoria, intente en otra.");
			menuPasajeros();
		}
		// else: hay cupos identificar posicion vacia
		int iPasajeros = 0; // indice en el array principal
		while (pasajerosTodos[iPasajeros] != null) {
			iPasajeros++;
		}
		int iTipo = 0; // indice en el array del tipo de pasajero
		while (arr[iTipo] != null) {
			iTipo++;
		}
		// es null, esta vacio
		pasajerosTodos[iPasajeros] = dato;
		arr[iTipo] = dato;
		System.out.println("El pasajero <<" + dato + ">> ha sido registrado correctamente. ");
		menuPasajeros();
	}

}

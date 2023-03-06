package clinicaRibera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Clinica {
	//Atributos
		private ArrayList<Profesional>profesionales;
		private ArrayList<Paciente>pacientes;
	//Constructor
		//sin parámetros
		public Clinica() {
		this.profesionales = new ArrayList<>();
		this.pacientes = new ArrayList<>();
		}

		
	//MÉTODOS
		//implemento la interfaz EntradaUsuario
		public static boolean esInt(String n) {
			return n.matches("^\\+?[0-9]+");
		}

		//Menú
		public static void menu() {
			System.out.println("Elija una oción: ");
			System.out.println("\t 1. Listado de pacientes");
			System.out.println("\t 2. Cargar historias clinicas");
			System.out.println("\t 3. Atender paciente");
			System.out.println("\t 4. Salir");
		}
		//Cargar datos de Médicos y Pacientes
		public void cargarDatos(String filename) {
			File fichero = new File(filename);


			try {
				Scanner entrada = new Scanner(fichero);
				entrada.nextLine();
				String cadena = "";
				String[] linea;
				while (entrada.hasNext()) { //Lee si quedan datos en el fichero
					cadena = entrada.nextLine();
					linea = cadena.split(";"); //Aquí se guardarán los datos

					//Añado los datos guardados en linea[] al AL/objeto/variable que quiera tenerlos
					//Tener en cuenta el órden de los datos y el tipo que son: pasar de String al tipo que sean si es necesario
					if(linea[0].equals("medico")) {
						this.profesionales.add(new Profesional(linea[1], linea[2], linea[3], linea[4], linea[5], Integer.parseInt(linea[6])));
					}else {
						this.pacientes.add(new Paciente(linea[1], linea[2], linea[3], linea[4], Float.parseFloat(linea[7]), Integer.parseInt(linea[8]), linea[10], ""));
					}

				}
				entrada.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("No existe el fichero");
				e.printStackTrace();
			}
		}
		//Imprimir listado de pacientes
		public void listadoPacientes(ArrayList<Paciente> pacientes, ArrayList<Profesional> profesionales) {
			for (Profesional x : profesionales) {
				System.out.println("*****************  "+x.getEspecialidad()+"  *****************");
				System.out.println("Pacientes del Dr/Dra. "+x.getNombre()+" "+x.getApellidos()+": ");
				for (Paciente y : pacientes) {
					if(y.getCita().equals(x.getEspecialidad())) {
						System.out.println("\t -"+y.getNombre()+" "+y.getApellidos()+" (DNI: "+y.getDni()+")");
					}
				}
				System.out.println("\n");
			}
			
		}
		//Cargar datos de Historiales clínicas
		public void cargarHistorial(String filename) throws IOException {

			for (Paciente x : pacientes) {
				String nombre=filename+"Historia_"+x.getDni()+".txt";
				File fichero=new File(nombre);
				if(fichero.exists()) {//Si existe el fichero cargo los datos
					System.out.println("El archivo Hitoria_"+x.getDni()+" ya existe");
					//Cargo los datos
					File ficheroLectura = new File(nombre);
					String cadena = ""; //Donde guardaré los datos que vaya a leer 
					Scanner entrada = null;

					try {
						entrada = new Scanner(fichero);
						while (entrada.hasNext()) { //Lee si quedan datos en el fichero
								cadena +=entrada.nextLine()+"\n";
								
								x.setHistoria(cadena);//guarda su historial en la historia del paciente
						}
						entrada.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("No existe el fichero");
						e.printStackTrace();
					}					
				}
			}
			
		}
		//Escribir la ficha de los pacientes:
		public void escribirHistoria(String filename, String dni, String visita){
			try {
				for (Paciente x : pacientes) {
					if(x.getDni().equals(dni)) {
						File fichero = new File(filename+"Historia_"+x.getDni()+".txt");
						
						if(fichero.exists()) {//Si existe el fichero añado los nuevos motivos de la visita
						PrintWriter salida = new PrintWriter(fichero);
						visita=x.getHistoria()+"\n \n-Informe de la visita:  \n \t \t"+visita;
						salida.println(visita);//Añado las nuevas lineas de la nueva visita
						salida.close();

						}else {//Si no existe el fichero lo creo desde cero
							PrintWriter salida = new PrintWriter(fichero);
							salida.println("Ficha del paciente "+x.getNombre()+" "+x.getApellidos()+" (DNI: "+x.getDni()+") \n -Informe de la visita: \n \t \t"+visita);
							salida.close();
						}
					
					}	
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
	}
		
		
		//Main del programa
		public static void main(String[] args) throws IOException {
			Scanner sc = new Scanner(System.in);
			String opcion = "", dni,visita;
			Clinica c = new Clinica();

			do {
				do {
					menu();
					opcion = sc.next();
				}while(!esInt(opcion));
				switch (opcion) {
				case "1": //Listado de pacientes
					c.cargarDatos("personas.csv");
					c.listadoPacientes(c.pacientes, c.profesionales);
					break;
				case "2": //Cargar historias clinicas
					c.cargarHistorial("./src/clinicaRibera/historiales/");
					break;
				case "3": //Atender paciente
					System.out.println("Introduzca el dni ");
					dni=sc.next();
					for (Paciente x : c.pacientes) {
						if(x.getDni().equals(dni)) {
							System.out.println("El paciente es "+x.getNombre()+" "+x.getApellidos());
							System.out.println("Indique los sintomas que persenta el paciente y su diagnostico:");
							sc.nextLine();
							visita=sc.nextLine();
							c.cargarHistorial("./historiales/");//Cargo el historial del paciente en caso de existir
							c.escribirHistoria("./src/clinicaRibera/historiales/", x.getDni(), visita);
						}
					}
					break;
				case "4": //Salir
					System.out.println("Ha salido del programa");
					break;

				default:
					System.out.println("El número introducido no está dentro de las opciones posibles");;
				}
			} while (Integer.parseInt(opcion)!=4);

		sc.close();
		}
}

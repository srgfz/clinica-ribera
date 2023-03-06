package clinicaRibera;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class ClinicaVentanaGrafica extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dni;
	static ClinicaVentanaGrafica frame1;
	private JButton salir;
	private JButton btnCargarHistoriales;
	private JButton btnAtenderPaciente;
	private JTextArea textArea;
	private ArrayList<Profesional>profesionales;
	private ArrayList<Paciente>pacientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 = new ClinicaVentanaGrafica();
					frame1.setVisible(true);


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Getters y setters de los input de usuario y contraseña
		public JTextField getDni() {
			return dni;
		}
		public void setDni(JTextField dni) {
			this.dni = dni;
		}
	/**
	 * Create the frame.
	 */
	public ClinicaVentanaGrafica() {
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 477);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Reservo espacio para los arrays
			this.profesionales = new ArrayList<>();
			this.pacientes = new ArrayList<>();
		//Titulo de la ventana Login
			setTitle(" Clinica Ribera");
//		//Impedimos que el usuario pueda cambiar el tamaño de la ventana
//			setResizable(false);
		//Hacemos que la ventana aparezca centrada
			setLocationRelativeTo(null);
		//COMPONENTES DE LA VENTANA
			
			//Input del usuario
				dni = new JTextField();
				dni.setBackground(new Color(102, 102, 102));
				dni.setBounds(291, 290, 194, 30);
				contentPane.add(dni);
				dni.setColumns(10);
				dni.setForeground(Color.white);
				//Padding al input User
				dni.setBorder(BorderFactory.createCompoundBorder(dni.getBorder(), BorderFactory.createEmptyBorder(5,5,5,5)));
			//Botón de Listado de pacientes
				JButton btnListadoPacientes = new JButton("1. Listado de pacientes");
				btnListadoPacientes.setFont(new Font("Constantia", Font.BOLD, 15));
				btnListadoPacientes.setBackground(new Color(50, 205, 50));
				btnListadoPacientes.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e){ //Este es el evento que se acciona al pulsar  el botón de Listar pacientes
						//Limpiamos los AL:
						profesionales.clear();
						pacientes.clear();
						textArea.setText(null);//Limpio el text Area
						frame1.cargarDatos("personas.csv");//Cargo los datos
						frame1.listadoPacientes(frame1.pacientes, frame1.profesionales);
					}
				});
				btnListadoPacientes.setBounds(30, 52, 191, 56);
				contentPane.add(btnListadoPacientes);
			//Botón de salir
				salir = new JButton("Salir");
				salir.setFont(new Font("Constantia", Font.BOLD, 17));
				salir.setBackground(new Color(50, 205, 50));
				salir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { //Evento del botón salir
						JOptionPane.showMessageDialog(null, "Ha salido del programa", "", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0); //Para salir del programa
					}
				});
				salir.setBounds(402, 342, 121, 41);
				contentPane.add(salir);
			//Label de Dni
				JLabel labelDni = new JLabel("DNI");
				labelDni.setFont(new Font("Tahoma", Font.BOLD, 15));
				labelDni.setBounds(257, 288, 37, 30);
				contentPane.add(labelDni);
				labelDni.setForeground(Color.white);
			//Botón de cargar historiales
				btnCargarHistoriales = new JButton("2. Cargar Historiales");
				btnCargarHistoriales.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {//evento de botón cargar historiales
						textArea.setText(null);//Limpio el text Area
						try {
							frame1.cargarHistorial("./src/clinicaRibera/historiales/");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
				});
				btnCargarHistoriales.setFont(new Font("Constantia", Font.BOLD, 15));
				btnCargarHistoriales.setBackground(new Color(50, 205, 50));
				btnCargarHistoriales.setBounds(30, 168, 191, 56);
				contentPane.add(btnCargarHistoriales);
			//Botón de atender paciente
				btnAtenderPaciente = new JButton("3. Atender Paciente");
				btnAtenderPaciente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {//evento del botón atender paciente
						String visita="";
						Scanner sc = new Scanner(System.in);
						textArea.setText(null);//Limpio el textArea
						//Leo el dni:
						String dniPaciente = frame1.getDni().getText();
						for (Paciente x : frame1.pacientes) {
							if(x.getDni().equals(dniPaciente)) {
								System.out.println("El paciente es "+x.getNombre()+" "+x.getApellidos());
								System.out.println("Indique los sintomas que persenta el paciente y su diagnostico:");
								visita=sc.nextLine();
								try {
									frame1.cargarHistorial("./src/clinicaRibera/historiales/");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}//Cargo el historial del paciente en caso de existir
								frame1.escribirHistoria("./src/clinicaRibera/historiales/", x.getDni(), visita);
							}
						}
						frame1.imprimirHistoria("./src/clinicaRibera/historiales/", dniPaciente);
					}
					
				});
				btnAtenderPaciente.setFont(new Font("Constantia", Font.BOLD, 15));
				btnAtenderPaciente.setBackground(new Color(50, 205, 50));
				btnAtenderPaciente.setBounds(483, 290, 176, 30);
				contentPane.add(btnAtenderPaciente);
				
				textArea = new JTextArea();
				textArea.setEditable(false);
				textArea.setBounds(1, 1, 399, 255);
				contentPane.add(textArea);
				
				//Scroll
				JScrollPane scroll = new JScrollPane(textArea);
				scroll.setSize(380, 230);
				scroll.setLocation(315, 30);
					//Añado el Scroll al panel
					getContentPane().add(scroll, BorderLayout.CENTER);
		}
	//MÉTODOS		
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
					profesionales.add(new Profesional(linea[1], linea[2], linea[3], linea[4], linea[5], Integer.parseInt(linea[6])));
				}else {
					pacientes.add(new Paciente(linea[1], linea[2], linea[3], linea[4], Float.parseFloat(linea[7]), Integer.parseInt(linea[8]), linea[10], ""));
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

			textArea.append("*****************  "+x.getEspecialidad()+"  *****************");
			textArea.append("\n");
			textArea.append("Pacientes del Dr/Dra. "+x.getNombre()+" "+x.getApellidos()+": ");
			textArea.append("\n");

			for (Paciente y : pacientes) {
				if(y.getCita().equals(x.getEspecialidad())) {
					textArea.append("-"+y.getNombre()+" "+y.getApellidos()+" (DNI: "+y.getDni()+")");
					textArea.append("\n");

				}
			}
			textArea.append("\n");
		}
		
	}
	//Cargar datos de Historiales clínicas
	public void cargarHistorial(String filename) throws IOException {

		for (Paciente x : pacientes) {
			String nombre=filename+"Historia_"+x.getDni()+".txt";
			File fichero=new File(nombre);
			if(fichero.exists()) {//Si existe el fichero cargo los datos
				textArea.append("El archivo Hitoria_"+x.getDni()+" ya existe");
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
					textArea.append("No existe el fichero");
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
	//Imprimir Historial del paciente 
	public void imprimirHistoria(String filename, String dniPaciente) {
		for (Paciente x : pacientes) {
			if(dniPaciente.equals(x.getDni())) {
				textArea.append("\n");
				String nombre=filename+"Historia_"+x.getDni()+".txt";
				File fichero=new File(nombre);
				if(fichero.exists()) {//Si existe el fichero cargo los datos
					//Cargo los datos
					File ficheroLectura = new File(nombre);
					String cadena = ""; //Donde guardaré los datos que vaya a leer 
					Scanner entrada = null;

					try {
						entrada = new Scanner(fichero);
						while (entrada.hasNext()) { //Lee si quedan datos en el fichero
								textArea.append(entrada.nextLine());
								textArea.append("\n");

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
		
		
	}
}

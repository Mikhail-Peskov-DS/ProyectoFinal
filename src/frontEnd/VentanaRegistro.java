package frontEnd;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.Banco;
import clasesCliente.Cliente;
import clasesCliente.Cuenta;
import clasesCliente.Divisa;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class VentanaRegistro extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRegistrarse;
	private JTextField textNif;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEmail;
	private JTextField textUsuario;
	private JPasswordField password;
	private JPasswordField passwordRep;
	private JLabel lblNombreVacio;
	private JLabel lblNifVacio;
	private JLabel lblEmailVacio;
	private JLabel lblApellidoVacio;
	private JLabel lblErrorPasswdRep;
	private JLabel lblErrorPasswd;
	private JLabel lblErrorUsuario;
	private JRadioButton rdbtnDolar;
	private JRadioButton rdbtnEuro;
	private ButtonGroup bg;

	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(this);
		btnRegistrarse.setBounds(167, 365, 89, 23);
		contentPane.add(btnRegistrarse);
		
		JLabel lblNewLabel = new JLabel("Rellena el formulario para registrarte y abrir una cuenta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NIF:");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textNif = new JTextField();
		textNif.setBounds(66, 33, 86, 20);
		contentPane.add(textNif);
		textNif.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre: ");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textNombre = new JTextField();
		textNombre.setBounds(66, 58, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setBounds(10, 86, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textApellido = new JTextField();
		textApellido.setBounds(66, 83, 86, 20);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Elige el nombre de usuario y la contraseña, que vas a usar para entrar a la app");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 150, 462, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setBounds(10, 113, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		textEmail = new JTextField();
		textEmail.setBounds(66, 110, 86, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Usuario:");
		lblNewLabel_6.setBounds(10, 175, 142, 14);
		contentPane.add(lblNewLabel_6);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(167, 172, 89, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Contraseña: ");
		lblNewLabel_7.setBounds(10, 207, 142, 14);
		contentPane.add(lblNewLabel_7);
		
		password = new JPasswordField();
		password.setBounds(167, 203, 89, 23);
		contentPane.add(password);
		
		JLabel lblNewLabel_8 = new JLabel("Repite la contraseña:");
		lblNewLabel_8.setBounds(10, 244, 142, 14);
		contentPane.add(lblNewLabel_8);
		
		passwordRep = new JPasswordField();
		passwordRep.setBounds(167, 237, 89, 23);
		contentPane.add(passwordRep);
		
		lblNifVacio = new JLabel("");
		lblNifVacio.setBounds(167, 36, 257, 14);
		contentPane.add(lblNifVacio);
		
		lblNombreVacio = new JLabel("");
		lblNombreVacio.setBounds(167, 61, 305, 14);
		contentPane.add(lblNombreVacio);
		
		lblApellidoVacio = new JLabel("");
		lblApellidoVacio.setBounds(162, 86, 310, 14);
		contentPane.add(lblApellidoVacio);
		
		lblEmailVacio = new JLabel("");
		lblEmailVacio.setBounds(162, 113, 310, 14);
		contentPane.add(lblEmailVacio);
		
		lblErrorUsuario = new JLabel("");
		lblErrorUsuario.setBounds(266, 175, 206, 14);
		contentPane.add(lblErrorUsuario);
		
		lblErrorPasswd = new JLabel("");
		lblErrorPasswd.setBounds(266, 207, 206, 14);
		contentPane.add(lblErrorPasswd);
		
		lblErrorPasswdRep = new JLabel("");
		lblErrorPasswdRep.setBounds(266, 244, 206, 14);
		contentPane.add(lblErrorPasswdRep);
		
		JLabel lblNewLabel_9 = new JLabel("Divisa principal");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(113, 281, 195, 14);
		contentPane.add(lblNewLabel_9);
		
		bg = new ButtonGroup();
		
		rdbtnEuro = new JRadioButton("Euro");
		rdbtnEuro.setSelected(true);
		rdbtnEuro.setBounds(66, 302, 109, 23);
		contentPane.add(rdbtnEuro);
		
		rdbtnDolar = new JRadioButton("Dólar");
		rdbtnDolar.setBounds(315, 302, 109, 23);
		contentPane.add(rdbtnDolar);
		
		bg.add(rdbtnEuro);
		bg.add(rdbtnDolar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnRegistrarse==e.getSource()) {
			registrarCliente();
			this.setVisible(false);
			VentanaAutorizacion auth = new VentanaAutorizacion();
			auth.setVisible(true);
		}
		
	}

	private void registrarCliente() {
		if(datosCorrectos()) {
			Cliente cliente = crearCliente();
			Cuenta cuenta = crearCuenta();
			cliente.obtenerCuentaNueva(cuenta);
			Banco.nuevoCliente(cliente);
		}
		
	}

	private Cuenta crearCuenta() {
		Divisa divisa = rdbtnEuro.isSelected() ? Divisa.EURO : Divisa.DOLAR;
		Cuenta cuenta = new Cuenta();
		cuenta.setDivisa(divisa);
		cuenta.setNumero();
		return cuenta;
	}

	private Cliente crearCliente() {
		Cliente cliente = new Cliente();
		cliente.setNif(textNif.getText());
		cliente.setNombre(textNombre.getText());
		cliente.setApellido(textApellido.getText());
		cliente.setEmail(textEmail.getText());
		cliente.setNombreUsuario(textUsuario.getText());
		cliente.setPasswd(String.valueOf(password.getPassword()));
		return cliente;
	}

	private void limpiarLabels() {
		lblNifVacio.setText("");
		lblNombreVacio.setText("");
		lblApellidoVacio.setText("");
		lblEmailVacio.setText("");
		lblErrorUsuario.setText("");
		lblErrorPasswd.setText("");
		lblErrorPasswdRep.setText("");
		
	}

	@SuppressWarnings("unused")
	private boolean datosCorrectos() {
		limpiarLabels();
		boolean correcto = true;
		if(textNif.getText().isEmpty()) {
			correcto = false;
			lblNifVacio.setText("Introduce el NIF");
		} else {
			String nif = textNif.getText();
		}
		if(textNombre.getText().isEmpty()) {
			correcto = false;
			lblNombreVacio.setText("Introduce el nombre");
		} else {
			String nombre = textNombre.getText();
		}
		if(textApellido.getText().isEmpty()) {
			correcto = false;
			lblApellidoVacio.setText("Introduce el apellido");
		} else {
			String apellido = textApellido.getText();
		}
		if(textEmail.getText().isEmpty()) {
			correcto = false;
			lblEmailVacio.setText("Introduce el email");
		} else {
			String email = textEmail.getText();
		}
		if(textUsuario.getText().isEmpty()) {
			correcto = false;
			lblErrorUsuario.setText("Introduce el nombre de usuario");
		} else {
			String usuario = textUsuario.getText();
			if(Banco.usuarioExiste(usuario)) {
				correcto = false;
				lblErrorUsuario.setText("El usuario ya existe");
			}
		}
		if(String.valueOf(password.getPassword()).isEmpty()) // El getPassword devuelve un char[]
		{
			correcto = false;
			lblErrorPasswd.setText("Introduce la contraseña");
		} else {
			String passwd = String.valueOf(password.getPassword());
			if(String.valueOf(passwordRep.getPassword()).isEmpty()) {
				correcto = false;
				lblErrorPasswdRep.setText("Repite la contraseña");
			} else {
				String passwdRep = String.valueOf(passwordRep.getPassword());
				if(!passwd.equals(passwdRep)) {
					correcto = false;
					lblErrorPasswdRep.setText("Las contraseñas no coinciden");
				}
			}
		}
		return correcto;
	}
}

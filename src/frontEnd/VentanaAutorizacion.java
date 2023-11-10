package frontEnd;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import banco.Banco;
import clasesCliente.Cliente;

public class VentanaAutorizacion extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textPasswd;
	private JButton btnAcceder, btnRegistrarse;

/** Es una ventana de javax.swing que contiene un formulario de acceso a la aplicación para usuarios
 * registrados y la opción de registrar un nuevo usuario.
 * @author Mikhail Peskov
 */
	public VentanaAutorizacion(){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		iniciarComponentes();
	}

	private void iniciarComponentes() {
		JLabel lblNewLabel = new JLabel("Bienvenido al banco!");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 424, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Introduce el nombre de usuario: ");
		lblNewLabel_1.setBounds(10, 53, 194, 14);
		contentPane.add(lblNewLabel_1);

		textUsuario = new JTextField();
		textUsuario.setBounds(214, 50, 119, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Introduce la contraseña:");
		lblNewLabel_2.setBounds(10, 78, 194, 14);
		contentPane.add(lblNewLabel_2);

		textPasswd = new JTextField();
		textPasswd.setBounds(214, 75, 119, 20);
		contentPane.add(textPasswd);
		textPasswd.setColumns(10);

		btnAcceder = new JButton("Acceder");
		btnAcceder.addActionListener(this);
		btnAcceder.setBounds(164, 112, 95, 49);
		contentPane.add(btnAcceder);

		JLabel lblNewLabel_3 = new JLabel("Y si no estás registrado: ");
		lblNewLabel_3.setBounds(148, 171, 128, 14);
		contentPane.add(lblNewLabel_3);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(this);
		btnRegistrarse.setBounds(168, 196, 91, 38);
		contentPane.add(btnRegistrarse);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnAcceder==e.getSource()) {
			acceder();
		}
		if(btnRegistrarse==e.getSource()) {
			registrarse();
		}

	}

	private void registrarse() {
		VentanaRegistro ventanaRegistro = new VentanaRegistro();
		this.setVisible(false);
		ventanaRegistro.setVisible(true);

	}

	private void acceder() {
		if(datosCorrectos()) {
			Cliente cliente = Banco.getDatosCliente(textUsuario.getText());
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(cliente);
			ventanaPrincipal.setVisible(true);
			this.setVisible(false);
		}

	}

	private boolean datosCorrectos() {
		boolean correcto = false;
		String nombreUsuario, passwdUsuario;
		if(!(nombreUsuario=textUsuario.getText()).isEmpty()) {
			if(Banco.usuarioExiste(nombreUsuario)) {
				if(!(passwdUsuario=textPasswd.getText()).isEmpty()) {
					correcto = Banco.comprobarPasswd(nombreUsuario, passwdUsuario);
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Usuario no encontrado");			}
		} else {
			JOptionPane.showMessageDialog(null, "Introduce el nombre de usuario");
		}
		return correcto;
	}
}

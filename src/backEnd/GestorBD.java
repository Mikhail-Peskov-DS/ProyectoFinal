package backEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;

import javax.swing.JOptionPane;

import clasesCliente.Cliente;
import clasesCliente.Cuenta;

/**Clase abstracta que gestiona todos los accesos a las bases de datos de banco y de los usuarios
 * @author Mikhail Peskov
 */
public abstract class GestorBD {
	private static String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	private static Connection conBanco;

	public static Connection conectarBanco(String url) {
		try {
			if(conBanco==null) {
				Class.forName(GestorBD.driver);
				conBanco = DriverManager.getConnection(url);
				JOptionPane.showMessageDialog(null, "Conexion Establecida!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fallo en la conexion!\n"+e);
		}
		return conBanco;
	}

	/** Método generico para crear una tabla usando la conexión necesaria y la sentencia SQL
	 * @author Mikhail Peskov
	 * @param java.sql.Connection con
	 * @param String sql
	 */
	public static void crearTabla(String url, String sql) {
		try {
			try {
				Class.forName(GestorBD.driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conBanco = DriverManager.getConnection(url+";newDatabaseVersion=V2010");
			Statement st = conBanco.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	/**Método que devuelve true si el usuario con el login especificado en los parámetros existe en la
	 * base de datos del banco
	 * @author Mikhail Peskov
	 * @param String usuario
	 * @return boolean
	 */
	public static boolean encontrarUsuario(String usuario) {
		boolean correcto = false;
		String sql = "SELECT nif FROM clientes WHERE nombre_usuario='" + usuario + "'";
		try {
			Statement st = conBanco.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				correcto = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return correcto;
	}

	/** El método que comprueba si la contraseña introducida por el usuario coincide con la que
	 * está en la base de datos del banco
	 * @author Mikhail Peskov
	 * @param String nombreUsuario
	 * @param String passwdUsuario
	 * @return boolean
	 */
	public static boolean comprobarPasswd(String nombreUsuario, String passwdUsuario) {
		boolean correcto = false;
		String sql = "SELECT passwd FROM clientes WHERE nombre_usuario='" + nombreUsuario +"'";
		try {
			Statement st = conBanco.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				correcto = passwdUsuario.equals(rs.getString(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return correcto;
	}

	/**Devuelve el nif, el nombre y el apellido del cliente con el login especificado
	 * @author Mikhail Peskov
	 * @param String usuario
	 * @return String[3] datos
	 */
	public static String[] getDatosCliente(String usuario) {
		String sql = "SELECT nif, nombre, apellido, email FROM clientes WHERE nombre_usuario='" + usuario +"'";
		String[] datos = new String[4];
		try {
			Statement st = conBanco.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return datos;
	}

	public static void registrarCliente(Cliente cliente) {
		String sql = "INSERT INTO clientes(nif,nombre,apellido,email,nombre_usuario,passwd) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = conBanco.prepareStatement(sql);
			pst.setString(1, cliente.getNif());
			pst.setString(2, cliente.getNombre());
			pst.setString(3, cliente.getApellido());
			pst.setString(4, cliente.getEmail());
			pst.setString(5, cliente.getNombreUsuario());
			pst.setString(6, cliente.getPasswd());
			int n = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, n > 0 ? "Registrado" : "Error en registro");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void registrarCuenta(Cliente cliente) {
		String sql = "INSERT INTO cuentas(numero_cuenta,nif_cliente) values(?,?);";
		try {
			PreparedStatement pst = conBanco.prepareStatement(sql);
			LinkedHashSet<Cuenta> cuentas = cliente.getCuentas();
			Cuenta ultimaCuenta = new Cuenta();
			for(Cuenta c : cuentas) {
				ultimaCuenta = c;
			}
			pst.setString(1, ultimaCuenta.getNumero());
			pst.setString(2, cliente.getNif());
			pst.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


}

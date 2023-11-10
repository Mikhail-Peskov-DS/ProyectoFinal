package banco;

import java.io.File;
import java.sql.Connection;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import backEnd.GestorBD;
import clasesCliente.Cliente;
import clasesCliente.Divisa;

/** Es una clase que sirve como una capa intermedia entre el cliente y la base de datos, que controla
 * los accesos. Solo puede existir una única instancia de esta clase (Singleton)
 * @author Mikhail Peskov
 */
public final class Banco {
	private static final String PATHBD = "DAT\\dbRoot\\clientes.accdb";
	private static Banco instance;
	@SuppressWarnings("unused")
	private Connection con;

	/* El constructor es privado, para que no se pueda accederlo directamente */
	private Banco() {
		try {
			this.setCon(getConexion());
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	private void setCon(Connection conexion) {
		this.con = conexion;

	}

	private Connection getConexion() {
		File f = new File(Banco.PATHBD);
	    if (!f.exists()) {
	        crearTablaClientes();
	    }
	    return GestorBD.conectarBanco("jdbc:ucanaccess://"+PATHBD);

	}

	/** Es el método que crea el objeto de banco si no existe uno y lo devuelve en cualquier caso
	 * (Singleton)
	 *  @author Mikhail Peskov
	 * @return Banco
	 */
	 public static Banco getInstance() {
	        if (instance == null) {
	            instance = new Banco();
	        }
	        return instance;
	 }

	/** El método para crear la tabla de los clientes del banco, si aún no existe
	 * @author Mikhail Peskov
	 */
	private void crearTablaClientes() {
		File f = new File(PATHBD);
		if (!f.getParentFile().exists()) {
		    f.getParentFile().mkdirs();
		}
		String sql = "CREATE TABLE clientes (nif VARCHAR(8) PRIMARY KEY,"
										+ "nombre_usuario VARCHAR(255) UNIQUE,"
										+ "passwd VARCHAR(255),"
										+ "nombre VARCHAR(255),"
										+ "apellido VARCHAR(255),"
										+ "email VARCHAR(255));";
		GestorBD.crearTabla("jdbc:ucanaccess://"+ PATHBD, sql); //Las acciones sobre las BBDD se hacen solo en la clase GestorBD
		sql = "CREATE TABLE cuentas (numero_cuenta VARCHAR(24) PRIMARY KEY,"
									+ "nif_cliente VARCHAR(255));";
		GestorBD.crearTabla("jdbc:ucanaccess://" + PATHBD, sql);


	}

	/** Devuelve el resultado de búsqueda de un nombre de usuario
	 * @author Mikhail Peskov
	 * @param String usuario
	 * @return boolean
	 */
	public static boolean usuarioExiste(String usuario) {
		boolean correcto = GestorBD.encontrarUsuario(usuario);
		return correcto;
	}

	/** Devuelve el resultado de comparación entre la contraseña de un usuario con lo que el usuario está
	 * introduciendo
	 * @author Mikhail Peskov
	 * @param String nombreUsuario
	 * @param String passwdUsuario
	 * @return boolean
	 */
	public static boolean comprobarPasswd(String nombreUsuario, String passwdUsuario) {
		boolean correcto = GestorBD.comprobarPasswd(nombreUsuario, passwdUsuario);
		return correcto;
	}

	/** Metodo que crea y devuelve un objeto de la clase Cliente a partir de los datos de la base de datos
	 * según el login
	 * @author Mikhail Peskov
	 * @param String usuario
	 * @return Cliente cliente
	 */
	public static Cliente getDatosCliente(String usuario) {
		Cliente cliente = new Cliente();
		String[] infoCliente = GestorBD.getDatosCliente(usuario);
		cliente.setNif(infoCliente[0]);
		cliente.setNombre(infoCliente[1]);
		cliente.setApellido(infoCliente[2]);
		cliente.setEmail(infoCliente[3]);
		return cliente;
	}

	public static void nuevoCliente(Cliente cliente) {
		GestorBD.registrarCliente(cliente);
		GestorBD.registrarCuenta(cliente);
		
	}

	public static String generarNumeroCuenta(Divisa divisa) {
		String letras, digitosControl, entidadBancaria, sucursal, digitosControlFinal, numero;
		switch(divisa) {
			case EURO -> letras = "EU";
			case DOLAR -> letras = "US";
			default -> letras = "UN";
		}
		digitosControl = "00";
		entidadBancaria = "9999";
		sucursal = "0001";
		digitosControlFinal = "00";
		long min = 1000000000L;
		long max = 9999999999L;
		long randomNum = ThreadLocalRandom.current().nextLong(min, max + 1);
		numero = String.valueOf(randomNum);
		return letras + digitosControl + entidadBancaria + sucursal + digitosControlFinal + numero;
	}


}

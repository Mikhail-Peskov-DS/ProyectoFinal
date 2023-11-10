package clasesCliente;

import java.util.LinkedHashSet;

/**Esta clase guarda los detalles típicos de un cliente del banco (nif, nombre, apellido y población).
 * Además, tiene dos LinkedHashSets que guardan las listas de las cuentas y las tarjetas asociadas.
 * @author Mikhail Peskov
 */
public class Cliente {
	private String nif, nombre, apellido, email, nombreUsuario, passwd;
	private LinkedHashSet<Tarjeta> tarjetas;
	private LinkedHashSet<Cuenta> cuentas;

	public Cliente() {
		this.nif = null;
		this.nombre = null;
		this.apellido = null;
		this.email = null;
		this.nombreUsuario = null;
		this.passwd = null;
		this.tarjetas = new LinkedHashSet<Tarjeta>();
		this.cuentas = new LinkedHashSet<Cuenta>();
	}

	public String getNif() {
		return nif;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public LinkedHashSet<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public LinkedHashSet<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void obtenerCuentaNueva(Cuenta cuenta) {
		this.cuentas.add(cuenta);
	}

	public void obtenerTarjetaNueva(Tarjeta targeta) {
		this.tarjetas.add(targeta);
	}


}

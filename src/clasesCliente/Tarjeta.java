package clasesCliente;

import java.util.Objects;

import javax.swing.JOptionPane;

/** Esta clase guardará los datos de una tarjeta bancária: la cuenta asociada, el numero, el código PIN
 *  y el saldo disponible. También, controla si la tarjeta está activa.
 *  Tiene los métodos para asignar un pin nuevo o cambiarlo.
 * @author Mikhail Peskov
 */
public class Tarjeta {
	private Cuenta cuenta;
	private String numero;
	private double saldo;
	private String pin;
	private boolean activa;

	public Tarjeta(Cuenta cuenta) {
		this.cuenta = cuenta;
		this.saldo = this.cuenta.getSaldo();
		this.numero = null;
		this.pin = null;
		this.activa = false;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean esActiva() {
		return this.activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	/**Este método asigna un código pin nuevo a la targeta, si es correcto o repite la petición hasta
	 * que todo esté correcto.
	 * @author Mikhail Peskov
	 */
	public void setPin() {
		boolean correcto = false;
		while(!correcto) {
			String pinString = JOptionPane.showInputDialog("Introduce el código PIN nuevo: ");
			if(pinEstaBien(pinString)) {
				correcto = true;
				this.pin = pinString;
			}
		}
	}

	/** El método sirve para cambiar el PIN de la targeta.
	 * Comprueba si sabes el pin antiguo en tres intentos y si todo está correcto, cambia el PIN.
	 * Si no, bloquea la tarjeta.
	 */
	public void cambiarPin() {
		byte intentos = 3;
		while(intentos > 0) {
			String antiguo = JOptionPane.showInputDialog("Introduce tu código pin");
			if (antiguo.equals(this.pin)) {
				setPin();
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Te quedan " + intentos + " intentos");
				intentos--;
			}
		}
		if(intentos == 0) {
			JOptionPane.showMessageDialog(null, "Pareces un hácker, la tarjeta está bloqueada");
			setActiva(false);
		}
	}


	/** Este método comprueba si un nuevo código pin es válido o no
	 * @author Mikhail Peskov
	 * @param pinString
	 * @return boolean
	 */
	private boolean pinEstaBien(String pinString) {
		boolean correcto = false;
		if(pinString.length() < 4) {
			JOptionPane.showMessageDialog(null, "El pin es demasiado corto");
		} else if (pinString.length() > 6) {
			JOptionPane.showMessageDialog(null, "El pin es demasiado largo");
		} else {
			try {
				int i = Integer.parseInt(pinString);
				if(i < 0) {
					JOptionPane.showMessageDialog(null, "El pin debe ser un número positivo");
				} else {
					correcto = true;
				}
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "El pin debe ser numérico");
			}
		}
		return correcto;
	}

	@Override
	//Solo nos interresa el numero de tarjeta para decir si dos tarjetas son iguales o no
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	//Solo nos interresa el numero de tarjeta para decir si dos tarjetas son iguales o no
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Tarjeta other = (Tarjeta) obj;
		return Objects.equals(numero, other.numero);
	}


}

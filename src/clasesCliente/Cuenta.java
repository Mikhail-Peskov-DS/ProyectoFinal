package clasesCliente;

import java.util.LinkedHashSet;
import java.util.Objects;

import banco.Banco;

/** Es una clase que guarda el número de la cuenta bancária, el saldo disponible, la divisa de la cuenta
 * y un Set de transferencias realizadas desde su creación
 * @author Mikhail Peskov
 */
public class Cuenta {
	private String numero;
	private Divisa divisa;
	private double saldo;
	private LinkedHashSet<Transferencia> transferencias;

	public Cuenta() {
		this.numero = null;
		this.saldo = 0.00;
		this.transferencias = new LinkedHashSet<Transferencia>();
		this.divisa = Divisa.DEFAULT;
	}

	public String getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public LinkedHashSet<Transferencia> getTransferencias() {
		return transferencias;
	}

	public void setNumero() {
		this.numero = Banco.generarNumeroCuenta(this.divisa);
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public void setTransferencias(LinkedHashSet<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(numero, other.numero);
	}

}

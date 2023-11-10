package clasesCliente;

import java.util.Date;
import java.util.Objects;

/** Es una clase que guardará el origen y el destino de la transferencia bancária
 * Tambieán guarda la cantidad transferida junto a la divisa de la operación.
 * @author Mikhail Peskov
 */
public class Transferencia {
	private Cuenta origen, destino;
	private double cantidad;
	private String divisa;
	private Date fecha;

	public Transferencia() {
		this.origen = null;
		this.destino = null;
		this.cantidad = Double.NaN;
		this.divisa = null;
		this.fecha = new Date(); // La fecha de creación se asigna automáticamente
	}

	public Cuenta getOrigen() {
		return origen;
	}

	public Cuenta getDestino() {
		return destino;
	}

	public double getCantidad() {
		return cantidad;
	}

	public String getDivisa() {
		return divisa;
	}
	public Date getFecha() {
		return this.fecha;
	}

	public void setOrigen(Cuenta origen) {
		this.origen = origen;
	}

	public void setDestino(Cuenta destino) {
		this.destino = destino;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	@Override
	// Usamos todos los campos de la clase para decir si dos transferencias son iguales o no
	public int hashCode() {
		return Objects.hash(cantidad, destino, divisa, fecha, origen);
	}

	@Override
	// Usamos todos los campos de la clase para decir si dos transferencias son iguales o no
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Transferencia other = (Transferencia) obj;
		return Double.doubleToLongBits(cantidad) == Double.doubleToLongBits(other.cantidad)
				&& Objects.equals(destino, other.destino) && Objects.equals(divisa, other.divisa)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(origen, other.origen);
	}


}

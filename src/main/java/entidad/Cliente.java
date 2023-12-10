package entidad;

import java.sql.Date;

public class Cliente {
	
	private String dni;
	private String nombre;
	private String apellido;
	private String cuil;
	private String sexo;
	private Pais nacionalidad;
	private Date fecha_nac; 
	private String direccion;
	private Localidad localidad;
	private Provincia provincia;
	private Pais pais;
	private String correo_electronico;
	private String telefonos;
	private boolean estado;

	public Cliente() {	}

	public Cliente(String dni, String nombre, String apellido, String cuil, String sexo, Pais nacionalidad,
			Date fecha_nac, String direccion, Localidad localidad, Provincia provincia, Pais pais,
			String correo_electronico, String telefonos, boolean estado) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuil = cuil;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fecha_nac = fecha_nac;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
		this.correo_electronico = correo_electronico;
		this.telefonos = telefonos;
		this.estado = estado;
	}
	
	

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Pais getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", cuil=" + cuil + ", sexo="
				+ sexo + ", nacionalidad=" + nacionalidad + ", fecha_nac=" + fecha_nac + ", direccion=" + direccion
				+ ", localidad=" + localidad + ", provincia=" + provincia + ", pais=" + pais + ", correo_electronico="
				+ correo_electronico + ", telefonos=" + telefonos + ", estado=" + estado + "]";
	}

	

	
}

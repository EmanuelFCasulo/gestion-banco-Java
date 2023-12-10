package negocioImpl;

import java.util.List;

import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

	ClienteDao cDao = new ClienteDaoImpl();

	public boolean Insert(Cliente cliente) {

		boolean estado = false;
		/*if (cliente.getDni() > -1 && cliente.getDescripcion().trim().length() > 0 && cliente.getIdTipo() > -1
				&& cliente.getCostoContratacion() > 0 && cliente.getCostoAsegurado() > 0) {*/
			estado = cDao.Insert(cliente);
		//}
		return estado;
	}
	
	public boolean Update(Cliente cliente) {

		boolean estado = false;
		estado = cDao.Update(cliente);
		
		return estado;
	}

	public boolean EliminacionLogica(Cliente cliente_a_eliminar) {
		boolean estado = false;
		try {
			if (cliente_a_eliminar.getDni() != null) {
				estado = cDao.EliminacionLogica(cliente_a_eliminar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}


	public List<Cliente> BuscarTodos() {

		List<Cliente> lClientes;
		lClientes = cDao.BuscarTodos();
		return lClientes;
	}
	
	
	public Cliente BuscarUno(String dni) {

		Cliente cliente;
		cliente = cDao.BuscarUno(dni);
		return cliente;
	}


	public String BuscarUltimo() {
		System.out.print(cDao.BuscarUltimo());
		return cDao.BuscarUltimo();
	}
	
	public int ContarActivo() {

		int cant;
		cant = cDao.ClientesActivos();
		return cant;
	}


}
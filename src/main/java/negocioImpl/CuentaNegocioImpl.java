package negocioImpl;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.env.ISourceMethod;

import dao.ClienteDao;
import dao.CuentaDao;
import dao.TipoCuentaDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.CuentaDaoImpl;
import daoImpl.TipoCuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {

	CuentaDao cDao = new CuentaDaoImpl();

	public boolean Insert(Cuenta Cuenta) {

		boolean estado = false;
			estado = cDao.Insert(Cuenta);
		//}
		return estado;
	}


	public List<Cuenta> BuscarTodos() {

		List<Cuenta> lCuentas;
		lCuentas = cDao.BuscarTodos();
		return lCuentas;
	}


	public int BuscarUltimo() {
		System.out.print(cDao.BuscarUltimo());
		return cDao.BuscarUltimo();
	}

	public List<Cuenta> BuscarClienteDni(String dni){
		List<Cuenta> lCuentas_x_usuario;
		lCuentas_x_usuario = cDao.BuscarClienteDni(dni);
		return lCuentas_x_usuario; 
	}

	public Cuenta BuscarUno(int nroCta){
		Cuenta cta = new Cuenta();
		cta = cDao.BuscarUno(nroCta);
		return cta; 
	}
	
	public Cuenta BuscarCbu(String cbu){
		Cuenta cta = new Cuenta();
		cta = cDao.BuscarCbu(cbu);
		return cta; 
	}



	public boolean Update(Cuenta cuenta) {

		boolean estado=false;
		if( cuenta.getNroCuenta()  > 0 )
		{
			estado=cDao.Update(cuenta);
		}
		return estado;
	}
	
	
	public boolean Delete(Cuenta cuenta) {

		boolean estado=false;
		try
		{
			if( cuenta.getNroCuenta()  > 0 )
			{
				estado=cDao.EliminacionLogica(cuenta);
			}
		}
		catch(Exception e) {
            e.printStackTrace();
}
		return estado;
	}


	@Override
	public boolean Insert(String dni, int tc) {


		CuentaDao cDao = new CuentaDaoImpl();
		ClienteDao clienteDao = new ClienteDaoImpl();
		TipoCuentaDao tcDao = new TipoCuentaDaoImpl();
		Cuenta  c = new Cuenta();
		boolean agregado;
		ArrayList<Cuenta> lCuenta = (ArrayList<Cuenta>) cDao.BuscarClienteDni(dni); 	

		///verificamos realmente que exista ese dni
		
		try {
			c.setDni(clienteDao.BuscarUno(dni));
			c.setTipoCuenta(tcDao.BuscarUna(tc));
					
			long cbuNuevo = Long.parseLong(clienteDao.BuscarUno(dni).getDni().toString());
			ArrayList<Cuenta> todas = (ArrayList<Cuenta>)cDao.BuscarTodos();
			int x;
			
			if(lCuenta.isEmpty()) {
				for(x=0; x<todas.size(); x++) {
					if(todas.get(x).getCbu()==cbuNuevo) {
						cbuNuevo++;
						x=0;
						}
					}
				c.setCbu(cbuNuevo);
				}
			else{
				int tam = lCuenta.size();
				if(tam==1) {
					cbuNuevo++;
					for(x=0; x<todas.size(); x++) {
						if(todas.get(x).getCbu()==cbuNuevo){
							cbuNuevo++;
							x=0;
							}
						}
					c.setCbu(cbuNuevo);
					}
				if(tam==2) {
					cbuNuevo+=2;
					for(x=0; x<todas.size(); x++) {
						if(todas.get(x).getCbu()==cbuNuevo){
							cbuNuevo++;
							x=0;
							}
						}
					c.setCbu(cbuNuevo);
					}
				}
			System.out.println(c.getDni().getDni());
			System.out.println(c.getCbu());
			System.out.println(c.getTipoCuenta().getCodTipo());
			return cDao.Insert(c);

		}
		
		catch(Exception e) {
			e.printStackTrace();
			}
		return false;
		}
	
	public boolean VerificarCliente(String dni) {
		ClienteDao clienteDao = new ClienteDaoImpl();
		try										
		{
		return clienteDao.BuscarUno(dni).getDni().contains(dni);
		}
		catch(Exception e) {
           return false;
}
	}
	public boolean VerificarMaxCuentas(String dni) {
		try
		{
		CuentaDao cDao = new CuentaDaoImpl();
		ArrayList<Cuenta> lCuenta = (ArrayList<Cuenta>) cDao.BuscarClienteDni(dni);
       int i=0;
       if(lCuenta !=  null)
       {
       for(Cuenta c:lCuenta)
       {
    	   if(c.isEstado())
    		   i++;
       }
		if(i>=3)
		{
			return true;
		}
       }
		return false;
		}
		catch(Exception  e)
		{
			return false;
		}
	}


	@Override
	public List<Cuenta> BuscarClienteNroCta(int nroCta) {
		List<Cuenta> lCuentas_x_usuario;
		lCuentas_x_usuario = cDao.BuscarClienteNroCta(nroCta);
		return lCuentas_x_usuario; 
	}
}
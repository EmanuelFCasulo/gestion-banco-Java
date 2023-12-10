package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.CallableStatement;

import dao.TransferenciaDao;
import entidad.Cuenta;
import entidad.TipoMovimiento;

public class TransferenciaDaoImpl implements TransferenciaDao{
	private static final String transferir = "{CALL SP_TRANSFERENCIA(?,?,?,?,?,?,?)}";
	
	public Boolean CrearTrans(Cuenta CtaOrigen, Cuenta CtaDestino, BigDecimal importe, BigDecimal saldoOrigen, BigDecimal SaldoDestino, TipoMovimiento TMov, String detalle){
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false; 
		try {
	
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();   //setInt setDouble
		}
		try {
			 CallableStatement cs = (CallableStatement) conexion.prepareCall(transferir);
			cs.setInt(1, CtaOrigen.getNroCuenta());
			cs.setInt(2, CtaDestino.getNroCuenta());
			cs.setBigDecimal(3, importe); 
			cs.setBigDecimal(4, saldoOrigen);
			cs.setBigDecimal(5, SaldoDestino);
			cs.setInt(6,TMov.getCodTipo());
			cs.setString(7,detalle);
			
			
			
			if (cs.executeUpdate() == 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error de base de datos al ejecutar SP_TRANSFERENCIA");
		}

		return isInsertExitoso;
		
	}
}

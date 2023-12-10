package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.CallableStatement;

import dao.CuotasDao;

public class CuotasDaoImpl implements CuotasDao{
	private static final String pagarCuota = "{CALL SP_PAGO_CUOTA(?,?,?,?,?)}";
	
	
	public boolean pagarCuota(int NroCuenta,int idCuota, BigDecimal saldo, String detalle) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
	
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();   //setInt setDouble
		}
		try {
			 CallableStatement cs = (CallableStatement) conexion.prepareCall(pagarCuota);
			cs.setInt(1, idCuota);
			cs.setInt(2, NroCuenta);
			cs.setInt(3, 3);
			cs.setBigDecimal(4,saldo);
			cs.setString(5, detalle);
			if (cs.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
			else {
				conexion.rollback();
			}
		} catch (SQLException e) {

			System.out.println("Error de base de datos al ejecutar SP_TRANSFERENCIA");
		}
		return isInsertExitoso;	
	}
}

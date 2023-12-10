package excepciones;

import java.sql.SQLException;

public class SaldoCuenta extends RuntimeException {

    public SaldoCuenta() { }

    @Override
    public String getMessage() {
        return "El saldo de la cuenta debe ser 0";
        
    }
}


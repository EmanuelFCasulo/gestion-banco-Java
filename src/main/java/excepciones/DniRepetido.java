package excepciones;

import java.sql.SQLException;

public class DniRepetido extends SQLException {

    public DniRepetido() { }

    @Override
    public String getMessage() {
        return "Ya hay un usuario registrado con ese DNI";
        
    }

}

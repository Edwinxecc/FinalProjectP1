package src;
import java.util.Random;
public class Facturacion {

    String cedula;
    String nombre;
    int numAsientos;

    public Facturacion(String cedula, String nombre, int numAsientos){
        this.cedula = cedula;
        this.nombre = nombre;
        this.numAsientos = numAsientos;
    }

    public String GenerarMail(){
        String email = nombre.charAt(0) + "";
        if (nombre.length() > 10) {
            for (int i = 1; i < nombre.length(); i++) {
                if (nombre.charAt(i)+"" == " ") {
                    email += nombre.charAt(i+1);
                }
            }
            return email;
        }
        return "nomail@cliente.mail";
    }

}
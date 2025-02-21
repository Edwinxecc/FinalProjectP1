package src;
import java.util.Random;
public class Facturacion {

    private String cedula;
    private String nombre;
    private int numAsientos;

    public Facturacion(String cedula, String nombre, int numAsientos){
        this.cedula = cedula;
        this.nombre = nombre;
        this.numAsientos = numAsientos;
    }

    public String GenerarMail(){
        String email = nombre.charAt(0) + "";
        String dominio = "@cliente.mail", temp;
        if (nombre.length() > 10) {
            for (int i = 1; i < nombre.length(); i++) {
                temp = nombre.charAt(i) + "";
                if (temp.equalsIgnoreCase(" ")) {
                    email += nombre.charAt(i+1);
                }
            }
            return email + dominio;
        }
        return "nomail@cliente.mail";
    }

}
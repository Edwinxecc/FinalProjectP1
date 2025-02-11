package src;
import java.util.Scanner;
public class mainProject {

    public static boolean ValidarUsuario(String usr, String pass){
        return (usr.equalsIgnoreCase("code") && pass.equalsIgnoreCase("1234"));
    }
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String user, pass;
        boolean acces = false;
        // validacion de usuario
        int intentos=0;
        do {
            System.out.println("---------------------------------------");
            System.out.println("Intento numero: " + (intentos+1));
            System.out.print("Por favor ingresa tu usuario: ");
            user = entrada.next();
            System.out.print("Por favor ingresa tu clave: ");
            pass = entrada.next();
            if (acces = ValidarUsuario(user, pass)) {
                break;
            }
            //System.out.println(acces);
            intentos++;

        } while (acces == false && intentos != 3);
        entrada.close();
    }
}

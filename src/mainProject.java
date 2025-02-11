package src;
import java.util.Scanner;
public class mainProject {

    public static void ValidarUsuario(String usr, String pass){
        return (usr.equalsIgnoreCase("code")|| pass.equalsIgnoreCase("1234"));
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String user, pass;
        boolean acces = false;
        // validacion de usuario
        int i=1;
        do {
            System.out.print("Por favor ingresa tu usurio: ");
            user = entrada.next();
            System.out.print("Por favor ingresa tu clave: ");
            pass = entrada.next();
            acces = ValidarUsuario(user, pass);
            i++;

        } while (acces == true || i == 3);
        entrada.close();
    }
}

package src;
import java.util.Scanner;
public class mainProject {
    public static boolean ValidarCedula(String cedual){
        int valorFijo[] = {2,1,2,1,2,1,2,1,2};
        int cedulaLista[] = new int[9];
        int digitoInicial = Character.getNumericValue(cedual.charAt(cedual.length()-1));
        int multi[] = new int[9];
        int suma = 0, digitoVer = -1;

        for (int i = 0; i < ((cedual.length())-1); i++) {
            cedulaLista[i] = Character.getNumericValue(cedual.charAt(i));
        }

        for (int i = 0; i < cedulaLista.length; i++) {
            multi[i] = valorFijo[i] * cedulaLista[i];
            if (multi[i] > 9) {
                multi[i] = multi[i] - 9;
            }
            suma+=multi[i];
        }
        if (suma % 10 == 0) {
            digitoVer = 0;
        }else{
            for (int i = 1; i <= 9; i++) {
                if (suma > (i*10)) {
                    digitoVer = suma - ((i+1) * 10);
                    if (digitoVer < 0) {
                        digitoVer = digitoVer * -1;
                    }
                }
            }
        }

        return digitoVer == digitoInicial;
    }

    public static boolean ValidarUsuario(String usr, String pass){
        return (usr.equalsIgnoreCase("code") && pass.equalsIgnoreCase("1234"));
    }
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String user, pass, cedulaString;
        boolean acces = false;
        //validar cedula
        System.out.println("Ingresa tu numero de cedula: ");
        cedulaString = entrada.next();
        System.out.println(ValidarCedula(cedulaString));
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

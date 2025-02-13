package src;
import java.util.Scanner;


public class mainProject {

    public static void AsignarAsientoUnico(){

    }

    public static void AsignarAsientoPareja(){

    }

    public static void ImprimirEstadoBus(int [][] normales, int [] finBus){
        System.out.println(" ");
        for (int i = 0; i < normales.length; i++) {
            System.out.print("|");
            for (int j = 0; j < normales[i].length; j++) {
                System.out.print(normales[i][j] + "|");
                if (j==1) {
                    System.out.print(" --- |");
                }
            }
            System.out.println(" ");
        }

        for (int i = 0; i < finBus.length; i++) {
            System.out.print("|");
            System.out.print(finBus[i]);
            System.out.print("|");
        }
    }

    public static void LimpiarPantalla(){
        try {
            String sistemaOperativo = System.getProperty("os.name").toLowerCase();
            if (sistemaOperativo.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static boolean ValidarUsuario(String usr, String pass, String cedula){
        return (usr.equalsIgnoreCase("code") && pass.equalsIgnoreCase("1234") && ValidarCedula(cedula));
    }
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String user, pass, cedulaString;
        boolean acces = false;
        int optionAsiento;
        //mensajes de incio
        System.out.println("Bienvenido al Sistema de compra de voletos");
        System.out.println("                en un Bus");
        System.out.println("A continuacion tines 3 faces de verificacion");
        // asientos de bus
        // definimos la parte trasera de 5 asientos en un bus
        int finBus[] = {0,0,0,0,0};
        // con una matriz definimos los asientos normales
        int normales[][] = {
            {1,0,0,0},
            {0,1,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,1,0},
            {0,0,0,0},
            {0,1,0,0},
            {0,1,0,1}
        };
        // validacion de usuario
        int intentos=0;
        do {
            System.out.println("");
            System.out.println("---------------------------------------");
            System.out.println("Intento numero: " + (intentos+1));
            System.out.print("Por favor ingresa tu usuario: ");
            user = entrada.next();
            System.out.print("Por favor ingresa tu clave: ");
            pass = entrada.next();
            System.out.println("Ingresa tu numero de cedula: ");
            cedulaString = entrada.next();
            if (acces = ValidarUsuario(user, pass, cedulaString)) {
                break;
            }
            //System.out.println(acces);
            intentos++;

        } while (acces == false && intentos != 3);
        // continuamos 
        if (acces) {
            LimpiarPantalla();
            System.out.println("El bus cuenta con los siguientes asientos: ");
            ImprimirEstadoBus(normales, finBus);
            System.out.println("");
            System.out.println("Seleccione una opcion: ");
            System.out.println("[1] Asiento Unico.");
            System.out.println("[2] Asiento en Pareja.");
            optionAsiento = entrada.nextInt();
            switch (optionAsiento) {
                case 1:
                    LimpiarPantalla();
                    System.out.println("Proceso de Compra de asiento unico.");
                    AsignarAsientoUnico();
                    break;
                case 2:
                    LimpiarPantalla();
                    System.out.println("Proceso de Compra de asiento en Pareja.");
                    AsignarAsientoPareja();
                    break;
                default:
                    System.out.println("Seleccione una opcion correcta");
                    break;
            }
        } 

        entrada.close();
    }
    
}

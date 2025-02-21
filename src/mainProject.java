package src;
import java.util.Scanner;
import java.io.*;

public class mainProject {

    public static int [][] AsignarAsientoPareja(int [][] matriz){
        // fue mejor analisar las columnas sin bucles jaja
        int a = -1, b = -1, c=-1, d=-1;
        for (int i = 0; i < matriz.length; i++) {
            //izquierda
            a = matriz[i][0];
            b = matriz[i][1];
            if (a == 0 && b == a) {
                matriz[i][0] = 1;
                matriz[i][1] = 1;
                return matriz;
            }
            //derecha de bus
            c = matriz[i][2];
            d = matriz[i][3];
            if (c == 0 && d == c) {
                matriz[i][2] = 1;
                matriz[i][3] = 1;
                return matriz;
            }
        }
        return matriz;
    }

    public static int AsientosLibres(int [][] matriz){
        int suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                suma += matriz[i][j];
            }
        }
        suma = (36 - suma);
        return suma;
    }

    public static void CrearNuevoBus(int [][] matriz, String rutaArchivo){
        EliminarBus(); // solo es para eliminar cualquier registro anterior
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (int i = 0; i < matriz.length; i++) {
                
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < matriz[i].length; j++) {
                    sb.append(matriz[i][j]);
                    if (j < matriz[i].length - 1) sb.append(","); 
                }
                writer.write(sb.toString());
                writer.newLine(); 
            }
            System.out.println("");
            System.out.println("Creacion de nuevo bus exitosa!");
        } catch (IOException e) {
            System.err.println("Error al guardar la matriz: " + e.getMessage());
        }
    }

    public static void CrearArchivo(){
        String rutaArchivo = "bsd.txt";
        try {
            File archivo = new File(rutaArchivo);
            if (archivo.createNewFile()) {
                System.out.println("--------------------");
                System.out.println("Archivo creado: " + archivo.getName());
                System.out.println("--------------------");
            }else{
                System.out.println("--------------------");
                System.out.println("El archivo ya existe.");
                System.out.println("--------------------");
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public static void EliminarBus(){
        String rutaArchivo = "bsd.txt";
        File archivo = new File(rutaArchivo);
        if (archivo.delete()) {
            System.out.println("El archivo se elimino correctamente");
        }else{
            System.out.println("No se pudo eliminar el archivo");
        }
    }

    public static void GuardarEstadoMatriz(int [][] matriz, String rutaArchivo){
        //EliminarBus(); // solo es para eliminar cualquier registro anterior
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (int i = 0; i < matriz.length; i++) {
                
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < matriz[i].length; j++) {
                    sb.append(matriz[i][j]);
                    if (j < matriz[i].length - 1) sb.append(","); 
                }
                writer.write(sb.toString());
                writer.newLine(); 
            }
            System.out.println("");
            System.out.println("Asiento comprado exitosamente");
        } catch (IOException e) {
            System.err.println("Error al guardar la matriz: " + e.getMessage());
        }
    }

    public static int[][] AsignarAsientoUnico(int[][] asientoNormal){
        for (int i = 0; i < asientoNormal.length; i++) {
            for (int j = 0; j < asientoNormal[i].length; j++) {
                if (asientoNormal[i][j] == 0) {
                    asientoNormal[i][j] = 1;
                    return asientoNormal;
                }
            }
        }
        return asientoNormal;
    }

    public static void ImprimirEstadoBus(int [][] normales, int [] finBus){
        System.out.println(" ");
        for (int i = 0; i < normales.length; i++) {
            System.out.print("|");
            for (int j = 0; j < normales[i].length; j++) {
                if (normales[i][j] == 1) {
                    System.out.print("x" + "|");
                }else{
                    System.out.print(normales[i][j] + "|");
                }
                if (j==1) {
                    System.out.print(" --- |");
                }
            }
            System.out.println(" ");
        }

        for (int i = 0; i < finBus.length; i++) {
            System.out.print("|");
            if (finBus[i] == 1) {
                System.out.print("x");
            }else{
                System.out.print(finBus[i]);
            }
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

    public static boolean ValidarAdmin(String usr, String pass, String cedula){
        return (usr.equalsIgnoreCase("code") && pass.equalsIgnoreCase("1234") && ValidarCedula(cedula));
    }

    public static void main(String[] args) {
        CrearArchivo();
        Scanner exclusivo = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        String user, pass, cedulaString ="", rutaArchivo = "bsd.txt";
        boolean acces = false, accesUser = false;
        int optionAsiento, optionPermisos;
        int finBus[] = {0,1,0,0,1};
        // con una matriz definimos los asientos normales
        int BusInicio[][] = {
            {1,0,0,0},
            {0,1,0,0},
            {0,0,1,1},
            {0,1,0,0},
            {0,0,0,0},
            {0,0,1,0},
            {1,0,0,0},
            {0,1,0,0},
            {0,1,0,1}
        };
        //GuardarEstadoMatriz(BusInicio, rutaArchivo); no descomentar sino exite un bug jajaja
        LimpiarPantalla();
        int [][] matrizEjecucion = new int[9][4];
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            int fila = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("//") || line.trim().isEmpty()) continue;
                
                String[] valores = line.split(",");
                for (int col = 0; col < valores.length; col++) {
                    matrizEjecucion[fila][col] = Integer.parseInt(valores[col].trim());
                }
                fila++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        //mensajes de incio
        
        System.out.println("Bienvenido al Sistema de compra de voletos");
        System.out.println("                en un Bus");
        System.out.println("[0] Administrado.");
        System.out.println("[1] Usuario.");
        optionPermisos = entrada.nextInt();
        switch (optionPermisos) {
            case 0:
                //administrador solo code  
                LimpiarPantalla();
                System.out.println("A continuacion tines 3 faces de verificacion");
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
                    if (acces = ValidarAdmin(user, pass, cedulaString)) {
                        break;
                    }
                    //System.out.println(acces);
                    intentos++;
                
                } while (acces == false && intentos != 3);
                if (acces) {
                    LimpiarPantalla();
                    System.out.println("");
                    System.out.println("Opciones como administrador: ");
                    System.out.println("[1] Eliminar bus guardado.");
                    System.out.println("[2] Crear nuevo bus por defecto");
                    switch (entrada.nextInt()) {
                        case 1:
                            EliminarBus();
                            break;
                        case 2:
                            CrearNuevoBus(BusInicio, rutaArchivo);
                            System.out.println("");
                            break;
                        default:
                            System.out.println("Selecciona una opcion valida");
                            break;
                    }
                }
                break;
            case 1:
                LimpiarPantalla();
                //usuario normal
                System.out.println("A continuacion tines 1 face de verificacion");
                System.out.println("Ingresa tu numero de cedula: ");
                cedulaString = entrada.next();
                accesUser = ValidarCedula(cedulaString);
                break;
            default:
                System.out.println("Ingrese una opcion valida.");
                break;
        }
        
        // asientos de bus
        // definimos la parte trasera de 5 asientos en un bus
        
        // validacion de usuario
        
        // continuamos 
        if (accesUser) {
            LimpiarPantalla();
            System.out.println("El bus cuenta con los siguientes asientos: ");
            System.out.println("Libres: " + AsientosLibres(matrizEjecucion));
            ImprimirEstadoBus(matrizEjecucion, finBus);
            System.out.println("");
            System.out.println("Seleccione una opcion: ");
            System.out.println("[1] Comprar Asiento Unico.");
            System.out.println("[2] Comprar Asiento en Pareja.");
            System.out.println("[0] Salir.");
            optionAsiento = exclusivo.nextInt();
            switch (optionAsiento) {
                case 0:
                    LimpiarPantalla();
                    System.out.println("");
                    System.out.println("Saliendo del Sistema");
                    System.out.println(" ");
                    break;
                case 1:
                    int optionFactura = -1;
                    LimpiarPantalla();
                    System.out.println("Proceso de Compra de asiento unico.");
                    System.out.println("");
                    System.out.println("Asiento antes de la compra");
                    System.out.println("Libres: " + AsientosLibres(matrizEjecucion));
                    ImprimirEstadoBus(matrizEjecucion, finBus);
                    System.out.println("");
                    System.out.println("Asientos despues de la compra: ");
                    AsignarAsientoUnico(matrizEjecucion);
                    System.out.println("Libres: " + AsientosLibres(matrizEjecucion));
                    ImprimirEstadoBus(matrizEjecucion, finBus);
                    GuardarEstadoMatriz(matrizEjecucion, rutaArchivo);
                    System.out.println("\n");
                    System.out.println("Desea Generar su factura ?");
                    System.out.println("[1] Si.");
                    System.out.println("[2] No.");
                    optionFactura = exclusivo.nextInt();

                    switch (optionFactura) {
                        case 1:
                            LimpiarPantalla();
                            String nombreUnico;
                            System.out.println("Para generar su factura pot favor ingrese");
                            System.out.println("Sus 2 Apellidos y Nombres: ");
                            // por aqui existe un bug de ingreso de daros solucionar !!!
                            nombreUnico = exclusivo.nextLine();
                            nombreUnico = exclusivo.nextLine();
                            if (nombreUnico.length() > 7) {
                                // usaria la clase Facturacion pero aun no esa JAJAJAj
                                // fuck 
                                LimpiarPantalla();
                                System.out.println("Generando Factura...");
                                System.out.println(nombreUnico);
                                Facturacion cliienteUnico = new Facturacion(cedulaString, nombreUnico, optionAsiento);
                                //System.out.println(cliienteUnico.GenerarMail());
                                //System.out.println(cliienteUnico.GenerarNumero());
                                cliienteUnico.GenerarFacturaEnArchivo();
                                System.out.println("Factura Guardad como: " + cedulaString + ".txt");
                            }else{
                                System.out.println("Ingrese un nombre valido.");
                            }
                            break;
                        case 2:
                            LimpiarPantalla();
                            System.out.println("\nGracias por comprar tu asiento.");
                            System.out.println("Chaooo.");
                            break;
                        default:
                            System.out.println("Seleccione una opcion correcta");
                            break;
                    }

                    break;
                case 2:
                    int optionFactura2 = -1;
                    LimpiarPantalla();
                    System.out.println("Proceso de Compra de asiento en Pareja.");
                    System.out.println("");
                    System.out.println("Asiento antes de la compra");
                    System.out.println("Libres: " + AsientosLibres(matrizEjecucion));
                    ImprimirEstadoBus(matrizEjecucion, finBus);
                    System.out.println("");
                    System.out.println("Asientos despues de la compra: ");
                    AsignarAsientoPareja(matrizEjecucion);
                    System.out.println("Libres: " + AsientosLibres(matrizEjecucion));
                    ImprimirEstadoBus(matrizEjecucion, finBus);
                    GuardarEstadoMatriz(matrizEjecucion, rutaArchivo);
                    System.out.println("\n");
                    System.out.println("Desea Generar su factura ?");
                    System.out.println("[1] Si.");
                    System.out.println("[2] No.");
                    optionFactura2 = exclusivo.nextInt();

                    switch (optionFactura2) {
                        case 1:
                            LimpiarPantalla();
                            String nombrePareja;
                            System.out.println("Para generar su factura pot favor ingrese");
                            System.out.println("Sus 2 Apellidos y Nombres: ");
                            // por aqui existe un bug de ingreso de daros solucionar !!!
                            nombrePareja = exclusivo.nextLine();
                            nombrePareja = exclusivo.nextLine();
                            if (nombrePareja.length() > 7) {
                                // usaria la clase Facturacion pero aun no esa JAJAJAj
                                // fuck 
                                LimpiarPantalla();
                                System.out.println("Generando Factura...");
                                //System.out.println(nombrePareja);
                                //clase facturacion aqui empiza lo de la factura 
                                Facturacion clientePareja = new Facturacion(cedulaString, nombrePareja, optionAsiento);
                                //System.out.println(clientePareja.GenerarMail());
                                clientePareja.GenerarFacturaEnArchivo();
                                System.out.println("Factura Guardad como: " + cedulaString + ".txt");
                            }else{
                                System.out.println("Ingrese un nombre valido.");
                            }
                            break;
                        case 2:
                            LimpiarPantalla();
                            System.out.println("\nGracias por comprar tu asiento.");
                            System.out.println("Chaooo.");
                            break;
                        default:
                            System.out.println("Seleccione una opcion correcta");
                            break;
                    }

                    break;
                default:
                    System.out.println("Seleccione una opcion correcta");
                    break;
            }
            exclusivo.close();
        }
        
        entrada.close();
    }
    
}

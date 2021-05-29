package paqxpress.test;

import java.util.List;
import java.util.Scanner;
import paqxpress.core.*;

public class ConsoleApp {
    private static Scanner cin = new Scanner(System.in);
    private static PqManager pqXpress = null;

    public static void main(String[] args) {

        String entrada = "";

        final String altaCliente = "1";
        final String listaClientes = "2";
        final String altaNuevoEnvio = "3";
        final String consultarEnvio = "4";
        final String cambiarEnvio = "5";
        final String listarEnvioCliente = "6";
        final String listarEnvioReparto = "7";
        final String salir = "x";

        launchApp();

        while (!entrada.equals(salir)) {
            printMenu();
            entrada = cin.nextLine().toLowerCase();

            switch (entrada) {
                case altaCliente:
                    altaCliente();
                    break;
                
                case listaClientes:
                    listaClientes();
                    break;

                case altaNuevoEnvio:
                    altaNuevoEnvio();
                    break;

                case consultarEnvio:
                    consultarEnvio();
                    break;
                    
                case cambiarEnvio:
                    cambiarEnvio();
                    break;

                case listarEnvioCliente:
                    listarEnvioCliente();
                    break;

                case listarEnvioReparto:
                    listarEnvioReparto();
                    break;

                default:
                    break;
            }
        }
    }

    public static void printMenu(){
        clean();
        String menu = "\n\t#######################################\n";
              menu += "\t#                                     #\n";
              menu += "\t#           p q X p r e s s           #\n";
              menu += "\t#                                     #\n";
              menu += "\t# --->--->--->--->--->--->--->--->--- #\n";
              menu += "\t#                                     #\n";
              menu += "\t# [1] - Alta cliente                  #\n";
              menu += "\t# [2] - Lista clientes                #\n";
              menu += "\t# [3] - Alta nuevo envío              #\n";
              menu += "\t# [4] - Consultar estado envío        #\n";
              menu += "\t# [5] - Cambiar estado envío          #\n";
              menu += "\t# [6] - Listar envíos por cliente     #\n";
              menu += "\t# [7] - Listar envíos en reparto      #\n";
              menu += "\t# [X] - Salir                         #\n";
              menu += "\t#                                     #\n";
              menu += "\t#######################################\n";
              menu += "\nOpción > ";
        System.out.print(menu);
    }

    public static void clean() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void altaCliente() {
        clean();
        System.out.println("\nAlta nuevo cliente >\n");
        System.out.print("NIF/CIF: ");
        String dni = cin.nextLine().toUpperCase();
        System.out.print("Nombre/Razón Social: ");
        String nombre = cin.nextLine();
        System.out.println("\n[C]onfirmar | []-Cancelar");
        if(cin.nextLine().toUpperCase().equals("C")) {
            pqXpress.altaCliente(dni, nombre);
            clean();
            System.out.println("\nCliente creado");
            System.out.print("\nPulse [RET] para continuar ");
            cin.nextLine();
        }
    }

    public static void listaClientes() {
        clean();
        List<Cliente> lista = pqXpress.listaClientes();
        System.out.println("\nLista de clientes >\n");
        System.out.println("NIF/CIF \tNOMBRE");
        System.out.println("-----------------------------------------------------");
        for (Cliente cli : lista) {
            System.out.println(cli.getIdCli() + "\t" + cli.getNombre());
        }
        System.out.print("\nPulse [RET] para continuar ");
        cin.nextLine();
    }

    public static void altaNuevoEnvio() {
        String dni = "", destinatario, destino;
        Cliente cliente = null;
        Boolean exit = false;
        while (exit == false) {
            clean();
            System.out.println("\nAlta de nuevo envío >\n");
            System.out.print("NIF/CIF ([L]ista clientes): ");
            dni = cin.nextLine().toUpperCase();
            if(dni.equals("L")){
                listaClientes();
                continue;
            }
            System.out.print("\nDestinatario: ");
            destinatario = cin.nextLine();
            System.out.print("\nDestino: ");
            destino = cin.nextLine();
            //Verificamos datos
            if(dni.length() != 9 || destinatario == null || destino == null) {
                System.out.println("Datos no válidos");
                System.out.print("\nPulse [RET] para continuar ");
                continue;
            }
            if(pqXpress.listaClientes().contains(dni) == false) {
                System.out.println("Cliente no registrado");
                continue;
            }
            System.out.println("\n[C]onfirmar | []-Cancelar");
            if(cin.nextLine().toUpperCase().equals("C")) {
                for (Cliente cli : pqXpress.listaClientes()) {
                    if(cli.getIdCli().equals(dni))
                    cliente = cli;
                }
                int id = pqXpress.altaEnvio(cliente, destinatario, destino);;
                clean();
                System.out.println("\nNuevo envío creado con ID: " + id);
                System.out.print("\nPulse [RET] para continuar ");
                cin.nextLine();
                exit = true;
            }
        }
    }

    public static void consultarEnvio() {
        clean();
        System.out.println("\nConsultar estado de un envío >\n");
        System.out.print("ID del envío: ");
        int id = cin.nextInt();
    }

    public static void cambiarEnvio() {
        
    }

    public static void listarEnvioCliente() {
        
    }

    public static void listarEnvioReparto() {
        
    }

    public static void launchApp() {
        pqXpress = new PqManager();
    }
}


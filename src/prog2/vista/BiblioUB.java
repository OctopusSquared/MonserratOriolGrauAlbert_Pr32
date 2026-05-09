package prog2.vista;

import java.util.List;
import java.util.Scanner;
import prog2.adaptador.Adaptador;

public class BiblioUB {

    static private enum OpcionsMenuPrincipal {
        MENU_PRINCIPAL_EXEMPLARS,
        MENU_PRINCIPAL_USUARIS,
        MENU_PRINCIPAL_PRESTECS,
        MENU_PRINCIPAL_SAVE,
        MENU_PRINCIPAL_LOAD,
        MENU_PRINCIPAL_EXIT
    }

    static private String[] descMenuPrincipal = {"Gestió Exemplars",
                                                 "Gestió Usuaris",
                                                 "Gestió Prestecs",
                                                 "Guardar Dades",
                                                 "Recuperar Dades",
                                                 "Sortir"};

    static private enum OpcionsMenuGestioExemplars {
        MENU_GESTIO_EXEMPLARS_ADD,
        MENU_GESTIO_EXEMPLARS_VIEW,
        MENU_GESTIO_EXEMPLARS_EXIT
    }

    static private String[] descMenuGestioExemplars = {"Afegir Exemplar",
                                                       "Visualitzar Exemplars",
                                                       "Sortir"};

    static private enum OpcionsMenuGestioClients {
        MENU_GESTIO_USUARIS_ADD,
        MENU_GESTIO_USUARIS_VIEW,
        MENU_GESTIO_USUARIS_EXIT
    }

    static private String[] descMenuGestioUsuaris = {"Afegir Usuari",
                                                     "Visualitzar Usuaris",
                                                     "Sortir"};

    static private enum OpcionsMenuGestioPrestecs {
        MENU_GESTIO_PRESTECS_ADD,
        MENU_GESTIO_PRESTECS_REMOVE,
        MENU_GESTIO_PRESTECS_VIEW,
        MENU_GESTIO_PRESTECS_VIEW_URG,
        MENU_GESTIO_PRESTECS_EXIT
    }

    static private String[] descMenuGestioPrestecs = {"Afegir Prestec",
                                                      "Retornar Prestec",
                                                      "Visualitzar Prestecs",
                                                      "Visualitzar Prestecs no Retornats",
                                                      "Sortir"};

    private Adaptador adaptador;

    public BiblioUB() {
        adaptador = new Adaptador();
    }

    public void gestioBiblioUB() {
        Scanner sc = new Scanner(System.in);
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menu principal", OpcionsMenuPrincipal.values());
        menu.setDescripcions(descMenuPrincipal);

        OpcionsMenuPrincipal opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch (opcio) {
                case MENU_PRINCIPAL_EXEMPLARS:
                    menuGestioExemplars(sc);
                    break;
                case MENU_PRINCIPAL_USUARIS:
                    menuGestioUsuaris(sc);
                    break;
                case MENU_PRINCIPAL_PRESTECS:
                    menuGestioPrestecs(sc);
                    break;
                case MENU_PRINCIPAL_SAVE:
                    guardarDades(sc);
                    break;
                case MENU_PRINCIPAL_LOAD:
                    carregarDades(sc);
                    break;
                case MENU_PRINCIPAL_EXIT:
                    System.out.println("Sortint de l'aplicació...");
                    break;
            }
        } while (opcio != OpcionsMenuPrincipal.MENU_PRINCIPAL_EXIT);
    }

    private void menuGestioExemplars(Scanner sc) {
        Menu<OpcionsMenuGestioExemplars> menu = new Menu<>("Gestió Exemplars", OpcionsMenuGestioExemplars.values());
        menu.setDescripcions(descMenuGestioExemplars);
        OpcionsMenuGestioExemplars opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch (opcio) {
                case MENU_GESTIO_EXEMPLARS_ADD:
                    afegirExemplar(sc);
                    break;
                case MENU_GESTIO_EXEMPLARS_VIEW:
                    showList("Exemplars", adaptador.llistaExemplarsToLlistaString());
                    break;
                case MENU_GESTIO_EXEMPLARS_EXIT:
                    break;
            }
        } while (opcio != OpcionsMenuGestioExemplars.MENU_GESTIO_EXEMPLARS_EXIT);
    }

    private void afegirExemplar(Scanner sc) {
        System.out.print("Identificador: ");
        String id = sc.nextLine();
        System.out.print("Títol: ");
        String titol = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Admet préstec llarg (true/false): ");
        boolean admetLlarg = sc.nextBoolean();
        sc.nextLine(); // consumir salt de línia

        try {
            adaptador.afegirExemplar(id, titol, autor, admetLlarg);
            System.out.println("Exemplar afegit correctament.");
        } catch (BiblioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void menuGestioUsuaris(Scanner sc) {
        Menu<OpcionsMenuGestioClients> menu = new Menu<>("Gestió Usuaris", OpcionsMenuGestioClients.values());
        menu.setDescripcions(descMenuGestioUsuaris);
        OpcionsMenuGestioClients opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch (opcio) {
                case MENU_GESTIO_USUARIS_ADD:
                    afegirUsuari(sc);
                    break;
                case MENU_GESTIO_USUARIS_VIEW:
                    showList("Usuaris", adaptador.llistaUsuarisToLlistaString());
                    break;
                case MENU_GESTIO_USUARIS_EXIT:
                    break;
            }
        } while (opcio != OpcionsMenuGestioClients.MENU_GESTIO_USUARIS_EXIT);
    }

    private void afegirUsuari(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Adreça: ");
        String adreca = sc.nextLine();
        System.out.print("És estudiant? (true/false): ");
        boolean esEstudiant = sc.nextBoolean();
        sc.nextLine();

        try {
            adaptador.afegirUsuari(email, nom, adreca, esEstudiant);
            System.out.println("Usuari afegit correctament.");
        } catch (BiblioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void menuGestioPrestecs(Scanner sc) {
        Menu<OpcionsMenuGestioPrestecs> menu = new Menu<>("Gestió Prestecs", OpcionsMenuGestioPrestecs.values());
        menu.setDescripcions(descMenuGestioPrestecs);
        OpcionsMenuGestioPrestecs opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch (opcio) {
                case MENU_GESTIO_PRESTECS_ADD:
                    afegirPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_REMOVE:
                    retornarPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_VIEW:
                    showList("Tots els préstecs", adaptador.llistaPrestecsToLlistaString());
                    break;
                case MENU_GESTIO_PRESTECS_VIEW_URG:
                    showList("Préstecs no retornats", adaptador.llistaPrestecsNoRetornatsToLlistaString());
                    break;
                case MENU_GESTIO_PRESTECS_EXIT:
                    break;
            }
        } while (opcio != OpcionsMenuGestioPrestecs.MENU_GESTIO_PRESTECS_EXIT);
    }

    private void afegirPrestec(Scanner sc) {
        // Mostrar exemplars disponibles
        List<String> exemplars = adaptador.llistaExemplarsToLlistaString();
        if (exemplars.isEmpty()) {
            System.out.println("No hi ha exemplars.");
            return;
        }
        showList("Exemplars", exemplars);
        System.out.print("Tria l'índex de l'exemplar: ");
        int exemplarPos = sc.nextInt();
        sc.nextLine();

        List<String> usuaris = adaptador.llistaUsuarisToLlistaString();
        if (usuaris.isEmpty()) {
            System.out.println("No hi ha usuaris.");
            return;
        }
        showList("Usuaris", usuaris);
        System.out.print("Tria l'índex de l'usuari: ");
        int usuariPos = sc.nextInt();
        sc.nextLine();

        System.out.print("Préstec llarg? (true/false): ");
        boolean esLlarg = sc.nextBoolean();
        sc.nextLine();

        try {
            adaptador.afegirPrestec(exemplarPos, usuariPos, esLlarg);
            System.out.println("Préstec creat correctament.");
        } catch (BiblioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void retornarPrestec(Scanner sc) {
        List<String> prestecs = adaptador.llistaPrestecsToLlistaString();
        if (prestecs.isEmpty()) {
            System.out.println("No hi ha préstecs.");
            return;
        }
        showList("Préstecs", prestecs);
        System.out.print("Tria l'índex del préstec a retornar: ");
        int pos = sc.nextInt();
        sc.nextLine();

        try {
            adaptador.retornarPrestec(pos);
            System.out.println("Préstec retornat correctament.");
        } catch (BiblioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void guardarDades(Scanner sc) {
        String path = getFilePath(sc, false);
        if (path != null) {
            try {
                adaptador.guardaDades(path);
                System.out.println("Dades guardades.");
            } catch (BiblioException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void carregarDades(Scanner sc) {
        String path = getFilePath(sc, true);
        if (path != null) {
            try {
                adaptador.carregaDades(path);
                System.out.println("Dades carregades.");
            } catch (BiblioException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showList(String title, List<String> lines) {
        System.out.println("============================================");
        System.out.println(title);
        System.out.println("============================================");
        int i = 0;
        for (String l : lines) {
            System.out.println("\t[" + (i++) + "] " + l);
        }
        System.out.println("============================================");
    }

    private String getFilePath(Scanner sc, boolean mustExist) {
        System.out.print("Entra ruta completa del fitxer (ENTER per ometre): ");
        String filePath = sc.nextLine();
        if (filePath.isEmpty()) {
            return null;
        }
        // Si mustExist és true, es podria comprovar l'existència, però ho deixem simple
        return filePath;
    }
}

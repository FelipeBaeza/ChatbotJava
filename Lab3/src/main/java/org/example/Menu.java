package org.example;

import java.util.*;


public class Menu {

    public static void main(String[] args) {
        MySystem mySystem = initializeSystem();
        displayMainMenu(mySystem);
    }

    public static MySystem initializeSystem() {
        Option O1 = new Option(1, "1) Viajar", 1, 1, List.of("viajar", "turistear", "conocer"));
        Option O2 = new Option(2, "2) Estudiar", 2, 1, List.of("estudiar", "aprender", "perfeccionarme"));
        Option O3 = new Option(1, "1) New York, USA", 1, 2, List.of("USA", "Estados Unidos", "New York"));
        Option O4 = new Option(2, "2) París, Francia", 1, 1, List.of("Paris", "Eiffel"));
        Option O5 = new Option(3, "3) Torres del Paine, Chile", 1, 1, List.of("Chile", "Torres", "Paine", "Torres Paine", "Torres del Paine"));
        Option O6 = new Option(4, "4) Volver", 0, 1, List.of("Regresar", "Salir", "Volver"));
        Option O7 = new Option(1, "1) Central Park", 1, 2, List.of("Central", "Park", "Central Park"));
        Option O8 = new Option(2, "2) Museos", 1, 2, List.of("Museo"));
        Option O9 = new Option(3, "3) Ningún otro atractivo", 1, 3, List.of("Museo"));
        Option O10 = new Option(4, "4) Cambiar destino", 1, 1, List.of("Cambiar", "Volver", "Salir"));
        Option O11 = new Option(1, "1) Solo", 1, 3, List.of("Solo"));
        Option O12 = new Option(2, "2) En pareja", 1, 3, List.of("Pareja"));
        Option O13 = new Option(3, "3) En familia", 1, 3, List.of("Familia"));
        Option O14 = new Option(4, "4) Agregar más atractivos", 1, 2, List.of("Volver", "Atractivos"));
        Option O15 = new Option(5, "5) En realidad quiero otro destino", 1, 1, List.of("Cambiar destino"));
        Option O16 = new Option(1, "1) Carrera Técnica", 2, 1, List.of("Técnica"));
        Option O17 = new Option(2, "2) Postgrado", 2, 1, List.of("Doctorado", "Magister", "Postgrado"));
        Option O18 = new Option(3, "3) Volver", 0, 1, List.of("Volver", "Salir", "Regresar"));

        // Crear flujos
        Flow F10 = new Flow(1, "Flujo Principal Chatbot 1\nBienvenido\n¿Qué te gustaría hacer?", List.of(O1, O2));
        Flow F20 = new Flow(1, "Flujo 1 Chatbot1\n¿Dónde te gustaría ir?", List.of(O3, O4, O5, O6));
        Flow F21 = new Flow(2, "Flujo 2 Chatbot1\n¿Qué atractivos te gustaría visitar?", List.of(O7, O8, O9, O10));
        Flow F22 = new Flow(3, "Flujo 3 Chatbot1\n¿Vas solo o acompañado?", List.of(O11, O12, O13, O14, O15));
        Flow F30 = new Flow(1, "Flujo 1 Chatbot2\n¿Qué te gustaría estudiar?", List.of(O16, O17, O18));

        // Crear chatbots
        Chatbot CB0 = new Chatbot(0, "Inicial", "Bienvenido ¿Qué te gustaría hacer?", 1, List.of(F10));
        Chatbot CB1 = new Chatbot(1, "Agencia Viajes", "Bienvenido ¿Dónde quieres viajar?", 1, List.of(F20, F21, F22));
        Chatbot CB2 = new Chatbot(2, "Orientador Académico", "Bienvenido ¿Qué te gustaría estudiar?", 1, List.of(F30));

        // Crear usuarios
        List<User> users = new ArrayList<>();
        User user1 = new User("checopete", true);
        User user2 = new User("Ruperto", false);
        users.add(user1);
        users.add(user2);

        // Crear sistema
        MySystem mySystem = new MySystem("Chatbots Paradigmas", 0,List.of(CB0, CB1, CB2), new ArrayList<>(), "", users);
        return mySystem;
    }

    public static void displayMainMenu(MySystem mySystem) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String userName;
        String register;

        do {
            try {
                System.out.println("### Sistema de Chatbots - Inicio ###");
                System.out.println("1. Login de Usuario");
                System.out.println("2. Registro de Usuario");
                System.out.println("3. Salir");
                System.out.print("INTRODUZCA SU OPCIÓN: ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea después de nextInt()

                switch (choice) {
                    case 1:
                        System.out.println("### Sistema de Chatbots - Login ###");
                        System.out.print("INTRODUZCA NOMBRE DE USUARIO: ");
                        userName = scanner.nextLine().toLowerCase();
                        login(mySystem, userName);
                        break;
                    case 2:
                        displayRegistrationMenu(mySystem);
                        break;
                    case 3:
                        System.out.println("Saliendo del Sistema de Chatbots. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                        break;
                }
            } catch (NoSuchElementException e) {
                // Manejar la excepción y mostrar nuevamente las opciones
                System.out.println("Error: Opción no válida. Por favor, ingrese una opción válida.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                choice = 0; // Asignar 0 para que se vuelva a mostrar el menú
            }

        } while (choice != 3);

        scanner.close();
    }


    public static void login(MySystem mySystem, String userName) {
        boolean loginSuccessful = false;
        do {
            String finalUserName = userName;
            Optional<User> userOptional = mySystem.usuariosRegistrados.stream()
                    .filter(user -> user.getName().equals(finalUserName))
                    .findFirst();

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                mySystem.systemLogin(userName);
                System.out.println("Inicio de sesión exitoso para el usuario: " + userName);

                if (user.isAdmin()) {
                    displayAdminMenu(mySystem);
                } else {
                    displayUserMenu(mySystem);
                }

                loginSuccessful = true;
            } else {
                System.out.println("Usuario no encontrado. Por favor, verifique el nombre de usuario.");
                System.out.println("Puede volver a intentarlo o volver al Menu princiapl");
                System.out.println("1. Intentar nuevamente");
                System.out.println("2. Vvolver al menú principal");
                System.out.print("INTRODUZCA SU OPCIÓN: ");
                Scanner scanner = new Scanner(System.in);
                int retryChoice = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea después de nextInt()

                if (retryChoice == 2) {
                    break; // Salir del bucle y volver al menú principal
                } else {
                    System.out.print("INTRODUZCA NOMBRE DE USUARIO: ");
                    userName = scanner.nextLine();
                }
            }
        } while (!loginSuccessful);
    }

    public static void displayRegistrationMenu(MySystem mySystem) {
        Scanner scanner = new Scanner(System.in);
        int registrationChoice;
        String nombre;

        do {
            try {
                System.out.println("### Sistema de Chatbots - Registro ###");
                System.out.println("1. Registrar usuario normal");
                System.out.println("2. Registrar usuario administrador");
                System.out.print("INTRODUZCA SU OPCIÓN: ");

                registrationChoice = scanner.nextInt();
                scanner.nextLine();

                switch (registrationChoice) {
                    case 1:
                        do {
                            System.out.print("INTRODUZCA NOMBRE DEL USUARIO NORMAL: ");
                            nombre = scanner.nextLine().toLowerCase();

                            if (mySystem.systemAddUser(nombre, false)) {
                                System.out.println("Usuario normal registrado exitosamente.");
                                displayUserMenu(mySystem);
                                break;
                            } else {
                                System.out.println("El nombre de usuario ya está en uso. Por favor, elija otro.");
                                System.out.print("¿Desea intentar nuevamente? (1: Sí, 2: No): ");
                                int tryAgainChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (tryAgainChoice != 1) {
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    case 2:
                        do {
                            System.out.print("INTRODUZCA NOMBRE DEL USUARIO ADMINISTRADOR: ");
                            nombre = scanner.nextLine().toLowerCase();

                            if (mySystem.systemAddUser(nombre, true)) {
                                System.out.println("Usuario Administrador registrado exitosamente.");
                                displayAdminMenu(mySystem);
                                break;
                            } else {
                                System.out.println("El nombre de usuario ya está en uso. Por favor, elija otro.");
                                System.out.print("¿Desea intentar nuevamente? (1: Sí, 2: No): ");
                                int tryAgainChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (tryAgainChoice != 1) {
                                    break;
                                }
                            }
                        } while (true);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                        break;
                }
            } catch (Exception e) {
                // Manejar la excepción y volver a mostrar las opciones
                System.out.println("Error: Opción no válida. Por favor, ingrese una opción válida.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                registrationChoice = 0; // Asignar 0 para que se vuelva a mostrar el menú
            }

        } while (registrationChoice != 1 && registrationChoice != 2);

        scanner.close();
    }

    public static void displayAdminMenu(MySystem mySystem) {
        Scanner scanner = new Scanner(System.in);
        int adminChoice;

        do {
            try {
                System.out.println("### Sistema de Chatbots - Usuario Administrador ###");
                System.out.println("Bienvenido " + mySystem.userLogin + ", usted es administrador.");
                System.out.println("1. Crear un Chatbot");
                System.out.println("2. Modificar un Chatbot");
                System.out.println("3. Ejecutar un Chatbot");
                System.out.println("4. Visualizar todos los chatbots existentes en el sistema");
                System.out.println("5. Visualizar todos los chatbots con sus flujos y opciones creadas");
                System.out.println("6. Ejecutar una simulación del sistema de chatbot");
                System.out.println("7. Salir (Cerrar Sesión)");
                System.out.print("INTRODUZCA SU OPCIÓN: ");

                adminChoice = scanner.nextInt();
                scanner.nextLine();

                switch (adminChoice) {
                    case 1:
                        System.out.println("### Sistema de Chatbots - Crear Chatbot ###");
                        createChatbot(mySystem, scanner);
                        break;
                    case 2:
                        modifyChatbot(mySystem, scanner);
                        break;
                    case 3:
                        // Lógica para ejecutar un chatbot (pendiente)
                        break;
                    case 4:
                        displayChatbotNames(mySystem);
                        break;
                    case 5:
                        displayAllChatbotsWithFlowsAndOptions(mySystem);
                        break;
                    case 6:
                        // Lógica para ejecutar una simulación del sistema de chatbot (pendiente)
                        break;
                    case 7:
                        System.out.println("Saliendo del Menú Administrador. ¡Hasta luego!");
                        mySystem.systemLogout();
                        displayMainMenu(mySystem);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                        break;
                }
            } catch (Exception e) {
                // Manejar la excepción y volver a mostrar las opciones
                System.out.println("Error: Opción no válida. Por favor, ingrese una opción válida.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                adminChoice = 0; // Asignar 0 para que se vuelva a mostrar el menú
            }

        } while (adminChoice != 7);

        scanner.close();
    }


    public static void displayUserMenu(MySystem mySystem) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;

        do {
            try {
                System.out.println("### Sistema de Chatbots - Usuario Normal ###");
                System.out.println("Bienvenido " + mySystem.userLogin);
                System.out.println("1. Ejecutar un Chatbot(SystemTalk)");
                System.out.println("2. Visualizar todos los chatbots existentes en el sistema");
                System.out.println("3. Visualizar todos los chatbots con sus flujos y opciones creadas");
                System.out.println("4. Ejecutar una simulación del sistema de chatbot");
                System.out.println("5. Salir (Cerrar Sesión)");
                System.out.print("INTRODUZCA SU OPCIÓN: ");

                userChoice = scanner.nextInt();
                scanner.nextLine();

                switch (userChoice) {
                    case 1:
                        String respuesta;
                        System.out.println("PARA PARAR LA INTERACION INTRODUZCA 'fin'");
                        do {
                            System.out.print("INTRODUZCA SU INTERACCION: ");
                            respuesta = scanner.nextLine().toLowerCase();
                            mySystem.systemTalk(respuesta); // Llamar al método systemTalk de MySystem
                        } while (!respuesta.equals("fin"));

                        break;
                    case 2:
                        displayChatbotNames(mySystem);
                        break;
                    case 3:
                        displayAllChatbotsWithFlowsAndOptions(mySystem);
                        break;
                    case 4:
                        // Algo que no está implementado
                        break;
                    case 5:
                        System.out.println("Saliendo del Menú Usuario Normal. ¡Hasta luego!");
                        mySystem.systemLogout();
                        displayMainMenu(mySystem);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                        break;
                }
            } catch (Exception e) {
                // Manejar la excepción y volver a mostrar las opciones
                System.out.println("Error: Opción no válida. Por favor, ingrese una opción válida.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                userChoice = 0; // Asignar 0 para que se vuelva a mostrar el menú
            }

        } while (userChoice != 5);

        scanner.close();
    }

    public static void displayChatbotNames(MySystem mySystem) {
        List<Chatbot> chatbots = mySystem.chatbots;
        if (!chatbots.isEmpty()) {
            System.out.println("Nombres de Chatbots en el Sistema:");
            for (Chatbot chatbot : chatbots) {
                System.out.println(chatbot.getName());
            }
        } else {
            System.out.println("No hay chatbots en el sistema.");
        }
    }
    public static void displayAllChatbotsWithFlowsAndOptions(MySystem mySystem) {
        System.out.println("### Chatbots con Flujos y Opciones ###");

        for (Chatbot chatbot : mySystem.getChatbots()) {
            System.out.println("Chatbot: " + chatbot.getName());

            for (Flow flow : chatbot.getFlows()) {
                System.out.println("  - Flujo: " + flow.getName_msg());

                for (Option option : flow.getOptions()) {
                    System.out.println("    - Opción " + option.getCode() + ": " + option.getMessage());
                }
            }

            System.out.println(); // Separador entre chatbots
        }
    }

    public static void createChatbot(MySystem mySystem, Scanner scanner) {
        // Crear chatbot
        System.out.print("Ingrese el ID del chatbot: ");
        int chatbotID = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de nextInt()

        System.out.print("Ingrese el nombre del chatbot: ");
        String chatbotName = scanner.nextLine();
        System.out.print("Ingrese el mensaje de bienvenida del chatbot: ");
        String welcomeMessage = scanner.nextLine();
        System.out.print("Ingrese el startFlowId del chatbot: ");
        int startFlowId = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de nextInt()

        List<Flow> flows = new ArrayList<>();

        // Preguntar si desea agregar un flujo
        System.out.println("¿Desea agregar un flujo al chatbot?: ");
        System.out.println("1. Sí");
        System.out.println("2. No");
        System.out.print("Digite su Opcion: ");
        String addFlowChoice = scanner.nextLine();
        scanner.nextLine();

        while (addFlowChoice.equalsIgnoreCase("1")) {
            // Crear flujo
            System.out.print("Ingrese el ID del flujo: ");
            int flowId = scanner.nextInt();
            System.out.print("Ingrese el mensaje del flujo: ");
            String flowMessage = scanner.nextLine();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            List<Option> options = new ArrayList<>();

            // Preguntar si desea agregar una Option
            System.out.println("¿Desea agregar una opción al flujo?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Digite su Opcion: ");
            String addOptionChoice = scanner.nextLine();
            scanner.nextLine();
            while (addOptionChoice.equalsIgnoreCase("1")) {
                // Crear opción
                System.out.print("Ingrese el código de la opción: ");
                int optionCode = scanner.nextInt();
                System.out.print("Ingrese el mensaje de la opción: ");
                String optionMessage = scanner.nextLine();
                scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                System.out.print("Ingrese el chatbotcodelink para la opción: ");
                int chatbotCodeLink = scanner.nextInt();
                System.out.print("Ingrese el initialFlowCodeLink para la opción: ");
                int initialFlowCodeLink = scanner.nextInt();
                System.out.print("Ingrese las palabras clave separadas por coma (ejemplo: palabra1, palabra2, palabra3): ");
                scanner.nextLine();
                String keywordInput = scanner.nextLine();
                List<String> keywords = Arrays.asList(keywordInput.split("\\s*,\\s*"));
                Option option = new Option(optionCode, optionMessage, chatbotCodeLink, initialFlowCodeLink, keywords);
                options.add(option);

                // Preguntar si desea agregar otra Option
                System.out.print("¿Desea agregar otra opción al flujo?: ");
                System.out.println("1. Sí");
                System.out.println("2. No");
                addOptionChoice = scanner.nextLine();
                scanner.nextLine();
            }

            Flow flow = new Flow(flowId, flowMessage, options);
            flows.add(flow);

            // Preguntar si desea agregar otro flujo
            System.out.print("¿Desea agregar otro flujo al chatbot?: ");
            System.out.println("1. Sí");
            System.out.println("2. No");
            addFlowChoice = scanner.nextLine();
            scanner.nextLine();
        }

        Chatbot chatbot = new Chatbot(chatbotID, chatbotName, welcomeMessage, startFlowId, flows);

        mySystem.systemAddChatbot(chatbot);
        System.out.println("Chatbot creado exitosamente.");
    }


    public static void modifyChatbot(MySystem mySystem, Scanner scanner) {
        System.out.println("### Sistema de Chatbots - Modificar Chatbot ###");

        // Obtener la cantidad total de chatbots
        int totalChatbots = mySystem.getChatbots().size();

        /// Solicitar al usuario que ingrese el número del chatbot a modificar
        int chatbotNumber;
        do {
            System.out.println("Seleccione el Chatbot que desea modificar:");
            for (int i = 0; i < totalChatbots; i++) {
                System.out.println((i + 1) + ". " + mySystem.getChatbots().get(i).getName());
            }
            System.out.print("Ingrese el número del Chatbot: ");
            chatbotNumber = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            // Verificar si el número ingresado es válido
            if (chatbotNumber < 1 || chatbotNumber > totalChatbots) {
                System.out.println("Número no válido. Por favor, ingrese un número válido.");
            }
        } while (chatbotNumber < 1 || chatbotNumber > totalChatbots);
        // Obtener el chatbot seleccionado
        Chatbot chatbotToModify = mySystem.getChatbots().get(chatbotNumber - 1);

        // Mostrar el submenú para modificar el chatbot
        int subMenuOption;
        do {
            System.out.println("Opciones de Modificación para " + chatbotToModify.getName() + ":");
            System.out.println("1. Cambiar nombre del Chatbot");
            System.out.println("2. Cambiar mensaje de bienvenida");
            System.out.println("3. Cambiar chatbotID");
            System.out.println("4. Cambiar startFlowId");
            System.out.println("5. Modificar flujos del Chatbot");
            System.out.println("6. Volver al Menú Administrador");
            System.out.print("INTRODUZCA SU OPCIÓN: ");

            subMenuOption = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (subMenuOption) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre para el Chatbot: ");
                    String newChatbotName = scanner.nextLine();
                    chatbotToModify.setName(newChatbotName);
                    System.out.println("Nombre del Chatbot cambiado exitosamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo mensaje de bienvenida: ");
                    String newWelcomeMessage = scanner.nextLine();
                    chatbotToModify.setWelcomeMessage(newWelcomeMessage);
                    System.out.println("Mensaje de bienvenida del Chatbot cambiado exitosamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo chatbotID: ");
                    int newChatbotID = scanner.nextInt();
                    chatbotToModify.setChatbotID(newChatbotID);
                    System.out.println("chatbotID cambiado exitosamente.");
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo startFlowId: ");
                    int newStartFlowId = scanner.nextInt();
                    chatbotToModify.setStartFlowId(newStartFlowId);
                    System.out.println("startFlowId cambiado exitosamente.");
                    break;
                case 5:
                    // Submenú para modificar flujos del Chatbot
                    modifyFlows(chatbotToModify, scanner);
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Administrador.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        } while (subMenuOption != 6);
    }

    private static void modifyFlows(Chatbot chatbot, Scanner scanner) {
        int flowOption;
        do {
            System.out.println("Opciones para Modificar Flujos del Chatbot " + chatbot.getName() + ":");
            System.out.println("1. Modificar flujo existente");
            System.out.println("2. Agregar nuevo flujo");
            System.out.println("3. Volver al Menú Modificación del Chatbot");
            System.out.print("INTRODUZCA SU OPCIÓN: ");

            flowOption = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (flowOption) {
                case 1:
                    // Modificar flujo existente
                    if (chatbot.getFlows().isEmpty()) {
                        System.out.println("Este chatbot no tiene flujos creados.");
                    } else {
                        System.out.println("Seleccione el flujo que desea modificar:");
                        for (int i = 0; i < chatbot.getFlows().size(); i++) {
                            System.out.println((i + 1) + ". " + chatbot.getFlows().get(i).getName_msg());
                        }
                        System.out.print("Ingrese el número del flujo: ");
                        int selectedFlowNumber = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea después de nextInt()

                        // Verificar si el número ingresado es válido
                        if (selectedFlowNumber >= 1 && selectedFlowNumber <= chatbot.getFlows().size()) {
                            Flow selectedFlow = chatbot.getFlows().get(selectedFlowNumber - 1);
                            modifyExistingFlow(selectedFlow, scanner);
                        } else {
                            System.out.println("Número no válido. Volviendo al Menú Modificación de Flujos.");
                        }
                    }
                    break;
                case 2:
                    // Agregar nuevo flujo
                    System.out.println("Creando un nuevo flujo para " + chatbot.getName() + ":");
                    System.out.print("Ingrese el ID del nuevo flujo: ");
                    int newFlowId = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()

                    System.out.print("Ingrese el mensaje del nuevo flujo: ");
                    String newFlowMessage = scanner.nextLine();

                    // Crear el nuevo flujo
                    Flow newFlow = new Flow(newFlowId, newFlowMessage, new ArrayList<>());

                    // Preguntar si desea agregar opciones al nuevo flujo
                    System.out.print("¿Desea agregar opciones al nuevo flujo? (1: Sí, 2: No): ");
                    int addOptionsChoice = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()

                    if (addOptionsChoice == 1) {
                        // Agregar opciones al nuevo flujo
                        addOptionsToFlow(newFlow, scanner);
                    }

                    // Agregar el nuevo flujo a la lista de flujos del Chatbot
                    chatbot.chatbotAddFlow(newFlow);

                    System.out.println("Nuevo flujo agregado exitosamente.");
                    break;
                case 3:
                    System.out.println("Volviendo al Menú Modificación del Chatbot.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        } while (flowOption != 3);
    }

    private static void addOptionsToFlow(Flow flow, Scanner scanner) {
        int addOptionChoice;
        do {
            System.out.println("Agregando opciones al flujo " + flow.getName_msg() + ":");

            // Solicitar información para la nueva opción
            System.out.print("Ingrese el código de la nueva opción: ");
            int newOptionCode = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            System.out.print("Ingrese el mensaje de la nueva opción: ");
            String newOptionMessage = scanner.nextLine();

            System.out.print("Ingrese el chatbotCodeLink de la nueva opción: ");
            int newChatbotCodeLink = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            System.out.print("Ingrese el initialFlowCodeLink de la nueva opción: ");
            int newInitialFlowCodeLink = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            // Solicitar y procesar las keywords
            System.out.print("Ingrese las keywords (separadas por comas) de la nueva opción: ");
            String keywordsInput = scanner.nextLine();
            List<String> newKeywords = Arrays.asList(keywordsInput.split(", "));

            // Crear la nueva opción con la información recopilada
            Option newOption = new Option(newOptionCode, newOptionMessage, newChatbotCodeLink, newInitialFlowCodeLink, newKeywords);

            // Agregar la nueva opción a la lista de opciones del flujo
            flow.flowAddOption(newOption);

            System.out.println("Nueva opción agregada al flujo exitosamente.");

            // Preguntar si desea agregar más opciones
            System.out.print("¿Desea agregar otra opción al flujo? (1: Sí, 2: No): ");
            addOptionChoice = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()
        } while (addOptionChoice == 1);
    }

    private static void modifyExistingFlow(Flow flow, Scanner scanner) {
        int subFlowOption;
        do {
            System.out.println("Opciones de Modificación para el Flujo " + flow.getName_msg() + ":");
            System.out.println("1. Cambiar ID del flujo");
            System.out.println("2. Cambiar mensaje del flujo");
            System.out.println("3. Modificar opciones del flujo");
            System.out.println("4. Volver al Menú Modificación de Flujos");
            System.out.print("INTRODUZCA SU OPCIÓN: ");

            subFlowOption = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (subFlowOption) {
                case 1:
                    System.out.print("Ingrese el nuevo ID para el flujo: ");
                    int newFlowId = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                    flow.setId(newFlowId);
                    System.out.println("ID del flujo cambiado exitosamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo mensaje para el flujo: ");
                    String newFlowMessage = scanner.nextLine();
                    flow.setName_msg(newFlowMessage);
                    System.out.println("Mensaje del flujo cambiado exitosamente.");
                    break;
                case 3:
                    modifyOptions(flow, scanner);
                    break;
                case 4:
                    System.out.println("Volviendo al Menú Modificación de Flujos.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        } while (subFlowOption != 4);
    }

    // Agrega esta función para modificar opciones de un flujo
// Dentro de tu clase que contiene los métodos
    private static void modifyOptions(Flow flow, Scanner scanner) {
        int optionNumber;
        do {
            System.out.println("Opciones para Modificar Opciones del Flujo " + flow.getName_msg() + ":");
            displayOptions(flow);

            System.out.print("Ingrese el número de la opción que desea modificar (o 0 para salir): ");
            optionNumber = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            if (optionNumber >= 1 && optionNumber <= flow.getOptions().size()) {
                Option selectedOption = flow.getOptions().get(optionNumber - 1);
                modifyExistingOption(selectedOption, scanner);
            } else if (optionNumber != 0) {
                System.out.println("Número no válido. Por favor, ingrese un número válido.");
            }
        } while (optionNumber != 0);
    }

    private static void modifyExistingOption(Option option, Scanner scanner) {
        int subOption;
        do {
            System.out.println("Opciones de Modificación para la Opción " + option.getCode() + ":");
            System.out.println("1. Cambiar código de la opción");
            System.out.println("2. Cambiar mensaje de la opción");
            System.out.println("3. Cambiar ChatbotCodeLink de la opción");
            System.out.println("4. Cambiar InitialFlowCodeLink de la opción");
            System.out.println("5. Cambiar Keywords de la opción");
            System.out.println("6. Volver al Menú Modificación de Opciones");
            System.out.print("INTRODUZCA SU OPCIÓN: ");

            subOption = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (subOption) {
                case 1:
                    System.out.print("Ingrese el nuevo código para la opción: ");
                    int newOptionCode = scanner.nextInt();
                    option.setCode(newOptionCode);
                    System.out.println("Código de la opción cambiado exitosamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo mensaje para la opción: ");
                    String newOptionMessage = scanner.nextLine();
                    option.setMessage(newOptionMessage);
                    System.out.println("Mensaje de la opción cambiado exitosamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo ChatbotCodeLink para la opción: ");
                    int newChatbotCodeLink = scanner.nextInt();
                    option.setChatbotCodeLink(newChatbotCodeLink);
                    System.out.println("ChatbotCodeLink de la opción cambiado exitosamente.");
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo InitialFlowCodeLink para la opción: ");
                    int newInitialFlowCodeLink = scanner.nextInt();
                    option.setInitialFlowCodeLink(newInitialFlowCodeLink);
                    System.out.println("InitialFlowCodeLink de la opción cambiado exitosamente.");
                    break;
                case 5:
                    // Puedes manejar las keywords como una lista de String, similar a cómo se manejan en la clase Option
                    System.out.print("Ingrese las nuevas Keywords (separadas por comas): ");
                    String keywordsInput = scanner.nextLine();
                    List<String> newKeywords = Arrays.asList(keywordsInput.split(","));
                    option.setKeyword(newKeywords);
                    System.out.println("Keywords de la opción cambiadas exitosamente.");
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Modificación de Opciones.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        } while (subOption != 6);
    }


    private static void displayOptions(Flow flow) {
        List<Option> options = flow.getOptions();
        for (int i = 0; i < options.size(); i++) {
            Option option = options.get(i);
            System.out.println(option.getMessage());
        }
    }

}



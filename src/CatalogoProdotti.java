import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogoProdotti {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        
        //creazione dell'amministratore e della lista di Clienti
        Utente admin = new Admin();
        List<Cliente> clienti = new ArrayList<>();

        int tentativi = 3; //numero di tentativi per accedere
        boolean accesso = false, isAdmin = false; //variabili per stabilire se l'utente ha effettuato l'accesso
        do {
            int scelta = 0;
            // ciclo per l'inserimento della scelta dell'utente
            do {
                System.out.println("1. ACCEDI \n2. REGISTRATI \n3. ESCI");
                System.out.println("Inserisci la tua scelta: ");
                scelta = scanner.nextInt();
                if (scelta < 1 || scelta > 3) System.out.println("Valore non valido!");
            } while (scelta < 1 || scelta > 3);

            String mail, password;
            // ciclo per svolgere le varie opzioni del menu precedente
            switch (scelta) {
                // prima opzione - accesso
                case 1:
                    System.out.print("Inserisci email: ");
                    mail = scan.nextLine();
                    System.out.print("Inserisci password: ");
                    password = scan.nextLine();

                    System.out.println();

                    // verifico se ad accedere è l'amministratore
                    if (admin.getEmail().equals(mail) && admin.getPassword().equals(password)) {
                        System.out.println("Accesso come AMMINISTRATORE!");
                        isAdmin = true;
                        accesso = true;
                    } else if (clienti != null) { //verifica di accesso come cliente
                        boolean trovato = false;
                        for (int i = 0; i < clienti.size() && !trovato; i++) {
                            if (clienti.get(i).getEmail().equals(mail)
                                    && clienti.get(i).getPassword().equals(password)) {
                                System.out.println("Accesso come CLIENTE");
                                accesso = true;
                                trovato = true;
                            }
                        }

                        if (!trovato) {
                            System.out.println("Utente non trovato!");
                            tentativi--;
                        }

                    } else {
                        System.out.println("Nessun utente trovato!");
                    }
                    break;
                // seconda opzione - registrazione
                case 2:
                    System.out.println("NUOVO UTENTE!");
                    System.out.print("Inserisci email: ");
                    mail = scan.nextLine();
                    System.out.print("\nInserisci password: ");
                    password = scan.nextLine();

                    Cliente nuovoCliente = new Cliente(mail, password);
                    if (nuovoCliente != null) {
                        clienti.add(nuovoCliente);
                    }
                    break;
                // opzione di uscita
                case 3:
                    System.out.println("Programma in chiusura!");
                    System.exit(0);
                    break;
            }

            if (!accesso) {
                System.out.println("\nTentativi rimasti: " + tentativi);
            }

        } while (tentativi > 0 && !accesso);

        List<Prodotto> prodotti = new ArrayList<>();
        Scanner scan1 = new Scanner(System.in);

        if (accesso) {
            String risp = null;
            // stampa del menu in base al tipo di accesso (amministratore o cliente)
            do {
                System.out.println("\nMENU");
                System.out.println("0. ESCI. \n1. VISUALIZZA PRODOTTI.");

                if (isAdmin) {
                    System.out.println("2. AGGIUNGI PRODOTTO. \n3. ELIMINA PRODOTTO. \n4. MODIFICA PRODOTTO.");
                }

                int scelta1 = scan1.nextInt();

                switch (scelta1) {
                    // opzione per uscire
                    case 0:
                        System.out.println("\nProgramma in chiusura!");
                        System.exit(0);
                        break;
                    // opzione per visualizzare i prodotti
                    case 1:
                        if(prodotti != null){
                            for (Prodotto prodotto : prodotti) {
                                System.out.println(prodotto);
                            }
                        } else{
                            System.out.println("Non ci sono prodotti nel catalogo!");
                        }
                        
                        break;
                    // opzione per inserire un nuovo prodotto (only admin)
                    case 2:
                        if (isAdmin) {
                            System.out.print("NUOVO PRODOTTO.\nInserisci il nome: ");
                            String nome = scan.nextLine();
                            System.out.print("Inserisci il prezzo: ");
                            double prezzo = scan1.nextDouble();
                            System.out.print("Inserisci la quantità: ");
                            int quantita = scan1.nextInt();

                            Prodotto nuovoProdotto = new Prodotto(nome, prezzo, quantita);

                            if (prodotti.add(nuovoProdotto)) {
                                System.out.println("Prodotto inserito correttamente!");
                            }

                        } else {
                            System.out.println("Valore non valido!");
                        }
                        break;
                    // opzione per eliminare un prodotto (only admin)
                    case 3:
                        if (isAdmin) {
                            System.out.println("ELIMINA PRODOTTO.\nInserisci il nome del prodotto da eliminare: ");
                            String nome = scan1.next();

                            if (prodotti != null) {
                                for (int i = 0; i < prodotti.size(); i++) {
                                    if (prodotti.get(i).getNome().equalsIgnoreCase(nome)) {
                                        prodotti.remove(i);
                                        System.out.println("Prodotto rimosso correttamente!");
                                    }
                                }
                            }
                        } else {
                            System.out.println("Valore non valido!");
                        }
                        break;
                    // opzione per modificare un prodotto (only admin)
                    case 4:
                        if (isAdmin) {
                            if (prodotti != null) {
                                System.out.println("Inserisci il nome del prodotto che vuoi modificare: ");
                                String nome = scan1.next();

                                for (int i = 0; i < prodotti.size(); i++) {
                                    if (prodotti.get(i).getNome().equalsIgnoreCase(nome)) {  // controllo per verificare che il nome inserito sia quello corretto
                                        // menu di scelta per modificare il prodotto secondo un criterio scelto dall'utente
                                        System.out.println("Cosa vuoi modificare: \n1. NOME.\n2. PREZZO.\n3. QUANTITA.");
                                        int scelta2 = scan1.nextInt();

                                        if(scelta2 == 1){
                                            System.out.println("Inserisci il nuovo nome: ");
                                            String nuovoNome = scan1.next();
                                            prodotti.get(i).setNome(nuovoNome);
                                            System.out.println("\nNome modificato correttamente!");
                                        }
                                        else if(scelta2 == 2){
                                            System.out.println("Inserisci il nuovo prezzo: ");
                                            double nuovoPrezzo = scan1.nextDouble();
                                            prodotti.get(i).setPrezzo(nuovoPrezzo);
                                            System.out.println("\nPrezzo modificato correttamente!");
                                        }
                                        else if(scelta2 == 3){
                                            System.out.println("Inserisci la nuova quantità disponibile: ");
                                                int nuovaQuantita = scan1.nextInt();
                                                prodotti.get(i).setQuantita(nuovaQuantita);
                                                System.out.println("\nQuantità modificata correttamente!");
                                        }
                                        else{
                                            System.out.println("Valore inserito non valido!");
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("Valore non valido!");
                        }
                        break;
                    default:
                        System.out.println("Valore non valido!");
                        break;
                }

                // domanda per sapere se l'utente vuole continuare
                System.out.println("\nVuoi fare qualcos'altro? (si o no) ");
                risp = scan1.next();

                if (!risp.equalsIgnoreCase("si") && !risp.equalsIgnoreCase("no")) {
                    System.out.println("Scrivi si o no");
                }

            } while (risp.equalsIgnoreCase("si"));

        }
    }
}

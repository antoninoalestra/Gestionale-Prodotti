import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogoProdotti {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);

        Utente admin = new Admin();
        List<Cliente> clienti = new ArrayList<>();

        int tentativi = 3;
        boolean accesso = false, isAdmin = false;
        do {
            int scelta = 0;
            do {
                System.out.println("1. ACCEDI \n2. REGISTRATI \n3. ESCI");
                System.out.println("Inserisci la tua scelta: ");
                scelta = scanner.nextInt();
                if (scelta < 1 || scelta > 3)
                    System.out.println("Valore non valido!");
            } while (scelta < 1 || scelta > 3);

            String mail, password;
            switch (scelta) {
                case 1:
                    System.out.print("Inserisci email: ");
                    mail = scan.nextLine();
                    System.out.print("Inserisci password: ");
                    password = scan.nextLine();

                    System.out.println();

                    if (admin.getEmail().equals(mail) && admin.getPassword().equals(password)) {
                        System.out.println("Accesso come AMMINISTRATORE!");
                        isAdmin = true;
                        accesso = true;
                    } else if (clienti != null) {
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
            do {
                System.out.println("\nMENU");
                System.out.println("0. ESCI. \n1. VISUALIZZA PRODOTTI.");

                if (isAdmin) {
                    System.out.println("2. AGGIUNGI PRODOTTO. \n3. ELIMINA PRODOTTO. \n4. MODIFICA PRODOTTO.");
                }

                int scelta1 = scan1.nextInt();

                switch (scelta1) {
                    case 0:
                        System.out.println("\nProgramma in chiusura!");
                        System.exit(0);
                        break;
                    case 1:
                        if(prodotti != null){
                            for (Prodotto prodotto : prodotti) {
                                System.out.println(prodotto);
                            }
                        } else{
                            System.out.println("Non ci sono prodotti nel catalogo!");
                        }
                        
                        break;
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
                    case 4:
                        if (isAdmin) {
                            if (prodotti != null) {
                                System.out.println("Inserisci il nome del prodotto che vuoi modificare: ");
                                String nome = scan1.next();

                                for (int i = 0; i < prodotti.size(); i++) {
                                    if (prodotti.get(i).getNome().equalsIgnoreCase(nome)) {
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

                System.out.println("\nVuoi fare qualcos'altro? (si o no) ");
                risp = scan1.next();

                if (!risp.equalsIgnoreCase("si") && !risp.equalsIgnoreCase("no")) {
                    System.out.println("Scrivi si o no");
                }

            } while (risp.equalsIgnoreCase("si"));

        }
    }
}

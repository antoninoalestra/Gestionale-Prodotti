import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);

        Utente admin = new Admin();
        List<Cliente> clienti = new ArrayList<>();

        int tentativi = 3;
        boolean accesso = false;
        do{
            int scelta = 0;            
            do{
                System.out.println("1. ACCEDI \n2. REGISTRATI \n3. ESCI");
                System.out.println("Inserisci la tua scelta: ");
                scelta = scanner.nextInt();
                if(scelta < 1 || scelta >3) System.out.println("Valore non valido!");
            }while(scelta < 1 || scelta >3);
            
            String mail, password;
            boolean isAdmin = false;
            switch(scelta){
                case 1:
                    System.out.print("Inserisci email: ");
                    mail = scan.nextLine();
                    System.out.print("\nInserisci password: ");
                    password = scan.nextLine();

                    System.out.println();

                    if(admin.getEmail().equals(mail) && admin.getPassword().equals(password)){
                        System.out.println("Accesso come AMMINISTRATORE!");
                        isAdmin = true;
                        accesso = true;
                    } else if(clienti != null){
                        boolean trovato = false;
                        for(int i = 0; i < clienti.size() && !trovato; i++){
                            if(clienti.get(i).getEmail().equals(mail) && clienti.get(i).getPassword().equals(password)){
                                System.out.println("Accesso come CLIENTE");
                                accesso = true;
                                trovato = true;
                            }
                        }

                        if(!trovato){
                            System.out.println("Utente non trovato!");
                            tentativi--;
                        }

                    } else{
                        System.out.println("Nessun utente trovato!");
                        tentativi--;
                    }
                    break;
                case 2:
                    System.out.println("NUOVO UTENTE!");
                    System.out.print("Inserisci email: ");
                    mail = scan.nextLine();
                    System.out.print("\nInserisci password: ");
                    password = scan.nextLine();

                    Cliente nuovoCliente = new Cliente(mail, password);
                    if(nuovoCliente != null){
                        clienti.add(nuovoCliente);
                    }
                    break;
                case 3:
                    System.out.println("Programma in chiusura!");
                    System.exit(0);
                    break;
            }

            if(!accesso){
                System.out.println("\nTentativi rimasti: " + tentativi);
            }

        }while(tentativi > 0 && !accesso);

        
    }
}

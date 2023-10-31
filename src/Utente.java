public class Utente {
    private String email;
    private String password;

    public Utente(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String toString(){
        return email + "  " + password;
    }
}

package model;


public class UserBean { 
	
    private int id; 
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String indirizzoSpedizione; 
    private String cittaSpedizione;
    private String capSpedizione; 
    private String provinciaSpedizione;
    private String telefono;
    private boolean isAdmin;

    public UserBean() {
        super();
        // TODO Auto-generated constructor stub
    }

   
    public UserBean(int id, String nome, String cognome, String email, String password,
                    String telefono, String indirizzoSpedizione, String cittaSpedizione,
                    String capSpedizione, String provinciaSpedizione, boolean isAdmin) {
        super();
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.cittaSpedizione = cittaSpedizione;
        this.capSpedizione = capSpedizione;
        this.provinciaSpedizione = provinciaSpedizione;
        this.isAdmin = isAdmin;
    }

    // Metodi getter e setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public String getCittaSpedizione() {
        return cittaSpedizione;
    }

    public void setCittaSpedizione(String cittaSpedizione) {
        this.cittaSpedizione = cittaSpedizione;
    }

    public String getCapSpedizione() {
        return capSpedizione;
    }

    public void setCapSpedizione(String capSpedizione) {
        this.capSpedizione = capSpedizione;
    }

    public String getProvinciaSpedizione() {
        return provinciaSpedizione;
    }

    public void setProvinciaSpedizione(String provinciaSpedizione) {
        this.provinciaSpedizione = provinciaSpedizione;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
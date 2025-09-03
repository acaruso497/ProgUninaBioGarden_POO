public class Main {
    public static void main(String[] args) {
        
        // 1. CREAZIONE AUTORI
        Autore[] autoriLibro = { new Autore("Patrick", "Rothfuss") };
        Autore[] autoriRivista = { new Autore("Vari", "Autori") };

        // 2. CREAZIONE ELEMENTI
        Catalogo libro = new Libro(1001, "Il nome del vento", 2007, "Narrativa");
        Catalogo rivista = new Rivista(2002, "Scientific American", 2024, "Scienza", 5);

        // 3. CREAZIONE UTENTI
        Utente u1 = new Utente("Mario", "Rossi", "mario@email.it");
        Utente u2 = new Utente("Maria", "Bianchi", "maria@email.it");

        // 4. CREAZIONE PRESTITI
        Prestito p1 = new Prestito();
        p1.RegistraPrestito(u1, libro, "01/05/2025", "15/05/2025");

        Prestito p2 = new Prestito();
        p2.RegistraPrestito(u2, rivista, "03/05/2025", "10/05/2025");

        // 5. TEST elemento già non disponibile
        Prestito p3 = new Prestito();
        p3.RegistraPrestito(u2, libro, "17/05/2025", "20/05/2025");
    }
}

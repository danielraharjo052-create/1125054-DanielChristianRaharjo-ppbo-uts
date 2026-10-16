package exception;

public class MenuTidakDitemukanException extends RuntimeException {
    public MenuTidakDitemukanException(String pesan) {
        super(pesan);
    }
}
package med.voll.api.domain;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String messageError) {
        super(messageError);
    }
}

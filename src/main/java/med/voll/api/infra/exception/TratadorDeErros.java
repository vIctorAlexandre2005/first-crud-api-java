package med.voll.api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException error) {
        var errorReceive = error.getFieldErrors();
        return ResponseEntity.badRequest().body(errorReceive.stream().map(DadosErros:: new).toList());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException error) {
        return ResponseEntity.badRequest().body(error.getMessage());
    }

    private record DadosErros(String campo, String mensagem) {
        public DadosErros(FieldError errorFiled) {
            this(errorFiled.getField(), errorFiled.getDefaultMessage());
        }
    }
}

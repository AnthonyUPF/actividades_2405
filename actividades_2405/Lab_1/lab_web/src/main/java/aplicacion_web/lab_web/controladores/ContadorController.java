package aplicacion_web.lab_web.controladores;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContadorController {

    private int contador = 0;

    @GetMapping("/contador")
    public int obtenerContador() {
        return contador;
    }

    @PostMapping("/contador/incrementar")
    public int incrementarContador() {
        contador++;
        return contador;
    }

    @PostMapping("/contador/decrementar")
    public int decrementarContador() {
        contador--;
        return contador;
    }
}

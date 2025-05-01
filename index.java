import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CrudUsuariosApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudUsuariosApplication.class, args);
    }
}

@RestController
@RequestMapping("/users")
class UserController {

    private List<User> users = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "API CRUD de Usuários";
    }

    // CREATE - Criar um novo usuário
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(users.size() + 1); // cria um ID simples
        users.add(user);
        return user;
    }

    // READ - Listar todos os usuários
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // READ - Buscar um usuário pelo ID
    @GetMapping("/{id}")
    public Object getUserById(@PathVariable int id) {
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        return user.orElseGet(() -> new ErrorResponse("Usuário não encontrado"));
    }

    // UPDATE - Atualizar um usuário pelo ID
    @PutMapping("/{id}")
    public Object updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        Optional<User> userOptional = users.stream().filter(u -> u.getId() == id).findFirst();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return user;
        } else {
            return new ErrorResponse("Usuário não encontrado");
        }
    }

    // DELETE - Deletar um usuário pelo ID
    @DeleteMapping("/{id}")
    public Object deleteUser(@PathVariable int id) {
        Optional<User> userOptional = users.stream().filter(u -> u.getId() == id).findFirst();
        if (userOptional.isPresent()) {
            users.remove(userOptional.get());
            return new SuccessResponse("Usuário deletado com sucesso");
        } else {
            return new ErrorResponse("Usuário não encontrado");
        }
    }
}

class User {
    private int id;
    private String name;
    private String email;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

class SuccessResponse {
    private String message;

    public SuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
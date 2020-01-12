package edu.ufp.esof.EngSoftPro.services.authentication;

import edu.ufp.esof.EngSoftPro.models.Aluno;
import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.services.AlunoService;
import edu.ufp.esof.EngSoftPro.services.ExplicadorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AlunoLoginService {

    private Map<String,String> users=new HashMap<>();

    private AlunoService alunoService;

    @Autowired
    public AlunoLoginService(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    public void addUser(String username, String password){

        this.users.put(username,password);
    }

    private Optional<Aluno> isAuthenticated(String username, String password){
        Optional<Aluno> optionalAluno=this.alunoService.findByName(username);
        if(optionalAluno.isPresent()){
            Aluno aluno=optionalAluno.get();
            if(aluno.getPassword().equals(password)){
                return optionalAluno;
            }
        }
        return Optional.empty();
    }

    public boolean authenticateUser(Aluno aluno ,String token){
        if(aluno!=null && aluno.getName()!=null) {
            String cachedToken=this.users.get(aluno.getName());
            if(cachedToken!=null) {
                return cachedToken.equals(token);
            }
        }
        return false;
    }



    public Optional<String> generateToken(String username, String password) {
        Optional<Aluno> optionalAluno=this.isAuthenticated(username,password);
        if(optionalAluno.isPresent()){
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");

                //token generated from concatenate client username and password
                byte[] encodedHash = digest.digest((username+password).getBytes());

                String encodedHashString=bytesToHex(encodedHash);
                this.users.put(username,encodedHashString);
                return Optional.of(encodedHashString);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public Optional<String> generateToken(Credentials credentials) {
        return this.generateToken(credentials.getUsername(),credentials.getPassword());
    }
}

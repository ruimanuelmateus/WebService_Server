package edu.ufp.esof.EngSoftPro.services.authentication;

import edu.ufp.esof.EngSoftPro.models.Explicador;
import edu.ufp.esof.EngSoftPro.services.ExplicadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ExplicadorLoginService {

    private Map<String,String> users=new HashMap<>();

    private ExplicadorService explicadorService;

    @Autowired
    public ExplicadorLoginService(ExplicadorService explicadorService) {
        this.explicadorService = explicadorService;
    }

    public void addUser(String username, String password){

        this.users.put(username,password);
    }

    private Optional<Explicador> isAuthenticated(String username, String password){
        Optional<Explicador> optionalExplicador=this.explicadorService.findByName(username);
        if(optionalExplicador.isPresent()){
            Explicador explicador=optionalExplicador.get();
            if(explicador.getPassword().equals(password)){
                return optionalExplicador;
            }
        }
        return Optional.empty();
    }

    public boolean authenticateUser(Explicador explicador ,String token){
        if(explicador!=null && explicador.getName()!=null) {
            String cachedToken=this.users.get(explicador.getName());
            if(cachedToken!=null) {
                return cachedToken.equals(token);
            }
        }
        return false;
    }



    public Optional<String> generateToken(String username, String password) {
        Optional<Explicador> optionalExplicador=this.isAuthenticated(username,password);
        if(optionalExplicador.isPresent()){
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

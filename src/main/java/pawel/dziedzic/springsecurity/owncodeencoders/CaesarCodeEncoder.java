package pawel.dziedzic.springsecurity.owncodeencoders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CaesarCodeEncoder implements PasswordEncoder {

    @Value("${defaultShift}")
    private int defaultShift;

    @Override
    public String encode(CharSequence userPassword) {
        StringBuilder encodedPassword = new StringBuilder(userPassword);
        for(int i = 0; i < encodedPassword.length(); i ++) {
            int numberElement = encodedPassword.charAt(i);
            if(numberElement + defaultShift> 122) {
                numberElement = 33 + numberElement + defaultShift - 122;
            }
            else {
                numberElement += defaultShift;
            }
            encodedPassword.setCharAt(i, (char) numberElement);
        }
        return encodedPassword.toString();
    }

    @Override
    public boolean matches(CharSequence userPassword, String encodedPassword) {
        return encode(userPassword).equals(encodedPassword);
    }
}
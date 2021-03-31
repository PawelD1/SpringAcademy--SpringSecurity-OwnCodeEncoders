package pawel.dziedzic.springsecurity.owncodeencoders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MyOwnEncoder implements PasswordEncoder {

    @Value("${firstElementIndex}")
    private int firstElementIndex;

    public int digitalInverse(int number) {
        int shift = firstElementIndex - 1;
        number = number - shift;
        if (number > 9) {
            int oneDigit = number % 10;
            int tensDigit = number / 10;
            if (oneDigit == 9) {
                switch (tensDigit) {
                    case 1:
                        return 89 + shift;
                    case 2:
                        return 79 + shift;
                    case 3:
                        return 69 + shift;
                    case 4:
                        return 59 + shift;
                    case 5:
                        return 49 + shift;
                    case 6:
                        return 39 + shift;
                    case 7:
                        return 29 + shift;
                    case 8:
                        return 19 + shift;
                }
            } else {
                return oneDigit * 10 + tensDigit + shift ;
            }
        }
        return number * 10 + shift;
    }

    @Override
    public String encode(CharSequence userPassword) {
        StringBuilder encodedPassword = new StringBuilder(userPassword);
        for(int i = 0; i < encodedPassword.length(); i ++) {
            int numberElement = encodedPassword.charAt(i);
            numberElement = this.digitalInverse(numberElement);
            encodedPassword.setCharAt(i, (char) numberElement);
        }
            return encodedPassword.toString();
    }

    @Override
    public boolean matches(CharSequence userPassword, String encodedPassword) {
        return encode(userPassword).equals(encodedPassword);
    }
}
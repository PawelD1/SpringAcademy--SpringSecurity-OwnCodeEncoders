package pawel.dziedzic.springsecurity.owncodeencoders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunCodeEncoders {

    private final CaesarCodeEncoder caesarCodeEncoder;
    private final MyOwnEncoder myOwnEncoder;
    private final MyOwnEncoderMechanism myOwnEncoderMechanism;

    @Autowired
    public RunCodeEncoders(CaesarCodeEncoder caesarCodeEncoder, MyOwnEncoder myOwnEcnoder, MyOwnEncoderMechanism myOwnEncoderMechanism) {
        this.caesarCodeEncoder = caesarCodeEncoder;
        this.myOwnEncoder = myOwnEcnoder;
        this.myOwnEncoderMechanism = myOwnEncoderMechanism;
        this.checkCodeEncoders();
    }

    public void checkCodeEncoders() {
        System.out.println("--- Try to encode word: #AveCezar-AveJa!$ ---");
        System.out.println("Caesar Code Encoder: " + caesarCodeEncoder.encode("#AveCezar-AveJa!$"));
        System.out.println("Caesar Code Encoder works correctly: " + caesarCodeEncoder.matches("#AveCezar-AveJa!$", caesarCodeEncoder.encode("#AveCezar-AveJa!$")));
        System.out.println("My own Code Encoder: " + myOwnEncoder.encode("#AveCezar-AveJa!$"));
        System.out.println("My own Code Encoder works correctly: " + myOwnEncoder.matches("#AveCezar-AveJa!$", myOwnEncoder.encode("#AveCezar-AveJa!$")));
        System.out.println("--- The end of using encoders ---");
        myOwnEncoderMechanism.calculate();
    }
}
package authenticationAndAuthorization;

import org.testng.annotations.Test;

import java.util.Base64;

public class Base64Encoding {

    @Test
    public void basic64Encoding()
    {
        String userNameColonPassword = "myUsername:myPassword";

        String encodedBase64 = Base64.getEncoder().encodeToString(userNameColonPassword.getBytes());
        System.out.println("EncodedBase64 : "+encodedBase64);

       byte[] decodedBytes =  Base64.getDecoder().decode(encodedBase64);
       System.out.println("DecodedBase64 : "+new String(decodedBytes));

    }




}

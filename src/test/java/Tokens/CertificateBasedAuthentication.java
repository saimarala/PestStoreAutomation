package Tokens;

import static io.restassured.RestAssured.given;

public class CertificateBasedAuthentication {
    //In certificate-based authentication, a certificate is used to authenticate with the API.
    // This is useful when dealing with secured APIs that use SSL certificates.
    void test(){
        given()
                .auth()
                .certificate("/path/to/client_cert.p12", "certPassword")
                .when()
                .get("https://api.secure.com/data")
                .then()
                .statusCode(200);


    }
}

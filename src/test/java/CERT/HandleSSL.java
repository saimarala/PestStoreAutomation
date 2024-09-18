package CERT;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import static io.restassured.RestAssured.given;

public class HandleSSL {

    void test() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, KeyManagementException {
        //1. Trusting All Certificates (Default Behavior)
        //Rest Assured, by default, trusts all certificates (including self-signed certificates),
        // so you don't need to take extra steps for simple testing scenarios with SSL.
        given()
                .when()
                .get("https://your-secure-api.com")
                .then()
                .statusCode(200);
      //2. Disabling SSL Certificate Validation (For Self-Signed Certificates)
        //If you're testing with a self-signed certificate and want to ensure Rest Assured doesn't
        // fail due to certificate validation, you can explicitly
        // disable SSL validation by using relaxedHTTPSValidation()
        given()
                .relaxedHTTPSValidation()  // Disable SSL certificate validation
                .when()
                .get("https://your-secure-api.com")
                .then()
                .statusCode(200);
       //3. Using a Specific Protocol (e.g., TLS)
        //You can specify a particular SSL/TLS protocol, like TLS or SSL,
        // using the relaxedHTTPSValidation(String protocol) method.
        given()
                .relaxedHTTPSValidation("TLS")  // Specify TLS protocol
                .when()
                .get("https://your-secure-api.com")
                .then()
                .statusCode(200);
         //4. Using a Custom TrustStore (For Custom Certificates)
        //If you have a custom certificate authority (CA) or you need to provide a specific certificate
        // for validation, you can configure Rest Assured to use a custom trust store.
        //
        //Steps to Use a Custom TrustStore:
        //Create a Java Keystore (JKS) file containing the custom certificates.
        //Load this keystore into Rest Assured using the trustStore() method.
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream trustStream = new FileInputStream(new File("path_to_truststore.jks"));
        trustStore.load(trustStream, "password".toCharArray());  // Load the keystore

        given()
                .trustStore(trustStore)  // Use the custom trust store
                .when()
                .get("https://your-secure-api.com")
                .then()
                .statusCode(200);
      //The trustStore() method is used to provide the custom trust store.
        //This ensures that only the certificates in your custom trust store are trusted during the SSL handshake.


        //5. Configuring KeyStore for Mutual SSL Authentication
        //If the API requires Mutual SSL Authentication (i.e., both the client and server authenticate each
        // other using certificates), you'll need to configure both the KeyStore (for the client certificate)
        // and the TrustStore (to trust the server's certificate).
//        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        FileInputStream keyStream = new FileInputStream(new File("path_to_keystore.jks"));
//        keyStore.load(keyStream, "keystore_password".toCharArray());  // Load the keystore
//
//        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        FileInputStream trustStream = new FileInputStream(new File("path_to_truststore.jks"));
//        trustStore.load(trustStream, "truststore_password".toCharArray());  // Load the truststore
//
//        given()
//                .keyStore(keyStore, "keystore_password")  // Client certificate
//                .trustStore(trustStore)  // Trust store for server certificate
//                .when()
//                .get("https://your-secure-api.com")
//                .then()
//                .statusCode(200);

        //6. Custom SSLContext Configuration
        //If you need more control over the SSL context, you can create and
        // configure a custom SSLContext object and use it in Rest Assured.
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, null, new java.security.SecureRandom());  // Custom initialization
//
//        given()
//                .sslContext(sslContext)  // Use the custom SSL context
//                .when()
//                .get("https://your-secure-api.com")
//                .then()
//                .statusCode(200);


    }
}

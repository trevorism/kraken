import com.trevorism.secure.PropertiesProvider
import org.jasypt.util.text.StrongTextEncryptor

class Encryptor {

    public static final String THING_TO_ENCRYPT = "hello world"

    static void main(String[] args) {
        StrongTextEncryptor encryptor = new StrongTextEncryptor()
        String encryptionKey = new PropertiesProvider("encryption.properties").getProperty("encryptionKey")

        encryptor.setPassword(encryptionKey)
        def encrypted = encryptor.encrypt(THING_TO_ENCRYPT)
        println encrypted

    }
}

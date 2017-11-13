package seedu.address.commons.util.encryption;

import java.io.FileNotFoundException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test the File Encryptor
 */
public class FileEncryptorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getImage_exitingImage() throws Exception {
        thrown.expect(FileNotFoundException.class);
        FileEncryptor.decryptFile("test", "test");
    }
}

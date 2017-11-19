package seedu.address.commons.util.encryption;

//@@author quanle1994

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;

/**
 * Test the File Encryptor
 */
public class FileEncryptorTest {
    @Test
    public void decrypt_successful() throws Exception {
        FileOutputStream test = new FileOutputStream("data/test.encrypted");
        test.write("test".getBytes());
        FileEncryptor.encryptFile("test", "test", false);
        FileEncryptor.decryptFile("test", "test");
        File file = new File("data/test.encrypted");
        file.delete();
        file = new File("data/addressbook.xml");
        file.delete();
    }
}

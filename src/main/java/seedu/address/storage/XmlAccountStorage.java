package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class XmlAccountStorage implements AccountStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlAddressBookStorage.class);

    private String filePath;

    public XmlAccountStorage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getAccountFilePath() {
        return filePath;
    }

    public Optional<ReadOnlyAccount> readAccount(String FilePath) throws FileNotFoundException, DataConversionException {
        requireNonNull(filePath);

        File accountFile = new File(filePath);
        if (!accountFile.exists()) {
            logger.info("AddressBook file " + accountFile + " not found");
            return Optional.empty();
        }

        ReadOnlyAccount accountOptional = XmlFileStorage.loadAccountFromSaveFile(new File(filePath));

        return Optional.of(accountOptional);
    }

    @Override
    public Optional<ReadOnlyAccount> readAccount() throws FileNotFoundException, DataConversionException {
        return readAccount(filePath);
    }

    @Override
    public void saveAccount(ReadOnlyAccount addressBook) {

    }

    @Override
    public void saveAccount(ReadOnlyAccount account, String filePath) throws IOException {
        requireNonNull(account);
        requireNonNull(filePath);

        File file = new File(filePath);
        FileUtil.createIfMissing(file);
        XmlFileStorage.saveAccountToFile(file, new XmlSerializableAccount(account));
    }
}

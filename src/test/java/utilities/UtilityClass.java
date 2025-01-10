package utilities;

import io.restassured.path.json.JsonPath;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Properties;

public class UtilityClass {

    //<editor-fold desc="Variables">
    private final Properties properties;
    private final String validJsonFilePath = "src/test/resources/jsonFiles/validbooking.json";
    private final String maxDigitPhoneNumberJsonFilePath = "src/test/resources/jsonFiles/maxDigitPhoneNumberJsonFilePath.json";
    private final String onlyMandatoryJsonFilePath = "src/test/resources/jsonFiles/onlyMandatoryValuesbooking.json";
    private final String invalidJsonFilePath = "src/test/resources/jsonFiles/invalidbooking.json";
    private final String invalidPhoneNumber = "src/test/resources/jsonFiles/invalidPhoneNumber.json";
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private String readJsonFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        return IOUtils.toString(is, "UTF-8");
    }
    //</editor-fold>

    //<editor-fold desc="Public Methods">
    public UtilityClass() {
        BufferedReader reader;
        String propertyFilePath = "src/test/resources/endpoints.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getValueOf(String key) {
        return properties.getProperty(key);
    }

    public String readValidJsonInputFile() throws IOException {
        return readJsonFile(validJsonFilePath);
    }

    public String readOnlyMandatoryJsonInputFile() throws IOException {
        return readJsonFile(onlyMandatoryJsonFilePath);
    }

    public String readMaxDigitPhoneNumberJsonInputFile() throws IOException {
        return readJsonFile(maxDigitPhoneNumberJsonFilePath);
    }

    public String readInvalidJsonInputFile() throws IOException {
        return readJsonFile(invalidJsonFilePath);
    }

    public String readInvalidPhoneNumber() throws IOException {
        return readJsonFile(invalidPhoneNumber);
    }

    public String getString(String responseString, String path){
        JsonPath jsonPath = new JsonPath(responseString);
        String value = jsonPath.getString(path);
        return value;
    }
    //</editor-fold>
}
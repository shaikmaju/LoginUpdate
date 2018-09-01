package middleware.org.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;




public class PropertyValuesFromFile {

	private static final Logger LOG = LogManager.getLogger(PropertyValuesFromFile.class);
	private final static PropertyValuesFromFile INSTANCE = new PropertyValuesFromFile();

    public static PropertyValuesFromFile getInstance() {
        return INSTANCE;
    }

    private static Properties configuration = new Properties();

    private static Properties getConfiguration() {
        return configuration;
    }

    public synchronized void initialize(final String file) {
        LOG.info("initialize - initializing with file: {}"+file);
        InputStream in = null;
        try {
            in = new FileInputStream(new File(file));
            configuration.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("initialize - {} initialized."+file);
    }

    public String getConfiguration(final String key) {
        LOG.trace("getConfiguration - called with key: {}"+ key);
        LOG.trace("getConfiguration - value of: {} is: {}"+key+""+getConfiguration().get(key).toString().trim());
        return getConfiguration().get(key).toString().trim();
    }

    public String getConfigurationWithDefaultValue(final String key, final String defaultValue) {
        return (String) getConfiguration().getProperty(key, defaultValue);
    }

    public void setStringConfiguration(final String key, final String value) {
        LOG.trace("setStringConfiguration - called with key: {} & value: {}"+ key+""+value);
        configuration.put(key, value);
    }

    public void setListConfiguration(final String key, final List<String> value) {
        LOG.trace("setListConfiguration - called with key: {} & value: {}"+key+""+value);
        configuration.put(key, value);
    }

    @SuppressWarnings("unchecked")
	public List<String> getListConfiguration(final String key) {
        LOG.trace("getListConfiguration - called with key: {} & returning: {}"+key+""+ ((List<String>)configuration.get(key)).toString());
        return (List<String>)configuration.get(key);
    }

}

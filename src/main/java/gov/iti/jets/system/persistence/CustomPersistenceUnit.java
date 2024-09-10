package gov.iti.jets.system.persistence;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import org.hibernate.cfg.AvailableSettings;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnit implements PersistenceUnitInfo {
    @Override
    public String getPersistenceUnitName() {
        return "Mysql";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        try {
            HikariDataSource dataSource = new HikariDataSource();

            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ecommerce");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setMaximumPoolSize(200);
            dataSource.setConnectionTimeout(30000);  // 30 seconds
            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException("Failed to configure HikariDataSource", e);
        }

    }

    @Override
    public List<String> getMappingFileNames() {
        return List.of();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return List.of();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of(
                "gov.iti.jets.models.User",
                "gov.iti.jets.models.Admin",
                "gov.iti.jets.models.Category",
                "gov.iti.jets.models.Product",
                "gov.iti.jets.models.Order",
                "gov.iti.jets.models.OrderItem",
                "gov.iti.jets.models.CartItem"

                // Add more classes as needed
        );
    }

     @Override
     public boolean excludeUnlistedClasses() {
         return false;
     }

     @Override
     public SharedCacheMode getSharedCacheMode() {
         return SharedCacheMode.ENABLE_SELECTIVE;
     }

     @Override
     public ValidationMode getValidationMode() {
         return ValidationMode.NONE;
     }

    @Override
    public  Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
        properties.setProperty(AvailableSettings.SHOW_SQL, "true");
        properties.setProperty(AvailableSettings.FORMAT_SQL, "true");
        return properties;
    }

     @Override
     public String getPersistenceXMLSchemaVersion() {
         return "2.2";
     }

     @Override
     public ClassLoader getClassLoader() {
         return Thread.currentThread().getContextClassLoader();
     }

     @Override
     public void addTransformer(ClassTransformer classTransformer) {
     }

     @Override
     public ClassLoader getNewTempClassLoader() {
         return Thread.currentThread().getContextClassLoader();
     }
 }

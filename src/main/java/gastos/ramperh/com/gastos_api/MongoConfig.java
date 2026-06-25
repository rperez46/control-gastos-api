package gastos.ramperh.com.gastos_api;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	@Value("${spring.data.mongodb.database}")
	private String databaseName;

	@Override
	protected String getDatabaseName() {
		return this.databaseName;
	}

	@Override
	public MongoClient mongoClient() {
		ConnectionString cs = new ConnectionString(this.mongoUri);
		MongoClientSettings settings = MongoClientSettings.builder()
			.applyConnectionString(cs)
			.build();
		return MongoClients.create(settings);
	}
}
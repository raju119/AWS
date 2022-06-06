package aws.dynamodb.demo.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class DemoConfig {
    public static final DynamoDBMapperConfig.TableNameResolver TABLE_NAME_RESOLVER = (className, config) -> "demo";

    ClientConfiguration getClientConfiguration() {
            ClientConfiguration cfg = new ClientConfiguration();
            cfg.setProtocol(Protocol.HTTPS);
            cfg.setProxyPort(8089);
            return cfg;
        }

    @Bean("dynamoDBMapper")
    public DynamoDBMapper dynamoDBMapperLocal(){
        Regions region = Regions.US_EAST_1;
        DynamoDBMapperConfig dbMapperConfig = new DynamoDBMapperConfig.Builder().withTableNameResolver(TABLE_NAME_RESOLVER).build();
        AmazonDynamoDBClient dynamoDBClient = getAmazonDynamoDBLocalClient(region);
        return  new DynamoDBMapper(dynamoDBClient, dbMapperConfig);
    }

    private AmazonDynamoDBClient getAmazonDynamoDBLocalClient(Regions region){
        return (AmazonDynamoDBClient) AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
    }

    @Bean("SnsClient")
    public SnsClient snsClient(){
        //Region region = Region.getRegion(Regions.US_EAST_1);
        SnsClient snsClient = SnsClient.builder().build();
        return snsClient;
    }
}

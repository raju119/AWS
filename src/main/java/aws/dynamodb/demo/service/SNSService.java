package aws.dynamodb.demo.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import javax.annotation.Resource;

@Service
public class SNSService {

    @Resource(name = "SnsClient")
    SnsClient snsClient;


    private String topicARN = "arn:aws:sns:us-east-1:372476467608:my-first-topic";

    public PublishResponse publishMsg(String message) {
        PublishResponse response = null;
        try {
            PublishRequest resquest = PublishRequest.builder()
                    .message(message)
                    .topicArn(topicARN)
                    .build();

            response = snsClient.publish(resquest);

        } catch (Exception e) {

        }
        return response;
    }
}

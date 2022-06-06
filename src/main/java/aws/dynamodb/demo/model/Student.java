package aws.dynamodb.demo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Student{

    public String getsId() {
        return sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }


    @DynamoDBHashKey(attributeName = "sId")
    private String sId;

    @DynamoDBAttribute(attributeName = "sName")
    private String sName;
}

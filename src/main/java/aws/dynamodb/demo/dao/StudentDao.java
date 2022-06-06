package aws.dynamodb.demo.dao;

import aws.dynamodb.demo.model.Student;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDao {

    @Resource(name = "dynamoDBMapper")
    DynamoDBMapper dynamoDBMapper;

    @Autowired
    Student student;

    public List<Student> getDetails(String sId){
        System.out.println("Inside Dao method");
        Regions region = Regions.US_EAST_1;
        List<Student> students = new ArrayList<>();
        QueryResultPage<Student> itemList;

        //#Expresion attributes
        Map<String, String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#sId", "sId");
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":sId", new AttributeValue().withS(sId));

        DynamoDBQueryExpression<Student> retrieveQuery = new DynamoDBQueryExpression<Student>()
                .withKeyConditionExpression("#sId=:sId")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues)
                .withScanIndexForward(false);
        try{
            itemList = dynamoDBMapper.queryPage(Student.class, retrieveQuery);
            students = itemList.getResults();
        }catch (Exception e){
            throw e;
        }
        return students;
    }

    public boolean saveItem(String sId, String sName){
        student.setsId(sId);
        student.setsName(sName);
        try{
            dynamoDBMapper.save(student);
        }catch (Exception e){
            System.out.println("Exception occur in DAO class" + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

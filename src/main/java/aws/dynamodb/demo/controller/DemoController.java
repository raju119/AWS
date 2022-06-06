package aws.dynamodb.demo.controller;

import aws.dynamodb.demo.dao.StudentDao;
import aws.dynamodb.demo.model.Student;
import aws.dynamodb.demo.service.SNSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    StudentDao studentDao;

    @Autowired
    SNSService snsService;

    @GetMapping(value = "getDetails/{sId}")
    public List<Student> getAllValues(@PathVariable String sId){
        System.out.println("Inside Controler");
        return studentDao.getDetails(sId);
    }

    @PostMapping(value = "save/{sId}/{sName}")
    public String saveItem(@PathVariable String sId, @PathVariable String sName){
        return  studentDao.saveItem(sId, sName) ? "Success" : "Something went Wrong";
    }

    @GetMapping(value="/test")
    public String testInstance(){
        return "Hello your application is successfully deployed to EC2 and Running...";
    }

    @PostMapping(value="publish")
    public String puslishMessage(@RequestParam String message){
        int code = snsService.publishMsg(message).sdkHttpResponse().statusCode();
        if(code == 200) return "Notification sent.";
        return "Yekkado tannindhi... logs chuskora ayya..";
    }
}

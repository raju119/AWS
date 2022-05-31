package aws.dynamodb.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    StudentDao studentDao;

    @GetMapping(value = "getDetails/{sId}")
    public List<Student> getAllValues(@PathVariable String sId){
        System.out.println("Inside Controler");
        return studentDao.getDetails(sId);
    }

    @PostMapping(value = "save/{sId}/{sName}")
    public String saveItem(@PathVariable String sId, @PathVariable String sName){
        return  studentDao.saveItem(sId, sName) ? "Success" : "Something went Wrong";
    }
}

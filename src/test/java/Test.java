import com.aiyun.cache.CacheUtils;
import org.apache.log4j.Logger;

/**
 * Created by zhaoy on 2016/12/7.
 */
public class Test {

    private String getDemoName(){
        CacheUtils cacheUtils = CacheUtils.getInstance();
        String demoName = null;
        Demo demo = new Demo();
        demoName = cacheUtils.getValue("demoname");
        if(demoName == null){
            demoName = demo.getName();
            cacheUtils.setValue("demoname",demoName);
        }
        return demoName;
    }

    public static void main(String[] args) {
        Test test = new Test();
        String demoName = test.getDemoName();
        System.out.println(demoName);
        String demoName1 = test.getDemoName();
        System.out.println(demoName1);
    }
}

class Demo{

    Logger logger = Logger.getLogger(this.getClass());

    public String getName(){
        logger.info("request to demo name");
        return "demo";
    }
}

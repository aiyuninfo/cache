import com.aiyun.cache.CacheUtils;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.function.Predicate;

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
        List<Integer> list = new ArrayList<>();
        for(int i = 100; i > 0; i--){
            list.add(i);
        }
        Optional<List<Integer>> optional = Optional.ofNullable(list);
        Optional<List<Integer>> list1 = optional.map(integer -> {
            List<Integer> integerList = new ArrayList<>();
            Iterator<Integer> iterator = integer.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next >= 30) {
                    integerList.add(next);
                }
            }
            return integerList;
        });
        list.sort((o1, o2) -> o1.compareTo(o2));
        Optional<List<Integer>> list3 = list1.filter(integers -> integers.size() > 200);
        list.forEach(integer -> {
            System.out.println(integer);
        });
        System.out.println(list1);
        List<Integer> list2 = list1.orElse(new ArrayList<>());
        System.out.println(list2);
    }
}

class Demo{

    Logger logger = Logger.getLogger(this.getClass());

    public String getName(){
        logger.info("request to demo name");
        return "demo";
    }
}

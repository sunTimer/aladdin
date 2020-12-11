import com.nucc.entity.App;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringStarter {

    public static void main(String[] args) {
        fileSystemXmlApplicationContext();
    }

    private static void fileSystemXmlApplicationContext() {
        String xml = "//Users/shixu/IdeaProjects/aladdin/aladdin-spring/src/main/resources/spring-context-user.xml";
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(xml);

        App app = applicationContext.getBean("app", App.class);
        System.out.println(app);
    }

}


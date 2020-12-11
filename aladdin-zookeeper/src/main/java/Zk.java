import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Zk {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Watcher watcher = new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event.getPath());
                System.out.println(event.getState());
                System.out.println(event.getType());
            }
        };

        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 100000, watcher);
        zooKeeper.getChildren("/", true);
        List<String> children = zooKeeper.getChildren("/", watcher);

        for (String child : children) {
            System.out.println(child);
        }
    }

    public void newTest(){
        Zk zk = new Zk();

        Zk zk1 = new Zk();

        try {
            Zk zk2 = new Zk();
            File file = new File("dd");
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

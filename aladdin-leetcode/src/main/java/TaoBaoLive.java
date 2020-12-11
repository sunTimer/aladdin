import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaoBaoLive {

    List<Anchor> anchorList = new ArrayList<>();



    public static void main(String[] args) {

    }


    public Anchor openLive(){
        Anchor anchor = new Anchor();
        anchorList.add(anchor);
        return anchor;
    }


    public void giveOk(User user, Anchor anchor) {
        user.giveOk(anchor);
    }
}

class UserScoreNode {
    User user;
    int score;

    public UserScoreNode(User user, int score) {
        this.user = user;
        this.score = score;
    }
}

class Anchor {

    Map<User, UserScoreNode> map = new HashMap<>();

    AtomicInteger score = new AtomicInteger(0);

    PriorityQueue<UserScoreNode> priorityQueue = new PriorityQueue<>(10, new Comparator<UserScoreNode>() {
        @Override
        public int compare(UserScoreNode o1, UserScoreNode o2) {
            return o1.score - o2.score;
        }
    });


    /**
     * 点赞
     *
     * @param user
     */
    public void giveOk(User user) {
        score.incrementAndGet();
        UserScoreNode userScoreNode = map.get(user);
        if (userScoreNode == null) {
            UserScoreNode node = new UserScoreNode(user, 1);
            map.put(user, node);
            priorityQueue.add(node);
        } else {
            userScoreNode.score = userScoreNode.score + 1;
        }
    }

    public void cancleOk(User user) {
        score.decrementAndGet();
        UserScoreNode userScoreNode = map.get(user);
        if (userScoreNode == null) {
            UserScoreNode node = new UserScoreNode(user, -1);
            map.put(user, node);
            priorityQueue.add(node);
        } else {
            userScoreNode.score = userScoreNode.score - 1;
        }
    }


}

class User {

    public void giveOk(Anchor anchor) {
        anchor.giveOk(this);
    }

    public void canceOk(Anchor anchor) {
        anchor.cancleOk(this);
    }
}
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDaoImpl implements UserDao{
    public static UserDaoImpl userDao = null;
    public Map<String,List<User>> takeMap;
    public Map<String,User> userMap;
    private UserDaoImpl(){
        takeMap = new HashMap<>();
        userMap = new HashMap<>();

    }
    public static UserDaoImpl getInstance(){
        if(userDao == null){
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    @Override
    public int getAllDebtMoney(String phoneNo) {
        AtomicInteger amount = new AtomicInteger();
          if(takeMap.containsKey(phoneNo)){
              for(Map.Entry<String,List<User>>users : takeMap.entrySet()){
                  users.getValue().forEach(val ->{
                      amount.set(amount.get() + val.getToatlAmountNeedsToGet());
                  });
              }
          }
          return amount.get();
    }

    @Override
    public int getAllOwedMoney(String userName) {
        if(userMap.containsKey(userName)){
            return userMap.get(userName).getTotalAmoutNeedsToGive();
        }
        return 0;
    }

    @Override
    public void addTransaction(String a, String b, int amount) {
        //  a take 10 from b
        List<User>result = new ArrayList<>();
        if(takeMap.containsKey(a)){
            result = takeMap.get(a);
        }
        User user = result.stream().filter(res ->res.getName().equals(b)).findAny().orElse(null);
        if(user == null){
            user = userMap.get(b);
        }
        user.setTotalAmoutNeedsToGive(user.getTotalAmoutNeedsToGive()+amount);
        result.add(user);
        takeMap.put(a,result);
        User user1 = userMap.get(a);
        user1.setToatlAmountNeedsToGet(user1.getToatlAmountNeedsToGet() + amount);
    }

    @Override
    public void addUser(User user) {
        userMap.put(user.getName(),user);
    }

    @Override
    public User getMostDebtUser() {
        User user = null;
        int max = 0;
        for(Map.Entry<String,User>users : userMap.entrySet()){
            if(users.getValue().getToatlAmountNeedsToGet() > max){
                max = users.getValue().getToatlAmountNeedsToGet();
                user = users.getValue();
            }
        }
        return user;
    }

    @Override
    public User getMostOwedUser() {
        User user = null;
        int max = 0;
        for(Map.Entry<String,User>users : userMap.entrySet()){
            if(users.getValue().getToatlAmountNeedsToGet() > max){
                max = users.getValue().getTotalAmoutNeedsToGive();
                user = users.getValue();
            }
        }
        return user;
    }
}

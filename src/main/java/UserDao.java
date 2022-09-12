public interface UserDao {
     int getAllDebtMoney(String phoneNo);
     int getAllOwedMoney(String phoneNo);
     void addTransaction(String a,String b,int amounrt);
     void addUser(User user);
     User getMostDebtUser();
     User getMostOwedUser();
}

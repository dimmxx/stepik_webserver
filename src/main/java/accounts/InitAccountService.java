package accounts;

public class InitAccountService {

    private static AccountService accountService;

    public static AccountService getAccountService(){
        if(accountService == null){
            accountService = new AccountService();
        }
        return accountService;
    }




}

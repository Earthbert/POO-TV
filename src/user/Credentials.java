package user;

public class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String Country;
    private int balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getBalance() {
        return String.valueOf(balance);
    }

    public void setBalance(String balance) {
        this.balance = Integer.parseInt(balance);
    }
}

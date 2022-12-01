package user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {
    private final String name;
    private final String password;
    private final String accountType;
    private final String country;
    private int balance;

    @JsonCreator
    private Credentials(@JsonProperty("name") final String name,
                       @JsonProperty("password") final String password,
                       @JsonProperty("accountType") final String accountType,
                       @JsonProperty("country") final String country,
                       @JsonProperty("balance") final int balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public Credentials(Credentials credentials) {
        this.name = credentials.name;
        this.password = credentials.password;
        this.accountType = credentials.accountType;
        this.country = credentials.country;
        this.balance = credentials.balance;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCountry() {
        return country;
    }

    public String getBalance() {
        return String.valueOf(balance);
    }
}

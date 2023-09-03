package com.example.demo.Zsample.jpa.compositeKey;


import javax.persistence.*;
import java.io.Serializable;


// 因為accountjpa為composite primary key, 所以對應的欄位都需要加上@Id註解
// 且class必須增加對應設定composite primary key的class名稱
// 另外補充, 若其他資料夾內有相同名稱的bean就會讓spring boot無法啟動, 故將此class由原先的Account改名為Accounts
@Entity
@Table(name = "accountjpa")
@IdClass(AccountsId.class)
public class Accounts implements Serializable {
    @Id
    @Column(name = "fromaccount")
    String fromaccount;

    @Column(name = "toaccount")
    String toaccount;

    @Id
    @Column(name = "fromname")
    String fromname;

    @Column(name = "toname")
    String toname;

    @Column(name = "money")
    Integer money;

    public Accounts(){

    }

    public Accounts(String fromAccount, String toAccount, String fromName, String toName, Integer money) {
        this.fromaccount = fromAccount;
        this.toaccount = toAccount;
        this.fromname = fromName;
        this.toname = toName;
        this.money = money;
    }

    public String getFromAccount() {
        return fromaccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromaccount = fromAccount;
    }

    public String getToAccount() {
        return toaccount;
    }

    public void setToAccount(String toAccount) {
        this.toaccount = toAccount;
    }

    public String getFromName() {
        return fromname;
    }

    public void setFromName(String fromName) {
        this.fromname = fromName;
    }

    public String getToName() {
        return toname;
    }

    public void setToName(String toName) {
        this.toname = toName;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}

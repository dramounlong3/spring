package com.example.demo.Zsample.jpa.compositeKey;

import java.io.Serializable;
import java.util.Objects;


//配合myjdbc.accountjpa為composite primary key, 故需增加此class, 並設定composite key對應的欄位成員
//主要需包含override的method
public class AccountsId implements Serializable {

    private String fromaccount;
    private String fromname;

    public AccountsId(){

    }

    public AccountsId(String fromAccount, String fromName){
        this.fromaccount = fromAccount;
        this.fromname = fromName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        AccountsId accountId = (AccountsId) o;
        return fromaccount.equals(accountId.fromaccount) &&
                fromname.equals(accountId.fromname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromaccount, fromname);
    }

}

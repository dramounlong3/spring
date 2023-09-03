package com.example.demo.Zsample.jpa.compositeKey;

import com.example.demo.Zsample.jpa.compositeKey.Accounts;
import com.example.demo.Zsample.jpa.compositeKey.AccountsId;
import org.springframework.data.repository.CrudRepository;


// 繼承spring boot jpa的class, 第一個參數放要對應的table class, 第二個參數放此table的primary key變數類型
// 因為Accounts屬於composite primary key, 故這邊需要放另外設定的composite primary key class於第二個參數
public interface AccountsJpaRepository extends CrudRepository<Accounts, AccountsId> {
//    //雖然只有定義interface, 但hibernate會自動幫我們實作
//
//    //必須回傳Optional的StudentJpa型態, 前面呼叫才能以StudentJpa的型態接收並使用orElse
//    Optional<Account> findByName(String studentName);
//
//    Optional<Account> findByIdAndName(Integer studentId, String studentName);
//
//    // @Query 使用sql語法取代spring jpa產生的語法, 通常用於複雜的搜尋時
//    // natvieQuery = true 一般的SQL語法
//    // nativeQuery = false JPQL(除非很熟否則很少用)
//    @Query(value = "SELECT id, name, courselist, todolist FROM threetier WHERE id = ?1 AND todolist = ?2", nativeQuery = true)
//    List<Account> useQuery(Integer id, String todolist);

}

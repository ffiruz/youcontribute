package com.ferdi.youcontribute.repository;

import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface IssueRepository  extends PagingAndSortingRepository<Issue,Integer> {

    public List<Issue> findAll();

    public List<Issue> findByRepository(Repository repository);


    @Query(value = "select * from issue where id not in (select issue_id from issue_challenge) order by rand() limit 1", nativeQuery = true)
    Optional<Issue> findRandomIssue();

    //Burada issuelar -> githubdakiler
    //issue_challenge ise -> kullanıcaya önerdiğimiz issuları tuttuğumuz tablo
    //Yukarıdaki sorgu şunu yapıyor.->Issue tablosundan random getirlecek bir data .Ancak bunu getiriken , issue_challenge tablosunda olmayan bir kayıt olmalı.
    //Çünkü issue_challenge kullanıcaya gönderilmiş issueları eklediğimiz tablo
}

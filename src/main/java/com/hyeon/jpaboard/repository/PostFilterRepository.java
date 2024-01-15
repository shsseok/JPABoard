package com.hyeon.jpaboard.repository;

import com.hyeon.jpaboard.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostFilterRepository {

    private final EntityManager em;

    public List<Post> findSortAllList(String sortMethod)
    {
        String sql = "select p from "
                    +"Post p "
                    +"join fetch p.member m "
                    +"order by ";

        if(sortMethod.equals("DATE"))
        {
            sql+="p.commonDate.createDate DESC";

        } else if (sortMethod.equals("VIEW")) {
            sql+="p.postViews DESC";
        }
        else if (sortMethod.equals("TAG"))
        {
            sql = "SELECT p FROM Tag t JOIN t.post p " +
                    "join fetch p.member m " +
                    "GROUP BY p ORDER BY COUNT(t) DESC";

        }
        else if(sortMethod.equals("LIKE"))
        {
            sql = "SELECT p FROM Likes l JOIN l.post p " +
                    "join fetch p.member m " +
                    "GROUP BY p ORDER BY COUNT(l) DESC";
        }
        else
        {
            sql="select p FROM Post p JOIN FETCH p.member m";
        }
        List<Post> resultList = em.createQuery(sql, Post.class).getResultList();
        return  resultList;
    }

    }


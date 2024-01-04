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
                    +"join fetch p.member m"
                    +"order by ";

        if(sortMethod.equals("DATE"))
        {
            sql+="p.commonDate.createDate DESC";

        } else if (sortMethod.equals("VIEW")) {
            sql+="p.postViews DESC";
        }
        else if (sortMethod.equals("TAG"))
        {
            sql="select  from "
                    +"Post p "
                    +"join fetch p.tagList l"
                    +"order b"
        }
        else
        {
            sql+=""
        }
        List<Post> resultList = em.createQuery(sql, Post.class).getResultList();
        return  resultList;
    }

    }


package com.hyeon.jpaboard.domain;

import com.hyeon.jpaboard.domain.util.CommonDate;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostUpdateDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String postTitle;
    @Lob
    private String postContent;
    @Embedded
    private CommonDate commonDate;
    private Long postViews;
    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private List<Likes> likesList=new ArrayList<>();
    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private List<Tag> tagList=new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Review> reviewList=new ArrayList<>();
    @Builder
    public Post(Member member, String postTitle, String postContent,LocalDateTime localDateTime) {
        this.member = member;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postViews=0L;
        this.commonDate=CommonDate.builder()
                .createDate(localDateTime)
                .updateDate(LocalDateTime.now())
                .build();
    }
    public Post updatePost(PostUpdateDto postUpdateDto)
    {
            this.postContent=postUpdateDto.getPostContent();
            commonDate.changeUpdateDate(LocalDateTime.now());
            return this;
    }
    public void upPostView(Post post)
    {
      this.postViews=post.getPostViews()+1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}

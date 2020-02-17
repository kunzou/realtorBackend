package kun.dev.springBootAngular.service;

import kun.dev.springBootAngular.Domain.Blog;
import kun.dev.springBootAngular.Domain.BlogCard;
import kun.dev.springBootAngular.Domain.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BlogService {
  private static final Logger logger = LoggerFactory.getLogger(BlogService.class);
  private MongoTemplate mongoTemplate;

  public BlogService(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public Blog findById(String id) {
    Blog blog = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Blog.class);
    blog.setViewCount(blog.getViewCount()+1);
    return blog;
  }

  public Collection<BlogCard> findByCategory(String category) {
    Query query = new Query();
    query.addCriteria(Criteria.where("category").is(category));
    return mongoTemplate.find(query, Blog.class).stream()
      .map(this::toCard)
      .collect(Collectors.toSet());
  }

  public void deleteById(String id) {
    mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Blog.class);
  }

  public Blog addNewBlog(Blog blog) {
    blog.setVersion(1L);
    blog.setViewCount(0);
    blog.setBrief(new Description());
    blog.setTitle(new Description());
    blog.setContent(new Description());
    blog.setDate(LocalDateTime.now());
    return mongoTemplate.save(blog);
  }

  public Blog update(Blog blog) {
    blog.setUpdatedDate(LocalDateTime.now());
    return mongoTemplate.save(blog);
  }

  public Collection<BlogCard> findByKeyword(String keyword) {
    String searchTerm = keyword.replace(" ", "").replaceAll("[A-Z|a-z|0-9]", "$0.*?");
    return Stream.concat(
      mongoTemplate.find(new Query(Criteria.where("content.chinese").regex(searchTerm, "i")), Blog.class).stream().map(this::toCard),
      mongoTemplate.find(new Query(Criteria.where("content.english").regex(searchTerm, "i")), Blog.class).stream().map(this::toCard))
      .collect(Collectors.toSet());
  }

  public Collection<BlogCard> getBlogCards() {
    return mongoTemplate.findAll(Blog.class).stream()
      .map(this::toCard)
      .collect(Collectors.toList());
  }

  private BlogCard toCard(Blog blog) {
    BlogCard blogCard = new BlogCard();
    blogCard.setId(blog.getId());
    blogCard.setCategory(blog.getCategory());
    blogCard.setBrief(blog.getBrief());
    blogCard.setTitle(blog.getTitle());
    blogCard.setDate(blog.getDate());
    blogCard.setCoverageImage(blog.getCoverImage());
    blogCard.setViewCount(blog.getViewCount());
    return blogCard;
  }
}




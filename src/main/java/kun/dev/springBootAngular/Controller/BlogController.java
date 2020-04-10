package kun.dev.springBootAngular.Controller;

import kun.dev.springBootAngular.Domain.Blog;
import kun.dev.springBootAngular.Domain.BlogCard;
import kun.dev.springBootAngular.Domain.Blog;
import kun.dev.springBootAngular.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
  private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
  private BlogService blogService;

  public BlogController(BlogService blogService) {
    this.blogService = blogService;
  }

  @GetMapping("")
  public List<BlogCard> getAllBlogs() {
    return blogService.getBlogCards();
  }

  @GetMapping("/{id}")
  public Blog getBlogById(@PathVariable("id") String id) {
    return blogService.findById(id);
  }


  @GetMapping("/category/{name}")
  public Collection<BlogCard> getBlogsByCategory(@PathVariable("name") String name) {
    return blogService.findByCategory(name);
  }

  @PostMapping("")
  public Blog addNewBlog(@RequestBody Blog blog) {
    return blogService.addNewBlog(blog);
  }

  @PutMapping("")
  public Blog updateBlog(@RequestBody Blog blog) {
    logger.info("update blog" + blog);
    return blogService.update(blog);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteBlog(@PathVariable("id") String id) {
    logger.info("deleteBlog" + id);
    blogService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}

package dev.camilo.articlesservice.controller;

import dev.camilo.articlesservice.model.Article;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

  private final List<Article> articles = new ArrayList<>();

  @GetMapping
  public List<Article> findAll(){
    return articles;
  }

  @GetMapping("/{id}")
  public Optional<Article> findById(@PathVariable Integer id){
    return articles.stream().filter(article -> article.id().equals(id)).findFirst();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody Article article){
    articles.add(article);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody Article article, @PathVariable Integer id){
    Optional<Article> currentArticle = articles.stream().filter(a -> a.id().equals(id)).findFirst();
    currentArticle.ifPresent(articleExisting -> articles.set(articles.indexOf(articleExisting), article));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id){
    articles.removeIf(a -> a.id().equals(id));
  }


  @PostConstruct
  private void init(){
    Article article = new Article(1,"Hello, World", "This is my firts post");
    articles.add(article);
  }
}

//package id.ac.ui.cs.advprog.articleservice.service;
//import id.ac.ui.cs.advprog.articleservice.model.Article;
//import id.ac.ui.cs.advprog.articleservice.repository.ArticleRepository;
//import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.BillRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class PaymentServiceImplTest {
//
//    @Mock
//    private BillRepository billRepository;
//
//    @InjectMocks
//    private PaymentServiceImplTest paymentService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreateBill() {
//        List<Article> articles = new ArrayList<>();
//        var timestamp = Timestamp.valueOf(LocalDateTime.of(2023, 4,30,12, 12, 12));
//        articles.add(new Article(1, "Author 1", "Title 1", "Content 1", timestamp));
//        articles.add(new Article(2, "Author 2", "Title 2", "Content 2", timestamp));
//
//        when(articleRepository.findAll()).thenReturn(articles);
//        List<Article> actualArticles = articleService.getAllArticles();
//
//        verify(articleRepository, times(1)).findAll();
//        assertEquals(2, actualArticles.size());
//    }
//
//    @Test
//    void testFindArticleById() {
//        int id = 1;
//        Article expectedArticle = new Article(id, "Author 1", "Title 1", "Content 1", Timestamp.valueOf(LocalDateTime.of(2023, 4,30,12, 12, 12)));
//        when(articleRepository.findArticleById(id)).thenReturn(expectedArticle);
//
//        Article actualArticle = articleService.findArticleById(id);
//
//        verify(articleRepository, times(1)).findArticleById(id);
//        assertEquals(expectedArticle, actualArticle);
//    }
//
//    @Test
//    void testDeleteArticle() {
//        int id = 1;
//
//        articleService.deleteArticle(id);
//
//        verify(articleRepository, times(1)).deleteById(id);
//    }
//}

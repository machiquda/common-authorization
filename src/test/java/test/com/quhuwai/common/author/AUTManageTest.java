package test.com.quhuwai.common.author;

import com.quhuwai.common.author.dao.MngRoleInfoDAO;
import com.quhuwai.common.author.service.AuthorityManageService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fys on 2016/1/11.
 */
public class AUTManageTest {

    public static ApplicationContext context;
    public static MngRoleInfoDAO mngRoleInfoDAO;
    public static AuthorityManageService authorityManageService;

    @BeforeClass
    public static void initSpringContext() {
        context = new ClassPathXmlApplicationContext("spring-test.xml");
        authorityManageService = context.getBean("authorityManageServiceImpl", AuthorityManageService.class);
    }

    @Test
    public void distributionAutForRoleTest() {
        boolean a = false;
        try {
            a = authorityManageService.distributeAutToRole(5l, 3l, 9l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }
    @Test
    public void deleteAutOfRoleTest() {
        boolean a = false;
        try {
            a = authorityManageService.deleteAutOfRole(2l, 3l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }

    @Test
    public void disableAutOfRoleTest() {
        boolean a = false;
        try {
            a = authorityManageService.disableAutOfRole(4l, 3l,5l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }
    @Test
    public void disableAllAutOfRoleTest() {
        boolean a = false;
        try {
            a = authorityManageService.disableAllAutOfRole(3l,5l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }
    @Test
    public void verifyAuthorityOfRoleTest() {
        boolean a = false;
        try {
            a = authorityManageService.verifyAuthorityOfRole(3l, 3l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }
    @Test
    public void verifyAuthorityOfRole2Test() {
        boolean a = false;
        try {
            a = authorityManageService.verifyAuthorityOfRole("444",3l);

            a = authorityManageService.verifyAuthorityOfRole("444",3l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }

    @Test
      public void verifyAuthorityOfUserTest() {
        boolean a = false;
        try {
            a = authorityManageService.verifyAuthorityOfUser(3l, 2l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }
    @Test
    public void verifyAuthorityOfUser2Test() {
        boolean a = false;
        try {
            a = authorityManageService.verifyAuthorityOfUser("444", 2l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }

    @Test
    public void disableAllAutOfRole2Test() {
        boolean a = false;
        try {
            a = authorityManageService.disableAllAutOfRole(2l, 6l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }

    @Test
    public void addAuthorityTest() {
        boolean a = false;
        try {
           // a= authorityManageService.addAuthority("sfzfas", "444", 0L,5l);
            a= authorityManageService.deleteAuthority(2l);
        }catch (Exception E){

        }

        System.out.println(a);
    }

}

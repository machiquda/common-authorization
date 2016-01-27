package test.com.quhuwai.common.author;

import com.quhuwai.common.author.dao.MngRoleInfoDAO;
import com.quhuwai.common.author.domain.MngRoleInfoDO;
import com.quhuwai.common.author.service.RoleManageService;
import com.quhuwai.common.category.service.impl.CategoryCacheServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fys on 2016/1/10.
 */
public class RoleTest {

    public static ApplicationContext context;
    public static MngRoleInfoDAO mngRoleInfoDAO;
    public static RoleManageService roleManageService;
    public static CategoryCacheServiceImpl categoryCacheService;

    @BeforeClass
    public static void initSpringContext() {
        context = new ClassPathXmlApplicationContext("spring-test.xml");
        roleManageService = context.getBean("roleManageImpl", RoleManageService.class);
        categoryCacheService = context.getBean("categoryCacheServiceImpl", CategoryCacheServiceImpl.class);
    }

    @Test
    public void addRoleTest() {
        boolean a = false;
        try {
            categoryCacheService.refreshCache();
            //a = roleManageService.addRole("永远也一样", "88888",6l);
        } catch (Exception E) {

        }

        //System.out.println(a);
    }


    @Test
    public void deleteRoleTest() {
        boolean a = false;
        try {
            a = roleManageService.deleteRole(4l);
        } catch (Exception E) {

        }

        System.out.println(a);
    }

    @Test
    public void reSetRoleTest() {
        boolean a = false;
        try {
            a = roleManageService.resetRole(3l, "ccv", "cxc", 1, 5l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }

    @Test
    public void getRoleTest() {
        MngRoleInfoDO a = null;
        try {
            a = roleManageService.getRoleInfoById(3l);
        } catch (Exception E) {

        }
        System.out.println(a);
    }

    @Test
    public void distributionRoleForUserTest() {
        boolean a = false;
        int b = 0;
        Long[] c = {1l, 2l, 3l};
        try {

            //b =  mngRoleInfoDAO.distributeRoleToUser(c,2l);

            // b = roleManageService.distributeRoleToUser(c, 3l);
            b = roleManageService.distributeRoleToUser(c, 3l, 6l);
        } catch (Exception E) {

        }
        System.out.println(b);
    }
//    @Test
//    public void removeRoleOfUserTest() {
//        boolean a = false;
//        try {
//            a = roleManageService.removeRoleOfUser(1l);
//        } catch (Exception E) {
//
//        }
//        System.out.println(a);
//    }

}
package jdt.mantis.appmanager;

        import jdt.mantis.model.UsersData;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.boot.MetadataSources;
        import org.hibernate.boot.registry.StandardServiceRegistry;
        import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
        import org.openqa.selenium.By;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;
        import jdt.mantis.model.Users;

       import javax.mail.MessagingException;
        import java.io.IOException;
        import java.util.List;

        public class HBConnectionHelper {
   private final SessionFactory sessionFactory;
   public  HBConnectionHelper() {
        // A SessionFactory is set up once for an application!
               final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure() // configures settings from hibernate.cfg.xml
                        .build();
          sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
      }

           public Users users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <UsersData> result = session.createQuery("from UsersData where enabled = '1'").list();
       session.getTransaction().commit();
       session.close();
        return new Users(result);

              }

         }
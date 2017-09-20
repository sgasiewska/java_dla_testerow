package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.GroupData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HbConnectionTests {
  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }


  @Test
  public void testBbConnection(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
  //  List result = session.createQuery( "from GroupData" ).list();
    List result = session.createQuery( "from ContactData where deprecated= '0000-00-00'" ).list();
    //List result2 = session.createQuery("from address_in_groups where id = '95'").list();
 //   for ( GroupData group : (List <GroupData>) result) {
 //     System.out.println( group);
    /*  for ( ContactData contact : (List <ContactData>) result) {
        System.out.println( contact);
        System.out.println(contact.getGroups());
    }*/
    session.getTransaction().commit();
    session.close();

   for ( ContactData contact : (List <ContactData>) result) {
     System.out.println(contact);
     System.out.println(contact.getGroups());
   }
  }
}

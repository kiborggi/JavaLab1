import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HibernateSessionCreator {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            return new Configuration().configure(new File("hibernate.cgf.xml")).buildSessionFactory();

        }
        catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {

        getSessionFactory().close();
    }


    public static void saveMessage(Message message){
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(message);
        session.close();
    }

    public static ArrayList<Message> getMessagesFrom(int id){
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Message WHERE fromid = :paramid ");
        query.setParameter("paramid" , id);
        ArrayList<Message> messages = (ArrayList<Message>) query.list();
        return  messages;
    }
    public static ArrayList <Message> getMessagesTo(int id){
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Message where toid = :paramId");
        query.setParameter("paramId", id);
        ArrayList<Message> messages = (ArrayList<Message>) query.list();
        return  messages;
    }
}

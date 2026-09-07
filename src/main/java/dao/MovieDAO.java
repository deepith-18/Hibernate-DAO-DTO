package dao;

import dto.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MovieDAO {

    SessionFactory sf = new Configuration()
            .configure()
            .buildSessionFactory();

    public void insertMovie(Movie movie) {

        Session session = sf.openSession();

        session.beginTransaction();

        session.persist(movie);

        session.getTransaction().commit();

        session.close();
    }
}
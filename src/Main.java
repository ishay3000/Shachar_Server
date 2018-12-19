/*
import Ishay.MySqlUsersEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.util.TestUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

import javax.persistence.TypedQuery;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.exit;

enum Command{
    LOGIN, REGISTER
}
class TestCommand implements Serializable {
    private Command mCommand;
    private MySqlUsersEntity mUser;

    public TestCommand(Command mCommand, MySqlUsersEntity mUser) {
        this.mCommand = mCommand;
        this.mUser = mUser;
    }

    @Override
    public String toString() {
        return "TestCommand{" +
                "mCommand=" + mCommand +
                ", mUser=" + mUser +
                '}';
    }
}

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
//            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
*/
/*            Set<EntityType<?>> entities = session.getSessionFactory().getMetamodel().getEntities();
            Map<String, ClassMetadata> map = (Map<String, ClassMetadata>)session.getSessionFactory().getMetamodel();
            for(String entityName : map.keySet()){
                SessionFactoryImpl sfImpl = (SessionFactoryImpl) session.getSessionFactory();
                String tableName = ((AbstractEntityPersister)sfImpl.getEntityPersister(entityName)).getTableName();
                System.out.println(entityName + "\t" + tableName);
            }*//*

            Set<EntityType<?>> entities = session.getSessionFactory().getMetamodel().getEntities();
            List<?> classes = entities.stream()
                    .map(EntityType::getJavaType)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            MySqlUsersEntity usersEntity =
                    new MySqlUsersEntity(4, "Yoav", "IDK", "1234", "example@example.com");

            Transaction transaction = session.beginTransaction();
            session.save(usersEntity);
            transaction.commit();

            for (Object user :
                    session.createQuery("From MySqlUsersEntity ").getResultList()) {
                System.out.println(user);
            }

*/
/*            MySqlUsersEntity usersEntity =
                    new MySqlUsersEntity(2, "Dor", "SucksPHP", "1234", "DorScusk.PHP@gmail.com");

            TestCommand command = new TestCommand(Command.REGISTER, usersEntity);
            Gson gson = new Gson();
            String json = gson.toJson(command);
            System.out.println(">> JSON: " + json);

            TestCommand retrievedCommand = gson.fromJson(json, TestCommand.class);
            System.out.println(">> Retrieved: " + retrievedCommand);*//*


//            Query q = session.createQuery(entities.iterator().next().getName());

*/
/*            for (int i = 0; i < classes.size(); i++) {
                System.out.println(classes.get(0));

            }*//*


            */
/*List<?> classes = entities.stream()
                    .map(EntityType::getJavaType)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            for (int i = 0; i < classes.size(); i++) {
                System.out.println();
            }*//*


//            for (Object key : metadataMap.keySet()) {
//                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
//                final String entityName = classMetadata.getEntityName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
        } finally {
            session.close();
            exit(0);
        }
    }
}
*/

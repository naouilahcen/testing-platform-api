package ma.valueit.testingplatform.core.service;

import ma.valueit.testingplatform.core.repository.impl.BasicRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BasicRepositoryFactory<T extends JpaRepository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {

	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new DefaultRepositoryFactory(entityManager);
    }

    private class DefaultRepositoryFactory<I extends Serializable> extends JpaRepositoryFactory {
        private EntityManager entityManager;

        private DefaultRepositoryFactory(EntityManager entityManager) {

            super(entityManager);
            Assert.notNull(entityManager);
            this.entityManager = entityManager;
        }

       /** @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BasicRepositoryImpl(getEntityInformation(information.getDomainType()), this.entityManager);
        }
**/
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BasicRepositoryImpl.class;
        }
    }

    public BasicRepositoryFactory(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }
}

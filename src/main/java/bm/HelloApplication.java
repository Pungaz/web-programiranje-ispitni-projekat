package bm;

import bm.repositories.impl.CategoryRepositoryImpl;
import bm.repositories.impl.PostRepositoryImpl;
import bm.repositories.impl.PostTagRepositoryImpl;
import bm.repositories.impl.TagRepositoryImpl;
import bm.repositories.interfaces.CategoryRepository;
import bm.repositories.interfaces.PostRepository;
import bm.repositories.interfaces.PostTagRepository;
import bm.repositories.interfaces.TagRepository;
import bm.services.impl.CategoryServiceImpl;
import bm.services.impl.PostServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(CategoryRepositoryImpl.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(PostRepositoryImpl.class).to(PostRepository.class).in(Singleton.class);
                this.bind(TagRepositoryImpl.class).to(TagRepository.class).in(Singleton.class);
                this.bind(PostTagRepositoryImpl.class).to(PostTagRepository.class).in(Singleton.class);

                this.bindAsContract(CategoryServiceImpl.class);
                this.bindAsContract(PostServiceImpl.class);
            }
        };
        register(binder);
        packages("bm");
    }


}
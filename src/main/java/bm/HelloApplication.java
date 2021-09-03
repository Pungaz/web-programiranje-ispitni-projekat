package bm;

import bm.repositories.CategoryRepository;
import bm.repositories.PostRepository;
import bm.repositories.impl.CategoryRepositoryImpl;
import bm.repositories.impl.PostRepositoryImpl;
import bm.services.CategoryService;
import bm.services.PostService;
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

                this.bindAsContract(CategoryService.class);
                this.bindAsContract(PostService.class);
            }
        };
        register(binder);
        packages("bm");
    }


}
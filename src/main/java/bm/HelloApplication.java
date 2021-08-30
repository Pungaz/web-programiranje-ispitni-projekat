package bm;

import bm.services.CategoryService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import bm.repositories.CategoryRepository;
import bm.repositories.impl.CategoryRepositoryImpl;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(CategoryRepositoryImpl.class).to(CategoryRepository.class).in(Singleton.class);

                this.bindAsContract(CategoryService.class);
            }
        };
        register(binder);
        packages("bm");
    }


}
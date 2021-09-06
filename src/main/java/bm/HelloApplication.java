package bm;

import bm.repositories.impl.*;
import bm.repositories.interfaces.*;
import bm.services.impl.CategoryServiceImpl;
import bm.services.impl.CommentServiceImpl;
import bm.services.impl.PostServiceImpl;
import bm.services.impl.UserServiceImpl;
import bm.services.interfaces.CategoryService;
import bm.services.interfaces.CommentService;
import bm.services.interfaces.PostService;
import bm.services.interfaces.UserService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(CategoryRepositoryImpl.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(PostRepositoryImpl.class).to(PostRepository.class).in(Singleton.class);
                this.bind(TagRepositoryImpl.class).to(TagRepository.class).in(Singleton.class);
                this.bind(PostTagRepositoryImpl.class).to(PostTagRepository.class).in(Singleton.class);
                this.bind(CommentRepositoryImpl.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(UserRepositoryImpl.class).to(UserRepository.class).in(Singleton.class);

                this.bindAsContract(CategoryServiceImpl.class).to(CategoryService.class).in(Singleton.class);
                this.bindAsContract(PostServiceImpl.class).to(PostService.class).in(Singleton.class);
                this.bindAsContract(CommentServiceImpl.class).to(CommentService.class).in(Singleton.class);
                this.bindAsContract(UserServiceImpl.class).to(UserService.class).in(Singleton.class);
            }
        };
        register(binder);
        packages("bm");
    }
}
package alexey.moviedbapp.app.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Alexey Lyubchenko.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
